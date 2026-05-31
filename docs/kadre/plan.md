# Kadre — Project Plan

> Status: **Draft for review**
> Author: Kadre team
> Last updated: 2026-05-27

---

## 1. Context

**Kadre** is a project to provide a [winit](https://github.com/rust-windowing/winit) equivalent (the reference Rust library for cross-platform windowing and event handling) **in pure Kotlin**.

The ultimate goal is to give Kotlin developers **low-level control** over the native window and the host system's compositor, **without AWT/Swing dependencies**, to enable integration of native 3D rendering engines (Metal, Vulkan, WebGPU) via native handles (`raw window handle`).

The primary motivation is to unlock **3D / GPU-intensive** use cases in the Kotlin Multiplatform ecosystem, which are today constrained by AWT (heavy event cycle, fragile GPU integration, no direct compositor access).

---

## 2. Vision

A KMP lib that:

- Exposes a **callback-driven API** inspired by winit (`ApplicationHandler`, `EventLoop`, `Window`).
- Gives access to **low-level native handles** (`NSView`, `UIView`, `android.view.Surface`) directly consumable by a 3D renderer.
- Has **no dependency** on AWT or Swing.
- Follows idiomatic Kotlin conventions (sealed interfaces, coroutines for async operations, null-safety).
- Remains **stable and publishable** on Maven Central via the repo's existing conventions.

---

## 3. Goals and non-goals

### Goals

| Category | Goal |
|----------|------|
| Platforms (V1) | macOS Desktop, iOS, Android |
| Platforms (V2+) | Windows, Linux X11/Wayland |
| Network layer | No AWT/Swing/JavaFX dependency |
| 3D integration | `RawWindowHandle` contract compatible with wgpu4k |
| API | Inspired by winit, idiomatic Kotlin |
| Distribution | Publishable KMP artifact on Maven Central |

### Non-goals (V1)

- The **3D rendering itself** — delegated to wgpu4k or any renderer consuming a raw handle.
- **Web support (WebGPU/canvas)** — to be evaluated after V1.
- **Compose Multiplatform support** — Kadre is lower-level; Compose integration may come later if relevant.
- Multi-window in the initial POC (M1/M2).
- System accessibility (VoiceOver, TalkBack) — later phase.
- IME, advanced clipboard, drag & drop — later phase.

---

## 4. Stakeholders

| Role | Responsibility |
|------|---------------|
| PM / Tech Lead | Project management, spec validation |
| Kadre team | Core + backend implementation |
| kextract team | Finalizing Obj-C subclassing support, AppKit FFM bindings |
| wgpu4k team | Consuming raw handles on the 3D renderer side |
| Reviewers | Plan and spec validation in PR |

---

## 5. Functional scope

### Delivered modules (V1)

| Module | Role | KMP targets |
|--------|------|-------------|
| `kadre-core` | Interfaces, events, DPI types, raw handles | jvm, android, iosX64, iosArm64, iosSimulatorArm64 |
| `kadre-appkit` | macOS Desktop backend via kextract (FFM) | jvm |
| `kadre-uikit` | iOS backend via Kotlin/Native cinterop | iosX64, iosArm64, iosSimulatorArm64 |
| `kadre-android` | Android backend via Surface SDK | android |
| `kadre` (facade) | Public API, backend selection via `expect/actual` | all |
| `samples/hello-metal` | POC sample | jvm |

### Out-of-scope modules (V1)

- `kadre-win32` — Win32 via FFM (V2)
- `kadre-x11` — Xlib/xcb via FFM (V2)
- `kadre-wayland` — wl_compositor via FFM (V2)
- `kadre-web` — WebGPU/canvas (to be decided)

---

## 6. Milestones and deliverables

### Milestone M1 — POC: minimal Metal view

**Goal**: prove that the kextract binding stack + modular architecture can open a native window and expose an `NSView` ready for Metal.

**Deliverable**:
- Gradle modules created (`kadre-core`, `kadre-appkit`, `kadre`)
- A macOS window opens via `samples/hello-metal`
- The `contentView` is layer-backed (`wantsLayer = true`)
- The application closes cleanly (clicking the window X)

**Out of scope for M1**:
- No advanced event loop
- No keyboard/mouse input
- No resize handling
- No other backends (iOS, Android)

**Done definition**:
- `./gradlew :samples:hello-metal:run` opens an empty window.
- `nsView.layer != null` (verified via log).
- Closes without crash.

---

### Milestone M2 — wgpu4k demo

**Goal**: validate the **raw handle contract** with a real 3D renderer (wgpu4k) and demonstrate a basic render.

**Deliverable**:
- `wgpu4k` consumes the `RawWindowHandle.AppKit` exposed by Kadre.
- A simple scene (rotating triangle or cube) is rendered in the window.
- Resize triggers swap chain recreation (event `WindowEvent.Resized`).
- The event loop handles `CloseRequested` and `RedrawRequested`.

**Out of scope for M2**:
- Keyboard/mouse input (not needed for the demo)
- iOS/Android backends (still macOS only)
- Multi-window

**Done definition**:
- Demo runnable on Apple Silicon, stable 60 fps.
- Resize without crash, swap chain correctly reconfigured.
- Demo video recorded for communication.

---

### Milestone M3 — Target lib

**Goal**: publishable KMP library, multiplatform, integrable in third-party projects.

**Deliverable**:
- Complete backends for 3 platforms: macOS (`kadre-appkit`), iOS (`kadre-uikit`), Android (`kadre-android`).
- Stable and documented public API: `ApplicationHandler`, `EventLoop`, `Window`, complete events.
- Full lifecycle: `resumed`, `suspended`, `destroySurfaces` (Android).
- Multi-window supported (at least on Desktop).
- Input: keyboard, mouse (Desktop), touch (mobile), device events.
- Samples: `hello-window`, `hello-triangle` runnable on all 3 platforms.
- Dokka + MkDocs documentation.
- Maven Central publication via existing convention plugins (`kmp-library`, `kmp-publish`).

**Done definition**:
- Test suite passing in CI on all 3 targets.
- Artifact published to Maven Central with version `0.1.0`.
- API documentation accessible via the MkDocs site.

---

## 7. Success criteria

| Milestone | Measurable criterion |
|-----------|---------------------|
| M1 | NSWindow opened with visible layer-backed contentView. Clean close. |
| M2 | wgpu4k renders a basic scene at 60 fps. Resize does not crash. |
| M3 | Lib published to Maven Central. Samples runnable on 3 platforms. CI green. |

---

## 8. Risks and mitigations

| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|------------|
| Obj-C subclassing not finalized in kextract | Medium | Blocking M3 (and partially M1) | Close coordination with kextract team. Fallback: Obj-C shim compiled separately in C, bundled in the artifact. |
| Divergence between cinterop (iOS) and FFM (macOS) at the API level | High | Maintenance friction | Strict `kadre-core` contract in commonMain forcing convergence. Shared integration tests. |
| wgpu4k not ready to consume the handle for M2 | Low | M2 schedule slip | M1 remains demonstrable alone. M2 can switch to direct Metal if wgpu4k is delayed. |
| Complex iOS lifecycle (background, scene restoration) | Medium | Deferrable post-M3 | Cover only `resumed`/`suspended` in M3. The rest in V1.x. |
| JDK FFM compatibility (Panama API surface) | Low | Refactor at JDK 26+ | FFM is stable since JDK 22, JDK 25 LTS is safe. |
| Apple AppKit changes (macOS 26+) | Low | Bug surface | CI tests on macOS LTS only. |
| Multi-thread bugs (main thread enforcement) | Medium | Hard-to-debug crashes | Runtime asserts on `Thread.currentThread() == mainThread` at every public entry point. |

---

## 9. External dependencies

| Dependency | Target version | Status |
|------------|----------------|--------|
| **kextract** | Obj-C subclassing finalization | In progress |
| **wgpu4k** | Version consuming `RawWindowHandle` | To verify |
| JDK | 25 (LTS) | Available |
| Kotlin | 2.3.21 | Configured in repo |
| Gradle | 9.5.0 | Configured in repo |
| AGP | 9.0.0 | Configured in repo |

---

## 10. Indicative timeline

> Estimates to refine with the Linear backlog.

| Milestone | Duration | Target deadline |
|-----------|----------|----------------|
| M1 — Metal view POC | ~2 weeks | T0 + 2 weeks |
| M2 — wgpu4k demo | ~2 weeks | T0 + 4 weeks |
| M3 — Target lib V1 | ~10 weeks | T0 + 14 weeks |

T0 is contingent on the finalization of Obj-C subclassing in kextract.

---

## 11. Locked architecture decisions

Decisions locked during preparatory discussions, formalized in the [specs](./specs.md):

1. **iOS via Kotlin/Native + cinterop** (no kextract on iOS, no JVM).
2. **Android Strategy A**: raw `android.view.Surface` exposed, no custom JNI lib.
3. **macOS via JVM 25 + kextract FFM**.
4. **AppKit and UIKit in separate modules** (fundamentally different lifecycles).
5. **Obj-C subclassing** rather than method swizzling to intercept `NSApplication.sendEvent:`.

---

## 12. Appendices

### Glossary

| Term | Definition |
|------|-----------|
| **winit** | Reference Rust crate for cross-platform windowing (https://github.com/rust-windowing/winit). |
| **wgpu4k** | Kotlin port of wgpu, a low-level cross-platform 3D renderer. |
| **kextract** | Internal tool — jextract equivalent for Kotlin with Obj-C support, generates FFM bindings (JVM 22+). |
| **FFM** | Foreign Function & Memory API (JEP 454), standard JVM native interop since Java 22. |
| **cinterop** | Kotlin/Native tool for generating bindings to C/Obj-C libraries. |
| **Raw Window Handle** | Contract exposing native window handles (`NSView*`, `HWND`, etc.) to an external renderer. |
| **CAMetalLayer** | Core Animation layer supporting Metal on macOS/iOS. |

### Associated documents

- [Technical specifications](./specs.md)
- [Project README](../../README.md)
