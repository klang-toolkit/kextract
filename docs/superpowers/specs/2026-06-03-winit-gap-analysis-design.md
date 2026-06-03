# Winit Gap Analysis — Design

> Status: **Approved**
> Tracks discrepancies between winit (upstream Rust) and kadre (Kotlin reimplementation) to guide 1:1 feature parity.

## Motivation

Kadre is a pure-Kotlin reimplementation of the [winit](https://github.com/rust-windowing/winit) windowing library API.
The `winit/` submodule provides the upstream reference. This system tracks which winit features
have been ported to kadre — at both the core API level (`kadre-core`) and per-backend implementation level
(`kadre-appkit`, `kadre-win32`, etc.) — and prioritises remaining gaps.

## Storage

All gap data lives in **Basic Memory** under the `poc-koreos` project, in a `winit-gaps/` directory.

## Schema per note

Each note uses type `gap` with the following frontmatter fields:

| Field | Values | Description |
|-------|--------|-------------|
| `type` | `gap` | Note type identifier |
| `winit-module` | text (e.g. `winit-core`, `winit-appkit`) | Source crate in winit |
| `kadre-module` | text (e.g. `kadre-core`, `kadre-appkit`) | Target module in kadre |
| `category` | `api`, `event`, `platform-ext`, `dpi`, `keyboard`, `cursor`, `icon`, `monitor`, `error` | Type of feature gap |
| `core-status` | `present`, `partial`, `missing`, `n/a` | Status in `kadre-core` |
| `appkit-status` | same | Status in `kadre-appkit` |
| `win32-status` | same | Status in `kadre-win32` |
| `x11-status` | same | Status in `kadre-x11` |
| `wayland-status` | same | Status in `kadre-wayland` |
| `uikit-status` | same | Status in `kadre-uikit` |
| `android-status` | same | Status in `kadre-android` |
| `web-status` | same | Status in `kadre-js`/`kadre-wasm` |
| `priority` | `low`, `medium`, `high`, `critical` | Priority for implementing |
| `winit-ref` | text | Path to source in winit submodule |
| `kadre-ref` | text | Path to source in kadre |

### Status semantics

- **present**: Feature exists with matching semantics.
- **partial**: Feature exists but with limitations (documented in note body).
- **missing**: Feature not yet implemented.
- **n/a**: Not applicable to this backend (e.g. `set_tabbing_identifier` on X11).

## Directory and note layout

```
winit-gaps/
├── _index.md              ← Summary with gap counts per backend and priority
├── core-api.md            ← Window trait methods, ActiveEventLoop methods
├── events.md              ← WindowEvent (26 variants), DeviceEvent (4 variants)
├── dpi.md                 ← PhysicalSize, LogicalSize, scaleFactor
├── keyboard.md            ← Key, PhysicalKey, Modifiers, IME, keyboard-types
├── cursor.md              ← CursorIcon, CustomCursor, CursorGrabMode
├── icon.md                ← Icon types
├── monitor.md             ← MonitorHandle, VideoMode, Fullscreen
├── error.md               ← EventLoopError, RequestError, OsError
├── appkit-platform.md     ← macOS extensions (tabbing, fullscreen, ActivationPolicy)
├── win32-platform.md      ← Windows extensions (backdrop, corners, taskbar icon)
├── x11-platform.md        ← X11 extensions (visual, window type, override_redirect)
├── wayland-platform.md    ← Wayland extensions (CSD, decoration, activation token)
├── uikit-platform.md      ← iOS extensions (gestures, orientation, status bar)
├── android-platform.md    ← Android extensions (content rect, volume keys)
└── web-platform.md        ← Web extensions (poll strategy, canvas options)
```

Each note body contains a markdown table listing every relevant feature, its status
across core and all backends, and any implementation notes.

## Relations

Backend notes are linked to core notes via the `has_backend` relation:

```
core-api.md ──has_backend──> appkit-platform.md
core-api.md ──has_backend──> win32-platform.md
core-api.md ──has_backend──> x11-platform.md
core-api.md ──has_backend──> wayland-platform.md

events.md ────has_backend──> appkit-platform.md
events.md ────has_backend──> win32-platform.md
...
```

This enables queries like "what core features are missing on Wayland?" by
traversing from `core-api.md` to its backend notes.

## Maintenance workflow

1. **Initial population**: For each note, inspect winit source (submodule at `winit/`)
   and kadre source to determine status for each feature.
2. **Updates**: After implementing a feature, update the corresponding note's
   status field(s) and commit.
3. **Review**: Periodically re-sync with upstream winit to detect new features.

## Success criteria

- All 15 notes exist in Basic Memory with populated tables.
- Each status field is accurate (verified against actual source).
- Priority field reflects actual implementation order.
