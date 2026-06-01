//[kadre](../../../index.md)/[org.graphiks.kadre](../index.md)/[EventLoop](index.md)

# EventLoop

[common]\
expect class [EventLoop](index.md)

Entry point of the kadre event loop.

Typical usage:

```kotlin
EventLoop().runApp(object : ApplicationHandler {
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        val window = eventLoop.createWindow(WindowAttributes(title = "Mon App"))
    }
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
        if (event is WindowEvent.CloseRequested) eventLoop.exit()
    }
})
```

[android, iosArm64, iosSimulatorArm64, iosX64]\
actual class [EventLoop](index.md)

Entry point of the kadre event loop.

Typical usage:

```kotlin
EventLoop().runApp(object : ApplicationHandler {
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        val window = eventLoop.createWindow(WindowAttributes(title = "Mon App"))
    }
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
        if (event is WindowEvent.CloseRequested) eventLoop.exit()
    }
})
```

[js]\
actual class [EventLoop](index.md)

JS implementation of [EventLoop](index.md) — delegates to org.graphiks.kadre.web.JsWebEventLoop.

[jvm]\
actual class [EventLoop](index.md)

JVM implementation of [EventLoop](index.md).

Routes to the appropriate backend via reflection:

- 
   macOS   → `org.graphiks.kadre.appkit.AppKitEventLoopKt#runApp`
- 
   Windows → `org.graphiks.kadre.win32.Win32EventLoopKt#runApp`
- 
   Linux   → X11 or Wayland according to LinuxBackendDetector

No direct import of the backend modules — loading is deferred to runtime to avoid initializing native bindings on the wrong OS.

[wasmJs]\
actual class [EventLoop](index.md)

wasmJs implementation of [EventLoop](index.md) — delegates to org.graphiks.kadre.web.WasmJsWebEventLoop.

## Constructors

| | |
|---|---|
| [EventLoop](-event-loop.md) | [common]<br>expect constructor()<br>[android, iosArm64, iosSimulatorArm64, iosX64, js, jvm, wasmJs]<br>actual constructor() |

## Functions

| Name | Summary |
|---|---|
| [runApp](run-app.md) | [common, android, iosArm64, iosSimulatorArm64, iosX64, jvm]<br>[common]<br>expect fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))<br>[android, jvm]<br>actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))<br>[iosArm64, iosSimulatorArm64, iosX64]<br>actual fun [runApp](run-app.md)(handler: ApplicationHandler)<br>Starts the event loop and delegates callbacks to the provided handler.<br>[js]<br>actual fun [runApp](run-app.md)(handler: ApplicationHandler)<br>Starts the browser-side event loop (JS/IR).<br>[wasmJs]<br>actual fun [runApp](run-app.md)(handler: ApplicationHandler)<br>Starts the browser-side event loop (wasmJs). |