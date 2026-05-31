//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ActiveEventLoop](index.md)

# ActiveEventLoop

[common]\
interface [ActiveEventLoop](index.md)

Accès à la boucle d'événements depuis les rappels de [ApplicationHandler](../-application-handler/index.md).

Cette interface est passée en paramètre lors de chaque appel entrant dans le gestionnaire d'application, permettant à ce dernier de créer des fenêtres, de contrôler le flux d'exécution et d'initier l'arrêt de la boucle.

## Properties

| Name | Summary |
|---|---|
| [controlFlow](control-flow.md) | [common]<br>abstract val [controlFlow](control-flow.md): [ControlFlow](../-control-flow/index.md)<br>Retourne le comportement d'attente actuellement configuré. |
| [isExiting](is-exiting.md) | [common]<br>abstract val [isExiting](is-exiting.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Indique si une demande d'arrêt a été émise. |

## Functions

| Name | Summary |
|---|---|
| [createProxy](create-proxy.md) | [common]<br>abstract fun [createProxy](create-proxy.md)(): [EventLoopProxy](../-event-loop-proxy/index.md)<br>Crée un proxy thread-safe vers cette boucle d'événements. |
| [createWindow](create-window.md) | [common]<br>abstract fun [createWindow](create-window.md)(attributes: [WindowAttributes](../-window-attributes/index.md)): [Window](../-window/index.md)<br>Crée une nouvelle fenêtre avec les attributs spécifiés. |
| [exit](exit.md) | [common]<br>abstract fun [exit](exit.md)()<br>Demande l'arrêt de la boucle d'événements. |
| [setControlFlow](set-control-flow.md) | [common]<br>abstract fun [setControlFlow](set-control-flow.md)(controlFlow: [ControlFlow](../-control-flow/index.md))<br>Définit le comportement d'attente de la boucle d'événements après la fin de l'itération courante. |