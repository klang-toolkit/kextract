//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)

# Window

[common]\
interface [Window](index.md)

Abstraction d'une fenêtre native créée par la boucle d'événements.

Les implémentations concrètes sont fournies par les modules de plateforme (kadre-appkit, etc.).

## Properties

| Name | Summary |
|---|---|
| [id](id.md) | [common]<br>abstract val [id](id.md): [WindowId](../-window-id/index.md)<br>Identifiant unique de la fenêtre. |
| [innerSize](inner-size.md) | [common]<br>abstract val [innerSize](inner-size.md): [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;<br>Retourne la taille interne de la fenêtre en pixels physiques (surface de rendu, sans les décorations). |
| [outerSize](outer-size.md) | [common]<br>abstract val [outerSize](outer-size.md): [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;<br>Retourne la taille externe de la fenêtre en pixels physiques (surface de rendu plus les décorations de la plateforme). |
| [rawDisplayHandle](raw-display-handle.md) | [common]<br>abstract val [rawDisplayHandle](raw-display-handle.md): [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)<br>Retourne le handle natif de l'affichage. |
| [rawWindowHandle](raw-window-handle.md) | [common]<br>abstract val [rawWindowHandle](raw-window-handle.md): [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)<br>Retourne le handle natif de la surface de rendu. |
| [scaleFactor](scale-factor.md) | [common]<br>abstract val [scaleFactor](scale-factor.md): [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)<br>Retourne le facteur d'échelle entre les pixels logiques et physiques pour cette fenêtre. |

## Functions

| Name | Summary |
|---|---|
| [close](close.md) | [common]<br>abstract fun [close](close.md)()<br>Ferme la fenêtre. |
| [requestRedraw](request-redraw.md) | [common]<br>abstract fun [requestRedraw](request-redraw.md)()<br>Demande un rafraîchissement (redraw) de la fenêtre à la prochaine itération. |
| [setTitle](set-title.md) | [common]<br>abstract fun [setTitle](set-title.md)(title: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html))<br>Définit le titre affiché dans la barre de titre de la fenêtre. |
| [setVisible](set-visible.md) | [common]<br>abstract fun [setVisible](set-visible.md)(visible: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Rend la fenêtre visible ou invisible. |