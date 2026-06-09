package org.graphiks.kadre.samples.simulation.scenarios.window

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class FullscreenScenario : WindowScenario(
    id = "window-fullscreen",
    title = "Fullscreen",
    description = "Tests fullscreen toggle. Press F to toggle, Esc to quit.",
    priority = 90
) {
    private var isMaximized = false

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        onEvent(ScenarioEvent.Message("Press F to toggle fullscreen (maximized)", MessageSeverity.INFO))
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
                    message = if (isMaximized) "🖥️ Fullscreen enabled (maximized)" else "🖥️ Fullscreen disabled",
                    data = mapOf("maximized" to isMaximized)
                )))
            }
        }
    }
}
