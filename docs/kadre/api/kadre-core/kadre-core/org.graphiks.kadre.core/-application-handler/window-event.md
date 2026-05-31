//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ApplicationHandler](index.md)/[windowEvent](window-event.md)

# windowEvent

[common]\
abstract fun [windowEvent](window-event.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md), windowId: [WindowId](../-window-id/index.md), event: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html))

Appelé lorsqu'un événement de fenêtre est reçu.

Obligatoire — aucune implémentation par défaut.

Les types d'événements de fenêtre seront définis dans GRA-123.

#### Parameters

common

| | |
|---|---|
| eventLoop | Boucle d'événements active. |
| windowId | Identifiant de la fenêtre ayant émis l'événement. |
| event | Événement reçu (type Any en attente de GRA-123). |