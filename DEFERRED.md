# Kadre — Backlog résiduel (éléments reportés)

> Le backlog de remédiation **R0 → R5** (parité winit) a été livré (cf. historique git, PRs #167–#184).
> Ce fichier recense ce qui a été **volontairement mis de côté** au fil de l'implémentation.
> Tout est documenté par un commentaire/TODO dans le code — rien n'a été omis silencieusement.

Dernière mise à jour : 2026-06-01

---

## 1. Hors périmètre (par conception — plan / specs §7)

- **Manette / gamepad** : hors périmètre (winit lui-même délègue à `gilrs`).
- **Multi-fenêtre sur Web** : non visé.
- **Vérification runtime réelle** (Windows / Linux / navigateur / iOS) : non faite. Tout est compile-vérifié + ABI (`checkLegacyAbi`) + tests unitaires Android + CI. Le comportement à l'exécution reste à valider manuellement par plateforme.

## 2. API winit jamais reprise (aucun ticket)

- `WindowButtons` — `setEnabledButtons` / `enabledButtons`: API commune présente; AppKit câblé localement via `NSWindowStyleMaskClosable` / `NSWindowStyleMaskMiniaturizable` et `standardWindowButton(NSWindowZoomButton).setEnabled`; Win32 câblé localement via `WS_MINIMIZEBOX`, `WS_MAXIMIZEBOX` et `EnableMenuItem(SC_CLOSE)`; X11/Wayland/Web/mobile restent `all()`/unsupported documenté à compléter selon les limites natives.
- `surfaceResizeIncrements` (redimensionnement par incréments).
- `safeArea` / `Insets` (avait été envisagé pour R5, abandonné).
- `ActivationTokenDone` (event) — listé à l'analyse d'écart, non implémenté (seul `Occluded` ajouté).
- `ownedDisplayHandle` (sur `ActiveEventLoop`).

## 3. Décisions d'architecture délibérément non faites

- **Modèle pointeur** : `MouseInput` + `Touch` conservés au lieu du modèle unifié winit `PointerButton` / `PointerKind` → **pas de support stylet/tablette** (`TabletTool`).
- **Modèle clavier** : `Key` reste un enum **fermé** (~70 touches). winit a un modèle ouvert (`Character`/`Named`/`Dead`) + `PhysicalKey`/`KeyCode` (200+). R4 a ajouté `text`/`scanCode`/`location`, mais la divergence de modèle subsiste.

## 4. Events définis mais non émis (émission = TODO par backend)

- **IME** (Enabled/Preedit/Commit/DeleteSurrounding/Disabled), **DnD** (DragEntered/Moved/Dropped/Left), **Occluded** : API posée, aucun backend ne les émet encore.
- **Gestes hors Apple** (Pinch/Pan/Rotation/DoubleTap/TouchpadPressure) : AppKit émet les gestes natifs, UIKit les émet après opt-in explicite ; Win32/X11/Wayland/Web/Android restent à câbler ou non applicables selon la plateforme.
- **ModifiersChanged** : émis sur AppKit / Win32 / Web ; **non émis** sur X11 / Wayland / Android / iOS.

## 5. Méthodes définies mais no-op (selon la plateforme)

- **Curseurs custom** (`createCustomCursor` / `setCustomCursor`) : no-op partout (défaut interface).
- **Divers fenêtre** : `requestUserAttention` et `setContentProtected` retournent désormais `WindowRequestResult`: AppKit câble attention Dock et content protection sur la main queue, Win32 câble attention via `FlashWindowEx` et content protection via `SetWindowDisplayAffinity`, et les autres backends retournent `Failure(RequestError.Unsupported(...))` par défaut. `memoryWarning` reste no-op par défaut. `showWindowMenu`, `dragWindow` et `dragResizeWindow` retournent désormais `WindowRequestResult.Failure(RequestError.Unsupported(...))` par défaut. AppKit câble `dragWindow` via le `NSEvent` courant, en marshalisant les appels non-main-thread sur la main queue comme winit, et retourne `RequestError.Ignored` quand aucun événement courant n'est disponible. Win32 câble le menu système et enfile les drags move/resize cross-thread vers le thread fenêtre comme winit. X11 câble les drags move/resize via `_NET_WM_MOVERESIZE`. AppKit et X11 retournent success no-op pour `showWindowMenu` comme winit. Wayland câble `showWindowMenu` et les drags move/resize via `xdg_toplevel.show_window_menu/move/resize`. Le démarrage natif reste fire-and-forget. `dragResizeWindow` AppKit reste unsupported comme winit.
- **iOS / Android** : la quasi-totalité de l'état/géométrie de fenêtre = no-op (attendu sur mobile).

## 6. Implémentations natives partielles (TODO concrets dans le code)

| Backend | À compléter |
|---|---|
| **Win32** | enforcement min/max via `WM_GETMINMAXINFO` ; plein écran **exclusif** (`ChangeDisplaySettingsExW`, fallback borderless) ; `ShowCursor` non rééquilibré |
| **X11** | `setResizable` → `XSetWMNormalHints` ; **texte clavier** → `XLookupString` (`text = null`) ; `ScaleFactorChanged` dynamique (RRNotify) |
| **Wayland** | **curseur** → `libwayland-cursor` (no-op) ; **grab** → `CursorGrabMode.None` success no-op comme winit, `Confined`/`Locked` restent à câbler via pointer-constraints ; **texte clavier** → `xkb_state_key_get_utf8` (`text = null`) ; changement dynamique de décoration SSD/CSD ; plein écran exclusif N/A |
| **AppKit** | `outerPosition` en coords Cocoa bas-gauche non converties ; `CGWarpMouseCursorPosition` en scalaires (marche x64/arm64, non conforme spec FFM) |
| **Web** | `setCustomCursor` / window level / transparent / blur / cursor-warp = no-op |

## 7. Findings mineurs de revue laissés (non bloquants)

- Win32 `readWString` s'arrête sur l'espace au lieu du `\0`.
- Win32 `EnumDisplayMonitors` : callback à état global non thread-safe (OK car appel synchrone).
- Web : double lecture `devicePixelRatio` à l'init ; cast `asDynamic() as Double`.

## 8. Bug pré-existant flaggé séparément (hors R0–R5)

- **X11 `X11DrawMapper`** : offsets de lecture `ClientMessage` (`data.l[0] @ 64`) incohérents avec le layout canonique LP64 (`@ 56`) → `WindowEvent.CloseRequested` X11 possiblement non émis. Le **writer** (`sendNetWmState`, R1) a été corrigé en offsets canoniques ; le **reader** reste à vérifier (un chip de tâche séparé a été créé pendant R1).
