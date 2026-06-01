# [R2] Moniteurs et plein écran

- **Milestone** : R2
- **Statut** : ✅ Fait
- **Dépend de** : R1
- **Modules touchés** : kadre-core, kadre-appkit, kadre-win32, kadre-x11, kadre-wayland, kadre-uikit, kadre-android, kadre-web-common, kadre-js, kadre-wasm

## Objectif
Exposer l'énumération des moniteurs (prérequis du plein écran exclusif) et les deux modes plein écran :
`Borderless` (partout applicable) et `Exclusive` (desktop uniquement).
`ActiveEventLoop` gagne `availableMonitors()` / `primaryMonitor()` ; `Window` gagne `currentMonitor()` et `setFullscreen`.

## Périmètre
### API commune (kadre-core)
```kotlin
// Nouveaux types
data class MonitorHandle(
    val id: Long,
    val name: String?,
    val position: PhysicalPosition,
    val scaleFactor: Double,
    val currentVideoMode: VideoMode?,
    val videoModes: List<VideoMode>
)
data class VideoMode(val size: PhysicalSize, val bitDepth: Int, val refreshRate: Double)

sealed class Fullscreen {
    data class Borderless(val monitor: MonitorHandle? = null) : Fullscreen()
    data class Exclusive(val monitor: MonitorHandle, val videoMode: VideoMode) : Fullscreen()
}

// ActiveEventLoop
fun availableMonitors(): List<MonitorHandle>
fun primaryMonitor(): MonitorHandle?

// Window
fun currentMonitor(): MonitorHandle?
fun setFullscreen(fullscreen: Fullscreen?)
val fullscreen: Fullscreen?

// WindowAttributes
var fullscreen: Fullscreen? = null
```

### Par backend
- **kadre-appkit** : réel via `NSScreen.screens`, `NSScreen.main` ; fullscreen via `toggleFullScreen` / `NSWindowStyleMaskFullScreen`
- **kadre-win32** : réel via `EnumDisplayMonitors` + `GetMonitorInfo` ; fullscreen exclusif via `ChangeDisplaySettings`
- **kadre-x11** : réel via RANDR (`XRRGetScreenResources`, `XRRGetCrtcInfo`) ; fullscreen via `_NET_WM_STATE_FULLSCREEN`
- **kadre-wayland** : réel via `wl_output` pour `availableMonitors` ; fullscreen borderless via `xdg_toplevel.set_fullscreen` ; **exclusif N/A** (no-op documenté)
- **kadre-uikit** : `availableMonitors` → écran principal UIKit ; fullscreen immersif (borderless) via `UIWindowScene`
- **kadre-android** : `availableMonitors` → `Display.getRealMetrics` ; fullscreen immersif via `WindowInsetsController`
- **kadre-web-common** : `availableMonitors` → écran courant via `screen.width/height` ; fullscreen borderless via Fullscreen API ; exclusif N/A (no-op documenté)
- **kadre-js** : délègue à web-common
- **kadre-wasm** : délègue à web-common

## Critères d'acceptation
- [ ] `availableMonitors()` retourne au moins un moniteur sur chaque backend
- [ ] `primaryMonitor()` non nul sur desktop et mobile
- [ ] `Borderless` fullscreen fonctionne sur tous les backends
- [ ] `Exclusive` fullscreen fonctionne sur appkit / win32 / x11 ; no-op documenté ailleurs
- [ ] Tests par backend (mock monitor ou écran réel)
- [ ] Dump ABI régénéré

## Notes
Exclusive fullscreen sur Wayland est protocolairement impossible — documenter explicitement le no-op.
Référence : plan R2 ; specs §3.
