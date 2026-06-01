//[kadre](../../../index.md)/[org.graphiks.kadre](../index.md)/[EventLoop](index.md)/[runApp](run-app.md)

# runApp

[common, android, iosArm64, iosSimulatorArm64, iosX64]\
[common]\
expect fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))

[android]\
actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))

[iosArm64, iosSimulatorArm64, iosX64]\
actual fun [runApp](run-app.md)(handler: ApplicationHandler)

Starts the event loop and delegates callbacks to the provided handler.

This method is blocking — it only returns when the application closes.

#### Parameters

common

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

android

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

iosArm64

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

iosSimulatorArm64

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

iosX64

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

js

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

jvm

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

wasmJs

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

[js]\
actual fun [runApp](run-app.md)(handler: ApplicationHandler)

Starts the browser-side event loop (JS/IR).

#### Parameters

common

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

android

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

iosArm64

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

iosSimulatorArm64

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

iosX64

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

js

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

jvm

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

wasmJs

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

[jvm]\
actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))

Starts the event loop and delegates callbacks to the provided handler.

Blocking — only returns when the application closes.

#### Parameters

common

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

android

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

iosArm64

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

iosSimulatorArm64

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

iosX64

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

js

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

jvm

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

wasmJs

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

#### Throws

| | |
|---|---|
| [UnsupportedOperationException](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unsupported-operation-exception/index.html) | if the OS is not supported or if the corresponding backend cannot be found on the classpath. |

[wasmJs]\
actual fun [runApp](run-app.md)(handler: ApplicationHandler)

Starts the browser-side event loop (wasmJs).

#### Parameters

common

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

android

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

iosArm64

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

iosSimulatorArm64

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

iosX64

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

js

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

jvm

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |

wasmJs

| | |
|---|---|
| handler | Handler for the application's lifecycle and events. |