//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ApplicationHandler](index.md)/[deviceEvent](device-event.md)

# deviceEvent

[common]\
open fun [deviceEvent](device-event.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md), deviceId: [DeviceId](../-device-id/index.md), event: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html))

Appelé lorsqu'un événement de périphérique d'entrée est reçu.

#### Parameters

common

| | |
|---|---|
| eventLoop | Boucle d'événements active. |
| deviceId | Identifiant du périphérique ayant émis l'événement. |
| event | Événement reçu (type Any en attente de GRA-123). |