package org.graphiks.kadre.samples.hellotouch

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.Window
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.WindowEvent

/**
 * iOS entry point — launches the EventLoop with the iOS handler.
 *
 * Window creation is iOS-specific: on Android, the window
 * is managed by KadreActivity and is not created manually here.
 */
fun main() {
    EventLoop().runApp(IosHelloTouchHandler())
}

/**
 * iOS handler — creates the main window and delegates touch events.
 *
 * On iOS, `canCreateSurfaces` must call `eventLoop.createWindow()`;
 * this behavior is specific to the iOS platform.
 * The touch event handling logic is duplicated from
 * [HelloTouchHandler] (commonMain) to avoid inheriting from a final class.
 */
private class IosHelloTouchHandler : ApplicationHandler {

    private var window: Window? = null

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        println("[HelloTouch] canCreateSurfaces — surface ready for rendering")
        window = eventLoop.createWindow(WindowAttributes(title = "Hello Touch"))
        println("[HelloTouch] window created id=${window?.id?.value}")
    }

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
        when (event) {
            is WindowEvent.PointerMoved -> if (event.source is PointerSource.Touch) {
                println("[HelloTouch] Touch move @ (${event.position.x.toInt()}, ${event.position.y.toInt()})")
            }
            is WindowEvent.PointerButton -> if (event.button is ButtonSource.Touch) {
                println("[HelloTouch] Touch ${event.state} @ (${event.position.x.toInt()}, ${event.position.y.toInt()})")
            }
            else -> Unit
        }
    }

    override fun resumed(eventLoop: ActiveEventLoop) = println("[HelloTouch] resumed")
    override fun suspended(eventLoop: ActiveEventLoop) = println("[HelloTouch] suspended")
    override fun destroySurfaces(eventLoop: ActiveEventLoop) = println("[HelloTouch] destroySurfaces")
}
