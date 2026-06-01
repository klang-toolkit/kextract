//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[ThemeChanged](index.md)

# ThemeChanged

[common]\
data class [ThemeChanged](index.md)(val theme: [Theme](../../-theme/index.md)) : [WindowEvent](../index.md)

The system UI theme changed (light ↔ dark).

Emitted by backends that support theme-change notifications (AppKit, Win32). Not emitted on X11, Wayland, Android (where [ActiveEventLoop.systemTheme](../../-active-event-loop/system-theme.md) should be polled) or Web (use `matchMedia('prefers-color-scheme')` via the bridge).

## Constructors

| | |
|---|---|
| [ThemeChanged](-theme-changed.md) | [common]<br>constructor(theme: [Theme](../../-theme/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [theme](theme.md) | [common]<br>val [theme](theme.md): [Theme](../../-theme/index.md)<br>New active theme. |