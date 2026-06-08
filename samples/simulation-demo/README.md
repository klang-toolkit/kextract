# Kadre Simulation Demo

Interactive demo application showcasing the [Kadre](https://kadre.dev) windowing toolkit across mouse, keyboard, touch, window, and integration scenarios.

The demo runs as a true native window with a Compose UI renderer. Events can be injected **programmatically without AWT** — using first-class Kadre `WindowEvent` types (`KeyInput`, `PointerMoved`, `PointerButton`, etc.) delivered directly to the scenario's `onWindowEvent()` handler.

## Quick start

```shell
# List all available scenarios
./gradlew :samples:simulation-demo:run --args="--list"

# Run one scenario for 5 seconds with programmatic event injection
./gradlew :samples:simulation-demo:run --args="--scenario keyboard-basic --duration 5"

# Run all 18 scenarios sequentially and export results to JSON
./gradlew :samples:simulation-demo:run --args="--all --output results.json"

# Launch the interactive GUI mode
./gradlew :samples:simulation-demo:run
```

### CLI reference

| Flag | Effect |
|------|--------|
| `--list` | List all registered scenarios |
| `--scenario <id>` | Run a single scenario with event injection |
| `--all` | Run all scenarios sequentially |
| `--duration <sec>` | Duration per scenario (default: 5s) |
| `--output <path>` | Export results as JSON |
| `--info` | Show detailed info for a scenario |
| `--interactive` | Launch the interactive GUI |
| `-h`, `--help` | Show help |

## Scenarios

### Keyboard (5)

| ID | Title | Description |
|----|-------|-------------|
| `keyboard-basic` | Basic typing | Letters, digits and symbols — observe key press/release events |
| `keyboard-shortcuts` | Keyboard shortcuts | Modifier chords (Ctrl+C, Ctrl+V, Cmd+A, etc.) |
| `keyboard-ime` | IME input | Accented characters and CJK input via Input Method Editor |
| `keyboard-modifiers` | Modifier states | Real-time display of Shift, Ctrl, Alt, Meta/Cmd state |
| `keyboard-repeat` | Key repeat | Automatic key repeat behavior for held keys |

### Mouse (4)

| ID | Title | Description |
|----|-------|-------------|
| `mouse-clicks` | Mouse clicks | Single, double and triple clicks with all buttons |
| `mouse-drag` | Drag and drop | Press → Move → Release sequence with trajectory display |
| `mouse-scroll` | Scroll wheel | Horizontal and vertical scroll delta events |
| `mouse-cursor` | Cursor position | Position tracking, grab modes and visibility toggles |

### Window (4)

| ID | Title | Description |
|----|-------|-------------|
| `window-resize` | Resize | Window resize events with size change observations |
| `window-fullscreen` | Fullscreen | Fullscreen toggle (F key, Escape to exit) |
| `window-multi` | Multi-window | Multiple window creation and management |
| `window-focus` | Focus & events | Focus, move, minimize and restore events |

### Touch (3)

| ID | Title | Description |
|----|-------|-------------|
| `touch-single` | Single touch | Single finger press, move, release |
| `touch-multi` | Multi-touch | Simultaneous multi-finger touch events |
| `touch-gestures` | Gestures | Tap, double-tap and swipe recognition |

### Integration (2)

| ID | Title | Description |
|----|-------|-------------|
| `game-simple` | Simple game | Mini-game combining keyboard (WASD/ZQSD) and mouse (click to shoot) |
| `text-editor` | Mini text editor | Combined keyboard input, IME and shortcut handling |

Total: **18 scenarios** across **5 categories**.

## Architecture

```
┌──────────────────────────────────────────────────┐
│  runCliDemo()                                    │
│  ┌────────────────┐    ┌───────────────────────┐ │
│  │ Event collector │    │ Simulation coroutine  │ │
│  │ (real events)   │    │ (synthetic events)    │ │
│  │ win.events      │    │ ScenarioSimulator     │ │
│  │     .collect()  │    │     .simulateEvents() │ │
│  └───────┬────────┘    └───────────┬───────────┘ │
│          │                         │             │
│          ▼                         ▼             │
│  ┌──────────────────────────────────────────┐    │
│  │        Scenario.onWindowEvent(event)     │    │
│  │        (LoggingScenario wrapper logs)    │    │
│  └─────────────────┬────────────────────────┘    │
│                    ▼                             │
│  ┌──────────────────────────────────────────┐    │
│  │  CliDisplayState.eventLog (Compose UI)   │    │
│  └──────────────────────────────────────────┘    │
└──────────────────────────────────────────────────┘
```

Key design decisions:

- **No AWT, no Robot, no CGEvent** — synthetic events are Kadre `WindowEvent` instances created directly in `commonMain` and passed to `Scenario.onWindowEvent()`. The scenario has no way to distinguish them from OS-delivered events.
- **Window-level scenarios** (`window-resize`, `window-fullscreen`, `window-focus`) use the Kadre `Window` API (`requestSurfaceSize()`, `setMinimized()`) to trigger real native events.
- **Live event log** — every event (synthetic or real) is captured by the `LoggingScenario` wrapper and displayed in a scrollable monospace list in the Compose window.
- **Results** — each scenario exposes `eventsReceived` and `eventsExpected` counters via `collectResult()`, viewable on screen or exported as JSON.

## Requirements

- JDK 17+
- macOS (AppKit), Linux (X11/Wayland), or Windows (Win32)
- A desktop environment with OpenGL 3.3+ / Metal / Vulkan support
