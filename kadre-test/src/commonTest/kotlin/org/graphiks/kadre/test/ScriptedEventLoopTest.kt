/**
 * Example tests for [ScriptedEventLoop].
 *
 * Covers: lifecycle order, key press/release, pointer sequence, resize cascade,
 * output stream. These tests validate the framework and also serve as
 * executable documentation of the DSL.
 */
package org.graphiks.kadre.test

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.KeyLocation
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.LogicalKey
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.NamedKey
import org.graphiks.kadre.core.PhysicalKey
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

/** Recording handler: captures the received window events. */
private class RecordingHandler(
    private val exitOnClose: Boolean = false,
) : ApplicationHandler {
    val received = mutableListOf<WindowEvent>()
    var surfacesCreated = false

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        surfacesCreated = true
        eventLoop.createWindow(org.graphiks.kadre.core.WindowAttributes())
    }

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
        received += event
        if (exitOnClose && event is WindowEvent.CloseRequested) eventLoop.exit()
    }
}

class ScriptedEventLoopTest {

    @Test
    fun lifecycleOrder_resumedBeforeCanCreateSurfaces_suspendedLast() {
        val trace = scriptedTest {
            canCreateSurfaces()
        }.run(RecordingHandler())

        assertEquals(Callback.Resumed, trace.first())
        assertEquals(Callback.CanCreateSurfaces, trace[1])
        assertEquals(Callback.Suspended, trace.last())
    }

    @Test
    fun physicalKeyPressRelease_dispatchInOrder() {
        val handler = RecordingHandler()
        scriptedTest {
            physicalKeyPress(KeyCode.ArrowUp)
            physicalKeyRelease(KeyCode.ArrowUp)
        }.run(handler)

        assertEquals(2, handler.received.size)
        val press = handler.received[0] as WindowEvent.KeyInput
        val release = handler.received[1] as WindowEvent.KeyInput
        assertEquals(KeyState.Pressed, press.event.state)
        assertEquals(PhysicalKey.Code(KeyCode.ArrowUp), press.event.physicalKey)
        assertEquals(LogicalKey.Named(NamedKey.ArrowUp), press.event.logicalKey)
        assertEquals(KeyState.Released, release.event.state)
    }

    @Test
    fun physicalKeyPress_keyA_usesCoreDefaultLogicalKeyAndText() {
        val handler = RecordingHandler()
        scriptedTest {
            physicalKeyPress(KeyCode.KeyA)
        }.run(handler)

        val press = handler.received.single() as WindowEvent.KeyInput
        assertEquals(LogicalKey.Character("a"), press.event.logicalKey)
        assertEquals("a", press.event.text)
    }

    @Test
    fun physicalKeyPress_usesOverriddenCharacterLogicalKeyForDefaultText() {
        val handler = RecordingHandler()
        scriptedTest {
            physicalKeyPress(KeyCode.KeyQ, logicalKey = LogicalKey.Character("a"))
        }.run(handler)

        val press = handler.received.single() as WindowEvent.KeyInput
        assertEquals(LogicalKey.Character("a"), press.event.logicalKey)
        assertEquals("a", press.event.text)
    }

    @Test
    fun physicalKeyPress_doesNotInferTextWhenLogicalKeyIsOverriddenToNamedKey() {
        val handler = RecordingHandler()
        scriptedTest {
            physicalKeyPress(KeyCode.KeyA, logicalKey = LogicalKey.Named(NamedKey.Enter))
        }.run(handler)

        val press = handler.received.single() as WindowEvent.KeyInput
        assertEquals(LogicalKey.Named(NamedKey.Enter), press.event.logicalKey)
        assertEquals(null, press.event.text)
    }

    @Test
    fun physicalKeyPress_shiftLeft_usesCoreDefaultLocation() {
        val handler = RecordingHandler()
        scriptedTest {
            physicalKeyPress(KeyCode.ShiftLeft)
        }.run(handler)

        val press = handler.received.single() as WindowEvent.KeyInput
        assertEquals(KeyLocation.Left, press.event.location)
    }

    @Test
    fun physicalKeyPress_numpadEnter_usesCoreDefaultLocation() {
        val handler = RecordingHandler()
        scriptedTest {
            physicalKeyPress(KeyCode.NumpadEnter)
        }.run(handler)

        val press = handler.received.single() as WindowEvent.KeyInput
        assertEquals(KeyLocation.Numpad, press.event.location)
    }

    @Test
    fun logicalKeyPress_dispatchesTextAndModifiers() {
        val handler = RecordingHandler()
        scriptedTest {
            logicalKeyPress(LogicalKey.Character("s"), modifiers = KeyboardModifiers.Ctrl, text = "s")
        }.run(handler)

        val press = handler.received.single() as WindowEvent.KeyInput
        assertEquals(LogicalKey.Character("s"), press.event.logicalKey)
        assertEquals("s", press.event.text)
        assertTrue(press.event.modifiers.ctrl)
    }

    @Test
    fun pointerSequence_moveAndClick() {
        val handler = RecordingHandler()
        scriptedTest {
            pointerMove(10.0, 20.0)
            mouseInput(MouseButton.Left, KeyState.Pressed)
            mouseInput(MouseButton.Left, KeyState.Released)
        }.run(handler)

        assertEquals(3, handler.received.size)
        assertTrue(handler.received[0] is WindowEvent.PointerMoved)
        assertTrue(handler.received[1] is WindowEvent.PointerButton)
        val click = handler.received[1] as WindowEvent.PointerButton
        assertEquals(ButtonSource.Mouse(MouseButton.Left), click.button)
    }

    @Test
    fun resizeCascade_andScaleFactor() {
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
    fun outputStream_exitStopsRemainingEvents() {
        val handler = RecordingHandler(exitOnClose = true)
        val trace = scriptedTest {
            physicalKeyPress(KeyCode.Escape)
            closeRequested()
            // These events must NOT be dispatched after exit().
            physicalKeyPress(KeyCode.ArrowUp)
            tick()
        }.run(handler)

        // Only Escape (press) and CloseRequested are received.
        assertEquals(2, handler.received.size)
        assertTrue(handler.received.last() is WindowEvent.CloseRequested)
        // suspended is still invoked at the end of the loop.
        assertEquals(Callback.Suspended, trace.last())
        // No RedrawRequested (the tick after exit is ignored).
        assertFalse(handler.received.any { it is WindowEvent.RedrawRequested })
    }

    @Test
    fun tick_producesNewEventsRedrawAboutToWait() {
        val handler = RecordingHandler()
        val trace = scriptedTest {
            tick(16)
        }.run(handler)

        // The frame produces the expected subsequence in the trace.
        val idx = trace.indexOfFirst { it is Callback.NewEvents }
        assertTrue(idx >= 0)
        assertTrue(trace[idx + 1] is Callback.WindowEventCb)
        assertEquals(Callback.AboutToWait, trace[idx + 2])
        assertEquals(1, handler.received.count { it is WindowEvent.RedrawRequested })
    }
}
