# Gap analysis clavier Kadre vs winit

Date: 2026-06-02
Portee: clavier uniquement
References:

- winit docs.rs `0.30.13`: `winit::event::KeyEvent`, `winit::keyboard::{Key, PhysicalKey, KeyLocation}`.
- winit sous-module local: `third_party/winit` a `c4afadbfabf7b1e7989b40b493db1a4c7bd8ff4e`.
- Kadre local: `kadre-core/src/commonMain/kotlin/org/graphiks/kadre/core/Events.kt`.

## Synthese

Kadre est maintenant aligne sur la forme moderne de winit pour le clavier: un `WindowEvent.KeyInput(event, deviceId)`, une separation stricte entre touche physique et touche logique, un evenement raw device dedie, un etat de repetition, une location clavier, du texte produit, une touche sans modifieurs et un etat de modifieurs.

Le gap principal n'est plus l'architecture d'API. Il est dans trois zones:

1. exhaustivite des enums `KeyCode` et `NamedKey` par rapport aux tables winit/`keyboard-types`;
2. qualite runtime des backends, surtout layout-aware text, `keyWithoutModifiers`, `textWithAllModifiers`, IME et synthetic release;
3. granularite native runtime: Kadre expose maintenant `NativeKeyCode` et `NativeLogicalKey`, mais tous les backends ne fournissent pas encore les champs natifs les plus riches.

## Correspondance API

| Capacite winit | Kadre actuel | Statut | Ecart |
|---|---|---:|---|
| `WindowEvent::KeyboardInput { event: KeyEvent, device_id, is_synthetic }` | `WindowEvent.KeyInput(event, deviceId)` | OK | `synthetic` reste porte dans `KeyEvent`, ce qui evite de dupliquer l'etat. |
| `KeyEvent.physical_key: PhysicalKey` | `KeyEvent.physicalKey: PhysicalKey` | OK | Meme concept. |
| `PhysicalKey::Code(KeyCode)` | `PhysicalKey.Code(KeyCode)` | OK | Meme concept. |
| `PhysicalKey::Unidentified(NativeKeyCode)` | `PhysicalKey.Native(platform, code)` + `NativeKeyInfo.nativeCode` | Partiel | `PhysicalKey.Native` garde l'ABI `(KeyPlatform, Long)`; `NativeKeyInfo.nativeCode` porte l'identite typee complete. |
| `KeyEvent.logical_key: Key` | `KeyEvent.logicalKey: LogicalKey` | OK | Meme structure: `Character`, `Named`, `Dead`, `Unidentified`. |
| `Key::Unidentified(NativeKey)` | `LogicalKey.Unidentified(NativeKeyInfo(nativeKey = NativeLogicalKey))` | OK API | Runtime backend encore incomplet selon plateforme. |
| `KeyEvent.text` | `KeyEvent.text` | OK API | Runtime backend encore incomplet. |
| `KeyEvent.text_with_all_modifiers` | `KeyEvent.textWithAllModifiers` + `effectiveText` | OK API | Runtime backend encore incomplet. |
| `KeyEvent.key_without_modifiers` | `KeyEvent.keyWithoutModifiers` + `shortcutKey` | OK API | Runtime backend encore incomplet. |
| `KeyEvent.location: KeyLocation` | `KeyEvent.location: KeyLocation` + `KeyCode.location()` | OK API | Certains backends infèrent ou laissent `Standard`. |
| `KeyEvent.state: ElementState` | `KeyEvent.state: KeyState` | OK | `Pressed` / `Released`. |
| `KeyEvent.repeat` | `KeyEvent.repeat` | OK | Implementations variables selon backend. |
| `DeviceEvent::Key(RawKeyEvent)` | `DeviceEvent.Key(RawKeyEvent)` | OK API | Kadre garde un constructeur legacy scancode. |
| `RawKeyEvent { physical_key, state }` | `RawKeyEvent(physicalKey, state, native)` | OK+ | Kadre ajoute `native`. |
| `WindowEvent::ModifiersChanged(Modifiers)` | `WindowEvent.ModifiersChanged(KeyboardModifierState)` | OK | Kadre separe logique et physique. |
| `ModifiersState` | `KeyboardModifiers` | OK+ | Kadre ajoute `AltGraph`, `CapsLock`, `NumLock`, `Symbol`; winit stable expose surtout Shift/Ctrl/Alt/Meta. |
| `ModifiersKeys` left/right | `ModifierKeys` | OK API | Runtime incomplet selon backend. |
| `Key::to_text()` helper | `defaultText()` + `effectiveText` | Partiel | Kadre a un fallback commun, mais pas encore equivalent exhaustif a winit. |

## Gaps d'exhaustivite enum

### `KeyCode`

Kadre couvre deja les familles importantes:

- lettres `KeyA..KeyZ`;
- chiffres `Digit0..Digit9`;
- ponctuation courante: backquote, slash, bracket, comma, period, quote, semicolon, minus, equal, intl;
- navigation: arrows, home/end/page, insert/delete;
- modifiers gauche/droite: shift/control/alt/meta;
- numpad principal: chiffres, operations, enter, decimal, comma, clear, backspace;
- fonction `F1..F35`;
- media/browser/launch courants.

Gaps probables face a winit/`keyboard-types`:

- `KeyCode.Unidentified` n'existe pas dans Kadre; Kadre utilise `PhysicalKey.Unidentified` ou `PhysicalKey.Native`.
- touches IME/langues asiatiques incompletes: `Lang1..Lang5`, `KanaMode`, `Convert`, `NonConvert`, `Hiragana`, `Katakana`, `Eisu` selon plateformes.
- numpad et clavier etendu incomplets: `NumpadParenLeft`, `NumpadParenRight`, `NumpadMemory*`, `NumpadSignChange`, `NumpadHash`, etc.
- touches media/app/system encore partielles: certaines variantes Android/XKB/Web finissent en `Native`.
- touches mobile/gamepad qui arrivent parfois comme keycodes Android: Kadre les ignore ou les conserve en native, winit en mappe une partie ou les laisse en native selon cas.

Impact: les applications peuvent matcher les touches inconnues via `PhysicalKey.Native`, mais ne peuvent pas ecrire de code portable par nom pour ces touches.

### `NamedKey`

Kadre couvre les named keys frequents: modifiers, navigation, edition, F1-F35, media/browser/launch, `PrintScreen`, `Pause`, `AudioVolume*`, etc.

Gaps probables:

- named keys UI Events tres long tail: TV/media avance, color keys, app switch, contacts/calendar/calculator, candidate/IME modes, zoom/media speciales.
- valeurs liees a IME et composition: `GroupNext`, `KanjiMode`, `AllCandidates`, `NextCandidate`, etc.
- valeurs specifiques Android ou XKB que winit expose via `NamedKey` quand il peut.

Impact: ces touches tombent aujourd'hui dans `LogicalKey.Unidentified(native)` ou dans un fallback approximatif. C'est acceptable en incubation mais incomplet pour des applications internationales ou kiosk/media center.

## Gaps runtime par backend

| Backend Kadre | Etat actuel | Gaps clavier prioritaires |
|---|---|---|
| Web | Meilleur candidat runtime: DOM fournit `code`, `key`, `location`, `repeat`, modifiers; `NativeKeyCode.Web(code)` evite le hash fragile. | `textWithAllModifiers` et `keyWithoutModifiers` restent surtout des fallbacks; dead keys dependent browser; `ModifiersChanged` physique gauche/droite absent. |
| AppKit | Map physique partielle QWERTY US + modifiers logiques. | Utiliser la vraie semantique `characters`, `charactersIgnoringModifiers`, dead keys/IME, synthetic release, left/right modifier state. |
| UIKit | HID usage pour hardware keyboard, map lettres/chiffres/F1-F12/navigation/modifiers. | Texte/logical layout limite; `textWithAllModifiers` non implemente; IME/hints incomplets. |
| Android | Map hardware basique A-Z, digits, F1-F12, dpad, special, modifiers; `deviceId`, scancode et keycode natifs sont exposes. | Utiliser `unicodeChar`, `displayLabel`, `characters`, `isPrintingKey`, AltGraph/Caps/NumLock, `keyWithoutModifiers`, media/lang/gamepad coverage. |
| Win32 | Map VK basique + repeat via lParam, native VK/scancode type. | Preferer scancode/MapVirtualKey pour physical layout complet; distinguer left/right generic VK proprement; alimenter `text`, `textWithAllModifiers`, `keyWithoutModifiers`, IME; synthetic release. |
| X11 | Keycode/keysym table basique + repeat tracker + `ModifiersChanged` avec rehydratation `XQueryKeymap` au focus. | Integrer xkbcommon/XKB pour layout, `key_without_modifiers`, dead keys, compose, AltGraph, lock modifiers, precise location. |
| Wayland | evdev table basique + repeat state conventionnel + `ModifiersChanged` depuis transitions et touches pressees au focus enter. | Integrer xkbcommon pour layout/text/modifiers/compose; gerer repeat protocol; IME text-input v3; locked/latched modifiers via `wl_keyboard.modifiers`. |

## IME et texte

winit couple explicitement clavier et IME:

- `KeyEvent.text` porte le texte produit par une frappe;
- `WindowEvent::Ime` porte `Enabled`, `Preedit`, `Commit`, `DeleteSurrounding`, `Disabled`;
- selon plateforme, pendant une phase preedit, les `KeyboardInput` peuvent etre reduits ou differes;
- `Window::set_ime_allowed`, cursor area, purpose/hints/surrounding text pilotent l'IME.

Kadre a deja:

- `KeyEvent.text`, `textWithAllModifiers`, `keyWithoutModifiers`;
- `WindowEvent.Ime` avec `Enabled`, `Preedit`, `Commit`, `DeleteSurrounding`, `Disabled`;
- `Window.setImeAllowed`, `setImeCursorArea`, `setImePurpose`.

Gaps:

- runtime IME incomplet ou absent sur plusieurs backends;
- pas encore de modele riche equivalent a `ImeRequest`, `ImeCapabilities`, `ImeHint`, `ImeSurroundingText`;
- coordination exacte IME vs `KeyInput` non documentee par backend;
- `DeleteSurrounding` utilise `Int` dans Kadre, winit utilise `usize`; pas bloquant, mais a documenter comme offsets UTF-8 bytes.

## Modifieurs

Kadre est plus expressif que winit stable sur les flags logiques (`AltGraph`, locks), mais moins mature sur le runtime:

- `KeyboardModifiers` est une value class ergonomique Kotlin;
- `ModifierKeys` donne left/right en `Pressed/Released/Unknown`;
- winit ne promet pas toujours left/right non plus: son `ModifiersKeys` est une metadata, pas la source de verite.

Gaps:

- les backends alimentent peu `ModifierKeys`;
- sticky modifiers / locks ne sont pas documentes dans Kadre;
- `AltGraph` doit etre traite backend par backend: Windows right-alt, XKB level3, macOS Option, Android meta flags.

## Decision API recommandee

Ne pas revenir a une API `KeyboardInput(deviceId, key, state, modifiers)`. La surface actuelle est la bonne direction.

Ajustements recommandes avant stabilisation:

1. Fait: `deviceId: DeviceId?` est porte par `WindowEvent.KeyInput`, comme winit.
2. Fait: `NativeKeyInfo.nativeCode` porte un sealed `NativeKeyCode`; `PhysicalKey.Native(platform, code)` garde l'ABI incubee precedente.
3. Fait: `NativeLogicalKey` est disponible dans `NativeKeyInfo.nativeKey` pour distinguer DOM `key`, Android keycode, Win32 VK, X11 keysym, Wayland keysym et AppKit/UIKit.
4. Garder `KeyChord`: c'est l'avantage Kotlin par rapport a winit, utile pour raccourcis configurables.
5. Documenter explicitement: pour le jeu utiliser `physicalKey`; pour shortcuts configurables utiliser `shortcutKey`; pour saisie texte utiliser IME + `text`/`effectiveText`.

## Plan TDD priorise

### P0: contrats communs

- Exhaustivite `WindowEvent.KeyInput`, `DeviceEvent.Key`, `ModifiersChanged` sans `else`.
- `KeyChord` physique vs logique, repeat ignore par defaut, matching exact/inclusif.
- `defaultLogicalKey()` et `defaultText()` pour les named/text keys prioritaires.
- `KeyLocation` inferee pour left/right/numpad.

### P1: backend fixtures

- Web: `code`, `key`, `location`, `repeat`, modifiers, dead key browser-dependent documente.
- Android: `keyCode`, `scanCode`, `unicodeChar`, `metaState`, locks.
- Win32: VK + scancode + lParam repeat + left/right modifiers + dead key sequence.
- AppKit/UIKit: keyCode/HID + characters vs charactersIgnoringModifiers.
- X11/Wayland: XKB/evdev fixtures pour AZERTY/QWERTY, AltGraph, compose/dead key.

### P2: enum coverage

- Generer un test de parite partielle depuis `keyboard-types`/winit pour les familles que Kadre choisit de supporter.
- Pour chaque key non supportee, verifier qu'elle tombe dans `PhysicalKey.Native` ou `LogicalKey.Unidentified` avec native info stable.

### P3: IME integration

- Contrats `Ime.Enabled -> Preedit* -> Commit/DeleteSurrounding -> Disabled`.
- Pas de double emission `KeyInput` pendant preedit quand le backend doit suivre winit.
- Cursor area et purpose/hints documentes par backend.

## Conclusion

Kadre a rattrape le design clavier moderne de winit sur la surface API commune. L'ecart restant est surtout industriel: tables exhaustives, mapping layout-aware, IME complet et garanties par backend. Les breaking changes prioritaires d'incubation (`deviceId`, `NativeKeyCode`, `NativeLogicalKey`) sont maintenant poses; la suite doit porter sur la qualite runtime et les tables exhaustives.
