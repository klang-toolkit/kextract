//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[RawWindowHandle](index.md)

# RawWindowHandle

sealed interface [RawWindowHandle](index.md)

Raw handle of a native window.

Each variant corresponds to a target platform. The pointers are exposed as [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html) so that commonMain remains 100% pure Kotlin (no native import).

#### Inheritors

| |
|---|
| [AppKit](-app-kit/index.md) |
| [UiKit](-ui-kit/index.md) |
| [Android](-android/index.md) |
| [Win32](-win32/index.md) |
| [Xlib](-xlib/index.md) |
| [Wayland](-wayland/index.md) |
| [Web](-web/index.md) |

## Types

| Name | Summary |
|---|---|
| [Android](-android/index.md) | [common]<br>data class [Android](-android/index.md)(val surface: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)) : [RawWindowHandle](index.md)<br>Android window handle. |
| [AppKit](-app-kit/index.md) | [common]<br>data class [AppKit](-app-kit/index.md)(val nsView: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val nsWindow: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val nsLayer: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html) = 0) : [RawWindowHandle](index.md)<br>AppKit window handle (macOS). |
| [UiKit](-ui-kit/index.md) | [common]<br>data class [UiKit](-ui-kit/index.md)(val uiView: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val uiViewController: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)?) : [RawWindowHandle](index.md)<br>UIKit window handle (iOS / tvOS). |
| [Wayland](-wayland/index.md) | [common]<br>data class [Wayland](-wayland/index.md)(val surface: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val display: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [RawWindowHandle](index.md)<br>Wayland window handle (Linux). |
| [Web](-web/index.md) | [common]<br>data class [Web](-web/index.md)(val canvasElementId: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, val canvasElement: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)? = null) : [RawWindowHandle](index.md)<br>Web window handle (browser / Wasm). |
| [Win32](-win32/index.md) | [common]<br>data class [Win32](-win32/index.md)(val hwnd: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val hinstance: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [RawWindowHandle](index.md)<br>Win32 window handle (Windows). |
| [Xlib](-xlib/index.md) | [common]<br>data class [Xlib](-xlib/index.md)(val window: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val display: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [RawWindowHandle](index.md)<br>X11/Xlib window handle (Linux). |