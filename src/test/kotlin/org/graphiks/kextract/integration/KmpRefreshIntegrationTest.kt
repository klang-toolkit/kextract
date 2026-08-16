package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.Declaration
import org.graphiks.kextract.kotlin.KotlinGenerator
import org.graphiks.kextract.kotlin.models.KotlinSourceFile
import org.graphiks.kextract.pipeline.IncludeHelper
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import java.nio.file.Files

class KmpRefreshIntegrationTest : FreeSpec({
    fun generateKmp(header: String, includedFunctions: List<String> = emptyList()): Map<String, String> {
        val input = Files.createTempFile("kextract-kmp-refresh", ".h")
        val output = Files.createTempDirectory("kextract-kmp-refresh-out")
        return try {
            input.toFile().writeText(header)
            KextractTool(Logger.DEFAULT).runGeneration(
                listOf(input.toString()),
                Options(
                    targetPackage = "sample.bindings",
                    outputDir = output.toString(),
                    includeHelper = IncludeHelper().also { helper ->
                        includedFunctions.forEach { function ->
                            helper.addSymbol(IncludeHelper.IncludeKind.FUNCTION, function)
                        }
                    },
                    multiplatform = true,
                ),
            ) shouldBe KextractTool.SUCCESS

            listOf("commonMain", "jvmMain", "nativeMain", "androidMain").associateWith { sourceSet ->
                Files.walk(output.resolve(sourceSet)).use { paths ->
                    paths.filter { it.fileName.toString().endsWith(".kt") }
                        .map { it.toFile().readText() }
                        .toList()
                        .joinToString("\n")
                }
            }
        } finally {
            input.toFile().delete()
            output.toFile().deleteRecursively()
        }
    }

    fun generateKmpFromIncludedHeader(header: String): Map<String, String> {
        val workspace = Files.createTempDirectory("kextract-kmp-refresh-include")
        val input = workspace.resolve("wgpu.h")
        val included = workspace.resolve("webgpu.h")
        val output = workspace.resolve("out")
        return try {
            input.toFile().writeText("""#include "webgpu.h"""")
            included.toFile().writeText(header)
            KextractTool(Logger.DEFAULT).runGeneration(
                listOf(input.toString()),
                Options(
                    targetPackage = "sample.bindings",
                    outputDir = output.toString(),
                    multiplatform = true,
                ),
            ) shouldBe KextractTool.SUCCESS

            listOf("commonMain", "jvmMain", "nativeMain", "androidMain").associateWith { sourceSet ->
                Files.walk(output.resolve(sourceSet)).use { paths ->
                    paths.filter { it.fileName.toString().endsWith(".kt") }
                        .map { it.toFile().readText() }
                        .toList()
                        .joinToString("\n")
                }
            }
        } finally {
            workspace.toFile().deleteRecursively()
        }
    }

    "multiplatform mode emits common, JVM, Native, and Android bindings" {
        val generated = generateKmp(
            """
            typedef struct WGPUDeviceImpl* WGPUDevice;
            WGPUDevice wgpuGetDevice(void);
            """.trimIndent(),
        )

        generated.keys shouldBe setOf("commonMain", "jvmMain", "nativeMain", "androidMain")
        generated.getValue("commonMain") shouldContain "expect value class WGPUDevice"
        generated.getValue("jvmMain") shouldContain "actual value class WGPUDevice"
        generated.getValue("nativeMain") shouldContain "actual value class WGPUDevice"
        generated.getValue("androidMain") shouldContain "actual value class WGPUDevice"
    }

    "multiplatform mode preserves pointer depth for opaque handle output arrays" {
        val generated = generateKmp(
            """
            typedef struct WGPUInstanceImpl *WGPUInstance;
            typedef struct WGPUAdapterImpl *WGPUAdapter;
            unsigned long long wgpuInstanceEnumerateAdapters(
                WGPUInstance instance,
                void const *options,
                WGPUAdapter *adapters
            );
            """.trimIndent(),
        )

        generated.getValue("commonMain") shouldContain
            "expect fun wgpuInstanceEnumerateAdapters(instance: WGPUInstance?, options: NativeAddress?, adapters: NativeAddress?): ULong"
        generated.getValue("androidMain") shouldContain
            "actual fun wgpuInstanceEnumerateAdapters(instance: WGPUInstance?, options: NativeAddress?, adapters: NativeAddress?): ULong"
        generated.getValue("jvmMain") shouldContain
            "actual fun wgpuInstanceEnumerateAdapters(instance: WGPUInstance?, options: NativeAddress?, adapters: NativeAddress?): ULong"
        generated.getValue("jvmMain") shouldContain
            "adapters?.handler ?: MemorySegment.NULL"
        generated.getValue("nativeMain") shouldContain
            "actual fun wgpuInstanceEnumerateAdapters(instance: WGPUInstance?, options: NativeAddress?, adapters: NativeAddress?): ULong"
        generated.getValue("nativeMain") shouldContain
            "adapters?.pointer?.takeIf { adapters.rawValue != 0L }?.reinterpret()"
        generated.values.forEach { source ->
            source shouldNotContain "adapters: WGPUAdapter?"
        }
    }

    "multiplatform mode resolves forward-declared struct typedef fields" {
        val generated = generateKmpFromIncludedHeader(
            """
            typedef struct WGPUValue WGPUValue;
            typedef struct WGPUContainer WGPUContainer;
            typedef struct WGPUValue {
                int value;
            } WGPUValue;
            typedef struct WGPUContainer {
                WGPUValue member;
            } WGPUContainer;
            """.trimIndent(),
        )

        generated.getValue("commonMain") shouldContain "var member: WGPUValue"
    }

    "multiplatform mode resolves forward-declared struct typedef function signatures" {
        // La forme combinée (argument struct + retour struct) n'a pas de wrapper
        // moteur JVM et échoue à la génération ; les deux positions (retour, argument)
        // sont couvertes séparément par des formes supportées, sur un struct de la
        // table des wrappers du moteur (Box — voir jvmEngineStructWrappers).
        val generated = generateKmpFromIncludedHeader(
            """
            typedef struct Box Box;
            typedef struct Box {
                int value;
            } Box;
            Box wgpuRoundTrip(int x);
            void wgpuConsume(Box value);
            """.trimIndent(),
        )

        generated.getValue("commonMain") shouldContain
            "expect fun wgpuRoundTrip(allocator: MemoryAllocator, x: Int): Box"
        generated.getValue("commonMain") shouldContain
            "expect fun wgpuConsume(value: Box): Unit"
    }

    "multiplatform output uses explicit source roots" {
        val workspace = Files.createTempDirectory("kextract-kmp-paths")
        val input = workspace.resolve("wgpu_fixture.h")
        val output = workspace.resolve("out")
        try {
            input.toFile().writeText(
                """
                typedef struct WGPUDeviceImpl* WGPUDevice;
                WGPUDevice wgpuGetDevice(void);
                """.trimIndent(),
            )
            KextractTool(Logger.DEFAULT).runGeneration(
                listOf(input.toString()),
                Options(
                    targetPackage = "sample.bindings",
                    outputDir = output.toString(),
                    multiplatform = true,
                ),
            ) shouldBe KextractTool.SUCCESS

            Files.walk(output).use { paths ->
                paths.filter { it.fileName.toString().endsWith(".kt") }
                    .map { output.relativize(it).toString().replace('\\', '/') }
                    .toList()
                    .toSet() shouldBe setOf(
                    "commonMain/kotlin/sample/bindings/wgpu_fixture_hCommon.kt",
                    "jvmMain/kotlin/sample/bindings/wgpu_fixture_hJvm.kt",
                    "nativeMain/kotlin/sample/bindings/wgpu_fixture_hNative.kt",
                    "androidMain/kotlin/sample/bindings/wgpu_fixture_hAndroid.kt",
                )
            }
        } finally {
            workspace.toFile().deleteRecursively()
        }
    }

    "source root precedes package and split-output subdirectory" {
        KotlinSourceFile(
            packageName = "sample.bindings",
            className = "Types",
            contents = "",
            subDirectory = "generated",
            sourceRoot = "commonMain/kotlin",
        ).getPath().toString().replace('\\', '/') shouldBe
            "commonMain/kotlin/sample/bindings/generated/Types.kt"
    }

    "multiplatform mode honors include filters" {
        val generated = generateKmp(
            """
            int wgpuIncluded(void);
            int wgpuExcluded(void);
            """.trimIndent(),
            includedFunctions = listOf("wgpuIncluded"),
        )

        generated.values.forEach { source ->
            source shouldContain "wgpuIncluded"
            source shouldNotContain "wgpuExcluded"
        }
    }

    "empty target package keeps generated paths relative" {
        val input = Files.createTempFile("kextract-kmp-empty-package", ".h")
        try {
            input.toFile().writeText("int wgpuFunction(void);")
            val parsed = KextractTool.parse(listOf(input.toString()))
            val files = KotlinGenerator().generate(
                parsed,
                input.fileName.toString(),
                targetPackage = "",
                multiplatform = true,
            )

            files.all { !it.getPath().isAbsolute } shouldBe true
        } finally {
            Files.deleteIfExists(input)
        }
    }

    "empty target package routes Android engine support to androidMain" {
        val input = Files.createTempFile("kextract-kmp-empty-package-routing", ".h")
        val output = Files.createTempDirectory("kextract-kmp-empty-package-routing-out")
        try {
            input.toFile().writeText("int wgpuFunction(void);")
            KextractTool(Logger.DEFAULT).runGeneration(
                listOf(input.toString()),
                Options(outputDir = output.toString(), multiplatform = true),
            ) shouldBe KextractTool.SUCCESS

            val androidMain = output.resolve("androidMain/kotlin")
            Files.isDirectory(androidMain) shouldBe true
            Files.walk(androidMain).use { paths ->
                paths.filter { it.fileName.toString().endsWith(".kt") }
                    .anyMatch {
                        it.toFile().readText().contains("NativeEngine.resolveSymbol(\"wgpuFunction\")")
                    } shouldBe true
            }
            Files.exists(output.resolve("commonMain/kotlin/android")) shouldBe false
        } finally {
            input.toFile().delete()
            output.toFile().deleteRecursively()
        }
    }

    "KMP common output preserves C documentation" {
        val generated = generateKmp(
            """
            /** Completes all work submitted before this call. */
            void wgpuQueueDone(void);
            """.trimIndent(),
        )

        generated.getValue("commonMain") shouldContain
            "Completes all work submitted before this call."
    }

    "KMP common output emits enum documentation immediately before the generated type" {
        val generated = generateKmp(
            """
            /** Describes queue completion states. */
            typedef enum WGPUQueueStatus : unsigned int {
                WGPUQueueStatus_Success = 0
            } WGPUQueueStatus;
            """.trimIndent(),
        )

        generated.getValue("commonMain") shouldContain
            """
            /**
             * Describes queue completion states.
             */
            typealias WGPUQueueStatus = UInt
            """.trimIndent()
    }

    "KMP common output emits callback documentation immediately before the generated type" {
        val generated = generateKmp(
            """
            /** Invoked when queue work completes. */
            typedef void (*WGPUQueueDoneCallback)(int status);
            """.trimIndent(),
        )

        generated.getValue("commonMain") shouldContain
            """
            /**
             * Invoked when queue work completes.
             */
            fun interface WGPUQueueDoneCallback : Callback
            """.trimIndent()
    }

    "source comment extraction retains libclang brief text for fallback" {
        val input = Files.createTempFile("kextract-kmp-brief-comment", ".h")
        try {
            input.toFile().writeText(
                """
                /** Provides a brief fallback. */
                void wgpuBriefComment(void);
                """.trimIndent(),
            )
            val parsed = KextractTool.parse(listOf(input.toString()))
            val function = parsed.members()
                .filterIsInstance<Declaration.Function>()
                .single { it.name() == "wgpuBriefComment" }
            val sourceComment = function.attributes()
                .single { it.javaClass.simpleName == "SourceComment" }
            val brief = sourceComment.javaClass.getMethod("getBrief").invoke(sourceComment) as String

            brief shouldBe "Provides a brief fallback."

            val fallbackComment = sourceComment.javaClass
                .getConstructor(String::class.java, String::class.java)
                .newInstance("", brief) as Declaration.Attribute
            val fallbackFunction = Declaration.function(
                function.pos(),
                function.name(),
                function.type(),
                *function.parameters().toTypedArray(),
            ).also { it.addAttribute(fallbackComment) }
            val common = KotlinGenerator().generate(
                Declaration.toplevel(parsed.pos(), fallbackFunction),
                input.fileName.toString(),
                targetPackage = "sample.bindings",
                multiplatform = true,
            ).first().contents

            common shouldContain
                """
                /**
                 * Provides a brief fallback.
                 */
                expect fun wgpuBriefComment(): Unit
                """.trimIndent()
        } finally {
            Files.deleteIfExists(input)
        }
    }
})
