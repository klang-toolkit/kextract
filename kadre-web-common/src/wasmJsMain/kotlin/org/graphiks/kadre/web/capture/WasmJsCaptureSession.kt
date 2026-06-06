package org.graphiks.kadre.web.capture

import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.*

@JsFun("() => document.createElement('video')")
private external fun jsCreateVideo(): JsAny

@JsFun("(el) => { el.muted = true; el.playsInline = true; }")
private external fun jsSetupVideo(el: JsAny)

@JsFun("(el, stream) => { el.srcObject = stream; }")
private external fun jsSetSrcObject(el: JsAny, stream: JsMediaStream)

@JsFun("(el) => { el.play(); }")
private external fun jsPlayVideo(el: JsAny)

@JsFun("(el) => { el.onloadedmetadata = () => {}; }")
private external fun jsClearOnLoaded(el: JsAny)

@JsFun("(el, fn) => { el.onloadedmetadata = fn; }")
private external fun jsOnLoadedMetadata(el: JsAny, fn: () -> Unit)

@JsFun("(el) => document.body.appendChild(el)")
private external fun jsAppendChild(el: JsAny)

@JsFun("(el) => el.videoWidth")
private external fun jsVideoWidth(el: JsAny): Int

@JsFun("(el) => el.videoHeight")
private external fun jsVideoHeight(el: JsAny): Int

@JsFun("(el) => { el.pause(); el.srcObject = null; el.remove(); }")
private external fun jsCleanupVideo(el: JsAny)

@JsFun("(w, h) => { const c = document.createElement('canvas'); c.width = w; c.height = h; return c; }")
private external fun jsCreateCanvas(w: Int, h: Int): JsAny

@JsFun("(c, w, h) => { c.width = w; c.height = h; }")
private external fun jsResizeCanvas(c: JsAny, w: Int, h: Int)

@JsFun("(el) => el.remove()")
private external fun jsRemoveElement(el: JsAny)

@JsFun("(fn) => requestAnimationFrame(fn)")
private external fun jsRequestAnimationFrame(fn: (Double) -> Unit): Int

@JsFun("(id) => cancelAnimationFrame(id)")
private external fun jsCancelAnimationFrame(id: Int)

@JsFun("() => performance.now()")
private external fun jsPerformanceNow(): Double

@JsFun("""(video, canvas) => {
    const ctx = canvas.getContext('2d');
    ctx.drawImage(video, 0, 0);
    const imgData = ctx.getImageData(0, 0, canvas.width, canvas.height);
    const data = imgData.data;
    const arr = [];
    for (let i = 0; i < data.length; i++) {
        arr.push(data[i]);
    }
    return arr;
}""")
private external fun jsCaptureFrameData(video: JsAny, canvas: JsAny): JsArray<JsNumber?>

class WasmJsCaptureSession(
    source: CaptureSource,
    config: CaptureConfig,
    private val stream: JsMediaStream,
) : CaptureSession(source, config) {

    private val video: JsAny
    private val canvas: JsAny
    private var rafId = 0
    private var closed = false

    init {
        video = jsCreateVideo()
        jsSetupVideo(video)
        jsSetSrcObject(video, stream)
        jsPlayVideo(video)
        jsAppendChild(video)

        canvas = jsCreateCanvas(0, 0)

        jsOnLoadedMetadata(video) {
            val w = jsVideoWidth(video)
            val h = jsVideoHeight(video)
            if (w > 0 && h > 0) {
                jsResizeCanvas(canvas, w, h)
                scheduleFrame()
            }
        }
    }

    private fun scheduleFrame() {
        if (closed) return
        rafId = jsRequestAnimationFrame { _ ->
            if (closed) return@jsRequestAnimationFrame
            captureFrame()
            scheduleFrame()
        }
    }

    private var lastWidth = 0
    private var lastHeight = 0

    private fun captureFrame() {
        val w = jsVideoWidth(video)
        val h = jsVideoHeight(video)
        if (w == 0 || h == 0) return

        if (w != lastWidth || h != lastHeight) {
            jsResizeCanvas(canvas, w, h)
            lastWidth = w
            lastHeight = h
        }

        val pixelArray = jsCaptureFrameData(video, canvas)
        val len = pixelArray.length
        val bytes = ByteArray(len) { i ->
            pixelArray[i]!!.toDouble().toInt().toByte()
        }

        _frames.tryEmit(
            CaptureFrame(
                size = PhysicalSize(w, h),
                format = PixelFormat.RGBA8,
                stride = w * 4,
                data = bytes,
                timestampNanos = (jsPerformanceNow() * 1_000_000).toLong(),
            ),
        )
    }

    override fun close() {
        if (closed) return
        closed = true
        if (rafId != 0) jsCancelAnimationFrame(rafId)
        rafId = 0

        val tracks = stream.getTracks()
        for (i in 0 until tracks.length) {
            tracks[i]!!.stop()
        }

        jsClearOnLoaded(video)
        jsCleanupVideo(video)
        jsRemoveElement(canvas)
    }
}
