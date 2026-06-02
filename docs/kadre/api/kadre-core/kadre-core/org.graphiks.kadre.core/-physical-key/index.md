//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[PhysicalKey](index.md)

# PhysicalKey

sealed interface [PhysicalKey](index.md)

Physical keyboard key independent of the active keyboard layout.

#### Inheritors

| |
|---|
| [Code](-code/index.md) |
| [Native](-native/index.md) |
| [Unidentified](-unidentified/index.md) |

## Types

| Name | Summary |
|---|---|
| [Code](-code/index.md) | [common]<br>data class [Code](-code/index.md)(val code: [KeyCode](../-key-code/index.md)) : [PhysicalKey](index.md) |
| [Native](-native/index.md) | [common]<br>data class [Native](-native/index.md)(val platform: [KeyPlatform](../-key-platform/index.md), val code: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [PhysicalKey](index.md) |
| [Unidentified](-unidentified/index.md) | [common]<br>data object [Unidentified](-unidentified/index.md) : [PhysicalKey](index.md) |