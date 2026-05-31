# Kadre — Kotlin Multiplatform Windowing Library

[![Kotlin](https://img.shields.io/badge/Kotlin-2.3.21-purple?logo=kotlin)](https://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/Gradle-9.5.0-blue?logo=gradle)](https://gradle.org)
[![Java](https://img.shields.io/badge/Java-25-red?logo=openjdk)](https://openjdk.org)
[![CI](https://github.com/ygdrasil-io/poc-koreos/actions/workflows/ci.yml/badge.svg)](https://github.com/ygdrasil-io/poc-koreos/actions/workflows/ci.yml)
[![Maven Central](https://img.shields.io/maven-central/v/org.graphiks.kadre/kadre)](https://central.sonatype.com/artifact/org.graphiks.kadre/kadre)
[![M2 POC: Validated](https://img.shields.io/badge/M2%20POC-Validated%20%E2%9C%85-brightgreen?style=for-the-badge)](docs/kadre/postmortem-m2.md)
[![Projet: Incubating](https://img.shields.io/badge/Statut-Incubating-orange?style=for-the-badge)](https://github.com)

---

## Qu'est-ce que Kadre ?

**Kadre** est une bibliothèque Kotlin Multiplatform (KMP) de fenêtrage et de gestion d'event-loop. Elle expose une API callback-driven inspirée de [winit](https://github.com/rust-windowing/winit) et fournit un accès bas-niveau aux handles de fenêtre natifs, directement consommables par un renderer GPU (wgpu4k, Metal, etc.).

Kadre **ne dépend pas** d'AWT/Swing, JNA ni Rococoa. Les bindings natifs utilisent exclusivement **Panama FFM** (JDK 25) sur macOS/JVM.

**Version publiée** : `1.0.0` — macOS, iOS, Android, Windows, Linux, Web. Disponible sur [Maven Central](https://central.sonatype.com/artifact/org.graphiks.kadre/kadre).

---

## Démarrage rapide

```bash
# Prérequis : macOS + JDK 25
./gradlew :samples:hello-triangle:run
```

Ouvre une fenêtre macOS avec un triangle RGB rendu via wgpu4k/Metal. Redimensionnable. Fermeture propre via Cmd+W.

```kotlin
// build.gradle.kts
dependencies {
    implementation("org.graphiks.kadre:kadre:1.0.0")
}
```

---

## Architecture du projet

### Modules de la bibliothèque

| Module | Rôle | Plateformes |
|--------|------|-------------|
| `kadre-core` | Interfaces pures KMP : `EventLoop`, `Window`, `ApplicationHandler`, `WindowEvent`, `DeviceEvent` | jvm, iosX64, iosArm64, iosSimulatorArm64, android |
| `kadre-appkit` | Backend macOS via Panama FFM (NSWindow, NSView, CFRunLoop, CAMetalLayer) | jvm (macOS) |
| `kadre-uikit` | Backend iOS via Kotlin/Native cinterop (UIWindow, UIView, CAMetalLayer) | iosX64, iosArm64, iosSimulatorArm64 |
| `kadre-android` | Backend Android (SurfaceView, Choreographer, API 24+) | android |
| `kadre` | Facade publique KMP — `expect`/`actual` reliant les backends | jvm, iosX64, iosArm64, iosSimulatorArm64, android |

### Samples

| Sample | Description | Plateformes |
|--------|-------------|-------------|
| `samples/hello-triangle` | Triangle RGB via wgpu4k (Metal, WGSL) | macOS/JVM |
| `samples/hello-metal` | NSWindow + CAMetalLayer bare | macOS/JVM |
| `samples/hello-window` | Fenêtre partagée `commonMain` | JVM, iOS, Android |
| `samples/hello-window-android` | APK hello-window | Android |
| `samples/hello-touch` | Gestion multi-touch | iOS |
| `samples/hello-touch-android` | Gestion multi-touch | Android |

---

## Stack technique

| Couche | Technologie |
|--------|-------------|
| Fenêtrage macOS | AppKit (NSWindow, NSView) via Panama FFM |
| Fenêtrage iOS | UIKit (UIWindow, UIView) via Kotlin/Native cinterop |
| Fenêtrage Android | SurfaceView + Choreographer (API 24+) |
| Event loop macOS | CFRunLoop (kCFRunLoopBeforeWaiting observer) |
| GPU (samples) | wgpu4k 0.1.1 (Metal backend) |
| Shaders (samples) | WGSL inline |
| JDK (macOS) | 25 (`--enable-native-access=ALL-UNNAMED`) |

---

## CI/CD

Le pipeline GitHub Actions (`.github/workflows/ci.yml`) utilise une stratégie à quatre jobs :

| Job | Runner | Déclencheur | Tâches |
|-----|--------|-------------|--------|
| `build-and-test` | macos-15 | toutes branches | Fast-Track : JVM tests ; Deep : + iosSimulatorArm64 |
| `macos-build` | macos-latest | toutes branches | AppKit + samples macOS |
| `ios-build` | macos-15 | master / PR→master | Compilation + tests iOS simulator |
| `android-build` | ubuntu-latest | master / PR→master | Modules Android + APK samples |

---

## Commandes utiles

```bash
# Tests rapides (JVM, ~3 min)
./gradlew :kadre-core:jvmTest :kadre-appkit:jvmTest :kadre:jvmTest

# Tous les tests (JVM + iOS simulator, ~10 min)
./gradlew :kadre-core:jvmTest :kadre-core:iosSimulatorArm64Test \
          :kadre-appkit:jvmTest :kadre:jvmTest :kadre:iosSimulatorArm64Test

# Demos macOS
./gradlew :samples:hello-triangle:run
./gradlew :samples:hello-window:run
```

---

## Documentation

### Tutoriels

- [Intégrer Kadre dans une application Windows](docs/kadre/tutorials/windows-app.md) — fenêtre Win32, DPI PerMonitorV2, événements souris/clavier, packaging
- [Intégrer Kadre dans une page web](docs/kadre/tutorials/web-embed.md) — canvas HTML, Kotlin/JS + Kotlin/Wasm, boucle requestAnimationFrame

### Références

- [Specs](docs/kadre/specs.md) — contrats d'interface
- [Plan de développement](docs/kadre/plan.md) — vision, périmètre, risques
- [Post-mortem M2](docs/kadre/postmortem-m2.md) — bilan technique, apprentissages, metriques
- [Release process](docs/kadre/release-process.md) — publication Maven Central
- [Stabilité de l'API](docs/kadre/api-stability.md) · [Performance](docs/kadre/performance.md) · [Tests](docs/kadre/testing.md) · [Tests visuels](docs/kadre/visual-testing.md)
