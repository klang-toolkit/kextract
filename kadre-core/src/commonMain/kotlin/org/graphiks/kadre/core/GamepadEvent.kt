/**
 * Gamepad input events.
 *
 * Dispatched by [GamepadController.pollEvents] and consumed by the application.
 * Mirrors the event model from gilrs (Game Input Library for Rust).
 */
package org.graphiks.kadre.core

/**
 * Base type for all gamepad-related events.
 *
 * @property id   Identifier of the gamepad that generated this event.
 * @property time Timestamp in milliseconds (0L if unavailable).
 */
sealed interface GamepadEvent {
    val id: GamepadId
    val time: Long

    /**
     * A button was pressed.
     *
     * @property button The button that was pressed.
     */
    data class ButtonPressed(
        override val id: GamepadId,
        val button: Button,
        override val time: Long = 0L,
    ) : GamepadEvent

    /**
     * A button was released.
     *
     * @property button The button that was released.
     */
    data class ButtonReleased(
        override val id: GamepadId,
        val button: Button,
        override val time: Long = 0L,
    ) : GamepadEvent

    /**
     * An analog axis changed value.
     *
     * @property axis  The axis that changed.
     * @property value The new value in [-1.0, 1.0].
     */
    data class AxisChanged(
        override val id: GamepadId,
        val axis: Axis,
        val value: Float,
        override val time: Long = 0L,
    ) : GamepadEvent

    /**
     * A new gamepad was connected.
     *
     * @property name Human-readable name of the gamepad.
     */
    data class Connected(
        override val id: GamepadId,
        val name: String,
        override val time: Long = 0L,
    ) : GamepadEvent

    /**
     * A gamepad was disconnected.
     */
    data class Disconnected(
        override val id: GamepadId,
        override val time: Long = 0L,
    ) : GamepadEvent
}
