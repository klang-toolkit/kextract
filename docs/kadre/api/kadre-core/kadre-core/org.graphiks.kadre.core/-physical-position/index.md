//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[PhysicalPosition](index.md)

# PhysicalPosition

data class [PhysicalPosition](index.md)&lt;[T](index.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt;(val x: [T](index.md), val y: [T](index.md))

Position expressed in **physical pixels** (real hardware pixels).

#### Type Parameters

common

| | |
|---|---|
| T | Numeric type of the components (e.g. [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html), [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)). |

## Constructors

| | |
|---|---|
| [PhysicalPosition](-physical-position.md) | [common]<br>constructor(x: [T](index.md), y: [T](index.md)) |

## Properties

| Name | Summary |
|---|---|
| [x](x.md) | [common]<br>val [x](x.md): [T](index.md)<br>Horizontal coordinate in physical pixels. |
| [y](y.md) | [common]<br>val [y](y.md): [T](index.md)<br>Vertical coordinate in physical pixels. |

## Functions

| Name | Summary |
|---|---|
| [toLogical](../to-logical.md) | [common]<br>fun &lt;[T](../to-logical.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt; [PhysicalPosition](index.md)&lt;[T](../to-logical.md)&gt;.[toLogical](../to-logical.md)(scaleFactor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)): [LogicalPosition](../-logical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Converts this physical position into a logical position by dividing by the [scaleFactor](../to-logical.md). |