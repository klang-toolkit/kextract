//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[RawWindowHandle](../index.md)/[Android](index.md)

# Android

[common]\
data class [Android](index.md)(val surface: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)) : [RawWindowHandle](../index.md)

Android window handle.

## Constructors

| | |
|---|---|
| [Android](-android.md) | [common]<br>constructor(surface: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [surface](surface.md) | [common]<br>val [surface](surface.md): [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)<br>Instance of the native surface. At runtime, this parameter is                    necessarily an instance of `android.view.Surface`; the type                    is declared [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html) so as not to introduce an Android import into                    commonMain — the consumer performs the explicit cast. |