package org.graphiks.kextract.kotlin

import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.writeBytes
import kotlin.io.path.writeText
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KotlinJvmNativeBundleIndexTest {
    @TempDir
    private lateinit var tempDir: Path

    @Test
    fun `indexes every platform recursively with deterministic hashes and mappings`() {
        val header = tempDir.resolve("sample.h").also { it.writeText("void sample_call(void);") }
        val output = tempDir.resolve("output")
        val resources = output.resolve("jvmMain/resources")
        val mappedNames = linkedMapOf(
            "darwin-aarch64" to "libsample.dylib",
            "darwin-x86-64" to "libsample.dylib",
            "linux-aarch64" to "libsample.so",
            "linux-x86-64" to "libsample.so",
            "win32-x86-64" to "sample.dll",
        )
        mappedNames.forEach { (platform, mappedName) ->
            resources.resolve("$platform/deps").createDirectories()
            resources.resolve("$platform/deps/libsupport.bin").writeBytes("dependency".encodeToByteArray())
            resources.resolve("$platform/$mappedName").writeBytes("sample".encodeToByteArray())
        }

        val result = KextractTool(Logger()).runGeneration(
            listOf(header.toString()),
            Options(
                targetPackage = "test",
                outputDir = output.toString(),
                multiplatform = true,
                libraries = listOf(Options.Library.parse("sample")),
                jvmNativeLibraries = listOf(
                    Options.Library.parse("sample"),
                    Options.Library.parse(":/opt/native/libabsolute.so"),
                ),
            ),
        )

        assertEquals(KextractTool.SUCCESS, result)
        val generated = output.resolve("jvmMain/kotlin/test/sample_hJvm.kt").toFile().readText()
        val platformOffsets = mappedNames.keys.map(generated::indexOf)
        assertTrue(platformOffsets.all { it >= 0 })
        assertEquals(platformOffsets.sorted(), platformOffsets)
        mappedNames.values.toSet().forEach { mappedName -> assertContains(generated, mappedName) }
        assertContains(generated, "deps/libsupport.bin")
        assertContains(generated, "f26350dafe3f19aabfd69ac463fb5daf76015c9a2763e76e2ad32fc0fcfedf31")
        assertContains(generated, "Path.of(\"/opt/native/libabsolute.so\").toAbsolutePath().normalize()")
    }

    @Test
    fun `rejects ambiguous mapped entries in one platform`() {
        val header = tempDir.resolve("ambiguous.h").also { it.writeText("void sample_call(void);") }
        val output = tempDir.resolve("ambiguous-output")
        val resources = output.resolve("jvmMain/resources/linux-aarch64")
        resources.resolve("one").createDirectories()
        resources.resolve("two").createDirectories()
        resources.resolve("one/libsample.so").writeBytes(byteArrayOf(1))
        resources.resolve("two/libsample.so").writeBytes(byteArrayOf(2))

        val result = KextractTool(Logger()).runGeneration(
            listOf(header.toString()),
            Options(
                outputDir = output.toString(),
                multiplatform = true,
                libraries = listOf(Options.Library.parse("sample")),
            ),
        )

        assertEquals(KextractTool.FAILURE, result)
        assertTrue(Files.notExists(output.resolve("jvmMain/kotlin/ambiguous_hJvm.kt")))
    }
}
