package org.graphiks.kadre.samples.simulation.scenarios.mouse

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class ClicksScenario : MouseScenario(
    id = "mouse-clicks",
    title = "Mouse clicks",
    description = "Tests single, double and triple clicks with all buttons (left, right, middle).",
    priority = 100
) {
    private var clickCount = 0

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        onEvent(ScenarioEvent.Message("Click anywhere to test click events", MessageSeverity.INFO))
    }

    override fun onWindowEvent(event: WindowEvent) {
        when (event) {
            is WindowEvent.PointerButton -> {
                mouseEventsReceived++
                val btn = event.button
                val buttonName = when (btn) {
                    is ButtonSource.Mouse -> mouseButtonLabel(btn.button)
                    is ButtonSource.Touch -> "touch#${btn.fingerId.value}"
                    is ButtonSource.TabletTool -> "tablet:${btn.kind}"
                    is ButtonSource.Unknown -> "code:${btn.code}"
                }
                val pos = "${event.position.x.toInt()},${event.position.y.toInt()}"

                if (event.state == KeyState.Pressed) {
                    clickCount++
                    onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                        isRunning = true,
                        message = "🖱️ Click #$clickCount — button: $buttonName at ($pos)",
                        data = mapOf(
                            "click_count" to clickCount,
                            "button" to buttonName,
                            "x" to event.position.x.toInt(),
                            "y" to event.position.y.toInt()
                        )
                    )))
                } else {
                    onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                        isRunning = true,
                        message = "🔄 Released: $buttonName",
                        data = mapOf("released" to buttonName)
                    )))
                }
            }
            else -> {}
        }
    }
}

private fun mouseButtonLabel(button: MouseButton): String = when (button) {
    is MouseButton.Left -> "left"
    is MouseButton.Right -> "right"
    is MouseButton.Middle -> "middle"
    is MouseButton.Other -> "other(${button.button})"
}
