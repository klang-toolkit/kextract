//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[EventLoopProxy](index.md)

# EventLoopProxy

[common]\
interface [EventLoopProxy](index.md)

Proxy thread-safe vers la boucle d'événements principale.

Permet à des fils d'exécution secondaires de réveiller la boucle d'événements sans y avoir accès directement.

## Functions

| Name | Summary |
|---|---|
| [wakeUp](wake-up.md) | [common]<br>abstract fun [wakeUp](wake-up.md)()<br>Réveille la boucle d'événements si elle est en attente. |