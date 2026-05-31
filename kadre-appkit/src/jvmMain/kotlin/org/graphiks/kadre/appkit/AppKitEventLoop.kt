/**
 * Implémentation AppKit de [ActiveEventLoop] et point d'entrée [runApp].
 *
 * [AppKitEventLoop] implémente [ActiveEventLoop] et est passé à chaque
 * callback de [ApplicationHandler]. La fonction top-level [runApp] orchestre
 * l'initialisation AppKit (KadreApplication + KadreAppDelegate + NSApp.run).
 *
 * GRA-128 : premier câblage complet — M1.
 * GRA-136 : ControlFlow effectif + CFRunLoopObserver + proxy thread-safe.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.NSApplicationActivationPolicy
import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.util.concurrent.ConcurrentHashMap

/**
 * Verrou global garantissant qu'une seule boucle d'événements AppKit est active
 * à la fois dans le processus. Redmine #41 — DoD #3.
 *
 * Utilisation de [java.util.concurrent.atomic.AtomicBoolean] pour la thread-safety :
 * [runApp] fait un CAS atomique false→true au démarrage et lève
 * [IllegalStateException] si la valeur était déjà true.
 */
internal val appKitRunning = java.util.concurrent.atomic.AtomicBoolean(false)

/**
 * Implémentation interne de [ActiveEventLoop] pour la plateforme AppKit (macOS).
 *
 * Une instance est créée par appel à [runApp] et passée comme récepteur
 * à tous les callbacks [ApplicationHandler].
 *
 * Périmètre M1 :
 * - [createWindow] : crée une [AppKitWindow] et lui installe un
 *   [KadreWindowDelegate] pour la gestion de la fermeture.
 * - [exit] : lève le drapeau [isExiting] puis déclenche
 *   `[NSApp terminate:nil]` pour quitter la boucle AppKit.
 * - [controlFlow] / [setControlFlow] : état piloté par [CFRunLoopRedrawObserver] (GRA-136).
 * - [createProxy] : implémenté via [AppKitEventLoopProxy] (GRA-136) — wakeUp thread-safe.
 */
internal class AppKitEventLoop(
    internal val handler: ApplicationHandler,
) : ActiveEventLoop {

    /** Fenêtres vivantes : windowId → AppKitWindow. */
    internal val windows = ConcurrentHashMap<Long, AppKitWindow>()

    @Volatile
    private var _isExiting = false

    override val isExiting: Boolean
        get() = _isExiting

    @Volatile
    private var _controlFlow: ControlFlow = ControlFlow.Wait

    override val controlFlow: ControlFlow
        get() = _controlFlow

    override fun setControlFlow(controlFlow: ControlFlow) {
        _controlFlow = controlFlow
    }

    /**
     * Crée une nouvelle fenêtre AppKit et installe le delegate de fermeture.
     *
     * Doit être appelé depuis le thread principal (validé par [AppKitWindow.init]).
     */
    override fun createWindow(attributes: WindowAttributes): Window {
        val window = AppKitWindow(attributes)
        window.setWindowDelegate(handler, this)
        windows[window.id.value] = window
        return window
    }

    /**
     * Demande l'arrêt de la boucle d'événements AppKit.
     *
     * Lève [isExiting] puis appelle `[NSApp terminate:nil]`, ce qui déclenche
     * `applicationShouldTerminate:` dans [KadreAppDelegate] — qui retourne
     * `NSTerminateNow` car [isExiting] vaut déjà true.
     */
    override fun exit() {
        _isExiting = true
        // Close all open windows before terminating
        windows.values.toList().forEach { window ->
            try { window.close() } catch (_: Exception) { /* ignore */ }
        }
        val nsAppClass = ObjCRuntime.getClass("NSApplication")
        val nsApp = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            nsAppClass,
            ObjCRuntime.sel("sharedApplication"),
        ) as MemorySegment
        ObjCRuntime.msgSend(null, nsApp, ObjCRuntime.sel("terminate:"), MemorySegment.NULL)
    }

    /**
     * Crée un [EventLoopProxy] dont [EventLoopProxy.wakeUp] est thread-safe
     * (GRA-136). Implémenté via `CFRunLoopWakeUp(CFRunLoopGetMain())` — voir
     * [AppKitEventLoopProxy].
     */
    override fun createProxy(): EventLoopProxy = AppKitEventLoopProxy.create()
}

/**
 * Point d'entrée de la boucle d'événements kadre sur macOS.
 *
 * Initialise AppKit, installe les delegates et lance la boucle bloquante
 * `NSApp.run()`. Ne retourne qu'à la fermeture de l'application.
 *
 * Doit être appelé depuis le thread principal macOS.
 *
 * @param handler Gestionnaire du cycle de vie et des événements.
 */
fun runApp(handler: ApplicationHandler) {
    check(appKitRunning.compareAndSet(false, true)) {
        "AppKitEventLoop.runApp() ne peut être appelé qu'une seule fois par processus. Une boucle d'événements AppKit est déjà active."
    }

    MainThreadCheck.require()

    val eventLoop = AppKitEventLoop(handler)

    // 1. Sous-classe KadreApplication + sharedApplication (mémorisé dans sharedApp)
    val app = KadreApplication.initialize()

    try {
        // 2. Câble la boucle sur l'instance — récupérée via sharedApp (NSApp as? KadreApplication)
        //    dans sendEvent:. Aucune variable statique mutable dédiée (Redmine #41).
        app.eventLoop = eventLoop

        // 3. Politique d'activation : application régulière (icône dans le Dock)
        app.setActivationPolicyRegular()

        // 4. Délégué d'application — câble canCreateSurfaces / shouldTerminate
        val appDelegate = KadreAppDelegate(handler, eventLoop)
        app.setDelegate(appDelegate.ptr)

        // 5. Installer l'observer CFRunLoop pour le coalescing RedrawRequested (GRA-134)
        CFRunLoopRedrawObserver.install(handler, eventLoop, eventLoop.windows)

        // 6. Lance la boucle bloquante AppKit — retourne à la fermeture
        app.run()
    } finally {
        // Nettoyage : libère les références et remet le verrou à false
        // pour permettre un éventuel re-démarrage (tests ou processus réentrants).
        app.eventLoop = null
        KadreApplication.sharedApp = null
        appKitRunning.set(false)
    }
}
