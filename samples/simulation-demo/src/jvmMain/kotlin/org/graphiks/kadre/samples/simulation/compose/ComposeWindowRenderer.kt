package org.graphiks.kadre.samples.simulation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.input.pointer.PointerButton
import org.graphiks.kadre.coroutines.EventLoopDispatcher
import org.graphiks.kadre.core.RawWindowHandle
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image
import org.jetbrains.skia.ImageInfo
import org.jetbrains.skia.Surface
import java.io.File

interface ComposeWindowRenderer {
    val host: ComposeSceneHost

    fun resize(widthPx: Int, heightPx: Int, scaleFactor: Double)

    fun renderFrame()

    fun captureFrameToPng(path: String): Boolean

    fun dispose()

    fun setContent(content: @Composable () -> Unit) = host.setContent(content)
    fun onPointerMoved(x: Double, y: Double) = host.onPointerMoved(x, y)
    fun onPointerButton(bit: Int, pressed: Boolean, button: PointerButton) =
        host.onPointerButton(bit, pressed, button)
    fun onScroll(dx: Double, dy: Double) = host.onScroll(dx, dy)
    fun onPointerEnter() = host.onPointerEnter()
    fun onPointerExit() = host.onPointerExit()
    fun sendKey(awtEvent: java.awt.event.KeyEvent) = host.sendKey(awtEvent)

    companion object {
        fun create(
            handle: RawWindowHandle,
            scaleFactor: Double,
            dispatcher: EventLoopDispatcher,
        ): Result<ComposeWindowRenderer> =
            runCatching {
                when (handle) {
                    is RawWindowHandle.AppKit -> {
                        require(handle.nsLayer != 0L) { "AppKit handle without a CAMetalLayer (nsLayer=0)" }
                        MetalComposeRenderer(handle.nsLayer, scaleFactor, dispatcher)
                    }
                    is RawWindowHandle.Win32 ->
                        GlComposeRenderer(Win32WglContext(handle.hwnd), scaleFactor, dispatcher)
                    is RawWindowHandle.Xlib ->
                        GlComposeRenderer(X11GlxContext(handle.display, handle.window), scaleFactor, dispatcher)
                    is RawWindowHandle.Wayland ->
                        GlComposeRenderer(WaylandEglContext(handle.display, handle.surface), scaleFactor, dispatcher)
                    else -> throw UnsupportedOperationException("Unsupported window handle: $handle")
                }
            }
    }
}

internal fun writeSurfacePng(surface: Surface, path: String): Boolean {
    val bitmap = Bitmap()
    try {
        if (!bitmap.allocPixels(ImageInfo.makeN32Premul(surface.width, surface.height))) return false
        if (!surface.readPixels(bitmap, 0, 0)) return false
        val data = Image.makeFromBitmap(bitmap).encodeToData(EncodedImageFormat.PNG) ?: return false
        File(path).apply { parentFile?.mkdirs() }.writeBytes(data.bytes)
        return true
    } finally {
        bitmap.close()
    }
}
