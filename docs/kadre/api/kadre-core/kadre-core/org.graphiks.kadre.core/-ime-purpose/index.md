//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ImePurpose](index.md)

# ImePurpose

[common]\
enum [ImePurpose](index.md) : [Enum](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-enum/index.html)&lt;[ImePurpose](index.md)&gt; 

Intended purpose of the IME text field currently focused.

Passed to [Window.setImePurpose](../-window/set-ime-purpose.md) so that the platform input method can adapt its behaviour (e.g. hide suggestions for a terminal, mask characters for a password field).

#### Since

R5-IME

## Entries

| | |
|---|---|
| [Normal](-normal/index.md) | [common]<br>[Normal](-normal/index.md)<br>General text input — the default. |
| [Password](-password/index.md) | [common]<br>[Password](-password/index.md)<br>Password field — the IME should hide the composed text. |
| [Terminal](-terminal/index.md) | [common]<br>[Terminal](-terminal/index.md)<br>Terminal / command input — suggestions and auto-correct should be suppressed. |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | [common]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.enums/-enum-entries/index.html)&lt;[ImePurpose](index.md)&gt; |
| [name](-terminal/index.md#-372974862%2FProperties%2F-959609056) | [common]<br>expect val [name](-terminal/index.md#-372974862%2FProperties%2F-959609056): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) |
| [ordinal](-terminal/index.md#-739389684%2FProperties%2F-959609056) | [common]<br>expect val [ordinal](-terminal/index.md#-739389684%2FProperties%2F-959609056): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [common]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [ImePurpose](index.md) |
| [values](values.md) | [common]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-array/index.html)&lt;[ImePurpose](index.md)&gt; |