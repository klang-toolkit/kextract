/**
 * Sample hello-triangle-web — JS/IR entry point.
 *
 * Creates a browser canvas via the Kadre API, plugs wgpu4k Web onto it
 * (CanvasSurface from the `<canvas>`), then renders an RGB triangle each frame.
 *
 * Reuses the WGSL shader and render sequence from the desktop sample
 * `org.graphiks.kadre.samples.hellotriangle`, adapted to the wgpu4k web API.
 */
package org.graphiks.kadre.samples.web

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.WindowEvent
import io.ygdrasil.webgpu.CanvasSurface
import io.ygdrasil.webgpu.ColorTargetState
import io.ygdrasil.webgpu.FragmentState
import io.ygdrasil.webgpu.GPUDevice
import io.ygdrasil.webgpu.GPULoadOp
import io.ygdrasil.webgpu.GPURenderPipeline
import io.ygdrasil.webgpu.GPUStoreOp
import io.ygdrasil.webgpu.GPUTextureFormat
import io.ygdrasil.webgpu.GPUTextureUsage
import io.ygdrasil.webgpu.PrimitiveState
import io.ygdrasil.webgpu.RenderPassColorAttachment
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.SurfaceConfiguration
import io.ygdrasil.webgpu.VertexState
import io.ygdrasil.webgpu.getCanvasSurface
import io.ygdrasil.webgpu.requestAdapter
import io.ygdrasil.webgpu.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.browser.document
import kotlin.js.unsafeCast
import kotlin.math.max
import kotlin.math.roundToInt

/** `window.devicePixelRatio` — read via JS interop (not exposed typed on the current `Window`). */
private fun jsDevicePixelRatio(): Double = js("window.devicePixelRatio").unsafeCast<Double>()

// ---------------------------------------------------------------------------
// WGSL shader — RGB triangle with hardcoded positions (identical to the desktop sample)
// ---------------------------------------------------------------------------

private val TRIANGLE_WGSL = """
struct VertexOutput {
    @builtin(position) position: vec4<f32>,
    @location(0) color: vec3<f32>,
};

@vertex
fn vs_main(@builtin(vertex_index) vertexIndex: u32) -> VertexOutput {
    var positions = array<vec2<f32>, 3>(
        vec2<f32>( 0.0,  0.5),
        vec2<f32>(-0.5, -0.5),
        vec2<f32>( 0.5, -0.5),
    );
    var colors = array<vec3<f32>, 3>(
        vec3<f32>(1.0, 0.0, 0.0),
        vec3<f32>(0.0, 1.0, 0.0),
        vec3<f32>(0.0, 0.0, 1.0),
    );
    var out: VertexOutput;
    out.position = vec4<f32>(positions[vertexIndex], 0.0, 1.0);
    out.color = colors[vertexIndex];
    return out;
}

@fragment
fn fs_main(in: VertexOutput) -> @location(0) vec4<f32> {
    return vec4<f32>(in.color, 1.0);
}
""".trimIndent()

/**
 * Handler for the hello-triangle-web sample (JS/IR).
 *
 * Maintains the wgpu4k Web resources across frames:
 * - [surface]: [CanvasSurface] bound to the DOM `<canvas>`
 * - [device]: GPU device
 * - [pipeline]: render pipeline (vertex + fragment)
 * - [format]: presentation format negotiated at configuration
 *
 * Rendering is triggered on each [WebWindowEvent.RedrawRequested].
 * [aboutToWait] requests a continuous redraw (~60 fps via the web loop).
 * [WebWindowEvent.Resized] reconfigures the surface (useful for #21).
 */
class HelloTriangleWebApp : ApplicationHandler {

    private var surface: CanvasSurface? = null
    private var device: GPUDevice? = null
    private var pipeline: GPURenderPipeline? = null
    private var format: GPUTextureFormat = GPUTextureFormat.BGRA8Unorm
    private var window: org.graphiks.kadre.core.Window? = null
    /** stdlib DOM reference to the `<canvas>` — used to adjust the drawing buffer on resize (#21). */
    private var domCanvas: org.w3c.dom.HTMLCanvasElement? = null
    private var ready = false

    private val scope = CoroutineScope(Dispatchers.Default)

    /**
     * Called as soon as the event loop allows surface creation.
     *
     * Web sequence:
     * 1. Kadre window (canvas `kadre-canvas`)
     * 2. Resolution of the DOM `<canvas>` from [RawWindowHandle.Web]
     * 3. [CanvasSurface] via `HTMLCanvasElement.getCanvasSurface()`
     * 4. Adapter + Device (suspend → launched in a coroutine)
     * 5. Surface configuration + pipeline (reuses [TRIANGLE_WGSL])
     */
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        println("[hello-triangle-web] canCreateSurfaces — initializing wgpu4k Web")

        val win = eventLoop.createWindow(
            WindowAttributes(
                title = "kadre-canvas",
                resizable = true,
            )
        )
        window = win

        val handle = win.rawWindowHandle
        if (handle !is RawWindowHandle.Web) {
            println("[hello-triangle-web] Unsupported platform: $handle")
            return
        }
        val canvasId = handle.canvasElementId ?: "kadre-canvas"
        val domCanvas = document.getElementById(canvasId)
        if (domCanvas == null) {
            println("[hello-triangle-web] Canvas '$canvasId' not found in DOM")
            return
        }
        // `getCanvasSurface()` is defined by wgpu4k on its own external type
        // `io.ygdrasil.webgpu.HTMLCanvasElement`. We cast the stdlib DOM element
        // (`org.w3c.dom`) to that type via `unsafeCast`: at runtime, it is the same
        // JS `HTMLCanvasElement` object.
        this.domCanvas = domCanvas.unsafeCast<org.w3c.dom.HTMLCanvasElement>()
        val canvas = domCanvas.unsafeCast<io.ygdrasil.webgpu.HTMLCanvasElement>()

        // Size the drawing buffer in physical pixels from the start (#21).
        syncCanvasBackingStore()

        val canvasSurface = canvas.getCanvasSurface().let { CanvasSurface(it) }
        surface = canvasSurface
        println("[hello-triangle-web] CanvasSurface created")

        // Adapter + Device are suspend (navigator.gpu) → coroutine.
        scope.launch {
            val adapter = requestAdapter().getOrElse { err ->
                println("[hello-triangle-web] Failed to acquire Adapter: $err")
                return@launch
            }
            val gpuDevice = adapter.requestDevice().getOrElse { err ->
                println("[hello-triangle-web] Failed to acquire Device: $err")
                adapter.close()
                return@launch
            }
            device = gpuDevice

            format = canvasSurface.preferredCanvasFormat ?: GPUTextureFormat.BGRA8Unorm
            configureSurface(canvasSurface, gpuDevice)

            val shaderModule = gpuDevice.createShaderModule(ShaderModuleDescriptor(code = TRIANGLE_WGSL))
            pipeline = gpuDevice.createRenderPipeline(
                RenderPipelineDescriptor(
                    vertex = VertexState(module = shaderModule, entryPoint = "vs_main"),
                    primitive = PrimitiveState(),
                    fragment = FragmentState(
                        module = shaderModule,
                        entryPoint = "fs_main",
                        targets = listOf(ColorTargetState(format = format)),
                    ),
                )
            )
            adapter.close()
            ready = true
            println("[hello-triangle-web] Pipeline ready — format=$format")
            win.requestRedraw()
        }
    }

    /**
     * Adjusts the `<canvas>` drawing buffer to the current physical size (#21).
     *
     * The `ResizeObserver` reports CSS pixels; the wgpu swap chain follows the
     * canvas `width`/`height` attributes, which must be in physical pixels
     * (`CSS size × devicePixelRatio`) for crisp rendering on high-density screens.
     *
     * @param cssWidth  CSS width (or `null` to read `clientWidth`).
     * @param cssHeight CSS height (or `null` to read `clientHeight`).
     * @return `true` if the buffer size changed (reconfiguration needed).
     */
    private fun syncCanvasBackingStore(cssWidth: Int? = null, cssHeight: Int? = null): Boolean {
        val canvas = domCanvas ?: return false
        val dpr = jsDevicePixelRatio()
        val cw = cssWidth ?: canvas.clientWidth
        val ch = cssHeight ?: canvas.clientHeight
        val physW = max(1, (cw * dpr).roundToInt())
        val physH = max(1, (ch * dpr).roundToInt())
        if (canvas.width == physW && canvas.height == physH) return false
        canvas.width = physW
        canvas.height = physH
        println("[hello-triangle-web] Canvas backing store → ${physW}×${physH} (dpr=$dpr)")
        return true
    }

    /**
     * Configures (or reconfigures) the [CanvasSurface] with the current device and format.
     *
     * On web, the surface size follows the `<canvas>` `width`/`height` attribute
     * (updated by [syncCanvasBackingStore]) — the wgpu swap chain is thus
     * reconfigured to the new physical resolution on resize (#21).
     */
    private fun configureSurface(canvasSurface: CanvasSurface, gpuDevice: GPUDevice) {
        canvasSurface.configure(
            SurfaceConfiguration(
                device = gpuDevice,
                format = format,
                usage = setOf(GPUTextureUsage.RenderAttachment),
            )
        )
    }

    /**
     * Requests a continuous redraw to keep the rendering animated.
     */
    override fun aboutToWait(eventLoop: ActiveEventLoop) {
        if (ready) window?.requestRedraw()
    }

    /**
     * Web window events.
     *
     * - [WindowEvent.RedrawRequested]: renders an RGB triangle frame
     * - [WindowEvent.Resized]: reconfigures the surface (preparation for #21)
     * - [WindowEvent.CloseRequested]: releases resources and exits
     */
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
        when (event) {
            is WindowEvent.RedrawRequested -> renderFrame()
            is WindowEvent.Resized -> {
                println("[hello-triangle-web] Resized → ${event.size.width}×${event.size.height} (CSS px)")
                // Update the drawing buffer in physical pixels then reconfigure
                // the swap chain to the new resolution (#21).
                syncCanvasBackingStore(event.size.width, event.size.height)
                val s = surface
                val d = device
                if (s != null && d != null) configureSurface(s, d)
            }
            is WindowEvent.CloseRequested -> {
                println("[hello-triangle-web] CloseRequested — releasing resources")
                releaseResources()
                eventLoop.exit()
            }
            else -> { /* ignore */ }
        }
    }

    /**
     * Renders a frame: black clear + draw 3 vertices + present.
     *
     * WebGPU sequence: getCurrentTexture → createView → commandEncoder →
     * renderPass (clear + draw) → submit → present.
     */
    private fun renderFrame() {
        val surf = surface ?: return
        val dev = device ?: return
        val pipe = pipeline ?: return

        val surfaceTexture = surf.getCurrentTexture()
        val texture = surfaceTexture.texture
        val textureView = texture.createView(null)
        val encoder = dev.createCommandEncoder()

        val renderPass = encoder.beginRenderPass(
            RenderPassDescriptor(
                colorAttachments = listOf(
                    RenderPassColorAttachment(
                        view = textureView,
                        loadOp = GPULoadOp.Clear,
                        storeOp = GPUStoreOp.Store,
                        clearValue = Color(r = 0.0, g = 0.0, b = 0.0, a = 1.0),
                    )
                )
            )
        )
        renderPass.setPipeline(pipe)
        renderPass.draw(3u, 1u, 0u, 0u)
        renderPass.end()

        val commandBuffer = encoder.finish()
        dev.queue.submit(listOf(commandBuffer))
        surf.present()

        textureView.close()
        encoder.close()
    }

    /**
     * Releases the wgpu4k Web resources.
     */
    private fun releaseResources() {
        pipeline?.let { runCatching { it.close() } }
        device?.let { runCatching { it.close() } }
        surface?.let { runCatching { it.close() } }
        pipeline = null
        device = null
        surface = null
        window = null
        ready = false
    }
}

/**
 * Entry point of the hello-triangle-web sample (JS/IR).
 */
fun main() {
    println("[hello-triangle-web] Starting — Kadre + wgpu4k Web triangle RGB")
    EventLoop().runApp(HelloTriangleWebApp())
}
