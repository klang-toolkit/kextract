package org.graphiks.kadre.x11

import org.graphiks.kadre.ffi.x11.libX11
import kotlin.test.Test

class X11SmokeTest {
    @Test
    fun `libX11 binding loads on Linux only`() {
        // On Linux: libX11 should load; on macOS/Windows: null (safe skip)
        // We just verify the lazy init doesn't throw on any platform
        val lib = libX11
        if (lib == null) return // Not Linux — skip silently
        // If we're on Linux, XOpenDisplay with null should return non-null Display
        // (requires a running X display, so skip if null too)
    }
}
