//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[KeyboardInput](index.md)

# KeyboardInput

[common]\
data class [KeyboardInput](index.md)(val key: [Key](../../-key/index.md), val state: [KeyState](../../-key-state/index.md), val modifiers: [Modifiers](../../-modifiers/index.md), val isRepeat: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = false) : [WindowEvent](../index.md)

Un événement clavier s'est produit alors que la fenêtre avait le focus.

## Constructors

| | |
|---|---|
| [KeyboardInput](-keyboard-input.md) | [common]<br>constructor(key: [Key](../../-key/index.md), state: [KeyState](../../-key-state/index.md), modifiers: [Modifiers](../../-modifiers/index.md), isRepeat: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = false) |

## Properties

| Name | Summary |
|---|---|
| [isRepeat](is-repeat.md) | [common]<br>val [isRepeat](is-repeat.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [key](key.md) | [common]<br>val [key](key.md): [Key](../../-key/index.md)<br>Touche logique concernée. |
| [modifiers](modifiers.md) | [common]<br>val [modifiers](modifiers.md): [Modifiers](../../-modifiers/index.md)<br>Modificateurs actifs au moment de l'événement. |
| [state](state.md) | [common]<br>val [state](state.md): [KeyState](../../-key-state/index.md)<br>État de la touche ([KeyState.Pressed](../../-key-state/-pressed/index.md) ou [KeyState.Released](../../-key-state/-released/index.md)). |