/**
 * Sample hello-triangle — rendu d'un triangle coloré via wgpu4k (GRA-138 + GRA-139).
 *
 * Ouvre une fenêtre Koreos et affiche un triangle RGB animé en continu :
 *   Instance → Surface → Adapter → Device → Pipeline → render loop.
 *
 * Chaque [WindowEvent.RedrawRequested] déclenche une frame :
 *   getCurrentTexture → createView → commandEncoder → renderPass (draw 3 vertices) → submit → present.
 *
 * [WindowEvent.Resized] et [WindowEvent.ScaleFactorChanged] déclenchent une reconfiguration
 * de la surface (nouveau swap chain) via [HelloTriangleApp.handleResize] (GRA-139).
 *
 * Usage : ./gradlew :samples:hello-triangle:run
 */
package io.ygdrasil.koreos.samples.hellotriangle

import ffi.JvmNativeAddress
import io.ygdrasil.koreos.ActiveEventLoop
import io.ygdrasil.koreos.ApplicationHandler
import io.ygdrasil.koreos.EventLoop
import io.ygdrasil.koreos.PhysicalSize
import io.ygdrasil.koreos.WindowAttributes
import io.ygdrasil.koreos.WindowId
import io.ygdrasil.koreos.core.RawWindowHandle
import io.ygdrasil.koreos.core.Window
import io.ygdrasil.koreos.core.WindowEvent
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
import io.ygdrasil.koreos.appkit.bindings.ObjCRuntime
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import kotlinx.coroutines.runBlocking

// ---------------------------------------------------------------------------
// WGSL shaders — triangle RGB à positions codées en dur
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
 * Gestionnaire du sample hello-triangle (GRA-138 + GRA-139).
 *
 * Maintient les ressources wgpu4k entre les frames :
 * - [surface] : surface de rendu liée au CAMetalLayer
 * - [gpuDevice] : device GPU
 * - [pipeline] : pipeline de rendu (vertex + fragment shaders)
 * - [window] : fenêtre Koreos pour `requestRedraw()`
 * - [surfaceFormat] : format de texture négocié à la configuration
 * - [surfaceAlphaMode] : mode alpha utilisé pour la reconfiguration sur resize
 *
 * Le rendu est déclenché à chaque [WindowEvent.RedrawRequested].
 * [aboutToWait] appelle [Window.requestRedraw] pour un rendu continu (~60 fps).
 * [WindowEvent.Resized] et [WindowEvent.ScaleFactorChanged] déclenchent [handleResize].
 */
@OptIn(WGPULowLevelApi::class)
class HelloTriangleApp : ApplicationHandler {

    // Ressources wgpu4k (initialisées dans canCreateSurfaces)
    private var wgpu: WGPU? = null
    private var surface: NativeSurface? = null
    private var gpuDevice: GPUDevice? = null
    private var pipeline: GPURenderPipeline? = null
    private var window: io.ygdrasil.koreos.core.Window? = null
    private var surfaceFormat: GPUTextureFormat = GPUTextureFormat.BGRA8Unorm
    private var surfaceAlphaMode: CompositeAlphaMode = CompositeAlphaMode.Auto

    // Compteur FPS
    private var frameCount = 0
    private var fpsWindowStart = System.currentTimeMillis()

    // ---------------------------------------------------------------------------
    // Initialisation
    // ---------------------------------------------------------------------------

    /**
     * Appelé dès qu'AppKit autorise la création de surfaces de rendu.
     *
     * Séquence :
     * 1. Création de la fenêtre
     * 2. Récupération du CAMetalLayer depuis le NSView
     * 3. WGPU Instance (Metal backend)
     * 4. Surface depuis le CAMetalLayer
     * 5. Adapter + Device
     * 6. Configuration de la Surface
     * 7. Shader module + Pipeline de rendu
     */
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        println("[hello-triangle] canCreateSurfaces — initialisation wgpu4k + pipeline")

        // 1. Fenêtre Koreos
        val win = eventLoop.createWindow(
            WindowAttributes(
                title = "Hello Triangle — Kadre + wgpu4k",
                size = PhysicalSize(width = 800, height = 600),
                visible = true,
                resizable = true,
            )
        )
        window = win
        println("[hello-triangle] Fenêtre créée — windowId=${win.id.value}")

        // 2. CAMetalLayer depuis le NSView
        val handle = win.rawWindowHandle
        if (handle !is RawWindowHandle.AppKit) {
            println("[hello-triangle] Plateforme non supportée : $handle")
            return
        }
        println("[hello-triangle] RawWindowHandle.AppKit — nsView=0x%x  nsWindow=0x%x"
            .format(handle.nsView, handle.nsWindow))

        // Utiliser nsLayer directement (exposé par AppKitWindow) plutôt que [nsView layer],
        // qui peut retourner la CALayer générique d'AppKit si l'ordre setLayer/setWantsLayer
        // était incorrect. nsLayer = metalLayerPtr.address() depuis AppKitWindow.
        val metalLayerAddr = if (handle.nsLayer != 0L) {
            handle.nsLayer
        } else {
            // Fallback : obtenir via [nsView layer] (chemin legacy)
            getMetalLayerFromNsView(handle.nsView)
        }
        if (metalLayerAddr == 0L) {
            println("[hello-triangle] Impossible d'obtenir le CAMetalLayer depuis le NSView")
            return
        }
        println("[hello-triangle] CAMetalLayer = 0x%x".format(metalLayerAddr))

        // 3. WGPU Instance (Metal backend)
        // libWGPU.dylib is bundled inside the wgpu4k-native JAR and must be explicitly
        // extracted + loaded before calling any wgpu function.
        ffi.LibraryLoader.load()
        val wgpuInstance = WGPU.createInstance(WGPUInstanceBackend.Metal)
            ?: run {
                println("[hello-triangle] Échec création WGPU Instance")
                return
            }
        wgpu = wgpuInstance
        println("[hello-triangle] WGPU Instance créée")

        // 4. Surface depuis le CAMetalLayer
        val metalLayerNativeAddr = JvmNativeAddress(MemorySegment.ofAddress(metalLayerAddr))
        val surf: NativeSurface = wgpuInstance.getSurfaceFromMetalLayer(metalLayerNativeAddr)
            ?: run {
                println("[hello-triangle] Échec création Surface depuis CAMetalLayer")
                wgpuInstance.close()
                return
            }
        surface = surf
        println("[hello-triangle] Surface créée")

        // 5. Adapter
        val adapter = wgpuInstance.requestAdapter(surf)
            ?: run {
                println("[hello-triangle] Échec acquisition Adapter")
                surf.close()
                wgpuInstance.close()
                return
            }
        println("[hello-triangle] Adapter — info=${adapter.info}")

        surf.computeSurfaceCapabilities(adapter)
        println("[hello-triangle] Formats supportés   : ${surf.supportedFormats}")
        println("[hello-triangle] Alpha modes supportés: ${surf.supportedAlphaMode}")

        // 6. Device
        val device = runBlocking { adapter.requestDevice() }
            .getOrElse { err ->
                println("[hello-triangle] Échec acquisition Device : $err")
                adapter.close()
                surf.close()
                wgpuInstance.close()
                return
            }
        gpuDevice = device
        println("[hello-triangle] Device créé")

        // 7a. Configuration de la Surface
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
        println("[hello-triangle] Surface configurée — format=$format  size=${innerSize.width}×${innerSize.height}  alpha=$alphaMode")

        // 7b. Shader module
        val shaderModule = device.createShaderModule(ShaderModuleDescriptor(code = TRIANGLE_WGSL))
        println("[hello-triangle] Shader module créé")

        // 7c. Pipeline de rendu
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
        println("[hello-triangle] Pipeline de rendu créé — prêt à dessiner (GRA-138)")

        // Libérer le shader module (il n'est plus nécessaire après la création du pipeline)
        shaderModule.close()
        adapter.close()

        fpsWindowStart = System.currentTimeMillis()
    }

    // ---------------------------------------------------------------------------
    // Boucle de rendu
    // ---------------------------------------------------------------------------

    /**
     * Appelé lorsque la boucle d'événements est sur le point de se mettre en attente.
     *
     * Demande un redraw continu pour maintenir ~60 fps.
     */
    override fun aboutToWait(eventLoop: ActiveEventLoop) {
        window?.requestRedraw()
    }

    /**
     * Événements fenêtre.
     *
     * - [WindowEvent.RedrawRequested] : rend une frame triangle RGB
     * - [WindowEvent.Resized] : reconfigure la surface avec la nouvelle taille physique (GRA-139)
     * - [WindowEvent.ScaleFactorChanged] : reconfigure la surface depuis innerSize (GRA-139)
     * - [WindowEvent.CloseRequested] : libère les ressources et quitte
     */
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        when (event) {
            is WindowEvent.RedrawRequested -> renderFrame()
            is WindowEvent.Resized -> {
                println("[hello-triangle] Resized → ${event.size.width}×${event.size.height}")
                handleResize(event.size.width, event.size.height)
            }
            is WindowEvent.ScaleFactorChanged -> {
                // Reconfigure à partir de la taille physique actuelle de la fenêtre
                val win = window ?: return
                val inner = win.innerSize
                println("[hello-triangle] ScaleFactorChanged scaleFactor=${event.factor} → reconfigure ${inner.width}×${inner.height}")
                handleResize(inner.width, inner.height)
            }
            is WindowEvent.CloseRequested -> {
                println("[hello-triangle] CloseRequested — libération des ressources")
                releaseResources()
                eventLoop.exit()
            }
            else -> { /* ignorer */ }
        }
    }

    /**
     * Reconfigure la surface WebGPU avec la nouvelle taille en pixels physiques.
     *
     * Appelé sur [WindowEvent.Resized] et [WindowEvent.ScaleFactorChanged].
     * Gère silencieusement les cas où les ressources wgpu ne sont pas encore initialisées.
     *
     * @param width  Nouvelle largeur en pixels physiques.
     * @param height Nouvelle hauteur en pixels physiques.
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
        println("[hello-triangle] Surface reconfigurée — ${width}×${height}")
    }

    /**
     * Rendu d'une frame.
     *
     * Séquence standard WebGPU :
     * 1. `getCurrentTexture()` → vérification du statut
     * 2. `createView()` → vue de la texture de présentation
     * 3. `createCommandEncoder()` → encodeur de commandes
     * 4. `beginRenderPass(...)` → passe de rendu (clear noir + draw 3 vertices)
     * 5. `end()` → fin de la passe
     * 6. `finish()` → command buffer
     * 7. `queue.submit(...)` → soumission au GPU
     * 8. `present()` → affichage
     */
    private fun renderFrame() {
        val surf = surface ?: return
        val device = gpuDevice ?: return
        val pipe = pipeline ?: return

        // 1. Texture de présentation
        //
        // NOTE — Compatibilité wgpu-native 0.25+ / wgpu4k 0.1.1 :
        //   Ancien API :  Success(0) Timeout(1)     Outdated(2) Lost(3) OutOfMemory(4) DeviceLost(5)
        //   Nouveau API : SuccessOptimal(0) SuccessSuboptimal(1) Timeout(2) Outdated(3) ...
        //
        //   wgpu4k 0.1.1 mappe les valeurs brutes de l'ancien API :
        //     rawStatus=0 → success    (SuccessOptimal   → rendu OK)
        //     rawStatus=1 → timeout    (SuccessSuboptimal→ texture VALIDE, mais suboptimale)
        //     rawStatus=2 → outdated   (vrai Timeout     → aucun drawable)
        //     rawStatus=3 → lost       (Outdated         → reconfigurer)
        //
        //   ⇒ Traiter `timeout` (=successSuboptimal) comme `success` : la texture est valide.
        val surfaceTexture = surf.getCurrentTexture()
        when (surfaceTexture.status) {
            // lost (3) dans wgpu4k = Outdated (3) dans nouveau API → reconfigurer la surface
            SurfaceTextureStatus.lost -> {
                surfaceTexture.texture.close()
                val win = window
                if (win != null) {
                    val inner = win.innerSize
                    handleResize(inner.width, inner.height)
                }
                return
            }
            // outdated (2) dans wgpu4k = Timeout (2) dans nouveau API → aucun drawable, skip
            // (compatible avec ancien API où outdated=2 → reconfigure)
            SurfaceTextureStatus.outdated -> {
                surfaceTexture.texture.close()
                val win = window
                if (win != null) {
                    val inner = win.innerSize
                    handleResize(inner.width, inner.height)
                }
                return
            }
            // Erreurs terminales
            SurfaceTextureStatus.outOfMemory,
            SurfaceTextureStatus.deviceLost -> {
                println("[hello-triangle] getCurrentTexture status=${surfaceTexture.status} — erreur terminale")
                surfaceTexture.texture.close()
                return
            }
            // success (0) = SuccessOptimal, timeout (1) = SuccessSuboptimal → texture VALIDE
            SurfaceTextureStatus.success,
            SurfaceTextureStatus.timeout -> { /* continuer le rendu */ }
        }
        val texture = surfaceTexture.texture

        // 2. Vue de la texture
        val textureView = texture.createView(null)

        // 3. Encodeur de commandes
        val encoder = device.createCommandEncoder()

        // 4. Passe de rendu
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

        // 5. Soumission
        val commandBuffer = encoder.finish()
        device.queue.submit(listOf(commandBuffer))

        // 6. Présentation
        surf.present()

        // 7. Libération des ressources temporaires
        textureView.close()
        encoder.close()

        // Compteur FPS
        frameCount++
        val now = System.currentTimeMillis()
        val elapsed = now - fpsWindowStart
        if (elapsed >= 1_000L) {
            val fps = frameCount * 1_000.0 / elapsed
            println("[hello-triangle] FPS : %.1f".format(fps))
            frameCount = 0
            fpsWindowStart = now
        }
    }

    // ---------------------------------------------------------------------------
    // Nettoyage
    // ---------------------------------------------------------------------------

    /**
     * Libère toutes les ressources wgpu4k.
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
        println("[hello-triangle] Ressources libérées")
    }

    // ---------------------------------------------------------------------------
    // Utilitaire natif
    // ---------------------------------------------------------------------------

    /**
     * Récupère le pointeur natif du `CAMetalLayer` attaché à un `NSView`.
     *
     * Appel ObjC : `[nsView layer]` via Panama FFM downcall sur `objc_msgSend`.
     * Le NSView doit déjà avoir `wantsLayer = YES` et un `CAMetalLayer` assigné
     * (ce que [io.ygdrasil.koreos.appkit.AppKitWindow] garantit).
     *
     * @param nsViewPtr Adresse mémoire du pointeur ObjC vers le NSView.
     * @return Adresse du CAMetalLayer, ou 0 en cas d'échec.
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
// Point d'entrée
// ---------------------------------------------------------------------------

/**
 * Point d'entrée du sample hello-triangle.
 *
 * Doit être exécuté depuis le thread principal macOS (garanti par Gradle via
 * `-XstartOnFirstThread` dans [build.gradle.kts]).
 */
fun main(args: Array<String>) {
    // Mode capture offscreen GPU (Redmine #88) : `--capture <path>` rend le triangle dans
    // une texture, relit le framebuffer, écrit un PNG, puis quitte — sans ouvrir de fenêtre.
    val captureIndex = args.indexOf("--capture")
    if (captureIndex >= 0) {
        val path = args.getOrNull(captureIndex + 1)
            ?: error("--capture requiert un chemin de fichier : --capture <path>")
        captureFrame(path)
        return
    }

    println("[hello-triangle] Démarrage — Kadre + wgpu4k triangle RGB (GRA-138)")
    EventLoop().runApp(HelloTriangleApp())
    println("[hello-triangle] Terminé")
}
