//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ApplicationHandler](index.md)/[destroySurfaces](destroy-surfaces.md)

# destroySurfaces

[common]\
open fun [destroySurfaces](destroy-surfaces.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))

Appelé lorsque la plateforme demande la destruction des surfaces de rendu.

C'est le moment idéal pour libérer les ressources graphiques liées aux surfaces avant leur invalidation.

#### Parameters

common

| | |
|---|---|
| eventLoop | Boucle d'événements active. |