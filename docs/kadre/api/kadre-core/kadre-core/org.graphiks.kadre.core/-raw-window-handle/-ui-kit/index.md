//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[RawWindowHandle](../index.md)/[UiKit](index.md)

# UiKit

[common]\
data class [UiKit](index.md)(val uiView: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val uiViewController: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)?) : [RawWindowHandle](../index.md)

Handle de fenêtre UIKit (iOS / tvOS).

## Constructors

| | |
|---|---|
| [UiKit](-ui-kit.md) | [common]<br>constructor(uiView: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), uiViewController: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)?) |

## Properties

| Name | Summary |
|---|---|
| [uiView](ui-view.md) | [common]<br>val [uiView](ui-view.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Pointeur vers l'instance `UIView` (cast vers `UIView*` au point d'usage). |
| [uiViewController](ui-view-controller.md) | [common]<br>val [uiViewController](ui-view-controller.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)?<br>Pointeur optionnel vers l'instance `UIViewController`                             (cast vers `UIViewController*` au point d'usage), ou `null`                             si aucun contrôleur n'est associé. |