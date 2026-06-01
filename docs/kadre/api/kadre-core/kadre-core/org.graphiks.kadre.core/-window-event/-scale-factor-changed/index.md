//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[ScaleFactorChanged](index.md)

# ScaleFactorChanged

[common]\
data class [ScaleFactorChanged](index.md)(val factor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) : [WindowEvent](../index.md)

The window's DPI scale factor changed (e.g. moved to another monitor).

## Constructors

| | |
|---|---|
| [ScaleFactorChanged](-scale-factor-changed.md) | [common]<br>constructor(factor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [factor](factor.md) | [common]<br>val [factor](factor.md): [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)<br>New scale factor (e.g. `2.0` on a Retina screen). |