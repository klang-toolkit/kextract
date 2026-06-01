# [R5-DnD] Glisser-déposer (Drag & Drop)

- **Milestone** : R5
- **Statut** : À faire
- **Dépend de** : R3, R4
- **Modules touchés** : kadre-core, kadre-appkit, kadre-win32, kadre-x11, kadre-wayland, kadre-web-common, kadre-js, kadre-wasm

## Objectif
Recevoir des fichiers ou du texte déposés sur la fenêtre via le mécanisme natif drag & drop.
Ce lot est indépendant des autres lots R5 ; il ne bloque pas la 1.0.

## Périmètre
### API commune (kadre-core)
```kotlin
sealed class DragDropEvent : WindowEvent() {
    data class DragEntered(val position: PhysicalPosition, val paths: List<String>) : DragDropEvent()
    data class DragMoved(val position: PhysicalPosition) : DragDropEvent()
    data class DragDropped(val position: PhysicalPosition, val paths: List<String>) : DragDropEvent()
    object DragLeft : DragDropEvent()
}
```

### Par backend
- **kadre-appkit** : réel via `NSDraggingDestination` protocol
- **kadre-win32** : réel via `IDropTarget` (OLE drag & drop)
- **kadre-x11** : réel via XDND protocol (`XdndEnter`, `XdndPosition`, `XdndDrop`)
- **kadre-wayland** : réel via `wl_data_device` / `zwp_primary_selection_device_v1`
- **kadre-uikit** : réel via `UIDropInteraction` (iOS 11+)
- **kadre-android** : réel via `View.setOnDragListener` (Android 7+)
- **kadre-web-common** : réel via événements DOM `dragenter/dragover/drop`
- **kadre-js** / **kadre-wasm** : délèguent à web-common

## Critères d'acceptation
- [ ] `DragEntered` + `DragDropped` avec la liste des chemins reçus sur macOS et Windows
- [ ] `DragLeft` émis quand le curseur sort de la fenêtre pendant un drag
- [ ] Tests par backend (drag simulé ou mock)
- [ ] Dump ABI régénéré

## Notes
Sur web, `paths` contient les noms de fichiers (pas les chemins complets — API Filesystem limitée).
Sur Wayland, le type MIME doit être filtré pour sélectionner `text/uri-list`.
Référence : plan R5 §Drag & drop.
