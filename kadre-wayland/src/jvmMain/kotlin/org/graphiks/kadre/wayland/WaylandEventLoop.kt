/**
 * WaylandEventLoop — Wayland event loop for kadre.
 *
 * Implements the canonical Wayland prepare_read / poll / read_events sequence
 * with an eventfd for inter-thread wakeup (wakeUp).
 *
 * Pump sequence:
 *  1. while (wl_display_prepare_read != 0) → wl_display_dispatch_pending
 *  2. wl_display_flush
 *  3. poll([displayFd, eventfdFd], timeout)
 *  4. If displayFd ready → wl_display_read_events + dispatch_pending
 *     Otherwise          → wl_display_cancel_read
 *  5. If eventfdFd ready → read(eventfd) to drain the counter
 *
 * WaylandEventLoop.
 */
package org.graphiks.kadre.wayland
import org.graphiks.kadre.ffi.wayland.*

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.DeviceEvent
import org.graphiks.kadre.core.DeviceId
import org.graphiks.kadre.core.CursorImage
import org.graphiks.kadre.core.CustomCursor
import org.graphiks.kadre.core.DeviceEvents
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.VideoMode
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.util.Queue
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean

// ── Singleton guard ───────────────────────────────────────────────────────────

/**
 * Guarantees that only a single Wayland loop runs at a time.
 * compareAndSet(false, true) before starting, set(false) in finally.
 */
internal val waylandRunning = AtomicBoolean(false)

// ── WaylandEventLoop ──────────────────────────────────────────────────────────

/**
 * Active Wayland event loop, passed to [ApplicationHandler] callbacks.
 *
 * Created and controlled by [runApp]. Windows created here are stored in
 * [windows] to enable event dispatch.
 *
 * @param displayPtr  Address of the wl_display* (Long, never 0).
 * @param compositorPtr Address of the wl_compositor* (Long, 0 if unavailable).
 * @param xdgWmBasePtr  Address of the xdg_wm_base* (Long, 0 if unavailable).
 * @param eventFd     eventfd descriptor for inter-thread wakeup.
 */
class WaylandEventLoop internal constructor(
    internal val displayPtr: Long,
    internal val compositorPtr: Long,
    internal val xdgWmBasePtr: Long,
    internal val shmPtr: Long,
    internal val eventFd: Int,
    internal val decorationManagerPtr: Long = 0L,
    internal val pointerConstraintsPtr: Long = 0L,
    internal val iconManagerPtr: Long = 0L,
    internal val activationManagerPtr: Long = 0L,
    internal val seatPtr: Long = 0L,
    internal val extBackgroundEffectManagerPtr: Long = 0L,
    internal val kwinBlurManagerPtr: Long = 0L,
) : ActiveEventLoop {

    /** Active windows indexed by the address of their wl_surface*. */
    internal val windows = ConcurrentHashMap<Long, WaylandWindow>()

    /**
     * Window events produced by native upcalls (xdg configure/close), queued here and drained
     * into [ApplicationHandler.windowEvent] from the loop thread after each pump.
     */
    internal val eventQueue = java.util.concurrent.ConcurrentLinkedQueue<Pair<WindowId, WindowEvent>>()

    /**
     * Default CSD preference for newly created windows.
     * Set via [setPreferCsd] extension.
     */
    @Volatile
    internal var _preferCsd: Boolean = false

    /**
     * Activation token for xdg_activation_v1, set via [setActivationToken] extension.
     */
    internal var _activationToken: String? = null

    @Volatile private var _isExiting = false
    override val isExiting: Boolean get() = _isExiting

    @Volatile private var _controlFlow: ControlFlow = ControlFlow.Wait
    override val controlFlow: ControlFlow get() = _controlFlow

    private var _systemTheme: Theme? = null

    override fun setControlFlow(controlFlow: ControlFlow) {
        _controlFlow = controlFlow
    }

    override fun exit() {
        _isExiting = true
    }

    /**
     * Creates a native Wayland window and registers it in [windows].
     *
     * @param attributes Window configuration parameters.
     * @return The created window, or throws IllegalStateException if libwayland is absent.
     */
    override fun createWindow(attributes: WindowAttributes): Window {
        val window = WaylandWindow.create(
            display = displayPtr,
            compositor = compositorPtr,
            xdgWmBase = xdgWmBasePtr,
            shmPtr = shmPtr,
            attrs = attributes,
            decorationManager = decorationManagerPtr,
            pointerConstraintsPtr = pointerConstraintsPtr,
            iconManagerPtr = iconManagerPtr,
            activationManagerPtr = activationManagerPtr,
            seatPtr = seatPtr,
            extBackgroundEffectManagerPtr = extBackgroundEffectManagerPtr,
            kwinBlurManagerPtr = kwinBlurManagerPtr,
        ) ?: error("WaylandWindow.create failed — libwayland-client.so.0 absent or display invalid")
        // Route this window's compositor-driven events into the loop's queue for dispatch.
        window.onWindowEvent = { event -> eventQueue.add(window.id to event) }
        windows[window.id.value] = window
        // Initial paint so the surface attaches a buffer and becomes visible. Subsequent repaints
        // are driven on demand (e.g. after a resize), not continuously — see the main loop.
        eventQueue.add(window.id to org.graphiks.kadre.core.WindowEvent.RedrawRequested)
        return window
    }

    /**
     * Creates a window with Wayland-specific attributes.
     *
     * Merges [WaylandWindowAttributes] fields into the core [WindowAttributes]
     * and applies platform-specific settings at creation time.
     */
    fun createWindow(attrs: WaylandWindowAttributes): Window {
        val window = WaylandWindow.create(
            display = displayPtr,
            compositor = compositorPtr,
            xdgWmBase = xdgWmBasePtr,
            shmPtr = shmPtr,
            attrs = attrs.core,
            decorationManager = decorationManagerPtr,
            pointerConstraintsPtr = pointerConstraintsPtr,
            iconManagerPtr = iconManagerPtr,
            activationManagerPtr = activationManagerPtr,
            seatPtr = seatPtr,
            extBackgroundEffectManagerPtr = extBackgroundEffectManagerPtr,
            kwinBlurManagerPtr = kwinBlurManagerPtr,
        ) ?: error("WaylandWindow.create failed — libwayland-client.so.0 absent")
        window.onWindowEvent = { event -> eventQueue.add(window.id to event) }
        windows[window.id.value] = window
        // Apply platform extension settings
        attrs.preferCsd?.let { window.setPreferCsd(it) }
        attrs.activationToken?.let { window.setActivationToken(it) }
        attrs.name?.let { name -> window.setAppId(name) }
        eventQueue.add(window.id to org.graphiks.kadre.core.WindowEvent.RedrawRequested)
        return window
    }

    /**
     * Creates a thread-safe proxy to this event loop.
     *
     * The proxy uses the eventfd to wake up the loop from any thread.
     */
    override fun createProxy(): EventLoopProxy = WaylandEventLoopProxy(eventFd)

    // ── R2: monitor enumeration ───────────────────────────────────────────────

    /**
     * Returns a synthetic monitor derived from the first window's size and scale factor.
     *
     * On Wayland, the compositor does not expose physical monitor geometry to clients
     * via a simple public API in the core protocol (wl_output geometry is available
     * but requires additional setup). We return a synthetic monitor based on the
     * current window/screen state.
     *
     * A complete implementation would iterate the bound wl_output objects and read
     * their `geometry` and `mode` events, which requires storing that data during
     * the registry binding phase (TODO for a future ticket).
     */
    override fun availableMonitors(): List<MonitorHandle> {
        val win = windows.values.firstOrNull()
        val scale = win?._scaleFactor ?: 1.0
        val size = win?.innerSize ?: PhysicalSize(1920, 1080)
        return listOf(syntheticWaylandMonitor(displayPtr, scale, size))
    }

    /**
     * Returns null because Wayland has no global primary-monitor concept.
     *
     * This matches winit's Wayland backend. [availableMonitors] may still expose
     * synthetic output data, but that must not be promoted to a primary monitor.
     */
    override fun primaryMonitor(): MonitorHandle? = null

    // ── R3: system theme ──────────────────────────────────────────────────────

    override fun systemTheme(): Theme? {
        if (_systemTheme == null) {
            _systemTheme = WaylandThemePortal.queryColorScheme()
        }
        return _systemTheme
    }

    fun refreshTheme() {
        WaylandThemePortal.resetCache()
        val newTheme = WaylandThemePortal.queryColorScheme()
        val oldTheme = _systemTheme
        _systemTheme = newTheme
        if (newTheme != null && newTheme != oldTheme) {
            for (win in windows.values) {
                eventQueue.add(win.id to WindowEvent.ThemeChanged(newTheme))
            }
        }
    }

    // ── R4: device event filter ───────────────────────────────────────────────

    /**
     * Device event filter controlling raw [DeviceEvent] dispatch.
     *
     * Defaults to [DeviceEvents.WhenFocused], which is the natural behavior on
     * Wayland since keyboard/pointer events are already delivered per-surface.
     * [DeviceEvents.Never] suppresses raw `DeviceEvent.Key` dispatch.
     */
    @Volatile
    internal var deviceEventFilter: DeviceEvents = DeviceEvents.WhenFocused

    override fun listenDeviceEvents(mode: DeviceEvents) {
        deviceEventFilter = mode

    }

    // ── R5-CustomCursor ─────────────────────────────────────────────────────────

    /**
     * Stores the [CursorImage] data and returns a [CustomCursor] handle.
     *
     * The full cursor-surface path (wl_shm buffer + wl_pointer.set_cursor) is
     * deferred. The returned handle can be passed to [Window.setCustomCursor]
     * which will look up the stored image data and apply it when the cursor
     * surface path is wired.
     */
    override fun createCustomCursor(image: CursorImage): CustomCursor? {
        if (image.width <= 0 || image.height <= 0 || image.rgba.isEmpty()) return null
        val id = WaylandCustomCursorStore.store(image)
        return CustomCursor(id)
    }
}

internal fun routeWaylandInputEvent(
    surfacePtr: Long,
    event: WindowEvent,
    windows: Map<Long, WaylandWindow>,
    eventQueue: Queue<Pair<WindowId, WindowEvent>>,
): Boolean {
    val win = windows[surfacePtr] ?: return false
    eventQueue.add(win.id to event)
    return true
}

/** Creates a synthetic [MonitorHandle] for a Wayland output. */
private fun syntheticWaylandMonitor(
    outputPtr: Long,
    scale: Double,
    size: PhysicalSize<Int>,
): MonitorHandle = object : MonitorHandle {
    override val id: Long = outputPtr
    override val name: String? = null
    override val position: PhysicalPosition<Int> = PhysicalPosition(0, 0)
    override val scaleFactor: Double = scale
    override val currentVideoMode: VideoMode = VideoMode(size, null, null)
    override val videoModes: List<VideoMode> = listOf(currentVideoMode)
}

// ── runApp ────────────────────────────────────────────────────────────────────

/**
 * Starts the Wayland event loop and delegates the lifecycle to [handler].
 *
 * Blocking: only returns when the loop ends (via [ActiveEventLoop.exit]
 * or when all windows are closed).
 *
 * @throws IllegalStateException if a Wayland loop is already running.
 * @throws IllegalStateException if wl_display_connect fails.
 */
fun runApp(handler: ApplicationHandler) {
    if (!waylandRunning.compareAndSet(false, true)) {
        error("WaylandEventLoop already running")
    }
    try {
        runAppInternal(handler)
    } finally {
        waylandRunning.set(false)
    }
}

// ── Internal implementation ───────────────────────────────────────────────────

private fun runAppInternal(handler: ApplicationHandler) {
    // ── 1. Connect to the Wayland server ──────────────────────────────────────
    if (waylandNativeDisabled()) {
        error("Wayland native access disabled via KADRE_WAYLAND_DISABLE_NATIVE")
    }
    val connectHandle = wlDisplayConnect
        ?: error("wl_display_connect not available — libwayland-client.so.0 missing")

    val displaySeg: MemorySegment = Arena.ofConfined().use { arena ->
        val nullSeg = MemorySegment.NULL
        try {
            connectHandle.invokeExact(nullSeg) as MemorySegment
        } catch (t: Throwable) {
            error("wl_display_connect threw an exception: $t")
        }
    }

    if (displaySeg == MemorySegment.NULL || displaySeg.address() == 0L) {
        error("wl_display_connect returned NULL — Wayland server not available (WAYLAND_DISPLAY ?)")
    }

    val displayPtr = displaySeg.address()

    // ── 2. Wayland socket file descriptor ─────────────────────────────────────
    val displayFd: Int = try {
        val fdHandle = wlDisplayGetFd
            ?: error("wl_display_get_fd not available")
        fdHandle.invokeExact(displaySeg) as Int
    } catch (t: Throwable) {
        // Clean disconnect before propagating
        disconnectDisplay(displaySeg)
        throw t
    }

    // ── 3. Create the eventfd for wakeUp ──────────────────────────────────────
    val eventFd: Int = try {
        val efdHandle = nativeEventfd
            ?: error("eventfd not available — libc.so.6 missing")
        val fd = efdHandle.invokeExact(0, 0) as Int
        if (fd < 0) error("eventfd() returned $fd")
        fd
    } catch (t: Throwable) {
        disconnectDisplay(displaySeg)
        throw t
    }

    // ── 4. Discover Wayland globals (compositor, seat, output) ───────────────
    // get_registry + listener(global) + roundtrip + bind(wl_compositor, wl_seat, wl_output, …).
    val globals = discoverGlobals(displayPtr)

    val eventLoop = WaylandEventLoop(
        displayPtr, globals.compositorPtr, globals.xdgWmBasePtr, globals.shmPtr, eventFd,
        globals.decorationManagerPtr, globals.pointerConstraintsPtr, globals.iconManagerPtr,
        globals.activationManagerPtr, globals.seatPtr,
        globals.extBackgroundEffectManagerPtr, globals.kwinBlurManagerPtr,
    )

    // ── 4b. Install seat / output listeners (keyboard, pointer, touch, scale) ─
    // Route all input events into the eventQueue by their source wl_surface.
    // The seat and output globals may be absent (0) — installSeatListeners tolerates that.
    // DeviceEvent.Key is dispatched directly to the handler for raw key events.
    installSeatListeners(
        displayPtr    = displayPtr,
        seatPtr       = globals.seatPtr,
        outputPtr     = globals.outputPtr,
        seatVersion   = globals.seatVersion,
        outputVersion = globals.outputVersion,
        onEvent = { surfacePtr, event ->
            routeWaylandInputEvent(surfacePtr, event, eventLoop.windows, eventLoop.eventQueue)
        },
        onDeviceEvent = { event ->
            handler.deviceEvent(eventLoop, DeviceId(0L), event)
        },
        onScaleChanged = { scale ->
            val factor = scale.toDouble()
            for (win in eventLoop.windows.values) {
                if (win._scaleFactor != factor) {
                    win._scaleFactor = factor
                    eventLoop.eventQueue.add(win.id to org.graphiks.kadre.core.WindowEvent.ScaleFactorChanged(factor))
                }
            }
        },
        dataDeviceManagerPtr = globals.dataDeviceManagerPtr,
        deviceFilter = eventLoop.deviceEventFilter,
    )

    // ── 4c. Create zwp_text_input_v3 for IME (if compositor exposes the protocol) ──
    if (globals.textInputManagerPtr != 0L && globals.seatPtr != 0L) {
        createTextInput(
            managerPtr = globals.textInputManagerPtr,
            display = displayPtr,
            onEvent = { surfacePtr, event ->
                routeWaylandInputEvent(surfacePtr, event, eventLoop.windows, eventLoop.eventQueue)
            },
        )
    }

    try {
        // ── 5. Lifecycle: resumed ─────────────────────────────────────────────
        handler.resumed(eventLoop)

        // ── 6. First newEvents (Init) ─────────────────────────────────────────
        handler.newEvents(eventLoop, StartCause.Init)

        // ── 7. canCreateSurfaces ──────────────────────────────────────────────
        handler.canCreateSurfaces(eventLoop)

        // ── 8. Main loop ──────────────────────────────────────────────────────
        while (!eventLoop.isExiting) {
            // aboutToWait — the handler can change controlFlow here
            handler.aboutToWait(eventLoop)

            // Compute the timeout in milliseconds. If we already have queued window events (e.g.
            // a pending RedrawRequested), don't block in poll — drain them this iteration.
            val timeoutMs: Int = if (eventLoop.eventQueue.isNotEmpty()) {
                0
            } else when (val cf = eventLoop.controlFlow) {
                is ControlFlow.Wait -> -1
                is ControlFlow.Poll -> 0
                is ControlFlow.WaitUntil -> {
                    val delta = cf.instant - System.currentTimeMillis()
                    if (delta <= 0) 0 else delta.coerceAtMost(Int.MAX_VALUE.toLong()).toInt()
                }
            }

            // Canonical Wayland prepare_read / poll / read_events sequence. This dispatches the
            // pending Wayland protocol events, whose native upcalls enqueue WindowEvents.
            val startCause = pumpOnce(displaySeg, displayFd, eventFd, timeoutMs, eventLoop)

            // Drain queued window events (initial/resize RedrawRequested, xdg configure/close)
            // into the handler. Rendering happens here, on demand — NOT on a continuous pump:
            // eglSwapBuffers blocks the loop thread (no frame-callback pacing yet), so a steady
            // pump deadlocks the loop. On-demand keeps the loop responsive to resize/close.
            while (true) {
                val (windowId, event) = eventLoop.eventQueue.poll() ?: break
                handler.windowEvent(eventLoop, windowId, event)
            }

            handler.newEvents(eventLoop, startCause)
        }

        // ── 9. Shutdown ───────────────────────────────────────────────────────
        handler.destroySurfaces(eventLoop)
        handler.suspended(eventLoop)
    } finally {
        // Close the eventfd
        try { nativeClose?.invokeExact(eventFd) } catch (_: Throwable) {}
        // Disconnect from the Wayland server
        disconnectDisplay(displaySeg)
    }
}

/**
 * Performs one iteration of the canonical Wayland pump.
 *
 * Sequence:
 *  1. Drain the queue (prepare_read retry)
 *  2. Flush
 *  3. poll([displayFd, eventFd], timeoutMs)
 *  4. Conditional processing of the results
 *
 * @return [StartCause] describing the cause of the wakeup.
 */
private fun pumpOnce(
    displaySeg: MemorySegment,
    displayFd: Int,
    eventFd: Int,
    timeoutMs: Int,
    eventLoop: WaylandEventLoop,
): StartCause {
    val prepareRead = wlDisplayPrepareRead
    val dispatchPending = wlDisplayDispatchPending
    val flush = wlDisplayFlush
    val readEvents = wlDisplayReadEvents
    val cancelRead = wlDisplayCancelRead

    // ── Step 1: prepare the read (drain the queue first if needed) ────────────
    if (prepareRead != null && dispatchPending != null) {
        while (true) {
            val rc = try {
                prepareRead.invokeExact(displaySeg) as Int
            } catch (_: Throwable) { 0 }
            if (rc == 0) break
            // Events are queued — process them before retrying
            try { dispatchPending.invokeExact(displaySeg) } catch (_: Throwable) {}
        }
    }

    // ── Step 2: flush ─────────────────────────────────────────────────────────
    try { flush?.invokeExact(displaySeg) } catch (_: Throwable) {}

    // ── Step 3: poll ──────────────────────────────────────────────────────────
    val (displayReady, eventFdReady) = Arena.ofConfined().use { arena ->
        val fds = allocPollFd(arena)
        setPollFd(fds, 0, displayFd, POLLIN)
        setPollFd(fds, 1, eventFd, POLLIN)

        val pollRc = try {
            nativePoll?.invokeExact(fds, 2, timeoutMs) as? Int ?: 0
        } catch (_: Throwable) { 0 }

        if (pollRc > 0) {
            val rev0 = getPollRevents(fds, 0)
            val rev1 = getPollRevents(fds, 1)
            Pair(
                (rev0.toInt() and POLLIN.toInt()) != 0,
                (rev1.toInt() and POLLIN.toInt()) != 0,
            )
        } else {
            Pair(false, false)
        }
    }

    // ── Step 4a: read Wayland events if available ─────────────────────────────
    if (displayReady && readEvents != null && dispatchPending != null) {
        try { readEvents.invokeExact(displaySeg) } catch (_: Throwable) {}
        try { dispatchPending.invokeExact(displaySeg) } catch (_: Throwable) {}
    } else if (!displayReady && cancelRead != null && wlDisplayPrepareRead != null) {
        // Cancel the read announced in step 1 only if prepareRead is available
        try { cancelRead.invokeExact(displaySeg) } catch (_: Throwable) {}
    }

    // ── Step 4b: drain the eventfd if triggered ───────────────────────────────
    if (eventFdReady) {
        Arena.ofConfined().use { arena ->
            val buf = arena.allocate(8L, 8L)
            try { nativeRead?.invokeExact(eventFd, buf, 8L) } catch (_: Throwable) {}
        }
    }

    // ── Determine the StartCause ──────────────────────────────────────────────
    return when {
        eventFdReady -> StartCause.WaitCancelled()
        displayReady -> StartCause.Poll
        else -> when (val cf = eventLoop.controlFlow) {
            is ControlFlow.WaitUntil -> {
                val now = System.currentTimeMillis()
                if (now >= cf.instant) StartCause.ResumeTimeReached(cf.instant, now)
                else StartCause.Poll
            }
            else -> StartCause.Poll
        }
    }
}

/** Closes the connection to the Wayland server cleanly. */
private fun disconnectDisplay(displaySeg: MemorySegment) {
    try {
        wlDisplayDisconnect?.invokeExact(displaySeg)
    } catch (_: Throwable) {}
}
