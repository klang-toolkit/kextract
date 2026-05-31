/**
 * Tests d'exemple pour [ScriptedEventLoop].
 *
 * Couvre : ordre du cycle de vie, key press/release, séquence pointeur, cascade de
 * resize, flux de sortie. Ces tests valident à la fois le framework et servent de
 * documentation exécutable du DSL.
 */
package org.graphiks.kadre.test

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

/** Handler d'enregistrement : capture les événements de fenêtre reçus. */
private class RecordingHandler(
    private val exitOnClose: Boolean = false,
) : ApplicationHandler {
    val received = mutableListOf<WindowEvent>()
    var surfacesCreated = false

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        surfacesCreated = true
        eventLoop.createWindow(org.graphiks.kadre.core.WindowAttributes())
    }

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        if (event is WindowEvent) received += event
        if (exitOnClose && event is WindowEvent.CloseRequested) eventLoop.exit()
    }
}

class ScriptedEventLoopTest {

    @Test
    fun ordreCycleDeVie_resumedAvantCanCreateSurfaces_suspendedEnDernier() {
        val trace = scriptedTest {
            canCreateSurfaces()
        }.run(RecordingHandler())

        assertEquals(Callback.Resumed, trace.first())
        assertEquals(Callback.CanCreateSurfaces, trace[1])
        assertEquals(Callback.Suspended, trace.last())
    }

    @Test
    fun keyPressRelease_dispatchEnOrdre() {
        val handler = RecordingHandler()
        scriptedTest {
            keyPress(Key.ArrowUp)
            keyRelease(Key.ArrowUp)
        }.run(handler)

        assertEquals(2, handler.received.size)
        val press = handler.received[0] as WindowEvent.KeyboardInput
        val release = handler.received[1] as WindowEvent.KeyboardInput
        assertEquals(KeyState.Pressed, press.state)
        assertEquals(Key.ArrowUp, press.key)
        assertEquals(KeyState.Released, release.state)
    }

    @Test
    fun sequencePointeur_moveEtClick() {
        val handler = RecordingHandler()
        scriptedTest {
            pointerMove(10.0, 20.0)
            mouseInput(MouseButton.Left, KeyState.Pressed)
            mouseInput(MouseButton.Left, KeyState.Released)
        }.run(handler)

        assertEquals(3, handler.received.size)
        assertTrue(handler.received[0] is WindowEvent.PointerMoved)
        assertTrue(handler.received[1] is WindowEvent.MouseInput)
        val click = handler.received[1] as WindowEvent.MouseInput
        assertEquals(MouseButton.Left, click.button)
    }

    @Test
    fun cascadeResize_etScaleFactor() {
        val handler = RecordingHandler()
        scriptedTest {
            resized(1024, 768)
            scaleFactorChanged(2.0)
            resized(2048, 1536)
        }.run(handler)

        assertEquals(3, handler.received.size)
        val first = handler.received[0] as WindowEvent.Resized
        assertEquals(1024, first.size.width)
        val scale = handler.received[1] as WindowEvent.ScaleFactorChanged
        assertEquals(2.0, scale.factor)
    }

    @Test
    fun fluxDeSortie_exitArreteLesEvenementsRestants() {
        val handler = RecordingHandler(exitOnClose = true)
        val trace = scriptedTest {
            keyPress(Key.Escape)
            closeRequested()
            // Ces événements ne doivent PAS être dispatché après exit().
            keyPress(Key.ArrowUp)
            tick()
        }.run(handler)

        // Seuls Escape (press) et CloseRequested sont reçus.
        assertEquals(2, handler.received.size)
        assertTrue(handler.received.last() is WindowEvent.CloseRequested)
        // suspended est tout de même invoqué en fin de boucle.
        assertEquals(Callback.Suspended, trace.last())
        // Aucun RedrawRequested (le tick après exit est ignoré).
        assertFalse(handler.received.any { it is WindowEvent.RedrawRequested })
    }

    @Test
    fun tick_produitNewEventsRedrawAboutToWait() {
        val handler = RecordingHandler()
        val trace = scriptedTest {
            tick(16)
        }.run(handler)

        // La frame produit la sous-séquence attendue dans la trace.
        val idx = trace.indexOfFirst { it is Callback.NewEvents }
        assertTrue(idx >= 0)
        assertTrue(trace[idx + 1] is Callback.WindowEventCb)
        assertEquals(Callback.AboutToWait, trace[idx + 2])
        assertEquals(1, handler.received.count { it is WindowEvent.RedrawRequested })
    }
}
