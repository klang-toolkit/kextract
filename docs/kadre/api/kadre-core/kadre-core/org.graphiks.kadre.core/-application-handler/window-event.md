//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ApplicationHandler](index.md)/[windowEvent](window-event.md)

# windowEvent

[common]\
abstract fun [windowEvent](window-event.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md), windowId: [WindowId](../-window-id/index.md), event: [WindowEvent](../-window-event/index.md))

Called when a window event is received.

Mandatory — no default implementation.

#### Parameters

common

| | |
|---|---|
| eventLoop | Active event loop. |
| windowId | Identifier of the window that emitted the event. |
| event | Received window event. |