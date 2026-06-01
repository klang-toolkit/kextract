# [R5-CustomCursor] Curseurs personnalisés

- **Milestone** : R5
- **Statut** : À faire
- **Dépend de** : R3, R4
- **Modules touchés** : kadre-core, kadre-appkit, kadre-win32, kadre-x11, kadre-wayland, kadre-web-common, kadre-js, kadre-wasm

## Objectif
Permettre la création et l'utilisation de curseurs définis par l'application (image RGBA + hotspot),
avec support optionnel des animations.
Ce lot est indépendant des autres lots R5 ; il ne bloque pas la 1.0.

## Périmètre
### API commune (kadre-core)
```kotlin
data class CursorImage(
    val rgba: ByteArray,
    val width: Int,
    val height: Int,
    val hotspotX: Int,
    val hotspotY: Int
)

// CustomCursor : handle opaque retourné par createCustomCursor
class CustomCursor internal constructor(internal val id: Long)

// ActiveEventLoop
fun createCustomCursor(image: CursorImage): CustomCursor

// Window
fun setCustomCursor(cursor: CustomCursor)
```

### Par backend
- **kadre-appkit** : réel via `NSCursor(image:hotSpot:)` avec `NSImage` construit depuis les données RGBA
- **kadre-win32** : réel via `CreateIconIndirect` (ICONINFO + HBITMAP/HCURSOR)
- **kadre-x11** : réel via `XcursorImageLoadCursor` (libXcursor)
- **kadre-wayland** : réel via `wl_cursor_theme` ou surface `wl_pointer.set_cursor` avec buffer RGBA
- **kadre-uikit** : no-op documenté (iOS ne supporte pas les curseurs personnalisés)
- **kadre-android** : no-op documenté
- **kadre-web-common** : réel via CSS `cursor: url(data:image/png;base64,...) hotspotX hotspotY, auto`
- **kadre-js** / **kadre-wasm** : délèguent à web-common

## Critères d'acceptation
- [ ] Un curseur RGBA personnalisé s'affiche correctement sur les 4 backends desktop
- [ ] `createCustomCursor` sur web génère un data-URL PNG valide
- [ ] No-ops documentés sur mobile
- [ ] Tests par backend (vérification visuelle ou mock)
- [ ] Dump ABI régénéré

## Notes
Animations de curseur : fonctionnalité optionnelle dans ce lot (macOS supporte les curseurs animés via `NSCursor` + timer).
Gérer la libération mémoire de `CustomCursor` (destructor / `close()`).
Référence : plan R5 §Custom cursors.
