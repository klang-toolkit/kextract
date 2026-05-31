/**
 * Tests de l'interface ApplicationHandler.
 *
 * Vérifie que :
 * - Un ApplicationHandler anonyme peut être instancié en n'implémentant que
 *   les méthodes obligatoires (canCreateSurfaces et windowEvent).
 * - Toutes les méthodes optionnelles ont bien une implémentation par défaut vide.
 * - Les méthodes optionnelles peuvent être surchargées.
 */
package org.graphiks.kadre.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ApplicationHandlerTest {

    // -------------------------------------------------------------------------
    // Stub minimal d'ActiveEventLoop pour les tests
    // -------------------------------------------------------------------------

    private val stubEventLoop = object : ActiveEventLoop {
        override fun createWindow(attributes: WindowAttributes): Window =
            error("Non implémenté dans le stub de test")

        override fun setControlFlow(controlFlow: ControlFlow) = Unit

        override val controlFlow: ControlFlow get() = ControlFlow.Wait

        override fun exit() = Unit

        override val isExiting: Boolean get() = false

        override fun createProxy(): EventLoopProxy = object : EventLoopProxy {
            override fun wakeUp() = Unit
        }
    }

    // -------------------------------------------------------------------------
    // Vérification que le handler minimal compile et s'instancie
    // -------------------------------------------------------------------------

    /**
     * Un ApplicationHandler n'implémentant que les méthodes obligatoires
     * doit pouvoir être instancié sans erreur de compilation.
     */
    @Test
    fun handlerMinimalInstancié() {
        val handler: ApplicationHandler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
        }
        // Vérifie que le handler est bien non-null et instancié correctement.
        assertTrue(handler.toString().isNotEmpty())
    }

    // -------------------------------------------------------------------------
    // Vérification des implémentations par défaut (corps vides = Unit)
    // -------------------------------------------------------------------------

    @Test
    fun deviceEventParDéfautRetourneUnit() {
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
        }
        // Ne doit pas lever d'exception
        handler.deviceEvent(stubEventLoop, DeviceId(1L), Any())
    }

    @Test
    fun newEventsParDéfautRetourneUnit() {
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
        }
        handler.newEvents(stubEventLoop, StartCause.Init)
    }

    @Test
    fun aboutToWaitParDéfautRetourneUnit() {
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
        }
        handler.aboutToWait(stubEventLoop)
    }

    @Test
    fun resumedParDéfautRetourneUnit() {
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
        }
        handler.resumed(stubEventLoop)
    }

    @Test
    fun suspendedParDéfautRetourneUnit() {
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
        }
        handler.suspended(stubEventLoop)
    }

    @Test
    fun destroySurfacesParDéfautRetourneUnit() {
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
        }
        handler.destroySurfaces(stubEventLoop)
    }

    // -------------------------------------------------------------------------
    // Vérification que les méthodes optionnelles sont surchargeables
    // -------------------------------------------------------------------------

    @Test
    fun deviceEventEstSurchargeable() {
        var appelé = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
            override fun deviceEvent(eventLoop: ActiveEventLoop, deviceId: DeviceId, event: Any) {
                appelé = true
            }
        }
        handler.deviceEvent(stubEventLoop, DeviceId(42L), Any())
        assertTrue(appelé, "deviceEvent surchargé doit être appelé")
    }

    @Test
    fun newEventsEstSurchargeable() {
        var causReçue: StartCause? = null
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
            override fun newEvents(eventLoop: ActiveEventLoop, startCause: StartCause) {
                causReçue = startCause
            }
        }
        handler.newEvents(stubEventLoop, StartCause.Poll)
        assertEquals(StartCause.Poll, causReçue)
    }

    @Test
    fun aboutToWaitEstSurchargeable() {
        var appelé = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
            override fun aboutToWait(eventLoop: ActiveEventLoop) {
                appelé = true
            }
        }
        handler.aboutToWait(stubEventLoop)
        assertTrue(appelé)
    }

    @Test
    fun resumedEstSurchargeable() {
        var appelé = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
            override fun resumed(eventLoop: ActiveEventLoop) {
                appelé = true
            }
        }
        handler.resumed(stubEventLoop)
        assertTrue(appelé)
    }

    @Test
    fun suspendedEstSurchargeable() {
        var appelé = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
            override fun suspended(eventLoop: ActiveEventLoop) {
                appelé = true
            }
        }
        handler.suspended(stubEventLoop)
        assertTrue(appelé)
    }

    @Test
    fun destroySurfacesEstSurchargeable() {
        var appelé = false
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
            override fun destroySurfaces(eventLoop: ActiveEventLoop) {
                appelé = true
            }
        }
        handler.destroySurfaces(stubEventLoop)
        assertTrue(appelé)
    }

    // -------------------------------------------------------------------------
    // Vérification de canCreateSurfaces (obligatoire, avec eventLoop)
    // -------------------------------------------------------------------------

    @Test
    fun canCreateSurfacesReçoitEventLoop() {
        var eventLoopReçu: ActiveEventLoop? = null
        val handler = object : ApplicationHandler {
            override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
                eventLoopReçu = eventLoop
            }
            override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
        }
        handler.canCreateSurfaces(stubEventLoop)
        assertEquals(stubEventLoop, eventLoopReçu)
    }

    // -------------------------------------------------------------------------
    // Vérification des types de contrôle de flux
    // -------------------------------------------------------------------------

    @Test
    fun controlFlowWaitEstDistinct() {
        val cf: ControlFlow = ControlFlow.Wait
        assertEquals(ControlFlow.Wait, cf)
    }

    @Test
    fun controlFlowPollEstDistinct() {
        val cf: ControlFlow = ControlFlow.Poll
        assertEquals(ControlFlow.Poll, cf)
    }

    @Test
    fun controlFlowWaitUntilContientInstant() {
        val cf = ControlFlow.WaitUntil(instant = 1_000L)
        assertEquals(1_000L, cf.instant)
    }

    // -------------------------------------------------------------------------
    // Vérification des StartCause
    // -------------------------------------------------------------------------

    @Test
    fun startCauseInitEstSingleton() {
        val sc: StartCause = StartCause.Init
        assertEquals(StartCause.Init, sc)
    }

    @Test
    fun startCausePollEstSingleton() {
        val sc: StartCause = StartCause.Poll
        assertEquals(StartCause.Poll, sc)
    }

    @Test
    fun startCauseWaitCancelledAvecValeurOptionnelle() {
        val sc = StartCause.WaitCancelled(requestedResume = 500L)
        assertEquals(500L, sc.requestedResume)
    }

    @Test
    fun startCauseWaitCancelledSansValeur() {
        val sc = StartCause.WaitCancelled()
        assertFalse(sc.requestedResume != null)
    }

    @Test
    fun startCauseResumeTimeReachedContientInstants() {
        val sc = StartCause.ResumeTimeReached(requestedResume = 100L, start = 105L)
        assertEquals(100L, sc.requestedResume)
        assertEquals(105L, sc.start)
    }

    // -------------------------------------------------------------------------
    // Vérification des value classes WindowId / DeviceId
    // -------------------------------------------------------------------------

    @Test
    fun windowIdEncapsuleUnLong() {
        val id = WindowId(42L)
        assertEquals(42L, id.value)
    }

    @Test
    fun deviceIdEncapsuleUnLong() {
        val id = DeviceId(7L)
        assertEquals(7L, id.value)
    }

    // -------------------------------------------------------------------------
    // Vérification de WindowAttributes
    // -------------------------------------------------------------------------

    @Test
    fun windowAttributesValeursParDéfaut() {
        val attrs = WindowAttributes()
        assertEquals("Kadre", attrs.title)
        assertFalse(attrs.size != null)
        assertTrue(attrs.visible)
        assertTrue(attrs.resizable)
    }

    @Test
    fun windowAttributesPersonnalisées() {
        val attrs = WindowAttributes(
            title = "Ma fenêtre",
            size = PhysicalSize(1920, 1080),
            visible = false,
            resizable = false,
        )
        assertEquals("Ma fenêtre", attrs.title)
        assertEquals(PhysicalSize(1920, 1080), attrs.size)
        assertFalse(attrs.visible)
        assertFalse(attrs.resizable)
    }
}
