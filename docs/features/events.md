# Events

## `WindowEvent` Mapping

| winit variant | Kadre variant | Emission Status |
|--------------|---------------|-----------------|
| `CloseRequested` | `CloseRequested` | ✅ All backends |
| `Resized(size)` | `Resized(size: PhysicalSize<Int>)` | ✅ All backends |
| `Moved(position)` | `Moved(position: PhysicalPosition<Int>)` | ✅ All backends |
| `ScaleFactorChanged(factor)` | `ScaleFactorChanged(factor: Double)` | ✅ All backends |
| `Focused(gained)` | `Focused(gained: Boolean)` | ✅ All backends |
| `KeyboardInput { event, device_id, is_synthetic }` | `KeyInput(event, deviceId)` | ✅ All backends |
| `ModifiersChanged(Modifiers)` | `ModifiersChanged(state: KeyboardModifierState)` | ✅ All backends |
| `RedrawRequested` | `RedrawRequested` | ✅ All backends |
| `Destroyed` | `Destroyed` | ✅ All backends |
| `ThemeChanged(Theme)` | `ThemeChanged(theme: Theme)` | ⚠️ AppKit, Win32 only |
| `Occluded(occluded)` | `Occluded(occluded: Boolean)` | ⚠️ AppKit, X11, Web, iOS |
| `Ime(ime)` | `Ime(ImeEvent)` | ⚠️ All backends emit (was deferred) |
| `DroppedFile(path)` / `HoveredFile(path)` / `HoveredFileCancelled` | `DragEntered(position, paths)` / `DragMoved(position)` / `DragDropped(position, paths)` / `DragLeft` | ⚠️ Win32, X11, Wayland, Web, iOS; AppKit partial |
| `PinchGesture(delta, phase)` | `PinchGesture(deviceId, delta, phase)` | ⚠️ AppKit, Win32; UIKit opt-in |
| `PanGesture(delta, phase)` | `PanGesture(deviceId, delta, phase)` | ⚠️ AppKit, Win32; UIKit opt-in |
| `RotationGesture(delta, phase)` | `RotationGesture(deviceId, deltaDegrees, phase)` | ⚠️ AppKit, Win32; UIKit opt-in |
| `DoubleTapGesture` | `DoubleTapGesture(deviceId)` | ⚠️ AppKit, Win32; UIKit opt-in |
| `TouchpadPressure(pressure, stage)` | `TouchpadPressure(deviceId, pressure, stage)` | ⚠️ AppKit only (macOS Force Touch) |

### Pointer Model Difference

Kadre uses a **unified pointer model** where winit separates `MouseInput` / `Touch` / `TabletTool`:

| winit event | Kadre event | Notes |
|------------|-------------|-------|
| `WindowEvent::CursorMoved` | `WindowEvent.PointerMoved(deviceId, position, primary, source)` | `source` encodes Mouse / Touch / TabletTool |
| `WindowEvent::CursorEntered` / `CursorLeft` | `WindowEvent.PointerEntered` / `PointerLeft` | |
| `WindowEvent::MouseInput` | `WindowEvent.PointerButton(deviceId, state, position, primary, button)` | |
| `WindowEvent::Touch` | `WindowEvent.PointerMoved` / `PointerButton` with `PointerSource.Touch` | Unified into pointer events |
| `WindowEvent::MouseWheel` | `WindowEvent.MouseWheel(deltaX, deltaY, phase)` | |
| `WindowEvent::TabletTool` events | `PointerSource.TabletTool` in pointer events | |

## `DeviceEvent` Mapping

| winit variant | Kadre variant | Status |
|--------------|---------------|--------|
| `DeviceEvent::MouseMotion { delta }` | `DeviceEvent.PointerMotion(dx, dy)` | ✅ |
| `DeviceEvent::Button { button, state }` | `DeviceEvent.Button(button, state)` | ✅ |
| `DeviceEvent::Key(RawKeyEvent)` | `DeviceEvent.Key(event: RawKeyEvent)` | ✅ |
| `DeviceEvent::MouseWheel { delta }` | `DeviceEvent.MouseWheel(deltaX, deltaY)` | ✅ |

## Event Emission Status by Backend

### Core Events (implemented on all backends)

- `CloseRequested`, `Destroyed`
- `Resized`, `Moved`, `ScaleFactorChanged`
- `Focused` (gained)
- `KeyInput` (keyboard)
- `ModifiersChanged`
- `RedrawRequested`
- `PointerMoved`, `PointerEntered`, `PointerLeft`, `PointerButton`
- `MouseWheel`

### Theme Events

| Backend | `ThemeChanged` |
|---------|:--------------:|
| AppKit | ✅ REAL |
| Win32 | ✅ REAL |
| X11 | — not emitted |
| Wayland | ✅ REAL (via portal) |
| Web | — not emitted |
| Android | — not emitted |
| UIKit | — not emitted |

### Gestures

| Backend | PinchGesture | PanGesture | RotationGesture | DoubleTapGesture | TouchpadPressure |
|---------|:-----------:|:----------:|:---------------:|:----------------:|:----------------:|
| AppKit | ✅ | ✅ | ✅ | ✅ | ✅ (Force Touch) |
| Win32 | ✅ (WM_GESTURE) | ✅ | ✅ | ✅ | — |
| X11 | ⚠️ simulated | — | — | — | — |
| Wayland | — | — | — | — | — |
| Web | — | — | — | — | — |
| Android | — | — | — | — | — |
| UIKit | ✅ opt-in | ✅ opt-in | ✅ opt-in | ✅ opt-in | — |
