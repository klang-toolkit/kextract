//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[KeyboardInput](index.md)

# KeyboardInput

[common]\
data class [KeyboardInput](index.md)(val key: [Key](../../-key/index.md), val state: [KeyState](../../-key-state/index.md), val modifiers: [Modifiers](../../-modifiers/index.md), val isRepeat: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = false, val text: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, val location: [KeyLocation](../../-key-location/index.md) = KeyLocation.Standard, val scanCode: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)? = null) : [WindowEvent](../index.md)

A keyboard event occurred while the window had focus.

## Constructors

| | |
|---|---|
| [KeyboardInput](-keyboard-input.md) | [common]<br>constructor(key: [Key](../../-key/index.md), state: [KeyState](../../-key-state/index.md), modifiers: [Modifiers](../../-modifiers/index.md), isRepeat: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = false, text: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, location: [KeyLocation](../../-key-location/index.md) = KeyLocation.Standard, scanCode: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [isRepeat](is-repeat.md) | [common]<br>val [isRepeat](is-repeat.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>`true` if the key is being held and this is an auto-repeat. |
| [key](key.md) | [common]<br>val [key](key.md): [Key](../../-key/index.md)<br>Logical key involved (layout-dependent). |
| [location](location.md) | [common]<br>val [location](location.md): [KeyLocation](../../-key-location/index.md)<br>Physical location of the key on the keyboard (standard, left, right, or numpad). Defaults to [KeyLocation.Standard](../../-key-location/-standard/index.md). Populated where the platform exposes it. |
| [modifiers](modifiers.md) | [common]<br>val [modifiers](modifiers.md): [Modifiers](../../-modifiers/index.md)<br>Modifiers active at the time of the event. |
| [scanCode](scan-code.md) | [common]<br>val [scanCode](scan-code.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)?<br>Platform-independent physical key code (evdev / HID usage / Win32 scan), independent of the active keyboard layout. Null if the backend does not expose it. |
| [state](state.md) | [common]<br>val [state](state.md): [KeyState](../../-key-state/index.md)<br>Key state ([KeyState.Pressed](../../-key-state/-pressed/index.md) or [KeyState.Released](../../-key-state/-released/index.md)). |
| [text](text.md) | [common]<br>val [text](text.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?<br>Unicode character(s) produced by this key press, or null if the key does not produce printable text (e.g. function keys, modifiers, arrows). Populated on a best-effort basis per backend — null is always safe to handle. |