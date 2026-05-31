//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[WindowEvent](index.md)

# WindowEvent

sealed interface [WindowEvent](index.md)

Événement émis par une fenêtre.

Chaque variant correspond à un changement d'état ou à une action de l'utilisateur sur la fenêtre ciblée.

### Utilisation typique

```kotlin
fun onWindowEvent(event: WindowEvent) {
    when (event) {
        WindowEvent.CloseRequested    -> quitter()
        is WindowEvent.Resized        -> redimensionner(event.size)
        is WindowEvent.Moved          -> deplacer(event.position)
        is WindowEvent.ScaleFactorChanged -> mettreAJourDpi(event.factor)
        is WindowEvent.Focused        -> gererFocus(event.gained)
        is WindowEvent.KeyboardInput  -> gererClavier(event.key, event.state, event.modifiers)
        is WindowEvent.PointerMoved   -> gererPointeur(event.position)
        WindowEvent.PointerEntered    -> gererEntree()
        WindowEvent.PointerLeft       -> gererSortie()
        is WindowEvent.MouseInput     -> gererSouris(event.button, event.state)
        is WindowEvent.MouseWheel     -> gererMolette(event.deltaX, event.deltaY)
        is WindowEvent.Touch          -> gererTactile(event.phase, event.location, event.id)
        WindowEvent.RedrawRequested   -> redessiner()
        WindowEvent.Destroyed         -> libererRessources()
    }
}
```

#### Inheritors

| |
|---|
| [CloseRequested](-close-requested/index.md) |
| [Resized](-resized/index.md) |
| [Moved](-moved/index.md) |
| [ScaleFactorChanged](-scale-factor-changed/index.md) |
| [Focused](-focused/index.md) |
| [KeyboardInput](-keyboard-input/index.md) |
| [PointerMoved](-pointer-moved/index.md) |
| [PointerEntered](-pointer-entered/index.md) |
| [PointerLeft](-pointer-left/index.md) |
| [MouseInput](-mouse-input/index.md) |
| [MouseWheel](-mouse-wheel/index.md) |
| [Touch](-touch/index.md) |
| [RedrawRequested](-redraw-requested/index.md) |
| [Destroyed](-destroyed/index.md) |

## Types

| Name | Summary |
|---|---|
| [CloseRequested](-close-requested/index.md) | [common]<br>data object [CloseRequested](-close-requested/index.md) : [WindowEvent](index.md)<br>L'utilisateur a demandé la fermeture de la fenêtre (bouton ×, Alt+F4, ⌘W, etc.). |
| [Destroyed](-destroyed/index.md) | [common]<br>data object [Destroyed](-destroyed/index.md) : [WindowEvent](index.md)<br>La fenêtre a été détruite et ses ressources natives libérées. |
| [Focused](-focused/index.md) | [common]<br>data class [Focused](-focused/index.md)(val gained: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)) : [WindowEvent](index.md)<br>La fenêtre a gagné ou perdu le focus clavier. |
| [KeyboardInput](-keyboard-input/index.md) | [common]<br>data class [KeyboardInput](-keyboard-input/index.md)(val key: [Key](../-key/index.md), val state: [KeyState](../-key-state/index.md), val modifiers: [Modifiers](../-modifiers/index.md), val isRepeat: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) = false) : [WindowEvent](index.md)<br>Un événement clavier s'est produit alors que la fenêtre avait le focus. |
| [MouseInput](-mouse-input/index.md) | [common]<br>data class [MouseInput](-mouse-input/index.md)(val button: [MouseButton](../-mouse-button/index.md), val state: [KeyState](../-key-state/index.md)) : [WindowEvent](index.md)<br>Un bouton de souris a été enfoncé ou relâché. |
| [MouseWheel](-mouse-wheel/index.md) | [common]<br>data class [MouseWheel](-mouse-wheel/index.md)(val deltaX: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), val deltaY: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) : [WindowEvent](index.md)<br>La molette de souris (ou le pavé tactile) a produit un défilement. |
| [Moved](-moved/index.md) | [common]<br>data class [Moved](-moved/index.md)(val position: [PhysicalPosition](../-physical-position/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;) : [WindowEvent](index.md)<br>La fenêtre a été déplacée. |
| [PointerEntered](-pointer-entered/index.md) | [common]<br>data object [PointerEntered](-pointer-entered/index.md) : [WindowEvent](index.md)<br>Le pointeur vient d'entrer dans la zone cliente de la fenêtre. |
| [PointerLeft](-pointer-left/index.md) | [common]<br>data object [PointerLeft](-pointer-left/index.md) : [WindowEvent](index.md)<br>Le pointeur vient de quitter la zone cliente de la fenêtre. |
| [PointerMoved](-pointer-moved/index.md) | [common]<br>data class [PointerMoved](-pointer-moved/index.md)(val position: [PhysicalPosition](../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;) : [WindowEvent](index.md)<br>Le pointeur s'est déplacé au-dessus de la fenêtre. |
| [RedrawRequested](-redraw-requested/index.md) | [common]<br>data object [RedrawRequested](-redraw-requested/index.md) : [WindowEvent](index.md)<br>La fenêtre doit être redessinée. |
| [Resized](-resized/index.md) | [common]<br>data class [Resized](-resized/index.md)(val size: [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;) : [WindowEvent](index.md)<br>La fenêtre a été redimensionnée. |
| [ScaleFactorChanged](-scale-factor-changed/index.md) | [common]<br>data class [ScaleFactorChanged](-scale-factor-changed/index.md)(val factor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) : [WindowEvent](index.md)<br>Le facteur d'échelle DPI de la fenêtre a changé (ex. déplacement vers un autre moniteur). |
| [Touch](-touch/index.md) | [common]<br>data class [Touch](-touch/index.md)(val phase: [TouchPhase](../-touch-phase/index.md), val location: [PhysicalPosition](../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;, val id: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [WindowEvent](index.md)<br>Un contact tactile a changé d'état. |