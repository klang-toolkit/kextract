//[kadre-core](../../index.md)/[org.graphiks.kadre.core](index.md)/[toLogical](to-logical.md)

# toLogical

[common]\
fun &lt;[T](to-logical.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt; [PhysicalSize](-physical-size/index.md)&lt;[T](to-logical.md)&gt;.[toLogical](to-logical.md)(scaleFactor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)): [LogicalSize](-logical-size/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;

Converts this physical size into a logical size by dividing by the [scaleFactor](to-logical.md).

Formula: `logical = physical ÷ scaleFactor`

#### Return

[LogicalSize](-logical-size/index.md) whose components are of type [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html).

#### Parameters

common

| | |
|---|---|
| scaleFactor | DPI scale factor (e.g. `2.0` for a Retina screen). |

[common]\
fun &lt;[T](to-logical.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt; [PhysicalPosition](-physical-position/index.md)&lt;[T](to-logical.md)&gt;.[toLogical](to-logical.md)(scaleFactor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)): [LogicalPosition](-logical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;

Converts this physical position into a logical position by dividing by the [scaleFactor](to-logical.md).

Formula: `logical = physical ÷ scaleFactor`

#### Return

[LogicalPosition](-logical-position/index.md) whose components are of type [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html).

#### Parameters

common

| | |
|---|---|
| scaleFactor | DPI scale factor (e.g. `2.0` for a Retina screen). |