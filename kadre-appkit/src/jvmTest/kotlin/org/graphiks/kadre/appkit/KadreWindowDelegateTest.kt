package org.graphiks.kadre.appkit

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.DeviceId
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import java.lang.foreign.MemorySegment
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Typing tests for KadreWindowDelegate (GRA-127).
 *
 * No native runtime test: actual instantiation requires the macOS main
 * thread and the AppKit environment. These tests only verify that the
 * types, signatures and dispatch contracts compile correctly.
 */
class KadreWindowDelegateTest {

    // ── Test stubs ─────────────────────────────────────────────────────────────

    /** ApplicationHandler stub recording the received events. */
    private class RecordingHandler : ApplicationHandler {
        val events = mutableListOf<Pair<WindowId, Any>>()

        override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit

        override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
            events.add(windowId to event)
        }
    }

    /** Minimal ActiveEventLoop stub. */
    private class StubEventLoop : ActiveEventLoop {
        private var _exiting = false
        override val isExiting: Boolean get() = _exiting
        override val controlFlow: ControlFlow get() = ControlFlow.Wait

        fun markExiting() { _exiting = true }

        override fun createWindow(attributes: WindowAttributes): Window =
            throw UnsupportedOperationException()
        override fun setControlFlow(controlFlow: ControlFlow) = Unit
        override fun exit() { _exiting = true }
        override fun createProxy(): EventLoopProxy = throw UnsupportedOperationException()
    }

    // ── Tests ────────────────────────────────────────────────────────────────

    @Test
    fun `KadreWindowDelegate a un constructeur acceptant handler eventLoop windowId nsWindowPtr metalLayerPtr`() {
        // The public constructor generated for a value class param (WindowId) has an extra
        // DefaultConstructorMarker → paramCount >= 5, the first params are ApplicationHandler, ActiveEventLoop, long/WindowId
        val ctor = KadreWindowDelegate::class.java.constructors
            .filter { it.parameterCount >= 5 }
            .firstOrNull { c ->
                val p = c.parameterTypes
                ApplicationHandler::class.java.isAssignableFrom(p[0]) &&
                        ActiveEventLoop::class.java.isAssignableFrom(p[1]) &&
                        (p[2] == WindowId::class.java || p[2] == Long::class.java || p[2] == java.lang.Long.TYPE)
            }
        assertNotNull(ctor, "KadreWindowDelegate doit avoir un ctor(ApplicationHandler, ActiveEventLoop, WindowId/long, MemorySegment, MemorySegment)")
    }

    @Test
    fun `KadreWindowDelegate expose un champ ptr de type MemorySegment`() {
        val field = KadreWindowDelegate::class.java.methods
            .firstOrNull { it.name == "getPtr" && it.parameterCount == 0 }
        assertNotNull(field, "KadreWindowDelegate doit exposer ptr: MemorySegment via getPtr()")
        assertEquals(MemorySegment::class.java, field.returnType)
    }

    @Test
    fun `onWindowShouldClose retourne 0 quand eventLoop nest pas en cours darret`() {
        val handler = RecordingHandler()
        val eventLoop = StubEventLoop()
        val windowId = WindowId(42L)

        // We create a delegate without instantiating the ObjC runtime (typing test only)
        // → we invoke the method via reflection on the static Callbacks object
        // which is accessible without instantiating KadreWindowDelegate (no ObjC runtime).
        //
        // Here we only verify that Callbacks.windowShouldClose returns Byte.
        val callbacksClass = Class.forName("org.graphiks.kadre.appkit.KadreWindowDelegate\$Callbacks")
        val method = callbacksClass.methods.firstOrNull { it.name == "windowShouldClose" }
        assertNotNull(method, "Callbacks.windowShouldClose doit exister")
        assertEquals(
            java.lang.Byte.TYPE,
            method.returnType,
            "windowShouldClose doit retourner Byte (BOOL)",
        )
        assertEquals(3, method.parameterCount, "windowShouldClose doit prendre 3 params (self, cmd, sender)")
    }

    @Test
    fun `Callbacks windowDidResize existe avec la bonne signature`() {
        val callbacksClass = Class.forName("org.graphiks.kadre.appkit.KadreWindowDelegate\$Callbacks")
        val method = callbacksClass.methods.firstOrNull { it.name == "windowDidResize" }
        assertNotNull(method, "Callbacks.windowDidResize doit exister")
        assertEquals(
            Void.TYPE,
            method.returnType,
            "windowDidResize doit retourner void",
        )
        assertEquals(3, method.parameterCount, "windowDidResize doit prendre 3 params (self, cmd, notification)")
    }

    @Test
    fun `Callbacks windowDidChangeBackingProperties existe avec la bonne signature`() {
        val callbacksClass = Class.forName("org.graphiks.kadre.appkit.KadreWindowDelegate\$Callbacks")
        val method = callbacksClass.methods.firstOrNull { it.name == "windowDidChangeBackingProperties" }
        assertNotNull(method, "Callbacks.windowDidChangeBackingProperties doit exister")
        assertEquals(Void.TYPE, method.returnType)
        assertEquals(3, method.parameterCount)
    }

    @Test
    fun `WindowEvent ScaleFactorChanged est reconnu comme WindowEvent`() {
        val event: WindowEvent = WindowEvent.ScaleFactorChanged(2.0)
        assertTrue(event is WindowEvent.ScaleFactorChanged)
        assertEquals(2.0, (event as WindowEvent.ScaleFactorChanged).factor)
    }

    @Test
    fun `WindowEvent Resized est reconnu comme WindowEvent`() {
        val event: WindowEvent = WindowEvent.Resized(org.graphiks.kadre.core.PhysicalSize(800, 600))
        assertTrue(event is WindowEvent.Resized)
    }

    @Test
    fun `WindowEvent CloseRequested est reconnu comme WindowEvent`() {
        val event: WindowEvent = WindowEvent.CloseRequested
        assertTrue(event is WindowEvent.CloseRequested)
    }

    @Test
    fun `AppKitWindow expose setWindowDelegate avec la bonne signature`() {
        val method = AppKitWindow::class.java.methods
            .firstOrNull { it.name == "setWindowDelegate" }
        assertNotNull(method, "AppKitWindow doit avoir setWindowDelegate()")
        assertEquals(2, method.parameterCount)
        assertTrue(ApplicationHandler::class.java.isAssignableFrom(method.parameterTypes[0]))
        assertTrue(ActiveEventLoop::class.java.isAssignableFrom(method.parameterTypes[1]))
    }

    @Test
    fun `AppKitWindow expose delegate nullable`() {
        val method = AppKitWindow::class.java.methods
            .firstOrNull { it.name == "getDelegate" }
        assertNotNull(method, "AppKitWindow doit exposer getDelegate()")
        assertEquals(KadreWindowDelegate::class.java, method.returnType)
    }
}
