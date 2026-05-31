/**
 * Convention plugin kmp-dokka — KDoc documentation via Dokka.
 *
 * Two rendering modes:
 *   - GFM (GitHub Flavored Markdown) → docs/kadre/api/${project.name}
 *     Used by MkDocs for the documentation site.
 *   - HTML → build/dokka/html/${project.name}
 *     Used by kmp-publish for the -javadoc.jar artifact (Maven Central).
 *
 * GRA-157: initial documentation generation.
 * GRA-159: GFM/HTML separation to avoid mixing in docs/.
 */
package ygdrasil.conventions

import java.net.URI
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("org.jetbrains.dokka")
}

dependencies {
    add("dokkaGfmPlugin", "org.jetbrains.dokka:gfm-plugin:2.2.0")
}

// ── GFM output (MkDocs) ──────────────────────────────────────────────────────

tasks.named<DokkaTask>("dokkaGfm") {
    moduleName.set(project.name)
    outputDirectory.set(rootProject.file("docs/kadre/api/${project.name}"))

    dokkaSourceSets.configureEach {
        skipEmptyPackages.set(true)
        reportUndocumented.set(false)

        val srcSetName = name
        val srcDir = project.file("src/${srcSetName}/kotlin")
        if (srcDir.exists()) {
            sourceLink {
                localDirectory.set(srcDir)
                remoteUrl.set(URI("https://github.com/ygdrasil-io/poc-koreos/blob/master/${project.name}/src/${srcSetName}/kotlin").toURL())
                remoteLineSuffix.set("#L")
            }
        }
    }
}

// ── HTML output (javadoc JAR for Maven Central) ──────────────────────────────

tasks.named<DokkaTask>("dokkaHtml") {
    moduleName.set(project.name)
    // Output to build/ — not docs/ — so HTML files don't pollute the GFM tree
    outputDirectory.set(layout.buildDirectory.dir("dokka/html"))

    dokkaSourceSets.configureEach {
        skipEmptyPackages.set(true)
        reportUndocumented.set(false)

        val srcSetName = name
        val srcDir = project.file("src/${srcSetName}/kotlin")
        if (srcDir.exists()) {
            sourceLink {
                localDirectory.set(srcDir)
                remoteUrl.set(URI("https://github.com/ygdrasil-io/poc-koreos/blob/master/${project.name}/src/${srcSetName}/kotlin").toURL())
                remoteLineSuffix.set("#L")
            }
        }
    }
}
