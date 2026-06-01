/**
 * Web event loop via requestAnimationFrame.
 *
 * ## Behavior per [ControlFlow] mode
 * - [ControlFlow.Wait]      : the loop waits for a DOM event before triggering a frame.
 *                             The next RAF is scheduled only when an event
 *                             arrives via [WebDomBridge.onWindowEvent].
 * - [ControlFlow.Poll]      : continuous RAF — a new frame is scheduled on each tick.
 * - [ControlFlow.WaitUntil] : setTimeout until the target instant, then a single RAF.
 *
 * ## webMain constraint
 * This file resides in `webMain` — NO DOM import is allowed here.
 * The actual RAF scheduling is delegated to the subclasses in
 * `jsMain` ([JsWebEventLoop]) and `wasmJsMain` ([WasmJsWebEventLoop]).
 *
 * ## Lifecycle
 * ```
 * runApp(handler)
 *   └─► handler.resumed(this)
 *   └─► handler.newEvents(this, StartCause.Init)
 *   └─► handler.aboutToWait(this)
 *   └─► scheduleNextFrame(handler)    ← RAF scheduled
 *         └─► tick(handler)           ← called back by the browser
 *               ├─ handler.newEvents(...)
 *               ├─ dispatch of the accumulated DOM events
 *               ├─ handler.aboutToWait(this)
 *               └─ scheduleNextFrame(handler)  ← next RAF (if !isExiting)
 * ```
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.VideoMode
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId

/**
 * Web event loop shared between the JS and wasmJs targets.
 *
 * This class is abstract: the concrete subclasses ([JsWebEventLoop] and
 * [WasmJsWebEventLoop]) provide access to `window.requestAnimationFrame`
 * via their target-specific APIs.
 *
 * ## Thread safety
 * JavaScript is single-threaded; the calls from `wakeUp()` are synchronous.
 */
open class WebEventLoop : ActiveEventLoop {

    // -------------------------------------------------------------------------
    // Internal state
    // -------------------------------------------------------------------------

    private var _controlFlow: ControlFlow = ControlFlow.Wait
    private var _isExiting = false

    /** List of active windows created by this loop. */
    private val windows = mutableListOf<WebWindow>()

    /** Queue of DOM events received between two frames. */
    private val pendingEvents = mutableListOf<Pair<WindowId, WebWindowEvent>>()

    // -------------------------------------------------------------------------
    // ActiveEventLoop
    // -------------------------------------------------------------------------

    override val controlFlow: ControlFlow get() = _controlFlow

    override fun setControlFlow(controlFlow: ControlFlow) {
        _controlFlow = controlFlow
    }

    override val isExiting: Boolean get() = _isExiting

    override fun exit() {
        _isExiting = true
    }

    /**
     * Creates a web window from the core [WindowAttributes] contract.
     *
     * **Legacy**: uses `attributes.title` as the canvas CSS `id` (a non-idiomatic
     * convention, kept for backward compatibility). Prefer the
     * `createWindow(WebWindowAttributes)` overload, which explicitly exposes the canvas id
     * and the auto-creation mode (equivalent of winit's `WindowAttributesExtWebSys`
     * trait).
     */
    override fun createWindow(attributes: WindowAttributes): Window {
        return createWindow(
            WebWindowAttributes(
                canvasId = attributes.title.ifEmpty { null },
                core = attributes,
            )
        )
    }

    /**
     * Creates a web window from the web-only extension [WebWindowAttributes].
     *
     * Inspired by winit's `WindowAttributesExtWebSys` trait:
     *
     *  - Targets an existing DOM `<canvas>` via [WebWindowAttributes.canvasId], OR
     *  - Lets Kadre create a `<canvas>` ([WebWindowAttributes.appendToBody] = true).
     *
     * The DOM bridge is instantiated by [createDomBridge] then:
     *  1. [WebDomBridge.ensureCanvas] resolves (or creates) the canvas.
     *  2. [WebDomBridge.attach] wires the DOM listeners (keydown, pointer, resize…).
     *
     * @param attrs Web-specific configuration of the window.
     * @return The created and attached [WebWindow].
     */
    fun createWindow(attrs: WebWindowAttributes): Window {
        val bridge = createDomBridge()
        val canvasId = bridge.ensureCanvas(attrs)
        val window = WebWindow(canvasId, bridge)

        // Initialise synchronously from the current DOM state (before any event fires).
        val (initW, initH) = bridge.readCanvasPhysicalSize(canvasId)
        window.updatePhysicalSize(initW, initH)
        window.updateScaleFactor(bridge.readDevicePixelRatio())

        bridge.onWindowEvent = { event ->
            // Keep WebWindow's cached state in sync with DOM events.
            when (event) {
                is WebWindowEvent.Resized ->
                    window.updatePhysicalSize(event.width, event.height)
                is WebWindowEvent.ScaleFactorChanged ->
                    window.updateScaleFactor(event.factor)
                else -> Unit
            }
            // Queue the event for dispatch on the next frame
            pendingEvents.add(Pair(windows.firstOrNull()?.id ?: WindowId(0L), event))
            // In Wait mode, wake the loop immediately
            if (_controlFlow is ControlFlow.Wait) {
                scheduleWakeUp()
            }
        }
        windows.add(window)
        bridge.attach(canvasId)
        return window
    }

    /**
     * Creates a thread-safe proxy to this event loop.
     *
     * In JavaScript (single-threaded), the proxy simply calls [scheduleWakeUp].
     */
    override fun createProxy(): EventLoopProxy = object : EventLoopProxy {
        override fun wakeUp() = scheduleWakeUp()
    }

    // ── R2: monitor enumeration ───────────────────────────────────────────────

    /**
     * Returns a synthetic monitor representing the browser window.
     *
     * Uses the current canvas size and device pixel ratio from the first window.
     * The Fullscreen API cannot expose physical monitor properties.
     */
    override fun availableMonitors(): List<MonitorHandle> {
        val win = windows.firstOrNull()
        val scale = win?._scaleFactor ?: 1.0
        val size = win?._physicalSize ?: PhysicalSize(1920, 1080)
        return listOf(syntheticWebMonitor(scale, size))
    }

    /**
     * Returns the single synthetic web monitor.
     */
    override fun primaryMonitor(): MonitorHandle? = availableMonitors().firstOrNull()

    // -------------------------------------------------------------------------
    // Public entry point
    // -------------------------------------------------------------------------



    /**
     * Starts the event loop and notifies the handler.
     *
     * Calls [ApplicationHandler.resumed], then [ApplicationHandler.canCreateSurfaces]
     * (the browser allows surface creation right from startup), then
     * [ApplicationHandler.newEvents] with [StartCause.Init], then
     * [ApplicationHandler.aboutToWait], and finally schedules the first frame via
     * [scheduleNextFrame].
     *
     * @param handler Handler for the application's lifecycle.
     */
    open fun runApp(handler: ApplicationHandler) {
        handler.resumed(this)
        // On the web, the canvas is available immediately: we allow surface
        // creation right away (parity with the AppKit/Win32 desktop loops).
        handler.canCreateSurfaces(this)
        handler.newEvents(this, StartCause.Init)
        handler.aboutToWait(this)
        if (!_isExiting) {
            scheduleNextFrame(handler)
        }
    }

    // -------------------------------------------------------------------------
    // Internal tick — called by RAF
    // -------------------------------------------------------------------------

    /**
     * Runs one iteration of the event loop.
     *
     * Called by `requestAnimationFrame` in the concrete subclasses.
     * Dispatches the accumulated DOM events, then schedules the next frame
     * if the loop is not shutting down.
     *
     * @param handler Lifecycle handler.
     * @param now     Timestamp provided by requestAnimationFrame (in ms, ignored here).
     */
    protected fun tick(handler: ApplicationHandler, now: Double = 0.0) {
        if (_isExiting) return

        // Determines the start cause of this iteration
        val cause: StartCause = when (val cf = _controlFlow) {
            is ControlFlow.Poll        -> StartCause.Poll
            is ControlFlow.Wait        -> StartCause.WaitCancelled()
            is ControlFlow.WaitUntil   -> StartCause.ResumeTimeReached(
                requestedResume = cf.instant,
                start = now.toLong()
            )
        }

        handler.newEvents(this, cause)

        // Dispatch the accumulated DOM events
        val snapshot = pendingEvents.toList()
        pendingEvents.clear()
        for ((windowId, event) in snapshot) {
            handler.windowEvent(this, windowId, event.toWindowEvent())
        }

        handler.aboutToWait(this)

        // Schedule the next frame according to the current mode
        if (!_isExiting) {
            scheduleNextFrame(handler)
        } else {
            // Notify the handler of the imminent end
            handler.suspended(this)
        }
    }

    // -------------------------------------------------------------------------
    // Methods extensible by subclasses
    // -------------------------------------------------------------------------

    /**
     * Schedules the next frame according to the current [ControlFlow].
     *
     * - [ControlFlow.Poll]      → immediate `requestAnimationFrame`
     * - [ControlFlow.Wait]      → waits for a DOM event ([scheduleWakeUp] will schedule the RAF)
     * - [ControlFlow.WaitUntil] → `setTimeout` until the target instant, then RAF
     *
     * The concrete subclasses must provide `requestAnimationFrame`
     * and `setTimeout` via the mechanisms specific to their target (JS or wasmJs).
     *
     * This method is `open` to allow overriding in tests.
     */
    protected open fun scheduleNextFrame(handler: ApplicationHandler) {
        // Stub: the concrete implementation is provided by JsWebEventLoop / WasmJsWebEventLoop
        // via requestAnimationFrame in jsMain / wasmJsMain.
    }

    /**
     * Schedules an immediate wake-up of the loop (single RAF).
     *
     * Used in [ControlFlow.Wait] mode when a DOM event arrives,
     * and by [createProxy] to wake the loop from another context.
     *
     * Stub — overridden in the subclasses to call `requestAnimationFrame`.
     */
    protected open fun scheduleWakeUp() {
        // Stub: overridden in JsWebEventLoop / WasmJsWebEventLoop
    }

    /**
     * Creates the DOM bridge appropriate to the compilation target.
     *
     * Overridden in [JsWebEventLoop] to return [JsWebDomBridge],
     * and in [WasmJsWebEventLoop] to return [WasmJsWebDomBridge].
     *
     * The default implementation returns a no-op bridge (useful for tests).
     */
    protected open fun createDomBridge(): WebDomBridge = object : WebDomBridge {
        override var onWindowEvent: ((WebWindowEvent) -> Unit)? = null
        override fun attach(targetElementId: String) {}
        override fun detach() {}
    }
}

/** Creates a synthetic [MonitorHandle] representing the browser window. */
internal fun syntheticWebMonitor(scale: Double, size: PhysicalSize<Int>): MonitorHandle =
    object : MonitorHandle {
        override val id: Long = 0L
        override val name: String? = null
        override val position: PhysicalPosition<Int> = PhysicalPosition(0, 0)
        override val scaleFactor: Double = scale
        override val currentVideoMode: VideoMode = VideoMode(size, null, null)
        override val videoModes: List<VideoMode> = listOf(currentVideoMode)
    }
