package org.graphiks.kadre.core.capture

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize

data class DisplayInfo(
    val id: DisplayId,
    val name: String?,
    val position: PhysicalPosition<Int>,
    val resolution: PhysicalSize<Int>,
    val scaleFactor: Double,
)
