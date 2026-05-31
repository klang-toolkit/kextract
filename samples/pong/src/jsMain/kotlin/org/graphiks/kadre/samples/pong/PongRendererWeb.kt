/**
 * PongRendererWeb — 2D wgpu4k rendering for Pong on the browser side (JS/IR).
 *
 * Ports the JVM [PongRenderer] pattern to wgpu4k Web:
 *   - DOM canvas → [CanvasSurface]
 *   - Adapter + Device acquired via coroutine (requestAdapter / requestDevice are suspend)
 *   - Identical 2D pipeline (WGSL quad by vertex_index, uniforms x/y/w/h/r/g/b/_pad)
 *   - Pool of uniform buffers + bind groups (1 per quad) — fixes the bug of the single
 *     shared buffer that only displayed the last quad (cf. PR #129).
 *   - writeBuffer for all quads BEFORE beginRenderPass (strict WebGPU spec).
 *
 * The wgpu init is asynchronous on the Web side; [draw] is a no-op as long as the pipeline
 * is not ready — which is compatible with the PongGame tick (the first
 * frames are silently dropped during init).
 *
 * (web extension — JVM initially).
 */
package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.core.RawWindowHandle
import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.webgpu.BindGroupEntry
import io.ygdrasil.webgpu.BindGroupLayoutDescriptor
import io.ygdrasil.webgpu.BindGroupLayoutEntry
import io.ygdrasil.webgpu.BufferBinding
import io.ygdrasil.webgpu.BufferBindingLayout
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.CanvasSurface
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.ColorTargetState
import io.ygdrasil.webgpu.FragmentState
import io.ygdrasil.webgpu.GPUBindGroup
import io.ygdrasil.webgpu.GPUBuffer
import io.ygdrasil.webgpu.GPUBufferBindingType
import io.ygdrasil.webgpu.GPUBufferUsage
import io.ygdrasil.webgpu.GPUDevice
import io.ygdrasil.webgpu.GPULoadOp
import io.ygdrasil.webgpu.GPURenderPipeline
import io.ygdrasil.webgpu.GPUShaderStage
import io.ygdrasil.webgpu.GPUStoreOp
import io.ygdrasil.webgpu.GPUTextureFormat
import io.ygdrasil.webgpu.GPUTextureUsage
import io.ygdrasil.webgpu.PipelineLayoutDescriptor
import io.ygdrasil.webgpu.PrimitiveState
import io.ygdrasil.webgpu.RenderPassColorAttachment
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.SurfaceConfiguration
import io.ygdrasil.webgpu.VertexState
import io.ygdrasil.webgpu.getCanvasSurface
import io.ygdrasil.webgpu.requestAdapter
import io.ygdrasil.webgpu.writeBuffer
import kotlinx.browser.document
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.js.unsafeCast

// ---------------------------------------------------------------------------
// The WGSL shader, the layout constants and `buildPongQuads` are in commonMain
// (cf. PongRendererCore.kt). Here we just expose the ULong alias for the wgpu4k Web API.
// ---------------------------------------------------------------------------

// 32 bytes (cf. UNIFORM_BYTES_LONG in commonMain — wgpu4k wants a ULong on the API side).
private val UNIFORM_BYTES: ULong = 32uL

// ---------------------------------------------------------------------------
// PongRendererWeb
// ---------------------------------------------------------------------------

class PongRendererWeb(windowHandle: RawWindowHandle) : PongRendererInterface {

    private var surface: CanvasSurface? = null
    private var device: GPUDevice? = null
    private var pipeline: GPURenderPipeline? = null
    // Pool: one uniform buffer + one bind group per quad (cf. fix PR #129).
    private val uniformBuffers = mutableListOf<GPUBuffer>()
    private val bindGroups = mutableListOf<GPUBindGroup>()
    private var format: GPUTextureFormat = GPUTextureFormat.BGRA8Unorm
    private var ready = false

    private val scope = CoroutineScope(Dispatchers.Default)

    init {
        if (windowHandle !is RawWindowHandle.Web) {
            println("[PongRendererWeb] Handle non supporté : $windowHandle")
        } else {
            // Cascading canvas resolution:
            //   1. canvasElementId from the handle (on the current Kadre Web, it is the `title` of WindowAttributes
            //      — debatable convention, to be fixed on the backend side; follow-up to open).
            //   2. Fallback "kadre-canvas" (id present in the sample's index.html).
            val requestedId = windowHandle.canvasElementId
            val domCanvas = requestedId?.let { document.getElementById(it) }
                ?: document.getElementById("kadre-canvas")
            if (domCanvas == null) {
                println("[PongRendererWeb] Canvas introuvable (essayé '${requestedId}' puis 'kadre-canvas')")
            } else {
                if (requestedId != null && document.getElementById(requestedId) == null) {
                    println("[PongRendererWeb] Fallback canvas 'kadre-canvas' (id demandé '$requestedId' absent)")
                }
                val canvas = domCanvas.unsafeCast<io.ygdrasil.webgpu.HTMLCanvasElement>()
                val canvasSurface = canvas.getCanvasSurface().let { CanvasSurface(it) }
                surface = canvasSurface
                initWgpu(canvasSurface)
            }
        }
    }

    private fun initWgpu(canvasSurface: CanvasSurface) {
        scope.launch {
            val adapter = requestAdapter().getOrElse { err ->
                println("[PongRendererWeb] Échec adapter : $err")
                return@launch
            }
            val gpuDevice = adapter.requestDevice().getOrElse { err ->
                println("[PongRendererWeb] Échec device : $err")
                adapter.close()
                return@launch
            }
            device = gpuDevice

            format = canvasSurface.preferredCanvasFormat ?: GPUTextureFormat.BGRA8Unorm
            canvasSurface.configure(
                SurfaceConfiguration(
                    device = gpuDevice,
                    format = format,
                    usage = setOf(GPUTextureUsage.RenderAttachment),
                )
            )

            val shaderModule = gpuDevice.createShaderModule(ShaderModuleDescriptor(code = PONG_WGSL))

            val bgl = gpuDevice.createBindGroupLayout(
                BindGroupLayoutDescriptor(
                    entries = listOf(
                        BindGroupLayoutEntry(
                            binding = 0u,
                            visibility = setOf(GPUShaderStage.Vertex, GPUShaderStage.Fragment),
                            buffer = BufferBindingLayout(
                                type = GPUBufferBindingType.Uniform,
                                hasDynamicOffset = false,
                                minBindingSize = UNIFORM_BYTES,
                            ),
                        )
                    )
                )
            )

            // Pool: one uniform buffer + one bind group per draw call (cf. PR #129).
            repeat(MAX_QUADS_PER_FRAME) {
                val buf = gpuDevice.createBuffer(
                    BufferDescriptor(
                        size = UNIFORM_BYTES,
                        usage = setOf(GPUBufferUsage.Uniform, GPUBufferUsage.CopyDst),
                    )
                )
                uniformBuffers.add(buf)
                bindGroups.add(
                    gpuDevice.createBindGroup(
                        BindGroupDescriptor(
                            layout = bgl,
                            entries = listOf(
                                BindGroupEntry(
                                    binding = 0u,
                                    resource = BufferBinding(
                                        buffer = buf,
                                        offset = 0uL,
                                        size = UNIFORM_BYTES,
                                    ),
                                )
                            )
                        )
                    )
                )
            }

            val pipelineLayout = gpuDevice.createPipelineLayout(
                PipelineLayoutDescriptor(bindGroupLayouts = listOf(bgl))
            )

            pipeline = gpuDevice.createRenderPipeline(
                RenderPipelineDescriptor(
                    layout = pipelineLayout,
                    vertex = VertexState(module = shaderModule, entryPoint = "vs_main"),
                    primitive = PrimitiveState(),
                    fragment = FragmentState(
                        module = shaderModule,
                        entryPoint = "fs_main",
                        targets = listOf(ColorTargetState(format = format)),
                    ),
                )
            )

            shaderModule.close()
            bgl.close()
            pipelineLayout.close()
            adapter.close()
            ready = true
            println("[PongRendererWeb] Pipeline prêt — format=$format")
        }
    }

    // -------------------------------------------------------------------------
    // PongRendererInterface
    // -------------------------------------------------------------------------

    override fun resize(width: Int, height: Int) {
        // On Web, the canvas drawing buffer is adjusted on the Kadre backend side
        // (cf. WebWindow / ResizeObserver). We reconfigure the swap chain to the
        // new format if the device is ready.
        val s = surface ?: return
        val d = device ?: return
        if (!ready) return
        s.configure(
            SurfaceConfiguration(
                device = d,
                format = format,
                usage = setOf(GPUTextureUsage.RenderAttachment),
            )
        )
    }

    override fun draw(state: GameState) {
        if (!ready) return
        val surf = surface ?: return
        val dev = device ?: return
        val pipe = pipeline ?: return
        if (uniformBuffers.isEmpty()) return

        // List of quads built by `buildPongQuads` in commonMain (cf. PongRendererCore).
        val quads = buildPongQuads(state)

        val drawCount = minOf(quads.size, uniformBuffers.size)
        if (quads.size > uniformBuffers.size) {
            println("[PongRendererWeb] Pool insuffisant : ${quads.size} > $MAX_QUADS_PER_FRAME")
        }

        // CRITICAL: all writeBuffer BEFORE beginRenderPass (WebGPU spec + cf. PR #129).
        for (i in 0 until drawCount) {
            val data = quads[i]
            dev.queue.writeBuffer(uniformBuffers[i], 0uL, data, 0uL, data.size.toULong())
        }

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

        for (i in 0 until drawCount) {
            renderPass.setBindGroup(0u, bindGroups[i], emptyList())
            renderPass.draw(6u, 1u, 0u, 0u)
        }

        renderPass.end()

        val commandBuffer = encoder.finish()
        dev.queue.submit(listOf(commandBuffer))
        surf.present()

        textureView.close()
        encoder.close()
    }

    override fun release() {
        runCatching { pipeline?.close() }
        bindGroups.forEach { runCatching { it.close() } }
        uniformBuffers.forEach { runCatching { it.close() } }
        runCatching { device?.close() }
        runCatching { surface?.close() }
        pipeline = null
        bindGroups.clear()
        uniformBuffers.clear()
        device = null
        surface = null
        ready = false
        println("[PongRendererWeb] Ressources libérées")
    }
}
