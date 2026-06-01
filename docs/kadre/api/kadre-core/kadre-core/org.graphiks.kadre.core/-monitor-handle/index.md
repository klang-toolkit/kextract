//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[MonitorHandle](index.md)

# MonitorHandle

[common]\
interface [MonitorHandle](index.md)

Handle to a physical monitor.

Returned by [ActiveEventLoop.availableMonitors](../-active-event-loop/available-monitors.md) and [ActiveEventLoop.primaryMonitor](../-active-event-loop/primary-monitor.md). Concrete instances are created by each platform backend.

## Properties

| Name | Summary |
|---|---|
| [currentVideoMode](current-video-mode.md) | [common]<br>abstract val [currentVideoMode](current-video-mode.md): [VideoMode](../-video-mode/index.md)?<br>Current video mode of the monitor, or null if unavailable. |
| [id](id.md) | [common]<br>abstract val [id](id.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Platform-specific monitor identifier (HMONITOR, CGDirectDisplayID, XRROutput, etc.). |
| [name](name.md) | [common]<br>abstract val [name](name.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?<br>Human-readable monitor name (may be null if unavailable on the platform). |
| [position](position.md) | [common]<br>abstract val [position](position.md): [PhysicalPosition](../-physical-position/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;<br>Position of the top-left corner of the monitor in the virtual screen space,                       expressed in physical pixels. |
| [scaleFactor](scale-factor.md) | [common]<br>abstract val [scaleFactor](scale-factor.md): [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)<br>Scale factor between logical and physical pixels (e.g. 2.0 on a Retina display). |
| [videoModes](video-modes.md) | [common]<br>abstract val [videoModes](video-modes.md): [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[VideoMode](../-video-mode/index.md)&gt;<br>All video modes supported by the monitor (may be empty on some backends). |