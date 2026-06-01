# [R5-IME] Saisie IME (Input Method Editor)

- **Milestone** : R5
- **Statut** : À faire
- **Dépend de** : R3, R4
- **Modules touchés** : kadre-core, kadre-appkit, kadre-win32, kadre-x11, kadre-wayland, kadre-uikit, kadre-android, kadre-web-common, kadre-js, kadre-wasm

## Objectif
Exposer le protocole IME complet : pré-composition affichée à l'écran, validation finale.
Ce lot est indépendant des autres lots R5 ; il ne bloque pas la 1.0.

## Périmètre
### API commune (kadre-core)
```kotlin
sealed class Ime : WindowEvent() {
    data class Enabled(val capabilities: ImeCapabilities) : Ime()
    data class Preedit(val text: String, val cursorRange: IntRange?) : Ime()
    data class Commit(val text: String) : Ime()
    object Disabled : Ime()
}

data class ImeCapabilities(val canSetCursorArea: Boolean)
enum class ImePurpose { Normal, Password, Terminal }

// Window
fun requestImeUpdate(enabled: Boolean, purpose: ImePurpose = ImePurpose.Normal,
                     cursorArea: PhysicalPosition? = null)
val imeCapabilities: ImeCapabilities
```

### Par backend
- **kadre-appkit** : réel via `NSTextInputClient` / `NSInputManager`
- **kadre-win32** : réel via TSF (Text Services Framework) ou IMM32
- **kadre-x11** : réel via XIM (`XOpenIM`, `XCreateIC`)
- **kadre-wayland** : réel via `zwp_text_input_v3`
- **kadre-uikit** : réel via `UITextInput` protocol
- **kadre-android** : réel via `InputConnection` + `EditorInfo`
- **kadre-web-common** : réel via `compositionstart/update/end` + `input` events sur `<input>` caché
- **kadre-js** / **kadre-wasm** : délèguent à web-common

## Critères d'acceptation
- [ ] Pré-composition visible en temps réel sur macOS et Windows (test manuel)
- [ ] `Ime.Commit` reçu avec le texte final validé
- [ ] `requestImeUpdate(false)` désactive le clavier logiciel sur mobile
- [ ] Tests par backend (mock IME ou test d'intégration)
- [ ] Dump ABI régénéré

## Notes
Classé "future" dans specs §7. Complexité élevée sur X11 (XIM legacy) ; envisager IBus/Fcitx via D-Bus comme alternative.
Référence : plan R5 §IME ; specs §7.
