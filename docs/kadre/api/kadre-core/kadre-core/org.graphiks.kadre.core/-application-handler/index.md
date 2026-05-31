//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ApplicationHandler](index.md)

# ApplicationHandler

[common]\
interface [ApplicationHandler](index.md)

Gestionnaire du cycle de vie de l'application et des événements de la boucle.

L'implémentation de cette interface constitue le point d'entrée métier de toute application kadre. La boucle d'événements invoque les méthodes de ce gestionnaire en réponse aux événements système et aux changements d'état du cycle de vie.

Les méthodes [canCreateSurfaces](can-create-surfaces.md) et [windowEvent](window-event.md) sont obligatoires (aucune implémentation par défaut). Toutes les autres méthodes disposent d'une implémentation par défaut vide et peuvent être surchargées au besoin.

## Functions

| Name | Summary |
|---|---|
| [aboutToWait](about-to-wait.md) | [common]<br>open fun [aboutToWait](about-to-wait.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))<br>Appelé lorsque tous les événements de l'itération courante ont été distribués et que la boucle est sur le point de se mettre en attente. |
| [canCreateSurfaces](can-create-surfaces.md) | [common]<br>abstract fun [canCreateSurfaces](can-create-surfaces.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))<br>Appelé lorsque la plateforme autorise la création de surfaces de rendu. |
| [destroySurfaces](destroy-surfaces.md) | [common]<br>open fun [destroySurfaces](destroy-surfaces.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))<br>Appelé lorsque la plateforme demande la destruction des surfaces de rendu. |
| [deviceEvent](device-event.md) | [common]<br>open fun [deviceEvent](device-event.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md), deviceId: [DeviceId](../-device-id/index.md), event: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html))<br>Appelé lorsqu'un événement de périphérique d'entrée est reçu. |
| [newEvents](new-events.md) | [common]<br>open fun [newEvents](new-events.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md), startCause: [StartCause](../-start-cause/index.md))<br>Appelé au début de chaque itération de la boucle d'événements, avant la distribution des événements accumulés. |
| [resumed](resumed.md) | [common]<br>open fun [resumed](resumed.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))<br>Appelé lorsque l'application reprend son exécution après une suspension. |
| [suspended](suspended.md) | [common]<br>open fun [suspended](suspended.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))<br>Appelé lorsque l'application est sur le point d'être suspendue. |
| [windowEvent](window-event.md) | [common]<br>abstract fun [windowEvent](window-event.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md), windowId: [WindowId](../-window-id/index.md), event: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html))<br>Appelé lorsqu'un événement de fenêtre est reçu. |