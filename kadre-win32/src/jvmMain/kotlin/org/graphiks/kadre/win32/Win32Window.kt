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

import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.CursorIcon
import org.graphiks.kadre.core.Fullscreen
import org.graphiks.kadre.core.Icon
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.InputCapabilities
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.RequestError
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.WindowLevel
import org.graphiks.kadre.core.WindowRequestResult
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

    override fun inputCapabilities(): InputCapabilities =
        InputCapabilities(touch = true)

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

    override fun availableMonitors(): List<MonitorHandle> =
        enumerateWin32Monitors()

    override fun primaryMonitor(): MonitorHandle? =
        enumerateWin32Monitors().firstOrNull { it.isPrimary }

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

    // ── R3: cursor, theme & appearance ───────────────────────────────────────

    /** Current cursor handle (loaded via LoadCursorW). */
    @Volatile private var _cursorHandle: MemorySegment? = null

    /**
     * Sets the cursor shape by loading the system cursor via LoadCursorW.
     *
     * The cursor is set immediately and takes effect on the next WM_SETCURSOR.
     */
    override fun setCursor(cursor: CursorIcon) {
        try {
            val id = cursorIdcResource(cursor)
            val hCursor = loadCursorW?.invokeExact(
                MemorySegment.NULL,
                MemorySegment.ofAddress(id),
            ) as? MemorySegment ?: return
            _cursorHandle = hCursor
            setCursor?.invokeExact(hCursor) as? MemorySegment
        } catch (_: Throwable) {}
    }

    /**
     * Shows or hides the system cursor via ShowCursor.
     *
     * Note: ShowCursor uses a display counter — multiple hide calls require
     * multiple show calls. We call it once each way as a best-effort.
     */
    override fun setCursorVisible(visible: Boolean) {
        try {
            showCursorHandle?.invokeExact(if (visible) 1 else 0) as? Int
        } catch (_: Throwable) {}
    }

    /**
     * Sets the cursor grab mode.
     *
     * - [CursorGrabMode.Confined]: calls ClipCursor with the client rect.
     * - [CursorGrabMode.Locked]:   same (no Pointer Lock API on Win32; raw input
     *   would be the full implementation, flagged as TODO).
     * - [CursorGrabMode.None]:     releases the clip via ClipCursor(NULL).
     *
     * Note: Risk FFM — ClipCursor writes a RECT via the pointer; layout must
     * be exact (16 bytes, 4-byte aligned).
     */
    override fun setCursorGrab(mode: CursorGrabMode): WindowRequestResult =
        try {
            val clip = clipCursor ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("Win32 ClipCursor is unavailable"),
            )
            when (mode) {
                CursorGrabMode.None -> {
                    val ok = clip.invokeExact(MemorySegment.NULL) as Int
                    if (ok == 0) return WindowRequestResult.Failure(RequestError.OsError("ClipCursor(NULL) failed"))
                }
                CursorGrabMode.Confined, CursorGrabMode.Locked -> {
                    val getRect = getWindowRect ?: return WindowRequestResult.Failure(
                        RequestError.Unsupported("Win32 GetWindowRect is unavailable"),
                    )
                    // ClipCursor expects SCREEN coordinates → GetWindowRect (not GetClientRect,
                    // whose top-left is always 0,0 and would confine to the screen corner).
                    Arena.ofConfined().use { arena ->
                        val rect = arena.allocate(RECT_SIZE, RECT_ALIGN)
                        val rectOk = getRect.invokeExact(hwnd, rect) as Int
                        if (rectOk == 0) return WindowRequestResult.Failure(RequestError.OsError("GetWindowRect failed for cursor grab"))
                        val clipOk = clip.invokeExact(rect) as Int
                        if (clipOk == 0) return WindowRequestResult.Failure(RequestError.OsError("ClipCursor failed"))
                    }
                }
            }
            WindowRequestResult.Success
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "Win32 cursor grab failed"))
        }

    /**
     * Moves the cursor to the specified window-relative position via SetCursorPos.
     */
    override fun setCursorPosition(position: PhysicalPosition<Int>): WindowRequestResult =
        try {
            val getRect = getWindowRect ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("Win32 GetWindowRect is unavailable"),
            )
            val setPos = setCursorPos ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("Win32 SetCursorPos is unavailable"),
            )
            // Convert window-client coords to screen coords via GetWindowRect
            val (screenX, screenY) = Arena.ofConfined().use { arena ->
                val rect = arena.allocate(RECT_SIZE, RECT_ALIGN)
                val ok = getRect.invokeExact(hwnd, rect) as Int
                if (ok == 0) return WindowRequestResult.Failure(RequestError.OsError("GetWindowRect failed for cursor position"))
                rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_LEFT) + position.x to
                    rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_TOP) + position.y
            }
            val ok = setPos.invokeExact(screenX, screenY) as Int
            if (ok == 0) {
                WindowRequestResult.Failure(RequestError.OsError("SetCursorPos failed"))
            } else {
                WindowRequestResult.Success
            }
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "Win32 cursor position failed"))
        }

    /**
     * Enables or disables click-through via WS_EX_TRANSPARENT (extended style).
     *
     * Note: WS_EX_TRANSPARENT makes the window transparent to mouse input.
     */
    override fun setCursorHittest(hittest: Boolean): WindowRequestResult =
        try {
            val getStyle = getWindowLongPtrW ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("Win32 GetWindowLongPtrW is unavailable"),
            )
            val setStyle = setWindowLongPtrW ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("Win32 SetWindowLongPtrW is unavailable"),
            )
            setLastError?.invokeExact(0)
            val exStyle = getStyle.invokeExact(hwnd, GWL_EXSTYLE) as Long
            if (exStyle == 0L) {
                val error = try { getLastError?.invokeExact() as? Int ?: 0 } catch (_: Throwable) { 0 }
                if (error != 0) {
                    return WindowRequestResult.Failure(RequestError.OsError("GetWindowLongPtrW failed: $error"))
                }
            }
            val transparentFlag = 0x00000020L // WS_EX_TRANSPARENT
            val newStyle = if (!hittest) exStyle or transparentFlag
                           else exStyle and transparentFlag.inv()
            setLastError?.invokeExact(0)
            val previous = setStyle.invokeExact(hwnd, GWL_EXSTYLE, newStyle) as Long
            if (previous == 0L) {
                val error = try { getLastError?.invokeExact() as? Int ?: 0 } catch (_: Throwable) { 0 }
                if (error != 0) {
                    return WindowRequestResult.Failure(RequestError.OsError("SetWindowLongPtrW failed: $error"))
                }
            }
            WindowRequestResult.Success
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "Win32 cursor hit-testing failed"))
        }

    /** In-memory theme for this window. */
    @Volatile private var _theme: Theme? = attrs.preferredTheme

    /**
     * Returns the current theme by reading the registry `AppsUseLightTheme`.
     */
    override val theme: Theme?
        get() = Win32ThemeHelper.systemThemeFromRegistry() ?: _theme

    /**
     * Applies a theme via DwmSetWindowAttribute(DWMWA_USE_IMMERSIVE_DARK_MODE).
     *
     * No-op on Windows < 11 where the attribute is not supported.
     * Risk FFM: DwmSetWindowAttribute passes a pointer to a BOOL; the layout is
     * a single 4-byte INT.
     */
    override fun setTheme(theme: Theme?) {
        _theme = theme
        try {
            Win32ThemeHelper.setWindowDarkMode(hwnd, theme ?: Win32ThemeHelper.systemThemeFromRegistry())
        } catch (_: Throwable) {}
    }

    /**
     * Sets the Z-order level via SetWindowPos with HWND_TOPMOST / HWND_BOTTOM.
     */
    override fun setWindowLevel(level: WindowLevel) {
        try {
            val insertAfter: MemorySegment = when (level) {
                WindowLevel.AlwaysOnTop    -> HWND_TOPMOST
                WindowLevel.Normal         -> HWND_NOTOPMOST
                WindowLevel.AlwaysOnBottom -> HWND_BOTTOM
            }
            // Change Z-order via insertAfter → must NOT pass SWP_NOZORDER.
            setWindowPos?.invokeExact(
                hwnd, insertAfter,
                0, 0, 0, 0,
                SWP_NOSIZE or SWP_NOMOVE or SWP_NOACTIVATE,
            ) as? Int
        } catch (_: Throwable) {}
    }

    /**
     * Makes the window background transparent via WS_EX_LAYERED +
     * SetLayeredWindowAttributes with LWA_ALPHA and alpha=255 (fully opaque
     * but with per-pixel alpha when the renderer uses alpha < 1).
     *
     * Note: actual transparency requires the renderer to paint with alpha < 1.
     */
    override fun setTransparent(transparent: Boolean) {
        try {
            val exStyle = try {
                getWindowLongPtrW?.invokeExact(hwnd, GWL_EXSTYLE) as? Long ?: 0L
            } catch (_: Throwable) { 0L }
            val newStyle = if (transparent) exStyle or WS_EX_LAYERED.toLong()
                           else exStyle and WS_EX_LAYERED.toLong().inv()
            setWindowLongPtrW?.invokeExact(hwnd, GWL_EXSTYLE, newStyle) as? Long
            if (transparent) {
                // LWA_ALPHA = 0x2, bAlpha = 255 (use per-pixel alpha from compositor)
                setLayeredWindowAttributes?.invokeExact(hwnd, 0, 255.toByte(), 0x2) as? Int
            }
        } catch (_: Throwable) {}
    }

    /**
     * No-op on Win32: blur requires third-party compositor extensions (ACRYLIC)
     * which are not exposed via standard Win32 API. Documented no-op.
     *
     * TODO(R3-win32-blur): implement via DwmEnableBlurBehindWindow or
     * SetWindowCompositionAttribute (undocumented Win10/11).
     */
    override fun setBlur(blur: Boolean) {
        // No-op: Win32 does not expose a standard blur API. DwmEnableBlurBehindWindow
        // was deprecated in Windows 8 and does not work on modern builds.
    }

    /**
     * Sets the window icon via WM_SETICON.
     *
     * Creates an HICON from the RGBA data via CreateIconFromResourceEx (best-effort).
     * Note: risk FFM — CreateIconFromResourceEx requires a packed DIB-format buffer.
     *
     * TODO(R3-win32-icon): full CreateBitmap + CreateIconIndirect implementation.
     */
    override fun setWindowIcon(icon: Icon?) {
        try {
            // Pass NULL to reset the icon
            sendMessageW?.invokeExact(hwnd, WM_SETICON, ICON_SMALL, 0L) as? Long
            sendMessageW?.invokeExact(hwnd, WM_SETICON, ICON_BIG, 0L) as? Long
            if (icon == null) return
            // TODO: full icon creation from RGBA data (CreateBitmap / CreateIconIndirect).
            // Current implementation resets to default (null HICON).
        } catch (_: Throwable) {}
    }

    // ── R4: keyboard ──────────────────────────────────────────────────────────

    /**
     * Resets any pending dead-key state by calling ToUnicode with a dummy key.
     *
     * Win32 maintains a dead-key state in the thread's key buffer. Calling
     * ToUnicode with VK_SPACE and the scan code 0x39 is the canonical way to
     * flush that state without producing visible text.
     *
     * **FFM risk (R4)**: if [toUnicode] is not available (non-Windows), this is a no-op.
     */
    override fun resetDeadKeys() {
        try {
            val handle = toUnicode ?: return
            Arena.ofConfined().use { arena ->
                val keyState = arena.allocate(256L, 1L)  // BYTE[256], native
                getKeyboardState?.invoke(keyState)
                val buf = arena.allocate(16L, 2L)        // 8 WCHARs
                // Flush dead-key buffer by translating VK_SPACE (0x20), scan 0x39
                handle.invokeExact(0x20, 0x39, keyState, buf, 8, 0) as Int
            }
        } catch (_: Throwable) {
            // Best-effort only
        }
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

/**
 * Maps a [CursorIcon] to the Win32 IDC_* resource id for LoadCursorW.
 */
internal fun cursorIdcResource(cursor: CursorIcon): Long = when (cursor) {
    CursorIcon.Default        -> IDC_ARROW
    CursorIcon.Pointer        -> IDC_HAND
    CursorIcon.Text           -> IDC_IBEAM
    CursorIcon.Crosshair      -> IDC_CROSS
    CursorIcon.Move           -> IDC_SIZEALL
    CursorIcon.ResizeNorth,
    CursorIcon.ResizeSouth,
    CursorIcon.NsResize,
    CursorIcon.RowResize      -> IDC_SIZENS
    CursorIcon.ResizeEast,
    CursorIcon.ResizeWest,
    CursorIcon.EwResize,
    CursorIcon.ColResize      -> IDC_SIZEWE
    CursorIcon.ResizeNorthEast,
    CursorIcon.ResizeSouthWest,
    CursorIcon.NeswResize     -> IDC_SIZENESW
    CursorIcon.ResizeNorthWest,
    CursorIcon.ResizeSouthEast,
    CursorIcon.NwseResize     -> IDC_SIZENWSE
    CursorIcon.NotAllowed     -> IDC_NO
    CursorIcon.Grab,
    CursorIcon.Grabbing       -> IDC_SIZEALL
    CursorIcon.Wait           -> IDC_WAIT
    CursorIcon.Progress       -> IDC_APPSTARTING
}
