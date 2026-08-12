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
        val probe = workspace.resolve("probe.kt")
        val output = Files.createDirectories(workspace.resolve("classes"))

        common.toFile().writeText(generated.common)
        jvm.toFile().writeText(generated.jvm)
        kffiCommon.toFile().writeText(KFFI_COMMON_STUB)
        kffiJvm.toFile().writeText(KFFI_JVM_STUB)
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
            kffiCommon.toString(), kffiJvm.toString(), probe.toString(),
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

    expect class NativeAddress
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
    expect class MemoryAllocator()
    interface CStructure {
        val handler: NativeAddress
    }
    """.trimIndent()

internal val KFFI_JVM_STUB =
    """
    package org.graphiks.kffi

    import java.lang.foreign.Arena
    import java.lang.foreign.MemorySegment

    object TestNativeSymbols {
        private val symbols = mutableMapOf<String, MemorySegment>()

        fun register(name: String, address: MemorySegment) {
            symbols[name] = address
        }

        fun find(name: String): MemorySegment =
            symbols[name] ?: error("Missing test symbol: ${'$'}name")
    }

    class JvmNativeAddress(val handler: MemorySegment)
    actual typealias NativeAddress = JvmNativeAddress
    @JvmInline
    actual value class CString actual constructor(actual val handler: NativeAddress)
    actual class MemoryAllocator actual constructor() {
        fun allocate(byteSize: Long): NativeAddress = NativeAddress(Arena.global().allocate(byteSize))
    }
    fun findOrThrow(name: String): MemorySegment {
        CallbackRuntime.symbolResolutionCount += 1
        return TestNativeSymbols.find(name)
    }
    """.trimIndent()
