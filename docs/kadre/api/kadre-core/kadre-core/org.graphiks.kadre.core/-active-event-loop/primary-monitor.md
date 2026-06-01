//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ActiveEventLoop](index.md)/[primaryMonitor](primary-monitor.md)

# primaryMonitor

[common]\
abstract fun [primaryMonitor](primary-monitor.md)(): [MonitorHandle](../-monitor-handle/index.md)?

Returns the primary monitor, or null if no primary monitor can be determined.

On desktop backends this is the monitor designated as &quot;primary&quot; by the OS. On mobile / web backends this is the main screen.

#### Return

The primary [MonitorHandle](../-monitor-handle/index.md), or null if unavailable.