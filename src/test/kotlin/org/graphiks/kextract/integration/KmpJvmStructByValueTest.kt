package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import java.io.ByteArrayOutputStream
import java.io.PrintWriter
import java.nio.file.Files

class KmpJvmStructByValueTest : FreeSpec({

    fun generateJvm(header: String): String {
        val input = Files.createTempFile("kextract-jvm-struct", ".h")
        val output = Files.createTempDirectory("kextract-jvm-struct-out")
        return try {
            input.toFile().writeText(header)
            KextractTool(Logger()).runGeneration(
                listOf(input.toString()),
                Options(targetPackage = "sample.bindings", outputDir = output.toString(), multiplatform = true),
            ) shouldBe KextractTool.SUCCESS
            Files.walk(output.resolve("jvmMain")).use { paths ->
                paths.filter { it.fileName.toString().endsWith(".kt") }
                    .map { it.toFile().readText() }
                    .toList()
                    .joinToString("\n")
            }
        } finally {
            input.toFile().delete()
            output.toFile().deleteRecursively()
        }
    }

    /** Génère [header] et retourne les erreurs du logger ; la génération doit échouer. */
    fun generateJvmFailure(header: String): String {
        val input = Files.createTempFile("kextract-jvm-struct", ".h")
        val output = Files.createTempDirectory("kextract-jvm-struct-out")
        val errors = ByteArrayOutputStream()
        return try {
            input.toFile().writeText(header)
            KextractTool(
                Logger(
                    PrintWriter(ByteArrayOutputStream(), true),
                    PrintWriter(errors, true),
                ),
            ).runGeneration(
                listOf(input.toString()),
                Options(targetPackage = "sample.bindings", outputDir = output.toString(), multiplatform = true),
            ) shouldBe KextractTool.FAILURE

            Files.walk(output).use { paths ->
                paths.noneMatch { it.fileName.toString().endsWith(".kt") }
            } shouldBe true
            errors.toString()
        } finally {
            input.toFile().delete()
            output.toFile().deleteRecursively()
        }
    }

    "struct-by-value arg and return register layouts and call engine wrappers" {
        val source = generateJvm(
            """
            typedef struct { int a; int b; } Box;
            Box makeBox(int x);
            void consumeBox(Box b);
            """.trimIndent(),
        )
        source shouldContain "JvmDowncallEngine.registerStructLayout"
        source shouldContain "JvmDowncallEngine.callStructReturnBox"
        source shouldContain "JvmDowncallEngine.callStructArgBox"
        source shouldNotContain "MemoryLayout"
        source shouldNotContain "FunctionDescriptor"
    }

    "struct-by-value emission compiles against the engine API" {
        val generated = generateKmpSources(
            """
            typedef struct { int a; int b; } Box;
            Box makeBox(int x);
            void consumeBox(Box b);
            """.trimIndent(),
        )
        compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.probe",
            probeSource =
                """
                package sample.probe

                import sample.bindings.Box
                import sample.bindings.makeBox

                fun probeCompiles(): Long {
                    // Le registre de layouts s'initialise au chargement du fichier
                    // (classe façade) sans référence aux symboles natifs.
                    return Box.Companion.hashCode().toLong()
                }
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "probeCompiles",
        )
    }

    "struct-arg shape with a non-Unit return fails loudly at generation time" {
        val errors = generateJvmFailure(
            """
            typedef struct { int a; int b; } Box;
            int consumeBox(Box b);
            """.trimIndent(),
        )
        errors shouldContain "struct-by-value arg shape not supported by JVM engine"
        errors shouldContain "Int consumeBox(b: Box)"
    }

    "wgpu-shaped emission compiles against the engine API" {
        // Exercice complet des formes M5.3 : StringView par valeur derrière un
        // pointeur, retour pointeur (GetProcAddress), retour WGPUFuture avec
        // callbackInfo par valeur, formes scalaires de l'union wgpu.
        val generated = generateKmpSources(
            """
            typedef struct WGPUDeviceImpl* WGPUDevice;
            typedef struct WGPUQueueImpl* WGPUQueue;
            typedef struct { void* data; unsigned long long length; } WGPUStringView;
            typedef struct { unsigned long long id; } WGPUFuture;
            typedef struct { void* callback; void* userdata; } WGPUQueueWorkDoneCallbackInfo;
            typedef void* WGPUProc;
            WGPUProc wgpuGetProcAddress(WGPUStringView procName);
            void wgpuDeviceSetLabel(WGPUDevice device, WGPUStringView label);
            void wgpuQueueSubmit(WGPUQueue queue, unsigned long long commandCount, void* commands);
            WGPUFuture wgpuQueueOnSubmittedWorkDone(WGPUQueue queue, WGPUQueueWorkDoneCallbackInfo callbackInfo);
            void wgpuRenderPassEncoderSetViewport(void* encoder, float x, float y, float width, float height, float minDepth, float maxDepth);
            """.trimIndent(),
        )
        compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.probe",
            probeSource =
                """
                package sample.probe

                import sample.bindings.WGPUStringView
                import sample.bindings.wgpuGetProcAddress

                fun probeCompiles(): Long {
                    // Le registre de layouts s'initialise au chargement du fichier
                    // (classe façade) sans référence aux symboles natifs.
                    return WGPUStringView.Companion.hashCode().toLong()
                }
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "probeCompiles",
        )
    }

    "struct-return shape with extra or missing scalar args fails loudly at generation time" {
        val errors = generateJvmFailure(
            """
            typedef struct { int a; int b; } Box;
            Box makeBox(int x, int y);
            """.trimIndent(),
        )
        errors shouldContain "struct-by-value return shape not supported by JVM engine"
        errors shouldContain "Box makeBox(x: Int, y: Int)"

        val errorsVoid = generateJvmFailure(
            """
            typedef struct { int a; int b; } Box;
            Box makeBox(void);
            """.trimIndent(),
        )
        errorsVoid shouldContain "struct-by-value return shape not supported by JVM engine"
        errorsVoid shouldContain "Box makeBox()"
    }

    "combined struct-arg and struct-return shape fails loudly at generation time" {
        val errors = generateJvmFailure(
            """
            typedef struct { int a; int b; } Box;
            Box modifyBox(Box b, int x);
            """.trimIndent(),
        )
        errors shouldContain "struct-by-value return shape not supported by JVM engine"
        errors shouldContain "Box modifyBox(b: Box, x: Int)"
    }

    "supported shape on a struct without an engine wrapper fails loudly" {
        // La forme (retour struct, un argument Int) est supportée, mais le wrapper
        // callStructReturn<Name> n'existe que pour les structs de la table du moteur
        // (actuellement Box) — Rect générerait callStructReturnRect, irrésolu.
        val errors = generateJvmFailure(
            """
            typedef struct { int x; int y; } Rect;
            Rect makeRect(int x);
            """.trimIndent(),
        )
        errors shouldContain "struct-by-value wrapper for 'Rect' not yet implemented in JvmDowncallEngine"
    }

    "scalar downcall shape outside the engine table fails loudly at generation time" {
        // M5.3 étend la table du moteur à l'union des signatures wgpu (callV3PPI —
        // wgpuCommandEncoderWriteTimestamp etc. — en fait désormais partie) : la
        // garde doit donc être exercée sur une forme restée hors table, callV3PPD.
        val errors = generateJvmFailure(
            """
            void set_triple(void* first, void* second, double value);
            """.trimIndent(),
        )
        errors shouldContain "downcall shape callV3PPD for 'set_triple' not yet implemented in JvmDowncallEngine"
        errors shouldContain "(M5.3 extends the table)"
    }

    "wgpu StringView-style shape emits callStructArg with the pointer before the struct" {
        val source = generateJvm(
            """
            typedef struct { void* data; unsigned long long length; } WGPUStringView;
            typedef struct WGPUDeviceImpl* WGPUDevice;
            void wgpuDeviceSetLabel(WGPUDevice device, WGPUStringView label);
            """.trimIndent(),
        )
        source shouldContain
            "JvmDowncallEngine.callStructArgWGPUStringView(wgpuDeviceSetLabel_ADDR, device?.handler?.rawValue ?: 0L, label.handler.rawValue)"
        source shouldNotContain "FunctionDescriptor"
    }

    "wgpu proc-address shape emits callStructArgWGPUStringViewRetP with a pointer return" {
        val source = generateJvm(
            """
            typedef struct { void* data; unsigned long long length; } WGPUStringView;
            typedef void* WGPUProc;
            WGPUProc wgpuGetProcAddress(WGPUStringView procName);
            """.trimIndent(),
        )
        source shouldContain
            "JvmDowncallEngine.callStructArgWGPUStringViewRetP(wgpuGetProcAddress_ADDR, procName.handler.rawValue)"
    }

    "wgpu WGPUFuture-style shape emits callStructReturn with the callbackInfo struct arg" {
        val source = generateJvm(
            """
            typedef struct { unsigned long long id; } WGPUFuture;
            typedef struct { void* callback; void* userdata; } WGPUQueueWorkDoneCallbackInfo;
            typedef struct WGPUQueueImpl* WGPUQueue;
            WGPUFuture wgpuQueueOnSubmittedWorkDone(WGPUQueue queue, WGPUQueueWorkDoneCallbackInfo callbackInfo);
            """.trimIndent(),
        )
        source shouldContain
            "JvmDowncallEngine.callStructReturnWGPUFutureWGPUQueueWorkDoneCallbackInfo(" +
                "wgpuQueueOnSubmittedWorkDone_ADDR, allocator, queue?.handler?.rawValue ?: 0L, callbackInfo.handler.rawValue)"
        source shouldContain "WGPUFuture.ByValue("
    }

    "registerStructLayout maps enum, unsigned, float and nested struct fields" {
        val source = generateJvm(
            """
            typedef enum Color : int { Color_Red = 0, Color_Green = 1 } Color;
            typedef enum UnsignedColor : unsigned int { UnsignedColor_Zero = 0 } UnsignedColor;
            typedef struct { unsigned int u; } Inner;
            typedef struct { Color color; UnsignedColor uc; unsigned int u; float f; Inner inner; } Complex;
            """.trimIndent(),
        )
        source shouldContain "JvmDowncallEngine.StructField(\"color\", JvmDowncallEngine.FieldKind.INT32, 0L)"
        source shouldContain "JvmDowncallEngine.StructField(\"uc\", JvmDowncallEngine.FieldKind.UINT32, 4L)"
        source shouldContain "JvmDowncallEngine.StructField(\"u\", JvmDowncallEngine.FieldKind.UINT32, 8L)"
        source shouldContain "JvmDowncallEngine.StructField(\"f\", JvmDowncallEngine.FieldKind.FLOAT32, 12L)"
        source shouldContain "JvmDowncallEngine.StructField(\"Inner\", JvmDowncallEngine.FieldKind.STRUCT, 16L)"
    }
})
