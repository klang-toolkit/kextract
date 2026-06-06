package org.graphiks.kadre.web.capture

import org.graphiks.kadre.core.capture.*

class WebScreenCapturer(
    private val backend: WebCaptureBackend,
) : ScreenCapturer {

    override suspend fun enumerateDisplays(): List<DisplayInfo> = emptyList()

    override suspend fun enumerateWindows(): List<WindowInfo> = emptyList()

    override suspend fun createSession(source: CaptureSource, config: CaptureConfig): CaptureSession {
        return backend.createCaptureSession(source, config)
    }

    override suspend fun requestPermission(): CapturePermission {
        return backend.requestPermissionInternal()
    }

    override fun permissionStatus(): CapturePermission = CapturePermission.Pending
}
