package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.Declaration
import org.graphiks.kextract.kotlin.abi.AndroidRecordLayout
import org.graphiks.kextract.kotlin.abi.AndroidRecordLayoutPlan
import org.graphiks.kextract.kotlin.builders.isOptionsStyleName
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import java.net.URLClassLoader
import java.nio.file.Files
import java.nio.file.Path

private data class AndroidSources(val common: String, val bridge: String, val jna: String)

private fun generateAndroidSources(header: String): AndroidSources {
    val workspace = Files.createTempDirectory("kextract-kmp-android-memory")
    val input = workspace.resolve("wgpu.h")
    val output = workspace.resolve("out")
    return try {
        input.toFile().writeText(header)
        KextractTool(Logger.DEFAULT).runGeneration(
            listOf(input.toString()),
            Options(
                targetPackage = "sample.bindings",
                outputDir = output.toString(),
                multiplatform = true,
                libraries = listOf(
                    Options.Library("fixture", Options.Library.SpecKind.NAME),
                ),
            ),
        ) shouldBe KextractTool.SUCCESS

        AndroidSources(
            common = output.resolve("commonMain/kotlin/sample/bindings/wgpu_hCommon.kt").toFile().readText(),
            bridge = output.resolve("androidMain/kotlin/sample/bindings/wgpu_hAndroid.kt").toFile().readText(),
            jna = output.resolve("androidMain/kotlin/sample/bindings/android/wgpu_h.kt").toFile().readText(),
        )
    } finally {
        workspace.toFile().deleteRecursively()
    }
}

private fun androidLayout(header: String, name: String): AndroidRecordLayout {
    val tmp = Files.createTempFile("kextract_mem_layout_", ".h")
    return try {
        tmp.toFile().writeText(header)
        val parsed = KextractTool.parse(listOf(tmp.toString()))
        val record = parsed.findScoped(name) ?: error("Missing Clang declaration for $name")
        AndroidRecordLayoutPlan.create(parsed)[record]
    } finally {
        Files.deleteIfExists(tmp)
    }
}

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

    unsigned int wgpuGetAdapterVendorID(const WGPUAdapterInfo* info);
    """.trimIndent()

private val GENERAL_UNION_HEADER =
    """
    typedef union WGPUScalar {
        unsigned int u32;
        double f64;
        _Bool boolean;
    } WGPUScalar;

    typedef struct WGPUUnionContainer {
        WGPUScalar scalar;
        WGPUScalar* scalarPointer;
    } WGPUUnionContainer;
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

private val MEMBACK_KFFI_COMMON_STUB =
    """
    package org.graphiks.kffi

    expect class NativeAddress(rawValue: Long) {
        val rawValue: Long
    }
    interface Callback
    enum class CallbackPolicy { ONCE, REPEATING }
    fun interface CallbackExceptionHandler {
        fun onException(error: Throwable)
        companion object {
            val Default = CallbackExceptionHandler { }
        }
    }
    interface CallbackRegistration<C : Callback>
    @RequiresOptIn
    annotation class CallbackRuntimeApi
    @RequiresOptIn
    annotation class UnsafeCallbackRearmApi
    @CallbackRuntimeApi
    class CallbackType<C : Callback>(
        val canonicalId: String,
        val hasRoutingUserdata: Boolean,
    )
    @CallbackRuntimeApi
    class PreparedCallbackRegistration<C : Callback>
    @OptIn(CallbackRuntimeApi::class)
    object CallbackRuntime {
        fun <C : Callback> register(
            type: CallbackType<C>,
            trampoline: NativeAddress,
            policy: CallbackPolicy,
            onError: CallbackExceptionHandler,
            callback: C,
        ): CallbackRegistration<C> = object : CallbackRegistration<C> {}
        fun <C : Callback> prepare(
            type: CallbackType<C>,
            trampoline: NativeAddress,
            policy: CallbackPolicy,
            onError: CallbackExceptionHandler,
            callback: C,
        ): PreparedCallbackRegistration<C> = PreparedCallbackRegistration()
        fun <C : Callback> rearmAfterNativeQuiescence(
            type: CallbackType<C>,
            trampoline: NativeAddress,
            policy: CallbackPolicy,
            onError: CallbackExceptionHandler,
            callback: C,
        ): CallbackRegistration<C> = object : CallbackRegistration<C> {}
        fun <C : Callback> dispatchSafely(
            type: CallbackType<C>,
            userdata: NativeAddress?,
            dispatch: (C) -> Unit,
        ) = Unit
        fun reportUnroutedFailure(failure: Throwable) = Unit
    }
    expect class CallbackHolder<T> {
        val handler: NativeAddress
    }
    expect value class CString(val handler: NativeAddress)
    @JvmInline
    value class ArrayHolder<T>(val handler: NativeAddress)
    expect class MemoryAllocator() {
        fun allocateBuffer(size: ULong): MemoryBuffer
    }
    expect class MemoryBuffer(handler: NativeAddress, size: ULong) {
        val size: ULong
        val handler: NativeAddress
        fun writeByte(value: Byte, offset: ULong)
        fun readByte(offset: ULong): Byte
        fun writeUByte(value: UByte, offset: ULong)
        fun readUByte(offset: ULong): UByte
        fun writeShort(value: Short, offset: ULong)
        fun readShort(offset: ULong): Short
        fun writeUShort(value: UShort, offset: ULong)
        fun readUShort(offset: ULong): UShort
        fun writeInt(value: Int, offset: ULong)
        fun readInt(offset: ULong): Int
        fun writeUInt(value: UInt, offset: ULong)
        fun readUInt(offset: ULong): UInt
        fun writeLong(value: Long, offset: ULong)
        fun readLong(offset: ULong): Long
        fun writeULong(value: ULong, offset: ULong)
        fun readULong(offset: ULong): ULong
        fun writeFloat(value: Float, offset: ULong)
        fun readFloat(offset: ULong): Float
        fun writeDouble(value: Double, offset: ULong)
        fun readDouble(offset: ULong): Double
        fun writePointer(value: NativeAddress, offset: ULong)
        fun readPointer(offset: ULong): NativeAddress
        fun readBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong)
        fun writeBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong)
    }
    """.trimIndent()

private val MEMBACK_KFFI_ANDROID_STUB =
    """
    package org.graphiks.kffi

    private val retainedMemory = mutableListOf<com.sun.jna.Memory>()

    actual class NativeAddress actual constructor(rawValue: Long) : com.sun.jna.Pointer(rawValue) {
        actual val rawValue: Long = rawValue
    }

    actual class CallbackHolder<T>(actual val handler: NativeAddress)

    @JvmInline
    actual value class CString actual constructor(actual val handler: NativeAddress)

    actual class MemoryAllocator actual constructor() {
        actual fun allocateBuffer(size: ULong): MemoryBuffer {
            val block = com.sun.jna.Memory(size.toLong())
            block.clear()
            retainedMemory.add(block)
            return MemoryBuffer(NativeAddress(com.sun.jna.Pointer.nativeValue(block)), size)
        }
    }

    actual class MemoryBuffer actual constructor(
        actual val handler: NativeAddress,
        actual val size: ULong,
    ) {
        private val p: com.sun.jna.Pointer = handler
        actual fun writeByte(value: Byte, offset: ULong) { p.setByte(offset.toLong(), value) }
        actual fun readByte(offset: ULong): Byte = p.getByte(offset.toLong())
        actual fun writeUByte(value: UByte, offset: ULong) { p.setByte(offset.toLong(), value.toByte()) }
        actual fun readUByte(offset: ULong): UByte = p.getByte(offset.toLong()).toUByte()
        actual fun writeShort(value: Short, offset: ULong) { p.setShort(offset.toLong(), value) }
        actual fun readShort(offset: ULong): Short = p.getShort(offset.toLong())
        actual fun writeUShort(value: UShort, offset: ULong) { p.setShort(offset.toLong(), value.toShort()) }
        actual fun readUShort(offset: ULong): UShort = p.getShort(offset.toLong()).toUShort()
        actual fun writeInt(value: Int, offset: ULong) { p.setInt(offset.toLong(), value) }
        actual fun readInt(offset: ULong): Int = p.getInt(offset.toLong())
        actual fun writeUInt(value: UInt, offset: ULong) { p.setInt(offset.toLong(), value.toInt()) }
        actual fun readUInt(offset: ULong): UInt = p.getInt(offset.toLong()).toUInt()
        actual fun writeLong(value: Long, offset: ULong) { p.setLong(offset.toLong(), value) }
        actual fun readLong(offset: ULong): Long = p.getLong(offset.toLong())
        actual fun writeULong(value: ULong, offset: ULong) { p.setLong(offset.toLong(), value.toLong()) }
        actual fun readULong(offset: ULong): ULong = p.getLong(offset.toLong()).toULong()
        actual fun writeFloat(value: Float, offset: ULong) { p.setFloat(offset.toLong(), value) }
        actual fun readFloat(offset: ULong): Float = p.getFloat(offset.toLong())
        actual fun writeDouble(value: Double, offset: ULong) { p.setDouble(offset.toLong(), value) }
        actual fun readDouble(offset: ULong): Double = p.getDouble(offset.toLong())
        actual fun writePointer(value: NativeAddress, offset: ULong) {
            p.setPointer(offset.toLong(), value)
        }
        actual fun readPointer(offset: ULong): NativeAddress =
            NativeAddress(com.sun.jna.Pointer.nativeValue(p.getPointer(offset.toLong())))
        actual fun readBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) {
            p.read(bufferOffset.toLong(), array, arrayIndex.toInt(), size.toInt())
        }
        actual fun writeBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) {
            p.write(bufferOffset.toLong(), array, arrayIndex.toInt(), size.toInt())
        }
    }

    fun NativeAddress.toAddress(): Long = rawValue
    """.trimIndent()

private fun compileGeneratedAndroid(sources: AndroidSources, probe: String? = null): LongArray? {
    val workspace = Files.createTempDirectory("kextract-generated-android-memory-classes")
    try {
        val common = workspace.resolve("wgpu_hCommon.kt")
        val bridge = workspace.resolve("wgpu_hAndroid.kt")
        val jna = workspace.resolve("wgpu_h.kt")
        val kffiCommon = workspace.resolve("kffiCommon.kt")
        val kffiAndroid = workspace.resolve("kffiAndroid.kt")
        val probeFile = probe?.let { workspace.resolve("probe.kt") }
        val output = Files.createDirectories(workspace.resolve("classes"))
        common.toFile().writeText(sources.common)
        bridge.toFile().writeText(sources.bridge)
        jna.toFile().writeText(sources.jna)
        kffiCommon.toFile().writeText(MEMBACK_KFFI_COMMON_STUB)
        kffiAndroid.toFile().writeText(MEMBACK_KFFI_ANDROID_STUB)
        probe?.let { probeFile?.toFile()?.writeText(it) }

        val arguments = mutableListOf(
            "-no-stdlib",
            "-no-reflect",
            "-Xmulti-platform",
            "-Xcommon-sources=${common},${kffiCommon}",
            "-classpath",
            System.getProperty("java.class.path"),
            "-d",
            output.toString(),
            common.toString(),
            bridge.toString(),
            jna.toString(),
            kffiCommon.toString(),
            kffiAndroid.toString(),
        )
        probeFile?.let { arguments.add(it.toString()) }
        K2JVMCompiler().exec(System.err, *arguments.toTypedArray()) shouldBe ExitCode.OK

        return probeFile?.let {
            URLClassLoader(arrayOf(output.toUri().toURL()), com.sun.jna.Structure::class.java.classLoader).use { classLoader ->
                classLoader.loadClass("sample.probe.ProbeKt").getMethod("runProbe").invoke(null) as LongArray
            }
        }
    } finally {
        workspace.toFile().deleteRecursively()
    }
}

private fun Declaration.findScoped(name: String): Declaration.Scoped? = when (this) {
    is Declaration.Scoped -> if (name() == name) this else members().firstNotNullOfOrNull { it.findScoped(name) }
    else -> null
}

class KmpAndroidMemoryBackedAbiTest : FreeSpec({
    "structs emit memory-backed interfaces with MemoryBuffer accessors" {
        val generated = generateAndroidSources(ADAPTER_INFO_HEADER)
        val layout = androidLayout(ADAPTER_INFO_HEADER, "WGPUAdapterInfo")
        val size = layout.sizeBytes
        val vendorIDOffset = layout.field("vendorID").offsetBytes
        val vendorNameOffset = layout.field("vendorName").offsetBytes
        val adapterIDOffset = layout.field("adapterID").offsetBytes
        val limitsOffset = layout.field("limits").offsetBytes
        val hostOffset = layout.field("hasCompromisedHost").offsetBytes
        val flagsOffset = layout.field("featureFlags").offsetBytes

        generated.bridge shouldContain "actual interface WGPUAdapterInfo {"
        generated.bridge shouldContain "actual var nextInChain: WGPUChainedStruct?"
        generated.bridge shouldContain "actual var vendorID: UInt"
        generated.bridge shouldContain "actual var vendorName: CString?"
        generated.bridge shouldContain "actual var deviceType: WGPUDeviceType"
        generated.bridge shouldContain "actual var adapterID: ULong"
        generated.bridge shouldContain "actual var limits: WGPULimits"
        generated.bridge shouldContain "actual var hasCompromisedHost: Boolean"
        generated.bridge shouldContain "actual var featureFlags: WGPUFeatureFlags"
        generated.bridge shouldContain "actual val handler: NativeAddress"

        generated.bridge shouldContain "actual companion object {"
        generated.bridge shouldContain "actual operator fun invoke(address: NativeAddress): WGPUAdapterInfo = ByReference(address)"
        generated.bridge shouldContain "actual fun allocate(allocator: MemoryAllocator): WGPUAdapterInfo = ByReference(allocator.allocateBuffer(${size}uL).handler)"

        generated.bridge shouldContain "class ByReference(val handle: NativeAddress = NativeAddress(0L)) : WGPUAdapterInfo {"
        generated.bridge shouldContain "class ByValue(val handle: NativeAddress = NativeAddress(0L)) : WGPUAdapterInfo {"
        generated.bridge shouldContain "private val buffer: MemoryBuffer by lazy { MemoryBuffer(handle, ${size}uL) }"

        generated.bridge shouldContain "get() = buffer.readUInt(${vendorIDOffset}uL)"
        generated.bridge shouldContain "set(value) { buffer.writeUInt(value, ${vendorIDOffset}uL) }"
        generated.bridge shouldContain "get() = buffer.readULong(${adapterIDOffset}uL)"
        generated.bridge shouldContain "set(value) { buffer.writeULong(value, ${adapterIDOffset}uL) }"
        generated.bridge shouldContain "get() = buffer.readPointer(${vendorNameOffset}uL).takeIf { it.rawValue != 0L }?.let(::CString)"
        generated.bridge shouldContain "set(value) { buffer.writePointer(value?.handler ?: NativeAddress(0L), ${vendorNameOffset}uL) }"
        generated.bridge shouldContain "get() = buffer.readPointer(${layout.field("nextInChain").offsetBytes}uL).takeIf { it.rawValue != 0L }?.let { WGPUChainedStruct(it) }"
        generated.bridge shouldContain "set(value) { buffer.writePointer(value?.handler ?: NativeAddress(0L), ${layout.field("nextInChain").offsetBytes}uL) }"
        generated.bridge shouldContain "get() = buffer.readByte(${hostOffset}uL) != 0.toByte()"
        generated.bridge shouldContain "set(value) { buffer.writeByte(if (value) 1 else 0, ${hostOffset}uL) }"
        generated.bridge shouldContain "get() = WGPULimits.ByValue(NativeAddress(handle.rawValue + ${limitsOffset}L))"
        generated.bridge shouldContain "buffer.writeBytes(bytes, 0u, ${limitsOffset}uL, ${layout.field("limits").sizeBytes}uL)"
        generated.bridge shouldContain "get() = WGPUFeatureFlags((buffer.readInt(${flagsOffset}uL)).toUInt().toLong())"
        generated.bridge shouldContain "set(value) { buffer.writeInt(value.rawValue.toInt(), ${flagsOffset}uL) }"
        generated.bridge shouldContain "override val handler: NativeAddress"
        generated.bridge shouldContain "get() = handle"
    }

    "memory-backed struct emission never references com.sun.jna" {
        val generated = generateAndroidSources(ADAPTER_INFO_HEADER)

        generated.bridge shouldNotContain "com.sun.jna"
        generated.bridge shouldNotContain "Structure"
        generated.bridge shouldNotContain "Union"
        generated.bridge shouldNotContain "readField"
        generated.bridge shouldNotContain "writeField"
        generated.bridge shouldNotContain "@JvmField"
    }

    "allocateArray computes element addresses from the buffer base" {
        val generated = generateAndroidSources(ADAPTER_INFO_HEADER)
        val size = androidLayout(ADAPTER_INFO_HEADER, "WGPUAdapterInfo").sizeBytes

        generated.bridge shouldContain
            "actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUAdapterInfo) -> Unit): ArrayHolder<WGPUAdapterInfo> {"
        generated.bridge shouldContain "val buffer = allocator.allocateBuffer(${size}uL * size)"
        generated.bridge shouldContain "val result = ArrayHolder<WGPUAdapterInfo>(buffer.handler)"
        generated.bridge shouldContain "provider(index.toUInt(), ByValue(NativeAddress(buffer.handler.rawValue + index.toLong() * ${size}L)))"
        generated.bridge shouldContain "return result"
    }

    "union records map every field to offset zero and size to the largest member" {
        val generated = generateAndroidSources(GENERAL_UNION_HEADER)
        val layout = androidLayout(GENERAL_UNION_HEADER, "WGPUScalar")
        val container = androidLayout(GENERAL_UNION_HEADER, "WGPUUnionContainer")

        generated.bridge shouldContain "actual interface WGPUScalar {"
        generated.bridge shouldContain "get() = buffer.readUInt(0uL)"
        generated.bridge shouldContain "get() = buffer.readDouble(0uL)"
        generated.bridge shouldContain "get() = buffer.readByte(0uL) != 0.toByte()"
        generated.bridge shouldContain "private val buffer: MemoryBuffer by lazy { MemoryBuffer(handle, ${layout.sizeBytes}uL) }"
        generated.bridge shouldContain "get() = WGPUScalar.ByValue(NativeAddress(handle.rawValue + ${container.field("scalar").offsetBytes}L))"
        generated.bridge shouldContain "set(value) { buffer.writePointer(value?.handler ?: NativeAddress(0L), ${container.field("scalarPointer").offsetBytes}uL) }"
    }

    "functions still emit against the raw JNA library proxy for M5.3" {
        val generated = generateAndroidSources(ADAPTER_INFO_HEADER)

        generated.jna shouldContain "internal interface wgpu_hLibrary : Library"
        generated.jna shouldContain "Native.load(\"fixture\", wgpu_hLibrary::class.java)"
        generated.jna shouldContain "fun wgpuGetAdapterVendorID(info: Pointer?): Int"

        generated.bridge shouldContain "actual fun wgpuGetAdapterVendorID(info: WGPUAdapterInfo?): UInt"
        generated.bridge shouldNotContain "not implemented for Android"
    }

    "generated memory-backed sources compile against the kffi runtime" {
        compileGeneratedAndroid(generateAndroidSources(ADAPTER_INFO_HEADER))
        compileGeneratedAndroid(generateAndroidSources(GENERAL_UNION_HEADER))
        compileGeneratedAndroid(generateAndroidSources(NATIVE_DISPLAY_HEADER))
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

        compileGeneratedAndroid(generateAndroidSources(ADAPTER_INFO_HEADER), probe)
            ?.toList() shouldBe listOf(0x1234L, 0x1234567890123456L, 1L, 0xABCDL, 0L)
    }

    "tagged display handle is memory-backed and copies its active Xlib payload" {
        val generated = generateAndroidSources(NATIVE_DISPLAY_HEADER)
        val layout = androidLayout(NATIVE_DISPLAY_HEADER, "WGPUNativeDisplayHandle")
        val xlibSize = androidLayout(NATIVE_DISPLAY_HEADER, "WGPUXlibDisplayHandle").sizeBytes
        val dataOffset = layout.field("data").offsetBytes

        generated.bridge shouldContain "actual interface WGPUNativeDisplayHandle {"
        generated.bridge shouldContain "actual var type: WGPUNativeDisplayHandleType"
        generated.bridge shouldContain "actual val xlib: WGPUXlibDisplayHandle?"
        generated.bridge shouldContain "actual fun setXlib(value: WGPUXlibDisplayHandle)"
        generated.bridge shouldContain "class ByReference(val handle: NativeAddress = NativeAddress(0L)) : WGPUNativeDisplayHandle {"
        generated.bridge shouldContain "get() = if (type != WGPUNativeDisplayHandleType_Xlib) null else WGPUXlibDisplayHandle.ByValue(NativeAddress(handle.rawValue + ${dataOffset}L))"
        generated.bridge shouldContain "val bytes = ByteArray($xlibSize)"
        generated.bridge shouldContain "buffer.writeBytes(bytes, 0u, ${dataOffset}uL, ${xlibSize}uL)"

        val probe =
            """
            package sample.probe

            import org.graphiks.kffi.MemoryAllocator
            import sample.bindings.WGPUNativeDisplayHandle
            import sample.bindings.WGPUXlibDisplayHandle

            fun runProbe(): LongArray {
                val allocator = MemoryAllocator()
                val xlibBuffer = allocator.allocateBuffer(16uL)
                xlibBuffer.writePointer(org.graphiks.kffi.NativeAddress(0x1234), 0uL)
                xlibBuffer.writeInt(7, 8uL)

                val display = WGPUNativeDisplayHandle.ByValue(allocator.allocateBuffer(24uL).handler)
                display.setXlib(WGPUXlibDisplayHandle.ByValue(xlibBuffer.handler))

                val extras = sample.bindings.WGPUInstanceExtras.ByValue(allocator.allocateBuffer(24uL).handler)
                extras.displayHandle = display

                val copied = extras.displayHandle.xlib ?: error("missing copied Xlib payload")
                return longArrayOf(copied.display?.rawValue ?: 0L, copied.screen.toLong())
            }
            """.trimIndent()

        compileGeneratedAndroid(generateAndroidSources(NATIVE_DISPLAY_HEADER), probe)
            ?.toList() shouldBe listOf(0x1234L, 7L)
    }

    "union by-value round-trips its active member" {
        val probe =
            """
            package sample.probe

            import org.graphiks.kffi.MemoryAllocator
            import sample.bindings.WGPUScalar
            import sample.bindings.WGPUUnionContainer

            fun runProbe(): LongArray {
                val allocator = MemoryAllocator()
                val scalarBuffer = allocator.allocateBuffer(8uL)
                scalarBuffer.writeUInt(0x12345678u, 0uL)
                val scalar = WGPUScalar.ByValue(scalarBuffer.handler)

                val container = WGPUUnionContainer.ByValue(allocator.allocateBuffer(16uL).handler)
                container.scalar = scalar

                return longArrayOf(container.scalar.u32.toLong())
            }
            """.trimIndent()

        compileGeneratedAndroid(generateAndroidSources(GENERAL_UNION_HEADER), probe)
            ?.toList() shouldBe listOf(0x12345678L)
    }

    "byValueTransitional struct-by-value functions pin the transitional ByValue marker" {
        val generated = generateAndroidSources(
            """
            typedef struct WGPUPoint { int x; int y; } WGPUPoint;
            WGPUPoint wgpuPointByValue(WGPUPoint p);
            """.trimIndent(),
        )

        generated.bridge shouldContain "actual fun wgpuPointByValue(p: WGPUPoint): WGPUPoint"
        // M5.2 emits transitional JNA-style struct-by-value code that references raw JNA
        // classes deleted by the memory-backed rework; M5.3 must re-emit it through the engine.
        // Assert the marker so a future header can't silently ship uncompilable bindings.
        generated.bridge shouldContain "ByValue("
        generated.bridge shouldContain ".apply { read() }"
    }

    "isOptionsEnumType recognizes historical WGPUInstance options enums" {
        val generated = generateAndroidSources(
            """
            typedef enum WGPUInstanceBackend {
                WGPUInstanceBackend_Undefined = 0,
                WGPUInstanceBackend_Vulkan = 0x1,
                WGPUInstanceBackend_GL = 0x2,
            } WGPUInstanceBackend;

            typedef enum WGPUFoo {
                WGPUFoo_A = 0,
                WGPUFoo_B = 1,
            } WGPUFoo;

            typedef struct WGPUBackendHolder {
                WGPUInstanceBackend backend;
                WGPUFoo foo;
            } WGPUBackendHolder;
            """.trimIndent(),
        )

        generated.common shouldContain "value class WGPUInstanceBackend(val rawValue: Long) {"
        generated.common shouldContain "typealias WGPUFoo = UInt"
        generated.common shouldNotContain "value class WGPUFoo"

        generated.bridge shouldContain "WGPUInstanceBackend((buffer.readInt(0uL))"
        generated.bridge shouldContain "set(value) { buffer.writeInt(value.rawValue.toInt(), 0uL) }"
        generated.bridge shouldContain "as WGPUFoo"
        generated.bridge shouldNotContain "as WGPUInstanceBackend"
    }

    "isOptionsStyleName is the single source of truth for the options predicate" {
        isOptionsStyleName("WGPUInstanceBackend") shouldBe true
        isOptionsStyleName("WGPUInstanceFlag") shouldBe true
        isOptionsStyleName("WGPUFlags") shouldBe true
        isOptionsStyleName("WGPUFeatureFlags") shouldBe true
        isOptionsStyleName("WGPUFoo") shouldBe false
        isOptionsStyleName("WGPUInstanceExtras") shouldBe false
    }
})
