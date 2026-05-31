//[kadre](../../../index.md)/[org.graphiks.kadre](../index.md)/[EventLoop](index.md)/[runApp](run-app.md)

# runApp

[common, android, iosArm64, iosSimulatorArm64, iosX64]\
[common]\
expect fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))

[android]\
actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))

[iosArm64, iosSimulatorArm64, iosX64]\
actual fun [runApp](run-app.md)(handler: ApplicationHandler)

Démarre la boucle d'événements et délègue les rappels au gestionnaire fourni.

Cette méthode est bloquante — elle ne retourne qu'à la fermeture de l'application.

#### Parameters

common

| | |
|---|---|
| handler | Gestionnaire du cycle de vie et des événements de l'application. |

android

| | |
|---|---|
| handler | Gestionnaire du cycle de vie et des événements de l'application. |

iosArm64

| | |
|---|---|
| handler | Gestionnaire du cycle de vie et des événements de l'application. |

iosSimulatorArm64

| | |
|---|---|
| handler | Gestionnaire du cycle de vie et des événements de l'application. |

iosX64

| | |
|---|---|
| handler | Gestionnaire du cycle de vie et des événements de l'application. |

jvm

| | |
|---|---|
| handler | Gestionnaire du cycle de vie et des événements de l'application. |

[jvm]\
actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))

Démarre la boucle d'événements AppKit et délègue les rappels au gestionnaire fourni.

Bloquant — ne retourne qu'à la fermeture de l'application.

#### Parameters

common

| | |
|---|---|
| handler | Gestionnaire du cycle de vie et des événements de l'application. |

android

| | |
|---|---|
| handler | Gestionnaire du cycle de vie et des événements de l'application. |

iosArm64

| | |
|---|---|
| handler | Gestionnaire du cycle de vie et des événements de l'application. |

iosSimulatorArm64

| | |
|---|---|
| handler | Gestionnaire du cycle de vie et des événements de l'application. |

iosX64

| | |
|---|---|
| handler | Gestionnaire du cycle de vie et des événements de l'application. |

jvm

| | |
|---|---|
| handler | Gestionnaire du cycle de vie et des événements de l'application. |