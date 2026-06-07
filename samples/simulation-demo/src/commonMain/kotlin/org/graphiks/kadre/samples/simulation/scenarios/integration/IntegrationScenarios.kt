package org.graphiks.kadre.samples.simulation.scenarios.integration

import org.graphiks.kadre.samples.simulation.*

fun register() {
    val scenarios = listOf(
        GameScenario(),
        TextEditorScenario()
    )
    scenarios.forEach { ScenarioRegistry.register(it) }
}
