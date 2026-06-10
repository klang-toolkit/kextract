# Kadre Extras

Features available in Kadre with no direct winit equivalent.

## `KeyChord` — Typed Keyboard Shortcuts

```kotlin
data class KeyChord(
    val physicalKey: PhysicalKey? = null,
    val logicalKey: LogicalKey? = null,
    val modifiers: KeyboardModifiers = KeyboardModifiers.NONE,
    val allowRepeat: Boolean = false,
    val modifierMatch: KeyChordModifierMatch = KeyChordModifierMatch.Contains,
)
```

- `KeyChordModifierMatch.Contains` — required modifiers must be present; extras allowed
- `KeyChordModifierMatch.Exact` — event modifiers must match exactly
- Portable: works with both `physicalKey` (layout-independent) and `logicalKey` (layout-aware)

## Extended `KeyboardModifiers` Flags

Beyond winit's Shift/Ctrl/Alt/Meta:
- `AltGraph` — AltGr key (used on international keyboards)
- `CapsLock` — Caps lock state
- `NumLock` — Num lock state
- `Symbol` — Symbol/Command modifier

## `ModifierKeys` — Per-Side Modifier Tracking

```kotlin
data class ModifierKeys(
    val leftShift: ModifierKeyState,
    val rightShift: ModifierKeyState,
    val leftCtrl: ModifierKeyState,
    val rightCtrl: ModifierKeyState,
    val leftAlt: ModifierKeyState,
    val rightAlt: ModifierKeyState,
    val leftMeta: ModifierKeyState,
    val rightMeta: ModifierKeyState,
)
enum class ModifierKeyState { Pressed, Released, Unknown }
```

## `SurfaceSizeRequestResult`

```kotlin
sealed interface SurfaceSizeRequestResult {
    data class Applied(val size: PhysicalSize<Int>) : SurfaceSizeRequestResult
    data object Pending : SurfaceSizeRequestResult
    data class Failure(val error: RequestError) : SurfaceSizeRequestResult
}
```

## `RequestError.Ignored`

Kadre adds `RequestError.Ignored` for requests that the platform silently ignores (e.g., `dragWindow` on AppKit when no current event is available).

## Unified Pointer Model

Kadre uses a single `PointerSource` sealed interface that encodes Mouse / Touch / TabletTool within all pointer events:

```kotlin
sealed interface PointerSource {
    data object Mouse : PointerSource
    data class Touch(val fingerId: FingerId, val force: TouchForce? = null) : PointerSource
    data class TabletTool(val kind: TabletToolKind, val data: TabletToolData = TabletToolData()) : PointerSource
    data object Unknown : PointerSource
}
```

This simplifies event handling compared to winit's separate `MouseInput` / `Touch` / `TabletTool` events.

## `ScreenCapture` API

Full screen/window capture API in package `org.graphiks.kadre.core.capture`:

| Type | Description |
|------|-------------|
| `ScreenCapturer` | Enumerate displays/windows, create capture sessions, manage permissions |
| `CaptureSource` | `Display(id)` or `Window(id)` |
| `CaptureSession` | `SharedFlow<CaptureFrame>` for streaming; `captureSingle()` for one-shot |
| `CaptureFrame` | Raw pixel data with metadata (size, format, stride, timestamp) |
| `PixelFormat` | RGBA8, BGRA8, NV12, BGRX8 |
| `CaptureRegion` | Sub-region capture support |

## `Gamepad` API

| Type | Description |
|------|-------------|
| `GamepadController` | Accessible via `ActiveEventLoop.gamepadController` |
| `GamepadEvent` | `ButtonPressed/Released`, `AxisChanged`, `Connected/Disconnected` |
| `GamepadState` | Per-gamepad button/axis state |
| `PowerInfo` | Battery/power state (Wired/Charging/Discharging/Charged) |
| `Button` (enum) | Standard gamepad buttons (South/East/North/West, triggers, DPad, etc.) |
| `Axis` (enum) | Left/Right stick, Z-axis, DPad axes |

Note: winit delegates gamepad handling to the `gilrs` crate — this is out of winit's own scope.

## `FrameTimingTracer`

Built-in frame timing instrumentation:

```kotlin
object FrameTimingTracing {
    var enabled: Boolean
    var slowFrameThresholdMs: Double  // default 16.7ms
    var sink: (String) -> Unit        // default println
}
```

Zero overhead when disabled. Publishes min/p50/p99/max stats every second.

## `kadre-coroutines` Module

Extension module for coroutine integration. The capture API uses `kotlinx.coroutines` (`SharedFlow`, `suspend`); the core windowing API remains purely callback-driven.

## `WindowButtons` Bitmask

```kotlin
data class WindowButtons(val bits: Int) {
    companion object {
        val NONE, CLOSE, MINIMIZE, MAXIMIZE, ALL
    }
}
```

## iOS Gesture Opt-In Pattern

```kotlin
fun recognizePinchGesture(shouldRecognize: Boolean)
fun recognizePanGesture(shouldRecognize: Boolean, minimumNumberOfTouches: Int, maximumNumberOfTouches: Int)
fun recognizeDoubleTapGesture(shouldRecognize: Boolean)
fun recognizeRotationGesture(shouldRecognize: Boolean)
```

UIKit requires explicit recognizer installation. This opt-in pattern has no equivalent in winit.
