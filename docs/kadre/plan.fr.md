# Kadre — Plan projet

> Statut : **Canonique**
> Auteur : équipe Kadre
> Dernière mise à jour : 2026-05-31

---

## 1. Contexte

Kadre **1.0.0** livre une API de fenêtrage Kotlin Multiplatform stable inspirée de winit, avec intégration validée avec wgpu4k (triangle rendu), publiée sur Maven Central (`org.graphiks.kadre:kadre:1.0.0`).

La fondation initiale macOS / iOS / Android a été relue et un ensemble d'écarts (mkdocs branding, samples Android dupliqués, README résidus, post-mortem, vidéo démo, etc.) a été identifié et corrigé — voir [sprint-review.md](./sprint-review.md).

**Périmètre 1.0.0** :
1. Solidifier la fondation macOS / iOS / Android et résorber les écarts de la relecture.
2. Étendre Kadre à **6 plateformes** (ajout Web, Windows, Linux) et livrer une démo technique **Pong cross-platform** comme proof point.

---

## 2. Vision

Une lib KMP qui :
- Expose une API callback-driven inspirée de winit.
- Donne accès aux handles natifs bas-niveau directement consommables par un renderer 3D.
- **Ne dépend pas** d'AWT/Swing.
- Tourne sur **toutes les plateformes desktop + mobile + web** : macOS, iOS, Android, Web (JS+WASM), Windows, Linux (X11+Wayland).

---

## 3. Objectifs et non-objectifs

### Objectifs

| Catégorie | Objectif |
|-----------|----------|
| Plateformes | macOS, iOS, Android, **Web (JS+WASM)**, **Windows (Win32)**, **Linux (X11+Wayland)** |
| Démo cross-platform | Pong (1 joueur vs IA simple) tournant sur **les 6 plateformes** avec le même code commonMain |
| Remédiation | Écarts identifiés en relecture corrigés avant la release |
| API publique | Stable, avec variants `RawWindowHandle.Web/Win32/Xlib/Wayland` |
| Distribution | Artefact 1.0.0 publié Maven Central |

### Non-objectifs

- **Compose-on-Kadre** : POC d'évaluation **après** 1.0.0 (2 sem R&D, voir §11)
- **Audio, gamepad, ECS, asset loading** : hors scope ygdrasil (bindings only, cf. décision actée)
- **Pong avec son** : démo visuelle pure, pas d'audio
- **Pong multi-joueur réseau** : 1 joueur vs IA uniquement
- **Accessibilité système** : reportée
- **IME, drag&drop, clipboard avancé** : reportés post-1.0.0

---

## 4. Parties prenantes

| Rôle | Responsabilité |
|------|----------------|
| PM / Tech Lead | Pilotage projet, validation specs |
| Équipe Kadre | Fondation + 3 nouveaux backends + Pong |
| Équipe kextract | Bindings Win32 (déjà supporté), X11 (à confirmer), Wayland (à confirmer) |
| Équipe wgpu4k | Cibles Web déjà disponibles ; consommatrices côté Pong |
| Relecteurs | Validation plan/specs en PR |

---

## 5. Périmètre fonctionnel

### Modules

| Module | Rôle |
|--------|------|
| `kadre-core` | Variants RawWindowHandle (AppKit/UiKit/Android/Web/Win32/Xlib/Wayland) + fondamentaux KMP |
| `kadre-appkit` | Backend macOS (AppKit, FFM) |
| `kadre-uikit` | Backend iOS (UIKit) |
| `kadre-android` | Backend Android (SurfaceView) |
| `kadre` (facade) | Actuals pour toutes les plateformes (macOS, iOS, Android, Web JS+WASM, Windows, Linux) |
| `samples/hello-window` | Sample cross-platform `commonMain` partagé (JVM + iOS + Android) |
| `samples/hello-touch*` | Samples multi-touch |
| `kadre-web-common` | Abstractions partagées Web (DOM events, lifecycle pagehide/show) |
| `kadre-js` | Backend Web Kotlin/JS |
| `kadre-wasm` | Backend Web Kotlin/Wasm |
| `kadre-win32` | Backend Windows via kextract FFM |
| `kadre-x11` | Backend Linux X11 via kextract FFM |
| `kadre-wayland` | Backend Linux Wayland via kextract FFM |
| `samples/pong` | Démo Pong cross-6-platforms |

### Modules hors périmètre

- `koreaudio`, `koreassets`, `koreecs`, `koreinput` : pas dans ygdrasil (cf. décision "bindings only")
- Compose-on-Kadre : POC après 1.0.0

---

## 6. Périmètre de livraison et livrables

### Remédiation de la fondation

**Objectif** : solidifier la fondation macOS / iOS / Android en résorbant les écarts de la relecture.

**Livrables** :
- `mkdocs.yml` rebranded Kadre (site_name, site_description, nav vers `kadre/api/`)
- Samples Android fusionnés dans les samples KMP commonMain (`hello-window-android` et `hello-touch-android` deviennent des entry points application, pas des duplicats HelloApp)
- `AndroidEventLoop.createWindow` retourne une `AndroidWindow` correcte (ne throw plus `UnsupportedOperationException`)
- README racine actualisé (résidus "Clean Architecture / DDD / Compose / Koin" → Kadre)
- Post-mortem M2 : métrique 60fps → 120fps + vidéo démo livrée
- Commentaire "stub" dans `AppKitEventLoop.kt:35` actualisé
- `KadreApplication.eventLoop` refactor (instance scopée, plus de variable statique mutable)
- CI ios-build/android-build sur PR feature branches (pas que master push)
- Test E2E smoke "au moins une frame rendue" sur hello-triangle (anti-régression pour PR #25)

**Définition de "done"** :
- Tous les écarts de la relecture résorbés
- CHANGELOG.md mis à jour
- Site doc déployé reflète le branding Kadre

---

### Backend Web JS+WASM

**Objectif** : Kadre tourne dans le navigateur, valider le contrat raw handle pour WebGPU via wgpu4k.

**Livrables** :
- `kadre-web-common` (commonMain pour les targets web) : abstractions DOM, mapping events
- `kadre-js` (jsMain via Kotlin/JS) : actual backend Canvas + DOM events
- `kadre-wasm` (wasmJsMain via Kotlin/Wasm) : actual backend identique
- Variant `RawWindowHandle.Web(canvasElementId: String)` dans `kadre-core`
- Variant `RawDisplayHandle.Web` dans `kadre-core`
- Sample `hello-triangle-web` : triangle rendu via wgpu4k Web dans un canvas HTML
- Sample `hello-window-web` : sample minimal cross-platform tournant en navigateur
- CI : nouveau job `web-build` (Node + KMP) + publication GitHub Pages des samples web
- Documentation Web : section dans specs.md + tutoriel "Embed Kadre in a webpage"

**Hors scope ici** :
- Pong (livré en dernier)
- Mobile responsive avancé
- PWA / offline

**Définition de "done"** :
- `./gradlew :samples:hello-triangle-web:run` (ou équivalent webpack-serve) ouvre la page, triangle rendu 60fps stable
- Idem pour Wasm
- Mêmes WindowEvent dispatchés que sur Desktop (PointerMoved, MouseInput, KeyboardInput, Resized)
- Lifecycle : `visibilitychange` → suspended/resumed cohérents

---

### Backend Windows

**Objectif** : Kadre tourne sur Windows desktop avec rendu Direct/Metal via wgpu4k.

**Livrables** :
- `kadre-win32` (jvm + kextract FFM) : KadreWindow Win32, ALooper Win32 (CreateWindowExW, message pump GetMessage/DispatchMessage)
- `WndProc` custom pour intercepter WM_PAINT, WM_SIZE, WM_KEYDOWN, WM_MOUSEMOVE, WM_DESTROY, etc.
- Variant `RawWindowHandle.Win32(hwnd: Long, hinstance: Long)` dans `kadre-core` (déjà spec, à activer)
- Variant `RawDisplayHandle.Win32(hinstance: Long)`
- Sample `hello-triangle` tournant sur Windows (recompilation, code commonMain inchangé)
- CI : nouveau job `windows-build` sur `windows-latest`
- Documentation Windows : section dans specs.md

**Définition de "done"** :
- `./gradlew :samples:hello-triangle:run` sur Windows 10/11 → triangle rendu
- DPI scaling correct (PerMonitorV2)
- Clavier/souris/resize dispatchés cohérents avec macOS

---

### Backend Linux X11 + Wayland

**Objectif** : Kadre tourne sur Linux, support des deux compositors (X11 legacy + Wayland moderne).

**Livrables** :
- `kadre-x11` (jvm + kextract FFM Xlib) : XOpenDisplay, XCreateWindow, XSelectInput, event loop XNextEvent
- `kadre-wayland` (jvm + kextract FFM libwayland-client) : wl_display_connect, wl_registry, wl_compositor, xdg_shell pour les fenêtres top-level
- Variants `RawWindowHandle.Xlib(window: Long, display: Long)` et `Wayland(surface: Long, display: Long)`
- Variants `RawDisplayHandle.Xlib` et `Wayland`
- Détection runtime au démarrage : tenter Wayland, fallback X11 (via `XDG_SESSION_TYPE` ou tentative connect)
- Sample `hello-triangle` tournant sur Linux X11 + Linux Wayland (recompilation, code commonMain inchangé)
- CI : nouveau job `linux-build` sur `ubuntu-latest` avec Xvfb pour X11, weston headless pour Wayland (smoke seulement)
- Documentation Linux : section dans specs.md

**Définition de "done"** :
- Sample tourne sur Ubuntu 24.04 (Wayland) et Debian 12 (X11)
- Détection automatique fonctionnelle, pas de configuration manuelle requise par l'utilisateur
- Clavier/souris dispatchés cohérents avec macOS/Windows

---

### Pong cross-6-platforms + Release 1.0.0

**Objectif** : démo technique pure montrant le même code Kotlin tournant sur 6 plateformes.

**Livrables** :
- `samples/pong` : module KMP avec cibles jvm, androidTarget, iosX64/Arm64/SimArm64, jsBrowser, wasmJsBrowser, jvm-windows, jvm-linux (cibles toutes via les facades existantes)
- Logique Pong en `commonMain` :
  - `PongGame : ApplicationHandler`
  - Raquette droite contrôlée par `WindowEvent.KeyboardInput` (Desktop : flèches haut/bas) OU `WindowEvent.Touch` (mobile/web touch : zone droite de l'écran tap to move)
  - Raquette gauche = IA simple (suit la balle avec un coefficient de lag pour difficulté)
  - Balle : physique 2D simple (rebonds raquettes/murs haut/bas)
  - Score affiché en haut (pas d'audio)
  - Reset après score
- Rendu via wgpu4k : 5 quads colorés (2 raquettes + 1 balle + 2 chiffres pour le score via primitives ou bitmap font hardcodée)
- Frame timing : `requestRedraw` à chaque `aboutToWait`, 60fps cible
- Pause sur `suspended` (mobile/web background)
- Build tasks par cible
- Vidéo de démo enregistrée sur les 6 plateformes
- Documentation : section "Multi-platform game loop pattern" dans la doc
- CHANGELOG 1.0.0 + tag git + release Maven Central

**Définition de "done"** :
- Code source du sample Pong **strictement identique** (le même `PongGame.kt`) tourne sur les 6 plateformes
- Vidéos de démo enregistrées et attachées au tag GitHub release
- Lib 1.0.0 publiée Maven Central avec tous les modules (`kadre-core`, `kadre-appkit`, `kadre-uikit`, `kadre-android`, `kadre-js`, `kadre-wasm`, `kadre-win32`, `kadre-x11`, `kadre-wayland`, + facade `kadre`)
- CI verte sur 6 OS/runners

---

## 7. Critères de succès

| Domaine | Critère mesurable |
|---------|--------------------|
| Fondation | mkdocs branded Kadre, samples Android partagés, créateWindow fonctionnel sur Android. |
| Web | Triangle rendu 60fps stable dans Chrome/Firefox/Safari sur JS et WASM. |
| Windows | Triangle rendu sur Windows 10+11. Input clavier/souris cohérent. |
| Linux | Triangle rendu Ubuntu (Wayland) + Debian (X11) avec détection auto. |
| Pong | Pong jouable identiquement sur 6 plateformes. 1.0.0 release Maven Central. |

---

## 8. Risques et mitigations

| Risque | Probabilité | Impact | Mitigation |
|--------|-------------|--------|------------|
| **Kotlin/Wasm encore en alpha** | Moyenne | Bugs runtime non-anticipés | Cibler la version Kotlin la plus stable au démarrage du backend Web. JS d'abord comme MVP, WASM ensuite. |
| **kextract X11/Wayland non testé** | Forte | Backend Linux retardé | Test smoke kextract sur Xlib en amont. Coordination équipe kextract en amont. |
| **Wayland protocol versions** (xdg_shell, xdg_decoration) | Moyenne | Compat compositors | Cibler xdg_shell v3 + zxdg_decoration_v1 (minimum Mutter 3.32+, KWin 5.20+). Fallback : pas de décorations custom. |
| **Détection X11/Wayland auto** non fiable | Faible | UX dégradée Linux | Variable d'environnement `KADRE_LINUX_BACKEND` pour override manuel. |
| **wgpu4k Web ne supporte pas tous les formats** (compute shaders, etc.) | Faible | Limites samples web | Pong = render-only, pas concerné. À monitorer pour usages futurs. |
| **Pong cross-platform : input divergences subtiles** | Forte | Bugs comportement plateforme-spécifique | Tests d'intégration manuels par plateforme. Documenter les divergences acceptables (e.g. tap vs flèche). |
| **CI Linux Wayland (weston headless)** instable | Moyenne | Tests CI flaky | Smoke test uniquement (build + 1 frame). Pas de test runtime intensif. |
| **DPI scaling Windows complexe** (PerMonitorV2 + multi-monitors) | Moyenne | Bugs visuels HiDPI | Test sur multi-screen mixed scale dès l'arrivée du backend Windows. |
| **Stabilité de l'API** sous les nouveaux variants | Faible | Migration users | Aucun changement de signature des interfaces existantes. Seulement ajout de variants RawWindowHandle. |

---

## 9. Dépendances externes

| Dépendance | Version cible | Statut |
|------------|---------------|--------|
| kextract Win32 | À confirmer | Probablement supporté (FFM Win32 = chemin standard) |
| kextract X11 | À confirmer | À investiguer en amont |
| kextract Wayland | À confirmer | À investiguer en amont |
| **wgpu4k Web JS** | À aligner | **Disponible** (confirmé) |
| **wgpu4k Web WASM** | À aligner | **Disponible** (confirmé) |
| Kotlin | 2.3.21+ (alignement avec stable Kotlin/Wasm) | Configuré |
| JDK | 25 (LTS) | Configuré |
| Node.js | LTS (pour cibles Web) | À ajouter à la CI |

---

## 10. Timeline indicative

| Phase | Durée |
|-------|-------|
| Remédiation de la fondation | 2 sem |
| Web JS+WASM | 4 sem |
| Windows | 2 sem |
| Linux X11+Wayland | 3 sem |
| Pong + release 1.0.0 | 2 sem |
| (Post-1.0.0) POC Compose-on-Kadre | 2 sem |

Total jusqu'à 1.0.0 : **~13 semaines** d'effort planifié (livré en ~24h d'effort effectif en pratique).

---

## 11. Décisions actées (résumé)

| # | Décision |
|---|----------|
| D1 | **6 plateformes** cibles : macOS, iOS, Android, Web (JS+WASM), Windows, Linux (X11+Wayland) |
| D2 | **Ordre nouvelles plateformes** : Web → Windows → Linux |
| D3 | **Démo Pong** codée une seule fois en commonMain, livrée à la fin sur les 6 plateformes |
| D4 | **Format Pong** : 1 joueur vs IA simple, pas d'audio |
| D5 | **Rémédiation** : résorber les écarts de la relecture avant les nouvelles plateformes |
| D6 | **wgpu4k Web** : disponible, pas de chantier amont |
| D7 | **Format planification** : backlog complet + doc plan/specs |
| D8 | **Compose-on-Kadre** : POC d'évaluation 2 sem **après** 1.0.0 |
| D9 | **Modèle ygdrasil** : bindings only (pas de koreaudio, koreassets, koreecs) |
| D10 | **Communauté** : Discord wgpu4k (rebrand futur), pas de Discord ygdrasil dédié |
| D11 | **Stratégie hybride JS/WASM** : JS first (1 sem MVP), WASM ensuite (1.5 sem) avec couche commune `kadre-web-common` |
| D12 | **Détection Linux X11/Wayland** : auto-détection runtime + override par variable d'env `KADRE_LINUX_BACKEND` |
| D13 | **JDK cible = 25 (LTS)**. Trade-off conscient adoption vs modernité — voir §13. |

---

## 13. Décision JDK 25 — justification

Un reviewer a soulevé la question (legitimement) du choix JDK 25 vs JDK 22/21 pour élargir l'adoption (FFM est stable depuis JDK 22, JDK 21 est LTS). La décision **JDK 25** est maintenue, avec les arguments suivants :

| Critère | JDK 21 LTS | JDK 22 | **JDK 25 LTS** |
|---|---|---|---|
| FFM stable | preview | ✅ stable | ✅ stable |
| Statut LTS | LTS (jusqu'à 2031) | non-LTS | **LTS (jusqu'à 2033)** |
| Pattern matching switch | preview | ✅ stable | ✅ stable + amélioré |
| Virtual threads | ✅ stable | ✅ stable | ✅ stable + tuned |
| `Linker.upcallStub` API | preview | ✅ stable | ✅ stable + perf améliorée |
| Adoption Q3 2026 | très large | déclinant | en croissance |

**Pourquoi JDK 25** :

1. **LTS du moment** — JDK 25 est la LTS la plus récente, supportée jusqu'à 2033 (Oracle/Eclipse Temurin). JDK 21 sort de "premium support" avant 2030. Cibler la LTS la plus fraîche garantit que les utilisateurs Kadre auront une version supportée longtemps.

2. **Aucune dépendance Kadre ne nécessite < JDK 25** — Pas de partenaire imposant une version inférieure (à confirmer si un consommateur le demande).

3. **L'écosystème ygdrasil est récent** — Kadre cible des utilisateurs qui construisent des projets neufs (jeux/outils 3D/Pong-like), pas des migrations legacy. Ces utilisateurs sont en général sur la JDK la plus récente.

4. **kextract génère du code FFM moderne** — les API `Linker`, `Arena.ofShared`, `MemorySegment.reinterpret` ont été polies post-JDK 22. Travailler sur la LTS la plus récente évite des workarounds.

**Conditions de révision** :

- Si un consommateur stratégique de Kadre (ex : intégration upstream Compose) impose JDK 21 → ré-évaluer.
- Si Kotlin/JVM perd la cible bytecode JDK 25 → fallback JDK 22 (compromise FFM stable + adoption plus large).
- Si > 30% des bugs reportés mentionnent "JDK trop récent" → fallback JDK 22 (mesure d'adoption réelle).

**Fallback prêt** : le projet utilise déjà des `toolchain` Gradle ; descendre la cible de JDK 25 à JDK 22 est un changement minime (1 ligne dans le convention plugin `kmp-library`). À documenter dans `release-process.md`.

---

## 12. Annexes

### Glossaire

| Terme | Définition |
|-------|------------|
| **Wayland** | Protocole de compositor Linux moderne, remplaçant de X11. Stack : libwayland-client + xdg_shell. |
| **X11** | Protocole historique Linux/Unix de fenêtrage. Xlib (C) ou xcb (plus moderne). |
| **WebGPU** | API GPU moderne pour navigateurs, exposée via JS/WASM. Utilisée par wgpu4k Web. |
| **Kotlin/Wasm** | Cible de compilation Kotlin vers WebAssembly. Plus performante que Kotlin/JS pour code GPU/compute. |
| **xdg_shell** | Protocole Wayland standard pour fenêtres top-level (decorations, resize, fullscreen). |
| **PerMonitorV2** | Mode de DPI awareness Windows 10+ : chaque monitor a sa propre scale, géré par l'app. |

### Mapping winit → Kadre

| winit (Rust) | Kadre |
|--------------|-------|
| `RawWindowHandle::Web(WebHandle)` | `RawWindowHandle.Web(canvasElementId: String)` |
| `RawWindowHandle::Win32(Win32Handle)` | `RawWindowHandle.Win32(hwnd: Long, hinstance: Long)` |
| `RawWindowHandle::Xlib(XlibHandle)` | `RawWindowHandle.Xlib(window: Long, display: Long)` |
| `RawWindowHandle::Wayland(WaylandHandle)` | `RawWindowHandle.Wayland(surface: Long, display: Long)` |

### Documents associés

- [Spécifications techniques](./specs.md)
- [Sprint review](./sprint-review.md)
