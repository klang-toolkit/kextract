//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ActiveEventLoop](index.md)/[availableMonitors](available-monitors.md)

# availableMonitors

[common]\
abstract fun [availableMonitors](available-monitors.md)(): [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MonitorHandle](../-monitor-handle/index.md)&gt;

Returns all monitors currently connected to the system.

The list contains at least one entry on all backends when a display is available. On mobile / web backends, a single synthetic monitor representing the screen is returned.

#### Return

Immutable list of [MonitorHandle](../-monitor-handle/index.md) objects.