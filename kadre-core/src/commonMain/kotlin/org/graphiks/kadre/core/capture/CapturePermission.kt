package org.graphiks.kadre.core.capture

sealed interface CapturePermission {
    data object Granted : CapturePermission
    data class Denied(val reason: String) : CapturePermission
    data object Pending : CapturePermission
}
