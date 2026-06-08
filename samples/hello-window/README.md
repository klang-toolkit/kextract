# hello-window

Cross-platform native window — shared handler in `commonMain`.

## Purpose

Demonstrates the core Kadre API:
- Window creation
- Full lifecycle (`canCreateSurfaces`, `resumed`, `suspended`, `destroySurfaces`)
- Keyboard, mouse, touch, resize, focus, and close events
- Same source code (`HelloApp.kt`) runs on JVM, iOS, and Android

## Platforms

- JVM (macOS, Windows, Linux)
- iOS (iosX64, iosArm64, iosSimulatorArm64)
- Android (via `samples/hello-window-android`)

## Usage

```bash
# JVM
./gradlew :samples:hello-window:run

# Android
./gradlew :samples:hello-window-android:installDebug
```
