//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ApplicationHandler](index.md)

# ApplicationHandler

[common]\
interface [ApplicationHandler](index.md)

Handler for the application lifecycle and the loop's events.

The implementation of this interface is the business entry point of any kadre application. The event loop invokes the methods of this handler in response to system events and lifecycle state changes.

The [canCreateSurfaces](can-create-surfaces.md) and [windowEvent](window-event.md) methods are mandatory (no default implementation). All other methods have an empty default implementation and may be overridden as needed.

## Functions

| Name | Summary |
|---|---|
| [aboutToWait](about-to-wait.md) | [common]<br>open fun [aboutToWait](about-to-wait.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))<br>Called when all events of the current iteration have been dispatched and the loop is about to go into a waiting state. |
| [canCreateSurfaces](can-create-surfaces.md) | [common]<br>abstract fun [canCreateSurfaces](can-create-surfaces.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))<br>Called when the platform allows the creation of rendering surfaces. |
| [destroySurfaces](destroy-surfaces.md) | [common]<br>open fun [destroySurfaces](destroy-surfaces.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))<br>Called when the platform requests the destruction of rendering surfaces. |
| [deviceEvent](device-event.md) | [common]<br>open fun [deviceEvent](device-event.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md), deviceId: [DeviceId](../-device-id/index.md), event: [DeviceEvent](../-device-event/index.md))<br>Called when an input device event is received. |
| [memoryWarning](memory-warning.md) | [common]<br>open fun [memoryWarning](memory-warning.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))<br>Called when the system notifies the application of a low-memory condition. |
| [newEvents](new-events.md) | [common]<br>open fun [newEvents](new-events.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md), startCause: [StartCause](../-start-cause/index.md))<br>Called at the start of each event loop iteration, before the accumulated events are dispatched. |
| [resumed](resumed.md) | [common]<br>open fun [resumed](resumed.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))<br>Called when the application resumes execution after a suspension. |
| [suspended](suspended.md) | [common]<br>open fun [suspended](suspended.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))<br>Called when the application is about to be suspended. |
| [windowEvent](window-event.md) | [common]<br>abstract fun [windowEvent](window-event.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md), windowId: [WindowId](../-window-id/index.md), event: [WindowEvent](../-window-event/index.md))<br>Called when a window event is received. |