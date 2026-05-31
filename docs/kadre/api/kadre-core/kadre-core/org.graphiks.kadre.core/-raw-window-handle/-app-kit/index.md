//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[RawWindowHandle](../index.md)/[AppKit](index.md)

# AppKit

[common]\
data class [AppKit](index.md)(val nsView: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val nsWindow: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val nsLayer: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html) = 0) : [RawWindowHandle](../index.md)

Handle de fenêtre AppKit (macOS).

## Constructors

| | |
|---|---|
| [AppKit](-app-kit.md) | [common]<br>constructor(nsView: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), nsWindow: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), nsLayer: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html) = 0) |

## Properties

| Name | Summary |
|---|---|
| [nsLayer](ns-layer.md) | [common]<br>val [nsLayer](ns-layer.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Pointeur vers l'instance `CAMetalLayer` attachée au `NSView`.                     Exposé directement pour éviter de passer par `[nsView layer]`                     qui peut retourner la couche générique créée par AppKit si                     l'ordre `setLayer`/`setWantsLayer` n'est pas respecté. |
| [nsView](ns-view.md) | [common]<br>val [nsView](ns-view.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Pointeur vers l'instance `NSView` (cast vers `NSView*` au point d'usage). |
| [nsWindow](ns-window.md) | [common]<br>val [nsWindow](ns-window.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Pointeur vers l'instance `NSWindow` (cast vers `NSWindow*` au point d'usage). |