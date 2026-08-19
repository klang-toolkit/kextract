package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.callbacks.CallbackBindingsConfig
import org.graphiks.kextract.callbacks.CallbackInfoBinding
import org.graphiks.kextract.callbacks.CallbackInfoLifetime
import org.graphiks.kextract.callbacks.CallbackInfoMode
import org.graphiks.kextract.callbacks.CallbackInfoOwner
import org.graphiks.kextract.callbacks.DirectFunctionBinding
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import java.io.ByteArrayOutputStream
import java.io.PrintWriter
import java.nio.file.Files

private fun String.shouldContainAny(vararg candidates: String) {
    candidates.any { contains(it) } shouldBe true
}

class CallbackGeneratorIntegrationTest : FreeSpec({
    fun generateKmp(
        header: String,
        callbackBindings: CallbackBindingsConfig? = null,
    ): Map<String, String> {
        val workspace = Files.createTempDirectory("kextract-callback-generator")
        val input = workspace.resolve("wgpu.h")
        val output = Files.createTempDirectory("kextract-callback-generator-out")
        return try {
            input.toFile().writeText(header)
            KextractTool(Logger()).runGeneration(
                listOf(input.toString()),
                Options(
                    targetPackage = "sample.bindings",
                    outputDir = output.toString(),
                    multiplatform = true,
                    callbackBindings = callbackBindings,
                ),
            ) shouldBe KextractTool.SUCCESS

            listOf("commonMain", "jvmMain", "nativeMain", "androidMain").associateWith { sourceSet ->
                Files.walk(output.resolve(sourceSet)).use { paths ->
                    paths.filter { it.fileName.toString().endsWith(".kt") }
                        .map { it.toFile().readText() }
                        .toList()
                        .joinToString("\n")
                }
            }
        } finally {
            workspace.toFile().deleteRecursively()
            output.toFile().deleteRecursively()
        }
    }

    fun generateKmpFailure(header: String): String {
        val input = Files.createTempFile("kextract-invalid-callback-carrier", ".h")
        val output = Files.createTempDirectory("kextract-invalid-callback-carrier-out")
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
                Options(outputDir = output.toString(), multiplatform = true),
            ) shouldBe KextractTool.FAILURE
            errors.toString()
        } finally {
            input.toFile().delete()
            output.toFile().deleteRecursively()
        }
    }

    fun compileGeneratedJvmFixture(commonSource: String, jvmSource: String) {
        val workspace = Files.createTempDirectory("kextract-callback-name-classes")
        try {
            val common = workspace.resolve("callbackNamesCommon.kt")
            val jvm = workspace.resolve("callbackNamesJvm.kt")
            val kffiCommon = workspace.resolve("kffiCommon.kt")
            val kffiJvm = workspace.resolve("kffiJvm.kt")
            val kffiEngine = workspace.resolve("kffiEngine.kt")
            val output = Files.createDirectories(workspace.resolve("classes"))
            common.toFile().writeText(commonSource)
            jvm.toFile().writeText(jvmSource)
            kffiCommon.toFile().writeText(
                """
                package org.graphiks.kffi

                expect value class NativeAddress(val rawValue: Long)
                interface Callback
                enum class CallbackPolicy { ONCE, REPEATING }
                fun interface CallbackExceptionHandler {
                    fun onException(error: Throwable)
                    companion object {
                        val Default = CallbackExceptionHandler { }
                    }
                }
                interface CallbackRegistration<C : Callback> : AutoCloseable {
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
                    ): CallbackRegistration<C> = error("fixture")
                    fun <C : Callback> prepare(
                        type: CallbackType<C>,
                        trampoline: NativeAddress,
                        policy: CallbackPolicy,
                        onError: CallbackExceptionHandler,
                        callback: C,
                    ): PreparedCallbackRegistration<C> = error("fixture")
                    fun <C : Callback> rearmAfterNativeQuiescence(
                        type: CallbackType<C>,
                        trampoline: NativeAddress,
                        policy: CallbackPolicy,
                        onError: CallbackExceptionHandler,
                        callback: C,
                    ): CallbackRegistration<C> = error("fixture")
                    fun <C : Callback> activateForNativeCall(
                        prepared: PreparedCallbackRegistration<C>,
                        call: (CallbackRegistration<C>) -> Unit,
                    ): CallbackRegistration<C> = error("fixture")
                    fun <C : Callback> dispatchSafely(
                        type: CallbackType<C>,
                        userdata: NativeAddress?,
                        call: (C) -> Unit,
                    ) = Unit
                    fun reportUnroutedFailure(failure: Throwable) = Unit
                }
                expect value class CString(val handler: NativeAddress)
                @JvmInline
                value class ArrayHolder<T>(val handler: NativeAddress)
                expect class MemoryAllocator() {
                    fun allocate(byteSize: Long): NativeAddress
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
                """.trimIndent(),
            )
            kffiJvm.toFile().writeText(
                """
                package org.graphiks.kffi

                import java.lang.foreign.Arena
                import java.lang.foreign.MemorySegment
                import java.lang.foreign.ValueLayout

                @JvmInline
                actual value class NativeAddress actual constructor(actual val rawValue: Long) {
                    val handler: MemorySegment get() = MemorySegment.ofAddress(rawValue)
                }
                @JvmInline
                actual value class CString actual constructor(actual val handler: NativeAddress)
                actual class MemoryAllocator actual constructor() {
                    private val arena = Arena.global()
                    actual fun allocate(byteSize: Long): NativeAddress = NativeAddress(arena.allocate(byteSize).address())
                    actual fun allocateBuffer(size: ULong): MemoryBuffer =
                        MemoryBuffer(NativeAddress(arena.allocate(size.toLong()).address()), size)
                }
                actual class MemoryBuffer actual constructor(
                    actual val handler: NativeAddress,
                    actual val size: ULong,
                ) {
                    private val segment: MemorySegment = MemorySegment.ofAddress(handler.rawValue).reinterpret(size.toLong())
                    actual fun writeByte(value: Byte, offset: ULong) { segment.set(ValueLayout.JAVA_BYTE, offset.toLong(), value) }
                    actual fun readByte(offset: ULong): Byte = segment.get(ValueLayout.JAVA_BYTE, offset.toLong())
                    actual fun writeUByte(value: UByte, offset: ULong) { segment.set(ValueLayout.JAVA_BYTE, offset.toLong(), value.toByte()) }
                    actual fun readUByte(offset: ULong): UByte = segment.get(ValueLayout.JAVA_BYTE, offset.toLong()).toUByte()
                    actual fun writeShort(value: Short, offset: ULong) { segment.set(ValueLayout.JAVA_SHORT, offset.toLong(), value) }
                    actual fun readShort(offset: ULong): Short = segment.get(ValueLayout.JAVA_SHORT, offset.toLong())
                    actual fun writeUShort(value: UShort, offset: ULong) { segment.set(ValueLayout.JAVA_SHORT, offset.toLong(), value.toShort()) }
                    actual fun readUShort(offset: ULong): UShort = segment.get(ValueLayout.JAVA_SHORT, offset.toLong()).toUShort()
                    actual fun writeInt(value: Int, offset: ULong) { segment.set(ValueLayout.JAVA_INT, offset.toLong(), value) }
                    actual fun readInt(offset: ULong): Int = segment.get(ValueLayout.JAVA_INT, offset.toLong())
                    actual fun writeUInt(value: UInt, offset: ULong) { segment.set(ValueLayout.JAVA_INT, offset.toLong(), value.toInt()) }
                    actual fun readUInt(offset: ULong): UInt = segment.get(ValueLayout.JAVA_INT, offset.toLong()).toUInt()
                    actual fun writeLong(value: Long, offset: ULong) { segment.set(ValueLayout.JAVA_LONG, offset.toLong(), value) }
                    actual fun readLong(offset: ULong): Long = segment.get(ValueLayout.JAVA_LONG, offset.toLong())
                    actual fun writeULong(value: ULong, offset: ULong) { segment.set(ValueLayout.JAVA_LONG, offset.toLong(), value.toLong()) }
                    actual fun readULong(offset: ULong): ULong = segment.get(ValueLayout.JAVA_LONG, offset.toLong()).toULong()
                    actual fun writeFloat(value: Float, offset: ULong) { segment.set(ValueLayout.JAVA_FLOAT, offset.toLong(), value) }
                    actual fun readFloat(offset: ULong): Float = segment.get(ValueLayout.JAVA_FLOAT, offset.toLong())
                    actual fun writeDouble(value: Double, offset: ULong) { segment.set(ValueLayout.JAVA_DOUBLE, offset.toLong(), value) }
                    actual fun readDouble(offset: ULong): Double = segment.get(ValueLayout.JAVA_DOUBLE, offset.toLong())
                    actual fun writePointer(value: NativeAddress, offset: ULong) {
                        segment.set(ValueLayout.ADDRESS, offset.toLong(), MemorySegment.ofAddress(value.rawValue))
                    }
                    actual fun readPointer(offset: ULong): NativeAddress =
                        NativeAddress(segment.get(ValueLayout.ADDRESS, offset.toLong()).address())
                    actual fun readBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) {
                        segment.asSlice(bufferOffset.toLong(), size.toLong()).asByteBuffer().get(array, arrayIndex.toInt(), size.toInt())
                    }
                    actual fun writeBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) {
                        segment.asSlice(bufferOffset.toLong(), size.toLong()).asByteBuffer().put(array, arrayIndex.toInt(), size.toInt())
                    }
                }
                fun findOrThrow(name: String): Long = 0L
                """.trimIndent(),
            )
            kffiEngine.toFile().writeText(
                """
                package org.graphiks.kffi.engine

                import org.graphiks.kffi.NativeAddress
                import java.lang.foreign.Arena
                import java.lang.foreign.FunctionDescriptor
                import java.lang.foreign.Linker
                import java.lang.foreign.MemorySegment
                import java.lang.foreign.ValueLayout
                import java.lang.invoke.MethodHandle
                import java.lang.invoke.MethodHandles

                object JvmUpcallEngine {
                    private val linker = Linker.nativeLinker()
                    private val arena = Arena.global()

                    fun allocateTrampoline(
                        dispatcherClass: Class<*>,
                        dispatchMethod: String,
                        dispatchSig: String,
                    ): NativeAddress {
                        val (returnType, parameterTypes) = parseSig(dispatchSig)
                        val descriptor = if (returnType == null) {
                            FunctionDescriptor.ofVoid(*parameterTypes.map { it.layout }.toTypedArray())
                        } else {
                            FunctionDescriptor.of(returnType.layout, *parameterTypes.map { it.layout }.toTypedArray())
                        }
                        val methodHandle = MethodHandles.privateLookupIn(dispatcherClass, MethodHandles.lookup())
                            .findStatic(dispatcherClass, dispatchMethod, descriptor.toMethodType())
                        return NativeAddress(
                            MemorySegment.ofAddress(linker.upcallStub(methodHandle, descriptor, arena).address()).address(),
                        )
                    }

                    private enum class Carrier(val layout: ValueLayout) {
                        I(ValueLayout.JAVA_INT),
                        J(ValueLayout.JAVA_LONG),
                        F(ValueLayout.JAVA_FLOAT),
                        D(ValueLayout.JAVA_DOUBLE),
                        Z(ValueLayout.JAVA_BOOLEAN),
                    }

                    private fun parseSig(sig: String): Pair<Carrier?, List<Carrier>> {
                        val parameters = sig.substringAfter('(').substringBefore(')')
                            .map { Carrier.valueOf(it.toString()) }
                        val returnPart = sig.substringAfter(')')
                        return (if (returnPart == "V") null else Carrier.valueOf(returnPart)) to parameters
                    }
                }

                object JvmDowncallEngine {
                    private val linker = Linker.nativeLinker()

                    fun resolveSymbol(name: String): Long = 0L

                    private fun segment(address: Long): MemorySegment = MemorySegment.ofAddress(address)

                    private fun handle(fn: Long, descriptor: FunctionDescriptor): MethodHandle =
                        linker.downcallHandle(segment(fn), descriptor)

                    fun callV0(fn: Long) {
                        handle(fn, FunctionDescriptor.ofVoid()).invokeExact()
                    }

                    fun callV1P(fn: Long, p1: Long) {
                        handle(fn, FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)).invokeExact(segment(p1))
                    }

                    fun callV2PP(fn: Long, p1: Long, p2: Long) {
                        handle(fn, FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS))
                            .invokeExact(segment(p1), segment(p2))
                    }

                    fun callV3PPL(fn: Long, p1: Long, p2: Long, a3: Long) {
                        handle(fn, FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG))
                            .invokeExact(segment(p1), segment(p2), a3)
                    }

                    fun callI0(fn: Long): Long =
                        handle(fn, FunctionDescriptor.of(ValueLayout.JAVA_LONG)).invokeExact() as Long

                    data class StructField(val cName: String, val kind: FieldKind, val offsetBytes: Long)

                    enum class FieldKind { INT8, UINT8, INT16, UINT16, INT32, UINT32, INT64, UINT64, FLOAT32, FLOAT64, POINTER, STRUCT, PADDING }

                    fun registerStructLayout(name: String, sizeBytes: Long, alignmentBytes: Long, fields: List<StructField>) {}
                }
                """.trimIndent(),
            )

            K2JVMCompiler().exec(
                System.err,
                "-no-stdlib",
                "-no-reflect",
                "-Xmulti-platform",
                "-Xcommon-sources=$common,$kffiCommon",
                "-classpath",
                System.getProperty("java.class.path"),
                "-d",
                output.toString(),
                common.toString(),
                jvm.toString(),
                kffiCommon.toString(),
                kffiJvm.toString(),
                kffiEngine.toString(),
            ) shouldBe ExitCode.OK
        } finally {
            workspace.toFile().deleteRecursively()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun generatedCallbackReservedIdentifiers(): Set<String> {
        val field = Class.forName("org.graphiks.kextract.kotlin.KotlinGeneratorKt")
            .getDeclaredField("GENERATED_CALLBACK_RESERVED_IDENTIFIERS")
            .apply { isAccessible = true }
        return field.get(null) as Set<String>
    }

    val genericCallbacks = """
        typedef void (*SampleCallback)(unsigned int value, void * userdata1, void * userdata2);
        typedef void (*NoUserdataCallback)(unsigned int value);

        typedef struct SampleCallbackFields {
            SampleCallback callback;
        } SampleCallbackFields;

        void sample_set_callback(SampleCallback callback);
        unsigned int sample_get_value(unsigned int value);
    """.trimIndent()

    val abiCallbacks = """
        typedef enum LargeStatus : unsigned long long {
            LargeStatus_Zero = 0,
            LargeStatus_High = 0x100000000ULL
        } LargeStatus;

        typedef enum LargeOptions : unsigned long long {
            LargeOptions_None = 0,
            LargeOptions_High = 0x100000000ULL
        } LargeOptions;

        typedef enum NarrowOptions : unsigned int {
            NarrowOptions_None = 0,
            NarrowOptions_High = 0x80000000U
        } NarrowOptions;

        typedef struct SamplePayload {
            unsigned long long value;
        } SamplePayload;

        typedef struct WGPUDeviceImpl * WGPUDevice;

        typedef void (*AbiCallback)(
            LargeStatus status,
            LargeOptions options,
            SamplePayload payload,
            WGPUDevice const * device,
            void * userdata
        );

        typedef void (*NarrowOptionsCallback)(
            NarrowOptions options,
            void * userdata
        );
    """.trimIndent()

    "callback names are valid and collision-free in every generated target" {
        val config = CallbackBindingsConfig().also { bindings ->
            bindings.directFunctionBindings = listOf(
                DirectFunctionBinding().also { binding ->
                    binding.function = "function:set_class_callback"
                    binding.callbackParameter = "callback"
                    binding.callbackType = "typedef:class"
                    binding.routingUserdataParameter = "userdata"
                },
            )
        }
        val generated = generateKmp(
            """
                typedef void (*class)(int callback,
                                      int failure,
                                      int policy,
                                      int onError,
                                      int fun,
                                      int fun_,
                                      void *userdata);
                void set_class_callback(class callback, void *userdata, long long policy);
            """.trimIndent(),
            config,
        )
        val common = generated.getValue("commonMain")
        val jvm = generated.getValue("jvmMain")
        val native = generated.getValue("nativeMain")
        val android = generated.getValue("androidMain")

        common shouldContain "fun interface class_ : Callback"
        common shouldContain "fun_: Int,"
        common shouldContain "fun__2: Int,"
        common shouldContain "canonicalId = \"typedef:class\""
        common shouldContain "policy_2: Long,"
        common shouldContain "callback: class_,"
        common shouldContain """
            fun set_class_callback(
                policy_2: Long,
                policy: CallbackPolicy,
                onError: CallbackExceptionHandler = CallbackExceptionHandler.Default,
                callback: class_,
            ): CallbackRegistration<class_>
        """.trimIndent()
        common shouldNotContain "policy: Int,\n    policy: CallbackPolicy,"
        jvm shouldContain "private object class_Trampoline"
        jvm shouldContain "actual fun class_.Companion.register("
        jvm shouldContain "findOrThrow(\"set_class_callback\")"
        native shouldContain "private val class_Trampoline = staticCFunction"
        native shouldContain "actual fun class_.Companion.register("
        android shouldContain "actual fun class_.Companion.register("
        listOf(common, jvm, native).forEach { source ->
            source shouldContain "fun_"
            source shouldContain "fun__2"
        }

        compileGeneratedJvmFixture(common, jvm)
    }

    "callback names allocate away from every external identifier rendered by callback emitters" {
        val reservedIdentifiers = generatedCallbackReservedIdentifiers()
        val generated = generateKmp(
            reservedIdentifiers.joinToString("\n") { name ->
                "typedef void (*$name)(void);"
            },
        )
        val common = generated.getValue("commonMain")
        val jvm = generated.getValue("jvmMain")
        val native = generated.getValue("nativeMain")
        val android = generated.getValue("androidMain")

        reservedIdentifiers.forEach { rawName ->
            val allocatedName = "${rawName}_2"
            common shouldContain "fun interface $allocatedName : Callback"
            common shouldContain "canonicalId = \"typedef:$rawName\""
            jvm shouldContain "actual fun $allocatedName.Companion.register("
            native shouldContain "actual fun $allocatedName.Companion.register("
            android shouldContain "actual fun $allocatedName.Companion.register("
        }
        common shouldNotContain "fun interface Callback : Callback"

        compileGeneratedJvmFixture(common, jvm)
    }

    "callback classifiers allocate away from emitted C tag declarations" {
        val generated = generateKmp(
            """
                struct Foo { int value; };
                typedef void (*Foo)(void);
            """.trimIndent(),
        )
        val common = generated.getValue("commonMain")
        val jvm = generated.getValue("jvmMain")
        val native = generated.getValue("nativeMain")
        val android = generated.getValue("androidMain")

        common shouldContain "expect interface Foo {"
        common shouldContain "fun interface Foo_2 : Callback"
        common shouldContain "internal val Foo_2Type: CallbackType<Foo_2>"
        common shouldContain "canonicalId = \"typedef:Foo\""
        jvm shouldContain "private object Foo_2Trampoline"
        native shouldContain "private val Foo_2Trampoline = staticCFunction"
        android shouldContain "actual fun Foo_2.Companion.register("

        compileGeneratedJvmFixture(common, jvm)
    }

    "direct preflight names allocate away from emitted raw declarations on every target" {
        val config = CallbackBindingsConfig().also { bindings ->
            bindings.directFunctionBindings = listOf(
                DirectFunctionBinding().also { binding ->
                    binding.function = "function:sample_set_callback"
                    binding.callbackParameter = "callback"
                    binding.callbackType = "typedef:SampleCallback"
                },
            )
        }
        val generated = generateKmp(
            """
                typedef void (*SampleCallback)(void);
                void sample_set_callback(SampleCallback callback);
                void sample_set_callbackCallbackBindingPreflight(void);
            """.trimIndent(),
            config,
        )
        val common = generated.getValue("commonMain")
        val jvm = generated.getValue("jvmMain")
        val native = generated.getValue("nativeMain")
        val android = generated.getValue("androidMain")
        val allocatedPreflight = "sample_set_callbackCallbackBindingPreflight_2"

        common shouldContain "expect fun sample_set_callbackCallbackBindingPreflight(): Unit"
        common shouldContain "internal expect fun $allocatedPreflight(): (NativeAddress?) -> Unit"
        common shouldContain "val preparedCall = $allocatedPreflight()"
        common shouldContain "fun interface SampleCallback : Callback"
        common shouldContain "internal val SampleCallbackType: CallbackType<SampleCallback>"
        common shouldContain "canonicalId = \"typedef:SampleCallback\""
        jvm shouldContain "private object SampleCallbackTrampoline"
        jvm shouldContain "internal actual fun $allocatedPreflight(): (NativeAddress?) -> Unit"
        jvm shouldContain "findOrThrow(\"sample_set_callback\")"
        jvm shouldContain "findOrThrow(\"sample_set_callbackCallbackBindingPreflight\")"
        native shouldContain "private val SampleCallbackTrampoline = staticCFunction"
        native shouldContain "internal actual fun $allocatedPreflight(): (NativeAddress?) -> Unit"
        native shouldContain "webgpu.native.sample_set_callback("
        android shouldContain "internal actual fun $allocatedPreflight(): (NativeAddress?) -> Unit"

        compileGeneratedJvmFixture(common, jvm)
    }

    "JVM callback named OptIn preserves the external annotation classifier" {
        val generated = generateKmp("typedef void (*OptIn)(void);")
        val common = generated.getValue("commonMain")
        val jvm = generated.getValue("jvmMain")

        compileGeneratedJvmFixture(common, jvm)
        common shouldContain "fun interface OptIn_2 : Callback"
        common shouldContain "canonicalId = \"typedef:OptIn\""
        jvm shouldContain "@OptIn(CallbackRuntimeApi::class)\nprivate object OptIn_2Trampoline"
    }

    "direct binding callback named Suppress preserves the external annotation classifier" {
        val config = CallbackBindingsConfig().also { bindings ->
            bindings.directFunctionBindings = listOf(
                DirectFunctionBinding().also { binding ->
                    binding.function = "function:set_suppress_callback"
                    binding.callbackParameter = "callback"
                    binding.callbackType = "typedef:Suppress"
                },
            )
        }
        val generated = generateKmp(
            """
                typedef void (*Suppress)(void);
                void set_suppress_callback(Suppress callback);
            """.trimIndent(),
            config,
        )
        val common = generated.getValue("commonMain")
        val jvm = generated.getValue("jvmMain")

        compileGeneratedJvmFixture(common, jvm)
        common shouldContain "fun interface Suppress_2 : Callback"
        common shouldContain "canonicalId = \"typedef:Suppress\""
        jvm shouldContain """
            internal actual fun set_suppress_callbackCallbackBindingPreflight()
        """.trimIndent()
    }

    "Android callback named UnsupportedOperationException remains collision safe" {
        val generated = generateKmp("typedef void (*UnsupportedOperationException)(void);")
        val common = generated.getValue("commonMain")
        val android = generated.getValue("androidMain")

        common shouldContain "fun interface UnsupportedOperationException_2 : Callback"
        common shouldContain "canonicalId = \"typedef:UnsupportedOperationException\""
        android shouldContain "actual fun UnsupportedOperationException_2.Companion.register("
        android shouldContain "CallbackRuntime.register("
        android shouldNotContain "actual fun UnsupportedOperationException.Companion.register("
    }

    "outer opaque handle pointers in callbacks preserve raw address semantics" {
        val generated = generateKmp(
            """
                typedef struct ExternalImpl * External;
                typedef void (*COpaquePointerVar)(External const * value);
            """.trimIndent(),
        )
        val common = generated.getValue("commonMain")
        val jvm = generated.getValue("jvmMain")
        val native = generated.getValue("nativeMain")
        val android = generated.getValue("androidMain")

        common shouldContain "fun interface COpaquePointerVar_2 : Callback"
        common shouldContain "canonicalId = \"typedef:COpaquePointerVar\""
        common shouldContain "fun invoke(value: NativeAddress?)"
        jvm shouldContain
            "callback.invoke(value.takeIf { it != MemorySegment.NULL }?.let { NativeAddress(it.address()) })"
        native shouldContain "private val COpaquePointerVar_2Trampoline = staticCFunction"
        native shouldContain "callback.invoke(value?.let { NativeAddress.fromPointer(it) })"
        native shouldNotContain "reinterpret<COpaquePointerVar_2>()"
        android shouldContain "actual fun COpaquePointerVar_2.Companion.register("
        generated.values.forEach { source ->
            source shouldNotContain "value: External?"
            source shouldNotContain "value?.reinterpret<COpaquePointerVar>()?.pointed?.value"
        }
    }

    "configured direct callback helpers are transactional on every platform" {
        val config = CallbackBindingsConfig().also { bindings ->
            bindings.directFunctionBindings = listOf(
                DirectFunctionBinding().also { binding ->
                    binding.function = "function:sample_set_callback"
                    binding.callbackParameter = "callback"
                    binding.callbackType = "typedef:SampleCallback"
                    binding.routingUserdataParameter = "userdata"
                },
                DirectFunctionBinding().also { binding ->
                    binding.function = "function:sample_set_no_userdata_callback"
                    binding.callbackParameter = "callback"
                    binding.callbackType = "typedef:NoUserdataCallback"
                },
            )
        }
        val generated = generateKmp(
            """
                typedef struct SamplePayload { int value; } SamplePayload;
                typedef void (*SampleCallback)(void * userdata);
                typedef void (*NoUserdataCallback)(unsigned int value);
                void sample_set_callback(
                    SampleCallback callback,
                    void * userdata,
                    long long payload
                );
                void sample_set_no_userdata_callback(void* limit, NoUserdataCallback callback);
            """.trimIndent(),
            config,
        )
        val common = generated.getValue("commonMain")
        val jvm = generated.getValue("jvmMain")
        val native = generated.getValue("nativeMain")
        val android = generated.getValue("androidMain")

        common shouldContain """
            internal expect fun sample_set_callbackCallbackBindingPreflight(
                payload: Long,
            ): (NativeAddress?, NativeAddress?) -> Unit
        """.trimIndent()
        common shouldContain """
            internal expect fun sample_set_no_userdata_callbackCallbackBindingPreflight(
                limit: NativeAddress?,
            ): (NativeAddress?) -> Unit
        """.trimIndent()
        common shouldContain """
            fun sample_set_callback(
                payload: Long,
                policy: CallbackPolicy,
                onError: CallbackExceptionHandler = CallbackExceptionHandler.Default,
                callback: SampleCallback,
            ): CallbackRegistration<SampleCallback> {
        """.trimIndent()
        val safeSetter = common
            .substringAfter("fun sample_set_callback(\n    payload: Long,\n    policy: CallbackPolicy,")
            .substringBefore("\n}\n")
        safeSetter shouldContain "val preparedCall = sample_set_callbackCallbackBindingPreflight(payload)"
        safeSetter shouldContain "val prepared = SampleCallback.prepare("
        safeSetter shouldContain "return CallbackRuntime.activateForNativeCall(prepared) { registration ->"
        safeSetter shouldContain "preparedCall(registration.callback, registration.userdata)"
        safeSetter shouldNotContain "sample_set_callback(payload, registration.callback, registration.userdata)"
        safeSetter shouldNotContain "userdata: NativeAddress?"
        safeSetter shouldNotContain "val validatedPayload = payload"
        (safeSetter.indexOf("val preparedCall = sample_set_callbackCallbackBindingPreflight(payload)") <
            safeSetter.indexOf("val prepared = SampleCallback.prepare(")) shouldBe true
        (safeSetter.indexOf("activateForNativeCall") <
            safeSetter.indexOf("preparedCall(registration.callback, registration.userdata)")) shouldBe true

        common shouldContain """
            @UnsafeCallbackRearmApi
            fun rearmAfterNativeQuiescence(
        """.trimIndent()
        val rearmSetter = common
            .substringAfter("fun rearmAfterNativeQuiescence(\n    limit: NativeAddress?,\n    policy: CallbackPolicy,")
            .substringBefore("\n}\n")
        rearmSetter shouldContain
            "val preparedCall = sample_set_no_userdata_callbackCallbackBindingPreflight(limit)"
        rearmSetter shouldContain "preparedCall(registration.callback)"
        rearmSetter shouldNotContain "sample_set_no_userdata_callback(limit, registration.callback)"
        rearmSetter shouldNotContain "val validatedLimit = limit"
        (rearmSetter.indexOf("val preparedCall = sample_set_no_userdata_callbackCallbackBindingPreflight(limit)") <
            rearmSetter.indexOf("val registration = NoUserdataCallback.rearmAfterNativeQuiescence(")) shouldBe true
        common shouldNotContain "rearmAfterNativeQuiescence: Boolean"
        common shouldNotContain "allowRearm"

        jvm shouldContain """
            internal actual fun sample_set_callbackCallbackBindingPreflight(
                payload: Long,
            ): (NativeAddress?, NativeAddress?) -> Unit {
                return { callback, userdata ->
                    JvmDowncallEngine.callV3PPL(sample_set_callback_ADDR, callback?.rawValue ?: 0L, userdata?.rawValue ?: 0L, payload)
                }
            }
        """.trimIndent()
        (jvm.indexOf("internal actual fun sample_set_callbackCallbackBindingPreflight(") <
            jvm.indexOf("return { callback, userdata ->")) shouldBe true
        native shouldContain """
            internal actual fun sample_set_callbackCallbackBindingPreflight(
                payload: Long,
            ): (NativeAddress?, NativeAddress?) -> Unit {
                val preparedPayload = payload
        """.trimIndent()
        native shouldContain "return { callback, userdata ->"
        native shouldContain "webgpu.native.sample_set_callback("
        native shouldContain "preparedPayload,"
        android shouldContain """
            internal actual fun sample_set_callbackCallbackBindingPreflight(
                payload: Long,
            ): (NativeAddress?, NativeAddress?) -> Unit {
        """.trimIndent()
        android shouldContain "return { callback, userdata ->"
        android shouldContain "NativeEngine.callV3PPL(sample_set_callback_ADDR, callback.toAddress(), userdata.toAddress(), payload)"
        android shouldNotContain "LibraryInstance"
        android shouldNotContain "Android/JNA safe callback bindings are not supported"
        android shouldNotContain "val prepared = SampleCallback.prepare("
        android shouldNotContain "CallbackRuntime.activateForNativeCall"
    }

    "Android direct callback preflight invokes the raw JNA function" {
        val config = CallbackBindingsConfig().also { bindings ->
            bindings.directFunctionBindings = listOf(
                DirectFunctionBinding().also { binding ->
                    binding.function = "function:sample_request"
                    binding.callbackParameter = "callback"
                    binding.callbackType = "typedef:SampleCallback"
                    binding.routingUserdataParameter = "userdata"
                },
            )
        }
        val android = generateKmp(
            """
                typedef void (*SampleCallback)(unsigned int value, void * userdata);
                void sample_request(SampleCallback callback, void * userdata, long long input);
            """.trimIndent(),
            config,
        ).getValue("androidMain")

        android shouldContain "actual fun sample_requestCallbackBindingPreflight("
        android shouldContain "NativeEngine.callV3PPL(sample_request_ADDR, callback.toAddress(), userdata.toAddress(), input)"
        android shouldContain "return { callback, userdata ->"
        android shouldNotContain "LibraryInstance"
        android shouldNotContain "Android/JNA safe callback bindings are not supported"
    }

    "configured callback-info factory enforces the mode allowlist before allocation" {
        val config = CallbackBindingsConfig().also { bindings ->
            bindings.callbackInfoBindings = listOf(
                CallbackInfoBinding().also { binding ->
                    binding.struct = "struct:WGPUQueueWorkDoneCallbackInfo"
                    binding.owner = CallbackInfoOwner().also { owner ->
                        owner.function = "function:wgpuQueueOnSubmittedWorkDone"
                        owner.parameterPath = "callbackInfo"
                        owner.lifetime = CallbackInfoLifetime.CONSUMED_DURING_CALL
                    }
                    binding.callbackField = "callback"
                    binding.callbackType = "typedef:WGPUQueueWorkDoneCallback"
                    binding.routingUserdataField = "userdata2"
                    binding.applicationUserdataFields = listOf("userdata1")
                    binding.mode = CallbackInfoMode().also { mode ->
                        mode.field = "mode"
                        mode.type = "typedef:WGPUCallbackMode"
                        mode.allowedConstants = listOf(
                            "constant:WGPUCallbackMode_WaitAnyOnly",
                            "constant:WGPUCallbackMode_AllowProcessEvents",
                            "constant:WGPUCallbackMode_AllowSpontaneous",
                        )
                    }
                },
            )
        }
        val common = generateKmp(
            """
                typedef unsigned int WGPUCallbackMode;
                const WGPUCallbackMode WGPUCallbackMode_Undefined = 0;
                const WGPUCallbackMode WGPUCallbackMode_WaitAnyOnly = 1;
                const WGPUCallbackMode WGPUCallbackMode_AllowProcessEvents = 2;
                const WGPUCallbackMode WGPUCallbackMode_AllowSpontaneous = 3;
                const WGPUCallbackMode WGPUCallbackMode_Force32 = 0x7fffffff;

                typedef void (*WGPUQueueWorkDoneCallback)(
                    unsigned int status,
                    void * userdata1,
                    void * userdata2
                );
                typedef struct WGPUQueueWorkDoneCallbackInfo {
                    WGPUCallbackMode mode;
                    WGPUQueueWorkDoneCallback callback;
                    void * userdata1;
                    void * userdata2;
                } WGPUQueueWorkDoneCallbackInfo;
                void wgpuQueueOnSubmittedWorkDone(WGPUQueueWorkDoneCallbackInfo const * callbackInfo);
            """.trimIndent(),
            config,
        ).getValue("commonMain")

        common shouldContain """
            fun WGPUQueueWorkDoneCallbackInfo.Companion.allocate(
                allocator: MemoryAllocator,
                mode: WGPUCallbackMode,
                registration: CallbackRegistration<WGPUQueueWorkDoneCallback>,
                userdata1: NativeAddress? = null,
            ): WGPUQueueWorkDoneCallbackInfo
        """.trimIndent()
        val factory = common
            .substringAfter("fun WGPUQueueWorkDoneCallbackInfo.Companion.allocate(\n")
            .substringBefore("\n}\n")
        factory shouldContain "require("
        factory shouldContain "mode == WGPUCallbackMode_WaitAnyOnly ||"
        factory shouldContain "mode == WGPUCallbackMode_AllowProcessEvents ||"
        factory shouldContain "mode == WGPUCallbackMode_AllowSpontaneous,"
        factory shouldNotContain "WGPUCallbackMode_Undefined"
        factory shouldNotContain "WGPUCallbackMode_Force32"
        factory shouldContain "val info = allocate(allocator)"
        factory shouldContain "info.callback = registration.callback"
        factory shouldContain "info.userdata2 = registration.userdata"
        factory shouldContain "info.userdata1 = userdata1"
        (factory.indexOf("require(") < factory.indexOf("val info = allocate(allocator)")) shouldBe true
        common shouldContain "fun allocate(allocator: MemoryAllocator): WGPUQueueWorkDoneCallbackInfo"
    }

    "common KMP output emits typed callback registrations for generic typedefs" {
        val common = generateKmp(genericCallbacks).getValue("commonMain")

        common shouldContain """
            fun interface SampleCallback : Callback {
                fun invoke(value: UInt, userdata1: NativeAddress?)
                companion object
            }
        """.trimIndent()
        common shouldContain """
            expect fun SampleCallback.Companion.register(
                policy: CallbackPolicy,
                onError: CallbackExceptionHandler = CallbackExceptionHandler.Default,
                callback: SampleCallback,
            ): CallbackRegistration<SampleCallback>
        """.trimIndent()
        common shouldNotContain "policy: CallbackPolicy ="
        common shouldNotContain "fun invoke(value: UInt, userdata1: NativeAddress?, userdata2: NativeAddress?)"
        common shouldContain """
            @CallbackRuntimeApi
            internal val SampleCallbackType: CallbackType<SampleCallback> = CallbackType(
                canonicalId = "typedef:SampleCallback",
                hasRoutingUserdata = true,
            )
        """.trimIndent()
        common shouldContain """
            @CallbackRuntimeApi
            internal expect fun SampleCallback.Companion.prepare(
                policy: CallbackPolicy,
                onError: CallbackExceptionHandler = CallbackExceptionHandler.Default,
                callback: SampleCallback,
            ): PreparedCallbackRegistration<SampleCallback>
        """.trimIndent()

        common shouldContain "var callback: NativeAddress?"
        common shouldContain "expect fun sample_set_callback(callback: NativeAddress?): Unit"
        common shouldNotContain "expect class SampleCallback"
        common shouldNotContain "fun allocate(callback:"
        common shouldNotContain "override fun close()"
    }

    "queue callback keeps application userdata and reserves final routing userdata" {
        val generated = generateKmp(
            """
                typedef enum WGPUQueueWorkDoneStatus {
                    WGPUQueueWorkDoneStatus_Success = 0
                } WGPUQueueWorkDoneStatus;
                typedef struct WGPUStringView {
                    char const * data;
                    unsigned long long length;
                } WGPUStringView;
                typedef void (*WGPUQueueWorkDoneCallback)(
                    WGPUQueueWorkDoneStatus status,
                    WGPUStringView message,
                    void * userdata1,
                    void * userdata2
                );
            """.trimIndent(),
        )
        val common = generated.getValue("commonMain")
        val android = generated.getValue("androidMain")

        common shouldContain """
            fun interface WGPUQueueWorkDoneCallback : Callback {
                fun invoke(
                    status: WGPUQueueWorkDoneStatus,
                    message: WGPUStringView,
                    userdata1: NativeAddress?,
                )

                companion object
            }
        """.trimIndent()
        common shouldNotContain "userdata2: NativeAddress?"
        android shouldContain "private object WGPUQueueWorkDoneCallbackTrampoline"
        android shouldContain "dispatchJvmSignature = \"(JIJJ)V\""
        android.shouldContainAny(
            "dispatchAbiSignature = \"v(i32,struct(ptr,u64),ptr,ptr)\"",
            "dispatchAbiSignature = \"v(u32,struct(ptr,u64),ptr,ptr)\"",
        )
        android shouldContain "message: Long"
        android shouldContain "WGPUStringView.ByValue(NativeAddress(message))"
        android shouldNotContain "com.sun.jna"
    }

    "callbacks without userdata expose explicit unsafe re-arming" {
        val common = generateKmp(genericCallbacks).getValue("commonMain")

        common shouldContain """
            fun interface NoUserdataCallback : Callback {
                fun invoke(value: UInt)
                companion object
            }
        """.trimIndent()
        common shouldContain "expect fun NoUserdataCallback.Companion.register("
        common shouldContain """
            @UnsafeCallbackRearmApi
            expect fun NoUserdataCallback.Companion.rearmAfterNativeQuiescence(
        """.trimIndent()
        common shouldNotContain "SampleCallback.Companion.rearmAfterNativeQuiescence"
    }

    "JVM callbacks use one permanent static trampoline per callback type" {
        val jvm = generateKmp(genericCallbacks).getValue("jvmMain")

        listOf("SampleCallback", "NoUserdataCallback").forEach { callbackType ->
            jvm shouldContain "@OptIn(CallbackRuntimeApi::class)\nprivate object ${callbackType}Trampoline"
            val trampoline = jvm
                .substringAfter("private object ${callbackType}Trampoline")
                .substringBefore("\n}\n")
            trampoline shouldContain "JvmUpcallEngine.allocateTrampoline("
            trampoline shouldContain "dispatcherClass = ${callbackType}Trampoline::class.java"
            trampoline shouldContain "dispatchMethod = \"dispatch\""
            trampoline shouldContain "CallbackRuntime.dispatchSafely("
            trampoline shouldContain "type = ${callbackType}Type,"
            trampoline shouldContain "catch (failure: Throwable)"
            trampoline shouldContain "CallbackRuntime.reportUnroutedFailure(failure)"
            trampoline shouldNotContain "MethodHandles.lookup().findStatic("
            trampoline shouldNotContain "upcallStub("
        }
        jvm shouldContain "dispatchSig = \"(IJJ)V\""
        jvm shouldContain "dispatchSig = \"(I)V\""
        jvm shouldContain "userdata = userdata2.takeIf { it != 0L }?.let(::NativeAddress),"
        jvm shouldContain "type = NoUserdataCallbackType,\n                userdata = null,"
        jvm shouldNotContain "Arena.ofShared()"
        jvm shouldNotContain ".bindTo("
    }

    "Native callbacks use top-level static trampolines and runtime routing" {
        val native = generateKmp(genericCallbacks).getValue("nativeMain")

        native shouldContain "private val SampleCallbackTrampoline = staticCFunction<UInt, COpaquePointer?, COpaquePointer?, Unit>"
        native shouldContain "private val NoUserdataCallbackTrampoline = staticCFunction<UInt, Unit>"
        listOf("SampleCallback", "NoUserdataCallback").forEach { callbackType ->
            native shouldContain
                "@OptIn(CallbackRuntimeApi::class)\nprivate val ${callbackType}Trampoline = staticCFunction"
            val trampoline = native
                .substringAfter("private val ${callbackType}Trampoline = staticCFunction")
                .substringBefore("\n}\n")
            trampoline shouldContain "CallbackRuntime.dispatchSafely("
            trampoline shouldContain "type = ${callbackType}Type,"
            trampoline shouldContain "catch (failure: Throwable)"
            trampoline shouldContain "CallbackRuntime.reportUnroutedFailure(failure)"
        }
        native shouldContain "userdata = userdata2?.let { NativeAddress.fromPointer(it) },"
        native shouldContain "type = NoUserdataCallbackType,\n            userdata = null,"
        native.split("staticCFunction<").size shouldBe 3
        native shouldNotContain "private var SampleCallback_callback"
        native shouldNotContain "private var NoUserdataCallback_callback"
        native shouldNotContain "mutableMapOf<NativeAddress"
        native shouldNotContain "Map<NativeAddress"
    }

    "large enums use one normalized raw ABI carrier on JVM and Native" {
        val generated = generateKmp(abiCallbacks)
        val common = generated.getValue("commonMain")
        val jvm = generated.getValue("jvmMain")
        val native = generated.getValue("nativeMain")

        common shouldContain "typealias LargeStatus = ULong"
        common shouldContain "const val LargeStatus_High : LargeStatus = 4294967296uL"
        jvm shouldContain
            "private val descriptor: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, JvmDowncallEngine.structLayout(\"SamplePayload\"), ValueLayout.ADDRESS, ValueLayout.ADDRESS)"
        jvm shouldContain """
            private fun invoke(
                status: Long,
                options: Long,
                payload: MemorySegment,
                device: MemorySegment,
                userdata: MemorySegment,
            )
        """.trimIndent().prependIndent("    ").trimStart()
        jvm shouldContain "status.toULong() as LargeStatus,"
        jvm shouldContain "LargeOptions(options),"

        native shouldContain
            "staticCFunction<ULong, ULong, CValue<webgpu.native.SamplePayload>, COpaquePointer?, COpaquePointer?, Unit> { status, options, payload, device, userdata ->"
        native shouldContain "status.toULong() as LargeStatus,"
        native shouldContain "LargeOptions(options.toLong()),"
    }

    "signed long callback scalars fail with a target-independent diagnostic" {
        generateKmpFailure("typedef void (*LongCallback)(long value);") shouldContain
            "Unsupported multiplatform callback C ABI scalar 'long': " +
            "target-dependent width (LP64 vs LLP64); use a fixed-width C integer type"
    }

    "unsigned long callback scalars fail with the same target-independent diagnostic" {
        generateKmpFailure("typedef void (*UnsignedLongCallback)(unsigned long value);") shouldContain
            "Unsupported multiplatform callback C ABI scalar 'long': " +
            "target-dependent width (LP64 vs LLP64); use a fixed-width C integer type"
    }

    "long double callback scalars fail with a target-independent diagnostic" {
        generateKmpFailure("typedef void (*LongDoubleCallback)(long double value);") shouldContain
            "Unsupported multiplatform callback C ABI scalar 'long double': " +
            "target-dependent size and format; use double or an explicit fixed-width representation"
    }

    "fixed-width callback scalars retain stable JVM and Native carriers" {
        val generated = generateKmp(
            """
                typedef void (*StableCallback)(long long signed_value,
                                               unsigned long long unsigned_value,
                                               double floating_value);
            """.trimIndent(),
        )

        generated.getValue("jvmMain") shouldContain "dispatchSig = \"(JJD)V\""
        generated.getValue("nativeMain") shouldContain
            "staticCFunction<Long, ULong, Double, Unit>"
    }

    "unsigned narrow options zero-extend into the application Long" {
        val generated = generateKmp(abiCallbacks)

        generated.getValue("jvmMain") shouldContain "dispatchSig = \"(IJ)V\""
        generated.getValue("jvmMain") shouldContain
            "callback.invoke(NarrowOptions(options.toUInt().toLong()))"
        generated.getValue("nativeMain") shouldContain
            "staticCFunction<UInt, COpaquePointer?, Unit> { options, userdata ->"
        generated.getValue("nativeMain") shouldContain
            "callback.invoke(NarrowOptions(options.toLong()))"
    }

    "struct-by-value callback parameters keep raw carriers until post-claim conversion" {
        val generated = generateKmp(abiCallbacks)

        generated.getValue("jvmMain") shouldContain "SamplePayload(NativeAddress(payload.address())),"
        generated.getValue("nativeMain") shouldContain "SamplePayload.ByValue(payload),"
    }

    "pointer-to-opaque-handle callback parameters expose the raw buffer address" {
        val generated = generateKmp(abiCallbacks)

        generated.getValue("commonMain") shouldContain "device: NativeAddress?,"
        generated.getValue("jvmMain") shouldContain
            "device.takeIf { it != MemorySegment.NULL }?.let { NativeAddress(it.address()) },"
        generated.getValue("nativeMain") shouldContain
            "device?.let { NativeAddress.fromPointer(it) },"
        generated.getValue("androidMain") shouldContain
            "actual fun AbiCallback.Companion.register("
        generated.values.forEach { source ->
            source shouldNotContain "device: WGPUDevice?"
            source shouldNotContain "device?.reinterpret<COpaquePointerVar>()?.pointed?.value"
        }
    }

    "platform trampolines preserve the analyzed routing userdata position" {
        val generated = generateKmp(
            """
                typedef void (*InterleavedCallback)(
                    void * userdata9,
                    unsigned int value,
                    void * userdata1
                );
            """.trimIndent(),
        )

        generated.getValue("jvmMain") shouldContain
            "fun dispatch(\n        userdata9: Long,\n        value: Int,\n        userdata1: Long,\n    )"
        generated.getValue("nativeMain") shouldContain
            "staticCFunction<COpaquePointer?, UInt, COpaquePointer?, Unit> { userdata9, value, userdata1 ->"
    }

    "zero-argument Native callbacks emit a valid static lambda" {
        val native = generateKmp("typedef void (*EmptyCallback)(void);").getValue("nativeMain")

        native shouldContain "private val EmptyCallbackTrampoline = staticCFunction<Unit> {"
        native shouldNotContain "staticCFunction<Unit> {  ->"
    }

    "Android callbacks use the dynamic kffi ABI engine" {
        val android = generateKmp(genericCallbacks).getValue("androidMain")

        listOf("SampleCallback", "NoUserdataCallback").forEach { callbackType ->
            android shouldContain "actual fun ${callbackType}.Companion.register("
            android shouldContain "internal actual fun ${callbackType}.Companion.prepare("
            android shouldContain "private object ${callbackType}Trampoline"
            android shouldContain "UpcallEngine.allocateTrampoline("
        }
        android shouldContain "actual fun NoUserdataCallback.Companion.rearmAfterNativeQuiescence("
        android shouldContain "dispatchJvmSignature = \"(JIJ)V\""
        android shouldContain "dispatchAbiSignature = \"v(u32,ptr,ptr)\""
        android shouldContain "dispatchJvmSignature = \"(I)V\""
        android shouldContain "dispatchAbiSignature = \"v(u32)\""
        android shouldContain "CallbackRuntime.register("
        android shouldContain "CallbackRuntime.prepare("
        android shouldContain "CallbackRuntime.rearmAfterNativeQuiescence("
        android shouldContain "CallbackRuntime.dispatchSafely("
        android shouldContain "CallbackRuntime.reportUnroutedFailure(failure)"
        android shouldNotContain "com.sun.jna"
    }

    "Android routed callbacks in the fixed (u32, routing userdata) shape allocate upcall trampolines" {
        val config = CallbackBindingsConfig().also { bindings ->
            bindings.directFunctionBindings = listOf(
                DirectFunctionBinding().also { binding ->
                    binding.function = "function:sample_request"
                    binding.callbackParameter = "callback"
                    binding.callbackType = "typedef:SampleCallback"
                    binding.routingUserdataParameter = "userdata"
                },
            )
        }
        val android = generateKmp(
            """
                typedef void (*SampleCallback)(unsigned int value, void * userdata);
                void sample_request(SampleCallback callback, void * userdata, long long input);
            """.trimIndent(),
            config,
        ).getValue("androidMain")

        android shouldContain "import org.graphiks.kffi.engine.UpcallEngine"
        android shouldContain "import kotlin.jvm.JvmStatic"
        android shouldContain "private object SampleCallbackTrampoline {"
        android shouldContain "val address: NativeAddress by lazy {"
        android shouldContain "NativeAddress(UpcallEngine.allocateTrampoline("
        android shouldContain "dispatcherClass = SampleCallbackTrampoline::class.java,"
        android shouldContain "dispatchMethod = \"dispatch\","
        android shouldContain "dispatchJvmSignature = \"(JI)V\","
        android shouldContain "dispatchAbiSignature = \"v(u32,ptr)\","
        android shouldContain "@JvmStatic"
        android shouldContain "fun dispatch(token: Long, value: Int) {"
        android shouldContain "CallbackRuntime.dispatchSafely("
        android shouldContain "type = SampleCallbackType,"
        android shouldContain "userdata = NativeAddress(token),"
        android shouldContain "callback.invoke(value.toUInt())"
        android shouldNotContain "com.sun.jna.CallbackReference"
        android shouldNotContain "com.sun.jna.Callback"
        android shouldNotContain "SampleCallbackJna"
        android shouldNotContain "TODO(M5.5)"
    }

    "ordinary generic functions remain generated in every KMP target" {
        val generated = generateKmp(genericCallbacks)

        generated.forEach { (_, source) ->
            source shouldContain "sample_get_value"
            source shouldContain "sample_set_callback(callback: NativeAddress?)"
        }
        generated.filterKeys { it != "commonMain" }.values.forEach { source ->
            source shouldNotContain "actual class SampleCallback"
        }
    }

    "Native raw callback arguments are reinterpreted as C function pointers" {
        val native = generateKmp(genericCallbacks).getValue("nativeMain")

        native shouldContain """
            actual fun sample_set_callback(callback: NativeAddress?): Unit {
                webgpu.native.sample_set_callback(callback?.pointer?.takeIf { callback.rawValue != 0L }?.reinterpret())
                return
            }
        """.trimIndent()
    }

    "non-void callbacks fail generation" {
        val input = Files.createTempFile("kextract-invalid-callback", ".h")
        val output = Files.createTempDirectory("kextract-invalid-callback-out")
        try {
            input.toFile().writeText("typedef int (*InvalidCallback)(unsigned int value);")

            KextractTool(Logger()).runGeneration(
                listOf(input.toString()),
                Options(outputDir = output.toString(), multiplatform = true),
            ) shouldBe KextractTool.FAILURE
        } finally {
            input.toFile().delete()
            output.toFile().deleteRecursively()
        }
    }
})
