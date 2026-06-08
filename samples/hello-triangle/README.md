# hello-triangle

Animated RGB triangle rendered via wgpu4k (Metal/WGSL) in a Kadre window.

## Purpose

Demonstrates full wgpu4k + Kadre integration:
- wgpu4k `Instance` (Metal backend) from `CAMetalLayer`
- `Surface` → `Adapter` → `Device` → `Pipeline`
- Continuous render loop (~60 fps)
- Surface reconfiguration on resize
- GPU capture (offscreen + native screen)

## Platforms

- JVM (macOS arm64, Windows, Linux)

## Usage

```bash
# Launch the interactive app
./gradlew :samples:hello-triangle:run

# Offscreen GPU capture to PNG
./gradlew :samples:hello-triangle:run --args="--capture output.png"

# Native screen capture
./gradlew :samples:hello-triangle:run --args="--native-capture output.png"
```
