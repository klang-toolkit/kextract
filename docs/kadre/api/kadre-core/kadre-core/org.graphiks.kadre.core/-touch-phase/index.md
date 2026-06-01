//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[TouchPhase](index.md)

# TouchPhase

[common]\
enum [TouchPhase](index.md) : [Enum](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-enum/index.html)&lt;[TouchPhase](index.md)&gt; 

Phase of a touch contact.

## Entries

| | |
|---|---|
| [Started](-started/index.md) | [common]<br>[Started](-started/index.md)<br>The contact has just been placed on the screen. |
| [Moved](-moved/index.md) | [common]<br>[Moved](-moved/index.md)<br>The contact has moved on the screen. |
| [Ended](-ended/index.md) | [common]<br>[Ended](-ended/index.md)<br>The contact has been removed from the screen. |
| [Cancelled](-cancelled/index.md) | [common]<br>[Cancelled](-cancelled/index.md)<br>The contact has been cancelled (e.g. incoming call, system gesture). |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | [common]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.enums/-enum-entries/index.html)&lt;[TouchPhase](index.md)&gt; |
| [name](../-ime-purpose/-terminal/index.md#-372974862%2FProperties%2F-959609056) | [common]<br>expect val [name](../-ime-purpose/-terminal/index.md#-372974862%2FProperties%2F-959609056): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) |
| [ordinal](../-ime-purpose/-terminal/index.md#-739389684%2FProperties%2F-959609056) | [common]<br>expect val [ordinal](../-ime-purpose/-terminal/index.md#-739389684%2FProperties%2F-959609056): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [common]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [TouchPhase](index.md) |
| [values](values.md) | [common]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-array/index.html)&lt;[TouchPhase](index.md)&gt; |