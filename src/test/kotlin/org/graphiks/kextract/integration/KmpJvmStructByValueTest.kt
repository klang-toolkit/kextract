package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import java.nio.file.Files

class KmpJvmStructByValueTest : FreeSpec({

    fun generateJvm(header: String): String {
        val input = Files.createTempFile("kextract-jvm-struct", ".h")
        val output = Files.createTempDirectory("kextract-jvm-struct-out")
        return try {
            input.toFile().writeText(header)
            KextractTool(Logger.DEFAULT).runGeneration(
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
})
