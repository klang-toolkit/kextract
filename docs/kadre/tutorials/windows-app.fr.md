# Tutoriel : intégrer Kadre dans une application Windows

Ce tutoriel vous guide pas-à-pas pour créer une fenêtre native Windows avec Kadre sur JVM 25. Vous obtiendrez une fenêtre opérationnelle qui répond aux événements souris et clavier et se ferme proprement.

**Prérequis** : JDK 25, Gradle 9+, Windows 10 21H1 ou supérieur.

---

## Étape 1 — Configurer `build.gradle.kts`

Créez (ou adaptez) votre fichier `build.gradle.kts` avec la dépendance Kadre et un `JavaExec` task configuré pour Panama FFM :

```kotlin
plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.3.21"
}

kotlin {
    jvmToolchain(25)      // JVM 25 obligatoire — Panama FFM (JEP 454)

    jvm()

    sourceSets {
        jvmMain {
            dependencies {
                // Façade publique Kadre — routage automatique macOS/Windows
                implementation("org.graphiks.kadre:kadre:1.0.0")
            }
        }
    }
}

// Tâche d'exécution JVM
tasks.register<JavaExec>("run") {
    group = "application"
    dependsOn("jvmJar")
    mainClass.set("com.example.myapp.MainKt")
    classpath = files(
        kotlin.targets.getByName("jvm").compilations.getByName("main").output.allOutputs,
        configurations.getByName("jvmRuntimeClasspath"),
    )
    jvmArgs(
        // Ouvre l'accès aux API natives non nommées (Panama FFM)
        "--enable-native-access=ALL-UNNAMED",
    )
}
```

!!! warning "JVM 25 obligatoire"
    Kadre utilise la Foreign Function & Memory API (Panama, JEP 454), finalisée dans JDK 25.
    Toute version inférieure lève `java.lang.reflect.InaccessibleObjectException` au démarrage.

---

## Étape 2 — Implémenter `ApplicationHandler`

`ApplicationHandler` est l'interface centrale : Kadre l'appelle pour chaque événement du cycle de vie et de la fenêtre.

```kotlin
package com.example.myapp

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.Window
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.WindowEvent

class MyAppHandler : ApplicationHandler {

    private var window: Window? = null

    // Appelé quand la surface peut être créée (équivalent WM_CREATE)
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        window = eventLoop.createWindow(
            WindowAttributes(
                title = "Mon application Windows — Kadre",
                resizable = true,
            )
        )
    }

    // Appelé pour chaque événement de fenêtre
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        when (event) {
            // Dessin : déclenché par WM_PAINT / InvalidateRect
            WindowEvent.RedrawRequested -> {
                // Placez ici l'appel à votre renderer (wgpu4k, Direct3D, etc.)
            }

            // Fermeture : l'utilisateur a cliqué sur la croix (WM_CLOSE)
            WindowEvent.CloseRequested -> {
                eventLoop.exit()   // PostQuitMessage(0) → WM_QUIT
            }

            // Redimensionnement (WM_SIZE)
            is WindowEvent.Resized ->
                println("Resized → ${event.size.width}×${event.size.height}")

            // Changement de DPI (WM_DPICHANGED) — voir Étape 4
            is WindowEvent.ScaleFactorChanged ->
                println("DPI scale factor → ${event.factor}")

            // Entrées souris
            is WindowEvent.MouseInput ->
                println("MouseInput ${event.state} button=${event.button}")

            is WindowEvent.PointerMoved ->
                println("PointerMoved (${event.position.x.toInt()}, ${event.position.y.toInt()})")

            // Entrées clavier
            is WindowEvent.KeyboardInput ->
                println("Key ${event.state} key=${event.key} repeat=${event.isRepeat}")

            // Fenêtre détruite : libérer les ressources GPU ici
            WindowEvent.Destroyed -> window = null

            else -> Unit
        }
    }

    override fun resumed(eventLoop: ActiveEventLoop) = Unit
    override fun suspended(eventLoop: ActiveEventLoop) = Unit

    override fun destroySurfaces(eventLoop: ActiveEventLoop) {
        // Libérer les ressources GPU avant la destruction des surfaces
        window = null
    }
}
```

---

## Étape 3 — Point d'entrée `main()`

```kotlin
package com.example.myapp

import org.graphiks.kadre.EventLoop

fun main() {
    // EventLoop détecte l'OS à l'exécution et charge le backend Win32
    EventLoop().runApp(MyAppHandler())
}
```

La classe `EventLoop` de la façade Kadre détecte automatiquement le système d'exploitation via `System.getProperty("os.name")` et délègue au backend Win32 (`org.graphiks.kadre.win32.Win32EventLoopKt.runApp`) par réflexion. Aucun import plateforme-spécifique n'est nécessaire dans votre code.

---

## Étape 4 — Lancer l'application

```bash
./gradlew run
```

Vous pouvez aussi lancer depuis la ligne de commande avec le JAR assemblé :

```bash
java --enable-native-access=ALL-UNNAMED \
     -cp "build/libs/myapp.jar;build/libs/*" \
     com.example.myapp.MainKt
```

!!! note "DPI haute résolution — PerMonitorV2"
    Kadre configure automatiquement `SetProcessDpiAwarenessContext(DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2)`
    au démarrage du backend Win32. Vous n'avez rien à faire dans le manifeste de l'application.

    Quand la fenêtre est déplacée vers un écran de résolution différente, Windows envoie `WM_DPICHANGED`.
    Kadre le traduit en `WindowEvent.ScaleFactorChanged(factor)` où `factor` est le nouveau ratio
    (ex. `1.5` pour un affichage à 144 DPI). Relancez votre renderer avec la nouvelle taille physique
    à ce moment.

---

## Étape 5 (optionnel) — Packager avec `jpackage`

Pour distribuer un installateur Windows autonome (`.msi` ou `.exe`), utilisez `jpackage` (inclus dans JDK 14+) :

```bash
jpackage \
  --type msi \
  --name "MonAppKadre" \
  --app-version "1.0.0" \
  --input build/libs \
  --main-jar myapp.jar \
  --main-class com.example.myapp.MainKt \
  --java-options "--enable-native-access=ALL-UNNAMED" \
  --win-dir-chooser \
  --win-shortcut
```

!!! tip "Icône et métadonnées"
    Ajoutez `--icon myapp.ico` (format `.ico` requis sur Windows) et `--win-menu` pour créer
    une entrée dans le menu Démarrer.

---

## Récapitulatif des points clés

| Point | Détail |
|-------|--------|
| JVM minimum | **25** — Panama FFM (JEP 454) |
| Flag JVM requis | `--enable-native-access=ALL-UNNAMED` |
| DPI awareness | Automatique — `PerMonitorV2` (pas de manifeste requis) |
| Événement DPI | `WindowEvent.ScaleFactorChanged(factor)` déclenché sur `WM_DPICHANGED` |
| Fermeture propre | `eventLoop.exit()` dans `CloseRequested` → `PostQuitMessage(0)` |
| Packaging | `jpackage` avec `--java-options "--enable-native-access=ALL-UNNAMED"` |
