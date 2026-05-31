//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[LogicalSize](index.md)

# LogicalSize

data class [LogicalSize](index.md)&lt;[T](index.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt;(val width: [T](index.md), val height: [T](index.md))

Taille exprimée en **unités logiques** (device-independent pixels).

#### Type Parameters

common

| | |
|---|---|
| T | Type numérique des composantes (ex. [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html), [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)). |

## Constructors

| | |
|---|---|
| [LogicalSize](-logical-size.md) | [common]<br>constructor(width: [T](index.md), height: [T](index.md)) |

## Properties

| Name | Summary |
|---|---|
| [height](height.md) | [common]<br>val [height](height.md): [T](index.md)<br>Hauteur en unités logiques. |
| [width](width.md) | [common]<br>val [width](width.md): [T](index.md)<br>Largeur en unités logiques. |

## Functions

| Name | Summary |
|---|---|
| [toPhysical](../to-physical.md) | [common]<br>fun &lt;[T](../to-physical.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt; [LogicalSize](index.md)&lt;[T](../to-physical.md)&gt;.[toPhysical](../to-physical.md)(scaleFactor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)): [PhysicalSize](../-physical-size/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Convertit cette taille logique en taille physique en appliquant le [scaleFactor](../to-physical.md). |