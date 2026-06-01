//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[theme](theme.md)

# theme

[common]\
abstract val [theme](theme.md): [Theme](../-theme/index.md)?

Returns the current system theme as seen by this window, or null if unknown.

Uses the same source as [ActiveEventLoop.systemTheme](../-active-event-loop/system-theme.md) but restricted to this window's appearance (e.g. a per-window NSAppearance override on macOS).