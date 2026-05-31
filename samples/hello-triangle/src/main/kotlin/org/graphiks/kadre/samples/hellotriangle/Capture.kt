/**
 * Mode capture offscreen GPU pour le sample hello-triangle (Redmine #88).
 *
 * Rend le triangle RGB dans une texture offscreen (sans fenêtre visible), relit le
 * framebuffer via un buffer de readback, et écrit le résultat dans un fichier PNG, puis
 * quitte. Conçu pour être exécutable en CI (headless), bien qu'il exige un GPU réel.
 *
 * Séquence :
 *   Instance → CAMetalLayer offscreen → Surface → Adapter → Device → Texture (RGBA8Unorm)
 *   → render pass (clear noir + triangle) → copyTextureToBuffer → mapAsync → lecture FFM
 *   → reconstruction BufferedImage → ImageIO PNG.
 *
 * Limitation connue (wgpu4k 0.1.1) :
 *   `WGPU.requestAdapter(surface, ...)` exige une surface NON-nulle (le handle de la surface
 *   est déréférencé sans condition côté natif). Un adapter purement « headless » sans aucune
 *   surface n'est donc pas exposé par l'API haut-niveau. On crée donc un `CAMetalLayer`
 *   offscreen (sans NSView/fenêtre) uniquement pour satisfaire `requestAdapter`, puis on rend
 *   exclusivement dans une texture offscreen — aucune fenêtre n'est ouverte.
 *
 * Polling natif : sur wgpu natif, `mapAsync` ne se résout pas sans faire avancer le device.
 *   wgpu4k 0.1.1 appelle `wgpuDevicePoll(device, wait=true, null)` en interne dans `mapAsync`,
 *   donc `runBlocking { buffer.mapAsync(...) }` se résout sans poll manuel.
 *
 * Usage : ./gradlew :samples:hello-triangle:run --args="--capture /tmp/htri-capture.png"
 */
package org.graphiks.kadre.samples.hellotriangle

import ffi.JvmNativeAddress
import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.ColorTargetState
import io.ygdrasil.webgpu.Extent3D
import io.ygdrasil.webgpu.FragmentState
import io.ygdrasil.webgpu.GPUBufferUsage
import io.ygdrasil.webgpu.GPULoadOp
import io.ygdrasil.webgpu.GPUMapMode
import io.ygdrasil.webgpu.GPUStoreOp
import io.ygdrasil.webgpu.GPUTextureFormat
import io.ygdrasil.webgpu.GPUTextureUsage
import io.ygdrasil.webgpu.PrimitiveState
import io.ygdrasil.webgpu.RenderPassColorAttachment
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.TexelCopyBufferInfo
import io.ygdrasil.webgpu.TexelCopyTextureInfo
import io.ygdrasil.webgpu.TextureDescriptor
import io.ygdrasil.webgpu.VertexState
import io.ygdrasil.webgpu.WGPU
import io.ygdrasil.webgpu.WGPUInstanceBackend
import io.ygdrasil.webgpu.WGPULowLevelApi
import kotlinx.coroutines.runBlocking
import java.awt.image.BufferedImage
import java.io.File
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import javax.imageio.ImageIO
import kotlin.math.ceil

/** Dimensions fixes de la capture offscreen. */
internal const val CAPTURE_WIDTH = 800
internal const val CAPTURE_HEIGHT = 600

/** Alignement WebGPU : `bytesPerRow` doit être un multiple de 256 octets. */
private const val BYTES_PER_ROW_ALIGNMENT = 256

/**
 * Crée un `CAMetalLayer` offscreen via le runtime ObjC (sans NSView ni fenêtre).
 *
 * Équivalent ObjC : `[[CAMetalLayer alloc] init]`. Le layer est dimensionné via
 * `drawableSize` mais cela n'a pas d'impact ici : le rendu cible une texture offscreen
 * indépendante, le layer ne sert qu'à satisfaire `WGPU.requestAdapter` (qui exige une surface).
 *
 * @return Adresse native du `CAMetalLayer`, ou 0 en cas d'échec.
 */
private fun createOffscreenMetalLayer(): Long {
    val cls = ObjCRuntime.getClass("CAMetalLayer")
    if (cls.address() == 0L) return 0L
    val alloc = ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, ObjCRuntime.sel("alloc")) as MemorySegment
    if (alloc.address() == 0L) return 0L
    val layer = ObjCRuntime.msgSend(ValueLayout.ADDRESS, alloc, ObjCRuntime.sel("init")) as MemorySegment
    return layer.address()
}

/**
 * Exécute le mode capture : rend le triangle dans une texture offscreen et écrit un PNG.
 *
 * @param path Chemin du fichier PNG à écrire.
 */
@OptIn(WGPULowLevelApi::class)
fun captureFrame(path: String) {
    println("[hello-triangle] Mode capture offscreen — cible=$path (${CAPTURE_WIDTH}×${CAPTURE_HEIGHT})")

    ffi.LibraryLoader.load()
    val os = System.getProperty("os.name", "").lowercase()
    when {
        os.contains("mac")   -> captureMacOs(path)
        os.contains("win")   -> captureWindows(path)
        os.contains("nux")   -> captureLinux(path)
        else -> error("Mode capture non supporté sur cet OS : '$os' (macOS, Windows, Linux).")
    }
}

/**
 * Capture macOS : Instance Metal + CAMetalLayer offscreen (sans fenêtre, cf. KDoc d'en-tête),
 * puis pipeline commun [renderSurfaceToPng].
 */
@OptIn(WGPULowLevelApi::class)
private fun captureMacOs(path: String) {
    val instance = WGPU.createInstance(WGPUInstanceBackend.Metal)
        ?: error("Échec création WGPU Instance (Metal)")

    val metalLayerAddr = createOffscreenMetalLayer()
    if (metalLayerAddr == 0L) {
        instance.close()
        error("Impossible de créer un CAMetalLayer offscreen")
    }
    val surface = instance.getSurfaceFromMetalLayer(JvmNativeAddress(MemorySegment.ofAddress(metalLayerAddr)))
        ?: run {
            instance.close()
            error("Échec création Surface depuis CAMetalLayer offscreen")
        }
    renderSurfaceToPng(instance, surface, path)
}

/**
 * Pipeline commun de capture : depuis une [instance] et une [surface] déjà créées par le
 * code spécifique à l'OS, acquiert adapter+device, rend le triangle dans une texture
 * offscreen RGBA8, relit le framebuffer et écrit le PNG. Libère toutes les ressources.
 */
@OptIn(WGPULowLevelApi::class)
internal fun renderSurfaceToPng(
    instance: WGPU,
    surface: io.ygdrasil.webgpu.NativeSurface,
    path: String,
) {
    // 3. Adapter + Device
    val adapter = instance.requestAdapter(surface)
        ?: run {
            surface.close(); instance.close()
            error("Échec acquisition Adapter (headless requiert une surface — cf. KDoc)")
        }
    println("[hello-triangle] Adapter — info=${adapter.info}")
    val device = runBlocking { adapter.requestDevice() }
        .getOrElse { err ->
            adapter.close(); surface.close(); instance.close()
            error("Échec acquisition Device : $err")
        }

    // 4. Texture offscreen RGBA8Unorm (RenderAttachment + CopySrc)
    val texture = device.createTexture(
        TextureDescriptor(
            size = Extent3D(CAPTURE_WIDTH.toUInt(), CAPTURE_HEIGHT.toUInt(), 1u),
            format = GPUTextureFormat.RGBA8Unorm,
            usage = setOf(GPUTextureUsage.RenderAttachment, GPUTextureUsage.CopySrc),
        )
    )

    // 5. Shader + pipeline (réutilise le WGSL du sample, cible RGBA8Unorm)
    val shaderModule = device.createShaderModule(ShaderModuleDescriptor(code = TRIANGLE_WGSL))
    val pipeline = device.createRenderPipeline(
        RenderPipelineDescriptor(
            vertex = VertexState(module = shaderModule, entryPoint = "vs_main"),
            primitive = PrimitiveState(),
            fragment = FragmentState(
                module = shaderModule,
                entryPoint = "fs_main",
                targets = listOf(ColorTargetState(format = GPUTextureFormat.RGBA8Unorm)),
            ),
        )
    )
    shaderModule.close()

    // 6. Buffer de readback — bytesPerRow aligné à 256 octets
    val alignedBytesPerRow = (ceil(CAPTURE_WIDTH * 4.0 / BYTES_PER_ROW_ALIGNMENT) * BYTES_PER_ROW_ALIGNMENT).toInt()
    val bufferSize = (alignedBytesPerRow.toLong() * CAPTURE_HEIGHT).toULong()
    val readbackBuffer = device.createBuffer(
        BufferDescriptor(
            size = bufferSize,
            usage = setOf(GPUBufferUsage.MapRead, GPUBufferUsage.CopyDst),
            mappedAtCreation = false,
        )
    )

    // 7. Render pass : clear noir + triangle
    val textureView = texture.createView(null)
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
    renderPass.setPipeline(pipeline)
    renderPass.draw(3u, 1u, 0u, 0u)
    renderPass.end()

    // 8. Copie texture → buffer de readback
    encoder.copyTextureToBuffer(
        TexelCopyTextureInfo(texture = texture, mipLevel = 0u),
        TexelCopyBufferInfo(
            buffer = readbackBuffer,
            offset = 0u,
            bytesPerRow = alignedBytesPerRow.toUInt(),
            rowsPerImage = CAPTURE_HEIGHT.toUInt(),
        ),
        Extent3D(CAPTURE_WIDTH.toUInt(), CAPTURE_HEIGHT.toUInt(), 1u),
    )
    device.queue.submit(listOf(encoder.finish()))

    // 9. Map + lecture (mapAsync poll le device en interne sur wgpu4k 0.1.1)
    runBlocking { readbackBuffer.mapAsync(setOf(GPUMapMode.Read), 0u, bufferSize) }
        .getOrElse { err -> error("Échec mapAsync du buffer de readback : $err") }
    val mapped = readbackBuffer.getMappedRange(0u, bufferSize)
    val rawPointer = mapped.rawPointer.toLong()
    val size = mapped.size.toLong()
    val bytes = MemorySegment.ofAddress(rawPointer)
        .reinterpret(size)
        .toArray(ValueLayout.JAVA_BYTE)

    // 10. Reconstruction de l'image (RGBA → ARGB, retrait du padding par ligne)
    val image = BufferedImage(CAPTURE_WIDTH, CAPTURE_HEIGHT, BufferedImage.TYPE_INT_ARGB)
    for (y in 0 until CAPTURE_HEIGHT) {
        val rowStart = y * alignedBytesPerRow
        for (x in 0 until CAPTURE_WIDTH) {
            val i = rowStart + x * 4
            val r = bytes[i].toInt() and 0xFF
            val g = bytes[i + 1].toInt() and 0xFF
            val b = bytes[i + 2].toInt() and 0xFF
            val a = bytes[i + 3].toInt() and 0xFF
            image.setRGB(x, y, (a shl 24) or (r shl 16) or (g shl 8) or b)
        }
    }
    readbackBuffer.unmap()

    val outFile = File(path)
    outFile.parentFile?.mkdirs()
    ImageIO.write(image, "png", outFile)
    println("[hello-triangle] PNG écrit : ${outFile.absolutePath} (${outFile.length()} octets)")

    // 11. Libération des ressources
    readbackBuffer.close()
    textureView.close()
    encoder.close()
    pipeline.close()
    texture.close()
    device.close()
    adapter.close()
    surface.close()
    instance.close()
    println("[hello-triangle] Capture terminée — ressources libérées")
}
