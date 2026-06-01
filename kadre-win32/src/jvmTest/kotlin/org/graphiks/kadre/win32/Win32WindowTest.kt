/**
 * Tests for Win32Window.
 *
 * These tests verify the structure of the Win32 bindings and the expected
 * behaviors on non-Windows platforms (null values, automatic skip).
 *
 * On macOS/Linux: the tests related to window creation are skipped
 * automatically because the FFM MethodHandles are null (user32.dll not found).
 *
 * On Windows: the full tests run and validate the creation of a
 * native window via RegisterClassExW + CreateWindowExW.
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
 * Returns true if the test runs on Windows.
 *
 * Used to skip the tests requiring user32.dll on macOS/Linux.
 */
private fun isWindows(): Boolean =
    System.getProperty("os.name", "").contains("Windows", ignoreCase = true)

class Win32WindowTest {

    // ── Tests compiling on all platforms ───────────────────────────────────────

    @Test
    fun `WndClassExW SIZEOF is 80 bytes`() {
        assertEquals(80, WndClassExW.SIZEOF)
    }

    @Test
    fun `WndClassExW ALIGN is 8`() {
        assertEquals(8, WndClassExW.ALIGN)
    }

    @Test
    fun `WndClassExW offsets correct for Win64 ABI`() {
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
    fun `WndClassExW LAYOUT byte size matches SIZEOF`() {
        assertEquals(WndClassExW.SIZEOF.toLong(), WndClassExW.LAYOUT.byteSize())
    }

    @Test
    fun `Win32 constants have the expected values`() {
        assertEquals(0x00CF0000, WS_OVERLAPPEDWINDOW)
        assertEquals(0x00040000, WS_EX_APPWINDOW)
        assertEquals(5, SW_SHOW)
        assertEquals(0, SW_HIDE)
        assertEquals(0x0003, CS_HREDRAW_VREDRAW)
        assertEquals(0x0002, WM_DESTROY)
    }

    @Test
    fun `user32 and kernel32 are null on non-Windows platform`() {
        if (isWindows()) return  // skip on Windows (the libs exist)
        // On macOS/Linux, the lazy loaders must return null
        assertNull(user32)
        assertNull(kernel32)
    }

    @Test
    fun `the FFM MethodHandles are null on non-Windows platform`() {
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
    fun `Win32Window create returns null on non-Windows platform`() {
        if (isWindows()) return
        val attrs = WindowAttributes(title = "Test", visible = false)
        val window = Win32Window.create(attrs)
        assertNull(window, "create() must return null on macOS/Linux (user32.dll missing)")
    }

    @Test
    fun `Win32WndProcArena arena is accessible`() {
        // The arena itself can be created on any platform
        assertNotNull(Win32WndProcArena.arena)
    }

    // ── Tests run only on Windows ─────────────────────────────────────────────

    @Test
    fun `Win32Window create produces a valid window on Windows`() {
        if (!isWindows()) return  // skip on macOS/Linux

        val attrs = WindowAttributes(
            title = "Test Kadre Win32",
            visible = false,  // do not display in CI
        )
        val window = Win32Window.create(attrs)
        assertNotNull(window, "create() must succeed on Windows")

        // Check the handles
        val rawHandle = window.rawWindowHandle
        assertTrue(rawHandle is RawWindowHandle.Win32, "rawWindowHandle must be Win32")
        assertTrue(rawHandle.hwnd != 0L, "HWND must not be null")
        assertTrue(rawHandle.hinstance != 0L, "HINSTANCE must not be null")

        val displayHandle = window.rawDisplayHandle
        assertTrue(displayHandle is RawDisplayHandle.Win32, "rawDisplayHandle must be Win32")
        assertTrue((displayHandle).hinstance != 0L)

        // Check the basic properties
        assertEquals(rawHandle.hwnd, window.id.value)
        assertEquals(1.0, window.scaleFactor)
        assertNotNull(window.innerSize)
        assertNotNull(window.outerSize)

        // Cleanup
        window.close()
    }

    @Test
    fun `Win32Window setTitle does not throw an exception on Windows`() {
        if (!isWindows()) return

        val attrs = WindowAttributes(title = "Initial", visible = false)
        val window = Win32Window.create(attrs) ?: return

        // Must not throw an exception
        window.setTitle("Nouveau titre")

        window.close()
    }

    @Test
    fun `Win32Window setVisible does not throw an exception on Windows`() {
        if (!isWindows()) return

        val attrs = WindowAttributes(title = "Test setVisible", visible = false)
        val window = Win32Window.create(attrs) ?: return

        window.setVisible(false)  // already hidden
        window.setVisible(true)   // show
        window.setVisible(false)  // hide again

        window.close()
    }
}
