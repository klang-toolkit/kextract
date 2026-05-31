# Tests de régression visuelle (screenshot diff)

Redmine #88 — équivalent Roborazzi, cross-plateforme. Compare une capture d'écran
d'un sample à une **baseline** committée, avec une tolérance en % de pixels
différents. Complète le smoke E2E (#22, « au moins une frame présentée ») en
détectant les régressions visuelles subtiles (couleur, position, antialiasing).

## Stratégie de capture : readback GPU, pas capture d'écran

La capture d'écran système (ScreenCaptureKit / `screencapture`, `CGWindowListCreateImage`,
etc.) est **inadaptée** en CI : elle exige un display + la permission TCC « Screen
Recording » (indisponibles sur les runners headless), et `CGWindowListCreateImage`
est de surcroît **supprimée sur macOS 26**. Les outils d'instrumentation UI
(Roborazzi/Paparazzi, XCUITest) snapshotent une **hiérarchie de vues**, pas une
**surface GPU** — donc inutilisables pour un sample qui rend directement via wgpu.

La méthode retenue est le **readback du framebuffer** via l'API graphique : le sample
rend une frame dans une **texture offscreen**, la copie vers un buffer
(`copyTextureToBuffer`), mappe et lit les octets, puis écrit un PNG. C'est
**déterministe**, **sans fenêtre ni permission**, et **identique en CI et en local**.
Comme wgpu4k est multiplateforme, ce chemin est commun à toutes les cibles ; seul
l'encodage PNG diffère par plateforme.

## État par plateforme

| Plateforme | Capture | Automatisé en CI |
|------------|---------|------------------|
| **Web** (JS) | Playwright `page.screenshot()` | ✅ oui (informatif, non bloquant) |
| **macOS** | **readback GPU** (`hello-triangle --capture`) | ✅ oui — job `macos-visual` (informatif, non bloquant) |
| **iOS** | readback GPU (Kotlin/Native, wgpu4k Metal) | ⚠️ implémenté — **simulateur headless sans Metal** |
| **Android** | readback GPU (wgpu4k Vulkan, émulateur) | ✅ oui — job `android-visual` (émulateur SwiftShader, non bloquant) |
| Windows | readback GPU (même code wgpu, PNG via ImageIO) | 🟡 à brancher — runner Windows GPU |
| Linux X11/Wayland | readback GPU (même code wgpu) | 🟡 à brancher |

> **Web** et **macOS** sont exécutés en CI. Les autres plateformes réutiliseront le
> même readback GPU (code wgpu commun) ; seul un runner avec GPU/émulateur par cible
> est nécessaire pour les activer.

## macOS — readback GPU (`hello-triangle --capture`)

```bash
./gradlew :samples:hello-triangle:run --args="--capture out.png"
```

Rend le triangle dans une texture offscreen `RGBA8Unorm` (aucune fenêtre ouverte —
un `CAMetalLayer` offscreen est créé uniquement pour satisfaire `requestAdapter`,
wgpu4k 0.1.1 ne supportant pas encore l'adapter sans surface), lit le framebuffer
par readback et écrit le PNG (`ImageIO`). Le job CI `macos-visual` compare ce PNG à
`tests/visual/baselines/macos/hello-triangle.png` via `tests/visual/diff-cli.js`
(pixelmatch, tolérance 2 %), **non bloquant** : verdict dans le Job Summary + diff
archivé.

### Mettre à jour la baseline macOS

```bash
./gradlew :samples:hello-triangle:run --args="--capture tests/visual/baselines/macos/hello-triangle.png"
git add tests/visual/baselines/macos/hello-triangle.png
```

## Tranche Web (implémentée)

Le test `samples/hello-triangle-web/e2e/tests/visual.spec.js` capture le canvas
WebGPU et le compare à `e2e/baselines/hello-triangle-web.png` via
[pixelmatch](https://github.com/mapbox/pixelmatch) (helper
`visual/assert-screenshot.js`, `assertScreenshotMatches(actualPng, baselinePath,
{ tolerance })`, défaut **2 %**).

### Non bloquant

Le rendu WebGPU **SwiftShader** headless peut varier légèrement entre
environnements ; le test est donc **informatif** : il journalise le ratio de diff et
archive l'image de diff en artefact CI (`hello-triangle-web-visual-diff`), mais
**n'échoue jamais le build**. Cela évite un gate flaky tout en rendant les
régressions visibles en revue.

### Mettre à jour la baseline

Quand un changement visuel est **légitime** (humain only — jamais auto) :

```bash
cd samples/hello-triangle-web/e2e
npm run update-baselines          # supprime + régénère baselines/*.png
git add baselines/*.png           # commiter la nouvelle baseline
```

## Ajouter un sample / une plateforme

1. Réutiliser `assertScreenshotMatches(actualPng, baselinePath, { tolerance, diffPath })`.
2. Fournir un provider de capture pour la plateforme (cf. tableau).
3. Stocker la baseline sous `baselines/<plateforme>/<sample>.png` (git-lfs si > 5 Mo cumulés).

## iOS — readback GPU (`samples/hello-triangle-ios`, best-effort)

Capture **Kotlin/Native** : `captureTriangle()` (iosMain) crée une `CAMetalLayer`
offscreen, obtient une surface wgpu4k Metal, rend le triangle dans une texture, relit
le framebuffer (`copyTextureToBuffer` + `mapAsync` + lecture via `CPointer`) et retourne
les octets RGBA. Exécuté par `iosSimulatorArm64Test` (job CI `ios-visual`, non bloquant).

**Limitation** : le **simulateur iOS headless** (CI et harnais de test K/N) n'expose
**pas de device Metal** (`MTLCreateSystemDefaultDevice() == null`) — contrairement à
Linux, il n'existe pas de Metal logiciel. Le test se saute alors proprement. Le rendu
réel du triangle nécessite un **device iOS physique** (`iosArm64`) ou un simulateur avec
Metal (Simulator.app GUI). Le job CI garantit néanmoins que le code de capture iOS
**compile et link**.

## Android — readback GPU (`samples/hello-triangle-android-capture`)

Test instrumenté (`connectedDebugAndroidTest`) : `captureTriangle()` crée une `Surface`
adossée à une `SurfaceTexture` (offscreen), obtient l'`ANativeWindow` via
`io.ygdrasil.nativeHelper.Helper.nativeWindowFromSurface` (android-native-helper),
crée une surface wgpu4k Vulkan, rend le triangle dans une texture offscreen et relit le
framebuffer (`copyTextureToBuffer` + `mapAsync` + `mapInto`). Le test vérifie la présence
des régions R/G/B (triangle rendu).

Job CI `android-visual` : émulateur API 34 (`reactivecircus/android-emulator-runner`)
avec **Vulkan logiciel SwiftShader** (`-gpu swiftshader_indirect`), KVM activé. **Non
bloquant**. Vérifié localement (émulateur API 36) : test vert, triangle rendu.
