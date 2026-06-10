/**
 * Sample hello-triangle — colored triangle rendering via wgpu4k (GRA-138 + GRA-139).
 *
 * Opens a Kadre window and displays a continuously animated RGB triangle:
 *   Instance → Surface → Adapter → Device → Pipeline → render loop.
 *
 * Each [WindowEvent.RedrawRequested] triggers a frame:
 *   getCurrentTexture → createView → commandEncoder → renderPass (draw 3 vertices) → submit → present.
 *
 * [WindowEvent.Resized] and [WindowEvent.ScaleFactorChanged] trigger a surface
 * reconfiguration (new swap chain) via [HelloTriangleApp.handleResize] (GRA-139).
 *
 * Usage: ./gradlew :samples:hello-triangle:run
 */
package org.graphiks.kadre.samples.hellotriangle

import ffi.JvmNativeAddress
import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.PhysicalSize
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowEvent
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.ColorTargetState
import io.ygdrasil.webgpu.CompositeAlphaMode
import io.ygdrasil.webgpu.FragmentState
import io.ygdrasil.webgpu.GPUDevice
import io.ygdrasil.webgpu.GPULoadOp
import io.ygdrasil.webgpu.GPURenderPipeline
import io.ygdrasil.webgpu.GPUStoreOp
import io.ygdrasil.webgpu.GPUTextureFormat
import io.ygdrasil.webgpu.GPUTextureUsage
import io.ygdrasil.webgpu.NativeSurface
import io.ygdrasil.webgpu.PrimitiveState
import io.ygdrasil.webgpu.RenderPassColorAttachment
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.SurfaceConfiguration
import io.ygdrasil.webgpu.SurfaceTextureStatus
import io.ygdrasil.webgpu.VertexState
import io.ygdrasil.webgpu.WGPU
import io.ygdrasil.webgpu.WGPUInstanceBackend
import io.ygdrasil.webgpu.WGPULowLevelApi
import org.graphiks.kadre.ffi.objc.ObjCRuntime
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import kotlinx.coroutines.runBlocking

// ---------------------------------------------------------------------------
// WGSL shaders — RGB triangle with hardcoded positions
// ---------------------------------------------------------------------------

internal val TRIANGLE_WGSL = """
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

// ---------------------------------------------------------------------------
// Application
// ---------------------------------------------------------------------------

/**
 * Handler for the hello-triangle sample (GRA-138 + GRA-139).
 *
 * Maintains the wgpu4k resources across frames:
 * - [surface]: render surface bound to the CAMetalLayer
 * - [gpuDevice]: GPU device
 * - [pipeline]: render pipeline (vertex + fragment shaders)
 * - [window]: Kadre window for `requestRedraw()`
 * - [surfaceFormat]: texture format negotiated at configuration
 * - [surfaceAlphaMode]: alpha mode used for reconfiguration on resize
 *
 * Rendering is triggered on each [WindowEvent.RedrawRequested].
 * [aboutToWait] calls [Window.requestRedraw] for continuous rendering (~60 fps).
 * [WindowEvent.Resized] and [WindowEvent.ScaleFactorChanged] trigger [handleResize].
 */
@OptIn(WGPULowLevelApi::class)
class HelloTriangleApp : ApplicationHandler {

    // wgpu4k resources (initialized in canCreateSurfaces)
    private var wgpu: WGPU? = null
    private var surface: NativeSurface? = null
    private var gpuDevice: GPUDevice? = null
    private var pipeline: GPURenderPipeline? = null
    private var window: org.graphiks.kadre.core.Window? = null
    private var surfaceFormat: GPUTextureFormat = GPUTextureFormat.BGRA8Unorm
    private var surfaceAlphaMode: CompositeAlphaMode = CompositeAlphaMode.Auto

    // FPS counter
    private var frameCount = 0
    private var fpsWindowStart = System.currentTimeMillis()

    // ---------------------------------------------------------------------------
    // Initialization
    // ---------------------------------------------------------------------------

    /**
     * Called as soon as AppKit allows render surface creation.
     *
     * Sequence:
     * 1. Window creation
     * 2. Retrieval of the CAMetalLayer from the NSView
     * 3. WGPU Instance (Metal backend)
     * 4. Surface from the CAMetalLayer
     * 5. Adapter + Device
     * 6. Surface configuration
     * 7. Shader module + render pipeline
     */
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        println("[hello-triangle] canCreateSurfaces — initializing wgpu4k + pipeline")

        // 1. Kadre window
        val win = eventLoop.createWindow(
            WindowAttributes(
                title = "Hello Triangle — Kadre + wgpu4k",
                size = PhysicalSize(width = 800, height = 600),
                visible = true,
                resizable = true,
            )
        )
        window = win
        println("[hello-triangle] Window created — windowId=${win.id.value}")

        // 2. CAMetalLayer from the NSView
        val handle = win.rawWindowHandle
        if (handle !is RawWindowHandle.AppKit) {
            println("[hello-triangle] Unsupported platform: $handle")
            return
        }
        println("[hello-triangle] RawWindowHandle.AppKit — nsView=0x%x  nsWindow=0x%x"
            .format(handle.nsView, handle.nsWindow))

        // Use nsLayer directly (exposed by AppKitWindow) rather than [nsView layer],
        // which may return AppKit's generic CALayer if the setLayer/setWantsLayer order
        // was incorrect. nsLayer = metalLayerPtr.address() from AppKitWindow.
        val metalLayerAddr = if (handle.nsLayer != 0L) {
            handle.nsLayer
        } else {
            // Fallback: obtain via [nsView layer] (legacy path)
            getMetalLayerFromNsView(handle.nsView)
        }
        if (metalLayerAddr == 0L) {
            println("[hello-triangle] Unable to obtain CAMetalLayer from NSView")
            return
        }
        println("[hello-triangle] CAMetalLayer = 0x%x".format(metalLayerAddr))

        // 3. WGPU Instance (Metal backend)
        // libWGPU.dylib is bundled inside the wgpu4k-native JAR and must be explicitly
        // extracted + loaded before calling any wgpu function.
        ffi.LibraryLoader.load()
        val wgpuInstance = WGPU.createInstance(WGPUInstanceBackend.Metal)
            ?: run {
                println("[hello-triangle] Failed to create WGPU Instance")
                return
            }
        wgpu = wgpuInstance
        println("[hello-triangle] WGPU Instance created")

        // 4. Surface from the CAMetalLayer
        val metalLayerNativeAddr = JvmNativeAddress(MemorySegment.ofAddress(metalLayerAddr))
        val surf: NativeSurface = wgpuInstance.getSurfaceFromMetalLayer(metalLayerNativeAddr)
            ?: run {
                println("[hello-triangle] Failed to create Surface from CAMetalLayer")
                wgpuInstance.close()
                return
            }
        surface = surf
        println("[hello-triangle] Surface created")

        // 5. Adapter
        val adapter = wgpuInstance.requestAdapter(surf)
            ?: run {
                println("[hello-triangle] Failed to acquire Adapter")
                surf.close()
                wgpuInstance.close()
                return
            }
        println("[hello-triangle] Adapter — info=${adapter.info}")

        surf.computeSurfaceCapabilities(adapter)
        println("[hello-triangle] Supported formats   : ${surf.supportedFormats}")
        println("[hello-triangle] Supported alpha modes: ${surf.supportedAlphaMode}")

        // 6. Device
        val device = runBlocking { adapter.requestDevice() }
            .getOrElse { err ->
                println("[hello-triangle] Failed to acquire Device: $err")
                adapter.close()
                surf.close()
                wgpuInstance.close()
                return
            }
        gpuDevice = device
        println("[hello-triangle] Device created")

        // 7a. Surface configuration
        val format = surf.supportedFormats
            .firstOrNull { it == GPUTextureFormat.BGRA8Unorm }
            ?: surf.supportedFormats.firstOrNull()
            ?: GPUTextureFormat.BGRA8Unorm
        surfaceFormat = format
        val innerSize = win.innerSize
        val alphaMode = surf.supportedAlphaMode
            .firstOrNull { it == CompositeAlphaMode.Opaque }
            ?: CompositeAlphaMode.Auto
        surfaceAlphaMode = alphaMode

        surf.configure(
            SurfaceConfiguration(
                device = device,
                format = format,
                usage = setOf(GPUTextureUsage.RenderAttachment),
                alphaMode = alphaMode,
            ),
            innerSize.width.toUInt(),
            innerSize.height.toUInt(),
        )
        println("[hello-triangle] Surface configured — format=$format  size=${innerSize.width}×${innerSize.height}  alpha=$alphaMode")

        // 7b. Shader module
        val shaderModule = device.createShaderModule(ShaderModuleDescriptor(code = TRIANGLE_WGSL))
        println("[hello-triangle] Shader module created")

        // 7c. Render pipeline
        val renderPipeline = device.createRenderPipeline(
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
        pipeline = renderPipeline
        println("[hello-triangle] Render pipeline created — ready to draw (GRA-138)")

        // Release the shader module (no longer needed after pipeline creation)
        shaderModule.close()
        adapter.close()

        fpsWindowStart = System.currentTimeMillis()
    }

    // ---------------------------------------------------------------------------
    // Render loop
    // ---------------------------------------------------------------------------

    /**
     * Called when the event loop is about to go into a wait state.
     *
     * Requests a continuous redraw to maintain ~60 fps.
     */
    override fun aboutToWait(eventLoop: ActiveEventLoop) {
        window?.requestRedraw()
    }

    /**
     * Window events.
     *
     * - [WindowEvent.RedrawRequested]: renders an RGB triangle frame
     * - [WindowEvent.Resized]: reconfigures the surface with the new physical size (GRA-139)
     * - [WindowEvent.ScaleFactorChanged]: reconfigures the surface from innerSize (GRA-139)
     * - [WindowEvent.CloseRequested]: releases resources and exits
     */
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
        when (event) {
            is WindowEvent.RedrawRequested -> renderFrame()
            is WindowEvent.Resized -> {
                println("[hello-triangle] Resized → ${event.size.width}×${event.size.height}")
                handleResize(event.size.width, event.size.height)
            }
            is WindowEvent.ScaleFactorChanged -> {
                // Reconfigure from the window's current physical size
                val win = window ?: return
                val inner = win.innerSize
                println("[hello-triangle] ScaleFactorChanged scaleFactor=${event.factor} → reconfigure ${inner.width}×${inner.height}")
                handleResize(inner.width, inner.height)
            }
            is WindowEvent.CloseRequested -> {
                println("[hello-triangle] CloseRequested — releasing resources")
                releaseResources()
                eventLoop.exit()
            }
            else -> { /* ignore */ }
        }
    }

    /**
     * Reconfigures the WebGPU surface with the new size in physical pixels.
     *
     * Called on [WindowEvent.Resized] and [WindowEvent.ScaleFactorChanged].
     * Silently handles cases where the wgpu resources are not yet initialized.
     *
     * @param width  New width in physical pixels.
     * @param height New height in physical pixels.
     */
    private fun handleResize(width: Int, height: Int) {
        val surf = surface ?: return
        val device = gpuDevice ?: return
        if (width <= 0 || height <= 0) return

        surf.configure(
            SurfaceConfiguration(
                device = device,
                format = surfaceFormat,
                usage = setOf(GPUTextureUsage.RenderAttachment),
                alphaMode = surfaceAlphaMode,
            ),
            width.toUInt(),
            height.toUInt(),
        )
        println("[hello-triangle] Surface reconfigured — ${width}×${height}")
    }

    /**
     * Renders a frame.
     *
     * Standard WebGPU sequence:
     * 1. `getCurrentTexture()` → status check
     * 2. `createView()` → view of the presentation texture
     * 3. `createCommandEncoder()` → command encoder
     * 4. `beginRenderPass(...)` → render pass (black clear + draw 3 vertices)
     * 5. `end()` → end of the pass
     * 6. `finish()` → command buffer
     * 7. `queue.submit(...)` → submission to the GPU
     * 8. `present()` → display
     */
    private fun renderFrame() {
        val surf = surface ?: return
        val device = gpuDevice ?: return
        val pipe = pipeline ?: return

        // 1. Presentation texture
        //
        // NOTE — wgpu-native 0.25+ / wgpu4k 0.1.1 compatibility:
        //   Old API:  Success(0) Timeout(1)     Outdated(2) Lost(3) OutOfMemory(4) DeviceLost(5)
        //   New API:  SuccessOptimal(0) SuccessSuboptimal(1) Timeout(2) Outdated(3) ...
        //
        //   wgpu4k 0.1.1 maps the raw values of the old API:
        //     rawStatus=0 → success    (SuccessOptimal   → render OK)
        //     rawStatus=1 → timeout    (SuccessSuboptimal→ VALID texture, but suboptimal)
        //     rawStatus=2 → outdated   (real Timeout     → no drawable)
        //     rawStatus=3 → lost       (Outdated         → reconfigure)
        //
        //   ⇒ Treat `timeout` (=successSuboptimal) as `success`: the texture is valid.
        val surfaceTexture = surf.getCurrentTexture()
        when (surfaceTexture.status) {
            // lost (3) in wgpu4k = Outdated (3) in the new API → reconfigure the surface
            SurfaceTextureStatus.lost -> {
                surfaceTexture.texture.close()
                val win = window
                if (win != null) {
                    val inner = win.innerSize
                    handleResize(inner.width, inner.height)
                }
                return
            }
            // outdated (2) in wgpu4k = Timeout (2) in the new API → no drawable, skip
            // (compatible with the old API where outdated=2 → reconfigure)
            SurfaceTextureStatus.outdated -> {
                surfaceTexture.texture.close()
                val win = window
                if (win != null) {
                    val inner = win.innerSize
                    handleResize(inner.width, inner.height)
                }
                return
            }
            // Terminal errors
            SurfaceTextureStatus.outOfMemory,
            SurfaceTextureStatus.deviceLost -> {
                println("[hello-triangle] getCurrentTexture status=${surfaceTexture.status} — terminal error")
                surfaceTexture.texture.close()
                return
            }
            // success (0) = SuccessOptimal, timeout (1) = SuccessSuboptimal → VALID texture
            SurfaceTextureStatus.success,
            SurfaceTextureStatus.timeout -> { /* continue rendering */ }
        }
        val texture = surfaceTexture.texture

        // 2. Texture view
        val textureView = texture.createView(null)

        // 3. Command encoder
        val encoder = device.createCommandEncoder()

        // 4. Render pass
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

        // 5. Submission
        val commandBuffer = encoder.finish()
        device.queue.submit(listOf(commandBuffer))

        // 6. Presentation
        surf.present()

        // 7. Release of temporary resources
        textureView.close()
        encoder.close()

        // FPS counter
        frameCount++
        val now = System.currentTimeMillis()
        val elapsed = now - fpsWindowStart
        if (elapsed >= 1_000L) {
            val fps = frameCount * 1_000.0 / elapsed
            println("[hello-triangle] FPS: %.1f".format(fps))
            frameCount = 0
            fpsWindowStart = now
        }
    }

    // ---------------------------------------------------------------------------
    // Cleanup
    // ---------------------------------------------------------------------------

    /**
     * Releases all wgpu4k resources.
     */
    private fun releaseResources() {
        pipeline?.let { runCatching { it.close() } }
        gpuDevice?.let { runCatching { it.close() } }
        surface?.let { runCatching { it.close() } }
        wgpu?.let { runCatching { it.close() } }
        pipeline = null
        gpuDevice = null
        surface = null
        wgpu = null
        window = null
        println("[hello-triangle] Resources released")
    }

    // ---------------------------------------------------------------------------
    // Native utility
    // ---------------------------------------------------------------------------

    /**
     * Retrieves the native pointer of the `CAMetalLayer` attached to an `NSView`.
     *
     * ObjC call: `[nsView layer]` via a Panama FFM downcall on `objc_msgSend`.
     * The NSView must already have `wantsLayer = YES` and an assigned `CAMetalLayer`
     * (which [org.graphiks.kadre.appkit.AppKitWindow] guarantees).
     *
     * @param nsViewPtr Memory address of the ObjC pointer to the NSView.
     * @return Address of the CAMetalLayer, or 0 on failure.
     */
    private fun getMetalLayerFromNsView(nsViewPtr: Long): Long {
        // Reuse ObjCRuntime infrastructure — avoids duplicating sel_registerName / objc_msgSend lookup
        // which is unreliable via SymbolLookup.loaderLookup() on some JDK configurations.
        val sel = ObjCRuntime.sel("layer")
        val nsViewSeg = MemorySegment.ofAddress(nsViewPtr)
        return (ObjCRuntime.msgSend(ValueLayout.ADDRESS, nsViewSeg, sel) as MemorySegment).address()
    }
}

// ---------------------------------------------------------------------------
// Entry point
// ---------------------------------------------------------------------------

/**
 * Entry point of the hello-triangle sample.
 *
 * Must be run from the main macOS thread (guaranteed by Gradle via
 * `-XstartOnFirstThread` in [build.gradle.kts]).
 */
fun main(args: Array<String>) {
    // Native screen capture: `--native-capture <path>` captures the screen via
    // ScreenCapturer API and writes a PNG.
    val nativeCaptureIndex = args.indexOf("--native-capture")
    if (nativeCaptureIndex >= 0) {
        val path = args.getOrNull(nativeCaptureIndex + 1)
            ?: error("--native-capture requires a file path: --native-capture <path>")
        nativeCapture(path)
        return
    }

    // Offscreen GPU capture mode: `--capture <path>` renders the triangle into
    // a texture, reads back the framebuffer, writes a PNG, then exits — without opening a window.
    val captureIndex = args.indexOf("--capture")
    if (captureIndex >= 0) {
        val path = args.getOrNull(captureIndex + 1)
            ?: error("--capture requires a file path: --capture <path>")
        captureFrame(path)
        return
    }

    println("[hello-triangle] Starting — Kadre + wgpu4k triangle RGB (GRA-138)")
    EventLoop().runApp(HelloTriangleApp())
    println("[hello-triangle] Finished")
}
