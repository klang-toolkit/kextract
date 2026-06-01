# [R5-Gestures] Gestes trackpad

- **Milestone** : R5
- **Statut** : À faire
- **Dépend de** : R3, R4
- **Modules touchés** : kadre-core, kadre-appkit, kadre-uikit, kadre-web-common, kadre-js, kadre-wasm

## Objectif
Exposer les gestes multitouch trackpad/touchscreen : pinch (zoom), pan, rotation, double-tap, pression trackpad.
Principalement macOS et iOS ; les autres backends sont des no-ops documentés.
Ce lot est indépendant des autres lots R5 ; il ne bloque pas la 1.0.

## Périmètre
### API commune (kadre-core)
```kotlin
sealed class GestureEvent : WindowEvent() {
    data class PinchGesture(val delta: Double, val phase: TouchPhase) : GestureEvent()
    data class PanGesture(val delta: PhysicalPosition, val phase: TouchPhase) : GestureEvent()
    data class RotationGesture(val delta: Double, val phase: TouchPhase) : GestureEvent()
    object DoubleTapGesture : GestureEvent()
    data class TouchpadPressure(val pressure: Float, val stage: Int) : GestureEvent()
}
```

### Par backend
- **kadre-appkit** : réel via `NSGestureRecognizer` (magnification, rotation, pan) + `pressureChangeWithEvent`
- **kadre-uikit** : réel via `UIPinchGestureRecognizer`, `UIPanGestureRecognizer`, `UIRotationGestureRecognizer`, `UITapGestureRecognizer`
- **kadre-win32** : `PinchGesture` possible via WM_GESTURE (Precision Touchpad) — implémentation optionnelle ; sinon no-op documenté
- **kadre-x11** : no-op documenté (pas de protocole standard)
- **kadre-wayland** : no-op documenté (pas de protocole standard stable)
- **kadre-android** : `PinchGesture` + `PanGesture` via `ScaleGestureDetector` / `GestureDetector`
- **kadre-web-common** : `PinchGesture` via Pointer Events (pointermove multi-touch) ; `DoubleTapGesture` via `dblclick`
- **kadre-js** / **kadre-wasm** : délèguent à web-common

## Critères d'acceptation
- [ ] `PinchGesture` émis sur trackpad macOS (test manuel ou mock NSEvent)
- [ ] `PinchGesture` émis sur écran tactile iOS
- [ ] No-ops documentés sur x11 / wayland
- [ ] Tests par backend concerné
- [ ] Dump ABI régénéré

## Notes
`TouchpadPressure` : spécifique aux trackpads Apple Force Touch (macOS uniquement) — vérifier la disponibilité en runtime.
Référence : plan R5 §Trackpad gestures.
