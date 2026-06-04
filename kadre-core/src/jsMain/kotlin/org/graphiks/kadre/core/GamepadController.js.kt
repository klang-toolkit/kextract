package org.graphiks.kadre.core

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
    actual val time: Long,
)

actual class PlatformGamepadBackend {
    actual fun pollEvent(): PlatformEvent? = null
    actual fun connectedIds(): List<Int> = emptyList()
    actual fun getGamepad(id: Int): PlatformGamepad? = null
}

internal actual fun createDefaultGamepadMapping(): GamepadMapping = GamepadMapping()
