//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setBlur](set-blur.md)

# setBlur

[common]\
abstract fun [setBlur](set-blur.md)(blur: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))

Enables or disables a blur effect behind the window.

Requires true to be meaningful. On backends that do not support blur (X11, Web, Android) this is a no-op. Wayland currently no-ops in Kadre, but winit can use compositor-specific optional protocols such as `ext_background_effect` or KWin blur when available. Never throws.

#### Parameters

common

| | |
|---|---|
| blur | true to enable blur, false to disable it. |
