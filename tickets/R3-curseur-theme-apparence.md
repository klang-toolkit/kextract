# [R3] Curseur, thème et apparence

- **Milestone** : R3
- **Statut** : À faire
- **Dépend de** : R0.1
- **Modules touchés** : kadre-core, kadre-appkit, kadre-win32, kadre-x11, kadre-wayland, kadre-uikit, kadre-android, kadre-web-common, kadre-js, kadre-wasm

## Objectif
Exposer le contrôle du curseur (icône, visibilité, confinement, position, hittest), la détection et le changement
de thème (clair/sombre) ainsi que les attributs d'apparence avancés (niveau de fenêtre, transparence, flou, icône).
Parallélisable avec R4 dès que R0.1 est validé.

## Périmètre
### API commune (kadre-core)
```kotlin
// Curseur
enum class CursorIcon { Default, Pointer, Text, Crosshair, Move,
    ResizeNorth, ResizeSouth, ResizeEast, ResizeWest,
    ResizeNorthEast, ResizeNorthWest, ResizeSouthEast, ResizeSouthWest,
    NotAllowed, Grab, Grabbing, /* subset utile */ }
enum class CursorGrabMode { None, Confined, Locked }

// Window
fun setCursor(cursor: CursorIcon)
fun setCursorVisible(visible: Boolean)
fun setCursorGrab(mode: CursorGrabMode)
fun setCursorPosition(position: PhysicalPosition)
fun setCursorHittest(hittest: Boolean)

// Thème
enum class Theme { Light, Dark }
// WindowEvent déjà déclaré :
data class ThemeChanged(val theme: Theme) : WindowEvent()
// Window
fun theme(): Theme?
fun setTheme(theme: Theme?)
// ActiveEventLoop
fun systemTheme(): Theme?

// Apparence
enum class WindowLevel { AlwaysOnBottom, Normal, AlwaysOnTop }
fun setWindowLevel(level: WindowLevel)
fun setTransparent(transparent: Boolean)
fun setBlur(blur: Boolean)
data class Icon(val rgba: ByteArray, val width: Int, val height: Int)
fun setWindowIcon(icon: Icon?)

// WindowAttributes
var cursor: CursorIcon = CursorIcon.Default
var preferredTheme: Theme? = null
var transparent: Boolean = false
var blur: Boolean = false
var windowLevel: WindowLevel = WindowLevel.Normal
var windowIcon: Icon? = null
```

### Par backend
- **kadre-appkit** : curseur via `NSCursor` ; confinement via `CGAssociateMouseAndMouseCursorPosition` ; thème via `NSAppearance` ; transparence via `NSWindow.backgroundColor` ; blur via `NSVisualEffectView` ; windowLevel via `NSWindow.level` ; icône via `NSApp.applicationIconImage`
- **kadre-win32** : curseur via `SetCursor(LoadCursor(...))` ; confinement via `ClipCursor` ; thème via registry `AppsUseLightTheme` + `WM_SETTINGCHANGE` ; transparence via `SetLayeredWindowAttributes` ; `WS_EX_LAYERED` ; icône via `SendMessage(WM_SETICON)`
- **kadre-x11** : curseur via `XDefineCursor` (Xcursor) ; confinement via `XGrabPointer` ; thème via `_GTK_THEME_VARIANT` / `xsettings` ; icône via `_NET_WM_ICON`
- **kadre-wayland** : curseur via `wl_pointer.set_cursor` (surface xcursor) ; confinement via `zwp_pointer_constraints_v1` ; thème via `org.freedesktop.portal.Settings` ; icône N/A (no-op documenté)
- **kadre-uikit** : curseur no-op ; thème via `UIViewController.overrideUserInterfaceStyle` ; reste no-op documenté
- **kadre-android** : curseur no-op ; thème via `UiModeManager` (nuit/jour) ; reste no-op documenté
- **kadre-web-common** : curseur via CSS `cursor` ; thème via `matchMedia('prefers-color-scheme')` ; grab partiel (Pointer Lock API) — no sovereign grab documenté ; reste no-op documenté
- **kadre-js** / **kadre-wasm** : délèguent à web-common

## Critères d'acceptation
- [ ] `setCursor(CursorIcon.Pointer)` change le curseur sur les 4 backends desktop
- [ ] `setCursorGrab(Confined)` confine le curseur dans la fenêtre sur desktop (no-op documenté sur mobile/web)
- [ ] `ThemeChanged` émis sur changement système (macOS/Windows)
- [ ] `setTransparent(true)` rend la fenêtre transparente sur macOS et Windows
- [ ] Tests par backend pour chaque groupe (curseur, thème, apparence)
- [ ] Dump ABI régénéré

## Notes
`CursorGrabMode.Locked` : vérifier la disponibilité de `zwp_pointer_constraints_v1` sur Wayland en runtime.
Web : le Pointer Lock API est asynchrone — gérer le callback.
Référence : plan R3 ; specs §3.
