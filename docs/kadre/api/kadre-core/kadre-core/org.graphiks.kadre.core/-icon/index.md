//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Icon](index.md)

# Icon

[common]\
data class [Icon](index.md)(val rgba: [ByteArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-byte-array/index.html), val width: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), val height: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html))

Window application icon (RGBA pixel data).

## Constructors

| | |
|---|---|
| [Icon](-icon.md) | [common]<br>constructor(rgba: [ByteArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-byte-array/index.html), width: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), height: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [height](height.md) | [common]<br>val [height](height.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Height in pixels. |
| [rgba](rgba.md) | [common]<br>val [rgba](rgba.md): [ByteArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-byte-array/index.html)<br>Raw RGBA bytes (4 bytes per pixel, row-major, top-left origin). |
| [width](width.md) | [common]<br>val [width](width.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Width in pixels. |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [common]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | [common]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |