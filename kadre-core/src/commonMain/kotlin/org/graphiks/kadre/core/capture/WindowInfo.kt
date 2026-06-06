package org.graphiks.kadre.core.capture

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize

data class WindowInfo(
    val id: WindowId,
    val title: String?,
    val applicationName: String?,
    val position: PhysicalPosition<Int>,
    val size: PhysicalSize<Int>,
)
