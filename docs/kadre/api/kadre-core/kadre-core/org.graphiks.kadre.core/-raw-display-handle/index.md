//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[RawDisplayHandle](index.md)

# RawDisplayHandle

sealed interface [RawDisplayHandle](index.md)

Raw handle of a native display.

Each variant is a singleton corresponding to a target platform. On these platforms, the display has no pointer handle distinct from the window.

#### Inheritors

| |
|---|
| [AppKit](-app-kit/index.md) |
| [UiKit](-ui-kit/index.md) |
| [Android](-android/index.md) |
| [Win32](-win32/index.md) |
| [Web](-web/index.md) |
| [Xlib](-xlib/index.md) |
| [Wayland](-wayland/index.md) |

## Types

| Name | Summary |
|---|---|
| [Android](-android/index.md) | [common]<br>data object [Android](-android/index.md) : [RawDisplayHandle](index.md)<br>Android display handle. |
| [AppKit](-app-kit/index.md) | [common]<br>data object [AppKit](-app-kit/index.md) : [RawDisplayHandle](index.md)<br>AppKit display handle (macOS). |
| [UiKit](-ui-kit/index.md) | [common]<br>data object [UiKit](-ui-kit/index.md) : [RawDisplayHandle](index.md)<br>UIKit display handle (iOS / tvOS). |
| [Wayland](-wayland/index.md) | [common]<br>data class [Wayland](-wayland/index.md)(val display: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [RawDisplayHandle](index.md)<br>Wayland display handle (Linux). |
| [Web](-web/index.md) | [common]<br>data object [Web](-web/index.md) : [RawDisplayHandle](index.md)<br>Web display handle (browser / Wasm). |
| [Win32](-win32/index.md) | [common]<br>data class [Win32](-win32/index.md)(val hinstance: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [RawDisplayHandle](index.md)<br>Win32 display handle (Windows). |
| [Xlib](-xlib/index.md) | [common]<br>data class [Xlib](-xlib/index.md)(val display: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [RawDisplayHandle](index.md)<br>X11/Xlib display handle (Linux). |