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
    fun `canLoad retourne true pour une classe existante`() {
        // String is always on the classpath
        val result = LinuxBackendDetector.canLoad("java.lang.String")
        assertTrue(result, "java.lang.String doit être chargeable")
    }

    @Test
    fun `canLoad retourne false pour une classe inexistante`() {
        val result = LinuxBackendDetector.canLoad("org.graphiks.kadre.NonExistentClass999")
        assertFalse(result, "Une classe fictive ne doit pas être chargeable")
    }

    @Test
    fun `canLoad retourne false pour le backend X11 absent du classpath`() {
        // kadre-x11 is not a dependency of :kadre — must be absent
        val result = LinuxBackendDetector.canLoad(LinuxBackendDetector.X11_CLASS)
        assertFalse(result, "kadre-x11 ne doit pas être sur le classpath de :kadre")
    }

    @Test
    fun `canLoad retourne false pour le backend Wayland absent du classpath`() {
        // kadre-wayland is not a dependency of :kadre — must be absent
        val result = LinuxBackendDetector.canLoad(LinuxBackendDetector.WAYLAND_CLASS)
        assertFalse(result, "kadre-wayland ne doit pas être sur le classpath de :kadre")
    }

    @Test
    fun `canLoad avec debug=true ne lance pas d exception`() {
        // Verifies that the debug flag does not introduce a regression
        val result = LinuxBackendDetector.canLoad("org.graphiks.kadre.DoesNotExist", debug = true)
        assertFalse(result)
    }

    // -------------------------------------------------------------------------
    // Constants — verification of the target class names
    // -------------------------------------------------------------------------

    @Test
    fun `X11_CLASS pointe vers le package x11 attendu`() {
        assertEquals(
            "org.graphiks.kadre.x11.X11EventLoopKt",
            LinuxBackendDetector.X11_CLASS,
        )
    }

    @Test
    fun `WAYLAND_CLASS pointe vers le package wayland attendu`() {
        assertEquals(
            "org.graphiks.kadre.wayland.WaylandEventLoopKt",
            LinuxBackendDetector.WAYLAND_CLASS,
        )
    }
}
