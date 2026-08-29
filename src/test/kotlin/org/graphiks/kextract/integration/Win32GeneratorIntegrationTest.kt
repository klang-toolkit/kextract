package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.graphiks.kextract.cli.DllEntry
import org.graphiks.kextract.cli.DllMap
import org.graphiks.kextract.kotlin.KotlinGenerator
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.NameMangler
import org.graphiks.kextract.pipeline.Options
import java.nio.file.Files

class Win32GeneratorIntegrationTest : FreeSpec({

    fun generateWin32(
        csource: String,
        functionNames: List<String>,
        useInitMethod: Boolean = false,
    ): String {
        val tmp = Files.createTempFile("kextract_win32_test_", ".h")
        try {
            tmp.toFile().writeText(csource)
            val headerName = tmp.fileName.toString()
            val parsed = KextractTool.parse(
                listOf(tmp.toString()),
                "-x", "c",
                "-target", "x86_64-pc-windows-msvc",
                "-fshort-wchar",
            )
            val mangled = NameMangler(headerName).scan(parsed)
            val dllMap = DllMap(
                mapOf("test.dll" to DllEntry(functions = functionNames)),
            )
            return KotlinGenerator().generate(
                scoped = mangled,
                headerName = headerName,
                targetPackage = "test",
                win32Mode = true,
                dllMap = dllMap,
                useInitMethod = useInitMethod,
            ).single().contents
        } finally {
            Files.deleteIfExists(tmp)
        }
    }

    fun runGeneration(csource: String, win32Mode: Boolean): String {
        val header = Files.createTempFile("kextract_win32_wchar_", ".h")
        val output = Files.createTempDirectory("kextract_win32_wchar_output_")
        try {
            header.toFile().writeText(csource)
            KextractTool(Logger()).runGeneration(
                listOf(header.toString()),
                Options(
                    clangArgs = listOf(
                        "-x", "c",
                        "-target", "x86_64-pc-windows-msvc",
                        "-fshort-wchar",
                    ),
                    targetPackage = "test",
                    outputDir = output.toString(),
                    win32Mode = win32Mode,
                    dllMap = DllMap(
                        mapOf("test.dll" to DllEntry(functions = listOf("win32_wchar"))),
                    ),
                    useInitMethod = win32Mode,
                ),
            ) shouldBe KextractTool.SUCCESS
            return Files.walk(output).use { paths ->
                paths
                    .filter { it.fileName.toString().endsWith(".kt") }
                    .findFirst()
                    .orElseThrow()
                    .toFile()
                    .readText()
            }
        } finally {
            Files.deleteIfExists(header)
            output.toFile().deleteRecursively()
        }
    }

    "Win32 scalar generation" - {
        "maps C long to the Win32 ABI carrier and layout" {
            val src = generateWin32(
                "long win32_long(long value);",
                listOf("win32_long"),
            )
            src shouldContain "fun win32_long(arg0: Int): Int"
            src shouldContain "FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)"
        }

        "keeps C long long as Long and JAVA_LONG" {
            val src = generateWin32(
                "long long win32_long_long(long long value);",
                listOf("win32_long_long"),
            )

            src shouldContain "fun win32_long_long(arg0: Long): Long"
            src shouldContain "FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)"
        }

        "runs WCHAR typedef generation with the Win32 ABI only" {
            val win32Source = runGeneration(
                "typedef wchar_t WCHAR;\nWCHAR win32_wchar(WCHAR value);",
                win32Mode = true,
            )
            val defaultSource = runGeneration(
                "typedef wchar_t WCHAR;\nWCHAR win32_wchar(WCHAR value);",
                win32Mode = false,
            )

            win32Source shouldContain "val C_WCHAR: ValueLayout = ValueLayout.JAVA_CHAR"
            win32Source shouldContain "fun win32_wchar(arg0: Char): Char"
            win32Source shouldContain "FunctionDescriptor.of(ValueLayout.JAVA_CHAR, ValueLayout.JAVA_CHAR)"
            win32Source shouldContain "return '\\u0000'"
            defaultSource.contains("fun win32_wchar") shouldBe false
            defaultSource.contains("C_WCHAR") shouldBe false
        }
    }

    "Win32 init method generation" - {
        "sizes scalar global symbols and supplies the VarHandle offset" {
            val src = generateWin32(
                "extern int KxWin32Global;",
                emptyList(),
                useInitMethod = true,
            )

            src shouldContain "?.reinterpret(KxWin32Global_LAYOUT.byteSize())"
            src shouldContain "KxWin32Global_VH!!.get(_seg, 0L) as Int"
            src shouldContain "KxWin32Global_VH!!.set(_seg, 0L, value)"
        }

        "publishes initialized state after generated handle setup under a lock" {
            val src = generateWin32(
                "int initialize_me(int value);",
                listOf("initialize_me"),
                useInitMethod = true,
            )

            src shouldContain "@Volatile private var _initialized: Boolean = false"
            src shouldContain "@Synchronized\nfun init() {"
            src shouldContain "val _handle = initialize_me_HANDLE ?: return 0"

            val handleAssignment = src.indexOf("initialize_me_HANDLE =")
            val initializedPublication = src.indexOf("_initialized = true")
            (handleAssignment >= 0) shouldBe true
            (initializedPublication > handleAssignment) shouldBe true
        }
    }
})
