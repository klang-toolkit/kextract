package org.graphiks.kadre.core.capture

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize

data class CaptureRegion(
    val origin: PhysicalPosition<Int>,
    val size: PhysicalSize<Int>,
)
