//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[RawWindowHandle](../index.md)/[Xlib](index.md)

# Xlib

[common]\
data class [Xlib](index.md)(val window: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val display: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [RawWindowHandle](../index.md)

X11/Xlib window handle (Linux).

## Constructors

| | |
|---|---|
| [Xlib](-xlib.md) | [common]<br>constructor(window: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), display: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [display](display.md) | [common]<br>val [display](display.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Pointer to the X11 Display (returned by XOpenDisplay), represented as Long. |
| [window](window.md) | [common]<br>val [window](window.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>XID of the X11 window (returned by XCreateWindow). |