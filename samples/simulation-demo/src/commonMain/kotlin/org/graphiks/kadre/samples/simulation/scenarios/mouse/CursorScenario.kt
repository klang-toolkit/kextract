package org.graphiks.kadre.samples.simulation.scenarios.mouse

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class CursorScenario : MouseScenario(
    id = "mouse-cursor",
    title = "Cursor position and visibility",
    description = "Tests cursor position tracking, grab and visibility changes.",
    priority = 70
) {
    override val requiredCapabilities: Set<Capability> = setOf(
        Capability.MOUSE,
        Capability.CURSOR_POSITION
    )

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        onEvent(ScenarioEvent.Message("Move the mouse to see the real-time position", MessageSeverity.INFO))
    }

    override fun onWindowEvent(event: WindowEvent) {
        if (event is WindowEvent.PointerMoved) {
            mouseEventsReceived++
            onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                isRunning = true,
                message = "📍 Position: (${event.position.x.toInt()}, ${event.position.y.toInt()})",
                data = mapOf("x" to event.position.x.toInt(), "y" to event.position.y.toInt())
            )))
        }
    }
}
