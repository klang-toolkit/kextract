//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[MouseWheel](index.md)

# MouseWheel

[common]\
data class [MouseWheel](index.md)(val deltaX: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), val deltaY: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) : [WindowEvent](../index.md)

The mouse wheel (or trackpad) produced a scroll.

## Constructors

| | |
|---|---|
| [MouseWheel](-mouse-wheel.md) | [common]<br>constructor(deltaX: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), deltaY: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [deltaX](delta-x.md) | [common]<br>val [deltaX](delta-x.md): [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)<br>Horizontal scroll (positive towards the right). |
| [deltaY](delta-y.md) | [common]<br>val [deltaY](delta-y.md): [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)<br>Vertical scroll (positive towards the bottom). |