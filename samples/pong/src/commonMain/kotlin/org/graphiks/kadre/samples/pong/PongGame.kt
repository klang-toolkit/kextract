/**
 * PongGame — gestionnaire d'application principal pour Pong.
 *
 * Orchestre la boucle de jeu, les entrées, l'IA et le rendu.
 * Délègue le rendu à [PongRendererInterface], implémentée en jvmMain via wgpu4k.
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
 * Interface de rendu Pong — implémentée par plateforme.
 *
 * jvmMain : PongRenderer (wgpu4k)
 * Autres plateformes : implémentations à venir dans les tickets suivants.
 */
interface PongRendererInterface {
    fun draw(state: GameState)
    fun resize(width: Int, height: Int)
    fun release()
}

/**
 * Gestionnaire d'application Pong.
 *
 * @param rendererFactory Fabrique de rendu — reçoit le [RawWindowHandle] natif
 *   et retourne un [PongRendererInterface] initialisé.
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
    // Cycle de vie
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
    // Événements fenêtre
    // -------------------------------------------------------------------------

    override fun windowEvent(
        eventLoop: ActiveEventLoop,
        windowId: WindowId,
        event: Any
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
    // Boucle de jeu
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
        // Pause — rien à faire pour l'instant
    }

    override fun resumed(eventLoop: ActiveEventLoop) {
        lastFrameMs = 0L // Évite un spike de dt au retour
    }

    override fun destroySurfaces(eventLoop: ActiveEventLoop) {
        renderer?.release()
        renderer = null
    }
}

