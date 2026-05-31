# Sprint Review — Kadre

> **Date** : 2026-05-29  
> **Périmètre couvert** : fondation macOS, rendu GPU, et les backends iOS / Android / macOS étendu  
> **Statut** : Livré ✓

---

## 1. Résumé exécutif

La fondation macOS, iOS et Android de Kadre a été livrée en une session intensive d'environ 24 heures effectives, suivant le [plan projet](./plan.md). La librairie expose une API fenêtrage cross-platform Kotlin Multiplatform, inspirée de winit, sur ces 3 plateformes, avec intégration validée wgpu4k (triangle RGB rendu à ~120 fps sur Apple M2).

---

## 2. Métriques de sprint

| Métrique | Valeur |
|----------|--------|
| Tickets Linear livrés | 28 (GRA-133 → GRA-160) |
| Pull Requests mergées | ~29 PRs (branches feature → master) |
| Plateformes supportées | 3 (macOS, iOS, Android) |
| Artefacts publiés Maven Central | 5 modules (`kadre-core`, `kadre-appkit`, `kadre-uikit`, `kadre-android`, `kadre`) |
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
| Modules Gradle créés (`kadre-core`, `kadre-appkit`, `kadre`) | ✓ Livré |
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
| GRA-140 | Post-mortem M2 + README Kadre validé | #24 |

**Correctif post-review** (PR #25) : triangle RGB @ 120 fps — 3 correctifs Metal/wgpu-native 0.25+ (framebuffer format, présentation FIFO, wgpu-native 0.25.x API).

**Critère de "done" atteint** : démo runnable Apple Silicon, 120 fps stables après correctif ; resize sans crash ; swap chain reconfiguré.

---

### Jalon M3 — Lib cible (GRA-141 → GRA-160, PRs #26–#46)

**Objectif** : lib KMP publiable, multi-plateforme, intégrable dans des projets tiers.

#### Backend iOS — `kadre-uikit` (GRA-141 → GRA-146)

| Ticket | Livrable |
|--------|---------|
| GRA-141 | Setup module `kadre-uikit` (iosX64, iosArm64, iosSimulatorArm64) |
| GRA-142 | `KadreAppDelegate` iOS lifecycle (AppDelegate-only) |
| GRA-143 | `UiKitWindow` — `UIWindow` + `UIView` + `CAMetalLayer` plein écran |
| GRA-144 | Touch events `UIResponder` → `WindowEvent.Touch` |
| GRA-145 | Lifecycle background/foreground + KDoc ordre callbacks |
| GRA-146 | Actual `EventLoop` iOS → `kadre-uikit` + sample `hello-touch` |

#### Backend Android — `kadre-android` (GRA-147 → GRA-152)

| Ticket | Livrable |
|--------|---------|
| GRA-147 | Setup module `kadre-android` (AGP, manifest, minSdk=24) |
| GRA-148 | `KadreActivity` + `AndroidWindow` SurfaceView plein écran |
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

**Critère de "done" atteint** : artefact `org.graphiks.kadre:kadre` publié Maven Central ; CI verte sur 3 plateformes ; documentation API MkDocs déployée.

---

## 4. Écarts identifiés (9 points de remédiation)

Ces écarts ont été identifiés en sortie de sprint review et corrigés avant la release 1.0.0.

| # | Domaine | Écart |
|---|---------|-------|
| 1 | MkDocs branding | `mkdocs.yml` : `site_name`, `site_description`, nav `kadre/api/` non rebranded Kadre |
| 2 | Samples Android | `hello-window-android` et `hello-touch-android` dupliquent la logique instead of commonMain partagé |
| 3 | Android EventLoop | `AndroidEventLoop.createWindow` lève `UnsupportedOperationException` au lieu de retourner une `AndroidWindow` fonctionnelle |
| 4 | README résidus | README racine contient encore des références "Clean Architecture / DDD / Compose / Koin" du starter-pack |
| 5 | Post-mortem M2 | Métrique FPS incorrecte (`~60 fps` → `~120 fps`) ; vidéo démo M2 non enregistrée |
| 6 | Commentaire stub | `AppKitEventLoop.kt:35` contient un commentaire "stub" obsolète |
| 7 | `KadreApplication.eventLoop` | Variable statique mutable (`var`) à refactorer en instance scopée |
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

- **Pas de test E2E smoke** : la régression PR #25 aurait pu être détectée automatiquement. Corrigé avant release.
- **`requestRedraw()` dans `aboutToWait`** : fonctionnel mais non-idiomatique. Remplacé par `ControlFlow.Poll` (cf. post-mortem M2 §Décisions M3).
- **Libération ressources wgpu** : ordre de destruction non garanti dans `releaseResources()`. `AutoClosableContext` prévu.
- **Absence de `Device.poll()`** : nécessaire pour les backends non-Metal (Linux, Windows). Anticipé pour la portabilité cross-platform.
- **Vidéo démo M2 non enregistrée** : livrable manquant pour la communication externe.

---

## 7. Références

- [Plan projet](./plan.md) — vision, périmètre (6 plateformes, Pong), risques
- [Post-mortem M2](./postmortem-m2.md) — analyse détaillée jalon M2
- [Spécifications techniques](./specs.md) — architecture, API, diagrammes
- [Release 1.0.0](https://github.com/ygdrasil-io/poc-koreos/releases/tag/v1.0.0) — tag GitHub + artefacts Maven Central
