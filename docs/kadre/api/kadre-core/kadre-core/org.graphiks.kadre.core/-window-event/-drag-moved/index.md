//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[DragMoved](index.md)

# DragMoved

[common]\
data class [DragMoved](index.md)(val position: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;) : [WindowEvent](../index.md)

The drag cursor moved within the window while carrying files.

Emitted continuously as the user moves the drag cursor over the window. Default emission: no-op — TODO per backend.

## Constructors

| | |
|---|---|
| [DragMoved](-drag-moved.md) | [common]<br>constructor(position: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [position](position.md) | [common]<br>val [position](position.md): [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Current drag position in physical pixels. |