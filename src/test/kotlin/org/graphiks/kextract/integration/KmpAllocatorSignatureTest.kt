package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import java.nio.file.Files

class KmpAllocatorSignatureTest : FreeSpec({

    fun generate(header: String, sourceSet: String): String {
        val input = Files.createTempFile("kextract-alloc", ".h")
        val output = Files.createTempDirectory("kextract-alloc-out")
        return try {
            input.toFile().writeText(header)
            KextractTool(Logger.DEFAULT).runGeneration(
                listOf(input.toString()),
                Options(
                    targetPackage = "sample.bindings",
                    outputDir = output.toString(),
                    multiplatform = true,
                ),
            ) shouldBe KextractTool.SUCCESS
            val root = output.resolve(sourceSet)
            Files.walk(root).use { paths ->
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

    val header = """
        typedef struct { int a; } Box;
        typedef struct { int a; int b; } Box2;
        Box makeBox(void);
        void consumeBox(Box b);
        Box2 makeBox2(int x);
    """.trimIndent()

    "common expect carries allocator on struct-by-value returns only" {
        val source = generate(header, "commonMain")
        source shouldContain "expect fun makeBox(allocator: MemoryAllocator): Box"
        source shouldContain "expect fun makeBox2(allocator: MemoryAllocator, x: Int): Box2"
        val consumeLine = source.lineSequence().first { it.contains("expect fun consumeBox") }
        consumeLine shouldContain "expect fun consumeBox(b: Box): Unit"
        consumeLine shouldNotContain "allocator"
    }

    "android actual uses the caller allocator for the out buffer" {
        val source = generate(header, "androidMain")
        source shouldContain "actual fun makeBox(allocator: MemoryAllocator): Box"
        source shouldContain "actual fun makeBox2(allocator: MemoryAllocator, x: Int): Box2"
        source shouldContain "val out = allocator.allocateBuffer(4uL)"
        val consumeLine = source.lineSequence().first { it.contains("actual fun consumeBox") }
        consumeLine shouldNotContain "allocator"
    }

    "jvm actual carries the allocator parameter on struct-by-value returns" {
        val source = generate(header, "jvmMain")
        source shouldContain "actual fun makeBox(allocator: MemoryAllocator): Box"
        source shouldContain "actual fun makeBox2(allocator: MemoryAllocator, x: Int): Box2"
    }

    "native actual carries the allocator parameter on struct-by-value returns" {
        val source = generate(header, "nativeMain")
        source shouldContain "actual fun makeBox(allocator: MemoryAllocator): Box"
        source shouldContain "actual fun makeBox2(allocator: MemoryAllocator, x: Int): Box2"
    }
})
