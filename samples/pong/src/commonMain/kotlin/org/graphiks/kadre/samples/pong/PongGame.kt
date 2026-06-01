/**
 * PongGame — main application handler for Pong.
 *
 * Orchestrates the game loop, the inputs, the AI and the rendering.
 * Delegates rendering to [PongRendererInterface], implemented in jvmMain via wgpu4k.
 *
 * .
 */
package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * Pong render interface — implemented per platform.
 *
 * jvmMain: PongRenderer (wgpu4k)
 * Other platforms: implementations coming in the following tickets.
 */
interface PongRendererInterface {
    fun draw(state: GameState)
    fun resize(width: Int, height: Int)
    fun release()
}

/**
 * Pong application handler.
 *
 * @param rendererFactory Render factory — receives the native [RawWindowHandle]
 *   and returns an initialized [PongRendererInterface].
 */
@OptIn(ExperimentalTime::class)
class PongGame(
    private val rendererFactory: (RawWindowHandle) -> PongRendererInterface
) : ApplicationHandler {

    private var state = GameState.INITIAL
    private val ai = PongAi(reactionLagMs = 80L)
    private val inputAdapter = InputAdapter()
    private var renderer: PongRendererInterface? = null
    private var window: Window? = null
    private var lastFrameMs: Long = 0L

    // -------------------------------------------------------------------------
    // Lifecycle
    // -------------------------------------------------------------------------

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        val win = eventLoop.createWindow(
            WindowAttributes(
                title = "Pong — Kadre",
                size = PhysicalSize(800, 600),
            )
        )
        window = win

        val handle = win.rawWindowHandle
        if (handle is RawWindowHandle) {
            renderer = rendererFactory(handle)
        }

        eventLoop.setControlFlow(ControlFlow.Poll)
    }

    // -------------------------------------------------------------------------
    // Window events
    // -------------------------------------------------------------------------

    override fun windowEvent(
        eventLoop: ActiveEventLoop,
        windowId: WindowId,
        event: WindowEvent,
    ) {
        when (event) {
            is WindowEvent.RedrawRequested -> renderer?.draw(state)
            is WindowEvent.Resized -> renderer?.resize(event.size.width, event.size.height)
            is WindowEvent.CloseRequested -> {
                renderer?.release()
                renderer = null
                eventLoop.exit()
            }
            is WindowEvent.KeyboardInput -> inputAdapter.onKey(event)
            is WindowEvent.Touch -> {
                val win = window
                if (win != null) {
                    inputAdapter.onTouch(event, win.innerSize)
                }
            }
            else -> Unit
        }
    }

    // -------------------------------------------------------------------------
    // Game loop
    // -------------------------------------------------------------------------

    @OptIn(ExperimentalTime::class)
    override fun aboutToWait(eventLoop: ActiveEventLoop) {
        val now = Clock.System.now().toEpochMilliseconds()
        val dt = if (lastFrameMs == 0L) 0.016
                  else ((now - lastFrameMs) / 1000.0).coerceAtMost(0.05)
        lastFrameMs = now

        val aiInput = ai.suggest(state, now)
        state = state.tick(dt, inputAdapter.playerInput, aiInput)
        window?.requestRedraw()
    }

    override fun suspended(eventLoop: ActiveEventLoop) {
        // Pause — nothing to do for now
    }

    override fun resumed(eventLoop: ActiveEventLoop) {
        lastFrameMs = 0L // Avoids a dt spike on return
    }

    override fun destroySurfaces(eventLoop: ActiveEventLoop) {
        renderer?.release()
        renderer = null
    }
}

