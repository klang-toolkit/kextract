package org.graphiks.kadre.samples.simulation.scenarios.window

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class ResizeScenario : WindowScenario(
    id = "window-resize",
    title = "Redimensionnement",
    description = "Teste les événements de redimensionnement de fenêtre. Redimensionnez la fenêtre et observez les changements de taille.",
    priority = 100
) {
    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        val size = window.innerSize
        onEvent(ScenarioEvent.Message(
            "Redimensionnez la fenêtre. Taille initiale: ${size.width}x${size.height}",
            MessageSeverity.INFO
        ))
    }

    override fun onWindowEvent(event: WindowEvent) {
        when (event) {
            is WindowEvent.Resized -> {
                eventsReceived++
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "📐 Fenêtre redimensionnée: ${event.size.width}x${event.size.height}",
                    data = mapOf("width" to event.size.width, "height" to event.size.height)
                )))
            }
            is WindowEvent.KeyInput -> {
                val ke = event.event
                if (ke.isPressed && ke.physicalKey == PhysicalKey.Code(KeyCode.KeyR)) {
                    window?.requestSurfaceSize(PhysicalSize(1200, 800))
                    eventsReceived++
                    onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                        isRunning = true,
                        message = "🔄 Taille demandée: 1200x800",
                        data = mapOf("width" to 1200, "height" to 800)
                    )))
                }
            }
            else -> {}
        }
    }
}
