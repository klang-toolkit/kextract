//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[RotationGesture](index.md)

# RotationGesture

[common]\
data class [RotationGesture](index.md)(val delta: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), val phase: [TouchPhase](../../-touch-phase/index.md)) : [WindowEvent](../index.md)

A rotation gesture changed state.

Emitted on macOS (NSGestureRecognizer rotation) and iOS (UIRotationGestureRecognizer). Default emission: no-op — TODO per backend.

## Constructors

| | |
|---|---|
| [RotationGesture](-rotation-gesture.md) | [common]<br>constructor(delta: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), phase: [TouchPhase](../../-touch-phase/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [delta](delta.md) | [common]<br>val [delta](delta.md): [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)<br>Rotation angle in radians (positive = clockwise). |
| [phase](phase.md) | [common]<br>val [phase](phase.md): [TouchPhase](../../-touch-phase/index.md)<br>Current phase of the gesture. |