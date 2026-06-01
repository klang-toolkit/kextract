//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[EventLoop](index.md)/[runApp](run-app.md)

# runApp

[common]\
expect fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))

Starts the event loop and delegates callbacks to the provided handler.

This method is blocking: it returns only once the loop has ended (via [ActiveEventLoop.exit](../-active-event-loop/exit.md) or closing all windows depending on the platform).

#### Parameters

common

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

android

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

iosArm64

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

iosSimulatorArm64

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

iosX64

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

js

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

jvm

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

wasmJs

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

[android, iosArm64, iosSimulatorArm64, iosX64]\
[android]\
actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))

[iosArm64, iosSimulatorArm64, iosX64]\
actual fun [runApp](run-app.md)(handler: ApplicationHandler)

Starts the event loop and delegates callbacks to the provided handler.

#### Parameters

common

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

android

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

iosArm64

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

iosSimulatorArm64

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

iosX64

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

js

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

jvm

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

wasmJs

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

[js, wasmJs]\
[js, wasmJs]\
actual fun [runApp](run-app.md)(handler: ApplicationHandler)

Starts the event loop and delegates callbacks to the provided handler.

#### Parameters

common

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

android

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

iosArm64

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

iosSimulatorArm64

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

iosX64

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

js

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

jvm

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

wasmJs

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

#### Throws

| | |
|---|---|
| [UnsupportedOperationException](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unsupported-operation-exception/index.html) | Always — complete implementation in #24. |
| [UnsupportedOperationException](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unsupported-operation-exception/index.html) | if kadre-appkit is not on the classpath. |
| [UnsupportedOperationException](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unsupported-operation-exception/index.html) | Always — complete implementation in #24. |

[jvm]\
actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))

Starts the AppKit event loop and delegates callbacks to the provided handler.

Blocking — returns only once the application closes.

#### Parameters

common

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

android

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

iosArm64

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

iosSimulatorArm64

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

iosX64

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

js

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

jvm

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

wasmJs

| | |
|---|---|
| handler | Handler for the application lifecycle and events. |

#### Throws

| | |
|---|---|
| [UnsupportedOperationException](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unsupported-operation-exception/index.html) | Always — complete implementation in #24. |
| [UnsupportedOperationException](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unsupported-operation-exception/index.html) | if kadre-appkit is not on the classpath. |
| [UnsupportedOperationException](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unsupported-operation-exception/index.html) | Always — complete implementation in #24. |