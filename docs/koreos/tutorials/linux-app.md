# Tutorial: integrate Koreos in a Linux application

This tutorial walks you through creating a native Linux window with Koreos on JVM 25 step by step. You will end up with a working window on X11 or Wayland that responds to mouse and keyboard events and closes cleanly.

**Prerequisites**: JDK 25, Gradle 9+, Linux (Debian/Ubuntu 22.04+ recommended, or any distribution with Wayland/X11).

---

## System prerequisites — native libraries

Koreos uses Vulkan for low-level rendering and X11/Wayland protocols for window management. Install the development headers before building:

```bash
# Debian / Ubuntu / Linux Mint
sudo apt install \
    libvulkan-dev \
    libwayland-dev \
    libx11-dev \
    libxkbcommon-dev
```

```bash
# Fedora / RHEL / CentOS Stream
sudo dnf install \
    vulkan-loader-devel \
    wayland-devel \
    libX11-devel \
    libxkbcommon-devel
```

```bash
# Arch Linux / Manjaro
sudo pacman -S \
    vulkan-headers \
    wayland \
    libx11 \
    libxkbcommon
```

!!! note "Vulkan driver"
    The packages above install the Vulkan headers and *loader*, but not the GPU-specific ICD driver.
    Also install the driver matching your hardware:

    | GPU | Package (Debian/Ubuntu) |
    |-----|------------------------|
    | Intel | `mesa-vulkan-drivers` |
    | AMD | `mesa-vulkan-drivers` |
    | NVIDIA proprietary | `nvidia-driver` (includes Vulkan driver) |
    | NVIDIA open | `libnvidia-gl-<version>` |

    Verify Vulkan availability with `vulkaninfo --summary` (package `vulkan-tools`).

---

## Step 1 — Configure `build.gradle.kts`

Create (or adapt) your `build.gradle.kts` with the Koreos dependency and a `JavaExec` task configured for Panama FFM:

```kotlin
plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.3.21"
}

kotlin {
    jvmToolchain(25)      // JVM 25 required — Panama FFM (JEP 454)

    jvm()

    sourceSets {
        jvmMain {
            dependencies {
                // Koreos public facade — automatic Linux/Windows/macOS routing
                implementation("io.ygdrasil.koreos:koreos:0.1.1")
            }
        }
    }
}

// JVM run task
tasks.register<JavaExec>("run") {
    group = "application"
    dependsOn("jvmJar")
    mainClass.set("com.example.myapp.MainKt")
    classpath = files(
        kotlin.targets.getByName("jvm").compilations.getByName("main").output.allOutputs,
        configurations.getByName("jvmRuntimeClasspath"),
    )
    jvmArgs(
        // Opens access to unnamed native APIs (Panama FFM)
        "--enable-native-access=ALL-UNNAMED",
    )
}
```

!!! warning "JVM 25 required"
    Koreos uses the Foreign Function & Memory API (Panama, JEP 454), finalized in JDK 25.
    Any lower version throws `java.lang.reflect.InaccessibleObjectException` at startup.

---

## Step 2 — Implement `ApplicationHandler`

`ApplicationHandler` is the central interface: Koreos calls it for each lifecycle and window event. The implementation is identical for X11 and Wayland — backend detection is transparent.

```kotlin
package com.example.myapp

import io.ygdrasil.koreos.ActiveEventLoop
import io.ygdrasil.koreos.ApplicationHandler
import io.ygdrasil.koreos.Window
import io.ygdrasil.koreos.WindowAttributes
import io.ygdrasil.koreos.WindowId
import io.ygdrasil.koreos.WindowEvent

class MyAppHandler : ApplicationHandler {

    private var window: Window? = null

    // Called when the surface can be created — the X11/Wayland connection is established
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        window = eventLoop.createWindow(
            WindowAttributes(
                title = "My Linux application — Koreos",
                resizable = true,
            )
        )
    }

    // Called for each window event
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        when (event) {
            // Draw: triggered on window exposure (Expose / frame callback)
            WindowEvent.RedrawRequested -> {
                // Place your renderer call here (wgpu4k, Vulkan, OpenGL, etc.)
            }

            // Close: user clicked the X or sent WM_DELETE_WINDOW
            WindowEvent.CloseRequested -> {
                eventLoop.exit()   // Terminates the event loop cleanly
            }

            // Resize
            is WindowEvent.Resized ->
                println("Resized → ${event.size.width}×${event.size.height}")

            // DPI change (moved to HiDPI screen, or scale changed)
            is WindowEvent.ScaleFactorChanged ->
                println("Scale factor → ${event.factor}")

            // Mouse input
            is WindowEvent.MouseInput ->
                println("MouseInput ${event.state} button=${event.button}")

            is WindowEvent.PointerMoved ->
                println("PointerMoved (${event.position.x.toInt()}, ${event.position.y.toInt()})")

            // Keyboard input (via libxkbcommon on X11 and Wayland)
            is WindowEvent.KeyboardInput ->
                println("Key ${event.state} key=${event.key} repeat=${event.isRepeat}")

            // Window destroyed: release GPU resources here
            WindowEvent.Destroyed -> window = null

            else -> Unit
        }
    }

    override fun resumed(eventLoop: ActiveEventLoop) = Unit
    override fun suspended(eventLoop: ActiveEventLoop) = Unit

    override fun destroySurfaces(eventLoop: ActiveEventLoop) {
        // Release GPU resources before surface destruction
        window = null
    }
}
```

---

## Step 3 — `main()` entry point

```kotlin
package com.example.myapp

import io.ygdrasil.koreos.EventLoop

fun main() {
    // EventLoop detects the OS at runtime and loads the Linux backend (X11 or Wayland)
    EventLoop().runApp(MyAppHandler())
}
```

The Koreos facade `EventLoop` class automatically detects the operating system via `System.getProperty("os.name")` and delegates to the Linux backend by reflection. Inside the Linux backend, Koreos then chooses between X11 and Wayland based on the environment variables present (see [X11 vs Wayland choice](#x11-vs-wayland-choice) below). No platform-specific import is needed in your code.

---

## Step 4 — Run the application

```bash
./gradlew run
```

You can also run from the command line with the assembled JAR:

```bash
java --enable-native-access=ALL-UNNAMED \
     -cp "build/libs/myapp.jar:build/libs/*" \
     com.example.myapp.MainKt
```

!!! note "Linux classpath separator"
    On Linux (and macOS), the classpath separator is `:`, not `;` as on Windows.
    Use `build/libs/myapp.jar:build/libs/*` (colon) in the `java` command.

---

## X11 vs Wayland choice

Koreos inspects environment variables in this priority order at Linux backend startup:

| Priority | Condition | Backend selected |
|----------|-----------|-----------------|
| 1 | `KOREOS_LINUX_BACKEND=wayland` | Wayland (forced) |
| 2 | `KOREOS_LINUX_BACKEND=x11` | X11 (forced) |
| 3 | `WAYLAND_DISPLAY` set and non-empty | Wayland (auto-detection) |
| 4 | `DISPLAY` set and non-empty | X11 (auto-detection) |
| 5 | No variable found | Error at startup |

### Auto-detection (default behavior)

In a modern Wayland desktop environment (GNOME 45+, KDE Plasma 6, Sway), `WAYLAND_DISPLAY` is set by the compositor. Koreos selects Wayland automatically. In a pure X11 session or an XWayland terminal, only `DISPLAY` is present, and Koreos switches to X11.

### Forcing a specific backend

```bash
# Force Wayland (useful for testing Wayland compatibility in a mixed session)
KOREOS_LINUX_BACKEND=wayland ./gradlew run

# Force X11 (useful under Wayland via XWayland, for legacy compatibility)
KOREOS_LINUX_BACKEND=x11 ./gradlew run
```

!!! warning "XWayland is not native Wayland"
    If you force `KOREOS_LINUX_BACKEND=x11` in a Wayland session, Koreos uses
    XWayland as a bridge. Native Wayland features (server decorations,
    fractional scaling) are then unavailable. Prefer the native Wayland backend
    whenever possible for better desktop integration.

---

## Linux-specific details

### DPI and scaling

DPI support varies by backend:

**Wayland — fractional scaling:**
Wayland exposes the screen scale factor via the `wl_output` protocol (integer since Wayland 1.x) and `wp_fractional_scale_v1` for fractional values (e.g., `1.25×`, `1.5×`). Koreos translates these to `WindowEvent.ScaleFactorChanged(factor)`.

**X11 — Xft.dpi heuristic:**
X11 has no standardized DPI mechanism. Koreos reads the `Xft.dpi` X resource via `XGetDefault(display, "Xft", "dpi")`. This resource is set by GNOME, KDE, and most window managers. If absent, Koreos falls back to the physical DPI computed from `DisplayWidth` / `DisplayWidthMM`, or a default of 96 DPI.

```bash
# Check the current Xft.dpi value
xrdb -query | grep dpi
# Or:
xdpyinfo | grep resolution
```

!!! tip "Sharp rendering on HiDPI screens under X11"
    If your application looks blurry on a 4K screen in an X11 session, check that
    `Xft.dpi` is set in `~/.Xresources`:

    ```
    Xft.dpi: 192
    ```

    Then reload with `xrdb -merge ~/.Xresources` and restart the application.

### Window decorations (Wayland)

Under Wayland, decorations (title bar, close/minimize/maximize buttons) can be managed by the compositor (*server-side decorations*, SSD) or by the application (*client-side decorations*, CSD). The `xdg-decoration-unstable-v1` protocol allows negotiating the mode.

Koreos requests server-side decorations first. If the compositor does not support `xdg-decoration` (e.g., Sway without `xwayland`), Koreos falls back automatically to CSD mode with client-drawn decorations.

```bash
# Check if your compositor supports xdg-decoration
wayland-info | grep xdg_decoration
# Or with weston-info:
weston-info | grep xdg_decoration
```

!!! note "No title bar under Sway?"
    Sway (and i3-like Wayland compositors) manage windows in tiling mode and remove
    decorations by default. This is expected window manager behavior, not a Koreos bug.
    Use Sway shortcuts (`$mod+Shift+q`, etc.) to control the window.

### Clean close

Always call `eventLoop.exit()` in the `CloseRequested` handler to ensure a clean shutdown:

```kotlin
WindowEvent.CloseRequested -> {
    // Release your GPU resources here if needed before exiting
    eventLoop.exit()
}
```

Without this call, the window closes visually but the JVM process stays suspended waiting for events. Under Wayland, this can leave the `wl_display` socket open and block the compositor.

---

## Linux CI — automated tests without a display

CI environments (GitHub Actions, GitLab CI) have no graphics server. Two approaches allow running Koreos tests in *headless* mode:

### Approach 1 — Xvfb (virtual X11)

`Xvfb` (*X Virtual Framebuffer*) emulates an X11 server in memory without a physical display. This is the simplest and most compatible solution.

```yaml
# .github/workflows/ci.yml
jobs:
  linux-tests:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Install dependencies
        run: |
          sudo apt-get update
          sudo apt-get install -y \
            libvulkan-dev libwayland-dev libx11-dev libxkbcommon-dev \
            xvfb vulkan-tools mesa-vulkan-drivers

      - name: Setup JDK 25
        uses: actions/setup-java@v4
        with:
          java-version: '25'
          distribution: 'temurin'

      - name: Run tests with Xvfb
        run: |
          # Start Xvfb on display :99, 24-bit color
          Xvfb :99 -screen 0 1280x720x24 &
          export DISPLAY=:99
          # Force X11 backend (Xvfb does not support Wayland)
          export KOREOS_LINUX_BACKEND=x11
          ./gradlew :koreos-core:jvmTest :koreos:jvmTest
```

!!! tip "Wait for Xvfb to be ready"
    On low-CPU CI runners, Xvfb may take a moment to start.
    Use `xdpyinfo -display :99 > /dev/null 2>&1` in a loop to wait
    until it is ready before running tests:

    ```bash
    Xvfb :99 -screen 0 1280x720x24 &
    until xdpyinfo -display :99 > /dev/null 2>&1; do sleep 0.1; done
    export DISPLAY=:99
    ```

### Approach 2 — Weston headless (virtual Wayland)

`weston` is the reference Wayland compositor. Its `headless` backend creates a compositor without a physical display, allowing Koreos's native Wayland backend to be tested in CI.

```yaml
# .github/workflows/ci.yml
jobs:
  linux-wayland-tests:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Install dependencies
        run: |
          sudo apt-get update
          sudo apt-get install -y \
            libvulkan-dev libwayland-dev libx11-dev libxkbcommon-dev \
            weston vulkan-tools mesa-vulkan-drivers

      - name: Setup JDK 25
        uses: actions/setup-java@v4
        with:
          java-version: '25'
          distribution: 'temurin'

      - name: Run tests with Weston headless
        run: |
          # Start Weston in headless mode on a dedicated Wayland socket
          weston --backend=headless-backend.so \
                 --socket=weston-test \
                 --width=1280 --height=720 &
          export WAYLAND_DISPLAY=weston-test
          export KOREOS_LINUX_BACKEND=wayland
          # Wait for Weston to be ready
          until [ -S "$XDG_RUNTIME_DIR/$WAYLAND_DISPLAY" ]; do sleep 0.1; done
          ./gradlew :koreos-core:jvmTest :koreos:jvmTest
```

!!! note "XDG_RUNTIME_DIR"
    Wayland stores its sockets in `$XDG_RUNTIME_DIR` (typically `/run/user/<uid>`).
    On GitHub Actions runners, this directory is set automatically.
    If not, export it manually: `export XDG_RUNTIME_DIR=/tmp/runtime-$(id -u)`.

### CI recommendation

| Criterion | Xvfb (X11) | Weston headless (Wayland) |
|-----------|------------|---------------------------|
| Setup simplicity | Excellent | Moderate |
| Tests native Wayland backend | No | Yes |
| Support on cloud runners | Universal | Good (Ubuntu 22.04+) |
| CPU/memory overhead | Minimal | Light |

For most projects, **Xvfb** is the most pragmatic choice. Add **Weston headless** as a separate job if you specifically want to validate the Wayland backend.

---

## Key points summary

| Point | Detail |
|-------|--------|
| Minimum JVM | **25** — Panama FFM (JEP 454) |
| Required JVM flag | `--enable-native-access=ALL-UNNAMED` |
| Classpath separator | `:` on Linux (not `;`) |
| Auto-detected backend | Wayland if `WAYLAND_DISPLAY` is set, otherwise X11 |
| Force a backend | `KOREOS_LINUX_BACKEND=wayland` or `=x11` |
| Wayland DPI | Via `wl_output` / `wp_fractional_scale_v1` → `ScaleFactorChanged` |
| X11 DPI | `Xft.dpi` heuristic → X resource or computed physical DPI |
| Wayland decorations | SSD requested first, CSD as fallback |
| Clean close | `eventLoop.exit()` in `CloseRequested` — closes the Wayland socket |
| Headless CI X11 | `Xvfb :99 -screen 0 1280x720x24` + `DISPLAY=:99` |
| Headless CI Wayland | `weston --backend=headless-backend.so` + `WAYLAND_DISPLAY=weston-test` |
