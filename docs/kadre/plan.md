# Kadre — Project Plan

> Status: **Canonical**
> Author: Kadre team
> Last updated: 2026-05-31

---

## 1. Context

Kadre **1.0.0** ships a stable Kotlin Multiplatform windowing API inspired by winit, with validated integration with wgpu4k (rendered triangle), published to Maven Central (`org.graphiks.kadre:kadre:1.0.0`).

The early macOS / iOS / Android foundation was reviewed and a set of remediation items (mkdocs branding, duplicated Android samples, README leftovers, post-mortem, demo video, etc.) was identified and resolved — see [sprint-review.md](./sprint-review.md).

**1.0.0 scope**:
1. Solidify the macOS / iOS / Android foundation and clear the gaps from the review.
2. Extend Kadre to **6 platforms** (adding Web, Windows, Linux) and deliver a technical **cross-platform Pong demo** as a proof point.

---

## 2. Vision

A KMP lib that:
- Exposes a callback-driven API inspired by winit.
- Gives access to low-level native handles directly consumable by a 3D renderer.
- **Has no dependency** on AWT/Swing.
- Runs on **all desktop + mobile + web platforms**: macOS, iOS, Android, Web (JS+WASM), Windows, Linux (X11+Wayland).

---

## 3. Goals and non-goals

### Goals

| Category | Goal |
|----------|------|
| Platforms | macOS, iOS, Android, **Web (JS+WASM)**, **Windows (Win32)**, **Linux (X11+Wayland)** |
| Cross-platform demo | Pong (1 player vs simple AI) running on **all 6 platforms** with the same commonMain code |
| Remediation | Gaps identified in the review resolved before release |
| Public API | Stable, with `RawWindowHandle.Web/Win32/Xlib/Wayland` variants |
| Distribution | 1.0.0 artifact published to Maven Central |

### Non-goals

- **Compose-on-Kadre**: evaluation POC **after** 1.0.0 (2-week R&D, see §11)
- **Audio, gamepad, ECS, asset loading**: out of ygdrasil scope (bindings only, per locked decision)
- **Pong with sound**: visual-only demo, no audio
- **Multiplayer Pong**: 1 player vs AI only
- **System accessibility**: deferred
- **IME, drag & drop, advanced clipboard**: deferred post-1.0.0

---

## 4. Stakeholders

| Role | Responsibility |
|------|---------------|
| PM / Tech Lead | Project management, spec validation |
| Kadre team | Foundation + 3 new backends + Pong implementation |
| kextract team | Win32 bindings (already supported), X11 (to confirm), Wayland (to confirm) |
| wgpu4k team | Web targets already available; consumers on the Pong side |
| Reviewers | Plan/spec validation in PR |

---

## 5. Functional scope

### Modules

| Module | Role |
|--------|------|
| `kadre-core` | RawWindowHandle variants (AppKit/UiKit/Android/Web/Win32/Xlib/Wayland) + KMP fundamentals |
| `kadre-appkit` | macOS backend (AppKit, FFM) |
| `kadre-uikit` | iOS backend (UIKit) |
| `kadre-android` | Android backend (SurfaceView) |
| `kadre` (facade) | Actuals for all platforms (macOS, iOS, Android, Web JS+WASM, Windows, Linux) |
| `samples/hello-window` | Shared `commonMain` cross-platform sample (JVM + iOS + Android) |
| `samples/hello-touch*` | Multi-touch samples |
| `kadre-web-common` | Shared Web abstractions (DOM events, pagehide/pageshow lifecycle) |
| `kadre-js` | Web backend Kotlin/JS |
| `kadre-wasm` | Web backend Kotlin/Wasm |
| `kadre-win32` | Windows backend via kextract FFM |
| `kadre-x11` | Linux X11 backend via kextract FFM |
| `kadre-wayland` | Linux Wayland backend via kextract FFM |
| `samples/pong` | Pong demo cross-6-platforms |

### Modules out of scope

- `koreaudio`, `koreassets`, `koreecs`, `koreinput`: not in ygdrasil (per "bindings only" decision)
- Compose-on-Kadre: POC after 1.0.0

---

## 6. Delivery scope and deliverables

### Foundation remediation

**Goal**: solidify the macOS / iOS / Android foundation by clearing the gaps from the review.

**Deliverables**:
- `mkdocs.yml` rebranded as Kadre (site_name, site_description, nav to `kadre/api/`)
- Android samples merged into KMP commonMain samples (`hello-window-android` and `hello-touch-android` become application entry points, not duplicates of HelloApp)
- `AndroidEventLoop.createWindow` returns a valid `AndroidWindow` (no longer throws `UnsupportedOperationException`)
- Root README updated (leftovers "Clean Architecture / DDD / Compose / Koin" → Kadre)
- M2 post-mortem: FPS metric `~60 fps` → `~120 fps` + demo video delivered
- Stale "stub" comment in `AppKitEventLoop.kt:35` updated
- `KadreApplication.eventLoop` refactored (scoped instance, no more mutable static variable)
- CI ios-build/android-build on PR feature branches (not just master push)
- E2E smoke test "at least one frame rendered" on hello-triangle (anti-regression for PR #25)

**Done definition**:
- All review gaps closed
- CHANGELOG.md updated
- Deployed doc site reflects Kadre branding

---

### Web JS+WASM backend

**Goal**: Kadre runs in the browser, validate the raw handle contract for WebGPU via wgpu4k.

**Deliverables**:
- `kadre-web-common` (commonMain for web targets): DOM abstractions, event mapping
- `kadre-js` (jsMain via Kotlin/JS): actual Canvas + DOM events backend
- `kadre-wasm` (wasmJsMain via Kotlin/Wasm): identical actual backend
- `RawWindowHandle.Web(canvasElementId: String)` variant in `kadre-core`
- `RawDisplayHandle.Web` variant in `kadre-core`
- Sample `hello-triangle-web`: triangle rendered via wgpu4k Web in an HTML canvas
- Sample `hello-window-web`: minimal cross-platform sample running in the browser
- CI: new `web-build` job (Node + KMP) + GitHub Pages publication of web samples
- Web documentation: section in specs.md + tutorial "Embed Kadre in a webpage"

**Out of scope here**:
- Pong (delivered last)
- Advanced mobile responsive
- PWA / offline

**Done definition**:
- `./gradlew :samples:hello-triangle-web:run` (or equivalent webpack-serve) opens the page, triangle rendered at stable 60 fps
- Same for Wasm
- Same WindowEvents dispatched as on Desktop (PointerMoved, MouseInput, KeyboardInput, Resized)
- Lifecycle: `visibilitychange` → consistent suspended/resumed

---

### Windows backend

**Goal**: Kadre runs on Windows desktop with Direct/Metal rendering via wgpu4k.

**Deliverables**:
- `kadre-win32` (jvm + kextract FFM): KadreWindow Win32, Win32 ALooper (CreateWindowExW, message pump GetMessage/DispatchMessage)
- Custom `WndProc` to intercept WM_PAINT, WM_SIZE, WM_KEYDOWN, WM_MOUSEMOVE, WM_DESTROY, etc.
- `RawWindowHandle.Win32(hwnd: Long, hinstance: Long)` variant in `kadre-core` (already spec'd, to activate)
- `RawDisplayHandle.Win32(hinstance: Long)`
- Sample `hello-triangle` running on Windows (recompile, commonMain code unchanged)
- CI: new `windows-build` job on `windows-latest`
- Windows documentation: section in specs.md

**Done definition**:
- `./gradlew :samples:hello-triangle:run` on Windows 10/11 → triangle rendered
- Correct DPI scaling (PerMonitorV2)
- Keyboard/mouse/resize dispatched consistently with macOS

---

### Linux X11 + Wayland backend

**Goal**: Kadre runs on Linux, supporting both compositors (X11 legacy + modern Wayland).

**Deliverables**:
- `kadre-x11` (jvm + kextract FFM Xlib): XOpenDisplay, XCreateWindow, XSelectInput, event loop XNextEvent
- `kadre-wayland` (jvm + kextract FFM libwayland-client): wl_display_connect, wl_registry, wl_compositor, xdg_shell for top-level windows
- `RawWindowHandle.Xlib(window: Long, display: Long)` and `Wayland(surface: Long, display: Long)` variants
- `RawDisplayHandle.Xlib` and `Wayland` variants
- Runtime detection at startup: try Wayland, fall back to X11 (via `XDG_SESSION_TYPE` or connection attempt)
- Sample `hello-triangle` running on Linux X11 + Linux Wayland (recompile, commonMain code unchanged)
- CI: new `linux-build` job on `ubuntu-latest` with Xvfb for X11, headless weston for Wayland (smoke only)
- Linux documentation: section in specs.md

**Done definition**:
- Sample runs on Ubuntu 24.04 (Wayland) and Debian 12 (X11)
- Automatic detection works, no manual configuration required from the user
- Keyboard/mouse dispatched consistently with macOS/Windows

---

### Cross-6-platform Pong + 1.0.0 release

**Goal**: technical demo showing the same Kotlin code running on 6 platforms.

**Deliverables**:
- `samples/pong`: KMP module with targets jvm, androidTarget, iosX64/Arm64/SimArm64, jsBrowser, wasmJsBrowser, jvm-windows, jvm-linux (all targets via existing facades)
- Pong logic in `commonMain`:
  - `PongGame : ApplicationHandler`
  - Right paddle controlled by `WindowEvent.KeyboardInput` (Desktop: arrow up/down) OR `WindowEvent.Touch` (mobile/web touch: right zone of screen tap to move)
  - Left paddle = simple AI (follows ball with a lag coefficient for difficulty)
  - Ball: simple 2D physics (bounces off paddles/top/bottom walls)
  - Score displayed at top (no audio)
  - Reset after score
- Rendering via wgpu4k: 5 colored quads (2 paddles + 1 ball + 2 score digits via primitives or hardcoded bitmap font)
- Frame timing: `requestRedraw` on each `aboutToWait`, 60 fps target
- Pause on `suspended` (mobile/web background)
- Build tasks per target
- Demo video recorded on all 6 platforms
- Documentation: "Multi-platform game loop pattern" section in the doc
- CHANGELOG 1.0.0 + git tag + Maven Central release

**Done definition**:
- Pong sample source code **strictly identical** (the same `PongGame.kt`) runs on all 6 platforms
- Demo videos recorded and attached to the GitHub release tag
- Lib 1.0.0 published to Maven Central with all modules (`kadre-core`, `kadre-appkit`, `kadre-uikit`, `kadre-android`, `kadre-js`, `kadre-wasm`, `kadre-win32`, `kadre-x11`, `kadre-wayland`, + facade `kadre`)
- CI green on 6 OS/runners

---

## 7. Success criteria

| Area | Measurable criterion |
|------|---------------------|
| Foundation | mkdocs branded Kadre, Android samples shared, createWindow functional on Android. |
| Web | Triangle rendered at stable 60 fps in Chrome/Firefox/Safari on JS and WASM. |
| Windows | Triangle rendered on Windows 10+11. Consistent keyboard/mouse input. |
| Linux | Triangle rendered on Ubuntu (Wayland) + Debian (X11) with auto-detection. |
| Pong | Pong identically playable on 6 platforms. 1.0.0 Maven Central release. |

---

## 8. Risks and mitigations

| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|------------|
| **Kotlin/Wasm still in alpha** | Medium | Unanticipated runtime bugs | Target the most stable Kotlin version when the Web backend starts. JS first as MVP, WASM next. |
| **kextract X11/Wayland untested** | High | Linux backend delayed | Smoke test kextract on Xlib early. Upstream coordination with kextract team. |
| **Wayland protocol versions** (xdg_shell, xdg_decoration) | Medium | Compositor compat | Target xdg_shell v3 + zxdg_decoration_v1 (minimum Mutter 3.32+, KWin 5.20+). Fallback: no custom decorations. |
| **Auto X11/Wayland detection** unreliable | Low | Degraded Linux UX | `KADRE_LINUX_BACKEND` environment variable for manual override. |
| **wgpu4k Web doesn't support all formats** (compute shaders, etc.) | Low | Sample web limitations | Pong = render-only, not affected. Monitor for future uses. |
| **Pong cross-platform: subtle input divergences** | High | Platform-specific behavior bugs | Manual integration tests per platform. Document acceptable divergences (e.g. tap vs arrow). |
| **CI Linux Wayland (headless weston)** unstable | Medium | Flaky CI tests | Smoke test only (build + 1 frame). No intensive runtime testing. |
| **Complex Windows DPI scaling** (PerMonitorV2 + multi-monitors) | Medium | HiDPI visual bugs | Test on multi-screen mixed scale once the Windows backend lands. |
| **API stability** under new variants | Low | User migration | No existing interface signature changes. Only add RawWindowHandle variants. |

---

## 9. External dependencies

| Dependency | Target version | Status |
|------------|----------------|--------|
| kextract Win32 | To confirm | Probably supported (FFM Win32 = standard path) |
| kextract X11 | To confirm | To investigate early |
| kextract Wayland | To confirm | To investigate early |
| **wgpu4k Web JS** | To align | **Available** (confirmed) |
| **wgpu4k Web WASM** | To align | **Available** (confirmed) |
| Kotlin | 2.3.21+ (aligned with stable Kotlin/Wasm) | Configured |
| JDK | 25 (LTS) | Configured |
| Node.js | LTS (for Web targets) | To add to CI |

---

## 10. Indicative timeline

| Phase | Duration |
|-------|----------|
| Foundation remediation | 2 weeks |
| Web JS+WASM | 4 weeks |
| Windows | 2 weeks |
| Linux X11+Wayland | 3 weeks |
| Pong + 1.0.0 release | 2 weeks |
| (Post-1.0.0) Compose-on-Kadre POC | 2 weeks |

Total to 1.0.0: **~13 weeks** of planned effort (delivered in ~24h of effective work in practice).

---

## 11. Locked decisions (summary)

| # | Decision |
|---|----------|
| D1 | **6 target platforms**: macOS, iOS, Android, Web (JS+WASM), Windows, Linux (X11+Wayland) |
| D2 | **New platform order**: Web → Windows → Linux |
| D3 | **Pong demo** coded once in commonMain, delivered at the end on all 6 platforms |
| D4 | **Pong format**: 1 player vs simple AI, no audio |
| D5 | **Remediation**: clear the review gaps before adding new platforms |
| D6 | **wgpu4k Web**: available, no upstream work needed |
| D7 | **Planning format**: full backlog + plan/specs docs |
| D8 | **Compose-on-Kadre**: 2-week evaluation POC **after** 1.0.0 |
| D9 | **ygdrasil model**: bindings only (no koreaudio, koreassets, koreecs) |
| D10 | **Community**: wgpu4k Discord (future rebrand), no dedicated ygdrasil Discord |
| D11 | **Hybrid JS/WASM strategy**: JS first (1-week MVP), WASM next (1.5 weeks) with shared `kadre-web-common` layer |
| D12 | **Linux X11/Wayland detection**: runtime auto-detection + `KADRE_LINUX_BACKEND` env var override |
| D13 | **JDK target = 25 (LTS)**. Conscious trade-off between adoption and modernity — see §13. |

---

## 13. JDK 25 decision — rationale

A reviewer legitimately raised the question of JDK 25 vs JDK 22/21 for broader adoption (FFM is stable since JDK 22, JDK 21 is LTS). The **JDK 25** decision is maintained, with the following arguments:

| Criterion | JDK 21 LTS | JDK 22 | **JDK 25 LTS** |
|---|---|---|---|
| FFM stable | preview | ✅ stable | ✅ stable |
| LTS status | LTS (until 2031) | non-LTS | **LTS (until 2033)** |
| Pattern matching switch | preview | ✅ stable | ✅ stable + improved |
| Virtual threads | ✅ stable | ✅ stable | ✅ stable + tuned |
| `Linker.upcallStub` API | preview | ✅ stable | ✅ stable + improved perf |
| Q3 2026 adoption | very wide | declining | growing |

**Why JDK 25**:

1. **Current LTS** — JDK 25 is the most recent LTS, supported until 2033 (Oracle/Eclipse Temurin). JDK 21 leaves "premium support" before 2030. Targeting the freshest LTS ensures Kadre users have a long-supported version.

2. **No Kadre dependency requires < JDK 25** — No partner imposing a lower version (to confirm if a consumer requests it).

3. **The ygdrasil ecosystem is new** — Kadre targets users building new projects (games/3D tools/Pong-like), not legacy migrations. These users are generally on the latest JDK.

4. **kextract generates modern FFM code** — The `Linker`, `Arena.ofShared`, `MemorySegment.reinterpret` APIs were polished post-JDK 22. Working on the most recent LTS avoids workarounds.

**Revision conditions**:

- If a strategic Kadre consumer (e.g., upstream Compose integration) requires JDK 21 → re-evaluate.
- If Kotlin/JVM drops the JDK 25 bytecode target → fallback JDK 22 (compromise: stable FFM + wider adoption).
- If > 30% of reported bugs mention "JDK too recent" → fallback JDK 22 (real adoption measurement).

**Fallback ready**: the project already uses Gradle `toolchain`; lowering the target from JDK 25 to JDK 22 is a minimal change (1 line in the `kmp-library` convention plugin). To document in `release-process.md`.

---

## 12. Appendices

### Glossary

| Term | Definition |
|------|-----------|
| **Wayland** | Modern Linux compositor protocol, X11 replacement. Stack: libwayland-client + xdg_shell. |
| **X11** | Historical Linux/Unix windowing protocol. Xlib (C) or xcb (more modern). |
| **WebGPU** | Modern GPU API for browsers, exposed via JS/WASM. Used by wgpu4k Web. |
| **Kotlin/Wasm** | Kotlin compilation target to WebAssembly. More performant than Kotlin/JS for GPU/compute code. |
| **xdg_shell** | Standard Wayland protocol for top-level windows (decorations, resize, fullscreen). |
| **PerMonitorV2** | Windows 10+ DPI awareness mode: each monitor has its own scale, managed by the app. |

### winit → Kadre mapping

| winit (Rust) | Kadre |
|--------------|-------|
| `RawWindowHandle::Web(WebHandle)` | `RawWindowHandle.Web(canvasElementId: String)` |
| `RawWindowHandle::Win32(Win32Handle)` | `RawWindowHandle.Win32(hwnd: Long, hinstance: Long)` |
| `RawWindowHandle::Xlib(XlibHandle)` | `RawWindowHandle.Xlib(window: Long, display: Long)` |
| `RawWindowHandle::Wayland(WaylandHandle)` | `RawWindowHandle.Wayland(surface: Long, display: Long)` |

### Associated documents

- [Technical specifications](./specs.md)
- [Sprint review](./sprint-review.md)
