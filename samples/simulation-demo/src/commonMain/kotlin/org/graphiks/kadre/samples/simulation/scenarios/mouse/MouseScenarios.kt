package org.graphiks.kadre.samples.simulation.scenarios.mouse

import org.graphiks.kadre.samples.simulation.*

fun register() {
    val scenarios = listOf(
        ClicksScenario(),
        DragScenario(),
        ScrollScenario(),
        CursorScenario()
    )

    scenarios.forEach { ScenarioRegistry.register(it) }
}
