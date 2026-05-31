//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[RawWindowHandle](index.md)

# RawWindowHandle

sealed interface [RawWindowHandle](index.md)

Handle brut d'une fenêtre native.

Chaque variant correspond à une plateforme cible. Les pointeurs sont exposés en [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html) afin que commonMain reste 100 % Kotlin pur (pas d'import natif).

#### Inheritors

| |
|---|
| [AppKit](-app-kit/index.md) |
| [UiKit](-ui-kit/index.md) |
| [Android](-android/index.md) |

## Types

| Name | Summary |
|---|---|
| [Android](-android/index.md) | [common]<br>data class [Android](-android/index.md)(val surface: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)) : [RawWindowHandle](index.md)<br>Handle de fenêtre Android. |
| [AppKit](-app-kit/index.md) | [common]<br>data class [AppKit](-app-kit/index.md)(val nsView: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val nsWindow: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val nsLayer: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html) = 0) : [RawWindowHandle](index.md)<br>Handle de fenêtre AppKit (macOS). |
| [UiKit](-ui-kit/index.md) | [common]<br>data class [UiKit](-ui-kit/index.md)(val uiView: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val uiViewController: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)?) : [RawWindowHandle](index.md)<br>Handle de fenêtre UIKit (iOS / tvOS). |