package org.graphiks.kadre.samples.simulation.scenarios.touch

import org.graphiks.kadre.samples.simulation.*

fun register() {
    val scenarios = listOf(
        SingleTouchScenario(),
        MultiTouchScenario(),
        GesturesScenario()
    )
    scenarios.forEach { ScenarioRegistry.register(it) }
}
