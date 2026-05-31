//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[ControlFlow](../index.md)/[WaitUntil](index.md)

# WaitUntil

data class [WaitUntil](index.md)(val instant: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [ControlFlow](../index.md)

Attend jusqu'à un instant précis (en millisecondes depuis l'époque Unix) ou jusqu'au prochain événement.

#### Parameters

common

| | |
|---|---|
| instant | Instant cible exprimé en millisecondes depuis l'époque Unix. |

## Constructors

| | |
|---|---|
| [WaitUntil](-wait-until.md) | [common]<br>constructor(instant: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [instant](instant.md) | [common]<br>val [instant](instant.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html) |