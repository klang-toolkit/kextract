//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[CursorIcon](index.md)

# CursorIcon

[common]\
enum [CursorIcon](index.md) : [Enum](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-enum/index.html)&lt;[CursorIcon](index.md)&gt; 

Cursor shape to display over the window.

The mapping to native cursors is backend-specific. Backends that do not support a given shape fall back to [Default](-default/index.md).

## Entries

| | |
|---|---|
| [Default](-default/index.md) | [common]<br>[Default](-default/index.md)<br>Standard OS default pointer. |
| [Pointer](-pointer/index.md) | [common]<br>[Pointer](-pointer/index.md)<br>Pointer / hand — typically used on links and buttons. |
| [Text](-text/index.md) | [common]<br>[Text](-text/index.md)<br>Text I-beam cursor. |
| [Crosshair](-crosshair/index.md) | [common]<br>[Crosshair](-crosshair/index.md)<br>Crosshair cursor — used for precise selection. |
| [Move](-move/index.md) | [common]<br>[Move](-move/index.md)<br>Four-directional move cursor. |
| [ResizeNorth](-resize-north/index.md) | [common]<br>[ResizeNorth](-resize-north/index.md)<br>Resize north (up) cursor. |
| [ResizeSouth](-resize-south/index.md) | [common]<br>[ResizeSouth](-resize-south/index.md)<br>Resize south (down) cursor. |
| [ResizeEast](-resize-east/index.md) | [common]<br>[ResizeEast](-resize-east/index.md)<br>Resize east (right) cursor. |
| [ResizeWest](-resize-west/index.md) | [common]<br>[ResizeWest](-resize-west/index.md)<br>Resize west (left) cursor. |
| [ResizeNorthEast](-resize-north-east/index.md) | [common]<br>[ResizeNorthEast](-resize-north-east/index.md)<br>Resize north-east cursor. |
| [ResizeNorthWest](-resize-north-west/index.md) | [common]<br>[ResizeNorthWest](-resize-north-west/index.md)<br>Resize north-west cursor. |
| [ResizeSouthEast](-resize-south-east/index.md) | [common]<br>[ResizeSouthEast](-resize-south-east/index.md)<br>Resize south-east cursor. |
| [ResizeSouthWest](-resize-south-west/index.md) | [common]<br>[ResizeSouthWest](-resize-south-west/index.md)<br>Resize south-west cursor. |
| [NotAllowed](-not-allowed/index.md) | [common]<br>[NotAllowed](-not-allowed/index.md)<br>Not-allowed / forbidden cursor. |
| [Grab](-grab/index.md) | [common]<br>[Grab](-grab/index.md)<br>Open hand / grab cursor. |
| [Grabbing](-grabbing/index.md) | [common]<br>[Grabbing](-grabbing/index.md)<br>Closed hand / grabbing cursor. |
| [Wait](-wait/index.md) | [common]<br>[Wait](-wait/index.md)<br>Wait / busy cursor. |
| [Progress](-progress/index.md) | [common]<br>[Progress](-progress/index.md)<br>Progress / background busy cursor. |
| [EwResize](-ew-resize/index.md) | [common]<br>[EwResize](-ew-resize/index.md)<br>East-west (horizontal) resize cursor (alias for ColResize). |
| [NsResize](-ns-resize/index.md) | [common]<br>[NsResize](-ns-resize/index.md)<br>North-south (vertical) resize cursor (alias for RowResize). |
| [NeswResize](-nesw-resize/index.md) | [common]<br>[NeswResize](-nesw-resize/index.md)<br>North-east / south-west resize cursor. |
| [NwseResize](-nwse-resize/index.md) | [common]<br>[NwseResize](-nwse-resize/index.md)<br>North-west / south-east resize cursor. |
| [ColResize](-col-resize/index.md) | [common]<br>[ColResize](-col-resize/index.md)<br>Column resize cursor. |
| [RowResize](-row-resize/index.md) | [common]<br>[RowResize](-row-resize/index.md)<br>Row resize cursor. |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | [common]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.enums/-enum-entries/index.html)&lt;[CursorIcon](index.md)&gt; |
| [name](../-ime-purpose/-terminal/index.md#-372974862%2FProperties%2F-959609056) | [common]<br>expect val [name](../-ime-purpose/-terminal/index.md#-372974862%2FProperties%2F-959609056): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) |
| [ordinal](../-ime-purpose/-terminal/index.md#-739389684%2FProperties%2F-959609056) | [common]<br>expect val [ordinal](../-ime-purpose/-terminal/index.md#-739389684%2FProperties%2F-959609056): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [common]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [CursorIcon](index.md) |
| [values](values.md) | [common]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-array/index.html)&lt;[CursorIcon](index.md)&gt; |