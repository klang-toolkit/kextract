# Plan d'integration gestures en incubation

Date: 2026-06-01

## Position

Kadre est en incubation: on peut casser l'API publique pour obtenir un modele d'input coherent au lieu d'empiler des events historiques.

Objectif: converger vers le modele winit moderne:

- events pointer unifies pour souris, touch, stylet/tablette et source inconnue;
- `DeviceId` conserve dans les events ou le backend peut identifier la source;
- gestures integrees dans `WindowEvent`, pas dans un systeme parallele;
- semantique de payload alignee sur winit quand elle existe.

## Decision API

On remplace le modele actuel:

- `PointerMoved(position)` trop pauvre;
- `PointerEntered` / `PointerLeft` sans position ni source;
- `MouseInput(button, state)` separe du touch;
- `Touch(phase, location, id)` separe du pointeur;
- gestures absentes.

Par un modele unifie:

```kotlin
@JvmInline
value class DeviceId(val value: Long)

@JvmInline
value class FingerId(val value: Long)

enum class PointerKind {
    Mouse,
    Touch,
    TabletTool,
    Unknown,
}

sealed interface PointerSource {
    data object Mouse : PointerSource
    data class Touch(val fingerId: FingerId, val force: TouchForce? = null) : PointerSource
    data class TabletTool(
        val kind: TabletToolKind,
        val data: TabletToolData = TabletToolData(),
    ) : PointerSource
    data object Unknown : PointerSource
}

sealed interface ButtonSource {
    data class Mouse(val button: MouseButton) : ButtonSource
    data class Touch(val fingerId: FingerId, val force: TouchForce? = null) : ButtonSource
    data class TabletTool(
        val kind: TabletToolKind,
        val button: TabletToolButton,
        val data: TabletToolData = TabletToolData(),
    ) : ButtonSource
    data class Unknown(val code: Int) : ButtonSource
}
```

Support minimal tablet/force en incubation:

```kotlin
enum class TabletToolKind {
    Pen,
    Eraser,
    Cursor,
    Unknown,
}

enum class TabletToolButton {
    Tip,
    Barrel,
    SecondaryBarrel,
    Eraser,
    Unknown,
}

data class TabletToolData(
    val pressure: Float? = null,
    val tiltX: Float? = null,
    val tiltY: Float? = null,
    val twistDegrees: Float? = null,
)

sealed interface TouchForce {
    data class Calibrated(val force: Double, val maxPossibleForce: Double) : TouchForce
    data class Normalized(val value: Double) : TouchForce
}
```

## `WindowEvent` propose

Remplacer les events pointer/touch/mouse actuels par:

```kotlin
sealed interface WindowEvent {
    data object CloseRequested : WindowEvent
    data class Resized(val size: PhysicalSize<Int>) : WindowEvent
    data class Moved(val position: PhysicalPosition<Int>) : WindowEvent
    data class ScaleFactorChanged(val factor: Double) : WindowEvent
    data class Focused(val gained: Boolean) : WindowEvent

    data class KeyboardInput(
        val deviceId: DeviceId?,
        val key: Key,
        val state: KeyState,
        val modifiers: Modifiers,
        val isRepeat: Boolean = false,
        val isSynthetic: Boolean = false,
    ) : WindowEvent

    data class PointerMoved(
        val deviceId: DeviceId?,
        val position: PhysicalPosition<Double>,
        val primary: Boolean,
        val source: PointerSource,
    ) : WindowEvent

    data class PointerEntered(
        val deviceId: DeviceId?,
        val position: PhysicalPosition<Double>,
        val primary: Boolean,
        val kind: PointerKind,
    ) : WindowEvent

    data class PointerLeft(
        val deviceId: DeviceId?,
        val position: PhysicalPosition<Double>?,
        val primary: Boolean,
        val kind: PointerKind,
    ) : WindowEvent

    data class PointerButton(
        val deviceId: DeviceId?,
        val state: KeyState,
        val position: PhysicalPosition<Double>,
        val primary: Boolean,
        val button: ButtonSource,
    ) : WindowEvent

    data class MouseWheel(
        val deviceId: DeviceId?,
        val deltaX: Double,
        val deltaY: Double,
        val phase: TouchPhase,
    ) : WindowEvent

    data class PinchGesture(
        val deviceId: DeviceId?,
        val delta: Double,
        val phase: TouchPhase,
    ) : WindowEvent

    data class PanGesture(
        val deviceId: DeviceId?,
        val delta: PhysicalPosition<Float>,
        val phase: TouchPhase,
    ) : WindowEvent

    data class RotationGesture(
        val deviceId: DeviceId?,
        val deltaDegrees: Float,
        val phase: TouchPhase,
    ) : WindowEvent

    data class DoubleTapGesture(
        val deviceId: DeviceId?,
    ) : WindowEvent

    data class TouchpadPressure(
        val deviceId: DeviceId?,
        val pressure: Float,
        val stage: Long,
    ) : WindowEvent

    data object RedrawRequested : WindowEvent
    data object Destroyed : WindowEvent
}
```

Notes:

- `DeviceId?` est conserve partout ou l'origine device est pertinente. `null` signifie que le backend ne sait pas fournir l'information.
- `primary` suit winit: souris, premier doigt d'un geste multi-touch, ou source inconnue principale.
- `RotationGesture.deltaDegrees` est en degres, pas radians, pour rester aligne avec winit.
- `PanGesture.delta` utilise `Float` comme winit; les positions pointer restent en `Double`.

## Compatibilite et suppression

Comme l'API est incubee, on supprime au lieu de deprecier:

- `WindowEvent.MouseInput`
- `WindowEvent.Touch`
- `WindowEvent.PointerEntered` object sans payload
- `WindowEvent.PointerLeft` object sans payload
- `WindowEvent.PointerMoved(position)` ancien format
- `WindowEvent.MouseWheel(deltaX, deltaY)` ancien format

Migration directe:

| Ancien | Nouveau |
|---|---|
| `MouseInput(button, state)` | `PointerButton(button = ButtonSource.Mouse(button), state = state, ...)` |
| `Touch(Started, location, id)` | `PointerEntered(... kind = PointerKind.Touch)` + `PointerButton(ButtonSource.Touch(...), Pressed, ...)` |
| `Touch(Moved, location, id)` | `PointerMoved(source = PointerSource.Touch(...), ...)` |
| `Touch(Ended, location, id)` | `PointerButton(... Released, ...)` + `PointerLeft(kind = Touch, ...)` |
| `MouseWheel(deltaX, deltaY)` | `MouseWheel(deviceId, deltaX, deltaY, phase)` |

## Activation gestures

Ne pas ajouter `GestureConfig` dans l'API commune au debut.

Raison: winit expose les gestures comme events de fenetre et laisse les backends gerer leurs contraintes. En incubation, on doit d'abord stabiliser le modele d'event.

Exception possible plus tard: si UIKit/AppKit exigent un opt-in explicite pour eviter des conflits de recognizers, ajouter:

```kotlin
data class GestureRecognitionPolicy(
    val pinch: Boolean = true,
    val pan: Boolean = true,
    val rotation: Boolean = true,
    val doubleTap: Boolean = true,
    val touchpadPressure: Boolean = true,
)
```

Mais cette API doit etre differee tant qu'on n'a pas une contrainte backend reelle.

## Capacites backend

Garder une discovery, mais la rendre plus large que les gestures:

```kotlin
data class InputCapabilities(
    val deviceIds: Boolean,
    val touch: Boolean,
    val tabletTool: Boolean,
    val touchForce: Boolean,
    val pinchGesture: Boolean,
    val panGesture: Boolean,
    val rotationGesture: Boolean,
    val doubleTapGesture: Boolean,
    val touchpadPressure: Boolean,
)

interface Window {
    fun inputCapabilities(): InputCapabilities
}
```

Raison: le vrai probleme applicatif n'est pas seulement "gestures oui/non", mais "quelle richesse d'input ce backend peut fournir".

## Mapping backend

| Backend | Phase cible | Notes |
|---|---|---|
| AppKit | P0 | Mouse, trackpad gestures, pressure. Touch direct macOS reste limite. |
| UIKit | P0 | Touch unifie, pinch/pan/rotation/doubleTap via recognizers. |
| Web | P1 | Pointer Events vers `PointerSource`; gestures best-effort depuis pointer/touch/wheel. |
| Android | P1 | MotionEvent + detectors standards; `DeviceId` Android a mapper en `Long`. |
| Win32 | P2 | `WM_POINTER*` prioritaire pour touch/stylet; `WM_MOUSE*` fallback souris. |
| X11 | P2 | XInput2 pour touch/tablet si disponible. |
| Wayland | P2 | `wl_pointer`, `wl_touch`, tablet protocols, gestures si accessibles. |

## Plan d'execution

### Phase 0 - Refonte `kadre-core`

1. Ajouter `DeviceId`, `FingerId`, `PointerKind`, `PointerSource`, `ButtonSource`, `TouchForce`, `TabletToolKind`, `TabletToolButton`, `TabletToolData`.
2. Remplacer les variants pointer/mouse/touch dans `WindowEvent`.
3. Ajouter les variants gestures avec `DeviceId?`.
4. Ajouter `InputCapabilities` et `Window.inputCapabilities()`.
5. Adapter `kadre-test` pour produire les nouveaux events.
6. Mettre a jour docs et ABI.

Critere de sortie: le modele commonMain compile, les tests scripted peuvent generer pointer souris et touch.

### Phase 1 - Backends Apple

1. AppKit: mapper souris/trackpad vers pointer unifie.
2. AppKit: emettre pinch, pan, rotation, double tap, pressure.
3. UIKit: mapper touches vers pointer unifie.
4. UIKit: emettre pinch, pan, rotation, double tap.

Critere de sortie: demo locale capable de zoom/pan/rotate sur macOS et iOS.

### Phase 2 - Web et Android

1. Web: utiliser Pointer Events quand disponible, fallback touch/mouse.
2. Web: mapper `pointerId` en `DeviceId` ou `FingerId` selon source.
3. Android: mapper `MotionEvent.getDeviceId()` en `DeviceId`.
4. Android: implementer pinch/pan/double tap via detectors.

Critere de sortie: meme handler commonMain fonctionne sur Apple, Web, Android.

### Phase 3 - Desktop bas niveau

1. Win32: passer de `WM_TOUCH`/`WM_MOUSE*` vers `WM_POINTER*` quand possible.
2. X11: ajouter XInput2 pour touch/tablet.
3. Wayland: terminer dispatch events puis ajouter `wl_touch` et tablet protocols.
4. Documenter precisement les `InputCapabilities` par backend.

Critere de sortie: aucun backend ne ment sur ses capacites.

## Tests

- Tests commonMain de construction et pattern matching des nouveaux events.
- Tests `kadre-test` pour sequences souris, touch, multi-touch et gesture.
- Tests golden de conversion backend:
  - mouse press -> `PointerButton(ButtonSource.Mouse(...))`;
  - touch start/move/end -> entered/button/moved/button/left;
  - pinch -> `PinchGesture(deviceId, delta, phase)`;
  - rotation -> degres, signe stable.
- ABI: regeneration assumee, car breaking volontaire.

## Recommandation

Faire la refonte maintenant. Ajouter seulement des gestures au modele actuel creerait une API hybride qu'il faudrait casser plus tard pour supporter proprement touch, stylet et multi-device.
