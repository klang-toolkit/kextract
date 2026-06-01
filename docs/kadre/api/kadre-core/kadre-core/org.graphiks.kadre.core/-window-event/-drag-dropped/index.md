//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[DragDropped](index.md)

# DragDropped

[common]\
data class [DragDropped](index.md)(val position: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;, val paths: [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;) : [WindowEvent](../index.md)

Files were dropped onto the window.

Emitted when the user releases the drag within the window client area. Default emission: no-op — TODO per backend.

## Constructors

| | |
|---|---|
| [DragDropped](-drag-dropped.md) | [common]<br>constructor(position: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;, paths: [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [paths](paths.md) | [common]<br>val [paths](paths.md): [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;<br>List of dropped file paths (or file names on Web). |
| [position](position.md) | [common]<br>val [position](position.md): [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Drop position in physical pixels. |