# hello-triangle-ios

iOS GPU capture (Metal) of the wgpu4k triangle — Kotlin/Native test.

## Purpose

Renders the RGB triangle into an offscreen texture via `CAMetalLayer`, reads back the framebuffer, and encodes as PNG with CoreGraphics. The iOS simulator provides the Metal backend.

## Platforms

- iOS (iosArm64, iosSimulatorArm64)

## Usage

```bash
# Run the test on the simulator
./gradlew :samples:hello-triangle-ios:iosSimulatorArm64Test
```
