# Screen Capture Demo

A sample application demonstrating the Kadre ScreenCapturer API across multiple platforms.

## Features

- Enumerate available displays and windows
- Capture frames from displays or windows
- Save captured frames as PNG files
- Works on Windows, macOS, Linux (X11/Wayland), Android, and iOS

## Usage

### JVM (Windows, macOS, Linux)

```bash
# List available displays
./gradlew :samples:screen-capture-demo:run --args="--list-displays"

# List available windows
./gradlew :samples:screen-capture-demo:run --args="--list-windows"

# Capture from a display
./gradlew :samples:screen-capture-demo:run --args="--capture-display 0 --output ./captures"

# Capture from a window
./gradlew :samples:screen-capture-demo:run --args="--capture-window 12345678 --output ./captures"
```

### Android

The Android app provides a simple UI with buttons to:
- List available displays
- List available windows (if supported)
- Capture from a display

Note: Android requires the `MEDIA_PROJECTION` permission, which will be requested at runtime.

### iOS

The iOS app demonstrates screen capture using ReplayKit. Note that:
- Screen capture on iOS requires user permission
- The simulator may not support all capture features
- Real device testing is recommended

## Platform Support

| Platform | Display Capture | Window Capture | Notes |
|----------|----------------|----------------|-------|
| Windows | ✅ | ✅ | Uses DXGI/GDI |
| macOS | ✅ | ✅ | Uses ScreenCaptureKit |
| Linux X11 | ✅ | ✅ | Uses XShm/XComposite |
| Linux Wayland | ✅ | ⚠️ | Uses xdg-desktop-portal (window capture requires portal support) |
| Android | ✅ | ❌ | Uses MediaProjection (display only) |
| iOS | ✅ | ❌ | Uses ReplayKit (display only) |

## Implementation Details

### Wayland xdg-desktop-portal

The Wayland implementation uses xdg-desktop-portal's ScreenCast interface for broader compatibility with GNOME, KDE, and other desktop environments. This is used as a fallback to the existing wlr-screencopy implementation which only works with wlroots-based compositors (Sway, Hyprland, etc.).

The portal flow:
1. Create a session with `CreateSession`
2. Select sources with `SelectSources` (triggers user prompt)
3. Start the session with `Start`
4. Receive frames via PipeWire

### Error Handling

The sample demonstrates proper error handling for:
- Missing screen capturer on a platform
- No displays/windows available
- Permission denied errors
- Frame capture failures

## Requirements

### Linux (Wayland)

For xdg-desktop-portal support:
```bash
# Ubuntu/Debian
sudo apt-get install -y xdg-desktop-portal xdg-desktop-portal-gnome pipewire

# Fedora
sudo dnf install -y xdg-desktop-portal xdg-desktop-portal-gnome pipewire
```

### Android

Requires API level 21+ and the following permissions:
- `RECORD_AUDIO` (for audio capture, if needed)
- `WRITE_EXTERNAL_STORAGE` (for saving captures)
- `FOREGROUND_SERVICE` (for MediaProjection)
- `SYSTEM_ALERT_WINDOW` (for screen capture overlay)

## Building

```bash
# Build all platforms
./gradlew :samples:screen-capture-demo:build

# Run on JVM
./gradlew :samples:screen-capture-demo:run --args="--help"

# Install on Android
./gradlew :samples:screen-capture-demo:installDebug
```

## Troubleshooting

### Wayland: No portal available

Ensure xdg-desktop-portal is running:
```bash
# Check if portal is running
ps aux | grep xdg-desktop-portal

# Restart portal (GNOME)
killall xdg-desktop-portal-gnome
xdg-desktop-portal-gnome &
```

### Permission denied on macOS

Ensure Screen Recording permission is granted in:
- System Settings > Privacy & Security > Screen Recording
- Add your terminal app or IDE to the allowed list

### No displays found

Some platforms may require a running display server:
- X11: Ensure X server is running
- Wayland: Ensure compositor is running
- Android: Run on a real device (emulator may have limitations)

## License

This sample is part of the Kadre project and is licensed under the same terms.
