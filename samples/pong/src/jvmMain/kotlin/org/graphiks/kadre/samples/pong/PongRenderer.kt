/**
 * PongRenderer — rendu wgpu4k 2D pour Pong (JVM uniquement).
 *
 * Dessine les éléments du jeu sous forme de quads rectangulaires :
 *   - Fond noir (clear)
 *   - Raquette joueur (gauche, blanche)
 *   - Raquette IA (droite, blanche)
 *   - Balle (petite, blanche)
 *   - Ligne centrale en pointillés
 *   - Score (police bitmap via [BitmapFont])
 *
 * Le shader WGSL accepte un quad décrit par [x, y, w, h] en coordonnées
 * normalisées [0..1] et une couleur RGB. Deux triangles (6 sommets) sont
 * générés en dur dans le vertex shader à partir du vertex_index.
 *
 * .
 */
package org.graphiks.kadre.samples.pong

import ffi.JvmNativeAddress
import org.graphiks.kadre.core.RawWindowHandle
import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.webgpu.BindGroupEntry
import io.ygdrasil.webgpu.BindGroupLayoutDescriptor
import io.ygdrasil.webgpu.BindGroupLayoutEntry
import io.ygdrasil.webgpu.BufferBinding
import io.ygdrasil.webgpu.BufferBindingLayout
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.ColorTargetState
import io.ygdrasil.webgpu.CompositeAlphaMode
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
import io.ygdrasil.webgpu.NativeSurface
import io.ygdrasil.webgpu.PipelineLayoutDescriptor
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
import io.ygdrasil.webgpu.writeBuffer
import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import kotlinx.coroutines.runBlocking

// ---------------------------------------------------------------------------
// Le shader WGSL, les constantes de layout et la construction des quads sont
// désormais en commonMain dans `PongRendererCore.kt` (factorisé entre JVM et Web).
// On expose ici juste l'alias ULong de la taille d'uniform pour l'API wgpu4k JVM.
// ---------------------------------------------------------------------------

// Taille du uniform buffer (cf. UNIFORM_BYTES_LONG en commonMain).
private const val UNIFORM_BYTES: ULong = 32uL

// Nombre max de quads par frame (pool d'uniform buffers + bind groups).
// Décompte : 12 dashes + 2 paddles + 1 ball + 2 × (max 3 digits × ~25 pixels chiffre) = ~165
private const val MAX_QUADS_PER_FRAME = 256

// ---------------------------------------------------------------------------
// PongRenderer
// ---------------------------------------------------------------------------

/**
 * Rendu wgpu4k pour Pong — JVM uniquement (macOS/Metal via AppKit).
 *
 * @param windowHandle Handle natif de la fenêtre Kadre.
 */
@OptIn(WGPULowLevelApi::class)
class PongRenderer(windowHandle: RawWindowHandle) : PongRendererInterface {

    private var wgpu: WGPU? = null
    private var surface: NativeSurface? = null
    private var gpuDevice: GPUDevice? = null
    private var pipeline: GPURenderPipeline? = null
    // Pool d'uniform buffers + bind groups (1 par quad pour ne pas écraser entre draw calls)
    private val uniformBuffers = mutableListOf<GPUBuffer>()
    private val bindGroups = mutableListOf<GPUBindGroup>()

    private var surfaceFormat: GPUTextureFormat = GPUTextureFormat.BGRA8Unorm
    private var surfaceAlphaMode: CompositeAlphaMode = CompositeAlphaMode.Auto
    private var surfaceWidth: Int = 800
    private var surfaceHeight: Int = 600

    // -------------------------------------------------------------------------
    // Initialisation
    // -------------------------------------------------------------------------

    init {
        ffi.LibraryLoader.load()

        val wgpuInstance = WGPU.createInstance(WGPUInstanceBackend.Metal)
            ?: error("[PongRenderer] Échec création WGPU Instance")
        wgpu = wgpuInstance

        val surf = createSurface(wgpuInstance, windowHandle)
            ?: error("[PongRenderer] Impossible de créer la surface wgpu")
        surface = surf

        val adapter = wgpuInstance.requestAdapter(surf)
            ?: error("[PongRenderer] Échec acquisition Adapter")
        surf.computeSurfaceCapabilities(adapter)

        val device = runBlocking { adapter.requestDevice() }
            .getOrElse { err -> error("[PongRenderer] Échec acquisition Device : $err") }
        gpuDevice = device

        val format = surf.supportedFormats
            .firstOrNull { it == GPUTextureFormat.BGRA8Unorm }
            ?: surf.supportedFormats.firstOrNull()
            ?: GPUTextureFormat.BGRA8Unorm
        surfaceFormat = format

        val alphaMode = surf.supportedAlphaMode
            .firstOrNull { it == CompositeAlphaMode.Opaque }
            ?: CompositeAlphaMode.Auto
        surfaceAlphaMode = alphaMode

        configureSurface(device, surf, surfaceWidth, surfaceHeight)

        val shaderModule = device.createShaderModule(ShaderModuleDescriptor(code = PONG_WGSL))

        // Bind group layout
        val bgl = device.createBindGroupLayout(
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

        // Pool : un uniform buffer + un bind group par draw call.
        // Sans pool, tous les draw calls partagent le même buffer et seul le dernier
        // writeBuffer est visible côté GPU (bug : seul le dernier quad s'affiche).
        repeat(MAX_QUADS_PER_FRAME) {
            val buf = device.createBuffer(
                BufferDescriptor(
                    size = UNIFORM_BYTES,
                    usage = setOf(GPUBufferUsage.Uniform, GPUBufferUsage.CopyDst),
                )
            )
            uniformBuffers.add(buf)
            bindGroups.add(
                device.createBindGroup(
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

        val pipelineLayout = device.createPipelineLayout(
            PipelineLayoutDescriptor(bindGroupLayouts = listOf(bgl))
        )

        pipeline = device.createRenderPipeline(
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

        println("[PongRenderer] Initialisé — format=$format alpha=$alphaMode")
    }

    // -------------------------------------------------------------------------
    // Surface helpers
    // -------------------------------------------------------------------------

    private fun createSurface(wgpuInstance: WGPU, handle: RawWindowHandle): NativeSurface? =
        when (handle) {
            is RawWindowHandle.AppKit -> {
                val metalLayerAddr = if (handle.nsLayer != 0L) handle.nsLayer
                                     else getMetalLayerFromNsView(handle.nsView)
                if (metalLayerAddr == 0L) null
                else wgpuInstance.getSurfaceFromMetalLayer(
                    JvmNativeAddress(MemorySegment.ofAddress(metalLayerAddr))
                )
            }
            else -> {
                println("[PongRenderer] Handle non supporté pour wgpu4k : $handle")
                null
            }
        }

    private fun configureSurface(device: GPUDevice, surf: NativeSurface, w: Int, h: Int) {
        if (w <= 0 || h <= 0) return
        surf.configure(
            SurfaceConfiguration(
                device = device,
                format = surfaceFormat,
                usage = setOf(GPUTextureUsage.RenderAttachment),
                alphaMode = surfaceAlphaMode,
            ),
            w.toUInt(),
            h.toUInt(),
        )
    }

    private fun getMetalLayerFromNsView(nsViewPtr: Long): Long {
        val sel = ObjCRuntime.sel("layer")
        val nsViewSeg = MemorySegment.ofAddress(nsViewPtr)
        return (ObjCRuntime.msgSend(ValueLayout.ADDRESS, nsViewSeg, sel) as MemorySegment).address()
    }

    // -------------------------------------------------------------------------
    // PongRendererInterface
    // -------------------------------------------------------------------------

    override fun resize(width: Int, height: Int) {
        surfaceWidth = width
        surfaceHeight = height
        val surf = surface ?: return
        val device = gpuDevice ?: return
        configureSurface(device, surf, width, height)
    }

    override fun draw(state: GameState) {
        val surf = surface ?: return
        val device = gpuDevice ?: return
        val pipe = pipeline ?: return
        if (uniformBuffers.isEmpty()) return

        val surfaceTexture = surf.getCurrentTexture()
        when (surfaceTexture.status) {
            SurfaceTextureStatus.lost, SurfaceTextureStatus.outdated -> {
                surfaceTexture.texture.close()
                configureSurface(device, surf, surfaceWidth, surfaceHeight)
                return
            }
            SurfaceTextureStatus.outOfMemory, SurfaceTextureStatus.deviceLost -> {
                surfaceTexture.texture.close()
                return
            }
            SurfaceTextureStatus.success, SurfaceTextureStatus.timeout -> { /* ok */ }
        }
        val texture = surfaceTexture.texture
        val textureView = texture.createView(null)

        // Build list of quads
        val quads = buildList {
            // Center dashes (gray)
            repeat(DASH_COUNT) { i ->
                val dashY = (i.toDouble() / DASH_COUNT) + (0.5 / DASH_COUNT) - DASH_HEIGHT_N / 2
                add(floatArrayOf(
                    (0.5 - DASH_WIDTH_N / 2).toFloat(), dashY.toFloat(),
                    DASH_WIDTH_N.toFloat(), DASH_HEIGHT_N.toFloat(),
                    0.4f, 0.4f, 0.4f, 0f,
                ))
            }
            // Player paddle (left, white)
            add(floatArrayOf(
                PADDLE_X_LEFT.toFloat(), (state.player.y - state.player.height / 2).toFloat(),
                PADDLE_WIDTH_N.toFloat(), state.player.height.toFloat(),
                1f, 1f, 1f, 0f,
            ))
            // AI paddle (right, white)
            add(floatArrayOf(
                PADDLE_X_RIGHT.toFloat(), (state.ai.y - state.ai.height / 2).toFloat(),
                PADDLE_WIDTH_N.toFloat(), state.ai.height.toFloat(),
                1f, 1f, 1f, 0f,
            ))
            // Ball (white)
            add(floatArrayOf(
                (state.ball.x - BALL_SIZE_N / 2).toFloat(), (state.ball.y - BALL_SIZE_N / 2).toFloat(),
                BALL_SIZE_N.toFloat(), BALL_SIZE_N.toFloat(),
                1f, 1f, 1f, 0f,
            ))
            // Player score (left)
            BitmapFont.renderNumber(state.score.player, x = 0.30, y = SCORE_Y, pixelSize = SCORE_PIXEL)
                .forEach { q ->
                    add(floatArrayOf(q.x.toFloat(), q.y.toFloat(), q.w.toFloat(), q.h.toFloat(), 1f, 1f, 1f, 0f))
                }
            // AI score (right)
            BitmapFont.renderNumber(state.score.ai, x = 0.62, y = SCORE_Y, pixelSize = SCORE_PIXEL)
                .forEach { q ->
                    add(floatArrayOf(q.x.toFloat(), q.y.toFloat(), q.w.toFloat(), q.h.toFloat(), 1f, 1f, 1f, 0f))
                }
        }

        // Garde-fou : on dépasse le pool ? Skip les derniers (mieux que de crasher).
        val drawCount = minOf(quads.size, uniformBuffers.size)
        if (quads.size > uniformBuffers.size) {
            System.err.println(
                "[PongRenderer] Pool insuffisant : ${quads.size} quads > $MAX_QUADS_PER_FRAME"
            )
        }

        // CRITIQUE : tous les writeBuffer DOIVENT être faits AVANT beginRenderPass
        // (sinon spec WebGPU violée, et avec un seul buffer les writes s'écrasent).
        for (i in 0 until drawCount) {
            val data = quads[i]
            device.queue.writeBuffer(uniformBuffers[i], 0uL, data, 0uL, data.size.toULong())
        }

        val encoder = device.createCommandEncoder()
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

        // Un bindGroup différent par quad (chaque bindGroup pointe sur son propre uniform buffer)
        for (i in 0 until drawCount) {
            renderPass.setBindGroup(0u, bindGroups[i], emptyList())
            renderPass.draw(6u, 1u, 0u, 0u)
        }

        renderPass.end()

        val commandBuffer = encoder.finish()
        device.queue.submit(listOf(commandBuffer))
        surf.present()

        textureView.close()
        encoder.close()
    }

    override fun release() {
        runCatching { pipeline?.close() }
        bindGroups.forEach { runCatching { it.close() } }
        uniformBuffers.forEach { runCatching { it.close() } }
        runCatching { gpuDevice?.close() }
        runCatching { surface?.close() }
        runCatching { wgpu?.close() }
        pipeline = null
        bindGroups.clear()
        uniformBuffers.clear()
        gpuDevice = null
        surface = null
        wgpu = null
        println("[PongRenderer] Ressources libérées")
    }
}
