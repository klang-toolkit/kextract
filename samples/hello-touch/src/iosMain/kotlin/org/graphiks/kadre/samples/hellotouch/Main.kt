package org.graphiks.kadre.samples.hellotouch

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.Window
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
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

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        if (event is WindowEvent.Touch) {
            println("[HelloTouch] Touch ${event.phase} id=${event.id} @ (${event.location.x.toInt()}, ${event.location.y.toInt()})")
        }
    }

    override fun resumed(eventLoop: ActiveEventLoop) = println("[HelloTouch] resumed")
    override fun suspended(eventLoop: ActiveEventLoop) = println("[HelloTouch] suspended")
    override fun destroySurfaces(eventLoop: ActiveEventLoop) = println("[HelloTouch] destroySurfaces")
}
