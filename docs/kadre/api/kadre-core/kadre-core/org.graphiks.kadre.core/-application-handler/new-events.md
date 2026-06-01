//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ApplicationHandler](index.md)/[newEvents](new-events.md)

# newEvents

[common]\
open fun [newEvents](new-events.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md), startCause: [StartCause](../-start-cause/index.md))

Called at the start of each event loop iteration, before the accumulated events are dispatched.

#### Parameters

common

| | |
|---|---|
| eventLoop | Active event loop. |
| startCause | Cause that triggered this new iteration. |