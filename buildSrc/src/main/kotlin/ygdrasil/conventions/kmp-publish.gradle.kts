/**
 * Convention plugin kmp-publish — publication Maven Central pour les modules KMP Kadre.
 *
 * Configure :
 *   - maven-publish : publications KMP + POM complet (license MIT, dev info, SCM)
 *   - javadocJar    : JAR Dokka HTML packagé en artifact "-javadoc" (requis Maven Central)
 *   - signing       : signature GPG en mémoire via vars d'env / gradle.properties
 *   - dépôt Maven Central (Sonatype Central) + Maven Local
 *
 * Variables attendues (gradle.properties ou env) :
 *   ossrhUsername / OSSRH_USERNAME   — Sonatype OSSRH user
 *   ossrhPassword / OSSRH_PASSWORD   — Sonatype OSSRH password
 *   signingKey    / SIGNING_KEY      — clé GPG armored ASCII (base64)
 *   signingPassword / SIGNING_PASSWORD — passphrase GPG
 *
 * group et version sont lus depuis le projet (définis dans gradle.properties racine).
 *
 * GRA-159
 */
package ygdrasil.conventions

plugins {
    id("maven-publish")
    id("signing")
}

// ── Javadoc JAR (Dokka HTML) ────────────────────────────────────────────────
// Crée un artifact "-javadoc" en packagant la sortie Dokka HTML.
// Si Dokka n'est pas encore lancé (tâche absente), un JAR vide est créé
// pour satisfaire l'exigence Maven Central lors de publishToMavenLocal.

val javadocJar by tasks.registering(Jar::class) {
    group = "documentation"
    description = "Packages Dokka HTML output as a -javadoc JAR for Maven Central."
    archiveClassifier.set("javadoc")

    // Priorité 1 : Dokka 2.x (dokkaGeneratePublicationHtml)
    val dokkaV2 = tasks.findByName("dokkaGeneratePublicationHtml")
    // Priorité 2 : Dokka 1.x compat (dokkaHtml)
    val dokkaV1 = tasks.findByName("dokkaHtml")

    val dokkaTask = dokkaV2 ?: dokkaV1
    if (dokkaTask != null) {
        dependsOn(dokkaTask)
        from(dokkaTask.outputs.files)
    }
    // Si Dokka absent : JAR vide (stub) — acceptable pour publishToMavenLocal
}

// ── POM + Publications ──────────────────────────────────────────────────────

publishing {
    publications.withType<MavenPublication>().configureEach {
        // Attache le javadoc JAR à toutes les publications
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
