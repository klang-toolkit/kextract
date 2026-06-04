package org.graphiks.kadre.core

import java.util.concurrent.ConcurrentLinkedQueue

actual class PlatformGamepadBackend {
    private val eventQueue = ConcurrentLinkedQueue<PlatformEvent>()

    actual fun pollEvent(): PlatformEvent? = eventQueue.poll()
    actual fun connectedIds(): List<Int> = emptyList()
    actual fun getGamepad(id: Int): PlatformGamepad? = null
}

actual class PlatformGamepad {
    actual val name: String get() = "Unknown"
    actual val powerInfo: PowerInfo get() = PowerInfo.Unknown
    actual val vendorId: Int get() = 0
    actual val productId: Int get() = 0
    actual val nativeButtonCodes: List<Int> get() = emptyList()
    actual val nativeAxisCodes: List<Int> get() = emptyList()
    actual fun buttonValue(code: Int): Float = 0f
    actual fun axisValue(code: Int): Float = 0f
}

actual class PlatformEvent(
    actual val id: Int,
    actual val type: PlatformEventType,
    actual val time: Long = System.currentTimeMillis(),
)

internal class JvmGamepadMapping : GamepadMapping() {
    override fun mapButton(code: Int): Button = when (code) {
        304 -> Button.South
        305 -> Button.East
        306 -> Button.North
        307 -> Button.West
        308 -> Button.LeftTrigger
        309 -> Button.RightTrigger
        310 -> Button.LeftTrigger2
        311 -> Button.RightTrigger2
        312 -> Button.Select
        313 -> Button.Start
        314 -> Button.Mode
        315 -> Button.LeftThumb
        316 -> Button.RightThumb
        544 -> Button.DPadUp
        545 -> Button.DPadDown
        546 -> Button.DPadLeft
        547 -> Button.DPadRight
        else -> Button.South
    }

    override fun mapAxis(code: Int): Axis = when (code) {
        0 -> Axis.LeftStickX
        1 -> Axis.LeftStickY
        2 -> Axis.RightStickX
        3 -> Axis.RightStickY
        4 -> Axis.DPadX
        5 -> Axis.DPadY
        6 -> Axis.LeftZ
        7 -> Axis.RightZ
        else -> Axis.LeftStickX
    }
}

internal actual fun createDefaultGamepadMapping(): GamepadMapping = JvmGamepadMapping()
