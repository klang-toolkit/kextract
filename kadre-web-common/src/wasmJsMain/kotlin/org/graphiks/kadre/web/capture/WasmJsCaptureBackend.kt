package org.graphiks.kadre.web.capture

import org.graphiks.kadre.core.capture.*
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@JsName("MediaStream")
external interface JsMediaStream : JsAny {
    fun getTracks(): JsArray<JsMediaStreamTrack>
}

@JsName("MediaStreamTrack")
external interface JsMediaStreamTrack : JsAny {
    fun stop(): Unit
}

@JsFun("(fn) => fn")
private external fun wrapResolve(fn: (JsMediaStream) -> Unit): JsAny

@JsFun("(fn) => fn")
private external fun wrapReject(fn: (JsString) -> Unit): JsAny

@JsFun("""(frameRate, onResolve, onReject) => {
    navigator.mediaDevices.getDisplayMedia({
        video: { frameRate: { ideal: frameRate } },
        audio: false
    }).then(
        stream => onResolve(stream),
        error => onReject(error.message || String(error))
    );
}""")
private external fun jsGetDisplayMedia(
    frameRate: Int,
    onResolve: JsAny,
    onReject: JsAny,
)

class WasmJsCaptureBackend : WebCaptureBackend {

    override suspend fun createCaptureSession(source: CaptureSource, config: CaptureConfig): CaptureSession {
        val stream = getDisplayMedia(config.frameRate)
        return WasmJsCaptureSession(source, config, stream)
    }

    override suspend fun requestPermissionInternal(): CapturePermission {
        return try {
            val stream = getDisplayMedia(30)
            val tracks = stream.getTracks()
            for (i in 0 until tracks.length) {
                tracks[i]!!.stop()
            }
            CapturePermission.Granted
        } catch (e: Throwable) {
            CapturePermission.Denied(e.message ?: "User denied the screen capture request")
        }
    }

    companion object {
        suspend fun getDisplayMedia(frameRate: Int = 30): JsMediaStream {
            return suspendCancellableCoroutine { cont ->
                jsGetDisplayMedia(
                    frameRate,
                    wrapResolve { stream -> cont.resume(stream) },
                    wrapReject { msg -> cont.resumeWithException(Exception(msg.toString())) },
                )
            }
        }
    }
}
