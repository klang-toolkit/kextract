//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ActiveEventLoop](index.md)

# ActiveEventLoop

[common]\
interface [ActiveEventLoop](index.md)

Access to the event loop from [ApplicationHandler](../-application-handler/index.md) callbacks.

This interface is passed as a parameter on each incoming call into the application handler, allowing the latter to create windows, control the execution flow, and initiate shutdown of the loop.

## Properties

| Name | Summary |
|---|---|
| [controlFlow](control-flow.md) | [common]<br>abstract val [controlFlow](control-flow.md): [ControlFlow](../-control-flow/index.md)<br>Returns the currently configured waiting behavior. |
| [isExiting](is-exiting.md) | [common]<br>abstract val [isExiting](is-exiting.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Indicates whether a shutdown request has been issued. |

## Functions

| Name | Summary |
|---|---|
| [availableMonitors](available-monitors.md) | [common]<br>abstract fun [availableMonitors](available-monitors.md)(): [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MonitorHandle](../-monitor-handle/index.md)&gt;<br>Returns all monitors currently connected to the system. |
| [createCustomCursor](create-custom-cursor.md) | [common]<br>open fun [createCustomCursor](create-custom-cursor.md)(image: [CursorImage](../-cursor-image/index.md)): [CustomCursor](../-custom-cursor/index.md)?<br>Creates a custom cursor from the provided RGBA pixel data. |
| [createProxy](create-proxy.md) | [common]<br>abstract fun [createProxy](create-proxy.md)(): [EventLoopProxy](../-event-loop-proxy/index.md)<br>Creates a thread-safe proxy to this event loop. |
| [createWindow](create-window.md) | [common]<br>abstract fun [createWindow](create-window.md)(attributes: [WindowAttributes](../-window-attributes/index.md)): [Window](../-window/index.md)<br>Creates a new window with the specified attributes. |
| [exit](exit.md) | [common]<br>abstract fun [exit](exit.md)()<br>Requests shutdown of the event loop. |
| [listenDeviceEvents](listen-device-events.md) | [common]<br>abstract fun [listenDeviceEvents](listen-device-events.md)(mode: [DeviceEvents](../-device-events/index.md))<br>Controls which raw [DeviceEvent](../-device-event/index.md)s are dispatched to [ApplicationHandler.deviceEvent](../-application-handler/device-event.md). |
| [primaryMonitor](primary-monitor.md) | [common]<br>abstract fun [primaryMonitor](primary-monitor.md)(): [MonitorHandle](../-monitor-handle/index.md)?<br>Returns the primary monitor, or null if no primary monitor can be determined. |
| [setControlFlow](set-control-flow.md) | [common]<br>abstract fun [setControlFlow](set-control-flow.md)(controlFlow: [ControlFlow](../-control-flow/index.md))<br>Sets the waiting behavior of the event loop after the end of the current iteration. |
| [systemTheme](system-theme.md) | [common]<br>abstract fun [systemTheme](system-theme.md)(): [Theme](../-theme/index.md)?<br>Returns the current system-wide UI theme, or null if the information is not available on this platform. |