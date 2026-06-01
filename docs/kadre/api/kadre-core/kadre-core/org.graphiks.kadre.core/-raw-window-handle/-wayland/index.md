//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[RawWindowHandle](../index.md)/[Wayland](index.md)

# Wayland

[common]\
data class [Wayland](index.md)(val surface: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val display: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [RawWindowHandle](../index.md)

Wayland window handle (Linux).

## Constructors

| | |
|---|---|
| [Wayland](-wayland.md) | [common]<br>constructor(surface: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), display: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [display](display.md) | [common]<br>val [display](display.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Pointer to the Wayland display (wl_display*), represented as Long. |
| [surface](surface.md) | [common]<br>val [surface](surface.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Pointer to the Wayland surface (wl_surface*), represented as Long. |