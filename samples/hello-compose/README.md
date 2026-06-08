# hello-compose

Jetpack Compose (Compose Multiplatform) hosted inside a native Kadre window.

## Purpose

Demonstrates Compose Multiplatform integration with Kadre:
- Kadre window with `CAMetalLayer` backing
- Skiko rendering (Metal on macOS, OpenGL on Windows/Linux)
- Mouse/keyboard event forwarding to `ComposeScene`
- Coroutine/Flow API (`kadreApplication`) without `ApplicationHandler` boilerplate

## Platforms

- JVM (macOS arm64, Windows, Linux)

## Usage

```bash
# Launch the interactive app
./gradlew :samples:hello-compose:run

# Headless raster capture to PNG
./gradlew :samples:hello-compose:run --args="--capture output.png"

# Windowed Metal/GL capture to PNG
./gradlew :samples:hello-compose:run --args="--window-capture output.png"
```
