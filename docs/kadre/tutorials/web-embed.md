# Tutorial: integrate Kadre in a web page

This tutorial walks you through displaying a Kadre surface in an HTML page with Kotlin/JS step by step. You will end up with an interactive canvas that responds to pointer and keyboard events via the `requestAnimationFrame` event loop.

**Prerequisites**: Kotlin 2.3.21+, Gradle 9+, a modern browser (Chrome 112+, Firefox 113+, Safari 17+).

---

## Step 1 — Configure `build.gradle.kts`

Create (or adapt) your `build.gradle.kts` with the `kadre-js` dependency and the Kotlin/JS target configured for the browser:

```kotlin
plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.3.21"
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "myapp.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        jsMain {
            dependencies {
                // Kadre facade for the browser — Kotlin/JS IR
                implementation("org.graphiks.kadre:kadre-js:1.0.0")
            }
        }
    }
}
```

!!! note "Kotlin/JS IR required"
    Kadre uses `@JsExport` and the IR compiler's DCE optimizations.
    The legacy Kotlin/JS backend is not supported.

---

## Step 2 — Implement `ApplicationHandler`

`ApplicationHandler` is the central interface: Kadre calls it for each lifecycle and window event. On the web, the "window" corresponds to a `<canvas>` element identified by its CSS id.

```kotlin
package com.example.myapp

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.WindowEvent

class MyWebHandler : ApplicationHandler {

    private var window: Window? = null

    // Called at startup — the page is ready, the DOM is loaded
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        window = eventLoop.createWindow(
            WindowAttributes(
                // The title is used as the CSS id of the target canvas
                title = "my-canvas",
            )
        )
    }

    // Called for each window event (translated DOM events)
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
        when (event) {
            // Redraw: triggered by requestAnimationFrame
            WindowEvent.RedrawRequested -> {
                // Place your renderer call here (wgpu4k, WebGL, WebGPU, etc.)
            }

            // Page close / navigation away
            WindowEvent.CloseRequested -> {
                eventLoop.exit()
            }

            // Canvas resize
            is WindowEvent.Resized ->
                println("Resized → ${event.size.width}×${event.size.height}")

            // Pointer movement (unified DOM PointerEvent — mouse, stylus, touch)
            is WindowEvent.PointerMoved ->
                println("PointerMoved (${event.position.x.toInt()}, ${event.position.y.toInt()})")

            // Mouse click / touch press
            is WindowEvent.MouseInput ->
                println("MouseInput ${event.state} button=${event.button}")

            // Scroll (wheel or pinch-to-zoom)
            is WindowEvent.MouseWheel ->
                println("Wheel Δx=${event.deltaX} Δy=${event.deltaY}")

            // Keyboard input
            is WindowEvent.KeyboardInput ->
                println("Key ${event.state} key=${event.key} repeat=${event.isRepeat}")

            // Focus / focus lost
            is WindowEvent.Focused ->
                println("Focused: ${event.gained}")

            else -> Unit
        }
    }

    override fun resumed(eventLoop: ActiveEventLoop) = Unit
    override fun suspended(eventLoop: ActiveEventLoop) = Unit

    override fun destroySurfaces(eventLoop: ActiveEventLoop) {
        window = null
    }
}
```

---

## Step 3 — `main()` entry point

```kotlin
package com.example.myapp

import org.graphiks.kadre.web.JsWebEventLoop

fun main() {
    // JsWebEventLoop is non-blocking: it schedules callbacks via
    // requestAnimationFrame and returns control to the browser immediately.
    JsWebEventLoop().runApp(MyWebHandler())
}
```

!!! warning "Non-blocking loop — key difference from JVM"
    Unlike the JVM or Win32 EventLoop, `JsWebEventLoop.runApp()` **returns immediately**.
    The loop relies on `requestAnimationFrame` to schedule frames at the browser's rate
    (typically 60 Hz). No dedicated thread is created: everything runs on the main JavaScript thread.

---

## Step 4 — Host HTML page

Create (or adapt) your `index.html`. The canvas must exist in the DOM **before** the JS bundle loads:

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Kadre Web application</title>
    <style>
        /* The canvas must have explicit CSS dimensions */
        #my-canvas {
            display: block;
            width: 800px;
            height: 600px;
            border: 1px solid #ccc;
            touch-action: none; /* Disables default touch scrolling */
        }
    </style>
</head>
<body>
    <!-- The id matches the title passed in WindowAttributes -->
    <canvas id="my-canvas"></canvas>

    <!-- JS bundle generated by Gradle — loaded deferred -->
    <script src="myapp.js" defer></script>
</body>
</html>
```

Then start the Gradle development server:

```bash
./gradlew jsBrowserDevelopmentRun
```

Or to produce a production bundle:

```bash
./gradlew jsBrowserProductionWebpack
```

Artifacts are generated in `build/distributions/`.

!!! warning "The canvas must exist before `runApp`"
    Kadre resolves the canvas by CSS id at the moment of the `createWindow` call.
    If the `<script>` loads **before** the canvas in the DOM, `createWindow`
    will not find the element and the surface will not be attached.
    Use `defer` or place the `<script>` after the `<canvas>`.

!!! tip "High density DPI — `devicePixelRatio`"
    On Retina or HiDPI screens, `window.devicePixelRatio` is greater than 1 (e.g., 2.0).
    For sharp rendering, set the canvas's physical dimensions in pixels:

    ```javascript
    const canvas = document.getElementById('my-canvas');
    const dpr = window.devicePixelRatio || 1;
    canvas.width  = canvas.offsetWidth  * dpr;
    canvas.height = canvas.offsetHeight * dpr;
    ```

    Kadre will expose this value via `Window.scaleFactor` in a future version
    (ticket #24). In the meantime, read `window.devicePixelRatio` directly.

---

## Step 5 (optional) — Kotlin/Wasm variant

For maximum rendering performance, use the `kadre-wasm` target (Kotlin/Wasm) instead of `kadre-js`. Kotlin/Wasm compiles to WebAssembly, which offers near-native execution performance for rendering code.

### Dependency

```kotlin
// build.gradle.kts
plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.3.21"
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        wasmJsMain {
            dependencies {
                implementation("org.graphiks.kadre:kadre-wasm:1.0.0")
            }
        }
    }
}
```

### Wasm entry point

```kotlin
package com.example.myapp

import org.graphiks.kadre.web.WasmJsWebEventLoop

fun main() {
    // WasmJsWebEventLoop — same API as JsWebEventLoop, same RAF behavior
    WasmJsWebEventLoop().runApp(MyWebHandler())
}
```

!!! note "Browser compatibility"
    Kotlin/Wasm requires the **WasmGC** and **JS imports/exports** extensions, available
    in Chrome 119+, Firefox 120+, and Safari 17.4+. Check target compatibility
    before migrating.

!!! tip "When to choose Kotlin/Wasm?"
    | Criterion | Kotlin/JS | Kotlin/Wasm |
    |-----------|-----------|-------------|
    | Browser compatibility | Wide (all modern browsers) | Restricted (WasmGC required) |
    | JavaScript interop | Native | Via `@JsExport` / `external` |
    | CPU performance | Good | Excellent |
    | Bundle size | Standard | Smaller (no JS stdlib) |

    For most use cases, `kadre-js` is sufficient. Choose `kadre-wasm`
    if your renderer performs intensive computation on the Kotlin side.

---

## Key points summary

| Point | Detail |
|-------|--------|
| Event loop | Non-blocking — `requestAnimationFrame`, no dedicated thread |
| Target canvas | Identified by `WindowAttributes.title` as CSS id |
| DOM at startup | The `<canvas>` must exist before the JS bundle loads |
| Pointer events | `WindowEvent.PointerMoved` / `MouseInput` — based on DOM `PointerEvent` |
| High density DPI | Read `window.devicePixelRatio` and adjust `canvas.width`/`canvas.height` |
| Touch scroll | Add `touch-action: none` on the canvas to avoid conflicts |
| Wasm variant | `kadre-wasm` + `WasmJsWebEventLoop` — identical API, better CPU performance |
