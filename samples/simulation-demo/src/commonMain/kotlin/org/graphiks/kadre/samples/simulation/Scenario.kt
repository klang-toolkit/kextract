package org.graphiks.kadre.samples.simulation

import org.graphiks.kadre.core.*

interface Scenario {
    val id: String
    val title: String
    val description: String
    val category: String
    val requiredCapabilities: Set<Capability>
    val priority: Int

    fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit)
    fun stop()
    fun onWindowEvent(event: WindowEvent) {}
    fun runHeadless(args: List<String>): ScenarioResult
}

sealed class ScenarioEvent {
    data class StateChanged(val state: ScenarioState) : ScenarioEvent()
    data class Message(val text: String, val severity: MessageSeverity) : ScenarioEvent()
    data class Result(val data: Any) : ScenarioEvent()
    data object Completed : ScenarioEvent()
    data class Error(val throwable: Throwable) : ScenarioEvent()
}

enum class MessageSeverity { INFO, WARNING, ERROR }

data class ScenarioState(
    val isRunning: Boolean = false,
    val progress: Float = 0f,
    val message: String? = null,
    val data: Map<String, Any> = emptyMap()
)

data class ScenarioResult(
    val success: Boolean,
    val durationMs: Long,
    val eventsReceived: Int,
    val eventsExpected: Int,
    val errors: List<String> = emptyList(),
    val warnings: List<String> = emptyList(),
    val platform: Platform
)

enum class Capability {
    KEYBOARD, MOUSE, TOUCH, MULTI_TOUCH, GAMEPAD, IME,
    CURSOR_GRAB, CURSOR_POSITION, CURSOR_HITTEST, MULTI_WINDOW
}

enum class SupportLevel { FULL, PARTIAL, STUB, NOT_AVAILABLE }

data class ScenarioMetadata(
    val scenario: Scenario,
    val platformSupport: Map<Platform, SupportLevel> = emptyMap(),
    val availableOn: Set<Platform> = Platform.ALL,
    val limitations: Map<Platform, String> = emptyMap(),
    val icon: String? = null
)
