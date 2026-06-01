//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[UserAttentionType](index.md)

# UserAttentionType

[common]\
enum [UserAttentionType](index.md) : [Enum](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-enum/index.html)&lt;[UserAttentionType](index.md)&gt; 

Type of user-attention request on the taskbar or dock icon.

Passed to [Window.requestUserAttention](../-window/request-user-attention.md).

## Entries

| | |
|---|---|
| [Critical](-critical/index.md) | [common]<br>[Critical](-critical/index.md)<br>Critical attention — the icon bounces continuously (macOS) or flashes rapidly (Win32). Used for urgent alerts that require an immediate response. |
| [Informational](-informational/index.md) | [common]<br>[Informational](-informational/index.md)<br>Informational attention — the icon bounces once (macOS) or flashes once (Win32). Used for non-blocking notifications. |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | [common]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.enums/-enum-entries/index.html)&lt;[UserAttentionType](index.md)&gt; |
| [name](../-ime-purpose/-terminal/index.md#-372974862%2FProperties%2F-959609056) | [common]<br>expect val [name](../-ime-purpose/-terminal/index.md#-372974862%2FProperties%2F-959609056): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) |
| [ordinal](../-ime-purpose/-terminal/index.md#-739389684%2FProperties%2F-959609056) | [common]<br>expect val [ordinal](../-ime-purpose/-terminal/index.md#-739389684%2FProperties%2F-959609056): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [common]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [UserAttentionType](index.md) |
| [values](values.md) | [common]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-array/index.html)&lt;[UserAttentionType](index.md)&gt; |