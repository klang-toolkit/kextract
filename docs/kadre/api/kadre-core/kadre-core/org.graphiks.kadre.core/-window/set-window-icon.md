//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setWindowIcon](set-window-icon.md)

# setWindowIcon

[common]\
abstract fun [setWindowIcon](set-window-icon.md)(icon: [Icon](../-icon/index.md)?)

Sets the native window icon where the platform exposes one.

Passing null resets to the default icon. Behaviour is best-effort:

-
   AppKit: no-op, matching winit: macOS has no per-window icon.
-
   Win32:  sends `WM_SETICON` for `ICON_SMALL`.
-
   X11:    sets `_NET_WM_ICON`.
-
   Wayland: currently no-op in Kadre; winit can use `xdg_toplevel_icon_manager_v1` when the compositor exposes it.
-
   Web/mobile: no-op. Never throws.

#### Parameters

common

| | |
|---|---|
| icon | Icon data, or null to restore the default. |
