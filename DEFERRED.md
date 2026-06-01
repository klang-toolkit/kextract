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

- `WindowButtons` — `setEnabledButtons` / `enabledButtons`.
- `surfaceResizeIncrements` (redimensionnement par incréments).
- `safeArea` / `Insets` (avait été envisagé pour R5, abandonné).
- `ActivationTokenDone` (event) — listé à l'analyse d'écart, non implémenté (seul `Occluded` ajouté).
- `ownedDisplayHandle` (sur `ActiveEventLoop`).

## 3. Décisions d'architecture délibérément non faites

- **Modèle pointeur** : `MouseInput` + `Touch` conservés au lieu du modèle unifié winit `PointerButton` / `PointerKind` → **pas de support stylet/tablette** (`TabletTool`).
- **Modèle clavier** : `Key` reste un enum **fermé** (~70 touches). winit a un modèle ouvert (`Character`/`Named`/`Dead`) + `PhysicalKey`/`KeyCode` (200+). R4 a ajouté `text`/`scanCode`/`location`, mais la divergence de modèle subsiste.

## 4. Events définis mais non émis (émission = TODO par backend)

- **IME** (Enabled/Preedit/Commit/DeleteSurrounding/Disabled), **DnD** (DragEntered/Moved/Dropped/Left), **gestes** (Pinch/Pan/Rotation/DoubleTap/TouchpadPressure), **Occluded** : API posée, aucun backend ne les émet encore.
- **ModifiersChanged** : émis sur AppKit / Win32 / Web ; **non émis** sur X11 / Wayland / Android / iOS.

## 5. Méthodes définies mais no-op (selon la plateforme)

- **Curseurs custom** (`createCustomCursor` / `setCustomCursor`) : no-op partout (défaut interface).
- **Divers fenêtre** (`requestUserAttention`, `setContentProtected`, `showWindowMenu`, `dragWindow`, `dragResizeWindow`, `memoryWarning`) : no-op partout.
- **iOS / Android** : la quasi-totalité de l'état/géométrie de fenêtre = no-op (attendu sur mobile).

## 6. Implémentations natives partielles (TODO concrets dans le code)

| Backend | À compléter |
|---|---|
| **Win32** | enforcement min/max via `WM_GETMINMAXINFO` ; plein écran **exclusif** (`ChangeDisplaySettingsExW`, fallback borderless) ; icône (`WM_SETICON`, stub) ; `ShowCursor` non rééquilibré |
| **X11** | `setResizable` → `XSetWMNormalHints` ; `setCursorVisible` → `XCreatePixmapCursor` ; **texte clavier** → `XLookupString` (`text = null`) ; `ScaleFactorChanged` dynamique (RRNotify) ; thème (`null`, pas de standard) |
| **Wayland** | **curseur** → `libwayland-cursor` (no-op) ; **grab** → pointer-constraints (no-op) ; **texte clavier** → `xkb_state_key_get_utf8` (`text = null`) ; changement dynamique de décoration SSD/CSD ; plein écran exclusif N/A |
| **AppKit** | `setWindowIcon` (upload `NSBitmapImageRep`, stub) ; `outerPosition` en coords Cocoa bas-gauche non converties ; `CGWarpMouseCursorPosition` en scalaires (marche x64/arm64, non conforme spec FFM) |
| **Web** | `setCustomCursor` / window level / transparent / blur / cursor-warp = no-op |

## 7. Findings mineurs de revue laissés (non bloquants)

- Win32 `readWString` s'arrête sur l'espace au lieu du `\0`.
- Win32 `EnumDisplayMonitors` : callback à état global non thread-safe (OK car appel synchrone).
- Web : double lecture `devicePixelRatio` à l'init ; cast `asDynamic() as Double`.

## 8. Bug pré-existant flaggé séparément (hors R0–R5)

- **X11 `X11DrawMapper`** : offsets de lecture `ClientMessage` (`data.l[0] @ 64`) incohérents avec le layout canonique LP64 (`@ 56`) → `WindowEvent.CloseRequested` X11 possiblement non émis. Le **writer** (`sendNetWmState`, R1) a été corrigé en offsets canoniques ; le **reader** reste à vérifier (un chip de tâche séparé a été créé pendant R1).
