package org.graphiks.kadre.web.capture

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.suspendCancellableCoroutine
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.*
import org.w3c.dom.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.js.Promise

class JsCaptureSession(
    source: CaptureSource,
    config: CaptureConfig,
    private val stream: Any,
) : CaptureSession(source, config) {

    private val video: HTMLVideoElement
    private val canvas: HTMLCanvasElement
    private val ctx: CanvasRenderingContext2D
    private var closed = false
    private var animFrameId: Int? = null

    init {
        video = document.createElement("video") as HTMLVideoElement
        video.style.setProperty("display", "none")
        video.muted = true
        video.playsInline = true
        video.asDynamic().srcObject = stream
        video.play()

        canvas = document.createElement("canvas") as HTMLCanvasElement
        ctx = canvas.getContext("2d") as CanvasRenderingContext2D

        document.body!!.appendChild(video)
        document.body!!.appendChild(canvas)

        video.onloadedmetadata = { _ ->
            canvas.width = video.videoWidth
            canvas.height = video.videoHeight
            scheduleFrame()
        }
    }

    private fun scheduleFrame() {
        if (closed) return
        animFrameId = window.requestAnimationFrame { _ ->
            captureFrame()
            scheduleFrame()
        }
    }

    private fun captureFrame() {
        val w = video.videoWidth
        val h = video.videoHeight
        if (w == 0 || h == 0) return

        if (canvas.width != w || canvas.height != h) {
            canvas.width = w
            canvas.height = h
        }

        ctx.drawImage(video, 0.0, 0.0)
        val imageData = ctx.getImageData(0.0, 0.0, w.toDouble(), h.toDouble())
        val src: dynamic = imageData.data
        val len = (src.length as Int)
        val bytes = ByteArray(len) { i -> (src[i] as Number).toByte() }

        _frames.tryEmit(
            CaptureFrame(
                size = PhysicalSize(w, h),
                format = PixelFormat.RGBA8,
                stride = w * 4,
                data = bytes,
                timestampNanos = (window.performance.now() * 1_000_000).toLong(),
            ),
        )
    }

    override fun close() {
        if (closed) return
        closed = true
        animFrameId?.let { window.cancelAnimationFrame(it) }
        animFrameId = null

        val tracks = (stream.asDynamic().getTracks() as Array<*>)
        for (track in tracks) {
            track.asDynamic().stop()
        }

        video.pause()
        video.asDynamic().srcObject = null
        video.remove()
        canvas.remove()
    }
}
