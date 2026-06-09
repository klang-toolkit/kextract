package org.graphiks.kadre.samples.simulation.scenarios.integration

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class TextEditorScenario : Scenario {
    override val id = "text-editor"
    override val title = "Mini text editor"
    override val description = "Mini text editor combining keyboard input, IME and shortcut management. Supports basic rich text."
    override val category = "Integration"
    override val requiredCapabilities: Set<Capability> = setOf(Capability.KEYBOARD)
    override val priority: Int = 90

    private var window: Window? = null
    private var eventLoop: ActiveEventLoop? = null
    private var onEvent: ((ScenarioEvent) -> Unit)? = null
    private var isRunning = false

    private val textContent = StringBuilder()
    private var cursorPosition = 0
    private var wordCount = 0
    private var keystrokeCount = 0

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        this.window = window
        this.eventLoop = eventLoop
        this.onEvent = onEvent
        this.isRunning = true
        this.textContent.clear()
        this.cursorPosition = 0
        this.wordCount = 0
        this.keystrokeCount = 0

        onEvent(ScenarioEvent.StateChanged(ScenarioState(
            isRunning = true,
            message = "📝 Type text. Ctrl+S to save, Ctrl+Z to undo.",
            data = mapOf("text" to "", "words" to 0, "keys" to 0)
        )))
    }

    override fun stop() {
        isRunning = false
    }

    override fun onWindowEvent(event: WindowEvent) {
        when (event) {
            is WindowEvent.KeyInput -> onKeyEvent(event)
            is WindowEvent.Ime -> onImeEvent(event)
            else -> {}
        }
    }

    private fun onKeyEvent(event: WindowEvent.KeyInput) {
        if (!isRunning) return
        val ke = event.event

        if (!ke.isPressed) return

        keystrokeCount++

        val text = ke.text ?: ""
        val isCtrl = ke.modifiers.ctrl

        if (isCtrl) {
            when (ke.physicalKey) {
                PhysicalKey.Code(KeyCode.KeyS) -> {
                    onEvent?.invoke(ScenarioEvent.Message("💾 Text saved (${textContent.length} characters)", MessageSeverity.INFO))
                    onEvent?.invoke(ScenarioEvent.Result(textContent.toString()))
                }
                PhysicalKey.Code(KeyCode.KeyZ) -> {
                    onEvent?.invoke(ScenarioEvent.Message("↩️ Undo (simulated)", MessageSeverity.INFO))
                }
                else -> {}
            }
            return
        }

        when (ke.physicalKey) {
            PhysicalKey.Code(KeyCode.Backspace) -> {
                if (cursorPosition > 0) {
                    textContent.deleteCharAt(cursorPosition - 1)
                    cursorPosition--
                }
            }
            PhysicalKey.Code(KeyCode.Enter) -> {
                textContent.insert(cursorPosition, '\n')
                cursorPosition++
            }
            PhysicalKey.Code(KeyCode.ArrowLeft) -> cursorPosition = (cursorPosition - 1).coerceAtLeast(0)
            PhysicalKey.Code(KeyCode.ArrowRight) -> cursorPosition = cursorPosition.coerceAtMost(textContent.length)
            else -> {
                if (text.isNotBlank()) {
                    textContent.insert(cursorPosition, text)
                    cursorPosition += text.length
                }
            }
        }

        wordCount = textContent.split(Regex("\\s+")).filter { it.isNotBlank() }.size

        val displayText = if (textContent.length > 100) {
            textContent.substring(0, 100) + "..."
        } else {
            textContent.toString()
        }

        onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
            isRunning = true,
            message = "📝 Words: $wordCount | Characters: ${textContent.length} | Keys: $keystrokeCount",
            data = mapOf(
                "text_preview" to displayText,
                "words" to wordCount,
                "characters" to textContent.length,
                "keystrokes" to keystrokeCount
            )
        )))
    }

    private fun onImeEvent(event: WindowEvent.Ime) {
        when (val ime = event.ime) {
            is WindowEvent.Ime.ImeEvent.Preedit -> {
                onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                    isRunning = true,
                    message = "🈳 IME: '${ime.text}'",
                    data = mapOf("ime_preedit" to ime.text)
                )))
            }
            else -> {}
        }
    }

    override fun collectResult(durationMs: Long): ScenarioResult {
        return ScenarioResult(
            success = wordCount > 0,
            durationMs = durationMs,
            eventsReceived = keystrokeCount,
            eventsExpected = 20,
            warnings = if (wordCount == 0) listOf("No text was entered") else emptyList(),
            platform = Platform.current()
        )
    }
}
