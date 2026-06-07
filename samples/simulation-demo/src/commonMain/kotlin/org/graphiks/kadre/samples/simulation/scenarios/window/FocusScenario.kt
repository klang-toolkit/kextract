package org.graphiks.kadre.samples.simulation.scenarios.window

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class FocusScenario : WindowScenario(
    id = "window-focus",
    title = "Focus et événements de fenêtre",
    description = "Teste les événements de focus, déplacement, minimisation et restauration de la fenêtre.",
    priority = 70
) {
    private var focusCount = 0

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        onEvent(ScenarioEvent.Message("Déplacez la fenêtre, changez de focus, minimisez-la (touche M)", MessageSeverity.INFO))
    }

    override fun onWindowEvent(event: WindowEvent) {
        when (event) {
            is WindowEvent.Focused -> {
                eventsReceived++
                focusCount++
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = if (event.gained) "🔵 Fenêtre focus gagné" else "⚫ Fenêtre focus perdu",
                    data = mapOf("focused" to event.gained, "focus_count" to focusCount)
                )))
            }
            is WindowEvent.Moved -> {
                eventsReceived++
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "📦 Fenêtre déplacée à (${event.position.x}, ${event.position.y})",
                    data = mapOf("x" to event.position.x, "y" to event.position.y)
                )))
            }
            is WindowEvent.KeyInput -> {
                val ke = event.event
                if (ke.isPressed && ke.physicalKey == PhysicalKey.Code(KeyCode.KeyM)) {
                    window?.setMinimized(true)
                    eventsReceived++
                    onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                        isRunning = true,
                        message = "🗕️ Fenêtre minimisée"
                    )))
                }
            }
            else -> {}
        }
    }
}
