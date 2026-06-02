//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[showWindowMenu](show-window-menu.md)

# showWindowMenu

[common]\
open fun [showWindowMenu](show-window-menu.md)(position: [PhysicalPosition](../-physical-position/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;)

Shows the platform window menu (system / title-bar context menu) at the given position.

Platform behaviour:

-
   Win32  : `TrackPopupMenu(GetSystemMenu(...))`.
-
   Others : no-op documented.

Default implementation is a no-op. Never throws. TODO R5-MiscWindow: wire in Win32 backend.

#### Parameters

common

| | |
|---|---|
| position | Position in physical pixels (window-relative) at which to show the menu. |