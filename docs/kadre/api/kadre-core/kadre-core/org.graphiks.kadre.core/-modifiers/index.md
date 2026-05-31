//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Modifiers](index.md)

# Modifiers

[common]\
@[JvmInline](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.jvm/-jvm-inline/index.html)

value class [Modifiers](index.md)(val bits: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html))

Ensemble de modificateurs clavier actifs au moment d'un événement.

Implémenté comme un entier de bits pour minimiser les allocations. Utilisez les constantes du [companion object](-companion/index.md) pour construire des valeurs, et l'opérateur [plus](plus.md) pour les combiner.

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
| [alt](alt.md) | [common]<br>val [alt](alt.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>`true` si la touche Alt est enfoncée. |
| [bits](bits.md) | [common]<br>val [bits](bits.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Représentation interne sous forme de champ de bits. |
| [ctrl](ctrl.md) | [common]<br>val [ctrl](ctrl.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>`true` si la touche Contrôle est enfoncée. |
| [meta](meta.md) | [common]<br>val [meta](meta.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>`true` si la touche Meta (⌘ / Win) est enfoncée. |
| [shift](shift.md) | [common]<br>val [shift](shift.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>`true` si la touche Majuscule est enfoncée. |

## Functions

| Name | Summary |
|---|---|
| [contains](contains.md) | [common]<br>fun [contains](contains.md)(other: [Modifiers](index.md)): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Vérifie si cet ensemble contient tous les modificateurs de [other](contains.md). |
| [plus](plus.md) | [common]<br>operator fun [plus](plus.md)(other: [Modifiers](index.md)): [Modifiers](index.md)<br>Combine ces modificateurs avec [other](plus.md). |