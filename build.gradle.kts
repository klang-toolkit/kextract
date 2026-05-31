plugins {
    // Les plugins communs et leurs versions sont gérés et importés par buildSrc
}

// ── Toolchain Kotlin/JS Node + Yarn ──────────────────────────────
//
// Par défaut, le plugin KGP ajoute au niveau *projet* les repos ivy de distribution
// Node.js et Yarn (nodejs.org / github yarnpkg). Avec
// `dependencyResolutionManagement { repositoriesMode = FAIL_ON_PROJECT_REPOS }`,
// Gradle rejette ces repos projet → `kotlinNodeJsSetup` échoue, ce qui bloque tout
// build webpack (jsBrowserDistribution, webpack-dev-server, browser tests).
//
// Correctif : les repos sont déclarés côté `settings.gradle.kts` (hermétique), et on
// efface ici le `downloadBaseUrl` des EnvSpec Node/Yarn (js + wasm) pour que KGP
// n'ajoute PAS son propre repo projet et résolve via les repos settings.
// Débloque #20 (GitHub Pages) et #22 (E2E web).
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
    // NOTE : la distribution wasmJs (wasmJsBrowserDistribution) requiert en plus
    // l'outil Binaryen, dont le plugin KGP ajoute son repo projet sans respecter
    // downloadBaseUrl — non résolu ici. Les deux samples web ciblent js + wasmJs ;
    // la variante **JS** (utilisée par #20 Pages et #22 E2E) est pleinement débloquée.
    // Le webpack wasmJs reste un reliquat de #91.
}
