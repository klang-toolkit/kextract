/**
 * PongAppWeb — gestionnaire d'application Pong côté navigateur.
 *
 * NOTE D'ARCHITECTURE
 * -------------------
 * Le [PongGame] commonMain filtre les `WindowEvent.*` (kadre-core), mais le
 * backend Web de Kadre dispatch des `WebWindowEvent.*` (kadre-web-common).
 * Les deux hiérarchies ne sont pas unifiées → le `when` de PongGame ne matche
 * aucun event sur Web, donc le rendu n'est jamais déclenché.
 *
 * Ce fichier contourne ce trou en réécrivant un [ApplicationHandler] dédié
 * Web qui filtre `WebWindowEvent.*` directement. Un suivi côté backend doit
 * unifier les hiérarchies (ou exposer un mapping commonMain).
 *
 * Sinon, la logique est identique au [PongGame] commonMain : GameState, PongAi,
 * boucle aboutToWait → tick → draw.
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
        // Pattern winit (WindowAttributesExtWebSys) : on cible explicitement le
        // canvas du sample par son id CSS, sans détourner `WindowAttributes.title`.
        // Si le canvas n'existe pas dans le DOM (cas d'une page sans <canvas>
        // pré-déclaré), `appendToBody = true` demande à Kadre de le créer.
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
        // Mode Poll : aboutToWait est rappelé à chaque tick (RAF) pour animer
        // sans dépendre d'events DOM. Sans cela, en mode Wait par défaut, on
        // n'aurait un draw que sur input utilisateur → écran noir au repos.
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
            else -> { /* PointerMoved/Entered/Left/MouseInput/MouseWheel/Focused ignorés */ }
        }
    }

    private fun onKey(event: WebWindowEvent.KeyboardInput) {
        // Log de debug : confirme l'arrivée des events clavier côté handler.
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
        // Tick + draw direct ; pas de requestRedraw (le PongRendererWeb est appelé
        // synchroniquement depuis ici, et la boucle web rappelle aboutToWait à la
        // frame suivante via le mode Poll que le backend gère).
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
 * Horloge JS — `Date.now()` retourne le temps Unix en ms.
 *
 * En Kotlin/Wasm, `js(...)` doit être l'expression unique d'un corps de fonction
 * top-level et son type de retour est inféré depuis la signature. On retourne
 * donc un `Double` puis on convertit en `Long` côté Kotlin.
 */
private fun jsDateNow(): Double = js("Date.now()")
private fun currentTimeMs(): Long = jsDateNow().toLong()
