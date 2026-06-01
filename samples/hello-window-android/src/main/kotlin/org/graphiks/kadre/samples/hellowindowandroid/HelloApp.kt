/**
 * Shared cross-platform handler — identical to samples/hello-window/commonMain.
 *
 * On all platforms (Android included), [createWindow] is called from
 * [canCreateSurfaces]. On Android, the surface is not yet available at
 * the time of [createWindow]; it becomes available a few moments later via
 * [AndroidWindow.onSurfaceAvailable]. Renderers must access
 * [rawWindowHandle] only in or after this callback.
 */
package org.graphiks.kadre.samples.hellowindowandroid

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.Window
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.WindowEvent

/**
 * Hello Window demonstration handler.
 *
 * Creates a window via [ActiveEventLoop.createWindow] in [canCreateSurfaces]
 * and logs all received events. Identical on Android, iOS and JVM.
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

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
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
                println("[HelloWindow] PointerMoved ${event.source} (${event.position.x.toInt()}, ${event.position.y.toInt()})")
            is WindowEvent.PointerEntered ->
                println("[HelloWindow] PointerEntered ${event.kind} @ (${event.position.x.toInt()}, ${event.position.y.toInt()})")
            is WindowEvent.PointerLeft ->
                println("[HelloWindow] PointerLeft ${event.kind}")
            is WindowEvent.PointerButton ->
                println("[HelloWindow] PointerButton ${event.state} button=${event.button} @ (${event.position.x.toInt()}, ${event.position.y.toInt()})")
            is WindowEvent.MouseWheel ->
                println("[HelloWindow] MouseWheel dx=${event.deltaX} dy=${event.deltaY}")
            is WindowEvent.PinchGesture ->
                println("[HelloWindow] PinchGesture delta=${event.delta} phase=${event.phase}")
            is WindowEvent.PanGesture ->
                println("[HelloWindow] PanGesture delta=(${event.delta.x}, ${event.delta.y}) phase=${event.phase}")
            is WindowEvent.RotationGesture ->
                println("[HelloWindow] RotationGesture deltaDegrees=${event.deltaDegrees} phase=${event.phase}")
            is WindowEvent.DoubleTapGesture ->
                println("[HelloWindow] DoubleTapGesture device=${event.deviceId}")
            is WindowEvent.TouchpadPressure ->
                println("[HelloWindow] TouchpadPressure pressure=${event.pressure} stage=${event.stage}")
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
