package org.graphiks.kadre.samples.simulation.scenarios.keyboard

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class ImeScenario : KeyboardScenario(
    id = "keyboard-ime",
    title = "Saisie internationale (IME)",
    description = "Tests input with Input Method Editor for accented characters, CJK and other writing systems.",
    priority = 50
) {
    override val requiredCapabilities: Set<Capability> = setOf(Capability.KEYBOARD, Capability.IME)
    private val inputBuffer = StringBuilder()

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        super.start(window, eventLoop, onEvent)
        inputBuffer.clear()
        onEvent(ScenarioEvent.Message("IME enabled - type text with accented characters (é, ü, ñ, ç...)", MessageSeverity.INFO))
    }

    override fun onKeyEvent(event: WindowEvent.KeyInput) {
        val ke = event.event
        if (!ke.isPressed) return

        val text = ke.text ?: ""
        if (text.isNotBlank()) {
            inputBuffer.append(text)
        }

        when (ke.physicalKey) {
            PhysicalKey.Code(KeyCode.Enter) -> {
                onEvent?.invoke(ScenarioEvent.Result(inputBuffer.toString()))
                inputBuffer.clear()
            }
            PhysicalKey.Code(KeyCode.Backspace) -> {
                if (inputBuffer.isNotEmpty()) {
                    inputBuffer.deleteCharAt(inputBuffer.length - 1)
                }
            }
            else -> {}
        }

        onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
            isRunning = true,
            message = "Buffer: \"$inputBuffer\"",
            data = mapOf("buffer" to inputBuffer.toString())
        )))
    }

    override fun onWindowEvent(event: WindowEvent) {
        super.onWindowEvent(event)
        if (event is WindowEvent.Ime) {
            onImeEvent(event)
        }
    }

    private fun onImeEvent(event: WindowEvent.Ime) {
        when (val ime = event.ime) {
            is WindowEvent.Ime.ImeEvent.Preedit -> {
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "IME: preedit='${ime.text}' cursor=${ime.cursorRange?.let { "${it.first}-${it.second}" } ?: "?"}",
                    data = mapOf("preedit" to ime.text, "cursorRange" to (ime.cursorRange?.toString() ?: "none"))
                )))
            }
            is WindowEvent.Ime.ImeEvent.Commit -> {
                inputBuffer.append(ime.text)
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "IME commit: '${ime.text}' | Buffer: \"$inputBuffer\"",
                    data = mapOf("commit" to ime.text, "buffer" to inputBuffer.toString())
                )))
            }
            else -> {}
        }
    }
}
