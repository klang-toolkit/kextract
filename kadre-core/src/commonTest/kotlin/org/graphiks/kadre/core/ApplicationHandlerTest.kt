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
        var appelé = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
            override fun deviceEvent(eventLoop: ActiveEventLoop, deviceId: DeviceId, event: DeviceEvent) {
                appelé = true
            }
        }
        handler.deviceEvent(stubEventLoop, DeviceId(42L), DeviceEvent.Button(0, KeyState.Pressed))
        assertTrue(appelé, "overridden deviceEvent must be called")
    }

    @Test
    fun newEventsIsOverridable() {
        var causReçue: StartCause? = null
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
            override fun newEvents(eventLoop: ActiveEventLoop, startCause: StartCause) {
                causReçue = startCause
            }
        }
        handler.newEvents(stubEventLoop, StartCause.Poll)
        assertEquals(StartCause.Poll, causReçue)
    }

    @Test
    fun aboutToWaitIsOverridable() {
        var appelé = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
            override fun aboutToWait(eventLoop: ActiveEventLoop) {
                appelé = true
            }
        }
        handler.aboutToWait(stubEventLoop)
        assertTrue(appelé)
    }

    @Test
    fun resumedIsOverridable() {
        var appelé = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
            override fun resumed(eventLoop: ActiveEventLoop) {
                appelé = true
            }
        }
        handler.resumed(stubEventLoop)
        assertTrue(appelé)
    }

    @Test
    fun suspendedIsOverridable() {
        var appelé = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
            override fun suspended(eventLoop: ActiveEventLoop) {
                appelé = true
            }
        }
        handler.suspended(stubEventLoop)
        assertTrue(appelé)
    }

    @Test
    fun destroySurfacesIsOverridable() {
        var appelé = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
            override fun destroySurfaces(eventLoop: ActiveEventLoop) {
                appelé = true
            }
        }
        handler.destroySurfaces(stubEventLoop)
        assertTrue(appelé)
    }

    // -------------------------------------------------------------------------
    // Verifying canCreateSurfaces (mandatory, with eventLoop)
    // -------------------------------------------------------------------------

    @Test
    fun canCreateSurfacesReceivesEventLoop() {
        var eventLoopReçu: ActiveEventLoop? = null
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
                eventLoopReçu = eventLoop
            }
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit
        }
        handler.canCreateSurfaces(stubEventLoop)
        assertEquals(stubEventLoop, eventLoopReçu)
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
    }

    @Test
    fun windowAttributesCustomized() {
        val attrs = WindowAttributes(
            title = "My window",
            size = PhysicalSize(1920, 1080),
            visible = false,
            resizable = false,
        )
        assertEquals("My window", attrs.title)
        assertEquals(PhysicalSize(1920, 1080), attrs.size)
        assertFalse(attrs.visible)
        assertFalse(attrs.resizable)
    }
}
