package org.graphiks.kadre.samples.simulation.scenarios.keyboard

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class ShortcutsScenario : KeyboardScenario(
    id = "keyboard-shortcuts",
    title = "Raccourcis clavier",
    description = "Teste les raccourcis clavier (Ctrl+C, Ctrl+V, etc.). Les événements sont filtrés par modifieurs.",
    priority = 90
) {
    private val knownShortcuts = mapOf(
        "ctrl+c" to "Copier",
        "ctrl+v" to "Coller",
        "ctrl+x" to "Couper",
        "ctrl+z" to "Annuler",
        "ctrl+s" to "Sauvegarder",
        "ctrl+a" to "Tout sélectionner",
        "shift+ctrl+c" to "Ouvrir les outils développeur"
    )

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        onEvent(ScenarioEvent.Message("Raccourcis activés - essayez Ctrl+C, Ctrl+V, Ctrl+S...", MessageSeverity.INFO))
    }

    override fun onKeyEvent(event: WindowEvent.KeyInput) {
        val ke = event.event
        if (!ke.isPressed) return

        val isCtrl = ke.modifiers.ctrl
        val isShift = ke.modifiers.shift

        if (isCtrl) {
            val keyName = when (val lk = ke.logicalKey) {
                is LogicalKey.Character -> lk.text.lowercase()
                is LogicalKey.Named -> lk.key.name.lowercase()
                else -> "?"
            }
            val shortcutKey = buildString {
                if (isShift) append("shift+")
                append("ctrl+")
                append(keyName)
            }

            val action = knownShortcuts[shortcutKey]
            if (action != null) {
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "✅ Raccourci détecté : $shortcutKey → $action",
                    data = mapOf("shortcut" to shortcutKey, "action" to action)
                )))
            } else {
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "Raccourci non reconnu : $shortcutKey",
                    data = mapOf("shortcut" to shortcutKey)
                )))
            }
        } else {
            onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                isRunning = true,
                message = "Utilisez un modifieur (Ctrl/Shift) pour tester les raccourcis"
            )))
        }
    }
}
