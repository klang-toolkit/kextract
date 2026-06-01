//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[RawWindowHandle](../index.md)/[UiKit](index.md)

# UiKit

[common]\
data class [UiKit](index.md)(val uiView: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val uiViewController: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)?) : [RawWindowHandle](../index.md)

UIKit window handle (iOS / tvOS).

## Constructors

| | |
|---|---|
| [UiKit](-ui-kit.md) | [common]<br>constructor(uiView: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), uiViewController: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)?) |

## Properties

| Name | Summary |
|---|---|
| [uiView](ui-view.md) | [common]<br>val [uiView](ui-view.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Pointer to the `UIView` instance (cast to `UIView*` at the point of use). |
| [uiViewController](ui-view-controller.md) | [common]<br>val [uiViewController](ui-view-controller.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)?<br>Optional pointer to the `UIViewController` instance                             (cast to `UIViewController*` at the point of use), or `null`                             if no controller is associated. |