package org.graphiks.kadre.samples.simulation.scenarios.touch

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*
import kotlin.math.sqrt

class GesturesScenario : TouchScenario(
    id = "touch-gestures",
    title = "Gestes tactiles",
    description = "Recognizes simple gestures: tap, double-tap, swipe.",
    priority = 80
) {
    override val requiredCapabilities: Set<Capability> = setOf(Capability.MULTI_TOUCH)

    private var gestureStartX = 0.0
    private var gestureStartY = 0.0
    private var isTracking = false
    private var gestureCount = 0

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        onEvent(ScenarioEvent.Message("Try gestures: tap, double-tap, swipe", MessageSeverity.INFO))
    }

    override fun onWindowEvent(event: WindowEvent) {
        when (event) {
            is WindowEvent.PointerButton -> {
                val touch = event.button as? ButtonSource.Touch ?: return
                eventsReceived++
                val fingerId = touch.fingerId
                if (event.state == KeyState.Pressed) {
                    gestureStartX = event.position.x
                    gestureStartY = event.position.y
                    isTracking = true
                    onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                        isRunning = true,
                        message = "🟢 Touch start at (${event.position.x.toInt()}, ${event.position.y.toInt()})",
                        data = mapOf("phase" to "started", "x" to event.position.x.toInt(), "y" to event.position.y.toInt())
                    )))
                } else {
                    if (isTracking) {
                        val dx = event.position.x - gestureStartX
                        val dy = event.position.y - gestureStartY
                        val distance = sqrt(dx * dx + dy * dy)
                        gestureCount++
                        val gestureName = if (distance > 50f) {
                            val direction = when {
                                kotlin.math.abs(dx) > kotlin.math.abs(dy) ->
                                    if (dx > 0) "swipe_right" else "swipe_left"
                                else -> if (dy > 0) "swipe_down" else "swipe_up"
                            }
                            direction
                        } else {
                            "tap"
                        }
                        onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                            isRunning = true,
                            message = "✅ Gesture #$gestureCount: $gestureName (${distance.toInt()}px)",
                            data = mapOf("gesture" to gestureName, "count" to gestureCount)
                        )))
                        isTracking = false
                    }
                }
            }
            is WindowEvent.PointerMoved -> {
                val touch = event.source as? PointerSource.Touch ?: return
                eventsReceived++
                if (isTracking) {
                    val dx = event.position.x - gestureStartX
                    val dy = event.position.y - gestureStartY
                    onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                        isRunning = true,
                        message = "✋ Movement: (${dx.toInt()}, ${dy.toInt()})",
                        data = mapOf("phase" to "moved", "dx" to dx.toInt(), "dy" to dy.toInt())
                    )))
                }
            }
            else -> {}
        }
    }
}
