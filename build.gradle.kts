plugins {
    // The common plugins and their versions are managed and imported by buildSrc
}

// ── Toolchain Kotlin/JS Node + Yarn ──────────────────────────────
//
// By default, the KGP plugin adds the Node.js and Yarn distribution ivy repos at the
// *project* level (nodejs.org / github yarnpkg). With
// `dependencyResolutionManagement { repositoriesMode = FAIL_ON_PROJECT_REPOS }`,
// Gradle rejects these project repos → `kotlinNodeJsSetup` fails, which blocks every
// webpack build (jsBrowserDistribution, webpack-dev-server, browser tests).
//
// Fix: the repos are declared in `settings.gradle.kts` (hermetic), and here we
// clear the `downloadBaseUrl` of the Node/Yarn EnvSpecs (js + wasm) so that KGP
// does NOT add its own project repo and resolves via the settings repos.
// Unblocks #20 (GitHub Pages) and #22 (web E2E).
run {
    val noBaseUrl = provider { null as String? }
    plugins.withType(org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin::class.java) {
        the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsEnvSpec>().downloadBaseUrl.set(noBaseUrl)
    }
    plugins.withType(org.jetbrains.kotlin.gradle.targets.wasm.nodejs.WasmNodeJsRootPlugin::class.java) {
        the<org.jetbrains.kotlin.gradle.targets.wasm.nodejs.WasmNodeJsEnvSpec>().downloadBaseUrl.set(noBaseUrl)
    }
    plugins.withType(org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin::class.java) {
        the<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootEnvSpec>().downloadBaseUrl.set(noBaseUrl)
    }
    plugins.withType(org.jetbrains.kotlin.gradle.targets.wasm.yarn.WasmYarnPlugin::class.java) {
        the<org.jetbrains.kotlin.gradle.targets.wasm.yarn.WasmYarnRootEnvSpec>().downloadBaseUrl.set(noBaseUrl)
    }
    // NOTE: the wasmJs distribution (wasmJsBrowserDistribution) additionally requires
    // the Binaryen tool, for which the KGP plugin adds its project repo without honoring
    // downloadBaseUrl — not resolved here. Both web samples target js + wasmJs;
    // the **JS** variant (used by #20 Pages and #22 E2E) is fully unblocked.
    // The wasmJs webpack remains a leftover from #91.
}
