package org.graphiks.kadre.samples.screencapture

import org.graphiks.kadre.core.capture.*
import kotlinx.coroutines.runBlocking
import java.io.File
import javax.imageio.ImageIO

/**
 * Screen Capture Demo - Main entry point.
 * 
 * This sample demonstrates the ScreenCapturer API by:
 * 1. Enumerating available displays and windows
 * 2. Creating a capture session for a selected source
 * 3. Capturing frames and saving them as PNG files
 * 
 * Usage:
 *   --list-displays      List available displays
 *   --list-windows       List available windows
 *   --capture-display <id>  Capture from display and save as PNG
 *   --capture-window <id>   Capture from window and save as PNG
 *   --output <path>       Output directory for captured frames (default: ./captures)
 *   --help               Show this help
 */

fun main(args: Array<String>) = runBlocking {
    val options = parseArgs(args)
    
    when {
        options.help -> printHelp()
        options.listDisplays -> listDisplays()
        options.listWindows -> listWindows()
        options.captureDisplay != null -> captureDisplay(options.captureDisplay, options.output)
        options.captureWindow != null -> captureWindow(options.captureWindow, options.output)
        else -> printHelp()
    }
}

private data class Options(
    val help: Boolean = false,
    val listDisplays: Boolean = false,
    val listWindows: Boolean = false,
    val captureDisplay: Long? = null,
    val captureWindow: Long? = null,
    val output: String = "./captures"
)

private fun parseArgs(args: Array<String>): Options {
    var options = Options()
    var i = 0
    while (i < args.size) {
        when (args[i]) {
            "--help" -> options = options.copy(help = true)
            "--list-displays" -> options = options.copy(listDisplays = true)
            "--list-windows" -> options = options.copy(listWindows = true)
            "--capture-display" -> {
                i++
                if (i < args.size) options = options.copy(captureDisplay = args[i].toLongOrNull())
            }
            "--capture-window" -> {
                i++
                if (i < args.size) options = options.copy(captureWindow = args[i].toLongOrNull())
            }
            "--output" -> {
                i++
                if (i < args.size) options = options.copy(output = args[i])
            }
            else -> {}
        }
        i++
    }
    return options
}

private fun printHelp() {
    println("""
        Screen Capture Demo
        
        Usage:
          --list-displays       List available displays
          --list-windows        List available windows
          --capture-display <id>  Capture from display and save as PNG
          --capture-window <id>   Capture from window and save as PNG
          --output <path>        Output directory for captured frames (default: ./captures)
          --help                Show this help
        
        Examples:
          ./gradlew :samples:screen-capture-demo:run --args="--list-displays"
          ./gradlew :samples:screen-capture-demo:run --args="--capture-display 0 --output ./captures"
          ./gradlew :samples:screen-capture-demo:run --args="--capture-window 12345678"
    """.trimIndent())
}

private suspend fun listDisplays() {
    val capturer = ScreenCapturer.resolve()
        ?: error("No screen capturer available on this platform")
    
    println("Available Displays:")
    println("==================")
    
    val displays = capturer.enumerateDisplays()
    if (displays.isEmpty()) {
        println("No displays found")
        return
    }
    
    displays.forEachIndexed { index, display ->
        println("$index. ID: ${display.id}, Name: ${display.name ?: "N/A"}")
        println("   Position: ${display.position}, Resolution: ${display.resolution}")
        println("   Scale Factor: ${display.scaleFactor}")
        println()
    }
    
    println("Total: ${displays.size} display(s)")
}

private suspend fun listWindows() {
    val capturer = ScreenCapturer.resolve()
        ?: error("No screen capturer available on this platform")
    
    println("Available Windows:")
    println("================")
    
    val windows = capturer.enumerateWindows()
    if (windows.isEmpty()) {
        println("No windows found")
        return
    }
    
    windows.forEachIndexed { index, window ->
        println("$index. ID: ${window.id}")
        println("   Title: ${window.title ?: "N/A"}")
        println("   Application: ${window.applicationName ?: "N/A"}")
        println("   Position: ${window.position}, Size: ${window.size}")
        println()
    }
    
    println("Total: ${windows.size} window(s)")
}

private suspend fun captureDisplay(displayId: Long, outputDir: String) {
    val capturer = ScreenCapturer.resolve()
        ?: error("No screen capturer available on this platform")
    
    println("Capturing from display $displayId...")
    
    val session = capturer.createSession(
        CaptureSource.Display(displayId),
        CaptureConfig(frameRate = 1, pixelFormat = PixelFormat.BGRA8)
    )
    
    try {
        val frame = session.captureSingle()
        println("Captured frame: ${frame.size.width}x${frame.size.height}, format=${frame.format}")
        
        val outputFile = File(outputDir, "display_${displayId}_${System.currentTimeMillis()}.png")
        outputFile.parentFile?.mkdirs()
        saveFrameAsPng(frame, outputFile)
        println("Saved to: ${outputFile.absolutePath}")
    } finally {
        session.close()
    }
}

private suspend fun captureWindow(windowId: Long, outputDir: String) {
    val capturer = ScreenCapturer.resolve()
        ?: error("No screen capturer available on this platform")
    
    println("Capturing from window $windowId...")
    
    val session = capturer.createSession(
        CaptureSource.Window(windowId),
        CaptureConfig(frameRate = 1, pixelFormat = PixelFormat.BGRA8)
    )
    
    try {
        val frame = session.captureSingle()
        println("Captured frame: ${frame.size.width}x${frame.size.height}, format=${frame.format}")
        
        val outputFile = File(outputDir, "window_${windowId}_${System.currentTimeMillis()}.png")
        outputFile.parentFile?.mkdirs()
        saveFrameAsPng(frame, outputFile)
        println("Saved to: ${outputFile.absolutePath}")
    } finally {
        session.close()
    }
}

private fun saveFrameAsPng(frame: CaptureFrame, file: File) {
    val width = frame.size.width
    val height = frame.size.height
    val rawData = frame.data
    
    // Create BufferedImage - use TYPE_INT_ARGB for RGBA
    val bufferedImage = java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB)
    val pixels = IntArray(width * height)
    
    for (y in 0 until height) {
        for (x in 0 until width) {
            val i = y * frame.stride + x * 4
            val r = rawData[i].toInt() and 0xFF
            val g = rawData[i + 1].toInt() and 0xFF
            val b = rawData[i + 2].toInt() and 0xFF
            val a = rawData[i + 3].toInt() and 0xFF
            
            // Convert BGRA to ARGB for BufferedImage
            pixels[y * width + x] = when (frame.format) {
                PixelFormat.RGBA8 -> (a shl 24) or (r shl 16) or (g shl 8) or b
                PixelFormat.BGRA8, PixelFormat.BGRX8 -> (a shl 24) or (b shl 16) or (g shl 8) or r
                else -> (a shl 24) or (r shl 16) or (g shl 8) or b
            }
        }
    }
    
    bufferedImage.setRGB(0, 0, width, height, pixels, 0, width)
    ImageIO.write(bufferedImage, "png", file)
}
