//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Modifiers](index.md)

# Modifiers

[common]\
@[JvmInline](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.jvm/-jvm-inline/index.html)

value class [Modifiers](index.md)(val bits: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html))

Set of keyboard modifiers active at the time of an event.

Implemented as a bit integer to minimize allocations. Use the constants from the [companion object](-companion/index.md) to build values, and the [plus](plus.md) operator to combine them.

```kotlin
val mods = Modifiers.SHIFT + Modifiers.CTRL
assert(mods.contains(Modifiers.SHIFT))
assert(mods.shift)
assert(mods.ctrl)
```

## Constructors

| | |
|---|---|
| [Modifiers](-modifiers.md) | [common]<br>constructor(bits: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [alt](alt.md) | [common]<br>val [alt](alt.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>`true` if the Alt key is pressed. |
| [bits](bits.md) | [common]<br>val [bits](bits.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Internal representation as a bit field. |
| [ctrl](ctrl.md) | [common]<br>val [ctrl](ctrl.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>`true` if the Control key is pressed. |
| [meta](meta.md) | [common]<br>val [meta](meta.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>`true` if the Meta key (⌘ / Win) is pressed. |
| [shift](shift.md) | [common]<br>val [shift](shift.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>`true` if the Shift key is pressed. |

## Functions

| Name | Summary |
|---|---|
| [contains](contains.md) | [common]<br>fun [contains](contains.md)(other: [Modifiers](index.md)): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Checks whether this set contains all the modifiers of [other](contains.md). |
| [plus](plus.md) | [common]<br>operator fun [plus](plus.md)(other: [Modifiers](index.md)): [Modifiers](index.md)<br>Combines these modifiers with [other](plus.md). |