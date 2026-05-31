//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[RawDisplayHandle](index.md)

# RawDisplayHandle

sealed interface [RawDisplayHandle](index.md)

Handle brut d'un écran (display) natif.

Chaque variant est un singleton correspondant à une plateforme cible. Sur ces plateformes, l'écran n'a pas de handle pointer distinct de la fenêtre.

#### Inheritors

| |
|---|
| [AppKit](-app-kit/index.md) |
| [UiKit](-ui-kit/index.md) |
| [Android](-android/index.md) |

## Types

| Name | Summary |
|---|---|
| [Android](-android/index.md) | [common]<br>data object [Android](-android/index.md) : [RawDisplayHandle](index.md)<br>Handle d'affichage Android. |
| [AppKit](-app-kit/index.md) | [common]<br>data object [AppKit](-app-kit/index.md) : [RawDisplayHandle](index.md)<br>Handle d'affichage AppKit (macOS). |
| [UiKit](-ui-kit/index.md) | [common]<br>data object [UiKit](-ui-kit/index.md) : [RawDisplayHandle](index.md)<br>Handle d'affichage UIKit (iOS / tvOS). |