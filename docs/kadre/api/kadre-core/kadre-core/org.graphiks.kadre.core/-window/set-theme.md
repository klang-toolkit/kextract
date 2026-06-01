//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setTheme](set-theme.md)

# setTheme

[common]\
abstract fun [setTheme](set-theme.md)(theme: [Theme](../-theme/index.md)?)

Requests a specific theme for this window.

Passing null restores the system default. On backends where per-window theme control is not available this is a no-op. Never throws.

#### Parameters

common

| | |
|---|---|
| theme | Desired theme, or null to follow the system. |