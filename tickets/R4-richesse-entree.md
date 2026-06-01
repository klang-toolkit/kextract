# [R4] Richesse des entrées (clavier / pointeur)

- **Milestone** : R4
- **Statut** : À faire
- **Dépend de** : R0.1
- **Modules touchés** : kadre-core, kadre-appkit, kadre-win32, kadre-x11, kadre-wayland, kadre-uikit, kadre-android, kadre-web-common, kadre-js, kadre-wasm

## Objectif
Enrichir le modèle clavier avec `text`, la distinction physique/logique et `KeyLocation` ;
ajouter `ModifiersChanged` et `DeviceEvent.MouseWheel` ; décider et implémenter le modèle pointeur définitif.
Parallélisable avec R3 dès que R0.1 est validé.

## Périmètre
### API commune (kadre-core)
```kotlin
// Clavier enrichi
enum class KeyLocation { Standard, Left, Right, Numpad }

// Décision de modèle (à trancher avant implémentation) :
// Option A — conserver le Key enum fermé + enrichir KeyboardInput
// Option B — adopter le modèle ouvert winit : Character/Named/Dead/Unidentified
// Le ticket doit documenter le choix retenu.
data class KeyboardInput(
    val physicalKey: PhysicalKey,   // scancode / position indépendant du layout
    val logicalKey: LogicalKey,     // caractère selon le layout actif
    val text: String?,              // texte produit (null si non printable)
    val location: KeyLocation,
    val state: KeyState,
    val modifiers: Modifiers
) : DeviceEvent()

// ModifiersChanged
data class ModifiersChanged(val modifiers: Modifiers) : WindowEvent()

// Window
fun resetDeadKeys()

// DeviceEvent enrichi
data class MouseWheel(val delta: MouseScrollDelta) : DeviceEvent()

// ActiveEventLoop
enum class DeviceEvents { Always, WhenFocused, Never }
fun listenDeviceEvents(filter: DeviceEvents)

// Modèle pointeur (décision ici) :
// Option A — garder MouseInput + Touch (modèle historique)
// Option B — migrer vers PointerButton / PointerSource{Mouse, Touch, TabletTool} (winit actuel)
// Si Option B : breaking change, à faire ici.
```

### Par backend
- **kadre-appkit** : `text` via `NSEvent.characters` ; physicalKey via `keyCode` ; `ModifiersChanged` via `flagsChanged`
- **kadre-win32** : `text` via `ToUnicode` ; physicalKey via `scanCode` ; `ModifiersChanged` via `WM_KEYDOWN` sur VK_SHIFT etc.
- **kadre-x11** : keymapper xkbcommon ; `text` via `xkb_state_key_get_utf8` ; `ModifiersChanged` via `XkbStateNotify`
- **kadre-wayland** : keymapper xkbcommon partagé avec x11 ; `ModifiersChanged` via `wl_keyboard.modifiers`
- **kadre-uikit** : `text` via `UIKey.characters` (iOS 13.4+) ; physicalKey via `UIKey.keyCode`
- **kadre-android** : `text` via `KeyEvent.unicodeChar` ; physicalKey via `KeyEvent.scanCode`
- **kadre-web-common** : `text` via DOM `key` ; physicalKey via DOM `code` ; `ModifiersChanged` via événements clavier
- **kadre-js** / **kadre-wasm** : délèguent à web-common

## Critères d'acceptation
- [ ] `KeyboardInput.text` non nul pour les touches imprimables sur tous les backends
- [ ] `physicalKey` stable quel que soit le layout clavier actif
- [ ] `ModifiersChanged` émis au changement de Shift/Ctrl/Alt/Meta
- [ ] `DeviceEvent.MouseWheel` émis lors du scroll souris
- [ ] `listenDeviceEvents(Never)` supprime les événements device
- [ ] `resetDeadKeys()` fonctionne sur les backends concernés (appkit, win32, wayland/x11 xkb)
- [ ] Décision modèle pointeur documentée + implémentée de façon cohérente sur tous les backends
- [ ] Tests keymapper par backend (entrées fixtures → sorties attendues)
- [ ] Dump ABI régénéré

## Notes
⚠️ Si Option B (migration pointeur) : breaking change — annoncer et versionner comme R0.1.
xkbcommon peut être partagé entre kadre-x11 et kadre-wayland (même dépendance native).
Référence : plan R4 ; specs §3.
