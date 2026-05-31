/**
 * Renders an interactive Compose UI into a native Kadre window, abstracting over the
 * platform present path:
 * - macOS  → [MetalComposeRenderer] (Skiko Metal into the CAMetalLayer)
 * - Windows/Linux → [GlComposeRenderer] (Skiko OpenGL into the window's GL framebuffer)
 *
 * Input and scene handling are shared via [ComposeSceneHost]; only [resize]/[renderFrame]/
 * [dispose] are platform-specific.
 */
package org.graphiks.kadre.samples.hellocompose

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

    /** Resizes the present surface and updates the scene size/density. Sizes are physical px. */
    fun resize(widthPx: Int, heightPx: Int, scaleFactor: Double)

    /** Renders and presents one frame. */
    fun renderFrame()

    /**
     * Renders one frame and writes the rendered surface to [path] as a PNG, exercising the
     * real platform present surface (Metal texture / GL framebuffer). Used for headless CI
     * capture. Returns true on success.
     */
    fun captureFrameToPng(path: String): Boolean

    /** Releases GPU/native resources and the scene. */
    fun dispose()

    // Input + content delegate to the shared host.
    fun setContent(content: @Composable () -> Unit) = host.setContent(content)
    fun onPointerMoved(x: Double, y: Double) = host.onPointerMoved(x, y)
    fun onPointerButton(bit: Int, pressed: Boolean, button: PointerButton) =
        host.onPointerButton(bit, pressed, button)
    fun onScroll(dx: Double, dy: Double) = host.onScroll(dx, dy)
    fun onPointerEnter() = host.onPointerEnter()
    fun onPointerExit() = host.onPointerExit()
    fun sendKey(awtEvent: java.awt.event.KeyEvent) = host.sendKey(awtEvent)

    companion object {
        /**
         * Builds the renderer matching the window's native handle, or returns null with a
         * reason if the platform/handle is unsupported.
         */
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

/**
 * Snapshots a rendered Skia [surface] to a PNG file. Returns true on success.
 *
 * GPU (Metal/GL) surfaces can't be encoded directly ("texture backed images not supported"),
 * so we read the pixels back into a raster bitmap first.
 */
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
