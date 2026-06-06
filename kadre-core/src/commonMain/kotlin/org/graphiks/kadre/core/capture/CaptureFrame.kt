package org.graphiks.kadre.core.capture

import org.graphiks.kadre.core.PhysicalSize

enum class PixelFormat {
    RGBA8,
    BGRA8,
    NV12,
    BGRX8,
}

data class CaptureFrame(
    val size: PhysicalSize<Int>,
    val format: PixelFormat,
    val stride: Int,
    val data: ByteArray,
    val timestampNanos: Long,
)
