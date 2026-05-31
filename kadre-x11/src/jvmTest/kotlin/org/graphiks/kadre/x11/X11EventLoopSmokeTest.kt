/**
 * Tests de smoke pour X11EventLoop et X11EventLoopProxy.
 *
 * Verifies:
 * - x11Running starts at false.
 * - runApp enables/disables the x11Running flag (handler that quits immediately).
 * - X11EventLoopProxy.wakeUp() est safe sur non-Linux (no-op).
 *
 * X11EventLoop smoke tests.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.WindowId
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class X11EventLoopSmokeTest {

    @Test
    fun `x11Running commence a false`() {
        // The global flag must be false at startup (or after a finished runApp)
        // Note: if another test left the flag at true, this test will fail —
        // but x11Running is reset to false in runApp's finally block.
        assertFalse(x11Running.get(), "x11Running doit être false hors boucle active")
    }

    @Test
    fun `runApp reste un no-op sur non-Linux`() {
        // On macOS/Windows, libX11 is null → runApp returns immediately
        if (libX11 != null) return // Skip on Linux (requires an X server)

        var canCreateSurfacesCalled = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
                canCreateSurfacesCalled = true
                eventLoop.exit()
            }
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {}
        }

        // runApp ne doit pas lever d'exception
        runApp(handler)

        // Sur non-Linux : libX11 null → on ne rentre pas dans la boucle
        assertFalse(canCreateSurfacesCalled,
            "canCreateSurfaces ne doit pas être appelé si libX11 est absent")
        assertFalse(x11Running.get(),
            "x11Running doit être false après runApp()")
    }

    @Test
    fun `x11Running est remis a false apres runApp sur non-Linux`() {
        if (libX11 != null) return // Skip sur Linux

        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {}
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {}
        }

        assertFalse(x11Running.get())
        runApp(handler)
        assertFalse(x11Running.get(),
            "x11Running doit être false après runApp() sur non-Linux")
    }

    @Test
    fun `runApp leve IllegalStateException si deja active`() {
        if (libX11 != null) return // Skip sur Linux (manipulation de flag non thread-safe en test)

        // Simuler une boucle active
        x11Running.set(true)
        try {
            var threw = false
            try {
                runApp(object : ApplicationHandler {
                    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {}
                    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {}
                })
            } catch (e: IllegalStateException) {
                threw = true
            }
            assertTrue(threw, "runApp doit lever IllegalStateException si x11Running est true")
        } finally {
            x11Running.set(false)
        }
    }

    @Test
    fun `X11EventLoopProxy wakeUp est un no-op si xSendEvent absent`() {
        // Sur macOS/Windows, xSendEvent est null → wakeUp ne doit pas lever d'exception
        if (libX11 != null) return // Skip sur Linux

        // Create a proxy with a fictitious displayPtr and an empty loop
        val fakeLoop = X11EventLoop(displayPtr = 0L, screen = 0)
        val proxy = X11EventLoopProxy(fakeLoop, displayPtr = 0L)

        // Ne doit pas lever d'exception
        proxy.wakeUp()
    }
}
