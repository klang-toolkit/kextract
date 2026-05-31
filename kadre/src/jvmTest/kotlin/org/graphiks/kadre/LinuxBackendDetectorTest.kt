/**
 * Tests unitaires pour [LinuxBackendDetector].
 *
 * Stratégie : on teste les comportements qui ne dépendent pas du classpath
 * ou des variables d'environnement effectives (env vars non mockables
 * facilement en JVM standard). Les cas couverts :
 *  - canLoad : classe existante → true, classe inexistante → false
 *  - La logique KADRE_LINUX_BACKEND est testée indirectement via la détection
 *    par canLoad (seul chemin garantissable sans modifier le process env).
 *
 * Redmine #68 : détection X11/Wayland.
 */
package org.graphiks.kadre

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LinuxBackendDetectorTest {

    // -------------------------------------------------------------------------
    // canLoad — logique principale, indépendante de l'OS
    // -------------------------------------------------------------------------

    @Test
    fun `canLoad retourne true pour une classe existante`() {
        // String est toujours sur le classpath
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
        // kadre-x11 n'est pas une dépendance de :kadre — doit être absent
        val result = LinuxBackendDetector.canLoad(LinuxBackendDetector.X11_CLASS)
        assertFalse(result, "kadre-x11 ne doit pas être sur le classpath de :kadre")
    }

    @Test
    fun `canLoad retourne false pour le backend Wayland absent du classpath`() {
        // kadre-wayland n'est pas une dépendance de :kadre — doit être absent
        val result = LinuxBackendDetector.canLoad(LinuxBackendDetector.WAYLAND_CLASS)
        assertFalse(result, "kadre-wayland ne doit pas être sur le classpath de :kadre")
    }

    @Test
    fun `canLoad avec debug=true ne lance pas d exception`() {
        // Vérifie que le flag debug n'introduit pas de régression
        val result = LinuxBackendDetector.canLoad("org.graphiks.kadre.DoesNotExist", debug = true)
        assertFalse(result)
    }

    // -------------------------------------------------------------------------
    // Constantes — vérification des noms de classes cibles
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
