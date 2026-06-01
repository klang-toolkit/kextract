//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setCursorHittest](set-cursor-hittest.md)

# setCursorHittest

[common]\
abstract fun [setCursorHittest](set-cursor-hittest.md)(hittest: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))

Enables or disables cursor hit-testing for this window.

When disabled (`false`) the window becomes click-through — pointer events are forwarded to the window underneath. On backends that do not support hit-testing this is a no-op. Never throws.

#### Parameters

common

| | |
|---|---|
| hittest | true (default) to receive pointer events; false to pass them through. |