//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[MouseButton](index.md)

# MouseButton

sealed interface [MouseButton](index.md)

Bouton de souris.

Les trois boutons principaux disposent d'objets nommés ; les boutons supplémentaires sont représentés par [Other](-other/index.md).

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
| [Left](-left/index.md) | [common]<br>data object [Left](-left/index.md) : [MouseButton](index.md)<br>Bouton gauche (bouton principal). |
| [Middle](-middle/index.md) | [common]<br>data object [Middle](-middle/index.md) : [MouseButton](index.md)<br>Bouton du milieu (molette ou bouton central). |
| [Other](-other/index.md) | [common]<br>data class [Other](-other/index.md)(val button: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)) : [MouseButton](index.md)<br>Bouton supplémentaire identifié par son index numérique. |
| [Right](-right/index.md) | [common]<br>data object [Right](-right/index.md) : [MouseButton](index.md)<br>Bouton droit (bouton secondaire / menu contextuel). |