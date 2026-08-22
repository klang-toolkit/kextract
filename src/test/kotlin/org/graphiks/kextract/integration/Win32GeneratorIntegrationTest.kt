package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.graphiks.kextract.Type
import org.graphiks.kextract.cli.DllEntry
import org.graphiks.kextract.cli.DllMap
import org.graphiks.kextract.kotlin.KotlinGenerator
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.LayoutUtils
import org.graphiks.kextract.pipeline.NameMangler
import org.graphiks.kextract.kotlin.utils.TypeMapper
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
                "-x", "c++",
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

    "Win32 scalar generation" - {
        "maps C long to the host ABI carrier and layout" {
            val src = generateWin32(
                "long win32_long(long value);",
                listOf("win32_long"),
            )
            val isWindowsHost = System.getProperty("os.name").startsWith("Windows")
            val longCarrier = if (isWindowsHost) "Int" else "Long"
            val longLayout = if (isWindowsHost) "JAVA_INT" else "JAVA_LONG"

            src shouldContain "fun win32_long(arg0: $longCarrier): $longCarrier"
            src shouldContain "FunctionDescriptor.of(ValueLayout.$longLayout, ValueLayout.$longLayout)"
        }

        "keeps C long long as Long and JAVA_LONG" {
            val src = generateWin32(
                "long long win32_long_long(long long value);",
                listOf("win32_long_long"),
            )

            src shouldContain "fun win32_long_long(arg0: Long): Long"
            src shouldContain "FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)"
        }

        "maps wchar_t to Char and JAVA_CHAR" {
            val wchar = Type.primitive(Type.Primitive.Kind.WChar)

            TypeMapper.map(wchar) shouldBe "Char"
            LayoutUtils.layoutString(wchar) shouldBe "ValueLayout.JAVA_CHAR"
        }
    }

    "Win32 init method generation" - {
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
