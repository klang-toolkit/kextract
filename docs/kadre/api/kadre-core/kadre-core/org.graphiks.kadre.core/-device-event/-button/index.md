//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[DeviceEvent](../index.md)/[Button](index.md)

# Button

[common]\
data class [Button](index.md)(val button: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), val state: [KeyState](../../-key-state/index.md)) : [DeviceEvent](../index.md)

Un bouton physique de périphérique a changé d'état.

## Constructors

| | |
|---|---|
| [Button](-button.md) | [common]<br>constructor(button: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), state: [KeyState](../../-key-state/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [button](button.md) | [common]<br>val [button](button.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Indice du bouton (spécifique à la plateforme). |
| [state](state.md) | [common]<br>val [state](state.md): [KeyState](../../-key-state/index.md)<br>État du bouton ([KeyState.Pressed](../../-key-state/-pressed/index.md) ou [KeyState.Released](../../-key-state/-released/index.md)). |