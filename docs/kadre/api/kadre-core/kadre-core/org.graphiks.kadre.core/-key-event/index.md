//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[KeyEvent](index.md)

# KeyEvent

[common]\
data class [KeyEvent](index.md)(val physicalKey: [PhysicalKey](../-physical-key/index.md), val logicalKey: [LogicalKey](../-logical-key/index.md), val state: [KeyState](../-key-state/index.md), val modifiers: [KeyboardModifiers](../-keyboard-modifiers/index.md), val location: [KeyLocation](../-key-location/index.md) = KeyLocation.Standard, val repeat: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = false, val synthetic: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = false, val text: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, val textWithAllModifiers: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, val keyWithoutModifiers: [LogicalKey](../-logical-key/index.md)? = null, val native: [NativeKeyInfo](../-native-key-info/index.md) = NativeKeyInfo())

## Constructors

| | |
|---|---|
| [KeyEvent](-key-event.md) | [common]<br>constructor(physicalKey: [PhysicalKey](../-physical-key/index.md), logicalKey: [LogicalKey](../-logical-key/index.md), state: [KeyState](../-key-state/index.md), modifiers: [KeyboardModifiers](../-keyboard-modifiers/index.md), location: [KeyLocation](../-key-location/index.md) = KeyLocation.Standard, repeat: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = false, synthetic: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = false, text: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, textWithAllModifiers: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, keyWithoutModifiers: [LogicalKey](../-logical-key/index.md)? = null, native: [NativeKeyInfo](../-native-key-info/index.md) = NativeKeyInfo()) |

## Properties

| Name | Summary |
|---|---|
| [character](character.md) | [common]<br>val [character](character.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? |
| [isPressed](is-pressed.md) | [common]<br>val [isPressed](is-pressed.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [isReleased](is-released.md) | [common]<br>val [isReleased](is-released.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [keyWithoutModifiers](key-without-modifiers.md) | [common]<br>val [keyWithoutModifiers](key-without-modifiers.md): [LogicalKey](../-logical-key/index.md)? |
| [location](location.md) | [common]<br>val [location](location.md): [KeyLocation](../-key-location/index.md) |
| [logicalKey](logical-key.md) | [common]<br>val [logicalKey](logical-key.md): [LogicalKey](../-logical-key/index.md) |
| [modifiers](modifiers.md) | [common]<br>val [modifiers](modifiers.md): [KeyboardModifiers](../-keyboard-modifiers/index.md) |
| [native](native.md) | [common]<br>val [native](native.md): [NativeKeyInfo](../-native-key-info/index.md) |
| [physicalKey](physical-key.md) | [common]<br>val [physicalKey](physical-key.md): [PhysicalKey](../-physical-key/index.md) |
| [repeat](repeat.md) | [common]<br>val [repeat](repeat.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [state](state.md) | [common]<br>val [state](state.md): [KeyState](../-key-state/index.md) |
| [synthetic](synthetic.md) | [common]<br>val [synthetic](synthetic.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [text](text.md) | [common]<br>val [text](text.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? |
| [textWithAllModifiers](text-with-all-modifiers.md) | [common]<br>val [textWithAllModifiers](text-with-all-modifiers.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? |