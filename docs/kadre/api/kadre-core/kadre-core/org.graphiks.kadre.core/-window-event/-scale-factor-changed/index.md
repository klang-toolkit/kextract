//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[ScaleFactorChanged](index.md)

# ScaleFactorChanged

[common]\
data class [ScaleFactorChanged](index.md)(val factor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) : [WindowEvent](../index.md)

Le facteur d'échelle DPI de la fenêtre a changé (ex. déplacement vers un autre moniteur).

## Constructors

| | |
|---|---|
| [ScaleFactorChanged](-scale-factor-changed.md) | [common]<br>constructor(factor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [factor](factor.md) | [common]<br>val [factor](factor.md): [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)<br>Nouveau facteur d'échelle (ex. `2.0` sur un écran Retina). |