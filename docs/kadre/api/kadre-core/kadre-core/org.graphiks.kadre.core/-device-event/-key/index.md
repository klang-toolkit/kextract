//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[DeviceEvent](../index.md)/[Key](index.md)

# Key

[common]\
data class [Key](index.md)(val scancode: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), val state: [KeyState](../../-key-state/index.md)) : [DeviceEvent](../index.md)

Une touche physique du clavier a changé d'état (identifiée par scancode).

## Constructors

| | |
|---|---|
| [Key](-key.md) | [common]<br>constructor(scancode: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), state: [KeyState](../../-key-state/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [scancode](scancode.md) | [common]<br>val [scancode](scancode.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Code physique de la touche (indépendant de la disposition clavier). |
| [state](state.md) | [common]<br>val [state](state.md): [KeyState](../../-key-state/index.md)<br>État de la touche ([KeyState.Pressed](../../-key-state/-pressed/index.md) ou [KeyState.Released](../../-key-state/-released/index.md)). |