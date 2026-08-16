package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import java.nio.file.Files

class KmpJvmMemoryBackedAbiTest : FreeSpec({
    fun generateJvm(header: String): String {
        val input = Files.createTempFile("kextract-kmp-jvm-memory", ".h")
        val output = Files.createTempDirectory("kextract-kmp-jvm-memory-out")
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

    "generated JVM struct is memory-backed (no java.lang.foreign)" {
        val source = generateJvm(
            """
            typedef struct Box {
                int x;
                long long y;
            } Box;
            """.trimIndent(),
        )

        source shouldContain "actual interface Box {"
        source shouldContain "actual var x: Int"
        source shouldContain "actual var y: Long"
        source shouldContain "class ByReference(val handle: NativeAddress = NativeAddress(0L)) : Box {"
        source shouldContain "private val buffer: MemoryBuffer by lazy { MemoryBuffer(handle, 16uL) }"
        source shouldContain "get() = buffer.readInt(0uL)"
        source shouldContain "get() = buffer.readLong(8uL)"
        source shouldNotContain "java.lang.foreign"
        source shouldNotContain "VarHandle"
        source shouldNotContain "MethodHandle"
        source shouldNotContain "CStructure"
    }

    "generated JVM union maps every field to offset zero and is memory-backed" {
        val header =
            """
            typedef union WGPUScalar {
                unsigned int u32;
                float f32;
                unsigned long long u64;
            } WGPUScalar;
            """.trimIndent()
        val source = generateJvm(header)

        source shouldContain "actual interface WGPUScalar {"
        source shouldContain "class ByReference(val handle: NativeAddress = NativeAddress(0L)) : WGPUScalar {"
        source shouldContain "class ByValue(val handle: NativeAddress = NativeAddress(0L)) : WGPUScalar {"
        source shouldContain "private val buffer: MemoryBuffer by lazy { MemoryBuffer(handle, 8uL) }"
        source shouldContain "get() = buffer.readUInt(0uL)"
        source shouldContain "get() = buffer.readFloat(0uL)"
        source shouldContain "get() = buffer.readULong(0uL)"
        source shouldNotContain "MemoryLayout.unionLayout("
        source shouldNotContain "java.lang.foreign"
    }

    "generated JVM union preserves Clang tail padding in its buffer size" {
        val header =
            """
            typedef union U {
                char bytes[3];
                short s;
            } U;
            """.trimIndent()
        val generated = generateKmpSources(header)

        generated.jvm shouldContain "class ByValue(val handle: NativeAddress = NativeAddress(0L)) : U {"
        generated.jvm shouldContain "private val buffer: MemoryBuffer by lazy { MemoryBuffer(handle, 4uL) }"
        generated.jvm shouldNotContain "java.lang.foreign"

        val result = compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.probe",
            probeSource =
                """
                package sample.probe

                import org.graphiks.kffi.MemoryAllocator
                import sample.bindings.U

                fun inspectUnionTailPadding(): LongArray {
                    val allocator = MemoryAllocator()
                    val raw = allocator.allocateBuffer(4uL)
                    val u = U.ByValue(raw.handler)
                    u.s = 0x0102
                    val bytes = ByteArray(4)
                    raw.readBytes(bytes, 0u, 0uL, 4uL)
                    return longArrayOf(
                        bytes[0].toLong(),
                        bytes[1].toLong(),
                        bytes[2].toLong(),
                        bytes[3].toLong(),
                    )
                }
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "inspectUnionTailPadding",
        ) as LongArray

        result.toList() shouldBe listOf(2L, 1L, 0L, 0L)
    }

    "memory-backed adapter info round-trips through a real buffer" {
        val probe =
            """
            package sample.probe

            import org.graphiks.kffi.MemoryAllocator
            import sample.bindings.WGPUAdapterInfo
            import sample.bindings.WGPULimits

            fun runProbe(): LongArray {
                val allocator = MemoryAllocator()
                val info = WGPUAdapterInfo.allocate(allocator)
                info.vendorID = 0x1234u
                info.adapterID = 0x1234567890123456uL
                info.hasCompromisedHost = true
                val nested = allocator.allocateBuffer(16uL)
                nested.writeUInt(0xABCDu, 0uL)
                info.limits = WGPULimits.ByReference(nested.handler)
                return longArrayOf(
                    info.vendorID.toLong(),
                    info.adapterID.toLong(),
                    if (info.hasCompromisedHost) 1L else 0L,
                    info.limits.maxTextureDimension1D.toLong(),
                    info.limits.maxBufferSize.toLong(),
                )
            }
            """.trimIndent()

        compileAndInvokeGeneratedKmpJvm(
            generated = generateKmpSources(ADAPTER_INFO_HEADER),
            probePackage = "sample.probe",
            probeSource = probe,
            facadeClassName = "ProbeKt",
            methodName = "runProbe",
        ) as LongArray shouldBe longArrayOf(0x1234L, 0x1234567890123456L, 1L, 0xABCDL, 0L)
    }

    "tagged JVM display handle is memory-backed and copies its active Xlib payload" {
        val generated = generateKmpSources(NATIVE_DISPLAY_HEADER)

        // WGPUNativeDisplayHandle: type (4 bytes) @ 0, union data @ 8, size 24.
        // WGPUXlibDisplayHandle: void* display @ 0, int screen @ 8, size 16.
        generated.jvm shouldContain "actual interface WGPUNativeDisplayHandle {"
        generated.jvm shouldContain "actual var type: WGPUNativeDisplayHandleType"
        generated.jvm shouldContain "actual val xlib: WGPUXlibDisplayHandle?"
        generated.jvm shouldContain "actual fun setXlib(value: WGPUXlibDisplayHandle)"
        generated.jvm shouldContain "class ByReference(val handle: NativeAddress = NativeAddress(0L)) : WGPUNativeDisplayHandle {"
        generated.jvm shouldContain "class ByValue(val handle: NativeAddress = NativeAddress(0L)) : WGPUNativeDisplayHandle {"
        generated.jvm shouldContain "private val buffer: MemoryBuffer by lazy { MemoryBuffer(handle, 24uL) }"
        generated.jvm shouldContain
            "get() = if (type != WGPUNativeDisplayHandleType_Xlib) null else WGPUXlibDisplayHandle.ByValue(NativeAddress(handle.rawValue + 8L))"
        generated.jvm shouldContain "val bytes = ByteArray(16)"
        generated.jvm shouldContain "buffer.writeBytes(bytes, 0u, 8uL, 16uL)"
        generated.jvm shouldNotContain "java.lang.foreign"

        val probe =
            """
            package sample.probe

            import org.graphiks.kffi.MemoryAllocator
            import org.graphiks.kffi.NativeAddress
            import sample.bindings.WGPUNativeDisplayHandle
            import sample.bindings.WGPUXlibDisplayHandle

            fun runProbe(): LongArray {
                val allocator = MemoryAllocator()
                val xlibBuffer = allocator.allocateBuffer(16uL)
                xlibBuffer.writePointer(NativeAddress(0x1234), 0uL)
                xlibBuffer.writeInt(7, 8uL)

                val display = WGPUNativeDisplayHandle.ByValue(allocator.allocateBuffer(24uL).handler)
                display.setXlib(WGPUXlibDisplayHandle.ByValue(xlibBuffer.handler))

                val extras = sample.bindings.WGPUInstanceExtras.ByValue(allocator.allocateBuffer(24uL).handler)
                extras.displayHandle = display

                val copied = extras.displayHandle.xlib ?: error("missing copied Xlib payload")
                return longArrayOf(copied.display?.rawValue ?: 0L, copied.screen.toLong())
            }
            """.trimIndent()

        compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.probe",
            probeSource = probe,
            facadeClassName = "ProbeKt",
            methodName = "runProbe",
        ) as LongArray shouldBe longArrayOf(0x1234L, 7L)
    }

    "generated JVM memory-backed sources compile against the kffi runtime" {
        // The JVM downlink path still rides FFM until M5.2 rewrites function
        // emission, so this fixture keeps struct-by-value functions out of the
        // compile check; scalar downcalls compile today.
        val generated = generateKmpSources(
            """
            typedef struct WGPUPoint { int x; int y; } WGPUPoint;
            typedef enum WGPUFoo { WGPUFoo_A = 0, WGPUFoo_B = 1 } WGPUFoo;
            unsigned long long wgpuFoo(unsigned long long a, unsigned long long b);
            """.trimIndent(),
        )

        generated.jvm shouldContain "class ByReference(val handle: NativeAddress = NativeAddress(0L)) : WGPUPoint {"
        compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.probe",
            probeSource =
                """
                package sample.probe

                fun runProbe(): LongArray = longArrayOf(0L)
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "runProbe",
        ) as LongArray shouldBe longArrayOf(0L)
    }
})

private val ADAPTER_INFO_HEADER =
    """
    typedef enum WGPUDeviceType {
        WGPUDeviceType_Other = 0,
        WGPUDeviceType_IntegratedGpu = 1,
    } WGPUDeviceType;

    typedef enum WGPUFeatureFlags {
        WGPUFeatureFlags_Undefined = 0,
        WGPUFeatureFlags_DepthClipControl = 0x1,
    } WGPUFeatureFlags;

    typedef struct WGPUChainedStruct {
        void* next;
    } WGPUChainedStruct;

    typedef struct WGPULimits {
        unsigned int maxTextureDimension1D;
        unsigned long long maxBufferSize;
    } WGPULimits;

    typedef struct WGPUAdapterInfo {
        WGPUChainedStruct* nextInChain;
        unsigned int vendorID;
        const char* vendorName;
        WGPUDeviceType deviceType;
        unsigned long long adapterID;
        WGPULimits limits;
        _Bool hasCompromisedHost;
        WGPUFeatureFlags featureFlags;
    } WGPUAdapterInfo;
    """.trimIndent()

private val NATIVE_DISPLAY_HEADER =
    """
    typedef enum WGPUNativeDisplayHandleType {
        WGPUNativeDisplayHandleType_None = 0,
        WGPUNativeDisplayHandleType_Xlib = 1,
        WGPUNativeDisplayHandleType_Xcb = 2,
        WGPUNativeDisplayHandleType_Wayland = 3
    } WGPUNativeDisplayHandleType;

    typedef struct WGPUXlibDisplayHandle {
        void* display;
        int screen;
    } WGPUXlibDisplayHandle;

    typedef struct WGPUXcbDisplayHandle {
        void* connection;
        int screen;
    } WGPUXcbDisplayHandle;

    typedef struct WGPUWaylandDisplayHandle {
        void* display;
    } WGPUWaylandDisplayHandle;

    typedef struct WGPUNativeDisplayHandle {
        WGPUNativeDisplayHandleType type;
        union {
            WGPUXlibDisplayHandle xlib;
            WGPUXcbDisplayHandle xcb;
            WGPUWaylandDisplayHandle wayland;
        } data;
    } WGPUNativeDisplayHandle;

    typedef struct WGPUInstanceExtras {
        WGPUNativeDisplayHandle displayHandle;
    } WGPUInstanceExtras;
    """.trimIndent()
