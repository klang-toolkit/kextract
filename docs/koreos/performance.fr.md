# Performance & instrumentation

## Benchmarks JMH (`benchmarks/jmh-core`)

Suite [JMH](https://github.com/openjdk/jmh) mesurant les composants purs appelés à
chaque frame. JVM-only, exécutée via :

```bash
./gradlew :benchmarks:jmh-core:jmh
```

Les résultats (JSON) sont écrits dans
`benchmarks/jmh-core/build/results/jmh/results.json`.

### Couverture

| Benchmark | Composant |
|-----------|-----------|
| `tickPlayerUp`, `tickIdle`, `tickBothMoving`, `tick64Frames` | `GameState.tick` (physique 2D) |
| `aiSuggestUpdate`, `aiSuggestNoUpdate` | `PongAi.suggest` |
| `renderDigit`, `renderNumberTwoDigits`, `renderNumberFiveDigits` | `BitmapFont` |
| `inputOnKeyPress`, `inputOnKeyPressRelease` | `InputAdapter.onKey` |

> Les mappers Win32/X11 sont `internal` (non accessibles depuis le module de
> benchmark) ; ils sont couverts par leurs tests unitaires de module.

### CI

Le job `bench-perf` (`.github/workflows/bench-perf.yml`) lance la suite sur push
master et publie le JSON en artefact. La comparaison automatique à une baseline
(seuil de régression) est un point ouvert — voir `benchmarks/baselines/README.md`.

## FrameTimingTracer (runtime)

`io.ygdrasil.koreos.core.FrameTimingTracer` mesure la durée
`RedrawRequested → fin de présentation` de chaque frame et publie, ~1×/seconde,
des statistiques `min/p50/p99/max` et le FPS approximatif.

```kotlin
FrameTimingTracer.enabled = true            // désactivé par défaut → 0 overhead
// dans la boucle de rendu :
FrameTimingTracer.onRedrawStart()
// … rendu de la frame …
FrameTimingTracer.onPresentEnd()
```

- **0 % d'overhead quand désactivé** : toutes les méthodes retournent immédiatement
  (aucune lecture d'horloge, aucune allocation) tant que `enabled == false`.
- Horloge multiplateforme (`kotlin.time.TimeSource.Monotonic`).
- `slowFrameThresholdMs` : journalise individuellement les frames dépassant le seuil.
- `sink` : redirige les lignes de log (défaut `println`), surchargeable en test.

Sur JVM, on peut conditionner l'activation à `-Dkoreos.tracing=true` au démarrage du
backend (lecture de la system property → `FrameTimingTracer.enabled = true`).
