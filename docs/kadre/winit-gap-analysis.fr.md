# Gap analysis Kadre vs winit

Date: 2026-06-01  
Référence winit analysée: `third_party/winit` à `c4afadbfabf7b1e7989b40b493db1a4c7bd8ff4e`

## Périmètre

Cette analyse compare l'API publique commune de Kadre (`kadre-core`) avec l'API publique de winit telle que présente dans le sous-module local, principalement `winit-core`.

Elle distingue deux types d'écart:

- **Écart API**: Kadre n'expose pas une capacité winit, ou l'expose avec une sémantique plus pauvre.
- **Écart runtime**: Kadre expose le type ou la méthode, mais les backends ne l'émettent pas encore ou l'implémentent en no-op documenté.

## Synthèse

Kadre couvre le socle winit attendu pour une bibliothèque de fenêtrage multiplateforme: `ApplicationHandler`, `EventLoop`, `ActiveEventLoop`, `Window`, events de fenêtre, events device, moniteurs, fullscreen, handles natifs, curseurs, thème et attributs de création.

Les écarts majeurs restants sont concentrés sur quatre zones:

| Zone | Statut Kadre | Risque |
|---|---:|---|
| Modèle clavier | Partiel | Élevé pour éditeurs, raccourcis avancés, layouts internationaux |
| Modèle pointeur | Partiel | Élevé pour touch/stylet/tablette, Web Pointer Events |
| Fenêtre avancée | Partiel | Moyen pour apps desktop sophistiquées |
| Émission backend IME/DnD/gestures | API posée, runtime incomplet | Élevé pour UX texte et drag/drop |

## Couvert correctement

| Fonction winit | Couverture Kadre | Notes |
|---|---|---|
| `ApplicationHandler` | Oui | Kadre expose `newEvents`, `canCreateSurfaces`, `windowEvent`, `deviceEvent`, `aboutToWait`, `resumed`, `suspended`, `destroySurfaces`, `memoryWarning`. |
| `EventLoop::run_app` | Oui | Modèle callback-driven aligné avec winit moderne. |
| `ActiveEventLoop` de base | Oui | `createWindow`, `setControlFlow`, `controlFlow`, `exit`, `isExiting`, `createProxy`. |
| `ControlFlow` | Oui | `Poll`, `Wait`, `WaitUntil`. |
| Window lifecycle | Oui | `CloseRequested`, `Destroyed`, `RedrawRequested`, focus, resize, move, scale factor. |
| Moniteurs | Oui | `availableMonitors`, `primaryMonitor`, `currentMonitor`, `VideoMode`, fullscreen borderless/exclusive. |
| Handles natifs | Oui | `RawWindowHandle` / `RawDisplayHandle` couvrent AppKit, UIKit, Android, Win32, X11, Wayland, Web. |
| Apparence de fenêtre | Majoritairement | title, visible, resizable, minimized, maximized, decorations, transparent, blur, window level, icon. |
| Curseurs standards | Majoritairement | `CursorIcon`, visibilité, grab, position, hittest. |
| Thème | Oui côté API | `systemTheme`, `Window.theme`, `setTheme`, `ThemeChanged`. |

## Écarts API prioritaires

### P0 - Modèle clavier moins expressif

winit expose un `KeyEvent` riche:

- `physical_key: PhysicalKey` avec `KeyCode` détaillé.
- `logical_key: Key`, modèle ouvert `Character` / `Named` / `Dead`.
- `text`, `text_with_all_modifiers`, `key_without_modifiers`.
- `location`, `repeat`, `state`.
- `is_synthetic` au niveau `WindowEvent::KeyboardInput`.

Kadre expose:

- `Key`, enum fermé limité aux lettres, chiffres, F1-F12, navigation et modifieurs.
- `text`, `scanCode`, `location`, `isRepeat`, mais pas de `PhysicalKey` typé ni de `NamedKey` large.
- pas de distinction `text_with_all_modifiers` / `key_without_modifiers`.
- pas de `is_synthetic`.
- `Modifiers` ne porte que Shift/Ctrl/Alt/Meta, sans état gauche/droite ni état logique détaillé.

Impact: raccourcis internationaux, éditeurs de texte, jeux avec remapping clavier robuste et claviers non-US restent moins fiables que winit.

Décision requise: soit accepter explicitement un modèle clavier simplifié pour Kadre 1.x, soit introduire un modèle parallèle non cassant (`PhysicalKey`, `LogicalKey`, `NamedKey`, `DeadKey`, `KeyboardInput2`) avant une éventuelle bascule majeure.

### P0 - Modèle pointeur non unifié

winit a migré vers un modèle `PointerMoved` / `PointerEntered` / `PointerLeft` / `PointerButton` avec:

- `PointerSource`: mouse, touch, tablet tool, unknown.
- `PointerKind`: mouse, touch, tablet tool, unknown.
- `ButtonSource`: mouse, touch, tablet tool, unknown.
- `primary`, `device_id`, position sur enter/left/button.
- `Force`, `TabletToolKind`, `TabletToolData`, `TabletToolButton`.

Kadre conserve:

- `PointerMoved(position)` sans source, `primary`, `device_id` ni force.
- `PointerEntered` / `PointerLeft` sans position ni source.
- `MouseInput(button, state)` séparé de `Touch`.
- `Touch(phase, location, id)` mais pas intégré au flux pointer.
- pas de stylet/tablette.

Impact: la parité Web Pointer Events, le multi-touch avancé, la pression tactile et les tablettes graphiques ne peuvent pas être représentés proprement.

Décision requise: conserver le modèle simple comme API stable, ou ajouter une nouvelle famille d'events pointer enrichie sans supprimer les events existants.

### P1 - `WindowButtons`

winit expose:

- `WindowAttributes.enabled_buttons`.
- `Window::set_enabled_buttons`.
- `Window::enabled_buttons`.
- bitflags close/minimize/maximize.

Kadre n'a pas d'équivalent.

Impact: impossible de désactiver finement les boutons de décoration natifs, utile pour des apps desktop à workflow contrôlé.

### P1 - `safe_area` / insets

winit expose `Window::safe_area()` et utilise cette notion dans la documentation de rendu, notamment mobile/web/notch.

Kadre n'a pas de type `Insets` ni de méthode `safeArea`.

Impact: les applications iOS, Android, Web mobile ou macOS avec notch ne disposent pas d'une API commune pour éviter les zones obstruées.

### P1 - Incréments de resize

winit expose:

- `WindowAttributes.surface_resize_increments`.
- `Window::surface_resize_increments`.
- `Window::set_surface_resize_increments`.

Kadre n'a pas d'équivalent.

Impact: terminaux, éditeurs en grille, pixel art tools et apps à cellules ne peuvent pas demander un redimensionnement par pas.

### P1 - `owned_display_handle`

winit expose `ActiveEventLoop::owned_display_handle()` pour conserver un display handle persistant et clonable.

Kadre expose `rawDisplayHandle` sur `Window`, mais pas d'équivalent persistant au niveau `ActiveEventLoop`.

Impact: intégrations graphiques qui veulent initialiser un device/display avant ou indépendamment d'une fenêtre sont moins ergonomiques.

### P2 - Activation token / startup notify

winit expose `WindowEvent::ActivationTokenDone` et les extensions `startup_notify`.

Kadre n'a pas d'équivalent.

Impact: intégration desktop Linux/startup notification incomplète. Priorité basse sauf cible desktop Linux native poussée.

### P2 - Parent window et attributs plateforme opaques

winit expose:

- `WindowAttributes::with_parent_window`.
- `WindowAttributes::with_platform_attributes`.
- extensions par plateforme (`WindowAttributesExt*`, `WindowExt*`, `ActiveEventLoopExt*`).

Kadre préfère une API commune pure Kotlin et ne propose pas de mécanisme générique d'attributs plateforme.

Impact: impossible de créer des child windows natives ou d'exposer proprement certaines options spécifiques sans ajouter des APIs Kadre dédiées par backend.

## Écarts de sémantique fenêtre

| winit | Kadre | Écart |
|---|---|---|
| `surface_size` / `request_surface_size` | `innerSize`, pas de request explicite | Kadre ne distingue pas clairement surface vs inner dans les noms publics. |
| `surface_position` | absent | Conversion surface/window/desktop moins complète. |
| `outer_position -> Result` | `outerPosition` direct | Kadre masque les cas unsupported par valeur/no-op selon backend. |
| `set_cursor_grab -> Result` | `setCursorGrab` sans erreur | Kadre documente no-op, winit permet fallback applicatif selon erreur. |
| `set_cursor_position -> Result` | `setCursorPosition` sans erreur | Même problème de diagnostic. |
| `drag_window -> Result` | `dragWindow` no-op par défaut | API présente, mais pas de retour d'échec. |
| `drag_resize_window -> Result` | `dragResizeWindow` no-op par défaut | API présente, mais pas de retour d'échec. |
| `set_cursor_hittest -> Result` | `setCursorHittest` sans erreur | Unsupported invisible côté appelant. |
| `is_visible -> Option<bool>` | `isVisible: Boolean` | Kadre perd l'état "unknown". |
| `is_minimized -> Option<bool>` | `isMinimized: Boolean` | Kadre perd l'état "unknown". |

Ce choix rend l'API Kadre plus simple, mais moins diagnostique que winit. Pour des apps de production desktop, il faudra probablement introduire des retours `Result` ou des méthodes `try*` sans casser les méthodes existantes.

## Écarts runtime backend

Ces fonctionnalités existent dans l'API Kadre mais restent non émises ou no-op dans tout ou partie des backends:

| Fonction | Statut Kadre |
|---|---|
| IME (`Enabled`, `Preedit`, `Commit`, `DeleteSurrounding`, `Disabled`) | Types exposés, émission backend à compléter. |
| Drag and drop | Types exposés, émission backend à compléter. |
| Gestures (`Pinch`, `Pan`, `Rotation`, `DoubleTap`, `TouchpadPressure`) | Types exposés, émission backend à compléter. |
| `Occluded` | Type exposé, émission backend à compléter. |
| Custom cursors | `createCustomCursor` / `setCustomCursor` no-op par défaut. |
| `requestUserAttention` | no-op par défaut. |
| `setContentProtected` | no-op par défaut. |
| `showWindowMenu` | no-op par défaut. |
| `dragWindow` / `dragResizeWindow` | no-op par défaut. |
| `ModifiersChanged` | partiel: AppKit / Win32 / Web, pas X11 / Wayland / Android / iOS. |

## Plateformes

winit couvre aussi Orbital/Redox et des extensions de plateforme plus larges. Kadre cible plutôt macOS, iOS, Android, Windows, Linux X11/Wayland et Web.

Écarts notables:

- Pas de cible Redox/Orbital Kadre.
- Pas de modèle public d'extension plateforme comparable aux traits `WindowExt*`, `WindowAttributesExt*`, `ActiveEventLoopExt*`.
- Pas de garanties de runtime réel équivalentes à winit sur Windows/Linux/Web/iOS dans les notes actuelles: le repo indique surtout compilation, ABI et tests unitaires.

## Priorisation proposée

1. **P0 clavier**: ajouter un modèle clavier enrichi compatible winit sans casser l'existant.
2. **P0 pointeur**: décider si Kadre veut la parité pointer/stylet ou rester volontairement sur mouse/touch simple.
3. **P1 IME runtime**: brancher au moins AppKit, Win32, X11/Wayland et mobile, sinon l'API posée donne une fausse impression de support texte.
4. **P1 DnD runtime**: brancher desktop + Web en priorité.
5. **P1 safe area**: ajouter `Insets` + `Window.safeArea` pour mobile/Web/macOS.
6. **P1 diagnostics Result/try***: ajouter des variantes `trySetCursorGrab`, `trySetCursorPosition`, `tryDragWindow`, etc.
7. **P2 WindowButtons / resize increments / owned display handle**: utiles, mais moins bloquants pour Pong et apps de rendu simples.
8. **P2 startup notify / activation token**: à traiter seulement si l'intégration desktop Linux devient un objectif produit.

## Conclusion

Kadre est proche de winit pour le chemin principal "créer une fenêtre, recevoir input basique, rendre, gérer fullscreen/moniteurs". La parité n'est pas atteinte pour les usages avancés: texte international, pointer/touch/stylet moderne, safe areas, diagnostics d'échec et plusieurs features desktop.

La dette principale n'est pas uniquement d'ajouter des variantes: il faut choisir entre une API Kadre volontairement plus simple que winit, ou une deuxième génération de types d'input/fenêtre qui expose les mêmes informations que winit sans casser l'API 1.x.
