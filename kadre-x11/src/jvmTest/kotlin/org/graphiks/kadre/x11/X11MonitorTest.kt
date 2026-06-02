package org.graphiks.kadre.x11

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.VideoMode
import kotlin.test.Test
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

    private fun monitor(id: Long, x: Int, isPrimary: Boolean = false): X11MonitorHandle =
        X11MonitorHandle(
            id = id,
            name = null,
            position = PhysicalPosition(x, 0),
            isPrimary = isPrimary,
            scaleFactor = 1.0,
            currentVideoMode = VideoMode(PhysicalSize(1920, 1080), null, null),
            videoModes = emptyList(),
        )
}
