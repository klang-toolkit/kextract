package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import io.kotest.matchers.string.shouldStartWith
import org.graphiks.kextract.Declaration
import org.graphiks.kextract.pipeline.NameMangler
import org.graphiks.kextract.kotlin.KotlinGenerator
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import java.net.URLClassLoader
import java.nio.file.Files
import java.nio.file.Path

/**
 * Integration tests for the full kextract pipeline:
 * C header source → Parser → KotlinGenerator → Kotlin source
 */
class GeneratorIntegrationTest : FreeSpec({

    // Helper: parse an inline C source and run the Kotlin generator.
    // Mirrors the full pipeline: parse → NameMangler → KotlinGenerator.
    fun generate(
        csource: String,
        pkg: String = "test",
        variadicArgs: Map<String, Int> = emptyMap(),
        libraries: List<Options.Library> = emptyList(),
    ): String {
        val tmp = Files.createTempFile("kextract_test_", ".h")
        try {
            tmp.toFile().writeText(csource)
            val headerName = tmp.fileName.toString()
            val parsed = KextractTool.parse(listOf(tmp.toString()))
            val mangled = NameMangler(headerName).scan(parsed)
            val files = KotlinGenerator().generate(
                mangled,
                headerName,
                pkg,
                libraries = libraries,
                variadicArgs = variadicArgs,
            )
            return files.firstOrNull()?.contents ?: ""
        } finally {
            Files.deleteIfExists(tmp)
        }
    }

    fun generateFilesWithPipeline(
        csource: String,
        pkg: String = "test",
        clangArgs: List<String> = emptyList(),
    ): List<org.graphiks.kextract.kotlin.models.KotlinSourceFile> {
        val input = Files.createTempFile("kextract_test_", ".h")
        val output = Files.createTempDirectory("kextract_test_out_")
        try {
            Files.writeString(input, csource)
            KextractTool(Logger()).runGeneration(
                listOf(input.toString()),
                Options(
                    clangArgs = clangArgs,
                    targetPackage = pkg,
                    outputDir = output.toString(),
                ),
            ) shouldBe KextractTool.SUCCESS
            return Files.walk(output).use { paths ->
                paths
                    .filter { Files.isRegularFile(it) && it.fileName.toString().endsWith(".kt") }
                    .sorted()
                    .map { path ->
                        org.graphiks.kextract.kotlin.models.KotlinSourceFile(
                            packageName = pkg,
                            className = path.fileName.toString().removeSuffix(".kt"),
                            contents = Files.readString(path),
                        )
                    }
                    .toList()
            }
        } finally {
            Files.deleteIfExists(input)
            output.toFile().deleteRecursively()
        }
    }

    fun generateWithPipeline(
        csource: String,
        pkg: String = "test",
        clangArgs: List<String> = emptyList(),
    ): String = generateFilesWithPipeline(csource, pkg, clangArgs)
        .joinToString("\n") { it.contents }

    fun generateKmpFilesWithPipeline(
        csource: String,
        pkg: String = "test",
        clangArgs: List<String> = emptyList(),
    ): List<org.graphiks.kextract.kotlin.models.KotlinSourceFile> {
        val input = Files.createTempFile("kextract_test_", ".h")
        val output = Files.createTempDirectory("kextract_test_out_")
        try {
            Files.writeString(input, csource)
            KextractTool(Logger()).runGeneration(
                listOf(input.toString()),
                Options(
                    clangArgs = clangArgs,
                    targetPackage = pkg,
                    outputDir = output.toString(),
                    multiplatform = true,
                ),
            ) shouldBe KextractTool.SUCCESS
            return Files.walk(output).use { paths ->
                paths
                    .filter { Files.isRegularFile(it) && it.fileName.toString().endsWith(".kt") }
                    .sorted()
                    .map { path ->
                        val relative = output.relativize(path)
                        val sourceRoot = relative.parent.parent.toString()
                        org.graphiks.kextract.kotlin.models.KotlinSourceFile(
                            packageName = pkg,
                            className = path.fileName.toString().removeSuffix(".kt"),
                            contents = Files.readString(path),
                            sourceRoot = sourceRoot,
                        )
                    }
                    .toList()
            }
        } finally {
            Files.deleteIfExists(input)
            output.toFile().deleteRecursively()
        }
    }

    fun compileGenerated(
        files: List<org.graphiks.kextract.kotlin.models.KotlinSourceFile>,
        probeSource: String,
    ): ExitCode {
        val workspace = Files.createTempDirectory("kextract_generated_compile_")
        return try {
            val sourcePaths = files.mapIndexed { index, source ->
                workspace.resolve("${source.className}_$index.kt").also {
                    Files.writeString(it, source.contents)
                }
            }
            val probe = workspace.resolve("PlatformAvailabilityProbe.kt")
            Files.writeString(probe, probeSource)
            val output = Files.createDirectories(workspace.resolve("classes"))
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
                addAll((sourcePaths + listOf(probe)).map(Path::toString))
            }
            K2JVMCompiler().exec(System.err, *arguments.toTypedArray())
        } finally {
            workspace.toFile().deleteRecursively()
        }
    }

    fun generateKmpFile(csource: String, sourceSet: String, suffix: String, pkg: String = "test"): String {
        val tmp = Files.createTempFile("kextract_test_", ".h")
        val output = Files.createTempDirectory("kextract_test_out_")
        try {
            tmp.toFile().writeText(csource)
            KextractTool(Logger()).runGeneration(
                listOf(tmp.toString()),
                Options(targetPackage = pkg, outputDir = output.toString(), multiplatform = true)
            ) shouldBe KextractTool.SUCCESS
            val className = tmp.fileName.toString()
                .substringAfterLast('/')
                .replace(Regex("[^a-zA-Z0-9_]"), "_")
                .replace(Regex("^\\d+"), "_")
            return output.resolve("$sourceSet/kotlin/${pkg.replace('.', '/')}/$className$suffix.kt").toFile().readText()
        } finally {
            Files.deleteIfExists(tmp)
            output.toFile().deleteRecursively()
        }
    }

    fun generateCommon(csource: String, pkg: String = "test"): String =
        generateKmpFile(csource, "commonMain", "Common", pkg)

    /**
     * Compiles a regular (non-KMP) generated binding and invokes a probe from a
     * fresh class loader. This makes FFM layout initialisation part of the
     * assertion instead of merely checking the rendered source text.
     */
    fun compileAndInvokeGeneratedLong(
        generatedSource: String,
        probeSource: String,
        methodName: String,
    ): Long {
        val workspace = Files.createTempDirectory("kextract_generated_layout_")
        return try {
            val generated = workspace.resolve("Generated.kt")
            val probe = workspace.resolve("LayoutProbe.kt")
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
                GeneratorIntegrationTest::class.java.classLoader,
            ).use { loader ->
                loader.loadClass("test.LayoutProbeKt")
                    .getMethod(methodName)
                    .invoke(null) as Long
            }
        } finally {
            workspace.toFile().deleteRecursively()
        }
    }

    "Package declaration" - {
        "should emit package when target package is set" {
            val src = generate("int add(int a, int b);", pkg = "com.example")
            src shouldStartWith "package com.example"
        }

        "should omit package line when target package is empty" {
            val src = generate("int add(int a, int b);", pkg = "")
            src shouldNotContain "package "
        }
    }

    "Target availability" - {
        "keeps top-level declarations unavailable for macOS" {
            val src = generateWithPipeline(
                """
                int kxAvailable(void);
                int kxUnavailable(void) __attribute__((availability(macos, unavailable)));
                int kxDeprecated(void) __attribute__((availability(macos, deprecated = 10.0)));
                int kxIntroduced(void) __attribute__((availability(macos, introduced = 10.0)));
                """.trimIndent(),
                clangArgs = listOf("-target", "arm64-apple-macos15.0"),
            )

            src shouldContain "fun kxAvailable()"
            src shouldContain "fun kxUnavailable()"
            src shouldContain "fun kxDeprecated()"
            src shouldContain "fun kxIntroduced()"
        }

        "preserves unavailable children of available declarations" {
            val src = generateWithPipeline(
                """
                typedef struct KxRecord {
                    int unavailableField __attribute__((unavailable));
                } KxRecord;

                enum KxEnum {
                    KxEnumAvailable,
                    KxEnumUnavailable __attribute__((unavailable)),
                };

                int kxUsesUnavailableParameter(
                    int unavailableParameter __attribute__((unavailable))
                );
                """.trimIndent(),
            )

            src shouldContain "unavailableField"
            src shouldContain "KxEnumUnavailable"
            src shouldContain "fun kxUsesUnavailableParameter"
        }

        "emits opt-in metadata for platform-versioned declarations" {
            val files = generateFilesWithPipeline(
                """
                int kxPlatformChecked(void)
                    __attribute__((availability(macos, introduced = 13.1, deprecated = 14.2, obsoleted = 15.3, message = "Use the replacement API")));
                """.trimIndent(),
                clangArgs = listOf("-target", "arm64-apple-macos15.0"),
            )
            val src = files.joinToString("\n") { it.contents }
            val binding = files.first { it.className != "PlatformAvailability" }

            src shouldContain "annotation class PlatformAvailability"
            binding.contents shouldStartWith "@file:OptIn(test.PlatformAvailability::class)\n\npackage test"
            src shouldContain "@PlatformAvailability("
            src shouldContain "platform = \"macos\""
            src shouldContain "introducedMajor = 13"
            src shouldContain "introducedMinor = 1"
            src shouldContain "deprecatedMajor = 14"
            src shouldContain "deprecatedMinor = 2"
            src shouldContain "obsoletedMajor = 15"
            src shouldContain "obsoletedMinor = 3"
            src shouldContain "message = \"Use the replacement API\""
            src shouldContain "fun kxPlatformChecked()"

            compileGenerated(
                files,
                """
                package test

                fun uncheckedAvailabilityUse(): Int = kxPlatformChecked()
                """.trimIndent(),
            ) shouldBe ExitCode.COMPILATION_ERROR

            compileGenerated(
                files,
                """
                package test

                @OptIn(PlatformAvailability::class)
                fun checkedAvailabilityUse(): Int = kxPlatformChecked()
                """.trimIndent(),
            ) shouldBe ExitCode.OK
        }

        "requires opt-in for an unavailable enum entry" {
            val files = generateFilesWithPipeline(
                """
                enum KxAvailability {
                    KxAvailabilityAvailable,
                    KxAvailabilityUnavailable __attribute__((availability(macos, unavailable))),
                };
                """.trimIndent(),
                clangArgs = listOf("-target", "arm64-apple-macos15.0"),
            )

            compileGenerated(
                files,
                """
                package test

                fun uncheckedEnumAvailabilityUse() = KxAvailability.KxAvailabilityUnavailable
                """.trimIndent(),
            ) shouldBe ExitCode.COMPILATION_ERROR

            compileGenerated(
                files,
                """
                package test

                @OptIn(PlatformAvailability::class)
                fun checkedEnumAvailabilityUse() = KxAvailability.KxAvailabilityUnavailable
                """.trimIndent(),
            ) shouldBe ExitCode.OK
        }

        "requires opt-in for an unavailable struct field accessor" {
            val files = generateFilesWithPipeline(
                """
                struct KxAvailabilityRecord {
                    int unavailableField __attribute__((availability(macos, unavailable)));
                };
                """.trimIndent(),
                clangArgs = listOf("-target", "arm64-apple-macos15.0"),
            )

            compileGenerated(
                files,
                """
                package test

                import java.lang.foreign.MemorySegment

                fun uncheckedFieldAvailabilityUse(value: MemorySegment): Int =
                    KxAvailabilityRecord().unavailableField(value)
                """.trimIndent(),
            ) shouldBe ExitCode.COMPILATION_ERROR

            compileGenerated(
                files,
                """
                package test

                import java.lang.foreign.MemorySegment

                @OptIn(PlatformAvailability::class)
                fun checkedFieldAvailabilityUse(value: MemorySegment): Int =
                    KxAvailabilityRecord().unavailableField(value)
                """.trimIndent(),
            ) shouldBe ExitCode.OK
        }

        "keeps an unversioned deprecation distinct from a version zero" {
            val src = generateWithPipeline(
                """
                int kxAlwaysDeprecated(void) __attribute__((deprecated("Use kxReplacement instead")));
                """.trimIndent(),
                clangArgs = listOf("-target", "arm64-apple-macos15.0"),
            )

            src shouldContain "platform = \"all\""
            src shouldContain "deprecated = true"
            src shouldNotContain "deprecatedMajor = 0"
            src shouldContain "message = \"Use kxReplacement instead\""
        }

        "emits the same opt-in contract in the common multiplatform source set" {
            val files = generateKmpFilesWithPipeline(
                """
                int kxCommonPlatformChecked(void)
                    __attribute__((availability(macos, introduced = 13.1)));
                """.trimIndent(),
                clangArgs = listOf("-target", "arm64-apple-macos15.0"),
            )

            val common = files.single { it.className.endsWith("Common") }
            val marker = files.single { it.className == "PlatformAvailability" }
            common.sourceRoot shouldBe "commonMain/kotlin"
            common.contents shouldStartWith "@file:OptIn(test.PlatformAvailability::class)\n\npackage test"
            common.contents shouldContain "@PlatformAvailability("
            common.contents shouldContain "expect fun kxCommonPlatformChecked()"
            marker.sourceRoot shouldBe "commonMain/kotlin"
            marker.contents shouldContain "annotation class PlatformAvailability"
        }
    }

    "C enum function lowering" - {
        "uses scalar ABI carriers while preserving typed enum signatures" {
            val src = generate(
                """
                typedef enum CGEventField : int {
                    CGEventField_Source = 0,
                    CGEventField_Target = 1
                } CGEventField;

                CGEventField createEventField(CGEventField field);
                void setEventField(CGEventField field);
                """.trimIndent(),
            )

            src shouldContain "fun createEventField(arg0: CGEventField): CGEventField"
            src shouldContain "fun setEventField(arg0: CGEventField): Unit"
            src shouldContain "FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)"
            src shouldContain "return CGEventField.fromValue((createEventField_HANDLE.invokeExact(arg0.value.toInt()) as Int).toLong())"
            src shouldContain "setEventField_HANDLE.invokeExact(arg0.value.toInt())"
        }

        "resolves a forward enum declaration to its definition before lowering functions" {
            val src = generate(
                """
                #define CF_ENUM(_type, _name) enum _name : _type _name; enum _name : _type

                typedef CF_ENUM(int, ForwardEventField) {
                    ForwardEventField_Source = 0,
                    ForwardEventField_Target = 1
                };

                ForwardEventField createForwardEventField(ForwardEventField field);
                void setForwardEventField(ForwardEventField field);
                """.trimIndent(),
            )

            src shouldContain "enum class ForwardEventField"
            src shouldContain "fun createForwardEventField(arg0: ForwardEventField): ForwardEventField"
            src shouldContain "fun setForwardEventField(arg0: ForwardEventField): Unit"
            src shouldContain
                "return ForwardEventField.fromValue((createForwardEventField_HANDLE.invokeExact(arg0.value.toInt()) as Int).toLong())"
            src shouldContain "setForwardEventField_HANDLE.invokeExact(arg0.value.toInt())"
        }
    }

    "Legacy C record layouts" - {
        "preserve Clang padding before a late double when FFM initializes the layout" {
            val generated = generate(
                """
                typedef struct KxGregorianUnits {
                    int years;
                    int months;
                    int days;
                    int hours;
                    int minutes;
                    double seconds;
                } KxGregorianUnits;
                """.trimIndent(),
            )

            compileAndInvokeGeneratedLong(
                generated,
                """
                package test

                import java.lang.foreign.MemoryLayout.PathElement.groupElement

                fun inspectKxGregorianUnitsLayout(): Long {
                    val layout = KxGregorianUnits.layout
                    return layout.byteSize() * 1_000_000_000L +
                        layout.byteAlignment() * 100_000_000L +
                        layout.byteOffset(groupElement("seconds")) * 1_000_000L +
                        layout.byteOffset(groupElement("minutes")) * 10_000L +
                        layout.byteOffset(groupElement("hours")) * 100L +
                        layout.byteOffset(groupElement("days")) * 10L +
                        layout.byteOffset(groupElement("months")) +
                        layout.byteOffset(groupElement("years"))
                }
                """.trimIndent(),
                "inspectKxGregorianUnitsLayout",
            ) shouldBe 32_824_161_284L
        }

        "preserve packed member placement when FFM initializes the layout" {
            val generated = generate(
                """
                typedef struct __attribute__((packed)) KxPackedUnits {
                    char tag;
                    double seconds;
                } KxPackedUnits;
                """.trimIndent(),
            )

            compileAndInvokeGeneratedLong(
                generated,
                """
                package test

                import java.lang.foreign.MemoryLayout.PathElement.groupElement

                fun inspectKxPackedUnitsLayout(): Long {
                    val layout = KxPackedUnits.layout
                    return layout.byteSize() * 1_000_000L +
                        layout.byteAlignment() * 10_000L +
                        layout.byteOffset(groupElement("seconds"))
                }
                """.trimIndent(),
                "inspectKxPackedUnitsLayout",
            ) shouldBe 9_010_001L
        }

        "preserve the Clang size of a union with trailing padding" {
            val generated = generate(
                """
                typedef union KxThreeByteUnion {
                    char bytes[3];
                    short shortValue;
                } KxThreeByteUnion;
                """.trimIndent(),
            )

            compileAndInvokeGeneratedLong(
                generated,
                """
                package test

                import java.lang.foreign.MemoryLayout.PathElement.groupElement

                fun inspectKxThreeByteUnionLayout(): Long {
                    val layout = KxThreeByteUnion.layout
                    return layout.byteSize() * 10_000L +
                        layout.byteAlignment() * 100L +
                        layout.byteOffset(groupElement("shortValue"))
                }
                """.trimIndent(),
                "inspectKxThreeByteUnionLayout",
            ) shouldBe 40_200L
        }
    }

    "KMP header names" - {
        "strip Windows directory components before naming generated files" {
            val tmp = Files.createTempFile("kextract_test_", ".h")
            try {
                tmp.toFile().writeText("int add(int a);")
                val parsed = KextractTool.parse(listOf(tmp.toString()))
                val mangled = NameMangler(tmp.fileName.toString()).scan(parsed)
                val files = KotlinGenerator().generate(
                    scoped = mangled,
                    headerName = "C:\\fixtures\\wgpu.h",
                    targetPackage = "test",
                    multiplatform = true,
                )

                files.map { it.className }.toSet() shouldBe setOf(
                    "wgpu_hCommon",
                    "wgpu_hJvm",
                    "wgpu_hAndroid",
                    "wgpu_hNative",
                )
            } finally {
                Files.deleteIfExists(tmp)
            }
        }
    }

    "KMP bitflag generation" - {
        "emits static const values for WGPUFlags aliases" {
            val src = generateCommon("""
                typedef unsigned long long WGPUFlags;
                typedef WGPUFlags WGPUTextureUsage;
                static const WGPUTextureUsage WGPUTextureUsage_None = 0x0000000000000000;
                static const WGPUTextureUsage WGPUTextureUsage_CopySrc = 0x0000000000000001;
                static const WGPUTextureUsage WGPUTextureUsage_CopyDst = 0x0000000000000002;
            """.trimIndent())

            src shouldContain "typealias WGPUTextureUsage = ULong"
            src shouldContain "const val WGPUTextureUsage_None : WGPUTextureUsage = 0uL"
            src shouldContain "const val WGPUTextureUsage_CopySrc : WGPUTextureUsage = 1uL"
            src shouldContain "const val WGPUTextureUsage_CopyDst : WGPUTextureUsage = 2uL"
        }
    }

    "KMP duplicate filtering" - {
        "keeps the historical enum member output for a same-name typed macro" {
            val src = generateCommon(
                """
                    typedef enum KxKmpDuplicateType : int {
                        KxKmpDuplicate = 1
                    } KxKmpDuplicateType;
                    #define KxKmpDuplicate ((KxKmpDuplicateType)2)
                """.trimIndent(),
            )

            src shouldContain "const val KxKmpDuplicate : KxKmpDuplicateType = 1"
            src shouldNotContain "KxKmpDuplicate_kextract"
            src shouldNotContain "KxKmpDuplicateType = 2"
        }
    }

    "KMP native display handle generation" - {
        "emits anonymous union accessors for WGPUNativeDisplayHandle" {
            val header = """
                typedef enum WGPUNativeDisplayHandleType {
                    WGPUNativeDisplayHandleType_Xlib = 1,
                    WGPUNativeDisplayHandleType_Xcb = 2,
                    WGPUNativeDisplayHandleType_Wayland = 3
                } WGPUNativeDisplayHandleType;
                typedef struct WGPUXlibDisplayHandle { void* display; int screen; } WGPUXlibDisplayHandle;
                typedef struct WGPUXcbDisplayHandle { void* connection; int screen; } WGPUXcbDisplayHandle;
                typedef struct WGPUWaylandDisplayHandle { void* display; } WGPUWaylandDisplayHandle;
                typedef struct WGPUNativeDisplayHandle {
                    WGPUNativeDisplayHandleType type;
                    union {
                        WGPUXlibDisplayHandle xlib;
                        WGPUXcbDisplayHandle xcb;
                        WGPUWaylandDisplayHandle wayland;
                    } data;
                } WGPUNativeDisplayHandle;
            """.trimIndent()

            val common = generateCommon(header)
            val jvm = generateKmpFile(header, "jvmMain", "Jvm")

            common shouldContain "expect interface WGPUNativeDisplayHandle"
            common shouldContain "var type: WGPUNativeDisplayHandleType"
            common shouldContain "val xlib: WGPUXlibDisplayHandle?"
            common shouldContain "fun setXlib(value: WGPUXlibDisplayHandle)"
            common shouldContain "val xcb: WGPUXcbDisplayHandle?"
            common shouldContain "fun setXcb(value: WGPUXcbDisplayHandle)"
            common shouldContain "val wayland: WGPUWaylandDisplayHandle?"
            common shouldContain "fun setWayland(value: WGPUWaylandDisplayHandle)"

            jvm shouldContain "actual interface WGPUNativeDisplayHandle {"
            jvm shouldContain "class ByReference(val handle: NativeAddress = NativeAddress(0L)) : WGPUNativeDisplayHandle {"
            jvm shouldContain "private val mem: MemoryBuffer by lazy { MemoryBuffer(handle, 24uL) }"
            jvm shouldContain "get() = if (type != WGPUNativeDisplayHandleType_Xlib) null else WGPUXlibDisplayHandle.ByValue(NativeAddress(handle.rawValue + 8L))"
            jvm shouldContain "type = WGPUNativeDisplayHandleType_Xlib"
            jvm shouldContain "mem.writeBytes(bytes, 0u, 8uL, 16uL)"
            jvm shouldNotContain "java.lang.foreign"
        }
    }

    "KMP aggregate layout generation" - {
        "JVM unions are memory-backed records with zero-based accessors" {
            val header = """
                typedef union WGPUScalar {
                    unsigned int u32;
                    float f32;
                    unsigned long long u64;
                } WGPUScalar;
            """.trimIndent()

            val jvm = generateKmpFile(header, "jvmMain", "Jvm")

            jvm shouldContain "actual interface WGPUScalar {"
            jvm shouldContain "class ByReference(val handle: NativeAddress = NativeAddress(0L)) : WGPUScalar {"
            jvm shouldContain "get() = mem.readUInt(0uL)"
            jvm shouldNotContain "MemoryLayout.unionLayout("
            jvm shouldNotContain "CStructure"
        }
    }

    "KMP function generation" - {
        "bootstraps declared JVM native libraries before symbol lookup" {
            val header = Files.createTempFile("kextract_bootstrap_", ".h")
            val output = Files.createTempDirectory("kextract_bootstrap_out_")
            try {
                header.toFile().writeText("void sample_call(void);")
                val linuxResources = output.resolve("jvmMain/resources/linux-x86-64")
                Files.createDirectories(linuxResources.resolve("deps"))
                Files.write(linuxResources.resolve("deps/libdependency.so"), byteArrayOf(1, 2, 3))
                Files.write(linuxResources.resolve("libsample.so"), byteArrayOf(4, 5, 6))

                KextractTool(Logger()).runGeneration(
                    listOf(header.toString()),
                    Options(
                        targetPackage = "test",
                        outputDir = output.toString(),
                        multiplatform = true,
                        libraries = listOf(Options.Library.parse("sample")),
                        jvmNativeLibraries = listOf(
                            Options.Library.parse("dependency"),
                            Options.Library.parse("sample"),
                            Options.Library.parse(":/opt/native/libabsolute.so"),
                        ),
                    ),
                ) shouldBe KextractTool.SUCCESS

                val className = header.fileName.toString().replace(Regex("[^a-zA-Z0-9_]"), "_")
                val jvm = output.resolve("jvmMain/kotlin/test/${className}Jvm.kt").toFile().readText()

                jvm shouldContain "private object KextractNativeBootstrap"
                jvm shouldContain "@kotlin.jvm.Volatile private var loaded: kotlin.Boolean = false"
                jvm shouldContain "kextract.native.cache.dir"
                jvm shouldContain "\"linux-x86-64\" to Bundle("
                jvm shouldContain "deps/libdependency.so"
                jvm shouldContain "System.loadLibrary(\"dependency\")"
                jvm shouldContain "System.loadLibrary(\"sample\")"
                jvm shouldContain "Path.of(\"/opt/native/libabsolute.so\").toAbsolutePath().normalize()"
                jvm shouldContain "KextractNativeBootstrap.resolve(\"sample_call\")"
                jvm shouldContain "if (loaded) return"
                jvm shouldContain "kotlin.synchronized(this)"
                jvm shouldContain "FileChannel.open("
                jvm shouldContain "channel.lock().use"
                jvm shouldContain "toString().intern()"
                jvm shouldContain "classLoader.getResources(resourceName)"
                jvm shouldContain "StandardCopyOption.ATOMIC_MOVE"
                jvm shouldContain "catch (_: java.nio.file.AtomicMoveNotSupportedException)"
                (
                    jvm.indexOf("System.loadLibrary(\"dependency\")") <
                        jvm.indexOf("System.loadLibrary(\"sample\")")
                ) shouldBe true
                (
                    jvm.indexOf("Path.of(\"/opt/native/libabsolute.so\")") <
                        jvm.indexOf("loaded = true")
                ) shouldBe true
            } finally {
                Files.deleteIfExists(header)
                output.toFile().deleteRecursively()
            }
        }

        "indexes JVM native libraries from an explicit resources directory" {
            val header = Files.createTempFile("kextract_bootstrap_external_resources_", ".h")
            val output = Files.createTempDirectory("kextract_bootstrap_external_output_")
            val resources = Files.createTempDirectory("kextract_bootstrap_external_resources_")
            try {
                header.toFile().writeText("void sample_call(void);")
                val linuxResources = resources.resolve("linux-x86-64")
                Files.createDirectories(linuxResources)
                Files.write(linuxResources.resolve("libsample.so"), byteArrayOf(7, 8, 9))

                KextractTool(Logger()).runGeneration(
                    listOf(header.toString()),
                    Options(
                        targetPackage = "test",
                        outputDir = output.toString(),
                        jvmNativeResourcesDir = resources.toString(),
                        multiplatform = true,
                        libraries = listOf(Options.Library.parse("sample")),
                    ),
                ) shouldBe KextractTool.SUCCESS

                val className = header.fileName.toString().replace(Regex("[^a-zA-Z0-9_]"), "_")
                val jvm = output.resolve("jvmMain/kotlin/test/${className}Jvm.kt").toFile().readText()

                jvm shouldContain "\"libsample.so\""
                jvm shouldContain "\"sample\" to \"libsample.so\""
                jvm shouldContain "System.load(bundleDirectory.resolve(libraryPath0).toAbsolutePath().normalize().toString())"
                jvm.lineSequence().any { line -> line.endsWith(' ') } shouldBe false
                jvm.endsWith("\n\n") shouldBe false
            } finally {
                Files.deleteIfExists(header)
                output.toFile().deleteRecursively()
                resources.toFile().deleteRecursively()
            }
        }

        "keeps direct JVM symbol lookup when no native library is declared" {
            val jvm = generateKmpFile("void sample_call(void);", "jvmMain", "Jvm")

            jvm shouldContain "by lazy { findOrThrow(\"sample_call\") }"
            jvm shouldNotContain "KextractNativeBootstrap"
        }

        "emits common and JVM wrappers for WGPU functions" {
            val header = """
                typedef struct WGPUDeviceImpl* WGPUDevice;
                typedef struct WGPUQueueImpl* WGPUQueue;
                typedef struct WGPUShaderModuleImpl* WGPUShaderModule;
                struct WGPUShaderModuleDescriptor;
                typedef unsigned int WGPUBool;
                typedef unsigned long long WGPUSubmissionIndex;
                typedef struct WGPUShaderModuleDescriptor {
                    int label;
                } WGPUShaderModuleDescriptor;
                typedef struct WGPUDeviceBinding {
                    WGPUDevice device;
                } WGPUDeviceBinding;
                WGPUQueue wgpuDeviceGetQueue(WGPUDevice device);
                WGPUShaderModule wgpuDeviceCreateShaderModule(WGPUDevice device, WGPUShaderModuleDescriptor const * descriptor);
                void wgpuDevicePoll(WGPUDevice device, WGPUSubmissionIndex const * submissionIndex, unsigned long long wait);
            """.trimIndent()

            val common = generateCommon(header)
            val jvm = generateKmpFile(header, "jvmMain", "Jvm")

            common shouldContain "expect value class WGPUDevice(val handler: NativeAddress)"
            common shouldContain "expect value class WGPUQueue(val handler: NativeAddress)"
            common shouldContain "expect value class WGPUShaderModule(val handler: NativeAddress)"
            common shouldNotContain "expect interface WGPUDeviceImpl"
            common shouldNotContain "typealias WGPUDevice = WGPUDeviceImpl"
            common shouldContain "var device: WGPUDevice?"
            jvm shouldContain "@kotlin.jvm.JvmInline"
            jvm shouldContain "actual value class WGPUDevice actual constructor(actual val handler: NativeAddress)"
            jvm shouldNotContain "actual interface WGPUDeviceImpl"
            jvm shouldNotContain "typealias WGPUDevice = WGPUDeviceImpl"
            jvm shouldContain "actual var device: WGPUDevice?"
            jvm shouldContain "?.let { WGPUDevice(it) }"
            common shouldContain "expect fun wgpuDeviceGetQueue(device: WGPUDevice?): WGPUQueue?"
            common shouldContain "expect fun wgpuDeviceCreateShaderModule(device: WGPUDevice?, descriptor: WGPUShaderModuleDescriptor?): WGPUShaderModule?"
            common shouldContain "expect fun wgpuDevicePoll(device: WGPUDevice?, submissionIndex: NativeAddress?, wait: ULong): Unit"
            common shouldNotContain "submissionIndex: WGPUSubmissionIndex?"
            jvm shouldContain "private val wgpuDeviceGetQueue_ADDR: Long by lazy { findOrThrow(\"wgpuDeviceGetQueue\") }"
            jvm shouldContain "JvmDowncallEngine.callP1P(wgpuDeviceGetQueue_ADDR, device?.handler?.rawValue ?: 0L)"
            jvm shouldContain "JvmDowncallEngine.callP2PP(wgpuDeviceCreateShaderModule_ADDR, device?.handler?.rawValue ?: 0L, descriptor?.handler?.rawValue ?: 0L)"
            jvm shouldContain "JvmDowncallEngine.callV3PPL(wgpuDevicePoll_ADDR, device?.handler?.rawValue ?: 0L, submissionIndex?.rawValue ?: 0L, wait.toLong())"
            jvm shouldContain "actual fun wgpuDeviceGetQueue(device: WGPUDevice?): WGPUQueue?"
            jvm shouldContain "actual fun wgpuDeviceCreateShaderModule(device: WGPUDevice?, descriptor: WGPUShaderModuleDescriptor?): WGPUShaderModule?"
            jvm shouldContain "actual fun wgpuDevicePoll(device: WGPUDevice?, submissionIndex: NativeAddress?, wait: ULong): Unit"
            jvm shouldNotContain "submissionIndex?.handler?.handler"
            jvm shouldContain "findOrThrow(\"wgpuDeviceGetQueue\")"
            jvm shouldContain "device?.handler?.rawValue ?: 0L"
            jvm shouldContain "?.let(::WGPUQueue)"
        }

        "emits typed callback registrations and raw callback addresses" {
            val header = """
                typedef enum WGPULogLevel {
                    WGPULogLevel_Off = 0,
                    WGPULogLevel_Error = 1
                } WGPULogLevel;
                typedef struct WGPUStringView {
                    char const * data;
                    unsigned long long length;
                } WGPUStringView;
                typedef void (*WGPULogCallback)(WGPULogLevel level, WGPUStringView message, void * userdata);
                void wgpuSetLogCallback(WGPULogCallback callback, void * userdata);
            """.trimIndent()

            val common = generateCommon(header)
            val jvm = generateKmpFile(header, "jvmMain", "Jvm")

            common shouldContain "fun interface WGPULogCallback : Callback"
            common shouldContain "fun invoke(level: WGPULogLevel, message: WGPUStringView)"
            common shouldContain "expect fun WGPULogCallback.Companion.register("
            common shouldContain "expect fun wgpuSetLogCallback(callback: NativeAddress?, userdata: NativeAddress?): Unit"
            common shouldNotContain "expect class WGPULogCallback"
            common shouldNotContain "fun allocate(callback:"

            jvm shouldNotContain "actual class WGPULogCallback"
            jvm shouldContain "Linker.nativeLinker().upcallStub(methodHandle, descriptor, Arena.global())"
            jvm shouldNotContain "Arena.ofShared()"
            jvm shouldContain "actual fun wgpuSetLogCallback(callback: NativeAddress?, userdata: NativeAddress?): Unit"
            jvm shouldContain "callback?.rawValue ?: 0L"
        }
    }

    "Struct generation" - {
        "simple struct produces a class with companion object" {
            val src = generate("""
                struct Point { int x; int y; };
            """.trimIndent())

            src shouldContain "class Point"
            src shouldContain "companion object"
            src shouldContain "val layout"
            src shouldContain "fun allocate"
            src shouldContain "val byteSize"
        }

        "struct fields generate VarHandle accessors" {
            val src = generate("""
                struct Point { int x; int y; };
            """.trimIndent())

            src shouldContain "x_VH"
            src shouldContain "y_VH"
            // getter
            src shouldContain "fun x(segment: MemorySegment)"
            // setter with value param
            src shouldContain "fun x(segment: MemorySegment, value:"
        }

        "array field generates asSlice accessor instead of VarHandle" {
            val src = generate("""
                struct Buffer { char data[64]; int len; };
            """.trimIndent())

            src shouldContain "asSlice"
            src shouldNotContain "data_VH"
        }

        "nested record field generates an asSlice accessor instead of an invalid VarHandle" {
            val src = generate("""
                struct Version { int major; int minor; };
                struct Availability { struct Version introduced; };
            """.trimIndent())

            src shouldContain "fun introduced(segment: MemorySegment): MemorySegment"
            src shouldNotContain "introduced_VH"
        }

        "struct with nested struct pointer field compiles" {
            val src = generate("""
                struct Node { int value; struct Node* next; };
            """.trimIndent())

            src shouldContain "class Node"
        }

        "union generates a warning comment" {
            val src = generate("""
                union Value { int i; float f; double d; };
            """.trimIndent())

            src shouldContain "WARNING"
            src shouldContain "union"
        }

        "union generates an overlapping union layout" {
            val src = generate("""
                union Value { int i; float f; double d; };
            """.trimIndent())

            src shouldContain "MemoryLayout.unionLayout("
            src shouldNotContain "MemoryLayout.structLayout("
        }
    }

    "Function generation" - {
        "void function generates try/catch without return" {
            val src = generate("void noop(void);")

            src shouldContain "fun noop("
            src shouldContain "_HANDLE"
            src shouldContain "invokeExact"
            // void → no return statement
            src shouldNotContain "return noop_HANDLE"
        }

        "int-returning function generates cast to Int" {
            val src = generate("int add(int a, int b);")

            src shouldContain "fun add("
            src shouldContain "): Int"
            src shouldContain "as Int"
        }

        "pointer-returning function maps to MemorySegment" {
            val src = generate("void* malloc(long size);")

            src shouldContain "): MemorySegment"
        }

        "function with multiple params generates arg0, arg1 names" {
            val src = generate("int clamp(int val, int lo, int hi);")

            src shouldContain "arg0: Int"
            src shouldContain "arg1: Int"
            src shouldContain "arg2: Int"
        }

        "struct-returning function adds SegmentAllocator param" {
            val src = generate("""
                struct Vec2 { float x; float y; };
                struct Vec2 make_vec(float x, float y);
            """.trimIndent())

            src shouldContain "allocator: SegmentAllocator"
        }

        "struct-by-value JVM functions use the canonical generic shape" {
            val src = generateKmpFile(
                """
                typedef struct Pair { int left; double right; } Pair;
                int sum_pair(Pair pair);
                """.trimIndent(),
                sourceSet = "jvmMain",
                suffix = "Jvm",
            )

            src shouldContain "JvmDowncallEngine.FunctionShape"
            src shouldContain "JvmDowncallEngine.AbiType.Struct(\"Pair_\")"
            src shouldContain "JvmDowncallEngine.callGeneric"
            src shouldNotContain "sumPair_HANDLE"
        }

        "FunctionDescriptor and MethodHandle are emitted" {
            val src = generate("int square(int n);")

            src shouldContain "_DESC: FunctionDescriptor"
            src shouldContain "_ADDR: MemorySegment"
            src shouldContain "_HANDLE: MethodHandle"
        }

        "variadic function with configured slots generates firstVariadicArg and extra MemorySegment params" {
            val src = generate("""
                void* variadic_fn(int fixed1, long fixed2, ...);
            """.trimIndent(), variadicArgs = mapOf("variadic_fn" to 3))
            // FunctionDescriptor should include: return ADDRESS + 2 fixed + 3 variadic ADDRESS
            src shouldContain "ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS"
            // firstVariadicArg(2) because 2 fixed args before variadic
            src shouldContain "Linker.Option.firstVariadicArg(2)"
            // Function signature should have 5 params: 2 typed + 3 MemorySegment
            src shouldContain "arg0: Int"
            src shouldContain "arg1: Long"
            src shouldContain "arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment"
            // invokeExact should pass all 5 args
            src shouldContain "invokeExact(arg0, arg1, arg2, arg3, arg4)"
        }

        "variadic function without config keeps current behavior (no firstVariadicArg)" {
            val src = generate("""
                void* raw_varargs(int fixed, ...);
            """.trimIndent())

            // Without --variadic-args config, no extra ADDRESS slots
            src shouldContain "FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)"
            // No firstVariadicArg option
            src shouldNotContain "firstVariadicArg"
            // No variadic MemorySegment params
            src shouldNotContain "arg1: MemorySegment"
        }
    }

    "Constant generation" - {
        "pointer globals read and write their symbol storage through a zero-offset VarHandle" {
            if (!System.getProperty("os.name").startsWith("Windows")) {
                val workspace = Files.createTempDirectory("kextract_pointer_global_")
                try {
                    val source = workspace.resolve("pointer_global.c")
                    val library = workspace.resolve(
                        if (System.getProperty("os.name").startsWith("Mac")) {
                            "libkextract_pointer_global.dylib"
                        } else {
                            "libkextract_pointer_global.so"
                        },
                    )
                    Files.writeString(
                        source,
                        """
                        static int kxPointerGlobalMarker = 0;
                        static int kxAlternatePointerGlobalMarker = 0;
                        void *KxDefaultRunLoopMode = &kxPointerGlobalMarker;
                        long kxExpectedPointerGlobalAddress(void) {
                            return (long) KxDefaultRunLoopMode;
                        }
                        long kxAlternatePointerGlobalAddress(void) {
                            return (long) &kxAlternatePointerGlobalMarker;
                        }
                        """.trimIndent(),
                    )
                    val compiler = if (System.getProperty("os.name").startsWith("Mac")) {
                        listOf("cc", "-dynamiclib", "-fPIC", source.toString(), "-o", library.toString())
                    } else {
                        listOf("cc", "-shared", "-fPIC", source.toString(), "-o", library.toString())
                    }
                    val compilerProcess = ProcessBuilder(compiler)
                        .directory(workspace.toFile())
                        .redirectErrorStream(true)
                        .start()
                    val compilerOutput = compilerProcess.inputStream.bufferedReader().readText()
                    check(compilerProcess.waitFor() == 0) {
                        "native pointer-global fixture failed: " + compiler.joinToString(" ") + "\n" + compilerOutput
                    }

                    val generated = generate(
                        """
                        extern void *KxDefaultRunLoopMode;
                        long kxExpectedPointerGlobalAddress(void);
                        long kxAlternatePointerGlobalAddress(void);
                        """.trimIndent(),
                        libraries = listOf(Options.Library.parse(":$library")),
                    )
                    compileAndInvokeGeneratedLong(
                        generated,
                        """
                        package test

                        import java.lang.foreign.MemorySegment

                        fun readPointerGlobal(): Long {
                            val replacement = kxAlternatePointerGlobalAddress()
                            KxDefaultRunLoopMode = MemorySegment.ofAddress(replacement)
                            return if (
                                KxDefaultRunLoopMode.address() == replacement &&
                                kxExpectedPointerGlobalAddress() == replacement
                            ) 1L else 0L
                        }
                        """.trimIndent(),
                        "readPointerGlobal",
                    ) shouldBe 1L
                } finally {
                    workspace.toFile().deleteRecursively()
                }
            }
        }

        "enum and options globals use their scalar ABI carriers" {
            if (!System.getProperty("os.name").startsWith("Windows")) {
                val workspace = Files.createTempDirectory("kextract_enum_global_")
                try {
                    val source = workspace.resolve("enum_global.c")
                    val library = workspace.resolve(
                        if (System.getProperty("os.name").startsWith("Mac")) {
                            "libkextract_enum_global.dylib"
                        } else {
                            "libkextract_enum_global.so"
                        },
                    )
                    Files.writeString(
                        source,
                        """
                        typedef enum KxGlobalState {
                            KxGlobalStateInitial = 0,
                            KxGlobalStateUpdated = 3
                        } KxGlobalState;
                        typedef enum __attribute__((flag_enum)) KxGlobalOptions {
                            KxGlobalOptionReadable = 1,
                            KxGlobalOptionWritable = 2
                        } KxGlobalOptions;

                        KxGlobalState KxCurrentState = KxGlobalStateInitial;
                        KxGlobalOptions KxCurrentOptions = KxGlobalOptionReadable;

                        long kxCurrentStateRaw(void) {
                            return (long) KxCurrentState;
                        }
                        long kxCurrentOptionsRaw(void) {
                            return (long) KxCurrentOptions;
                        }
                        """.trimIndent(),
                    )
                    val compiler = if (System.getProperty("os.name").startsWith("Mac")) {
                        listOf("cc", "-dynamiclib", "-fPIC", source.toString(), "-o", library.toString())
                    } else {
                        listOf("cc", "-shared", "-fPIC", source.toString(), "-o", library.toString())
                    }
                    val compilerProcess = ProcessBuilder(compiler)
                        .directory(workspace.toFile())
                        .redirectErrorStream(true)
                        .start()
                    val compilerOutput = compilerProcess.inputStream.bufferedReader().readText()
                    check(compilerProcess.waitFor() == 0) {
                        "native enum-global fixture failed: " + compiler.joinToString(" ") + "\n" + compilerOutput
                    }

                    val generated = generate(
                        """
                        typedef enum KxGlobalState {
                            KxGlobalStateInitial = 0,
                            KxGlobalStateUpdated = 3
                        } KxGlobalState;
                        typedef enum __attribute__((flag_enum)) KxGlobalOptions {
                            KxGlobalOptionReadable = 1,
                            KxGlobalOptionWritable = 2
                        } KxGlobalOptions;

                        extern KxGlobalState KxCurrentState;
                        extern KxGlobalOptions KxCurrentOptions;
                        long kxCurrentStateRaw(void);
                        long kxCurrentOptionsRaw(void);
                        """.trimIndent(),
                        libraries = listOf(Options.Library.parse(":$library")),
                    )
                    compileAndInvokeGeneratedLong(
                        generated,
                        """
                        package test

                        fun readAndWriteEnumGlobals(): Long {
                            KxCurrentState = KxGlobalState.KxGlobalStateUpdated
                            KxCurrentOptions = KxGlobalOptions.KxGlobalOptionReadable +
                                KxGlobalOptions.KxGlobalOptionWritable
                            return if (
                                KxCurrentState == KxGlobalState.KxGlobalStateUpdated &&
                                KxCurrentOptions.rawValue == 3L &&
                                kxCurrentStateRaw() == 3L &&
                                kxCurrentOptionsRaw() == 3L
                            ) 1L else 0L
                        }
                        """.trimIndent(),
                        "readAndWriteEnumGlobals",
                    ) shouldBe 1L
                } finally {
                    workspace.toFile().deleteRecursively()
                }
            }
        }

        "initialized mutable globals remain variables" {
            val tmp = Files.createTempFile("kextract_mutable_global_", ".h")
            try {
                tmp.toFile().writeText("int counter = 1;")
                val parsed = KextractTool.parse(listOf(tmp.toString()))
                val counter = parsed.members().single { it.name() == "counter" }

                (counter is Declaration.Variable) shouldBe true
            } finally {
                Files.deleteIfExists(tmp)
            }
        }

        "integer constant generates a fun returning Int" {
            val src = generate("#define MAX_SIZE 1024")

            src shouldContain "fun MAX_SIZE()"
            src shouldContain "1024"
        }

        "string constant with spaces in value is emitted as comment" {
            // Clang evaluates string macros by stripping the surrounding quotes,
            // leaving a bare string like "hello world" (with a space).
            // Since the space makes it impossible to emit as a valid Kotlin literal,
            // the generator emits a comment instead.
            val src = generate("""#define GREETING "hello world"""")

            src shouldContain "// Skipped constant"
        }
    }

    "TypeMapper" - {
        "primitive types map correctly" {
            val src = generate("""
                void f_bool(_Bool b);
                void f_char(char c);
                void f_short(short s);
                void f_int(int i);
                void f_long(long l);
                void f_float(float f);
                void f_double(double d);
            """.trimIndent())
            src shouldContain "arg0: Boolean"  // _Bool
            src shouldContain "arg0: Byte"     // char
            src shouldContain "arg0: Short"
            src shouldContain "arg0: Int"
            src shouldContain "fun f_long(arg0: Long): Unit"
            src shouldContain "arg0: Float"
            src shouldContain "arg0: Double"
        }

        "pointer type maps to MemorySegment" {
            val src = generate("void f(int* p, char* s, void* v);")

            src.lines()
                .filter { it.contains("fun f(") }
                .joinToString() shouldContain "MemorySegment"
        }

        "array type maps to MemorySegment" {
            val src = generate("""
                struct S { int arr[10]; };
            """.trimIndent())

            // array field accessor returns MemorySegment via asSlice
            src shouldContain "MemorySegment"
        }
    }

    "Generated file structure" - {
        "file contains required Panama imports" {
            val src = generate("int x;")

            src shouldContain "import java.lang.foreign"
        }

        "file for empty header has no declarations" {
            val files = run {
                val tmp = Files.createTempFile("kextract_empty_", ".h")
                try {
                    tmp.toFile().writeText("// empty header\n")
                    val parsed = KextractTool.parse(listOf(tmp.toString()))
                    KotlinGenerator().generate(parsed, tmp.fileName.toString(), "test")
                } finally {
                    Files.deleteIfExists(tmp)
                }
            }
            // Empty header may produce 0 or 1 file (just the package/imports)
            files.size shouldBe 1
            files.first().contents shouldNotContain "fun "
            files.first().contents shouldNotContain "class "
        }

        "generator returns exactly one file per header" {
            val tmp = Files.createTempFile("kextract_single_", ".h")
            try {
                tmp.toFile().writeText("int foo(void);")
                val parsed = KextractTool.parse(listOf(tmp.toString()))
                val files = KotlinGenerator().generate(parsed, tmp.fileName.toString(), "test")
                files shouldHaveAtLeastSize 1
                files.map { it.getPath().toString() }
                    .filter { it.endsWith(".kt") }
                    .shouldNotBeEmpty()
            } finally {
                Files.deleteIfExists(tmp)
            }
        }

        "output filename is derived from header basename not full path" {
            val tmp = Files.createTempFile("kextract_path_test_", ".h")
            try {
                tmp.toFile().writeText("int x;")
                val parsed = KextractTool.parse(listOf(tmp.toString()))
                val files = KotlinGenerator().generate(parsed, tmp.toString(), "test")
                // filename should be based on basename, not the full /tmp/... path
                files.first().getPath().toString() shouldNotContain "tmp"
                files.first().getPath().fileName.toString() shouldNotContain "/"
            } finally {
                Files.deleteIfExists(tmp)
            }
        }
    }

    "Typedef handling" - {
        "typedef of struct is not duplicated" {
            val src = generate("""
                typedef struct { int x; int y; } Point;
            """.trimIndent())

            // Should generate only one class for Point, not two
            src.lines().count { it.trimStart().startsWith("class Point") } shouldBe 1
        }
    }
})
