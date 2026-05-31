/**
 * Handler partagé cross-platform — identique à samples/hello-window/commonMain.
 *
 * Sur toutes les plateformes (Android inclus), [createWindow] est appelé depuis
 * [canCreateSurfaces]. Sur Android, la surface n'est pas encore disponible au
 * moment de [createWindow] ; elle le devient quelques instants plus tard via
 * [AndroidWindow.onSurfaceAvailable]. Les renderers doivent accéder à
 * [rawWindowHandle] uniquement dans ou après ce callback.
 */
package org.graphiks.kadre.samples.hellowindowandroid

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.Window
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.WindowEvent

/**
 * Handler de démonstration Hello Window.
 *
 * Crée une fenêtre via [ActiveEventLoop.createWindow] dans [canCreateSurfaces]
 * et logue tous les événements reçus. Identique sur Android, iOS et JVM.
 */
class HelloApp : ApplicationHandler {

    private var window: Window? = null

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        println("[HelloWindow] canCreateSurfaces")
        window = eventLoop.createWindow(
            WindowAttributes(title = "Hello Window — Kadre Android")
        )
        println("[HelloWindow] window created id=${window?.id?.value}")
    }

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        when (event) {
            WindowEvent.CloseRequested -> {
                println("[HelloWindow] CloseRequested — exiting")
                eventLoop.exit()
            }
            is WindowEvent.Resized ->
                println("[HelloWindow] Resized → ${event.size.width}×${event.size.height}")
            is WindowEvent.Moved ->
                println("[HelloWindow] Moved → (${event.position.x}, ${event.position.y})")
            is WindowEvent.ScaleFactorChanged ->
                println("[HelloWindow] ScaleFactorChanged → ${event.factor}")
            is WindowEvent.Focused ->
                println("[HelloWindow] Focused gained=${event.gained}")
            is WindowEvent.KeyboardInput ->
                println("[HelloWindow] KeyboardInput ${event.state} key=${event.key} mods=${event.modifiers.bits} repeat=${event.isRepeat}")
            is WindowEvent.PointerMoved ->
                println("[HelloWindow] PointerMoved (${event.position.x.toInt()}, ${event.position.y.toInt()})")
            WindowEvent.PointerEntered ->
                println("[HelloWindow] PointerEntered")
            WindowEvent.PointerLeft ->
                println("[HelloWindow] PointerLeft")
            is WindowEvent.MouseInput ->
                println("[HelloWindow] MouseInput ${event.state} button=${event.button}")
            is WindowEvent.MouseWheel ->
                println("[HelloWindow] MouseWheel dx=${event.deltaX} dy=${event.deltaY}")
            is WindowEvent.Touch ->
                println("[HelloWindow] Touch ${event.phase} id=${event.id} @ (${event.location.x.toInt()}, ${event.location.y.toInt()})")
            WindowEvent.RedrawRequested ->
                Unit // no-op: no renderer in this sample
            WindowEvent.Destroyed ->
                println("[HelloWindow] Destroyed")
            else ->
                println("[HelloWindow] unknown event: $event")
        }
    }

    override fun resumed(eventLoop: ActiveEventLoop) =
        println("[HelloWindow] resumed")

    override fun suspended(eventLoop: ActiveEventLoop) =
        println("[HelloWindow] suspended")

    override fun destroySurfaces(eventLoop: ActiveEventLoop) {
        println("[HelloWindow] destroySurfaces")
        window = null
    }
}
