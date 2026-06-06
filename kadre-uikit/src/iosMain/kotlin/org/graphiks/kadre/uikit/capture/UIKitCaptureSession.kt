@file:OptIn(ExperimentalForeignApi::class)

package org.graphiks.kadre.uikit.capture

import kotlinx.cinterop.ByteVarOf
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.get
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.CaptureConfig
import org.graphiks.kadre.core.capture.CaptureError
import org.graphiks.kadre.core.capture.CaptureFrame
import org.graphiks.kadre.core.capture.CaptureSession
import org.graphiks.kadre.core.capture.CaptureSource
import org.graphiks.kadre.core.capture.PixelFormat
import platform.CoreMedia.CMSampleBufferRef
import platform.CoreMedia.CMSampleBufferGetImageBuffer
import platform.CoreVideo.CVPixelBufferRef
import platform.CoreVideo.CVPixelBufferGetBaseAddress
import platform.CoreVideo.CVPixelBufferGetBytesPerRow
import platform.CoreVideo.CVPixelBufferGetHeight
import platform.CoreVideo.CVPixelBufferGetWidth
import platform.CoreVideo.CVPixelBufferLockBaseAddress
import platform.CoreVideo.CVPixelBufferUnlockBaseAddress
import platform.CoreVideo.kCVPixelBufferLock_ReadOnly
import platform.Foundation.NSDate
import platform.Foundation.NSError
import platform.Foundation.NSTimeInterval
import platform.ReplayKit.RPScreenRecorder
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import platform.Foundation.NSProcessInfo

class UIKitCaptureSession(
    source: CaptureSource.Display,
    config: CaptureConfig,
) : CaptureSession(source, config) {

    private var job: Job? = null

    init {
        job = CoroutineScope(Dispatchers.Default).launch {
            startCapture()
        }
    }

    private suspend fun startCapture() = suspendCancellableCoroutine<Unit> { cont ->
        RPScreenRecorder.sharedRecorder().startCaptureWithHandler(
            captureHandler = { sampleBuffer: CMSampleBufferRef?, _: Long, error: NSError? ->
                if (error != null) {
                    println("[UIKitCaptureSession] Capture error: ${error.localizedDescription}")
                    return@startCaptureWithHandler
                }
                val buffer = sampleBuffer ?: return@startCaptureWithHandler
                val pixelBuffer = CMSampleBufferGetImageBuffer(buffer)
                if (pixelBuffer != null) {
                    processFrame(pixelBuffer)
                }
            },
            completionHandler = { error: NSError? ->
                if (error != null) {
                    cont.resumeWithException(
                        CaptureError.Internal(Exception(error.localizedDescription))
                    )
                } else {
                    cont.resume(Unit)
                }
            },
        )
    }

    private fun processFrame(pixelBuffer: CVPixelBufferRef) {
        CVPixelBufferLockBaseAddress(pixelBuffer, kCVPixelBufferLock_ReadOnly)
        try {
            val baseAddress = CVPixelBufferGetBaseAddress(pixelBuffer) ?: return
            val width = CVPixelBufferGetWidth(pixelBuffer).toInt()
            val height = CVPixelBufferGetHeight(pixelBuffer).toInt()
            val stride = CVPixelBufferGetBytesPerRow(pixelBuffer).toInt()
            val dataSize = height * stride

            val data = ByteArray(dataSize)
            val src = baseAddress.reinterpret<ByteVarOf<Byte>>()
            for (i in 0 until dataSize) {
                data[i] = src[i]
            }

            val frame = CaptureFrame(
                size = PhysicalSize(width, height),
                format = PixelFormat.BGRA8,
                stride = stride,
                data = data,
                timestampNanos = (NSProcessInfo.processInfo.systemUptime * 1_000_000_000).toLong(),
            )
            _frames.tryEmit(frame)
        } finally {
            CVPixelBufferUnlockBaseAddress(pixelBuffer, kCVPixelBufferLock_ReadOnly)
        }
    }

    override fun close() {
        job?.cancel()
        RPScreenRecorder.sharedRecorder().stopCaptureWithHandler { error ->
            if (error != null) {
                println("[UIKitCaptureSession] Stop error: ${error.localizedDescription}")
            }
        }
    }
}
