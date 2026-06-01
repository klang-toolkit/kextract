# Kadre

POC de fenêtrage cross-platform en **Kotlin pur**, inspiré de [winit](https://github.com/rust-windowing/winit).

Objectif : exposer des handles natifs (`NSView`, `UIView`, `android.view.Surface`) consommables par un renderer 3D (Metal, Vulkan, [wgpu4k](https://github.com/wgpu4k/wgpu4k)) **sans dépendance AWT/Swing**.

## Tutoriels

- [Intégrer Kadre dans une application Windows](./tutorials/windows-app.md) — fenêtre Win32, événements, DPI PerMonitorV2
- [Intégrer Kadre dans une application Linux](./tutorials/linux-app.md) — X11 et Wayland, auto-détection backend, DPI, CI headless
- [Intégrer Kadre dans une page web](./tutorials/web-embed.md) — canvas HTML, Kotlin/JS + Kotlin/Wasm, boucle RAF

## Blog

- [Kadre v1.0.0 — 6 plateformes, Pong cross-platform, Demo en direct](./blog/v1.0.0-release.md) — la release 1.0.0 : macOS, iOS, Android, Win32, Web (JS+Wasm), Linux (X11+Wayland), Pong

## Documents

- [Plan projet](./plan.md) — vision, périmètre, risques
- [Sprint Review](./sprint-review.md) — métriques, livrables, rétrospective
- [Spécifications techniques](./specs.md) — architecture, API, diagrammes

## Fonctionnalités

- **État & géométrie fenêtre** — contraintes de taille, min/max, redimensionnable, minimisé, maximisé, décorations, position extérieure, pre-present notify
- **Énumération des moniteurs** — `availableMonitors()` / `primaryMonitor()` avec données `VideoMode`
- **Plein écran** — `Fullscreen.Borderless` (tous backends) et `Fullscreen.Exclusive` (desktop ; fallback borderless sur Wayland/Web/mobile)
- **Curseur** — 25 formes `CursorIcon`, visibilité, grab (Confined/Locked), warp, hit-testing ; curseurs custom RGBA (`CursorImage` / `CustomCursor`, câblage TODO)
- **Thème & apparence** — `Theme` (Light/Dark), override par fenêtre, événement `ThemeChanged`, `WindowLevel`, transparence, flou, icône de fenêtre
- **Richesse clavier** — `text`, `location` (`KeyLocation`), `scanCode`, `isRepeat` sur `KeyboardInput` ; `ModifiersChanged` ; reset dead-key
- **Événements device** — `DeviceEvent.MouseWheel` ; filtre via `listenDeviceEvents(DeviceEvents.Always/WhenFocused/Never)`
- **IME** — `setImeAllowed`, `setImeCursorArea`, `setImePurpose(ImePurpose)` ; cycle de vie `ImeEvent` complet (Enabled/Preedit/Commit/DeleteSurrounding/Disabled) — API définie, émission TODO
- **Drag & drop** — événements `DragEntered/Moved/Dropped/Left` — API définie, émission TODO
- **Gestes** — Pinch, Pan, Rotation, DoubleTap, TouchpadPressure — API définie, émission TODO
- **Occluded** — événement de changement de visibilité — API définie, émission TODO
- **Fenêtre divers** — attention utilisateur, protection contenu, menu fenêtre, drag/resize-window — API définie, tous no-op (cf. [DEFERRED.md](https://github.com/ygdrasil-io/poc-koreos/blob/master/DEFERRED.md))

## Matrice de capacités par plateforme

| Fonctionnalité | macOS (appkit) | Windows (win32) | Linux X11 | Linux Wayland | Web (JS/Wasm) | Android | iOS (uikit) |
|----------------|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
| Créer fenêtre / titre / taille | réel | réel | réel | réel | réel | réel | réel |
| Énumération moniteurs | réel | réel | réel | réel | synthétique | synthétique | synthétique |
| Plein écran Borderless | réel | réel | réel | réel | réel | réel | réel |
| Plein écran Exclusive | réel | partiel | réel | no-op | no-op | no-op | no-op |
| CursorIcon | réel | réel | réel | no-op* | réel (CSS) | no-op | no-op |
| CursorGrab Confined/Locked | réel | réel | réel | no-op* | réel | no-op | no-op |
| CursorVisible | réel | partiel* | no-op* | no-op | réel | no-op | no-op |
| CursorPosition (warp) | partiel* | réel | réel | no-op | no-op | no-op | no-op |
| systemTheme() | réel | réel | null | null | réel | réel | réel |
| setTheme() par fenêtre | réel | réel | no-op | no-op | no-op | no-op | no-op |
| Événement ThemeChanged | réel | réel | — | — | — | — | — |
| setBlur() | réel | réel | no-op | no-op | no-op | no-op | no-op |
| setWindowIcon() | partiel* | partiel* | réel | no-op | no-op | no-op | no-op |
| Événement ModifiersChanged | réel | réel | TODO | TODO | réel | TODO | TODO |
| IME (setImeAllowed etc.) | TODO | TODO | TODO | TODO | TODO | TODO | TODO |
| Événements DnD | TODO | TODO | TODO | TODO | TODO | TODO | TODO |
| Événements gestes | TODO | TODO | — | — | TODO | — | TODO |
| Événement Occluded | TODO | — | — | — | TODO | — | — |
| Curseurs custom | TODO | TODO | TODO | TODO | TODO | no-op | no-op |

`réel` = implémenté. `partiel*` / `no-op*` = partiel ou no-op documenté, cf. [DEFERRED.md](https://github.com/ygdrasil-io/poc-koreos/blob/master/DEFERRED.md). `TODO` = API définie, câblage backend en attente. `—` = non applicable sur cette plateforme.

## Plateformes

| Plateforme | Backend |
|------------|---------|
| **macOS** | NSWindow avec contentView layer-backed prêt pour Metal |
| **iOS** | UIKit (UIWindow + UIView + CAMetalLayer) |
| **Android** | SurfaceView + Choreographer |
| **Windows** | Win32 (RegisterClassExW + CreateWindowExW) |
| **Linux** | X11 + Wayland, auto-détectés |
| **Web** | canvas HTML (Kotlin/JS + Kotlin/Wasm) |

## Statut

**Publié** — `org.graphiks.kadre:kadre:1.0.0` sur Maven Central.

Éléments résiduels reportés (API définie, câblage backend en attente) : émission IME, émission DnD, émission gestes, émission Occluded, curseurs custom. Liste complète : [DEFERRED.md](https://github.com/ygdrasil-io/poc-koreos/blob/master/DEFERRED.md).
