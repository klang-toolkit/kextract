package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.NSView
import org.graphiks.kadre.appkit.bindings.NSWindow
import org.graphiks.kadre.appkit.bindings.NSWindowButton
import org.graphiks.kadre.appkit.bindings.NSWindowSharingType
import org.graphiks.kadre.appkit.bindings.NSWindowStyleMask
import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.RequestError
import org.graphiks.kadre.core.UserAttentionType
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowButtons
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.WindowLevel
import org.graphiks.kadre.core.WindowRequestResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
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
    fun `AppKitWindow implements Window`() {
        assertTrue(Window::class.java.isAssignableFrom(AppKitWindow::class.java))
    }

    @Test
    fun `AppKitWindow has a constructor accepting WindowAttributes`() {
        val ctor = AppKitWindow::class.java.constructors.first()
        val paramTypes = ctor.parameterTypes
        assertTrue(paramTypes.any { WindowAttributes::class.java.isAssignableFrom(it) })
    }

    @Test
    fun `RawWindowHandle AppKit contains nsView and nsWindow as Long`() {
        // Verify that RawWindowHandle.AppKit compiles with the right types (Long)
        val handle = RawWindowHandle.AppKit(nsView = 0L, nsWindow = 0L)
        assertTrue(handle.nsView == 0L)
        assertTrue(handle.nsWindow == 0L)
        assertTrue(handle is RawWindowHandle)
    }

    @Test
    fun `RawDisplayHandle AppKit is a data object`() {
        val display: RawDisplayHandle = RawDisplayHandle.AppKit
        assertTrue(display is RawDisplayHandle.AppKit)
    }

    @Test
    fun `WindowAttributes has reasonable default values`() {
        val attrs = WindowAttributes()
        assertTrue(attrs.title == "Kadre")
        assertTrue(attrs.visible)
        assertTrue(attrs.resizable)
        assertTrue(attrs.size == null)
    }

    @Test
    fun `WindowAttributes accepts a physical size`() {
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
    fun `WindowAttributes accepts resize increments and content protection`() {
        val attrs = WindowAttributes(
            resizeIncrements = PhysicalSize(8, 16),
            contentProtected = true,
        )
        assertTrue(attrs.resizeIncrements == PhysicalSize(8, 16))
        assertTrue(attrs.contentProtected)
    }

    @Test
    fun `WindowId encapsulates a Long`() {
        val id = WindowId(42L)
        assertTrue(id.value == 42L)
    }

    @Test
    fun `AppKitWindow inherits from NSWindow and NSView via bindings`() {
        // Verify that the binding classes used in AppKitWindow exist
        // and are instantiable via constructor(MemorySegment).
        val nsWindowCtor = NSWindow::class.java.constructors.firstOrNull { it.parameterCount == 1 }
        assertTrue(nsWindowCtor != null, "NSWindow must have a constructor(MemorySegment)")

        val nsViewCtor = NSView::class.java.constructors.firstOrNull { it.parameterCount == 1 }
        assertTrue(nsViewCtor != null, "NSView must have a constructor(MemorySegment)")
    }

    @Test
    fun `AppKitWindow overrides winit parity windowing methods`() {
        val appKitWindowClass = AppKitWindow::class.java

        assertNotNull(appKitWindowClass.getMethod("getSurfaceResizeIncrements"))
        assertNotNull(appKitWindowClass.getMethod("setSurfaceResizeIncrements", PhysicalSize::class.java))
        assertNotNull(appKitWindowClass.getMethod("focusWindow"))
        assertNotNull(appKitWindowClass.getMethod("getHasFocus"))
        assertNotNull(appKitWindowClass.getMethod("setEnabledButtons", WindowButtons::class.java))
        assertNotNull(appKitWindowClass.getMethod("getEnabledButtons"))
        val requestUserAttention = appKitWindowClass.getMethod("requestUserAttention", UserAttentionType::class.java)
        assertTrue(
            WindowRequestResult::class.java.isAssignableFrom(requestUserAttention.returnType),
            "requestUserAttention must return WindowRequestResult",
        )

        val setContentProtected = appKitWindowClass.getMethod("setContentProtected", Boolean::class.javaPrimitiveType)
        assertTrue(
            WindowRequestResult::class.java.isAssignableFrom(setContentProtected.returnType),
            "setContentProtected must return WindowRequestResult",
        )

        val showWindowMenu = appKitWindowClass.getMethod("showWindowMenu", PhysicalPosition::class.java)
        assertTrue(
            WindowRequestResult::class.java.isAssignableFrom(showWindowMenu.returnType),
            "showWindowMenu must return WindowRequestResult",
        )
    }

    @Test
    fun `AppKit window menu is a success no-op like winit`() {
        assertEquals(WindowRequestResult.Success, appKitShowWindowMenuResult(PhysicalPosition(10, 20)))
    }

    @Test
    fun `AppKit cursor grab mapping follows winit`() {
        assertEquals(1, AppKitCursorHelper.cursorAssociationValue(CursorGrabMode.None))
        assertEquals(0, AppKitCursorHelper.cursorAssociationValue(CursorGrabMode.Locked))
        assertNull(AppKitCursorHelper.cursorAssociationValue(CursorGrabMode.Confined))

        val confined = AppKitCursorHelper.setGrabMode(CursorGrabMode.Confined)
        assertTrue(confined is WindowRequestResult.Failure)
        assertTrue(confined.error is RequestError.Unsupported)
    }

    @Test
    fun `NSWindow bindings expose resize focus and sharing APIs`() {
        val nsWindowClass = NSWindow::class.java

        assertNotNull(nsWindowClass.getMethod("contentResizeIncrements"))
        assertNotNull(nsWindowClass.getMethod("setContentResizeIncrements", java.lang.foreign.MemorySegment::class.java))
        assertNotNull(nsWindowClass.getMethod("makeKeyWindow"))
        assertNotNull(nsWindowClass.getMethod("makeKeyAndOrderFront", java.lang.foreign.MemorySegment::class.java))
        assertNotNull(nsWindowClass.getMethod("isKeyWindow"))
        assertNotNull(nsWindowClass.getMethod("isMiniaturized"))
        assertNotNull(nsWindowClass.getMethod("isVisible"))
        assertNotNull(nsWindowClass.getMethod("setSharingType", NSWindowSharingType::class.java))
        assertNotNull(nsWindowClass.getMethod("standardWindowButton", NSWindowButton::class.java))
    }

    @Test
    fun `AppKit resize increment conversion uses physical pixels and points`() {
        assertTrue(appKitResizeIncrementsToPhysicalSize(4.0, 8.0, scale = 2.0) == PhysicalSize(8, 16))
        assertNull(appKitResizeIncrementsToPhysicalSize(0.0, 8.0, scale = 2.0))
        assertNull(appKitResizeIncrementsToPhysicalSize(4.0, 8.0, scale = 0.0))

        assertTrue(physicalSizeToAppKitResizeIncrements(PhysicalSize(8, 16), scale = 2.0) == (4.0 to 8.0))
        assertTrue(physicalSizeToAppKitResizeIncrements(null, scale = 2.0) == (0.0 to 0.0))
        assertTrue(physicalSizeToAppKitResizeIncrements(PhysicalSize(8, 16), scale = 0.0) == (0.0 to 0.0))
    }

    @Test
    fun `AppKit applies initial transparent and blur attributes only when requested`() {
        assertTrue(appKitShouldApplyInitialTransparency(true))
        assertTrue(!appKitShouldApplyInitialTransparency(false))
        assertTrue(appKitShouldApplyInitialBlur(true))
        assertTrue(!appKitShouldApplyInitialBlur(false))
    }

    @Test
    fun `AppKit transparency selects clear or window background color like winit`() {
        assertTrue(appKitBackgroundColorSelectorForTransparency(true) == "clearColor")
        assertTrue(appKitBackgroundColorSelectorForTransparency(false) == "windowBackgroundColor")
    }

    @Test
    fun `AppKit blur effect view install and remove decisions are idempotent`() {
        assertTrue(appKitShouldInstallBlurEffectView(java.lang.foreign.MemorySegment.NULL))
        assertTrue(!appKitShouldRemoveBlurEffectView(java.lang.foreign.MemorySegment.NULL))

        val existingView = java.lang.foreign.MemorySegment.ofAddress(0xB10BL)
        assertTrue(!appKitShouldInstallBlurEffectView(existingView))
        assertTrue(appKitShouldRemoveBlurEffectView(existingView))
    }

    @Test
    fun `AppKit enabled buttons update close and minimize style bits`() {
        val base = NSWindowStyleMask.NSWindowStyleMaskTitled +
            NSWindowStyleMask.NSWindowStyleMaskClosable +
            NSWindowStyleMask.NSWindowStyleMaskMiniaturizable +
            NSWindowStyleMask.NSWindowStyleMaskResizable

        val maximizeOnly = appKitStyleMaskWithEnabledButtons(base, WindowButtons.MAXIMIZE)
        assertTrue(NSWindowStyleMask.NSWindowStyleMaskTitled in maximizeOnly)
        assertTrue(NSWindowStyleMask.NSWindowStyleMaskResizable in maximizeOnly)
        assertTrue(NSWindowStyleMask.NSWindowStyleMaskClosable !in maximizeOnly)
        assertTrue(NSWindowStyleMask.NSWindowStyleMaskMiniaturizable !in maximizeOnly)

        val closeAndMinimize = appKitStyleMaskWithEnabledButtons(maximizeOnly, WindowButtons.CLOSE + WindowButtons.MINIMIZE)
        assertTrue(NSWindowStyleMask.NSWindowStyleMaskClosable in closeAndMinimize)
        assertTrue(NSWindowStyleMask.NSWindowStyleMaskMiniaturizable in closeAndMinimize)
        assertTrue(NSWindowStyleMask.NSWindowStyleMaskResizable in closeAndMinimize)

        val borderless = appKitStyleMaskWithEnabledButtons(
            NSWindowStyleMask.NSWindowStyleMaskBorderless,
            WindowButtons.ALL,
        )
        assertTrue(NSWindowStyleMask.NSWindowStyleMaskClosable !in borderless)
        assertTrue(NSWindowStyleMask.NSWindowStyleMaskMiniaturizable !in borderless)
    }

    @Test
    fun `AppKit focus follows winit visible and non-minimized guard`() {
        assertTrue(appKitShouldFocusWindow(isVisible = true, isMiniaturized = false))
        assertTrue(!appKitShouldFocusWindow(isVisible = false, isMiniaturized = false))
        assertTrue(!appKitShouldFocusWindow(isVisible = true, isMiniaturized = true))
        assertTrue(!appKitShouldFocusWindow(isVisible = false, isMiniaturized = true))
    }

    @Test
    fun `AppKit window levels match winit hardcoded NSWindow levels`() {
        assertTrue(appKitWindowLevelValue(WindowLevel.AlwaysOnTop) == 3L)
        assertTrue(appKitWindowLevelValue(WindowLevel.Normal) == 0L)
        assertTrue(appKitWindowLevelValue(WindowLevel.AlwaysOnBottom) == -1L)
    }

    @Test
    fun `AppKit window icon is unsupported like winit`() {
        assertTrue(!appKitWindowIconIsSupported())
    }
}
