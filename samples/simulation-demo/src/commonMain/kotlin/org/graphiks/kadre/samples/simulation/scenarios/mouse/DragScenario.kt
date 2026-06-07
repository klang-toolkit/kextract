package org.graphiks.kadre.samples.simulation.scenarios.mouse

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class DragScenario : MouseScenario(
    id = "mouse-drag",
    title = "Glisser-déposer",
    description = "Teste les événements de drag : Press → Move → Release. Visualisez la trajectoire du glissement.",
    priority = 90
) {
    private var isDragging = false
    private var startX = 0.0
    private var startY = 0.0
    private var dragCount = 0

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        onEvent(ScenarioEvent.Message("Appuyez et maintenez le bouton, déplacez la souris, puis relâchez", MessageSeverity.INFO))
    }

    override fun onWindowEvent(event: WindowEvent) {
        when (event) {
            is WindowEvent.PointerButton -> {
                val isMouseButton = event.button is ButtonSource.Mouse
                if (!isMouseButton) return
                if (event.state == KeyState.Pressed) {
                    isDragging = true
                    startX = event.position.x
                    startY = event.position.y
                    mouseEventsReceived++
                    onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                        isRunning = true,
                        message = "🟢 Début du glissement à (${startX.toInt()}, ${startY.toInt()})",
                        data = mapOf("drag_start_x" to startX.toInt(), "drag_start_y" to startY.toInt())
                    )))
                } else {
                    if (isDragging) {
                        isDragging = false
                        val dx = event.position.x - startX
                        val dy = event.position.y - startY
                        mouseEventsReceived++
                        onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                            isRunning = true,
                            message = "🔴 Fin du glissement — déplacement total: (${dx.toInt()}, ${dy.toInt()})",
                            data = mapOf("total_dx" to dx.toInt(), "total_dy" to dy.toInt(), "total_steps" to dragCount)
                        )))
                    }
                }
            }
            is WindowEvent.PointerMoved -> {
                if (isDragging) {
                    dragCount++
                    val dx = event.position.x - startX
                    val dy = event.position.y - startY
                    mouseEventsReceived++
                    onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                        isRunning = true,
                        message = "📐 Drag #$dragCount — déplacement: (${dx.toInt()}, ${dy.toInt()})",
                        data = mapOf("dx" to dx.toInt(), "dy" to dy.toInt(), "drag_step" to dragCount)
                    )))
                }
            }
            else -> {}
        }
    }
}
