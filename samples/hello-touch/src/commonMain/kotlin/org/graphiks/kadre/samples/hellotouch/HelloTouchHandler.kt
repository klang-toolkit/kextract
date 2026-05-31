package org.graphiks.kadre.samples.hellotouch

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.WindowEvent

/**
 * Hello Touch demonstration handler — shared between Android and iOS.
 *
 * Demonstrates Kadre API convergence across all platforms:
 * - [canCreateSurfaces]: signals that the system is ready for rendering
 * - [windowEvent]: reception and logging of touch events
 * - [resumed] / [suspended]: application lifecycle
 * - [destroySurfaces]: release of render surfaces
 *
 * On Android, the window is managed by KadreActivity — no call
 * to `createWindow()` needed here.
 */
class HelloTouchHandler : ApplicationHandler {

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        println("[HelloTouch] canCreateSurfaces — surface ready for rendering")
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
