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
            title = "Répétition de touches",
            description = "Teste la répétition automatique des touches maintenues enfoncées. Observez les événements repeat.",
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
                val repeatInfo = if (ke.repeat) "🔄 (répétition)" else "🆕 (première pression)"
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "$repeatInfo Touche: $keyDesc",
                    data = mapOf("repeat" to ke.repeat, "key" to keyDesc)
                )))
            }
        }
    )

    scenarios.forEach { ScenarioRegistry.register(it) }
}
