# Koreos — Project Plan v0.2

> Status: **Draft for review**
> Author: Koreos team
> Last updated: 2026-05-29
> Previous document: [plan-v0.1](./plan.md)

---

## 1. Context

Koreos **v0.1.0** has shipped: 3 platforms (macOS, iOS, Android), stable API inspired by winit, validated integration with wgpu4k (rendered triangle), artifact published to Maven Central (`io.ygdrasil.koreos:0.1.0`).

The sprint review identified **9 minor gaps** (mkdocs branding, duplicated Android samples, README leftovers, post-mortem, M2 demo video, etc.) — see [sprint-review-v0.1.md](./sprint-review-v0.1.md) or the reference conversation.

**v0.2 goals**:
1. **v0.1.1** — fix the 9 gaps from the sprint review.
2. **v0.2.0** — extend Koreos to **6 platforms** (adding Web, Windows, Linux) and deliver a technical **cross-platform Pong demo** as a proof point.

---

## 2. Vision (unchanged, extended)

A KMP lib that:
- Exposes a callback-driven API inspired by winit.
- Gives access to low-level native handles directly consumable by a 3D renderer.
- **Has no dependency** on AWT/Swing.
- Runs on **all desktop + mobile + web platforms**: macOS, iOS, Android, Web (JS+WASM), Windows, Linux (X11+Wayland).

---

## 3. Goals and non-goals

### v0.2 goals

| Category | Goal |
|----------|------|
| Platforms (V0.2) | macOS, iOS, Android, **Web (JS+WASM)**, **Windows (Win32)**, **Linux (X11+Wayland)** |
| Cross-platform demo | Pong (1 player vs simple AI) running on **all 6 platforms** with the same commonMain code |
| Remediation | 9 gaps identified in sprint review fixed in v0.1.1 |
| Public API | Stable, v0.1.x backward-compatible (addition of `RawWindowHandle.Web/Win32/Xlib/Wayland` variants) |
| Distribution | v0.2.0 artifact published to Maven Central |

### v0.2 non-goals

- **Compose-on-Koreos**: evaluation POC **after** v0.2.0 (2-week R&D, see §11)
- **Audio, gamepad, ECS, asset loading**: out of ygdrasil scope (bindings only, per locked decision)
- **Pong with sound**: visual-only demo, no audio
- **Multiplayer Pong**: 1 player vs AI only
- **System accessibility**: deferred
- **IME, drag & drop, advanced clipboard**: deferred post-v0.2

---

## 4. Stakeholders

| Role | Responsibility |
|------|---------------|
| PM / Tech Lead | Project management, spec validation |
| Koreos team | Remediation + 3 new backends + Pong implementation |
| kextract team | Win32 bindings (already supported), X11 (to confirm), Wayland (to confirm) |
| wgpu4k team | Web targets already available; consumers on the Pong side |
| Reviewers | Plan/spec validation in PR |

---

## 5. v0.2 functional scope

### Delivered modules

| Module | v0.1 state | v0.2 target state |
|--------|-----------|-------------------|
| `koreos-core` | Delivered | Add RawWindowHandle variants (Web/Win32/Xlib/Wayland), fundamentals unchanged |
| `koreos-appkit` | Delivered | Unchanged (minor remediation if applicable) |
| `koreos-uikit` | Delivered | Unchanged |
| `koreos-android` | Delivered | Remediation: `AndroidEventLoop.createWindow` must no longer throw |
| `koreos` (facade) | Delivered | Add actuals for Web (JS+WASM), Windows, Linux |
| `samples/hello-window` | Delivered | Refactor: shared `commonMain` code actually used on Android (merge `hello-window-android`) |
| `samples/hello-touch*` | Delivered | Same refactor |
| **`koreos-web-common`** | — | **New**: shared Web abstractions (DOM events, pagehide/pageshow lifecycle) |
| **`koreos-js`** | — | **New**: Web backend Kotlin/JS |
| **`koreos-wasm`** | — | **New**: Web backend Kotlin/Wasm |
| **`koreos-win32`** | — | **New**: Windows backend via kextract FFM |
| **`koreos-x11`** | — | **New**: Linux X11 backend via kextract FFM |
| **`koreos-wayland`** | — | **New**: Linux Wayland backend via kextract FFM |
| **`samples/pong`** | — | **New**: Pong demo cross-6-platforms |

### Modules out of scope for v0.2

- `koreaudio`, `koreassets`, `koreecs`, `koreinput`: not in ygdrasil (per "bindings only" decision)
- Compose-on-Koreos: POC after v0.2.0

---

## 6. Milestones and deliverables

### Sprint 0 — v0.1.1 remediation (2 weeks)

**Goal**: clean up the gaps identified in the v0.1 sprint review, deliver a clean v0.1.1 for external users.

**Deliverables**:
- `mkdocs.yml` rebranded as Koreos (site_name, site_description, nav to `koreos/api/`)
- Android samples merged into KMP commonMain samples (`hello-window-android` and `hello-touch-android` become application entry points, not duplicates of HelloApp)
- `AndroidEventLoop.createWindow` returns a valid `AndroidWindow` (no longer throws `UnsupportedOperationException`)
- Root README updated (leftovers "Clean Architecture / DDD / Compose / Koin" → Koreos)
- M2 post-mortem: FPS metric `~60 fps` → `~120 fps` + demo video delivered
- Stale "stub" comment in `AppKitEventLoop.kt:35` updated
- `KoreosApplication.eventLoop` refactored (scoped instance, no more mutable static variable)
- CI ios-build/android-build on PR feature branches (not just master push)
- E2E smoke test "at least one frame rendered" on hello-triangle (anti-regression PR #25-bis)

**Done definition**:
- Tag `v0.1.1` created on master
- v0.1.1 artifact published to Maven Central
- CHANGELOG.md updated
- Deployed doc site reflects Koreos branding

---

### Sprint 1-2 — Web JS+WASM backend (1 month)

**Goal**: Koreos runs in the browser, validate the raw handle contract for WebGPU via wgpu4k.

**Deliverables**:
- `koreos-web-common` (commonMain for web targets): DOM abstractions, event mapping
- `koreos-js` (jsMain via Kotlin/JS): actual Canvas + DOM events backend
- `koreos-wasm` (wasmJsMain via Kotlin/Wasm): identical actual backend
- `RawWindowHandle.Web(canvasElementId: String)` variant in `koreos-core`
- `RawDisplayHandle.Web` variant in `koreos-core`
- Sample `hello-triangle-web`: triangle rendered via wgpu4k Web in an HTML canvas
- Sample `hello-window-web`: minimal cross-platform sample running in the browser
- CI: new `web-build` job (Node + KMP) + GitHub Pages publication of web samples
- Web documentation: section in specs.md + tutorial "Embed Koreos in a webpage"

**Out of scope for Sprint 1-2**:
- Pong (deferred to Sprint 5)
- Advanced mobile responsive
- PWA / offline

**Done definition**:
- `./gradlew :samples:hello-triangle-web:run` (or equivalent webpack-serve) opens the page, triangle rendered at stable 60 fps
- Same for Wasm
- Same WindowEvents dispatched as on Desktop (PointerMoved, MouseInput, KeyboardInput, Resized)
- Lifecycle: `visibilitychange` → consistent suspended/resumed

---

### Sprint 3 — Windows backend (2 weeks)

**Goal**: Koreos runs on Windows desktop with Direct/Metal rendering via wgpu4k.

**Deliverables**:
- `koreos-win32` (jvm + kextract FFM): KoreosWindow Win32, Win32 ALooper (CreateWindowExW, message pump GetMessage/DispatchMessage)
- Custom `WndProc` to intercept WM_PAINT, WM_SIZE, WM_KEYDOWN, WM_MOUSEMOVE, WM_DESTROY, etc.
- `RawWindowHandle.Win32(hwnd: Long, hinstance: Long)` variant in `koreos-core` (already spec'd, to activate)
- `RawDisplayHandle.Win32(hinstance: Long)`
- Sample `hello-triangle` running on Windows (recompile, commonMain code unchanged)
- CI: new `windows-build` job on `windows-latest`
- Windows documentation: section in specs.md

**Done definition**:
- `./gradlew :samples:hello-triangle:run` on Windows 10/11 → triangle rendered
- Correct DPI scaling (PerMonitorV2)
- Keyboard/mouse/resize dispatched consistently with macOS

---

### Sprint 4 — Linux X11 + Wayland backend (3 weeks)

**Goal**: Koreos runs on Linux, supporting both compositors (X11 legacy + modern Wayland).

**Deliverables**:
- `koreos-x11` (jvm + kextract FFM Xlib): XOpenDisplay, XCreateWindow, XSelectInput, event loop XNextEvent
- `koreos-wayland` (jvm + kextract FFM libwayland-client): wl_display_connect, wl_registry, wl_compositor, xdg_shell for top-level windows
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

### Sprint 5 — Cross-6-platform Pong + v0.2.0 release (1-2 weeks)

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
- CHANGELOG v0.2.0 + git tag + Maven Central release

**Done definition**:
- Pong sample source code **strictly identical** (the same `PongGame.kt`) runs on all 6 platforms
- Demo videos recorded and attached to the GitHub release tag
- Lib v0.2.0 published to Maven Central with all **9 modules** (`koreos-core`, `koreos-appkit`, `koreos-uikit`, `koreos-android`, `koreos-js`, `koreos-wasm`, `koreos-win32`, `koreos-x11`, `koreos-wayland`, + facade `koreos`)
- CI green on 6 OS/runners

---

## 7. Success criteria

| Milestone | Measurable criterion |
|-----------|---------------------|
| v0.1.1 | Tag created, mkdocs branded Koreos, Android samples shared, createWindow functional on Android. |
| Sprint 1-2 (Web) | Triangle rendered at stable 60 fps in Chrome/Firefox/Safari on JS and WASM. |
| Sprint 3 (Windows) | Triangle rendered on Windows 10+11. Consistent keyboard/mouse input. |
| Sprint 4 (Linux) | Triangle rendered on Ubuntu (Wayland) + Debian (X11) with auto-detection. |
| Sprint 5 (Pong) | Pong identically playable on 6 platforms. v0.2.0 Maven Central release. |

---

## 8. Risks and mitigations

| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|------------|
| **Kotlin/Wasm still in alpha** | Medium | Unanticipated runtime bugs | Target the most stable Kotlin version at Sprint 1 start. JS first as MVP, WASM next. |
| **kextract X11/Wayland untested** | High | Sprint 4 delayed | Smoke test kextract on Xlib from Sprint 0. Upstream coordination with kextract team. |
| **Wayland protocol versions** (xdg_shell, xdg_decoration) | Medium | Compositor compat | Target xdg_shell v3 + zxdg_decoration_v1 (minimum Mutter 3.32+, KWin 5.20+). Fallback: no custom decorations. |
| **Auto X11/Wayland detection** unreliable | Low | Degraded Linux UX | `KOREOS_LINUX_BACKEND` environment variable for manual override. |
| **wgpu4k Web doesn't support all formats** (compute shaders, etc.) | Low | Sample web limitations | Pong = render-only, not affected. Monitor for future uses. |
| **Pong cross-platform: subtle input divergences** | High | Platform-specific behavior bugs | Manual integration tests per platform. Document acceptable divergences (e.g. tap vs arrow). |
| **CI Linux Wayland (headless weston)** unstable | Medium | Flaky CI tests | Smoke test only (build + 1 frame). No intensive runtime testing. |
| **Complex Windows DPI scaling** (PerMonitorV2 + multi-monitors) | Medium | HiDPI visual bugs | Test on multi-screen mixed scale at end of Sprint 3. |
| **v0.1.x backward compat broken** by API changes | Low | User migration | No existing interface signature changes. Only add RawWindowHandle variants. |

---

## 9. External dependencies

| Dependency | Target version | Status |
|------------|----------------|--------|
| kextract Win32 | To confirm | Probably supported (FFM Win32 = standard path) |
| kextract X11 | To confirm | To investigate in Sprint 0 |
| kextract Wayland | To confirm | To investigate in Sprint 0 |
| **wgpu4k Web JS** | To align | **Available** (confirmed) |
| **wgpu4k Web WASM** | To align | **Available** (confirmed) |
| Kotlin | 2.3.21+ (aligned with stable Kotlin/Wasm) | Configured |
| JDK | 25 (LTS) | Configured |
| Node.js | LTS (for Web targets) | To add to CI |

---

## 10. Indicative timeline

| Sprint | Duration | Target deadline (from 2026-05-29) |
|--------|----------|----------------------------------|
| Sprint 0 — v0.1.1 remediation | 2 weeks | T0 + 2 weeks (mid-June) |
| Sprint 1-2 — Web JS+WASM | 4 weeks | T0 + 6 weeks (mid-July) |
| Sprint 3 — Windows | 2 weeks | T0 + 8 weeks (end of July) |
| Sprint 4 — Linux X11+Wayland | 3 weeks | T0 + 11 weeks (mid-August) |
| Sprint 5 — Pong + v0.2.0 release | 2 weeks | T0 + 13 weeks (end of August) |
| (Outside v0.2) Compose-on-Koreos POC | 2 weeks | T0 + 15 weeks (September) |

Total v0.2.0: **~13 weeks** from T0. Shorter than the initial plan (M1→M3 = 14 weeks estimate, ~24h in practice).

---

## 11. Locked decisions (summary)

| # | Decision |
|---|----------|
| D1 | **6 target platforms**: macOS, iOS, Android, Web (JS+WASM), Windows, Linux (X11+Wayland) |
| D2 | **New platform order**: Web → Windows → Linux |
| D3 | **Pong demo** coded once in commonMain, delivered at the end on all 6 platforms |
| D4 | **Pong format**: 1 player vs simple AI, no audio |
| D5 | **Remediation**: dedicated v0.1.1 sprint before new platforms |
| D6 | **wgpu4k Web**: available, no upstream work needed |
| D7 | **Planning format**: full Linear backlog + plan/specs MR docs (M1-M3 pattern) |
| D8 | **Compose-on-Koreos**: 2-week evaluation POC **after** v0.2.0 |
| D9 | **ygdrasil model**: bindings only (no koreaudio, koreassets, koreecs) |
| D10 | **Community**: wgpu4k Discord (future rebrand), no dedicated ygdrasil Discord |
| D11 | **Hybrid JS/WASM strategy**: JS first (1-week MVP), WASM next (1.5 weeks) with shared `koreos-web-common` layer |
| D12 | **Linux X11/Wayland detection**: runtime auto-detection + `KOREOS_LINUX_BACKEND` env var override |
| D13 | **JDK target = 25 (LTS)**. Conscious trade-off between adoption and modernity — see §13. |

---

## 13. JDK 25 decision — rationale

The v0.2 reviewer legitimately raised the question of JDK 25 vs JDK 22/21 for broader adoption (FFM is stable since JDK 22, JDK 21 is LTS). The **JDK 25** decision is maintained, with the following arguments:

| Criterion | JDK 21 LTS | JDK 22 | **JDK 25 LTS** |
|---|---|---|---|
| FFM stable | preview | ✅ stable | ✅ stable |
| LTS status | LTS (until 2031) | non-LTS | **LTS (until 2033)** |
| Pattern matching switch | preview | ✅ stable | ✅ stable + improved |
| Virtual threads | ✅ stable | ✅ stable | ✅ stable + tuned |
| `Linker.upcallStub` API | preview | ✅ stable | ✅ stable + improved perf |
| Q3 2026 adoption | very wide | declining | growing |

**Why JDK 25**:

1. **Current LTS** — JDK 25 is the most recent LTS, supported until 2033 (Oracle/Eclipse Temurin). JDK 21 leaves "premium support" before 2030. Targeting the freshest LTS ensures Koreos users have a long-supported version.

2. **No Koreos dependency requires < JDK 25** — No partner imposing a lower version (to confirm if a consumer requests it).

3. **The ygdrasil ecosystem is new** — Koreos targets users building new projects (games/3D tools/Pong-like), not legacy migrations. These users are generally on the latest JDK.

4. **kextract generates modern FFM code** — The `Linker`, `Arena.ofShared`, `MemorySegment.reinterpret` APIs were polished post-JDK 22. Working on the most recent LTS avoids workarounds.

**Revision conditions**:

- If a strategic Koreos consumer (e.g., upstream Compose integration) requires JDK 21 → re-evaluate.
- If Kotlin/JVM drops the JDK 25 bytecode target before Koreos v1.0 → fallback JDK 22 (compromise: stable FFM + wider adoption).
- If > 30% of reported bugs mention "JDK too recent" → fallback JDK 22 (real adoption measurement).

**Fallback ready**: the project already uses Gradle `toolchain`; lowering the target from JDK 25 to JDK 22 is a minimal change (1 line in the `kmp-library` convention plugin). To document in `release-process.md`.

---

## 12. Appendices

### Glossary (v0.2 additions)

| Term | Definition |
|------|-----------|
| **Wayland** | Modern Linux compositor protocol, X11 replacement. Stack: libwayland-client + xdg_shell. |
| **X11** | Historical Linux/Unix windowing protocol. Xlib (C) or xcb (more modern). |
| **WebGPU** | Modern GPU API for browsers, exposed via JS/WASM. Used by wgpu4k Web. |
| **Kotlin/Wasm** | Kotlin compilation target to WebAssembly. More performant than Kotlin/JS for GPU/compute code. |
| **xdg_shell** | Standard Wayland protocol for top-level windows (decorations, resize, fullscreen). |
| **PerMonitorV2** | Windows 10+ DPI awareness mode: each monitor has its own scale, managed by the app. |

### winit → Koreos mapping (v0.2 additions)

| winit (Rust) | Koreos v0.2 |
|--------------|-------------|
| `RawWindowHandle::Web(WebHandle)` | `RawWindowHandle.Web(canvasElementId: String)` |
| `RawWindowHandle::Win32(Win32Handle)` | `RawWindowHandle.Win32(hwnd: Long, hinstance: Long)` |
| `RawWindowHandle::Xlib(XlibHandle)` | `RawWindowHandle.Xlib(window: Long, display: Long)` |
| `RawWindowHandle::Wayland(WaylandHandle)` | `RawWindowHandle.Wayland(surface: Long, display: Long)` |

### Associated documents

- [v0.1 plan (delivered)](./plan.md)
- [v0.1 specs (delivered)](./specs.md)
- [v0.1 sprint review — conversational output](#)
- [v0.2 specs (in progress)](./specs-v0.2.md)
