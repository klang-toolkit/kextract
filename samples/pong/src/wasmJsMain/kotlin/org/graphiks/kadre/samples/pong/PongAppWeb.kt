/**
 * PongAppWeb — Pong application handler on the browser side.
 *
 * ARCHITECTURE NOTE
 * -----------------
 * The commonMain [PongGame] filters `WindowEvent.*` (kadre-core), but the
 * Kadre Web backend dispatches `WebWindowEvent.*` (kadre-web-common).
 * The two hierarchies are not unified → PongGame's `when` matches
 * no event on Web, so rendering is never triggered.
 *
 * This file works around that gap by rewriting a dedicated Web
 * [ApplicationHandler] that filters `WebWindowEvent.*` directly. A backend follow-up should
 * unify the hierarchies (or expose a commonMain mapping).
 *
 * Otherwise, the logic is identical to the commonMain [PongGame]: GameState, PongAi,
 * aboutToWait → tick → draw loop.
 */
package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.web.WebEventLoop
import org.graphiks.kadre.web.WebKey
import org.graphiks.kadre.web.WebKeyState
import org.graphiks.kadre.web.WebWindowAttributes
import org.graphiks.kadre.web.WebWindowEvent
import kotlin.js.unsafeCast
import kotlin.math.min

class PongAppWeb : ApplicationHandler {

    private var state = GameState.INITIAL
    private val ai = PongAi(reactionLagMs = 80L)
    private var playerInput = PaddleInput.NONE
    private var renderer: PongRendererWeb? = null
    private var window: Window? = null
    private var lastFrameMs: Long = 0L

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        // winit pattern (WindowAttributesExtWebSys): we explicitly target the
        // sample's canvas by its CSS id, without hijacking `WindowAttributes.title`.
        // If the canvas does not exist in the DOM (case of a page without a pre-declared
        // <canvas>), `appendToBody = true` asks Kadre to create it.
        val webLoop = eventLoop as? WebEventLoop
            ?: error("[pong-web] PongAppWeb requires a WebEventLoop")
        val win = webLoop.createWindow(
            WebWindowAttributes(
                canvasId = "kadre-canvas",
                appendToBody = true,
                width = 800,
                height = 600,
            )
        )
        window = win
        val handle = win.rawWindowHandle
        if (handle is RawWindowHandle) {
            renderer = PongRendererWeb(handle)
        }
        // Poll mode: aboutToWait is called back on each tick (RAF) to animate
        // without depending on DOM events. Without this, in the default Wait mode, we
        // would only draw on user input → black screen at rest.
        eventLoop.setControlFlow(ControlFlow.Poll)
    }

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        when (event) {
            is WebWindowEvent.RedrawRequested -> renderer?.draw(state)
            is WebWindowEvent.Resized -> renderer?.resize(event.width, event.height)
            is WebWindowEvent.CloseRequested -> {
                renderer?.release()
                renderer = null
                eventLoop.exit()
            }
            is WebWindowEvent.KeyboardInput -> onKey(event)
            else -> { /* PointerMoved/Entered/Left/MouseInput/MouseWheel/Focused ignored */ }
        }
    }

    private fun onKey(event: WebWindowEvent.KeyboardInput) {
        // Debug log: confirms the arrival of keyboard events on the handler side.
        println("[pong-web] key ${event.key} ${event.state}")
        playerInput = when {
            event.key == WebKey.ArrowUp && event.state == WebKeyState.Pressed -> PaddleInput.UP
            event.key == WebKey.ArrowDown && event.state == WebKeyState.Pressed -> PaddleInput.DOWN
            event.key == WebKey.ArrowUp && event.state == WebKeyState.Released && playerInput == PaddleInput.UP -> PaddleInput.NONE
            event.key == WebKey.ArrowDown && event.state == WebKeyState.Released && playerInput == PaddleInput.DOWN -> PaddleInput.NONE
            else -> playerInput
        }
    }

    override fun aboutToWait(eventLoop: ActiveEventLoop) {
        // Direct tick + draw; no requestRedraw (the PongRendererWeb is called
        // synchronously from here, and the web loop calls aboutToWait back on the
        // next frame via the Poll mode that the backend manages).
        val now = currentTimeMs()
        val dt = if (lastFrameMs == 0L) 0.016
                  else min((now - lastFrameMs) / 1000.0, 0.05)
        lastFrameMs = now

        val aiInput = ai.suggest(state, now)
        state = state.tick(dt, playerInput, aiInput)
        renderer?.draw(state)
    }
}

/**
 * JS clock — `Date.now()` returns the Unix time in ms.
 *
 * In Kotlin/Wasm, `js(...)` must be the sole expression of a top-level function
 * body and its return type is inferred from the signature. So we return
 * a `Double` then convert it to `Long` on the Kotlin side.
 */
private fun jsDateNow(): Double = js("Date.now()")
private fun currentTimeMs(): Long = jsDateNow().toLong()
