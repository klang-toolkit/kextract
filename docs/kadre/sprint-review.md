# Sprint Review — Kadre

> **Date**: 2026-05-29  
> **Scope covered**: macOS foundation, GPU rendering, and the iOS / Android / extended macOS backends  
> **Status**: Delivered ✓

---

## 1. Executive summary

The macOS, iOS, and Android foundation of Kadre was delivered in an intensive session of approximately 24 effective hours, following the [project plan](./plan.md). The library exposes a cross-platform Kotlin Multiplatform windowing API, inspired by winit, on these 3 platforms, with validated wgpu4k integration (RGB triangle rendered at ~120 fps on Apple M2).

---

## 2. Sprint metrics

| Metric | Value |
|--------|-------|
| Linear tickets delivered | 28 (GRA-133 → GRA-160) |
| Pull requests merged | ~29 PRs (feature branches → master) |
| Platforms supported | 3 (macOS, iOS, Android) |
| Artifacts published to Maven Central | 5 modules (`kadre-core`, `kadre-appkit`, `kadre-uikit`, `kadre-android`, `kadre`) |
| Render FPS (hello-triangle, Apple M2, Release) | ~120 fps (post-fix PR #25) |
| Native dependencies (JNA/Rococoa) | 0 |
| CI build time (fast path) | ~3–4 min |
| Kotlin lines of code added (net, M2 only) | ~1,200 |
| Total M3 session duration | ~1 day |

---

## 3. Deliverables per milestone

### Milestone M1 — POC: minimal Metal view

**Goal**: prove that kextract + FFM can open a native window and expose a layer-backed `NSView`.

| Deliverable | Status |
|------------|--------|
| Gradle modules created (`kadre-core`, `kadre-appkit`, `kadre`) | ✓ Delivered |
| macOS window via `samples/hello-metal` | ✓ Delivered |
| `contentView` layer-backed (`wantsLayer = true`) | ✓ Delivered |
| Clean close (click on window X) | ✓ Delivered |

**Done criterion met**: `./gradlew :samples:hello-metal:run` opens an empty window; `nsView.layer != null`; closes without crash.

---

### Milestone M2 — wgpu4k demo (GRA-133 → GRA-140, PRs #18–#25)

**Goal**: validate the raw handle contract with wgpu4k and demonstrate a basic render.

| Ticket | Deliverable | PRs |
|--------|------------|-----|
| GRA-133 | `WindowEvent.ScaleFactorChanged` | #18 |
| GRA-134 | `WindowEvent.RedrawRequested` + `CFRunLoopObserver` | #19 |
| GRA-135 | `aboutToWait` callback after `RedrawRequested` | #20 |
| GRA-136 | Effective `ControlFlow` + thread-safe `EventLoopProxy.wakeUp` | #20 |
| GRA-137 | `hello-triangle`: wgpu4k Instance + Surface + Adapter + Device | #21 |
| GRA-138 | `hello-triangle`: RGB triangle render | #22 |
| GRA-139 | `hello-triangle`: swap chain resize | #23 |
| GRA-140 | M2 post-mortem + Kadre README | #24 |

**Post-review fix** (PR #25): RGB triangle @ 120 fps — 3 Metal/wgpu-native 0.25+ fixes (framebuffer format, FIFO presentation, wgpu-native 0.25.x API).

**Done criterion met**: demo runnable on Apple Silicon, 120 fps stable after fix; resize without crash; swap chain reconfigured.

---

### Milestone M3 — Target lib (GRA-141 → GRA-160, PRs #26–#46)

**Goal**: publishable KMP library, multiplatform, integrable in third-party projects.

#### iOS backend — `kadre-uikit` (GRA-141 → GRA-146)

| Ticket | Deliverable |
|--------|------------|
| GRA-141 | `kadre-uikit` module setup (iosX64, iosArm64, iosSimulatorArm64) |
| GRA-142 | `KadreAppDelegate` iOS lifecycle (AppDelegate-only) |
| GRA-143 | `UiKitWindow` — full-screen `UIWindow` + `UIView` + `CAMetalLayer` |
| GRA-144 | Touch events `UIResponder` → `WindowEvent.Touch` |
| GRA-145 | Background/foreground lifecycle + KDoc callback ordering |
| GRA-146 | iOS `EventLoop` actual → `kadre-uikit` + `hello-touch` sample |

#### Android backend — `kadre-android` (GRA-147 → GRA-152)

| Ticket | Deliverable |
|--------|------------|
| GRA-147 | `kadre-android` module setup (AGP, manifest, minSdk=24) |
| GRA-148 | `KadreActivity` + full-screen `AndroidWindow` SurfaceView |
| GRA-149 | Activity + SurfaceHolder lifecycle dispatch → `ApplicationHandler` |
| GRA-150 | `MotionEvent` → `WindowEvent.Touch` multi-touch dispatch |
| GRA-151 | Choreographer frame timing + `RedrawRequested` dispatch |
| GRA-152 | `EventLoop` androidMain actual + `hello-touch-android` sample |

#### Extended macOS backend (GRA-153 → GRA-156)

| Ticket | Deliverable |
|--------|------------|
| GRA-153 | Multi-window support: `windowWillClose` cleanup + `exit()` closes all |
| GRA-154 | Keyboard input `sendEvent:` -> `WindowEvent.KeyInput` + `repeat` |
| GRA-155 | Full mouse input (clicks, movement, scroll, enter/exit) |
| GRA-156 | `DeviceEvent` dispatch (`PointerMotion`, `Button`, `Key`) before `WindowEvent` |

#### Infrastructure & publication (GRA-157 → GRA-160)

| Ticket | Deliverable |
|--------|------------|
| GRA-157 | Dokka KDoc coverage + MkDocs API Reference integration |
| GRA-158 | `hello-window` sample cross-platform (JVM + iOS + Android) |
| GRA-159 | Maven Central publication (`kmp-publish`, signing, GPG) |
| GRA-160 | Multi-platform CI (macOS + iOS simulator + Android) |

**Done criterion met**: artifact `org.graphiks.kadre:kadre` published to Maven Central; CI green on 3 platforms; MkDocs API documentation deployed.

---

## 4. Identified gaps (9 remediation items)

These gaps were identified at sprint review and addressed before the 1.0.0 release.

| # | Area | Gap |
|---|------|-----|
| 1 | MkDocs branding | `mkdocs.yml`: `site_name`, `site_description`, nav `kadre/api/` not rebranded as Kadre |
| 2 | Android samples | `hello-window-android` and `hello-touch-android` duplicate logic instead of sharing commonMain |
| 3 | Android EventLoop | `AndroidEventLoop.createWindow` throws `UnsupportedOperationException` instead of returning a working `AndroidWindow` |
| 4 | README leftovers | Root README still references "Clean Architecture / DDD / Compose / Koin" from the starter pack |
| 5 | M2 post-mortem | Incorrect FPS metric (`~60 fps` → `~120 fps`); M2 demo video not recorded |
| 6 | Stub comment | `AppKitEventLoop.kt:35` contains an obsolete "stub" comment |
| 7 | `KadreApplication.eventLoop` | Mutable static variable (`var`) to refactor into a scoped instance |
| 8 | CI feature branches | `ios-build`/`android-build` only run on `master` push; extend to PR branches |
| 9 | E2E smoke test | No "at least one frame rendered" test on `hello-triangle` (possible regression, cf. PR #25) |

---

## 5. Key lesson — PR #25 (wgpu-native 0.25+ regression)

**Problem**: after merging GRA-138 (RGB triangle), rendering broke at 0 fps following the update to wgpu-native 0.25+.

**Cause**: three breaking change incompatibilities in wgpu-native:
1. Framebuffer format: `BGRA8Unorm` required on Metal instead of `RGBA8Unorm`.
2. Presentation mode: `PresentMode.FIFO` replaces the old default.
3. wgpu-native 0.25.x API: signature changes in `createRenderPipeline`.

**Fix** (PR #25): 3 targeted patches, triangle stable at 120 fps (Metal VSync on Apple M2).

**Lesson**: wgpu-native updates are frequent breaking changes. Pin the version (`wgpu-native = "0.25.x"`) in the version catalog and add an anti-regression smoke test ("at least one frame rendered") before each version bump.

---

## 6. Retrospective

### What worked well

- **Panama FFM as the sole native layer**: zero JNA/Rococoa dependency, direct downcalls to `objc_msgSend`, memory managed via `Arena.ofAuto()`. Approach confirmed solid for M3+.
- **`ApplicationHandler` architecture**: callback-driven interface (`canCreateSurfaces`, `aboutToWait`, `windowEvent`) extensible without coupling to AppKit/UIKit/Android internals.
- **CFRunLoop as scheduler**: `kCFRunLoopBeforeWaiting` + `CFRunLoopTimer` for `ControlFlow.WaitUntil` — elegant, precise, no extra thread.
- **Stable and portable wgpu4k API**: the `Instance → Surface → Adapter → Device → Pipeline → render loop` sequence is idiomatic WebGPU and reproducible on other platforms.
- **High velocity**: 28 tickets, ~29 PRs, 3 platforms, Maven Central publication — all delivered in ~24 effective hours.

### Areas to improve

- **No E2E smoke test**: the PR #25 regression could have been caught automatically. Addressed before release.
- **`requestRedraw()` in `aboutToWait`**: functional but not idiomatic. Replaced with `ControlFlow.Poll` (cf. M2 post-mortem §M3 decisions).
- **wgpu resource release**: destruction order not guaranteed in `releaseResources()`. `AutoClosableContext` planned.
- **No `Device.poll()`**: required for non-Metal backends (Linux, Windows). Anticipated for cross-platform portability.
- **M2 demo video not recorded**: missing deliverable for external communication.

---

## 7. References

- [Project plan](./plan.md) — vision, scope (6 platforms, Pong), risks
- [M2 post-mortem](./postmortem-m2.md) — detailed M2 milestone analysis
- [Technical specifications](./specs.md) — architecture, API, diagrams
- [1.0.0 release](https://github.com/ygdrasil-io/poc-koreos/releases/tag/v1.0.0) — GitHub tag + Maven Central artifacts
