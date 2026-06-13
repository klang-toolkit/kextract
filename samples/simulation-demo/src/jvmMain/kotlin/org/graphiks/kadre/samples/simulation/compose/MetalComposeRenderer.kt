package org.graphiks.kadre.samples.simulation.compose

import org.graphiks.kffi.objc.ObjCRuntime
import org.graphiks.kadre.coroutines.EventLoopDispatcher
import org.jetbrains.skia.BackendRenderTarget
import org.jetbrains.skia.ColorSpace
import org.jetbrains.skia.DirectContext
import org.jetbrains.skia.Surface
import org.jetbrains.skia.SurfaceColorFormat
import org.jetbrains.skia.SurfaceOrigin
import org.jetbrains.skia.SurfaceProps
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout

private const val MTL_PIXEL_FORMAT_BGRA8_UNORM = 80L

class MetalComposeRenderer(
    metalLayerAddr: Long,
    scaleFactor: Double,
    dispatcher: EventLoopDispatcher,
) : ComposeWindowRenderer {

    override val host = ComposeSceneHost(scaleFactor, dispatcher)

    private val arena = Arena.ofShared()
    private val layer: MemorySegment = MemorySegment.ofAddress(metalLayerAddr)

    private val device: MemorySegment
    private val queue: MemorySegment
    private val context: DirectContext

    init {
        device = createSystemDefaultDevice()

        ObjCRuntime.msgSend(null, layer, ObjCRuntime.sel("setDevice:"), device)
        ObjCRuntime.msgSend(null, layer, ObjCRuntime.sel("setPixelFormat:"), MTL_PIXEL_FORMAT_BGRA8_UNORM)
        ObjCRuntime.msgSend(null, layer, ObjCRuntime.sel("setFramebufferOnly:"), false)

        queue = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS, device, ObjCRuntime.sel("newCommandQueue"),
        ) as MemorySegment

        context = DirectContext.makeMetal(device.address(), queue.address())
    }

    override fun resize(widthPx: Int, heightPx: Int, scaleFactor: Double) {
        if (widthPx <= 0 || heightPx <= 0) return
        setDrawableSize(widthPx, heightPx)
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
        ObjCRuntime.autoreleasePool {
            val drawable = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS, layer, ObjCRuntime.sel("nextDrawable"),
            ) as MemorySegment
            if (drawable == MemorySegment.NULL) return@autoreleasePool

            val texture = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS, drawable, ObjCRuntime.sel("texture"),
            ) as MemorySegment
            val texW = msgSendLong(texture, "width").toInt()
            val texH = msgSendLong(texture, "height").toInt()
            if (texW <= 0 || texH <= 0) return@autoreleasePool

            val renderTarget = BackendRenderTarget.makeMetal(texW, texH, texture.address())
            val surface = Surface.makeFromBackendRenderTarget(
                context,
                renderTarget,
                SurfaceOrigin.TOP_LEFT,
                SurfaceColorFormat.BGRA_8888,
                ColorSpace.sRGB,
                SurfaceProps(),
            ) ?: run {
                renderTarget.close()
                return@autoreleasePool
            }

            host.pumpAndRender(surface.canvas, texW, texH)

            surface.flushAndSubmit()
            onRendered?.invoke(surface)
            val commandBuffer = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS, queue, ObjCRuntime.sel("commandBuffer"),
            ) as MemorySegment
            ObjCRuntime.msgSend(null, commandBuffer, ObjCRuntime.sel("presentDrawable:"), drawable)
            ObjCRuntime.msgSend(null, commandBuffer, ObjCRuntime.sel("commit"))

            surface.close()
            renderTarget.close()
        }
    }

    override fun dispose() {
        host.close()
        runCatching { context.close() }
    }

    private fun msgSendLong(receiver: MemorySegment, selector: String): Long =
        ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, receiver, ObjCRuntime.sel(selector)) as Long

    private fun setDrawableSize(widthPx: Int, heightPx: Int) {
        val size = arena.allocate(CG_SIZE_LAYOUT)
        size.setAtIndex(ValueLayout.JAVA_DOUBLE, 0, widthPx.toDouble())
        size.setAtIndex(ValueLayout.JAVA_DOUBLE, 1, heightPx.toDouble())
        ObjCRuntime.msgSend(
            null, layer, ObjCRuntime.sel("setDrawableSize:"),
            ObjCRuntime.ObjCStructArg(size, CG_SIZE_LAYOUT),
        )
    }

    private fun createSystemDefaultDevice(): MemorySegment {
        val metal = SymbolLookup.libraryLookup(
            "/System/Library/Frameworks/Metal.framework/Metal", arena,
        )
        val symbol = metal.find("MTLCreateSystemDefaultDevice")
            .orElseThrow { UnsatisfiedLinkError("MTLCreateSystemDefaultDevice not found in Metal.framework") }
        val handle = Linker.nativeLinker().downcallHandle(
            symbol, FunctionDescriptor.of(ValueLayout.ADDRESS),
        )
        val ptr = handle.invokeExact() as MemorySegment
        if (ptr == MemorySegment.NULL) {
            throw IllegalStateException("MTLCreateSystemDefaultDevice returned null — no Metal GPU available")
        }
        return ptr
    }

    private companion object {
        val CG_SIZE_LAYOUT: java.lang.foreign.GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("width"),
            ValueLayout.JAVA_DOUBLE.withName("height"),
        ).withName("CGSize")
    }
}
