package org.graphiks.kadre.android.capture

import android.content.Context
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.Image
import android.media.ImageReader
import android.media.projection.MediaProjection
import android.os.Handler
import android.os.HandlerThread
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.CaptureConfig
import org.graphiks.kadre.core.capture.CaptureError
import org.graphiks.kadre.core.capture.CaptureFrame
import org.graphiks.kadre.core.capture.CaptureSession
import org.graphiks.kadre.core.capture.CaptureSource
import org.graphiks.kadre.core.capture.PixelFormat

class AndroidCaptureSession(
    source: CaptureSource.Display,
    config: CaptureConfig,
    private val mediaProjection: MediaProjection,
    private val context: Context,
) : CaptureSession(source, config) {

    private val imageReader: ImageReader
    private val virtualDisplay: VirtualDisplay
    private val handlerThread: HandlerThread

    @Volatile
    private var closed = false

    init {
        val display = (context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager)
            .getDisplay(source.id.toInt())
            ?: throw CaptureError.NoSuchSource(source)
        val metrics = android.util.DisplayMetrics()
        @Suppress("DEPRECATION")
        display.getRealMetrics(metrics)
        val width = metrics.widthPixels
        val height = metrics.heightPixels

        imageReader = ImageReader.newInstance(
            width,
            height,
            android.graphics.PixelFormat.RGBA_8888,
            2,
        )

        handlerThread = HandlerThread("KadreCapture").apply { start() }
        val handler = Handler(handlerThread.looper)

        imageReader.setOnImageAvailableListener({ reader ->
            if (closed) return@setOnImageAvailableListener
            val image = reader.acquireLatestImage() ?: return@setOnImageAvailableListener
            try {
                val frame = imageToFrame(image)
                if (!closed) {
                    _frames.tryEmit(frame)
                }
            } finally {
                image.close()
            }
        }, handler)

        virtualDisplay = mediaProjection.createVirtualDisplay(
            "KadreCapture",
            width,
            height,
            metrics.densityDpi,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
            imageReader.surface,
            null,
            null,
        )
    }

    private fun imageToFrame(image: Image): CaptureFrame {
        val plane = image.planes[0]
        val buffer = plane.buffer
        val data = ByteArray(buffer.remaining())
        buffer.get(data)
        return CaptureFrame(
            size = PhysicalSize(image.width, image.height),
            format = PixelFormat.RGBA8,
            stride = plane.rowStride,
            data = data,
            timestampNanos = image.timestamp,
        )
    }

    override fun close() {
        if (closed) return
        closed = true
        virtualDisplay.release()
        imageReader.close()
        handlerThread.quitSafely()
        mediaProjection.stop()
    }
}
