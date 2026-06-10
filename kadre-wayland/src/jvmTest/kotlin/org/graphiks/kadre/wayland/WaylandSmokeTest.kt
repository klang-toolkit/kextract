package org.graphiks.kadre.wayland

import org.graphiks.kadre.ffi.wayland.*
import kotlin.test.Test

class WaylandSmokeTest {
    @Test
    fun `libwayland-client binding loads safely on non-Wayland`() {
        // On Wayland Linux: loads; on macOS/Windows/X11: null — safe skip
        val lib = libWaylandClient
        if (lib == null) return // Not Wayland — skip silently
    }
}
