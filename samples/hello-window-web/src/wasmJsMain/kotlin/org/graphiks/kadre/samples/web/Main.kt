/**
 * Sample hello-window-web — wasmJs entry point.
 *
 * Opens a browser canvas via the Kadre API and logs all received
 * DOM events (mouse, keyboard, resize, close).
 *
 * minimal wasmJs web sample.
 */
package org.graphiks.kadre.samples.web

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.WindowEvent

/**
 * Hello Window Web demonstration handler (wasmJs).
 *
 * Creates a canvas window at startup and logs all received events.
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

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
        when (event) {
            is WindowEvent.CloseRequested -> {
                println("[HelloWindowWeb] CloseRequested — exiting")
                eventLoop.exit()
            }
            is WindowEvent.Resized ->
                println("[HelloWindowWeb] Resized → ${event.size.width}×${event.size.height}")
            is WindowEvent.Focused ->
                println("[HelloWindowWeb] Focused gained=${event.gained}")
            is WindowEvent.KeyInput ->
                println("[HelloWindowWeb] KeyInput ${event.event.state} physical=${event.event.physicalKey} logical=${event.event.logicalKey} mods=${event.event.modifiers.bits} repeat=${event.event.repeat}")
            is WindowEvent.PointerMoved ->
                println("[HelloWindowWeb] PointerMoved (${event.position.x.toInt()}, ${event.position.y.toInt()})")
            is WindowEvent.PointerEntered ->
                println("[HelloWindowWeb] PointerEntered")
            is WindowEvent.PointerLeft ->
                println("[HelloWindowWeb] PointerLeft")
            is WindowEvent.PointerButton ->
                println("[HelloWindowWeb] PointerButton ${event.state} button=${event.button}")
            is WindowEvent.MouseWheel ->
                println("[HelloWindowWeb] MouseWheel dx=${event.deltaX} dy=${event.deltaY}")
            is WindowEvent.RedrawRequested ->
                Unit // no-op: no renderer in this sample
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
