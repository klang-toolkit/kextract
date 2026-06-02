package org.graphiks.kadre.core

/**
 * Result of a platform windowing request.
 *
 * This mirrors winit's fallible window operations without using exceptions for
 * expected platform limitations.
 */
sealed interface WindowRequestResult {
    data object Success : WindowRequestResult
    data class Failure(val error: RequestError) : WindowRequestResult
}

sealed interface SurfaceSizeRequestResult {
    data class Applied(val size: PhysicalSize<Int>) : SurfaceSizeRequestResult
    data object Pending : SurfaceSizeRequestResult
    data class Failure(val error: RequestError) : SurfaceSizeRequestResult
}

sealed interface RequestError {
    val message: String

    data class Unsupported(override val message: String = "Unsupported on this platform") : RequestError
    data class PermissionDenied(override val message: String = "Permission denied") : RequestError
    data class OsError(override val message: String) : RequestError
}

data class Insets<T : Number>(
    val top: T,
    val right: T,
    val bottom: T,
    val left: T,
)

data class WindowButtons(val bits: Int) {
    fun contains(buttons: WindowButtons): Boolean = bits and buttons.bits == buttons.bits
    operator fun plus(buttons: WindowButtons): WindowButtons = WindowButtons(bits or buttons.bits)

    companion object {
        val NONE = WindowButtons(0)
        val CLOSE = WindowButtons(1 shl 0)
        val MINIMIZE = WindowButtons(1 shl 1)
        val MAXIMIZE = WindowButtons(1 shl 2)
        val ALL = CLOSE + MINIMIZE + MAXIMIZE
    }
}

data class OwnedDisplayHandle(val rawDisplayHandle: RawDisplayHandle)
