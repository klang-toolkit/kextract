//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[StartCause](index.md)

# StartCause

sealed class [StartCause](index.md)

Cause of the start or resumption of an event loop iteration.

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
| [Init](-init/index.md) | [common]<br>object [Init](-init/index.md) : [StartCause](index.md)<br>The event loop has just been initialized. |
| [Poll](-poll/index.md) | [common]<br>object [Poll](-poll/index.md) : [StartCause](index.md)<br>The event loop has been polled (Poll mode). |
| [ResumeTimeReached](-resume-time-reached/index.md) | [common]<br>data class [ResumeTimeReached](-resume-time-reached/index.md)(val requestedResume: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val start: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [StartCause](index.md)<br>The target wait instant has been reached. |
| [WaitCancelled](-wait-cancelled/index.md) | [common]<br>data class [WaitCancelled](-wait-cancelled/index.md)(val requestedResume: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)? = null) : [StartCause](index.md)<br>The wait was cancelled before the planned instant. |