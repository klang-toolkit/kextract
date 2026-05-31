//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[TouchPhase](index.md)

# TouchPhase

[common]\
enum [TouchPhase](index.md) : [Enum](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-enum/index.html)&lt;[TouchPhase](index.md)&gt; 

Phase d'un contact tactile.

## Entries

| | |
|---|---|
| [Started](-started/index.md) | [common]<br>[Started](-started/index.md)<br>Le contact vient d'être posé sur l'écran. |
| [Moved](-moved/index.md) | [common]<br>[Moved](-moved/index.md)<br>Le contact s'est déplacé sur l'écran. |
| [Ended](-ended/index.md) | [common]<br>[Ended](-ended/index.md)<br>Le contact a été retiré de l'écran. |
| [Cancelled](-cancelled/index.md) | [common]<br>[Cancelled](-cancelled/index.md)<br>Le contact a été annulé (ex. appel entrant, geste système). |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | [common]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.enums/-enum-entries/index.html)&lt;[TouchPhase](index.md)&gt; |
| [name](-cancelled/index.md#-372974862%2FProperties%2F-287879124) | [common]<br>expect val [name](-cancelled/index.md#-372974862%2FProperties%2F-287879124): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) |
| [ordinal](-cancelled/index.md#-739389684%2FProperties%2F-287879124) | [common]<br>expect val [ordinal](-cancelled/index.md#-739389684%2FProperties%2F-287879124): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [common]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [TouchPhase](index.md) |
| [values](values.md) | [common]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-array/index.html)&lt;[TouchPhase](index.md)&gt; |