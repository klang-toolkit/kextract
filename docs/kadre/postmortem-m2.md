# Post-mortem M2 — wgpu4k Demo on AppKit/macOS

**Closing date**: 2026-05-28  
**Tickets**: GRA-133 → GRA-140  
**Status**: ✅ Validated

---

## Summary

Milestone M2 validates the end-to-end integration of the Kadre graphics stack on macOS:

```
EventLoop (AppKit/CFRunLoop)
  ↓ canCreateSurfaces
NSWindow + CAMetalLayer (Panama FFM)
  ↓ rawWindowHandle
wgpu4k Instance → Surface → Adapter → Device
  ↓ createRenderPipeline
WGSL shader → RGB triangle @ ~120 fps
  ↓ WindowEvent.Resized
surface.configure(newWidth, newHeight)
```

**Result**: an RGB triangle runs at ~120 fps in a resizable Kadre window, with no JNA or Rococoa — Panama FFM only (JDK 25).

> **Post-review fix (PR #25)**: the initial render ran at ~60 fps (then 0 fps after the wgpu-native 0.25+ update). Three Metal fixes were applied via PR #25 (wgpu-native 0.25+ — BGRA8Unorm framebuffer format, FIFO presentation mode, 0.25.x API signatures). Final result: ~120 fps in ProMotion on Apple M2 Max.

---

## Delivered tickets

| Ticket | Title | Estimate | Actual |
|--------|-------|----------|--------|
| GRA-133 | WindowEvent.ScaleFactorChanged | 1 pt | ~1 h |
| GRA-134 | WindowEvent.RedrawRequested + CFRunLoopObserver | 3 pt | ~2 h |
| GRA-135 | aboutToWait callback after RedrawRequested | 2 pt | ~1.5 h |
| GRA-136 | Effective ControlFlow + thread-safe EventLoopProxy.wakeUp | 5 pt | ~4 h |
| GRA-137 | samples/hello-triangle: wgpu4k Instance+Surface+Adapter+Device | 3 pt | ~3 h |
| GRA-138 | samples/hello-triangle: RGB triangle render | 5 pt | ~4 h |
| GRA-139 | samples/hello-triangle: swap chain resize | 2 pt | ~1 h |
| GRA-140 | Post-mortem + README | 1 pt | ~1 h |

**Total**: 22 estimated points, delivered in one session (~18 h).

---

## What worked well

### 1. Panama FFM as the sole native layer

The choice of Panama FFM (`java.lang.foreign`) over JNA/Rococoa proved sound:
- **Zero native dependencies**: everything runs in the standard JVM (JDK 25)
- **Performance**: direct downcalls to `objc_msgSend`, `CFRunLoopWakeUp`, `sel_registerName` with no indirection
- **Maintainability**: signatures verified at compile time via `FunctionDescriptor`
- **No leaks**: `Arena.ofAuto()` manages native segment lifetimes automatically

### 2. EventLoop → ApplicationHandler architecture

The `ApplicationHandler` interface with its callbacks (`canCreateSurfaces`, `aboutToWait`, `windowEvent`) provides a clean extension point. The hello-triangle sample needs no knowledge of AppKit internals.

### 3. CFRunLoop as the scheduling foundation

Using `kCFRunLoopBeforeWaiting` + `CFRunLoopTimer` to implement `ControlFlow.WaitUntil` is elegant: AppKit manages the timer precision, with no extra thread required.

### 4. Stable and well-structured wgpu4k API

The wgpu4k 0.1.1 API faithfully follows the WebGPU spec. The `Instance → Surface → Adapter → Device → Pipeline → render loop` sequence is idiomatic and portable.

---

## Technical surprises

### 1. `webgpu-ktypes-descriptors` missing from `wgpu4k:0.1.1`

**Symptom**: `Unresolved reference 'VertexState'`, `'RenderPipelineDescriptor'`, etc.  
**Cause**: `wgpu4k:0.1.1` only depends on `webgpu-ktypes:0.0.7` (interfaces only). The concrete data classes (`VertexState`, `FragmentState`, `Color`, `RenderPassDescriptor`, etc.) live in the separate module `webgpu-ktypes-descriptors`.  
**Fix**: explicit addition of `io.ygdrasil:webgpu-ktypes-descriptors:0.0.7` in `samples/hello-triangle/build.gradle.kts`.  
**Lesson**: always check a dependency's POM before assuming types are available transitively.

### 2. `configureWithMetalLayer` vs direct FFM call

**Symptom**: `WGPU.getSurfaceFromMetalLayer` expects a `NativeAddress` (= `JvmNativeAddress`) wrapping a `MemorySegment`.  
**Cause**: the wgpu4k API exposes `ffi.JvmNativeAddress`, not a raw `Long`.  
**Fix**: `JvmNativeAddress(MemorySegment.ofAddress(metalLayerAddr))`.  
**Lesson**: the FFM/Panama type model requires wrapping addresses before passing them to libraries.

### 3. PascalCase enums in webgpu-ktypes

**Symptom**: `bgra8unorm`, `opaque`, `renderAttachment` → compilation errors.  
**Cause**: unlike WebGPU spec names (lowercase camelCase), Kotlin enums use PascalCase.  
**Fix**: `GPUTextureFormat.BGRA8Unorm`, `CompositeAlphaMode.Opaque`, `GPUTextureUsage.RenderAttachment`.

### 4. Non-transitive `kotlinx-coroutines-core` dependency

**Symptom**: `Unresolved reference 'runBlocking'` despite wgpu4k using coroutines.  
**Cause**: `kotlinx-coroutines-core` is scoped `runtime` in wgpu4k's POM, not `compile`.  
**Fix**: explicit declaration in `dependencies { implementation(libs.kotlinx.coroutines.core) }`.

### 5. Slow GitHub Actions runner (~10 min for a 4-min build)

**Symptom**: two runs triggered per push, the second on a struggling runner.  
**Cause**: GitHub Actions free tier, variable queue times.  
**Impact**: none on quality — the first run was always fully green.

---

## Decisions to revisit for M3

### 1. `requestRedraw()` in `aboutToWait` → replace with `ControlFlow.Poll`

Currently, `aboutToWait` calls `window.requestRedraw()` to trigger continuous redraws. This works but is not idiomatic: the application should instead set `ControlFlow.Poll` to signal "I want to run continuously" and let the loop manage the cadence.

### 2. wgpu resource release on the `Device` side

Resource release (`device.close()`, `surface.close()`, `wgpu.close()`) in `releaseResources()` works but does not guarantee destruction order. For M3, consider an `AutoClosableContext` as used in the wgpu4k-scenes samples.

### 3. No `Device.poll()` between frames

wgpu native requires periodic calls to `Device.poll()` (or equivalent) to process async GPU callbacks. On Metal this is not blocking, but on other backends it will be necessary. Anticipate this for cross-platform portability.

### 4. `hello-triangle` / `kadre-appkit` decoupling

The sample calls `getMetalLayerFromNsView()` directly via Panama FFM instead of going through `kadre-appkit`. This is intentional to keep the sample self-contained, but a `RawWindowHandle → NativeAddress` API in `kadre-appkit` would simplify future samples.

---

## M2 end metrics

| Metric | Value |
|--------|-------|
| Average FPS (Apple M2 Max, Release) | ~120 fps (ProMotion, post-fix PR #25) |
| Tickets delivered | 8 |
| PRs merged | 7 (#18 → #25) |
| Kotlin files created | 7 |
| Lines of Kotlin added (net) | ~1,200 |
| Native dependencies (JNA/Rococoa) | 0 |
| CI build time (fast) | ~3–4 min |
| M2 session duration | ~1 day |

---

## Demo video

> **To record manually**: run `./gradlew :samples:hello-triangle:run` on macOS Apple Silicon,  
> record ~30s with QuickTime (window open → RGB triangle → resize → close),  
> upload to the GitHub release or as a Linear GRA-140 attachment.

---

## Next steps (M3)

- Keyboard / mouse: `KeyInput`, `MouseInput` → interact with the triangle
- Multi-window: manage multiple `WindowId` in the same `ApplicationHandler`
- Portability: Linux (X11/Wayland), Windows (DXGI)
- `Renderer` abstraction: separate rendering logic from the ApplicationHandler
