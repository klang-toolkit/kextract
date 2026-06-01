//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[TouchpadPressure](index.md)

# TouchpadPressure

[common]\
data class [TouchpadPressure](index.md)(val pressure: [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html), val stage: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)) : [WindowEvent](../index.md)

A Force Touch / trackpad pressure event (macOS Force Touch trackpads only).

Availability must be checked at runtime (`NSEvent.isMouseEventType` / device capability). Default emission: no-op — TODO appkit backend.

## Constructors

| | |
|---|---|
| [TouchpadPressure](-touchpad-pressure.md) | [common]<br>constructor(pressure: [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html), stage: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [pressure](pressure.md) | [common]<br>val [pressure](pressure.md): [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html)<br>Normalized pressure value in `[0.0, 1.0]`. |
| [stage](stage.md) | [common]<br>val [stage](stage.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Discrete pressure stage (1 = light click, 2 = force click, etc.). |