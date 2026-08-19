package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.graphiks.kextract.pipeline.Options
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.util.concurrent.TimeUnit
import kotlin.io.path.createDirectories
import kotlin.io.path.readLines
import kotlin.io.path.writeText

class KmpJvmNativeBootstrapIntegrationTest : FreeSpec({
    "generated bootstrap compiles with bundled resources and a colliding C declaration" {
        val generated = generateKmpSources(
            header = """
                void KextractNativeBootstrap(void);
                void sample_call(void);
            """.trimIndent(),
            libraries = listOf(Options.Library.parse("sample")),
            writeJvmResources = { resources ->
                val platform = resources.resolve("linux-x86-64")
                Files.createDirectories(platform)
                Files.write(platform.resolve("libsample.so"), byteArrayOf(1, 2, 3))
            },
        )

        generated.jvm shouldContain "private object KextractNativeBootstrap_2"
        compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.bindings",
            probeSource = """
                package sample.bindings
                fun bootstrapCompilationProbe(): Int = 7
            """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "bootstrapCompilationProbe",
        ) shouldBe 7
    }

    "fresh JVM retries a failed load then serves concurrent first native downcalls" {
        val workspace = Files.createTempDirectory("kextract native bootstrap ")
        try {
            val platform = currentNativeFixturePlatform()
            val nativeDirectory = workspace.resolve("native libraries").createDirectories()
            val dependency = nativeDirectory.resolve(platform.dependencyFileName)
            val main = nativeDirectory.resolve(platform.mainFileName)
            compileNativeFixture(platform, nativeDirectory, dependency, main)

            val generated = generateKmpSources(
                header = """
                    typedef void (*FixtureCallback)(int value, void * userdata);
                    int fixture_first_downcall(void);
                """.trimIndent(),
                libraries = listOf(Options.Library.parse("fixture_main")),
                jvmNativeLibraries = listOf(
                    Options.Library.parse("fixture_dependency"),
                    Options.Library.parse("fixture_main"),
                ),
                writeJvmResources = { resources ->
                    val platformResources = resources.resolve(platform.id).createDirectories()
                    Files.copy(dependency, platformResources.resolve(dependency.fileName))
                    Files.copy(main, platformResources.resolve(main.fileName))
                },
            )
            val classes = compileBootstrapProbe(generated, workspace.resolve("compiled classes"))
            val classpathResources = classes.resolve(platform.id).createDirectories()
            Files.copy(dependency, classpathResources.resolve(dependency.fileName))
            Files.copy(main, classpathResources.resolve(main.fileName))
            val decoyRoot = workspace.resolve("earlier classpath resources")
            val decoyResources = decoyRoot.resolve(platform.id).createDirectories()
            Files.write(decoyResources.resolve(dependency.fileName), byteArrayOf(9, 9, 9))
            Files.write(decoyResources.resolve(main.fileName), byteArrayOf(8, 8, 8))

            val blockedCache = workspace.resolve("cache path with spaces")
            val loadCounter = workspace.resolve("load counter.txt")
            val javaExecutable = Path.of(
                System.getProperty("java.home"),
                "bin",
                if (System.getProperty("os.name").startsWith("Windows")) "java.exe" else "java",
            )
            val process = ProcessBuilder(
                javaExecutable.toString(),
                "--enable-native-access=ALL-UNNAMED",
                "-cp",
                listOf(decoyRoot.toString(), classes.toString(), System.getProperty("java.class.path"))
                    .joinToString(File.pathSeparator),
                "sample.bindings.ProbeKt",
                blockedCache.toString(),
            )
                .redirectErrorStream(true)
                .apply { environment()["KEXTRACT_FIXTURE_LOAD_COUNTER"] = loadCounter.toString() }
                .start()
            val completed = process.waitFor(60, TimeUnit.SECONDS)
            if (!completed) process.destroyForcibly()
            val output = process.inputStream.bufferedReader().readText()

            completed shouldBe true
            check(process.exitValue() == 0) { "Fresh JVM failed with exit ${process.exitValue()}:\n$output" }
            output shouldContain "BOOTSTRAP_OK"
            loadCounter.readLines() shouldBe listOf("loaded")
            Files.walk(blockedCache).use { paths ->
                val extractedNames = paths
                    .filter(Files::isRegularFile)
                    .map { it.fileName.toString() }
                    .toList()
                extractedNames.count { it == dependency.fileName.toString() } shouldBe 1
                extractedNames.count { it == main.fileName.toString() } shouldBe 1
            }
        } finally {
            workspace.toFile().deleteRecursively()
        }
    }
})

private data class NativeFixturePlatform(
    val id: String,
    val dependencyFileName: String,
    val mainFileName: String,
)

private fun currentNativeFixturePlatform(): NativeFixturePlatform {
    val os = System.getProperty("os.name").lowercase()
    val architecture = when (System.getProperty("os.arch").lowercase()) {
        "aarch64", "arm64" -> "aarch64"
        "amd64", "x86_64", "x64" -> "x86-64"
        else -> error("Unsupported test architecture: ${System.getProperty("os.arch")}")
    }
    return when {
        os.contains("mac") || os.contains("darwin") ->
            NativeFixturePlatform("darwin-$architecture", "libfixture_dependency.dylib", "libfixture_main.dylib")
        os.contains("linux") ->
            NativeFixturePlatform("linux-$architecture", "libfixture_dependency.so", "libfixture_main.so")
        os.contains("windows") && architecture == "x86-64" ->
            NativeFixturePlatform("win32-x86-64", "fixture_dependency.dll", "fixture_main.dll")
        else -> error("Unsupported native bootstrap test platform: ${System.getProperty("os.name")}/$architecture")
    }
}

private fun compileNativeFixture(
    platform: NativeFixturePlatform,
    workingDirectory: Path,
    dependency: Path,
    main: Path,
) {
    val sources = Path.of("src/test/native-bootstrap-fixture").toAbsolutePath()
    when {
        platform.id.startsWith("darwin-") -> {
            runFixtureCompiler(
                listOf(
                    "cc", "-dynamiclib", "-fPIC", sources.resolve("dependency.c").toString(),
                    "-Wl,-install_name,@loader_path/${dependency.fileName}", "-o", dependency.toString(),
                ),
                workingDirectory,
            )
            runFixtureCompiler(
                listOf(
                    "cc", "-dynamiclib", "-fPIC", sources.resolve("main.c").toString(),
                    "-L${workingDirectory}", "-lfixture_dependency", "-Wl,-rpath,@loader_path",
                    "-o", main.toString(),
                ),
                workingDirectory,
            )
        }
        platform.id.startsWith("linux-") -> {
            runFixtureCompiler(
                listOf(
                    "cc", "-shared", "-fPIC", sources.resolve("dependency.c").toString(),
                    "-Wl,-soname,${dependency.fileName}", "-o", dependency.toString(),
                ),
                workingDirectory,
            )
            runFixtureCompiler(
                listOf(
                    "cc", "-shared", "-fPIC", sources.resolve("main.c").toString(),
                    "-L${workingDirectory}", "-lfixture_dependency", "-Wl,-rpath,\$ORIGIN",
                    "-o", main.toString(),
                ),
                workingDirectory,
            )
        }
        platform.id == "win32-x86-64" -> {
            val importLibrary = workingDirectory.resolve("fixture_dependency.lib")
            runFixtureCompiler(
                listOf(
                    "cl", "/nologo", "/LD", sources.resolve("dependency.c").toString(),
                    "/Fe:${dependency}", "/link", "/IMPLIB:${importLibrary}",
                ),
                workingDirectory,
            )
            runFixtureCompiler(
                listOf(
                    "cl", "/nologo", "/LD", sources.resolve("main.c").toString(),
                    importLibrary.toString(), "/Fe:${main}",
                ),
                workingDirectory,
            )
        }
    }
}

private fun runFixtureCompiler(command: List<String>, workingDirectory: Path) {
    val process = ProcessBuilder(command)
        .directory(workingDirectory.toFile())
        .redirectErrorStream(true)
        .start()
    val output = process.inputStream.bufferedReader().readText()
    check(process.waitFor() == 0) { "Native fixture compiler failed: ${command.joinToString(" ")}\n$output" }
}

private fun compileBootstrapProbe(generated: GeneratedKmpSources, workspace: Path): Path {
    workspace.createDirectories()
    val common = workspace.resolve("sampleCommon.kt").also { it.writeText(generated.common) }
    val jvm = workspace.resolve("sampleJvm.kt").also { it.writeText(generated.jvm) }
    val kffiCommon = workspace.resolve("kffiCommon.kt").also { it.writeText(KFFI_COMMON_STUB) }
    val kffiJvm = workspace.resolve("kffiJvm.kt").also { it.writeText(REAL_KFFI_JVM_STUB) }
    val kffiEngine = workspace.resolve("kffiEngine.kt").also { it.writeText(REAL_KFFI_JVM_ENGINE_STUB) }
    val probe = workspace.resolve("probe.kt").also { it.writeText(BOOTSTRAP_PROBE) }
    val classes = workspace.resolve("classes").createDirectories()

    K2JVMCompiler().exec(
        System.err,
        "-no-stdlib",
        "-no-reflect",
        "-Xmulti-platform",
        "-Xcommon-sources=$common,$kffiCommon",
        "-classpath", System.getProperty("java.class.path"),
        "-d", classes.toString(),
        common.toString(), jvm.toString(),
        kffiCommon.toString(), kffiJvm.toString(), kffiEngine.toString(), probe.toString(),
    ) shouldBe ExitCode.OK
    return classes
}

private val REAL_KFFI_JVM_STUB =
    """
    package org.graphiks.kffi

    import java.lang.foreign.Arena
    import java.lang.foreign.MemorySegment
    import java.lang.foreign.SymbolLookup
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
    fun findOrThrow(name: String): Long =
        SymbolLookup.loaderLookup().find(name).orElseThrow {
            UnsatisfiedLinkError("Missing native test symbol: ${'$'}name")
        }.address()
    """.trimIndent()

private val REAL_KFFI_JVM_ENGINE_STUB =
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

        fun resolveSymbol(name: String): Long = org.graphiks.kffi.findOrThrow(name)

        private fun segment(address: Long): MemorySegment = MemorySegment.ofAddress(address)

        private fun handle(fn: Long, descriptor: FunctionDescriptor): MethodHandle =
            linker.downcallHandle(segment(fn), descriptor)

        fun callV0(fn: Long) {
            handle(fn, FunctionDescriptor.ofVoid()).invokeExact()
        }

        fun callI0(fn: Long): Long =
            handle(fn, FunctionDescriptor.of(ValueLayout.JAVA_LONG)).invokeExact() as Long
    }
    """.trimIndent()

private val BOOTSTRAP_PROBE =
    """
    package sample.bindings

    import org.graphiks.kffi.CallbackPolicy
    import java.nio.file.FileAlreadyExistsException
    import java.nio.file.Files
    import java.nio.file.Path
    import java.util.Collections
    import java.util.concurrent.CountDownLatch

    fun main(args: Array<String>) {
        val cachePath = Path.of(args.single())
        Files.writeString(cachePath, "block cache creation")
        System.setProperty("kextract.native.cache.dir", cachePath.toString())

        val registration = FixtureCallback.register(
            policy = CallbackPolicy.REPEATING,
            callback = FixtureCallback { },
        )
        registration.close()

        val originalFailure = runCatching { fixture_first_downcall() }.exceptionOrNull()
        check(originalFailure is FileAlreadyExistsException) {
            "Expected original FileAlreadyExistsException, got ${'$'}originalFailure"
        }

        Files.delete(cachePath)
        val workerCount = 16
        val ready = CountDownLatch(workerCount)
        val start = CountDownLatch(1)
        val values = Collections.synchronizedList(mutableListOf<Int>())
        val failures = Collections.synchronizedList(mutableListOf<Throwable>())
        val workers = List(workerCount) {
            Thread {
                ready.countDown()
                start.await()
                runCatching { fixture_first_downcall() }
                    .onSuccess(values::add)
                    .onFailure(failures::add)
            }
        }
        workers.forEach(Thread::start)
        ready.await()
        start.countDown()
        workers.forEach(Thread::join)

        check(failures.isEmpty()) { "Concurrent bootstrap failures: ${'$'}failures" }
        check(values.size == workerCount && values.all { it == 42 }) { "Unexpected values: ${'$'}values" }
        println("BOOTSTRAP_OK")
    }
    """.trimIndent()
