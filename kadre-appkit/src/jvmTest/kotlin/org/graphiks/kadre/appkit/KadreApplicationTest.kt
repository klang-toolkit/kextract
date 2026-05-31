package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.NSApplication
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith

/**
 * Tests de compilation des classes GRA-125.
 *
 * Aucun test d'exécution réel : l'appel de `NSApp` nécessite le thread
 * principal macOS et est validé par l'application de démonstration M1
 * plutôt que par les tests unitaires.
 */
class KadreApplicationTest {

    @Test
    fun `KadreApplication est une sous-classe de NSApplication`() {
        // Vérification au niveau types : KadreApplication doit hériter
        // de NSApplication (cf. binding kextract).
        val nsAppClass = NSApplication::class.java
        assertTrue(nsAppClass.isAssignableFrom(KadreApplication::class.java))
    }

    /**
     * Redmine #41 : eventLoop doit être une propriété d'instance (non statique).
     *
     * Vérifie que :
     * - `KadreApplication` possède un champ `eventLoop` non-statique (backing field JVM).
     * - `KadreApplication` possède un champ `sharedApp` statique (équivalent de
     *   `NSApp as? KadreApplication`) — backing field du companion property.
     * - Aucun champ `eventLoop` statique n'existe (la variable statique globale a été supprimée).
     */
    @Test
    fun `eventLoop est une propriete d instance et non une variable statique`() {
        val allFields = KadreApplication::class.java.declaredFields

        // eventLoop doit exister comme champ d'instance (non statique)
        val eventLoopField = allFields.firstOrNull { it.name == "eventLoop" }
        assertNotNull(
            eventLoopField,
            "eventLoop doit être un champ de KadreApplication, champs trouvés : ${allFields.map { it.name }}"
        )
        assertTrue(
            !java.lang.reflect.Modifier.isStatic(eventLoopField!!.modifiers),
            "eventLoop doit être non-statique (propriété d'instance)"
        )

        // sharedApp doit être un champ statique (backing field du companion property)
        val sharedAppField = allFields.firstOrNull { it.name == "sharedApp" }
        assertNotNull(
            sharedAppField,
            "sharedApp doit exister dans KadreApplication pour remplacer NSApp as? KadreApplication"
        )
        assertTrue(
            java.lang.reflect.Modifier.isStatic(sharedAppField!!.modifiers),
            "sharedApp doit être statique (companion object property)"
        )
    }

    /**
     * Redmine #41 : sharedApp est initialement null avant tout appel à initialize().
     *
     * Garantit qu'il n'y a pas d'initialisation eagerly et que le pattern
     * "NSApp as? KadreApplication" ne retourne null que si initialize() n'a pas
     * encore été appelé.
     */
    @Test
    fun `sharedApp est null avant initialize`() {
        // Note : ce test suppose que initialize() n'a pas été appelé dans ce processus
        // de test, ce qui est le cas car les tests ne touchent pas au runtime macOS.
        assertNull(
            KadreApplication.sharedApp,
            "sharedApp doit être null si initialize() n'a jamais été appelé"
        )
    }

    @Test
    fun `KadreAppDelegate accepte un ApplicationHandler et un ActiveEventLoop`() {
        // Vérifie la signature du constructeur sans instancier l'objet ObjC
        // sous-jacent (qui nécessiterait le thread principal macOS).
        val ctor = KadreAppDelegate::class.java.constructors.first()
        val paramTypes = ctor.parameterTypes
        assertTrue(paramTypes.any { ApplicationHandler::class.java.isAssignableFrom(it) })
        assertTrue(paramTypes.any { ActiveEventLoop::class.java.isAssignableFrom(it) })
    }

    @Test
    fun `MainThreadCheck est invocable`() {
        // Le type compile et l'objet singleton est accessible.
        val check: MainThreadCheck = MainThreadCheck
        assertNotNull(check)
    }

    @Test
    fun `Callbacks expose des methodes JvmStatic`() {
        // Garantit que les trampolines requis par Linker.upcallStub
        // existent et sont statiques au niveau JVM.
        val didFinish = KadreAppDelegate.Callbacks::class.java
            .getDeclaredMethod(
                "applicationDidFinishLaunching",
                java.lang.foreign.MemorySegment::class.java,
                java.lang.foreign.MemorySegment::class.java,
                java.lang.foreign.MemorySegment::class.java,
            )
        val shouldTerminate = KadreAppDelegate.Callbacks::class.java
            .getDeclaredMethod(
                "applicationShouldTerminate",
                java.lang.foreign.MemorySegment::class.java,
                java.lang.foreign.MemorySegment::class.java,
                java.lang.foreign.MemorySegment::class.java,
            )
        assertTrue(java.lang.reflect.Modifier.isStatic(didFinish.modifiers))
        assertTrue(java.lang.reflect.Modifier.isStatic(shouldTerminate.modifiers))
    }

    /**
     * Redmine #41 — DoD #3 : un second appel à [runApp] doit lever [IllegalStateException].
     *
     * Stratégie :
     * 1. On force [appKitRunning] à true via réflexion sur la classe Kotlin générée
     *    (`AppKitEventLoopKt`) pour simuler une boucle déjà active.
     * 2. On appelle [runApp] depuis un thread quelconque (non-principal) : la garde
     *    AtomicBoolean est vérifiée AVANT [MainThreadCheck], donc l'exception est levée
     *    immédiatement sans interaction avec le runtime AppKit.
     * 3. Le champ est restauré à false en finally pour ne pas polluer les autres tests.
     */
    @Test
    fun `runApp double appel lance IllegalStateException`() {
        // Accès au AtomicBoolean de niveau fichier via la classe Kotlin générée
        val ktClass = Class.forName("org.graphiks.kadre.appkit.AppKitEventLoopKt")
        val runningField = ktClass.getDeclaredField("appKitRunning")
        runningField.isAccessible = true
        val running = runningField.get(null) as java.util.concurrent.atomic.AtomicBoolean

        val previousValue = running.get()
        try {
            // Simule une boucle déjà active
            running.set(true)

            val handler = NoopHandler()

            assertFailsWith<IllegalStateException>(
                message = "runApp() doit lever IllegalStateException si une boucle est déjà active"
            ) {
                // La garde check(appKitRunning.compareAndSet(false, true)) échoue en premier,
                // avant tout accès au runtime AppKit ou à MainThreadCheck.
                runApp(handler)
            }
        } finally {
            // Restaure l'état initial pour ne pas affecter les autres tests
            running.set(previousValue)
        }
    }

    /** Stub no-op pour valider la signature du constructeur de KadreAppDelegate. */
    @Suppress("unused")
    private class NoopHandler : ApplicationHandler {
        override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
        override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
    }

    /** Stub no-op pour valider la signature du constructeur de KadreAppDelegate. */
    @Suppress("unused")
    private class NoopEventLoop : ActiveEventLoop {
        override fun createWindow(attributes: WindowAttributes): Window =
            throw UnsupportedOperationException()
        override fun setControlFlow(controlFlow: ControlFlow) = Unit
        override val controlFlow: ControlFlow = ControlFlow.Poll
        override fun exit() = Unit
        override val isExiting: Boolean = false
        override fun createProxy(): EventLoopProxy = throw UnsupportedOperationException()
    }
}
