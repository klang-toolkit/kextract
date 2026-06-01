//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ControlFlow](index.md)

# ControlFlow

sealed class [ControlFlow](index.md)

Control of the event loop's execution flow.

Allows the application to dictate the waiting behavior between iterations of the event loop.

#### Inheritors

| |
|---|
| [Wait](-wait/index.md) |
| [Poll](-poll/index.md) |
| [WaitUntil](-wait-until/index.md) |

## Types

| Name | Summary |
|---|---|
| [Poll](-poll/index.md) | [common]<br>object [Poll](-poll/index.md) : [ControlFlow](index.md)<br>Returns immediately without waiting for an event. |
| [Wait](-wait/index.md) | [common]<br>object [Wait](-wait/index.md) : [ControlFlow](index.md)<br>Waits indefinitely until the next event. |
| [WaitUntil](-wait-until/index.md) | [common]<br>data class [WaitUntil](-wait-until/index.md)(val instant: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [ControlFlow](index.md)<br>Waits until a specific instant (in milliseconds since the Unix epoch) or until the next event. |