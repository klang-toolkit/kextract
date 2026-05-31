# Tutoriel : intégrer Koreos dans une application Linux

Ce tutoriel vous guide pas-à-pas pour créer une fenêtre native Linux avec Koreos sur JVM 25. Vous obtiendrez une fenêtre opérationnelle sur X11 ou Wayland qui répond aux événements souris et clavier et se ferme proprement.

**Prérequis** : JDK 25, Gradle 9+, Linux (Debian/Ubuntu 22.04+ recommandé, ou toute distribution avec Wayland/X11).

---

## Prérequis système — bibliothèques natives

Koreos utilise Vulkan pour le rendu bas-niveau et les protocoles X11/Wayland pour le gestionnaire de fenêtres. Installez les en-têtes de développement avant de compiler :

```bash
# Debian / Ubuntu / Linux Mint
sudo apt install \
    libvulkan-dev \
    libwayland-dev \
    libx11-dev \
    libxkbcommon-dev
```

```bash
# Fedora / RHEL / CentOS Stream
sudo dnf install \
    vulkan-loader-devel \
    wayland-devel \
    libX11-devel \
    libxkbcommon-devel
```

```bash
# Arch Linux / Manjaro
sudo pacman -S \
    vulkan-headers \
    wayland \
    libx11 \
    libxkbcommon
```

!!! note "Pilote Vulkan"
    Les paquets ci-dessus installent les en-têtes et le *loader* Vulkan, mais pas le pilote ICD spécifique à votre GPU.
    Installez également le pilote correspondant à votre matériel :

    | GPU | Paquet (Debian/Ubuntu) |
    |-----|------------------------|
    | Intel | `mesa-vulkan-drivers` |
    | AMD | `mesa-vulkan-drivers` |
    | NVIDIA propriétaire | `nvidia-driver` (inclut le pilote Vulkan) |
    | NVIDIA open | `libnvidia-gl-<version>` |

    Vérifiez la disponibilité Vulkan avec `vulkaninfo --summary` (paquet `vulkan-tools`).

---

## Étape 1 — Configurer `build.gradle.kts`

Créez (ou adaptez) votre fichier `build.gradle.kts` avec la dépendance Koreos et un `JavaExec` task configuré pour Panama FFM :

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
                // Façade publique Koreos — routage automatique Linux/Windows/macOS
                implementation("io.ygdrasil.koreos:koreos:0.1.1")
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
    Koreos utilise la Foreign Function & Memory API (Panama, JEP 454), finalisée dans JDK 25.
    Toute version inférieure lève `java.lang.reflect.InaccessibleObjectException` au démarrage.

---

## Étape 2 — Implémenter `ApplicationHandler`

`ApplicationHandler` est l'interface centrale : Koreos l'appelle pour chaque événement du cycle de vie et de la fenêtre. L'implémentation est identique entre X11 et Wayland — la détection du backend est transparente.

```kotlin
package com.example.myapp

import io.ygdrasil.koreos.ActiveEventLoop
import io.ygdrasil.koreos.ApplicationHandler
import io.ygdrasil.koreos.Window
import io.ygdrasil.koreos.WindowAttributes
import io.ygdrasil.koreos.WindowId
import io.ygdrasil.koreos.WindowEvent

class MyAppHandler : ApplicationHandler {

    private var window: Window? = null

    // Appelé quand la surface peut être créée — la connexion X11/Wayland est établie
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        window = eventLoop.createWindow(
            WindowAttributes(
                title = "Mon application Linux — Koreos",
                resizable = true,
            )
        )
    }

    // Appelé pour chaque événement de fenêtre
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        when (event) {
            // Dessin : déclenché lors de l'exposition de la fenêtre (Expose / frame callback)
            WindowEvent.RedrawRequested -> {
                // Placez ici l'appel à votre renderer (wgpu4k, Vulkan, OpenGL, etc.)
            }

            // Fermeture : l'utilisateur a cliqué sur la croix ou envoyé WM_DELETE_WINDOW
            WindowEvent.CloseRequested -> {
                eventLoop.exit()   // Termine proprement la boucle d'événements
            }

            // Redimensionnement
            is WindowEvent.Resized ->
                println("Resized → ${event.size.width}×${event.size.height}")

            // Changement de DPI (déplacement vers un écran HiDPI, ou modification de l'échelle)
            is WindowEvent.ScaleFactorChanged ->
                println("Scale factor → ${event.factor}")

            // Entrées souris
            is WindowEvent.MouseInput ->
                println("MouseInput ${event.state} button=${event.button}")

            is WindowEvent.PointerMoved ->
                println("PointerMoved (${event.position.x.toInt()}, ${event.position.y.toInt()})")

            // Entrées clavier (via libxkbcommon sur X11 et Wayland)
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

import io.ygdrasil.koreos.EventLoop

fun main() {
    // EventLoop détecte l'OS à l'exécution et charge le backend Linux (X11 ou Wayland)
    EventLoop().runApp(MyAppHandler())
}
```

La classe `EventLoop` de la façade Koreos détecte automatiquement le système d'exploitation via `System.getProperty("os.name")` et délègue au backend Linux par réflexion. À l'intérieur du backend Linux, Koreos choisit ensuite entre X11 et Wayland selon les variables d'environnement présentes (voir la section [Choix X11 vs Wayland](#choix-x11-vs-wayland) ci-dessous). Aucun import plateforme-spécifique n'est nécessaire dans votre code.

---

## Étape 4 — Lancer l'application

```bash
./gradlew run
```

Vous pouvez aussi lancer depuis la ligne de commande avec le JAR assemblé :

```bash
java --enable-native-access=ALL-UNNAMED \
     -cp "build/libs/myapp.jar:build/libs/*" \
     com.example.myapp.MainKt
```

!!! note "Séparateur de classpath Linux"
    Sur Linux (et macOS), le séparateur de classpath est `:` et non `;` comme sous Windows.
    Utilisez `build/libs/myapp.jar:build/libs/*` (deux-points) dans la commande `java`.

---

## Choix X11 vs Wayland

Koreos inspecte les variables d'environnement dans cet ordre de priorité au démarrage du backend Linux :

| Priorité | Condition | Backend sélectionné |
|----------|-----------|---------------------|
| 1 | `KOREOS_LINUX_BACKEND=wayland` | Wayland (forcé) |
| 2 | `KOREOS_LINUX_BACKEND=x11` | X11 (forcé) |
| 3 | `WAYLAND_DISPLAY` définie et non vide | Wayland (auto-détection) |
| 4 | `DISPLAY` définie et non vide | X11 (auto-détection) |
| 5 | Aucune variable trouvée | Erreur au démarrage |

### Auto-détection (comportement par défaut)

Dans un environnement de bureau Wayland moderne (GNOME 45+, KDE Plasma 6, Sway), `WAYLAND_DISPLAY` est définie par le compositeur. Koreos sélectionne alors Wayland automatiquement. Dans une session X11 pure ou dans un terminal XWayland, seule `DISPLAY` est présente, et Koreos bascule sur X11.

### Forcer un backend spécifique

```bash
# Forcer Wayland (utile pour tester la compatibilité Wayland dans une session mixte)
KOREOS_LINUX_BACKEND=wayland ./gradlew run

# Forcer X11 (utile sous Wayland via XWayland, pour la compatibilité legacy)
KOREOS_LINUX_BACKEND=x11 ./gradlew run
```

!!! warning "XWayland n'est pas Wayland natif"
    Si vous forcez `KOREOS_LINUX_BACKEND=x11` dans une session Wayland, Koreos utilise
    XWayland comme pont. Les fonctionnalités Wayland natives (decorations serveur,
    fractional scaling) ne sont alors pas disponibles. Préférez le backend Wayland natif
    dès que possible pour une meilleure intégration desktop.

---

## Particularités Linux à connaître

### DPI et mise à l'échelle

Le support DPI varie selon le backend :

**Wayland — fractional scaling :**
Wayland expose le facteur d'échelle de l'écran via le protocole `wl_output` (entier depuis Wayland 1.x) et `wp_fractional_scale_v1` pour les valeurs fractionnaires (ex. `1.25×`, `1.5×`). Koreos traduit ces valeurs en `WindowEvent.ScaleFactorChanged(factor)`.

**X11 — heuristique Xft.dpi :**
X11 n'a pas de mécanisme DPI standardisé. Koreos lit la ressource X `Xft.dpi` via `XGetDefault(display, "Xft", "dpi")`. Cette ressource est définie par GNOME, KDE et la plupart des gestionnaires de fenêtres. Si elle est absente, Koreos se rabat sur le DPI physique calculé depuis `DisplayWidth` / `DisplayWidthMM`, ou sur une valeur par défaut de 96 DPI.

```bash
# Vérifier la valeur Xft.dpi courante
xrdb -query | grep dpi
# Ou :
xdpyinfo | grep resolution
```

!!! tip "Rendu net sur écran HiDPI sous X11"
    Si votre application semble floue sur un écran 4K en session X11, vérifiez que
    `Xft.dpi` est bien configuré dans `~/.Xresources` :

    ```
    Xft.dpi: 192
    ```

    Puis rechargez avec `xrdb -merge ~/.Xresources` et relancez l'application.

### Décorations de fenêtre (Wayland)

Sous Wayland, les décorations (barre de titre, boutons fermer/réduire/agrandir) peuvent être gérées par le compositeur (*server-side decorations*, SSD) ou par l'application (*client-side decorations*, CSD). Le protocole `xdg-decoration-unstable-v1` permet de négocier le mode.

Koreos demande les *server-side decorations* en priorité. Si le compositeur ne supporte pas `xdg-decoration` (ex. Sway sans `xwayland`), Koreos repasse automatiquement en mode CSD avec des décorations dessinées côté client.

```bash
# Vérifier si votre compositeur supporte xdg-decoration
wayland-info | grep xdg_decoration
# Ou avec weston-info :
weston-info | grep xdg_decoration
```

!!! note "Pas de barre de titre sous Sway ?"
    Sway (et i3-like sous Wayland) gère les fenêtres en mode tiling et supprime
    les décorations par défaut. C'est un comportement attendu du gestionnaire de fenêtres,
    non un bug Koreos. Utilisez les raccourcis Sway (`$mod+Shift+q`, etc.) pour contrôler
    la fenêtre.

### Fermeture propre

Toujours appeler `eventLoop.exit()` dans le handler `CloseRequested` pour garantir une fermeture nette :

```kotlin
WindowEvent.CloseRequested -> {
    // Libérez vos ressources GPU ici si nécessaire avant de quitter
    eventLoop.exit()
}
```

Sans cet appel, la fenêtre se ferme visuellement mais le processus JVM reste suspendu en attente d'événements. Sous Wayland, cela peut laisser le socket `wl_display` ouvert et bloquer le compositeur.

---

## CI Linux — tests automatisés sans affichage

Les environnements CI (GitHub Actions, GitLab CI) n'ont pas de serveur graphique. Deux approches permettent de lancer les tests Koreos en mode *headless* :

### Approche 1 — Xvfb (X11 virtuel)

`Xvfb` (*X Virtual Framebuffer*) émule un serveur X11 en mémoire sans affichage physique. C'est la solution la plus simple et la plus compatible.

```yaml
# .github/workflows/ci.yml
jobs:
  linux-tests:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Install dependencies
        run: |
          sudo apt-get update
          sudo apt-get install -y \
            libvulkan-dev libwayland-dev libx11-dev libxkbcommon-dev \
            xvfb vulkan-tools mesa-vulkan-drivers

      - name: Setup JDK 25
        uses: actions/setup-java@v4
        with:
          java-version: '25'
          distribution: 'temurin'

      - name: Run tests with Xvfb
        run: |
          # Démarre Xvfb sur le display :99, 24-bit color
          Xvfb :99 -screen 0 1280x720x24 &
          export DISPLAY=:99
          # Force le backend X11 (Xvfb ne supporte pas Wayland)
          export KOREOS_LINUX_BACKEND=x11
          ./gradlew :koreos-core:jvmTest :koreos:jvmTest
```

!!! tip "Attendre que Xvfb soit prêt"
    Sur des CI à faible CPU, Xvfb peut mettre quelques instants à démarrer.
    Utilisez `xdpyinfo -display :99 > /dev/null 2>&1` en boucle pour attendre
    qu'il soit opérationnel avant de lancer les tests :

    ```bash
    Xvfb :99 -screen 0 1280x720x24 &
    until xdpyinfo -display :99 > /dev/null 2>&1; do sleep 0.1; done
    export DISPLAY=:99
    ```

### Approche 2 — Weston headless (Wayland virtuel)

`weston` est le compositeur de référence Wayland. Son backend `headless` crée un compositeur sans affichage physique, ce qui permet de tester le backend Wayland natif de Koreos en CI.

```yaml
# .github/workflows/ci.yml
jobs:
  linux-wayland-tests:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Install dependencies
        run: |
          sudo apt-get update
          sudo apt-get install -y \
            libvulkan-dev libwayland-dev libx11-dev libxkbcommon-dev \
            weston vulkan-tools mesa-vulkan-drivers

      - name: Setup JDK 25
        uses: actions/setup-java@v4
        with:
          java-version: '25'
          distribution: 'temurin'

      - name: Run tests with Weston headless
        run: |
          # Démarre Weston en mode headless sur un socket Wayland dédié
          weston --backend=headless-backend.so \
                 --socket=weston-test \
                 --width=1280 --height=720 &
          export WAYLAND_DISPLAY=weston-test
          export KOREOS_LINUX_BACKEND=wayland
          # Attendre que Weston soit prêt
          until [ -S "$XDG_RUNTIME_DIR/$WAYLAND_DISPLAY" ]; do sleep 0.1; done
          ./gradlew :koreos-core:jvmTest :koreos:jvmTest
```

!!! note "XDG_RUNTIME_DIR"
    Wayland stocke ses sockets dans `$XDG_RUNTIME_DIR` (typiquement `/run/user/<uid>`).
    Sur les runners GitHub Actions, ce répertoire est défini automatiquement.
    Si ce n'est pas le cas, exportez-le manuellement : `export XDG_RUNTIME_DIR=/tmp/runtime-$(id -u)`.

### Recommandation CI

| Critère | Xvfb (X11) | Weston headless (Wayland) |
|---------|------------|---------------------------|
| Simplicité de configuration | Excellente | Moyenne |
| Tests du backend Wayland natif | Non | Oui |
| Support sur runners cloud | Universel | Bonne (Ubuntu 22.04+) |
| Overhead CPU/mémoire | Minimal | Léger |

Pour la majorité des projets, **Xvfb** est le choix le plus pragmatique. Ajoutez **Weston headless** en job séparé si vous souhaitez valider spécifiquement le backend Wayland.

---

## Récapitulatif des points clés

| Point | Détail |
|-------|--------|
| JVM minimum | **25** — Panama FFM (JEP 454) |
| Flag JVM requis | `--enable-native-access=ALL-UNNAMED` |
| Séparateur classpath | `:` sur Linux (et non `;`) |
| Backend auto-détecté | Wayland si `WAYLAND_DISPLAY` définie, sinon X11 |
| Forcer un backend | `KOREOS_LINUX_BACKEND=wayland` ou `=x11` |
| DPI Wayland | Via `wl_output` / `wp_fractional_scale_v1` → `ScaleFactorChanged` |
| DPI X11 | Heuristique `Xft.dpi` → ressource X ou DPI physique calculé |
| Décorations Wayland | SSD demandées en priorité, CSD en fallback |
| Fermeture propre | `eventLoop.exit()` dans `CloseRequested` — ferme le socket Wayland |
| CI headless X11 | `Xvfb :99 -screen 0 1280x720x24` + `DISPLAY=:99` |
| CI headless Wayland | `weston --backend=headless-backend.so` + `WAYLAND_DISPLAY=weston-test` |
