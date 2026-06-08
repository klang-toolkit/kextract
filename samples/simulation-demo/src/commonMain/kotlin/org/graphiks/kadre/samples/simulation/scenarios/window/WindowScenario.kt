package org.graphiks.kadre.samples.simulation.scenarios.window

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

abstract class WindowScenario(
    override val id: String,
    override val title: String,
    override val description: String,
    override val priority: Int = 0
) : Scenario {
    override val category: String = "Fenêtre"
    override val requiredCapabilities: Set<Capability> = emptySet()

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
            message = "Scénario fenêtre activé..."
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
            eventsExpected = 10,
            platform = Platform.current()
        )
    }
}
