package org.graphiks.kadre.samples.simulation.scenarios.keyboard

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

fun register() {
    val scenarios = listOf(
        BasicKeyboard(),
        ShortcutsScenario(),
        ImeScenario(),
        ModifiersScenario(),
        object : KeyboardScenario(
            id = "keyboard-repeat",
            title = "Key repeat",
            description = "Tests automatic key repeat for held-down keys. Observe repeat events.",
            priority = 70
        ) {
            override fun onKeyEvent(event: WindowEvent.KeyInput) {
                val ke = event.event
                if (!ke.isPressed) return
                val keyDesc = when (val lk = ke.logicalKey) {
                    is LogicalKey.Character -> "'${lk.text}'"
                    is LogicalKey.Named -> lk.key.name
                    is LogicalKey.Dead -> "dead:${lk.accent}"
                    is LogicalKey.Unidentified -> "?"
                }
                val repeatInfo = if (ke.repeat) "🔄 (repeat)" else "🆕 (first press)"
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "$repeatInfo Key: $keyDesc",
                    data = mapOf("repeat" to ke.repeat, "key" to keyDesc)
                )))
            }
        }
    )

    scenarios.forEach { ScenarioRegistry.register(it) }
}
