//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[Focused](index.md)

# Focused

[common]\
data class [Focused](index.md)(val gained: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)) : [WindowEvent](../index.md)

The window gained or lost keyboard focus.

## Constructors

| | |
|---|---|
| [Focused](-focused.md) | [common]<br>constructor(gained: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [gained](gained.md) | [common]<br>val [gained](gained.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>`true` if the window just gained focus, `false` if it lost it. |