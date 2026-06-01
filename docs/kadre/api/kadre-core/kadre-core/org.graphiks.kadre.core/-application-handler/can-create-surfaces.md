//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ApplicationHandler](index.md)/[canCreateSurfaces](can-create-surfaces.md)

# canCreateSurfaces

[common]\
abstract fun [canCreateSurfaces](can-create-surfaces.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))

Called when the platform allows the creation of rendering surfaces.

Mandatory — no default implementation.

This is the ideal moment to create windows and initialize the rendering pipeline.

#### Parameters

common

| | |
|---|---|
| eventLoop | Active event loop, allowing windows to be created. |