# Visual Regression Testing (screenshot diff)

A Roborazzi equivalent, cross-platform. Compares a screenshot of a sample
against a **committed baseline**, with a tolerance expressed as a percentage of
differing pixels. Complements the E2E smoke test (#22, "at least one frame presented")
by catching subtle visual regressions (color, position, antialiasing).

## Capture strategy: GPU readback, not system screenshot

System screenshots (ScreenCaptureKit / `screencapture`, `CGWindowListCreateImage`, etc.)
are **unsuitable in CI**: they require a display plus the TCC "Screen Recording"
permission (unavailable on headless runners), and `CGWindowListCreateImage` is moreover
**removed in macOS 26**. UI instrumentation tools (Roborazzi/Paparazzi, XCUITest)
snapshot a **view hierarchy**, not a **GPU surface** — therefore useless for a sample
that renders directly via wgpu.

The chosen method is **GPU framebuffer readback** via the graphics API: the sample
renders a frame into an **offscreen texture**, copies it to a buffer
(`copyTextureToBuffer`), maps and reads the bytes, then writes a PNG. This is
**deterministic**, **requires no window or permission**, and **identical in CI and locally**.
Since wgpu4k is multiplatform, this path is shared across all targets; only the PNG
encoding differs per platform.

## Status per platform

| Platform | Capture | Automated in CI |
|----------|---------|-----------------|
| **Web** (JS) | Playwright `page.screenshot()` | ✅ yes (informational, non-blocking) |
| **macOS** | **GPU readback** (`hello-triangle --capture`) | ✅ yes — `macos-visual` job (informational, non-blocking) |
| **iOS** | GPU readback (Kotlin/Native, wgpu4k Metal) | ⚠️ implemented — **headless simulator without Metal** |
| **Android** | GPU readback (wgpu4k Vulkan, emulator) | ✅ yes — `android-visual` job (SwiftShader emulator, non-blocking) |
| Windows | GPU readback (same wgpu code, PNG via ImageIO) | 🟡 to wire — Windows GPU runner |
| Linux X11/Wayland | GPU readback (same wgpu code) | 🟡 to wire |

> **Web** and **macOS** run in CI. Other platforms reuse the same GPU readback
> (shared wgpu code); only a platform-specific GPU/emulator runner is needed to enable them.

## macOS — GPU readback (`hello-triangle --capture`)

```bash
./gradlew :samples:hello-triangle:run --args="--capture out.png"
```

Renders the triangle into an offscreen `RGBA8Unorm` texture (no window is opened —
an offscreen `CAMetalLayer` is created solely to satisfy `requestAdapter`, as wgpu4k 0.1.1
does not yet support an adapterless surface), reads the framebuffer via readback, and
writes the PNG (`ImageIO`). The `macos-visual` CI job compares this PNG against
`tests/visual/baselines/macos/hello-triangle.png` using `tests/visual/diff-cli.js`
(pixelmatch, 2% tolerance), **non-blocking**: verdict in the Job Summary + diff archived.

### Updating the macOS baseline

```bash
./gradlew :samples:hello-triangle:run --args="--capture tests/visual/baselines/macos/hello-triangle.png"
git add tests/visual/baselines/macos/hello-triangle.png
```

## Web slice (implemented)

The test `samples/hello-triangle-web/e2e/tests/visual.spec.js` captures the WebGPU
canvas and compares it against `e2e/baselines/hello-triangle-web.png` via
[pixelmatch](https://github.com/mapbox/pixelmatch) (helper
`visual/assert-screenshot.js`, `assertScreenshotMatches(actualPng, baselinePath,
{ tolerance })`, default **2%**).

### Non-blocking

WebGPU **SwiftShader** headless rendering may vary slightly between environments;
the test is therefore **informational**: it logs the diff ratio and archives the diff
image as a CI artifact (`hello-triangle-web-visual-diff`), but **never fails the build**.
This avoids a flaky gate while keeping regressions visible in review.

### Updating the baseline

When a visual change is **intentional** (human only — never automated):

```bash
cd samples/hello-triangle-web/e2e
npm run update-baselines          # deletes and regenerates baselines/*.png
git add baselines/*.png           # commit the new baseline
```

## Adding a sample / platform

1. Reuse `assertScreenshotMatches(actualPng, baselinePath, { tolerance, diffPath })`.
2. Provide a capture provider for the platform (see the table above).
3. Store the baseline under `baselines/<platform>/<sample>.png` (git-lfs if > 5 MB cumulative).

## iOS — GPU readback (`samples/hello-triangle-ios`, best-effort)

**Kotlin/Native** capture: `captureTriangle()` (iosMain) creates an offscreen
`CAMetalLayer`, obtains a wgpu4k Metal surface, renders the triangle into a texture,
reads back the framebuffer (`copyTextureToBuffer` + `mapAsync` + read via `CPointer`),
and returns the RGBA bytes. Run by `iosSimulatorArm64Test` (CI job `ios-visual`,
non-blocking).

**Limitation**: the **headless iOS simulator** (CI and K/N test harness) exposes
**no Metal device** (`MTLCreateSystemDefaultDevice() == null`) — unlike Linux, there
is no software Metal implementation. The test skips gracefully in that case. Actually
rendering the triangle requires a **physical iOS device** (`iosArm64`) or a
Metal-capable simulator (Simulator.app GUI). The CI job still ensures that the iOS
capture code **compiles and links**.

## Android — GPU readback (`samples/hello-triangle-android-capture`)

Instrumented test (`connectedDebugAndroidTest`): `captureTriangle()` creates a
`Surface` backed by a `SurfaceTexture` (offscreen), obtains the `ANativeWindow` via
`io.ygdrasil.nativeHelper.Helper.nativeWindowFromSurface` (android-native-helper),
creates a wgpu4k Vulkan surface, renders the triangle into an offscreen texture, and
reads back the framebuffer (`copyTextureToBuffer` + `mapAsync` + `mapInto`). The test
verifies the presence of the R/G/B regions (rendered triangle).

CI job `android-visual`: API 34 emulator (`reactivecircus/android-emulator-runner`)
with **software Vulkan SwiftShader** (`-gpu swiftshader_indirect`), KVM enabled.
**Non-blocking**. Verified locally (API 36 emulator): test green, triangle rendered.
