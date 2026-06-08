# hello-window-web

Cross-platform web canvas window (JS + Wasm).

## Purpose

Demonstrates the Kadre API on browser targets:
- DOM canvas creation via the Kadre API
- DOM event logging (mouse, keyboard, resize, close)
- Same code on JS and WasmJS

## Platforms

- JS (browser)
- WasmJS (browser)

## Usage

```bash
# JS
./gradlew :samples:hello-window-web:jsBrowserDevelopmentRun

# Wasm
./gradlew :samples:hello-window-web:wasmJsBrowserDevelopmentRun
```
