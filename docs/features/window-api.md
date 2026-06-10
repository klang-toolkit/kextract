# Window API

## `Window` trait/interface mapping

### Core Identity & Control

| winit API | Kadre API | Status | Notes |
|-----------|-----------|--------|-------|
| `Window::id()` | `Window.id: WindowId` | ✅ Implemented | |
| `Window::request_redraw()` | `requestRedraw()` | ✅ Implemented | |
| `Window::title()` / `set_title()` | `title` / `setTitle()` | ✅ Implemented | |
| `Window::inner_size()` | `innerSize: PhysicalSize<Int>` | ✅ Implemented | |
| `Window::outer_size()` | `outerSize: PhysicalSize<Int>` | ✅ Implemented | |
| `Window::scale_factor()` | `scaleFactor: Double` | ✅ Implemented | |
| `Window::set_visible()` | `setVisible(Boolean)` | ✅ Implemented | |
| `Window::is_visible()` | `isVisible: Boolean?` | ✅ Implemented | Nullable mirrors `Option<bool>` |
| `Window::close()` | `close()` | ✅ Implemented | |
| `Window::pre_present_notify()` | `prePresentNotify()` | ✅ Implemented | |

### Window State & Geometry

| winit API | Kadre API | Status | Notes |
|-----------|-----------|--------|-------|
| `Window::is_resizable()` / `set_resizable()` | `isResizable` / `setResizable()` | ✅ Implemented | |
| `Window::is_minimized()` / `set_minimized()` | `isMinimized: Boolean?` / `setMinimized()` | ✅ Implemented | Nullable for unknown state |
| `Window::is_maximized()` / `set_maximized()` | `isMaximized` / `setMaximized()` | ✅ Implemented | |
| `Window::is_decorated()` / `set_decorations()` | `isDecorated` / `setDecorations()` | ✅ Implemented | |
| `Window::outer_position()` / `set_outer_position()` | `outerPosition` / `setOuterPosition()` | ✅ Implemented | |
| `Window::surface_position()` | `surfacePosition: PhysicalPosition<Int>` | ✅ Implemented | |
| `Window::surface_size()` | `surfaceSize: PhysicalSize<Int>` | ✅ Implemented | |
| `Window::request_surface_size()` | `requestSurfaceSize(): SurfaceSizeRequestResult` | ✅ Implemented | Kadre adds `SurfaceSizeRequestResult` type |
| `Window::safe_area()` | `safeArea: Insets<Int>` | ✅ Implemented | Defaults to `Insets(0,0,0,0)` |
| `Window::set_min_surface_size()` | `setMinSurfaceSize()` | ✅ Implemented | |
| `Window::set_max_surface_size()` | `setMaxSurfaceSize()` | ✅ Implemented | |
| `Window::surface_resize_increments()` / `set_surface_resize_increments()` | `surfaceResizeIncrements` / `setSurfaceResizeIncrements()` | ✅ Implemented | |
| `WindowButtons` / `set_enabled_buttons()` | `WindowButtons` / `setEnabledButtons()` | ✅ Implemented | AppKit/Win32 wired; X11/Wayland no-op |

### Raw Handles

| winit API | Kadre API | Status | Notes |
|-----------|-----------|--------|-------|
| `Window::raw_window_handle()` | `rawWindowHandle: RawWindowHandle` | ✅ Implemented | 7 platform variants |
| `Window::raw_display_handle()` | `rawDisplayHandle: RawDisplayHandle` | ✅ Implemented | 7 platform variants |
| `owned_display_handle()` | `ownedDisplayHandle(): OwnedDisplayHandle?` | 🔶 Deferred | winit non-null; Kadre returns `null` by default |

### Monitor & Fullscreen

| winit API | Kadre API | Status | Notes |
|-----------|-----------|--------|-------|
| `Window::current_monitor()` | `currentMonitor(): MonitorHandle?` | ✅ Implemented | |
| `Window::available_monitors()` | `availableMonitors(): List<MonitorHandle>` | ✅ Implemented | Falls back to `emptyList()` |
| `Window::primary_monitor()` | `primaryMonitor(): MonitorHandle?` | ✅ Implemented | Falls back to `null` |
| `Window::fullscreen()` / `set_fullscreen()` | `fullscreen` / `setFullscreen()` | ✅ Implemented | |
| `Fullscreen::Borderless(Option<MonitorHandle>)` | `Fullscreen.Borderless(monitor?)` | ✅ Implemented | All backends |
| `Fullscreen::Exclusive(MonitorHandle, VideoMode)` | `Fullscreen.Exclusive(monitor, videoMode)` | ❌ UnsupportedPlatform | Falls back to Borderless on Wayland/Web/mobile |

### Focus

| winit API | Kadre API | Status | Notes |
|-----------|-----------|--------|-------|
| `Window::focus_window()` | `focusWindow()` | ✅ Implemented | |
| `Window::has_focus()` | `hasFocus: Boolean` | ✅ Implemented | |

### Cursor

| winit API | Kadre API | Status | Notes |
|-----------|-----------|--------|-------|
| `Window::set_cursor(CursorIcon)` | `setCursor(CursorIcon)` | ✅ Implemented | No-throw Unit setter |
| `Window::set_cursor_visible(bool)` | `setCursorVisible(Boolean)` | ✅ Implemented | No-throw Unit setter |
| `Window::set_cursor_grab(CursorGrabMode)` | `setCursorGrab(CursorGrabMode): WindowRequestResult` | ✅ Implemented | Returns typed result |
| `Window::set_cursor_position(PhysicalPosition)` | `setCursorPosition(PhysicalPosition): WindowRequestResult` | ✅ Implemented | Returns typed result |
| `Window::set_cursor_hittest(bool)` | `setCursorHittest(Boolean): WindowRequestResult` | ✅ Implemented | Returns typed result |
| `CursorImage` / `CustomCursor` | `CursorImage` / `CustomCursor` | ✅ Implemented | Runtime no-op on mobile |

### Theme & Appearance

| winit API | Kadre API | Status | Notes |
|-----------|-----------|--------|-------|
| `Window::theme()` / `set_theme()` | `theme` / `setTheme()` | ✅ Implemented | |
| `Window::set_window_level()` | `setWindowLevel(WindowLevel)` | ✅ Implemented | |
| `Window::set_transparent()` | `setTransparent(Boolean)` | ✅ Implemented | |
| `Window::set_blur()` | `setBlur(Boolean)` | ✅ Implemented | |
| `Window::set_window_icon()` | `setWindowIcon(Icon?)` | ✅ Implemented | |
| `Window::request_user_attention()` | `requestUserAttention(UserAttentionType?): WindowRequestResult` | 🔶 Deferred | |
| `Window::set_content_protected()` | `setContentProtected(Boolean): WindowRequestResult` | 🔶 Deferred | |

### IME

| winit API | Kadre API | Status | Notes |
|-----------|-----------|--------|-------|
| `Window::set_ime_allowed(bool)` | `setImeAllowed(Boolean)` | ✅ Implemented | |
| `Window::set_ime_cursor_area(position, size)` | `setImeCursorArea(position, size)` | ✅ Implemented | |
| `Window::set_ime_purpose(ImePurpose)` | `setImePurpose(ImePurpose)` | ✅ Implemented | |
| `Window::ime_capabilities()` | absent | 🔶 Deferred | Kadre has `inputCapabilities()` for device features |

### Window Management Requests

| winit API | Kadre API | Status | Notes |
|-----------|-----------|--------|-------|
| `Window::drag_window()` | `dragWindow(): WindowRequestResult` | 🔶 Deferred | |
| `Window::drag_resize_window(ResizeDirection)` | `dragResizeWindow(ResizeDirection): WindowRequestResult` | 🔶 Deferred | |
| `Window::show_window_menu(PhysicalPosition)` | `showWindowMenu(PhysicalPosition): WindowRequestResult` | 🔶 Deferred | |
| `Window::reset_dead_keys()` | `resetDeadKeys()` | ✅ Implemented | |

### Result / Error Types

| winit | Kadre | Status |
|-------|-------|--------|
| `Result<(), RequestError>` | `WindowRequestResult` (Success/Failure) | ✅ |
| `RequestError::Unsupported` | `RequestError.Unsupported` | ✅ |
| `RequestError::Ignored` | `RequestError.Ignored` | ✅ Kadre addition |
| `RequestError::PermissionDenied` | `RequestError.PermissionDenied` | ✅ |
| `RequestError::OsError` | `RequestError.OsError` | ✅ |
| — | `SurfaceSizeRequestResult` (Applied/Pending/Failure) | ✅ Kadre addition |

## `WindowAttributes`

| winit method | Kadre field | Status |
|-------------|-------------|--------|
| `with_title()` | `title: String` | ✅ |
| `with_inner_size()` | `size: PhysicalSize<Int>?` | ✅ |
| `with_visible()` | `visible: Boolean` | ✅ |
| `with_resizable()` | `resizable: Boolean` | ✅ |
| `with_min_inner_size()` | `minSize: PhysicalSize<Int>?` | ✅ |
| `with_max_inner_size()` | `maxSize: PhysicalSize<Int>?` | ✅ |
| `with_position()` | `position: PhysicalPosition<Int>?` | ✅ |
| `with_fullscreen()` | `fullscreen: Fullscreen?` | ✅ |
| `with_decorations()` | `decorations: Boolean` | ✅ |
| `with_activated()` | `active: Boolean` | ✅ |
| `with_cursor()` | `cursor: CursorIcon` | ✅ |
| `with_theme()` | `preferredTheme: Theme?` | ✅ |
| `with_transparent()` | `transparent: Boolean` | ✅ |
| `with_blur()` | `blur: Boolean` | ✅ |
| `with_window_level()` | `windowLevel: WindowLevel` | ✅ |
| `with_window_icon()` | `windowIcon: Icon?` | ✅ |
| `with_name()` (Wayland app ID) | `name: String?` | ✅ |
| `with_parent_window()` | `parentWindow: RawWindowHandle?` | ✅ |
| (resize increments) | `resizeIncrements: PhysicalSize<Int>?` | ✅ |
| (maximized) | `maximized: Boolean` | ✅ |
| (enabled buttons) | `enabledButtons: WindowButtons` | ✅ |

## `ActiveEventLoop`

| winit API | Kadre API | Status | Notes |
|-----------|-----------|--------|-------|
| `create_window(WindowAttributes)` | `createWindow(WindowAttributes): Window` | ✅ Implemented | |
| `control_flow()` / `set_control_flow()` | `controlFlow: ControlFlow` / `setControlFlow()` | ✅ Implemented | Wait / Poll / WaitUntil |
| `exit()` / `is_exiting()` | `exit()` / `isExiting` | ✅ Implemented | |
| `create_proxy()` | `createProxy(): EventLoopProxy` | ✅ Implemented | |
| `available_monitors()` | `availableMonitors(): List<MonitorHandle>` | ✅ Implemented | |
| `primary_monitor()` | `primaryMonitor(): MonitorHandle?` | ✅ Implemented | |
| `owned_display_handle()` | `ownedDisplayHandle(): OwnedDisplayHandle?` | 🔶 Deferred | winit non-null; Kadre nullable |
| `system_theme()` | `systemTheme(): Theme?` | ✅ Implemented | |
| `create_custom_cursor(CursorImage)` | `createCustomCursor(CursorImage): CustomCursor?` | 🔶 Deferred | No-op by default |
| `listen_device_events(DeviceEvents)` | `listenDeviceEvents(DeviceEvents)` | ✅ Implemented | |

## `MonitorHandle` & `VideoMode`

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
