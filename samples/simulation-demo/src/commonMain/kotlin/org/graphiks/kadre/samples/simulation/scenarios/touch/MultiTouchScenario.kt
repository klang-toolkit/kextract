package org.graphiks.kadre.samples.simulation.scenarios.touch

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class MultiTouchScenario : TouchScenario(
    id = "touch-multi",
    title = "Multi-touch",
    description = "Teste les événements multi-touch avec plusieurs doigts simultanés.",
    priority = 90
) {
    override val requiredCapabilities: Set<Capability> = setOf(Capability.MULTI_TOUCH)
    private val activeFingers = mutableMapOf<FingerId, PhysicalPosition<Double>>()

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        activeFingers.clear()
        onEvent(ScenarioEvent.Message("Utilisez plusieurs doigts pour tester le multi-touch", MessageSeverity.INFO))
    }

    override fun onWindowEvent(event: WindowEvent) {
        when (event) {
            is WindowEvent.PointerButton -> {
                val touch = event.button as? ButtonSource.Touch ?: return
                eventsReceived++
                val fingerId = touch.fingerId
                if (event.state == KeyState.Pressed) {
                    activeFingers[fingerId] = event.position
                } else {
                    activeFingers.remove(fingerId)
                }
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "🖐️ Doigts actifs: ${activeFingers.size} | ID: ${fingerId.value} à (${event.position.x.toInt()}, ${event.position.y.toInt()})",
                    data = mapOf(
                        "active_fingers" to activeFingers.size,
                        "finger_id" to fingerId.value
                    )
                )))
            }
            is WindowEvent.PointerMoved -> {
                val touch = event.source as? PointerSource.Touch ?: return
                eventsReceived++
                val fingerId = touch.fingerId
                activeFingers[fingerId] = event.position
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "🖐️ Doigts actifs: ${activeFingers.size} | ID: $fingerId à (${event.position.x.toInt()}, ${event.position.y.toInt()})",
                    data = mapOf(
                        "active_fingers" to activeFingers.size,
                        "finger_id" to fingerId.value
                    )
                )))
            }
            else -> {}
        }
    }
}
