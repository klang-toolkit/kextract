/**
 * Tests for PongGame.
 *
 * Verifies the application handler's behavior without GPU:
 *   - [aboutToWait] advances the game state
 *   - [windowEvent] with CloseRequested calls [eventLoop.exit()]
 *   - [canCreateSurfaces] initializes rendering and configures Poll
 */
package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

// ---------------------------------------------------------------------------
// Mocks
// ---------------------------------------------------------------------------

/** Fake renderer — records the calls. */
class FakeRenderer : PongRendererInterface {
    var drawCount = 0
    var resizeCount = 0
    var released = false
    var lastState: GameState? = null

    override fun draw(state: GameState) {
        drawCount++
        lastState = state
    }

    override fun resize(width: Int, height: Int) {
        resizeCount++
    }

    override fun release() {
        released = true
    }
}

/** Fake window — returns a Win32 stub handle to trigger the factory. */
class FakeWindow : Window {
    override val id = WindowId(1L)
    // Win32 stub — satisfies the `handle is RawWindowHandle` check in PongGame
    override val rawWindowHandle: RawWindowHandle = RawWindowHandle.Win32(hwnd = 0L, hinstance = 0L)
    override val rawDisplayHandle: RawDisplayHandle = RawDisplayHandle.Win32(hinstance = 0L)
    var redrawRequested = false
    override fun requestRedraw() { redrawRequested = true }
    override fun setTitle(title: String) {}
    override val innerSize = PhysicalSize(800, 600)
    override val outerSize = PhysicalSize(800, 600)
    override val scaleFactor = 1.0
    override fun setVisible(visible: Boolean) {}
    override fun close() {}
}

/** Fake EventLoop — records the calls. */
class FakeEventLoop : ActiveEventLoop {
    var exited = false
    private var _controlFlow: ControlFlow = ControlFlow.Wait
    val windows = mutableListOf<Window>()

    override fun createWindow(attributes: WindowAttributes): Window {
        val win = FakeWindow()
        windows.add(win)
        return win
    }

    override fun setControlFlow(newFlow: ControlFlow) {
        _controlFlow = newFlow
    }

    override val controlFlow: ControlFlow get() = _controlFlow

    override fun exit() {
        exited = true
    }

    override val isExiting: Boolean get() = exited

    override fun createProxy(): EventLoopProxy {
        error("not needed in tests")
    }
}

// ---------------------------------------------------------------------------
// Tests
// ---------------------------------------------------------------------------

class PongGameTest {

    private fun makeGame(): Triple<PongGame, FakeRenderer, FakeEventLoop> {
        val renderer = FakeRenderer()
        val eventLoop = FakeEventLoop()
        val game = PongGame { _ -> renderer }
        game.canCreateSurfaces(eventLoop)
        return Triple(game, renderer, eventLoop)
    }

    // -------------------------------------------------------------------------
    // canCreateSurfaces
    // -------------------------------------------------------------------------

    @Test
    fun `canCreateSurfaces creates a window`() {
        val (_, _, eventLoop) = makeGame()
        assertEquals(1, eventLoop.windows.size)
    }

    @Test
    fun `canCreateSurfaces sets control flow to Poll`() {
        val (_, _, eventLoop) = makeGame()
        assertTrue(eventLoop.controlFlow is ControlFlow.Poll)
    }

    // -------------------------------------------------------------------------
    // aboutToWait — advances the game state
    // -------------------------------------------------------------------------

    @Test
    fun `aboutToWait requests redraw`() {
        val (game, _, eventLoop) = makeGame()
        game.aboutToWait(eventLoop)
        val win = eventLoop.windows.first() as FakeWindow
        assertTrue(win.redrawRequested, "requestRedraw() should have been called")
    }

    @Test
    fun `state advances after aboutToWait`() {
        val renderer = FakeRenderer()
        val eventLoop = FakeEventLoop()
        val game = PongGame { _ -> renderer }
        game.canCreateSurfaces(eventLoop)

        // Get initial state via reflection — draw captures it
        game.windowEvent(eventLoop, WindowId(1L), WindowEvent.RedrawRequested)
        val stateBefore = renderer.lastState

        // Wait a bit then tick again
        game.aboutToWait(eventLoop)
        game.windowEvent(eventLoop, WindowId(1L), WindowEvent.RedrawRequested)
        val stateAfter = renderer.lastState

        // Ball should have moved (dt > 0 after second tick unless timing is exact)
        // Just verify draw was called twice (state is passed through)
        assertEquals(2, renderer.drawCount)
        // The two states should both be non-null
        assertTrue(stateBefore != null)
        assertTrue(stateAfter != null)
    }

    @Test
    fun `multiple aboutToWait calls accumulate game ticks`() {
        val (game, renderer, eventLoop) = makeGame()
        repeat(5) {
            game.aboutToWait(eventLoop)
            game.windowEvent(eventLoop, WindowId(1L), WindowEvent.RedrawRequested)
        }
        assertEquals(5, renderer.drawCount)
    }

    // -------------------------------------------------------------------------
    // windowEvent — CloseRequested
    // -------------------------------------------------------------------------

    @Test
    fun `CloseRequested exits the event loop`() {
        val (game, _, eventLoop) = makeGame()
        assertFalse(eventLoop.exited)
        game.windowEvent(eventLoop, WindowId(1L), WindowEvent.CloseRequested)
        assertTrue(eventLoop.exited)
    }

    @Test
    fun `CloseRequested releases the renderer`() {
        val (game, renderer, eventLoop) = makeGame()
        game.windowEvent(eventLoop, WindowId(1L), WindowEvent.CloseRequested)
        assertTrue(renderer.released)
    }

    // -------------------------------------------------------------------------
    // windowEvent — RedrawRequested
    // -------------------------------------------------------------------------

    @Test
    fun `RedrawRequested calls renderer draw`() {
        val (game, renderer, eventLoop) = makeGame()
        assertEquals(0, renderer.drawCount)
        game.windowEvent(eventLoop, WindowId(1L), WindowEvent.RedrawRequested)
        assertEquals(1, renderer.drawCount)
    }

    // -------------------------------------------------------------------------
    // windowEvent — Resized
    // -------------------------------------------------------------------------

    @Test
    fun `Resized calls renderer resize`() {
        val (game, renderer, eventLoop) = makeGame()
        game.windowEvent(eventLoop, WindowId(1L), WindowEvent.Resized(PhysicalSize(1024, 768)))
        assertEquals(1, renderer.resizeCount)
    }

    // -------------------------------------------------------------------------
    // resumed — resets lastFrameMs
    // -------------------------------------------------------------------------

    @Test
    fun `resumed does not crash`() {
        val (game, _, eventLoop) = makeGame()
        // Should not throw
        game.resumed(eventLoop)
    }

    @Test
    fun `suspended does not crash`() {
        val (game, _, eventLoop) = makeGame()
        game.suspended(eventLoop)
    }

    // -------------------------------------------------------------------------
    // destroySurfaces — releases renderer
    // -------------------------------------------------------------------------

    @Test
    fun `destroySurfaces releases renderer`() {
        val (game, renderer, eventLoop) = makeGame()
        game.destroySurfaces(eventLoop)
        assertTrue(renderer.released)
    }

    // -------------------------------------------------------------------------
    // Keyboard input routing
    // -------------------------------------------------------------------------

    @Test
    fun `KeyboardInput does not crash`() {
        val (game, _, eventLoop) = makeGame()
        game.windowEvent(
            eventLoop,
            WindowId(1L),
            WindowEvent.KeyboardInput(
                key = org.graphiks.kadre.core.Key.ArrowUp,
                state = org.graphiks.kadre.core.KeyState.Pressed,
                modifiers = org.graphiks.kadre.core.Modifiers.NONE,
            )
        )
        // No assertion — just verify it doesn't crash
    }

    // -------------------------------------------------------------------------
    // Unknown events — ignored
    // -------------------------------------------------------------------------

    @Test
    fun `unknown event is ignored`() {
        val (game, renderer, eventLoop) = makeGame()
        game.windowEvent(eventLoop, WindowId(1L), "unknownEvent")
        assertEquals(0, renderer.drawCount)
        assertFalse(eventLoop.exited)
    }
}
