//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ActiveEventLoop](index.md)/[systemTheme](system-theme.md)

# systemTheme

[common]\
abstract fun [systemTheme](system-theme.md)(): [Theme](../-theme/index.md)?

Returns the current system-wide UI theme, or null if the information is not available on this platform.

| Backend | Source |
|---|---|
| AppKit | `NSApp.effectiveAppearance` |
| Win32 | Registry `AppsUseLightTheme` |
| UIKit | `UITraitCollection.current.userInterfaceStyle` |
| Android | `UiModeManager.nightMode` |
| Web | `matchMedia('prefers-color-scheme: dark')` |
| X11 | No standard — always null (documented) |
| Wayland | `org.freedesktop.portal.Settings` — no-op, always null |