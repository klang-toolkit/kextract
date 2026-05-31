//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[PointerMoved](index.md)

# PointerMoved

[common]\
data class [PointerMoved](index.md)(val position: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;) : [WindowEvent](../index.md)

Le pointeur s'est déplacé au-dessus de la fenêtre.

## Constructors

| | |
|---|---|
| [PointerMoved](-pointer-moved.md) | [common]<br>constructor(position: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [position](position.md) | [common]<br>val [position](position.md): [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Position courante du pointeur en pixels physiques (virgule flottante pour la précision sub-pixel des tablettes et trackpads). |