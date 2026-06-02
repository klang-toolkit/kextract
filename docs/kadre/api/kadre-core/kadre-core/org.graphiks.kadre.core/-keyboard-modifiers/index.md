//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[KeyboardModifiers](index.md)

# KeyboardModifiers

[common]\
@[JvmInline](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.jvm/-jvm-inline/index.html)

value class [KeyboardModifiers](index.md)(val bits: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html))

Logical keyboard modifiers active at the time of an event.

## Constructors

| | |
|---|---|
| [KeyboardModifiers](-keyboard-modifiers.md) | [common]<br>constructor(bits: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [alt](alt.md) | [common]<br>val [alt](alt.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [altGraph](alt-graph.md) | [common]<br>val [altGraph](alt-graph.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [bits](bits.md) | [common]<br>val [bits](bits.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |
| [capsLock](caps-lock.md) | [common]<br>val [capsLock](caps-lock.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [ctrl](ctrl.md) | [common]<br>val [ctrl](ctrl.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [meta](meta.md) | [common]<br>val [meta](meta.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [numLock](num-lock.md) | [common]<br>val [numLock](num-lock.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [shift](shift.md) | [common]<br>val [shift](shift.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [symbol](symbol.md) | [common]<br>val [symbol](symbol.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |

## Functions

| Name | Summary |
|---|---|
| [contains](contains.md) | [common]<br>fun [contains](contains.md)(other: [KeyboardModifiers](index.md)): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [minus](minus.md) | [common]<br>operator fun [minus](minus.md)(other: [KeyboardModifiers](index.md)): [KeyboardModifiers](index.md) |
| [plus](plus.md) | [common]<br>operator fun [plus](plus.md)(other: [KeyboardModifiers](index.md)): [KeyboardModifiers](index.md) |