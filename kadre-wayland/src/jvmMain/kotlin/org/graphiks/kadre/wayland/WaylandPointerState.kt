package org.graphiks.kadre.wayland

import java.lang.foreign.MemorySegment
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

private const val WL_POINTER_SET_CURSOR_OPCODE: Int = 0
private const val WL_POINTER_VERSION: Int = 1

/**
 * Minimal shared pointer state needed by xdg_toplevel interactive requests.
 *
 * Wayland requires the `wl_seat` and a recent button serial for
 * `xdg_toplevel.show_window_menu` / `move` / `resize`. The pointer listener
 * mirrors winit by remembering the latest button serial from pointer button events.
 */
internal object WaylandPointerState {
    private val seatPtr = AtomicLong(0L)
    private val pointerPtr = AtomicLong(0L)
    private val focusedSurfacePtr = AtomicLong(0L)
    private val latestEnterSerial = AtomicLong(0L)
    private val latestButtonSerial = AtomicLong(0L)
    private val cursorVisibleBySurface = ConcurrentHashMap<Long, Boolean>()

    fun updateSeat(ptr: Long) {
        seatPtr.set(ptr)
    }

    fun enterSurface(ptr: Long) {
        focusedSurfacePtr.set(ptr)
    }

    fun enterPointer(ptr: Long, surfacePtr: Long, serial: Int) {
        pointerPtr.set(ptr)
        latestEnterSerial.set(serial.toLong() and 0xFFFF_FFFFL)
        enterSurface(surfacePtr)
    }

    fun leaveSurface(ptr: Long) {
        focusedSurfacePtr.compareAndSet(ptr, 0L)
    }

    fun recordButton(serial: Int, button: Int, state: Int) {
        if (state == WL_POINTER_BUTTON_STATE_PRESSED || state == WL_POINTER_BUTTON_STATE_RELEASED) {
            latestButtonSerial.set(serial.toLong() and 0xFFFF_FFFFL)
        }
    }

    fun current(surfacePtr: Long): PointerRequestContext? {
        val seat = seatPtr.get()
        val serial = latestButtonSerial.get()
        val focusedSurface = focusedSurfacePtr.get()
        if (seat == 0L || serial == 0L || focusedSurface != surfacePtr) return null
        return PointerRequestContext(seat, serial.toInt())
    }

    fun currentCursor(surfacePtr: Long): CursorRequestContext? {
        val pointer = pointerPtr.get()
        val serial = latestEnterSerial.get()
        val focusedSurface = focusedSurfacePtr.get()
        if (pointer == 0L || serial == 0L || focusedSurface != surfacePtr) return null
        return CursorRequestContext(pointer, serial.toInt())
    }

    fun setCursorVisible(surfacePtr: Long, visible: Boolean) {
        if (surfacePtr == 0L) return
        cursorVisibleBySurface[surfacePtr] = visible
    }

    fun isCursorVisible(surfacePtr: Long): Boolean =
        cursorVisibleBySurface[surfacePtr] ?: true

    fun hideCursorForSurface(surfacePtr: Long): Boolean {
        val cursor = currentCursor(surfacePtr) ?: return false
        val setCursor = wlProxyMarshalFlagsUintObjectTwoInt ?: return false
        return try {
            setCursor.invokeExact(
                MemorySegment.ofAddress(cursor.pointerPtr),
                WL_POINTER_SET_CURSOR_OPCODE,
                MemorySegment.NULL,
                WL_POINTER_VERSION,
                0,
                cursor.enterSerial,
                MemorySegment.NULL,
                0,
                0,
            )
            true
        } catch (_: Throwable) {
            false
        }
    }

    data class PointerRequestContext(
        val seatPtr: Long,
        val serial: Int,
    )

    data class CursorRequestContext(
        val pointerPtr: Long,
        val enterSerial: Int,
    )
}
