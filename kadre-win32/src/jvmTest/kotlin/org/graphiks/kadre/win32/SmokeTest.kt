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
 * Smoke test — verifies that the Win32 type aliases and the Win32Runtime stub
 * compile correctly and are accessible.
 *
 * No real FFM call is performed here (that would require Windows +
 * user32.dll loaded). This test only validates the compilation and the
 * readability of the types and the singleton object.
 */
class SmokeTest {

    @Test
    fun `les type aliases Win32 compilent et sont assignables`() {
        // Verifies that the typealiases are usable as ordinary Kotlin types
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
        // DWORD is Long — can store 0xFFFFFFFF without overflow
        val maxDword: DWORD = 0xFFFF_FFFFL
        assertEquals(4294967295L, maxDword)
    }

    @Test
    fun `BOOL semantique Win32 - zero est FALSE`() {
        val winFalse: BOOL = 0
        val winTrue: BOOL = 1
        // Verification of the Win32 semantics: 0 = FALSE
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
        // This test runs on macOS/Linux in CI — isAvailable must be false
        // (on Windows, the test would be skipped or inverted)
        if (!System.getProperty("os.name", "").startsWith("Windows")) {
            assertFalse(Win32Runtime.isAvailable)
        }
    }
}
