/**
 * Core gamepad types for Kadre.
 *
 * Mirrors the abstractions from gilrs (Game Input Library for Rust)
 * without the SDL mapping layer.
 */
package org.graphiks.kadre.core

/**
 * Opaque identifier for a connected gamepad.
 *
 * @property value Platform-specific gamepad index.
 */
data class GamepadId(val value: Int)

/**
 * Named gamepad buttons using the common controller layout
 * (South/East/North/West = Nintendo ABXY layout).
 *
 * Map directly from platform key codes per backend.
 */
enum class Button {
    South, East, North, West,
    C, Z,
    LeftTrigger, LeftTrigger2,
    RightTrigger, RightTrigger2,
    Select, Start, Mode,
    LeftThumb, RightThumb,
    DPadUp, DPadDown, DPadLeft, DPadRight;

    companion object {
        fun fromOrdinal(v: Int): Button = entries.getOrElse(v) { South }
    }
}

/**
 * Named analog axes.
 *
 * Values range from -1.0 to 1.0 per the unified controller model.
 */
enum class Axis {
    LeftStickX, LeftStickY, LeftZ,
    RightStickX, RightStickY, RightZ,
    DPadX, DPadY;

    companion object {
        fun fromOrdinal(v: Int): Axis = entries.getOrElse(v) { LeftStickX }
    }
}

/**
 * Snapshot of a gamepad's current button and axis states.
 *
 * @property buttons Button states as [0.0..1.0] floats (0 = released, 1 = fully pressed).
 * @property axes   Axis states as [-1.0..1.0] floats.
 */
data class GamepadState(
    val buttons: Map<Button, Float> = emptyMap(),
    val axes: Map<Axis, Float> = emptyMap(),
)

/**
 * Power source and battery status for a gamepad.
 */
sealed interface PowerInfo {
    data object Unknown : PowerInfo
    data object Wired : PowerInfo
    data class Discharging(val battery: Int) : PowerInfo
    data class Charging(val battery: Int) : PowerInfo
    data object Charged : PowerInfo
}
