//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ApplicationHandler](index.md)/[canCreateSurfaces](can-create-surfaces.md)

# canCreateSurfaces

[common]\
abstract fun [canCreateSurfaces](can-create-surfaces.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))

Appelé lorsque la plateforme autorise la création de surfaces de rendu.

Obligatoire — aucune implémentation par défaut.

C'est le moment idéal pour créer les fenêtres et initialiser le pipeline de rendu.

#### Parameters

common

| | |
|---|---|
| eventLoop | Boucle d'événements active, permettant de créer des fenêtres. |