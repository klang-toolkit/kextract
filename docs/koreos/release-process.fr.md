# Koreos — Release Process

Procédure pas-à-pas pour publier une release sur Maven Central.

## Prérequis

### 1. Compte Sonatype Maven Central

Créer un compte sur [central.sonatype.com](https://central.sonatype.com) et enregistrer le
namespace `io.ygdrasil.koreos` (vérification DNS ou SCM requise).

### 2. Clé GPG

```bash
# Générer une paire de clés (si absente)
gpg --full-generate-key          # RSA 4096, email contact@ygdrasil.io

# Exporter la clé secrète en ASCII (pour les vars d'env CI)
gpg --export-secret-keys --armor <KEY_ID> | base64 > signing-key.b64

# Publier la clé publique (requis Maven Central)
gpg --keyserver keyserver.ubuntu.com --send-keys <KEY_ID>
```

### 3. Variables de configuration

Ajouter dans `~/.gradle/gradle.properties` (jamais dans le dépôt) :

```properties
ossrhUsername=<votre-token-sonatype>
ossrhPassword=<votre-token-sonatype-secret>
signingKey=<contenu de signing-key.b64>
signingPassword=<passphrase-gpg>
```

Pour CI GitHub Actions, ajouter les secrets correspondants dans les
*Repository secrets* (`Settings → Secrets and variables → Actions`) :

| Secret GitHub          | Propriété Gradle         |
|------------------------|--------------------------|
| `OSSRH_USERNAME`       | `ossrhUsername`          |
| `OSSRH_PASSWORD`       | `ossrhPassword`          |
| `SIGNING_KEY`          | `signingKey`             |
| `SIGNING_PASSWORD`     | `signingPassword`        |

---

## Workflow de release

### Étape 1 — Préparer la version

```bash
# 1a. Changer version=0.1.0-SNAPSHOT → version=0.1.0 dans gradle.properties
sed -i '' 's/^version=.*/version=0.1.0/' gradle.properties

# 1b. Vérifier le CHANGELOG / les notes de release

# 1c. Créer un commit de release
git add gradle.properties
git commit -m "chore: bump version to 0.1.0"
git tag v0.1.0
```

### Étape 2 — Vérification locale

```bash
# Compiler tous les modules publiés
./gradlew :koreos-core:build :koreos:build :koreos-appkit:build \
          :koreos-android:build :koreos-uikit:build

# Publier localement et inspecter les artefacts
./gradlew :koreos-core:publishToMavenLocal \
          :koreos:publishToMavenLocal \
          :koreos-android:publishToMavenLocal \
          :koreos-appkit:publishToMavenLocal \
          :koreos-uikit:publishToMavenLocal

# Vérifier les artefacts dans ~/.m2
ls ~/.m2/repository/io/ygdrasil/koreos/koreos-core/0.1.0/
# Attendu : koreos-core-0.1.0.jar
#           koreos-core-0.1.0-sources.jar
#           koreos-core-0.1.0-javadoc.jar
#           koreos-core-0.1.0.pom          (signé : .asc)
#           koreos-core-0.1.0.module       (signé : .asc)
```

### Étape 3 — Publication Maven Central

```bash
# Publier les 5 modules vers Maven Central
./gradlew :koreos-core:publishKotlinMultiplatformPublicationToMavenCentral \
          :koreos:publishKotlinMultiplatformPublicationToMavenCentral \
          :koreos-android:publishKotlinMultiplatformPublicationToMavenCentral \
          :koreos-appkit:publishJvmPublicationToMavenCentral \
          :koreos-uikit:publishKotlinMultiplatformPublicationToMavenCentral

# Ou globalement (attention : publie aussi les samples si mal filtré)
./gradlew publishToMavenCentral
```

> **Note Maven Central Portal API** : le dépôt cible (`mavenCentral`) utilise l'API
> Portal (`https://central.sonatype.com/api/v1/publisher/upload`). Les artefacts
> sont uploadés dans une "staging deployment" puis promus manuellement (ou auto)
> via l'interface [central.sonatype.com](https://central.sonatype.com/publishing).

### Étape 4 — Promotion (Maven Central Portal)

1. Aller sur [central.sonatype.com/publishing](https://central.sonatype.com/publishing)
2. Vérifier le déploiement en attente (validation automatique : POM, signatures, sources)
3. Cliquer **Publish** → propagation dans les miroirs Maven Central (~10–30 min)

### Étape 5 — Post-release

```bash
# Remettre en SNAPSHOT pour le prochain cycle
sed -i '' 's/^version=.*/version=0.2.0-SNAPSHOT/' gradle.properties
git add gradle.properties
git commit -m "chore: bump version to 0.2.0-SNAPSHOT"
git push origin master --tags
```

---

## Modules publiés

| Module           | Artifact ID       | Description                           |
|------------------|-------------------|---------------------------------------|
| `koreos-core`    | `koreos-core`     | Interfaces KMP pures (commonMain)     |
| `koreos`         | `koreos`          | Façade publique KMP (jvm + iOS + android) |
| `koreos-appkit`  | `koreos-appkit`   | Backend macOS (AppKit, JVM 25, FFM)   |
| `koreos-uikit`   | `koreos-uikit`    | Backend iOS (UIKit, Kotlin/Native)    |
| `koreos-android` | `koreos-android`  | Backend Android (SurfaceView)         |

GroupId : `io.ygdrasil.koreos`

---

## Dépannage

### Signature absente / invalide

```
Publication 'X' is not signed
```
Vérifier que `signingKey` et `signingPassword` sont définis dans
`~/.gradle/gradle.properties` ou les variables d'environnement CI.

### Artefact rejeté (POM incomplet)

Maven Central exige : `name`, `description`, `url`, `licenses`, `developers`, `scm`.
Ces champs sont tous configurés dans `buildSrc/.../kmp-publish.gradle.kts`.

### koreos-android : publications iOS désactivées

`koreos-android` utilise le plugin `kmp-library` qui ajoute des cibles iOS, mais
ce module ne contient que du code Android. Les publications iOS/JVM sont
intentionnellement désactivées via `afterEvaluate` dans `koreos-android/build.gradle.kts`.

---

## Références

- [Maven Central Publishing Guide](https://central.sonatype.org/publish/publish-guide/)
- [Kotlin KMP Maven Central Setup](https://kotlinlang.org/docs/multiplatform-publish-lib.html)
- Convention plugin : `buildSrc/src/main/kotlin/ygdrasil/conventions/kmp-publish.gradle.kts`
