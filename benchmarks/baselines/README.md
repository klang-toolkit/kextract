# Baselines de performance

Ce dossier accueille les baselines JMH de référence (`<suite>.json`) servant à
détecter les régressions de performance entre versions.

## Workflow

1. Lancer la suite : `./gradlew :benchmarks:jmh-core:jmh`
2. Résultats : `benchmarks/jmh-core/build/results/jmh/results.json`
3. Pour figer une baseline : copier ce fichier ici sous un nom versionné
   (ex. `jmh-core-v1.0.0.json`) et le commiter.

## Comparaison automatique (point ouvert #90)

La comparaison baseline ↔ run courant avec alerte si régression > 10 % n'est pas
encore câblée dans la CI : les machines partagées GitHub Actions sont trop bruitées
pour un seuil fiable sans exécutions répétées / médianes. Le job `bench-perf`
publie pour l'instant le JSON en artefact pour inspection manuelle. Une
comparaison robuste (médiane sur N runs, intervalle de confiance) est un suivi.
