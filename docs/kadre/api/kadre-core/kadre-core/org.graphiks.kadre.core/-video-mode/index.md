//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[VideoMode](index.md)

# VideoMode

[common]\
data class [VideoMode](index.md)(val size: [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;, val bitDepth: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)?, val refreshRateMilliHz: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)?)

A video mode supported by a monitor.

## Constructors

| | |
|---|---|
| [VideoMode](-video-mode.md) | [common]<br>constructor(size: [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;, bitDepth: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)?, refreshRateMilliHz: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)?) |

## Properties

| Name | Summary |
|---|---|
| [bitDepth](bit-depth.md) | [common]<br>val [bitDepth](bit-depth.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)?<br>Color depth in bits per channel (e.g. 8 for 24-bit color), or null if unavailable. |
| [refreshRateMilliHz](refresh-rate-milli-hz.md) | [common]<br>val [refreshRateMilliHz](refresh-rate-milli-hz.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)?<br>Refresh rate in milli-Hz (e.g. 60_000 for 60 Hz), or null if unavailable. |
| [size](size.md) | [common]<br>val [size](size.md): [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;<br>Resolution in physical pixels. |