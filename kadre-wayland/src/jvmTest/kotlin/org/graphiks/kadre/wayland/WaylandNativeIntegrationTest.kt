/**
 * Conditional native integration tests for the Wayland backend.
 *
 * These tests only run on Linux where libwayland-client.so.0 is available.
 * On macOS/Windows they verify that the FFM bindings gracefully return null.
 *
 * Based on the pattern established by [Win32WindowTest]
 * (kadre-win32/.../Win32WindowTest.kt) for OS-conditional native tests.
 */
package org.graphiks.kadre.wayland

import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

private fun isLinux(): Boolean =
    System.getProperty("os.name", "").contains("Linux", ignoreCase = true)

private fun nativeEnabled(): Boolean =
    isLinux() && !waylandNativeDisabled()

/** Reads the `const char *name` from a minimal `wl_interface` struct. */
private fun interfaceName(segment: MemorySegment): String {
    val nameSeg = segment.get(ValueLayout.ADDRESS, 0L)
    return nameSeg.reinterpret(64).getString(0)
}

class WaylandNativeIntegrationTest {

    // ── FFM library loading (Linux only) ──────────────────────────────────────

    @Test
    fun `libWaylandClient resolves on Linux`() {
        if (!nativeEnabled()) return
        assertNotNull(libWaylandClient, "libwayland-client.so.0 must load on Linux")
    }

    @Test
    fun `core wayland method handles resolve on Linux`() {
        if (!nativeEnabled()) return
        assertNotNull(wlDisplayConnect)
        assertNotNull(wlCompositorCreateSurface)
        assertNotNull(wlProxyMarshalNewId)
        assertNotNull(wlProxyMarshalBind)
    }

    @Test
    fun `zwp pointer constraints v1 method handles resolve on Linux`() {
        if (!nativeEnabled()) return
        assertNotNull(wlPointerConstraintsLockPointer)
        assertNotNull(wlPointerConstraintsConfinePointer)
    }

    // ── Constants (cross-platform) ─────────────────────────────────────────────

    @Test
    fun `WL_MARSHAL_FLAG_DESTROY has the correct value`() {
        assertEquals(1, WL_MARSHAL_FLAG_DESTROY)
    }

    // ── Protocol interface names (cross-platform) ──────────────────────────────

    @Test
    fun `protocol extension interface name constants are defined`() {
        assertEquals("xdg_toplevel_icon_manager_v1", XDG_TOPLEVEL_ICON_MANAGER_INTERFACE_NAME)
        assertEquals("ext_background_effect_v1", EXT_BACKGROUND_EFFECT_V1_INTERFACE_NAME)
        assertEquals("org_kde_kwin_blur_manager", ORG_KDE_KWIN_BLUR_MANAGER_INTERFACE_NAME)
    }

    @Test
    fun `zwp_pointer_constraints_v1 interface symbol name is correct`() {
        assertEquals("zwp_pointer_constraints_v1", interfaceName(zwpPointerConstraintsV1Interface))
    }

    @Test
    fun `xdg_toplevel_icon_manager_v1 interface symbol name is correct`() {
        assertEquals("xdg_toplevel_icon_manager_v1", interfaceName(xdgToplevelIconManagerV1Interface))
    }

    @Test
    fun `ext_background_effect_v1 interface symbol name is correct`() {
        assertEquals("ext_background_effect_v1", interfaceName(extBackgroundEffectV1Interface))
    }

    @Test
    fun `org_kde_kwin_blur_manager interface symbol name is correct`() {
        assertEquals("org_kde_kwin_blur_manager", interfaceName(orgKdeKwinBlurManagerInterface))
    }

    @Test
    fun `xdg_activation_v1 interface symbol name is correct`() {
        assertEquals("xdg_activation_v1", interfaceName(xdgActivationV1Interface))
    }

    @Test
    fun `zwp_text_input_manager_v3 interface symbol name is correct`() {
        assertEquals("zwp_text_input_manager_v3", interfaceName(zwpTextInputManagerV3Interface))
    }

    @Test
    fun `zwp_text_input_v3 interface symbol name is correct`() {
        assertEquals("zwp_text_input_v3", interfaceName(zwpTextInputV3Interface))
    }

    // ── WaylandRegistry.discoverGlobals (non-Linux only — no compositor) ────────

    @Test
    fun `discoverGlobals returns empty globals when library is unavailable`() {
        if (nativeEnabled()) return // on Linux with native libs this would invoke FFM with null display
        val globals = discoverGlobals(displayPtr = 0L)
        assertEquals(0L, globals.compositorPtr)
        assertEquals(0L, globals.xdgWmBasePtr)
    }

    @Test
    fun `discoverGlobals accepts empty protocolExtensions set`() {
        if (nativeEnabled()) return // on Linux with native libs this would invoke FFM with null display
        val globals = discoverGlobals(displayPtr = 0L, protocolExtensions = emptySet())
        assertEquals(0L, globals.compositorPtr)
    }

    // ── ZWP_POINTER_CONSTRAINTS_V1 lifetime constants (cross-platform) ─────────

    @Test
    fun `zwp pointer constraints v1 lifetime constants are defined`() {
        assertEquals(0, ZWP_POINTER_CONSTRAINTS_V1_LIFETIME_ONESHOT)
        assertEquals(1, ZWP_POINTER_CONSTRAINTS_V1_LIFETIME_PERSISTENT)
    }
}
