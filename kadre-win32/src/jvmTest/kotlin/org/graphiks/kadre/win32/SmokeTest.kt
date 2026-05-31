package org.graphiks.kadre.win32

import org.graphiks.kadre.win32.bindings.ATOM
import org.graphiks.kadre.win32.bindings.BOOL
import org.graphiks.kadre.win32.bindings.DWORD
import org.graphiks.kadre.win32.bindings.HDC
import org.graphiks.kadre.win32.bindings.HINSTANCE
import org.graphiks.kadre.win32.bindings.HMENU
import org.graphiks.kadre.win32.bindings.HMODULE
import org.graphiks.kadre.win32.bindings.HWND
import org.graphiks.kadre.win32.bindings.LPARAM
import org.graphiks.kadre.win32.bindings.LONG
import org.graphiks.kadre.win32.bindings.LONG_PTR
import org.graphiks.kadre.win32.bindings.LRESULT
import org.graphiks.kadre.win32.bindings.UINT
import org.graphiks.kadre.win32.bindings.ULONG_PTR
import org.graphiks.kadre.win32.bindings.WORD
import org.graphiks.kadre.win32.bindings.WPARAM
import org.graphiks.kadre.win32.bindings.Win32Runtime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

/**
 * Smoke test — vérifie que les type aliases Win32 et le stub Win32Runtime
 * compilent correctement et sont accessibles.
 *
 * Aucun appel FFM réel n'est effectué ici (cela nécessiterait Windows +
 * user32.dll chargée). Ce test valide uniquement la compilation et la
 * lisibilité des types et de l'objet singleton.
 */
class SmokeTest {

    @Test
    fun `les type aliases Win32 compilent et sont assignables`() {
        // Vérifie que les typealiases sont utilisables comme types Kotlin ordinaires
        val dword: DWORD = 0xFFFF_FFFFL
        val word: WORD = 0xFFFF
        val uint: UINT = 0L
        val ulongPtr: ULONG_PTR = 0L
        val long: LONG = -1
        val longPtr: LONG_PTR = -1L
        val lresult: LRESULT = 0L
        val wparam: WPARAM = 0L
        val lparam: LPARAM = 0L
        val bool: BOOL = 0
        val hwnd: HWND = 0L
        val hinstance: HINSTANCE = 0L
        val hmodule: HMODULE = 0L
        val hdc: HDC = 0L
        val hmenu: HMENU = 0L
        val atom: ATOM = 0

        assertNotNull(dword)
        assertNotNull(word)
        assertNotNull(uint)
        assertNotNull(ulongPtr)
        assertNotNull(long)
        assertNotNull(longPtr)
        assertNotNull(lresult)
        assertNotNull(wparam)
        assertNotNull(lparam)
        assertNotNull(bool)
        assertNotNull(hwnd)
        assertNotNull(hinstance)
        assertNotNull(hmodule)
        assertNotNull(hdc)
        assertNotNull(hmenu)
        assertNotNull(atom)
    }

    @Test
    fun `DWORD peut representer une valeur 32 bits non signee`() {
        // DWORD est Long — peut stocker 0xFFFFFFFF sans overflow
        val maxDword: DWORD = 0xFFFF_FFFFL
        assertEquals(4294967295L, maxDword)
    }

    @Test
    fun `BOOL semantique Win32 - zero est FALSE`() {
        val winFalse: BOOL = 0
        val winTrue: BOOL = 1
        // Vérification de la sémantique Win32 : 0 = FALSE
        assertEquals(0, winFalse)
        assertEquals(1, winTrue)
    }

    @Test
    fun `Win32Runtime est accessible`() {
        val runtime: Win32Runtime = Win32Runtime
        assertNotNull(runtime)
    }

    @Test
    fun `Win32Runtime constantes de bibliotheques sont definies`() {
        assertEquals("user32", Win32Runtime.USER32_LIB)
        assertEquals("kernel32", Win32Runtime.KERNEL32_LIB)
        assertEquals("gdi32", Win32Runtime.GDI32_LIB)
    }

    @Test
    fun `Win32Runtime isAvailable retourne false hors Windows`() {
        // Ce test tourne sur macOS/Linux en CI — isAvailable doit être false
        // (sur Windows, le test serait ignoré ou inversé)
        if (!System.getProperty("os.name", "").startsWith("Windows")) {
            assertFalse(Win32Runtime.isAvailable)
        }
    }
}
