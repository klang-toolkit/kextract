//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setCursorGrab](set-cursor-grab.md)

# setCursorGrab

[common]\
abstract fun [setCursorGrab](set-cursor-grab.md)(mode: [CursorGrabMode](../-cursor-grab-mode/index.md)): [WindowRequestResult](../-window-request-result/index.md)

Sets the cursor grab mode for this window.

-
   [CursorGrabMode.None](../-cursor-grab-mode/-none/index.md)     — releases any existing grab.
-
   [CursorGrabMode.Confined](../-cursor-grab-mode/-confined/index.md) — limits the cursor to the window bounds.
-
   [CursorGrabMode.Locked](../-cursor-grab-mode/-locked/index.md)   — locks the cursor position (FPS mode).

Backends that do not support a given mode return [WindowRequestResult.Failure](../-window-request-result/-failure/index.md) with [RequestError.Unsupported](../-request-error/-unsupported/index.md). Never throws.

#### Parameters

common

| | |
|---|---|
| mode | New grab mode. |
