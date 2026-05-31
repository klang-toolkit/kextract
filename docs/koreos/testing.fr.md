# Tester avec Koreos

## Scripted events (`koreos-test`)

Le module `koreos-test` fournit un harnais de test déterministe pour piloter un
`ApplicationHandler` sans backend natif (AppKit, Win32, X11, navigateur…). Idéal
pour tester la logique métier d'un handler (jeu, adaptateur d'entrée, machine à
états) en `commonTest`, sans OS réel.

### Dépendance

```kotlin
// build.gradle.kts du module à tester
kotlin {
    sourceSets {
        commonTest.dependencies {
            implementation(project(":koreos-test"))
            implementation(kotlin("test"))
        }
    }
}
```

### DSL `scriptedTest { … }`

On décrit une séquence d'événements, puis on l'exécute sur un handler. La méthode
`run(handler)` retourne la **trace ordonnée** des callbacks invoqués.

```kotlin
import io.ygdrasil.koreos.test.scriptedTest
import io.ygdrasil.koreos.test.Callback
import io.ygdrasil.koreos.core.Key

val trace = scriptedTest {
    canCreateSurfaces()
    keyPress(Key.ArrowUp)
    tick(16)            // simule une frame : newEvents → RedrawRequested → aboutToWait
    keyRelease(Key.ArrowUp)
    closeRequested()
}.run(MonHandler())

assertEquals(Callback.Resumed, trace.first())
assertEquals(Callback.Suspended, trace.last())
```

### Verbes du DSL

| Verbe | Effet |
|-------|-------|
| `canCreateSurfaces()` | invoque `handler.canCreateSurfaces` |
| `keyPress(key, modifiers)` / `keyRelease(key, modifiers)` | `WindowEvent.KeyboardInput` |
| `pointerMove(x, y)` | `WindowEvent.PointerMoved` |
| `mouseInput(button, state)` | `WindowEvent.MouseInput` |
| `resized(w, h)` | `WindowEvent.Resized` |
| `scaleFactorChanged(factor)` | `WindowEvent.ScaleFactorChanged` |
| `tick(dtMs)` | une frame : `newEvents(Poll)` → `RedrawRequested` → `aboutToWait` |
| `closeRequested()` | `WindowEvent.CloseRequested` |
| `windowEvent(event)` | événement de fenêtre brut (échappatoire) |

### Cycle de vie et `exit()`

`run` invoque toujours `resumed` en premier et `suspended` en dernier. Si le
handler appelle `eventLoop.exit()` pendant le traitement d'un événement (par
exemple sur `CloseRequested`), les événements **restants sont ignorés**, mais
`suspended` est tout de même invoqué. Cela permet de tester proprement le flux de
sortie.

### Fenêtre mockée

`createWindow(...)` retourne une `ScriptedWindow` en mémoire (aucun handle natif).
Elle enregistre `requestRedraw()` (`redrawRequests`), le titre et la visibilité,
ce qui permet d'asserter le comportement du handler sans environnement graphique.

### Exemples

Voir `koreos-test/src/commonTest/.../ScriptedEventLoopTest.kt` : ordre du cycle de
vie, press/release clavier, séquence pointeur, cascade de resize, flux de sortie.
