/**
 * R3 — AppKit cursor helpers (grab / warp).
 *
 * Isolated here to keep AppKitWindow readable.
 * All functions are no-ops on non-macOS or when the native calls fail.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.core.CursorGrabMode
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout

/**
 * AppKit / CoreGraphics cursor grab and warp helpers.
 */
internal object AppKitCursorHelper {

    private val cgAssociate: java.lang.invoke.MethodHandle? by lazy {
        try {
            val linker = Linker.nativeLinker()
            val lib = SymbolLookup.libraryLookup("CoreGraphics", java.lang.foreign.Arena.global())
            lib.find("CGAssociateMouseAndMouseCursorPosition").map { addr ->
                linker.downcallHandle(
                    addr,
                    FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT),
                )
            }.orElse(null)
        } catch (_: Throwable) { null }
    }

    private val cgWarp: java.lang.invoke.MethodHandle? by lazy {
        try {
            val linker = Linker.nativeLinker()
            val lib = SymbolLookup.libraryLookup("CoreGraphics", java.lang.foreign.Arena.global())
            lib.find("CGWarpMouseCursorPosition").map { addr ->
                linker.downcallHandle(
                    addr,
                    FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE),
                )
            }.orElse(null)
        } catch (_: Throwable) { null }
    }

    /**
     * Sets the cursor grab mode.
     *
     * - [CursorGrabMode.Confined] / [CursorGrabMode.Locked]:
     *   calls `CGAssociateMouseAndMouseCursorPosition(false)` — raw delta mode.
     * - [CursorGrabMode.None]: calls `CGAssociateMouseAndMouseCursorPosition(true)`.
     */
    fun setGrabMode(mode: CursorGrabMode) {
        try {
            val associate = cgAssociate ?: return
            val connected = if (mode == CursorGrabMode.None) 1 else 0
            associate.invokeExact(connected) as Int
        } catch (_: Throwable) {}
    }

    /**
     * Warps the cursor to screen coordinates (x, y).
     */
    fun warpCursor(x: Double, y: Double) {
        try {
            cgWarp?.invokeExact(x, y) as? Int
        } catch (_: Throwable) {}
    }
}
