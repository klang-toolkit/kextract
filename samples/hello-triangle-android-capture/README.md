# hello-triangle-android-capture

Android GPU capture (Vulkan) of the wgpu4k triangle — instrumented test.

## Purpose

Renders the RGB triangle into an offscreen texture via an Android `Surface` (SurfaceTexture), reads back the framebuffer, and verifies the result. Uses Vulkan with SwiftShader (emulator).

## Platforms

- Android (minSdk 26, emulator with Vulkan)

## Usage

```bash
./gradlew :samples:hello-triangle-android-capture:connectedCheck
```
