# Gap analysis Kadre vs winit

Date: 2026-06-02
Référence winit analysée: `third_party/winit` à `c4afadbfabf7b1e7989b40b493db1a4c7bd8ff4e`

## Synthèse

Le sous-module `winit` est présent et initialisé sous `third_party/winit`. La référence clavier pertinente se trouve dans `third_party/winit/winit-core/src/keyboard.rs` et `third_party/winit/winit-core/src/event.rs`.

Kadre couvre déjà le coeur moderne de winit pour le clavier: séparation physique/logique, `KeyEvent`, `KeyState`, `KeyLocation`, texte produit, texte avec tous les modifieurs, touche sans modifieurs, répétition, synthétique, état de modifieurs et informations natives.

L'écart clavier principal n'est donc plus la forme de l'API, mais son exhaustivité et son branchement runtime par backend.

## Clavier

| Capacité winit | Statut Kadre | Écart restant |
|---|---|---|
| `PhysicalKey::Code(KeyCode)` | Couvert par `PhysicalKey.Code(KeyCode)` | `KeyCode` Kadre reste moins exhaustif que `keyboard-types`. |
| `PhysicalKey::Unidentified(NativeKeyCode)` | Partiel via `PhysicalKey.Native` + `Unidentified` | Kadre sépare le natif dans `NativeKeyInfo`; pas de wrapper `NativeKeyCode` équivalent. |
| `Key::Character` | Couvert par `LogicalKey.Character` | Aucun écart majeur. |
| `Key::Named` | Couvert par `LogicalKey.Named(NamedKey)` | `NamedKey` Kadre couvre les usages courants, pas encore toute la table winit. |
| `Key::Dead` | Couvert par `LogicalKey.Dead` | Aucun écart majeur. |
| `KeyEvent.text` | Couvert | Backend dependent. |
| `text_with_all_modifiers` | Couvert par `textWithAllModifiers` + helper `effectiveText` | Backend dependent. |
| `key_without_modifiers` | Couvert par `keyWithoutModifiers` + helper `shortcutKey` | Backend dependent. |
| `location` | Couvert + inférence `KeyCode.location()` | Les backends doivent encore l'alimenter systématiquement. |
| `repeat` | Couvert | Aucun écart majeur. |
| `is_synthetic` | Couvert par `synthetic` | Les backends doivent l'utiliser lors de releases synthétiques. |
| `ModifiersState` bitflags | Couvert par `KeyboardModifiers` | Kadre ajoute `AltGraph`, `CapsLock`, `NumLock`, `Symbol`; les aliases Kotlin sont exposés. |
| `ModifiersKeys` gauche/droite | Couvert par `ModifierKeys` | Runtime incomplet selon backend. |

### Points à prioriser

1. Compléter les tables `KeyCode` et `NamedKey` avec les variantes winit/DOM manquantes utiles: numpad parenthèses, kana/lang, media étendues, browser/launch complètes.
2. Brancher `textWithAllModifiers`, `keyWithoutModifiers`, `location` et `synthetic` dans chaque backend au lieu de simples fallbacks.
3. Ajouter des tests contractuels de mapping par backend, en gardant des fixtures natives lisibles.
4. Garder `KeyChord` comme couche Kotlin ergonomique: matching physique ou logique, modifieurs inclusifs par défaut, matching exact opt-in.

## Autres écarts winit prioritaires

| Zone | Statut Kadre | Priorité |
|---|---|---:|
| Pointer moderne unifié (`PointerSource`, `PointerKind`, stylet/tablette, primary/device id) | Partiel | P0 |
| IME runtime complet | API posée, runtime partiel | P0 |
| Drag and drop runtime | API posée, runtime partiel | P1 |
| Safe area / insets | Absent | P1 |
| Diagnostics `Result` / `try*` pour opérations fenêtre | Absent | P1 |
| `WindowButtons` | Absent | P2 |
| Resize increments | Absent | P2 |
| Owned display handle | Absent | P2 |
| Activation token / startup notify | Absent | P2 |

## Conclusion

Pour le clavier, Kadre est maintenant aligné sur l'architecture winit, avec une API Kotlin plus directe: sealed interfaces, data classes, value class bitflags et helpers de raccourcis. Le travail restant est surtout de couverture exhaustive et de runtime backend.

Le prochain gap critique par rapport à winit est le pointeur moderne: Kadre conserve encore un modèle `PointerMoved` / `MouseInput` / `Touch` simple, qui ne représente pas correctement stylet, multi-touch enrichi, `device_id`, `primary`, pression et sources Web Pointer Events.
