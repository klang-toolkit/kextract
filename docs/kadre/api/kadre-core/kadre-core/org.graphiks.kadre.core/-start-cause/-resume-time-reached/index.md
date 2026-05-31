//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[StartCause](../index.md)/[ResumeTimeReached](index.md)

# ResumeTimeReached

data class [ResumeTimeReached](index.md)(val requestedResume: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val start: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [StartCause](../index.md)

L'instant cible d'attente a été atteint.

#### Parameters

common

| | |
|---|---|
| requestedResume | Instant cible original. |
| start | Instant auquel la reprise a effectivement eu lieu. |

## Constructors

| | |
|---|---|
| [ResumeTimeReached](-resume-time-reached.md) | [common]<br>constructor(requestedResume: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), start: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [requestedResume](requested-resume.md) | [common]<br>val [requestedResume](requested-resume.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html) |
| [start](start.md) | [common]<br>val [start](start.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html) |