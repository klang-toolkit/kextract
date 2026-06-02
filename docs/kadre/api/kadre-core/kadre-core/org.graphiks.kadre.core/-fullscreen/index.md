//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Fullscreen](index.md)

# Fullscreen

sealed interface [Fullscreen](index.md)

Fullscreen mode requested via [Window.setFullscreen](../-window/set-fullscreen.md).

## Variants

-
   [Borderless](-borderless/index.md) — covers the monitor with a borderless window. Works on all backends.
-
   [Exclusive](-exclusive/index.md)  — requests exclusive fullscreen with a specific video mode. Only supported on AppKit, Win32 and X11 (backend-specific implementation). On Wayland, Web, Android and UIKit, passing [Exclusive](-exclusive/index.md) falls back to borderless and is documented as a no-op for the exclusive part.

#### Inheritors

| |
|---|
| [Borderless](-borderless/index.md) |
| [Exclusive](-exclusive/index.md) |

## Types

| Name | Summary |
|---|---|
| [Borderless](-borderless/index.md) | [common]<br>data class [Borderless](-borderless/index.md)(val monitor: [MonitorHandle](../-monitor-handle/index.md)? = null) : [Fullscreen](index.md)<br>Borderless (windowed) fullscreen: covers a monitor without changing its video mode. |
| [Exclusive](-exclusive/index.md) | [common]<br>data class [Exclusive](-exclusive/index.md)(val monitor: [MonitorHandle](../-monitor-handle/index.md), val videoMode: [VideoMode](../-video-mode/index.md)) : [Fullscreen](index.md)<br>Exclusive fullscreen: requests a mode change on the given monitor. |