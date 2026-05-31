# Welcome to Kadre Documentation

This site centralizes all technical documentation, architecture guidelines, and API references for **Kadre** — a cross-platform windowing & events library for Kotlin Multiplatform.

---

## Key Features

*   **Cross-platform Windowing**: Native window management targeting **macOS**, **Linux**, **Windows**, **Android**, **iOS**, and **WebAssembly**.
*   **Unified Event Model**: Consistent `WindowEvent` and `DeviceEvent` API across all platforms.
*   **Kotlin Multiplatform**: Pure Kotlin API — `expect`/`actual` with platform-specific native backends (AppKit, Win32, Wayland/X11, UIKit).
*   **wgpu4k Integration**: Compatible with `wgpu4k` for GPU rendering on all targets.
*   **API Documentation Engine**: Automated API doc generation via **Dokka v2** and rendering through **MkDocs Material**.

---

## Quick Start

### Generate API documentation locally
```bash
./gradlew dokkaGenerate
```

### Compile the MkDocs site locally
```bash
mkdocs build
```

### Run the hello-triangle sample
```bash
./gradlew :samples:hello-triangle:run
```
