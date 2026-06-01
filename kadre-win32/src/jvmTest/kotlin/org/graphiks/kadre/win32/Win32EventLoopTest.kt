/**
 * Unit tests for Win32EventLoop.
 *
 * These tests verify the pure Kotlin logic of Win32EventLoop (ControlFlow management,
 * lifecycle, isExiting) without triggering real Win32 FFM calls.
 *
 * On macOS/Linux: all tests run because the tested logic is independent
 * of the FFM bindings (peekMessageW, getMessageW are null → fallback branches).
 *
 * On Windows: the additional runApp tests can be enabled
 * (see the `if (!isWindows()) return` block).
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.DeviceId
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/** Returns true if the test runs on Windows. */
private fun isWindows(): Boolean =
    System.getProperty("os.name", "").contains("Windows", ignoreCase = true)

// ── Minimal ApplicationHandler implementation for the tests ───────────────────

/**
 * Test ApplicationHandler that records the received callbacks.
 */
private class TestApplicationHandler(
    private val onResumed: (ActiveEventLoop) -> Unit = {},
    private val onSuspended: (ActiveEventLoop) -> Unit = {},
    private val onNewEvents: (ActiveEventLoop, StartCause) -> Unit = { _, _ -> },
    private val onAboutToWait: (ActiveEventLoop) -> Unit = {},
    private val onCanCreateSurfaces: (ActiveEventLoop) -> Unit = {},
) : ApplicationHandler {
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = onCanCreateSurfaces(eventLoop)
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {}
    override fun resumed(eventLoop: ActiveEventLoop) = onResumed(eventLoop)
    override fun suspended(eventLoop: ActiveEventLoop) = onSuspended(eventLoop)
    override fun newEvents(eventLoop: ActiveEventLoop, startCause: StartCause) = onNewEvents(eventLoop, startCause)
    override fun aboutToWait(eventLoop: ActiveEventLoop) = onAboutToWait(eventLoop)
}

// ── Win32EventLoop tests ──────────────────────────────────────────────────────

class Win32EventLoopTest {

    // ── ControlFlow ───────────────────────────────────────────────────────────

    @Test
    fun `initial ControlFlow is Wait`() {
        val loop = Win32EventLoop()
        assertTrue(loop.controlFlow is ControlFlow.Wait)
    }

    @Test
    fun `setControlFlow Poll changes the mode`() {
        val loop = Win32EventLoop()
        loop.setControlFlow(ControlFlow.Poll)
        assertTrue(loop.controlFlow is ControlFlow.Poll)
    }

    @Test
    fun `setControlFlow WaitUntil changes the mode with the target instant`() {
        val loop = Win32EventLoop()
        val instant = System.currentTimeMillis() + 1000L
        loop.setControlFlow(ControlFlow.WaitUntil(instant))
        val cf = loop.controlFlow
        assertTrue(cf is ControlFlow.WaitUntil)
        assertEquals(instant, cf.instant)
    }

    @Test
    fun `setControlFlow Wait returns to blocking wait mode`() {
        val loop = Win32EventLoop()
        loop.setControlFlow(ControlFlow.Poll)
        loop.setControlFlow(ControlFlow.Wait)
        assertTrue(loop.controlFlow is ControlFlow.Wait)
    }

    // ── isExiting ─────────────────────────────────────────────────────────────

    @Test
    fun `isExiting is false at startup`() {
        val loop = Win32EventLoop()
        assertFalse(loop.isExiting)
    }

    @Test
    fun `exit sets isExiting to true`() {
        val loop = Win32EventLoop()
        loop.exit()
        assertTrue(loop.isExiting)
    }

    // ── createProxy ──────────────────────────────────────────────────────────

    @Test
    fun `createProxy returns a non null proxy`() {
        val loop = Win32EventLoop()
        val proxy = loop.createProxy()
        assertNotNull(proxy)
    }

    @Test
    fun `proxy wakeUp does not throw an exception on non-Windows`() {
        if (isWindows()) return
        val loop = Win32EventLoop()
        val proxy = loop.createProxy()
        // Must not throw an exception on macOS/Linux (PostThreadMessageW is null)
        proxy.wakeUp()
    }

    // ── Message loop constants ────────────────────────────────────────────────

    @Test
    fun `PM_REMOVE has the value 0x0001`() {
        assertEquals(0x0001, PM_REMOVE)
    }

    @Test
    fun `PM_NOREMOVE has the value 0x0000`() {
        assertEquals(0x0000, PM_NOREMOVE)
    }

    @Test
    fun `QS_ALLINPUT has the value 0x04FF`() {
        assertEquals(0x04FF, QS_ALLINPUT)
    }

    @Test
    fun `MWMO_INPUTAVAILABLE has the value 0x0004`() {
        assertEquals(0x0004, MWMO_INPUTAVAILABLE)
    }

    @Test
    fun `WAIT_OBJECT_0 has the value 0`() {
        assertEquals(0, WAIT_OBJECT_0)
    }

    @Test
    fun `WAIT_TIMEOUT has the value 0x102`() {
        assertEquals(0x00000102, WAIT_TIMEOUT)
    }

    // ── MSG Layout ────────────────────────────────────────────────────────────

    @Test
    fun `MsgLayout SIZEOF is 48 bytes`() {
        assertEquals(48, MsgLayout.SIZEOF)
    }

    @Test
    fun `MsgLayout ALIGN is 8`() {
        assertEquals(8, MsgLayout.ALIGN)
    }

    @Test
    fun `MsgLayout LAYOUT byte size matches SIZEOF`() {
        assertEquals(MsgLayout.SIZEOF.toLong(), MsgLayout.LAYOUT.byteSize())
    }

    @Test
    fun `MsgLayout offsets correct for Win64 ABI`() {
        assertEquals(0,  MsgLayout.OFFSET_HWND)
        assertEquals(8,  MsgLayout.OFFSET_MESSAGE)
        assertEquals(16, MsgLayout.OFFSET_WPARAM)
        assertEquals(24, MsgLayout.OFFSET_LPARAM)
        assertEquals(32, MsgLayout.OFFSET_TIME)
        assertEquals(36, MsgLayout.OFFSET_PT_X)
        assertEquals(40, MsgLayout.OFFSET_PT_Y)
    }

    // ── FFM bindings — null on non-Windows ────────────────────────────────────

    @Test
    fun `peekMessageW is null on non-Windows platform`() {
        if (isWindows()) return
        assertEquals(null, peekMessageW)
    }

    @Test
    fun `getMessageW is null on non-Windows platform`() {
        if (isWindows()) return
        assertEquals(null, getMessageW)
    }

    @Test
    fun `translateMessage is null on non-Windows platform`() {
        if (isWindows()) return
        assertEquals(null, translateMessage)
    }

    @Test
    fun `dispatchMessageW is null on non-Windows platform`() {
        if (isWindows()) return
        assertEquals(null, dispatchMessageW)
    }

    @Test
    fun `msgWaitForMultipleObjectsEx is null on non-Windows platform`() {
        if (isWindows()) return
        assertEquals(null, msgWaitForMultipleObjectsEx)
    }

    // ── runApp — singleton lock ───────────────────────────────────────────────

    @Test
    fun `win32Running is false before runApp`() {
        // If another test forgot to reset the lock, this test will detect it
        assertFalse(win32Running.get(), "win32Running must be false at test startup")
    }
}
