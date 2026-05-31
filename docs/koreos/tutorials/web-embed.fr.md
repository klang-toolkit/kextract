# Tutoriel : intégrer Koreos dans une page web

Ce tutoriel vous guide pas-à-pas pour afficher une surface Koreos dans une page HTML avec Kotlin/JS. Vous obtiendrez un canvas interactif qui répond aux événements pointeur et clavier grâce à la boucle d'événements `requestAnimationFrame`.

**Prérequis** : Kotlin 2.3.21+, Gradle 9+, un navigateur moderne (Chrome 112+, Firefox 113+, Safari 17+).

---

## Étape 1 — Configurer `build.gradle.kts`

Créez (ou adaptez) votre fichier `build.gradle.kts` avec la dépendance `koreos-js` et la cible Kotlin/JS configurée pour le navigateur :

```kotlin
plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.3.21"
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "myapp.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        jsMain {
            dependencies {
                // Façade Koreos pour le navigateur — Kotlin/JS IR
                implementation("io.ygdrasil.koreos:koreos-js:0.1.1")
            }
        }
    }
}
```

!!! note "Kotlin/JS IR obligatoire"
    Koreos utilise `@JsExport` et les optimisations DCE du compilateur IR.
    Le backend legacy Kotlin/JS n'est pas supporté.

---

## Étape 2 — Implémenter `ApplicationHandler`

`ApplicationHandler` est l'interface centrale : Koreos l'appelle pour chaque événement du cycle de vie et de la fenêtre. Côté web, la « fenêtre » correspond à un élément `<canvas>` identifié par son id CSS.

```kotlin
package com.example.myapp

import io.ygdrasil.koreos.core.ActiveEventLoop
import io.ygdrasil.koreos.core.ApplicationHandler
import io.ygdrasil.koreos.core.Window
import io.ygdrasil.koreos.core.WindowAttributes
import io.ygdrasil.koreos.core.WindowId
import io.ygdrasil.koreos.web.WebWindowEvent

class MyWebHandler : ApplicationHandler {

    private var window: Window? = null

    // Appelé au démarrage — la page est prête, le DOM est chargé
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        window = eventLoop.createWindow(
            WindowAttributes(
                // Le titre est utilisé comme id CSS du canvas cible
                title = "my-canvas",
            )
        )
    }

    // Appelé pour chaque événement de fenêtre (événements DOM traduits)
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        when (event) {
            // Redessin : déclenché par requestAnimationFrame
            WebWindowEvent.RedrawRequested -> {
                // Placez ici l'appel à votre renderer (wgpu4k, WebGL, WebGPU, etc.)
            }

            // Clôture de page / navigation hors de la page
            WebWindowEvent.CloseRequested -> {
                eventLoop.exit()
            }

            // Redimensionnement du canvas
            is WebWindowEvent.Resized ->
                println("Resized → ${event.width}×${event.height}")

            // Déplacement du pointeur (PointerEvent DOM unifié — souris, stylet, tactile)
            is WebWindowEvent.PointerMoved ->
                println("PointerMoved (${event.x.toInt()}, ${event.y.toInt()})")

            // Clic souris / appui tactile
            is WebWindowEvent.MouseInput ->
                println("MouseInput ${event.state} button=${event.button}")

            // Défilement (molette ou pinch-to-zoom)
            is WebWindowEvent.MouseWheel ->
                println("Wheel Δx=${event.deltaX} Δy=${event.deltaY}")

            // Entrées clavier
            is WebWindowEvent.KeyboardInput ->
                println("Key ${event.state} key=${event.key} repeat=${event.isRepeat}")

            // Focus / perte de focus
            is WebWindowEvent.Focused ->
                println("Focused: ${event.gained}")

            else -> Unit
        }
    }

    override fun resumed(eventLoop: ActiveEventLoop) = Unit
    override fun suspended(eventLoop: ActiveEventLoop) = Unit

    override fun destroySurfaces(eventLoop: ActiveEventLoop) {
        window = null
    }
}
```

---

## Étape 3 — Point d'entrée `main()`

```kotlin
package com.example.myapp

import io.ygdrasil.koreos.web.JsWebEventLoop

fun main() {
    // JsWebEventLoop est non-bloquant : il planifie des callbacks via
    // requestAnimationFrame et rend le contrôle immédiatement au navigateur.
    JsWebEventLoop().runApp(MyWebHandler())
}
```

!!! warning "Boucle non-bloquante — différence clé avec JVM"
    Contrairement à l'EventLoop JVM ou Win32, `JsWebEventLoop.runApp()` **retourne immédiatement**.
    La boucle s'appuie sur `requestAnimationFrame` pour planifier les frames au rythme
    du navigateur (typiquement 60 Hz). Aucun thread dédié n'est créé : tout s'exécute
    dans le thread JavaScript principal.

---

## Étape 4 — Page HTML hôte

Créez (ou adaptez) votre `index.html`. Le canvas doit exister dans le DOM **avant** le chargement du bundle JS :

```html
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Mon application Koreos Web</title>
    <style>
        /* Le canvas doit avoir des dimensions explicites en CSS */
        #my-canvas {
            display: block;
            width: 800px;
            height: 600px;
            border: 1px solid #ccc;
            touch-action: none; /* Désactive le scroll tactile par défaut */
        }
    </style>
</head>
<body>
    <!-- L'id correspond au titre passé dans WindowAttributes -->
    <canvas id="my-canvas"></canvas>

    <!-- Bundle JS généré par Gradle — chargé en différé -->
    <script src="myapp.js" defer></script>
</body>
</html>
```

Lancez ensuite le serveur de développement Gradle :

```bash
./gradlew jsBrowserDevelopmentRun
```

Ou pour produire un bundle de production :

```bash
./gradlew jsBrowserProductionWebpack
```

Les artefacts sont générés dans `build/distributions/`.

!!! warning "Le canvas doit exister avant `runApp`"
    Koreos résout le canvas par son id CSS au moment de l'appel `createWindow`.
    Si le `<script>` est chargé **avant** le canvas dans le DOM, `createWindow`
    ne trouvera pas l'élément et la surface ne sera pas attachée.
    Utilisez `defer` ou placez le `<script>` après le `<canvas>`.

!!! tip "DPI haute densité — `devicePixelRatio`"
    Sur les écrans Retina ou HiDPI, `window.devicePixelRatio` est supérieur à 1 (ex. 2.0).
    Pour un rendu net, définissez les dimensions physiques du canvas en pixels :

    ```javascript
    const canvas = document.getElementById('my-canvas');
    const dpr = window.devicePixelRatio || 1;
    canvas.width  = canvas.offsetWidth  * dpr;
    canvas.height = canvas.offsetHeight * dpr;
    ```

    Koreos exposera cette valeur via `Window.scaleFactor` dans une version future
    (ticket #24). En attendant, lisez `window.devicePixelRatio` directement.

---

## Étape 5 (option) — Variante Kotlin/Wasm

Pour une performance maximale du rendu, utilisez la cible `koreos-wasm` (Kotlin/Wasm) à la place de `koreos-js`. Kotlin/Wasm compile vers WebAssembly, ce qui offre des performances d'exécution proches du natif pour le code de rendu.

### Dépendance

```kotlin
// build.gradle.kts
plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.3.21"
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        wasmJsMain {
            dependencies {
                implementation("io.ygdrasil.koreos:koreos-wasm:0.1.1")
            }
        }
    }
}
```

### Point d'entrée Wasm

```kotlin
package com.example.myapp

import io.ygdrasil.koreos.web.WasmJsWebEventLoop

fun main() {
    // WasmJsWebEventLoop — même API que JsWebEventLoop, même comportement RAF
    WasmJsWebEventLoop().runApp(MyWebHandler())
}
```

!!! note "Compatibilité navigateur"
    Kotlin/Wasm requiert les extensions **WasmGC** et **JS imports/exports**, disponibles
    dans Chrome 119+, Firefox 120+, et Safari 17.4+. Vérifiez la compatibilité cible
    avant de migrer.

!!! tip "Quand choisir Kotlin/Wasm ?"
    | Critère | Kotlin/JS | Kotlin/Wasm |
    |---------|-----------|-------------|
    | Compatibilité navigateur | Large (tous les navigateurs modernes) | Restreinte (WasmGC requis) |
    | Interop JavaScript | Native | Via `@JsExport` / `external` |
    | Performance CPU | Bonne | Excellente |
    | Taille du bundle | Standard | Plus petit (pas de stdlib JS) |

    Pour la majorité des cas d'usage, `koreos-js` suffit. Optez pour `koreos-wasm`
    si votre renderer effectue des calculs intensifs côté Kotlin.

---

## Récapitulatif des points clés

| Point | Détail |
|-------|--------|
| Boucle d'événements | Non-bloquante — `requestAnimationFrame`, pas de thread dédié |
| Canvas cible | Identifié par `WindowAttributes.title` comme id CSS |
| DOM au démarrage | Le `<canvas>` doit exister avant le chargement du bundle JS |
| Événements pointeur | `WebWindowEvent.PointerMoved` / `MouseInput` — basé sur `PointerEvent` DOM |
| DPI haute densité | Lire `window.devicePixelRatio` et ajuster `canvas.width`/`canvas.height` |
| Scroll tactile | Ajouter `touch-action: none` sur le canvas pour éviter les conflits |
| Variante Wasm | `koreos-wasm` + `WasmJsWebEventLoop` — API identique, meilleure performance CPU |
