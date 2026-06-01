//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setCursorPosition](set-cursor-position.md)

# setCursorPosition

[common]\
abstract fun [setCursorPosition](set-cursor-position.md)(position: [PhysicalPosition](../-physical-position/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;)

Warps the cursor to the given position (physical pixels, relative to the top-left of the window's client area).

On backends where cursor warping is not supported this is a no-op. Never throws.

#### Parameters

common

| | |
|---|---|
| position | New cursor position in physical pixels. |