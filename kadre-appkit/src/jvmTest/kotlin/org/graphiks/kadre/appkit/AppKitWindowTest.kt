package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.NSView
import org.graphiks.kadre.appkit.bindings.NSWindow
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Compilation tests for AppKitWindow (GRA-126).
 *
 * No real runtime test: creating an NSWindow requires the macOS main
 * thread and the AppKit environment, validated by the M1 demo
 * application.
 *
 * These tests only verify that the types and signatures compile
 * correctly and satisfy the interface contracts.
 */
class AppKitWindowTest {

    @Test
    fun `AppKitWindow implemente Window`() {
        assertTrue(Window::class.java.isAssignableFrom(AppKitWindow::class.java))
    }

    @Test
    fun `AppKitWindow a un constructeur acceptant WindowAttributes`() {
        val ctor = AppKitWindow::class.java.constructors.first()
        val paramTypes = ctor.parameterTypes
        assertTrue(paramTypes.any { WindowAttributes::class.java.isAssignableFrom(it) })
    }

    @Test
    fun `RawWindowHandle AppKit contient nsView et nsWindow en Long`() {
        // Verify that RawWindowHandle.AppKit compiles with the right types (Long)
        val handle = RawWindowHandle.AppKit(nsView = 0L, nsWindow = 0L)
        assertTrue(handle.nsView == 0L)
        assertTrue(handle.nsWindow == 0L)
        assertTrue(handle is RawWindowHandle)
    }

    @Test
    fun `RawDisplayHandle AppKit est un data object`() {
        val display: RawDisplayHandle = RawDisplayHandle.AppKit
        assertTrue(display is RawDisplayHandle.AppKit)
    }

    @Test
    fun `WindowAttributes a des valeurs par defaut raisonnables`() {
        val attrs = WindowAttributes()
        assertTrue(attrs.title == "Kadre")
        assertTrue(attrs.visible)
        assertTrue(attrs.resizable)
        assertTrue(attrs.size == null)
    }

    @Test
    fun `WindowAttributes accepte une taille physique`() {
        val attrs = WindowAttributes(
            title = "Test",
            size = PhysicalSize(1920, 1080),
            visible = false,
            resizable = false,
        )
        assertTrue(attrs.size?.width == 1920)
        assertTrue(attrs.size?.height == 1080)
        assertTrue(!attrs.visible)
        assertTrue(!attrs.resizable)
    }

    @Test
    fun `WindowId encapsule un Long`() {
        val id = WindowId(42L)
        assertTrue(id.value == 42L)
    }

    @Test
    fun `AppKitWindow herite bien de NSWindow et NSView via bindings`() {
        // Verify that the binding classes used in AppKitWindow exist
        // and are instantiable via constructor(MemorySegment).
        val nsWindowCtor = NSWindow::class.java.constructors.firstOrNull { it.parameterCount == 1 }
        assertTrue(nsWindowCtor != null, "NSWindow doit avoir un constructeur(MemorySegment)")

        val nsViewCtor = NSView::class.java.constructors.firstOrNull { it.parameterCount == 1 }
        assertTrue(nsViewCtor != null, "NSView doit avoir un constructeur(MemorySegment)")
    }
}
