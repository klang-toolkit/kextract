//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[PhysicalSize](index.md)

# PhysicalSize

data class [PhysicalSize](index.md)&lt;[T](index.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt;(val width: [T](index.md), val height: [T](index.md))

Taille exprimée en **pixels physiques** (pixels réels du matériel).

#### Type Parameters

common

| | |
|---|---|
| T | Type numérique des composantes (ex. [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html), [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)). |

## Constructors

| | |
|---|---|
| [PhysicalSize](-physical-size.md) | [common]<br>constructor(width: [T](index.md), height: [T](index.md)) |

## Properties

| Name | Summary |
|---|---|
| [height](height.md) | [common]<br>val [height](height.md): [T](index.md)<br>Hauteur en pixels physiques. |
| [width](width.md) | [common]<br>val [width](width.md): [T](index.md)<br>Largeur en pixels physiques. |

## Functions

| Name | Summary |
|---|---|
| [toLogical](../to-logical.md) | [common]<br>fun &lt;[T](../to-logical.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt; [PhysicalSize](index.md)&lt;[T](../to-logical.md)&gt;.[toLogical](../to-logical.md)(scaleFactor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)): [LogicalSize](../-logical-size/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Convertit cette taille physique en taille logique en divisant par le [scaleFactor](../to-logical.md). |