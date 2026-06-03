# Platform Extensions Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Add all missing platform-specific window/event-loop extensions across all 7 Kadre backends.

**Architecture:** Each platform module gets (a) a `*WindowAttributes` data class wrapping `WindowAttributes` via `core` field, (b) platform-specific enums/data classes for extension types, (c) extension functions on `Window`/`ActiveEventLoop` for runtime getters/setters, (d) overloaded `createWindow(...)` on the EventLoop.

**Tech Stack:** Kotlin Multiplatform, platform-specific APIs (Win32 via JNA, AppKit via UIKit, X11 via xlib/ffm, Wayland via ffm, Web via Kotlin/JS/Wasm, UIKit via Kotlin/Native, Android via Kotlin/Android)

**Order:** Win32 → AppKit → X11 → Wayland → Web → iOS → Android

---

### Task 1: Win32 Platform Extensions

**Files:**
- Create: `kadre-win32/src/jvmMain/kotlin/org/graphiks/kadre/win32/Win32ExtensionTypes.kt`
- Modify: `kadre-win32/src/jvmMain/kotlin/org/graphiks/kadre/win32/Win32EventLoop.kt`
- Modify: `kadre-win32/src/jvmMain/kotlin/org/graphiks/kadre/win32/Win32Window.kt`

- [ ] **Step 1: Create Win32ExtensionTypes.kt** — data classes and enums
  - `SystemBackdrop` enum: Auto, None, MainWindow, TransientWindow, TabbedWindow
  - `CornerPreference` enum: Default, DoNotRound, Round, RoundSmall
  - `Win32WindowAttributes` data class wrapping `WindowAttributes`
  - Extension functions: `Window.setSystemBackdrop()`, `Window.setCornerPreference()`, `Window.setBorderColor()`, `Window.setTitleBackgroundColor()`, `Window.setTitleTextColor()`, `Window.setSkipTaskbar()`, `Window.setUndecoratedShadow()`, `Window.setEnable()`, `Window.setUseSystemScrollSpeed()`

- [ ] **Step 2: Modify Win32EventLoop** — add overloaded `createWindow(Win32WindowAttributes)`

- [ ] **Step 3: Modify Win32Window** — wire runtime extension methods to native Win32 API calls

- [ ] **Step 4: Compile check**

Run: `./gradlew :kadre-win32:compileKotlinJvm` — must succeed

---

### Task 2: AppKit Platform Extensions

**Files:**
- Create: `kadre-appkit/src/jvmMain/kotlin/org/graphiks/kadre/appkit/AppKitExtensionTypes.kt`
- Modify: `kadre-appkit/src/jvmMain/kotlin/org/graphiks/kadre/appkit/AppKitEventLoop.kt`
- Modify: `kadre-appkit/src/jvmMain/kotlin/org/graphiks/kadre/appkit/AppKitWindow.kt`

- [ ] **Step 1: Create AppKitExtensionTypes.kt**
  - `ActivationPolicy` enum: Regular, Accessory, Prohibited
  - `AppKitWindowAttributes` data class wrapping `WindowAttributes`
  - Extension functions: `Window.setSimpleFullscreen()`, `Window.setHasShadow()`, `Window.setTabbingIdentifier()`, `Window.nsWindow()`, `ActiveEventLoopExtMacOS.hideApplication()`, `ActiveEventLoopExtMacOS.hideOtherApplications()`
  - `ActiveEventLoop.setActivationPolicy()`, `ActiveEventLoop.setDefaultMenu()`, `ActiveEventLoop.setAllowsAutomaticWindowTabbing()`

- [ ] **Step 2: Modify AppKitEventLoop** — add overloaded `createWindow(AppKitWindowAttributes)`

- [ ] **Step 3: Modify AppKitWindow** — wire runtime extension methods to native AppKit API calls

- [ ] **Step 4: Compile check**

Run: `./gradlew :kadre-appkit:compileKotlinJvm` — must succeed

---

### Task 3: X11 Platform Extensions

**Files:**
- Create: `kadre-x11/src/jvmMain/kotlin/org/graphiks/kadre/x11/X11ExtensionTypes.kt`
- Modify: `kadre-x11/src/jvmMain/kotlin/org/graphiks/kadre/x11/X11EventLoop.kt`
- Modify: `kadre-x11/src/jvmMain/kotlin/org/graphiks/kadre/x11/X11Window.kt`

- [ ] **Step 1: Create X11ExtensionTypes.kt**
  - `WindowType` enum: Desktop, Dock, Toolbar, Menu, Utility, Splash, Dialog, DropdownMenu, PopupMenu, Tooltip, Notification, Combo, Dnd, Normal
  - `X11WindowAttributes` data class wrapping `WindowAttributes`
  - Extension functions: `Window.setWindowType()`, `Window.setOverrideRedirect()`, `Window.x11Window()`, `ActiveEventLoopExtX11.isX11()`

- [ ] **Step 2: Modify X11EventLoop** — wire through new attributes fields during window creation

- [ ] **Step 3: Modify X11Window** — wire runtime extension methods

- [ ] **Step 4: Compile check**

Run: `./gradlew :kadre-x11:compileKotlinJvm` — must succeed

---

### Task 4: Wayland Platform Extensions

**Files:**
- Create: `kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandExtensionTypes.kt`
- Modify: `kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandEventLoop.kt`
- Modify: `kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandWindow.kt`

- [ ] **Step 1: Create WaylandExtensionTypes.kt**
  - `WaylandWindowAttributes` data class wrapping `WindowAttributes`
  - Extension functions: `WindowExtWayland.isWayland()`, `WindowExtWayland.xdgToplevel()`, methods for CSD preference, activation token
  - `ActiveEventLoop.setPreferCsd()`, `ActiveEventLoop.setActivationToken()`

- [ ] **Step 2: Modify WaylandEventLoop** — wire through new attributes

- [ ] **Step 3: Modify WaylandWindow** — wire runtime extension methods

- [ ] **Step 4: Compile check**

---

### Task 5: Web Platform Extensions

**Files:**
- Create: `kadre-web-common/src/webMain/kotlin/org/graphiks/kadre/web/WebExtensionTypes.kt`
- Modify: `kadre-web-common/src/webMain/kotlin/org/graphiks/kadre/web/WebWindow.kt`
- Modify: `kadre-web-common/src/webMain/kotlin/org/graphiks/kadre/web/WebEventLoop.kt`

- [ ] **Step 1: Create WebExtensionTypes.kt**
  - `PollStrategy` enum: IdleCallback, Scheduler
  - `WaitUntilStrategy` enum: Scheduler, Worker
  - Extension functions: `WindowExtWeb.canvas()`, `WindowExtWeb.setPreventDefault()`, `ActiveEventLoopExtWeb.setPollStrategy()`, `ActiveEventLoopExtWeb.setWaitUntilStrategy()`, `ActiveEventLoopExtWeb.createCustomCursorAsync()`

- [ ] **Step 2: Modify WebEventLoop** — wire through new attributes

- [ ] **Step 3: Modify WebWindow** — wire runtime extension methods

- [ ] **Step 4: Compile check**

---

### Task 6: iOS Platform Extensions

**Files:**
- Create: `kadre-uikit/src/iosMain/kotlin/org/graphiks/kadre/uikit/UiKitExtensionTypes.kt`
- Modify: `kadre-uikit/src/iosMain/kotlin/org/graphiks/kadre/uikit/UiKitActiveEventLoop.kt`
- Modify: `kadre-uikit/src/iosMain/kotlin/org/graphiks/kadre/uikit/UiKitWindow.kt`

- [ ] **Step 1: Create UiKitExtensionTypes.kt**
  - `ValidOrientations` enum: LandscapeAndPortrait, Landscape, Portrait
  - `StatusBarStyle` enum: Default, LightContent, DarkContent
  - `UiKitWindowAttributes` data class wrapping `WindowAttributes`
  - Extension functions for scale factor, orientations, home indicator, status bar, gestures

- [ ] **Step 2-3: Wire through event loop and window**

- [ ] **Step 4: Compile check**

---

### Task 7: Android Platform Extensions

**Files:**
- Create: `kadre-android/src/androidMain/kotlin/org/graphiks/kadre/android/AndroidExtensionTypes.kt`
- Modify: `kadre-android/src/androidMain/kotlin/org/graphiks/kadre/android/AndroidEventLoop.kt`
- Modify: `kadre-android/src/androidMain/kotlin/org/graphiks/kadre/android/AndroidWindow.kt`

- [ ] **Step 1: Create AndroidExtensionTypes.kt**
  - `AndroidWindowAttributes` data class wrapping `WindowAttributes`
  - Extension functions: `WindowExtAndroid.contentRect()`, `WindowExtAndroid.config()`, `ActiveEventLoopExtAndroid.androidApp()`

- [ ] **Step 2-3: Wire through event loop and window**

- [ ] **Step 4: Compile check**

---

### Final Step

- [ ] **Verify all 6 backends compile**

Run: `./gradlew compileKotlinJvm` for each of win32, appkit, x11, wayland
Run: `./gradlew :kadre-web-common:compileKotlinJs :kadre-web-common:compileKotlinWasmJs`
