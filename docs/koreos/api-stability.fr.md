# Stabilité de l'API publique (ABI)

Koreos est publié sur Maven Central. Pour éviter de casser silencieusement l'API
publique entre versions (changement de signature, ajout/retrait de variant `sealed`,
etc.), les **5 modules publiés** sont protégés par la validation ABI intégrée au
plugin Kotlin Gradle (Kotlin 2.2+) :

`koreos-core`, `koreos-appkit`, `koreos-uikit`, `koreos-android`, `koreos`.

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
