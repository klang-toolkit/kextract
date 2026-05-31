/**
 * Sample hello-window-web — point d'entrée JS/IR.
 *
 * Ouvre un canvas navigateur via l'API Kadre et journalise tous les événements
 * DOM reçus (souris, clavier, redimensionnement, fermeture).
 *
 * Redmine #26 : sample web minimal JS.
 */
package org.graphiks.kadre.samples.web

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.web.WebWindowEvent

/**
 * Handler de démonstration Hello Window Web (JS/IR).
 *
 * Crée une fenêtre canvas au démarrage et logue tous les événements reçus.
 */
class HelloWindowWebApp : ApplicationHandler {

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        println("[HelloWindowWeb] canCreateSurfaces")
        val window = eventLoop.createWindow(
            WindowAttributes(
                title = "Hello Window Web",
                resizable = true,
            )
        )
        println("[HelloWindowWeb] window created id=${window.id.value}")
    }

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        when (event) {
            is WebWindowEvent.CloseRequested -> {
                println("[HelloWindowWeb] CloseRequested — exiting")
                eventLoop.exit()
            }
            is WebWindowEvent.Resized ->
                println("[HelloWindowWeb] Resized → ${event.width}×${event.height}")
            is WebWindowEvent.Focused ->
                println("[HelloWindowWeb] Focused gained=${event.gained}")
            is WebWindowEvent.KeyboardInput ->
                println("[HelloWindowWeb] KeyboardInput ${event.state} key=${event.key} mods=${event.modifiers.bits} repeat=${event.isRepeat}")
            is WebWindowEvent.PointerMoved ->
                println("[HelloWindowWeb] PointerMoved (${event.x.toInt()}, ${event.y.toInt()})")
            is WebWindowEvent.PointerEntered ->
                println("[HelloWindowWeb] PointerEntered")
            is WebWindowEvent.PointerLeft ->
                println("[HelloWindowWeb] PointerLeft")
            is WebWindowEvent.MouseInput ->
                println("[HelloWindowWeb] MouseInput ${event.state} button=${event.button}")
            is WebWindowEvent.MouseWheel ->
                println("[HelloWindowWeb] MouseWheel dx=${event.deltaX} dy=${event.deltaY}")
            is WebWindowEvent.RedrawRequested ->
                Unit // no-op: pas de renderer dans ce sample
            else ->
                println("[HelloWindowWeb] event: $event")
        }
    }

    override fun resumed(eventLoop: ActiveEventLoop) =
        println("[HelloWindowWeb] resumed")

    override fun suspended(eventLoop: ActiveEventLoop) =
        println("[HelloWindowWeb] suspended")
}

fun main() {
    EventLoop().runApp(HelloWindowWebApp())
}
