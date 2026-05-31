/**
 * Tests pour Win32Window.
 *
 * Ces tests vérifient la structure des bindings Win32 et les comportements
 * attendus sur les plateformes non-Windows (valeurs null, skip automatique).
 *
 * Sur macOS/Linux : les tests liés à la création de fenêtre sont sautés
 * automatiquement car les MethodHandle FFM sont null (user32.dll introuvable).
 *
 * Sur Windows : les tests complets s'exécutent et valident la création d'une
 * fenêtre native via RegisterClassExW + CreateWindowExW.
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.RawDisplayHandle
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Retourne true si le test tourne sur Windows.
 *
 * Utilisé pour ignorer les tests nécessitant user32.dll sur macOS/Linux.
 */
private fun isWindows(): Boolean =
    System.getProperty("os.name", "").contains("Windows", ignoreCase = true)

class Win32WindowTest {

    // ── Tests compilant sur toutes les plateformes ─────────────────────────────

    @Test
    fun `WndClassExW SIZEOF est 80 octets`() {
        assertEquals(80, WndClassExW.SIZEOF)
    }

    @Test
    fun `WndClassExW ALIGN est 8`() {
        assertEquals(8, WndClassExW.ALIGN)
    }

    @Test
    fun `WndClassExW offsets corrects pour Win64 ABI`() {
        assertEquals(0,  WndClassExW.OFFSET_CB_SIZE)
        assertEquals(4,  WndClassExW.OFFSET_STYLE)
        assertEquals(8,  WndClassExW.OFFSET_WNDPROC)
        assertEquals(16, WndClassExW.OFFSET_CLS_EXTRA)
        assertEquals(20, WndClassExW.OFFSET_WND_EXTRA)
        assertEquals(24, WndClassExW.OFFSET_HINSTANCE)
        assertEquals(32, WndClassExW.OFFSET_HICON)
        assertEquals(40, WndClassExW.OFFSET_HCURSOR)
        assertEquals(48, WndClassExW.OFFSET_HBRUSH)
        assertEquals(56, WndClassExW.OFFSET_MENU_NAME)
        assertEquals(64, WndClassExW.OFFSET_CLASS_NAME)
        assertEquals(72, WndClassExW.OFFSET_HICON_SM)
    }

    @Test
    fun `WndClassExW LAYOUT byte size correspond a SIZEOF`() {
        assertEquals(WndClassExW.SIZEOF.toLong(), WndClassExW.LAYOUT.byteSize())
    }

    @Test
    fun `constantes Win32 ont les valeurs attendues`() {
        assertEquals(0x00CF0000, WS_OVERLAPPEDWINDOW)
        assertEquals(0x00040000, WS_EX_APPWINDOW)
        assertEquals(5, SW_SHOW)
        assertEquals(0, SW_HIDE)
        assertEquals(0x0003, CS_HREDRAW_VREDRAW)
        assertEquals(0x0002, WM_DESTROY)
    }

    @Test
    fun `user32 et kernel32 sont null sur plateforme non-Windows`() {
        if (isWindows()) return  // skip sur Windows (les libs existent)
        // Sur macOS/Linux, les lazy loaders doivent retourner null
        assertNull(user32)
        assertNull(kernel32)
    }

    @Test
    fun `les MethodHandle FFM sont null sur plateforme non-Windows`() {
        if (isWindows()) return
        assertNull(registerClassExW)
        assertNull(createWindowExW)
        assertNull(showWindow)
        assertNull(updateWindow)
        assertNull(destroyWindow)
        assertNull(defWindowProcW)
        assertNull(setWindowTextW)
        assertNull(getModuleHandleW)
    }

    @Test
    fun `Win32Window create retourne null sur plateforme non-Windows`() {
        if (isWindows()) return
        val attrs = WindowAttributes(title = "Test", visible = false)
        val window = Win32Window.create(attrs)
        assertNull(window, "create() doit retourner null sur macOS/Linux (user32.dll absent)")
    }

    @Test
    fun `Win32WndProcArena arena est accessible`() {
        // L'arène elle-même peut être créée sur toute plateforme
        assertNotNull(Win32WndProcArena.arena)
    }

    // ── Tests exécutés uniquement sur Windows ─────────────────────────────────

    @Test
    fun `Win32Window create produit une fenetre valide sur Windows`() {
        if (!isWindows()) return  // skip sur macOS/Linux

        val attrs = WindowAttributes(
            title = "Test Kadre Win32",
            visible = false,  // ne pas afficher en CI
        )
        val window = Win32Window.create(attrs)
        assertNotNull(window, "create() doit réussir sur Windows")

        // Vérifier les handles
        val rawHandle = window.rawWindowHandle
        assertTrue(rawHandle is RawWindowHandle.Win32, "rawWindowHandle doit être Win32")
        assertTrue(rawHandle.hwnd != 0L, "HWND ne doit pas être nul")
        assertTrue(rawHandle.hinstance != 0L, "HINSTANCE ne doit pas être nul")

        val displayHandle = window.rawDisplayHandle
        assertTrue(displayHandle is RawDisplayHandle.Win32, "rawDisplayHandle doit être Win32")
        assertTrue((displayHandle).hinstance != 0L)

        // Vérifier les propriétés de base
        assertEquals(rawHandle.hwnd, window.id.value)
        assertEquals(1.0, window.scaleFactor)
        assertNotNull(window.innerSize)
        assertNotNull(window.outerSize)

        // Nettoyage
        window.close()
    }

    @Test
    fun `Win32Window setTitle ne leve pas d exception sur Windows`() {
        if (!isWindows()) return

        val attrs = WindowAttributes(title = "Initial", visible = false)
        val window = Win32Window.create(attrs) ?: return

        // Ne doit pas lever d'exception
        window.setTitle("Nouveau titre")

        window.close()
    }

    @Test
    fun `Win32Window setVisible ne leve pas d exception sur Windows`() {
        if (!isWindows()) return

        val attrs = WindowAttributes(title = "Test setVisible", visible = false)
        val window = Win32Window.create(attrs) ?: return

        window.setVisible(false)  // déjà caché
        window.setVisible(true)   // montrer
        window.setVisible(false)  // recacher

        window.close()
    }
}
