/**
 * Tests for the ApplicationHandler interface.
 *
 * Verifies that:
 * - An anonymous ApplicationHandler can be instantiated by implementing only
 *   the mandatory methods (canCreateSurfaces and windowEvent).
 * - All optional methods do have an empty default implementation.
 * - The optional methods can be overridden.
 */
package org.graphiks.kadre.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ApplicationHandlerTest {

    // -------------------------------------------------------------------------
    // Minimal ActiveEventLoop stub for the tests
    // -------------------------------------------------------------------------

    private val stubEventLoop = object : ActiveEventLoop {
        override fun createWindow(attributes: WindowAttributes): Window =
            error("Not implemented in the test stub")

        override fun setControlFlow(controlFlow: ControlFlow) = Unit

        override val controlFlow: ControlFlow get() = ControlFlow.Wait

        override fun exit() = Unit

        override val isExiting: Boolean get() = false

        override fun createProxy(): EventLoopProxy = object : EventLoopProxy {
            override fun wakeUp() = Unit
        }

        // R2 stubs
        override fun availableMonitors() = emptyList<org.graphiks.kadre.core.MonitorHandle>()
        override fun primaryMonitor() = null

        // R3 stub
        override fun systemTheme() = null

        // R4 stub
        override fun listenDeviceEvents(mode: DeviceEvents) = Unit
    }

    // -------------------------------------------------------------------------
    // Verifying that the minimal handler compiles and instantiates
    // -------------------------------------------------------------------------

    /**
     * An ApplicationHandler implementing only the mandatory methods
     * must be instantiable without a compilation error.
     */
    @Test
    fun minimalHandlerInstantiated() {
        val handler: ApplicationHandler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
        }
        // Verifies that the handler is indeed non-null and correctly instantiated.
        assertTrue(handler.toString().isNotEmpty())
    }

    // -------------------------------------------------------------------------
    // Verifying the default implementations (empty bodies = Unit)
    // -------------------------------------------------------------------------

    @Test
    fun deviceEventByDefaultReturnsUnit() {
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
        }
        // Must not throw an exception
        handler.deviceEvent(stubEventLoop, DeviceId(1L), DeviceEvent.Button(0, KeyState.Pressed))
    }

    @Test
    fun newEventsByDefaultReturnsUnit() {
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
        }
        handler.newEvents(stubEventLoop, StartCause.Init)
    }

    @Test
    fun aboutToWaitByDefaultReturnsUnit() {
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
        }
        handler.aboutToWait(stubEventLoop)
    }

    @Test
    fun resumedByDefaultReturnsUnit() {
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
        }
        handler.resumed(stubEventLoop)
    }

    @Test
    fun suspendedByDefaultReturnsUnit() {
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
        }
        handler.suspended(stubEventLoop)
    }

    @Test
    fun destroySurfacesByDefaultReturnsUnit() {
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
        }
        handler.destroySurfaces(stubEventLoop)
    }

    // -------------------------------------------------------------------------
    // Verifying that the optional methods are overridable
    // -------------------------------------------------------------------------

    @Test
    fun deviceEventIsOverridable() {
        var called = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
            override fun deviceEvent(eventLoop: ActiveEventLoop, deviceId: DeviceId, event: DeviceEvent) {
                called = true
            }
        }
        handler.deviceEvent(stubEventLoop, DeviceId(42L), DeviceEvent.Button(0, KeyState.Pressed))
        assertTrue(called, "overridden deviceEvent must be called")
    }

    @Test
    fun newEventsIsOverridable() {
        var receivedCause: StartCause? = null
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
            override fun newEvents(eventLoop: ActiveEventLoop, startCause: StartCause) {
                receivedCause = startCause
            }
        }
        handler.newEvents(stubEventLoop, StartCause.Poll)
        assertEquals(StartCause.Poll, receivedCause)
    }

    @Test
    fun aboutToWaitIsOverridable() {
        var called = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
            override fun aboutToWait(eventLoop: ActiveEventLoop) {
                called = true
            }
        }
        handler.aboutToWait(stubEventLoop)
        assertTrue(called)
    }

    @Test
    fun resumedIsOverridable() {
        var called = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
            override fun resumed(eventLoop: ActiveEventLoop) {
                called = true
            }
        }
        handler.resumed(stubEventLoop)
        assertTrue(called)
    }

    @Test
    fun suspendedIsOverridable() {
        var called = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
            override fun suspended(eventLoop: ActiveEventLoop) {
                called = true
            }
        }
        handler.suspended(stubEventLoop)
        assertTrue(called)
    }

    @Test
    fun destroySurfacesIsOverridable() {
        var called = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
            override fun destroySurfaces(eventLoop: ActiveEventLoop) {
                called = true
            }
        }
        handler.destroySurfaces(stubEventLoop)
        assertTrue(called)
    }

    // -------------------------------------------------------------------------
    // Verifying canCreateSurfaces (mandatory, with eventLoop)
    // -------------------------------------------------------------------------

    @Test
    fun canCreateSurfacesReceivesEventLoop() {
        var receivedEventLoop: ActiveEventLoop? = null
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
                receivedEventLoop = eventLoop
            }
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
        }
        handler.canCreateSurfaces(stubEventLoop)
        assertEquals(stubEventLoop, receivedEventLoop)
    }

    // -------------------------------------------------------------------------
    // Verifying the control flow types
    // -------------------------------------------------------------------------

    @Test
    fun controlFlowWaitIsDistinct() {
        val cf: ControlFlow = ControlFlow.Wait
        assertEquals(ControlFlow.Wait, cf)
    }

    @Test
    fun controlFlowPollIsDistinct() {
        val cf: ControlFlow = ControlFlow.Poll
        assertEquals(ControlFlow.Poll, cf)
    }

    @Test
    fun controlFlowWaitUntilContainsInstant() {
        val cf = ControlFlow.WaitUntil(instant = 1_000L)
        assertEquals(1_000L, cf.instant)
    }

    // -------------------------------------------------------------------------
    // Verifying the StartCause values
    // -------------------------------------------------------------------------

    @Test
    fun startCauseInitIsSingleton() {
        val sc: StartCause = StartCause.Init
        assertEquals(StartCause.Init, sc)
    }

    @Test
    fun startCausePollIsSingleton() {
        val sc: StartCause = StartCause.Poll
        assertEquals(StartCause.Poll, sc)
    }

    @Test
    fun startCauseWaitCancelledWithOptionalValue() {
        val sc = StartCause.WaitCancelled(requestedResume = 500L)
        assertEquals(500L, sc.requestedResume)
    }

    @Test
    fun startCauseWaitCancelledWithoutValue() {
        val sc = StartCause.WaitCancelled()
        assertFalse(sc.requestedResume != null)
    }

    @Test
    fun startCauseResumeTimeReachedContainsInstants() {
        val sc = StartCause.ResumeTimeReached(requestedResume = 100L, start = 105L)
        assertEquals(100L, sc.requestedResume)
        assertEquals(105L, sc.start)
    }

    // -------------------------------------------------------------------------
    // Verifying the WindowId / DeviceId value classes
    // -------------------------------------------------------------------------

    @Test
    fun windowIdWrapsALong() {
        val id = WindowId(42L)
        assertEquals(42L, id.value)
    }

    @Test
    fun deviceIdWrapsALong() {
        val id = DeviceId(7L)
        assertEquals(7L, id.value)
    }

    // -------------------------------------------------------------------------
    // Verifying WindowAttributes
    // -------------------------------------------------------------------------

    @Test
    fun windowAttributesDefaultValues() {
        val attrs = WindowAttributes()
        assertEquals("Kadre", attrs.title)
        assertFalse(attrs.size != null)
        assertTrue(attrs.visible)
        assertTrue(attrs.resizable)
        assertEquals(WindowButtons.ALL, attrs.enabledButtons)
        assertEquals(WindowLevel.Normal, attrs.windowLevel)
        assertEquals(CursorIcon.Default, attrs.cursor)
        assertFalse(attrs.maximized)
        assertFalse(attrs.transparent)
        assertFalse(attrs.blur)
        assertTrue(attrs.decorations)
        assertTrue(attrs.active)
        assertNull(attrs.fullscreen)
    }

    @Test
    fun windowAttributesCustomized() {
        val icon = Icon(byteArrayOf(1, 2, 3, 4), width = 1, height = 1)
        val attrs = WindowAttributes(
            title = "My window",
            size = PhysicalSize(1920, 1080),
            minSize = PhysicalSize(640, 480),
            maxSize = PhysicalSize(3840, 2160),
            resizeIncrements = PhysicalSize(8, 16),
            position = PhysicalPosition(50, 60),
            visible = false,
            resizable = false,
            enabledButtons = WindowButtons.CLOSE,
            maximized = true,
            transparent = true,
            blur = true,
            decorations = false,
            windowIcon = icon,
            preferredTheme = Theme.Dark,
            contentProtected = true,
            windowLevel = WindowLevel.AlwaysOnTop,
            active = false,
            cursor = CursorIcon.Pointer,
            parentWindow = RawWindowHandle.Win32(1L, 2L),
        )
        assertEquals("My window", attrs.title)
        assertEquals(PhysicalSize(1920, 1080), attrs.size)
        assertEquals(PhysicalSize(640, 480), attrs.minSize)
        assertEquals(PhysicalSize(3840, 2160), attrs.maxSize)
        assertEquals(PhysicalSize(8, 16), attrs.resizeIncrements)
        assertEquals(PhysicalPosition(50, 60), attrs.position)
        assertFalse(attrs.visible)
        assertFalse(attrs.resizable)
        assertEquals(WindowButtons.CLOSE, attrs.enabledButtons)
        assertTrue(attrs.maximized)
        assertTrue(attrs.transparent)
        assertTrue(attrs.blur)
        assertFalse(attrs.decorations)
        assertEquals(icon, attrs.windowIcon)
        assertEquals(Theme.Dark, attrs.preferredTheme)
        assertTrue(attrs.contentProtected)
        assertEquals(WindowLevel.AlwaysOnTop, attrs.windowLevel)
        assertFalse(attrs.active)
        assertEquals(CursorIcon.Pointer, attrs.cursor)
        assertEquals(RawWindowHandle.Win32(1L, 2L), attrs.parentWindow)
    }
}
