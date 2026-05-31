/**
 * Centralized Win32 window procedure (WndProc) for kadre.
 *
 * This file defines [KadreWndProc], a singleton object that dispatches Win32 messages
 * to kadre-core [WindowEvent]s, and forwards them to the associated [Win32Window] instance
 * via a [WindowEventHandler] installed by the caller.
 *
 * ## Architecture
 *
 * ```
 *  Win32 OS  ─[WM_*]→  KadreWndProc.dispatch()
 *                           │
 *                           ├─ translates the message into a WindowEvent
 *                           │
 *                           └─ resolves Win32Window via windowResolver(hwnd)
 *                                   │
 *                                   └─ calls WindowEventHandler.onEvent(window, event)
 * ```
 *
 * ## Usage
 *
 * ```kotlin
 * KadreWndProc.install { hwnd -> win32WindowMap[hwnd] }
 * // …
 * // The FFM stub calls KadreWndProc.dispatch(hwnd, msg, wParam, lParam)
 * ```
 *
 * ## Platform constraints
 *
 * On macOS/Linux, no real FFM call is performed. The [dispatch] method
 * can be called in tests with synthetic values.
 *
 * @see WindowEvent
 * @see Win32KeyMapper
 * @see Win32Constants
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

// ── Handler interface ─────────────────────────────────────────────────────────

/**
 * Receiver for Win32 window events.
 *
 * Implemented by the application layer (Win32 EventLoop) to receive the [WindowEvent]s
 * translated by [KadreWndProc] and dispatch them to the [org.graphiks.kadre.core.ApplicationHandler].
 */
fun interface WindowEventHandler {
    /**
     * Called for each window event translated from a Win32 message.
     *
     * @param hwnd  Native handle of the source window (64-bit integer address).
     * @param event Translated kadre event.
     */
    fun onEvent(hwnd: Long, event: WindowEvent)
}

// ── KadreWndProc ─────────────────────────────────────────────────────────────

/**
 * Central dispatcher for Win32 messages → kadre [WindowEvent]s.
 *
 * ### Installation
 * Before the message loop starts, call [install] to register
 * the handler that will receive the events:
 *
 * ```kotlin
 * KadreWndProc.install { hwnd, event ->
 *     win32WindowMap[hwnd]?.let { appHandler.onWindowEvent(it.id, event) }
 * }
 * ```
 *
 * ### Call from Win32Window
 * [Win32Window] must route its static `wndProc` to [dispatch]:
 *
 * ```kotlin
 * @JvmStatic
 * fun wndProc(hwnd: MemorySegment, msg: Int, wParam: Long, lParam: Long): Long =
 *     KadreWndProc.dispatch(hwnd.address(), msg, wParam, lParam)
 * ```
 */
object KadreWndProc {

    /**
     * Handler installed by the caller via [install].
     *
     * Null until [install] is called — events are silently
     * ignored (but DefWindowProcW is always called for unhandled messages).
     */
    @Volatile
    private var handler: WindowEventHandler? = null

    // ── Installation ──────────────────────────────────────────────────────────

    /**
     * Registers the [WindowEventHandler] that will receive the translated [WindowEvent]s.
     *
     * Must be called before the Win32 message loop starts.
     * Thread-safe (volatile write).
     *
     * @param handler Handler implementation (lambda or object).
     */
    fun install(handler: WindowEventHandler) {
        this.handler = handler
    }

    /**
     * Unregisters the current handler.
     *
     * Useful at the end of the message loop to avoid object leaks.
     */
    fun uninstall() {
        handler = null
    }

    // ── Dispatch ──────────────────────────────────────────────────────────────

    /**
     * Dispatches a Win32 message to a kadre [WindowEvent] and forwards it to the handler.
     *
     * Signature compatible with the Win32 WndProc:
     * `LRESULT CALLBACK KadreWndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam)`
     *
     * Unrecognized messages are delegated to [defWindowProcW].
     *
     * @param hwnd   Integer address of the HWND (MemorySegment.address()).
     * @param msg    Message identifier (WM_*).
     * @param wParam First message parameter.
     * @param lParam Second message parameter.
     * @return LRESULT to return to Windows.
     */
    fun dispatch(hwnd: Long, msg: Int, wParam: Long, lParam: Long): Long {
        return when (msg.toUInt()) {

            // ── Redraw ────────────────────────────────────────────────────────
            WM_PAINT.toUInt() -> {
                emit(hwnd, WindowEvent.RedrawRequested)
                // Return 0 without calling BeginPaint/EndPaint — the window will be
                // redrawn by the render engine (wgpu4k) on the next frame.
                0L
            }

            // ── Resize ────────────────────────────────────────────────────────
            WM_SIZE.toUInt() -> {
                // lParam: LOWORD = new width, HIWORD = new height (client pixels)
                val width  = (lParam and 0xFFFF).toInt()
                val height = ((lParam ushr 16) and 0xFFFF).toInt()
                emit(hwnd, WindowEvent.Resized(PhysicalSize(width, height)))
                0L
            }

            // ── Move ──────────────────────────────────────────────────────────
            WM_MOVE.toUInt() -> {
                // lParam: LOWORD = x, HIWORD = y (client top-left, screen coords, signed)
                val x = (lParam and 0xFFFF).toShort().toInt()
                val y = ((lParam ushr 16) and 0xFFFF).toShort().toInt()
                emit(hwnd, WindowEvent.Moved(PhysicalPosition(x, y)))
                0L
            }

            // ── Focus ─────────────────────────────────────────────────────────
            WM_SETFOCUS.toUInt() -> {
                emit(hwnd, WindowEvent.Focused(gained = true))
                0L
            }
            WM_KILLFOCUS.toUInt() -> {
                emit(hwnd, WindowEvent.Focused(gained = false))
                0L
            }

            // ── Keyboard ──────────────────────────────────────────────────────
            WM_KEYDOWN.toUInt(),
            WM_SYSKEYDOWN.toUInt() -> {
                val key      = Win32KeyMapper.fromVkCode(wParam.toInt())
                val isRepeat = (lParam and KF_REPEAT) != 0L
                val mods     = currentModifiers()
                emit(hwnd, WindowEvent.KeyboardInput(key, KeyState.Pressed, mods, isRepeat))
                0L
            }

            WM_KEYUP.toUInt(),
            WM_SYSKEYUP.toUInt() -> {
                val key  = Win32KeyMapper.fromVkCode(wParam.toInt())
                val mods = currentModifiers()
                emit(hwnd, WindowEvent.KeyboardInput(key, KeyState.Released, mods, isRepeat = false))
                0L
            }

            // ── Cursor movement ───────────────────────────────────────────────
            WM_MOUSEMOVE.toUInt() -> {
                val x = (lParam and 0xFFFF).toDouble()
                val y = ((lParam ushr 16) and 0xFFFF).toDouble()
                emit(hwnd, WindowEvent.PointerMoved(PhysicalPosition(x, y)))
                // Arm TrackMouseEvent to detect WM_MOUSELEAVE
                armMouseLeaveTracking(hwnd)
                0L
            }

            // ── Cursor exit ───────────────────────────────────────────────────
            WM_MOUSELEAVE.toUInt() -> {
                // WM_MOUSELEAVE tracking is automatically disarmed after receipt.
                // It will be re-armed on the next WM_MOUSEMOVE if the cursor returns.
                emit(hwnd, WindowEvent.PointerLeft)
                0L
            }

            // ── Mouse buttons ─────────────────────────────────────────────────
            WM_LBUTTONDOWN.toUInt() -> {
                emit(hwnd, WindowEvent.MouseInput(MouseButton.Left, KeyState.Pressed))
                0L
            }
            WM_LBUTTONUP.toUInt() -> {
                emit(hwnd, WindowEvent.MouseInput(MouseButton.Left, KeyState.Released))
                0L
            }
            WM_RBUTTONDOWN.toUInt() -> {
                emit(hwnd, WindowEvent.MouseInput(MouseButton.Right, KeyState.Pressed))
                0L
            }
            WM_RBUTTONUP.toUInt() -> {
                emit(hwnd, WindowEvent.MouseInput(MouseButton.Right, KeyState.Released))
                0L
            }
            WM_MBUTTONDOWN.toUInt() -> {
                emit(hwnd, WindowEvent.MouseInput(MouseButton.Middle, KeyState.Pressed))
                0L
            }
            WM_MBUTTONUP.toUInt() -> {
                emit(hwnd, WindowEvent.MouseInput(MouseButton.Middle, KeyState.Released))
                0L
            }

            // ── Additional buttons (X1 / X2) ─────────────────────────────────
            WM_XBUTTONDOWN.toUInt() -> {
                // HIWORD(wParam) = X button number (XBUTTON1 = 1, XBUTTON2 = 2)
                val xButton = ((wParam ushr 16) and 0xFFFF).toInt()
                val button = MouseButton.Other(xButton)
                emit(hwnd, WindowEvent.MouseInput(button, KeyState.Pressed))
                // WM_XBUTTONDOWN must return TRUE (non-zero) per the Win32 docs
                1L
            }
            WM_XBUTTONUP.toUInt() -> {
                val xButton = ((wParam ushr 16) and 0xFFFF).toInt()
                val button = MouseButton.Other(xButton)
                emit(hwnd, WindowEvent.MouseInput(button, KeyState.Released))
                // WM_XBUTTONUP must return TRUE (non-zero) per the Win32 docs
                1L
            }

            // ── Wheel ─────────────────────────────────────────────────────────
            WM_MOUSEWHEEL.toUInt() -> {
                // wParam: HIWORD = signed delta (multiple of WHEEL_DELTA = 120)
                val rawDelta = ((wParam ushr 16) and 0xFFFF).toShort().toInt()
                val deltaY   = rawDelta.toDouble() / WHEEL_DELTA
                emit(hwnd, WindowEvent.MouseWheel(deltaX = 0.0, deltaY = deltaY))
                0L
            }

            // ── Close ─────────────────────────────────────────────────────────
            WM_CLOSE.toUInt() -> {
                emit(hwnd, WindowEvent.CloseRequested)
                // Do not call DefWindowProcW here — the application decides whether to destroy the window.
                0L
            }

            // ── Destroy ───────────────────────────────────────────────────────
            WM_DESTROY.toUInt() -> {
                emit(hwnd, WindowEvent.Destroyed)
                // PostQuitMessage(0) — signal to end the Win32 message loop.
                postQuitMessage(0)
                0L
            }

            // ── DPI change ────────────────────────────────────────────────────
            WM_DPICHANGED.toUInt() -> {
                // wParam: LOWORD = new DPI X, HIWORD = new DPI Y
                val dpiX   = (wParam and DPI_WPARAM_MASK).toInt()
                val factor = dpiX.toDouble() / 96.0  // 96 DPI = factor 1.0 (100%)
                emit(hwnd, WindowEvent.ScaleFactorChanged(factor))
                0L
            }

            // ── Default ───────────────────────────────────────────────────────
            else -> defWindowProcW(hwnd, msg, wParam, lParam)
        }
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    /**
     * Forwards a [WindowEvent] to the installed [handler], if present.
     */
    private fun emit(hwnd: Long, event: WindowEvent) {
        handler?.onEvent(hwnd, event)
    }

    /**
     * Calls DefWindowProcW via the FFM binding (lazy, null on macOS/Linux).
     *
     * @param hwnd  Integer address of the HWND.
     * @param msg   Message identifier.
     * @param wParam First parameter.
     * @param lParam Second parameter.
     * @return Return value of DefWindowProcW, or 0 if the binding is unavailable.
     */
    private fun defWindowProcW(hwnd: Long, msg: Int, wParam: Long, lParam: Long): Long {
        val handle = defWindowProcW ?: return 0L
        val hwndSeg = MemorySegment.ofAddress(hwnd)
        return handle.invokeExact(hwndSeg, msg, wParam, lParam) as Long
    }

    /**
     * Calls PostQuitMessage(nExitCode) via the FFM binding (lazy, null on macOS/Linux).
     *
     * Triggers the exit of the GetMessage message loop when WM_DESTROY is received.
     *
     * @param nExitCode Exit code (0 = normal success).
     */
    private fun postQuitMessage(nExitCode: Int) {
        postQuitMessage?.invoke(nExitCode)
    }

    /**
     * Reads the current state of the modifier keys via GetKeyState (lazy, null on macOS/Linux).
     *
     * GetKeyState reads the key state at the moment the message is processed — consistent
     * with the Win32 message thread.
     *
     * @return [Modifiers] representing the active modifiers.
     */
    private fun currentModifiers(): Modifiers {
        if (getKeyState == null) return Modifiers.NONE
        var bits = 0
        // GetKeyState returns a Short: bit 15 = key down, bit 0 = toggle
        if ((getKeyState!!.invokeExact(VK_SHIFT)   as Short).toInt() and 0x8000 != 0) bits = bits or 0x1
        if ((getKeyState!!.invokeExact(VK_CONTROL) as Short).toInt() and 0x8000 != 0) bits = bits or 0x2
        if ((getKeyState!!.invokeExact(VK_MENU)    as Short).toInt() and 0x8000 != 0) bits = bits or 0x4
        if ((getKeyState!!.invokeExact(VK_LWIN)    as Short).toInt() and 0x8000 != 0 ||
            (getKeyState!!.invokeExact(VK_RWIN)    as Short).toInt() and 0x8000 != 0) bits = bits or 0x8
        return Modifiers(bits)
    }

    /**
     * Arms WM_MOUSELEAVE tracking for the window [hwnd] via TrackMouseEvent.
     *
     * Must be called on every WM_MOUSEMOVE to keep the tracking active,
     * because Windows automatically disarms TrackMouseEvent after sending
     * WM_MOUSELEAVE. Redundant calls (tracking already active) are gracefully
     * ignored by Windows.
     *
     * On macOS/Linux (trackMouseEvent null), this method is a no-op.
     *
     * @param hwndAddr Integer address of the HWND (MemorySegment.address()).
     */
    private fun armMouseLeaveTracking(hwndAddr: Long) {
        val handle = trackMouseEvent ?: return
        try {
            val hwndSeg = MemorySegment.ofAddress(hwndAddr)
            // Allocate TRACKMOUSEEVENT on the stack (auto-freed confined arena)
            // Layout: DWORD cbSize (4) + DWORD dwFlags (4) + HWND hwndTrack (8) + DWORD dwHoverTime (4) + pad(4) = 24 bytes
            val tme = MemorySegment.ofArray(LongArray(3)) // 24 bytes, 8-aligned
            tme.set(ValueLayout.JAVA_INT,  0L, TRACKMOUSEEVENT_SIZE)  // cbSize
            tme.set(ValueLayout.JAVA_INT,  4L, TME_LEAVE)             // dwFlags
            tme.set(ValueLayout.ADDRESS,   8L, hwndSeg)               // hwndTrack
            tme.set(ValueLayout.JAVA_INT, 16L, 0)                     // dwHoverTime (HOVER_DEFAULT)
            handle.invokeExact(tme) as Int
        } catch (_: Throwable) {
            // Graceful degradation: WM_MOUSELEAVE will not be received
        }
    }
}
