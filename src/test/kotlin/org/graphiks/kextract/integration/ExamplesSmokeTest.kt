package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.string.shouldContain
import org.junit.jupiter.api.Assumptions.assumeTrue
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Smoke tests that actually compile and execute the projects under examples/.
 *
 * Each test runs the example's run.sh --skip-build (kextract binary must already
 * exist under build/kextract/bin/kextract — i.e. createKextractImage was run).
 *
 * Tests are skipped (not failed) when:
 *  - The kextract binary is missing (run ./gradlew createKextractImage first).
 *  - kotlinc is not on PATH (install via brew install kotlin).
 *  - A test requires macOS but the host is not macOS.
 *
 * Run the full cycle in one command:
 *   ./gradlew verifyExamples
 */
class ExamplesSmokeTest : FreeSpec({

    val projectRoot = File(System.getProperty("user.dir"))
    val kextractBinary = File(projectRoot, "build/kextract/bin/kextract")

    /** Runs examples/<dir>/run.sh --skip-build and returns (exitCode, stdout+stderr). */
    fun runExample(dirName: String, timeoutSeconds: Long = 120): Pair<Int, String> {
        val dir = File(projectRoot, "examples/$dirName")
        val proc = ProcessBuilder("bash", "run.sh", "--skip-build")
            .directory(dir)
            .redirectErrorStream(true)
            .start()
        val output = proc.inputStream.bufferedReader().readText()
        val finished = proc.waitFor(timeoutSeconds, TimeUnit.SECONDS)
        if (!finished) {
            proc.destroyForcibly()
            throw AssertionError("Example '$dirName' timed out after ${timeoutSeconds}s")
        }
        return proc.exitValue() to output
    }

    "helloworld-c" - {
        "generates and runs C bindings" {
            assumeTrue(kextractBinary.exists(),
                "kextract binary not found — run ./gradlew createKextractImage")
            assumeTrue(findOnPath("kotlinc") != null,
                "kotlinc not on PATH — install via 'brew install kotlin'")

            val (exitCode, output) = runExample("helloworld-c")
            println(output)
            assert(exitCode == 0) { "run.sh exited with $exitCode:\n$output" }
            output shouldContain "Hello from C via Panama FFI!"
            output shouldContain "40 + 2 = 42"
        }
    }

    "helloworld-objc" - {
        // Disabled until ObjC support is implemented (see ObjC plan).
        "generates and runs ObjC bindings".config(enabled = false) {
            assumeTrue(System.getProperty("os.name") == "Mac OS X",
                "ObjC example requires macOS")
            assumeTrue(kextractBinary.exists(),
                "kextract binary not found — run ./gradlew createKextractImage")
            assumeTrue(findOnPath("kotlinc") != null,
                "kotlinc not on PATH — install via 'brew install kotlin'")

            val (exitCode, output) = runExample("helloworld-objc")
            println(output)
            assert(exitCode == 0) { "run.sh exited with $exitCode:\n$output" }
            output shouldContain "Hello, World!"
        }
    }
})

private fun findOnPath(name: String): String? =
    System.getenv("PATH")?.split(File.pathSeparator)
        ?.map { File(it, name) }
        ?.firstOrNull { it.canExecute() }
        ?.absolutePath
