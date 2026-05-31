/**
 * Convention plugin kmp-dokka — documentation KDoc via Dokka.
 *
 * Deux modes de rendu :
 *   - GFM (GitHub Flavored Markdown) → docs/kadre/api/${project.name}
 *     Utilisé par MkDocs pour le site de documentation.
 *   - HTML → build/dokka/html/${project.name}
 *     Utilisé par kmp-publish pour le artefact -javadoc.jar (Maven Central).
 *
 * GRA-157 : génération initiale de la documentation.
 * GRA-159 : séparation GFM/HTML pour éviter le mélange dans docs/.
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
