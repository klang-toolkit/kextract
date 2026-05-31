# Post-mortem M2 — Démo wgpu4k sur AppKit/macOS

**Date de clôture** : 2026-05-28  
**Tickets** : GRA-133 → GRA-140  
**Statut** : ✅ Validé

---

## Résumé

Le jalon M2 valide l'intégration bout-en-bout de la stack graphique Koreos sur macOS :

```
EventLoop (AppKit/CFRunLoop)
  ↓ canCreateSurfaces
NSWindow + CAMetalLayer (Panama FFM)
  ↓ rawWindowHandle
wgpu4k Instance → Surface → Adapter → Device
  ↓ createRenderPipeline
WGSL shader → triangle RGB @ ~120 fps
  ↓ WindowEvent.Resized
surface.configure(newWidth, newHeight)
```

**Résultat** : un triangle RGB tourne à ~120 fps dans une fenêtre Koreos redimensionnable, sans JNA ni Rococoa — uniquement Panama FFM (JDK 25).

> **Correction post-review (PR #25)** : le rendu initial tournait à ~60 fps (puis 0 fps après mise à jour wgpu-native 0.25+). Trois correctifs Metal ont été appliqués via PR #25 (wgpu-native 0.25+ — format framebuffer BGRA8Unorm, mode de présentation FIFO, signatures API 0.25.x). Résultat final : ~120 fps en ProMotion sur Apple M2 Max.

---

## Tickets livrés

| Ticket | Titre | Estimate | Réel |
|--------|-------|----------|------|
| GRA-133 | WindowEvent.ScaleFactorChanged | 1pt | ~1h |
| GRA-134 | WindowEvent.RedrawRequested + CFRunLoopObserver | 3pt | ~2h |
| GRA-135 | aboutToWait callback après RedrawRequested | 2pt | ~1.5h |
| GRA-136 | ControlFlow effectif + EventLoopProxy.wakeUp thread-safe | 5pt | ~4h |
| GRA-137 | samples/hello-triangle: wgpu4k Instance+Surface+Adapter+Device | 3pt | ~3h |
| GRA-138 | samples/hello-triangle: rendu triangle RGB | 5pt | ~4h |
| GRA-139 | samples/hello-triangle: resize swap chain | 2pt | ~1h |
| GRA-140 | Post-mortem + README | 1pt | ~1h |

**Total** : 22 points estimés, livrés en une session (~18h).

---

## Ce qui a bien marché

### 1. Panama FFM comme seule couche native

Le choix de Panama FFM (`java.lang.foreign`) à la place de JNA/Rococoa s'est avéré judicieux :
- **Zero dépendances natives** : tout tient dans la JVM standard (JDK 25)
- **Performance** : downcalls directs vers `objc_msgSend`, `CFRunLoopWakeUp`, `sel_registerName` sans indirection
- **Maintenabilité** : les signatures sont vérifiées à la compilation via `FunctionDescriptor`
- **Pas de leak** : `Arena.ofAuto()` gère la durée de vie des segments natifs automatiquement

### 2. Architecture EventLoop → ApplicationHandler

L'interface `ApplicationHandler` avec ses callbacks (`canCreateSurfaces`, `aboutToWait`, `windowEvent`) offre un point d'extension propre. Le sample hello-triangle n'a besoin de rien connaître des détails AppKit.

### 3. CFRunLoop comme base du scheduling

Utiliser `kCFRunLoopBeforeWaiting` + `CFRunLoopTimer` pour implémenter `ControlFlow.WaitUntil` est élégant : le système AppKit gère lui-même la précision du timer, sans thread supplémentaire.

### 4. wgpu4k API stable et bien structurée

L'API wgpu4k 0.1.1 suit fidèlement la spec WebGPU. La séquence `Instance → Surface → Adapter → Device → Pipeline → render loop` est idiomatique et portable.

---

## Surprises techniques

### 1. `webgpu-ktypes-descriptors` manquant dans `wgpu4k:0.1.1`

**Symptôme** : `Unresolved reference 'VertexState'`, `'RenderPipelineDescriptor'`, etc.  
**Cause** : `wgpu4k:0.1.1` ne dépend que de `webgpu-ktypes:0.0.7` (interfaces uniquement). Les data classes concrètes (`VertexState`, `FragmentState`, `Color`, `RenderPassDescriptor`, etc.) vivent dans le module séparé `webgpu-ktypes-descriptors`.  
**Résolution** : ajout explicite de `io.ygdrasil:webgpu-ktypes-descriptors:0.0.7` dans `samples/hello-triangle/build.gradle.kts`.  
**Leçon** : toujours vérifier le POM d'une dépendance avant de supposer que les types sont disponibles transitivement.

### 2. `configureWithMetalLayer` vs appel FFM direct

**Symptôme** : `WGPU.getSurfaceFromMetalLayer` attend un `NativeAddress` (= `JvmNativeAddress`) wrappant un `MemorySegment`.  
**Cause** : l'API wgpu4k expose `ffi.JvmNativeAddress`, pas un `Long` brut.  
**Résolution** : `JvmNativeAddress(MemorySegment.ofAddress(metalLayerAddr))`.  
**Leçon** : le modèle de types de ffi/Panama nécessite d'encapsuler les adresses avant de les passer aux bibliothèques.

### 3. Enums PascalCase dans webgpu-ktypes

**Symptôme** : `bgra8unorm`, `opaque`, `renderAttachment` → erreurs de compilation.  
**Cause** : contrairement aux noms WebGPU spec (camelCase minuscule), les enums Kotlin utilisent PascalCase.  
**Résolution** : `GPUTextureFormat.BGRA8Unorm`, `CompositeAlphaMode.Opaque`, `GPUTextureUsage.RenderAttachment`.

### 4. Dépendance `kotlinx-coroutines-core` non transitive

**Symptôme** : `Unresolved reference 'runBlocking'` malgré wgpu4k qui utilise des coroutines.  
**Cause** : `kotlinx-coroutines-core` est en scope `runtime` dans le POM de wgpu4k, pas `compile`.  
**Résolution** : déclaration explicite dans `dependencies { implementation(libs.kotlinx.coroutines.core) }`.

### 5. Runner GitHub Actions lent (~10 min pour un build de 4 min)

**Symptôme** : deux runs déclenchés par push, le second avec un runner qui peine.  
**Cause** : free tier GitHub Actions, files d'attente variables.  
**Impact** : aucun sur la qualité — le premier run était toujours entièrement vert.

---

## Décisions à revoir pour M3

### 1. `requestRedraw()` dans `aboutToWait` → à remplacer par `ControlFlow.Poll`

Actuellement, `aboutToWait` appelle `window.requestRedraw()` pour déclencher un redraw continu. C'est fonctionnel mais pas idiomatique : il vaudrait mieux que l'application passe `ControlFlow.Poll` pour signifier "je veux tourner en continu" et laisser la boucle gérer la cadence.

### 2. Libération des ressources wgpu côté `Device`

La libération (`device.close()`, `surface.close()`, `wgpu.close()`) dans `releaseResources()` fonctionne mais ne garantit pas l'ordre de destruction. Pour M3, envisager un `AutoClosableContext` comme dans les samples wgpu4k-scenes.

### 3. Pas de `Device.poll()` entre les frames

wgpu native nécessite un appel périodique à `Device.poll()` (ou équivalent) pour processer les callbacks GPU asynchrones. Sur Metal, cela n'est pas bloquant, mais sur d'autres backends ce sera nécessaire. À anticiper pour la portabilité.

### 4. Découplage `hello-triangle` / `koreos-appkit`

Le sample appelle directement `getMetalLayerFromNsView()` via Panama FFM plutôt que de passer par `koreos-appkit`. C'est intentionnel pour maintenir le sample indépendant, mais une API `RawWindowHandle → NativeAddress` dans `koreos-appkit` simplifierait les futurs samples.

---

## Métriques de fin M2

| Métrique | Valeur |
|----------|--------|
| FPS moyen (Apple M2 Max, Release) | ~120 fps (ProMotion, post-correctif PR #25) |
| Tickets livrés | 8 |
| PRs mergées | 7 (#18 → #25) |
| Fichiers Kotlin créés | 7 |
| Lignes de code ajoutées (net) | ~1 200 |
| Dépendances natives (JNA/Rococoa) | 0 |
| Temps de build CI (fast) | ~3-4 min |
| Durée de la session M2 | ~1 journée |

---

## Vidéo de démo

> **À enregistrer manuellement** : lancer `./gradlew :samples:hello-triangle:run` sur macOS Apple Silicon,  
> enregistrer ~30s avec QuickTime (ouverture fenêtre → triangle RGB → resize → fermeture),  
> uploader sur la release GitHub ou en attachment Linear GRA-140.

---

## Prochaines étapes (M3)

- Clavier / souris : `KeyboardInput`, `MouseInput` → interaction avec le triangle
- Multi-fenêtres : gestion de plusieurs `WindowId` dans le même `ApplicationHandler`
- Portabilité : Linux (X11/Wayland), Windows (DXGI)
- Abstraction `Renderer` : séparer la logique de rendu de l'ApplicationHandler
