# [R1] État et géométrie de la fenêtre

- **Milestone** : R1
- **Statut** : ✅ Fait
- **Dépend de** : R0.1
- **Modules touchés** : kadre-core, kadre-appkit, kadre-win32, kadre-x11, kadre-wayland, kadre-uikit, kadre-android, kadre-web-common, kadre-js, kadre-wasm

## Objectif
Exposer les contrôles de fenêtre les plus courants : redimensionnable, minimisé, maximisé, décorations,
contraintes de taille, position extérieure, titre (getter), visibilité, `prePresentNotify`.
Les backends mobiles et web documentent des no-ops (pas de redimensionnement programmatique).

## Périmètre
### API commune (kadre-core)
```kotlin
// Window — nouvelles méthodes
fun setResizable(resizable: Boolean)
val isResizable: Boolean
fun setMinimized(minimized: Boolean)
val isMinimized: Boolean
fun setMaximized(maximized: Boolean)
val isMaximized: Boolean
fun setDecorations(decorated: Boolean)
val isDecorated: Boolean
fun setMinSurfaceSize(size: PhysicalSize?)
fun setMaxSurfaceSize(size: PhysicalSize?)
val outerPosition: PhysicalPosition
fun setOuterPosition(position: PhysicalPosition)
val isVisible: Boolean
val title: String
fun prePresentNotify()

// WindowAttributes — nouveaux champs
var minSize: PhysicalSize? = null
var maxSize: PhysicalSize? = null
var position: PhysicalPosition? = null
var maximized: Boolean = false
var decorations: Boolean = true
var active: Boolean = true
```

### Par backend
- **kadre-appkit** : réel via `NSWindow` (`setStyleMask`, `miniaturize`, `zoom`, `setFrame`, `setContentMinSize/MaxSize`, `title`)
- **kadre-win32** : réel via `ShowWindow`, `SetWindowPos`, `SetWindowLong` (style WS_THICKFRAME), `GetWindowText`
- **kadre-x11** : réel via `_NET_WM_STATE`, `XResizeWindow`, `XSetWMNormalHints`, `XMoveWindow`
- **kadre-wayland** : réel via `xdg_toplevel` (`set_min_size`, `set_max_size`, `set_maximized`, `set_title`) ; décorations via `zxdg_decoration_manager_v1`
- **kadre-uikit** : no-op documenté (iOS ne permet pas le redimensionnement programmatique)
- **kadre-android** : no-op documenté (Android ne permet pas le redimensionnement programmatique)
- **kadre-web-common** : no-op documenté pour la plupart ; `title` via `document.title`
- **kadre-js** : délègue à web-common
- **kadre-wasm** : délègue à web-common

## Critères d'acceptation
- [ ] Chacune des 13 nouvelles méthodes / propriétés implémentée sur les 4 backends desktop
- [ ] No-ops sur mobile/web documentés dans le code (annotation + commentaire)
- [ ] Tests desktop : setMaximized → isMaximized == true, etc.
- [ ] `WindowAttributes` étendu testé à la construction de fenêtre
- [ ] Dump ABI régénéré (android `.api` / ios `.klib`)

## Notes
`prePresentNotify()` : signal au compositeur (Wayland `pre_commit`, pas d'équivalent sur les autres — no-op acceptable).
Dépend de R0.1 pour les types scellés propres sur lesquels construire.
Référence : plan R1 ; specs §3.
