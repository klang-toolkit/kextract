//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ApplicationHandler](index.md)/[destroySurfaces](destroy-surfaces.md)

# destroySurfaces

[common]\
open fun [destroySurfaces](destroy-surfaces.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))

Called when the platform requests the destruction of rendering surfaces.

This is the ideal moment to release the graphics resources tied to the surfaces before they are invalidated.

#### Parameters

common

| | |
|---|---|
| eventLoop | Active event loop. |