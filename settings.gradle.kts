pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // PREFER_SETTINGS: the repos declared here take precedence for resolution, but we
    // tolerate that a plugin (typically Kotlin/Wasm via `BinaryenSetupTask`) adds
    // ITS own repo at the project level without crashing the build. Security
    // is preserved — a project repo cannot override a settings repo — and
    // the production wasm build (Binaryen / wasm-opt) is unblocked.
    // Before: FAIL_ON_PROJECT_REPOS rejected every project repo, including the one
    // added by the Wasm plugin itself → `wasmJsBrowserProductionWebpack` failed.
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()

        // Kotlin/JS toolchain: the KGP plugin downloads Node.js and Yarn from ivy
        // repos that it normally adds at the project level — which
        // FAIL_ON_PROJECT_REPOS rejects (failure of `kotlinNodeJsSetup`). We therefore
        // declare them here, in settings, to stay hermetic while still allowing the
        // production webpack builds (jsBrowserDistribution / wasmJsBrowserDistribution).
        // See; unblocks #20 (GitHub Pages) and #22 (web E2E).
        ivy("https://nodejs.org/dist") {
            name = "Node.js Distributions"
            patternLayout { artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]") }
            metadataSources { artifact() }
            content { includeModule("org.nodejs", "node") }
        }
        ivy("https://github.com/yarnpkg/yarn/releases/download") {
            name = "Yarn Distributions"
            patternLayout { artifact("v[revision]/[artifact](-v[revision]).[ext]") }
            metadataSources { artifact() }
            content { includeModule("com.yarnpkg", "yarn") }
        }
        // Binaryen (wasm-opt) — used by the Kotlin/Wasm plugin to optimize the
        // `.wasm` bundle of the production build (`wasmJsBrowserProductionWebpack`).
        // Declared here (in addition to the repo the plugin adds at the project level via
        // `BinaryenSetupTask`) to ensure hermetic resolution under `PREFER_SETTINGS`
        // and to document the external dependency just like Node / Yarn.
        // Actual URL:
        //   https://github.com/WebAssembly/binaryen/releases/download/version_<v>/binaryen-version_<v>-<classifier>.tar.gz
        ivy("https://github.com/WebAssembly/binaryen/releases/download") {
            name = "Binaryen Distributions"
            patternLayout { artifact("version_[revision]/[artifact]-version_[revision]-[classifier].[ext]") }
            metadataSources { artifact() }
            content { includeModule("com.github.webassembly", "binaryen") }
        }
    }
}

rootProject.name = "kadre"

// Kadre modules
include(":kadre-web-common")
include(":kadre-core")
include(":kadre-test")
include(":benchmarks:jmh-core")
include(":kadre-appkit")
include(":kadre-uikit")
include(":kadre-win32")
include(":kadre-x11")
include(":kadre-wayland")
include(":kadre-js")
include(":kadre-wasm")
include(":kadre")
include(":kadre-android")
include(":samples:hello-metal")
include(":samples:hello-compose")
include(":samples:hello-triangle")
include(":samples:hello-touch")
include(":samples:hello-touch-android")
include(":samples:hello-window")
include(":samples:hello-window-android")
include(":samples:pong")
include(":samples:hello-window-web")
include(":samples:hello-triangle-web")
include(":samples:hello-triangle-ios")
include(":samples:hello-triangle-android-capture")
