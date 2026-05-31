//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[WindowAttributes](index.md)

# WindowAttributes

[common]\
data class [WindowAttributes](index.md)(val title: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) = &quot;Kadre&quot;, val size: [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;? = null, val visible: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = true, val resizable: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = true)

Paramètres de création d'une fenêtre.

## Constructors

| | |
|---|---|
| [WindowAttributes](-window-attributes.md) | [common]<br>constructor(title: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) = &quot;Kadre&quot;, size: [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;? = null, visible: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = true, resizable: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = true) |

## Properties

| Name | Summary |
|---|---|
| [resizable](resizable.md) | [common]<br>val [resizable](resizable.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Indique si l'utilisateur peut redimensionner la fenêtre. |
| [size](size.md) | [common]<br>val [size](size.md): [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;?<br>Taille initiale en pixels physiques, ou null pour utiliser la taille par défaut. |
| [title](title.md) | [common]<br>val [title](title.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)<br>Titre affiché dans la barre de titre de la fenêtre. |
| [visible](visible.md) | [common]<br>val [visible](visible.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Indique si la fenêtre est visible au moment de sa création. |