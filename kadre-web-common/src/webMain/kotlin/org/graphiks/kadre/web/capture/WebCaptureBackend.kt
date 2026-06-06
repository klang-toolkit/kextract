package org.graphiks.kadre.web.capture

import org.graphiks.kadre.core.capture.*

interface WebCaptureBackend {
    suspend fun createCaptureSession(source: CaptureSource, config: CaptureConfig): CaptureSession
    suspend fun requestPermissionInternal(): CapturePermission
}
