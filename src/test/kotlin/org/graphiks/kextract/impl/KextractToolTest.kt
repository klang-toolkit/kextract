package org.graphiks.kextract.pipeline

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.graphiks.kextract.callbacks.CallbackBindingsConfig
import org.graphiks.kextract.cli.DllEntry
import org.graphiks.kextract.cli.DllMap
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URLClassLoader
import java.io.PrintWriter
import java.nio.file.Path
import kotlin.io.path.writeText
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class KextractToolTest {
    @TempDir
    private lateinit var tempDir: Path

    @Test
    fun `test KextractTool can be instantiated`() {
        val logger = Logger(PrintWriter(ByteArrayOutputStream()), PrintWriter(ByteArrayOutputStream()))
        val tool = KextractTool(logger)
        assertNotNull(tool)
    }

    @Test
    fun `test error codes are defined`() {
        assertEquals(0, KextractTool.SUCCESS)
        assertEquals(1, KextractTool.FAILURE)
        assertEquals(2, KextractTool.OPTION_ERROR)
        assertEquals(3, KextractTool.INPUT_ERROR)
        assertEquals(4, KextractTool.CLANG_ERROR)
        assertEquals(5, KextractTool.FATAL_ERROR)
        assertEquals(6, KextractTool.OUTPUT_ERROR)
    }

    // ── expandGnuArgs ────────────────────────────────────────────────────────

    @Test
    fun `expandGnuArgs splits concatenated -D defines`() {
        val result = KextractTool.expandGnuArgs(listOf("-DDEBUG", "-DFOO=1"))
        assertEquals(listOf("-D", "DEBUG", "-D", "FOO=1"), result)
    }

    @Test
    fun `expandGnuArgs splits concatenated -I paths`() {
        val result = KextractTool.expandGnuArgs(listOf("-I/usr/include", "-I/opt/include"))
        assertEquals(listOf("-I", "/usr/include", "-I", "/opt/include"), result)
    }

    @Test
    fun `expandGnuArgs leaves standalone -D and -I unchanged`() {
        val result = KextractTool.expandGnuArgs(listOf("-D", "FOO=1", "-I", "/usr/include"))
        assertEquals(listOf("-D", "FOO=1", "-I", "/usr/include"), result)
    }

    @Test
    fun `expandGnuArgs passes unknown flags through unchanged`() {
        val result = KextractTool.expandGnuArgs(listOf("-Wall", "--verbose", "header.h"))
        assertEquals(listOf("-Wall", "--verbose", "header.h"), result)
    }

    @Test
    fun `expandGnuArgs handles mixed args`() {
        val result = KextractTool.expandGnuArgs(listOf("-DDEBUG", "-I/usr/include", "-o", "out"))
        assertEquals(listOf("-D", "DEBUG", "-I", "/usr/include", "-o", "out"), result)
    }

    // ── Options.Library ──────────────────────────────────────────────────────

    @Test
    fun `Options Library parse NAME spec`() {
        val lib = Options.Library.parse("m")
        assertEquals("m", lib.libSpec)
        assertEquals(Options.Library.SpecKind.NAME, lib.specKind)
    }

    @Test
    fun `Options Library parse PATH spec`() {
        val lib = Options.Library.parse(":/usr/lib/libm.so")
        assertEquals("/usr/lib/libm.so", lib.libSpec)
        assertEquals(Options.Library.SpecKind.PATH, lib.specKind)
    }

    @Test
    fun `Options Library toQuotedName escapes backslashes`() {
        val lib = Options.Library("C:\\lib\\foo.dll", Options.Library.SpecKind.PATH)
        assertEquals("C:\\\\lib\\\\foo.dll", Options.Library.toQuotedName(lib))
    }

    @Test
    fun `DllEntry preserves the historical JVM constructor and YAML variable default`() {
        val legacyConstructor = DllEntry::class.java.getConstructor(
            List::class.java,
            List::class.java,
            List::class.java,
        )
        val constructed = legacyConstructor.newInstance(
            listOf("legacyFunction"),
            listOf("LegacyStruct"),
            listOf("LEGACY_CONSTANT"),
        )
        assertEquals(emptyList(), constructed.variables)

        val yaml =
            """
            dllMap:
              legacy.dll:
                functions: [legacyFunction]
                structs: [LegacyStruct]
                constants: [LEGACY_CONSTANT]
            """.trimIndent()
        val parsed = ObjectMapper(YAMLFactory()).readValue(yaml, DllMap::class.java)
        val yamlEntry = parsed.dllMap.getValue("legacy.dll")
        assertEquals(listOf("legacyFunction"), yamlEntry.functions)
        assertEquals(emptyList(), yamlEntry.variables)

        val modernYaml =
            """
            dllMap:
              modern.dll:
                functions: [modernFunction]
                variables: [modernGlobal]
            """.trimIndent()
        val modernEntry = ObjectMapper(YAMLFactory())
            .readValue(modernYaml, DllMap::class.java)
            .dllMap
            .getValue("modern.dll")
        assertEquals(listOf("modernGlobal"), modernEntry.variables)
        assertNotEquals(
            DllEntry(functions = listOf("modernFunction")),
            DllEntry(functions = listOf("modernFunction"), variables = listOf("modernGlobal")),
        )
    }

    @Test
    fun `precompiled Kotlin DllEntry default arguments remain binary compatible`() {
        val workspace = java.nio.file.Files.createTempDirectory("kextract_legacy_dll_entry_")
        try {
            val oldApi = workspace.resolve("OldDllEntry.kt")
            val legacyConsumer = workspace.resolve("LegacyConsumer.kt")
            val oldApiClasses = java.nio.file.Files.createDirectories(workspace.resolve("old-api"))
            val consumerClasses = java.nio.file.Files.createDirectories(workspace.resolve("consumer"))
            java.nio.file.Files.writeString(
                oldApi,
                """
                package org.graphiks.kextract.cli

                data class DllEntry(
                    val functions: List<String> = emptyList(),
                    val structs: List<String> = emptyList(),
                    val constants: List<String> = emptyList(),
                )
                """.trimIndent(),
            )
            java.nio.file.Files.writeString(
                legacyConsumer,
                """
                package legacy

                import org.graphiks.kextract.cli.DllEntry

                fun callLegacyDefaultConstructor(): Int =
                    DllEntry(functions = listOf("legacy")).functions.size
                """.trimIndent(),
            )

            val kotlinStdlib = kotlin.Unit::class.java.protectionDomain.codeSource.location.toURI().path

            val oldApiExit = K2JVMCompiler().exec(
                System.err,
                "-no-stdlib",
                "-no-reflect",
                "-jvm-target", "25",
                "-classpath", kotlinStdlib,
                "-d", oldApiClasses.toString(),
                oldApi.toString(),
            )
            assertEquals(ExitCode.OK, oldApiExit)
            val legacyConsumerExit = K2JVMCompiler().exec(
                System.err,
                "-no-stdlib",
                "-no-reflect",
                "-jvm-target", "25",
                "-classpath", oldApiClasses.toString() + File.pathSeparator + kotlinStdlib,
                "-d", consumerClasses.toString(),
                legacyConsumer.toString(),
            )
            assertEquals(ExitCode.OK, legacyConsumerExit)

            URLClassLoader(
                arrayOf(consumerClasses.toUri().toURL()),
                KextractToolTest::class.java.classLoader,
            ).use { loader ->
                val result = loader.loadClass("legacy.LegacyConsumerKt")
                    .getMethod("callLegacyDefaultConstructor")
                    .invoke(null)
                assertEquals(1, result)
            }
        } finally {
            workspace.toFile().deleteRecursively()
        }
    }

    @Test
    fun `legacy generation accepts callbacks without analyzing callback metadata`() {
        val header = tempDir.resolve("legacy-callback.h").also {
            it.writeText(
                """
                    typedef int (*LegacyComparator)(const void *, const void *);
                    int legacy_sort(LegacyComparator comparator);
                """.trimIndent(),
            )
        }
        val logger = Logger(
            PrintWriter(ByteArrayOutputStream(), true),
            PrintWriter(ByteArrayOutputStream(), true),
        )

        val exitCode = KextractTool(logger).runGeneration(
            listOf(header.toString()),
            Options(outputDir = tempDir.resolve("output").toString()),
        )

        assertEquals(KextractTool.SUCCESS, exitCode)
    }

    @Test
    fun `programmatic callback bindings require multiplatform generation`() {
        val header = tempDir.resolve("programmatic-callback-gate.h").also {
            it.writeText("typedef void (*ProgrammaticCallbackGate)(void);")
        }
        val errors = ByteArrayOutputStream()
        val logger = Logger(
            PrintWriter(ByteArrayOutputStream(), true),
            PrintWriter(errors, true),
        )

        val exitCode = KextractTool(logger).runGeneration(
            listOf(header.toString()),
            Options(
                outputDir = tempDir.resolve("programmatic-gate-output").toString(),
                callbackBindings = CallbackBindingsConfig(),
            ),
        )

        assertEquals(KextractTool.FAILURE, exitCode)
        assertContains(errors.toString(), "callbackBindings requires multiplatform generation")
    }
}
