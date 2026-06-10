# hello-metal

Minimal Metal view (CAMetalLayer) demonstrator using Kadre.

## Purpose

Illustrates the Kadre stack end-to-end:
- `EventLoop` → `AppKitEventLoop` creation
- `NSWindow` with `CAMetalLayer` backing
- `RawWindowHandle.AppKit` retrieval (nsView, nsWindow)
- Lifecycle: creation, resize, close

This is the minimal foundation used by more advanced samples (`hello-triangle`, `compose/desktop`).

## Platforms

- JVM (macOS arm64)

## Usage

```bash
./gradlew :samples:hello-metal:run
```
