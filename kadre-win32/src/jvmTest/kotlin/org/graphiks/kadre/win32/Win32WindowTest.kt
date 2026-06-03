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
import org.graphiks.kadre.core.WindowButtons
import org.graphiks.kadre.core.WindowLevel
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.Icon
import kotlin.test.Test
import kotlin.test.assertContentEquals
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
    fun `enabled window buttons update minimize and maximize style bits`() {
        val base = WS_OVERLAPPEDWINDOW

        val closeOnly = win32StyleWithEnabledButtons(base, WindowButtons.CLOSE)
        assertEquals(0, closeOnly and WS_MINIMIZEBOX)
        assertEquals(0, closeOnly and WS_MAXIMIZEBOX)

        val minimizeOnly = win32StyleWithEnabledButtons(base, WindowButtons.MINIMIZE)
        assertTrue((minimizeOnly and WS_MINIMIZEBOX) != 0)
        assertEquals(0, minimizeOnly and WS_MAXIMIZEBOX)

        val maximizeOnly = win32StyleWithEnabledButtons(base, WindowButtons.MAXIMIZE)
        assertEquals(0, maximizeOnly and WS_MINIMIZEBOX)
        assertTrue((maximizeOnly and WS_MAXIMIZEBOX) != 0)

        val all = win32StyleWithEnabledButtons(closeOnly, WindowButtons.ALL)
        assertTrue((all and WS_MINIMIZEBOX) != 0)
        assertTrue((all and WS_MAXIMIZEBOX) != 0)

        val undecorated = win32StyleWithEnabledButtons(base, WindowButtons.ALL, decorated = false)
        assertEquals(0, undecorated and WS_MINIMIZEBOX)
        assertEquals(0, undecorated and WS_MAXIMIZEBOX)
    }

    @Test
    fun `enabled window buttons use winit close menu flags`() {
        assertEquals(MF_BYCOMMAND or MF_ENABLED, win32CloseMenuState(enabled = true))
        assertEquals(MF_BYCOMMAND or MF_DISABLED, win32CloseMenuState(enabled = false))
    }

    @Test
    fun `style updates preserve current position`() {
        assertTrue((WIN32_STYLE_UPDATE_FLAGS and SWP_NOMOVE) != 0)
        assertTrue((WIN32_STYLE_UPDATE_FLAGS and SWP_FRAMECHANGED) != 0)
    }

    @Test
    fun `Win32 focus follows winit visible non-minimized non-foreground guard`() {
        assertTrue(win32ShouldFocusWindow(isVisible = true, isMinimized = false, isForeground = false))
        assertTrue(!win32ShouldFocusWindow(isVisible = false, isMinimized = false, isForeground = false))
        assertTrue(!win32ShouldFocusWindow(isVisible = true, isMinimized = true, isForeground = false))
        assertTrue(!win32ShouldFocusWindow(isVisible = true, isMinimized = false, isForeground = true))
    }

    @Test
    fun `Win32 hasFocus requires active non-client area and keyboard focus`() {
        val hwnd = 0xCAFE_BABEL

        Win32FocusState.unregister(hwnd)
        Win32FocusState.register(hwnd)
        assertTrue(!Win32FocusState.hasActiveFocus(hwnd))

        assertNull(Win32FocusState.setFocused(hwnd, true))
        assertTrue(!Win32FocusState.hasActiveFocus(hwnd))

        assertEquals(true, Win32FocusState.setActive(hwnd, true))
        assertTrue(Win32FocusState.hasActiveFocus(hwnd))

        assertNull(Win32FocusState.setActive(hwnd, true))
        assertTrue(Win32FocusState.hasActiveFocus(hwnd))

        assertEquals(false, Win32FocusState.setActive(hwnd, false))
        assertTrue(!Win32FocusState.hasActiveFocus(hwnd))

        assertNull(Win32FocusState.setFocused(hwnd, false))
        assertTrue(!Win32FocusState.hasActiveFocus(hwnd))

        Win32FocusState.unregister(hwnd)
        assertTrue(!Win32FocusState.hasActiveFocus(hwnd))
    }

    @Test
    fun `window levels map to winit Win32 insert-after handles`() {
        assertEquals(HWND_TOPMOST.address(), win32WindowLevelInsertAfter(WindowLevel.AlwaysOnTop).address())
        assertEquals(HWND_NOTOPMOST.address(), win32WindowLevelInsertAfter(WindowLevel.Normal).address())
        assertEquals(HWND_BOTTOM.address(), win32WindowLevelInsertAfter(WindowLevel.AlwaysOnBottom).address())
    }

    @Test
    fun `initial extended style includes layered only for transparent windows`() {
        assertEquals(WS_EX_APPWINDOW, win32InitialExtendedStyle(transparent = false))
        assertEquals(WS_EX_APPWINDOW or WS_EX_LAYERED, win32InitialExtendedStyle(transparent = true))
    }

    @Test
    fun `DWM blur behind layout matches Win64 ABI and winit flags`() {
        assertEquals(DWM_BB_ENABLE or DWM_BB_BLURREGION, win32TransparentBlurBehindFlags())
        assertEquals(24L, DWM_BLURBEHIND_SIZE)
        assertEquals(8L, DWM_BLURBEHIND_ALIGN)
        assertEquals(0L, DWM_BLURBEHIND_OFFSET_DW_FLAGS)
        assertEquals(4L, DWM_BLURBEHIND_OFFSET_F_ENABLE)
        assertEquals(8L, DWM_BLURBEHIND_OFFSET_H_RGN_BLUR)
        assertEquals(16L, DWM_BLURBEHIND_OFFSET_F_TRANSITION_ON_MAXIMIZED)
    }

    @Test
    fun `runtime blur setter is a no native update like winit`() {
        assertEquals(false, win32RuntimeBlurRequiresNativeUpdate(true))
        assertEquals(false, win32RuntimeBlurRequiresNativeUpdate(false))
    }

    @Test
    fun `window icon buffers convert RGBA to Win32 BGRA and inverted alpha mask`() {
        val icon = Icon(
            rgba = byteArrayOf(
                0x11, 0x22, 0x33, 0xFF.toByte(),
                0x44, 0x55, 0x66, 0x00,
            ),
            width = 2,
            height = 1,
        )

        val buffers = win32IconBuffers(icon)

        assertNotNull(buffers)
        assertContentEquals(
            byteArrayOf(
                0x33, 0x22, 0x11, 0xFF.toByte(),
                0x66, 0x55, 0x44, 0x00,
            ),
            buffers.bgra,
        )
        assertContentEquals(byteArrayOf(0x00, 0x01), buffers.andMask)
    }

    @Test
    fun `Win32 INPUT layout matches supported 64-bit JVM target`() {
        assertEquals(40L, INPUT_SIZE)
        assertEquals(8L, INPUT_ALIGN)
        assertEquals(0L, INPUT_OFFSET_TYPE)
        assertEquals(8L, INPUT_OFFSET_KI_WVK)
        assertEquals(10L, INPUT_OFFSET_KI_WSCAN)
        assertEquals(12L, INPUT_OFFSET_KI_DWFLAGS)
        assertEquals(16L, INPUT_OFFSET_KI_TIME)
        assertEquals(24L, INPUT_OFFSET_KI_DWEXTRAINFO)
        assertEquals(1, INPUT_KEYBOARD)
        assertEquals(0xA4, VK_LMENU)
        assertEquals(0x12, VK_MENU)
        assertEquals(0, MAPVK_VK_TO_VSC)
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
