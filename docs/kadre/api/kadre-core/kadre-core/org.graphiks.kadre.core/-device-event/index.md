//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[DeviceEvent](index.md)

# DeviceEvent

sealed interface [DeviceEvent](index.md)

Raw input device event.

Unlike [WindowEvent](../-window-event/index.md), these events are emitted independently of the active window and reflect the raw state of the device.

### Typical usage

```kotlin
fun onDeviceEvent(event: DeviceEvent) {
    when (event) {
        is DeviceEvent.PointerMotion -> handleMotion(event.dx, event.dy)
        is DeviceEvent.Button        -> handleButton(event.button, event.state)
        is DeviceEvent.Key           -> handleKey(event.scancode, event.state)
        is DeviceEvent.MouseWheel    -> handleWheel(event.deltaX, event.deltaY)
    }
}
```

#### Inheritors

| |
|---|
| [PointerMotion](-pointer-motion/index.md) |
| [Button](-button/index.md) |
| [Key](-key/index.md) |
| [MouseWheel](-mouse-wheel/index.md) |

## Types

| Name | Summary |
|---|---|
| [Button](-button/index.md) | [common]<br>data class [Button](-button/index.md)(val button: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), val state: [KeyState](../-key-state/index.md)) : [DeviceEvent](index.md)<br>A physical device button changed state. |
| [Key](-key/index.md) | [common]<br>data class [Key](-key/index.md)(val scancode: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), val state: [KeyState](../-key-state/index.md)) : [DeviceEvent](index.md)<br>A physical keyboard key changed state (identified by scancode). |
| [MouseWheel](-mouse-wheel/index.md) | [common]<br>data class [MouseWheel](-mouse-wheel/index.md)(val deltaX: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), val deltaY: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) : [DeviceEvent](index.md)<br>The mouse wheel (or trackpad) scrolled — raw device event, not clipped to a window. |
| [PointerMotion](-pointer-motion/index.md) | [common]<br>data class [PointerMotion](-pointer-motion/index.md)(val dx: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), val dy: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) : [DeviceEvent](index.md)<br>Raw pointer motion (delta, not limited to the screen edges). |