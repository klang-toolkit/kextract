package org.graphiks.kadre.win32

import org.graphiks.kadre.ffi.win32.ATOM
import org.graphiks.kadre.ffi.win32.BOOL
import org.graphiks.kadre.ffi.win32.DWORD
import org.graphiks.kadre.ffi.win32.HDC
import org.graphiks.kadre.ffi.win32.HINSTANCE
import org.graphiks.kadre.ffi.win32.HMENU
import org.graphiks.kadre.ffi.win32.HMODULE
import org.graphiks.kadre.ffi.win32.HWND
import org.graphiks.kadre.ffi.win32.LPARAM
import org.graphiks.kadre.ffi.win32.LONG
import org.graphiks.kadre.ffi.win32.LONG_PTR
import org.graphiks.kadre.ffi.win32.LRESULT
import org.graphiks.kadre.ffi.win32.UINT
import org.graphiks.kadre.ffi.win32.ULONG_PTR
import org.graphiks.kadre.ffi.win32.WORD
import org.graphiks.kadre.ffi.win32.WPARAM
import org.graphiks.kadre.ffi.win32.Win32Runtime
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
    fun `the Win32 type aliases compile and are assignable`() {
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
    fun `DWORD can represent a 32-bit unsigned value`() {
        // DWORD is Long — can store 0xFFFFFFFF without overflow
        val maxDword: DWORD = 0xFFFF_FFFFL
        assertEquals(4294967295L, maxDword)
    }

    @Test
    fun `BOOL Win32 semantics - zero is FALSE`() {
        val winFalse: BOOL = 0
        val winTrue: BOOL = 1
        // Verification of the Win32 semantics: 0 = FALSE
        assertEquals(0, winFalse)
        assertEquals(1, winTrue)
    }

    @Test
    fun `Win32Runtime is accessible`() {
        val runtime: Win32Runtime = Win32Runtime
        assertNotNull(runtime)
    }

    @Test
    fun `Win32Runtime library constants are defined`() {
        assertEquals("user32", Win32Runtime.USER32_LIB)
        assertEquals("kernel32", Win32Runtime.KERNEL32_LIB)
        assertEquals("gdi32", Win32Runtime.GDI32_LIB)
    }

    @Test
    fun `Win32Runtime isAvailable returns false outside Windows`() {
        // This test runs on macOS/Linux in CI — isAvailable must be false
        // (on Windows, the test would be skipped or inverted)
        if (!System.getProperty("os.name", "").startsWith("Windows")) {
            assertFalse(Win32Runtime.isAvailable)
        }
    }
}
