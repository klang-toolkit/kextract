//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[resetDeadKeys](reset-dead-keys.md)

# resetDeadKeys

[common]\
abstract fun [resetDeadKeys](reset-dead-keys.md)()

Resets any pending dead-key state for this window.

On layouts with dead keys (e.g. `^` + `e` → `ê`), pressing a dead key puts the input method into a &quot;waiting&quot; state. If the application wants to discard that state (e.g. when the window loses focus and the user switches away), it can call this method.

Backend behaviour:

-
   appkit   : calls `[[NSInputManager currentInputManager] markedTextAbandoned:]`               (best-effort; no-op if not supported).
-
   win32    : calls `ToUnicode` with a dummy scan code to flush the dead-key buffer.
-
   x11      : resets the XIC / XkbCompose state (best-effort).
-
   wayland  : resets the xkb_compose_state (best-effort).
-
   web      : no-op — dead-key state is managed by the browser IME.
-
   android  : no-op — IME state is managed by InputMethodManager.
-
   uikit    : no-op — dead-key state is managed by UIKit.

Never throws.