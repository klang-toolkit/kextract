package org.graphiks.kadre.core

import platform.Foundation.NSDate
import platform.GameController.GCController
import platform.GameController.GCExtendedGamepad

actual class PlatformGamepadBackend {
    private val controllers = mutableMapOf<Int, PlatformGamepad>()
    private val events = ArrayDeque<PlatformEvent>()
    private var nextId = 1

    init {
        val raw = GCController.controllers()
        for (ctrl in raw.orEmpty()) {
            if (ctrl is GCController) registerController(ctrl)
        }
    }

    private fun registerController(ctrl: GCController) {
        val id = nextId++
        controllers[id] = PlatformGamepad(ctrl)
        events.addLast(PlatformEvent(id, PlatformEventType.Connected))
    }

    actual fun pollEvent(): PlatformEvent? = events.removeFirstOrNull()

    actual fun connectedIds(): List<Int> = controllers.keys.toList()

    actual fun getGamepad(id: Int): PlatformGamepad? = controllers[id]
}

actual class PlatformGamepad(private val controller: GCController) {
    actual val name: String get() = controller.vendorName ?: "Unknown"
    actual val powerInfo: PowerInfo get() = PowerInfo.Wired
    actual val vendorId: Int get() = 0
    actual val productId: Int get() = 0

    actual val nativeButtonCodes: List<Int> = (0..14).toList()
    actual val nativeAxisCodes: List<Int> = (0..5).toList()

    actual fun buttonValue(code: Int): Float {
        val gp = controller.extendedGamepad ?: return 0f
        return when (code) {
            0 -> if (gp.buttonA.isPressed()) 1f else 0f
            1 -> if (gp.buttonB.isPressed()) 1f else 0f
            2 -> if (gp.buttonX.isPressed()) 1f else 0f
            3 -> if (gp.buttonY.isPressed()) 1f else 0f
            4 -> if (gp.leftShoulder.isPressed()) 1f else 0f
            5 -> if (gp.rightShoulder.isPressed()) 1f else 0f
            6 -> gp.leftTrigger.value
            7 -> gp.rightTrigger.value
            8 -> if (gp.dpad.up.isPressed()) 1f else 0f
            9 -> if (gp.dpad.down.isPressed()) 1f else 0f
            10 -> if (gp.dpad.left.isPressed()) 1f else 0f
            11 -> if (gp.dpad.right.isPressed()) 1f else 0f
            12 -> if (gp.leftThumbstickButton?.isPressed() == true) 1f else 0f
            13 -> if (gp.rightThumbstickButton?.isPressed() == true) 1f else 0f
            else -> 0f
        }
    }

    actual fun axisValue(code: Int): Float {
        val gp = controller.extendedGamepad ?: return 0f
        return when (code) {
            0 -> gp.leftThumbstick.xAxis.value
            1 -> gp.leftThumbstick.yAxis.value
            2 -> gp.rightThumbstick.xAxis.value
            3 -> gp.rightThumbstick.yAxis.value
            4 -> gp.leftTrigger.value
            5 -> gp.rightTrigger.value
            else -> 0f
        }
    }
}

actual class PlatformEvent(
    actual val id: Int,
    actual val type: PlatformEventType,
    actual val time: Long = (NSDate().timeIntervalSinceReferenceDate * 1000).toLong() + 978307200000L,
)

internal actual fun createDefaultGamepadMapping(): GamepadMapping = IosGamepadMapping()

internal class IosGamepadMapping : GamepadMapping() {
    override fun mapButton(code: Int): Button = when (code) {
        0 -> Button.South
        1 -> Button.East
        2 -> Button.West
        3 -> Button.North
        4 -> Button.LeftTrigger
        5 -> Button.RightTrigger
        6 -> Button.LeftTrigger2
        7 -> Button.RightTrigger2
        8 -> Button.DPadUp
        9 -> Button.DPadDown
        10 -> Button.DPadLeft
        11 -> Button.DPadRight
        12 -> Button.LeftThumb
        13 -> Button.RightThumb
        else -> Button.South
    }

    override fun mapAxis(code: Int): Axis = when (code) {
        0 -> Axis.LeftStickX
        1 -> Axis.LeftStickY
        2 -> Axis.RightStickX
        3 -> Axis.RightStickY
        4 -> Axis.LeftZ
        5 -> Axis.RightZ
        else -> Axis.LeftStickX
    }
}
