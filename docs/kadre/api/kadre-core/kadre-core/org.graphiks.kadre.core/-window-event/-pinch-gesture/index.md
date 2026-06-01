//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[PinchGesture](index.md)

# PinchGesture

[common]\
data class [PinchGesture](index.md)(val delta: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), val phase: [TouchPhase](../../-touch-phase/index.md)) : [WindowEvent](../index.md)

A pinch (zoom) gesture changed state.

Primarily emitted on macOS (NSGestureRecognizer magnification) and iOS (UIPinchGestureRecognizer). Other backends: no-op documented. Default emission: no-op — TODO per backend.

## Constructors

| | |
|---|---|
| [PinchGesture](-pinch-gesture.md) | [common]<br>constructor(delta: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), phase: [TouchPhase](../../-touch-phase/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [delta](delta.md) | [common]<br>val [delta](delta.md): [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)<br>Relative scale change (positive = zoom in, negative = zoom out). |
| [phase](phase.md) | [common]<br>val [phase](phase.md): [TouchPhase](../../-touch-phase/index.md)<br>Current phase of the gesture. |