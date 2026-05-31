//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[StartCause](index.md)

# StartCause

sealed class [StartCause](index.md)

Cause du démarrage ou de la reprise d'une itération de la boucle d'événements.

#### Inheritors

| |
|---|
| [Init](-init/index.md) |
| [Poll](-poll/index.md) |
| [WaitCancelled](-wait-cancelled/index.md) |
| [ResumeTimeReached](-resume-time-reached/index.md) |

## Types

| Name | Summary |
|---|---|
| [Init](-init/index.md) | [common]<br>object [Init](-init/index.md) : [StartCause](index.md)<br>La boucle d'événements vient d'être initialisée. |
| [Poll](-poll/index.md) | [common]<br>object [Poll](-poll/index.md) : [StartCause](index.md)<br>La boucle d'événements a été interrogée (mode Poll). |
| [ResumeTimeReached](-resume-time-reached/index.md) | [common]<br>data class [ResumeTimeReached](-resume-time-reached/index.md)(val requestedResume: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val start: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [StartCause](index.md)<br>L'instant cible d'attente a été atteint. |
| [WaitCancelled](-wait-cancelled/index.md) | [common]<br>data class [WaitCancelled](-wait-cancelled/index.md)(val requestedResume: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)? = null) : [StartCause](index.md)<br>L'attente a été annulée avant l'instant prévu. |