/**
 * Smoke tests for X11EventLoop and X11EventLoopProxy.
 *
 * Verifies:
 * - x11Running starts at false.
 * - runApp enables/disables the x11Running flag (handler that quits immediately).
 * - X11EventLoopProxy.wakeUp() is safe on non-Linux (no-op).
 *
 * X11EventLoop smoke tests.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.ffi.x11.*
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import java.lang.foreign.Arena
import java.lang.foreign.ValueLayout
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class X11EventLoopSmokeTest {

    @Test
    fun `x11Running starts at false`() {
        // The global flag must be false at startup (or after a finished runApp)
        // Note: if another test left the flag at true, this test will fail —
        // but x11Running is reset to false in runApp's finally block.
        assertFalse(x11Running.get(), "x11Running must be false outside an active loop")
    }

    @Test
    fun `runApp stays a no-op on non-Linux`() {
        // On macOS/Windows, libX11 is null → runApp returns immediately
        if (libX11 != null) return // Skip on Linux (requires an X server)

        var canCreateSurfacesCalled = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
                canCreateSurfacesCalled = true
                eventLoop.exit()
            }
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {}
        }

        // runApp must not throw an exception
        runApp(handler)

        // On non-Linux: libX11 null → we do not enter the loop
        assertFalse(canCreateSurfacesCalled,
            "canCreateSurfaces must not be called if libX11 is absent")
        assertFalse(x11Running.get(),
            "x11Running must be false after runApp()")
    }

    @Test
    fun `x11Running is reset to false after runApp on non-Linux`() {
        if (libX11 != null) return // Skip on Linux

        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {}
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {}
        }

        assertFalse(x11Running.get())
        runApp(handler)
        assertFalse(x11Running.get(),
            "x11Running must be false after runApp() on non-Linux")
    }

    @Test
    fun `runApp throws IllegalStateException if already active`() {
        if (libX11 != null) return // Skip on Linux (non thread-safe flag manipulation in test)

        // Simulate an active loop
        x11Running.set(true)
        try {
            var threw = false
            try {
                runApp(object : ApplicationHandler {
                    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {}
                    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {}
                })
            } catch (e: IllegalStateException) {
                threw = true
            }
            assertTrue(threw, "runApp must throw IllegalStateException if x11Running is true")
        } finally {
            x11Running.set(false)
        }
    }

    @Test
    fun `X11EventLoopProxy wakeUp is a no-op if xSendEvent is absent`() {
        // On macOS/Windows, xSendEvent is null → wakeUp must not throw an exception
        if (libX11 != null) return // Skip on Linux

        // Create a proxy with a fictitious displayPtr and an empty loop
        val fakeLoop = X11EventLoop(displayPtr = 0L, screen = 0)
        val proxy = X11EventLoopProxy(fakeLoop, displayPtr = 0L)

        // Must not throw an exception
        proxy.wakeUp()
    }

    @Test
    fun `X11 event window xid uses LP64 offsets per event type`() {
        Arena.ofConfined().use { arena ->
            val event = arena.allocate(96L, 8L)
            event.set(ValueLayout.JAVA_LONG, XANY_WINDOW_OFFSET, 10L)
            event.set(ValueLayout.JAVA_LONG, 40L, 20L)

            assertEquals(10L, x11EventWindowXid(event, FocusIn))
            assertEquals(10L, x11EventWindowXid(event, VisibilityNotify))
            assertEquals(10L, x11EventWindowXid(event, ClientMessage))
            assertEquals(20L, x11EventWindowXid(event, ConfigureNotify))
            assertEquals(20L, x11EventWindowXid(event, DestroyNotify))
        }
    }

    @Test
    fun `X11 client message LP64 offsets are canonical`() {
        Arena.ofConfined().use { arena ->
            val display = arena.allocate(8L, 8L)
            val event = arena.allocate(96L, 8L)
            event.set(ValueLayout.JAVA_INT, XCLIENT_SEND_EVENT_OFFSET, 1)
            event.set(ValueLayout.ADDRESS, XCLIENT_DISPLAY_OFFSET, display)
            event.set(ValueLayout.JAVA_LONG, XCLIENT_WINDOW_OFFSET, 10L)
            event.set(ValueLayout.JAVA_LONG, XCLIENT_MESSAGE_TYPE_OFFSET, 20L)
            event.set(ValueLayout.JAVA_INT, XCLIENT_FORMAT_OFFSET, 32)
            event.set(ValueLayout.JAVA_LONG, XCLIENT_DATA_L0_OFFSET, 30L)

            assertEquals(1, event.get(ValueLayout.JAVA_INT, 16L))
            assertEquals(display.address(), event.get(ValueLayout.ADDRESS, 24L).address())
            assertEquals(10L, event.get(ValueLayout.JAVA_LONG, 32L))
            assertEquals(20L, event.get(ValueLayout.JAVA_LONG, 40L))
            assertEquals(32, event.get(ValueLayout.JAVA_INT, 48L))
            assertEquals(30L, event.get(ValueLayout.JAVA_LONG, 56L))
        }
    }
}
