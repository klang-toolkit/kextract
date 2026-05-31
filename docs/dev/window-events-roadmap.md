# Window events — roadmap

Inventory of Kadre window/device events: what's defined, what each backend actually emits, and
what we can add. The callback `ApplicationHandler` / `WindowEvent` model follows winit.

## Emission matrix (defined events)

✓ emitted · ✗ missing (to add) · — n/a on this platform

| Event                | appkit | win32 | x11 | wayland |
|----------------------|:------:|:-----:|:---:|:-------:|
| CloseRequested       |   ✓    |   ✓   |  ✓  |    ✗    |
| Resized              |   ✓    |   ✓   |  ✓  |    ✗    |
| Moved                |   ✗    |   ✗   |  ✓  |    —    |
| ScaleFactorChanged   |   ✓    |   ✓   |  ✗  |    ✗    |
| Focused              |   ✗    |   ✗   |  ✓  |    ✗    |
| KeyboardInput        |   ✓    |   ✓   |  ✓  |    ✓    |
| PointerMoved         |   ✓    |   ✓   |  ✓  |    ✓    |
| PointerEntered       |   ✓    |   ✗   |  ✓  |    ✓    |
| PointerLeft          |   ✓    |   ✓   |  ✓  |    ✓    |
| MouseInput           |   ✓    |   ✓   |  ✓  |    ✓    |
| MouseWheel           |   ✓    |   ✓   |  ✓  |    ✓    |
| Touch                |   ✗    |   ✗   |  ✗  |    ✗    |
| RedrawRequested      |   ✓    |   ✓   |  ✓  |    ✗    |
| Destroyed            |   ✗    |   ✓   |  ✓  |    ✗    |

`DeviceEvent` (`PointerMotion`, `Button`, `Key`) is emitted **only by appkit** today.

> Note: Wayland clients cannot know their global window position → `Moved` is not emittable on
> Wayland by design.

## Phases

### Phase 1 — Moved · Focused · Destroyed (appkit + win32)  ← in progress
High value, low effort, testable on macOS.

- **Moved**
  - appkit: `windowDidMove:` → `WindowEvent.Moved(frame.origin × scale)`
  - win32: `WM_MOVE` → `Moved(LOWORD/HIWORD signed)`
- **Focused**
  - appkit: `windowDidBecomeKey:` → `Focused(true)`, `windowDidResignKey:` → `Focused(false)`
  - win32: `WM_SETFOCUS` → `Focused(true)`, `WM_KILLFOCUS` → `Focused(false)`
- **Destroyed**
  - appkit: emit in `windowWillClose:` (already removes the window from the map)

### Phase 2 — Linux completion
- wayland: `CloseRequested` (xdg_toplevel close), `Resized` (xdg configure), `Focused`
  (wl_keyboard enter/leave), `RedrawRequested` (frame callback), `Destroyed`, `Moved` is n/a.
- x11 + wayland: `ScaleFactorChanged` (XSETTINGS `Xft.dpi` / RandR; wl_output / fractional-scale).
- win32: `PointerEntered` (first `WM_MOUSEMOVE` after a leave, via TrackMouseEvent — already armed).

### Phase 3 — Touch
- appkit: `touchesBegan/Moved/Ended/Cancelled:` (trackpad), or NSTouch.
- win32: `WM_POINTER*` (or legacy `WM_TOUCH`).
- x11/wayland: XInput2 touch / `wl_touch`.

### Phase 4 — New event types (extend the model, winit parity)
- **ModifiersChanged** — modifier set changed on its own.
- **Ime** (`Enabled` / `Preedit` / `Commit` / `Disabled`) — composed text input (CJK, accents).
- **File drag-and-drop** — `DroppedFile`, `HoveredFile`, `HoveredFileCancelled`.
- **ThemeChanged** — light/dark.
- **Occluded** — window hidden/covered (suspend rendering).
- **Touchpad gestures** — `PinchGesture`, `RotationGesture`, `PanGesture`, `DoubleTapGesture`.
- **AxisMotion** — raw axis (device level).

## Notes
- New `WindowEvent` variants are added in `kadre-core` (commonMain) and are public API → update
  the ABI dumps (`*/api/*.api`, `updateKotlinAbi`).
- AppKit delegate methods are wired in `KadreWindowDelegate` (ObjC subclass + upcall stubs).
- Win32 messages are handled in `KadreWndProc.dispatch` with constants in `Win32Constants`.
