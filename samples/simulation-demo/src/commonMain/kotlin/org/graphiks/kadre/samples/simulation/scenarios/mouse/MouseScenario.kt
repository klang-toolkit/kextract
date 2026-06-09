package org.graphiks.kadre.samples.simulation.scenarios.mouse

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

abstract class MouseScenario(
    override val id: String,
    override val title: String,
    override val description: String,
    override val priority: Int = 0
) : Scenario {
    override val category: String = "Mouse"
    override val requiredCapabilities: Set<Capability> = setOf(Capability.MOUSE)

    protected var window: Window? = null
    protected var eventLoop: ActiveEventLoop? = null
    protected var onEvent: ((ScenarioEvent) -> Unit)? = null
    protected var isRunning: Boolean = false

    protected var mouseEventsReceived: Int = 0

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        this.window = window
        this.eventLoop = eventLoop
        this.onEvent = onEvent
        this.isRunning = true
        this.mouseEventsReceived = 0

        onEvent(ScenarioEvent.StateChanged(ScenarioState(
            isRunning = true,
            message = "Interact with the mouse..."
        )))
    }

    override fun stop() {
        isRunning = false
    }

    override fun onWindowEvent(event: WindowEvent) {
    }

    override fun collectResult(durationMs: Long): ScenarioResult {
        return ScenarioResult(
            success = true,
            durationMs = durationMs,
            eventsReceived = mouseEventsReceived,
            eventsExpected = 50,
            platform = Platform.current()
        )
    }
}
