//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[RawDisplayHandle](../index.md)/[Xlib](index.md)

# Xlib

[common]\
data class [Xlib](index.md)(val display: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [RawDisplayHandle](../index.md)

X11/Xlib display handle (Linux).

## Constructors

| | |
|---|---|
| [Xlib](-xlib.md) | [common]<br>constructor(display: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [display](display.md) | [common]<br>val [display](display.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Pointer to the X11 Display (returned by XOpenDisplay), represented as Long. |