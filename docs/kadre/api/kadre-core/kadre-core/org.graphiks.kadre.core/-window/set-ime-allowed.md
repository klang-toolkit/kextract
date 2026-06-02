//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setImeAllowed](set-ime-allowed.md)

# setImeAllowed

[common]\
open fun [setImeAllowed](set-ime-allowed.md)(allowed: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))

Enables or disables IME (Input Method Editor) input for this window.

When [allowed](set-ime-allowed.md) is `true`, the platform may activate the IME and the window will start receiving [WindowEvent.Ime](../-window-event/-ime/index.md) events. When `false`, the IME is suppressed and only raw [WindowEvent.KeyInput](../-window-event/-key-input/index.md) events are delivered.

Default implementation is a no-op — backends that support IME will override. TODO R5-IME: wire in each backend.

#### Parameters

common

| | |
|---|---|
| allowed | `true` to allow IME input, `false` to suppress it. |