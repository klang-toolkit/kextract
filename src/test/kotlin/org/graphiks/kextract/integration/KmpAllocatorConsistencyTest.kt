package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import java.nio.file.Files

/**
 * Guards against divergence between the shared [org.graphiks.kextract.kotlin.builders.KmpTypeMapper.returnsStructByValue]
 * predicate (which drives the generated `allocator: MemoryAllocator` signature parameter on every source set)
 * and the per-target predicates that decide how each actual's body treats the struct return (JVM Arena
 * downcall argument, Android caller-allocator out buffer, Native CValue/ByValue wrap).
 */
class KmpAllocatorConsistencyTest : FreeSpec({

    fun generate(header: String, sourceSet: String): String {
        val input = Files.createTempFile("kextract-alloc-consistency", ".h")
        val output = Files.createTempDirectory("kextract-alloc-consistency-out")
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
        typedef struct WGPUPoint { int x; int y; } WGPUPoint;
        typedef struct WGPUValue { int value; } WGPUValue;
        typedef struct WGPUAdapterImpl* WGPUAdapter;
        Box makeBox(void);
        Box2 makeBox2(int x);
        void consumeBox(Box b);
        WGPUPoint wgpuPointByValue(WGPUPoint p);
        WGPUValue wgpuRoundTrip(WGPUValue value);
        WGPUAdapter wgpuAdapterGetAdapter(void);
        void wgpuAdapterRelease(WGPUAdapter adapter);
        WGPUPoint* wgpuGetPointPointer(void);
        const char* wgpuGetLabel(void);
    """.trimIndent()

    "signature allocator param agrees with the shared predicate and each actual's body treatment" {
        for (sourceSet in listOf("commonMain", "androidMain", "jvmMain", "nativeMain")) {
            val source = generate(header, sourceSet)
            val violations = signatureBodyMismatches(source, sourceSet)
            violations shouldBe emptyList()
        }
    }

    "struct-by-value returns carry allocator; pointer, opaque-handle and void returns do not" {
        val common = generate(header, "commonMain")
        common shouldContain "expect fun makeBox(allocator: MemoryAllocator): Box"
        common shouldContain "expect fun makeBox2(allocator: MemoryAllocator, x: Int): Box2"
        common shouldContain "expect fun wgpuPointByValue(allocator: MemoryAllocator, p: WGPUPoint): WGPUPoint"
        common shouldContain "expect fun wgpuRoundTrip(allocator: MemoryAllocator, value: WGPUValue): WGPUValue"
        common shouldContain "expect fun consumeBox(b: Box): Unit"
        common shouldContain "expect fun wgpuAdapterGetAdapter(): WGPUAdapter?"
        common shouldContain "expect fun wgpuAdapterRelease(adapter: WGPUAdapter?): Unit"
        common shouldContain "expect fun wgpuGetPointPointer(): WGPUPoint?"
        common shouldContain "expect fun wgpuGetLabel(): CString?"
    }
})

private val FUN_HEADER = Regex("""(?:expect|actual) fun (\w+)\(([^)]*)\): (\S+)""")

/** Companion factories return structs but are not C bindings; the predicate does not apply to them. */
private val COMPANION_FUNS = setOf("invoke", "allocate", "allocateArray")

/**
 * Returns a description of every emitted C-binding function whose allocator
 * signature parameter disagrees with either the shared predicate (struct-by-value
 * return ⟺ allocator present, inferred from the file's emitted interface names) or
 * the platform body treatment:
 * - JVM:    struct-by-value downcalls ride the engine layout registry wrapper
 *           (`callStructReturn&lt;Name&gt;`) ; the combined shapes (struct arg + struct
 *           return) still pass an internal `Arena.ofAuto() as SegmentAllocator`
 *           until M5.2
 * - Android: struct-by-value returns read the caller-provided `allocator.allocateBuffer`
 * - Native:  struct-by-value returns wrap the result in `X.ByValue(...)`
 * - common:  no body; only the signature predicate applies
 */
private fun signatureBodyMismatches(source: String, sourceSet: String): List<String> {
    val interfaceNames = Regex("""(?:expect|actual) interface (\w+)""")
        .findAll(source)
        .map { it.groupValues[1] }
        .toSet()
    val lines = source.lines()
    return FUN_HEADER.findAll(source).mapNotNull { match ->
        val name = match.groupValues[1]
        if (name in COMPANION_FUNS) return@mapNotNull null
        val params = match.groupValues[2]
        val returnType = match.groupValues[3].removeSuffix("{").trim()
        val hasAllocator = "allocator: MemoryAllocator" in params
        val isStructByValueReturn = returnType in interfaceNames
        if (isStructByValueReturn != hasAllocator) {
            return@mapNotNull "$sourceSet: $name($params): $returnType — allocator=$hasAllocator, structByValue=$isStructByValueReturn"
        }
        val headerIndex = lines.indexOfFirst { it.startsWith(match.value) }
        val body = lines.drop(headerIndex + 1)
            .takeWhile { it.trim() != "}" }
            .joinToString("\n")
        // expect declarations have no body; the signature predicate above is the whole check.
        if (sourceSet != "commonMain") {
            val bodyMatches = when (sourceSet) {
                "jvmMain" ->
                    "callStructReturn" in body || "Arena.ofAuto() as SegmentAllocator" in body
                "androidMain" -> "out = allocator.allocateBuffer" in body
                "nativeMain" -> ".ByValue(" in body
                else -> true
            }
            if (hasAllocator != bodyMatches) {
                return@mapNotNull "$sourceSet: $name body treatment (structByValue=$bodyMatches) disagrees with allocator param=$hasAllocator"
            }
        }
        null
    }.toList()
}
