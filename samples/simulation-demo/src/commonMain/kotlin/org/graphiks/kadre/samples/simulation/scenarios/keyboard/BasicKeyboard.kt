package org.graphiks.kadre.samples.simulation.scenarios.keyboard

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class BasicKeyboard : KeyboardScenario(
    id = "keyboard-basic",
    title = "Saisie basique",
    description = "Tests simple character input: letters, digits and symbols. Observe key press/release events.",
    priority = 100
) {
    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        onEvent(ScenarioEvent.Message("Basic input enabled - press keys to see events", MessageSeverity.INFO))
    }

    override fun onKeyEvent(event: WindowEvent.KeyInput) {
        val ke = event.event
        val keyDesc = when (val lk = ke.logicalKey) {
            is LogicalKey.Character -> "'${lk.text}'"
            is LogicalKey.Named -> lk.key.name
            is LogicalKey.Dead -> "dead:${lk.accent}"
            is LogicalKey.Unidentified -> "?"
        }
        val action = if (ke.isPressed) "🔽" else "🔼"
        val pc = ke.physicalKey
        val physDesc = when (pc) {
            is PhysicalKey.Code -> pc.code.name
            is PhysicalKey.Native -> "native#${pc.code}"
            PhysicalKey.Unidentified -> "?"
        }

        onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
            isRunning = true,
            message = "$action Key: $keyDesc (phys: $physDesc)",
            data = mapOf(
                "pressed" to ke.isPressed,
                "logical" to keyDesc,
                "physical" to physDesc,
                "total_received" to keyEventsReceived
            )
        )))
    }
}
