# Proposition d'API clavier Kadre

Date: 2026-06-02
Statut: incubation, breaking changes autorisés  
Référence: winit `c4afadbfabf7b1e7989b40b493db1a4c7bd8ff4e`

## Décision

Kadre adopte une API clavier inspirée de winit, mais idiomatique Kotlin. Comme le projet est en incubation, l'ancienne API simplifiée peut être cassée plutôt que conservée en compatibilité legacy.

Principes:

- séparer strictement touche physique et touche logique;
- conserver les informations natives sans bloquer l'API commune;
- exposer les textes produits par la plateforme;
- fournir des helpers Kotlin pour les raccourcis;
- rendre les choix ambigus explicites, notamment le matching exact ou inclusif des modifieurs.

## API commune

```kotlin
data class KeyEvent(
    val physicalKey: PhysicalKey,
    val logicalKey: LogicalKey,
    val state: KeyState,
    val modifiers: KeyboardModifiers,
    val location: KeyLocation = KeyLocation.Standard,
    val repeat: Boolean = false,
    val synthetic: Boolean = false,
    val text: String? = null,
    val textWithAllModifiers: String? = null,
    val keyWithoutModifiers: LogicalKey? = null,
    val native: NativeKeyInfo = NativeKeyInfo(),
) {
    val isPressed: Boolean get() = state == KeyState.Pressed
    val isReleased: Boolean get() = state == KeyState.Released
    val character: String? get() = (logicalKey as? LogicalKey.Character)?.text
    val shortcutKey: LogicalKey get() = keyWithoutModifiers ?: logicalKey
    val effectiveText: String? get() = textWithAllModifiers ?: text
}
```

`shortcutKey` est le choix recommandé pour les raccourcis utilisateur, car il utilise la touche logique sans modifieurs quand le backend la fournit. `effectiveText` est le choix recommandé pour les terminaux/éditeurs qui veulent le texte complet produit avec tous les modifieurs.

## Touches physiques

```kotlin
sealed interface PhysicalKey {
    data class Code(val code: KeyCode) : PhysicalKey
    data class Native(val platform: KeyPlatform, val code: Long) : PhysicalKey
    data object Unidentified : PhysicalKey
}

fun PhysicalKey.location(): KeyLocation
fun KeyCode.location(): KeyLocation
```

`Native` est volontaire: Kadre ne doit pas jeter un scancode/HID usage encore non normalisé. `location()` donne un fallback commun pour gauche/droite/numpad quand le backend ne fournit pas déjà `KeyEvent.location`.

## Touches logiques

```kotlin
sealed interface LogicalKey {
    data class Character(val text: String) : LogicalKey
    data class Named(val key: NamedKey) : LogicalKey
    data class Dead(val accent: String?) : LogicalKey
    data class Unidentified(val native: NativeKeyInfo = NativeKeyInfo()) : LogicalKey
}
```

Ce modèle suit winit `Key::Character`, `Key::Named`, `Key::Dead`, tout en gardant une forme Kotlin simple.

## Modifieurs

`KeyboardModifiers` reste une `value class` bitflag. Les aliases publics couvrent maintenant `Shift`, `Ctrl`, `Alt`, `Meta`, `AltGraph`, `CapsLock`, `NumLock`, `Symbol`.

Pour le physique gauche/droite, `KeyboardModifierState` combine:

- `logical: KeyboardModifiers`;
- `physical: ModifierKeys` avec `leftShift`, `rightShift`, `leftCtrl`, `rightCtrl`, `leftAlt`, `rightAlt`, `leftMeta`, `rightMeta`.

## Raccourcis Kotlin

```kotlin
enum class KeyChordModifierMatch { Contains, Exact }

data class KeyChord(
    val physicalKey: PhysicalKey? = null,
    val logicalKey: LogicalKey? = null,
    val modifiers: KeyboardModifiers = KeyboardModifiers.NONE,
    val allowRepeat: Boolean = false,
    val modifierMatch: KeyChordModifierMatch = KeyChordModifierMatch.Contains,
)
```

`Contains` reste le défaut ergonomique: `Ctrl+S` matche aussi si `Shift` est maintenu. `Exact` est disponible pour les applications qui veulent la sémantique stricte type menu/éditeur.

## Plan TDD

1. Tests commonMain/commonTest pour les invariants: séparation physique/logique, dead key, texte, modifieurs, location, matching exact/inclusif.
2. Tests de mapping backend par backend avec fixtures natives minimales.
3. Tests ABI/API dumps après stabilisation de la surface publique.
4. Tests d'intégration: un sample doit recevoir `KeyInput` avec `physicalKey`, `logicalKey`, `shortcutKey`, `location` et `modifiers` cohérents.

## Travail restant

- Compléter les enums `KeyCode` et `NamedKey` avec les variantes winit/DOM manquantes prioritaires.
- Brancher proprement `textWithAllModifiers`, `keyWithoutModifiers`, `location` et `synthetic` dans AppKit, UIKit, Android, Web, Win32, X11 et Wayland.
- Documenter les garanties par backend, car certaines plateformes ne fournissent pas toutes les informations.
