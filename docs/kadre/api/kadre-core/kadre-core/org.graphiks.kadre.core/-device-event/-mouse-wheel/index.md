//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[DeviceEvent](../index.md)/[MouseWheel](index.md)

# MouseWheel

[common]\
data class [MouseWheel](index.md)(val deltaX: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), val deltaY: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) : [DeviceEvent](../index.md)

The mouse wheel (or trackpad) scrolled — raw device event, not clipped to a window.

Emitted alongside [WindowEvent.MouseWheel](../../-window-event/-mouse-wheel/index.md) when the device-events filter allows it. See [ActiveEventLoop.listenDeviceEvents](../../-active-event-loop/listen-device-events.md).

## Constructors

| | |
|---|---|
| [MouseWheel](-mouse-wheel.md) | [common]<br>constructor(deltaX: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), deltaY: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [deltaX](delta-x.md) | [common]<br>val [deltaX](delta-x.md): [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)<br>Horizontal scroll delta (positive towards the right). |
| [deltaY](delta-y.md) | [common]<br>val [deltaY](delta-y.md): [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)<br>Vertical scroll delta (positive towards the bottom). |