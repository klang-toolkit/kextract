//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[fullscreen](fullscreen.md)

# fullscreen

[common]\
abstract val [fullscreen](fullscreen.md): [Fullscreen](../-fullscreen/index.md)?

Returns the current fullscreen state, or null if the window is not fullscreen.

The value reflects the last successful [setFullscreen](set-fullscreen.md) call on backends that track state in-memory. It may differ from the actual compositor state immediately after calling [setFullscreen](set-fullscreen.md) (the compositor may asynchronously confirm the change).

#### Return

The active [Fullscreen](../-fullscreen/index.md) mode, or null when in windowed mode.