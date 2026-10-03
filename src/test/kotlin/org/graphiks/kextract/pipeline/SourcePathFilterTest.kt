package org.graphiks.kextract.pipeline

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SourcePathFilterIntegrationTest {
    @TempDir
    lateinit var tempDir: Path

    @Test
    fun `restrict to header paths drops declarations from included external headers`() {
        val root = tempDir.resolve("root").createDirectories()
        val external = tempDir.resolve("external").createDirectories()
        external.resolve("external.h").writeText("void externalFunction(int value);\n")
        val header = root.resolve("main.h")
        header.writeText("#include \"../external/external.h\"\nvoid mainFunction(int value);\n")
        val output = tempDir.resolve("out")

        val code = KextractTool(Logger()).runGeneration(
            listOf(header.toString()),
            Options(
                targetPackage = "sample.bindings",
                outputDir = output.toString(),
                multiplatform = true,
                restrictToHeaderPaths = true,
            ),
        )

        assertEquals(KextractTool.SUCCESS, code)
        val common = readSources(output.resolve("commonMain"))
        assertTrue(common.contains("mainFunction"), "expected root-header declaration in output")
        assertFalse(common.contains("externalFunction"), "external-header declaration leaked into output")
    }

    @Test
    fun `without the restriction both declarations are generated`() {
        val root = tempDir.resolve("root").createDirectories()
        val external = tempDir.resolve("external").createDirectories()
        external.resolve("external.h").writeText("void externalFunction(int value);\n")
        val header = root.resolve("main.h")
        header.writeText("#include \"../external/external.h\"\nvoid mainFunction(int value);\n")
        val output = tempDir.resolve("out")

        val code = KextractTool(Logger()).runGeneration(
            listOf(header.toString()),
            Options(
                targetPackage = "sample.bindings",
                outputDir = output.toString(),
                multiplatform = true,
            ),
        )

        assertEquals(KextractTool.SUCCESS, code)
        val common = readSources(output.resolve("commonMain"))
        assertTrue(common.contains("mainFunction"))
        assertTrue(common.contains("externalFunction"))
    }

    private fun readSources(root: Path): String {
        if (!Files.isDirectory(root)) return ""
        return Files.walk(root).use { paths ->
            paths.filter { Files.isRegularFile(it) }
                .map { it.toFile().readText() }
                .toList()
                .joinToString("\n")
        }
    }
}
