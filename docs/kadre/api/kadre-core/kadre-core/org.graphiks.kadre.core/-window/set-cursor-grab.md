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
AppKit supports [CursorGrabMode.Locked](../-cursor-grab-mode/-locked/index.md) and [CursorGrabMode.None](../-cursor-grab-mode/-none/index.md), but reports [RequestError.Unsupported](../-request-error/-unsupported/index.md) for [CursorGrabMode.Confined](../-cursor-grab-mode/-confined/index.md), matching winit.
Wayland accepts [CursorGrabMode.None](../-cursor-grab-mode/-none/index.md) as a success no-op like winit when pointer constraints are unavailable; [CursorGrabMode.Confined](../-cursor-grab-mode/-confined/index.md) and [CursorGrabMode.Locked](../-cursor-grab-mode/-locked/index.md) remain unsupported until `zwp_pointer_constraints_v1` is wired.

#### Parameters

common

| | |
|---|---|
| mode | New grab mode. |
