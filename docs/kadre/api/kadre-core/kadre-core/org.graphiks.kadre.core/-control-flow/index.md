//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ControlFlow](index.md)

# ControlFlow

sealed class [ControlFlow](index.md)

Contrôle du flux d'exécution de la boucle d'événements.

Permet à l'application de dicter le comportement d'attente entre les itérations de la boucle d'événements.

#### Inheritors

| |
|---|
| [Wait](-wait/index.md) |
| [Poll](-poll/index.md) |
| [WaitUntil](-wait-until/index.md) |

## Types

| Name | Summary |
|---|---|
| [Poll](-poll/index.md) | [common]<br>object [Poll](-poll/index.md) : [ControlFlow](index.md)<br>Retourne immédiatement sans attendre d'événement. |
| [Wait](-wait/index.md) | [common]<br>object [Wait](-wait/index.md) : [ControlFlow](index.md)<br>Attend indéfiniment jusqu'au prochain événement. |
| [WaitUntil](-wait-until/index.md) | [common]<br>data class [WaitUntil](-wait-until/index.md)(val instant: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [ControlFlow](index.md)<br>Attend jusqu'à un instant précis (en millisecondes depuis l'époque Unix) ou jusqu'au prochain événement. |