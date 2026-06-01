//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[PanGesture](index.md)

# PanGesture

[common]\
data class [PanGesture](index.md)(val delta: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;, val phase: [TouchPhase](../../-touch-phase/index.md)) : [WindowEvent](../index.md)

A pan (scroll) gesture changed state.

Emitted on macOS (NSGestureRecognizer pan) and iOS (UIPanGestureRecognizer). Default emission: no-op — TODO per backend.

## Constructors

| | |
|---|---|
| [PanGesture](-pan-gesture.md) | [common]<br>constructor(delta: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;, phase: [TouchPhase](../../-touch-phase/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [delta](delta.md) | [common]<br>val [delta](delta.md): [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Displacement vector in physical pixels. |
| [phase](phase.md) | [common]<br>val [phase](phase.md): [TouchPhase](../../-touch-phase/index.md)<br>Current phase of the gesture. |