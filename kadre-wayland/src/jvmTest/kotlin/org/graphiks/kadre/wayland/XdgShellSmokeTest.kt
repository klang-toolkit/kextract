package org.graphiks.kadre.wayland

import kotlin.test.Test
import kotlin.test.assertEquals

class XdgShellSmokeTest {
    @Test
    fun `xdg_shell constants are defined`() {
        // Validate key opcode values from Wayland xdg-shell protocol XML
        assertEquals(2, XDG_WM_BASE_GET_XDG_SURFACE)
        assertEquals(1, XDG_SURFACE_GET_TOPLEVEL)
        assertEquals(2, XDG_TOPLEVEL_SET_TITLE)
        assertEquals(4, XDG_TOPLEVEL_SHOW_WINDOW_MENU)
        assertEquals(5, XDG_TOPLEVEL_MOVE)
        assertEquals(6, XDG_TOPLEVEL_RESIZE)
        assertEquals(3, XDG_TOPLEVEL_SET_APP_ID)
    }

    @Test
    fun `wl_seat request opcodes match protocol order`() {
        assertEquals(0, WL_SEAT_GET_POINTER)
        assertEquals(1, WL_SEAT_GET_KEYBOARD)
        assertEquals(2, WL_SEAT_GET_TOUCH)
    }
}
