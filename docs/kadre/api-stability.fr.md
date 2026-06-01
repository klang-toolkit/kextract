# Stabilité de l'API publique (ABI)

Kadre est publié sur Maven Central. Pour éviter de casser silencieusement l'API
publique entre versions (changement de signature, ajout/retrait de variant `sealed`,
etc.), les **5 modules publiés** sont protégés par la validation ABI intégrée au
plugin Kotlin Gradle (Kotlin 2.2+) :

`kadre-core`, `kadre-appkit`, `kadre-uikit`, `kadre-android`, `kadre`.

## Comment ça marche

Chaque module publié active dans son `build.gradle.kts` :

```kotlin
kotlin {
    @OptIn(org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation::class)
    abiValidation { enabled.set(true) }
}
```

Le dump de référence de l'API est commité dans `<module>/api/` :

- `<module>/api/<module>.klib.api` — ABI multiplateforme (klib, toutes cibles)
- `<module>/api/jvm/<module>.api` — ABI JVM
- `<module>/api/android/<module>.api` — ABI Android

La tâche `checkKotlinAbi` est câblée dans `check` : **le build échoue** si l'API
publique courante diffère du dump commité. Contrairement à l'ancien plugin externe
`binary-compatibility-validator` (qui utilise ASM et échouait sur le bytecode JDK 25),
cette validation s'appuie sur le **compilateur Kotlin** — compatible JDK 25.

## Workflow lors d'un changement d'API

1. Modifier le code.
2. Si la CI (ou `./gradlew checkKotlinAbi`) signale une différence d'ABI :
   - **intentionnel** → régénérer le dump : `./gradlew updateKotlinAbi`
     puis commiter les fichiers `api/` modifiés dans la même PR ;
   - **non intentionnel** → corriger le code pour restaurer la compatibilité.

```bash
# Régénérer tous les dumps de référence
./gradlew updateKotlinAbi

# Vérifier (comme la CI)
./gradlew checkKotlinAbi
```

## Pour l'orchestrateur autonome

Si `checkKotlinAbi` échoue en CI, c'est qu'un changement d'API publique a été
introduit. Vérifier qu'il est intentionnel (selon le ticket), puis lancer
`./gradlew updateKotlinAbi` et commiter les `api/` modifiés avec un message
`chore(api): update ABI baseline for #ID`.

---

## Remédiation parité winit — R0–R5 (2026-06)

### R0.1 — Breaking change (types d'événements)

Deux signatures dans `ApplicationHandler` sont passées d'un type effacé `Any` à des types scellés concrets :

| Méthode | Avant | Après |
|---------|-------|-------|
| `windowEvent(eventLoop, windowId, event)` | `event: Any` | `event: WindowEvent` |
| `deviceEvent(eventLoop, deviceId, event)` | `event: Any` | `event: DeviceEvent` |

De même, `Window.rawWindowHandle` et `Window.rawDisplayHandle` sont passés de `Any` à `RawWindowHandle` et `RawDisplayHandle` respectivement. Ce sont des **breaking changes** : tout code utilisant `event as SomeType` ou `handle as SomeHandle` doit être mis à jour pour utiliser le filtrage de type sur la hiérarchie scellée.

Les dumps ABI ont été régénérés (`updateKotlinAbi`) et commités dans le cadre de R0.1 (PRs #167–#170).

### R1–R5 — Croissance additive

Tous les rounds suivants (R1 : état fenêtre/moniteurs/plein écran ; R2 : icône de fenêtre ; R3 : curseur/thème/apparence ; R4 : richesse clavier/ModifiersChanged/MouseWheel device ; R5 : DnD/gestes/curseurs custom/divers fenêtre/IME) n'ont ajouté que de **nouveaux variants scellés et de nouvelles méthodes d'interface** — aucune signature existante n'a été supprimée ou modifiée. Chaque ajout a nécessité de lancer `./gradlew updateKotlinAbi` et de commiter les fichiers `api/` mis à jour (PRs #171–#184).

Pour la liste complète des éléments volontairement reportés (implémentations no-op, événements non émis, backends natifs partiels), voir [DEFERRED.md](https://github.com/ygdrasil-io/poc-koreos/blob/master/DEFERRED.md).
