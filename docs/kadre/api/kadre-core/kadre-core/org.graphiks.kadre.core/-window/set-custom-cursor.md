//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setCustomCursor](set-custom-cursor.md)

# setCustomCursor

[common]\
open fun [setCustomCursor](set-custom-cursor.md)(cursor: [CustomCursor](../-custom-cursor/index.md))

Applies a previously created custom cursor to this window.

The [cursor](set-custom-cursor.md) must have been obtained via [ActiveEventLoop.createCustomCursor](../-active-event-loop/create-custom-cursor.md). Default implementation is a no-op — backends will override. No-op on iOS and Android (platform does not support custom cursors). Never throws.

TODO R5-CustomCursor: wire in each backend (AppKit, Win32, X11, Wayland, Web).

#### Parameters

common

| | |
|---|---|
| cursor | Custom cursor handle to apply. |