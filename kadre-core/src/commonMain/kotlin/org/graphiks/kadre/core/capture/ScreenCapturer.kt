package org.graphiks.kadre.core.capture

interface ScreenCapturer {
    suspend fun enumerateDisplays(): List<DisplayInfo>
    suspend fun enumerateWindows(): List<WindowInfo>
    suspend fun createSession(source: CaptureSource, config: CaptureConfig = CaptureConfig()): CaptureSession
    suspend fun requestPermission(): CapturePermission
    fun permissionStatus(): CapturePermission

    companion object {
        fun resolve(): ScreenCapturer? = resolveScreenCapturer()
    }
}

expect fun resolveScreenCapturer(): ScreenCapturer?
