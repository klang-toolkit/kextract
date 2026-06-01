//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[RawWindowHandle](../index.md)/[Win32](index.md)

# Win32

[common]\
data class [Win32](index.md)(val hwnd: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), val hinstance: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [RawWindowHandle](../index.md)

Win32 window handle (Windows).

## Constructors

| | |
|---|---|
| [Win32](-win32.md) | [common]<br>constructor(hwnd: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), hinstance: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [hinstance](hinstance.md) | [common]<br>val [hinstance](hinstance.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Win32 instance handle (HINSTANCE), represented as Long for FFM compatibility. |
| [hwnd](hwnd.md) | [common]<br>val [hwnd](hwnd.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Win32 window handle (HWND), represented as Long for FFM compatibility. |