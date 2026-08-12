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

class KmpJvmEnumAbiTest : FreeSpec({
    val enumHeader =
        """
        typedef enum Signed8 : signed char { Signed8_Min = -1, Signed8_Max = 1 } Signed8;
        typedef enum Unsigned8 : unsigned char { Unsigned8_Zero = 0, Unsigned8_Max = 255 } Unsigned8;
        typedef enum Signed16 : signed short { Signed16_Min = -1, Signed16_Max = 1 } Signed16;
        typedef enum Unsigned16 : unsigned short { Unsigned16_Zero = 0, Unsigned16_Max = 65535 } Unsigned16;
        typedef enum Signed32 : int { Signed32_Min = -1, Signed32_Max = 1 } Signed32;
        typedef enum Unsigned32 : unsigned int { Unsigned32_Zero = 0, Unsigned32_Max = 4294967295U } Unsigned32;
        typedef enum Signed64 : signed long long { Signed64_Min = -1, Signed64_Max = 1 } Signed64;
        typedef enum Unsigned64 : unsigned long long {
            Unsigned64_Zero = 0,
            Unsigned64_Max = 18446744073709551615ULL
        } Unsigned64;

        typedef struct EnumRecord {
            Signed8 small;
            Unsigned16 medium;
            Signed32 normal;
            Unsigned64 wide;
        } EnumRecord;

        typedef struct EnumCarrierRecord {
            Signed8 signed8;
            Unsigned8 unsigned8;
            Signed16 signed16;
            Unsigned16 unsigned16;
            Signed32 signed32;
            Unsigned32 unsigned32;
            Signed64 signed64;
            Unsigned64 unsigned64;
        } EnumCarrierRecord;

        Unsigned64 roundTripWide(Unsigned64 value);
        """.trimIndent()

    val optionsHeader =
        """
        typedef enum NarrowOptions : unsigned int {
            NarrowOptions_Max = 4294967295U
        } NarrowOptions;
        typedef struct R { NarrowOptions value; } R;
        NarrowOptions roundTrip(NarrowOptions value);
        """.trimIndent()

    "generated JVM enum functions and fields use their Clang carriers" {
        val generated = generateKmpSources(enumHeader)
        val source = generated.jvm

        generated.common shouldContain "typealias Unsigned64 = ULong"
        generated.common shouldContain "const val Unsigned8_Max : Unsigned8 = 255u"
        generated.common shouldContain "const val Unsigned16_Max : Unsigned16 = 65535u"
        generated.common shouldContain "const val Unsigned32_Max : Unsigned32 = 4294967295u"
        generated.common shouldContain
            "const val Unsigned64_Max : Unsigned64 = 18446744073709551615uL"
        source shouldContain "FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)"
        source shouldContain "roundTripWide_HANDLE.invokeExact(value.toLong()) as Long"
        source shouldContain ".toULong()"
        source shouldContain "actual var small: Signed8"
        source shouldContain "actual var medium: Unsigned16"
        source shouldContain "actual var normal: Signed32"
        source shouldContain "actual var wide: Unsigned64"
        source shouldContain "actual var signed8: Signed8"
        source shouldContain "actual var unsigned8: Unsigned8"
        source shouldContain "actual var signed16: Signed16"
        source shouldContain "actual var unsigned16: Unsigned16"
        source shouldContain "actual var signed32: Signed32"
        source shouldContain "actual var unsigned32: Unsigned32"
        source shouldContain "actual var signed64: Signed64"
        source shouldContain "actual var unsigned64: Unsigned64"
        source shouldNotContain "roundTripWide_HANDLE.invokeExact(value.toInt())"
        source shouldNotContain "roundTripWide_HANDLE.invokeExact(value) as Int"
    }

    "wide unsigned enum and every signedness-width field round trip on the JVM" {
        val generated = generateKmpSources(enumHeader)

        val result = compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.probe",
            probeSource =
                """
                package sample.probe

                import org.graphiks.kffi.MemoryAllocator
                import org.graphiks.kffi.TestNativeSymbols
                import java.lang.foreign.Arena
                import java.lang.foreign.FunctionDescriptor
                import java.lang.foreign.Linker
                import java.lang.foreign.ValueLayout
                import java.lang.invoke.MethodHandles
                import java.lang.invoke.MethodType
                import sample.bindings.EnumCarrierRecord
                import sample.bindings.roundTripWide

                object RoundTripTarget {
                    @JvmStatic
                    fun roundTripWide(value: Long): Long = value
                }

                fun runEnumAbiProbe(): LongArray = Arena.ofConfined().use { arena ->
                    val methodHandle = MethodHandles.lookup().findStatic(
                        RoundTripTarget::class.java,
                        "roundTripWide",
                        MethodType.methodType(java.lang.Long.TYPE, java.lang.Long.TYPE),
                    )
                    val descriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
                    TestNativeSymbols.register(
                        "roundTripWide",
                        Linker.nativeLinker().upcallStub(methodHandle, descriptor, arena),
                    )

                    val record = EnumCarrierRecord.allocate(MemoryAllocator())
                    record.signed8 = (-1).toByte()
                    record.unsigned8 = UByte.MAX_VALUE
                    record.signed16 = (-1).toShort()
                    record.unsigned16 = UShort.MAX_VALUE
                    record.signed32 = -1
                    record.unsigned32 = UInt.MAX_VALUE
                    record.signed64 = -1L
                    record.unsigned64 = ULong.MAX_VALUE

                    longArrayOf(
                        record.signed8.toLong(),
                        record.unsigned8.toLong(),
                        record.signed16.toLong(),
                        record.unsigned16.toLong(),
                        record.signed32.toLong(),
                        record.unsigned32.toLong(),
                        record.signed64,
                        record.unsigned64.toLong(),
                        roundTripWide(ULong.MAX_VALUE).toLong(),
                    )
                }
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "runEnumAbiProbe",
        ) as LongArray

        result.toList() shouldBe listOf(
            -1L,
            UByte.MAX_VALUE.toLong(),
            -1L,
            UShort.MAX_VALUE.toLong(),
            -1L,
            UInt.MAX_VALUE.toLong(),
            -1L,
            ULong.MAX_VALUE.toLong(),
            ULong.MAX_VALUE.toLong(),
        )
        result.last().toULong() shouldBe ULong.MAX_VALUE
    }

    "options-style enum source adapts rawValue through its indexed scalar" {
        val generated = generateKmpSources(optionsHeader)

        generated.common shouldContain "value class NarrowOptions(val rawValue: Long)"
        generated.common shouldContain
            "val NarrowOptions_Max = NarrowOptions(4294967295L)"
        generated.jvm shouldContain
            "roundTrip_HANDLE.invokeExact(value.rawValue.toInt()) as Int"
        generated.jvm shouldContain
            "NarrowOptions((roundTrip_HANDLE.invokeExact(value.rawValue.toInt()) as Int).toUInt().toLong())"
        generated.jvm shouldContain
            "get() = NarrowOptions((value_VH.get(handler.handler, 0L) as Int).toUInt().toLong())"
        generated.jvm shouldContain
            "set(value) = value_VH.set(handler.handler, 0L, value.rawValue.toInt())"
        generated.native shouldContain
            "webgpu.native.roundTrip(value.rawValue.toUInt())"
        generated.native shouldContain
            "return NarrowOptions(webgpu.native.roundTrip(value.rawValue.toUInt()).toLong())"
        generated.native shouldContain "get() = NarrowOptions(struct.value.toLong())"
        generated.native shouldContain
            "get() = handle.useContents { NarrowOptions(this.value.toLong()) }"
        generated.native shouldContain "set(value) { struct.value = value.rawValue.toUInt() }"
        generated.native shouldContain
            "this.value = this@toCValue.value.rawValue.toUInt()"
    }

    "options-style enum max constant function and field round trip on the JVM" {
        val generated = generateKmpSources(optionsHeader)

        val result = compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.probe",
            probeSource =
                """
                package sample.probe

                import org.graphiks.kffi.MemoryAllocator
                import org.graphiks.kffi.TestNativeSymbols
                import java.lang.foreign.Arena
                import java.lang.foreign.FunctionDescriptor
                import java.lang.foreign.Linker
                import java.lang.foreign.ValueLayout
                import java.lang.invoke.MethodHandles
                import java.lang.invoke.MethodType
                import sample.bindings.NarrowOptions
                import sample.bindings.R
                import sample.bindings.roundTrip

                object NarrowOptionsTarget {
                    @JvmStatic
                    fun roundTrip(value: Int): Int = value
                }

                fun runOptionsProbe(): LongArray = Arena.ofConfined().use { arena ->
                    val methodHandle = MethodHandles.lookup().findStatic(
                        NarrowOptionsTarget::class.java,
                        "roundTrip",
                        MethodType.methodType(java.lang.Integer.TYPE, java.lang.Integer.TYPE),
                    )
                    val descriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
                    TestNativeSymbols.register(
                        "roundTrip",
                        Linker.nativeLinker().upcallStub(methodHandle, descriptor, arena),
                    )

                    val record = R.allocate(MemoryAllocator())
                    val maximum = NarrowOptions.NarrowOptions_Max
                    record.value = maximum
                    longArrayOf(
                        maximum.rawValue,
                        record.value.rawValue,
                        roundTrip(maximum).rawValue,
                    )
                }
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "runOptionsProbe",
        ) as LongArray

        result.toList() shouldBe listOf(4294967295L, 4294967295L, 4294967295L)
    }

    "target-variable enum long fails before any source is emitted" {
        val input = Files.createTempFile("kextract-target-long-enum", ".h")
        val output = Files.createTempDirectory("kextract-target-long-enum-out")
        val errors = ByteArrayOutputStream()
        try {
            input.toFile().writeText(
                """
                typedef enum TargetLong : unsigned long {
                    TargetLong_Zero = 0,
                    TargetLong_One = 1
                } TargetLong;
                """.trimIndent(),
            )

            KextractTool(
                Logger(
                    PrintWriter(ByteArrayOutputStream(), true),
                    PrintWriter(errors, true),
                ),
            ).runGeneration(
                listOf(input.toString()),
                Options(
                    targetPackage = "sample.bindings",
                    outputDir = output.toString(),
                    multiplatform = true,
                ),
            ) shouldBe KextractTool.FAILURE

            Files.walk(output).use { paths ->
                paths.noneMatch { it.fileName.toString().endsWith(".kt") }
            } shouldBe true
            errors.toString() shouldContain
                "target-dependent width (LP64 vs LLP64); use a fixed-width C integer type"
        } finally {
            input.toFile().delete()
            output.toFile().deleteRecursively()
        }
    }
})
