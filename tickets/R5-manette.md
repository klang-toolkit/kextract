# [R5-Gamepad] Manette / Gamepad

- **Milestone** : R5
- **Statut** : ⛔ Hors périmètre (winit délègue à gilrs — non implémenté)
- **Dépend de** : R3, R4
- **Modules touchés** : kadre-core (surface minimale si intégration), kadre-android, kadre-uikit

## Objectif
Définir la position de Kadre vis-à-vis du support manette.
Ce lot est **hors du périmètre de winit lui-même** — winit délègue aux bibliothèques spécialisées (gilrs).
Kadre ne doit donc pas viser la parité avec winit sur ce point.

## Périmètre
### API commune (kadre-core)
Aucune nouvelle API obligatoire. Si une intégration légère est souhaitée :
```kotlin
// Optionnel — événements déjà partiellement présents (PR #163)
// Ne pas dupliquer ce que gilrs fait mieux
sealed class GamepadEvent : DeviceEvent() { ... }
```

### Par backend
- **kadre-android** : les manettes HW et Bluetooth arrivent via `KeyEvent` / `MotionEvent` Android — déjà couvert par PR #163 (KeyboardInput Android) ; documenter la limite
- **kadre-uikit** : `GCController` (Game Controller framework) — déjà couvert par PR #163 ; documenter la limite
- **kadre-win32** : XInput / DirectInput → déléguer à gilrs (no-op Kadre documenté)
- **kadre-appkit** : `GCController` → déléguer à gilrs (no-op Kadre documenté)
- **kadre-x11** / **kadre-wayland** : evdev joystick → déléguer à gilrs (no-op Kadre documenté)
- **kadre-web-common** : Gamepad API → hors périmètre (no-op documenté)

## Critères d'acceptation
- [ ] Documentation claire dans kadre-core indiquant que la manette est hors périmètre winit et délèguée à gilrs
- [ ] Les événements déjà implémentés (PR #163) ne sont pas régressés
- [ ] Aucune nouvelle API Kadre bloquante sur ce lot pour la 1.0

## Notes
⚠️ **Hors périmètre winit** : winit lui-même ne gère pas les manettes — il délègue à [gilrs](https://gitlab.com/gilrs-project/gilrs).
Kadre suit la même décision architecturale. Ne pas viser la parité manette avec winit.
Les évolutions PR #163 (Android + iOS clavier HW / manettes) sont la limite supérieure acceptable sans dépendre de gilrs.
Référence : plan R5 §Gamepad ; specs §7 known limitations.
