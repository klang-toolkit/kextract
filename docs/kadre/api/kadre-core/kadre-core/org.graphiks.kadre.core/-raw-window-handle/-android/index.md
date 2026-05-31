//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[RawWindowHandle](../index.md)/[Android](index.md)

# Android

[common]\
data class [Android](index.md)(val surface: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)) : [RawWindowHandle](../index.md)

Handle de fenêtre Android.

## Constructors

| | |
|---|---|
| [Android](-android.md) | [common]<br>constructor(surface: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [surface](surface.md) | [common]<br>val [surface](surface.md): [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)<br>Instance de la surface native. Au runtime, ce paramètre est                    obligatoirement une instance de `android.view.Surface` ; le type                    est déclaré [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html) afin de ne pas introduire d'import Android dans                    commonMain — le consommateur effectue le cast explicite. |