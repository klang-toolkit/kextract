package org.graphiks.kadre.samples.simulation.scenarios.mouse

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class ScrollScenario : MouseScenario(
    id = "mouse-scroll",
    title = "Défilement (scroll)",
    description = "Teste les événements de défilement horizontal et vertical avec la molette.",
    priority = 80
) {
    private var scrollY = 0.0
    private var scrollX = 0.0

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        onEvent(ScenarioEvent.Message("Utilisez la molette de la souris pour voir les événements de scroll", MessageSeverity.INFO))
    }

    override fun onWindowEvent(event: WindowEvent) {
        if (event is WindowEvent.MouseWheel) {
            mouseEventsReceived++
            scrollY += event.deltaY
            scrollX += event.deltaX

            val direction = when {
                event.deltaY > 0 -> "⬆️ Haut"
                event.deltaY < 0 -> "⬇️ Bas"
                event.deltaX > 0 -> "➡️ Droite"
                event.deltaX < 0 -> "⬅️ Gauche"
                else -> "—"
            }

            onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                isRunning = true,
                message = "$direction — delta: (${event.deltaX.toInt()}, ${event.deltaY.toInt()}) | Total: (${scrollX.toInt()}, ${scrollY.toInt()})",
                data = mapOf(
                    "delta_x" to event.deltaX.toInt(),
                    "delta_y" to event.deltaY.toInt(),
                    "total_x" to scrollX.toInt(),
                    "total_y" to scrollY.toInt()
                )
            )))
        }
    }
}
