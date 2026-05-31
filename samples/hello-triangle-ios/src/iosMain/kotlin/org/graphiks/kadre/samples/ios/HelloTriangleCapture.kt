/**
 * iOS offscreen GPU capture — Kotlin/Native + wgpu4k Metal.
 *
 * Renders the RGB triangle into an offscreen texture (an offscreen CAMetalLayer serves
 * only to satisfy requestAdapter, as on macOS), reads back the framebuffer via
 * a readback buffer, and returns the RGBA bytes. No window/UIView.
 */
@file:OptIn(ExperimentalForeignApi::class, WGPULowLevelApi::class)

package org.graphiks.kadre.samples.ios

import ffi.NativeAddress
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
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.get
import kotlinx.cinterop.objcPtr
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toLong
import kotlinx.coroutines.runBlocking
import platform.CoreGraphics.CGSizeMake
import platform.QuartzCore.CAMetalLayer
import kotlin.math.ceil

const val CAPTURE_WIDTH = 800
const val CAPTURE_HEIGHT = 600
private const val BYTES_PER_ROW_ALIGNMENT = 256

private val TRIANGLE_WGSL = """
struct VertexOutput {
    @builtin(position) position: vec4<f32>,
    @location(0) color: vec3<f32>,
};
@vertex
fn vs_main(@builtin(vertex_index) vertexIndex: u32) -> VertexOutput {
    var positions = array<vec2<f32>, 3>(
        vec2<f32>( 0.0,  0.5), vec2<f32>(-0.5, -0.5), vec2<f32>( 0.5, -0.5),
    );
    var colors = array<vec3<f32>, 3>(
        vec3<f32>(1.0, 0.0, 0.0), vec3<f32>(0.0, 1.0, 0.0), vec3<f32>(0.0, 0.0, 1.0),
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

/** Captured image: RGBA bytes (without padding) + dimensions. */
class CaptureImage(val width: Int, val height: Int, val rgba: ByteArray)

/**
 * Renders the triangle offscreen and returns the RGBA image via GPU readback.
 */
fun captureTriangle(): CaptureImage {
    val instance = WGPU.createInstance(WGPUInstanceBackend.Metal)
        ?: error("Échec création WGPU Instance (Metal)")

    // Offscreen CAMetalLayer, configured (Metal device + drawableSize) so that wgpu
    // finds a compatible adapter on the simulator. Rendering targets a texture.
    val layer = CAMetalLayer()
    layer.setDrawableSize(CGSizeMake(CAPTURE_WIDTH.toDouble(), CAPTURE_HEIGHT.toDouble()))
    val surface = instance.getSurfaceFromMetalLayer(NativeAddress(layer.objcPtr().toLong().toCPointer<ByteVar>()!!))
        ?: run { instance.close(); error("Échec création Surface depuis CAMetalLayer") }

    val adapter = instance.requestAdapter(surface)
        ?: run { surface.close(); instance.close(); error("Échec acquisition Adapter") }
    val device = runBlocking { adapter.requestDevice() }
        .getOrElse { adapter.close(); surface.close(); instance.close(); error("Échec Device : $it") }

    val texture = device.createTexture(
        TextureDescriptor(
            size = Extent3D(CAPTURE_WIDTH.toUInt(), CAPTURE_HEIGHT.toUInt(), 1u),
            format = GPUTextureFormat.RGBA8Unorm,
            usage = setOf(GPUTextureUsage.RenderAttachment, GPUTextureUsage.CopySrc),
        )
    )
    val shaderModule = device.createShaderModule(ShaderModuleDescriptor(code = TRIANGLE_WGSL))
    val pipeline = device.createRenderPipeline(
        RenderPipelineDescriptor(
            vertex = VertexState(module = shaderModule, entryPoint = "vs_main"),
            primitive = PrimitiveState(),
            fragment = FragmentState(
                module = shaderModule, entryPoint = "fs_main",
                targets = listOf(ColorTargetState(format = GPUTextureFormat.RGBA8Unorm)),
            ),
        )
    )
    shaderModule.close()

    val alignedBytesPerRow = (ceil(CAPTURE_WIDTH * 4.0 / BYTES_PER_ROW_ALIGNMENT) * BYTES_PER_ROW_ALIGNMENT).toInt()
    val bufferSize = (alignedBytesPerRow.toLong() * CAPTURE_HEIGHT).toULong()
    val readback = device.createBuffer(
        BufferDescriptor(
            size = bufferSize,
            usage = setOf(GPUBufferUsage.MapRead, GPUBufferUsage.CopyDst),
            mappedAtCreation = false,
        )
    )

    val view = texture.createView(null)
    val encoder = device.createCommandEncoder()
    val pass = encoder.beginRenderPass(
        RenderPassDescriptor(
            colorAttachments = listOf(
                RenderPassColorAttachment(
                    view = view,
                    loadOp = GPULoadOp.Clear,
                    storeOp = GPUStoreOp.Store,
                    clearValue = Color(0.0, 0.0, 0.0, 1.0),
                )
            )
        )
    )
    pass.setPipeline(pipeline)
    pass.draw(3u, 1u, 0u, 0u)
    pass.end()

    encoder.copyTextureToBuffer(
        TexelCopyTextureInfo(texture = texture, mipLevel = 0u),
        TexelCopyBufferInfo(
            buffer = readback, offset = 0u,
            bytesPerRow = alignedBytesPerRow.toUInt(), rowsPerImage = CAPTURE_HEIGHT.toUInt(),
        ),
        Extent3D(CAPTURE_WIDTH.toUInt(), CAPTURE_HEIGHT.toUInt(), 1u),
    )
    device.queue.submit(listOf(encoder.finish()))

    runBlocking { readback.mapAsync(setOf(GPUMapMode.Read), 0u, bufferSize) }
        .getOrElse { error("Échec mapAsync : $it") }
    val mapped = readback.getMappedRange(0u, bufferSize)
    val ptr = mapped.rawPointer.toLong().toCPointer<ByteVar>()!!

    val rgba = ByteArray(CAPTURE_WIDTH * CAPTURE_HEIGHT * 4)
    for (y in 0 until CAPTURE_HEIGHT) {
        val rowStart = y * alignedBytesPerRow
        for (x in 0 until CAPTURE_WIDTH * 4) {
            rgba[y * CAPTURE_WIDTH * 4 + x] = ptr[(rowStart + x).toLong()]
        }
    }
    readback.unmap()

    readback.close(); view.close(); encoder.close(); pipeline.close()
    texture.close(); device.close(); adapter.close(); surface.close(); instance.close()
    return CaptureImage(CAPTURE_WIDTH, CAPTURE_HEIGHT, rgba)
}
