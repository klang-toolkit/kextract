package org.graphiks.kadre.x11

import org.graphiks.kadre.ffi.x11.*
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.VideoMode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertSame

class X11MonitorTest {
    @Test
    fun `selectPrimaryMonitor returns monitor flagged primary`() {
        val first = monitor(id = 10L, x = 0)
        val primary = monitor(id = 20L, x = 1920, isPrimary = true)

        assertSame(primary, selectPrimaryMonitor(listOf(first, primary)))
    }

    @Test
    fun `selectPrimaryMonitor falls back to first monitor when no monitor is primary`() {
        val first = monitor(id = 10L, x = 0)
        val second = monitor(id = 20L, x = 1920)

        assertSame(first, selectPrimaryMonitor(listOf(first, second)))
    }

    @Test
    fun `selectPrimaryMonitor returns null for empty monitor list`() {
        assertNull(selectPrimaryMonitor(emptyList()))
    }

    @Test
    fun `selectX11MonitorForWindow matches largest overlapping monitor like winit`() {
        val left = monitor(id = 10L, x = 0)
        val right = monitor(id = 20L, x = 1920)
        // winit uses the outer position with the surface size, not the decorated outer size.
        val window = X11WindowRect(
            position = PhysicalPosition(1800, 0),
            size = PhysicalSize(400, 500),
        )

        assertSame(right, selectX11MonitorForWindow(listOf(left, right), window))
    }

    @Test
    fun `selectX11MonitorForWindow falls back to first monitor without overlap`() {
        val first = monitor(id = 10L, x = 0)
        val second = monitor(id = 20L, x = 1920)
        val offscreen = X11WindowRect(
            position = PhysicalPosition(-1000, -1000),
            size = PhysicalSize(100, 100),
        )

        assertSame(first, selectX11MonitorForWindow(listOf(first, second), offscreen))
    }

    @Test
    fun `X11 rectangle overlap area matches winit AaRect behavior`() {
        val window = X11WindowRect(
            position = PhysicalPosition(1800, 100),
            size = PhysicalSize(400, 200),
        )
        val monitor = X11WindowRect(
            position = PhysicalPosition(1920, 0),
            size = PhysicalSize(1920, 1080),
        )

        assertEquals(56_000L, x11RectOverlapArea(window, monitor))
    }

    private fun monitor(id: Long, x: Int, isPrimary: Boolean = false, width: Int = 1920): X11MonitorHandle =
        X11MonitorHandle(
            id = id,
            name = null,
            position = PhysicalPosition(x, 0),
            isPrimary = isPrimary,
            scaleFactor = 1.0,
            currentVideoMode = VideoMode(PhysicalSize(width, 1080), null, null),
            videoModes = emptyList(),
        )
}
