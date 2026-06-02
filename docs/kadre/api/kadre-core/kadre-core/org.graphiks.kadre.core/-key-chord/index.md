//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[KeyChord](index.md)

# KeyChord

[common]\
data class [KeyChord](index.md)(val physicalKey: [PhysicalKey](../-physical-key/index.md)? = null, val logicalKey: [LogicalKey](../-logical-key/index.md)? = null, val modifiers: [KeyboardModifiers](../-keyboard-modifiers/index.md) = KeyboardModifiers.NONE, val allowRepeat: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = false)

## Constructors

| | |
|---|---|
| [KeyChord](-key-chord.md) | [common]<br>constructor(physicalKey: [PhysicalKey](../-physical-key/index.md)? = null, logicalKey: [LogicalKey](../-logical-key/index.md)? = null, modifiers: [KeyboardModifiers](../-keyboard-modifiers/index.md) = KeyboardModifiers.NONE, allowRepeat: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = false) |

## Properties

| Name | Summary |
|---|---|
| [allowRepeat](allow-repeat.md) | [common]<br>val [allowRepeat](allow-repeat.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [logicalKey](logical-key.md) | [common]<br>val [logicalKey](logical-key.md): [LogicalKey](../-logical-key/index.md)? |
| [modifiers](modifiers.md) | [common]<br>val [modifiers](modifiers.md): [KeyboardModifiers](../-keyboard-modifiers/index.md) |
| [physicalKey](physical-key.md) | [common]<br>val [physicalKey](physical-key.md): [PhysicalKey](../-physical-key/index.md)? |

## Functions

| Name | Summary |
|---|---|
| [matches](matches.md) | [common]<br>fun [matches](matches.md)(event: [KeyEvent](../-key-event/index.md)): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |