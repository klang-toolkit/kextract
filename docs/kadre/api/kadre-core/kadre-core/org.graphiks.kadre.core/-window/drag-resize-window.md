//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[dragResizeWindow](drag-resize-window.md)

# dragResizeWindow

[common]\
open fun [dragResizeWindow](drag-resize-window.md)(direction: [ResizeDirection](../-resize-direction/index.md)): [WindowRequestResult](../-window-request-result/index.md)

Initiates a user-driven window resize from the current cursor position.

Must be called from a pointer-pressed event handler. Platform behaviour:

-
   Wayland  : `xdg_toplevel.resize` with the matching edge when implemented.
-
   Others   : [WindowRequestResult.Failure](../-window-request-result/-failure/index.md) with [RequestError.Unsupported](../-request-error/-unsupported/index.md).

Default implementation returns [WindowRequestResult.Failure](../-window-request-result/-failure/index.md) with [RequestError.Unsupported](../-request-error/-unsupported/index.md). Never throws. TODO R5-MiscWindow: wire in Wayland (and potentially Win32) backend.

#### Parameters

common

| | |
|---|---|
| direction | The window edge / corner to resize from. |
