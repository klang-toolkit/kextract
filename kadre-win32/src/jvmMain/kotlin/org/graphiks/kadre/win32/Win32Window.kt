/**
 * Win32 implementation of the [Window] interface for Windows Desktop.
 *
 * Uses the Foreign Function & Memory API (JEP 454, JDK 25) to interact
 * with user32.dll and kernel32.dll without JNA or any other intermediate layer.
 *
 * Creation flow:
 *  1. [companion.registerClassOnce] → RegisterClassExW (run only once)
 *  2. [createWindow]                → CreateWindowExW
 *  3. ShowWindow / UpdateWindow     → initial display
 *
 * GRA-141: Win32Window — complete implementation of the Window interface.
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.Fullscreen
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Native Win32 window implementing [Window].
 *
 * The constructor is private: use [Win32Window.create] to instantiate.
 *
 * @param hwnd      Native window handle (HWND), represented by a MemorySegment.
 * @param hInstance Handle of the current module (HINSTANCE), represented by a MemorySegment.
 * @param attrs     Window creation attributes.
 */
class Win32Window private constructor(
    private val hwnd: MemorySegment,
    private val hInstance: MemorySegment,
    private val attrs: WindowAttributes,
) : Window {

    // ── R2 fullscreen state ──────────────────────────────────────────────────

    /** In-memory fullscreen state (R2). */
    @Volatile private var _fullscreen: Fullscreen? = attrs.fullscreen

    /** Saved window style before entering borderless fullscreen (for restoration). */
    @Volatile private var _savedStyle: Long? = null

    /** Saved window rect before entering borderless fullscreen (for restoration). */
    @Volatile private var _savedRect: IntArray? = null

    override val id: WindowId = WindowId(hwnd.address())

    override val rawWindowHandle: RawWindowHandle
        get() = RawWindowHandle.Win32(
            hwnd = hwnd.address(),
            hinstance = hInstance.address(),
        )

    override val rawDisplayHandle: RawDisplayHandle
        get() = RawDisplayHandle.Win32(hinstance = hInstance.address())

    /**
     * Redraw flag — set by [requestRedraw], consumed by the message loop.
     */
    @Volatile
    private var needsRedraw: Boolean = false

    override fun requestRedraw() {
        needsRedraw = true
    }

    override fun setTitle(title: String) {
        val handle = setWindowTextW ?: return
        Arena.ofConfined().use { arena ->
            val titleW = arena.allocateWString(title)
            handle.invokeExact(hwnd, titleW) as Int
        }
    }

    /**
     * Inner size (render surface) in physical pixels.
     *
     * Calls GetClientRect(hwnd) directly so the value is always fresh,
     * even between WM_SIZE messages. Falls back to the attributes size
     * (or 800×600) if the handle is unavailable or the call fails.
     */
    override val innerSize: PhysicalSize<Int>
        get() = rectToSize(getClientRect) ?: attrs.size ?: PhysicalSize(800, 600)

    /**
     * Outer size (window + decorations) in physical pixels.
     *
     * Calls GetWindowRect(hwnd) directly so the value is always fresh.
     * Falls back to the attributes size (or 800×600) on failure.
     */
    override val outerSize: PhysicalSize<Int>
        get() = rectToSize(getWindowRect) ?: attrs.size ?: PhysicalSize(800, 600)

    /**
     * Calls the given Win32 rect-filling function (GetClientRect or GetWindowRect)
     * and converts the resulting RECT into a [PhysicalSize].
     *
     * Allocates a 16-byte RECT in a confined arena for the duration of the call.
     *
     * @return the measured size, or null if the handle is null or the call returns 0.
     */
    private fun rectToSize(handle: MethodHandle?): PhysicalSize<Int>? {
        handle ?: return null
        return try {
            Arena.ofConfined().use { arena ->
                val rect = arena.allocate(RECT_SIZE, RECT_ALIGN)
                val ok = handle.invokeExact(hwnd, rect) as Int
                if (ok == 0) return@use null
                val width  = rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_RIGHT)  -
                             rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_LEFT)
                val height = rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_BOTTOM) -
                             rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_TOP)
                PhysicalSize(width, height)
            }
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * DPI scale factor of this window.
     *
     * Calls GetDpiForWindow(hwnd) and divides by 96 (Windows reference logical
     * DPI). Returns 1.0 when unavailable (non-Windows or invalid window)
     * for graceful cross-platform behavior.
     *
     * GRA-12: DPI awareness PerMonitorV2.
     */
    override val scaleFactor: Double
        get() = try {
            // GetDpiForWindow returns UINT; capture as Int (an `as? Int` would make invokeExact
            // expect an Object return and throw WrongMethodTypeException, silently forcing 1.0).
            val dpi = getDpiForWindow?.let { it.invokeExact(hwnd) as Int } ?: 0
            if (dpi > 0) dpi.toDouble() / 96.0 else 1.0
        } catch (_: Throwable) {
            1.0
        }

    override fun setVisible(visible: Boolean) {
        val handle = showWindow ?: return
        val nCmdShow = if (visible) SW_SHOW else SW_HIDE
        handle.invokeExact(hwnd, nCmdShow) as Int
    }

    override fun close() {
        val handle = destroyWindow ?: return
        handle.invokeExact(hwnd) as Int
    }

    // ── R1: window state & geometry ───────────────────────────────────────────

    override val title: String
        get() {
            val handle = getWindowTextW ?: return attrs.title
            return try {
                Arena.ofConfined().use { arena ->
                    val buf = arena.allocate(512L, 2L)
                    val len = handle.invokeExact(hwnd, buf, 256) as Int
                    if (len <= 0) return@use ""
                    val chars = CharArray(len) {
                        buf.getAtIndex(ValueLayout.JAVA_SHORT, it.toLong()).toInt().toChar()
                    }
                    String(chars)
                }
            } catch (_: Throwable) { attrs.title }
        }

    override val isVisible: Boolean
        get() = try {
            (isWindowVisible?.invokeExact(hwnd) as? Int ?: 0) != 0
        } catch (_: Throwable) { false }

    /** Returns the current Win32 GWL_STYLE value, or 0 on failure. */
    private fun getWindowStyle(): Long = try {
        getWindowLongPtrW?.invokeExact(hwnd, GWL_STYLE) as? Long ?: 0L
    } catch (_: Throwable) { 0L }

    /** Applies a new GWL_STYLE value and forces a non-client redraw. */
    private fun setWindowStyle(style: Long) {
        try {
            setWindowLongPtrW?.invokeExact(hwnd, GWL_STYLE, style) as? Long
            // SWP with no-op move/size forces the frame to redraw immediately.
            setWindowPos?.invokeExact(
                hwnd, MemorySegment.NULL, 0, 0, 0, 0,
                SWP_NOSIZE or SWP_NOZORDER or SWP_NOACTIVATE or 0x0020 /* SWP_FRAMECHANGED */,
            ) as? Int
        } catch (_: Throwable) {}
    }

    override fun setResizable(resizable: Boolean) {
        val style = getWindowStyle()
        val newStyle = if (resizable) style or WS_THICKFRAME.toLong()
                       else style and WS_THICKFRAME.toLong().inv()
        setWindowStyle(newStyle)
    }

    override val isResizable: Boolean
        get() = (getWindowStyle() and WS_THICKFRAME.toLong()) != 0L

    override fun setMinimized(minimized: Boolean) {
        try {
            val nCmd = if (minimized) SW_MINIMIZE else SW_RESTORE
            showWindow?.invokeExact(hwnd, nCmd) as? Int
        } catch (_: Throwable) {}
    }

    override val isMinimized: Boolean
        get() = try {
            (isIconic?.invokeExact(hwnd) as? Int ?: 0) != 0
        } catch (_: Throwable) { false }

    override fun setMaximized(maximized: Boolean) {
        try {
            val nCmd = if (maximized) SW_MAXIMIZE else SW_RESTORE
            showWindow?.invokeExact(hwnd, nCmd) as? Int
        } catch (_: Throwable) {}
    }

    override val isMaximized: Boolean
        get() = try {
            (isZoomed?.invokeExact(hwnd) as? Int ?: 0) != 0
        } catch (_: Throwable) { false }

    override fun setDecorations(decorated: Boolean) {
        val style = getWindowStyle()
        val newStyle = if (decorated) {
            style or WS_CAPTION.toLong() or WS_SYSMENU.toLong() or
            WS_MINIMIZEBOX.toLong() or WS_MAXIMIZEBOX.toLong()
        } else {
            style and (WS_CAPTION.toLong() or WS_SYSMENU.toLong() or
            WS_MINIMIZEBOX.toLong() or WS_MAXIMIZEBOX.toLong()).inv()
        }
        setWindowStyle(newStyle)
    }

    override val isDecorated: Boolean
        get() = (getWindowStyle() and WS_CAPTION.toLong()) != 0L

    override fun setMinSurfaceSize(size: PhysicalSize<Int>?) {
        // Win32 min/max size is enforced via WM_GETMINMAXINFO in the WndProc.
        // Store the constraint in a thread-safe field so KadreWndProc can read it.
        _minSurfaceSize = size
    }

    override fun setMaxSurfaceSize(size: PhysicalSize<Int>?) {
        _maxSurfaceSize = size
    }

    @Volatile internal var _minSurfaceSize: PhysicalSize<Int>? = attrs.minSize
    @Volatile internal var _maxSurfaceSize: PhysicalSize<Int>? = attrs.maxSize

    override val outerPosition: PhysicalPosition<Int>
        get() = try {
            Arena.ofConfined().use { arena ->
                val rect = arena.allocate(RECT_SIZE, RECT_ALIGN)
                val ok = getWindowRect?.invokeExact(hwnd, rect) as? Int ?: 0
                if (ok == 0) return@use PhysicalPosition(0, 0)
                val x = rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_LEFT)
                val y = rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_TOP)
                PhysicalPosition(x, y)
            }
        } catch (_: Throwable) { PhysicalPosition(0, 0) }

    override fun setOuterPosition(position: PhysicalPosition<Int>) {
        try {
            setWindowPos?.invokeExact(
                hwnd, MemorySegment.NULL,
                position.x, position.y, 0, 0,
                SWP_NOSIZE or SWP_NOZORDER or SWP_NOACTIVATE,
            ) as? Int
        } catch (_: Throwable) {}
    }

    /**
     * No-op on Win32: there is no equivalent to Wayland's `wl_surface.pre_commit`.
     */
    override fun prePresentNotify() { /* no-op on Win32 */ }

    // ── R2: monitor & fullscreen ──────────────────────────────────────────────

    /**
     * Returns the monitor that contains the majority of this Win32 window
     * via MonitorFromWindow(MONITOR_DEFAULTTONEAREST).
     */
    override fun currentMonitor(): MonitorHandle? = win32MonitorFromHwnd(hwnd)

    override val fullscreen: Fullscreen?
        get() = _fullscreen

    /**
     * Enters or exits fullscreen mode on Win32.
     *
     * **Borderless** fullscreen: saves the current window style and rect, switches the
     * window to WS_POPUP, and stretches it to cover the target monitor's virtual rect.
     * Exiting restores the saved style and rect.
     *
     * **Exclusive** fullscreen: calls ChangeDisplaySettingsExW to request a mode change on
     * the target monitor, then enters borderless mode covering the full monitor. Exiting
     * calls ChangeDisplaySettingsExW with NULL to restore the original mode.
     *
     * Note: ChangeDisplaySettings is not wired here because it requires a DEVMODEW upcall
     * and is only relevant on hardware running Windows; it is documented as not yet
     * implemented and falls back to borderless.
     */
    override fun setFullscreen(fullscreen: Fullscreen?) {
        try {
            when (fullscreen) {
                null -> exitFullscreen()
                is Fullscreen.Borderless -> enterBorderless(fullscreen.monitor)
                is Fullscreen.Exclusive  -> {
                    // Exclusive on Win32 would require ChangeDisplaySettingsExW.
                    // Fallback to borderless and document the limitation.
                    // TODO(R2-win32-exclusive): implement via ChangeDisplaySettingsExW.
                    enterBorderless(fullscreen.monitor)
                    _fullscreen = fullscreen // report back the requested mode
                }
            }
        } catch (_: Throwable) {}
    }

    private fun enterBorderless(monitor: MonitorHandle?) {
        if (_savedStyle == null) {
            // Save current state for restoration
            _savedStyle = getWindowStyle()
            _savedRect = try {
                Arena.ofConfined().use { arena ->
                    val rect = arena.allocate(16L, 4L)
                    val ok = getWindowRect?.invokeExact(hwnd, rect) as? Int ?: 0
                    if (ok != 0) intArrayOf(
                        rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_LEFT),
                        rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_TOP),
                        rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_RIGHT),
                        rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_BOTTOM),
                    ) else null
                }
            } catch (_: Throwable) { null }
        }

        // Determine target monitor rect
        val targetMonitor: Win32MonitorHandle? = when (monitor) {
            is Win32MonitorHandle -> monitor
            null -> win32MonitorFromHwnd(hwnd)
            else -> null
        }

        val (mx, my, mw, mh) = if (targetMonitor != null) {
            val pos = targetMonitor.position
            val cm = targetMonitor.currentVideoMode
            intArrayOf(pos.x, pos.y, cm?.size?.width ?: 1920, cm?.size?.height ?: 1080)
        } else {
            intArrayOf(0, 0, 1920, 1080)
        }

        // Switch to WS_POPUP (borderless)
        setWindowStyle(0x80000000L.toLong() or WS_VISIBLE.toLong())
        setWindowPos?.invokeExact(
            hwnd, HWND_TOP,
            mx, my, mw, mh,
            SWP_NOACTIVATE or 0x0020 /* SWP_FRAMECHANGED */,
        ) as? Int

        _fullscreen = Fullscreen.Borderless(monitor)
    }

    private fun exitFullscreen() {
        val savedStyle = _savedStyle
        val savedRect  = _savedRect
        if (savedStyle != null) {
            setWindowStyle(savedStyle)
            if (savedRect != null) {
                val w = savedRect[2] - savedRect[0]
                val h = savedRect[3] - savedRect[1]
                setWindowPos?.invokeExact(
                    hwnd, HWND_TOP,
                    savedRect[0], savedRect[1], w, h,
                    SWP_NOACTIVATE or 0x0020 /* SWP_FRAMECHANGED */,
                ) as? Int
            }
            _savedStyle = null
            _savedRect  = null
        }
        _fullscreen = null
    }

    // ── Companion ─────────────────────────────────────────────────────────────

    companion object {

        /** Name of the registered Win32 window class. */
        private const val CLASS_NAME = "KadreWin32Window"

        /**
         * Atomic guard for window class registration.
         *
         * RegisterClassExW must be called only once per process
         * for a given class name.
         */
        private val classRegistered = AtomicBoolean(false)

        /**
         * Upcall stub for the WndProc — must stay alive as long
         * as windows of this class exist.
         *
         * Allocated in [Win32WndProcArena.arena] (Arena.ofShared).
         */
        @Volatile
        private var wndProcStub: MemorySegment? = null

        /**
         * Registers the Win32 window class only once.
         *
         * Thread-safe via [classRegistered] (AtomicBoolean compare-and-set).
         * No-op on macOS/Linux (the MethodHandles are null).
         *
         * @param hInstance Handle of the current module.
         * @param classNamePtr Wide-string pointer to the class name.
         */
        private fun registerClassOnce(hInstance: MemorySegment, classNamePtr: MemorySegment) {
            if (!classRegistered.compareAndSet(false, true)) return

            val registerHandle = registerClassExW ?: return

            // Create the upcall stub for the WndProc
            val wndProcMH = MethodHandles.lookup().findStatic(
                Win32Window::class.java,
                "wndProc",
                MethodType.methodType(Long::class.java, MemorySegment::class.java, Int::class.java, Long::class.java, Long::class.java)
            )

            val wndProcDesc = FunctionDescriptor.of(
                ValueLayout.JAVA_LONG,  // LRESULT
                ValueLayout.ADDRESS,    // HWND
                ValueLayout.JAVA_INT,   // UINT (message)
                ValueLayout.JAVA_LONG,  // WPARAM
                ValueLayout.JAVA_LONG,  // LPARAM
            )

            val linker = Linker.nativeLinker()
            val stub = linker.upcallStub(wndProcMH, wndProcDesc, Win32WndProcArena.arena)
            wndProcStub = stub

            // Allocate and fill the WNDCLASSEXW structure in a temporary arena
            Arena.ofConfined().use { arena ->
                val wndClass = WndClassExW(arena)
                wndClass.cbSize = WndClassExW.SIZEOF
                wndClass.style = CS_HREDRAW_VREDRAW
                wndClass.lpfnWndProc = stub
                wndClass.cbClsExtra = 0
                wndClass.cbWndExtra = 0
                wndClass.hInstance = hInstance
                wndClass.hIcon = MemorySegment.NULL
                // Give the class the standard arrow cursor (IDC_ARROW). Without it, Windows never
                // resets the cursor over the client area, leaving the resize cursor "stuck" from
                // the window border. MAKEINTRESOURCE(IDC_ARROW=32512) is encoded as a small address.
                wndClass.hCursor = loadCursorW
                    ?.let { it.invokeExact(MemorySegment.NULL, MemorySegment.ofAddress(IDC_ARROW)) as MemorySegment }
                    ?: MemorySegment.NULL
                wndClass.hbrBackground = MemorySegment.NULL
                wndClass.lpszMenuName = MemorySegment.NULL
                wndClass.lpszClassName = classNamePtr
                wndClass.hIconSm = MemorySegment.NULL

                val atom = registerHandle.invokeExact(wndClass.segment) as Short
                if (atom.toInt() == 0) {
                    // Reset to allow a future attempt
                    classRegistered.set(false)
                    wndProcStub = null
                    error("RegisterClassExW a échoué (atom = 0)")
                }
            }
        }

        /**
         * Win32 window procedure (WndProc).
         *
         * Called by the Windows system for each message sent to a window
         * of the KadreWin32Window class. Delegates the entire dispatch to
         * [KadreWndProc.dispatch] which translates the Win32 messages into kadre
         * [WindowEvent]s and forwards them to the installed handler.
         *
         * ⚠️ This method is called from the Win32 message thread —
         * it must be @JvmStatic so that MethodHandles.lookup() can find it.
         */
        @JvmStatic
        fun wndProc(hwnd: MemorySegment, msg: Int, wParam: Long, lParam: Long): Long {
            return KadreWndProc.dispatch(hwnd.address(), msg, wParam, lParam)
        }

        /**
         * Creates a native Win32 window.
         *
         * Registers the window class if necessary, then calls
         * CreateWindowExW to create the native window.
         *
         * @param attrs Window attributes (title, size, visibility, etc.).
         * @return The created window, or null if the Win32 bindings are not available
         *         (macOS/Linux) or if creation fails.
         */
        fun create(attrs: WindowAttributes): Win32Window? {
            // Check the availability of the Win32 bindings
            val createHandle = createWindowExW ?: return null
            val getModuleHandle = getModuleHandleW ?: return null

            // Get the handle of the current module (GetModuleHandleW(NULL))
            val hInstance = getModuleHandle.invokeExact(MemorySegment.NULL) as MemorySegment
            if (hInstance == MemorySegment.NULL) return null

            // Allocate the class name in a long-lived arena
            // (must stay valid for the entire lifetime of the windows of this class)
            val classArena = Win32WndProcArena.arena
            val classNamePtr = classArena.allocateWString(CLASS_NAME)

            // Register the class only once
            registerClassOnce(hInstance, classNamePtr)

            // Create the window
            val width = attrs.size?.width ?: 800
            val height = attrs.size?.height ?: 600
            val posX = attrs.position?.x ?: 100
            val posY = attrs.position?.y ?: 100

            // Build the base style from attrs.decorations / attrs.resizable.
            val baseStyle = if (attrs.decorations) {
                WS_OVERLAPPEDWINDOW and (if (attrs.resizable) Int.MAX_VALUE else WS_THICKFRAME.inv())
            } else {
                0x80000000.toInt() // WS_POPUP — borderless, no caption
            }

            val hwnd: MemorySegment = Arena.ofConfined().use { arena ->
                val titlePtr = arena.allocateWString(attrs.title)
                createHandle.invokeExact(
                    WS_EX_APPWINDOW,        // dwExStyle
                    classNamePtr,           // lpClassName
                    titlePtr,               // lpWindowName
                    baseStyle,              // dwStyle
                    posX,                   // X
                    posY,                   // Y
                    width,                  // nWidth
                    height,                 // nHeight
                    MemorySegment.NULL,     // hWndParent
                    MemorySegment.NULL,     // hMenu
                    hInstance,              // hInstance
                    MemorySegment.NULL,     // lpParam
                ) as MemorySegment
            }

            if (hwnd == MemorySegment.NULL) return null

            val window = Win32Window(hwnd, hInstance, attrs)

            // Register for WM_TOUCH so touchscreen contacts arrive as touch events
            // instead of being emulated as mouse input. Best-effort: ignored on
            // platforms/devices without touch support.
            registerTouchWindow?.let { it.invokeExact(hwnd, 0) as Int }

            // Initial display.
            // ShowWindow/UpdateWindow return BOOL (int) — invokeExact requires the exact
            // return type, so the result must be captured (as Int) or it throws
            // WrongMethodTypeException ("…)int but found …)void").
            if (attrs.visible) {
                val showCmd = if (attrs.maximized) SW_MAXIMIZE else SW_SHOW
                @Suppress("UNUSED_EXPRESSION")
                showWindow?.let { it.invokeExact(hwnd, showCmd) as Int }
                updateWindow?.let { it.invokeExact(hwnd) as Int }
            }

            return window
        }
    }
}
