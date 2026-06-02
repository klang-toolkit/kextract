//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[WindowEvent](index.md)

# WindowEvent

sealed interface [WindowEvent](index.md)

Event emitted by a window.

Each variant corresponds to a state change or a user action on the targeted window.

### Typical usage

```kotlin
fun onWindowEvent(event: WindowEvent) {
    when (event) {
        WindowEvent.CloseRequested    -> quit()
        is WindowEvent.Resized        -> resize(event.size)
        is WindowEvent.Moved          -> move(event.position)
        is WindowEvent.ScaleFactorChanged -> updateDpi(event.factor)
        is WindowEvent.Focused        -> handleFocus(event.gained)
        is WindowEvent.KeyInput       -> handleKeyboard(event.event)
        is WindowEvent.PointerMoved   -> handlePointer(event.position)
        WindowEvent.PointerEntered    -> handleEnter()
        WindowEvent.PointerLeft       -> handleLeave()
        is WindowEvent.MouseInput     -> handleMouse(event.button, event.state)
        is WindowEvent.MouseWheel     -> handleWheel(event.deltaX, event.deltaY)
        is WindowEvent.Touch          -> handleTouch(event.phase, event.location, event.id)
        WindowEvent.RedrawRequested   -> redraw()
        WindowEvent.Destroyed         -> releaseResources()
        is WindowEvent.ThemeChanged   -> applyTheme(event.theme)
        is WindowEvent.ModifiersChanged -> updateModifiers(event.modifiers)
        is WindowEvent.Ime            -> handleIme(event.ime)
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
| [KeyInput](-key-input/index.md) |
| [PointerMoved](-pointer-moved/index.md) |
| [PointerEntered](-pointer-entered/index.md) |
| [PointerLeft](-pointer-left/index.md) |
| [MouseInput](-mouse-input/index.md) |
| [MouseWheel](-mouse-wheel/index.md) |
| [Touch](-touch/index.md) |
| [ModifiersChanged](-modifiers-changed/index.md) |
| [RedrawRequested](-redraw-requested/index.md) |
| [Destroyed](-destroyed/index.md) |
| [ThemeChanged](-theme-changed/index.md) |
| [DragEntered](-drag-entered/index.md) |
| [DragMoved](-drag-moved/index.md) |
| [DragDropped](-drag-dropped/index.md) |
| [DragLeft](-drag-left/index.md) |
| [PinchGesture](-pinch-gesture/index.md) |
| [PanGesture](-pan-gesture/index.md) |
| [RotationGesture](-rotation-gesture/index.md) |
| [DoubleTapGesture](-double-tap-gesture/index.md) |
| [TouchpadPressure](-touchpad-pressure/index.md) |
| [Occluded](-occluded/index.md) |
| [Ime](-ime/index.md) |

## Types

| Name | Summary |
|---|---|
| [CloseRequested](-close-requested/index.md) | [common]<br>data object [CloseRequested](-close-requested/index.md) : [WindowEvent](index.md)<br>The user requested closing the window (× button, Alt+F4, ⌘W, etc.). |
| [Destroyed](-destroyed/index.md) | [common]<br>data object [Destroyed](-destroyed/index.md) : [WindowEvent](index.md)<br>The window has been destroyed and its native resources released. |
| [DoubleTapGesture](-double-tap-gesture/index.md) | [common]<br>data object [DoubleTapGesture](-double-tap-gesture/index.md) : [WindowEvent](index.md)<br>A double-tap gesture was recognized. |
| [DragDropped](-drag-dropped/index.md) | [common]<br>data class [DragDropped](-drag-dropped/index.md)(val position: [PhysicalPosition](../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;, val paths: [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;) : [WindowEvent](index.md)<br>Files were dropped onto the window. |
| [DragEntered](-drag-entered/index.md) | [common]<br>data class [DragEntered](-drag-entered/index.md)(val position: [PhysicalPosition](../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;, val paths: [List](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;) : [WindowEvent](index.md)<br>A drag operation entered the window, carrying files at the given position. |
| [DragLeft](-drag-left/index.md) | [common]<br>data object [DragLeft](-drag-left/index.md) : [WindowEvent](index.md)<br>The drag cursor left the window without dropping. |
| [DragMoved](-drag-moved/index.md) | [common]<br>data class [DragMoved](-drag-moved/index.md)(val position: [PhysicalPosition](../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;) : [WindowEvent](index.md)<br>The drag cursor moved within the window while carrying files. |
| [Focused](-focused/index.md) | [common]<br>data class [Focused](-focused/index.md)(val gained: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)) : [WindowEvent](index.md)<br>The window gained or lost keyboard focus. |
| [Ime](-ime/index.md) | [common]<br>data class [Ime](-ime/index.md)(val ime: [WindowEvent.Ime.ImeEvent](-ime/-ime-event/index.md)) : [WindowEvent](index.md)<br>An IME (Input Method Editor) event occurred on this window. |
| [KeyInput](-key-input/index.md) | [common]<br>data class [KeyInput](-key-input/index.md)(val event: [KeyEvent](../-key-event/index.md)) : [WindowEvent](index.md)<br>A keyboard event occurred while the window had focus. |
| [ModifiersChanged](-modifiers-changed/index.md) | [common]<br>data class [ModifiersChanged](-modifiers-changed/index.md)(val state: [KeyboardModifierState](../-keyboard-modifier-state/index.md)) : [WindowEvent](index.md)<br>The logical or physical keyboard modifier state changed. |
| [MouseInput](-mouse-input/index.md) | [common]<br>data class [MouseInput](-mouse-input/index.md)(val button: [MouseButton](../-mouse-button/index.md), val state: [KeyState](../-key-state/index.md)) : [WindowEvent](index.md)<br>A mouse button has been pressed or released. |
| [MouseWheel](-mouse-wheel/index.md) | [common]<br>data class [MouseWheel](-mouse-wheel/index.md)(val deltaX: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), val deltaY: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) : [WindowEvent](index.md)<br>The mouse wheel (or trackpad) produced a scroll. |
| [Moved](-moved/index.md) | [common]<br>data class [Moved](-moved/index.md)(val position: [PhysicalPosition](../-physical-position/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;) : [WindowEvent](index.md)<br>The window has been moved. |
| [Occluded](-occluded/index.md) | [common]<br>data class [Occluded](-occluded/index.md)(val occluded: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)) : [WindowEvent](index.md)<br>The window's occlusion state changed. |
| [PanGesture](-pan-gesture/index.md) | [common]<br>data class [PanGesture](-pan-gesture/index.md)(val delta: [PhysicalPosition](../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;, val phase: [TouchPhase](../-touch-phase/index.md)) : [WindowEvent](index.md)<br>A pan (scroll) gesture changed state. |
| [PinchGesture](-pinch-gesture/index.md) | [common]<br>data class [PinchGesture](-pinch-gesture/index.md)(val delta: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), val phase: [TouchPhase](../-touch-phase/index.md)) : [WindowEvent](index.md)<br>A pinch (zoom) gesture changed state. |
| [PointerEntered](-pointer-entered/index.md) | [common]<br>data object [PointerEntered](-pointer-entered/index.md) : [WindowEvent](index.md)<br>The pointer just entered the window's client area. |
| [PointerLeft](-pointer-left/index.md) | [common]<br>data object [PointerLeft](-pointer-left/index.md) : [WindowEvent](index.md)<br>The pointer just left the window's client area. |
| [PointerMoved](-pointer-moved/index.md) | [common]<br>data class [PointerMoved](-pointer-moved/index.md)(val position: [PhysicalPosition](../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;) : [WindowEvent](index.md)<br>The pointer moved over the window. |
| [RedrawRequested](-redraw-requested/index.md) | [common]<br>data object [RedrawRequested](-redraw-requested/index.md) : [WindowEvent](index.md)<br>The window must be redrawn. |
| [Resized](-resized/index.md) | [common]<br>data class [Resized](-resized/index.md)(val size: [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;) : [WindowEvent](index.md)<br>The window has been resized. |
| [RotationGesture](-rotation-gesture/index.md) | [common]<br>data class [RotationGesture](-rotation-gesture/index.md)(val delta: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), val phase: [TouchPhase](../-touch-phase/index.md)) : [WindowEvent](index.md)<br>A rotation gesture changed state. |
| [ScaleFactorChanged](-scale-factor-changed/index.md) | [common]<br>data class [ScaleFactorChanged](-scale-factor-changed/index.md)(val factor: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) : [WindowEvent](index.md)<br>The window's DPI scale factor changed (e.g. moved to another monitor). |
| [ThemeChanged](-theme-changed/index.md) | [common]<br>data class [ThemeChanged](-theme-changed/index.md)(val theme: [Theme](../-theme/index.md)) : [WindowEvent](index.md)<br>The system UI theme changed (light ↔ dark). |
| [Touch](-touch/index.md) | [common]<br>data class [Touch](-touch/index.md)(val phase: [TouchPhase](../-touch-phase/index.md), val location: [PhysicalPosition](../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;, val id: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [WindowEvent](index.md)<br>A touch contact changed state. |
| [TouchpadPressure](-touchpad-pressure/index.md) | [common]<br>data class [TouchpadPressure](-touchpad-pressure/index.md)(val pressure: [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html), val stage: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)) : [WindowEvent](index.md)<br>A Force Touch / trackpad pressure event (macOS Force Touch trackpads only). |