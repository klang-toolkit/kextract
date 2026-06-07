package org.graphiks.kadre.samples.simulation.scenarios.window

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class MultiWindowScenario : WindowScenario(
    id = "window-multi",
    title = "Multi-fenêtres",
    description = "Teste la création de fenêtres multiples. Appuyez sur N pour une nouvelle fenêtre.",
    priority = 80
) {
    override val requiredCapabilities: Set<Capability> = setOf(Capability.MULTI_WINDOW)
    private val extraWindows = mutableListOf<Window>()
    private var windowCount = 0

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        extraWindows.clear()
        windowCount = 1
        onEvent(ScenarioEvent.Message("Appuyez sur N pour créer une nouvelle fenêtre", MessageSeverity.INFO))
    }

    override fun stop() {
        super.stop()
        extraWindows.forEach { it.close() }
        extraWindows.clear()
    }

    override fun onWindowEvent(event: WindowEvent) {
        if (event is WindowEvent.KeyInput) {
            val ke = event.event
            if (ke.isPressed && ke.physicalKey == PhysicalKey.Code(KeyCode.KeyN)) {
                windowCount++
                val newWindow = eventLoop?.createWindow(WindowAttributes(
                    title = "Fenêtre #$windowCount - Simulation Demo",
                    size = PhysicalSize(400, 300),
                    resizable = true,
                    visible = true,
                ))
                if (newWindow != null) {
                    extraWindows.add(newWindow)
                    eventsReceived++
                    onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                        isRunning = true,
                        message = "🪟 Fenêtre #$windowCount créée (${extraWindows.size} au total)",
                        data = mapOf("windows" to extraWindows.size, "new_window" to windowCount)
                    )))
                }
            }
        }
    }
}
