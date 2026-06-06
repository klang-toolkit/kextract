package org.graphiks.kadre.core.capture

sealed class CaptureError(message: String, cause: Throwable? = null) : Exception(message, cause) {
    class PermissionDenied(reason: String) : CaptureError(reason)
    class NoSuchSource(source: CaptureSource) : CaptureError("No such source: $source")
    class SourceLost(source: CaptureSource) : CaptureError("Source lost: $source")
    class Unsupported(reason: String) : CaptureError(reason)
    class Internal(cause: Throwable) : CaptureError("Internal error", cause)
}
