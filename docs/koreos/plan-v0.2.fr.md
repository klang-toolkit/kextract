# Koreos — Plan projet v0.2

> Statut : **Draft pour relecture**
> Auteur : équipe Koreos
> Dernière mise à jour : 2026-05-29
> Document précédent : [plan-v0.1](./plan.md)

---

## 1. Contexte

Koreos **v0.1.0** est livré : 3 plateformes (macOS, iOS, Android), API stable inspirée de winit, intégration validée avec wgpu4k (triangle rendu), artefact publié Maven Central (`io.ygdrasil.koreos:0.1.0`).

La sprint review a identifié **9 écarts mineurs** (mkdocs branding, samples Android dupliqués, README résidus, post-mortem, vidéo démo M2, etc.) — voir [sprint-review-v0.1.md](./sprint-review-v0.1.md) ou la conversation de référence.

**Objectif v0.2** :
1. **v0.1.1** — corriger les 9 écarts de la sprint review.
2. **v0.2.0** — étendre Koreos à **6 plateformes** (ajout Web, Windows, Linux) et livrer une démo technique **Pong cross-platform** comme proof point.

---

## 2. Vision (inchangée, étendue)

Une lib KMP qui :
- Expose une API callback-driven inspirée de winit.
- Donne accès aux handles natifs bas-niveau directement consommables par un renderer 3D.
- **Ne dépend pas** d'AWT/Swing.
- Tourne sur **toutes les plateformes desktop + mobile + web** : macOS, iOS, Android, Web (JS+WASM), Windows, Linux (X11+Wayland).

---

## 3. Objectifs et non-objectifs

### Objectifs v0.2

| Catégorie | Objectif |
|-----------|----------|
| Plateformes (V0.2) | macOS, iOS, Android, **Web (JS+WASM)**, **Windows (Win32)**, **Linux (X11+Wayland)** |
| Démo cross-platform | Pong (1 joueur vs IA simple) tournant sur **les 6 plateformes** avec le même code commonMain |
| Remédiation | 9 écarts identifiés en sprint review corrigés en v0.1.1 |
| API publique | Stable, rétro-compatible v0.1.x (ajout de variants `RawWindowHandle.Web/Win32/Xlib/Wayland`) |
| Distribution | Artefact v0.2.0 publié Maven Central |

### Non-objectifs v0.2

- **Compose-on-Koreos** : POC d'évaluation **après** v0.2.0 (2 sem R&D, voir §11)
- **Audio, gamepad, ECS, asset loading** : hors scope ygdrasil (bindings only, cf. décision actée)
- **Pong avec son** : démo visuelle pure, pas d'audio
- **Pong multi-joueur réseau** : 1 joueur vs IA uniquement
- **Accessibilité système** : reportée
- **IME, drag&drop, clipboard avancé** : reportés post-v0.2

---

## 4. Parties prenantes

| Rôle | Responsabilité |
|------|----------------|
| PM / Tech Lead | Pilotage projet, validation specs |
| Équipe Koreos | Implémentation rémédiation + 3 nouveaux backends + Pong |
| Équipe kextract | Bindings Win32 (déjà supporté), X11 (à confirmer), Wayland (à confirmer) |
| Équipe wgpu4k | Cibles Web déjà disponibles ; consommatrices côté Pong |
| Relecteurs | Validation plan/specs en PR |

---

## 5. Périmètre fonctionnel v0.2

### Modules livrés

| Module | État v0.1 | État cible v0.2 |
|--------|-----------|------------------|
| `koreos-core` | Livré | Ajout variants RawWindowHandle (Web/Win32/Xlib/Wayland), inchangé sur fondamentaux |
| `koreos-appkit` | Livré | Inchangé (rémédiation mineure si applicable) |
| `koreos-uikit` | Livré | Inchangé |
| `koreos-android` | Livré | Rémédiation : `AndroidEventLoop.createWindow` ne doit plus throw |
| `koreos` (facade) | Livré | Ajout actuals pour Web (JS+WASM), Windows, Linux |
| `samples/hello-window` | Livré | Refactor : code partagé `commonMain` réellement utilisé sur Android (fusion `hello-window-android`) |
| `samples/hello-touch*` | Livré | Idem refactor |
| **`koreos-web-common`** | — | **Nouveau** : abstractions partagées Web (DOM events, lifecycle pagehide/show) |
| **`koreos-js`** | — | **Nouveau** : backend Web Kotlin/JS |
| **`koreos-wasm`** | — | **Nouveau** : backend Web Kotlin/Wasm |
| **`koreos-win32`** | — | **Nouveau** : backend Windows via kextract FFM |
| **`koreos-x11`** | — | **Nouveau** : backend Linux X11 via kextract FFM |
| **`koreos-wayland`** | — | **Nouveau** : backend Linux Wayland via kextract FFM |
| **`samples/pong`** | — | **Nouveau** : démo Pong cross-6-platforms |

### Modules hors périmètre v0.2

- `koreaudio`, `koreassets`, `koreecs`, `koreinput` : pas dans ygdrasil (cf. décision "bindings only")
- Compose-on-Koreos : POC après v0.2.0

---

## 6. Jalons et livrables

### Sprint 0 — Rémédiation v0.1.1 (2 semaines)

**Objectif** : nettoyer les écarts identifiés en sprint review v0.1, livrer une v0.1.1 propre pour les utilisateurs externes.

**Livrables** :
- `mkdocs.yml` rebranded Koreos (site_name, site_description, nav vers `koreos/api/`)
- Samples Android fusionnés dans les samples KMP commonMain (`hello-window-android` et `hello-touch-android` deviennent des entry points application, pas des duplicats HelloApp)
- `AndroidEventLoop.createWindow` retourne une `AndroidWindow` correcte (ne throw plus `UnsupportedOperationException`)
- README racine actualisé (résidus "Clean Architecture / DDD / Compose / Koin" → Koreos)
- Post-mortem M2 : métrique 60fps → 120fps + vidéo démo livrée
- Commentaire "stub" dans `AppKitEventLoop.kt:35` actualisé
- `KoreosApplication.eventLoop` refactor (instance scopée, plus de variable statique mutable)
- CI ios-build/android-build sur PR feature branches (pas que master push)
- Test E2E smoke "au moins une frame rendue" sur hello-triangle (anti-régression PR #25-bis)

**Définition de "done"** :
- Tag `v0.1.1` créé sur master
- Artefact v0.1.1 publié Maven Central
- CHANGELOG.md mis à jour
- Site doc déployé reflète le branding Koreos

---

### Sprint 1-2 — Backend Web JS+WASM (1 mois)

**Objectif** : Koreos tourne dans le navigateur, valider le contrat raw handle pour WebGPU via wgpu4k.

**Livrables** :
- `koreos-web-common` (commonMain pour les targets web) : abstractions DOM, mapping events
- `koreos-js` (jsMain via Kotlin/JS) : actual backend Canvas + DOM events
- `koreos-wasm` (wasmJsMain via Kotlin/Wasm) : actual backend identique
- Variant `RawWindowHandle.Web(canvasElementId: String)` dans `koreos-core`
- Variant `RawDisplayHandle.Web` dans `koreos-core`
- Sample `hello-triangle-web` : triangle rendu via wgpu4k Web dans un canvas HTML
- Sample `hello-window-web` : sample minimal cross-platform tournant en navigateur
- CI : nouveau job `web-build` (Node + KMP) + publication GitHub Pages des samples web
- Documentation Web : section dans specs.md + tutoriel "Embed Koreos in a webpage"

**Hors scope sprint 1-2** :
- Pong (déféré sprint 5)
- Mobile responsive avancé
- PWA / offline

**Définition de "done"** :
- `./gradlew :samples:hello-triangle-web:run` (ou équivalent webpack-serve) ouvre la page, triangle rendu 60fps stable
- Idem pour Wasm
- Mêmes WindowEvent dispatchés que sur Desktop (PointerMoved, MouseInput, KeyboardInput, Resized)
- Lifecycle : `visibilitychange` → suspended/resumed cohérents

---

### Sprint 3 — Backend Windows (2 semaines)

**Objectif** : Koreos tourne sur Windows desktop avec rendu Direct/Metal via wgpu4k.

**Livrables** :
- `koreos-win32` (jvm + kextract FFM) : KoreosWindow Win32, ALooper Win32 (CreateWindowExW, message pump GetMessage/DispatchMessage)
- `WndProc` custom pour intercepter WM_PAINT, WM_SIZE, WM_KEYDOWN, WM_MOUSEMOVE, WM_DESTROY, etc.
- Variant `RawWindowHandle.Win32(hwnd: Long, hinstance: Long)` dans `koreos-core` (déjà spec, à activer)
- Variant `RawDisplayHandle.Win32(hinstance: Long)`
- Sample `hello-triangle` tournant sur Windows (recompilation, code commonMain inchangé)
- CI : nouveau job `windows-build` sur `windows-latest`
- Documentation Windows : section dans specs.md

**Définition de "done"** :
- `./gradlew :samples:hello-triangle:run` sur Windows 10/11 → triangle rendu
- DPI scaling correct (PerMonitorV2)
- Clavier/souris/resize dispatchés cohérents avec macOS

---

### Sprint 4 — Backend Linux X11 + Wayland (3 semaines)

**Objectif** : Koreos tourne sur Linux, support des deux compositors (X11 legacy + Wayland moderne).

**Livrables** :
- `koreos-x11` (jvm + kextract FFM Xlib) : XOpenDisplay, XCreateWindow, XSelectInput, event loop XNextEvent
- `koreos-wayland` (jvm + kextract FFM libwayland-client) : wl_display_connect, wl_registry, wl_compositor, xdg_shell pour les fenêtres top-level
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

### Sprint 5 — Pong cross-6-platforms + Release v0.2.0 (1-2 semaines)

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
- CHANGELOG v0.2.0 + tag git + release Maven Central

**Définition de "done"** :
- Code source du sample Pong **strictement identique** (le même `PongGame.kt`) tourne sur les 6 plateformes
- Vidéos de démo enregistrées et attachées au tag GitHub release
- Lib v0.2.0 publiée Maven Central avec les **9 modules** (`koreos-core`, `koreos-appkit`, `koreos-uikit`, `koreos-android`, `koreos-js`, `koreos-wasm`, `koreos-win32`, `koreos-x11`, `koreos-wayland`, + facade `koreos`)
- CI verte sur 6 OS/runners

---

## 7. Critères de succès

| Jalon | Critère mesurable |
|-------|--------------------|
| v0.1.1 | Tag créé, mkdocs branded Koreos, samples Android partagés, créateWindow fonctionnel sur Android. |
| Sprint 1-2 (Web) | Triangle rendu 60fps stable dans Chrome/Firefox/Safari sur JS et WASM. |
| Sprint 3 (Windows) | Triangle rendu sur Windows 10+11. Input clavier/souris cohérent. |
| Sprint 4 (Linux) | Triangle rendu Ubuntu (Wayland) + Debian (X11) avec détection auto. |
| Sprint 5 (Pong) | Pong jouable identiquement sur 6 plateformes. v0.2.0 release Maven Central. |

---

## 8. Risques et mitigations

| Risque | Probabilité | Impact | Mitigation |
|--------|-------------|--------|------------|
| **Kotlin/Wasm encore en alpha** | Moyenne | Bugs runtime non-anticipés | Cibler la version Kotlin la plus stable au démarrage Sprint 1. JS d'abord comme MVP, WASM ensuite. |
| **kextract X11/Wayland non testé** | Forte | Sprint 4 retardé | Test smoke kextract sur Xlib dès Sprint 0. Coordination équipe kextract en amont. |
| **Wayland protocol versions** (xdg_shell, xdg_decoration) | Moyenne | Compat compositors | Cibler xdg_shell v3 + zxdg_decoration_v1 (minimum Mutter 3.32+, KWin 5.20+). Fallback : pas de décorations custom. |
| **Détection X11/Wayland auto** non fiable | Faible | UX dégradée Linux | Variable d'environnement `KOREOS_LINUX_BACKEND` pour override manuel. |
| **wgpu4k Web ne supporte pas tous les formats** (compute shaders, etc.) | Faible | Limites samples web | Pong = render-only, pas concerné. À monitorer pour usages futurs. |
| **Pong cross-platform : input divergences subtiles** | Forte | Bugs comportement plateforme-spécifique | Tests d'intégration manuels par plateforme. Documenter les divergences acceptables (e.g. tap vs flèche). |
| **CI Linux Wayland (weston headless)** instable | Moyenne | Tests CI flaky | Smoke test uniquement (build + 1 frame). Pas de test runtime intensif. |
| **DPI scaling Windows complexe** (PerMonitorV2 + multi-monitors) | Moyenne | Bugs visuels HiDPI | Test sur multi-screen mixed scale dès la fin du Sprint 3. |
| **Rétro-compat v0.1.x cassée** par changements API | Faible | Migration users | Aucun changement de signature des interfaces existantes. Seulement ajout de variants RawWindowHandle. |

---

## 9. Dépendances externes

| Dépendance | Version cible | Statut |
|------------|---------------|--------|
| kextract Win32 | À confirmer | Probablement supporté (FFM Win32 = chemin standard) |
| kextract X11 | À confirmer | À investiguer en Sprint 0 |
| kextract Wayland | À confirmer | À investiguer en Sprint 0 |
| **wgpu4k Web JS** | À aligner | **Disponible** (confirmé) |
| **wgpu4k Web WASM** | À aligner | **Disponible** (confirmé) |
| Kotlin | 2.3.21+ (alignement avec stable Kotlin/Wasm) | Configuré |
| JDK | 25 (LTS) | Configuré |
| Node.js | LTS (pour cibles Web) | À ajouter à la CI |

---

## 10. Timeline indicative

| Sprint | Durée | Échéance cible (depuis 2026-05-29) |
|--------|-------|----------------|
| Sprint 0 — v0.1.1 rémédiation | 2 sem | T0 + 2 sem (mi-juin) |
| Sprint 1-2 — Web JS+WASM | 4 sem | T0 + 6 sem (mi-juillet) |
| Sprint 3 — Windows | 2 sem | T0 + 8 sem (fin juillet) |
| Sprint 4 — Linux X11+Wayland | 3 sem | T0 + 11 sem (mi-août) |
| Sprint 5 — Pong + v0.2.0 release | 2 sem | T0 + 13 sem (fin août) |
| (Hors v0.2) POC Compose-on-Koreos | 2 sem | T0 + 15 sem (septembre) |

Total v0.2.0 : **~13 semaines** depuis T0. Plus court que le plan initial (M1→M3 = 14 sem en estimation, ~24h en réel).

---

## 11. Décisions actées (résumé)

| # | Décision |
|---|----------|
| D1 | **6 plateformes** cibles : macOS, iOS, Android, Web (JS+WASM), Windows, Linux (X11+Wayland) |
| D2 | **Ordre nouvelles plateformes** : Web → Windows → Linux |
| D3 | **Démo Pong** codée une seule fois en commonMain, livrée à la fin sur les 6 plateformes |
| D4 | **Format Pong** : 1 joueur vs IA simple, pas d'audio |
| D5 | **Rémédiation** : sprint dédié v0.1.1 avant les nouvelles plateformes |
| D6 | **wgpu4k Web** : disponible, pas de chantier amont |
| D7 | **Format planification** : backlog Linear complet + doc plan/specs MR (pattern M1-M3) |
| D8 | **Compose-on-Koreos** : POC d'évaluation 2 sem **après** v0.2.0 |
| D9 | **Modèle ygdrasil** : bindings only (pas de koreaudio, koreassets, koreecs) |
| D10 | **Communauté** : Discord wgpu4k (rebrand futur), pas de Discord ygdrasil dédié |
| D11 | **Stratégie hybride JS/WASM** : JS first (1 sem MVP), WASM ensuite (1.5 sem) avec couche commune `koreos-web-common` |
| D12 | **Détection Linux X11/Wayland** : auto-détection runtime + override par variable d'env `KOREOS_LINUX_BACKEND` |
| D13 | **JDK cible = 25 (LTS)**. Trade-off conscient adoption vs modernité — voir §13. |

---

## 13. Décision JDK 25 — justification

Le reviewer v0.2 a soulevé la question (legitimement) du choix JDK 25 vs JDK 22/21 pour élargir l'adoption (FFM est stable depuis JDK 22, JDK 21 est LTS). La décision **JDK 25** est maintenue, avec les arguments suivants :

| Critère | JDK 21 LTS | JDK 22 | **JDK 25 LTS** |
|---|---|---|---|
| FFM stable | preview | ✅ stable | ✅ stable |
| Statut LTS | LTS (jusqu'à 2031) | non-LTS | **LTS (jusqu'à 2033)** |
| Pattern matching switch | preview | ✅ stable | ✅ stable + amélioré |
| Virtual threads | ✅ stable | ✅ stable | ✅ stable + tuned |
| `Linker.upcallStub` API | preview | ✅ stable | ✅ stable + perf améliorée |
| Adoption Q3 2026 | très large | déclinant | en croissance |

**Pourquoi JDK 25** :

1. **LTS du moment** — JDK 25 est la LTS la plus récente, supportée jusqu'à 2033 (Oracle/Eclipse Temurin). JDK 21 sort de "premium support" avant 2030. Cibler la LTS la plus fraîche garantit que les utilisateurs Koreos auront une version supportée longtemps.

2. **Aucune dépendance Koreos ne nécessite < JDK 25** — Pas de partenaire imposant une version inférieure (à confirmer si un consommateur le demande).

3. **L'écosystème ygdrasil est récent** — Koreos cible des utilisateurs qui construisent des projets neufs (jeux/outils 3D/Pong-like), pas des migrations legacy. Ces utilisateurs sont en général sur la JDK la plus récente.

4. **kextract génère du code FFM moderne** — les API `Linker`, `Arena.ofShared`, `MemorySegment.reinterpret` ont été polies post-JDK 22. Travailler sur la LTS la plus récente évite des workarounds.

**Conditions de révision** :

- Si un consommateur stratégique de Koreos (ex : intégration upstream Compose) impose JDK 21 → ré-évaluer.
- Si Kotlin/JVM perd la cible bytecode JDK 25 avant Koreos v1.0 → fallback JDK 22 (compromise FFM stable + adoption plus large).
- Si > 30% des bugs reportés mentionnent "JDK trop récent" → fallback JDK 22 (mesure d'adoption réelle).

**Fallback prêt** : le projet utilise déjà des `toolchain` Gradle ; descendre la cible de JDK 25 à JDK 22 est un changement minime (1 ligne dans le convention plugin `kmp-library`). À documenter dans `release-process.md`.

---

## 12. Annexes

### Glossaire (ajouts v0.2)

| Terme | Définition |
|-------|------------|
| **Wayland** | Protocole de compositor Linux moderne, remplaçant de X11. Stack : libwayland-client + xdg_shell. |
| **X11** | Protocole historique Linux/Unix de fenêtrage. Xlib (C) ou xcb (plus moderne). |
| **WebGPU** | API GPU moderne pour navigateurs, exposée via JS/WASM. Utilisée par wgpu4k Web. |
| **Kotlin/Wasm** | Cible de compilation Kotlin vers WebAssembly. Plus performante que Kotlin/JS pour code GPU/compute. |
| **xdg_shell** | Protocole Wayland standard pour fenêtres top-level (decorations, resize, fullscreen). |
| **PerMonitorV2** | Mode de DPI awareness Windows 10+ : chaque monitor a sa propre scale, géré par l'app. |

### Mapping winit → Koreos (ajouts v0.2)

| winit (Rust) | Koreos v0.2 |
|--------------|------------------|
| `RawWindowHandle::Web(WebHandle)` | `RawWindowHandle.Web(canvasElementId: String)` |
| `RawWindowHandle::Win32(Win32Handle)` | `RawWindowHandle.Win32(hwnd: Long, hinstance: Long)` |
| `RawWindowHandle::Xlib(XlibHandle)` | `RawWindowHandle.Xlib(window: Long, display: Long)` |
| `RawWindowHandle::Wayland(WaylandHandle)` | `RawWindowHandle.Wayland(surface: Long, display: Long)` |

### Documents associés

- [Plan v0.1 (livré)](./plan.md)
- [Specs v0.1 (livrées)](./specs.md)
- [Sprint review v0.1 — sortie conversationnelle](#)
- [Specs v0.2 (en cours)](./specs-v0.2.md)
