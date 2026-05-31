/**
 * ScriptedEventLoop — boucle d'événements déterministe pour les tests (Redmine #89).
 *
 * Permet de piloter un [ApplicationHandler] avec une séquence d'événements scriptée,
 * sans dépendre d'un backend natif (AppKit, Win32, X11…). Retourne la trace ordonnée
 * des callbacks invoqués, ce qui permet d'asserter l'ordre du cycle de vie, le
 * dispatch des événements, le flux de sortie, etc.
 *
 * ## Exemple
 * ```kotlin
 * val trace = scriptedTest {
 *     keyPress(Key.ArrowUp)
 *     tick(16)
 *     keyRelease(Key.ArrowUp)
 *     closeRequested()
 * }.run(MonHandler())
 *
 * assertEquals(Callback.Resumed, trace.first())
 * ```
 */
package org.graphiks.kadre.test

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.DeviceId
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId

// ---------------------------------------------------------------------------
// Trace de callbacks
// ---------------------------------------------------------------------------

/**
 * Élément de trace : un callback d'[ApplicationHandler] invoqué par la boucle scriptée.
 *
 * Comparable par valeur (data) pour permettre des assertions d'égalité directes.
 */
sealed interface Callback {
    /** [ApplicationHandler.resumed] invoqué. */
    data object Resumed : Callback

    /** [ApplicationHandler.canCreateSurfaces] invoqué. */
    data object CanCreateSurfaces : Callback

    /** [ApplicationHandler.newEvents] invoqué avec la cause donnée. */
    data class NewEvents(val cause: StartCause) : Callback

    /** [ApplicationHandler.windowEvent] invoqué. */
    data class WindowEventCb(val windowId: WindowId, val event: Any) : Callback

    /** [ApplicationHandler.deviceEvent] invoqué. */
    data class DeviceEventCb(val deviceId: DeviceId, val event: Any) : Callback

    /** [ApplicationHandler.aboutToWait] invoqué. */
    data object AboutToWait : Callback

    /** [ApplicationHandler.suspended] invoqué. */
    data object Suspended : Callback
}

// ---------------------------------------------------------------------------
// Événements scriptés
// ---------------------------------------------------------------------------

/**
 * Un événement de la séquence scriptée, interprété par [ScriptedEventLoop].
 */
sealed interface ScriptedEvent {
    /** Déclenche [ApplicationHandler.canCreateSurfaces]. */
    data object CanCreateSurfaces : ScriptedEvent

    /** Dispatche un [WindowEvent] vers [ApplicationHandler.windowEvent]. */
    data class Window(val windowId: WindowId, val event: WindowEvent) : ScriptedEvent

    /** Dispatche un événement périphérique vers [ApplicationHandler.deviceEvent]. */
    data class Device(val deviceId: DeviceId, val event: Any) : ScriptedEvent

    /**
     * Simule une frame : [ApplicationHandler.newEvents] (Poll) →
     * [WindowEvent.RedrawRequested] → [ApplicationHandler.aboutToWait].
     *
     * @property dtMs Durée virtuelle écoulée (informative — la boucle est déterministe).
     */
    data class Tick(val dtMs: Long) : ScriptedEvent
}

// ---------------------------------------------------------------------------
// Fenêtre mockée
// ---------------------------------------------------------------------------

/**
 * Implémentation [Window] en mémoire pour les tests — aucun handle natif réel.
 *
 * [requestRedraw] est enregistré (compteur [redrawRequests]) mais ne déclenche
 * pas de frame automatiquement : c'est le script ([ScriptedEvent.Tick]) qui pilote
 * les frames, pour rester déterministe.
 */
class ScriptedWindow(
    override val id: WindowId = WindowId(1L),
    private var size: PhysicalSize<Int> = PhysicalSize(800, 600),
    override val scaleFactor: Double = 1.0,
) : Window {

    override val rawWindowHandle: Any = RawWindowHandle.Web(canvasElementId = "scripted-window")
    override val rawDisplayHandle: Any = RawDisplayHandle.Web

    /** Nombre d'appels à [requestRedraw] — utile pour asserter le rendu continu. */
    var redrawRequests: Int = 0
        private set

    /** Titre courant (dernier passé à [setTitle]). */
    var title: String = "scripted"
        private set

    /** Visibilité courante. */
    var visible: Boolean = true
        private set

    override fun requestRedraw() { redrawRequests++ }
    override fun setTitle(title: String) { this.title = title }
    override val innerSize: PhysicalSize<Int> get() = size
    override val outerSize: PhysicalSize<Int> get() = size
    override fun setVisible(visible: Boolean) { this.visible = visible }
    override fun close() { /* no-op en mémoire */ }
}

// ---------------------------------------------------------------------------
// Boucle scriptée
// ---------------------------------------------------------------------------

/**
 * [ActiveEventLoop] déterministe qui rejoue une liste d'[ScriptedEvent] et
 * enregistre la trace des callbacks invoqués.
 *
 * Cycle : `resumed` → (chaque ScriptedEvent) → `suspended`. Si le handler appelle
 * [exit] pendant le traitement d'un événement, les événements restants sont ignorés
 * (mais `suspended` est tout de même invoqué).
 *
 * @property events  Séquence à rejouer.
 * @property window  Fenêtre mockée exposée par [createWindow].
 */
class ScriptedEventLoop(
    private val events: List<ScriptedEvent>,
    val window: ScriptedWindow = ScriptedWindow(),
) : ActiveEventLoop {

    private var _controlFlow: ControlFlow = ControlFlow.Wait
    private var _isExiting = false
    private val trace = mutableListOf<Callback>()

    // ── ActiveEventLoop ─────────────────────────────────────────────────────

    override fun createWindow(attributes: WindowAttributes): Window = window
    override fun setControlFlow(controlFlow: ControlFlow) { _controlFlow = controlFlow }
    override val controlFlow: ControlFlow get() = _controlFlow
    override fun exit() { _isExiting = true }
    override val isExiting: Boolean get() = _isExiting
    override fun createProxy(): EventLoopProxy = object : EventLoopProxy {
        override fun wakeUp() { /* no-op : exécution mono-thread déterministe */ }
    }

    // ── Exécution ───────────────────────────────────────────────────────────

    /**
     * Rejoue la séquence sur [handler] et retourne la trace ordonnée des callbacks.
     *
     * @param handler Gestionnaire à tester.
     * @return Trace immuable des callbacks invoqués, dans l'ordre.
     */
    fun run(handler: ApplicationHandler): List<Callback> {
        record(Callback.Resumed) { handler.resumed(this) }

        for (event in events) {
            if (_isExiting) break
            when (event) {
                is ScriptedEvent.CanCreateSurfaces ->
                    record(Callback.CanCreateSurfaces) { handler.canCreateSurfaces(this) }

                is ScriptedEvent.Window ->
                    record(Callback.WindowEventCb(event.windowId, event.event)) {
                        handler.windowEvent(this, event.windowId, event.event)
                    }

                is ScriptedEvent.Device ->
                    record(Callback.DeviceEventCb(event.deviceId, event.event)) {
                        handler.deviceEvent(this, event.deviceId, event.event)
                    }

                is ScriptedEvent.Tick -> {
                    record(Callback.NewEvents(StartCause.Poll)) { handler.newEvents(this, StartCause.Poll) }
                    if (!_isExiting) record(Callback.WindowEventCb(window.id, WindowEvent.RedrawRequested)) {
                        handler.windowEvent(this, window.id, WindowEvent.RedrawRequested)
                    }
                    if (!_isExiting) record(Callback.AboutToWait) { handler.aboutToWait(this) }
                }
            }
        }

        record(Callback.Suspended) { handler.suspended(this) }
        return trace.toList()
    }

    private inline fun record(callback: Callback, invoke: () -> Unit) {
        trace += callback
        invoke()
    }
}

// ---------------------------------------------------------------------------
// DSL
// ---------------------------------------------------------------------------

/**
 * Constructeur de séquence pour [scriptedTest]. Chaque méthode ajoute un
 * [ScriptedEvent] à la séquence dans l'ordre d'appel.
 */
class ScriptBuilder {
    private val events = mutableListOf<ScriptedEvent>()
    private val windowId = WindowId(1L)

    /** Autorise la création de surfaces (déclenche `canCreateSurfaces`). */
    fun canCreateSurfaces() { events += ScriptedEvent.CanCreateSurfaces }

    /** Enfonce une touche logique. */
    fun keyPress(key: Key, modifiers: Modifiers = Modifiers.NONE) {
        events += ScriptedEvent.Window(windowId, WindowEvent.KeyboardInput(key, KeyState.Pressed, modifiers))
    }

    /** Relâche une touche logique. */
    fun keyRelease(key: Key, modifiers: Modifiers = Modifiers.NONE) {
        events += ScriptedEvent.Window(windowId, WindowEvent.KeyboardInput(key, KeyState.Released, modifiers))
    }

    /** Déplace le pointeur. */
    fun pointerMove(x: Double, y: Double) {
        events += ScriptedEvent.Window(windowId, WindowEvent.PointerMoved(PhysicalPosition(x, y)))
    }

    /** Clic souris (press + release implicite selon [state]). */
    fun mouseInput(button: MouseButton, state: KeyState) {
        events += ScriptedEvent.Window(windowId, WindowEvent.MouseInput(button, state))
    }

    /** Redimensionne la fenêtre. */
    fun resized(width: Int, height: Int) {
        events += ScriptedEvent.Window(windowId, WindowEvent.Resized(PhysicalSize(width, height)))
    }

    /** Changement de facteur d'échelle (DPI). */
    fun scaleFactorChanged(factor: Double) {
        events += ScriptedEvent.Window(windowId, WindowEvent.ScaleFactorChanged(factor))
    }

    /** Simule une frame (newEvents → RedrawRequested → aboutToWait). */
    fun tick(dtMs: Long = 16L) { events += ScriptedEvent.Tick(dtMs) }

    /** Demande de fermeture de la fenêtre. */
    fun closeRequested() {
        events += ScriptedEvent.Window(windowId, WindowEvent.CloseRequested)
    }

    /** Événement de fenêtre brut (échappatoire pour les cas non couverts). */
    fun windowEvent(event: WindowEvent) {
        events += ScriptedEvent.Window(windowId, event)
    }

    internal fun build(): List<ScriptedEvent> = events.toList()
}

/**
 * Point d'entrée du DSL : construit une [ScriptedEventLoop] à partir d'un bloc
 * de séquence. Appeler [ScriptedEventLoop.run] avec le handler à tester.
 *
 * ```kotlin
 * val trace = scriptedTest {
 *     canCreateSurfaces()
 *     keyPress(Key.ArrowUp); tick(); keyRelease(Key.ArrowUp)
 *     closeRequested()
 * }.run(handler)
 * ```
 */
fun scriptedTest(block: ScriptBuilder.() -> Unit): ScriptedEventLoop =
    ScriptedEventLoop(ScriptBuilder().apply(block).build())
