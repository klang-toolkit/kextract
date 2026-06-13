/**
 * Offscreen GPU capture mode for the hello-triangle sample.
 *
 * Renders the RGB triangle into an offscreen texture (no visible window), reads back the
 * framebuffer via a readback buffer, and writes the result to a PNG file, then
 * exits. Designed to be runnable in CI (headless), though it requires a real GPU.
 *
 * Sequence:
 *   Instance → offscreen CAMetalLayer → Surface → Adapter → Device → Texture (RGBA8Unorm)
 *   → render pass (black clear + triangle) → copyTextureToBuffer → mapAsync → FFM read
 *   → BufferedImage reconstruction → ImageIO PNG.
 *
 * Known limitation (wgpu4k 0.1.1):
 *   `WGPU.requestAdapter(surface, ...)` requires a NON-null surface (the surface handle
 *   is dereferenced unconditionally on the native side). A purely "headless" adapter without any
 *   surface is therefore not exposed by the high-level API. So we create an offscreen
 *   `CAMetalLayer` (without NSView/window) solely to satisfy `requestAdapter`, then we render
 *   exclusively into an offscreen texture — no window is opened.
 *
 * Native polling: on native wgpu, `mapAsync` does not resolve without advancing the device.
 *   wgpu4k 0.1.1 calls `wgpuDevicePoll(device, wait=true, null)` internally inside `mapAsync`,
 *   so `runBlocking { buffer.mapAsync(...) }` resolves without manual polling.
 *
 * Usage: ./gradlew :samples:hello-triangle:run --args="--capture /tmp/htri-capture.png"
 */
package org.graphiks.kadre.samples.hellotriangle

import ffi.JvmNativeAddress
import org.graphiks.kffi.objc.ObjCRuntime
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

/** Fixed dimensions of the offscreen capture. */
internal const val CAPTURE_WIDTH = 800
internal const val CAPTURE_HEIGHT = 600

/** WebGPU alignment: `bytesPerRow` must be a multiple of 256 bytes. */
private const val BYTES_PER_ROW_ALIGNMENT = 256

/**
 * Creates an offscreen `CAMetalLayer` via the ObjC runtime (without NSView or window).
 *
 * ObjC equivalent: `[[CAMetalLayer alloc] init]`. The layer is sized via
 * `drawableSize` but this has no impact here: rendering targets an independent offscreen
 * texture, the layer only serves to satisfy `WGPU.requestAdapter` (which requires a surface).
 *
 * @return Native address of the `CAMetalLayer`, or 0 on failure.
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
 * Runs capture mode: renders the triangle into an offscreen texture and writes a PNG.
 *
 * @param path Path of the PNG file to write.
 */
@OptIn(WGPULowLevelApi::class)
fun captureFrame(path: String) {
    println("[hello-triangle] Offscreen capture mode — target=$path (${CAPTURE_WIDTH}×${CAPTURE_HEIGHT})")

    ffi.LibraryLoader.load()
    val os = System.getProperty("os.name", "").lowercase()
    when {
        os.contains("mac")   -> captureMacOs(path)
        os.contains("win")   -> captureWindows(path)
        os.contains("nux")   -> captureLinux(path)
        else -> error("Capture mode not supported on this OS: '$os' (macOS, Windows, Linux).")
    }
}

/**
 * macOS capture: Metal Instance + offscreen CAMetalLayer (without window, cf. header KDoc),
 * then the common [renderSurfaceToPng] pipeline.
 */
@OptIn(WGPULowLevelApi::class)
private fun captureMacOs(path: String) {
    val instance = WGPU.createInstance(WGPUInstanceBackend.Metal)
        ?: error("Failed to create WGPU Instance (Metal)")

    val metalLayerAddr = createOffscreenMetalLayer()
    if (metalLayerAddr == 0L) {
        instance.close()
        error("Unable to create an offscreen CAMetalLayer")
    }
    val surface = instance.getSurfaceFromMetalLayer(JvmNativeAddress(MemorySegment.ofAddress(metalLayerAddr)))
        ?: run {
            instance.close()
            error("Failed to create Surface from offscreen CAMetalLayer")
        }
    renderSurfaceToPng(instance, surface, path)
}

/**
 * Common capture pipeline: from an [instance] and a [surface] already created by the
 * OS-specific code, acquires adapter+device, renders the triangle into an offscreen
 * RGBA8 texture, reads back the framebuffer and writes the PNG. Releases all resources.
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
            error("Failed to acquire Adapter (headless requires a surface — see KDoc)")
        }
    println("[hello-triangle] Adapter — info=${adapter.info}")
    val device = runBlocking { adapter.requestDevice() }
        .getOrElse { err ->
            adapter.close(); surface.close(); instance.close()
            error("Failed to acquire Device: $err")
        }

    // 4. Texture offscreen RGBA8Unorm (RenderAttachment + CopySrc)
    val texture = device.createTexture(
        TextureDescriptor(
            size = Extent3D(CAPTURE_WIDTH.toUInt(), CAPTURE_HEIGHT.toUInt(), 1u),
            format = GPUTextureFormat.RGBA8Unorm,
            usage = setOf(GPUTextureUsage.RenderAttachment, GPUTextureUsage.CopySrc),
        )
    )

    // 5. Shader + pipeline (reuses the sample's WGSL, targets RGBA8Unorm)
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

    // 6. Readback buffer — bytesPerRow aligned to 256 bytes
    val alignedBytesPerRow = (ceil(CAPTURE_WIDTH * 4.0 / BYTES_PER_ROW_ALIGNMENT) * BYTES_PER_ROW_ALIGNMENT).toInt()
    val bufferSize = (alignedBytesPerRow.toLong() * CAPTURE_HEIGHT).toULong()
    val readbackBuffer = device.createBuffer(
        BufferDescriptor(
            size = bufferSize,
            usage = setOf(GPUBufferUsage.MapRead, GPUBufferUsage.CopyDst),
            mappedAtCreation = false,
        )
    )

    // 7. Render pass: black clear + triangle
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

    // 8. Copy texture → readback buffer
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

    // 9. Map + read (mapAsync polls the device internally on wgpu4k 0.1.1)
    runBlocking { readbackBuffer.mapAsync(setOf(GPUMapMode.Read), 0u, bufferSize) }
        .getOrElse { err -> error("Failed to mapAsync readback buffer: $err") }
    val mapped = readbackBuffer.getMappedRange(0u, bufferSize)
    val rawPointer = mapped.rawPointer.toLong()
    val size = mapped.size.toLong()
    val bytes = MemorySegment.ofAddress(rawPointer)
        .reinterpret(size)
        .toArray(ValueLayout.JAVA_BYTE)

    // 10. Image reconstruction (RGBA → ARGB, removal of per-row padding)
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
    println("[hello-triangle] PNG written: ${outFile.absolutePath} (${outFile.length()} bytes)")

    // 11. Resource release
    readbackBuffer.close()
    textureView.close()
    encoder.close()
    pipeline.close()
    texture.close()
    device.close()
    adapter.close()
    surface.close()
    instance.close()
    println("[hello-triangle] Capture finished — resources released")
}
