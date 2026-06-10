package org.graphiks.kadre.wayland

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

/**
 * Manages `xdg_activation_v1` for window activation tokens.
 *
 * When [activationManagerPtr] is 0 (protocol extension unavailable), all operations
 * silently no-op.
 *
 * @param activationManagerPtr Address of the bound `xdg_activation_v1*` proxy, or 0.
 * @param seatPtr              Address of the `wl_seat*` proxy for serial-based activation, or 0.
 * @param displayPtr           Address of the `wl_display*` for flushing and roundtrips.
 */
internal class WaylandActivationToken(
    private val activationManagerPtr: Long,
    private val seatPtr: Long = 0L,
    private val displayPtr: Long = 0L,
) {
    private var lastSerial: Int = 0

    internal fun updateSerial(serial: Int) {
        lastSerial = serial
    }

    /**
     * Activates the surface at [surfacePtr] using the given [token] string.
     * Calls xdg_activation_v1.activate(token, surface) directly.
     */
    fun activate(token: String, surfacePtr: Long) {
        if (activationManagerPtr == 0L || surfacePtr == 0L) return
        val marshal = wlProxyMarshalFlagsStringObject ?: return
        try {
            Arena.ofConfined().use { arena ->
                val tokenSeg = arena.allocateFrom(token)
                marshal.invokeExact(
                    MemorySegment.ofAddress(activationManagerPtr),
                    2, // opcode: activate
                    MemorySegment.NULL,
                    1, // version
                    0, // flags
                    tokenSeg,
                    MemorySegment.ofAddress(surfacePtr),
                )
                flushDisplay()
            }
        } catch (_: Throwable) {}
    }

    private fun flushDisplay() {
        if (displayPtr == 0L) return
        try {
            wlDisplayFlush?.invokeExact(MemorySegment.ofAddress(displayPtr)) as Int
        } catch (_: Throwable) {}
    }

    companion object {
        internal fun create(
            globals: WaylandGlobals,
            displayPtr: Long,
        ): WaylandActivationToken? {
            if (globals.activationManagerPtr == 0L) return null
            return WaylandActivationToken(
                activationManagerPtr = globals.activationManagerPtr,
                seatPtr = globals.seatPtr,
                displayPtr = displayPtr,
            )
        }
    }
}
