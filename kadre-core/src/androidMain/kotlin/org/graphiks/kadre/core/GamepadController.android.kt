package org.graphiks.kadre.core

actual class PlatformGamepadBackend(private val context: android.content.Context) {
    private val devices = mutableMapOf<Int, PlatformGamepad>()
    private val eventQueue = java.util.concurrent.ConcurrentLinkedQueue<PlatformEvent>()
    private val inputManager = context.getSystemService(android.content.Context.INPUT_SERVICE) as android.hardware.input.InputManager

    init {
        for (id in android.view.InputDevice.getDeviceIds()) {
            val device = android.view.InputDevice.getDevice(id)
            if (device != null && isGamepad(device)) {
                registerDevice(id)
            }
        }
        inputManager.registerInputDeviceListener(object : android.hardware.input.InputManager.InputDeviceListener {
            override fun onInputDeviceAdded(id: Int) { registerDevice(id) }
            override fun onInputDeviceRemoved(id: Int) {
                devices.remove(id)
                eventQueue.add(PlatformEvent(id, PlatformEventType.Disconnected))
            }
            override fun onInputDeviceChanged(id: Int) { }
        }, null)
    }

    private fun registerDevice(id: Int) {
        val device = android.view.InputDevice.getDevice(id) ?: return
        if (isGamepad(device)) {
            devices[id] = PlatformGamepad(device)
            eventQueue.add(PlatformEvent(id, PlatformEventType.Connected))
        }
    }

    private fun isGamepad(device: android.view.InputDevice): Boolean =
        device.sources and (android.view.InputDevice.SOURCE_GAMEPAD or android.view.InputDevice.SOURCE_JOYSTICK) != 0

    actual fun pollEvent(): PlatformEvent? = eventQueue.poll()

    actual fun connectedIds(): List<Int> = devices.keys.toList()

    actual fun getGamepad(id: Int): PlatformGamepad? = devices[id]

    fun queueButtonEvent(deviceId: Int, buttonCode: Int, pressed: Boolean) {
        val type = if (pressed) PlatformEventType.ButtonPressed(buttonCode) else PlatformEventType.ButtonReleased(buttonCode)
        eventQueue.add(PlatformEvent(deviceId, type))
    }

    fun queueAxisEvent(deviceId: Int, axisCode: Int, rawValue: Int) {
        eventQueue.add(PlatformEvent(deviceId, PlatformEventType.AxisChanged(axisCode, rawValue)))
    }
}

actual class PlatformGamepad(private val device: android.view.InputDevice) {
    actual val name: String get() = device.name ?: "Unknown"
    actual val powerInfo: PowerInfo get() = PowerInfo.Wired
    actual val vendorId: Int get() = device.vendorId
    actual val productId: Int get() = device.productId

    actual val nativeButtonCodes: List<Int> get() = listOf(
        android.view.KeyEvent.KEYCODE_BUTTON_A,
        android.view.KeyEvent.KEYCODE_BUTTON_B,
        android.view.KeyEvent.KEYCODE_BUTTON_X,
        android.view.KeyEvent.KEYCODE_BUTTON_Y,
        android.view.KeyEvent.KEYCODE_BUTTON_L1,
        android.view.KeyEvent.KEYCODE_BUTTON_R1,
        android.view.KeyEvent.KEYCODE_BUTTON_L2,
        android.view.KeyEvent.KEYCODE_BUTTON_R2,
        android.view.KeyEvent.KEYCODE_BUTTON_THUMBL,
        android.view.KeyEvent.KEYCODE_BUTTON_THUMBR,
        android.view.KeyEvent.KEYCODE_BUTTON_START,
        android.view.KeyEvent.KEYCODE_BUTTON_SELECT,
        android.view.KeyEvent.KEYCODE_BUTTON_MODE,
        android.view.KeyEvent.KEYCODE_DPAD_UP,
        android.view.KeyEvent.KEYCODE_DPAD_DOWN,
        android.view.KeyEvent.KEYCODE_DPAD_LEFT,
        android.view.KeyEvent.KEYCODE_DPAD_RIGHT,
    )

    actual val nativeAxisCodes: List<Int> get() = listOf(
        android.view.MotionEvent.AXIS_X,
        android.view.MotionEvent.AXIS_Y,
        android.view.MotionEvent.AXIS_Z,
        android.view.MotionEvent.AXIS_RX,
        android.view.MotionEvent.AXIS_RY,
        android.view.MotionEvent.AXIS_RZ,
        android.view.MotionEvent.AXIS_HAT_X,
        android.view.MotionEvent.AXIS_HAT_Y,
        android.view.MotionEvent.AXIS_LTRIGGER,
        android.view.MotionEvent.AXIS_RTRIGGER,
    )

    actual fun buttonValue(code: Int): Float = 0f

    actual fun axisValue(code: Int): Float = 0f
}

actual class PlatformEvent(
    actual val id: Int,
    actual val type: PlatformEventType,
    actual val time: Long = System.currentTimeMillis(),
)

internal class AndroidGamepadMapping : GamepadMapping() {
    override fun mapButton(code: Int): Button = when (code) {
        android.view.KeyEvent.KEYCODE_BUTTON_A -> Button.South
        android.view.KeyEvent.KEYCODE_BUTTON_B -> Button.East
        android.view.KeyEvent.KEYCODE_BUTTON_X -> Button.West
        android.view.KeyEvent.KEYCODE_BUTTON_Y -> Button.North
        android.view.KeyEvent.KEYCODE_BUTTON_L1 -> Button.LeftTrigger
        android.view.KeyEvent.KEYCODE_BUTTON_R1 -> Button.RightTrigger
        android.view.KeyEvent.KEYCODE_BUTTON_L2 -> Button.LeftTrigger2
        android.view.KeyEvent.KEYCODE_BUTTON_R2 -> Button.RightTrigger2
        android.view.KeyEvent.KEYCODE_BUTTON_THUMBL -> Button.LeftThumb
        android.view.KeyEvent.KEYCODE_BUTTON_THUMBR -> Button.RightThumb
        android.view.KeyEvent.KEYCODE_BUTTON_START -> Button.Start
        android.view.KeyEvent.KEYCODE_BUTTON_SELECT -> Button.Select
        android.view.KeyEvent.KEYCODE_BUTTON_MODE -> Button.Mode
        android.view.KeyEvent.KEYCODE_DPAD_UP -> Button.DPadUp
        android.view.KeyEvent.KEYCODE_DPAD_DOWN -> Button.DPadDown
        android.view.KeyEvent.KEYCODE_DPAD_LEFT -> Button.DPadLeft
        android.view.KeyEvent.KEYCODE_DPAD_RIGHT -> Button.DPadRight
        else -> Button.South
    }

    override fun mapAxis(code: Int): Axis = when (code) {
        android.view.MotionEvent.AXIS_X -> Axis.LeftStickX
        android.view.MotionEvent.AXIS_Y -> Axis.LeftStickY
        android.view.MotionEvent.AXIS_Z -> Axis.LeftZ
        android.view.MotionEvent.AXIS_RX -> Axis.RightStickX
        android.view.MotionEvent.AXIS_RY -> Axis.RightStickY
        android.view.MotionEvent.AXIS_RZ -> Axis.RightZ
        android.view.MotionEvent.AXIS_HAT_X -> Axis.DPadX
        android.view.MotionEvent.AXIS_HAT_Y -> Axis.DPadY
        android.view.MotionEvent.AXIS_LTRIGGER -> Axis.LeftStickY
        android.view.MotionEvent.AXIS_RTRIGGER -> Axis.RightStickY
        else -> Axis.LeftStickX
    }
}

internal actual fun createDefaultGamepadMapping(): GamepadMapping = AndroidGamepadMapping()
