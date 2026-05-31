/**
 * Convention plugin kmp-publish — Maven Central publication for the Kadre KMP modules.
 *
 * Configures:
 *   - maven-publish : KMP publications + complete POM (MIT license, dev info, SCM)
 *   - javadocJar    : Dokka HTML JAR packaged as the "-javadoc" artifact (required by Maven Central)
 *   - signing       : in-memory GPG signing via env vars / gradle.properties
 *   - Maven Central repository (Sonatype Central) + Maven Local
 *
 * Expected variables (gradle.properties or env):
 *   ossrhUsername / OSSRH_USERNAME   — Sonatype OSSRH user
 *   ossrhPassword / OSSRH_PASSWORD   — Sonatype OSSRH password
 *   signingKey    / SIGNING_KEY      — armored ASCII GPG key (base64)
 *   signingPassword / SIGNING_PASSWORD — GPG passphrase
 *
 * group and version are read from the project (defined in the root gradle.properties).
 *
 * GRA-159
 */
package ygdrasil.conventions

plugins {
    id("maven-publish")
    id("signing")
}

// ── Javadoc JAR (Dokka HTML) ────────────────────────────────────────────────
// Creates a "-javadoc" artifact by packaging the Dokka HTML output.
// If Dokka has not run yet (task absent), an empty JAR is created
// to satisfy the Maven Central requirement during publishToMavenLocal.

val javadocJar by tasks.registering(Jar::class) {
    group = "documentation"
    description = "Packages Dokka HTML output as a -javadoc JAR for Maven Central."
    archiveClassifier.set("javadoc")

    // Priority 1: Dokka 2.x (dokkaGeneratePublicationHtml)
    val dokkaV2 = tasks.findByName("dokkaGeneratePublicationHtml")
    // Priority 2: Dokka 1.x compat (dokkaHtml)
    val dokkaV1 = tasks.findByName("dokkaHtml")

    val dokkaTask = dokkaV2 ?: dokkaV1
    if (dokkaTask != null) {
        dependsOn(dokkaTask)
        from(dokkaTask.outputs.files)
    }
    // If Dokka absent: empty JAR (stub) — acceptable for publishToMavenLocal
}

// ── POM + Publications ──────────────────────────────────────────────────────

publishing {
    publications.withType<MavenPublication>().configureEach {
        // Attach the javadoc JAR to all publications
        artifact(javadocJar)

        pom {
            name.set(project.name)
            description.set(
                "Kadre — Kotlin Multiplatform windowing and event-loop library (${project.name})"
            )
            url.set("https://github.com/ygdrasil-io/poc-koreos")

            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }

            developers {
                developer {
                    id.set("ygdrasil-io")
                    name.set("Ygdrasil team")
                    email.set("contact@ygdrasil.io")
                }
            }

            scm {
                connection.set("scm:git:git://github.com/ygdrasil-io/poc-koreos.git")
                developerConnection.set("scm:git:ssh://github.com/ygdrasil-io/poc-koreos.git")
                url.set("https://github.com/ygdrasil-io/poc-koreos")
            }
        }
    }

    repositories {
        maven {
            name = "mavenCentral"
            // Maven Central Portal API (new publisher since Feb 2024)
            val releasesRepoUrl = uri("https://central.sonatype.com/api/v1/publisher/upload")
            val snapshotsRepoUrl = uri("https://central.sonatype.com/api/v1/publisher/upload")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

            credentials {
                username = project.findProperty("ossrhUsername") as? String
                    ?: System.getenv("OSSRH_USERNAME")
                password = project.findProperty("ossrhPassword") as? String
                    ?: System.getenv("OSSRH_PASSWORD")
            }
        }
    }
}

// ── Signing ─────────────────────────────────────────────────────────────────

signing {
    val signingKey = project.findProperty("signingKey") as? String
        ?: System.getenv("SIGNING_KEY")
    val signingPassword = project.findProperty("signingPassword") as? String
        ?: System.getenv("SIGNING_PASSWORD")
    if (!signingKey.isNullOrEmpty() && !signingPassword.isNullOrEmpty()) {
        useInMemoryPgpKeys(signingKey, signingPassword)
        sign(publishing.publications)
    }
}
