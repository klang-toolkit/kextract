/**
 * Sample hello-triangle-web — point d'entrée wasmJs (Redmine #27).
 *
 * Crée un canvas navigateur via l'API Kadre, branche wgpu4k Web dessus
 * (CanvasSurface depuis le `<canvas>`), puis rend un triangle RGB à chaque frame.
 *
 * Réutilise le shader WGSL et la séquence de rendu du sample desktop
 * `org.graphiks.kadre.samples.hellotriangle`, adaptés à l'API web de wgpu4k.
 */
package org.graphiks.kadre.samples.web

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.web.WebWindowEvent
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

/** `window.devicePixelRatio` — non exposé sur le `Window` wasmJs, lu via interop JS. */
private fun jsDevicePixelRatio(): Double = js("window.devicePixelRatio")

// ---------------------------------------------------------------------------
// WGSL shader — triangle RGB à positions codées en dur (identique au sample desktop)
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
 * Gestionnaire du sample hello-triangle-web (wasmJs).
 *
 * Maintient les ressources wgpu4k Web entre les frames :
 * - [surface] : [CanvasSurface] liée au `<canvas>` DOM
 * - [device] : device GPU
 * - [pipeline] : pipeline de rendu (vertex + fragment)
 * - [format] : format de présentation négocié à la configuration
 *
 * Le rendu est déclenché à chaque [WebWindowEvent.RedrawRequested].
 * [aboutToWait] demande un redraw continu (~60 fps via la boucle web).
 * [WebWindowEvent.Resized] reconfigure la surface (utile pour #21).
 */
class HelloTriangleWebApp : ApplicationHandler {

    private var surface: CanvasSurface? = null
    private var device: GPUDevice? = null
    private var pipeline: GPURenderPipeline? = null
    private var format: GPUTextureFormat = GPUTextureFormat.BGRA8Unorm
    private var window: org.graphiks.kadre.core.Window? = null
    /** Référence DOM stdlib du `<canvas>` — sert à ajuster le drawing buffer sur resize (#21). */
    private var domCanvas: org.w3c.dom.HTMLCanvasElement? = null
    private var ready = false

    private val scope = CoroutineScope(Dispatchers.Default)

    /**
     * Appelé dès que la boucle d'événements autorise la création de surfaces.
     *
     * Séquence web :
     * 1. Fenêtre Kadre (canvas `kadre-canvas`)
     * 2. Résolution du `<canvas>` DOM depuis [RawWindowHandle.Web]
     * 3. [CanvasSurface] via `HTMLCanvasElement.getCanvasSurface()`
     * 4. Adapter + Device (suspend → lancés dans une coroutine)
     * 5. Configuration de la surface + pipeline (réutilise [TRIANGLE_WGSL])
     */
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        println("[hello-triangle-web] canCreateSurfaces — initialisation wgpu4k Web")

        val win = eventLoop.createWindow(
            WindowAttributes(
                title = "kadre-canvas",
                resizable = true,
            )
        )
        window = win

        val handle = win.rawWindowHandle
        if (handle !is RawWindowHandle.Web) {
            println("[hello-triangle-web] Plateforme non supportée : $handle")
            return
        }
        val canvasId = handle.canvasElementId ?: "kadre-canvas"
        val domCanvas = document.getElementById(canvasId)
        if (domCanvas == null) {
            println("[hello-triangle-web] Canvas '$canvasId' introuvable dans le DOM")
            return
        }
        // `getCanvasSurface()` est défini par wgpu4k sur son propre type external
        // `io.ygdrasil.webgpu.HTMLCanvasElement`. On caste l'élément DOM stdlib
        // (`org.w3c.dom`) vers ce type via `unsafeCast` : au runtime, c'est le même
        // objet JS `HTMLCanvasElement`.
        this.domCanvas = domCanvas.unsafeCast<org.w3c.dom.HTMLCanvasElement>()
        val canvas = domCanvas.unsafeCast<io.ygdrasil.webgpu.HTMLCanvasElement>()

        // Dimensionner le drawing buffer en pixels physiques dès le départ (#21).
        syncCanvasBackingStore()

        val canvasSurface = canvas.getCanvasSurface().let { CanvasSurface(it) }
        surface = canvasSurface
        println("[hello-triangle-web] CanvasSurface créée")

        // Adapter + Device sont suspend (navigator.gpu) → coroutine.
        scope.launch {
            val adapter = requestAdapter().getOrElse { err ->
                println("[hello-triangle-web] Échec acquisition Adapter : $err")
                return@launch
            }
            val gpuDevice = adapter.requestDevice().getOrElse { err ->
                println("[hello-triangle-web] Échec acquisition Device : $err")
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
            println("[hello-triangle-web] Pipeline prêt — format=$format")
            win.requestRedraw()
        }
    }

    /**
     * Ajuste le drawing buffer du `<canvas>` à la taille physique courante (#21).
     *
     * Le `ResizeObserver` reporte des pixels CSS ; le swap chain wgpu suit les
     * attributs `width`/`height` du canvas, qui doivent être en pixels physiques
     * (`taille CSS × devicePixelRatio`) pour un rendu net sur écrans haute densité.
     *
     * @param cssWidth  Largeur CSS (ou `null` pour lire `clientWidth`).
     * @param cssHeight Hauteur CSS (ou `null` pour lire `clientHeight`).
     * @return `true` si la taille du buffer a changé (reconfiguration nécessaire).
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
     * Configure (ou reconfigure) la [CanvasSurface] avec le device et le format courants.
     *
     * Sur web, la taille de la surface suit l'attribut `width`/`height` du `<canvas>`
     * (mis à jour par [syncCanvasBackingStore]) — le swap chain wgpu est ainsi
     * reconfiguré à la nouvelle résolution physique sur resize (#21).
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
     * Demande un redraw continu pour maintenir le rendu animé.
     */
    override fun aboutToWait(eventLoop: ActiveEventLoop) {
        if (ready) window?.requestRedraw()
    }

    /**
     * Événements fenêtre web.
     *
     * - [WebWindowEvent.RedrawRequested] : rend une frame triangle RGB
     * - [WebWindowEvent.Resized] : reconfigure la surface (préparation #21)
     * - [WebWindowEvent.CloseRequested] : libère les ressources et quitte
     */
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        when (event) {
            is WebWindowEvent.RedrawRequested -> renderFrame()
            is WebWindowEvent.Resized -> {
                println("[hello-triangle-web] Resized → ${event.width}×${event.height} (CSS px)")
                // Met à jour le drawing buffer en pixels physiques puis reconfigure
                // le swap chain à la nouvelle résolution (#21).
                syncCanvasBackingStore(event.width, event.height)
                val s = surface
                val d = device
                if (s != null && d != null) configureSurface(s, d)
            }
            is WebWindowEvent.CloseRequested -> {
                println("[hello-triangle-web] CloseRequested — libération des ressources")
                releaseResources()
                eventLoop.exit()
            }
            else -> { /* ignorer */ }
        }
    }

    /**
     * Rend une frame : clear noir + draw 3 vertices + present.
     *
     * Séquence WebGPU : getCurrentTexture → createView → commandEncoder →
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
     * Libère les ressources wgpu4k Web.
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
 * Point d'entrée du sample hello-triangle-web (wasmJs).
 */
fun main() {
    println("[hello-triangle-web] Démarrage — Kadre + wgpu4k Web triangle RGB (Redmine #27)")
    EventLoop().runApp(HelloTriangleWebApp())
}
