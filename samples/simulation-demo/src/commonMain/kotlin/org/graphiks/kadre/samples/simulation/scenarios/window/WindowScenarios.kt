package org.graphiks.kadre.samples.simulation.scenarios.window

import org.graphiks.kadre.samples.simulation.*

fun register() {
    val scenarios = listOf(
        ResizeScenario(),
        FullscreenScenario(),
        MultiWindowScenario(),
        FocusScenario()
    )
    scenarios.forEach { ScenarioRegistry.register(it) }
}
