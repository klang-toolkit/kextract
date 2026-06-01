//[kadre-core](../../index.md)/[org.graphiks.kadre.core](index.md)/[toPhysical](to-physical.md)

# toPhysical

[common]\
fun &lt;[T](to-physical.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt; [LogicalSize](-logical-size/index.md)&lt;[T](to-physical.md)&gt;.[toPhysical](to-physical.md)(scaleFactor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)): [PhysicalSize](-physical-size/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;

Converts this logical size into a physical size by applying the [scaleFactor](to-physical.md).

Formula: `physical = logical × scaleFactor`

#### Return

[PhysicalSize](-physical-size/index.md) whose components are of type [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html).

#### Parameters

common

| | |
|---|---|
| scaleFactor | DPI scale factor (e.g. `2.0` for a Retina screen). |

[common]\
fun &lt;[T](to-physical.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt; [LogicalPosition](-logical-position/index.md)&lt;[T](to-physical.md)&gt;.[toPhysical](to-physical.md)(scaleFactor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)): [PhysicalPosition](-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;

Converts this logical position into a physical position by applying the [scaleFactor](to-physical.md).

Formula: `physical = logical × scaleFactor`

#### Return

[PhysicalPosition](-physical-position/index.md) whose components are of type [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html).

#### Parameters

common

| | |
|---|---|
| scaleFactor | DPI scale factor (e.g. `2.0` for a Retina screen). |