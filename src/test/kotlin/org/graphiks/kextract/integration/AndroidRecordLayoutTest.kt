package org.graphiks.kextract.integration

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.kotlin.abi.AndroidRecordLayout
import org.graphiks.kextract.kotlin.abi.AndroidRecordLayoutPlan
import org.graphiks.kextract.pipeline.KextractTool
import java.nio.file.Files
import kotlin.test.Test
import kotlin.test.assertEquals

class AndroidRecordLayoutTest {

    private fun layoutOf(source: String, recordName: String): AndroidRecordLayout {
        val tmp = Files.createTempFile("kextract_android_record_", ".h")
        return try {
            tmp.toFile().writeText(source)
            val parsed = KextractTool.parse(listOf(tmp.toString()))
            val record = parsed.findScoped(recordName)
                ?: error("Missing Clang declaration for $recordName")
            AndroidRecordLayoutPlan.create(parsed)[record]
        } finally {
            Files.deleteIfExists(tmp)
        }
    }

    @Test
    fun `pair struct of two uint64 has a 16 byte size with 8-byte aligned fields`() {
        val layout = layoutOf(
            """
            struct bench_pair { unsigned long long a; unsigned long long b; };
            """.trimIndent(),
            "bench_pair",
        )
        assertEquals(16L, layout.sizeBytes)
        assertEquals(8L, layout.alignmentBytes)
        assertEquals(0L, layout.field("a").offsetBytes)
        assertEquals(8L, layout.field("b").offsetBytes)
        assertEquals(8L, layout.field("a").sizeBytes)
        assertEquals(8L, layout.field("b").sizeBytes)
    }

    @Test
    fun `union of char and unsigned long long has 8 byte size with all fields at offset zero`() {
        val layout = layoutOf(
            """
            union u { char c; unsigned long long l; };
            """.trimIndent(),
            "u",
        )
        assertEquals(8L, layout.sizeBytes)
        assertEquals(0L, layout.field("c").offsetBytes)
        assertEquals(0L, layout.field("l").offsetBytes)
        assertEquals(8L, layout.field("l").sizeBytes)
    }

    @Test
    fun `struct with trailing padding reports the full padded size`() {
        val layout = layoutOf(
            """
            struct tp { char c; double d; };
            """.trimIndent(),
            "tp",
        )
        assertEquals(16L, layout.sizeBytes)
        assertEquals(0L, layout.field("c").offsetBytes)
        assertEquals(8L, layout.field("d").offsetBytes)
        assertEquals(8L, layout.field("d").sizeBytes)
    }

    @Test
    fun `mixed struct lays out with natural padding`() {
        val layout = layoutOf(
            """
            struct mixed { char c; int i; unsigned long long l; };
            """.trimIndent(),
            "mixed",
        )
        assertEquals(16L, layout.sizeBytes)
        assertEquals(0L, layout.field("c").offsetBytes)
        assertEquals(4L, layout.field("i").offsetBytes)
        assertEquals(8L, layout.field("l").offsetBytes)
    }
}

private fun Declaration.findScoped(name: String): Declaration.Scoped? = when (this) {
    is Declaration.Scoped -> if (name() == name) this else members().firstNotNullOfOrNull { it.findScoped(name) }
    else -> null
}
