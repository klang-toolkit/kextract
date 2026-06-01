//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[Fullscreen](../index.md)/[Exclusive](index.md)

# Exclusive

data class [Exclusive](index.md)(val monitor: [MonitorHandle](../../-monitor-handle/index.md), val videoMode: [VideoMode](../../-video-mode/index.md)) : [Fullscreen](../index.md)

Exclusive fullscreen: requests a mode change on the given monitor.

Not supported on Wayland, Web, Android and UIKit — those backends treat [Exclusive](index.md) as [Borderless](../-borderless/index.md) and log a note about the fallback.

#### Parameters

common

| | |
|---|---|
| monitor | The monitor to go fullscreen on. |
| videoMode | The video mode to set. |

## Constructors

| | |
|---|---|
| [Exclusive](-exclusive.md) | [common]<br>constructor(monitor: [MonitorHandle](../../-monitor-handle/index.md), videoMode: [VideoMode](../../-video-mode/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [monitor](monitor.md) | [common]<br>val [monitor](monitor.md): [MonitorHandle](../../-monitor-handle/index.md) |
| [videoMode](video-mode.md) | [common]<br>val [videoMode](video-mode.md): [VideoMode](../../-video-mode/index.md) |