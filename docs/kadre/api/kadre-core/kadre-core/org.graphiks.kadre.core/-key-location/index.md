//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[KeyLocation](index.md)

# KeyLocation

[common]\
enum [KeyLocation](index.md) : [Enum](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-enum/index.html)&lt;[KeyLocation](index.md)&gt; 

Physical location of a keyboard key.

Distinguishes between keys that appear in multiple locations on the keyboard (e.g. left vs. right Shift, numpad digits vs. top-row digits).

#### Since

R4

## Entries

| | |
|---|---|
| [Standard](-standard/index.md) | [common]<br>[Standard](-standard/index.md)<br>Key appears only once or its position is the standard one. |
| [Left](-left/index.md) | [common]<br>[Left](-left/index.md)<br>Left-side instance of a key (e.g. left Shift, left Ctrl). |
| [Right](-right/index.md) | [common]<br>[Right](-right/index.md)<br>Right-side instance of a key (e.g. right Shift, right Alt/AltGr). |
| [Numpad](-numpad/index.md) | [common]<br>[Numpad](-numpad/index.md)<br>Key is on the numeric keypad. |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | [common]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.enums/-enum-entries/index.html)&lt;[KeyLocation](index.md)&gt; |
| [name](../-ime-purpose/-terminal/index.md#-372974862%2FProperties%2F-959609056) | [common]<br>expect val [name](../-ime-purpose/-terminal/index.md#-372974862%2FProperties%2F-959609056): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) |
| [ordinal](../-ime-purpose/-terminal/index.md#-739389684%2FProperties%2F-959609056) | [common]<br>expect val [ordinal](../-ime-purpose/-terminal/index.md#-739389684%2FProperties%2F-959609056): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [common]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [KeyLocation](index.md) |
| [values](values.md) | [common]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-array/index.html)&lt;[KeyLocation](index.md)&gt; |