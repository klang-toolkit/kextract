//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[CursorGrabMode](index.md)

# CursorGrabMode

[common]\
enum [CursorGrabMode](index.md) : [Enum](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-enum/index.html)&lt;[CursorGrabMode](index.md)&gt; 

Pointer grab mode.

- 
   [None](-none/index.md)     — cursor moves freely.
- 
   [Confined](-confined/index.md) — cursor is constrained inside the window bounds.
- 
   [Locked](-locked/index.md)   — cursor is hidden and positioned deltas are raw (FPS-style).

Mobile and web backends document their limitations in their respective implementations; this method never throws.

## Entries

| | |
|---|---|
| [None](-none/index.md) | [common]<br>[None](-none/index.md)<br>Cursor moves freely (default). |
| [Confined](-confined/index.md) | [common]<br>[Confined](-confined/index.md)<br>Cursor is confined to the window boundaries. |
| [Locked](-locked/index.md) | [common]<br>[Locked](-locked/index.md)<br>Cursor position is locked; raw deltas are provided instead. |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | [common]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.enums/-enum-entries/index.html)&lt;[CursorGrabMode](index.md)&gt; |
| [name](../-ime-purpose/-terminal/index.md#-372974862%2FProperties%2F-959609056) | [common]<br>expect val [name](../-ime-purpose/-terminal/index.md#-372974862%2FProperties%2F-959609056): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) |
| [ordinal](../-ime-purpose/-terminal/index.md#-739389684%2FProperties%2F-959609056) | [common]<br>expect val [ordinal](../-ime-purpose/-terminal/index.md#-739389684%2FProperties%2F-959609056): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [common]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [CursorGrabMode](index.md) |
| [values](values.md) | [common]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-array/index.html)&lt;[CursorGrabMode](index.md)&gt; |