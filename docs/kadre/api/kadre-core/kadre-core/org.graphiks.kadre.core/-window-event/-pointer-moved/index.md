//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[PointerMoved](index.md)

# PointerMoved

[common]\
data class [PointerMoved](index.md)(val position: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;) : [WindowEvent](../index.md)

The pointer moved over the window.

## Constructors

| | |
|---|---|
| [PointerMoved](-pointer-moved.md) | [common]<br>constructor(position: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [position](position.md) | [common]<br>val [position](position.md): [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Current pointer position in physical pixels (floating point for the sub-pixel precision of tablets and trackpads). |