//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[EventLoop](index.md)

# EventLoop

[common]\
expect class [EventLoop](index.md)

Entry point of the kadre event loop.

This class is declared with `expect`: each compilation target (JVM, iOS, etc.) must provide a corresponding `actual` implementation in its respective platform module.

Typical usage:

```kotlin
EventLoop().runApp(object : ApplicationHandler {
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) { /* ... */}
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) { /* ... */}
})
```

[android]\
actual class [EventLoop](index.md)

Android implementation of [EventLoop](index.md).

Temporary stub — the real implementation will be provided in a dedicated ticket.

[iosArm64]\
actual class [EventLoop](index.md)

iOS arm64 implementation of [EventLoop](index.md).

Temporary stub — the real implementation will be provided in a dedicated ticket.

[iosSimulatorArm64]\
actual class [EventLoop](index.md)

iOS Simulator arm64 implementation of [EventLoop](index.md).

Temporary stub — the real implementation will be provided in a dedicated ticket.

[iosX64]\
actual class [EventLoop](index.md)

iOS x64 implementation of [EventLoop](index.md).

Temporary stub — the real implementation will be provided in a dedicated ticket.

[js]\
actual class [EventLoop](index.md)

JS implementation of [EventLoop](index.md).

Temporary stub — the real implementation will be provided in ticket #24.

[jvm]\
actual class [EventLoop](index.md)

JVM implementation of [EventLoop](index.md).

Delegates to `org.graphiks.kadre.appkit.AppKitEventLoopKt.runApp` via reflection to avoid a direct kadre-core → kadre-appkit coupling. This delegation is resolved at runtime: kadre-appkit must be on the classpath.

[wasmJs]\
actual class [EventLoop](index.md)

wasmJs implementation of [EventLoop](index.md).

Temporary stub — the real implementation will be provided in ticket #24.

## Constructors

| | |
|---|---|
| [EventLoop](-event-loop.md) | [common]<br>expect constructor()<br>[android, iosArm64, iosSimulatorArm64, iosX64, js, jvm, wasmJs]<br>actual constructor() |

## Functions

| Name | Summary |
|---|---|
| [runApp](run-app.md) | [common, android, iosArm64, iosSimulatorArm64, iosX64, js, wasmJs]<br>[common]<br>expect fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))<br>[android]<br>actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))<br>[iosArm64, iosSimulatorArm64, iosX64, js, wasmJs]<br>actual fun [runApp](run-app.md)(handler: ApplicationHandler)<br>Starts the event loop and delegates callbacks to the provided handler.<br>[jvm]<br>actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))<br>Starts the AppKit event loop and delegates callbacks to the provided handler. |