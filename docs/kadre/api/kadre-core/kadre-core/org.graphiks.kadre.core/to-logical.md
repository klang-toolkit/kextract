//[kadre-core](../../index.md)/[org.graphiks.kadre.core](index.md)/[toLogical](to-logical.md)

# toLogical

[common]\
fun &lt;[T](to-logical.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt; [PhysicalSize](-physical-size/index.md)&lt;[T](to-logical.md)&gt;.[toLogical](to-logical.md)(scaleFactor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)): [LogicalSize](-logical-size/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;

Convertit cette taille physique en taille logique en divisant par le [scaleFactor](to-logical.md).

Formule : `logique = physique ÷ scaleFactor`

#### Return

[LogicalSize](-logical-size/index.md) dont les composantes sont de type [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html).

#### Parameters

common

| | |
|---|---|
| scaleFactor | Facteur d'échelle DPI (ex. `2.0` pour un écran Retina). |

[common]\
fun &lt;[T](to-logical.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt; [PhysicalPosition](-physical-position/index.md)&lt;[T](to-logical.md)&gt;.[toLogical](to-logical.md)(scaleFactor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)): [LogicalPosition](-logical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;

Convertit cette position physique en position logique en divisant par le [scaleFactor](to-logical.md).

Formule : `logique = physique ÷ scaleFactor`

#### Return

[LogicalPosition](-logical-position/index.md) dont les composantes sont de type [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html).

#### Parameters

common

| | |
|---|---|
| scaleFactor | Facteur d'échelle DPI (ex. `2.0` pour un écran Retina). |