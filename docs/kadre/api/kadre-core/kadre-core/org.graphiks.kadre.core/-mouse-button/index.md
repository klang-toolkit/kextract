//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[MouseButton](index.md)

# MouseButton

sealed interface [MouseButton](index.md)

Mouse button.

The three main buttons have named objects; additional buttons are represented by [Other](-other/index.md).

#### Inheritors

| |
|---|
| [Left](-left/index.md) |
| [Right](-right/index.md) |
| [Middle](-middle/index.md) |
| [Other](-other/index.md) |

## Types

| Name | Summary |
|---|---|
| [Left](-left/index.md) | [common]<br>data object [Left](-left/index.md) : [MouseButton](index.md)<br>Left button (primary button). |
| [Middle](-middle/index.md) | [common]<br>data object [Middle](-middle/index.md) : [MouseButton](index.md)<br>Middle button (wheel or center button). |
| [Other](-other/index.md) | [common]<br>data class [Other](-other/index.md)(val button: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)) : [MouseButton](index.md)<br>Additional button identified by its numeric index. |
| [Right](-right/index.md) | [common]<br>data object [Right](-right/index.md) : [MouseButton](index.md)<br>Right button (secondary button / context menu). |