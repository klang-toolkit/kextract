package org.graphiks.kadre.samples.simulation.scenarios.window

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class FullscreenScenario : WindowScenario(
    id = "window-fullscreen",
    title = "Plein écran",
    description = "Teste le passage en plein écran. Appuyez sur F pour basculer, Échap pour quitter.",
    priority = 90
) {
    private var isMaximized = false

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        onEvent(ScenarioEvent.Message("Appuyez sur F pour basculer en plein écran (maximisé)", MessageSeverity.INFO))
    }

    override fun onWindowEvent(event: WindowEvent) {
        if (event is WindowEvent.KeyInput) {
            val ke = event.event
            if (ke.isPressed && ke.physicalKey == PhysicalKey.Code(KeyCode.KeyF)) {
                isMaximized = !isMaximized
                window?.setMaximized(isMaximized)
                eventsReceived++
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = if (isMaximized) "🖥️ Plein écran activé (maximisé)" else "🖥️ Plein écran désactivé",
                    data = mapOf("maximized" to isMaximized)
                )))
            }
        }
    }
}
