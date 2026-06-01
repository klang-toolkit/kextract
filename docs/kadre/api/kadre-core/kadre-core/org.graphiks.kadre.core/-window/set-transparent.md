//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setTransparent](set-transparent.md)

# setTransparent

[common]\
abstract fun [setTransparent](set-transparent.md)(transparent: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))

Makes the window background transparent.

Transparency requires the renderer to draw with alpha < 1.0 for the transparent areas. On backends that do not support per-pixel alpha this is a no-op. Never throws.

#### Parameters

common

| | |
|---|---|
| transparent | true to enable transparency, false to disable it. |