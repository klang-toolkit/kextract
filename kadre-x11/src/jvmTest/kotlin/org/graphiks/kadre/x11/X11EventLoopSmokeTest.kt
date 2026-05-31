/**
 * Tests de smoke pour X11EventLoop et X11EventLoopProxy.
 *
 * Vérifie :
 * - x11Running démarre à false.
 * - runApp active/désactive le flag x11Running (handler qui quitte immédiatement).
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
        // Le flag global doit être false au démarrage (ou après un runApp terminé)
        // Note : si un autre test a laissé le flag à true, ce test échouera —
        // mais x11Running est remis à false dans le finally de runApp.
        assertFalse(x11Running.get(), "x11Running doit être false hors boucle active")
    }

    @Test
    fun `runApp reste un no-op sur non-Linux`() {
        // Sur macOS/Windows, libX11 est null → runApp retourne immédiatement
        if (libX11 != null) return // Skip sur Linux (nécessite un serveur X)

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

        // Créer un proxy avec un displayPtr fictif et une loop vide
        val fakeLoop = X11EventLoop(displayPtr = 0L, screen = 0)
        val proxy = X11EventLoopProxy(fakeLoop, displayPtr = 0L)

        // Ne doit pas lever d'exception
        proxy.wakeUp()
    }
}
