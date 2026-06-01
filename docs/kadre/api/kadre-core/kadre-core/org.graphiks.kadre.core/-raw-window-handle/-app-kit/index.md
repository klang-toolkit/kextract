//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[RawWindowHandle](../index.md)/[AppKit](index.md)

# AppKit

[common]\
data class [AppKit](index.md)(val nsView: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val nsWindow: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val nsLayer: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html) = 0) : [RawWindowHandle](../index.md)

AppKit window handle (macOS).

## Constructors

| | |
|---|---|
| [AppKit](-app-kit.md) | [common]<br>constructor(nsView: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), nsWindow: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), nsLayer: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html) = 0) |

## Properties

| Name | Summary |
|---|---|
| [nsLayer](ns-layer.md) | [common]<br>val [nsLayer](ns-layer.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Pointer to the `CAMetalLayer` instance attached to the `NSView`.                     Exposed directly to avoid going through `[nsView layer]`,                     which may return the generic layer created by AppKit if                     the `setLayer`/`setWantsLayer` order is not respected. |
| [nsView](ns-view.md) | [common]<br>val [nsView](ns-view.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Pointer to the `NSView` instance (cast to `NSView*` at the point of use). |
| [nsWindow](ns-window.md) | [common]<br>val [nsWindow](ns-window.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Pointer to the `NSWindow` instance (cast to `NSWindow*` at the point of use). |