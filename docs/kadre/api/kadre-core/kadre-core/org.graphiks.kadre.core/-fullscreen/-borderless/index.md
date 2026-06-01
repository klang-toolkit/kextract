//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[Fullscreen](../index.md)/[Borderless](index.md)

# Borderless

data class [Borderless](index.md)(val monitor: [MonitorHandle](../../-monitor-handle/index.md)? = null) : [Fullscreen](../index.md)

Borderless (windowed) fullscreen: covers a monitor without changing its video mode.

#### Parameters

common

| | |
|---|---|
| monitor | Target monitor, or null to use the monitor that currently contains                 the window (or the primary monitor if the window has no screen). |

## Constructors

| | |
|---|---|
| [Borderless](-borderless.md) | [common]<br>constructor(monitor: [MonitorHandle](../../-monitor-handle/index.md)? = null) |

## Properties

| Name | Summary |
|---|---|
| [monitor](monitor.md) | [common]<br>val [monitor](monitor.md): [MonitorHandle](../../-monitor-handle/index.md)? |