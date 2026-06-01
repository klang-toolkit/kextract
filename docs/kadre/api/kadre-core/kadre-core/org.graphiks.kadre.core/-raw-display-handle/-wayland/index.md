//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[RawDisplayHandle](../index.md)/[Wayland](index.md)

# Wayland

[common]\
data class [Wayland](index.md)(val display: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [RawDisplayHandle](../index.md)

Wayland display handle (Linux).

## Constructors

| | |
|---|---|
| [Wayland](-wayland.md) | [common]<br>constructor(display: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [display](display.md) | [common]<br>val [display](display.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Pointer to the Wayland display (wl_display*), represented as Long. |