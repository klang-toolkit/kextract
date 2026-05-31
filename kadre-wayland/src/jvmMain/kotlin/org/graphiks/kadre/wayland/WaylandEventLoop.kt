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

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
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
    internal val eventFd: Int,
) : ActiveEventLoop {

    /** Active windows indexed by the address of their wl_surface*. */
    internal val windows = ConcurrentHashMap<Long, WaylandWindow>()

    @Volatile private var _isExiting = false
    override val isExiting: Boolean get() = _isExiting

    @Volatile private var _controlFlow: ControlFlow = ControlFlow.Wait
    override val controlFlow: ControlFlow get() = _controlFlow

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
            attrs = attributes,
        ) ?: error("WaylandWindow.create failed — libwayland-client.so.0 absent or display invalid")
        windows[window.id.value] = window
        return window
    }

    /**
     * Creates a thread-safe proxy to this event loop.
     *
     * The proxy uses the eventfd to wake up the loop from any thread.
     */
    override fun createProxy(): EventLoopProxy = WaylandEventLoopProxy(eventFd)
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
        error("WaylandEventLoop déjà en cours d'exécution")
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
    val connectHandle = wlDisplayConnect
        ?: error("wl_display_connect non disponible — libwayland-client.so.0 absent")

    val displaySeg: MemorySegment = Arena.ofConfined().use { arena ->
        val nullSeg = MemorySegment.NULL
        try {
            connectHandle.invokeExact(nullSeg) as MemorySegment
        } catch (t: Throwable) {
            error("wl_display_connect a levé une exception : $t")
        }
    }

    if (displaySeg == MemorySegment.NULL || displaySeg.address() == 0L) {
        error("wl_display_connect a retourné NULL — serveur Wayland non disponible (WAYLAND_DISPLAY ?)")
    }

    val displayPtr = displaySeg.address()

    // ── 2. Wayland socket file descriptor ─────────────────────────────────────
    val displayFd: Int = try {
        val fdHandle = wlDisplayGetFd
            ?: error("wl_display_get_fd non disponible")
        fdHandle.invokeExact(displaySeg) as Int
    } catch (t: Throwable) {
        // Clean disconnect before propagating
        disconnectDisplay(displaySeg)
        throw t
    }

    // ── 3. Create the eventfd for wakeUp ──────────────────────────────────────
    val eventFd: Int = try {
        val efdHandle = nativeEventfd
            ?: error("eventfd non disponible — libc.so.6 absent")
        val fd = efdHandle.invokeExact(0, 0) as Int
        if (fd < 0) error("eventfd() a retourné $fd")
        fd
    } catch (t: Throwable) {
        disconnectDisplay(displaySeg)
        throw t
    }

    // ── 4. Discover Wayland globals (compositor) ──────────────────────────────
    // get_registry + listener(global) + roundtrip + bind(wl_compositor).
    // xdg_wm_base remains to be negotiated (not required to create a wl_surface / wgpu surface).
    val compositorPtr = discoverCompositor(displayPtr)
    val xdgWmBasePtr = 0L

    val eventLoop = WaylandEventLoop(displayPtr, compositorPtr, xdgWmBasePtr, eventFd)

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

            // Compute the timeout in milliseconds
            val timeoutMs: Int = when (val cf = eventLoop.controlFlow) {
                is ControlFlow.Wait -> -1
                is ControlFlow.Poll -> 0
                is ControlFlow.WaitUntil -> {
                    val delta = cf.instant - System.currentTimeMillis()
                    if (delta <= 0) 0 else delta.coerceAtMost(Int.MAX_VALUE.toLong()).toInt()
                }
            }

            // Canonical Wayland prepare_read / poll / read_events sequence
            val startCause = pumpOnce(displaySeg, displayFd, eventFd, timeoutMs, eventLoop)

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
