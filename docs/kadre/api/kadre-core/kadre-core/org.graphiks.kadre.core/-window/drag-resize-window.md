//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[dragResizeWindow](drag-resize-window.md)

# dragResizeWindow

[common]\
open fun [dragResizeWindow](drag-resize-window.md)(direction: [ResizeDirection](../-resize-direction/index.md)): [WindowRequestResult](../-window-request-result/index.md)

Initiates a user-driven window resize from the current cursor position.

Must be called from a pointer-pressed event handler. Platform behaviour:

-
   Win32    : posts a non-client resize request to the window owner thread.
-
   X11      : sends `_NET_WM_MOVERESIZE` with the matching resize action.
-
   Wayland  : sends `xdg_toplevel.resize` with the latest pointer button serial.
-
   Others   : [WindowRequestResult.Failure](../-window-request-result/-failure/index.md) with [RequestError.Unsupported](../-request-error/-unsupported/index.md).

Default implementation returns [WindowRequestResult.Failure](../-window-request-result/-failure/index.md) with [RequestError.Unsupported](../-request-error/-unsupported/index.md). Never throws.

#### Parameters

common

| | |
|---|---|
| direction | The window edge / corner to resize from. |
