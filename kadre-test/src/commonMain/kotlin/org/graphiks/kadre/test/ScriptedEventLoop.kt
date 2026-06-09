/**
 * ScriptedEventLoop — deterministic event loop for tests.
 *
 * Enables driving an [ApplicationHandler] with a scripted event sequence,
 * without depending on a native backend (AppKit, Win32, X11…). Returns the ordered trace
 * of invoked callbacks, allowing assertions on lifecycle order, event dispatch,
 * output stream, etc.
 *
 * ## Example
 * ```kotlin
 * val trace = scriptedTest {
 *     physicalKeyPress(KeyCode.ArrowUp)
 *     tick(16)
 *     physicalKeyRelease(KeyCode.ArrowUp)
 *     closeRequested()
 * }.run(MonHandler())
 *
 * assertEquals(Callback.Resumed, trace.first())
 * ```
 */
package org.graphiks.kadre.test

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.CursorIcon
import org.graphiks.kadre.core.DeviceEvent
import org.graphiks.kadre.core.DeviceEvents
import org.graphiks.kadre.core.DeviceId
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.Fullscreen
import org.graphiks.kadre.core.Icon
import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyEvent
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.LogicalKey
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.NativeKeyInfo
import org.graphiks.kadre.core.PhysicalKey
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.RequestError
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.WindowLevel
import org.graphiks.kadre.core.WindowRequestResult
import org.graphiks.kadre.core.defaultLogicalKey
import org.graphiks.kadre.core.location

// ---------------------------------------------------------------------------
// Callback trace
// ---------------------------------------------------------------------------

/**
 * Trace element: an [ApplicationHandler] callback invoked by the scripted loop.
 *
 * Value-comparable (data) to allow direct equality assertions.
 */
sealed interface Callback {
    /** [ApplicationHandler.resumed] invoked. */
    data object Resumed : Callback

    /** [ApplicationHandler.canCreateSurfaces] invoked. */
    data object CanCreateSurfaces : Callback

    /** [ApplicationHandler.newEvents] invoked with the given cause. */
    data class NewEvents(val cause: StartCause) : Callback

    /** [ApplicationHandler.windowEvent] invoked. */
    data class WindowEventCb(val windowId: WindowId, val event: WindowEvent) : Callback

    /** [ApplicationHandler.deviceEvent] invoked. */
    data class DeviceEventCb(val deviceId: DeviceId, val event: DeviceEvent) : Callback

    /** [ApplicationHandler.aboutToWait] invoked. */
    data object AboutToWait : Callback

    /** [ApplicationHandler.suspended] invoked. */
    data object Suspended : Callback
}

// ---------------------------------------------------------------------------
// Scripted events
// ---------------------------------------------------------------------------

/**
 * An event from the scripted sequence, interpreted by [ScriptedEventLoop].
 */
sealed interface ScriptedEvent {
    /** Triggers [ApplicationHandler.canCreateSurfaces]. */
    data object CanCreateSurfaces : ScriptedEvent

    /** Dispatches a [WindowEvent] to [ApplicationHandler.windowEvent]. */
    data class Window(val windowId: WindowId, val event: WindowEvent) : ScriptedEvent

    /** Dispatches a device event to [ApplicationHandler.deviceEvent]. */
    data class Device(val deviceId: DeviceId, val event: DeviceEvent) : ScriptedEvent

    /**
     * Simulates a frame: [ApplicationHandler.newEvents] (Poll) →
     * [WindowEvent.RedrawRequested] → [ApplicationHandler.aboutToWait].
     *
     * @property dtMs Virtual elapsed time (informational — the loop is deterministic).
     */
    data class Tick(val dtMs: Long, val windowId: WindowId = WindowId(1L)) : ScriptedEvent
}

// ---------------------------------------------------------------------------
// Mocked window
// ---------------------------------------------------------------------------

/**
 * In-memory [Window] implementation for tests — no real native handle.
 *
 * [requestRedraw] is recorded (counter [redrawRequests]) but does not trigger
 * a frame automatically: the script ([ScriptedEvent.Tick]) drives frames
 * to remain deterministic.
 */
class ScriptedWindow(
    override val id: WindowId = WindowId(1L),
    private var size: PhysicalSize<Int> = PhysicalSize(800, 600),
    override val scaleFactor: Double = 1.0,
) : Window {

    override val rawWindowHandle: RawWindowHandle =
        RawWindowHandle.Web(canvasElementId = "scripted-window-${id.value}")
    override val rawDisplayHandle: RawDisplayHandle = RawDisplayHandle.Web

    /** Number of calls to [requestRedraw] — useful for asserting continuous rendering. */
    var redrawRequests: Int = 0
        private set

    /** Current visibility. */
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
    override val isVisible: Boolean? get() = visible
    override fun close() { /* no-op in memory */ }

    // R1 implementations (in-memory)
    override fun setResizable(resizable: Boolean) { _isResizable = resizable }
    override val isResizable: Boolean get() = _isResizable
    override fun setMinimized(minimized: Boolean) { _isMinimized = minimized }
    override val isMinimized: Boolean? get() = _isMinimized
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
    override fun setCursorGrab(mode: CursorGrabMode): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("Scripted test window does not support cursor grab"))
    override fun setCursorPosition(position: PhysicalPosition<Int>): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("Scripted test window does not support cursor warping"))
    override fun setCursorHittest(hittest: Boolean): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("Scripted test window does not support cursor hit-testing"))
    override val theme: Theme? get() = null
    override fun setTheme(theme: Theme?) {}
    override fun setWindowLevel(level: WindowLevel) {}
    override fun setTransparent(transparent: Boolean) {}
    override fun setBlur(blur: Boolean) {}
    override fun setWindowIcon(icon: Icon?) {}

    /** No-op in scripted test: dead-key state is not simulated. */
    override fun resetDeadKeys() { /* no-op in scripted test */ }

    internal fun apply(attributes: WindowAttributes) {
        _title = attributes.title
        size = attributes.size ?: size
        visible = attributes.visible
        _isResizable = attributes.resizable
        _isMaximized = attributes.maximized
        _isDecorated = attributes.decorations
        _fullscreen = attributes.fullscreen
    }
}

// ---------------------------------------------------------------------------
// Scripted loop
// ---------------------------------------------------------------------------

/**
 * Deterministic [ActiveEventLoop] that replays a list of [ScriptedEvent] and
 * records the trace of invoked callbacks.
 *
 * Cycle: `resumed` → (each ScriptedEvent) → `suspended`. If the handler calls
 * [exit] while processing an event, remaining events are skipped
 * (but `suspended` is still invoked).
 *
 * @property events  Sequence to replay.
 * @property window  Mocked window exposed by [createWindow].
 */
class ScriptedEventLoop(
    private val events: List<ScriptedEvent>,
    val window: ScriptedWindow = ScriptedWindow(),
) : ActiveEventLoop {

    private var _controlFlow: ControlFlow = ControlFlow.Wait
    private var _isExiting = false
    private val trace = mutableListOf<Callback>()
    private val windowsById = linkedMapOf(window.id to window)
    private var firstWindowPending = true
    private var nextWindowIdValue: Long = window.id.value + 1

    /** Windows created by the loop, in deterministic creation order. */
    val windows: List<ScriptedWindow> get() = windowsById.values.toList()

    // ── ActiveEventLoop ─────────────────────────────────────────────────────

    override fun createWindow(attributes: WindowAttributes): Window {
        if (firstWindowPending) {
            firstWindowPending = false
            window.apply(attributes)
            return window
        }

        val nextWindow = ScriptedWindow(
            id = WindowId(nextWindowIdValue++),
            size = attributes.size ?: PhysicalSize(800, 600),
        ).also { it.apply(attributes) }
        windowsById[nextWindow.id] = nextWindow
        return nextWindow
    }
    override fun setControlFlow(controlFlow: ControlFlow) { _controlFlow = controlFlow }
    override val controlFlow: ControlFlow get() = _controlFlow
    override fun exit() { _isExiting = true }
    override val isExiting: Boolean get() = _isExiting
    override fun createProxy(): EventLoopProxy = object : EventLoopProxy {
        override fun wakeUp() { /* no-op: deterministic single-thread execution */ }
    }

    // R2 stubs
    override fun availableMonitors(): List<MonitorHandle> = emptyList()
    override fun primaryMonitor(): MonitorHandle? = null

    // R3 stub
    override fun systemTheme(): Theme? = null

    // R4 stub — no-op, device-event filtering not simulated in scripted tests
    override fun listenDeviceEvents(mode: DeviceEvents) { /* no-op in scripted test */ }

    // ── Execution ───────────────────────────────────────────────────────────

    /**
     * Replays the sequence on [handler] and returns the ordered trace of callbacks.
     *
     * @param handler Handler under test.
     * @return Immutable list of invoked callbacks, in order.
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
                    if (!_isExiting) record(Callback.WindowEventCb(event.windowId, WindowEvent.RedrawRequested)) {
                        handler.windowEvent(this, event.windowId, WindowEvent.RedrawRequested)
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
 * Sequence builder for [scriptedTest]. Each method adds a
 * [ScriptedEvent] to the sequence in call order.
 */
class ScriptBuilder {
    private val events = mutableListOf<ScriptedEvent>()
    private val windowId = WindowId(1L)

    /** Allows surface creation (triggers `canCreateSurfaces`). */
    fun canCreateSurfaces() { events += ScriptedEvent.CanCreateSurfaces }

    /** Presses a physical key. */
    fun physicalKeyPress(
        keyCode: KeyCode,
        modifiers: KeyboardModifiers = KeyboardModifiers.NONE,
        logicalKey: LogicalKey = keyCode.defaultLogicalKey(),
        text: String? = logicalKey.defaultText(),
        repeat: Boolean = false,
        windowId: WindowId = this.windowId,
    ) {
        keyInput(keyCode, logicalKey, KeyState.Pressed, modifiers, text, repeat, windowId)
    }

    /** Releases a physical key. */
    fun physicalKeyRelease(
        keyCode: KeyCode,
        modifiers: KeyboardModifiers = KeyboardModifiers.NONE,
        logicalKey: LogicalKey = keyCode.defaultLogicalKey(),
        windowId: WindowId = this.windowId,
    ) {
        keyInput(keyCode, logicalKey, KeyState.Released, modifiers, text = null, repeat = false, windowId)
    }

    /** Presses a logical key without constraining the physical key. */
    fun logicalKeyPress(
        logicalKey: LogicalKey,
        modifiers: KeyboardModifiers = KeyboardModifiers.NONE,
        text: String? = (logicalKey as? LogicalKey.Character)?.text,
        repeat: Boolean = false,
        windowId: WindowId = this.windowId,
    ) {
        events += ScriptedEvent.Window(
            windowId,
            WindowEvent.KeyInput(
                KeyEvent(
                    physicalKey = PhysicalKey.Unidentified,
                    logicalKey = logicalKey,
                    state = KeyState.Pressed,
                    modifiers = modifiers,
                    repeat = repeat,
                    text = text,
                ),
            ),
        )
    }

    /** Releases a logical key without constraining the physical key. */
    fun logicalKeyRelease(
        logicalKey: LogicalKey,
        modifiers: KeyboardModifiers = KeyboardModifiers.NONE,
        windowId: WindowId = this.windowId,
    ) {
        events += ScriptedEvent.Window(
            windowId,
            WindowEvent.KeyInput(
                KeyEvent(
                    physicalKey = PhysicalKey.Unidentified,
                    logicalKey = logicalKey,
                    state = KeyState.Released,
                    modifiers = modifiers,
                ),
            ),
        )
    }

    private fun keyInput(
        keyCode: KeyCode,
        logicalKey: LogicalKey,
        state: KeyState,
        modifiers: KeyboardModifiers,
        text: String?,
        repeat: Boolean,
        windowId: WindowId = this.windowId,
    ) {
        val physicalKey = PhysicalKey.Code(keyCode)
        events += ScriptedEvent.Window(
            windowId,
            WindowEvent.KeyInput(
                KeyEvent(
                    physicalKey = physicalKey,
                    logicalKey = logicalKey,
                    state = state,
                    modifiers = modifiers,
                    repeat = repeat,
                    location = physicalKey.location(),
                    text = text,
                    native = NativeKeyInfo(keyCode = keyCode.name),
                ),
            ),
        )
    }

    /** Moves the pointer. */
    fun pointerMove(x: Double, y: Double, windowId: WindowId = this.windowId) {
        events += ScriptedEvent.Window(
            windowId,
            WindowEvent.PointerMoved(
                deviceId = null,
                position = PhysicalPosition(x, y),
                primary = true,
                source = PointerSource.Mouse,
            ),
        )
    }

    /** Mouse click (implicit press + release depending on [state]). */
    fun pointerButton(
        button: MouseButton,
        state: KeyState,
        x: Double = 0.0,
        y: Double = 0.0,
        windowId: WindowId = this.windowId,
    ) {
        events += ScriptedEvent.Window(
            windowId,
            WindowEvent.PointerButton(
                deviceId = null,
                state = state,
                position = PhysicalPosition(x, y),
                primary = true,
                button = ButtonSource.Mouse(button),
            ),
        )
    }

    /** Migration alias for legacy mouse tests. */
    fun mouseInput(button: MouseButton, state: KeyState, windowId: WindowId = this.windowId) {
        pointerButton(button, state, windowId = windowId)
    }

    /** Mouse or trackpad scroll. */
    fun mouseWheel(
        deltaX: Double,
        deltaY: Double,
        phase: TouchPhase = TouchPhase.Moved,
        windowId: WindowId = this.windowId,
    ) {
        events += ScriptedEvent.Window(windowId, WindowEvent.MouseWheel(null, deltaX, deltaY, phase))
    }

    /** Resizes the window. */
    fun resized(width: Int, height: Int, windowId: WindowId = this.windowId) {
        events += ScriptedEvent.Window(windowId, WindowEvent.Resized(PhysicalSize(width, height)))
    }

    /** Scale factor change (DPI). */
    fun scaleFactorChanged(factor: Double, windowId: WindowId = this.windowId) {
        events += ScriptedEvent.Window(windowId, WindowEvent.ScaleFactorChanged(factor))
    }

    /** Simulates a frame (newEvents → RedrawRequested → aboutToWait). */
    fun tick(dtMs: Long = 16L, windowId: WindowId = this.windowId) {
        events += ScriptedEvent.Tick(dtMs, windowId)
    }

    /** Window close request. */
    fun closeRequested(windowId: WindowId = this.windowId) {
        events += ScriptedEvent.Window(windowId, WindowEvent.CloseRequested)
    }

    /** Raw window event (escape hatch for uncovered cases). */
    fun windowEvent(event: WindowEvent, windowId: WindowId = this.windowId) {
        events += ScriptedEvent.Window(windowId, event)
    }

    internal fun build(): List<ScriptedEvent> = events.toList()
}

private fun LogicalKey.defaultText(): String? = (this as? LogicalKey.Character)?.text

/**
 * DSL entry point: builds a [ScriptedEventLoop] from a sequence
 * block. Call [ScriptedEventLoop.run] with the handler under test.
 *
 * ```kotlin
 * val trace = scriptedTest {
 *     canCreateSurfaces()
 *     physicalKeyPress(KeyCode.ArrowUp); tick(); physicalKeyRelease(KeyCode.ArrowUp)
 *     closeRequested()
 * }.run(handler)
 * ```
 */
fun scriptedTest(block: ScriptBuilder.() -> Unit): ScriptedEventLoop =
    ScriptedEventLoop(ScriptBuilder().apply(block).build())
