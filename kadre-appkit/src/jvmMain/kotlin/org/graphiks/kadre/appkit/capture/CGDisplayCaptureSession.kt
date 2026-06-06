package org.graphiks.kadre.appkit.capture

import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.*
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodHandle
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * CaptureSession that uses CGDisplayCreateImage (CoreGraphics) instead of
 * ScreenCaptureKit. No TCC permission required — works on macOS 10.6+.
 */
class CGDisplayCaptureSession(
    source: CaptureSource,
    config: CaptureConfig,
    private val displayId: Long,
) : CaptureSession(source, config) {

    private val arena = Arena.global()
    private val linker = Linker.nativeLinker()
    private val cgLib: SymbolLookup = SymbolLookup.libraryLookup(
        "/System/Library/Frameworks/CoreGraphics.framework/CoreGraphics", arena,
    )
    private val cfLib: SymbolLookup = SymbolLookup.libraryLookup(
        "/System/Library/Frameworks/CoreFoundation.framework/CoreFoundation", arena,
    )

    private val cgDisplayCreateImage: MethodHandle = linker.downcallHandle(
        cgLib.find("CGDisplayCreateImage").orElseThrow(),
        FunctionDescriptor.of(ADDRESS, JAVA_INT),
    )
    private val cgImageGetWidth: MethodHandle = linker.downcallHandle(
        cgLib.find("CGImageGetWidth").orElseThrow(),
        FunctionDescriptor.of(JAVA_LONG, ADDRESS),
    )
    private val cgImageGetHeight: MethodHandle = linker.downcallHandle(
        cgLib.find("CGImageGetHeight").orElseThrow(),
        FunctionDescriptor.of(JAVA_LONG, ADDRESS),
    )
    private val cgImageGetBytesPerRow: MethodHandle = linker.downcallHandle(
        cgLib.find("CGImageGetBytesPerRow").orElseThrow(),
        FunctionDescriptor.of(JAVA_LONG, ADDRESS),
    )
    private val cgImageGetDataProvider: MethodHandle = linker.downcallHandle(
        cgLib.find("CGImageGetDataProvider").orElseThrow(),
        FunctionDescriptor.of(ADDRESS, ADDRESS),
    )
    private val cgDataProviderCopyData: MethodHandle = linker.downcallHandle(
        cgLib.find("CGDataProviderCopyData").orElseThrow(),
        FunctionDescriptor.of(ADDRESS, ADDRESS),
    )
    private val cfDataGetBytePtr: MethodHandle = linker.downcallHandle(
        cfLib.find("CFDataGetBytePtr").orElseThrow(),
        FunctionDescriptor.of(ADDRESS, ADDRESS),
    )
    private val cfDataGetLength: MethodHandle = linker.downcallHandle(
        cfLib.find("CFDataGetLength").orElseThrow(),
        FunctionDescriptor.of(JAVA_LONG, ADDRESS),
    )
    private val cfRelease: MethodHandle = linker.downcallHandle(
        cfLib.find("CFRelease").orElseThrow(),
        FunctionDescriptor.ofVoid(ADDRESS),
    )

    private val scope = kotlinx.coroutines.CoroutineScope(
        kotlinx.coroutines.SupervisorJob() + kotlinx.coroutines.Dispatchers.IO
    )

    init {
        scope.launch {
            captureLoop()
        }
    }

    private suspend fun captureLoop() {
        while (scope.isActive) {
            try {
                captureOneFrame()
            } catch (_: Exception) {
            }
            kotlinx.coroutines.delay(1000L / config.frameRate)
        }
    }

    private fun captureOneFrame() {
        val cgImage = cgDisplayCreateImage.invokeExact(displayId.toInt()) as MemorySegment
        if (cgImage == MemorySegment.NULL) return

        try {
            val width = (cgImageGetWidth.invokeExact(cgImage) as Long).toInt()
            val height = (cgImageGetHeight.invokeExact(cgImage) as Long).toInt()
            val bytesPerRow = (cgImageGetBytesPerRow.invokeExact(cgImage) as Long).toInt()
            if (width <= 0 || height <= 0) return

            val provider = cgImageGetDataProvider.invokeExact(cgImage) as MemorySegment
            if (provider == MemorySegment.NULL) return

            val cfData = cgDataProviderCopyData.invokeExact(provider) as MemorySegment
            if (cfData == MemorySegment.NULL) return

            try {
                val dataLen = (cfDataGetLength.invokeExact(cfData) as Long).toInt()
                val bytePtr = cfDataGetBytePtr.invokeExact(cfData) as MemorySegment
                if (bytePtr == MemorySegment.NULL) return

                val pixelData = ByteArray(dataLen)
                MemorySegment.copy(bytePtr.reinterpret(dataLen.toLong()), ValueLayout.JAVA_BYTE, 0, pixelData, 0, dataLen)

                _frames.tryEmit(CaptureFrame(
                    size = PhysicalSize(width, height),
                    format = PixelFormat.BGRA8,
                    stride = bytesPerRow,
                    data = pixelData,
                    timestampNanos = System.nanoTime(),
                ))
            } finally {
                cfRelease.invokeExact(cfData)
            }
        } finally {
            cfRelease.invokeExact(cgImage)
        }
    }

    override fun close() {
        scope.cancel()
    }
}

internal fun bgraToRgba(data: ByteArray): ByteArray {
    val result = data.copyOf()
    var i = 0
    while (i + 4 <= result.size) {
        val b = result[i]
        val r = result[i + 2]
        result[i] = r
        result[i + 2] = b
        i += 4
    }
    return result
}
