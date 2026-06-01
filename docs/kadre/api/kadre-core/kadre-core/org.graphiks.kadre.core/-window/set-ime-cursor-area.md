//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setImeCursorArea](set-ime-cursor-area.md)

# setImeCursorArea

[common]\
open fun [setImeCursorArea](set-ime-cursor-area.md)(position: [PhysicalPosition](../-physical-position/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;, size: [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;)

Notifies the IME of the text cursor's current position and bounding box.

The platform uses this information to position the IME candidate window near the cursor. Should be called whenever the cursor moves or the text layout changes.

Default implementation is a no-op — backends that support IME will override. TODO R5-IME: wire in each backend.

#### Parameters

common

| | |
|---|---|
| position | Top-left corner of the cursor area in physical pixels (window-relative). |
| size | Size of the cursor area in physical pixels. |