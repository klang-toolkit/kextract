# Gap analysis fenetrage Kadre vs winit

Date: 2026-06-02
Commit winit cible: `c4afadbfabf7b1e7989b40b493db1a4c7bd8ff4e`
Portee: `WindowAttributes`, `Window`, `ActiveEventLoop`, types monitor/fullscreen/window support.
Hors portee: clavier, pointeur pur, IME riche sauf les methodes `Window` necessaires au pilotage IME.

References locales:

- winit: `third_party/winit/winit-core/src/window.rs`, `third_party/winit/winit-core/src/event_loop/mod.rs`, `third_party/winit/winit-core/src/monitor.rs`.
- Kadre: `kadre-core/src/commonMain/kotlin/org/graphiks/kadre/core/WindowAttributes.kt`, `Window.kt`, `ActiveEventLoop.kt`, `Monitor.kt`, `WindowingTypes.kt`.
- Garde-fou test: `kadre-core/src/commonTest/kotlin/org/graphiks/kadre/core/WinitWindowingCompatibilityTest.kt`.

## Synthese

Kadre couvre deja le socle portable de fenetrage winit: attributs de creation, creation de fenetre depuis `ActiveEventLoop`, controle de boucle, identite/handles, redraw, titre, tailles, scale factor, monitoring de base, fullscreen, theme/cursor/icon et quelques result types.

Les ecarts prioritaires ne demandent pas de breaking change dans cette tache, mais doivent rester visibles:

1. `Window.is_visible` et `Window.is_minimized` sont `Option<bool>` dans winit, mais `Boolean` non nullable dans Kadre. Kadre ne peut donc pas representer l'etat inconnu.
2. Les operations winit fallibles de curseur et de gestion fenetre (`set_cursor_position`, `set_cursor_grab`, `drag_window`, `drag_resize_window`, `show_window_menu`) retournent maintenant des resultats types cote API commune; plusieurs backends doivent encore remplacer le fallback `RequestError.Unsupported` par du support natif.
3. `Window.available_monitors` et `Window.primary_monitor` existent dans winit en plus des methodes `ActiveEventLoop`; Kadre expose maintenant les equivalents `Window.availableMonitors()` et `Window.primaryMonitor()` avec fallback `emptyList`/`null` quand le backend ne sait pas repondre.
4. `ActiveEventLoop.owned_display_handle` est non nullable dans winit; `ActiveEventLoop.ownedDisplayHandle()` est nullable et retourne `null` par defaut dans Kadre.
5. `Window.request_ime_update` et `Window.ime_capabilities` sont reportes: l'IME riche est hors portee de ce passage, au-dela des methodes Kadre existantes `setImeAllowed`, `setImeCursorArea`, `setImePurpose`.
6. Plusieurs setters d'apparence et de gestion de fenetre existent cote API commune, mais restent incomplets backend par backend.

## Matrice cible

Statuts autorises par le test commun: `implemented`, `unsupported-platform`, `deferred`.

| API winit cible | API Kadre locale | Statut | Notes |
|---|---|---:|---|
| `WindowAttributes` | `WindowAttributes` | implemented | Couvre titre, taille, visibilite initiale, resizable, contraintes, position, fullscreen, decorations, activation, cursor, theme, transparence, level, icon, parent. |
| `Window.id` / handles / redraw / title / sizes / scale | `Window.id`, `rawWindowHandle`, `rawDisplayHandle`, `requestRedraw`, `title`, `innerSize`, `outerSize`, `scaleFactor` | implemented | Socle portable represente. |
| Surface geometry et contraintes | `surfacePosition`, `outerPosition`, `setOuterPosition`, `surfaceSize`, `requestSurfaceSize`, `outerSize`, `safeArea`, `setMinSurfaceSize`, `setMaxSurfaceSize`, `surfaceResizeIncrements`, `setSurfaceResizeIncrements` | implemented | API portable presente; support exact encore variable selon backend. |
| `Window.is_visible() -> Option<bool>` | `Window.isVisible: Boolean` | deferred | Nullable winit non represente; Kadre force `true`/`false`. |
| `Window.is_minimized() -> Option<bool>` | `Window.isMinimized: Boolean` | deferred | Nullable winit non represente; Kadre force `true`/`false`. |
| Monitor/fullscreen sur `Window` | `currentMonitor`, `setFullscreen`, `fullscreen` | implemented | Sous-ensemble portable present. |
| Focus sur `Window` | `focusWindow`, `hasFocus` | implemented | API commune presente; AppKit implemente le focus, les autres backends restent a valider finement. |
| Apparence et etat fenetre | `setWindowLevel`, `requestUserAttention`, `setTheme`, `theme`, `setTransparent`, `setBlur`, `setWindowIcon`, `setContentProtected` | deferred | API commune presente, mais plusieurs methodes restent no-op ou partielles selon backend; content protection AppKit est implemente dans cet increment. |
| `Window.reset_dead_keys()` | `resetDeadKeys()` | implemented | API commune presente avec implementations ou no-op best-effort documentes par plateforme. |
| `Window.available_monitors()` | `Window.availableMonitors()` | implemented | Methode Window-level presente; fallback commun `emptyList()` quand inconnu; overrides desktop ou synthetiques quand l'enumeration backend est disponible. |
| `Window.primary_monitor()` | `Window.primaryMonitor()` | implemented | Nullable comme winit; fallback commun `null` quand inconnu; overrides quand le backend connait un primaire. X11 utilise le primaire XRandR quand disponible; Wayland retourne `null` par absence de concept primaire. |
| Cursor grab/position/hittest | `setCursor`, `setCursorVisible`, `setCursorGrab`, `setCursorPosition`, `setCursorHittest` | implemented | `setCursorGrab`, `setCursorPosition` et `setCursorHittest` retournent `WindowRequestResult`; les backends sans support réel retournent `Failure(RequestError.Unsupported(...))`. `setCursor` et `setCursorVisible` restent des setters `Unit` no-throw. |
| `Window.drag_window()` | `Window.dragWindow()` | deferred | Kadre retourne `WindowRequestResult` au lieu d'un no-op `Unit`, mais le support natif desktop reste a cabler. |
| `Window.drag_resize_window()` | `Window.dragResizeWindow()` | deferred | Kadre retourne `WindowRequestResult` au lieu d'un no-op `Unit`, mais le support natif desktop reste a cabler. |
| `Window.show_window_menu()` | `Window.showWindowMenu()` | deferred | Kadre retourne `WindowRequestResult` au lieu d'un no-op `Unit`, mais le support natif desktop reste a cabler. |
| `Window.request_ime_update()` | `setImeAllowed`, `setImeCursorArea`, `setImePurpose` | deferred | IME riche hors portee, sauf methodes Window existantes. |
| `Window.ime_capabilities()` | absent | deferred | Kadre n'a pas le modele de capacites IME winit. |
| `ActiveEventLoop` create/control/exit/proxy | `createWindow`, `controlFlow`, `setControlFlow`, `exit`, `isExiting`, `createProxy` | implemented | Socle de controle represente. |
| `ActiveEventLoop.available_monitors()` | `availableMonitors()` | implemented | Equivalent local. |
| `ActiveEventLoop.primary_monitor()` | `primaryMonitor()` | implemented | Nullable comme winit. |
| `ActiveEventLoop.owned_display_handle()` | `ownedDisplayHandle(): OwnedDisplayHandle?` | deferred | winit non-null; Kadre nullable avec `null` par defaut. |
| `MonitorHandle`, `VideoMode` | `MonitorHandle`, `VideoMode` | implemented | Id, nom, position, scale factor, mode courant, modes disponibles. |
| `Fullscreen::{Borderless, Exclusive}` | `Fullscreen.Borderless`, `Fullscreen.Exclusive` | unsupported-platform | API presente, mais `Exclusive` est documente comme fallback/no-op sur Wayland, Web, Android, UIKit. |
| Result/error support | `WindowRequestResult`, `SurfaceSizeRequestResult`, `RequestError` | implemented | Types utilises par `requestSurfaceSize`, les requetes curseur fallibles (`setCursorGrab`, `setCursorPosition`, `setCursorHittest`) et les requetes de gestion fenetre (`showWindowMenu`, `dragWindow`, `dragResizeWindow`). |

## Implications API

Cette analyse suit l'API courante. Les methodes Window-level de monitoring sont maintenant presentes avec un fallback portable `emptyList`/`null` documente.

Priorites probables pour une suite:

1. Ajouter une representation nullable ou inconnue pour `isVisible` et `isMinimized`, ou documenter formellement le choix Kadre d'un bool par defaut backend.
2. Convertir les operations cursor/drag critiques vers des result types Kadre existants, sans exceptions pour les limitations attendues.
3. Faire progresser les backends non desktop vers une enumeration multi-ecran reelle si la plateforme expose plus qu'un moniteur synthetique.
4. Rendre `ownedDisplayHandle` non nullable quand chaque backend peut produire un handle fiable, ou documenter la divergence.
5. Reporter `request_ime_update` / `ime_capabilities` vers un ticket IME dedie.
6. Faire progresser les backends desktop un par un pour les setters deja exposes: focus, content protection, resize increments, attention utilisateur, window level, theme et icon.
