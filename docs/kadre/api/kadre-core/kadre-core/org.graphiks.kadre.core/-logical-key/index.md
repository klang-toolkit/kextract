//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[LogicalKey](index.md)

# LogicalKey

sealed interface [LogicalKey](index.md)

Logical key reported by the backend.

Backends should use the active keyboard layout when their native event exposes it. When it does not, they may emit a best-effort named/character fallback and keep raw platform details in [NativeKeyInfo](../-native-key-info/index.md).

#### Inheritors

| |
|---|
| [Character](-character/index.md) |
| [Named](-named/index.md) |
| [Dead](-dead/index.md) |
| [Unidentified](-unidentified/index.md) |

## Types

| Name | Summary |
|---|---|
| [Character](-character/index.md) | [common]<br>data class [Character](-character/index.md)(val text: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)) : [LogicalKey](index.md) |
| [Dead](-dead/index.md) | [common]<br>data class [Dead](-dead/index.md)(val accent: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?) : [LogicalKey](index.md) |
| [Named](-named/index.md) | [common]<br>data class [Named](-named/index.md)(val key: [NamedKey](../-named-key/index.md)) : [LogicalKey](index.md) |
| [Unidentified](-unidentified/index.md) | [common]<br>data class [Unidentified](-unidentified/index.md)(val native: [NativeKeyInfo](../-native-key-info/index.md) = NativeKeyInfo()) : [LogicalKey](index.md) |