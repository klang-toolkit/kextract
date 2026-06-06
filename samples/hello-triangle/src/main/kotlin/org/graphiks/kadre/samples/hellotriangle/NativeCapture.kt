package org.graphiks.kadre.samples.hellotriangle

import org.graphiks.kadre.core.capture.CaptureConfig
import org.graphiks.kadre.core.capture.CaptureFrame
import org.graphiks.kadre.core.capture.CaptureSource
import org.graphiks.kadre.core.capture.PixelFormat
import org.graphiks.kadre.core.capture.ScreenCapturer
import kotlinx.coroutines.runBlocking
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * Native screen capture mode using the ScreenCapturer API.
 *
 * Captures one frame from the primary display and saves it as PNG.
 * Supports macOS (ScreenCaptureKit), Windows (DXGI/GDI), X11 (XComposite+XShm).
 */
fun nativeCapture(path: String) = runBlocking {
    val capturer = ScreenCapturer.resolve()
        ?: error("No screen capturer available on this platform")

    println("[native-capture] Enumerating displays...")
    val displays = capturer.enumerateDisplays()
    println("[native-capture] Found ${displays.size} display(s):")
    displays.forEach { println("  ${it.name}: ${it.resolution} @ ${it.position}") }

    val primary = displays.firstOrNull() ?: error("No displays found")
    println("[native-capture] Capturing from: ${primary.name}")

    val session = capturer.createSession(
        CaptureSource.Display(primary.id),
        CaptureConfig(frameRate = 1, captureCursor = false, pixelFormat = PixelFormat.BGRA8),
    )

    val frame = session.captureSingle()
    println("[native-capture] Captured frame: ${frame.size.width}x${frame.size.height}, format=${frame.format}")

    val outFile = File(path)
    outFile.parentFile?.mkdirs()
    writePng(frame, path)
    println("[native-capture] Saved to ${outFile.absolutePath} (${outFile.length()} octets)")

    session.close()
}

    private fun writePng(frame: CaptureFrame, path: String) {
        val file = File(path)
        val width = frame.size.width
        val height = frame.size.height
        val rawData = frame.data

        val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            for (x in 0 until width) {
                val i = y * frame.stride + x * 4
                when (frame.format) {
                    PixelFormat.RGBA8 -> {
                        val r = rawData[i].toInt() and 0xFF
                        val g = rawData[i + 1].toInt() and 0xFF
                        val b = rawData[i + 2].toInt() and 0xFF
                        val a = rawData[i + 3].toInt() and 0xFF
                        pixels[y * width + x] = (a shl 24) or (r shl 16) or (g shl 8) or b
                    }
                    PixelFormat.BGRA8, PixelFormat.BGRX8 -> {
                        val b = rawData[i].toInt() and 0xFF
                        val g = rawData[i + 1].toInt() and 0xFF
                        val r = rawData[i + 2].toInt() and 0xFF
                        val a = rawData[i + 3].toInt() and 0xFF
                        pixels[y * width + x] = (a shl 24) or (r shl 16) or (g shl 8) or b
                    }
                    else -> {
                        val b = rawData[i].toInt() and 0xFF
                        val g = rawData[i + 1].toInt() and 0xFF
                        val r = rawData[i + 2].toInt() and 0xFF
                        val a = rawData[i + 3].toInt() and 0xFF
                        pixels[y * width + x] = (a shl 24) or (r shl 16) or (g shl 8) or b
                    }
                }
            }
        }
        bufferedImage.setRGB(0, 0, width, height, pixels, 0, width)
        ImageIO.write(bufferedImage, "png", file)
    }
