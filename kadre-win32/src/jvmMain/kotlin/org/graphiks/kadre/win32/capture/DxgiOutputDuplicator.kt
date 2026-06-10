package org.graphiks.kadre.win32.capture

import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.CaptureFrame
import org.graphiks.kadre.core.capture.PixelFormat
import org.graphiks.kadre.ffi.win32.*
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

class DxgiOutputDuplicator(
    displayId: Long,
    private val rect: Win32MonitorRect,
) : AutoCloseable {

    fun acquireFrame(timeoutMs: Long): CaptureFrame? =
        captureDisplayRect(rect)

    override fun close() = Unit
}

internal class GdiWindowCapture(
    private val hwnd: Long,
) : AutoCloseable {

    fun acquireFrame(): CaptureFrame? {
        val getWR = getWindowRect ?: return null
        val arena = Arena.ofConfined()
        return try {
            val hwndSeg = MemorySegment.ofAddress(hwnd)
            val rect = arena.allocate(RECT_SIZE, RECT_ALIGN)
            val rectOk = getWR.invokeExact(hwndSeg, rect) as Int
            if (rectOk == 0) return null

            val left = rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_LEFT)
            val top = rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_TOP)
            val right = rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_RIGHT)
            val bottom = rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_BOTTOM)
            val width = right - left
            val height = bottom - top
            if (width <= 0 || height <= 0) return null

            captureRectFromScreen(left, top, width, height)
        } catch (_: Throwable) {
            null
        } finally {
            arena.close()
        }
    }

    override fun close() = Unit
}

data class Win32MonitorRect(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
)

internal fun captureDisplayRect(rect: Win32MonitorRect): CaptureFrame? {
    val width = rect.right - rect.left
    val height = rect.bottom - rect.top
    if (width <= 0 || height <= 0) return null
    return captureRectFromScreen(rect.left, rect.top, width, height)
}

internal fun captureRectFromScreen(
    left: Int,
    top: Int,
    width: Int,
    height: Int,
): CaptureFrame? {
    val gdiGetDC = getDC ?: return null
    val compatDC = createCompatibleDC ?: return null
    val compatBmp = createCompatibleBitmap ?: return null
    val selObj = selectObject ?: return null
    val blt = bitBlt ?: return null
    val dib = getDIBits ?: return null
    val relDC = releaseDC ?: return null
    val delDC = deleteDC ?: return null
    val delObj = deleteObject ?: return null

    var hdcScreen: MemorySegment? = null
    var hdcMem: MemorySegment? = null
    var hBitmap: MemorySegment? = null
    var oldBitmap: MemorySegment? = null

    return try {
        hdcScreen = gdiGetDC.invokeExact(MemorySegment.NULL) as MemorySegment
        if (hdcScreen.address() == 0L) return null

        hdcMem = compatDC.invokeExact(hdcScreen) as MemorySegment
        if (hdcMem.address() == 0L) return null

        hBitmap = compatBmp.invokeExact(hdcScreen, width, height) as MemorySegment
        if (hBitmap.address() == 0L) return null

        oldBitmap = selObj.invokeExact(hdcMem, hBitmap) as MemorySegment

        val bltResult = blt.invokeExact(
            hdcMem, 0, 0, width, height,
            hdcScreen, left, top, SRCCOPY
        ) as Int
        if (bltResult == 0) return null

        val stride = width * 4
        val bufferSize = stride * height

        Arena.ofConfined().use { a ->
            val buffer = a.allocate(bufferSize.toLong(), 4L)
            val bmi = a.allocate(44L, 4L)

            bmi.set(ValueLayout.JAVA_INT, BMIH_BI_SIZE_OFFSET, BMIH_SIZE.toInt())
            bmi.set(ValueLayout.JAVA_INT, BMIH_BI_WIDTH_OFFSET, width)
            bmi.set(ValueLayout.JAVA_INT, BMIH_BI_HEIGHT_OFFSET, -height)
            bmi.set(ValueLayout.JAVA_SHORT, BMIH_BI_PLANES_OFFSET, 1)
            bmi.set(ValueLayout.JAVA_SHORT, BMIH_BI_BIT_COUNT_OFFSET, 32)
            bmi.set(ValueLayout.JAVA_INT, BMIH_BI_COMPRESSION_OFFSET, BI_RGB)

            val lines = dib.invokeExact(
                hdcMem, hBitmap, 0, height, buffer, bmi, DIB_RGB_COLORS
            ) as Int
            if (lines == 0) return@use null

            val pixelData = ByteArray(bufferSize)
            buffer.asByteBuffer().get(pixelData)

            CaptureFrame(
                size = PhysicalSize(width, height),
                format = PixelFormat.BGRA8,
                stride = stride,
                data = pixelData,
                timestampNanos = System.nanoTime(),
            )
        }
    } catch (_: Throwable) {
        null
    } finally {
        try { if (hdcMem != null && hdcMem.address() != 0L && oldBitmap != null && oldBitmap.address() != 0L) selObj.invokeExact(hdcMem, oldBitmap) } catch (_: Throwable) {}
        try { if (hBitmap != null && hBitmap.address() != 0L) delObj.invokeExact(hBitmap) } catch (_: Throwable) {}
        try { if (hdcMem != null && hdcMem.address() != 0L) delDC.invokeExact(hdcMem) } catch (_: Throwable) {}
        try { if (hdcScreen != null && hdcScreen.address() != 0L) relDC.invokeExact(MemorySegment.NULL, hdcScreen) } catch (_: Throwable) {}
    }
}


