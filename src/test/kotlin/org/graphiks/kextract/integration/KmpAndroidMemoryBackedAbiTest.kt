package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.Declaration
import org.graphiks.kextract.callbacks.CallbackBindingsConfig
import org.graphiks.kextract.callbacks.DirectFunctionBinding
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

private data class AndroidSources(val common: String, val bridge: String)

private fun String.shouldContainAny(vararg candidates: String) {
    candidates.any { contains(it) } shouldBe true
}

private fun generateAndroidSources(
    header: String,
    callbackBindings: CallbackBindingsConfig? = null,
): AndroidSources {
    val workspace = Files.createTempDirectory("kextract-kmp-android-memory")
    val input = workspace.resolve("wgpu.h")
    val output = workspace.resolve("out")
    return try {
        input.toFile().writeText(header)
        KextractTool(Logger()).runGeneration(
            listOf(input.toString()),
            Options(
                targetPackage = "sample.bindings",
                outputDir = output.toString(),
                multiplatform = true,
                libraries = listOf(
                    Options.Library("fixture", Options.Library.SpecKind.NAME),
                ),
                callbackBindings = callbackBindings,
            ),
        ) shouldBe KextractTool.SUCCESS

        AndroidSources(
            common = output.resolve("commonMain/kotlin/sample/bindings/wgpu_hCommon.kt").toFile().readText(),
            bridge = output.resolve("androidMain/kotlin/sample/bindings/wgpu_hAndroid.kt").toFile().readText(),
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

private val FUNCTION_HEADER =
    """
    typedef struct WGPUInstanceDescriptor {
        int dummy;
    } WGPUInstanceDescriptor;

    typedef struct WGPUInstanceImpl* WGPUInstance;

    typedef enum WGPUFeatureFlags {
        WGPUFeatureFlags_Undefined = 0,
        WGPUFeatureFlags_DepthClipControl = 0x1,
    } WGPUFeatureFlags;

    typedef enum WGPUFoo {
        WGPUFoo_A = 0,
        WGPUFoo_B = 1,
    } WGPUFoo;

    unsigned int wgpuFoo(unsigned int a, unsigned int b, unsigned int c, unsigned int d);
    void wgpuBar(WGPUInstance i, WGPUInstance j, unsigned long long n);
    const char* wgpuGetLabel(WGPUInstance instance);
    WGPUInstance wgpuCreateInstance(const WGPUInstanceDescriptor* descriptor);
    unsigned int wgpuFooFlag(WGPUFeatureFlags flags);
    WGPUFeatureFlags wgpuFlagCarrier(WGPUFeatureFlags flags);
    WGPUFoo wgpuPlainFoo(WGPUFoo foo);
    """.trimIndent()

private val STRUCT_VALUE_HEADER =
    """
    typedef struct Box { int x; int y; } Box;
    Box wgpuPointByValue(int x);
    """.trimIndent()

private val SINGLE_WORD_STRUCT_RETURN_HEADER =
    """
    typedef struct WGPUFuture { unsigned long long id; } WGPUFuture;
    WGPUFuture wgpuRequestFuture(void);
    """.trimIndent()

private val NESTED_ARRAY_STRUCT_VALUE_HEADER =
    """
    typedef struct WGPUPoint { float x; float y; } WGPUPoint;
    typedef struct WGPUPacket {
        unsigned int tag;
        WGPUPoint point;
        unsigned short samples[3];
    } WGPUPacket;
    WGPUPacket wgpuPacketByValue(WGPUPacket packet);
    """.trimIndent()

private val DIRECT_CALLBACK_BINDING_HEADER =
    """
    typedef struct WGPUPoint { int x; int y; } WGPUPoint;

    typedef void (*SampleCallback)(unsigned int value, void * userdata);

    void sample_request(SampleCallback callback, void * userdata, long long input);
    void sample_status(SampleCallback callback, void * userdata, long long status);
    """.trimIndent()

private fun directCallbackBindingConfig(): CallbackBindingsConfig =
    CallbackBindingsConfig().also { bindings ->
        bindings.directFunctionBindings = listOf(
            DirectFunctionBinding().also { binding ->
                binding.function = "function:sample_request"
                binding.callbackParameter = "callback"
                binding.callbackType = "typedef:SampleCallback"
                binding.routingUserdataParameter = "userdata"
            },
            DirectFunctionBinding().also { binding ->
                binding.function = "function:sample_status"
                binding.callbackParameter = "callback"
                binding.callbackType = "typedef:SampleCallback"
                binding.routingUserdataParameter = "userdata"
            },
        )
    }

private val ENGINE_FIT_NEGATIVE_HEADER =
    """
    typedef enum sample_enum {
        A = 1,
        B = 2,
    } sample_enum;

    typedef void (*SampleEnumCallback)(sample_enum value, void * userdata);
    typedef void (*SampleU64Callback)(unsigned long long value, void * userdata);
    typedef void (*SampleReversedCallback)(void * userdata, unsigned int value);

    void sample_enum_cb(SampleEnumCallback callback, void * userdata, long long input);
    void sample_u64_cb(SampleU64Callback callback, void * userdata, long long input);
    void sample_reversed_cb(SampleReversedCallback callback, void * userdata, long long input);
    """.trimIndent()

private fun engineFitNegativeBindingConfig(): CallbackBindingsConfig =
    CallbackBindingsConfig().also { bindings ->
        bindings.directFunctionBindings = listOf(
            DirectFunctionBinding().also { binding ->
                binding.function = "function:sample_enum_cb"
                binding.callbackParameter = "callback"
                binding.callbackType = "typedef:SampleEnumCallback"
                binding.routingUserdataParameter = "userdata"
            },
            DirectFunctionBinding().also { binding ->
                binding.function = "function:sample_u64_cb"
                binding.callbackParameter = "callback"
                binding.callbackType = "typedef:SampleU64Callback"
                binding.routingUserdataParameter = "userdata"
            },
            DirectFunctionBinding().also { binding ->
                binding.function = "function:sample_reversed_cb"
                binding.callbackParameter = "callback"
                binding.callbackType = "typedef:SampleReversedCallback"
                binding.routingUserdataParameter = "userdata"
            },
        )
    }

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
    interface CallbackRegistration<C : Callback> {
        val callback: NativeAddress
        val userdata: NativeAddress?
    }
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
        ): CallbackRegistration<C> = object : CallbackRegistration<C> {
            override val callback: NativeAddress = trampoline
            override val userdata: NativeAddress? = null
        }
        fun <C : Callback> prepare(
            type: CallbackType<C>,
            trampoline: NativeAddress,
            policy: CallbackPolicy,
            onError: CallbackExceptionHandler,
            callback: C,
        ): PreparedCallbackRegistration<C> = PreparedCallbackRegistration()
        fun <C : Callback> activateForNativeCall(
            prepared: PreparedCallbackRegistration<C>,
            downcall: (CallbackRegistration<C>) -> Unit,
        ): CallbackRegistration<C> = object : CallbackRegistration<C> {
            override val callback: NativeAddress = NativeAddress(0L)
            override val userdata: NativeAddress? = null
        }
        fun <C : Callback> rearmAfterNativeQuiescence(
            type: CallbackType<C>,
            trampoline: NativeAddress,
            policy: CallbackPolicy,
            onError: CallbackExceptionHandler,
            callback: C,
        ): CallbackRegistration<C> = object : CallbackRegistration<C> {
            override val callback: NativeAddress = trampoline
            override val userdata: NativeAddress? = null
        }
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

    fun NativeAddress?.toAddress(): Long = this?.rawValue ?: 0L
    """.trimIndent()

private val MEMBACK_KFFI_ENGINE_STUB =
    """
    package org.graphiks.kffi.engine

    import org.graphiks.kffi.MemoryBuffer
    import org.graphiks.kffi.NativeAddress
    import java.util.concurrent.ConcurrentHashMap
    import java.util.concurrent.atomic.AtomicLong

    object NativeEngine {
        private val handlers = ConcurrentHashMap<Long, (List<Long>) -> Long>()
        private val next = AtomicLong(0x1000L)
        private const val fixtureLibraryHandle = 0x7000L

        fun loadNativeLibrary(path: String): Long {
            check(path == "libfixture.so") { "Unexpected native library path: ${'$'}path" }
            return fixtureLibraryHandle
        }

        fun resolveSymbolIn(handle: Long, name: String): Long {
            check(handle == fixtureLibraryHandle) { "Unexpected native library handle: ${'$'}handle" }
            return resolveSymbol(name)
        }

        fun resolveSymbol(name: String): Long {
            val address = next.getAndIncrement()
            handlers[address] = when (name) {
                "wgpuFoo" -> { args -> args[0] + args[1] }
                "wgpuBar" -> { _ -> 0L }
                "wgpuGetLabel" -> { _ -> 0L }
                "wgpuCreateInstance" -> { _ -> 0L }
                "wgpuFooFlag" -> { args -> args[0] }
                "wgpuPlainFoo" -> { args -> args[0] }
                "wgpuGetAdapterVendorID" -> { _ -> 0x5b2cL }
                "wgpuPointByValue" -> { args -> args[0] }
                "sample_request" -> { args -> args[0] }
                "sample_status" -> { args -> args[0] }
                "sample_point" -> { args -> args[0] }
                else -> error("Unexpected stub symbol ${'$'}name")
            }
            return address
        }

        fun callI1P(fn: Long, p1: Long): Long = handlers.getValue(fn)(listOf(p1))
        fun callI4IIII(fn: Long, a: Int, b: Int, c: Int, d: Int): Long =
            handlers.getValue(fn)(listOf(a.toLong(), b.toLong(), c.toLong(), d.toLong()))
        fun callL2LL(fn: Long, a: Long, b: Long): Long = handlers.getValue(fn)(listOf(a, b))
        fun callV2PI(fn: Long, p1: Long, i: Int) { handlers.getValue(fn)(listOf(p1, i.toLong())) }
        fun callV3PPL(fn: Long, a: Long, b: Long, c: Long) { handlers.getValue(fn)(listOf(a, b, c)) }
        fun callP0(fn: Long): Long = handlers.getValue(fn)(emptyList())
        fun callP1P(fn: Long, p1: Long): Long = handlers.getValue(fn)(listOf(p1))
        fun callI1I(fn: Long, a: Int): Long = handlers.getValue(fn)(listOf(a.toLong()))
        fun callV3IPP(fn: Long, a: Int, b: Long, c: Long) { handlers.getValue(fn)(listOf(a.toLong(), b, c)) }
        fun callI2PP(fn: Long, a: Long, b: Long): Long = handlers.getValue(fn)(listOf(a, b))
        fun callGeneric(fn: Long, argc: Int, typeSpec: String, argsPtr: Long, outPtr: Long) {
            val values = (0 until argc).map { i ->
                MemoryBuffer(NativeAddress(argsPtr + i.toLong() * 8L), 8uL).readLong(0uL)
            }
            val result = handlers.getValue(fn)(values)
            MemoryBuffer(NativeAddress(outPtr), 8uL).writeLong(result, 0uL)
        }
    }

    object UpcallEngine {
        fun allocateTrampoline(
            dispatcherClass: Class<*>,
            dispatchMethod: String,
            dispatchJvmSignature: String,
            dispatchAbiSignature: String,
        ): Long = 0x6000L

        fun freeTrampoline(address: Long) = Unit
    }
    """.trimIndent()

private fun compileGeneratedAndroid(sources: AndroidSources, probe: String? = null): LongArray? {
    val workspace = Files.createTempDirectory("kextract-generated-android-memory-classes")
    try {
        val common = workspace.resolve("wgpu_hCommon.kt")
        val bridge = workspace.resolve("wgpu_hAndroid.kt")
        val kffiCommon = workspace.resolve("kffiCommon.kt")
        val kffiAndroid = workspace.resolve("kffiAndroid.kt")
        val kffiEngine = workspace.resolve("kffiEngine.kt")
        val probeFile = probe?.let { workspace.resolve("probe.kt") }
        val output = Files.createDirectories(workspace.resolve("classes"))
        common.toFile().writeText(sources.common)
        // The generated Android bridge loads its declared APK library and resolves
        // symbols through its handle. These JVM-hosted tests use a fake NativeEngine,
        // so keep the source assertions above but omit the platform load while
        // executing the generated probe.
        bridge.toFile().writeText(
            sources.bridge
                .replace(
                    "java.lang.System.loadLibrary(\"fixture\")",
                    "// Native library loading is supplied by the Android APK",
                )
                .replace(
                    "java.lang.System.mapLibraryName(\"fixture\")",
                    "\"libfixture.so\"",
                ),
        )
        kffiCommon.toFile().writeText(MEMBACK_KFFI_COMMON_STUB)
        kffiAndroid.toFile().writeText(MEMBACK_KFFI_ANDROID_STUB)
        kffiEngine.toFile().writeText(MEMBACK_KFFI_ENGINE_STUB)
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
            kffiCommon.toString(),
            kffiAndroid.toString(),
            kffiEngine.toString(),
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
        generated.bridge shouldContain "private val mem: MemoryBuffer by lazy { MemoryBuffer(handle, ${size}uL) }"

        generated.bridge shouldContain "get() = mem.readUInt(${vendorIDOffset}uL)"
        generated.bridge shouldContain "set(value) { mem.writeUInt(value, ${vendorIDOffset}uL) }"
        generated.bridge shouldContain "get() = mem.readULong(${adapterIDOffset}uL)"
        generated.bridge shouldContain "set(value) { mem.writeULong(value, ${adapterIDOffset}uL) }"
        generated.bridge shouldContain "get() = mem.readPointer(${vendorNameOffset}uL).takeIf { it.rawValue != 0L }?.let(::CString)"
        generated.bridge shouldContain "set(value) { mem.writePointer(value?.handler ?: NativeAddress(0L), ${vendorNameOffset}uL) }"
        generated.bridge shouldContain "get() = mem.readPointer(${layout.field("nextInChain").offsetBytes}uL).takeIf { it.rawValue != 0L }?.let { WGPUChainedStruct(it) }"
        generated.bridge shouldContain "set(value) { mem.writePointer(value?.handler ?: NativeAddress(0L), ${layout.field("nextInChain").offsetBytes}uL) }"
        generated.bridge shouldContain "get() = mem.readByte(${hostOffset}uL) != 0.toByte()"
        generated.bridge shouldContain "set(value) { mem.writeByte(if (value) 1 else 0, ${hostOffset}uL) }"
        generated.bridge shouldContain "get() = WGPULimits.ByValue(NativeAddress(handle.rawValue + ${limitsOffset}L))"
        generated.bridge shouldContain "mem.writeBytes(bytes, 0u, ${limitsOffset}uL, ${layout.field("limits").sizeBytes}uL)"
        // Plain C enums may use a signed or unsigned default carrier depending
        // on the host ABI (Windows commonly selects signed int).
        generated.bridge.shouldContainAny(
            "get() = WGPUFeatureFlags((mem.readInt(${flagsOffset}uL)).toUInt().toLong())",
            "get() = WGPUFeatureFlags((mem.readInt(${flagsOffset}uL)).toLong())",
        )
        generated.bridge shouldContain "set(value) { mem.writeInt(value.rawValue.toInt(), ${flagsOffset}uL) }"
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
        generated.bridge shouldContain "get() = mem.readUInt(0uL)"
        generated.bridge shouldContain "get() = mem.readDouble(0uL)"
        generated.bridge shouldContain "get() = mem.readByte(0uL) != 0.toByte()"
        generated.bridge shouldContain "private val mem: MemoryBuffer by lazy { MemoryBuffer(handle, ${layout.sizeBytes}uL) }"
        generated.bridge shouldContain "get() = WGPUScalar.ByValue(NativeAddress(handle.rawValue + ${container.field("scalar").offsetBytes}L))"
        generated.bridge shouldContain "set(value) { mem.writePointer(value?.handler ?: NativeAddress(0L), ${container.field("scalarPointer").offsetBytes}uL) }"
    }

    "functions resolve symbols once and call the typed NativeEngine wrapper" {
        val generated = generateAndroidSources(FUNCTION_HEADER)

        generated.bridge shouldContain "private object KextractAndroidBootstrap"
        generated.bridge shouldContain "java.lang.System.loadLibrary(\"fixture\")"
        generated.bridge shouldContain "private val libraryHandle: kotlin.Long by lazy"
        generated.bridge shouldContain "NativeEngine.loadNativeLibrary(java.lang.System.mapLibraryName(\"fixture\"))"
        generated.bridge shouldContain "NativeEngine.resolveSymbolIn(libraryHandle, name)"
        generated.bridge shouldContain "private val wgpuFoo_ADDR: Long by lazy { KextractAndroidBootstrap.resolve(\"wgpuFoo\") }"
        generated.bridge shouldContain "private val wgpuBar_ADDR: Long by lazy { KextractAndroidBootstrap.resolve(\"wgpuBar\") }"
        generated.bridge shouldContain "private val wgpuGetLabel_ADDR: Long by lazy { KextractAndroidBootstrap.resolve(\"wgpuGetLabel\") }"
        generated.bridge shouldContain "private val wgpuCreateInstance_ADDR: Long by lazy { KextractAndroidBootstrap.resolve(\"wgpuCreateInstance\") }"

        generated.bridge shouldContain "actual fun wgpuFoo(a: UInt, b: UInt, c: UInt, d: UInt): UInt"
        generated.bridge shouldContain "return NativeEngine.callI4IIII(wgpuFoo_ADDR, a.toInt(), b.toInt(), c.toInt(), d.toInt()).toInt().toUInt()"

        generated.bridge shouldContain "actual fun wgpuBar(i: WGPUInstance?, j: WGPUInstance?, n: ULong)"
        generated.bridge shouldContain "NativeEngine.callV3PPL(wgpuBar_ADDR, i?.handler?.rawValue ?: 0L, j?.handler?.rawValue ?: 0L, n.toLong())"

        generated.bridge shouldContain "actual fun wgpuGetLabel(instance: WGPUInstance?): CString?"
        generated.bridge shouldContain "return NativeEngine.callP1P(wgpuGetLabel_ADDR, instance?.handler?.rawValue ?: 0L).takeIf { it != 0L }?.let(::NativeAddress)?.let(::CString)"

        generated.bridge shouldContain "actual fun wgpuCreateInstance(descriptor: WGPUInstanceDescriptor?): WGPUInstance?"
        generated.bridge shouldContain "NativeEngine.callP1P(wgpuCreateInstance_ADDR, descriptor?.handler?.rawValue ?: 0L)"
        generated.bridge shouldContain "?.let(::NativeAddress)?.let(::WGPUInstance)"
    }

    "enum and options functions convert carriers through the engine" {
        val generated = generateAndroidSources(FUNCTION_HEADER)

        generated.bridge shouldContain "actual fun wgpuFooFlag(flags: WGPUFeatureFlags): UInt"
        generated.bridge shouldContain "return NativeEngine.callI1I(wgpuFooFlag_ADDR, flags.rawValue.toInt()).toInt().toUInt()"

        generated.bridge shouldContain "actual fun wgpuFlagCarrier(flags: WGPUFeatureFlags): WGPUFeatureFlags"
        generated.bridge.shouldContainAny(
            "return WGPUFeatureFlags((NativeEngine.callI1I(wgpuFlagCarrier_ADDR, flags.rawValue.toInt()).toInt()).toUInt().toLong())",
            "return WGPUFeatureFlags((NativeEngine.callI1I(wgpuFlagCarrier_ADDR, flags.rawValue.toInt()).toInt()).toLong())",
        )

        generated.bridge shouldContain "actual fun wgpuPlainFoo(foo: WGPUFoo): WGPUFoo"
        generated.bridge.shouldContainAny(
            "return (NativeEngine.callI1I(wgpuPlainFoo_ADDR, foo.toInt()).toInt()).toUInt()",
            "return NativeEngine.callI1I(wgpuPlainFoo_ADDR, foo).toInt()",
        )
    }

    "function emission never references the JNA library proxy" {
        val generated = generateAndroidSources(FUNCTION_HEADER)

        generated.bridge shouldNotContain "LibraryInstance"
        generated.bridge shouldNotContain "com.sun.jna.Library"
        generated.bridge shouldNotContain "Native.load"
        generated.bridge shouldNotContain "com.sun.jna"
    }

    "struct-by-value functions emit complete libffi layouts" {
        val generated = generateAndroidSources(STRUCT_VALUE_HEADER)

        generated.bridge shouldContain "actual fun wgpuPointByValue(allocator: MemoryAllocator, x: Int): Box"
        generated.bridge shouldContain "private val wgpuPointByValue_ADDR: Long by lazy { KextractAndroidBootstrap.resolve(\"wgpuPointByValue\") }"
        generated.bridge shouldContain "NativeEngine.callGeneric(wgpuPointByValue_ADDR, 1,"
        generated.bridge shouldContain "\"s8@4(i32,i32):i32\""
        generated.bridge shouldContain "return Box.ByValue(out.handler)"
    }

    "single-word struct returns use their scalar Android ABI carrier" {
        val generated = generateAndroidSources(SINGLE_WORD_STRUCT_RETURN_HEADER)

        generated.bridge shouldContain "actual fun wgpuRequestFuture(allocator: MemoryAllocator): WGPUFuture"
        generated.bridge shouldContain "NativeEngine.callGeneric(wgpuRequestFuture_ADDR, 0,"
        generated.bridge shouldContain "\"u64:\""
        generated.bridge shouldNotContain "\"s8@8(u64):\""
        generated.bridge shouldContain "return WGPUFuture.ByValue(out.handler)"
    }

    "struct-by-value layouts encode nested structs and fixed arrays" {
        val generated = generateAndroidSources(NESTED_ARRAY_STRUCT_VALUE_HEADER)

        generated.bridge shouldContain "actual fun wgpuPacketByValue(allocator: MemoryAllocator, packet: WGPUPacket): WGPUPacket"
        generated.bridge shouldContain
            "\"s20@4(i32,s8@4(f32,f32),a3(i16)):s20@4(i32,s8@4(f32,f32),a3(i16))\""
    }

    "generated memory-backed sources compile against the kffi runtime" {
        compileGeneratedAndroid(generateAndroidSources(ADAPTER_INFO_HEADER))
        compileGeneratedAndroid(generateAndroidSources(GENERAL_UNION_HEADER))
        compileGeneratedAndroid(generateAndroidSources(NATIVE_DISPLAY_HEADER))
        compileGeneratedAndroid(generateAndroidSources(FUNCTION_HEADER))
        compileGeneratedAndroid(generateAndroidSources(STRUCT_VALUE_HEADER))
        compileGeneratedAndroid(generateAndroidSources(SINGLE_WORD_STRUCT_RETURN_HEADER))
        compileGeneratedAndroid(generateAndroidSources(NESTED_ARRAY_STRUCT_VALUE_HEADER))
        compileGeneratedAndroid(generateAndroidSources(DIRECT_CALLBACK_BINDING_HEADER, directCallbackBindingConfig()))
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
        generated.bridge shouldContain "mem.writeBytes(bytes, 0u, ${dataOffset}uL, ${xlibSize}uL)"

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

    "scalar functions resolve and call through the engine stub" {
        val probe =
            """
            package sample.probe

            import sample.bindings.wgpuFoo
            import sample.bindings.wgpuGetLabel

            fun runProbe(): LongArray {
                val sum = wgpuFoo(5u, 7u, 0u, 0u)
                val sum2 = wgpuFoo(2u, 3u, 0u, 0u)
                val label = wgpuGetLabel(null)
                return longArrayOf(sum.toLong(), sum2.toLong(), if (label == null) 1L else 0L)
            }
            """.trimIndent()

        compileGeneratedAndroid(generateAndroidSources(FUNCTION_HEADER), probe)
            ?.toList() shouldBe listOf(12L, 5L, 1L)
    }

    "struct-by-value functions pin the engine callGeneric path" {
        val generated = generateAndroidSources(STRUCT_VALUE_HEADER)

        generated.bridge shouldContain "actual fun wgpuPointByValue(allocator: MemoryAllocator, x: Int): Box"
        generated.bridge shouldContain "NativeEngine.callGeneric(wgpuPointByValue_ADDR, 1,"
        generated.bridge shouldContain "return Box.ByValue(out.handler)"
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
        // Plain C enums have no fixed underlying type. libclang reports the
        // carrier selected by the target ABI: the Windows/MSVC target uses a
        // signed 32-bit int here, while Unix targets may select unsigned int.
        // Both carriers have the same four-byte ABI width, so this assertion
        // must validate the plain-enum mapping without hard-coding signedness.
        generated.common.shouldContainAny(
            "typealias WGPUFoo = UInt",
            "typealias WGPUFoo = Int",
        )
        generated.common shouldNotContain "value class WGPUFoo"

        generated.bridge shouldContain "WGPUInstanceBackend((mem.readInt(0uL))"
        generated.bridge shouldContain "set(value) { mem.writeInt(value.rawValue.toInt(), 0uL) }"
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

    "direct callback trampolines allocate through the kffi upcall engine" {
        val generated = generateAndroidSources(
            DIRECT_CALLBACK_BINDING_HEADER,
            directCallbackBindingConfig(),
        )

        generated.bridge shouldContain "import org.graphiks.kffi.engine.UpcallEngine"
        generated.bridge shouldContain "import kotlin.jvm.JvmStatic"
        generated.bridge shouldContain "private object SampleCallbackTrampoline {"
        generated.bridge shouldContain "val address: NativeAddress by lazy {"
        generated.bridge shouldContain "NativeAddress(UpcallEngine.allocateTrampoline("
        generated.bridge shouldContain "dispatcherClass = SampleCallbackTrampoline::class.java,"
        generated.bridge shouldContain "dispatchMethod = \"dispatch\","
        generated.bridge shouldContain "dispatchJvmSignature = \"(JI)V\","
        generated.bridge shouldContain "dispatchAbiSignature = \"v(u32,ptr)\","
        generated.bridge shouldContain "@JvmStatic"
        generated.bridge shouldContain "fun dispatch(token: Long, value: Int) {"
        generated.bridge shouldContain "CallbackRuntime.dispatchSafely("
        generated.bridge shouldContain "type = SampleCallbackType,"
        generated.bridge shouldContain "userdata = NativeAddress(token),"
        generated.bridge shouldContain "callback.invoke(value.toUInt())"
        generated.bridge shouldNotContain "com.sun.jna.CallbackReference"
        generated.bridge shouldNotContain "SampleCallbackJna"
        generated.bridge shouldNotContain "TODO(M5.5)"

        compileGeneratedAndroid(generated)
    }

    "enum-backed callback values use the dynamic engine ABI" {
        val generated = generateAndroidSources(
            ENGINE_FIT_NEGATIVE_HEADER,
            engineFitNegativeBindingConfig(),
        )

        generated.bridge shouldContain "UpcallEngine.allocateTrampoline"
        generated.bridge shouldContain "dispatchJvmSignature = \"(JI)V\","
        generated.bridge.shouldContainAny(
            "dispatchAbiSignature = \"v(i32,ptr)\",",
            "dispatchAbiSignature = \"v(u32,ptr)\",",
        )
        generated.bridge shouldNotContain "SampleEnumCallbackJna"
    }

    "non-I32 callback scalars use the dynamic engine ABI" {
        val generated = generateAndroidSources(
            ENGINE_FIT_NEGATIVE_HEADER,
            engineFitNegativeBindingConfig(),
        )

        generated.bridge shouldContain "UpcallEngine.allocateTrampoline"
        generated.bridge shouldContain "dispatchJvmSignature = \"(JJ)V\","
        generated.bridge shouldContain "dispatchAbiSignature = \"v(u64,ptr)\","
        generated.bridge shouldNotContain "SampleU64CallbackJna"
    }

    "reversed routing-order callbacks are excluded from the engine fit gate" {
        val generated = generateAndroidSources(
            ENGINE_FIT_NEGATIVE_HEADER,
            engineFitNegativeBindingConfig(),
        )
        val reversedCallback = generated.bridge.substringAfter("private fun interface SampleReversedCallbackJna")

        generated.bridge shouldContain "private fun interface SampleReversedCallbackJna : com.sun.jna.Callback"
        reversedCallback shouldNotContain "UpcallEngine.allocateTrampoline"
        reversedCallback shouldNotContain "dispatchJvmSignature = \"(JI)V\","
    }

    "direct callback binding preflights compile their Android lambda bodies" {
        val generated = generateAndroidSources(
            DIRECT_CALLBACK_BINDING_HEADER,
            directCallbackBindingConfig(),
        )

        generated.bridge shouldContain "actual fun SampleCallback.Companion.register("
        generated.bridge shouldContain "internal actual fun sample_requestCallbackBindingPreflight("
        generated.bridge shouldContain """
            internal actual fun sample_statusCallbackBindingPreflight(
                status: Long,
            ): (NativeAddress?, NativeAddress?) -> Unit
        """.trimIndent()
        generated.bridge shouldContain "NativeEngine.callV3PPL(sample_request_ADDR, callback.toAddress(), userdata.toAddress(), input)"
        generated.bridge shouldContain "NativeEngine.callV3PPL(sample_status_ADDR, callback.toAddress(), userdata.toAddress(), status)"

        val probe =
            """
            package sample.probe

            import org.graphiks.kffi.NativeAddress
            import sample.bindings.sample_requestCallbackBindingPreflight
            import sample.bindings.sample_statusCallbackBindingPreflight

            fun runProbe(): LongArray {
                sample_requestCallbackBindingPreflight(7L)(NativeAddress(0x1000L), NativeAddress(0x2000L))
                sample_statusCallbackBindingPreflight(0L)(NativeAddress(0x1000L), NativeAddress(0x2000L))
                return longArrayOf(1L)
            }
            """.trimIndent()

        compileGeneratedAndroid(generated, probe)?.toList() shouldBe listOf(1L)
    }
})
