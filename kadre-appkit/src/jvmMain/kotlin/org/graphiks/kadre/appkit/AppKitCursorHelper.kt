/**
 * R3 — AppKit cursor helpers (grab / warp).
 *
 * Isolated here to keep AppKitWindow readable.
 * All functions report unsupported symbols or CoreGraphics errors explicitly.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.RequestError
import org.graphiks.kadre.core.WindowRequestResult
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
     * - [CursorGrabMode.Locked]:
     *   calls `CGAssociateMouseAndMouseCursorPosition(false)` — raw delta mode.
     * - [CursorGrabMode.None]: calls `CGAssociateMouseAndMouseCursorPosition(true)`.
     * - [CursorGrabMode.Confined]: unsupported, matching winit.
     */
    fun setGrabMode(mode: CursorGrabMode): WindowRequestResult =
        try {
            val connected = cursorAssociationValue(mode) ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("AppKit confined cursor grab is unsupported"),
            )
            val associate = cgAssociate ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("CoreGraphics cursor association is unavailable"),
            )
            val result = associate.invokeExact(connected) as Int
            if (result == 0) {
                WindowRequestResult.Success
            } else {
                WindowRequestResult.Failure(RequestError.OsError("CGAssociateMouseAndMouseCursorPosition failed: $result"))
            }
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "CoreGraphics cursor grab failed"))
        }

    internal fun cursorAssociationValue(mode: CursorGrabMode): Int? =
        when (mode) {
            CursorGrabMode.None -> 1
            CursorGrabMode.Locked -> 0
            CursorGrabMode.Confined -> null
        }

    /**
     * Warps the cursor to screen coordinates (x, y).
     */
    fun warpCursor(x: Double, y: Double): WindowRequestResult =
        try {
            val warp = cgWarp ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("CoreGraphics cursor warp is unavailable"),
            )
            val result = warp.invokeExact(x, y) as Int
            if (result == 0) {
                WindowRequestResult.Success
            } else {
                WindowRequestResult.Failure(RequestError.OsError("CGWarpMouseCursorPosition failed: $result"))
            }
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "CoreGraphics cursor warp failed"))
        }
}
