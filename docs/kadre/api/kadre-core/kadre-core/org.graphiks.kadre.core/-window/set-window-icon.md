//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setWindowIcon](set-window-icon.md)

# setWindowIcon

[common]\
abstract fun [setWindowIcon](set-window-icon.md)(icon: [Icon](../-icon/index.md)?)

Sets the application icon shown in the taskbar / dock.

Passing null resets to the default icon. Behaviour is best-effort:

-
   AppKit: sets `NSApp.applicationIconImage`.
-
   Win32:  sends `WM_SETICON`.
-
   X11:    sets `_NET_WM_ICON`.
-
   Others: no-op. Never throws.

#### Parameters

common

| | |
|---|---|
| icon | Icon data, or null to restore the default. |