# [R5-MiscWindow] Divers fenêtre (attention, occluded, token, safe area…)

- **Milestone** : R5
- **Statut** : À faire
- **Dépend de** : R3, R4
- **Modules touchés** : kadre-core, kadre-appkit, kadre-win32, kadre-x11, kadre-wayland, kadre-uikit, kadre-android, kadre-web-common, kadre-js, kadre-wasm

## Objectif
Rassembler les fonctionnalités de fenêtre avancées qui ne forment pas un sous-système cohérent :
attention utilisateur, occultation, token d'activation (Wayland/X11), protection du contenu,
safe area (mobile), menu de fenêtre, drag par la titlebar, avertissement mémoire mobile.

## Périmètre
### API commune (kadre-core)
```kotlin
enum class UserAttentionType { Critical, Informational }

// WindowEvent
object Occluded : WindowEvent()                          // fenêtre masquée/révélée
data class ActivationTokenDone(val token: String) : WindowEvent()  // Wayland/X11

// Window
fun requestUserAttention(requestType: UserAttentionType?)
val contentProtected: Boolean
fun setContentProtected(protected: Boolean)
fun safeAreaInsets(): Insets        // mobile (iOS safe area, Android insets)
fun showWindowMenu(position: PhysicalPosition)
fun dragWindow()
fun dragResizeWindow(direction: ResizeDirection)

// ApplicationHandler
fun memoryWarning()     // mobile uniquement (iOS didReceiveMemoryWarning, Android onTrimMemory)
```

### Par backend
- **kadre-appkit** : `requestUserAttention` via `NSApp.requestUserAttention` ; `Occluded` via `NSWindowDidChangeOcclusionStateNotification` ; `setContentProtected` via `NSWindow.sharingType` ; `dragWindow` via `performWindowDragWithEvent`
- **kadre-win32** : `requestUserAttention` via `FlashWindowEx` ; `setContentProtected` via `SetWindowDisplayAffinity(WDA_EXCLUDEFROMCAPTURE)` ; `showWindowMenu` via `TrackPopupMenu(GetSystemMenu(...))` ; `ActivationTokenDone` N/A
- **kadre-x11** : `requestUserAttention` via `_NET_WM_STATE_DEMANDS_ATTENTION` ; `ActivationTokenDone` via `_NET_STARTUP_ID` ; `Occluded` N/A (no-op documenté)
- **kadre-wayland** : `requestUserAttention` via `xdg_toplevel.set_minimized` (approximation) ; `ActivationTokenDone` via `xdg_activation_v1` ; `dragWindow` via `xdg_toplevel.move`
- **kadre-uikit** : `safeAreaInsets` via `UIView.safeAreaInsets` ; `memoryWarning` via `applicationDidReceiveMemoryWarning` ; reste no-op documenté
- **kadre-android** : `safeAreaInsets` via `WindowInsetsCompat` ; `memoryWarning` via `onTrimMemory` ; reste no-op documenté
- **kadre-web-common** : `Occluded` via Page Visibility API (`visibilitychange`) ; reste no-op documenté
- **kadre-js** / **kadre-wasm** : délèguent à web-common

## Critères d'acceptation
- [ ] `requestUserAttention(Critical)` fait clignoter l'icône sur macOS et Windows
- [ ] `Occluded` émis quand la fenêtre est cachée sur macOS
- [ ] `setContentProtected(true)` empêche la capture d'écran sur Windows (`WDA_EXCLUDEFROMCAPTURE`)
- [ ] `memoryWarning()` appelé sur iOS lors d'une pression mémoire (test simulateur)
- [ ] No-ops documentés cohérents sur tous les backends
- [ ] Tests par backend pour chaque fonctionnalité applicable
- [ ] Dump ABI régénéré

## Notes
`dragResizeWindow` nécessite de savoir quelle bordure est ciblée (`ResizeDirection` enum à définir).
`ActivationTokenDone` est spécifique à l'écosystème FreeDesktop — no-op sur macOS/Windows/mobile.
Référence : plan R5 §Misc window.
