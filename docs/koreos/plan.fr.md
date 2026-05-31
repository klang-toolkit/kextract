# Koreos — Plan projet

> Statut : **Draft pour relecture**
> Auteur : équipe Koreos
> Dernière mise à jour : 2026-05-27

---

## 1. Contexte

**Koreos** est un projet visant à fournir un équivalent de [winit](https://github.com/rust-windowing/winit) (la bibliothèque Rust de référence pour le fenêtrage et la gestion d'événements cross-platform) **en Kotlin pur**.

L'objectif final est de donner aux développeurs Kotlin un **contrôle bas-niveau** sur la fenêtre native et le compositeur du système hôte, **sans dépendance à AWT/Swing**, afin de permettre l'intégration de moteurs de rendu 3D natifs (Metal, Vulkan, WebGPU) via des handles natifs (`raw window handle`).

La motivation principale est de débloquer des cas d'usage **3D / GPU-intensifs** sur l'écosystème Kotlin Multiplatform, qui sont aujourd'hui contraints par AWT (cycle d'événements lourd, intégration GPU friable, pas d'accès direct au compositeur).

---

## 2. Vision

Une lib KMP qui :

- Expose une **API callback-driven** inspirée de winit (`ApplicationHandler`, `EventLoop`, `Window`).
- Donne accès aux **handles natifs bas-niveau** (`NSView`, `UIView`, `android.view.Surface`) directement consommables par un renderer 3D.
- Ne dépend **pas** d'AWT ni de Swing.
- Respecte les conventions idiomatiques Kotlin (sealed interfaces, coroutines pour les opérations async, null-safety).
- Reste **stable et publiable** sur Maven Central via les conventions du repo.

---

## 3. Objectifs et non-objectifs

### Objectifs

| Catégorie | Objectif |
|-----------|----------|
| Plateformes (V1) | macOS Desktop, iOS, Android |
| Plateformes (V2+) | Windows, Linux X11/Wayland |
| Couche réseau | Aucune dépendance AWT/Swing/JavaFX |
| Intégration 3D | Contrat `RawWindowHandle` compatible avec wgpu4k |
| API | Inspirée de winit, idiomatique Kotlin |
| Distribution | Artefact KMP publiable Maven Central |

### Non-objectifs (V1)

- Le **rendu 3D lui-même** — délégué à wgpu4k ou tout autre renderer consommant un raw handle.
- Le support **Web (WebGPU/canvas)** — à statuer après V1.
- Le support **Compose Multiplatform** — Koreos est plus bas-niveau, l'intégration Compose viendra ultérieurement si pertinent.
- Multi-fenêtre dans le POC initial (M1/M2).
- Accessibilité système (VoiceOver, TalkBack) — phase ultérieure.
- IME, clipboard avancé, drag&drop — phase ultérieure.

---

## 4. Parties prenantes

| Rôle | Responsabilité |
|------|----------------|
| PM / Tech Lead | Pilotage projet, validation des specs |
| Équipe Koreos | Implémentation core + backends |
| Équipe kextract | Finalisation du support subclassing Obj-C, bindings FFM AppKit |
| Équipe wgpu4k | Consommation des raw handles côté renderer 3D |
| Relecteurs | Validation du plan et des specs en PR |

---

## 5. Périmètre fonctionnel

### Modules livrés (V1)

| Module | Rôle | Cibles KMP |
|--------|------|------------|
| `koreos-core` | Interfaces, events, types DPI, raw handles | jvm, android, iosX64, iosArm64, iosSimulatorArm64 |
| `koreos-appkit` | Backend macOS Desktop via kextract (FFM) | jvm |
| `koreos-uikit` | Backend iOS via cinterop Kotlin/Native | iosX64, iosArm64, iosSimulatorArm64 |
| `koreos-android` | Backend Android via Surface SDK | android |
| `koreos` (facade) | API publique, sélection backend via `expect/actual` | toutes |
| `samples/hello-metal` | Sample POC | jvm |

### Modules hors périmètre V1

- `koreos-win32` — Win32 via FFM (V2)
- `koreos-x11` — Xlib/xcb via FFM (V2)
- `koreos-wayland` — wl_compositor via FFM (V2)
- `koreos-web` — WebGPU/canvas (à statuer)

---

## 6. Jalons et livrables

### Jalon M1 — POC : vue Metal minimale

**Objectif** : prouver que la stack de binding kextract + l'archi modulaire permettent d'ouvrir une fenêtre native et d'exposer un `NSView` prêt pour Metal.

**Livrable** :
- Modules Gradle créés (`koreos-core`, `koreos-appkit`, `koreos`)
- Une fenêtre macOS s'ouvre via `samples/hello-metal`
- Le `contentView` est layer-backed (`wantsLayer = true`)
- L'application se ferme proprement (clic sur la croix de la fenêtre)

**Hors scope M1** :
- Aucun event loop avancé
- Aucun input clavier/souris
- Aucun resize géré
- Pas d'autres backends (iOS, Android)

**Définition de "done"** :
- `./gradlew :samples:hello-metal:run` ouvre une fenêtre vide.
- `nsView.layer != null` (vérifié via log).
- Fermeture sans crash.

---

### Jalon M2 — Démo wgpu4k

**Objectif** : valider le **contrat raw handle** avec un renderer 3D réel (wgpu4k) et démontrer un rendu basique.

**Livrable** :
- `wgpu4k` consomme le `RawWindowHandle.AppKit` exposé par Koreos.
- Une scène simple (triangle ou cube tournant) est rendue dans la fenêtre.
- Le redimensionnement déclenche la recréation du swap chain (event `WindowEvent.Resized`).
- L'event loop gère `CloseRequested` et `RedrawRequested`.

**Hors scope M2** :
- Input keyboard/mouse (pas nécessaire pour la démo)
- Backends iOS/Android (toujours macOS only)
- Multi-fenêtre

**Définition de "done"** :
- Démo runnable sur Apple Silicon, 60fps stable.
- Resize sans crash, swap chain reconfiguré correctement.
- Vidéo de la démo enregistrée pour communication.

---

### Jalon M3 — Lib cible

**Objectif** : librairie KMP publiable, multi-plateforme, intégrable dans des projets tiers.

**Livrable** :
- Backends complets pour les 3 plateformes : macOS (`koreos-appkit`), iOS (`koreos-uikit`), Android (`koreos-android`).
- API publique stable et documentée : `ApplicationHandler`, `EventLoop`, `Window`, événements complets.
- Lifecycle complet : `resumed`, `suspended`, `destroySurfaces` (Android).
- Multi-fenêtre supporté (au moins sur Desktop).
- Input : clavier, souris (Desktop), touch (mobile), device events.
- Samples : `hello-window`, `hello-triangle` runnables sur les 3 plateformes.
- Documentation Dokka + MkDocs.
- Publication Maven Central via les convention plugins existants (`kmp-library`, `kmp-publish`).

**Définition de "done"** :
- Suite de tests passant en CI sur les 3 cibles.
- Artefact publié Maven Central avec version `0.1.0`.
- Documentation API accessible via le site MkDocs.

---

## 7. Critères de succès

| Jalon | Critère mesurable |
|-------|--------------------|
| M1 | NSWindow ouverte avec contentView layer-backed visible. Fermeture propre. |
| M2 | wgpu4k rend une scène basique à 60fps. Resize ne crash pas. |
| M3 | Lib publiée Maven Central. Samples runnables sur 3 plateformes. CI verte. |

---

## 8. Risques et mitigations

| Risque | Probabilité | Impact | Mitigation |
|--------|-------------|--------|------------|
| Subclassing Obj-C non finalisé dans kextract | Moyenne | Bloquant M3 (et partiellement M1) | Coordination étroite équipe kextract. Fallback : shim Obj-C compilé à part en C, embarqué dans l'artefact. |
| Divergence cinterop (iOS) vs FFM (macOS) au niveau API | Forte | Friction de maintenance | Contrat strict `koreos-core` en commonMain qui force la convergence. Tests d'intégration partagés. |
| wgpu4k pas prêt à consommer le handle pour M2 | Faible | Décalage planning de M2 | M1 reste démontrable seul. M2 peut basculer sur Metal direct si wgpu4k tarde. |
| Lifecycle iOS complexe (background, scene restoration) | Moyenne | Reportable post-M3 | Couvrir uniquement `resumed`/`suspended` dans M3. Le reste en V1.x. |
| Compatibilité JDK FFM (Panama API surface) | Faible | Refacto à JDK 26+ | FFM est stable depuis JDK 22, JDK 25 LTS est sûr. |
| Apple changements AppKit (macOS 26+) | Faible | Bug surface | Tests CI sur macOS LTS uniquement. |
| Multi-thread bugs (main thread enforcement) | Moyenne | Crash hard-to-debug | Asserts runtime sur `Thread.currentThread() == mainThread` à chaque entrée publique. |

---

## 9. Dépendances externes

| Dépendance | Version cible | Statut |
|------------|---------------|--------|
| **kextract** | Finalisation subclassing Obj-C | En cours |
| **wgpu4k** | Version consommant `RawWindowHandle` | À vérifier |
| JDK | 25 (LTS) | Disponible |
| Kotlin | 2.3.21 | Configuré dans le repo |
| Gradle | 9.5.0 | Configuré dans le repo |
| AGP | 9.0.0 | Configuré dans le repo |

---

## 10. Timeline indicative

> Estimations à affiner avec le backlog Linear.

| Jalon | Durée | Échéance cible |
|-------|-------|----------------|
| M1 — POC Metal view | ~2 semaines | T0 + 2sem |
| M2 — Démo wgpu4k | ~2 semaines | T0 + 4sem |
| M3 — Lib cible V1 | ~10 semaines | T0 + 14sem |

Le T0 est conditionné à la finalisation du subclassing Obj-C dans kextract.

---

## 11. Décisions d'architecture déjà actées

Décisions verrouillées lors des discussions préparatoires, formalisées dans les [specs](./specs.md) :

1. **iOS via Kotlin/Native + cinterop** (pas de kextract sur iOS, pas de JVM).
2. **Android Strategy A** : `android.view.Surface` exposée brute, aucune lib JNI custom.
3. **macOS via JVM 25 + kextract FFM**.
4. **AppKit et UIKit séparés en modules distincts** (lifecycles fondamentalement différents).
5. **Subclassing Obj-C** plutôt que method swizzling pour intercepter `NSApplication.sendEvent:`.

---

## 12. Annexes

### Glossaire

| Terme | Définition |
|-------|------------|
| **winit** | Crate Rust de référence pour le fenêtrage cross-platform (https://github.com/rust-windowing/winit). |
| **wgpu4k** | Port Kotlin de wgpu, renderer 3D bas-niveau cross-platform. |
| **kextract** | Outil interne — équivalent jextract pour Kotlin avec support Obj-C, génère des bindings FFM (JVM 22+). |
| **FFM** | Foreign Function & Memory API (JEP 454), interop natif JVM standardisé depuis Java 22. |
| **cinterop** | Outil Kotlin/Native pour générer des bindings vers des bibliothèques C/Obj-C. |
| **Raw Window Handle** | Contrat exposant les handles natifs de fenêtre (`NSView*`, `HWND`, etc.) à un renderer externe. |
| **CAMetalLayer** | Layer Core Animation supportant Metal sur macOS/iOS. |

### Documents associés

- [Spécifications techniques](./specs.md)
- [README projet](../../README.md)
