# Testing with Kadre

## Scripted events (`kadre-test`)

The `kadre-test` module provides a deterministic test harness to drive an
`ApplicationHandler` without a native backend (AppKit, Win32, X11, browser, etc.).
Ideal for testing the business logic of a handler (game, input adapter, state machine)
in `commonTest`, without a real OS.

### Dependency

```kotlin
// build.gradle.kts of the module under test
kotlin {
    sourceSets {
        commonTest.dependencies {
            implementation(project(":kadre-test"))
            implementation(kotlin("test"))
        }
    }
}
```

### `scriptedTest { … }` DSL

Describe a sequence of events, then run it on a handler. The `run(handler)` method
returns the **ordered trace** of invoked callbacks.

```kotlin
import org.graphiks.kadre.test.scriptedTest
import org.graphiks.kadre.test.Callback
import org.graphiks.kadre.core.Key

val trace = scriptedTest {
    canCreateSurfaces()
    keyPress(Key.ArrowUp)
    tick(16)            // simulates one frame: newEvents → RedrawRequested → aboutToWait
    keyRelease(Key.ArrowUp)
    closeRequested()
}.run(MyHandler())

assertEquals(Callback.Resumed, trace.first())
assertEquals(Callback.Suspended, trace.last())
```

### DSL verbs

| Verb | Effect |
|------|--------|
| `canCreateSurfaces()` | invokes `handler.canCreateSurfaces` |
| `keyPress(key, modifiers)` / `keyRelease(key, modifiers)` | `WindowEvent.KeyboardInput` |
| `pointerMove(x, y)` | `WindowEvent.PointerMoved` |
| `mouseInput(button, state)` | `WindowEvent.MouseInput` |
| `resized(w, h)` | `WindowEvent.Resized` |
| `scaleFactorChanged(factor)` | `WindowEvent.ScaleFactorChanged` |
| `tick(dtMs)` | one frame: `newEvents(Poll)` → `RedrawRequested` → `aboutToWait` |
| `closeRequested()` | `WindowEvent.CloseRequested` |
| `windowEvent(event)` | raw window event (escape hatch) |

### Lifecycle and `exit()`

`run` always invokes `resumed` first and `suspended` last. If the handler calls
`eventLoop.exit()` while processing an event (e.g. on `CloseRequested`), the
**remaining events are skipped**, but `suspended` is still invoked. This lets you
cleanly test the exit flow.

### Mocked window

`createWindow(...)` returns an in-memory `ScriptedWindow` (no native handle).
It records `requestRedraw()` calls (`redrawRequests`), the title, and visibility,
so you can assert on handler behavior without a graphics environment.

### Examples

See `kadre-test/src/commonTest/.../ScriptedEventLoopTest.kt`: lifecycle ordering,
key press/release, pointer sequences, resize cascades, exit flow.
