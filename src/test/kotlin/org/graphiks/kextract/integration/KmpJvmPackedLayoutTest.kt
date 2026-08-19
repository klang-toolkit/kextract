package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain

class KmpJvmPackedLayoutTest : FreeSpec({
    "generated JVM memory-backed records preserve packed Clang offsets and sizes" {
        val generated = generateKmpSources(
            """
            typedef struct __attribute__((packed)) PackedLeaf {
                char tag;
                int value;
            } PackedLeaf;

            typedef struct __attribute__((packed)) PackedRecord {
                char prefix;
                int values[2];
                PackedLeaf leaf;
                short tail;
            } PackedRecord;
            """.trimIndent(),
        )

        generated.jvm shouldContain "private val mem: MemoryBuffer by lazy { MemoryBuffer(handle, 16uL) }"
        generated.jvm shouldContain "get() = mem.readByte(0uL)"
        generated.jvm shouldContain "get() = mem.readPointer(1uL)"
        generated.jvm shouldContain "get() = PackedLeaf.ByValue(NativeAddress(handle.rawValue + 9L))"
        generated.jvm shouldContain "val bytes = ByteArray(5)"
        generated.jvm shouldContain "mem.writeBytes(bytes, 0u, 9uL, 5uL)"
        generated.jvm shouldContain "get() = mem.readShort(14uL)"
        generated.jvm shouldNotContain "MemoryLayout.sequenceLayout"
        generated.jvm shouldNotContain "java.lang.foreign"

        val result = compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.probe",
            probeSource =
                """
                package sample.probe

                import org.graphiks.kffi.MemoryAllocator
                import sample.bindings.PackedRecord

                fun inspectPackedLayouts(): LongArray {
                    val allocator = MemoryAllocator()
                    val buffer = allocator.allocateBuffer(16uL)
                    buffer.writeByte(0x11, 0uL)
                    buffer.writeByte(0x22, 9uL)
                    buffer.writeShort(0x7788, 14uL)
                    val record = PackedRecord.ByReference(buffer.handler)
                    return longArrayOf(
                        record.prefix.toLong(),
                        record.leaf.tag.toLong(),
                        record.tail.toLong(),
                    )
                }
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "inspectPackedLayouts",
        ) as LongArray

        result.toList() shouldBe listOf(0x11L, 0x22L, 0x7788L)
    }

    "packed typedef arrays keep the array field at its Clang offset" {
        val generated = generateKmpSources(
            """
            typedef int Row[2];
            typedef struct __attribute__((packed)) PackedTypedefArray {
                char prefix;
                Row values[3];
            } PackedTypedefArray;
            """.trimIndent(),
        )

        generated.jvm shouldContain "private val mem: MemoryBuffer by lazy { MemoryBuffer(handle, 25uL) }"
        generated.jvm shouldContain "get() = mem.readByte(0uL)"
        generated.jvm shouldContain "get() = mem.readPointer(1uL)"
        generated.jvm shouldNotContain "MemoryLayout.sequenceLayout"
        generated.jvm shouldNotContain "java.lang.foreign"

        val result = compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.probe",
            probeSource =
                """
                package sample.probe

                import org.graphiks.kffi.MemoryAllocator
                import sample.bindings.PackedTypedefArray

                fun inspectPackedTypedefArray(): LongArray {
                    val allocator = MemoryAllocator()
                    val buffer = allocator.allocateBuffer(25uL)
                    val record = PackedTypedefArray.ByValue(buffer.handler)
                    record.prefix = 0x11
                    return longArrayOf(record.prefix.toLong(), buffer.readByte(0uL).toLong())
                }
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "inspectPackedTypedefArray",
        ) as LongArray

        result.toList() shouldBe listOf(0x11L, 0x11L)
    }

    "packed nested aggregates keep the embedded record at its Clang offset" {
        val generated = generateKmpSources(
            """
            typedef struct Inner { int x; } Inner;
            typedef struct __attribute__((packed)) Outer { char tag; Inner inner; } Outer;
            """.trimIndent(),
        )

        generated.jvm shouldContain "private val mem: MemoryBuffer by lazy { MemoryBuffer(handle, 5uL) }"
        generated.jvm shouldContain "get() = mem.readByte(0uL)"
        generated.jvm shouldContain "get() = Inner.ByValue(NativeAddress(handle.rawValue + 1L))"
        generated.jvm shouldContain "val bytes = ByteArray(4)"
        generated.jvm shouldContain "mem.writeBytes(bytes, 0u, 1uL, 4uL)"
        generated.jvm shouldNotContain "java.lang.foreign"

        val result = compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.probe",
            probeSource =
                """
                package sample.probe

                import org.graphiks.kffi.MemoryAllocator
                import sample.bindings.Outer

                fun inspectPackedNestedAggregate(): LongArray {
                    val allocator = MemoryAllocator()
                    val buffer = allocator.allocateBuffer(5uL)
                    val outer = Outer.ByValue(buffer.handler)
                    outer.tag = 0x11
                    return longArrayOf(outer.tag.toLong(), buffer.readByte(0uL).toLong())
                }
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "inspectPackedNestedAggregate",
        ) as LongArray

        result.toList() shouldBe listOf(0x11L, 0x11L)
    }
})
