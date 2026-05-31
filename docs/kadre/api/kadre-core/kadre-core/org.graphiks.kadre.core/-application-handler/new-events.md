//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ApplicationHandler](index.md)/[newEvents](new-events.md)

# newEvents

[common]\
open fun [newEvents](new-events.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md), startCause: [StartCause](../-start-cause/index.md))

Appelé au début de chaque itération de la boucle d'événements, avant la distribution des événements accumulés.

#### Parameters

common

| | |
|---|---|
| eventLoop | Boucle d'événements active. |
| startCause | Cause ayant déclenché cette nouvelle itération. |