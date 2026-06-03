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
import org.graphiks.kadre.core.ResizeDirection
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.UserAttentionType
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowButtons
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
import java.util.concurrent.ConcurrentHashMap
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
    private val ownerThreadId: Int,
) : Window {

    // ── R2 fullscreen state ──────────────────────────────────────────────────

    /** In-memory fullscreen state (R2). */
    @Volatile private var _fullscreen: Fullscreen? = attrs.fullscreen

    /** Saved window style before entering borderless fullscreen (for restoration). */
    @Volatile private var _savedStyle: Long? = null

    /** Saved window rect before entering borderless fullscreen (for restoration). */
    @Volatile private var _savedRect: IntArray? = null

    /** Tracks enabled title-bar/system-menu buttons, matching winit's WindowButtons model. */
    @Volatile private var _enabledButtons: WindowButtons = attrs.enabledButtons

    private val iconLock = Any()
    private var ownedWindowIconHandle: MemorySegment = MemorySegment.NULL

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
        setWindowIcon(null)
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

    override val isVisible: Boolean?
        get() = try {
            isWindowVisible?.let { (it.invokeExact(hwnd) as Int) != 0 }
        } catch (_: Throwable) { null }

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
                WIN32_STYLE_UPDATE_FLAGS,
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

    override val isMinimized: Boolean?
        get() = try {
            isIconic?.let { (it.invokeExact(hwnd) as Int) != 0 }
        } catch (_: Throwable) { null }

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
        applyEnabledButtons(enabledButtons)
    }

    override val isDecorated: Boolean
        get() = (getWindowStyle() and WS_CAPTION.toLong()) != 0L

    override fun setEnabledButtons(buttons: WindowButtons) {
        _enabledButtons = buttons
        applyEnabledButtons(buttons)
    }

    override val enabledButtons: WindowButtons
        get() = _enabledButtons

    private fun applyEnabledButtons(buttons: WindowButtons) {
        val style = getWindowStyle()
        val decorated = (style and WS_CAPTION.toLong()) != 0L
        val newStyle = style.withEnabledWindowButtonStyles(buttons, decorated)
        if (newStyle != style) {
            setWindowStyle(newStyle)
        }
        updateCloseMenuItem(buttons.contains(WindowButtons.CLOSE))
    }

    private fun updateCloseMenuItem(enabled: Boolean) {
        try {
            val menu = getSystemMenu?.invokeExact(hwnd, 0) as? MemorySegment ?: return
            if (menu == MemorySegment.NULL) return
            enableMenuItem?.invokeExact(
                menu,
                SC_CLOSE,
                win32CloseMenuState(enabled),
            ) as? Int
        } catch (_: Throwable) {}
    }

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

    override fun focusWindow() {
        try {
            val visible = isVisible == true
            val minimized = isMinimized == true
            if (win32ShouldFocusWindow(visible, minimized, isForegroundWindow())) {
                forceWindowActive(hwnd)
            }
        } catch (_: Throwable) {}
    }

    override val hasFocus: Boolean
        get() = Win32FocusState.hasActiveFocus(hwnd.address())

    private fun isForegroundWindow(): Boolean =
        try {
            val foreground = getForegroundWindow?.invokeExact() as? MemorySegment ?: return false
            foreground.address() == hwnd.address()
        } catch (_: Throwable) {
            false
        }

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
            SWP_NOACTIVATE or SWP_FRAMECHANGED,
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
                    SWP_NOACTIVATE or SWP_FRAMECHANGED,
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

    /**
     * Shows the native Win32 system menu at a window-relative physical position.
     */
    override fun showWindowMenu(position: PhysicalPosition<Int>): WindowRequestResult =
        try {
            val menuHandle = getSystemMenu ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("Win32 GetSystemMenu is unavailable"),
            )
            val trackMenu = trackPopupMenu ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("Win32 TrackPopupMenu is unavailable"),
            )
            val postMessage = postMessageW ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("Win32 PostMessageW is unavailable"),
            )
            val toScreen = clientToScreen ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("Win32 ClientToScreen is unavailable"),
            )
            val menu = menuHandle.invokeExact(hwnd, 0) as MemorySegment
            if (menu == MemorySegment.NULL) {
                return WindowRequestResult.Success
            }
            syncSystemMenuState(menu)
            val (screenX, screenY) = Arena.ofConfined().use { arena ->
                val point = arena.allocate(POINT_SIZE, POINT_ALIGN)
                point.set(ValueLayout.JAVA_INT, POINT_OFFSET_X, position.x)
                point.set(ValueLayout.JAVA_INT, POINT_OFFSET_Y, position.y)
                val ok = toScreen.invokeExact(hwnd, point) as Int
                if (ok == 0) return WindowRequestResult.Failure(RequestError.OsError("ClientToScreen failed for window menu"))
                point.get(ValueLayout.JAVA_INT, POINT_OFFSET_X) to
                    point.get(ValueLayout.JAVA_INT, POINT_OFFSET_Y)
            }
            val command = trackMenu.invokeExact(
                menu,
                TPM_RETURNCMD or TPM_LEFTALIGN,
                screenX,
                screenY,
                0,
                hwnd,
                MemorySegment.NULL,
            ) as Int
            if (command != 0) {
                val ok = postMessage.invokeExact(hwnd, WM_SYSCOMMAND, command.toLong(), 0L) as Int
                if (ok == 0) return WindowRequestResult.Failure(RequestError.OsError("PostMessageW(WM_SYSCOMMAND) failed"))
            }
            WindowRequestResult.Success
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "Win32 window menu failed"))
        }

    /**
     * Starts a native system move drag from the current pointer position.
     */
    override fun dragWindow(): WindowRequestResult =
        sendNonClientDrag(HTCAPTION, "Win32 window drag failed")

    /**
     * Starts a native system resize drag for the requested border/corner.
     */
    override fun dragResizeWindow(direction: ResizeDirection): WindowRequestResult =
        sendNonClientDrag(direction.toWin32HitTest(), "Win32 resize drag failed")

    private fun sendNonClientDrag(hitTest: Long, failureMessage: String): WindowRequestResult =
        try {
            val postMessage = postMessageW ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("Win32 PostMessageW is unavailable"),
            )
            if (!isOwnerThread()) {
                val ok = postMessage.invokeExact(hwnd, WM_KADRE_NON_CLIENT_DRAG, hitTest, 0L) as Int
                return if (ok == 0) {
                    WindowRequestResult.Failure(RequestError.OsError("PostMessageW(WM_KADRE_NON_CLIENT_DRAG) failed"))
                } else {
                    WindowRequestResult.Success
                }
            }
            performNonClientDrag(hwnd, hitTest)
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: failureMessage))
        }

    private fun isOwnerThread(): Boolean {
        val current = currentWin32ThreadId()
        return current == 0 || ownerThreadId == 0 || current == ownerThreadId
    }

    private fun syncSystemMenuState(menu: MemorySegment) {
        try {
            val enableItem = enableMenuItem ?: return
            fun state(enabled: Boolean): Int = if (enabled) MFS_ENABLED else MFS_DISABLED
            val maximized = isMaximized
            val resizable = isResizable
            enableItem.invokeExact(menu, SC_RESTORE, MF_BYCOMMAND or state(maximized && resizable)) as Int
            enableItem.invokeExact(menu, SC_MOVE, MF_BYCOMMAND or state(!maximized)) as Int
            enableItem.invokeExact(menu, SC_SIZE, MF_BYCOMMAND or state(!maximized && resizable)) as Int
            enableItem.invokeExact(menu, SC_MINIMIZE, MF_BYCOMMAND or MFS_ENABLED) as Int
            enableItem.invokeExact(menu, SC_MAXIMIZE, MF_BYCOMMAND or state(!maximized && resizable)) as Int
            enableItem.invokeExact(menu, SC_CLOSE, MF_BYCOMMAND or MFS_ENABLED) as Int
            setMenuDefaultItem?.let { it.invokeExact(menu, SC_CLOSE, 0) as Int }
        } catch (_: Throwable) {
            // Menu state synchronization is best-effort; showing the menu is still useful.
        }
    }

    private fun ResizeDirection.toWin32HitTest(): Long =
        when (this) {
            ResizeDirection.East -> HTRIGHT
            ResizeDirection.North -> HTTOP
            ResizeDirection.NorthEast -> HTTOPRIGHT
            ResizeDirection.NorthWest -> HTTOPLEFT
            ResizeDirection.South -> HTBOTTOM
            ResizeDirection.SouthEast -> HTBOTTOMRIGHT
            ResizeDirection.SouthWest -> HTBOTTOMLEFT
            ResizeDirection.West -> HTLEFT
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
            // Change Z-order via insertAfter → must NOT pass SWP_NOZORDER.
            setWindowPos?.invokeExact(
                hwnd, win32WindowLevelInsertAfter(level),
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
     * Mirrors winit's Win32 RGBA path: Kadre's RGBA bytes are converted to
     * BGRA color bits and paired with an inverted-alpha AND mask for CreateIcon.
     */
    override fun setWindowIcon(icon: Icon?) {
        var newHandle = MemorySegment.NULL
        try {
            if (icon != null) {
                newHandle = win32CreateIcon(hInstance, icon) ?: return
            }
            val send = sendMessageW ?: return
            synchronized(iconLock) {
                send.invokeExact(hwnd, WM_SETICON, ICON_SMALL, newHandle.address()) as Long
                val oldHandle = ownedWindowIconHandle
                ownedWindowIconHandle = newHandle
                win32DestroyIcon(oldHandle)
                newHandle = MemorySegment.NULL
            }
        } catch (_: Throwable) {}
        finally {
            win32DestroyIcon(newHandle)
        }
    }

    override fun setContentProtected(protected: Boolean): WindowRequestResult =
        try {
            val setAffinity = setWindowDisplayAffinity ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("Win32 SetWindowDisplayAffinity is unavailable"),
            )
            val affinity = if (protected) WDA_EXCLUDEFROMCAPTURE else WDA_NONE
            val ok = setAffinity.invokeExact(hwnd, affinity) as Int
            if (ok == 0) {
                WindowRequestResult.Failure(RequestError.OsError("SetWindowDisplayAffinity failed"))
            } else {
                WindowRequestResult.Success
            }
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "Win32 content protection failed"))
        }

    override fun requestUserAttention(requestType: UserAttentionType?): WindowRequestResult =
        try {
            val flash = flashWindowEx ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("Win32 FlashWindowEx is unavailable"),
            )
            val active = getActiveWindow?.invokeExact() as? MemorySegment
            if (active != null && active.address() == hwnd.address()) {
                return WindowRequestResult.Success
            }
            val (flags, count) = when (requestType) {
                UserAttentionType.Critical -> (FLASHW_ALL or FLASHW_TIMERNOFG) to -1
                UserAttentionType.Informational -> (FLASHW_TRAY or FLASHW_TIMERNOFG) to 0
                null -> FLASHW_STOP to 0
            }
            Arena.ofConfined().use { arena ->
                val info = arena.allocate(FLASHWINFO_SIZE, FLASHWINFO_ALIGN)
                info.set(ValueLayout.JAVA_INT, FLASHWINFO_CB_SIZE_OFFSET, FLASHWINFO_SIZE.toInt())
                info.set(ValueLayout.ADDRESS, FLASHWINFO_HWND_OFFSET, hwnd)
                info.set(ValueLayout.JAVA_INT, FLASHWINFO_FLAGS_OFFSET, flags)
                info.set(ValueLayout.JAVA_INT, FLASHWINFO_COUNT_OFFSET, count)
                info.set(ValueLayout.JAVA_INT, FLASHWINFO_TIMEOUT_OFFSET, 0)
                flash.invokeExact(info) as Int
                WindowRequestResult.Success
            }
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "Win32 user attention failed"))
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

        internal fun performNonClientDrag(hwnd: MemorySegment, hitTest: Long): WindowRequestResult =
            try {
                val release = releaseCapture ?: return WindowRequestResult.Failure(
                    RequestError.Unsupported("Win32 ReleaseCapture is unavailable"),
                )
                val postMessage = postMessageW ?: return WindowRequestResult.Failure(
                    RequestError.Unsupported("Win32 PostMessageW is unavailable"),
                )
                release.invokeExact() as Int
                val ok = postMessage.invokeExact(hwnd, WM_NCLBUTTONDOWN, hitTest, currentCursorLParam()) as Int
                if (ok == 0) {
                    WindowRequestResult.Failure(RequestError.OsError("PostMessageW(WM_NCLBUTTONDOWN) failed"))
                } else {
                    WindowRequestResult.Success
                }
            } catch (t: Throwable) {
                WindowRequestResult.Failure(
                    RequestError.OsError(t.message ?: t::class.simpleName ?: "Win32 native move/resize drag failed"),
                )
            }

        private fun currentCursorLParam(): Long =
            try {
                val cursor = getCursorPos ?: return 0L
                Arena.ofConfined().use { arena ->
                    val point = arena.allocate(POINT_SIZE, POINT_ALIGN)
                    val ok = cursor.invokeExact(point) as Int
                    if (ok == 0) return@use 0L
                    val x = point.get(ValueLayout.JAVA_INT, POINT_OFFSET_X)
                    val y = point.get(ValueLayout.JAVA_INT, POINT_OFFSET_Y)
                    ((y.toLong() and 0xffffL) shl 16) or (x.toLong() and 0xffffL)
                }
            } catch (_: Throwable) {
                0L
            }

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
            val buttonStyle = win32StyleWithEnabledButtons(baseStyle, attrs.enabledButtons, attrs.decorations)

            val hwnd: MemorySegment = Arena.ofConfined().use { arena ->
                val titlePtr = arena.allocateWString(attrs.title)
                createHandle.invokeExact(
                    win32InitialExtendedStyle(attrs.transparent), // dwExStyle
                    classNamePtr,           // lpClassName
                    titlePtr,               // lpWindowName
                    buttonStyle,            // dwStyle
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

            val window = Win32Window(hwnd, hInstance, attrs, currentWin32ThreadId())
            Win32FocusState.register(hwnd.address())
            window.applyEnabledButtons(attrs.enabledButtons)
            window.setWindowLevel(attrs.windowLevel)
            attrs.windowIcon?.let(window::setWindowIcon)
            if (attrs.transparent) {
                window.setTransparent(true)
                enableWin32TransparentBlurBehind(hwnd)
            }

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

internal object Win32FocusState {
    private data class State(
        @Volatile var active: Boolean = false,
        @Volatile var focused: Boolean = false,
    )

    private val states = ConcurrentHashMap<Long, State>()

    fun register(hwnd: Long) {
        states.putIfAbsent(hwnd, State())
    }

    fun unregister(hwnd: Long) {
        states.remove(hwnd)
    }

    @Synchronized
    fun setActive(hwnd: Long, active: Boolean): Boolean? {
        val state = state(hwnd)
        val previous = state.hasActiveFocus
        state.active = active
        val current = state.hasActiveFocus
        return current.takeIf { previous != current }
    }

    @Synchronized
    fun setFocused(hwnd: Long, focused: Boolean): Boolean? {
        val state = state(hwnd)
        val previous = state.hasActiveFocus
        state.focused = focused
        val current = state.hasActiveFocus
        return current.takeIf { previous != current }
    }

    @Synchronized
    fun hasActiveFocus(hwnd: Long): Boolean {
        val state = states[hwnd] ?: return false
        return state.hasActiveFocus
    }

    private fun state(hwnd: Long): State =
        states.computeIfAbsent(hwnd) { State() }

    private val State.hasActiveFocus: Boolean
        get() = active && focused
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

internal const val WIN32_STYLE_UPDATE_FLAGS: Int =
    SWP_NOSIZE or SWP_NOMOVE or SWP_NOZORDER or SWP_NOACTIVATE or SWP_FRAMECHANGED

internal fun win32CloseMenuState(enabled: Boolean): Int =
    MF_BYCOMMAND or if (enabled) MF_ENABLED else MF_DISABLED

internal fun win32ShouldFocusWindow(
    isVisible: Boolean,
    isMinimized: Boolean,
    isForeground: Boolean,
): Boolean =
    isVisible && !isMinimized && !isForeground

internal fun win32InitialExtendedStyle(transparent: Boolean): Int =
    if (transparent) {
        WS_EX_APPWINDOW or WS_EX_LAYERED
    } else {
        WS_EX_APPWINDOW
    }

internal fun win32TransparentBlurBehindFlags(): Int =
    DWM_BB_ENABLE or DWM_BB_BLURREGION

internal fun enableWin32TransparentBlurBehind(hwnd: MemorySegment): Boolean {
    val createRegion = createRectRgn ?: return false
    val enableBlur = dwmEnableBlurBehindWindow ?: return false
    var region = MemorySegment.NULL
    return try {
        region = createRegion.invokeExact(0, 0, -1, -1) as MemorySegment
        if (region == MemorySegment.NULL) return false
        Arena.ofConfined().use { arena ->
            val blurBehind = arena.allocate(DWM_BLURBEHIND_SIZE, DWM_BLURBEHIND_ALIGN)
            blurBehind.set(ValueLayout.JAVA_INT, DWM_BLURBEHIND_OFFSET_DW_FLAGS, win32TransparentBlurBehindFlags())
            blurBehind.set(ValueLayout.JAVA_INT, DWM_BLURBEHIND_OFFSET_F_ENABLE, 1)
            blurBehind.set(ValueLayout.ADDRESS, DWM_BLURBEHIND_OFFSET_H_RGN_BLUR, region)
            blurBehind.set(ValueLayout.JAVA_INT, DWM_BLURBEHIND_OFFSET_F_TRANSITION_ON_MAXIMIZED, 0)
            val hr = enableBlur.invokeExact(hwnd, blurBehind) as Int
            hr >= 0
        }
    } catch (_: Throwable) {
        false
    } finally {
        if (region != MemorySegment.NULL) {
            try {
                deleteObject?.let { it.invokeExact(region) as Int }
            } catch (_: Throwable) {}
        }
    }
}

internal fun win32WindowLevelInsertAfter(level: WindowLevel): MemorySegment =
    when (level) {
        WindowLevel.AlwaysOnTop -> HWND_TOPMOST
        WindowLevel.Normal -> HWND_NOTOPMOST
        WindowLevel.AlwaysOnBottom -> HWND_BOTTOM
    }

internal fun win32StyleWithEnabledButtons(
    style: Int,
    buttons: WindowButtons,
    decorated: Boolean = true,
): Int =
    style
        .withStyleBit(WS_MINIMIZEBOX, decorated && buttons.contains(WindowButtons.MINIMIZE))
        .withStyleBit(WS_MAXIMIZEBOX, decorated && buttons.contains(WindowButtons.MAXIMIZE))

private fun Long.withEnabledWindowButtonStyles(buttons: WindowButtons, decorated: Boolean): Long =
    this
        .withStyleBit(WS_MINIMIZEBOX.toLong(), decorated && buttons.contains(WindowButtons.MINIMIZE))
        .withStyleBit(WS_MAXIMIZEBOX.toLong(), decorated && buttons.contains(WindowButtons.MAXIMIZE))

private fun Int.withStyleBit(bit: Int, enabled: Boolean): Int =
    if (enabled) this or bit else this and bit.inv()

private fun Long.withStyleBit(bit: Long, enabled: Boolean): Long =
    if (enabled) this or bit else this and bit.inv()

private fun forceWindowActive(hwnd: MemorySegment) {
    val setForeground = setForegroundWindow ?: return
    val send = sendInput
    val map = mapVirtualKeyW
    if (send != null && map != null) {
        try {
            val scanCode = map.invokeExact(VK_MENU, MAPVK_VK_TO_VSC) as Int
            Arena.ofConfined().use { arena ->
                val inputs = arena.allocate(INPUT_SIZE * 2, INPUT_ALIGN)
                fillKeyboardInput(inputs, index = 0, scanCode = scanCode, flags = KEYEVENTF_EXTENDEDKEY)
                fillKeyboardInput(
                    inputs,
                    index = 1,
                    scanCode = scanCode,
                    flags = KEYEVENTF_EXTENDEDKEY or KEYEVENTF_KEYUP,
                )
                send.invokeExact(2, inputs, INPUT_SIZE.toInt()) as Int
            }
        } catch (_: Throwable) {
            // Fall through to SetForegroundWindow; the Alt-key permission hack is best-effort.
        }
    }
    setForeground.invokeExact(hwnd) as Int
}

private fun fillKeyboardInput(inputs: MemorySegment, index: Int, scanCode: Int, flags: Int) {
    val offset = INPUT_SIZE * index
    inputs.set(ValueLayout.JAVA_INT, offset + INPUT_OFFSET_TYPE, INPUT_KEYBOARD)
    inputs.set(ValueLayout.JAVA_SHORT, offset + INPUT_OFFSET_KI_WVK, VK_LMENU.toShort())
    inputs.set(ValueLayout.JAVA_SHORT, offset + INPUT_OFFSET_KI_WSCAN, scanCode.toShort())
    inputs.set(ValueLayout.JAVA_INT, offset + INPUT_OFFSET_KI_DWFLAGS, flags)
    inputs.set(ValueLayout.JAVA_INT, offset + INPUT_OFFSET_KI_TIME, 0)
    inputs.set(ValueLayout.JAVA_LONG, offset + INPUT_OFFSET_KI_DWEXTRAINFO, 0L)
}

internal data class Win32IconBuffers(
    val andMask: ByteArray,
    val bgra: ByteArray,
)

internal fun win32IconBuffers(icon: Icon): Win32IconBuffers? {
    if (icon.width <= 0 || icon.height <= 0) return null
    val pixelCount = icon.width.toLong() * icon.height.toLong()
    val byteCount = pixelCount * 4L
    if (byteCount > Int.MAX_VALUE || icon.rgba.size != byteCount.toInt()) return null

    val andMask = ByteArray(pixelCount.toInt())
    val bgra = ByteArray(byteCount.toInt())
    var source = 0
    var target = 0
    var pixel = 0
    while (source < icon.rgba.size) {
        val red = icon.rgba[source]
        val green = icon.rgba[source + 1]
        val blue = icon.rgba[source + 2]
        val alpha = icon.rgba[source + 3]

        bgra[target] = blue
        bgra[target + 1] = green
        bgra[target + 2] = red
        bgra[target + 3] = alpha
        andMask[pixel] = ((alpha.toInt() and 0xFF) - 255).toByte()

        source += 4
        target += 4
        pixel += 1
    }
    return Win32IconBuffers(andMask = andMask, bgra = bgra)
}

private fun win32CreateIcon(hInstance: MemorySegment, icon: Icon): MemorySegment? {
    val create = createIcon ?: return null
    val buffers = win32IconBuffers(icon) ?: return null
    return Arena.ofConfined().use { arena ->
        val andMask = arena.allocate(buffers.andMask.size.toLong(), 1L)
        val bgra = arena.allocate(buffers.bgra.size.toLong(), 1L)
        for (index in buffers.andMask.indices) {
            andMask.setAtIndex(ValueLayout.JAVA_BYTE, index.toLong(), buffers.andMask[index])
        }
        for (index in buffers.bgra.indices) {
            bgra.setAtIndex(ValueLayout.JAVA_BYTE, index.toLong(), buffers.bgra[index])
        }
        val handle = create.invokeExact(
            hInstance,
            icon.width,
            icon.height,
            1.toByte(),
            32.toByte(),
            andMask,
            bgra,
        ) as MemorySegment
        handle.takeUnless { it == MemorySegment.NULL }
    }
}

private fun win32DestroyIcon(handle: MemorySegment) {
    if (handle == MemorySegment.NULL) return
    try {
        destroyIcon?.invokeExact(handle) as? Int
    } catch (_: Throwable) {}
}
