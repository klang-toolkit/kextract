package org.graphiks.kadre.core.capture

typealias DisplayId = Long
typealias WindowId = Long

sealed interface CaptureSource {
    data class Display(val id: DisplayId) : CaptureSource
    data class Window(val id: WindowId) : CaptureSource
}
