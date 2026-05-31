# Kadre — Release Process

Step-by-step procedure for publishing a release to Maven Central.

## Prerequisites

### 1. Sonatype Maven Central account

Create an account on [central.sonatype.com](https://central.sonatype.com) and register the
namespace `org.graphiks.kadre` (DNS or SCM verification required).

### 2. GPG key

```bash
# Generate a key pair (if none exists)
gpg --full-generate-key          # RSA 4096, email contact@ygdrasil.io

# Export the secret key as ASCII (for CI environment variables)
gpg --export-secret-keys --armor <KEY_ID> | base64 > signing-key.b64

# Publish the public key (required by Maven Central)
gpg --keyserver keyserver.ubuntu.com --send-keys <KEY_ID>
```

### 3. Configuration variables

Add to `~/.gradle/gradle.properties` (never commit to the repository):

```properties
ossrhUsername=<your-sonatype-token>
ossrhPassword=<your-sonatype-secret-token>
signingKey=<contents of signing-key.b64>
signingPassword=<gpg-passphrase>
```

For CI (GitHub Actions), add the corresponding secrets under
*Repository secrets* (`Settings → Secrets and variables → Actions`):

| GitHub Secret          | Gradle Property          |
|------------------------|--------------------------|
| `OSSRH_USERNAME`       | `ossrhUsername`          |
| `OSSRH_PASSWORD`       | `ossrhPassword`          |
| `SIGNING_KEY`          | `signingKey`             |
| `SIGNING_PASSWORD`     | `signingPassword`        |

---

## Release workflow

### Step 1 — Prepare the version

```bash
# 1a. Change version=0.1.0-SNAPSHOT → version=0.1.0 in gradle.properties
sed -i '' 's/^version=.*/version=0.1.0/' gradle.properties

# 1b. Review the CHANGELOG / release notes

# 1c. Create a release commit
git add gradle.properties
git commit -m "chore: bump version to 0.1.0"
git tag v0.1.0
```

### Step 2 — Local verification

```bash
# Build all published modules
./gradlew :kadre-core:build :kadre:build :kadre-appkit:build \
          :kadre-android:build :kadre-uikit:build

# Publish locally and inspect artifacts
./gradlew :kadre-core:publishToMavenLocal \
          :kadre:publishToMavenLocal \
          :kadre-android:publishToMavenLocal \
          :kadre-appkit:publishToMavenLocal \
          :kadre-uikit:publishToMavenLocal

# Verify artifacts in ~/.m2
ls ~/.m2/repository/org/graphiks/kadre/kadre-core/0.1.0/
# Expected: kadre-core-0.1.0.jar
#           kadre-core-0.1.0-sources.jar
#           kadre-core-0.1.0-javadoc.jar
#           kadre-core-0.1.0.pom          (signed: .asc)
#           kadre-core-0.1.0.module       (signed: .asc)
```

### Step 3 — Maven Central publication

```bash
# Publish the 5 modules to Maven Central
./gradlew :kadre-core:publishKotlinMultiplatformPublicationToMavenCentral \
          :kadre:publishKotlinMultiplatformPublicationToMavenCentral \
          :kadre-android:publishKotlinMultiplatformPublicationToMavenCentral \
          :kadre-appkit:publishJvmPublicationToMavenCentral \
          :kadre-uikit:publishKotlinMultiplatformPublicationToMavenCentral

# Or globally (caution: also publishes samples if not filtered correctly)
./gradlew publishToMavenCentral
```

> **Maven Central Portal API note**: the target repository (`mavenCentral`) uses the
> Portal API (`https://central.sonatype.com/api/v1/publisher/upload`). Artifacts are
> uploaded into a "staging deployment" then promoted manually (or automatically) via
> the [central.sonatype.com](https://central.sonatype.com/publishing) interface.

### Step 4 — Promotion (Maven Central Portal)

1. Go to [central.sonatype.com/publishing](https://central.sonatype.com/publishing)
2. Check the pending deployment (automatic validation: POM, signatures, sources)
3. Click **Publish** → propagation to Maven Central mirrors (~10–30 min)

### Step 5 — Post-release

```bash
# Bump back to SNAPSHOT for the next cycle
sed -i '' 's/^version=.*/version=0.2.0-SNAPSHOT/' gradle.properties
git add gradle.properties
git commit -m "chore: bump version to 0.2.0-SNAPSHOT"
git push origin master --tags
```

---

## Published modules

| Module           | Artifact ID       | Description                              |
|------------------|-------------------|------------------------------------------|
| `kadre-core`    | `kadre-core`     | Pure KMP interfaces (commonMain)         |
| `kadre`         | `kadre`          | Public KMP facade (jvm + iOS + android)  |
| `kadre-appkit`  | `kadre-appkit`   | macOS backend (AppKit, JVM 25, FFM)      |
| `kadre-uikit`   | `kadre-uikit`    | iOS backend (UIKit, Kotlin/Native)       |
| `kadre-android` | `kadre-android`  | Android backend (SurfaceView)            |

GroupId: `org.graphiks.kadre`

---

## Troubleshooting

### Missing / invalid signature

```
Publication 'X' is not signed
```
Check that `signingKey` and `signingPassword` are defined in
`~/.gradle/gradle.properties` or the CI environment variables.

### Artifact rejected (incomplete POM)

Maven Central requires: `name`, `description`, `url`, `licenses`, `developers`, `scm`.
All these fields are configured in `buildSrc/.../kmp-publish.gradle.kts`.

### kadre-android: iOS publications disabled

`kadre-android` uses the `kmp-library` plugin which adds iOS targets, but
this module only contains Android code. iOS/JVM publications are intentionally
disabled via `afterEvaluate` in `kadre-android/build.gradle.kts`.

---

## References

- [Maven Central Publishing Guide](https://central.sonatype.org/publish/publish-guide/)
- [Kotlin KMP Maven Central Setup](https://kotlinlang.org/docs/multiplatform-publish-lib.html)
- Convention plugin: `buildSrc/src/main/kotlin/ygdrasil/conventions/kmp-publish.gradle.kts`
