//[kadre-core](../../index.md)/[org.graphiks.kadre.core](index.md)/[toPhysical](to-physical.md)

# toPhysical

[common]\
fun &lt;[T](to-physical.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt; [LogicalSize](-logical-size/index.md)&lt;[T](to-physical.md)&gt;.[toPhysical](to-physical.md)(scaleFactor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)): [PhysicalSize](-physical-size/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;

Convertit cette taille logique en taille physique en appliquant le [scaleFactor](to-physical.md).

Formule : `physique = logique × scaleFactor`

#### Return

[PhysicalSize](-physical-size/index.md) dont les composantes sont de type [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html).

#### Parameters

common

| | |
|---|---|
| scaleFactor | Facteur d'échelle DPI (ex. `2.0` pour un écran Retina). |

[common]\
fun &lt;[T](to-physical.md) : [Number](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-number/index.html)&gt; [LogicalPosition](-logical-position/index.md)&lt;[T](to-physical.md)&gt;.[toPhysical](to-physical.md)(scaleFactor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)): [PhysicalPosition](-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;

Convertit cette position logique en position physique en appliquant le [scaleFactor](to-physical.md).

Formule : `physique = logique × scaleFactor`

#### Return

[PhysicalPosition](-physical-position/index.md) dont les composantes sont de type [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html).

#### Parameters

common

| | |
|---|---|
| scaleFactor | Facteur d'échelle DPI (ex. `2.0` pour un écran Retina). |