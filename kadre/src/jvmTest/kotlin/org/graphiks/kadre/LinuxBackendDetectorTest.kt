/**
 * Unit tests for [LinuxBackendDetector].
 *
 * Strategy: we test the behaviors that do not depend on the classpath
 * or the effective environment variables (env vars are not easily mockable
 * in standard JVM). Covered cases:
 *  - canLoad: existing class → true, non-existent class → false
 *  - The KADRE_LINUX_BACKEND logic is tested indirectly via canLoad-based
 *    detection (the only guaranteeable path without modifying the process env).
 *
 * X11/Wayland detection.
 */
package org.graphiks.kadre

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LinuxBackendDetectorTest {

    // -------------------------------------------------------------------------
    // canLoad — main logic, OS-independent
    // -------------------------------------------------------------------------

    @Test
    fun `canLoad returns true for an existing class`() {
        // String is always on the classpath
        val result = LinuxBackendDetector.canLoad("java.lang.String")
        assertTrue(result, "java.lang.String must be loadable")
    }

    @Test
    fun `canLoad returns false for a non-existent class`() {
        val result = LinuxBackendDetector.canLoad("org.graphiks.kadre.NonExistentClass999")
        assertFalse(result, "A fictitious class must not be loadable")
    }

    @Test
    fun `canLoad returns false for the X11 backend absent from the classpath`() {
        // kadre-x11 is not a dependency of :kadre — must be absent
        val result = LinuxBackendDetector.canLoad(LinuxBackendDetector.X11_CLASS)
        assertFalse(result, "kadre-x11 must not be on the classpath of :kadre")
    }

    @Test
    fun `canLoad returns false for the Wayland backend absent from the classpath`() {
        // kadre-wayland is not a dependency of :kadre — must be absent
        val result = LinuxBackendDetector.canLoad(LinuxBackendDetector.WAYLAND_CLASS)
        assertFalse(result, "kadre-wayland must not be on the classpath of :kadre")
    }

    @Test
    fun `canLoad with debug=true does not throw an exception`() {
        // Verifies that the debug flag does not introduce a regression
        val result = LinuxBackendDetector.canLoad("org.graphiks.kadre.DoesNotExist", debug = true)
        assertFalse(result)
    }

    // -------------------------------------------------------------------------
    // Constants — verification of the target class names
    // -------------------------------------------------------------------------

    @Test
    fun `X11_CLASS points to the expected x11 package`() {
        assertEquals(
            "org.graphiks.kadre.x11.X11EventLoopKt",
            LinuxBackendDetector.X11_CLASS,
        )
    }

    @Test
    fun `WAYLAND_CLASS points to the expected wayland package`() {
        assertEquals(
            "org.graphiks.kadre.wayland.WaylandEventLoopKt",
            LinuxBackendDetector.WAYLAND_CLASS,
        )
    }
}
