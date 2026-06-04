package org.graphiks.kadre.core

expect class PlatformGamepad {
    val name: String
    val powerInfo: PowerInfo
    val vendorId: Int
    val productId: Int
    fun buttonValue(code: Int): Float
    fun axisValue(code: Int): Float
    val nativeButtonCodes: List<Int>
    val nativeAxisCodes: List<Int>
}

expect class PlatformEvent {
    val id: Int
    val type: PlatformEventType
    val time: Long
}

sealed interface PlatformEventType {
    data class ButtonPressed(val code: Int) : PlatformEventType
    data class ButtonReleased(val code: Int) : PlatformEventType
    data class AxisChanged(val code: Int, val rawValue: Int) : PlatformEventType
    data object Connected : PlatformEventType
    data object Disconnected : PlatformEventType
}

expect class PlatformGamepadBackend {
    fun pollEvent(): PlatformEvent?
    fun connectedIds(): List<Int>
    fun getGamepad(id: Int): PlatformGamepad?
}

internal expect fun createDefaultGamepadMapping(): GamepadMapping

internal open class GamepadMapping {
    open fun mapButton(code: Int): Button = Button.South
    open fun mapAxis(code: Int): Axis = Axis.LeftStickX
}

class Gamepad internal constructor(
    val id: GamepadId,
    internal var raw: PlatformGamepad?,
    private val mapping: GamepadMapping = GamepadMapping(),
) {
    val name: String get() = raw?.name ?: ""
    val powerInfo: PowerInfo get() = raw?.powerInfo ?: PowerInfo.Unknown
    val vendorId: Int get() = raw?.vendorId ?: 0
    val productId: Int get() = raw?.productId ?: 0
    val isConnected: Boolean get() = raw != null

    val state: GamepadState get() {
        val r = raw ?: return GamepadState()
        val buttons = r.nativeButtonCodes.associate { code ->
            mapping.mapButton(code) to r.buttonValue(code)
        }
        val axes = r.nativeAxisCodes.associate { code ->
            mapping.mapAxis(code) to r.axisValue(code)
        }
        return GamepadState(buttons, axes)
    }
}

class GamepadController(private val backend: PlatformGamepadBackend) {
    private val gamepads = mutableMapOf<GamepadId, Gamepad>()
    private val mappings = mutableMapOf<GamepadId, GamepadMapping>()

    fun pollEvents(): List<GamepadEvent> {
        val events = mutableListOf<GamepadEvent>()

        while (true) {
            val rawEvent = backend.pollEvent() ?: break
            val id = GamepadId(rawEvent.id)

            val gamepadEvent = when (val type = rawEvent.type) {
                is PlatformEventType.ButtonPressed -> {
                    val button = mappingFor(id).mapButton(type.code)
                    GamepadEvent.ButtonPressed(id, button, rawEvent.time)
                }
                is PlatformEventType.ButtonReleased -> {
                    val button = mappingFor(id).mapButton(type.code)
                    GamepadEvent.ButtonReleased(id, button, rawEvent.time)
                }
                is PlatformEventType.AxisChanged -> {
                    val axis = mappingFor(id).mapAxis(type.code)
                    val normalized = (type.rawValue.toFloat() / 32767f).coerceIn(-1f, 1f)
                    GamepadEvent.AxisChanged(id, axis, normalized, rawEvent.time)
                }
                is PlatformEventType.Connected -> {
                    val raw = backend.getGamepad(id.value)
                    gamepads[id] = Gamepad(id, raw)
                    GamepadEvent.Connected(id, raw?.name ?: "Unknown", rawEvent.time)
                }
                is PlatformEventType.Disconnected -> {
                    gamepads.remove(id)
                    GamepadEvent.Disconnected(id, rawEvent.time)
                }
            }
            events.add(gamepadEvent)
        }

        val connectedIds = backend.connectedIds()
        val knownIds = gamepads.keys.map { it.value }.toSet()

        for (cid in connectedIds) {
            if (cid !in knownIds) {
                val gid = GamepadId(cid)
                val raw = backend.getGamepad(cid)
                gamepads[gid] = Gamepad(gid, raw)
                events.add(GamepadEvent.Connected(gid, raw?.name ?: "Unknown"))
            }
        }

        val currentConnected = connectedIds.toSet()
        val disconnected = gamepads.keys.filter { it.value !in currentConnected }
        for (gid in disconnected) {
            gamepads.remove(gid)
            events.add(GamepadEvent.Disconnected(gid))
        }

        return events
    }

    fun gamepad(id: GamepadId): Gamepad? = gamepads[id]

    fun allGamepads(): Collection<Gamepad> = gamepads.values.toList()

    val isSupported: Boolean get() = true

    private fun mappingFor(id: GamepadId): GamepadMapping =
        mappings.getOrPut(id) { createDefaultGamepadMapping() }
}
