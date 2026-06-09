package org.graphiks.kadre.samples.simulation.scenarios.touch

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class SingleTouchScenario : TouchScenario(
    id = "touch-single",
    title = "Touch simple",
    description = "Tests touch events with a single finger. Press, move, release.",
    priority = 100
) {
    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        onEvent(ScenarioEvent.Message("Touch the screen to see events", MessageSeverity.INFO))
    }

    override fun onWindowEvent(event: WindowEvent) {
        when (event) {
            is WindowEvent.PointerButton -> {
                val touch = event.button as? ButtonSource.Touch ?: return
                eventsReceived++
                val action = if (event.state == KeyState.Pressed) "touch_start" else "touch_end"
                val pos = "${event.position.x.toInt()},${event.position.y.toInt()}"
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "👆 Touch: $action at ($pos)",
                    data = mapOf(
                        "phase" to action,
                        "x" to event.position.x.toInt(),
                        "y" to event.position.y.toInt()
                    )
                )))
            }
            is WindowEvent.PointerMoved -> {
                val touch = event.source as? PointerSource.Touch ?: return
                eventsReceived++
                val pos = "${event.position.x.toInt()},${event.position.y.toInt()}"
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "👆 Touch: moved at ($pos)",
                    data = mapOf(
                        "phase" to "moved",
                        "x" to event.position.x.toInt(),
                        "y" to event.position.y.toInt()
                    )
                )))
            }
            else -> {}
        }
    }
}
