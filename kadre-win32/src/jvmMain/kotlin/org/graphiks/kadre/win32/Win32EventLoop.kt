/**
 * Win32 implementation of [ActiveEventLoop] and the [runApp] entry point.
 *
 * [Win32EventLoop] implements [ActiveEventLoop] and is passed to every
 * [ApplicationHandler] callback. The [runApp] method orchestrates
 * Win32 initialization (KadreWndProc registration) and the message
 * loop with dynamic switching based on [ControlFlow]:
 *
 * - [ControlFlow.Poll]      → PeekMessageW (PM_REMOVE) — non-blocking
 * - [ControlFlow.Wait]      → GetMessageW — blocks until the next message
 * - [ControlFlow.WaitUntil] → MsgWaitForMultipleObjectsEx with a timeout in ms
 *
 * Lazy FFM pattern (tryCreate): all MethodHandles are null on macOS/Linux,
 * which lets the build pass on all platforms.
 *
 * GRA-11: Win32EventLoop — Win32 message loop with ControlFlow switching.
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.CursorImage
import org.graphiks.kadre.core.CustomCursor
import org.graphiks.kadre.core.DeviceEvents
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean

// ── Single-instance lock ──────────────────────────────────────────────────────

/**
 * Global lock guaranteeing that only one Win32 event loop is active
 * at a time in the process.
 *
 * Uses AtomicBoolean for thread-safety: [runApp] performs an atomic CAS
 * false→true at startup and throws [IllegalStateException] if already true.
 */
internal val win32Running = AtomicBoolean(false)

// ── Win32EventLoop ────────────────────────────────────────────────────────────

/**
 * Internal implementation of [ActiveEventLoop] for the Win32 platform (Windows).
 *
 * One instance is created per call to [runApp] and passed as the receiver
 * to all [ApplicationHandler] callbacks.
 *
 * ### Lifecycle
 * ```
 * runApp(handler)
 *   └─ handler.resumed(this)
 *   └─ message loop
 *        ├─ handler.newEvents(this, cause)
 *        ├─ pump messages according to ControlFlow
 *        └─ handler.aboutToWait(this)
 *   └─ handler.suspended(this)
 * ```
 *
 * ### Thread-safety
 * - [_controlFlow] is @Volatile: readable from any thread.
 * - [_isExiting] is @Volatile: readable from any thread.
 * - [windows] is a ConcurrentHashMap.
 * - The message loop itself runs on the calling thread.
 */
internal class Win32EventLoop : ActiveEventLoop {

    /** Live windows: windowId (HWND address) → Win32Window. */
    internal val windows = ConcurrentHashMap<Long, Win32Window>()

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
     * Creates a new native Win32 window and registers it in the window table.
     *
     * Also installs the [KadreWndProc] handler if not already done.
     *
     * @param attributes Window configuration attributes.
     * @return The created window.
     * @throws IllegalStateException if the Win32 bindings are not available
     *         (macOS/Linux) or if window creation fails.
     */
    override fun createWindow(attributes: WindowAttributes): Window {
        val window = Win32Window.create(attributes)
            ?: error(
                "Win32Window.create() returned null — Win32 (user32.dll) bindings are not available on this platform."
            )
        windows[window.id.value] = window
        return window
    }

    /**
     * Creates a window with Win32-specific attributes.
     *
     * Merges [Win32WindowAttributes] fields into the core [WindowAttributes]
     * and applies platform-specific settings at creation time.
     */
    fun createWindow(attrs: Win32WindowAttributes): Window {
        val window = Win32Window.create(attrs.core)
            ?: error(
                "Win32Window.create() returned null — Win32 (user32.dll) bindings are not available on this platform."
            )
        windows[window.id.value] = window
        // Apply platform extension settings
        if (attrs.skipTaskbar) window.setSkipTaskbar(true)
        if (attrs.undecoratedShadow) window.setUndecoratedShadow(true)
        attrs.systemBackdrop?.let { window.setSystemBackdrop(it) }
        attrs.cornerPreference?.let { window.setCornerPreference(it) }
        attrs.borderColor?.let { window.setBorderColor(it) }
        attrs.titleBackgroundColor?.let { window.setTitleBackgroundColor(it) }
        attrs.titleTextColor?.let { window.setTitleTextColor(it) }
        if (!attrs.enabled) window.setEnabled(false)
        return window
    }

    /**
     * Requests the Win32 event loop to stop.
     *
     * Sets [_isExiting] then calls PostQuitMessage(0) to insert WM_QUIT
     * into the message queue, which causes GetMessageW to return.
     */
    override fun exit() {
        _isExiting = true
        postQuitMessage?.invoke(0)
    }

    /**
     * Creates a thread-safe proxy to this event loop.
     *
     * The proxy uses PostThreadMessageW(WM_NULL) to wake the loop
     * from a secondary thread.
     */
    override fun createProxy(): EventLoopProxy = Win32EventLoopProxy.create()

    // ── R2: monitor enumeration ───────────────────────────────────────────────

    /**
     * Returns all connected monitors via EnumDisplayMonitors + GetMonitorInfoW.
     *
     * Returns an empty list on non-Windows platforms (graceful no-op).
     */
    override fun availableMonitors(): List<MonitorHandle> = enumerateWin32Monitors()

    /**
     * Returns the primary monitor (dwFlags & MONITORINFOF_PRIMARY), or null.
     */
    override fun primaryMonitor(): MonitorHandle? =
        enumerateWin32Monitors().firstOrNull { it.isPrimary }

    // ── R3: system theme ──────────────────────────────────────────────────────

    /**
     * Returns the current system theme by reading the registry.
     *
     * Reads HKCU\...\Personalize\AppsUseLightTheme via Java Preferences.
     * Returns null if the key is absent or the call fails.
     */
    override fun systemTheme(): Theme? = Win32ThemeHelper.systemThemeFromRegistry()

    // ── R4: device event filter ───────────────────────────────────────────────

    /**
     * No-op on Win32: device events are always dispatched regardless of focus.
     *
     * A proper implementation would require Raw Input (RIDEV_INPUTSINK / RIDEV_REMOVE)
     * to globally enable/disable raw input. Out of scope for R4.
     */
    override fun listenDeviceEvents(mode: DeviceEvents) {
        // no-op on Win32: device event filtering not implemented
    }

    // ── R5-CustomCursor ─────────────────────────────────────────────────────────

    /**
     * Creates a custom cursor from RGBA pixel data on Win32.
     *
     * Converts RGBA → BGRA + inverted-alpha AND mask and calls CreateIcon.
     * On Win32, HICON handles are interchangeable with HCURSOR handles.
     */
    override fun createCustomCursor(image: CursorImage): CustomCursor? {
        val hCursor = win32CreateCursorFromImage(image) ?: return null
        return CustomCursor(id = hCursor.address())
    }

    // ── Message loop ──────────────────────────────────────────────────────────

    /**
     * Starts the Win32 message loop.
     *
     * Must be called from the main Windows thread (the thread that created
     * the windows). Blocking — returns only when the application closes.
     *
     * @param handler Lifecycle and event handler.
     */
    internal fun runMessageLoop(handler: ApplicationHandler) {
        // Confined arena for the MSG segment — freed when the loop exits
        Arena.ofConfined().use { arena ->
            val msg = arena.allocateMsg()
            var startCause: StartCause = StartCause.Init

            while (!_isExiting) {
                // Notify the handler of the start of the iteration
                handler.newEvents(this, startCause)
                if (_isExiting) break

                // Dispatch the messages according to the current ControlFlow
                startCause = dispatchMessages(msg, handler)

                // Notify the handler that the loop is about to wait
                handler.aboutToWait(this)
            }
        }
    }

    /**
     * Dispatches Win32 messages according to the current [ControlFlow].
     *
     * @param msg    Pre-allocated MSG memory segment.
     * @param handler Event handler (to route window events).
     * @return The [StartCause] of the next iteration.
     */
    private fun dispatchMessages(msg: MemorySegment, handler: ApplicationHandler): StartCause {
        return when (val cf = _controlFlow) {
            is ControlFlow.Poll -> dispatchPoll(msg, handler)
            is ControlFlow.Wait -> dispatchWait(msg, handler)
            is ControlFlow.WaitUntil -> dispatchWaitUntil(msg, handler, cf.instant)
        }
    }

    /**
     * Poll mode: PeekMessageW (PM_REMOVE) — non-blocking.
     *
     * Drains the message queue in one pass (processes all available messages)
     * and returns immediately even if the queue is empty.
     */
    private fun dispatchPoll(msg: MemorySegment, handler: ApplicationHandler): StartCause {
        val peekHandle = peekMessageW
        val translateHandle = translateMessage
        val dispatchHandle = dispatchMessageW

        if (peekHandle != null && translateHandle != null && dispatchHandle != null) {
            while (!_isExiting) {
                val hasMsg = peekHandle.invokeExact(
                    msg,
                    MemorySegment.NULL,  // hWnd: NULL = all thread messages
                    0,                   // wMsgFilterMin: no filter
                    0,                   // wMsgFilterMax: no filter
                    PM_REMOVE,           // wRemoveMsg: remove from the queue
                ) as Int
                if (hasMsg == 0) break  // queue empty, exit the pump
                translateHandle.invokeExact(msg) as Int
                dispatchHandle.invokeExact(msg) as Long
            }
        }
        return StartCause.Poll
    }

    /**
     * Wait mode: GetMessageW — blocks until the next message.
     *
     * Blocks the thread until a message is received (or WM_QUIT).
     * Returns false (StartCause.WaitCancelled) if WM_QUIT is received.
     */
    private fun dispatchWait(msg: MemorySegment, handler: ApplicationHandler): StartCause {
        val getHandle = getMessageW
        val translateHandle = translateMessage
        val dispatchHandle = dispatchMessageW

        if (getHandle != null && translateHandle != null && dispatchHandle != null) {
            val result = getHandle.invokeExact(
                msg,
                MemorySegment.NULL,  // hWnd: NULL = all thread messages
                0,                   // wMsgFilterMin
                0,                   // wMsgFilterMax
            ) as Int

            when {
                result > 0 -> {
                    // Normal message → translate + dispatch
                    translateHandle.invokeExact(msg) as Int
                    dispatchHandle.invokeExact(msg) as Long
                }
                result == 0 -> {
                    // WM_QUIT → clean exit
                    _isExiting = true
                }
                // result < 0: error — ignore and continue
            }
        }
        return StartCause.WaitCancelled()
    }

    /**
     * WaitUntil mode: MsgWaitForMultipleObjectsEx with a timeout.
     *
     * Computes the remaining timeout until [targetInstant] (in ms since the Unix epoch)
     * and waits for either a message or the timeout to expire.
     *
     * @param targetInstant Target instant in milliseconds since the Unix epoch.
     */
    private fun dispatchWaitUntil(
        msg: MemorySegment,
        handler: ApplicationHandler,
        targetInstant: Long,
    ): StartCause {
        val waitHandle = msgWaitForMultipleObjectsEx
        val peekHandle = peekMessageW
        val translateHandle = translateMessage
        val dispatchHandle = dispatchMessageW

        val now = System.currentTimeMillis()
        val timeoutMs = (targetInstant - now).coerceIn(0L, Int.MAX_VALUE.toLong()).toInt()

        if (waitHandle != null) {
            val result = waitHandle.invokeExact(
                0,                   // nCount: no kernel object to wait on
                MemorySegment.NULL,  // pHandles: NULL because nCount = 0
                timeoutMs,           // dwMilliseconds: computed timeout
                QS_ALLINPUT,         // dwWakeMask: all input messages
                MWMO_INPUTAVAILABLE, // dwFlags: wake up if messages already available
            ) as Int

            when (result) {
                WAIT_OBJECT_0 -> {
                    // Messages available → pump with PeekMessageW
                    if (peekHandle != null && translateHandle != null && dispatchHandle != null) {
                        while (!_isExiting) {
                            val hasMsg = peekHandle.invokeExact(
                                msg,
                                MemorySegment.NULL,
                                0, 0,
                                PM_REMOVE,
                            ) as Int
                            if (hasMsg == 0) break
                            translateHandle.invokeExact(msg) as Int
                            dispatchHandle.invokeExact(msg) as Long
                        }
                    }
                    return StartCause.WaitCancelled(targetInstant)
                }
                WAIT_TIMEOUT -> {
                    // Timeout expired → target instant reached
                    return StartCause.ResumeTimeReached(
                        requestedResume = targetInstant,
                        start = System.currentTimeMillis(),
                    )
                }
                else -> {
                    // Other return code (error or unexpected signal)
                    return StartCause.WaitCancelled(targetInstant)
                }
            }
        } else {
            // Bindings unavailable (macOS/Linux) — simulate Poll
            return StartCause.Poll
        }
    }
}

// ── Entry point ───────────────────────────────────────────────────────────────

/**
 * Entry point for the kadre event loop on Windows.
 *
 * Initializes Win32 (installs KadreWndProc), creates a [Win32EventLoop],
 * calls [ApplicationHandler.resumed], then starts the blocking message loop.
 * Returns only when the application closes.
 *
 * Must be called from the main Windows thread (the message thread).
 *
 * @param handler Lifecycle and event handler.
 * @throws IllegalStateException if a Win32 loop is already active in this process.
 */
fun runApp(handler: ApplicationHandler) {
    check(win32Running.compareAndSet(false, true)) {
        "Win32EventLoop.runApp() can only be called once per process. A Win32 event loop is already active."
    }

    // Enable Per-Monitor-V2 before any window creation, otherwise Windows
    // virtualizes the DPI and makes content blurry on high-density displays.
    enablePerMonitorV2DpiAwareness()

    val eventLoop = Win32EventLoop()

    // Install the KadreWndProc handler to route messages to the windows
    KadreWndProc.install { hwnd, event ->
        val windowId = WindowId(hwnd)
        handler.windowEvent(eventLoop, windowId, event)

        // Handle window destruction: remove from the table + possibly quit
        if (event is WindowEvent.Destroyed) {
            eventLoop.windows.remove(hwnd)
        }
    }

    try {
        // Notify the handler that the application is resuming
        handler.resumed(eventLoop)

        // Notify that surfaces can be created
        handler.canCreateSurfaces(eventLoop)

        // Start the blocking message loop
        eventLoop.runMessageLoop(handler)
    } finally {
        // Final cleanup
        handler.suspended(eventLoop)
        KadreWndProc.uninstall()
        win32Running.set(false)
    }
}
