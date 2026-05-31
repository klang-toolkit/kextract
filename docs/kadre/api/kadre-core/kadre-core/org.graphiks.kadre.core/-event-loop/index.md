//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[EventLoop](index.md)

# EventLoop

[common]\
expect class [EventLoop](index.md)

Point d'entrée de la boucle d'événements kadre.

Cette classe est déclarée avec `expect` : chaque cible de compilation (JVM, iOS, etc.) doit fournir une implémentation `actual` correspondante dans son module de plateforme respectif.

Utilisation typique :

```kotlin
EventLoop().runApp(object : ApplicationHandler {
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) { /* ... */}
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) { /* ... */}
})
```

[android]\
actual class [EventLoop](index.md)

Implémentation Android de [EventLoop](index.md).

Stub provisoire — l'implémentation réelle sera apportée dans un ticket dédié.

[iosArm64]\
actual class [EventLoop](index.md)

Implémentation iOS arm64 de [EventLoop](index.md).

Stub provisoire — l'implémentation réelle sera apportée dans un ticket dédié.

[iosSimulatorArm64]\
actual class [EventLoop](index.md)

Implémentation iOS Simulator arm64 de [EventLoop](index.md).

Stub provisoire — l'implémentation réelle sera apportée dans un ticket dédié.

[iosX64]\
actual class [EventLoop](index.md)

Implémentation iOS x64 de [EventLoop](index.md).

Stub provisoire — l'implémentation réelle sera apportée dans un ticket dédié.

[jvm]\
actual class [EventLoop](index.md)

Implémentation JVM de [EventLoop](index.md).

Délègue à `org.graphiks.kadre.appkit.AppKitEventLoopKt.runApp` via réflexion pour éviter un couplage direct de kadre-core → kadre-appkit. Cette délégation est résolue à l'exécution : kadre-appkit doit être sur le classpath.

## Constructors

| | |
|---|---|
| [EventLoop](-event-loop.md) | [common]<br>expect constructor()<br>[android, iosArm64, iosSimulatorArm64, iosX64, jvm]<br>actual constructor() |

## Functions

| Name | Summary |
|---|---|
| [runApp](run-app.md) | [common, android, iosArm64, iosSimulatorArm64, iosX64]<br>[common]<br>expect fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))<br>[android]<br>actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))<br>[iosArm64, iosSimulatorArm64, iosX64]<br>actual fun [runApp](run-app.md)(handler: ApplicationHandler)<br>Démarre la boucle d'événements et délègue les rappels au gestionnaire fourni.<br>[jvm]<br>actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))<br>Démarre la boucle d'événements AppKit et délègue les rappels au gestionnaire fourni. |