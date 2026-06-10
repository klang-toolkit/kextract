# Fullscreen & Monitors

## Fullscreen Modes

| winit | Kadre | Status |
|-------|-------|--------|
| `Fullscreen::Borderless(Option<MonitorHandle>)` | `Fullscreen.Borderless(monitor?)` | ✅ All backends |
| `Fullscreen::Exclusive(MonitorHandle, VideoMode)` | `Fullscreen.Exclusive(monitor, videoMode)` | ❌ UnsupportedPlatform on Wayland/Web/mobile |

### Fullscreen Platform Matrix

| Backend | Borderless | Exclusive |
|---------|:----------:|:---------:|
| AppKit | ✅ `toggleFullScreen` | ✅ `toggleFullScreen` |
| Win32 | ✅ WS_POPUP + SetWindowPos | ⚠️ `ChangeDisplaySettingsExW` TODO |
| X11 | ✅ `_NET_WM_STATE_FULLSCREEN` | ✅ `_NET_WM_STATE_FULLSCREEN` |
| Wayland | ✅ `xdg_toplevel.set_fullscreen` | 🔶 no-op (falls back to borderless) |
| Web | ✅ `element.requestFullscreen()` | 🔶 no-op |
| Android | ✅ immersive mode (API 30+) | 🔶 no-op |
| UIKit | 🔶 no-op (app is always fullscreen) | 🔶 no-op |

## Monitor & Video Mode

| winit | Kadre | Status |
|-------|-------|--------|
| `MonitorHandle` (trait) | `MonitorHandle` (interface) | ✅ |
| `MonitorHandle.id()` | `id: Long` | ✅ |
| `MonitorHandle.name()` | `name: String?` | ✅ |
| `MonitorHandle.position()` | `position: PhysicalPosition<Int>` | ✅ |
| `MonitorHandle.scale_factor()` | `scaleFactor: Double` | ✅ |
| `MonitorHandle.video_modes()` | `videoModes: List<VideoMode>` | ✅ |
| `MonitorHandle.current_video_mode()` | `currentVideoMode: VideoMode?` | ✅ |
| `VideoMode` | `VideoMode(size, bitDepth?, refreshRateMilliHz?)` | ✅ |

### Monitor Platform Matrix

| Backend | `availableMonitors()` | `primaryMonitor()` | `VideoMode` refresh rate |
|---------|:---------------------:|:------------------:|:------------------------:|
| AppKit | ✅ `NSScreen.screens` | ✅ `NSScreen.mainScreen` | ⚠️ missing |
| Win32 | ✅ `EnumDisplayMonitors` | ✅ | ✅ `EnumDisplaySettings` |
| X11 | ✅ XRandR / Xinerama | ✅ XRandR primary | ✅ XRandR |
| Wayland | 🔶 synthetic (wl_output TODO) | ❌ null (no primary concept) | 🔶 synthetic |
| Web | ✅ synthetic | ✅ synthetic | ❌ |
| Android | ✅ synthetic `DisplayMetrics` | ✅ synthetic | ❌ |
| UIKit | ✅ synthetic `UIScreen.mainScreen` | ✅ synthetic | ❌ |
