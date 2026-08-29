package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.graphiks.kextract.cli.DllEntry
import org.graphiks.kextract.cli.DllMap
import org.graphiks.kextract.kotlin.KotlinGenerator
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.NameMangler
import org.graphiks.kextract.pipeline.Options
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import java.net.URLClassLoader
import java.nio.file.Files
import java.nio.file.Path

class Win32GeneratorIntegrationTest : FreeSpec({

    fun generateWin32(
        csource: String,
        functionNames: List<String>,
        useInitMethod: Boolean = false,
        dataSymbolNames: List<String> = emptyList(),
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
                mapOf("test.dll" to DllEntry(functions = functionNames, constants = dataSymbolNames)),
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

    fun compileAndInvokeGeneratedLong(
        generatedSource: String,
        probeSource: String,
        methodName: String,
    ): Long {
        val workspace = Files.createTempDirectory("kextract_win32_generated_")
        return try {
            val generated = workspace.resolve("Generated.kt")
            val probe = workspace.resolve("Win32Probe.kt")
            val output = Files.createDirectories(workspace.resolve("classes"))
            Files.writeString(generated, generatedSource)
            Files.writeString(probe, probeSource)
            val arguments = buildList {
                addAll(
                    listOf(
                        "-no-stdlib",
                        "-no-reflect",
                        "-jvm-target", "25",
                        "-classpath", System.getProperty("java.class.path"),
                        "-d", output.toString(),
                    ),
                )
                addAll(listOf(generated, probe).map(Path::toString))
            }
            K2JVMCompiler().exec(System.err, *arguments.toTypedArray()) shouldBe ExitCode.OK
            URLClassLoader(
                arrayOf(output.toUri().toURL()),
                Win32GeneratorIntegrationTest::class.java.classLoader,
            ).use { loader ->
                loader.loadClass("test.Win32ProbeKt")
                    .getMethod(methodName)
                    .invoke(null) as Long
            }
        } finally {
            workspace.toFile().deleteRecursively()
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
        "keeps open enum options neutral and reports unavailable closed enums when the declared DLL is missing" {
            val src = generateWin32(
                """
                typedef enum KxMissingClosedEnum : long long {
                    KxMissingClosedEnumFirst = 1
                } KxMissingClosedEnum;
                typedef enum __attribute__((flag_enum)) KxShortOptions : short {
                    KxShortOptionFirst = 1
                } KxShortOptions;
                typedef enum __attribute__((flag_enum)) KxByteOptions : signed char {
                    KxByteOptionFirst = 1
                } KxByteOptions;
                typedef enum __attribute__((flag_enum)) KxLongOptions : unsigned long long {
                    KxLongOptionFirst = 1
                } KxLongOptions;

                extern KxMissingClosedEnum KxMissingClosedValue;
                extern KxShortOptions KxMissingShortOptions;
                extern KxByteOptions KxMissingByteOptions;
                extern KxLongOptions KxMissingLongOptions;
                """.trimIndent(),
                emptyList(),
                useInitMethod = true,
                dataSymbolNames = listOf(
                    "KxMissingClosedValue",
                    "KxMissingShortOptions",
                    "KxMissingByteOptions",
                    "KxMissingLongOptions",
                ),
            )

            compileAndInvokeGeneratedLong(
                src,
                """
                package test

                fun readMissingDllOptionsDefaults(): Long {
                    init()
                    return if (
                        KxMissingShortOptions.rawValue == 0L &&
                        KxMissingByteOptions.rawValue == 0L &&
                        KxMissingLongOptions.rawValue == 0L
                    ) 1L else 0L
                }
                """.trimIndent(),
                "readMissingDllOptionsDefaults",
            ) shouldBe 1L

            val failure = shouldThrow<java.lang.reflect.InvocationTargetException> {
                compileAndInvokeGeneratedLong(
                    src,
                    """
                    package test

                    fun readMissingClosedEnum(): Long {
                        init()
                        KxMissingClosedValue
                        return 1L
                    }
                    """.trimIndent(),
                    "readMissingClosedEnum",
                )
            }
            failure.cause?.message shouldBe
                "Unavailable global binding 'KxMissingClosedValue': optional DLL or symbol is unavailable; make it available and call init() again"

            val setterFailure = shouldThrow<java.lang.reflect.InvocationTargetException> {
                compileAndInvokeGeneratedLong(
                    src,
                    """
                    package test

                    fun writeMissingClosedEnum(): Long {
                        init()
                        KxMissingClosedValue = KxMissingClosedEnum.KxMissingClosedEnumFirst
                        return 1L
                    }
                    """.trimIndent(),
                    "writeMissingClosedEnum",
                )
            }
            setterFailure.cause?.message shouldBe
                "Unavailable global binding 'KxMissingClosedValue': optional DLL or symbol is unavailable; make it available and call init() again"
        }

        "routes scalar globals listed in constants through their declared DLL lookup" {
            val src = generateWin32(
                "extern int KxDllMappedGlobal;",
                emptyList(),
                useInitMethod = true,
                dataSymbolNames = listOf("KxDllMappedGlobal"),
            )

            src shouldContain
                "\"KxDllMappedGlobal\" -> _DLL_TEST_DLL ?: SymbolLookup.loaderLookup()"
            src shouldContain
                "KxDllMappedGlobal_SEGMENT = _lookup(\"KxDllMappedGlobal\").find(\"KxDllMappedGlobal\")"
        }

        "returns typed primitive defaults after initializing an unavailable DLL" {
            val src = generateWin32(
                """
                extern signed char KxMissingByte;
                extern short KxMissingShort;
                """.trimIndent(),
                emptyList(),
                useInitMethod = true,
                dataSymbolNames = listOf("KxMissingByte", "KxMissingShort"),
            )

            compileAndInvokeGeneratedLong(
                src,
                """
                package test

                fun readMissingDllPrimitiveDefaults(): Long {
                    init()
                    return if (
                        KxMissingByte == 0.toByte() &&
                        KxMissingShort == 0.toShort()
                    ) 1L else 0L
                }
                """.trimIndent(),
                "readMissingDllPrimitiveDefaults",
            ) shouldBe 1L
        }

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
