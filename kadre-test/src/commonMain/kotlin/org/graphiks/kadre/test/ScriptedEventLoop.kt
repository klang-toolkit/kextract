/**
 * ScriptedEventLoop — boucle d'événements déterministe pour les tests.
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
import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.CursorIcon
import org.graphiks.kadre.core.DeviceId
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.Fullscreen
import org.graphiks.kadre.core.Icon
import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.DeviceEvent
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.WindowLevel

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
    data class WindowEventCb(val windowId: WindowId, val event: WindowEvent) : Callback

    /** [ApplicationHandler.deviceEvent] invoqué. */
    data class DeviceEventCb(val deviceId: DeviceId, val event: DeviceEvent) : Callback

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
    data class Device(val deviceId: DeviceId, val event: DeviceEvent) : ScriptedEvent

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

    override val rawWindowHandle: RawWindowHandle = RawWindowHandle.Web(canvasElementId = "scripted-window")
    override val rawDisplayHandle: RawDisplayHandle = RawDisplayHandle.Web

    /** Nombre d'appels à [requestRedraw] — utile pour asserter le rendu continu. */
    var redrawRequests: Int = 0
        private set

    /** Visibilité courante. */
    var visible: Boolean = true
        private set

    // R1 state fields
    private var _title: String = "scripted"
    private var _isResizable: Boolean = true
    private var _isMinimized: Boolean = false
    private var _isMaximized: Boolean = false
    private var _isDecorated: Boolean = true
    private var _outerPosition: PhysicalPosition<Int> = PhysicalPosition(0, 0)

    override fun requestRedraw() { redrawRequests++ }
    override fun setTitle(title: String) { _title = title }
    override val title: String get() = _title
    override val innerSize: PhysicalSize<Int> get() = size
    override val outerSize: PhysicalSize<Int> get() = size
    override fun setVisible(visible: Boolean) { this.visible = visible }
    override val isVisible: Boolean get() = visible
    override fun close() { /* no-op in memory */ }

    // R1 implementations (in-memory)
    override fun setResizable(resizable: Boolean) { _isResizable = resizable }
    override val isResizable: Boolean get() = _isResizable
    override fun setMinimized(minimized: Boolean) { _isMinimized = minimized }
    override val isMinimized: Boolean get() = _isMinimized
    override fun setMaximized(maximized: Boolean) { _isMaximized = maximized }
    override val isMaximized: Boolean get() = _isMaximized
    override fun setDecorations(decorated: Boolean) { _isDecorated = decorated }
    override val isDecorated: Boolean get() = _isDecorated
    override fun setMinSurfaceSize(size: PhysicalSize<Int>?) { /* no-op in scripted test */ }
    override fun setMaxSurfaceSize(size: PhysicalSize<Int>?) { /* no-op in scripted test */ }
    override val outerPosition: PhysicalPosition<Int> get() = _outerPosition
    override fun setOuterPosition(position: PhysicalPosition<Int>) { _outerPosition = position }
    override fun prePresentNotify() { /* no-op in scripted test */ }

    // R2 stubs (in-memory)
    override fun currentMonitor(): MonitorHandle? = null
    private var _fullscreen: Fullscreen? = null
    override val fullscreen: Fullscreen? get() = _fullscreen
    override fun setFullscreen(fullscreen: Fullscreen?) { _fullscreen = fullscreen }

    // R3 stubs (in-memory no-ops)
    override fun setCursor(cursor: CursorIcon) {}
    override fun setCursorVisible(visible: Boolean) {}
    override fun setCursorGrab(mode: CursorGrabMode) {}
    override fun setCursorPosition(position: PhysicalPosition<Int>) {}
    override fun setCursorHittest(hittest: Boolean) {}
    override val theme: Theme? get() = null
    override fun setTheme(theme: Theme?) {}
    override fun setWindowLevel(level: WindowLevel) {}
    override fun setTransparent(transparent: Boolean) {}
    override fun setBlur(blur: Boolean) {}
    override fun setWindowIcon(icon: Icon?) {}
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

    // R2 stubs
    override fun availableMonitors(): List<MonitorHandle> = emptyList()
    override fun primaryMonitor(): MonitorHandle? = null

    // R3 stub
    override fun systemTheme(): Theme? = null

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
