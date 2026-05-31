# Public API Stability (ABI)

Kadre is published on Maven Central. To prevent silently breaking the public API between versions (signature changes, sealed variant additions/removals, etc.), all **5 published modules** are protected by ABI validation built into the Kotlin Gradle plugin (Kotlin 2.2+):

`kadre-core`, `kadre-appkit`, `kadre-uikit`, `kadre-android`, `kadre`.

## How it works

Each published module enables the following in its `build.gradle.kts`:

```kotlin
kotlin {
    @OptIn(org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation::class)
    abiValidation { enabled.set(true) }
}
```

The reference API dump is committed under `<module>/api/`:

- `<module>/api/<module>.klib.api` — multiplatform ABI (klib, all targets)
- `<module>/api/jvm/<module>.api` — JVM ABI
- `<module>/api/android/<module>.api` — Android ABI

The `checkKotlinAbi` task is wired into `check`: **the build fails** if the current public API differs from the committed dump. Unlike the old external `binary-compatibility-validator` plugin (which uses ASM and failed on JDK 25 bytecode), this validation relies on the **Kotlin compiler** — compatible with JDK 25.

## Workflow for API changes

1. Modify the code.
2. If CI (or `./gradlew checkKotlinAbi`) reports an ABI difference:
   - **Intentional** → regenerate the dump: `./gradlew updateKotlinAbi`,
     then commit the modified `api/` files in the same PR;
   - **Unintentional** → fix the code to restore compatibility.

```bash
# Regenerate all reference dumps
./gradlew updateKotlinAbi

# Verify (as CI does)
./gradlew checkKotlinAbi
```

## For the autonomous agent

If `checkKotlinAbi` fails in CI, a public API change was introduced. Verify that it is intentional (per the ticket), then run `./gradlew updateKotlinAbi` and commit the modified `api/` files with a message `chore(api): update ABI baseline for #ID`.
