package org.graphiks.kadre.samples.simulation.scenarios.keyboard

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

abstract class KeyboardScenario(
    override val id: String,
    override val title: String,
    override val description: String,
    override val priority: Int = 0
) : Scenario {
    override val category: String = "Clavier"
    override val requiredCapabilities: Set<Capability> = setOf(Capability.KEYBOARD)

    protected var window: Window? = null
    protected var eventLoop: ActiveEventLoop? = null
    protected var onEvent: ((ScenarioEvent) -> Unit)? = null
    protected var isRunning: Boolean = false

    protected var keyEventsReceived: Int = 0
    protected var keyEventsExpected: Int = 100

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        this.window = window
        this.eventLoop = eventLoop
        this.onEvent = onEvent
        this.isRunning = true
        this.keyEventsReceived = 0

        onEvent(ScenarioEvent.StateChanged(ScenarioState(
            isRunning = true,
            message = "Appuyez sur des touches pour interagir..."
        )))
    }

    override fun stop() {
        isRunning = false
    }

    override fun onWindowEvent(event: WindowEvent) {
        if (event is WindowEvent.KeyInput) {
            keyEventsReceived++
            onKeyEvent(event)
        }
    }

    protected open fun onKeyEvent(event: WindowEvent.KeyInput) {}

    override fun collectResult(durationMs: Long): ScenarioResult {
        return ScenarioResult(
            success = true,
            durationMs = durationMs,
            eventsReceived = keyEventsReceived,
            eventsExpected = keyEventsExpected,
            platform = Platform.current()
        )
    }
}
