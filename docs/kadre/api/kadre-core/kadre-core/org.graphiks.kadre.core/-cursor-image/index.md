//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[CursorImage](index.md)

# CursorImage

[common]\
data class [CursorImage](index.md)(val rgba: [ByteArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-byte-array/index.html), val width: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), val height: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), val hotspotX: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) = 0, val hotspotY: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) = 0)

RGBA pixel buffer for a custom cursor image.

The buffer must contain exactly `width * height * 4` bytes, in row-major order, starting from the top-left corner.

## Constructors

| | |
|---|---|
| [CursorImage](-cursor-image.md) | [common]<br>constructor(rgba: [ByteArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-byte-array/index.html), width: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), height: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), hotspotX: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) = 0, hotspotY: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) = 0) |

## Properties

| Name | Summary |
|---|---|
| [height](height.md) | [common]<br>val [height](height.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Image height in pixels. |
| [hotspotX](hotspot-x.md) | [common]<br>val [hotspotX](hotspot-x.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Horizontal hot-spot offset from the left edge (default 0). |
| [hotspotY](hotspot-y.md) | [common]<br>val [hotspotY](hotspot-y.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Vertical hot-spot offset from the top edge (default 0). |
| [rgba](rgba.md) | [common]<br>val [rgba](rgba.md): [ByteArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-byte-array/index.html)<br>Raw RGBA bytes (4 bytes per pixel). |
| [width](width.md) | [common]<br>val [width](width.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Image width in pixels. |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [common]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | [common]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |