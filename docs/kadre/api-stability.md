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

---

## Winit parity remediation — R0–R5 (2026-06)

### R0.1 — Breaking change (event types)

Two signatures in `ApplicationHandler` changed from erased `Any` to proper sealed types:

| Method | Before | After |
|--------|--------|-------|
| `windowEvent(eventLoop, windowId, event)` | `event: Any` | `event: WindowEvent` |
| `deviceEvent(eventLoop, deviceId, event)` | `event: Any` | `event: DeviceEvent` |

Similarly, `Window.rawWindowHandle` and `Window.rawDisplayHandle` changed from `Any` to `RawWindowHandle` and `RawDisplayHandle` respectively. These are **breaking changes**: any code doing `event as SomeType` or `handle as SomeHandle` must be updated to pattern-match on the sealed hierarchy.

The ABI dumps were regenerated (`updateKotlinAbi`) and committed as part of R0.1 (PRs #167–#170).

### R1–R5 — Additive growth

All subsequent rounds (R1: window state/monitors/fullscreen; R2: window icon; R3: cursor/theme/appearance; R4: keyboard richness/ModifiersChanged/MouseWheel device; R5: DnD/gestures/custom cursors/misc window/IME) added **new sealed variants and new interface methods** only — no existing signatures were removed or changed. Each addition required running `./gradlew updateKotlinAbi` and committing the updated `api/` files (PRs #171–#184).

For the full list of items that were intentionally deferred (no-op implementations, unimitted events, partial native backends), see [DEFERRED.md](https://github.com/ygdrasil-io/poc-koreos/blob/master/DEFERRED.md).
