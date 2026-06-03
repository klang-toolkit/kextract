package org.graphiks.kadre.wayland

/**
 * Tracks keyboard focus per Wayland surface.
 *
 * winit Wayland treats keyboard focus as general window focus and keeps one focus entry per
 * seat. Kadre usually has one seat, but retaining the seat dimension keeps the transition
 * semantics correct for multi-seat compositors: emit Focused(true) on 0 -> 1 and Focused(false)
 * on 1 -> 0 only.
 */
internal object WaylandFocusState {
    private val focusedSeatsBySurface = mutableMapOf<Long, MutableSet<Long>>()

    fun addSeatFocus(surfacePtr: Long, seatPtr: Long): Boolean = synchronized(this) {
        if (surfacePtr == 0L) return@synchronized false
        val seats = focusedSeatsBySurface.getOrPut(surfacePtr) { mutableSetOf() }
        val wasUnfocused = seats.isEmpty()
        seats += seatPtr
        wasUnfocused
    }

    fun removeSeatFocus(surfacePtr: Long, seatPtr: Long): Boolean = synchronized(this) {
        val seats = focusedSeatsBySurface[surfacePtr] ?: return@synchronized false
        seats -= seatPtr
        val stillFocused = seats.isNotEmpty()
        if (!stillFocused) focusedSeatsBySurface.remove(surfacePtr)
        !stillFocused
    }

    fun hasFocus(surfacePtr: Long): Boolean = synchronized(this) {
        focusedSeatsBySurface[surfacePtr]?.isNotEmpty() == true
    }

    fun clear(surfacePtr: Long) {
        synchronized(this) {
            focusedSeatsBySurface.remove(surfacePtr)
        }
    }
}
