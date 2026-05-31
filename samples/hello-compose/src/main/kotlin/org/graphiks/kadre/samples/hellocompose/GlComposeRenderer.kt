/**
 * Windows/Linux present path: renders the shared [ComposeSceneHost] into a native window's
 * OpenGL framebuffer via Skiko's GL backend.
 *
 * Per frame: makeCurrent → DirectContext.makeGL → Skia BackendRenderTarget(GL fbo 0) → Surface
 * → host.pumpAndRender(canvas) → flush → swapBuffers.
 *
 * The platform-specific GL context (create/makeCurrent/swap/resize) is abstracted by
 * [GlContext]: [Win32WglContext], [X11GlxContext], [WaylandEglContext]. Skiko loads the GL
 * function pointers itself once a context is current, so this class issues no raw GL calls.
 */
package org.graphiks.kadre.samples.hellocompose

import org.jetbrains.skia.BackendRenderTarget
import org.jetbrains.skia.ColorSpace
import org.jetbrains.skia.DirectContext
import org.jetbrains.skia.Surface
import org.jetbrains.skia.SurfaceColorFormat
import org.jetbrains.skia.SurfaceOrigin
import org.jetbrains.skia.SurfaceProps

/** GL_RGBA8 — internal format of the window's default colour buffer. */
private const val GL_RGBA8 = 0x8058

/**
 * A native OpenGL context bound to a window, abstracting WGL / GLX / EGL.
 *
 * All methods are called on the single render (main) thread.
 */
interface GlContext {
    /** Make this GL context current on the calling thread (creates it lazily on first call). */
    fun makeCurrent()

    /** Present the back buffer to the window. */
    fun swapBuffers()

    /** Resize the drawable to [widthPx]×[heightPx] (no-op where the surface tracks the window). */
    fun resize(widthPx: Int, heightPx: Int)

    /** Current drawable size in physical pixels (ground truth from the window/surface). */
    fun drawableSize(): Size

    fun dispose()

    data class Size(val width: Int, val height: Int)
}

class GlComposeRenderer(
    private val gl: GlContext,
    scaleFactor: Double,
) : ComposeWindowRenderer {

    override val host = ComposeSceneHost(scaleFactor)

    private var context: DirectContext? = null
    private var width = 0
    private var height = 0
    private var scale = scaleFactor

    override fun resize(widthPx: Int, heightPx: Int, scaleFactor: Double) {
        if (widthPx <= 0 || heightPx <= 0) return
        width = widthPx
        height = heightPx
        scale = scaleFactor
        gl.resize(widthPx, heightPx)
        host.setDensityAndSize(widthPx, heightPx, scaleFactor)
    }

    override fun renderFrame() {
        renderInto(null)
    }

    override fun captureFrameToPng(path: String): Boolean {
        var ok = false
        renderInto { surface -> ok = writeSurfacePng(surface, path) }
        return ok
    }

    private fun renderInto(onRendered: ((Surface) -> Unit)?) {
        gl.makeCurrent()

        val size = gl.drawableSize()
        if (size.width <= 0 || size.height <= 0) return
        if (size.width != width || size.height != height) {
            width = size.width
            height = size.height
            host.setDensityAndSize(width, height, scale)
        }

        val ctx = context ?: DirectContext.makeGL().also { context = it }

        // The window's default framebuffer is fbo 0. GL framebuffers are bottom-left origin.
        val renderTarget = BackendRenderTarget.makeGL(
            width, height,
            /* sampleCnt = */ 0,
            /* stencilBits = */ 8,
            /* fbId = */ 0,
            /* fbFormat = */ GL_RGBA8,
        )
        val surface = Surface.makeFromBackendRenderTarget(
            ctx,
            renderTarget,
            SurfaceOrigin.BOTTOM_LEFT,
            SurfaceColorFormat.RGBA_8888,
            ColorSpace.sRGB,
            SurfaceProps(),
        ) ?: run {
            renderTarget.close()
            return
        }

        host.pumpAndRender(surface.canvas, width, height)

        ctx.flush()
        onRendered?.invoke(surface)
        surface.close()
        renderTarget.close()

        gl.swapBuffers()
    }

    override fun dispose() {
        host.close()
        runCatching { context?.close() }
        runCatching { gl.dispose() }
    }
}
