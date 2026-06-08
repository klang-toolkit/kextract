# pong

Pong game skeleton — 6-target Kadre application.

## Purpose

Demonstrates a complete Kadre application covering all platforms:
- `GameState` and `PongGame` in `commonMain` (game logic)
- `PongRenderer` in `jvmMain` (wgpu4k)
- Unit tests in `commonTest`
- Playwright visual tests (e2e)
- Game loop with AI, keyboard input, rendering

## Platforms

- JVM (macOS, Windows, Linux)
- Android
- iOS (iosX64, iosArm64, iosSimulatorArm64)
- JS (browser)
- WasmJS (browser)

## Usage

```bash
# JVM
./gradlew :samples:pong:run

# Web (JS)
./gradlew :samples:pong:jsBrowserDevelopmentRun

# Web (Wasm)
./gradlew :samples:pong:wasmJsBrowserDevelopmentRun

# Tests
./gradlew :samples:pong:jvmTest
```
