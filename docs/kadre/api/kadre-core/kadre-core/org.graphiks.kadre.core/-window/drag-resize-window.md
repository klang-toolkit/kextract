//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[dragResizeWindow](drag-resize-window.md)

# dragResizeWindow

[common]\
open fun [dragResizeWindow](drag-resize-window.md)(direction: [ResizeDirection](../-resize-direction/index.md))

Initiates a user-driven window resize from the current cursor position.

Must be called from a pointer-pressed event handler. Platform behaviour:

- 
   Wayland  : `xdg_toplevel.resize` with the matching edge.
- 
   Others   : no-op documented.

Default implementation is a no-op. Never throws. TODO R5-MiscWindow: wire in Wayland (and potentially Win32) backend.

#### Parameters

common

| | |
|---|---|
| direction | The window edge / corner to resize from. |