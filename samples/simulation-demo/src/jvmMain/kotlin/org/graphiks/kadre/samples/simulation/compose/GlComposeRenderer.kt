package org.graphiks.kadre.samples.simulation.compose

import org.graphiks.kadre.coroutines.EventLoopDispatcher
import org.jetbrains.skia.BackendRenderTarget
import org.jetbrains.skia.ColorSpace
import org.jetbrains.skia.DirectContext
import org.jetbrains.skia.Surface
import org.jetbrains.skia.SurfaceColorFormat
import org.jetbrains.skia.SurfaceOrigin
import org.jetbrains.skia.SurfaceProps

private const val GL_RGBA8 = 0x8058

interface GlContext {
    fun makeCurrent()

    fun swapBuffers()

    fun resize(widthPx: Int, heightPx: Int)

    fun drawableSize(): Size

    fun dispose()

    data class Size(val width: Int, val height: Int)
}

class GlComposeRenderer(
    private val gl: GlContext,
    scaleFactor: Double,
    dispatcher: EventLoopDispatcher,
) : ComposeWindowRenderer {

    override val host = ComposeSceneHost(scaleFactor, dispatcher)

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
