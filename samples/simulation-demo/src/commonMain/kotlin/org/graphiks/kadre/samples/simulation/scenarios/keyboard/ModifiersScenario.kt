package org.graphiks.kadre.samples.simulation.scenarios.keyboard

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class ModifiersScenario : KeyboardScenario(
    id = "keyboard-modifiers",
    title = "État des modifieurs",
    description = "Visualise l'état des touches modifieurs (Shift, Ctrl, Alt, Meta/Cmd) en temps réel.",
    priority = 80
) {
    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        onEvent(ScenarioEvent.Message("Appuyez sur Shift, Ctrl, Alt ou Cmd pour voir leur état", MessageSeverity.INFO))
    }

    override fun onKeyEvent(event: WindowEvent.KeyInput) {
        val m = event.event.modifiers
        val modStates = listOf(
            "Shift: ${if (m.shift) "🔵" else "⚪"}",
            "Ctrl: ${if (m.ctrl) "🔵" else "⚪"}",
            "Alt: ${if (m.alt) "🔵" else "⚪"}",
            "Meta: ${if (m.meta) "🔵" else "⚪"}",
            "Caps: ${if (m.capsLock) "🔵" else "⚪"}",
        ).joinToString(" | ")

        onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
            isRunning = true,
            message = modStates,
            data = mapOf(
                "shift" to m.shift,
                "ctrl" to m.ctrl,
                "alt" to m.alt,
                "meta" to m.meta,
                "capsLock" to m.capsLock
            )
        )))
    }
}
