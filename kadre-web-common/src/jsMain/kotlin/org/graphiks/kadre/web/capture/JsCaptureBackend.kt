package org.graphiks.kadre.web.capture

import org.graphiks.kadre.core.capture.*
import kotlinx.browser.window
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.js.Promise

class JsCaptureBackend : WebCaptureBackend {

    override suspend fun createCaptureSession(source: CaptureSource, config: CaptureConfig): CaptureSession {
        val stream = getDisplayMedia(config)
        return JsCaptureSession(source, config, stream)
    }

    override suspend fun requestPermissionInternal(): CapturePermission {
        return try {
            val stream = getDisplayMedia()
            val tracks = stream.asDynamic().getTracks() as Array<*>
            for (track in tracks) {
                track.asDynamic().stop()
            }
            CapturePermission.Granted
        } catch (e: Throwable) {
            CapturePermission.Denied(e.message ?: "User denied the screen capture request")
        }
    }

    companion object {
        suspend fun getDisplayMedia(config: CaptureConfig = CaptureConfig()): Any {
            return suspendCancellableCoroutine { cont ->
                val nav = window.navigator.asDynamic()
                val constraints: dynamic = js("({})")
                constraints.video = js("({})")
                constraints.video.frameRate = js("({})")
                constraints.video.frameRate.ideal = config.frameRate
                constraints.audio = false
                val promise = nav.mediaDevices.getDisplayMedia(constraints).unsafeCast<Promise<Any>>()
                promise.then(
                    { stream -> cont.resume(stream) },
                    { error -> cont.resumeWithException(error.unsafeCast<Throwable>()) },
                )
            }
        }
    }
}
