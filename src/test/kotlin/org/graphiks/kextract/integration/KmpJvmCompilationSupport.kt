package org.graphiks.kextract.integration

import io.kotest.matchers.shouldBe
import org.graphiks.kextract.callbacks.CallbackBindingsConfig
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import java.net.URLClassLoader
import java.nio.file.Files
import java.nio.file.Path

internal data class GeneratedKmpSources(
    val common: String,
    val jvm: String,
    val native: String,
    val android: String,
)

internal fun generateKmpSources(
    header: String,
    packageName: String = "sample.bindings",
    callbackBindings: CallbackBindingsConfig? = null,
    libraries: List<Options.Library> = emptyList(),
    jvmNativeLibraries: List<Options.Library> = emptyList(),
    writeJvmResources: (Path) -> Unit = {},
): GeneratedKmpSources {
    val workspace = Files.createTempDirectory("kextract-kmp-source")
    val input = workspace.resolve("fixture.h")
    return try {
        generateKmpSourcesFromHeaderPath(
            header,
            input,
            packageName,
            callbackBindings,
            libraries,
            jvmNativeLibraries,
            writeJvmResources,
        )
    } finally {
        workspace.toFile().deleteRecursively()
    }
}

internal fun generateKmpSourcesFromHeaderPath(
    header: String,
    input: Path,
    packageName: String = "sample.bindings",
    callbackBindings: CallbackBindingsConfig? = null,
    libraries: List<Options.Library> = emptyList(),
    jvmNativeLibraries: List<Options.Library> = emptyList(),
    writeJvmResources: (Path) -> Unit = {},
): GeneratedKmpSources {
    val output = Files.createTempDirectory("kextract-kmp-source-out")
    return try {
        input.toFile().writeText(header)
        writeJvmResources(output.resolve("jvmMain/resources"))
        KextractTool(Logger.DEFAULT).runGeneration(
            listOf(input.toString()),
            Options(
                targetPackage = packageName,
                outputDir = output.toString(),
                multiplatform = true,
                callbackBindings = callbackBindings,
                libraries = libraries,
                jvmNativeLibraries = jvmNativeLibraries,
            ),
        ) shouldBe KextractTool.SUCCESS

        fun readSourceSet(name: String): String = Files.walk(output.resolve(name)).use { paths ->
            paths.filter { it.fileName.toString().endsWith(".kt") }
                .sorted(Comparator.comparing { it.toString() })
                .map { it.toFile().readText() }
                .toList()
                .joinToString("\n")
        }

        GeneratedKmpSources(
            common = readSourceSet("commonMain"),
            jvm = readSourceSet("jvmMain"),
            native = readSourceSet("nativeMain"),
            android = readSourceSet("androidMain"),
        )
    } finally {
        output.toFile().deleteRecursively()
    }
}

internal fun compileAndInvokeGeneratedKmpJvm(
    generated: GeneratedKmpSources,
    probePackage: String,
    probeSource: String,
    facadeClassName: String,
    methodName: String,
): Any? {
    val workspace = Files.createTempDirectory("kextract-kmp-jvm-classes")
    return try {
        val common = workspace.resolve("sampleCommon.kt")
        val jvm = workspace.resolve("sampleJvm.kt")
        val kffiCommon = workspace.resolve("kffiCommon.kt")
        val kffiJvm = workspace.resolve("kffiJvm.kt")
        val kffiEngine = workspace.resolve("kffiEngine.kt")
        val probe = workspace.resolve("probe.kt")
        val output = Files.createDirectories(workspace.resolve("classes"))

        common.toFile().writeText(generated.common)
        jvm.toFile().writeText(generated.jvm)
        kffiCommon.toFile().writeText(KFFI_COMMON_STUB)
        kffiJvm.toFile().writeText(KFFI_JVM_STUB)
        kffiEngine.toFile().writeText(KFFI_JVM_ENGINE_STUB)
        probe.toFile().writeText(probeSource)

        K2JVMCompiler().exec(
            System.err,
            "-no-stdlib",
            "-no-reflect",
            "-Xmulti-platform",
            "-Xcommon-sources=$common,$kffiCommon",
            "-classpath", System.getProperty("java.class.path"),
            "-d", output.toString(),
            common.toString(), jvm.toString(),
            kffiCommon.toString(), kffiJvm.toString(), kffiEngine.toString(), probe.toString(),
        ) shouldBe ExitCode.OK

        URLClassLoader(
            arrayOf(output.toUri().toURL()),
            GeneratedKmpSources::class.java.classLoader,
        ).use { classLoader ->
            classLoader.loadClass("$probePackage.$facadeClassName")
                .getMethod(methodName)
                .invoke(null)
        }
    } finally {
        workspace.toFile().deleteRecursively()
    }
}

internal val KFFI_COMMON_STUB =
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
        var prepareCount: Int = 0
        var symbolResolutionCount: Int = 0

        fun <C : Callback> register(
            type: CallbackType<C>,
            trampoline: NativeAddress,
            policy: CallbackPolicy,
            onError: CallbackExceptionHandler,
            callback: C,
        ): CallbackRegistration<C> = object : CallbackRegistration<C> {
            override val callback: NativeAddress = trampoline
            override val userdata: NativeAddress? = null
            override fun close() = Unit
        }

        fun <C : Callback> prepare(
            type: CallbackType<C>,
            trampoline: NativeAddress,
            policy: CallbackPolicy,
            onError: CallbackExceptionHandler,
            callback: C,
        ): PreparedCallbackRegistration<C> {
            prepareCount += 1
            return PreparedCallbackRegistration()
        }

        fun <C : Callback> rearmAfterNativeQuiescence(
            type: CallbackType<C>,
            trampoline: NativeAddress,
            policy: CallbackPolicy,
            onError: CallbackExceptionHandler,
            callback: C,
        ): CallbackRegistration<C> = error("rearm reached")

        fun <C : Callback> activateForNativeCall(
            prepared: PreparedCallbackRegistration<C>,
            call: (CallbackRegistration<C>) -> Unit,
        ): CallbackRegistration<C> = error("activation reached")

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
    interface CStructure {
        val handler: NativeAddress
    }
    """.trimIndent()

internal val KFFI_JVM_STUB =
    """
    package org.graphiks.kffi

    import java.lang.foreign.Arena
    import java.lang.foreign.MemorySegment
    import java.lang.foreign.ValueLayout

    object TestNativeSymbols {
        private val symbols = mutableMapOf<String, MemorySegment>()

        fun register(name: String, address: MemorySegment) {
            symbols[name] = address
        }

        fun find(name: String): MemorySegment =
            symbols[name] ?: error("Missing test symbol: ${'$'}name")
    }

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
    fun findOrThrow(name: String): MemorySegment {
        CallbackRuntime.symbolResolutionCount += 1
        return TestNativeSymbols.find(name)
    }
    """.trimIndent()

internal val KFFI_JVM_ENGINE_STUB =
    """
    package org.graphiks.kffi.engine

    import org.graphiks.kffi.NativeAddress
    import java.lang.foreign.Arena
    import java.lang.foreign.FunctionDescriptor
    import java.lang.foreign.Linker
    import java.lang.foreign.MemorySegment
    import java.lang.foreign.ValueLayout
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

    object JvmDowncallEngine
    """.trimIndent()
