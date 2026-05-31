# Sprint Review — Koreos v0.1.0

> **Date** : 2026-05-29  
> **Sprint couvert** : M1 → M3 (jalons 1 à 3 du plan v0.1)  
> **Version livrée** : `io.ygdrasil.koreos:0.1.0` (publié Maven Central)  
> **Statut** : Livré ✓

---

## 1. Résumé exécutif

Koreos v0.1.0 est livré en une session intensive d'environ 24 heures effectives, couvrant les trois jalons initiaux du [plan v0.1](./plan.md). La librairie expose une API fenêtrage cross-platform Kotlin Multiplatform, inspirée de winit, sur 3 plateformes (macOS, iOS, Android), avec intégration validée wgpu4k (triangle RGB rendu à ~120 fps sur Apple M2).

---

## 2. Métriques de sprint

| Métrique | Valeur |
|----------|--------|
| Tickets Linear livrés | 28 (GRA-133 → GRA-160) |
| Pull Requests mergées | ~29 PRs (branches feature → master) |
| Plateformes supportées | 3 (macOS, iOS, Android) |
| Artefacts publiés Maven Central | 5 modules (`koreos-core`, `koreos-appkit`, `koreos-uikit`, `koreos-android`, `koreos`) |
| Version publiée | `0.1.0` |
| FPS rendu (hello-triangle, Apple M2, Release) | ~120 fps (post-correctif PR #25) |
| Dépendances natives (JNA/Rococoa) | 0 |
| Temps de build CI (fast path) | ~3-4 min |
| Lignes de code Kotlin ajoutées (net, M2 seul) | ~1 200 |
| Durée totale de la session M3 | ~1 journée |

---

## 3. Livrables par jalon

### Jalon M1 — POC : vue Metal minimale

**Objectif** : prouver que kextract + FFM permettent d'ouvrir une fenêtre native et d'exposer un `NSView` layer-backed.

| Livrable | Statut |
|---------|--------|
| Modules Gradle créés (`koreos-core`, `koreos-appkit`, `koreos`) | ✓ Livré |
| Fenêtre macOS via `samples/hello-metal` | ✓ Livré |
| `contentView` layer-backed (`wantsLayer = true`) | ✓ Livré |
| Fermeture propre (clic croix) | ✓ Livré |

**Critère de "done" atteint** : `./gradlew :samples:hello-metal:run` ouvre une fenêtre vide ; `nsView.layer != null` ; fermeture sans crash.

---

### Jalon M2 — Démo wgpu4k (GRA-133 → GRA-140, PRs #18–#25)

**Objectif** : valider le contrat raw handle avec wgpu4k et démontrer un rendu basique.

| Ticket | Livrable | PRs |
|--------|---------|-----|
| GRA-133 | `WindowEvent.ScaleFactorChanged` | #18 |
| GRA-134 | `WindowEvent.RedrawRequested` + `CFRunLoopObserver` | #19 |
| GRA-135 | `aboutToWait` callback après `RedrawRequested` | #20 |
| GRA-136 | `ControlFlow` effectif + `EventLoopProxy.wakeUp` thread-safe | #20 |
| GRA-137 | `hello-triangle` : wgpu4k Instance + Surface + Adapter + Device | #21 |
| GRA-138 | `hello-triangle` : rendu triangle RGB | #22 |
| GRA-139 | `hello-triangle` : resize swap chain | #23 |
| GRA-140 | Post-mortem M2 + README Koreos validé | #24 |

**Correctif post-review** (PR #25) : triangle RGB @ 120 fps — 3 correctifs Metal/wgpu-native 0.25+ (framebuffer format, présentation FIFO, wgpu-native 0.25.x API).

**Critère de "done" atteint** : démo runnable Apple Silicon, 120 fps stables après correctif ; resize sans crash ; swap chain reconfiguré.

---

### Jalon M3 — Lib cible (GRA-141 → GRA-160, PRs #26–#46)

**Objectif** : lib KMP publiable, multi-plateforme, intégrable dans des projets tiers.

#### Backend iOS — `koreos-uikit` (GRA-141 → GRA-146)

| Ticket | Livrable |
|--------|---------|
| GRA-141 | Setup module `koreos-uikit` (iosX64, iosArm64, iosSimulatorArm64) |
| GRA-142 | `KoreosAppDelegate` iOS lifecycle (AppDelegate-only) |
| GRA-143 | `UiKitWindow` — `UIWindow` + `UIView` + `CAMetalLayer` plein écran |
| GRA-144 | Touch events `UIResponder` → `WindowEvent.Touch` |
| GRA-145 | Lifecycle background/foreground + KDoc ordre callbacks |
| GRA-146 | Actual `EventLoop` iOS → `koreos-uikit` + sample `hello-touch` |

#### Backend Android — `koreos-android` (GRA-147 → GRA-152)

| Ticket | Livrable |
|--------|---------|
| GRA-147 | Setup module `koreos-android` (AGP, manifest, minSdk=24) |
| GRA-148 | `KoreosActivity` + `AndroidWindow` SurfaceView plein écran |
| GRA-149 | Lifecycle dispatch Activity + SurfaceHolder → `ApplicationHandler` |
| GRA-150 | `MotionEvent` → `WindowEvent.Touch` multi-touch dispatch |
| GRA-151 | Choreographer frame timing + `RedrawRequested` dispatch |
| GRA-152 | Actual `EventLoop` androidMain + sample `hello-touch-android` |

#### Backend macOS étendu (GRA-153 → GRA-156)

| Ticket | Livrable |
|--------|---------|
| GRA-153 | Multi-window support : `windowWillClose` cleanup + `exit()` ferme tout |
| GRA-154 | Keyboard input `sendEvent:` → `WindowEvent.KeyboardInput` + `isRepeat` |
| GRA-155 | Mouse input complet (clics, déplacement, scroll, enter/exit) |
| GRA-156 | `DeviceEvent` dispatch (`PointerMotion`, `Button`, `Key`) avant `WindowEvent` |

#### Infrastructure & publication (GRA-157 → GRA-160)

| Ticket | Livrable |
|--------|---------|
| GRA-157 | Dokka KDoc coverage + intégration MkDocs API Reference |
| GRA-158 | Sample `hello-window` cross-platform (JVM + iOS + Android) |
| GRA-159 | Maven Central publication (`kmp-publish`, signing, GPG) |
| GRA-160 | CI multi-platform (macOS + iOS simulator + Android) |

**Critère de "done" atteint** : artefact `io.ygdrasil.koreos:0.1.0` publié Maven Central ; CI verte sur 3 plateformes ; documentation API MkDocs déployée.

---

## 4. Écarts identifiés (9 points de remédiation → v0.1.1)

Ces écarts ont été identifiés en sortie de sprint review et sont planifiés pour correction dans [Sprint 0 — v0.1.1](./plan-v0.2.md#sprint-0-remediation-v011-2-semaines).

| # | Domaine | Écart |
|---|---------|-------|
| 1 | MkDocs branding | `mkdocs.yml` : `site_name`, `site_description`, nav `koreos/api/` non rebranded Koreos |
| 2 | Samples Android | `hello-window-android` et `hello-touch-android` dupliquent la logique instead of commonMain partagé |
| 3 | Android EventLoop | `AndroidEventLoop.createWindow` lève `UnsupportedOperationException` au lieu de retourner une `AndroidWindow` fonctionnelle |
| 4 | README résidus | README racine contient encore des références "Clean Architecture / DDD / Compose / Koin" du starter-pack |
| 5 | Post-mortem M2 | Métrique FPS incorrecte (`~60 fps` → `~120 fps`) ; vidéo démo M2 non enregistrée |
| 6 | Commentaire stub | `AppKitEventLoop.kt:35` contient un commentaire "stub" obsolète |
| 7 | `KoreosApplication.eventLoop` | Variable statique mutable (`var`) à refactorer en instance scopée |
| 8 | CI branches feature | `ios-build`/`android-build` ne tournent que sur push `master` ; à étendre aux branches PR |
| 9 | Test E2E smoke | Pas de test "au moins une frame rendue" sur `hello-triangle` (régression possible, cf. PR #25) |

---

## 5. Leçon principale — PR #25 (régression wgpu-native 0.25+)

**Problème** : après merge de GRA-138 (triangle RGB), le rendu était cassé à 0 fps suite à la mise à jour vers wgpu-native 0.25+.

**Cause** : trois incompatibilités de breaking changes wgpu-native :
1. Format framebuffer : `BGRA8Unorm` requis sur Metal à la place de `RGBA8Unorm`.
2. Mode de présentation : `PresentMode.FIFO` remplace l'ancienne valeur par défaut.
3. API wgpu-native 0.25.x : changements de signatures dans `createRenderPipeline`.

**Résolution** (PR #25) : 3 correctifs ciblés, triangle stable à 120 fps (VSync Metal sur Apple M2).

**Leçon** : les mises à jour de wgpu-native sont des breaking changes fréquents. Verrouiller la version (`wgpu-native = "0.25.x"`) dans le catalogue de versions et ajouter un test smoke anti-régression "au moins une frame rendue" avant chaque bump.

---

## 6. Rétrospective

### Ce qui a bien marché

- **Panama FFM comme seule couche native** : zéro dépendance JNA/Rococoa, downcalls directs vers `objc_msgSend`, gestion mémoire via `Arena.ofAuto()`. Approche confirmée solide pour M3+.
- **Architecture `ApplicationHandler`** : interface callback-driven (`canCreateSurfaces`, `aboutToWait`, `windowEvent`) extensible sans couplage aux détails AppKit/UIKit/Android.
- **CFRunLoop comme scheduler** : `kCFRunLoopBeforeWaiting` + `CFRunLoopTimer` pour `ControlFlow.WaitUntil` — élégant, précis, sans thread supplémentaire.
- **API wgpu4k stable et portable** : la séquence `Instance → Surface → Adapter → Device → Pipeline → render loop` est idiomatique WebGPU et reproductible sur d'autres plateformes.
- **Velocity élevée** : 28 tickets, ~29 PRs, 3 plateformes, publication Maven Central — tout livré en ~24h effectives.

### Points à améliorer

- **Pas de test E2E smoke** : la régression PR #25 aurait pu être détectée automatiquement. Priorité v0.1.1.
- **`requestRedraw()` dans `aboutToWait`** : fonctionnel mais non-idiomatique. Remplacer par `ControlFlow.Poll` en v0.2 (cf. post-mortem M2 §Décisions M3).
- **Libération ressources wgpu** : ordre de destruction non garanti dans `releaseResources()`. Prévoir `AutoClosableContext` en v0.2.
- **Absence de `Device.poll()`** : nécessaire pour les backends non-Metal (Linux, Windows). À anticiper pour la portabilité cross-platform.
- **Vidéo démo M2 non enregistrée** : livrable manquant pour la communication externe. Planifié v0.1.1.

---

## 7. Références

- [Plan projet v0.1](./plan.md) — jalons M1-M3, risques, timeline
- [Plan projet v0.2](./plan-v0.2.md) — roadmap extensions (6 plateformes, Pong)
- [Post-mortem M2](./postmortem-m2.md) — analyse détaillée jalon M2
- [Spécifications v0.1](./specs.md) — architecture, API, diagrammes
- [Release v0.1.0](https://github.com/ygdrasil-io/poc-koreos/releases/tag/v0.1.0) — tag GitHub + artefacts Maven Central
