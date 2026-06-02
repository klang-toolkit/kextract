//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[dragWindow](drag-window.md)

# dragWindow

[common]\
open fun [dragWindow](drag-window.md)()

Initiates a user-driven window drag from the current cursor position.

Intended to be called from a pointer-pressed event handler to allow dragging a custom title bar. Platform behaviour:

-
   AppKit   : `NSWindow.performWindowDragWithEvent`.
-
   Wayland  : `xdg_toplevel.move`.
-
   Others   : no-op documented.

Default implementation is a no-op. Never throws. TODO R5-MiscWindow: wire in AppKit and Wayland backends.