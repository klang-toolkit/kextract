# Tutorial: integrate Kadre in a Windows application

This tutorial walks you through creating a native Windows window with Kadre on JVM 25 step by step. You will end up with a working window on Win32 that responds to mouse and keyboard events and closes cleanly.

**Prerequisites**: JDK 25, Gradle 9+, Windows 10 21H1 or later.

---

## Step 1 — Configure `build.gradle.kts`

Create (or adapt) your `build.gradle.kts` with the Kadre dependency and a `JavaExec` task configured for Panama FFM:

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
                // Kadre public facade — automatic macOS/Windows routing
                implementation("org.graphiks.kadre:kadre:1.0.0")
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
    Kadre uses the Foreign Function & Memory API (Panama, JEP 454), finalized in JDK 25.
    Any lower version throws `java.lang.reflect.InaccessibleObjectException` at startup.

---

## Step 2 — Implement `ApplicationHandler`

`ApplicationHandler` is the central interface: Kadre calls it for each lifecycle and window event.

```kotlin
package com.example.myapp

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.Window
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.WindowEvent

class MyAppHandler : ApplicationHandler {

    private var window: Window? = null

    // Called when the surface can be created (equivalent to WM_CREATE)
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        window = eventLoop.createWindow(
            WindowAttributes(
                title = "My Windows application — Kadre",
                resizable = true,
            )
        )
    }

    // Called for each window event
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
        when (event) {
            // Draw: triggered by WM_PAINT / InvalidateRect
            WindowEvent.RedrawRequested -> {
                // Place your renderer call here (wgpu4k, Direct3D, etc.)
            }

            // Close: user clicked the X button (WM_CLOSE)
            WindowEvent.CloseRequested -> {
                eventLoop.exit()   // PostQuitMessage(0) → WM_QUIT
            }

            // Resize (WM_SIZE)
            is WindowEvent.Resized ->
                println("Resized → ${event.size.width}×${event.size.height}")

            // DPI change (WM_DPICHANGED) — see Step 4
            is WindowEvent.ScaleFactorChanged ->
                println("DPI scale factor → ${event.factor}")

            // Mouse input
            is WindowEvent.MouseInput ->
                println("MouseInput ${event.state} button=${event.button}")

            is WindowEvent.PointerMoved ->
                println("PointerMoved (${event.position.x.toInt()}, ${event.position.y.toInt()})")

            // Keyboard input
            is WindowEvent.KeyInput ->
                println("Key ${event.event.state} physical=${event.event.physicalKey} logical=${event.event.logicalKey} repeat=${event.event.repeat}")

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

import org.graphiks.kadre.EventLoop

fun main() {
    // EventLoop detects the OS at runtime and loads the Win32 backend
    EventLoop().runApp(MyAppHandler())
}
```

The Kadre facade `EventLoop` class automatically detects the operating system via `System.getProperty("os.name")` and delegates to the Win32 backend (`org.graphiks.kadre.win32.Win32EventLoopKt.runApp`) by reflection. No platform-specific import is needed in your code.

---

## Step 4 — Run the application

```bash
./gradlew run
```

You can also run from the command line with the assembled JAR:

```bash
java --enable-native-access=ALL-UNNAMED \
     -cp "build/libs/myapp.jar;build/libs/*" \
     com.example.myapp.MainKt
```

!!! note "High resolution DPI — PerMonitorV2"
    Kadre automatically configures `SetProcessDpiAwarenessContext(DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2)`
    at Win32 backend startup. No application manifest changes are needed.

    When the window is moved to a screen with a different resolution, Windows sends `WM_DPICHANGED`.
    Kadre translates this to `WindowEvent.ScaleFactorChanged(factor)` where `factor` is the new ratio
    (e.g., `1.5` for a 144 DPI display). Restart your renderer with the new physical size at that point.

---

## Step 5 (optional) — Package with `jpackage`

To distribute a standalone Windows installer (`.msi` or `.exe`), use `jpackage` (included in JDK 14+):

```bash
jpackage \
  --type msi \
  --name "MyKadreApp" \
  --app-version "1.0.0" \
  --input build/libs \
  --main-jar myapp.jar \
  --main-class com.example.myapp.MainKt \
  --java-options "--enable-native-access=ALL-UNNAMED" \
  --win-dir-chooser \
  --win-shortcut
```

!!! tip "Icon and metadata"
    Add `--icon myapp.ico` (`.ico` format required on Windows) and `--win-menu` to create
    a Start menu entry.

---

## Key points summary

| Point | Detail |
|-------|--------|
| Minimum JVM | **25** — Panama FFM (JEP 454) |
| Required JVM flag | `--enable-native-access=ALL-UNNAMED` |
| DPI awareness | Automatic — `PerMonitorV2` (no manifest required) |
| DPI event | `WindowEvent.ScaleFactorChanged(factor)` triggered on `WM_DPICHANGED` |
| Clean close | `eventLoop.exit()` in `CloseRequested` → `PostQuitMessage(0)` |
| Packaging | `jpackage` with `--java-options "--enable-native-access=ALL-UNNAMED"` |
