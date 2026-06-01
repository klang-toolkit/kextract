//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[DragEntered](index.md)

# DragEntered

[common]\
data class [DragEntered](index.md)(val position: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;, val paths: [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;) : [WindowEvent](../index.md)

A drag operation entered the window, carrying files at the given position.

Emitted when the user drags files over the window client area. Emission requires backend wiring — TODO per backend (AppKit NSDraggingDestination, Win32 IDropTarget, X11 XDND, Wayland wl_data_device, Web dragenter, UIKit UIDropInteraction).

## Constructors

| | |
|---|---|
| [DragEntered](-drag-entered.md) | [common]<br>constructor(position: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;, paths: [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [paths](paths.md) | [common]<br>val [paths](paths.md): [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;<br>List of file paths (or file names on Web where full paths are unavailable). |
| [position](position.md) | [common]<br>val [position](position.md): [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Current drag position in physical pixels. |