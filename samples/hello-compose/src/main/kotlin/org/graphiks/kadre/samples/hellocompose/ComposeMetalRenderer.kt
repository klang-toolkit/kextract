/**
 * Bridge that renders an interactive Compose [ComposeScene] into a Kadre CAMetalLayer
 * via Skiko's low-level Metal backend.
 *
 * Pipeline per frame:
 *   nextDrawable (CAMetalLayer) → drawable.texture → Skia BackendRenderTarget(Metal)
 *   → Skia Surface → ComposeScene.render(surface.canvas) → flush → [commandBuffer presentDrawable]
 *
 * All Metal/ObjC calls go through Kadre's public [ObjCRuntime] FFM bridge; the only
 * raw FFM downcall we add is the C function `MTLCreateSystemDefaultDevice()`.
 *
 * Threading: everything runs on the macOS main thread (Kadre owns it via
 * `-XstartOnFirstThread`). The ComposeScene's recomposer runs on [Dispatchers.Unconfined]
 * so its continuations resume synchronously on this same thread when we pump the frame
 * clock and apply snapshot notifications each frame.
 */
package org.graphiks.kadre.samples.hellocompose

import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asComposeCanvas
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.PointerButtons
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.scene.CanvasLayersComposeScene
import androidx.compose.ui.scene.ComposeScene
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import org.graphiks.kadre.appkit.bindings.ObjCRuntime
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
import kotlinx.coroutines.Dispatchers

/** MTLPixelFormatBGRA8Unorm — the CAMetalLayer default, mapped to Skia BGRA_8888. */
private const val MTL_PIXEL_FORMAT_BGRA8_UNORM = 80L

class ComposeMetalRenderer(metalLayerAddr: Long, scaleFactor: Double) {

    private val arena = Arena.ofShared()
    private val layer: MemorySegment = MemorySegment.ofAddress(metalLayerAddr)

    private val device: MemorySegment
    private val queue: MemorySegment
    private val context: DirectContext

    private val frameClock = BroadcastFrameClock()
    private val scene: ComposeScene

    /** Current pressed-button bitmask in Compose [PointerButtons] encoding. */
    private var pressedButtons = 0

    /** Last known pointer position (physical px) — synthesized for press/release/scroll. */
    private var lastPointer = Offset.Zero

    init {
        // 1. MTLDevice — MTLCreateSystemDefaultDevice() is a C function, not an ObjC message.
        device = createSystemDefaultDevice()

        // 2. Configure the CAMetalLayer (Kadre creates it device-less).
        ObjCRuntime.msgSend(null, layer, ObjCRuntime.sel("setDevice:"), device)
        ObjCRuntime.msgSend(null, layer, ObjCRuntime.sel("setPixelFormat:"), MTL_PIXEL_FORMAT_BGRA8_UNORM)
        // Skia reads/writes the drawable texture → it must not be framebuffer-only.
        ObjCRuntime.msgSend(null, layer, ObjCRuntime.sel("setFramebufferOnly:"), false)

        // 3. Metal command queue (owned for the app lifetime).
        queue = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS, device, ObjCRuntime.sel("newCommandQueue"),
        ) as MemorySegment

        // 4. Skia DirectContext bound to our device + queue.
        context = DirectContext.makeMetal(device.address(), queue.address())

        // 5. ComposeScene. Unconfined dispatcher + our frame clock ⇒ recomposition is
        //    driven synchronously on the main thread when we pump it each frame.
        scene = CanvasLayersComposeScene(
            density = Density(scaleFactor.toFloat()),
            layoutDirection = LayoutDirection.Ltr,
            size = IntSize(1, 1), // real size set by the first resize() before rendering
            coroutineContext = Dispatchers.Unconfined + frameClock,
            invalidate = { /* continuous redraw is driven by the host loop */ },
        )
    }

    fun setContent(content: @Composable () -> Unit) {
        scene.setContent(content)
    }

    /** Updates the drawable size, Compose scene size and density. Sizes are physical pixels. */
    fun resize(widthPx: Int, heightPx: Int, scaleFactor: Double) {
        if (widthPx <= 0 || heightPx <= 0) return
        setDrawableSize(widthPx, heightPx)
        scene.density = Density(scaleFactor.toFloat())
        scene.size = IntSize(widthPx, heightPx)
    }

    /**
     * Renders one frame: pumps Compose state/animation, acquires the next Metal drawable,
     * draws the scene into it via Skia, and presents.
     */
    fun renderFrame() {
        // Apply any pending state writes (e.g. a button click mutating remembered state)
        // and advance the frame clock so recomposition + animations run before we draw.
        Snapshot.sendApplyNotifications()
        frameClock.sendFrame(System.nanoTime())

        ObjCRuntime.autoreleasePool {
            val drawable = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS, layer, ObjCRuntime.sel("nextDrawable"),
            ) as MemorySegment
            if (drawable == MemorySegment.NULL) return@autoreleasePool // no drawable this frame

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

            // Keep the scene sized to the actual drawable (covers any resize race).
            if (scene.size != IntSize(texW, texH)) {
                scene.size = IntSize(texW, texH)
            }

            scene.render(surface.canvas.asComposeCanvas(), System.nanoTime())

            // Flush Skia's Metal work on our queue, then present on the same queue (ordered).
            surface.flushAndSubmit()
            val commandBuffer = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS, queue, ObjCRuntime.sel("commandBuffer"),
            ) as MemorySegment
            ObjCRuntime.msgSend(null, commandBuffer, ObjCRuntime.sel("presentDrawable:"), drawable)
            ObjCRuntime.msgSend(null, commandBuffer, ObjCRuntime.sel("commit"))

            surface.close()
            renderTarget.close()
        }
    }

    // ── Input forwarding ──────────────────────────────────────────────────────

    fun onPointerMoved(xPhysical: Double, yPhysical: Double) {
        lastPointer = Offset(xPhysical.toFloat(), yPhysical.toFloat())
        scene.sendPointerEvent(
            eventType = PointerEventType.Move,
            position = lastPointer,
            buttons = PointerButtons(pressedButtons),
        )
    }

    fun onPointerButton(bit: Int, pressed: Boolean, button: PointerButton) {
        pressedButtons = if (pressed) pressedButtons or bit else pressedButtons and bit.inv()
        scene.sendPointerEvent(
            eventType = if (pressed) PointerEventType.Press else PointerEventType.Release,
            position = lastPointer,
            buttons = PointerButtons(pressedButtons),
            button = button,
        )
    }

    fun onScroll(deltaX: Double, deltaY: Double) {
        scene.sendPointerEvent(
            eventType = PointerEventType.Scroll,
            position = lastPointer,
            scrollDelta = Offset(deltaX.toFloat(), deltaY.toFloat()),
            buttons = PointerButtons(pressedButtons),
        )
    }

    /**
     * Forwards a keyboard event to the Compose scene.
     *
     * Compose text fields only insert characters when the event is a real AWT `KEY_TYPED`
     * event (see `TextFieldKeyInput.isTypedEvent`), so callers must pass genuine
     * [java.awt.event.KeyEvent]s. The desktop `KeyEvent.toComposeEvent()` converter — which
     * preserves the AWT event so `getAwtEventOrNull` returns it downstream — is `internal`
     * to compose-ui, so we reach it reflectively (it is public at the bytecode level).
     */
    fun sendKey(awtEvent: java.awt.event.KeyEvent) {
        scene.sendKeyEvent(awtKeyToComposeKeyEvent(awtEvent))
    }

    fun onPointerEnter() =
        scene.sendPointerEvent(eventType = PointerEventType.Enter, position = lastPointer)

    fun onPointerExit() =
        scene.sendPointerEvent(eventType = PointerEventType.Exit, position = lastPointer)

    fun dispose() {
        runCatching { scene.close() }
        runCatching { context.close() }
    }

    // ── Native helpers ────────────────────────────────────────────────────────

    private fun msgSendLong(receiver: MemorySegment, selector: String): Long =
        ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, receiver, ObjCRuntime.sel(selector)) as Long

    /** `[layer setDrawableSize:(CGSize){w,h}]` — CGSize is two CGFloat (Double) by value. */
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
        /** CGSize = struct { CGFloat width; CGFloat height; } */
        val CG_SIZE_LAYOUT: java.lang.foreign.GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("width"),
            ValueLayout.JAVA_DOUBLE.withName("height"),
        ).withName("CGSize")

    }
}

/**
 * Converts a real AWT [java.awt.event.KeyEvent] into a Compose [KeyEvent] that preserves the
 * underlying AWT event (so `getAwtEventOrNull` returns it and text fields insert characters).
 *
 * The desktop `KeyEvent.toComposeEvent()` converter is `internal` to compose-ui, and it returns
 * the value class's underlying `InternalKeyEvent`; we reach it reflectively and re-box it into a
 * [KeyEvent] via the synthetic `box-impl`. Both are public at the bytecode level.
 */
internal fun awtKeyToComposeKeyEvent(awtEvent: java.awt.event.KeyEvent): KeyEvent {
    val internal = ComposeKeyEventBridge.toComposeEvent.invoke(null, awtEvent)
    return ComposeKeyEventBridge.box.invoke(null, internal) as KeyEvent
}

private object ComposeKeyEventBridge {
    val toComposeEvent: java.lang.reflect.Method =
        Class.forName("androidx.compose.ui.input.key.KeyEvent_desktopKt")
            .getMethod("toComposeEvent", java.awt.event.KeyEvent::class.java)

    /** `KeyEvent.box-impl(Object): KeyEvent` — boxes the underlying InternalKeyEvent. */
    val box: java.lang.reflect.Method =
        KeyEvent::class.java.getMethod("box-impl", Any::class.java)
}
