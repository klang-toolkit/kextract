package org.graphiks.kadre.samples.simulation

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.DeviceEvent
import org.graphiks.kadre.DeviceId
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.PhysicalSize
import org.graphiks.kadre.Window
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.WindowEvent

class SimulationAppHandler : ApplicationHandler {
    private var activeWindow: Window? = null
    private var activeScenario: Scenario? = null
    private var eventLoop: ActiveEventLoop? = null

    var currentScenarioState: ScenarioState = ScenarioState()
        private set

    val results: MutableList<ScenarioResult> = mutableListOf()

    var isInScenario: Boolean = false
        private set

    val activeScenarioId: String?
        get() = activeScenario?.id

    private var scenarioEventCallback: (ScenarioEvent) -> Unit = {}

    fun setEventLoopAndWindow(eventLoop: ActiveEventLoop, window: Window) {
        this.eventLoop = eventLoop
        this.activeWindow = window
        registerScenarios()
    }

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        if (activeWindow != null) return

        this.eventLoop = eventLoop

        activeWindow = eventLoop.createWindow(
            WindowAttributes(
                title = "Kadre Simulation Demo",
                size = PhysicalSize(1200, 800),
                resizable = true,
                visible = true,
            )
        )

        registerScenarios()
    }

    override fun windowEvent(
        eventLoop: ActiveEventLoop,
        windowId: WindowId,
        event: WindowEvent
    ) {
        when (event) {
            is WindowEvent.CloseRequested -> {
                activeScenario?.stop()
                activeScenario = null
                isInScenario = false
                eventLoop.exit()
            }
            is WindowEvent.Destroyed -> {
                if (windowId == activeWindow?.id) activeWindow = null
            }
            else -> {
                activeScenario?.onWindowEvent(event)
            }
        }
    }

    override fun deviceEvent(eventLoop: ActiveEventLoop, deviceId: DeviceId, event: DeviceEvent) {
    }

    fun launchScenario(scenario: Scenario, onEvent: (ScenarioEvent) -> Unit) {
        activeScenario?.stop()
        results.clear()

        activeScenario = scenario
        this.scenarioEventCallback = onEvent
        isInScenario = true
        currentScenarioState = ScenarioState(isRunning = false)

        activeWindow?.let { window ->
            eventLoop?.let { loop ->
                scenario.start(window, loop) { event ->
                    handleScenarioEvent(event)
                    onEvent(event)
                }
            }
        }

        currentScenarioState = currentScenarioState.copy(isRunning = true)
    }

    fun returnToMenu() {
        activeScenario?.stop()
        activeScenario = null
        isInScenario = false
        currentScenarioState = ScenarioState()
    }

    private fun handleScenarioEvent(event: ScenarioEvent) {
        when (event) {
            is ScenarioEvent.StateChanged -> currentScenarioState = event.state
            is ScenarioEvent.Message -> println("[${event.severity}] ${event.text}")
            is ScenarioEvent.Result -> results.add(
                ScenarioResult(
                    success = true,
                    durationMs = 0,
                    eventsReceived = results.size + 1,
                    eventsExpected = results.size + 1,
                    platform = Platform.current()
                )
            )
            ScenarioEvent.Completed -> {}
            is ScenarioEvent.Error -> results.add(
                ScenarioResult(
                    success = false,
                    durationMs = 0,
                    eventsReceived = 0,
                    eventsExpected = 0,
                    errors = listOf(event.throwable.message ?: "Unknown error"),
                    platform = Platform.current()
                )
            )
        }
    }
}
