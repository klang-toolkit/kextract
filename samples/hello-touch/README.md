# hello-touch

Multi-touch handling shared between iOS and Android.

## Purpose

Demonstrates Kadre API convergence between iOS and Android via a `HelloTouchHandler` in `commonMain`. This module is a KMP library consumed by `samples/hello-touch-android` and iOS apps.

## Platforms

- Android
- iOS (iosArm64, iosSimulatorArm64)

## Usage

This module is a library, not a standalone application. Run the host app:

```bash
# Android
./gradlew :samples:hello-touch-android:installDebug

# iOS — use Xcode or Gradle run (if configured)
```

Touch events are logged to the console.
