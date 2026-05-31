//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[StartCause](../index.md)/[WaitCancelled](index.md)

# WaitCancelled

data class [WaitCancelled](index.md)(val requestedResume: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)? = null) : [StartCause](../index.md)

L'attente a été annulée avant l'instant prévu.

#### Parameters

common

| | |
|---|---|
| requestedResume | Instant cible original, ou null s'il n'était pas défini. |

## Constructors

| | |
|---|---|
| [WaitCancelled](-wait-cancelled.md) | [common]<br>constructor(requestedResume: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [requestedResume](requested-resume.md) | [common]<br>val [requestedResume](requested-resume.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)? |