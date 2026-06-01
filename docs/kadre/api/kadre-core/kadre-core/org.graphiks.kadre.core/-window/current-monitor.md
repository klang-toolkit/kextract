//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[currentMonitor](current-monitor.md)

# currentMonitor

[common]\
abstract fun [currentMonitor](current-monitor.md)(): [MonitorHandle](../-monitor-handle/index.md)?

Returns the monitor that currently contains the majority of the window, or null if the information is not available.

On mobile / web backends this always returns the single synthetic monitor.

#### Return

The [MonitorHandle](../-monitor-handle/index.md) for the window's current monitor, or null.