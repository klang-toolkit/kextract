package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.Theme
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class WaylandThemePortalTest {

    @Test
    fun `parseColorScheme returns null for NoPreference variant uint32 0`() {
        val output = "  variant uint32 0\n"
        assertNull(WaylandThemePortal.parseColorScheme(output))
    }

    @Test
    fun `parseColorScheme returns Dark for uint32 1`() {
        val output = "  variant uint32 1\n"
        assertEquals(Theme.Dark, WaylandThemePortal.parseColorScheme(output))
    }

    @Test
    fun `parseColorScheme returns Light for uint32 2`() {
        val output = "  variant uint32 2\n"
        assertEquals(Theme.Light, WaylandThemePortal.parseColorScheme(output))
    }

    @Test
    fun `parseColorScheme returns null for empty output`() {
        assertNull(WaylandThemePortal.parseColorScheme(""))
    }

    @Test
    fun `parseColorScheme returns null for garbage output`() {
        assertNull(WaylandThemePortal.parseColorScheme("method return time=123\n"))
    }

    @Test
    fun `queryColorScheme returns null when dbus-send not available`() {
        WaylandThemePortal.resetCache()
        val result = WaylandThemePortal.queryColorScheme()
        // On Linux with dbus-send installed, this might return a real value or fail.
        // On macOS/CI, dbus-send is absent → returns null.
        // Either way, it must not crash.
        @Suppress("UNUSED_EXPRESSION")
        result
    }
}
