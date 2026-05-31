//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[EventLoop](index.md)/[runApp](run-app.md)

# runApp

[common]\
expect fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))

Démarre la boucle d'événements et délègue les rappels au gestionnaire fourni.

Cette méthode est bloquante : elle ne retourne qu'une fois la boucle terminée (via [ActiveEventLoop.exit](../-active-event-loop/exit.md) ou fermeture de toutes les fenêtres selon la plateforme).

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

[android, iosArm64, iosSimulatorArm64, iosX64]\
[android]\
actual fun [runApp](run-app.md)(handler: [ApplicationHandler](../-application-handler/index.md))

[iosArm64, iosSimulatorArm64, iosX64]\
actual fun [runApp](run-app.md)(handler: ApplicationHandler)

Démarre la boucle d'événements et délègue les rappels au gestionnaire fourni.

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

#### Throws

| | |
|---|---|
| [UnsupportedOperationException](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unsupported-operation-exception/index.html) | si kadre-appkit n'est pas sur le classpath. |