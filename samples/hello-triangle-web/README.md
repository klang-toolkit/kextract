# hello-triangle-web

RGB triangle rendered via wgpu4k Web (WebGPU) in a browser canvas.

## Purpose

Demonstrates wgpu4k on web targets:
- DOM canvas creation via Kadre API (`RawWindowHandle.Web`)
- wgpu4k `CanvasSurface` → `Adapter` → `Device` → `Pipeline`
- `devicePixelRatio` handling for Retina displays
- WGSL shaders reused from the desktop sample

## Platforms

- JS (browser)
- WasmJS (browser)

## Usage

```bash
# JS
./gradlew :samples:hello-triangle-web:jsBrowserDevelopmentRun

# Wasm
./gradlew :samples:hello-triangle-web:wasmJsBrowserDevelopmentRun
```
