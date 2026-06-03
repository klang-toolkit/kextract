//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[dragWindow](drag-window.md)

# dragWindow

[common]\
open fun [dragWindow](drag-window.md)(): [WindowRequestResult](../-window-request-result/index.md)

Initiates a user-driven window drag from the current cursor position.

Intended to be called from a pointer-pressed event handler to allow dragging a custom title bar. Platform behaviour:

-
   AppKit   : marshals to the AppKit main queue and calls `NSWindow.performWindowDragWithEvent(currentEvent)`; returns [RequestError.Ignored](../-request-error/-ignored/index.md) when no current event is available.
-
   Win32    : posts a non-client move request to the window owner thread.
-
   X11      : sends `_NET_WM_MOVERESIZE` with the move action to the WM.
-
   Wayland  : sends `xdg_toplevel.move` with the latest pointer button serial.
-
   Others   : [WindowRequestResult.Failure](../-window-request-result/-failure/index.md) with [RequestError.Unsupported](../-request-error/-unsupported/index.md).

Default implementation returns [WindowRequestResult.Failure](../-window-request-result/-failure/index.md) with [RequestError.Unsupported](../-request-error/-unsupported/index.md). Never throws.
