//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[LogicalPosition](index.md)

# LogicalPosition

data class [LogicalPosition](index.md)&lt;[T](index.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt;(val x: [T](index.md), val y: [T](index.md))

Position expressed in **logical units** (device-independent pixels).

#### Type Parameters

common

| | |
|---|---|
| T | Numeric type of the components (e.g. [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html), [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)). |

## Constructors

| | |
|---|---|
| [LogicalPosition](-logical-position.md) | [common]<br>constructor(x: [T](index.md), y: [T](index.md)) |

## Properties

| Name | Summary |
|---|---|
| [x](x.md) | [common]<br>val [x](x.md): [T](index.md)<br>Horizontal coordinate in logical units. |
| [y](y.md) | [common]<br>val [y](y.md): [T](index.md)<br>Vertical coordinate in logical units. |

## Functions

| Name | Summary |
|---|---|
| [toPhysical](../to-physical.md) | [common]<br>fun &lt;[T](../to-physical.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt; [LogicalPosition](index.md)&lt;[T](../to-physical.md)&gt;.[toPhysical](../to-physical.md)(scaleFactor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)): [PhysicalPosition](../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Converts this logical position into a physical position by applying the [scaleFactor](../to-physical.md). |