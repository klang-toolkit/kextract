package org.graphiks.kadre.samples.simulation.scenarios.touch

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

abstract class TouchScenario(
    override val id: String,
    override val title: String,
    override val description: String,
    override val priority: Int = 0
) : Scenario {
    override val category: String = "Touch"
    override val requiredCapabilities: Set<Capability> = setOf(Capability.TOUCH)

    protected var window: Window? = null
    protected var eventLoop: ActiveEventLoop? = null
    protected var onEvent: ((ScenarioEvent) -> Unit)? = null
    protected var isRunning: Boolean = false
    protected var eventsReceived: Int = 0

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        this.window = window
        this.eventLoop = eventLoop
        this.onEvent = onEvent
        this.isRunning = true
        this.eventsReceived = 0

        onEvent(ScenarioEvent.StateChanged(ScenarioState(
            isRunning = true,
            message = "Scénario touch activé..."
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
            eventsReceived = eventsReceived,
            eventsExpected = 20,
            platform = Platform.current()
        )
    }
}
