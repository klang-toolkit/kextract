//[kadre](../../../index.md)/[org.graphiks.kadre](../index.md)/[EventLoop](index.md)

# EventLoop

[common]\
expect class [EventLoop](index.md)

Point d'entrée de la boucle d'événements kadre.

Utilisation typique :

```kotlin
EventLoop().runApp(object : ApplicationHandler {
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        val window = eventLoop.createWindow(WindowAttributes(title = "Mon App"))
    }
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        if (event is WindowEvent.CloseRequested) eventLoop.exit()
    }
})
```

[android, iosArm64, iosSimulatorArm64, iosX64]\
actual class [EventLoop](index.md)

Point d'entrée de la boucle d'événements kadre.

Utilisation typique :

```kotlin
EventLoop().runApp(object : ApplicationHandler {
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        val window = eventLoop.createWindow(WindowAttributes(title = "Mon App"))
    }
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        if (event is WindowEvent.CloseRequested) eventLoop.exit()
    }
})
```

[jvm]\
actual class [EventLoop](index.md)

Implémentation JVM de [EventLoop](index.md).

Délègue directement à org.graphiks.kadre.appkit.runApp (kadre-appkit), sans indirection par réflexion.

## Constructors

| | |
|---|---|
| [EventLoop](-event-loop.md) | [common]<br>expect constructor()<br>[android, iosArm64, iosSimulatorArm64, iosX64, jvm]<br>actual constructor() |

## Functions

| Name | Summary |
|---|---|
| [runApp](run-app.md) | [common, android, iosArm64, iosSimulatorArm64, iosX64]<br>[common]<br>expect fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))<br>[android]<br>actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))<br>[iosArm64, iosSimulatorArm64, iosX64]<br>actual fun [runApp](run-app.md)(handler: ApplicationHandler)<br>Démarre la boucle d'événements et délègue les rappels au gestionnaire fourni.<br>[jvm]<br>actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))<br>Démarre la boucle d'événements AppKit et délègue les rappels au gestionnaire fourni. |