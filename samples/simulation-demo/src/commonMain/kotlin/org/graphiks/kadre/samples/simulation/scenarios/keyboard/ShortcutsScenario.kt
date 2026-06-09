package org.graphiks.kadre.samples.simulation.scenarios.keyboard

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class ShortcutsScenario : KeyboardScenario(
    id = "keyboard-shortcuts",
    title = "Keyboard shortcuts",
    description = "Tests keyboard shortcuts (Ctrl+C, Ctrl+V, etc.). Events are filtered by modifiers.",
    priority = 90
) {
    private val knownShortcuts = mapOf(
        "ctrl+c" to "Copy",
        "ctrl+v" to "Paste",
        "ctrl+x" to "Cut",
        "ctrl+z" to "Undo",
        "ctrl+s" to "Save",
        "ctrl+a" to "Select all",
        "shift+ctrl+c" to "Open developer tools"
    )

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        onEvent(ScenarioEvent.Message("Shortcuts enabled - try Ctrl+C, Ctrl+V, Ctrl+S...", MessageSeverity.INFO))
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
                    message = "✅ Shortcut detected: $shortcutKey → $action",
                    data = mapOf("shortcut" to shortcutKey, "action" to action)
                )))
            } else {
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "Unrecognized shortcut: $shortcutKey",
                    data = mapOf("shortcut" to shortcutKey)
                )))
            }
        } else {
            onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                isRunning = true,
                message = "Use a modifier (Ctrl/Shift) to test shortcuts"
            )))
        }
    }
}
