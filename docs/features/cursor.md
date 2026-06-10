# Cursor

## Cursor API Mapping

| winit API | Kadre API | Status | Notes |
|-----------|-----------|--------|-------|
| `CursorIcon` (enum) | `CursorIcon` (enum) | ✅ | 25+ shapes (same as winit) |
| `CursorGrabMode` (None/Confined/Locked) | `CursorGrabMode` (None/Confined/Locked) | ✅ | |
| `Window::set_cursor(CursorIcon)` | `Window.setCursor(CursorIcon)` | ✅ | No-throw Unit setter |
| `Window::set_cursor_visible(bool)` | `Window.setCursorVisible(Boolean)` | ✅ | No-throw Unit setter |
| `Window::set_cursor_grab(CursorGrabMode)` | `Window.setCursorGrab(CursorGrabMode): WindowRequestResult` | ✅ | Returns typed result |
| `Window::set_cursor_position(PhysicalPosition)` | `Window.setCursorPosition(PhysicalPosition): WindowRequestResult` | ✅ | Returns typed result |
| `Window::set_cursor_hittest(bool)` | `Window.setCursorHittest(Boolean): WindowRequestResult` | ✅ | Returns typed result |
| `CursorImage` | `CursorImage(rgba, width, height, hotspotX, hotspotY)` | ✅ | RGBA image data |
| `CustomCursor` (handle) | `CustomCursor(id: Long)` | ✅ | API defined, runtime no-op on mobile |
| `ActiveEventLoop.create_custom_cursor()` | `ActiveEventLoop.createCustomCursor(CursorImage): CustomCursor?` | 🔶 Deferred | No-op by default |

## Cursor Platform Matrix

| Feature | AppKit | Win32 | X11 | Wayland | Web | Android | UIKit |
|---------|:------:|:-----:|:---:|:-------:|:---:|:-------:|:-----:|
| **CursorIcon** (25 shapes) | ✅ `NSCursor` class methods | ✅ `LoadCursorW` + `SetCursor` | ✅ `XCreateFontCursor` cache | ✅ `libwayland-cursor` | ✅ CSS `cursor` | 🔶 no-op | 🔶 no-op |
| **CursorGrab.Confined** | ❌ unsupported (winit parity) | ✅ `ClipCursor` | ✅ `XGrabPointer` | ✅ `zwp_pointer_constraints_v1` | ❌ unsupported | 🔶 no-op | 🔶 no-op |
| **CursorGrab.Locked** | ✅ `CGAssociateMouseAndMouseCursorPosition` | ✅ `ClipCursor` | ✅ `XGrabPointer` | ✅ `zwp_pointer_constraints_v1` | ❌ unsupported (Pointer Lock bridge TODO) | 🔶 no-op | 🔶 no-op |
| **CursorVisible** | ✅ `NSCursor.hide/unhide` | ⚠️ `ShowCursor` not rebalanced | ✅ transparent pixmap 1x1 | ✅ `wl_pointer.set_cursor(NULL)` | ✅ CSS `cursor: none` | 🔶 no-op | 🔶 no-op |
| **CursorPosition** | ⚠️ `CGWarpMouseCursorPosition` (scalar cast) | ✅ `SetCursorPos` | ✅ `XWarpPointer` | ❌ unsupported (Wayland limitation) | ❌ unsupported | ❌ unsupported | ❌ unsupported |
| **CursorHittest** | ✅ `setIgnoresMouseEvents` | ✅ `WS_EX_TRANSPARENT` | ✅ `XShapeCombineRectangles` | ✅ `wl_surface.set_input_region` | ❌ unsupported (CSS `pointer-events` TODO) | ❌ unsupported | ❌ unsupported |
| **CustomCursor** (RGBA) | ✅ CoreGraphics CGImage→NSCursor | ✅ `CreateIcon` RGBA→BGRA | ✅ monochrome XBM | ✅ `wl_shm` buffer | ✅ CSS `cursor: url(dataUrl)` | 🔶 no-op | 🔶 no-op |

## Theme & Appearance

### Theme API Mapping

| winit API | Kadre API | Status |
|-----------|-----------|--------|
| `Theme` (Light/Dark) | `Theme` (Light/Dark) | ✅ |
| `Window::theme()` | `Window.theme: Theme?` | ✅ |
| `Window::set_theme(Theme?)` | `Window.setTheme(Theme?)` | ✅ |
| `ActiveEventLoop.system_theme()` | `ActiveEventLoop.systemTheme(): Theme?` | ✅ |
| `WindowEvent::ThemeChanged(Theme)` | `WindowEvent.ThemeChanged(theme: Theme)` | ✅ |

### Window Appearance API Mapping

| winit API | Kadre API | Status |
|-----------|-----------|--------|
| `WindowLevel` | `WindowLevel` (AlwaysOnBottom/Normal/AlwaysOnTop) | ✅ |
| `Window::set_window_level()` | `Window.setWindowLevel(WindowLevel)` | ✅ |
| `Window::set_transparent(bool)` | `Window.setTransparent(Boolean)` | ✅ |
| `Window::set_blur(bool)` | `Window.setBlur(Boolean)` | ✅ |
| `Window::set_window_icon(Icon)` | `Window.setWindowIcon(Icon?)` | ✅ |
| `UserAttentionType` | `UserAttentionType` (Critical/Informational) | ✅ |
| `Window::request_user_attention(UserAttentionType?)` | `Window.requestUserAttention(UserAttentionType?): WindowRequestResult` | 🔶 |
| `Window::set_content_protected(bool)` | `Window.setContentProtected(Boolean): WindowRequestResult` | 🔶 |

### Theme & Appearance Platform Matrix

| Feature | AppKit | Win32 | X11 | Wayland | Web | Android | UIKit |
|---------|:------:|:-----:|:---:|:-------:|:---:|:-------:|:-----:|
| **systemTheme()** | ✅ `NSApp.effectiveAppearance` | ✅ Registry `AppsUseLightTheme` | ❌ null (no standard) | ✅ D-Bus portal | ✅ `matchMedia` | ✅ `UiModeManager` | ❌ null |
| **setTheme()** per-window | ✅ `NSAppearance` | ✅ `DwmSetWindowAttribute` | ✅ `_GTK_THEME_VARIANT` | 🔶 no-op | 🔶 no-op | 🔶 no-op | ✅ `overrideUserInterfaceStyle` |
| **ThemeChanged** event | ✅ | ✅ `WM_SETTINGCHANGE` | — | ✅ portal signal | — | — | — |
| **setWindowLevel()** | ✅ `NSWindow.setLevel` | ✅ `SetWindowPos` HWND_TOPMOST | ✅ `_NET_WM_STATE_ABOVE/BELOW` | 🔶 no-op | 🔶 no-op | 🔶 no-op | 🔶 no-op |
| **setTransparent()** | ✅ `setOpaque(false)` + backgroundColor | ✅ `WS_EX_LAYERED` + `SetLayeredWindowAttributes` | 🔶 no-op (`_NET_WM_WINDOW_OPACITY` TODO) | ✅ `wl_surface.set_opaque_region(NULL)` | 🔶 no-op | 🔶 no-op | 🔶 no-op |
| **setBlur()** | ✅ `NSVisualEffectView` | 🔶 no-op (DWM deprecated) | 🔶 no-op | ✅ `ext_background_effect` / KWin blur | 🔶 no-op | 🔶 no-op | 🔶 no-op |
| **setWindowIcon()** | 🔶 no-op (winit parity) | ✅ `WM_SETICON` | ✅ `_NET_WM_ICON` | ✅ `xdg_toplevel_icon_manager_v1` | 🔶 no-op | 🔶 no-op | 🔶 no-op |
| **requestUserAttention** | ✅ Dock bounce | ✅ `FlashWindowEx` | ✅ `WM_HINTS` urgent | 🔶 no-op (`xdg_activation_v1` TODO) | — | — | — |
| **setContentProtected** | ✅ | ✅ `WDA_EXCLUDED` | ✅ success no-op | ✅ success no-op | — | — | — |
