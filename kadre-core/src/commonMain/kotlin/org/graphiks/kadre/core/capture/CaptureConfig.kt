package org.graphiks.kadre.core.capture

data class CaptureConfig(
    val frameRate: Int = 30,
    val pixelFormat: PixelFormat = PixelFormat.RGBA8,
    val captureCursor: Boolean = false,
    val region: CaptureRegion? = null,
)
