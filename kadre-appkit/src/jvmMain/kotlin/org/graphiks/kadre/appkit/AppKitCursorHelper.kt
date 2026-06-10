/**
 * R3 — AppKit cursor helpers (grab / warp).
 *
 * Isolated here to keep AppKitWindow readable.
 * All functions report unsupported symbols or CoreGraphics errors explicitly.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.ffi.objc.ObjCRuntime
import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.CursorImage
import org.graphiks.kadre.core.RequestError
import org.graphiks.kadre.core.WindowRequestResult
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.GroupLayout
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

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

    // ── R5-CustomCursor ─────────────────────────────────────────────────────────

    private val cgColorSpaceCreateDeviceRGB: MethodHandle? by lazy {
        try {
            val lib = SymbolLookup.libraryLookup("CoreGraphics", Arena.global())
            lib.find("CGColorSpaceCreateDeviceRGB").map { addr ->
                Linker.nativeLinker().downcallHandle(addr, FunctionDescriptor.of(ValueLayout.ADDRESS))
            }.orElse(null)
        } catch (_: Throwable) { null }
    }

    private val cgBitmapContextCreate: MethodHandle? by lazy {
        try {
            val lib = SymbolLookup.libraryLookup("CoreGraphics", Arena.global())
            lib.find("CGBitmapContextCreate").map { addr ->
                Linker.nativeLinker().downcallHandle(
                    addr,
                    FunctionDescriptor.of(
                        ValueLayout.ADDRESS,
                        ValueLayout.ADDRESS,   // void* data
                        ValueLayout.JAVA_LONG,  // size_t width
                        ValueLayout.JAVA_LONG,  // size_t height
                        ValueLayout.JAVA_LONG,  // size_t bitsPerComponent
                        ValueLayout.JAVA_LONG,  // size_t bytesPerRow
                        ValueLayout.ADDRESS,    // CGColorSpaceRef
                        ValueLayout.JAVA_INT,   // uint32_t bitmapInfo
                    ),
                )
            }.orElse(null)
        } catch (_: Throwable) { null }
    }

    private val cgBitmapContextCreateImage: MethodHandle? by lazy {
        try {
            val lib = SymbolLookup.libraryLookup("CoreGraphics", Arena.global())
            lib.find("CGBitmapContextCreateImage").map { addr ->
                Linker.nativeLinker().downcallHandle(
                    addr,
                    FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
                )
            }.orElse(null)
        } catch (_: Throwable) { null }
    }

    private val cgBitmapContextGetData: MethodHandle? by lazy {
        try {
            val lib = SymbolLookup.libraryLookup("CoreGraphics", Arena.global())
            lib.find("CGBitmapContextGetData").map { addr ->
                Linker.nativeLinker().downcallHandle(
                    addr,
                    FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
                )
            }.orElse(null)
        } catch (_: Throwable) { null }
    }

    private val cgContextRelease: MethodHandle? by lazy {
        try {
            val lib = SymbolLookup.libraryLookup("CoreGraphics", Arena.global())
            lib.find("CGContextRelease").map { addr ->
                Linker.nativeLinker().downcallHandle(addr, FunctionDescriptor.ofVoid(ValueLayout.ADDRESS))
            }.orElse(null)
        } catch (_: Throwable) { null }
    }

    private val cgColorSpaceRelease: MethodHandle? by lazy {
        try {
            val lib = SymbolLookup.libraryLookup("CoreGraphics", Arena.global())
            lib.find("CGColorSpaceRelease").map { addr ->
                Linker.nativeLinker().downcallHandle(addr, FunctionDescriptor.ofVoid(ValueLayout.ADDRESS))
            }.orElse(null)
        } catch (_: Throwable) { null }
    }

    private val cgImageRelease: MethodHandle? by lazy {
        try {
            val lib = SymbolLookup.libraryLookup("CoreGraphics", Arena.global())
            lib.find("CGImageRelease").map { addr ->
                Linker.nativeLinker().downcallHandle(addr, FunctionDescriptor.ofVoid(ValueLayout.ADDRESS))
            }.orElse(null)
        } catch (_: Throwable) { null }
    }

    private val NS_SIZE_LAYOUT: GroupLayout = MemoryLayout.structLayout(
        ValueLayout.JAVA_DOUBLE.withName("width"),
        ValueLayout.JAVA_DOUBLE.withName("height"),
    ).withName("NSSize")

    private val NS_POINT_LAYOUT: GroupLayout = MemoryLayout.structLayout(
        ValueLayout.JAVA_DOUBLE.withName("x"),
        ValueLayout.JAVA_DOUBLE.withName("y"),
    ).withName("NSPoint")

    /**
     * Creates an NSCursor from RGBA pixel data via CoreGraphics + AppKit.
     *
     * Converts RGBA → BGRA byte order and creates a CGImage via CGBitmapContext,
     * then wraps it in NSImage → NSCursor with the given hotspot.
     * Returns null on failure (missing symbols, invalid image, or OOM).
     */
    fun createNSCursorFromImage(image: CursorImage): MemorySegment? {
        val csCreate = cgColorSpaceCreateDeviceRGB ?: return null
        val bmCreate = cgBitmapContextCreate ?: return null
        val bmCreateImage = cgBitmapContextCreateImage ?: return null
        val bmGetData = cgBitmapContextGetData ?: return null

        if (image.width <= 0 || image.height <= 0) return null
        val pixelCount = image.width.toLong() * image.height.toLong()
        val byteCount = pixelCount * 4L
        if (byteCount > Int.MAX_VALUE || image.rgba.size.toLong() != byteCount) return null

        val colorSpace = try {
            csCreate.invokeExact() as MemorySegment
        } catch (_: Throwable) { return null }
        if (colorSpace == MemorySegment.NULL) return null

        val context: MemorySegment
        try {
            context = bmCreate.invokeExact(
                MemorySegment.NULL,
                image.width.toLong(),
                image.height.toLong(),
                8L,
                image.width.toLong() * 4L,
                colorSpace,
                0x2003,
            ) as MemorySegment
        } catch (_: Throwable) { cgColorSpaceRelease?.invokeExact(colorSpace); return null }

        if (context == MemorySegment.NULL) { cgColorSpaceRelease?.invokeExact(colorSpace); return null }

        val cgImage: MemorySegment
        try {
            val data = bmGetData.invokeExact(context) as MemorySegment
            if (data != MemorySegment.NULL) {
                for (i in 0 until image.rgba.size step 4) {
                    data.setAtIndex(ValueLayout.JAVA_BYTE, i.toLong(), image.rgba[i + 2])
                    data.setAtIndex(ValueLayout.JAVA_BYTE, i.toLong() + 1, image.rgba[i + 1])
                    data.setAtIndex(ValueLayout.JAVA_BYTE, i.toLong() + 2, image.rgba[i])
                    data.setAtIndex(ValueLayout.JAVA_BYTE, i.toLong() + 3, image.rgba[i + 3])
                }
            }
            cgImage = bmCreateImage.invokeExact(context) as MemorySegment
        } catch (_: Throwable) { cgContextRelease?.invokeExact(context); cgColorSpaceRelease?.invokeExact(colorSpace); return null }

        if (cgImage == MemorySegment.NULL) { cgContextRelease?.invokeExact(context); cgColorSpaceRelease?.invokeExact(colorSpace); return null }

        val result = try {
            Arena.ofConfined().use { arena ->
                val sizeSeg = arena.allocate(NS_SIZE_LAYOUT)
                sizeSeg.setAtIndex(ValueLayout.JAVA_DOUBLE, 0, image.width.toDouble())
                sizeSeg.setAtIndex(ValueLayout.JAVA_DOUBLE, 1, image.height.toDouble())

                val hotSpotSeg = arena.allocate(NS_POINT_LAYOUT)
                hotSpotSeg.setAtIndex(ValueLayout.JAVA_DOUBLE, 0, image.hotspotX.toDouble())
                hotSpotSeg.setAtIndex(ValueLayout.JAVA_DOUBLE, 1, image.hotspotY.toDouble())

                ObjCRuntime.autoreleasePool {
                    val nsImageClass = ObjCRuntime.getClass("NSImage")
                    val nsImage = ObjCRuntime.msgSend(
                        ValueLayout.ADDRESS,
                        nsImageClass,
                        ObjCRuntime.sel("alloc"),
                    ) as MemorySegment

                    val nsImageResult = ObjCRuntime.msgSend(
                        ValueLayout.ADDRESS,
                        nsImage,
                        ObjCRuntime.sel("initWithCGImage:size:"),
                        cgImage,
                        ObjCRuntime.ObjCStructArg(sizeSeg, NS_SIZE_LAYOUT),
                    ) as MemorySegment

                    if (nsImageResult == MemorySegment.NULL) return@autoreleasePool null

                    val nsCursorClass = ObjCRuntime.getClass("NSCursor")
                    val nsCursor = ObjCRuntime.msgSend(
                        ValueLayout.ADDRESS,
                        nsCursorClass,
                        ObjCRuntime.sel("alloc"),
                    ) as MemorySegment

                    ObjCRuntime.msgSend(
                        ValueLayout.ADDRESS,
                        nsCursor,
                        ObjCRuntime.sel("initWithImage:hotSpot:"),
                        nsImageResult,
                        ObjCRuntime.ObjCStructArg(hotSpotSeg, NS_POINT_LAYOUT),
                    ) as MemorySegment?
                }
            }
        } finally {
            try { cgImageRelease?.invokeExact(cgImage) } catch (_: Throwable) {}
            try { cgContextRelease?.invokeExact(context) } catch (_: Throwable) {}
            try { cgColorSpaceRelease?.invokeExact(colorSpace) } catch (_: Throwable) {}
        }

        return result
    }
}
