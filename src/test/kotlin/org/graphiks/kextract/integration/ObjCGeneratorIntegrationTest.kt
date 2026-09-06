package org.graphiks.kextract.integration

import io.kotest.core.annotation.EnabledIf
import io.kotest.core.annotation.MacCondition
import io.kotest.core.spec.style.FreeSpec
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Position
import org.graphiks.kextract.Type
import org.graphiks.kextract.pipeline.NameMangler
import org.graphiks.kextract.kotlin.KotlinGenerator
import org.graphiks.kextract.kotlin.models.KotlinSourceFile
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import java.net.URLClassLoader
import java.nio.file.Files
import java.nio.file.Path

/**
 * Integration tests for Objective-C → Kotlin/JVM binding generation.
 *
 * Only run on macOS:
 * - libclang's ObjC cursor kinds require the ObjC runtime headers
 * - The generated ObjCRuntime.kt references libobjc.dylib
 *
 * We use custom class/protocol names (not NS-prefixed) to avoid conflicts
 * with the ObjC runtime headers that clang auto-includes in ObjC mode.
 * We also omit `-fobjc-arc` to keep the runtime surface minimal.
 */
@EnabledIf(MacCondition::class)
class ObjCGeneratorIntegrationTest : FreeSpec({

    /**
     * Parse inline ObjC source and run the full generator pipeline.
     * Returns all generated [KotlinSourceFile] objects.
     */
    fun generateAll(objcSource: String, pkg: String = "test"): List<KotlinSourceFile> {
        val tmp = Files.createTempFile("kextract_objc_test_", ".h")
        return try {
            tmp.toFile().writeText(objcSource)
            val headerName = tmp.fileName.toString()
            val parsed = KextractTool.parse(
                listOf(tmp.toString()),
                "-x", "objective-c"
            )
            val mangled = NameMangler(headerName).scan(parsed)
            KotlinGenerator().generate(mangled, headerName, pkg)
        } finally {
            Files.deleteIfExists(tmp)
        }
    }

    /** Concatenates all generated source file contents. */
    fun generate(objcSource: String, pkg: String = "test"): String =
        generateAll(objcSource, pkg).joinToString("\n") { it.contents }

    fun macosSdkPath(): String {
        val process = ProcessBuilder("xcrun", "--sdk", "macosx", "--show-sdk-path")
            .redirectErrorStream(true)
            .start()
        val output = process.inputStream.bufferedReader().use { it.readText().trim() }
        check(process.waitFor() == 0) { "xcrun failed to locate the macOS SDK: $output" }
        return output
    }

    /** Runs every production filter and reads back all Kotlin files written by the tool. */
    fun generateWithPipeline(
        objcSource: String,
        pkg: String = "test",
        clangArgs: List<String> = emptyList(),
    ): List<KotlinSourceFile> {
        val workspace = Files.createTempDirectory("kextract_objc_pipeline_test_")
        val input = workspace.resolve("fixture.h")
        val output = workspace.resolve("output")
        return try {
            Files.writeString(input, objcSource)
            KextractTool(Logger.DEFAULT).runGeneration(
                listOf(input.toString()),
                Options(
                    clangArgs = listOf("-x", "objective-c") + clangArgs,
                    targetPackage = pkg,
                    outputDir = output.toString(),
                ),
            ) shouldBe KextractTool.SUCCESS

            Files.walk(output).use { paths ->
                paths
                    .filter { Files.isRegularFile(it) && it.fileName.toString().endsWith(".kt") }
                    .sorted()
                    .map { path ->
                        KotlinSourceFile(
                            packageName = pkg,
                            className = path.fileName.toString().removeSuffix(".kt"),
                            contents = Files.readString(path),
                        )
                    }
                    .toList()
            }
        } finally {
            workspace.toFile().deleteRecursively()
        }
    }

    fun generateManual(vararg declarations: Declaration): List<KotlinSourceFile> =
        KotlinGenerator().generate(
            Declaration.toplevel(Position.NO_POSITION, *declarations),
            "manual.h",
            "test",
        )

    fun enumConstant(name: String, value: Long): Declaration.Constant =
        Declaration.constant(
            Position.NO_POSITION,
            name,
            value,
            Type.primitive(Type.Primitive.Kind.Long),
        )

    fun markSkipped(declaration: Declaration) {
        val skipClass = Class.forName("org.graphiks.kextract.DeclarationImpl\$Skip")
        val skip = skipClass.getField("INSTANCE").get(null) as Declaration.Attribute
        declaration.addAttribute(skip)
    }

    fun compileAndInvoke(
        files: List<KotlinSourceFile>,
        probeSource: String,
        methodName: String,
    ): Any? {
        val workspace = Files.createTempDirectory("kextract_objc_enum_compile_")
        return try {
            val sourcePaths = files.mapIndexed { index, source ->
                workspace.resolve("${source.className}_$index.kt").also {
                    Files.writeString(it, source.contents)
                }
            }
            val probe = workspace.resolve("EnumMacroProbe.kt")
            Files.writeString(probe, probeSource)
            val output = Files.createDirectories(workspace.resolve("classes"))
            val arguments = buildList {
                addAll(listOf(
                    "-no-stdlib",
                    "-no-reflect",
                    "-jvm-target", "25",
                    "-classpath", System.getProperty("java.class.path"),
                    "-d", output.toString(),
                ))
                addAll(sourcePaths.map(Path::toString))
                add(probe.toString())
            }
            K2JVMCompiler().exec(System.err, *arguments.toTypedArray()) shouldBe ExitCode.OK
            URLClassLoader(
                arrayOf(output.toUri().toURL()),
                ObjCGeneratorIntegrationTest::class.java.classLoader,
            ).use { loader ->
                loader.loadClass("test.EnumMacroProbeKt")
                    .getMethod(methodName)
                    .invoke(null)
            }
        } finally {
            workspace.toFile().deleteRecursively()
        }
    }

    fun compileAndInvokeLong(
        files: List<KotlinSourceFile>,
        probeSource: String,
        methodName: String,
    ): Long = compileAndInvoke(files, probeSource, methodName) as Long

    fun compileAndInvokeBoolean(
        files: List<KotlinSourceFile>,
        probeSource: String,
        methodName: String,
    ): Boolean = compileAndInvoke(files, probeSource, methodName) as Boolean

    fun compileOnly(files: List<KotlinSourceFile>, probeSource: String) {
        val workspace = Files.createTempDirectory("kextract_objc_compile_")
        try {
            val sourcePaths = files.mapIndexed { index, source ->
                workspace.resolve("${source.className}_$index.kt").also {
                    Files.writeString(it, source.contents)
                }
            }
            val probe = workspace.resolve("ConsumerProbe.kt")
            Files.writeString(probe, probeSource)
            val output = Files.createDirectories(workspace.resolve("classes"))
            val arguments = buildList {
                addAll(listOf(
                    "-no-stdlib",
                    "-no-reflect",
                    "-jvm-target", "25",
                    "-classpath", System.getProperty("java.class.path"),
                    "-d", output.toString(),
                ))
                addAll(sourcePaths.map(Path::toString))
                add(probe.toString())
            }
            K2JVMCompiler().exec(System.err, *arguments.toTypedArray()) shouldBe ExitCode.OK
        } finally {
            workspace.toFile().deleteRecursively()
        }
    }

    fun compileX86ObjCAssembly(source: String): String {
        val lookup = ProcessBuilder("xcrun", "--find", "clang")
            .redirectErrorStream(true)
            .start()
        val clang = lookup.inputStream.bufferedReader().readText().trim()
        lookup.waitFor() shouldBe 0

        val process = ProcessBuilder(
            clang,
            "-target", "x86_64-apple-macos13.0",
            "-x", "objective-c",
            "-S", "-O0", "-fno-objc-arc",
            "-o", "-", "-",
        ).redirectErrorStream(true).start()
        process.outputStream.bufferedWriter().use { it.write(source) }
        val assembly = process.inputStream.bufferedReader().readText()
        process.waitFor() shouldBe 0
        return assembly
    }

    /** Returns the ObjCRuntime.kt content if present, else "". */
    fun getRuntime(files: List<KotlinSourceFile>): String =
        files.firstOrNull { it.className == "ObjCRuntime" }?.contents ?: ""

    // ── @interface (class) ───────────────────────────────────────────────────

    "ObjC class wrapper" - {
        // Use a custom root class (no superclass) to avoid NS-prefix runtime conflicts
        val src = generate("""
            @interface KxBaseObject
            - (long)count;
            + (instancetype)newInstance;
            @end
        """.trimIndent())

        "generates open class with MemorySegment ptr" {
            src shouldContain "open class KxBaseObject"
            src shouldContain "val ptr: MemorySegment"
        }

        "no supertype expression for root class" {
            src shouldNotContain ": KxBaseObject(ptr)"
        }

        "companion object with lazy _class" {
            src shouldContain "companion object"
            src shouldContain """private val _class: MemorySegment by lazy { ObjCRuntime.getClass("KxBaseObject") }"""
        }

        "class method in companion" {
            src shouldContain "fun newInstance()"
            src shouldContain "_class"
        }

        "instance method uses ObjCRuntime.msgSend" {
            src shouldContain "fun count()"
            src shouldContain "ObjCRuntime.msgSend"
        }
    }

    "ObjC class with superclass" - {
        // Define base first so clang sees the definition, then sub
        val src = generate("""
            @interface KxBase
            @end
            @interface KxDerived : KxBase
            - (void)doWork;
            @end
        """.trimIndent())

        "derived class extends base with ptr forwarding" {
            src shouldContain "open class KxDerived"
            src shouldContain ": KxBase(ptr)"
        }
    }

    "Foundation SDK predefined sugar" - {
        "generates declarations that use NSUInteger" {
            val sdk = macosSdkPath()
            val src = generateWithPipeline(
                """
                #import <Foundation/NSObjCRuntime.h>
                NSUInteger kxUsePredefinedSugar(NSUInteger value);
                """.trimIndent(),
                clangArgs = listOf(
                    "-F${sdk}/System/Library/Frameworks",
                    "-isysroot",
                    sdk,
                ),
            ).joinToString("\n") { it.contents }

            src shouldContain "fun kxUsePredefinedSugar"
        }
    }

    "ObjC member availability" - {
        val src = generateWithPipeline(
            """
            @interface KxAvailabilityHost
            - (void)availableMethod;
            - (void)unavailableMethod __attribute__((availability(macos, unavailable)));
            @property (readonly) long availableProperty;
            @property (readonly) long unavailableProperty __attribute__((availability(macos, unavailable)));
            @end
            """.trimIndent(),
            clangArgs = listOf("-target", "arm64-apple-macos15.0"),
        ).joinToString("\n") { it.contents }

        "keeps available members" {
            src shouldContain "availableMethod"
            src shouldContain "availableProperty"
        }

        "keeps members unavailable for macOS" {
            src shouldContain "unavailableMethod"
            src shouldContain "unavailableProperty"
        }

        "requires an explicit platform-availability opt-in for unavailable members" {
            src shouldContain "annotation class PlatformAvailability"
            src shouldContain "unavailable = true"
            src shouldContain "@PlatformAvailability("
        }
    }

    // ── @protocol ────────────────────────────────────────────────────────────

    "ObjC protocol interface" - {
        val src = generate("""
            @protocol KxCopyable
            - (id)copy;
            @end
        """.trimIndent())

        "generates interface" {
            src shouldContain "interface KxCopyable"
        }

        "required method is abstract (no default body)" {
            src shouldContain "fun copy()"
            // Required methods have no '= throw' body
            src shouldNotContain "throw UnsupportedOperationException"
        }
    }

    "ObjC protocol optional method" - {
        val src = generate("""
            @protocol KxOptional
            @optional
            - (void)optionalWork;
            @end
        """.trimIndent())

        "optional method has default throw body" {
            src shouldContain "// @optional"
            src shouldContain "throw UnsupportedOperationException"
            src shouldContain "optionalWork"
        }
    }

    // ── ObjCRuntime.kt emission ──────────────────────────────────────────────

    "ObjCRuntime helper file is emitted when ObjC declarations present" - {
        val files = generateAll("""
            @interface KxCounter
            - (long)count;
            @end
        """.trimIndent())

        "at least two files are generated (bindings + ObjCRuntime)" {
            files shouldHaveAtLeastSize 2
        }

        "ObjCRuntime.kt is among the output files" {
            val runtime = getRuntime(files)
            runtime shouldContain "object ObjCRuntime"
            runtime shouldContain "objc_msgSend"
            runtime shouldContain "fun sel(name: String)"
            runtime shouldContain "fun getClass(name: String)"
            runtime shouldContain "fun msgSend("
        }

        "ObjCRuntime carries the correct package" {
            val runtime = getRuntime(files)
            runtime shouldContain "package test"
        }
    }

    "Generated ObjC subclassing helper" - {
        "installs a BOOL(id, SEL) callback that Objective-C dispatch invokes" {
            val files = generateAll(
                """
                    @interface KxBooleanCallbackHost
                    @end
                """.trimIndent(),
            )

            compileAndInvokeBoolean(
                files,
                """
                    package test

                    import java.lang.foreign.MemorySegment
                    import java.lang.foreign.ValueLayout
                    import java.util.UUID

                    fun invokesGeneratedBooleanCallback(): Boolean {
                        val className = "KxGeneratedBooleanCallback_" + UUID.randomUUID().toString().replace('-', '_')
                        val callback = ObjCSubclassing.booleanNoArgumentCallback { _, _ -> true }
                        val cls = ObjCSubclassing.allocateClass("NSObject", className)
                        check(ObjCSubclassing.addBooleanNoArgumentMethod(cls, "acceptsFirstResponder", callback))
                        ObjCSubclassing.registerClass(cls)
                        val receiver = ObjCRuntime.msgSend(
                            ValueLayout.ADDRESS,
                            ObjCRuntime.getClass(className),
                            ObjCRuntime.sel("new"),
                        ) as MemorySegment
                        return try {
                            ObjCRuntime.msgSend(
                                ValueLayout.JAVA_BOOLEAN,
                                receiver,
                                ObjCRuntime.sel("acceptsFirstResponder"),
                            ) as Boolean
                        } finally {
                            ObjCRuntime.msgSend(null, receiver, ObjCRuntime.sel("release"))
                        }
                    }
                """.trimIndent(),
                "invokesGeneratedBooleanCallback",
            ) shouldBe true
        }

        "installs managed IME selector and object callbacks that Objective-C dispatch invokes" {
            val files = generateAll(
                """
                    @interface KxTextInputCallbackHost
                    @end
                """.trimIndent(),
            )

            compileAndInvokeBoolean(
                files,
                """
                    package test

                    import java.lang.foreign.MemorySegment
                    import java.lang.foreign.ValueLayout
                    import java.util.UUID
                    import java.util.concurrent.atomic.AtomicReference

                    fun invokesGeneratedTextInputCallbacks(): Boolean {
                        val className = "KxGeneratedTextInputCallback_" + UUID.randomUUID().toString().replace('-', '_')
                        val receivedCommand = AtomicReference<String?>(null)
                        val commandCallback = ObjCSubclassing.voidSelectorCallback { _, _, command ->
                            receivedCommand.set(ObjCRuntime.selectorName(command))
                        }
                        val attributesCallback = ObjCSubclassing.objectNoArgumentCallback { _, _ ->
                            ObjCRuntime.getClass("NSObject")
                        }
                        val cls = ObjCSubclassing.allocateClass("NSObject", className)
                        check(ObjCSubclassing.addVoidSelectorMethod(cls, "doCommandBySelector:", commandCallback))
                        check(ObjCSubclassing.addObjectNoArgumentMethod(cls, "validAttributesForMarkedText", attributesCallback))
                        ObjCSubclassing.registerClass(cls)
                        val receiver = ObjCRuntime.msgSend(
                            ValueLayout.ADDRESS,
                            ObjCRuntime.getClass(className),
                            ObjCRuntime.sel("new"),
                        ) as MemorySegment
                        return try {
                            ObjCRuntime.msgSend(
                                null,
                                receiver,
                                ObjCRuntime.sel("doCommandBySelector:"),
                                ObjCRuntime.sel("insertNewline:"),
                            )
                            val attributes = ObjCRuntime.msgSend(
                                ValueLayout.ADDRESS,
                                receiver,
                                ObjCRuntime.sel("validAttributesForMarkedText"),
                            ) as MemorySegment
                            receivedCommand.get() == "insertNewline:" &&
                                attributes == ObjCRuntime.getClass("NSObject")
                        } finally {
                            ObjCRuntime.msgSend(null, receiver, ObjCRuntime.sel("release"))
                        }
                    }
                """.trimIndent(),
                "invokesGeneratedTextInputCallbacks",
            ) shouldBe true
        }
    }

    "ObjCRuntime is NOT emitted for pure C headers" - {
        val tmpC = Files.createTempFile("kextract_c_test_", ".h")
        val files: List<KotlinSourceFile> = try {
            tmpC.toFile().writeText("int add(int a, int b);")
            val parsed = KextractTool.parse(listOf(tmpC.toString()))
            val mangled = NameMangler(tmpC.fileName.toString()).scan(parsed)
            KotlinGenerator().generate(mangled, tmpC.fileName.toString(), "test")
        } finally {
            Files.deleteIfExists(tmpC)
        }

        "no ObjCRuntime in pure-C output" {
            getRuntime(files) shouldBe ""
        }
    }

    // ── @property ────────────────────────────────────────────────────────────

    "ObjC property getter and setter" - {
        val src = generate("""
            @interface KxMutable
            @property (readwrite) long capacity;
            @end
        """.trimIndent())

        "getter function emitted" {
            src shouldContain "fun capacity()"
        }

        "setter function emitted for readwrite property" {
            src shouldContain "fun setCapacity"
        }
    }

    "ObjC readonly property has no setter" - {
        val src = generate("""
            @interface KxReadonly
            @property (readonly) long length;
            @end
        """.trimIndent())

        "getter emitted" {
            src shouldContain "fun length()"
        }

        "no setter emitted" {
            src shouldNotContain "fun setLength"
        }
    }

    // ── ObjC block pointer parameters ───────────────────────────────────────

    "ObjC block parameter maps to MemorySegment" - {
        // `void (^completion)(long)` is a BlockPointer type in libclang (TypeKind 113).
        // It must not fall through to `Any` — it should map to MemorySegment.
        val src = generate("""
            @interface KxAsync
            - (void)fetchWithCompletion:(void (^)(long result))completion;
            @end
        """.trimIndent())

        "block parameter type is MemorySegment, not Any" {
            src shouldContain "completion: MemorySegment"
            src shouldNotContain "completion: Any"
        }
    }

    // ── Selector → Kotlin name mapping ───────────────────────────────────────

    "selector name conversion" - {
        val src = generate("""
            @interface KxSelector
            - (void)withArg:(long)x andArg:(long)y;
            @end
        """.trimIndent())

        "colons in selector become underscores, trailing stripped" {
            // selector "withArg:andArg:" → kotlin name "withArg_andArg"
            src shouldContain "fun withArg_andArg"
        }
    }

    // ── NSString convenience overloads ────────────────────────────────────────
    // Use a minimal NSString stub instead of #include <Foundation/Foundation.h>
    // to avoid needing -isysroot / the Xcode SDK at test time.
    // The stub gives clang enough information to resolve NSString * as an
    // ObjCObjectPointer type, which is all the TypeMaker / isNSString() check needs.
    val kNSStringStub = """
        @interface NSObject
        @end
        @interface NSString : NSObject
        @end
    """.trimIndent()

    "NSString return-type convenience overload" - {
        // NSString is a system class; use a stub so no SDK headers are needed
        val src = generate("""
            ${kNSStringStub}
            @interface KxNSStringReturn
            - (NSString *)greeting;
            @end
        """.trimIndent())

        "raw MemorySegment method is emitted" {
            src shouldContain "fun greeting(): MemorySegment"
        }

        "String convenience overload is emitted" {
            src shouldContain "fun greetingAsString(): String = ObjCRuntime.toJavaString(greeting())"
        }
    }

    "NSString parameter convenience overload" - {
        val src = generate("""
            ${kNSStringStub}
            @interface KxNSStringParam
            - (void)setTitle:(NSString *)title;
            @end
        """.trimIndent())

        "raw MemorySegment overload is present" {
            src shouldContain "fun setTitle(title: MemorySegment)"
        }

        "String convenience overload is emitted" {
            src shouldContain "fun setTitle(title: String)"
            src shouldContain "ObjCRuntime.newNSString(Arena.global(), title)"
        }
    }

    "NSString readwrite property convenience overloads" - {
        val src = generate("""
            ${kNSStringStub}
            @interface KxNSStringProp
            @property (readwrite) NSString *label;
            @end
        """.trimIndent())

        "raw getter is emitted" {
            src shouldContain "fun label(): MemorySegment"
        }

        "String getter overload is emitted" {
            src shouldContain "fun labelAsString(): String = ObjCRuntime.toJavaString(label())"
        }

        "String setter overload is emitted" {
            src shouldContain "fun setLabel(value: String) = setLabel(ObjCRuntime.newNSString(Arena.global(), value))"
        }
    }

    "NSString readonly property has only getter overload" - {
        val src = generate("""
            ${kNSStringStub}
            @interface KxNSStringReadonly
            @property (readonly) NSString *title;
            @end
        """.trimIndent())

        "getter overload is emitted" {
            src shouldContain "fun titleAsString(): String = ObjCRuntime.toJavaString(title())"
        }

        "no setter overload emitted" {
            src shouldNotContain "fun setTitle(value: String)"
        }
    }

    "NSString conveniences allocate names around raw selector collisions" {
        val files = generateAll(
            """
                ${kNSStringStub}
                @interface KxNSStringCallableCollision
                - (NSString *)foo;
                - (id)fooAsString;
                @property (readonly) NSString *label;
                - (id)labelAsString;
                @end
            """.trimIndent(),
        )
        val src = files.joinToString("\n") { it.contents }
        val rawMethodName = "fooAsString__objc_666f6f4173537472696e67"
        val propertyConvenienceName = "labelAsString__objc_4e53537472696e674173537472696e673a6c6162656c"

        src shouldContain "fun fooAsString(): String = ObjCRuntime.toJavaString(foo())"
        src shouldContain "open fun $rawMethodName(): MemorySegment {"
        src.substringAfter("fun $rawMethodName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"fooAsString\")"
        src shouldContain "open fun labelAsString(): MemorySegment {"
        src.substringAfter("fun labelAsString(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"labelAsString\")"
        src shouldContain
            "open fun $propertyConvenienceName(): String = ObjCRuntime.toJavaString(label())"
        compileOnly(
            files,
            """
                package test

                import java.lang.foreign.MemorySegment

                fun callNSStringCollision(host: KxNSStringCallableCollision): String {
                    val methodString: String = host.fooAsString()
                    val methodRaw: MemorySegment = host.$rawMethodName()
                    val propertyRaw: MemorySegment = host.labelAsString()
                    val propertyString: String = host.$propertyConvenienceName()
                    return methodString + propertyString + methodRaw.toString() + propertyRaw.toString()
                }
            """.trimIndent(),
        )
    }

    // ── @category ────────────────────────────────────────────────────────────

    "ObjC category instance method becomes extension function" - {
        val src = generate("""
            @interface KxBase
            @end
            @interface KxBase (KxExtras)
            - (long)computedValue;
            @end
        """.trimIndent())

        "extension function on the extended class" {
            src shouldContain "fun KxBase.computedValue()"
        }

        "message sent to ptr" {
            src shouldContain "ObjCRuntime.msgSend"
            src shouldContain "ptr"
        }

        "no top-level function emitted for instance method" {
            src shouldNotContain "fun KxBase_computedValue"
        }
    }

    "ObjC category class method becomes top-level function" - {
        val src = generate("""
            @interface KxFactory
            @end
            @interface KxFactory (KxCreation)
            + (instancetype)createWithValue:(long)v;
            @end
        """.trimIndent())

        "top-level function named <Class>_<method>" {
            src shouldContain "fun KxFactory_createWithValue("
        }

        "function calls ObjCRuntime.getClass directly" {
            src shouldContain """ObjCRuntime.getClass("KxFactory")"""
        }

        "no extension function on the class for class method" {
            src shouldNotContain "fun KxFactory.createWithValue"
        }

        "comment identifies it as a class method" {
            src shouldContain "// Class method: +[KxFactory createWithValue:]"
        }
    }

    "ObjC category with both instance and class methods" - {
        val src = generate("""
            @interface KxMixed
            @end
            @interface KxMixed (KxBoth)
            - (void)doWork;
            + (instancetype)make;
            @end
        """.trimIndent())

        "instance method is extension function" {
            src shouldContain "fun KxMixed.doWork()"
        }

        "class method is top-level function" {
            src shouldContain "fun KxMixed_make()"
        }

        "class method uses getClass, instance method uses ptr" {
            src shouldContain """ObjCRuntime.getClass("KxMixed")"""
            src shouldContain "ptr"
        }
    }

    "Manual required protocol property and category property deduplicate accessors independently" {
        val objectPointer = Type.pointer()
        val requiredReadOnlyProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "foo",
            objectPointer,
            "id",
            isOptional = false,
            isReadOnly = true,
            getterSelector = "foo",
            setterSelector = "",
        )
        val categoryReadWriteProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "foo",
            objectPointer,
            "id",
            isOptional = false,
            isReadOnly = false,
            getterSelector = "foo",
            setterSelector = "setFoo:",
        )
        val requiredProtocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxManualReadOnlyRequirement",
            emptyList(),
            emptyList(),
            listOf(requiredReadOnlyProperty),
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxManualAccessorHost",
            null,
            listOf(requiredProtocol.name()),
            emptyList(),
            emptyList(),
        )
        val category = Declaration.objcCategory(
            Position.NO_POSITION,
            "KxManualAccessorHostWritableFoo",
            host.name(),
            "WritableFoo",
            emptyList(),
            listOf(categoryReadWriteProperty),
        )

        val src = generateManual(requiredProtocol, host, category)
            .joinToString("\n") { it.contents }

        src.split("fun KxManualAccessorHost.foo(): MemorySegment").size - 1 shouldBe 1
        src.split("fun KxManualAccessorHost.setFoo(value: MemorySegment)").size - 1 shouldBe 1
    }

    "Manual direct property suppresses matching protocol and category accessors" {
        val objectPointer = Type.pointer()
        val directReadWriteProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "foo",
            objectPointer,
            "id",
            isOptional = false,
            isReadOnly = false,
            getterSelector = "foo",
            setterSelector = "setFoo:",
        )
        val requiredReadOnlyProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "foo",
            objectPointer,
            "id",
            isOptional = false,
            isReadOnly = true,
            getterSelector = "foo",
            setterSelector = "",
        )
        val categoryReadWriteProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "foo",
            objectPointer,
            "id",
            isOptional = false,
            isReadOnly = false,
            getterSelector = "foo",
            setterSelector = "setFoo:",
        )
        val requiredProtocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxDirectReadOnlyRequirement",
            emptyList(),
            emptyList(),
            listOf(requiredReadOnlyProperty),
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxDirectAccessorHost",
            null,
            listOf(requiredProtocol.name()),
            emptyList(),
            listOf(directReadWriteProperty),
        )
        val category = Declaration.objcCategory(
            Position.NO_POSITION,
            "KxDirectAccessorHostWritableFoo",
            host.name(),
            "WritableFoo",
            emptyList(),
            listOf(categoryReadWriteProperty),
        )

        val src = generateManual(requiredProtocol, host, category)
            .joinToString("\n") { it.contents }

        src.split("open fun foo(): MemorySegment").size - 1 shouldBe 1
        src.split("open fun setFoo(value: MemorySegment)").size - 1 shouldBe 1
        src shouldNotContain "fun KxDirectAccessorHost.foo(): MemorySegment"
        src shouldNotContain "fun KxDirectAccessorHost.setFoo(value: MemorySegment)"
    }

    "Manual required class properties use top-level class dispatch without synthetic methods" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val requiredReadOnlyProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "classValue",
            longType,
            "long",
            isOptional = false,
            isReadOnly = true,
            getterSelector = "classValue",
            setterSelector = "",
            isClassProperty = true,
        )
        val requiredReadWriteProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "mutableClassValue",
            longType,
            "long",
            isOptional = false,
            isReadOnly = false,
            getterSelector = "mutableClassValue",
            setterSelector = "setMutableClassValue:",
            isClassProperty = true,
        )
        val requiredProtocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxManualClassPropertyRequirement",
            emptyList(),
            emptyList(),
            listOf(requiredReadOnlyProperty, requiredReadWriteProperty),
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxManualClassPropertyHost",
            null,
            listOf(requiredProtocol.name()),
            emptyList(),
            emptyList(),
        )

        val src = generateManual(requiredProtocol, host)
            .joinToString("\n") { it.contents }

        src.split("fun KxManualClassPropertyHost_classValue(): Long").size - 1 shouldBe 1
        src.split("fun KxManualClassPropertyHost_mutableClassValue(): Long").size - 1 shouldBe 1
        src.split("fun KxManualClassPropertyHost_setMutableClassValue(value: Long)").size - 1 shouldBe 1
        src shouldContain "ObjCRuntime.getClass(\"KxManualClassPropertyHost\")"
        src shouldNotContain "fun KxManualClassPropertyHost.classValue()"
        src shouldNotContain "fun KxManualClassPropertyHost.mutableClassValue()"
        src shouldNotContain "fun KxManualClassPropertyHost.setMutableClassValue("
    }

    "Manual direct and category class properties share class signatures without synthetic methods" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val directReadWriteProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "directValue",
            longType,
            "long",
            isOptional = false,
            isReadOnly = false,
            getterSelector = "directValue",
            setterSelector = "setDirectValue:",
            isClassProperty = true,
        )
        val requiredReadOnlyProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "directValue",
            longType,
            "long",
            isOptional = false,
            isReadOnly = true,
            getterSelector = "directValue",
            setterSelector = "",
            isClassProperty = true,
        )
        val categoryDuplicateProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "directValue",
            longType,
            "long",
            isOptional = false,
            isReadOnly = false,
            getterSelector = "directValue",
            setterSelector = "setDirectValue:",
            isClassProperty = true,
        )
        val categoryReadWriteProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "categoryValue",
            longType,
            "long",
            isOptional = false,
            isReadOnly = false,
            getterSelector = "categoryValue",
            setterSelector = "setCategoryValue:",
            isClassProperty = true,
        )
        val requiredProtocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxManualDirectClassPropertyRequirement",
            emptyList(),
            emptyList(),
            listOf(requiredReadOnlyProperty),
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxManualDirectClassPropertyHost",
            null,
            listOf(requiredProtocol.name()),
            emptyList(),
            listOf(directReadWriteProperty),
        )
        val category = Declaration.objcCategory(
            Position.NO_POSITION,
            "KxManualDirectClassPropertyHostCategory",
            host.name(),
            "Category",
            emptyList(),
            listOf(categoryDuplicateProperty, categoryReadWriteProperty),
        )

        val src = generateManual(requiredProtocol, host, category)
            .joinToString("\n") { it.contents }
        val hostAndExtensions = src.substringAfter("open class KxManualDirectClassPropertyHost")

        hostAndExtensions.split("fun directValue(): Long").size - 1 shouldBe 1
        hostAndExtensions.split("fun setDirectValue(value: Long)").size - 1 shouldBe 1
        hostAndExtensions shouldContain "ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, _class, sel)"
        src shouldNotContain "fun KxManualDirectClassPropertyHost_directValue()"
        src shouldNotContain "fun KxManualDirectClassPropertyHost_setDirectValue("
        src.split("fun KxManualDirectClassPropertyHost_categoryValue(): Long").size - 1 shouldBe 1
        src.split("fun KxManualDirectClassPropertyHost_setCategoryValue(value: Long)").size - 1 shouldBe 1
        src shouldContain "val cls = ObjCRuntime.getClass(\"KxManualDirectClassPropertyHost\")"
        src shouldNotContain "fun KxManualDirectClassPropertyHost.categoryValue()"
        src shouldNotContain "fun KxManualDirectClassPropertyHost.setCategoryValue("
    }

    "Required class-property foo and protocol +foo: stay concrete-only without synthetic accessors" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val value = Declaration.parameter(Position.NO_POSITION, "value", longType)
        val fooWithValue = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo",
            "foo:",
            true,
            longType,
            "long",
            listOf(value),
            false,
        )
        val writableClassProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "foo",
            longType,
            "long",
            isOptional = false,
            isReadOnly = false,
            getterSelector = "foo",
            setterSelector = "setFoo:",
            isClassProperty = true,
        )
        val protocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxProtocolSelectorRequirement",
            emptyList(),
            listOf(fooWithValue),
            listOf(writableClassProperty),
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxProtocolSelectorHost",
            null,
            listOf(protocol.name()),
            emptyList(),
            emptyList(),
        )

        val src = generateManual(protocol, host).joinToString("\n") { it.contents }
        val protocolInterface = src
            .substringAfter("interface KxProtocolSelectorRequirement")
            .substringBefore("open class KxProtocolSelectorHost")

        protocolInterface shouldNotContain "fun foo(): Long"
        protocolInterface shouldNotContain "fun setFoo(value: Long)"
        protocolInterface shouldNotContain "fun foo(value: Long): Long"
        src.split("fun KxProtocolSelectorHost_foo(): Long").size - 1 shouldBe 1
        src.split("fun KxProtocolSelectorHost_setFoo(value: Long)").size - 1 shouldBe 1
        src.split("fun KxProtocolSelectorHost_foo(value: Long): Long").size - 1 shouldBe 1
    }

    "Protocol interface retains required instance contract when matching class selector is optional" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val value = Declaration.parameter(Position.NO_POSITION, "typeIdentifier", longType)
        val selector = "itemProviderVisibilityForRepresentationWithTypeIdentifier:"
        val classMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "itemProviderVisibilityForRepresentationWithTypeIdentifier",
            selector,
            true,
            longType,
            "long",
            listOf(value),
            true,
        )
        val instanceMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "itemProviderVisibilityForRepresentationWithTypeIdentifier",
            selector,
            false,
            longType,
            "long",
            listOf(value),
            false,
        )
        val protocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxMatchingSelectorProtocol",
            emptyList(),
            listOf(classMethod, instanceMethod),
            emptyList(),
        )

        val files = generateManual(protocol)
        val src = files.joinToString("\n") { it.contents }
        val protocolInterface = src.substringAfter("interface KxMatchingSelectorProtocol")

        protocolInterface shouldContain
            "fun itemProviderVisibilityForRepresentationWithTypeIdentifier(typeIdentifier: Long): Long\n"
        protocolInterface shouldNotContain "// @optional"
        protocolInterface shouldNotContain "Optional ObjC method 'itemProviderVisibilityForRepresentationWithTypeIdentifier:'"
        compileOnly(
            files,
            """
                package test

                fun readVisibility(contract: KxMatchingSelectorProtocol): Long =
                    contract.itemProviderVisibilityForRepresentationWithTypeIdentifier(0L)
            """.trimIndent(),
        )
    }

    "Required protocol selector collisions retain distinct callable names and selectors" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val first = Declaration.parameter(Position.NO_POSITION, "first", longType)
        val second = Declaration.parameter(Position.NO_POSITION, "second", longType)
        val legacySelector = "foo_bar:baz:"
        val collidingSelector = "foo:bar_baz:"
        val legacyMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            legacySelector,
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val collidingMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            collidingSelector,
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val protocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxSelectorCollisionRequirement",
            emptyList(),
            listOf(legacyMethod, collidingMethod),
            emptyList(),
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxSelectorCollisionHost",
            null,
            listOf(protocol.name()),
            emptyList(),
            emptyList(),
        )

        val files = generateManual(protocol, host)
        val src = files.joinToString("\n") { it.contents }
        val legacyName = "foo_bar_baz"
        val suffixedName = "foo_bar_baz__objc_666f6f3a6261725f62617a3a"
        val protocolInterface = src
            .substringAfter("interface KxSelectorCollisionRequirement")
            .substringBefore("open class KxSelectorCollisionHost")

        protocolInterface shouldContain "fun $legacyName(first: Long, second: Long): Long"
        protocolInterface shouldContain "fun $suffixedName(first: Long, second: Long): Long"
        src shouldContain "fun KxSelectorCollisionHost.$legacyName(first: Long, second: Long): Long {"
        src shouldContain "fun KxSelectorCollisionHost.$suffixedName(first: Long, second: Long): Long {"
        src.substringAfter("fun KxSelectorCollisionHost.$legacyName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"$legacySelector\")"
        src.substringAfter("fun KxSelectorCollisionHost.$suffixedName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"$collidingSelector\")"
        compileOnly(
            files,
            """
                package test

                fun callBothSelectorCollisions(host: KxSelectorCollisionHost): Long =
                    host.$legacyName(1L, 2L) + host.$suffixedName(3L, 4L)
            """.trimIndent(),
        )
    }

    "Each protocol retains a required selector shared with another protocol" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val requiredMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "sharedValue",
            "sharedValue:",
            false,
            longType,
            "long",
            listOf(Declaration.parameter(Position.NO_POSITION, "value", longType)),
            false,
        )
        val first = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxFirstSharedSelectorProtocol",
            emptyList(),
            listOf(requiredMethod),
            emptyList(),
        )
        val second = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxSecondSharedSelectorProtocol",
            emptyList(),
            listOf(requiredMethod),
            emptyList(),
        )

        val files = generateManual(first, second)
        val src = files.joinToString("\n") { it.contents }
        val firstInterface = src
            .substringAfter("interface KxFirstSharedSelectorProtocol")
            .substringBefore("interface KxSecondSharedSelectorProtocol")
        val secondInterface = src.substringAfter("interface KxSecondSharedSelectorProtocol")

        firstInterface shouldContain "fun sharedValue(value: Long): Long"
        secondInterface shouldContain "fun sharedValue(value: Long): Long"
        compileOnly(
            files,
            """
                package test

                class SharedSelectorConsumer : KxFirstSharedSelectorProtocol, KxSecondSharedSelectorProtocol {
                    override fun sharedValue(value: Long): Long = value
                }
            """.trimIndent(),
        )
    }

    "Child protocols reserve inherited callable names for distinct selector collisions" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val first = Declaration.parameter(Position.NO_POSITION, "first", longType)
        val second = Declaration.parameter(Position.NO_POSITION, "second", longType)
        val parentMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo_bar:baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val childMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo:bar_baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val parent = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxParentSelectorProtocol",
            emptyList(),
            listOf(parentMethod),
            emptyList(),
        )
        val child = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxChildSelectorProtocol",
            listOf(parent.name()),
            listOf(childMethod),
            emptyList(),
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxProtocolSelectorHierarchyHost",
            null,
            listOf(child.name()),
            emptyList(),
            emptyList(),
        )

        // Visit the child before its parent to prove reservations come from the pre-scanned
        // protocol hierarchy rather than declaration/emission order.
        val files = generateManual(child, parent, host)
        val src = files.joinToString("\n") { it.contents }
        val suffixedName = "foo_bar_baz__objc_666f6f3a6261725f62617a3a"
        fun protocolBody(name: String): String {
            val declaration = src.indexOf("interface $name")
            val bodyStart = src.indexOf('{', declaration)
            val bodyEnd = src.indexOf("\n}", bodyStart)
            return src.substring(bodyStart + 1, bodyEnd)
        }
        val parentInterface = protocolBody("KxParentSelectorProtocol")
        val childInterface = protocolBody("KxChildSelectorProtocol")

        parentInterface shouldContain "fun foo_bar_baz(first: Long, second: Long): Long"
        childInterface shouldContain "fun $suffixedName(first: Long, second: Long): Long"
        childInterface shouldNotContain "fun foo_bar_baz(first: Long, second: Long): Long"
        src.substringAfter("fun KxProtocolSelectorHierarchyHost.foo_bar_baz(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"foo_bar:baz:\")"
        src.substringAfter("fun KxProtocolSelectorHierarchyHost.$suffixedName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"foo:bar_baz:\")"
        compileOnly(
            files,
            """
                package test

                class ProtocolHierarchyConsumer : KxChildSelectorProtocol {
                    override fun foo_bar_baz(first: Long, second: Long): Long = first + second
                    override fun $suffixedName(first: Long, second: Long): Long = first - second
                }

                fun callProtocolHierarchy(contract: KxChildSelectorProtocol): Long =
                    contract.foo_bar_baz(3L, 2L) + contract.$suffixedName(3L, 2L)
            """.trimIndent(),
        )
    }

    "Child protocols override an inherited callable with the same raw selector" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val first = Declaration.parameter(Position.NO_POSITION, "first", longType)
        val second = Declaration.parameter(Position.NO_POSITION, "second", longType)
        val parentMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo_bar:baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val childMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo_bar:baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val parent = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxParentExactSelectorProtocol",
            emptyList(),
            listOf(parentMethod),
            emptyList(),
        )
        val child = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxChildExactSelectorProtocol",
            listOf(parent.name()),
            listOf(childMethod),
            emptyList(),
        )

        val files = generateManual(child, parent)
        val src = files.joinToString("\n") { it.contents }
        val childDeclaration = src.indexOf("interface KxChildExactSelectorProtocol")
        val childBodyStart = src.indexOf('{', childDeclaration)
        val childBodyEnd = src.indexOf("\n}", childBodyStart)
        val childBody = src.substring(childBodyStart + 1, childBodyEnd)

        childBody shouldContain "override fun foo_bar_baz(first: Long, second: Long): Long"
        compileOnly(
            files,
            """
                package test

                class ExactSelectorProtocolConsumer : KxChildExactSelectorProtocol {
                    override fun foo_bar_baz(first: Long, second: Long): Long = first + second
                }

                fun callExactSelectorProtocol(contract: KxChildExactSelectorProtocol): Long =
                    contract.foo_bar_baz(3L, 2L)
            """.trimIndent(),
        )
    }

    "Filtered protocol parents do not reserve child interface overrides" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val parentMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "baseValue",
            "baseValue",
            false,
            longType,
            "long",
            emptyList(),
            false,
        )
        val parentProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "effectiveAppearance",
            longType,
            "long",
            isOptional = false,
            isReadOnly = true,
            getterSelector = "effectiveAppearance",
            setterSelector = "setEffectiveAppearance:",
            isClassProperty = false,
        )
        val parent = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxFilteredProtocolParent",
            emptyList(),
            listOf(parentMethod),
            listOf(parentProperty),
        )
        val classHomonym = Declaration.objcClass(
            Position.NO_POSITION,
            parent.name(),
            null,
            emptyList(),
            emptyList(),
            emptyList(),
        )
        val child = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxFilteredProtocolChild",
            listOf(parent.name()),
            listOf(parentMethod),
            listOf(parentProperty),
        )

        val files = generateManual(child, parent, classHomonym)
        val src = files.joinToString("\n") { it.contents }
        val childDeclaration = src.indexOf("interface KxFilteredProtocolChild")
        val childBodyStart = src.indexOf('{', childDeclaration)
        val childBodyEnd = src.indexOf("\n}", childBodyStart)
        val childBody = src.substring(childBodyStart + 1, childBodyEnd)

        childBody shouldContain "fun baseValue(): Long"
        childBody shouldContain "fun effectiveAppearance(): Long"
        childBody shouldNotContain "override fun baseValue(): Long"
        childBody shouldNotContain "override fun effectiveAppearance(): Long"
        compileOnly(
            files,
            """
                package test

                class FilteredParentConsumer : KxFilteredProtocolChild {
                    override fun baseValue(): Long = 1L
                    override fun effectiveAppearance(): Long = 2L
                }
            """.trimIndent(),
        )
    }

    "Child classes reserve inherited callable names for distinct selector collisions" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val first = Declaration.parameter(Position.NO_POSITION, "first", longType)
        val second = Declaration.parameter(Position.NO_POSITION, "second", longType)
        val parentMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo_bar:baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val childMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo:bar_baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val parent = Declaration.objcClass(
            Position.NO_POSITION,
            "KxParentSelectorClass",
            null,
            emptyList(),
            listOf(parentMethod),
            emptyList(),
        )
        val child = Declaration.objcClass(
            Position.NO_POSITION,
            "KxChildSelectorClass",
            parent.name(),
            emptyList(),
            listOf(childMethod),
            emptyList(),
        )

        // The hierarchy reservation must not rely on the parent having been emitted first.
        val files = generateManual(child, parent)
        val src = files.joinToString("\n") { it.contents }
        val suffixedName = "foo_bar_baz__objc_666f6f3a6261725f62617a3a"
        val childClass = src.substringAfter("open class KxChildSelectorClass")

        src.substringAfter("open class KxParentSelectorClass").substringBefore("open class KxChildSelectorClass") shouldContain
            "open fun foo_bar_baz(first: Long, second: Long): Long {"
        childClass shouldContain "open fun $suffixedName(first: Long, second: Long): Long {"
        childClass shouldNotContain "override fun foo_bar_baz(first: Long, second: Long): Long {"
        childClass.substringAfter("fun $suffixedName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"foo:bar_baz:\")"
        compileOnly(
            files,
            """
                package test

                fun callClassHierarchy(host: KxChildSelectorClass): Long =
                    host.foo_bar_baz(3L, 2L) + host.$suffixedName(3L, 2L)
            """.trimIndent(),
        )
    }

    "Child class protocol requirements do not inherit parent class selector suppression" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val version = Declaration.objcMethod(
            Position.NO_POSITION,
            "version",
            "version",
            true,
            longType,
            "long",
            emptyList(),
            false,
        )
        val parent = Declaration.objcClass(
            Position.NO_POSITION,
            "KxParentClassRequirement",
            null,
            emptyList(),
            listOf(version),
            emptyList(),
        )
        val requirement = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxChildClassVersionRequirement",
            emptyList(),
            listOf(version),
            emptyList(),
        )
        val child = Declaration.objcClass(
            Position.NO_POSITION,
            "KxChildClassRequirement",
            parent.name(),
            listOf(requirement.name()),
            emptyList(),
            emptyList(),
        )

        val files = generateManual(parent, requirement, child)
        val src = files.joinToString("\n") { it.contents }
        val childFunction = "KxChildClassRequirement_version"

        src shouldContain "fun $childFunction(): Long {"
        src.substringAfter("fun $childFunction(").substringBefore("\n}") shouldContain
            "ObjCRuntime.getClass(\"KxChildClassRequirement\")"
        compileOnly(
            files,
            """
                package test

                fun callChildClassRequirement(): Long = $childFunction()
            """.trimIndent(),
        )
    }

    "Direct class selector collisions retain distinct callable names" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val first = Declaration.parameter(Position.NO_POSITION, "first", longType)
        val second = Declaration.parameter(Position.NO_POSITION, "second", longType)
        val legacyMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo_bar:baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val collidingMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo:bar_baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxDirectSelectorCollisionHost",
            null,
            emptyList(),
            listOf(legacyMethod, collidingMethod),
            emptyList(),
        )

        val files = generateManual(host)
        val src = files.joinToString("\n") { it.contents }
        val suffixedName = "foo_bar_baz__objc_666f6f3a6261725f62617a3a"

        src shouldContain "open fun foo_bar_baz(first: Long, second: Long): Long {"
        src shouldContain "open fun $suffixedName(first: Long, second: Long): Long {"
        compileOnly(
            files,
            """
                package test

                fun callDirectSelectorCollisions(host: KxDirectSelectorCollisionHost): Long =
                    host.foo_bar_baz(1L, 2L) + host.$suffixedName(3L, 4L)
            """.trimIndent(),
        )
    }

    "Protocol requirements and categories share selector-collision names" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val first = Declaration.parameter(Position.NO_POSITION, "first", longType)
        val second = Declaration.parameter(Position.NO_POSITION, "second", longType)
        val requiredMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo_bar:baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val categoryMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo:bar_baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val protocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxCategorySelectorCollisionRequirement",
            emptyList(),
            listOf(requiredMethod),
            emptyList(),
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxCategorySelectorCollisionHost",
            null,
            listOf(protocol.name()),
            emptyList(),
            emptyList(),
        )
        val category = Declaration.objcCategory(
            Position.NO_POSITION,
            "KxCategorySelectorCollisionHostCategory",
            host.name(),
            "Collision",
            listOf(categoryMethod),
            emptyList(),
        )

        val files = generateManual(protocol, host, category)
        val src = files.joinToString("\n") { it.contents }
        val suffixedName = "foo_bar_baz__objc_666f6f3a6261725f62617a3a"

        src shouldContain "fun KxCategorySelectorCollisionHost.foo_bar_baz(first: Long, second: Long): Long {"
        src shouldContain "fun KxCategorySelectorCollisionHost.$suffixedName(first: Long, second: Long): Long {"
        compileOnly(
            files,
            """
                package test

                fun callCategorySelectorCollisions(host: KxCategorySelectorCollisionHost): Long =
                    host.foo_bar_baz(1L, 2L) + host.$suffixedName(3L, 4L)
            """.trimIndent(),
        )
    }

    "Required protocol extension avoids shadowing by a direct wrapper callable" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val first = Declaration.parameter(Position.NO_POSITION, "first", longType)
        val second = Declaration.parameter(Position.NO_POSITION, "second", longType)
        val directMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo_bar:baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val requiredMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo:bar_baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val protocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxDirectExtensionRequirement",
            emptyList(),
            listOf(requiredMethod),
            emptyList(),
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxDirectExtensionHost",
            null,
            listOf(protocol.name()),
            listOf(directMethod),
            emptyList(),
        )

        val files = generateManual(protocol, host)
        val src = files.joinToString("\n") { it.contents }
        val suffixedName = "foo_bar_baz__objc_666f6f3a6261725f62617a3a"

        src shouldContain "open fun foo_bar_baz(first: Long, second: Long): Long {"
        src shouldContain "fun KxDirectExtensionHost.$suffixedName(first: Long, second: Long): Long {"
        src.substringAfter("open fun foo_bar_baz(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"foo_bar:baz:\")"
        src.substringAfter("fun KxDirectExtensionHost.$suffixedName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"foo:bar_baz:\")"
        compileOnly(
            files,
            """
                package test

                fun callDirectAndRequiredExtensions(host: KxDirectExtensionHost): Long =
                    host.foo_bar_baz(1L, 2L) + host.$suffixedName(3L, 4L)
            """.trimIndent(),
        )
    }

    "Child extensions reserve distinct parent extension callables" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val first = Declaration.parameter(Position.NO_POSITION, "first", longType)
        val second = Declaration.parameter(Position.NO_POSITION, "second", longType)
        val parentExtensionMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo_bar:baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val childRequiredMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo:bar_baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val childProtocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxChildExtensionRequirement",
            emptyList(),
            listOf(childRequiredMethod),
            emptyList(),
        )
        val parent = Declaration.objcClass(
            Position.NO_POSITION,
            "KxInheritedExtensionParent",
            null,
            emptyList(),
            emptyList(),
            emptyList(),
        )
        val child = Declaration.objcClass(
            Position.NO_POSITION,
            "KxInheritedExtensionChild",
            parent.name(),
            listOf(childProtocol.name()),
            emptyList(),
            emptyList(),
        )
        val parentCategory = Declaration.objcCategory(
            Position.NO_POSITION,
            "KxInheritedExtensionParentCategory",
            parent.name(),
            "Collision",
            listOf(parentExtensionMethod),
            emptyList(),
        )

        // Visit the child before its parent/category to make hierarchy reservations order-independent.
        val files = generateManual(child, childProtocol, parent, parentCategory)
        val src = files.joinToString("\n") { it.contents }
        val suffixedName = "foo_bar_baz__objc_666f6f3a6261725f62617a3a"

        src shouldContain "fun KxInheritedExtensionParent.foo_bar_baz(first: Long, second: Long): Long {"
        src shouldContain "fun KxInheritedExtensionChild.$suffixedName(first: Long, second: Long): Long {"
        src.substringAfter("fun KxInheritedExtensionParent.foo_bar_baz(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"foo_bar:baz:\")"
        src.substringAfter("fun KxInheritedExtensionChild.$suffixedName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"foo:bar_baz:\")"
        compileOnly(
            files,
            """
                package test

                fun callInheritedExtensions(host: KxInheritedExtensionChild): Long =
                    host.foo_bar_baz(1L, 2L) + host.$suffixedName(3L, 4L)
            """.trimIndent(),
        )
    }

    "Required child extension survives an inherited selector hidden by a direct member" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val first = Declaration.parameter(Position.NO_POSITION, "first", longType)
        val second = Declaration.parameter(Position.NO_POSITION, "second", longType)
        val parentExtensionMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo_bar:baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val childDirectMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo:bar_baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val childRequiredMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo_bar:baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val childProtocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxHiddenParentExtensionRequirement",
            emptyList(),
            listOf(childRequiredMethod),
            emptyList(),
        )
        val parent = Declaration.objcClass(
            Position.NO_POSITION,
            "KxHiddenParentExtensionParent",
            null,
            emptyList(),
            emptyList(),
            emptyList(),
        )
        val parentCategory = Declaration.objcCategory(
            Position.NO_POSITION,
            "KxHiddenParentExtensionCategory",
            parent.name(),
            "Collision",
            listOf(parentExtensionMethod),
            emptyList(),
        )
        val child = Declaration.objcClass(
            Position.NO_POSITION,
            "KxHiddenParentExtensionChild",
            parent.name(),
            listOf(childProtocol.name()),
            listOf(childDirectMethod),
            emptyList(),
        )

        // The category precedes the child, reproducing the order-sensitive inherited signature.
        val files = generateManual(parent, parentCategory, childProtocol, child)
        val src = files.joinToString("\n") { it.contents }
        val suffixedName = "foo_bar_baz__objc_666f6f5f6261723a62617a3a"

        src shouldContain "open fun foo_bar_baz(first: Long, second: Long): Long {"
        src shouldContain "fun KxHiddenParentExtensionChild.$suffixedName(first: Long, second: Long): Long {"
        src.substringAfter("open fun foo_bar_baz(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"foo:bar_baz:\")"
        src.substringAfter("fun KxHiddenParentExtensionChild.$suffixedName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"foo_bar:baz:\")"
        compileOnly(
            files,
            """
                package test

                fun callHiddenParentExtension(host: KxHiddenParentExtensionChild): Long =
                    host.foo_bar_baz(1L, 2L) + host.$suffixedName(3L, 4L)
            """.trimIndent(),
        )
    }

    "A hidden inherited selector is re-emitted only once for child requirements" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val first = Declaration.parameter(Position.NO_POSITION, "first", longType)
        val second = Declaration.parameter(Position.NO_POSITION, "second", longType)
        val parentExtensionMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo_bar:baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val childDirectMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo:bar_baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        fun requiredMethod() = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            "foo_bar:baz:",
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val firstProtocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxFirstHiddenParentRequirement",
            emptyList(),
            listOf(requiredMethod()),
            emptyList(),
        )
        val secondProtocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxSecondHiddenParentRequirement",
            emptyList(),
            listOf(requiredMethod()),
            emptyList(),
        )
        val parent = Declaration.objcClass(
            Position.NO_POSITION,
            "KxDuplicateHiddenParent",
            null,
            emptyList(),
            emptyList(),
            emptyList(),
        )
        val parentCategory = Declaration.objcCategory(
            Position.NO_POSITION,
            "KxDuplicateHiddenParentCategory",
            parent.name(),
            "Collision",
            listOf(parentExtensionMethod),
            emptyList(),
        )
        val child = Declaration.objcClass(
            Position.NO_POSITION,
            "KxDuplicateHiddenChild",
            parent.name(),
            listOf(firstProtocol.name(), secondProtocol.name()),
            listOf(childDirectMethod),
            emptyList(),
        )

        val files = generateManual(parent, parentCategory, firstProtocol, secondProtocol, child)
        val src = files.joinToString("\n") { it.contents }
        val suffixedName = "foo_bar_baz__objc_666f6f5f6261723a62617a3a"

        src.split("fun KxDuplicateHiddenChild.$suffixedName(first: Long, second: Long): Long").size - 1 shouldBe 1
        src.substringAfter("fun KxDuplicateHiddenChild.$suffixedName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"foo_bar:baz:\")"
        compileOnly(
            files,
            """
                package test

                fun callDeduplicatedHiddenParentExtension(host: KxDuplicateHiddenChild): Long =
                    host.foo_bar_baz(1L, 2L) + host.$suffixedName(3L, 4L)
            """.trimIndent(),
        )
    }

    "Composed inherited extensions reserve a suffix occupied by a child direct member" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val first = Declaration.parameter(Position.NO_POSITION, "first", longType)
        val second = Declaration.parameter(Position.NO_POSITION, "second", longType)
        val firstSelector = "foo_bar:baz:"
        val requiredSelector = "foo:bar_baz:"
        val requiredSuffix = "foo_bar_baz__objc_666f6f3a6261725f62617a3a"
        val childSelector = "$requiredSuffix::"
        val firstExtension = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            firstSelector,
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val parentExtension = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo_bar_baz",
            requiredSelector,
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val childDirect = Declaration.objcMethod(
            Position.NO_POSITION,
            requiredSuffix,
            childSelector,
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val requiredProtocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxComposedExtensionRequirement",
            emptyList(),
            listOf(parentExtension),
            emptyList(),
        )
        val grandparent = Declaration.objcClass(
            Position.NO_POSITION,
            "KxComposedExtensionGrandparent",
            null,
            emptyList(),
            emptyList(),
            emptyList(),
        )
        val firstCategory = Declaration.objcCategory(
            Position.NO_POSITION,
            "KxComposedExtensionGrandparentCategory",
            grandparent.name(),
            "First",
            listOf(firstExtension),
            emptyList(),
        )
        val parent = Declaration.objcClass(
            Position.NO_POSITION,
            "KxComposedExtensionParent",
            grandparent.name(),
            emptyList(),
            emptyList(),
            emptyList(),
        )
        val secondCategory = Declaration.objcCategory(
            Position.NO_POSITION,
            "KxComposedExtensionParentCategory",
            parent.name(),
            "Second",
            listOf(parentExtension),
            emptyList(),
        )
        val child = Declaration.objcClass(
            Position.NO_POSITION,
            "KxComposedExtensionChild",
            parent.name(),
            listOf(requiredProtocol.name()),
            listOf(childDirect),
            emptyList(),
        )

        val files = generateManual(
            grandparent,
            firstCategory,
            parent,
            secondCategory,
            requiredProtocol,
            child,
        )
        val src = files.joinToString("\n") { it.contents }
        val childRequirementName = "${requiredSuffix}_2"

        src shouldContain "fun KxComposedExtensionParent.$requiredSuffix(first: Long, second: Long): Long {"
        src shouldContain "open fun $requiredSuffix(first: Long, second: Long): Long {"
        src shouldContain "fun KxComposedExtensionChild.$childRequirementName(first: Long, second: Long): Long {"
        src.substringAfter("open fun $requiredSuffix(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"$childSelector\")"
        src.substringAfter("fun KxComposedExtensionChild.$childRequirementName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"$requiredSelector\")"
        compileOnly(
            files,
            """
                package test

                fun callComposedExtensionSurface(host: KxComposedExtensionChild): Long =
                    host.$requiredSuffix(1L, 2L) + host.$childRequirementName(3L, 4L)
            """.trimIndent(),
        )
    }

    "Inherited extension replay follows inheritance order instead of inverse header order" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val first = Declaration.parameter(Position.NO_POSITION, "first", longType)
        val second = Declaration.parameter(Position.NO_POSITION, "second", longType)
        val grandparentSelector = "foo_bar:baz:"
        val parentSelector = "foo:bar_baz:"
        val parentSuffix = "foo_bar_baz__objc_666f6f3a6261725f62617a3a"
        val childSelector = "$parentSuffix::"
        fun method(selector: String, name: String = "foo_bar_baz") = Declaration.objcMethod(
            Position.NO_POSITION,
            name,
            selector,
            false,
            longType,
            "long",
            listOf(first, second),
            false,
        )
        val grandparent = Declaration.objcClass(
            Position.NO_POSITION,
            "KxInverseReplayGrandparent",
            null,
            emptyList(),
            emptyList(),
            emptyList(),
        )
        val parent = Declaration.objcClass(
            Position.NO_POSITION,
            "KxInverseReplayParent",
            grandparent.name(),
            emptyList(),
            emptyList(),
            emptyList(),
        )
        val parentCategory = Declaration.objcCategory(
            Position.NO_POSITION,
            "KxInverseReplayParentCategory",
            parent.name(),
            "Parent",
            listOf(method(parentSelector)),
            emptyList(),
        )
        val grandparentCategory = Declaration.objcCategory(
            Position.NO_POSITION,
            "KxInverseReplayGrandparentCategory",
            grandparent.name(),
            "Grandparent",
            listOf(method(grandparentSelector)),
            emptyList(),
        )
        val requiredProtocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxInverseReplayRequirement",
            emptyList(),
            listOf(method(parentSelector)),
            emptyList(),
        )
        val child = Declaration.objcClass(
            Position.NO_POSITION,
            "KxInverseReplayChild",
            parent.name(),
            listOf(requiredProtocol.name()),
            listOf(method(childSelector, parentSuffix)),
            emptyList(),
        )

        // Parent's category deliberately precedes the grandparent's category in header order.
        val files = generateManual(
            grandparent,
            parent,
            parentCategory,
            grandparentCategory,
            requiredProtocol,
            child,
        )
        val src = files.joinToString("\n") { it.contents }
        val requiredName = "${parentSuffix}_2"

        src shouldContain "fun KxInverseReplayParent.$parentSuffix(first: Long, second: Long): Long {"
        src shouldContain "open fun $parentSuffix(first: Long, second: Long): Long {"
        src shouldContain "fun KxInverseReplayChild.$requiredName(first: Long, second: Long): Long {"
        src.substringAfter("open fun $parentSuffix(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"$childSelector\")"
        src.substringAfter("fun KxInverseReplayChild.$requiredName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"$parentSelector\")"
        compileOnly(
            files,
            """
                package test

                fun callInverseReplaySurface(host: KxInverseReplayChild): Long =
                    host.$parentSuffix(1L, 2L) + host.$requiredName(3L, 4L)
            """.trimIndent(),
        )
    }

    "Class protocol requirements allocate class members in one top-level Kotlin scope" {
        val files = generateAll(
            """
                @protocol KxLeftClassRequirement
                + (long)c:(long)value;
                @property (class, readonly) long p;
                @end
                @interface KxA_B <KxLeftClassRequirement>
                @end

                @interface KxA
                @end
                @interface KxA (KxTopLevelCollision)
                + (long)B_c:(long)value;
                @property (class, readonly) long B_p;
                @end
            """.trimIndent(),
        )
        val src = files.joinToString("\n") { it.contents }
        val rightName = "KxA_B_c__objc_425f633a"
        val rightPropertyName = "KxA_B_p__objc_425f70"

        src shouldContain "fun KxA_B_c(value: Long): Long {"
        src.substringAfter("fun KxA_B_c(").substringBefore("\n}") shouldContain
            "ObjCRuntime.getClass(\"KxA_B\")"
        src.substringAfter("fun KxA_B_c(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"c:\")"
        src shouldContain "fun $rightName(value: Long): Long {"
        src.substringAfter("fun $rightName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.getClass(\"KxA\")"
        src.substringAfter("fun $rightName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"B_c:\")"
        src shouldContain "fun KxA_B_p(): Long {"
        src.substringAfter("fun KxA_B_p(").substringBefore("\n}") shouldContain
            "ObjCRuntime.getClass(\"KxA_B\")"
        src shouldContain "fun $rightPropertyName(): Long {"
        src.substringAfter("fun $rightPropertyName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.getClass(\"KxA\")"
        src.substringAfter("fun $rightPropertyName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"B_p\")"
        compileOnly(
            files,
            """
                package test

                fun callClassRequirementCollision(): Long =
                    KxA_B_c(1L) + $rightName(2L) + KxA_B_p() + $rightPropertyName()
            """.trimIndent(),
        )
    }

    "NSString keyword conveniences use a valid unescaped synthetic base name" {
        val files = generateAll(
            """
                ${kNSStringStub}
                @interface KxKeywordNSStringMethod
                - (NSString *)when;
                @end
                @interface KxKeywordNSStringProperty
                @property (readonly) NSString *when;
                @end
            """.trimIndent(),
        )
        val src = files.joinToString("\n") { it.contents }

        src shouldContain "fun whenAsString(): String = ObjCRuntime.toJavaString(`when`())"
        src shouldNotContain "`when`AsString"
        src.split("ObjCRuntime.sel(\"when\")").size - 1 shouldBe 2
        compileOnly(
            files,
            """
                package test

                import java.lang.foreign.MemorySegment

                fun callKeywordConveniences(
                    methodHost: KxKeywordNSStringMethod,
                    propertyHost: KxKeywordNSStringProperty,
                ): String {
                    val methodRaw: MemorySegment = methodHost.`when`()
                    val propertyRaw: MemorySegment = propertyHost.`when`()
                    return methodHost.whenAsString() + propertyHost.whenAsString() +
                        methodRaw.toString() + propertyRaw.toString()
                }
            """.trimIndent(),
        )
    }

    "Protocol property ownership KDoc is limited to direct Objective-C object references" {
        val files = generateAll(
            """
                ${kNSStringStub}
                @protocol KxObjectProtocol
                @end
                typedef void *KxOpaquePointer;
                typedef void (*KxFunctionPointer)(void);
                typedef void (^KxBlockPointer)(void);
                @protocol KxOwnershipRequirement
                @property (readonly) id<KxObjectProtocol> object;
                @property (readonly) id<KxObjectProtocol> *objectPointer;
                @property (readonly) void *opaque;
                @property (readonly) KxOpaquePointer opaqueAlias;
                @property (readonly) KxFunctionPointer functionPointer;
                @property (readonly) KxBlockPointer blockPointer;
                @end
                @interface KxOwnershipHost <KxOwnershipRequirement>
                @end
            """.trimIndent(),
        )
        val src = files.joinToString("\n") { it.contents }
        val borrowedKDoc = "This getter returns a borrowed (+0) Objective-C reference"
        val objectGetter = "fun KxOwnershipHost.`object`(): MemorySegment"
        val nonObjectGetters = listOf(
            "fun KxOwnershipHost.objectPointer(): MemorySegment",
            "fun KxOwnershipHost.opaque(): MemorySegment",
            "fun KxOwnershipHost.opaqueAlias(): MemorySegment",
            "fun KxOwnershipHost.functionPointer(): MemorySegment",
            "fun KxOwnershipHost.blockPointer(): MemorySegment",
        )

        src.split(borrowedKDoc).size - 1 shouldBe 1
        src shouldContain objectGetter
        src.substringBefore(objectGetter).substringAfterLast("/**") shouldContain borrowedKDoc
        nonObjectGetters.forEach { getter ->
            src shouldContain getter
            src.substringBefore(getter).substringAfterLast("/**") shouldNotContain borrowedKDoc
        }
        compileOnly(
            files,
            """
                package test

                import java.lang.foreign.MemorySegment

                fun consumeOwnershipReferences(host: KxOwnershipHost): List<MemorySegment> = listOf(
                    host.`object`(),
                    host.objectPointer(),
                    host.opaque(),
                    host.opaqueAlias(),
                    host.functionPointer(),
                    host.blockPointer(),
                )
            """.trimIndent(),
        )
    }

    "Unsupported protocol lowering identifies its source protocol and selector" {
        val unsupportedType = Type.primitive(Type.Primitive.Kind.WChar)
        val unsupportedMethod = Declaration.objcMethod(
            Position.NO_POSITION,
            "unsupported",
            "unsupported:",
            true,
            unsupportedType,
            "wchar_t",
            listOf(Declaration.parameter(Position.NO_POSITION, "value", unsupportedType)),
            false,
        )
        val protocol = Declaration.objcProtocol(
            Position.NO_POSITION,
            "KxUnsupportedProtocolRequirement",
            emptyList(),
            listOf(unsupportedMethod),
            emptyList(),
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxUnsupportedProtocolHost",
            null,
            listOf(protocol.name()),
            emptyList(),
            emptyList(),
        )

        val failure = shouldThrow<IllegalStateException> { generateManual(protocol, host) }
        failure.message.orEmpty() shouldContain protocol.name()
        failure.message.orEmpty() shouldContain unsupportedMethod.selector()
        failure.stackTrace.any {
            it.className == "org.graphiks.kextract.kotlin.builders.KotlinObjCProtocolRequirementBuilder"
        } shouldBe true
    }

    "Required protocol extension avoids shadowing by an NSString convenience" {
        val files = generateAll(
            """
                ${kNSStringStub}
                @protocol KxNSStringExtensionRequirement
                - (id)fooAsString;
                @end
                @interface KxNSStringExtensionHost <KxNSStringExtensionRequirement>
                - (NSString *)foo;
                @end
            """.trimIndent(),
        )
        val src = files.joinToString("\n") { it.contents }
        val suffixedName = "fooAsString__objc_666f6f4173537472696e67"

        src shouldContain "fun fooAsString(): String = ObjCRuntime.toJavaString(foo())"
        src shouldContain "fun KxNSStringExtensionHost.$suffixedName(): MemorySegment {"
        src.substringAfter("fun KxNSStringExtensionHost.$suffixedName(").substringBefore("\n}") shouldContain
            "ObjCRuntime.sel(\"fooAsString\")"
        compileOnly(
            files,
            """
                package test

                import java.lang.foreign.MemorySegment

                fun callNSStringAndRequiredExtensions(host: KxNSStringExtensionHost): String {
                    val directString: String = host.fooAsString()
                    val rawExtension: MemorySegment = host.$suffixedName()
                    return directString + rawExtension.toString()
                }
            """.trimIndent(),
        )
    }

    "Direct class-property getter foo and +foo: remain distinct overloads without synthetic accessors" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val value = Declaration.parameter(Position.NO_POSITION, "value", longType)
        val fooWithValue = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo",
            "foo:",
            true,
            longType,
            "long",
            listOf(value),
            false,
        )
        val writableClassProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "foo",
            longType,
            "long",
            isOptional = false,
            isReadOnly = false,
            getterSelector = "foo",
            setterSelector = "setFoo:",
            isClassProperty = true,
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxDirectSelectorHost",
            null,
            emptyList(),
            listOf(fooWithValue),
            listOf(writableClassProperty),
        )

        val src = generateManual(host).joinToString("\n") { it.contents }

        src.split("fun foo(): Long").size - 1 shouldBe 1
        src.split("fun setFoo(value: Long)").size - 1 shouldBe 1
        src.split("fun foo(value: Long): Long").size - 1 shouldBe 1
    }

    "Category class-property getter foo and +foo: remain distinct overloads without synthetic accessors" {
        val longType = Type.primitive(Type.Primitive.Kind.Long)
        val value = Declaration.parameter(Position.NO_POSITION, "value", longType)
        val fooWithValue = Declaration.objcMethod(
            Position.NO_POSITION,
            "foo",
            "foo:",
            true,
            longType,
            "long",
            listOf(value),
            false,
        )
        val writableClassProperty = Declaration.objcProperty(
            Position.NO_POSITION,
            "foo",
            longType,
            "long",
            isOptional = false,
            isReadOnly = false,
            getterSelector = "foo",
            setterSelector = "setFoo:",
            isClassProperty = true,
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxCategorySelectorHost",
            null,
            emptyList(),
            emptyList(),
            emptyList(),
        )
        val category = Declaration.objcCategory(
            Position.NO_POSITION,
            "KxCategorySelectorHostCategory",
            host.name(),
            "Category",
            listOf(fooWithValue),
            listOf(writableClassProperty),
        )

        val src = generateManual(host, category).joinToString("\n") { it.contents }

        src.split("fun KxCategorySelectorHost_foo(): Long").size - 1 shouldBe 1
        src.split("fun KxCategorySelectorHost_setFoo(value: Long)").size - 1 shouldBe 1
        src.split("fun KxCategorySelectorHost_foo(value: Long): Long").size - 1 shouldBe 1
    }

    // ── NS_ENUM / NS_OPTIONS ─────────────────────────────────────────────────

    "NS_ENUM generates Kotlin enum class" - {
        // Use a typed C enum (typedef enum : long { ... }) which is exactly what NS_ENUM expands
        // to after macro expansion.
        val src = generate("""
            typedef enum : long {
                KxOrderAscending  = -1,
                KxOrderSame       = 0,
                KxOrderDescending = 1
            } KxComparisonResult;
        """.trimIndent())

        "enum class is emitted instead of typealias" {
            src shouldContain "enum class KxComparisonResult"
            src shouldNotContain "typealias KxComparisonResult"
        }

        "enum entries carry Long values" {
            src shouldContain "KxOrderAscending(-1L)"
            src shouldContain "KxOrderSame(0L)"
            src shouldContain "KxOrderDescending(1L)"
        }

        "companion object with fromValue factory" {
            src shouldContain "companion object"
            src shouldContain "fun fromValue(v: Long): KxComparisonResult"
        }

        "enum constants not emitted as standalone top-level functions" {
            src shouldNotContain "fun KxOrderAscending()"
        }
    }

    "enum-typed sentinel macro is generated as a usable enum value" - {
        val files = generateAll("""
            typedef enum : unsigned long {
                KxEventNone  = 0,
                KxEventKnown = 1
            } KxEventType;
            #define KxAnyEventType ((KxEventType)4294967295UL)
        """.trimIndent())
        val src = files.joinToString("\n") { it.contents }

        "missing numeric value enriches the enum" {
            src shouldContain "KxAnyEventType(4294967295L)"
        }

        "macro function boxes through fromValue" {
            src shouldContain "fun KxAnyEventType(): KxEventType = KxEventType.fromValue(4294967295L)"
        }

        "generated value compiles and can be invoked" {
            compileAndInvokeLong(
                files,
                """
                    package test
                    fun readAnyEventType(): Long = KxAnyEventType().value
                """.trimIndent(),
                "readAnyEventType",
            ) shouldBe 4294967295L
        }
    }

    "enum-typed macro edge cases" - {
        "matching a declared value does not add a duplicate entry" {
            val src = generate("""
                typedef enum : long { KxKnown = 1 } KxAliasType;
                #define KxKnownAlias ((KxAliasType)1)
            """.trimIndent())
            src shouldNotContain "KxKnownAlias(1L)"
            src shouldContain "fun KxKnownAlias(): KxAliasType = KxAliasType.fromValue(1L)"
        }

        "multiple external macros sharing a value add one canonical entry" {
            val src = generate("""
                typedef enum : long { KxBase = 1 } KxSharedType;
                #define KxExternalFirst ((KxSharedType)42)
                #define KxExternalSecond ((KxSharedType)42)
            """.trimIndent())
            src shouldContain "KxExternalFirst(42L)"
            src shouldNotContain "KxExternalSecond(42L)"
            src shouldContain "fun KxExternalFirst(): KxSharedType = KxSharedType.fromValue(42L)"
            src shouldContain "fun KxExternalSecond(): KxSharedType = KxSharedType.fromValue(42L)"
        }

        "same name with another value receives a deterministic suffix" {
            val src = generate("""
                typedef enum : long { KxCollision = 1 } KxCollisionType;
                #define KxCollision ((KxCollisionType)2)
            """.trimIndent())
            src shouldContain "KxCollision(1L)"
            src shouldContain "KxCollision_kextract1(2L)"
            src shouldContain "fun KxCollision(): KxCollisionType = KxCollisionType.fromValue(2L)"
        }

        "options-style macros use the value-class constructor" {
            val src = generate("""
                typedef enum __attribute__((flag_enum)) : long {
                    KxFeatureA = 1,
                    KxFeatureB = 2
                } KxFeatureOptions;
                #define KxCombinedFeatures ((KxFeatureOptions)3)
            """.trimIndent())
            src shouldContain "fun KxCombinedFeatures(): KxFeatureOptions = KxFeatureOptions(3L)"
            src shouldNotContain "KxCombinedFeatures(3L)"
        }

        "primitive macro generation remains unchanged" {
            val src = generate("#define KX_ANSWER 42")
            src shouldContain "fun KX_ANSWER(): Int = (42).toInt()"
        }

        "regular enum without external macros keeps its exact declaration" {
            val src = generate("""
                typedef enum : long {
                    KxStableZero = 0,
                    KxStableOne = 1
                } KxStableType;
            """.trimIndent())
            val historicalDeclaration = """
                enum class KxStableType(val value: Long) {
                    KxStableZero(0L), KxStableOne(1L);
                <INDENTED_BLANK_LINE>
                    companion object {
                        fun fromValue(v: Long): KxStableType = entries.firstOrNull { it.value == v }
                            ?: error("Unknown KxStableType value: ${'$'}v")
                    }
                }
            """.trimIndent().replace("<INDENTED_BLANK_LINE>", "    ")
            src shouldContain historicalDeclaration
        }
    }

    "enum-typed macros through the full generation pipeline" - {
        "an enum member and same-name macro with another value both survive" {
            val files = generateWithPipeline("""
                typedef enum : long {
                    KxPipelineCollision = 1
                } KxPipelineCollisionType;
                #define KxPipelineCollision ((KxPipelineCollisionType)2)
            """.trimIndent())
            val src = files.joinToString("\n") { it.contents }

            src shouldContain "KxPipelineCollision(1L), KxPipelineCollision_kextract1(2L);"
            src shouldContain "fun KxPipelineCollision(): KxPipelineCollisionType = KxPipelineCollisionType.fromValue(2L)"
            compileAndInvokeLong(
                files,
                """
                    package test
                    fun readPipelineCollision(): Long = KxPipelineCollision().value
                """.trimIndent(),
                "readPipelineCollision",
            ) shouldBe 2L
        }

        "an enum member and exact same-name same-value macro emit only the member" {
            val files = generateWithPipeline("""
                typedef enum : long {
                    KxPipelineExactDuplicate = 1
                } KxPipelineExactDuplicateType;
                #define KxPipelineExactDuplicate ((KxPipelineExactDuplicateType)1)
            """.trimIndent())
            val src = files.joinToString("\n") { it.contents }

            Regex("KxPipelineExactDuplicate\\(1L\\)").findAll(src).count() shouldBe 1
            src shouldNotContain "fun KxPipelineExactDuplicate()"
        }

        "a same-name value in another enum does not suppress the target enum macro" {
            val files = generateWithPipeline("""
                typedef enum : long {
                    KxCrossEnumShared = 1
                } KxCrossEnumFirst;
                typedef enum : long {
                    KxCrossEnumSecondBase = 0
                } KxCrossEnumSecond;
                #define KxCrossEnumShared ((KxCrossEnumSecond)1)
            """.trimIndent())
            val src = files.joinToString("\n") { it.contents }

            src shouldContain "KxCrossEnumSecondBase(0L), KxCrossEnumShared(1L);"
            src shouldContain "fun KxCrossEnumShared(): KxCrossEnumSecond = KxCrossEnumSecond.fromValue(1L)"
        }

        "an anonymous enum macro stays on the literal path" {
            val files = generateWithPipeline("""
                #define KxAnonymousMacro ((enum { KxAnonymousInline = 1 })2)
            """.trimIndent())
            val src = files.joinToString("\n") { it.contents }

            src shouldContain "fun KxAnonymousMacro()"
            src shouldNotContain ".fromValue("
        }

        "a pointer-to-enum macro is not boxed as an enum" {
            val files = generateWithPipeline("""
                typedef enum : long {
                    KxPointerBase = 1
                } KxPointerType;
                typedef KxPointerType *KxPointerAlias;
                #define KxPointerMacro ((KxPointerAlias)0)
            """.trimIndent())
            val src = files.joinToString("\n") { it.contents }

            src shouldNotContain "fun KxPointerMacro()"
            src shouldNotContain "KxPointerType.fromValue("
            src shouldNotContain "MemorySegment.fromValue("
        }

        "a generated enum and macro share the mangled Kotlin type name" {
            val files = generateWithPipeline("""
                typedef enum : long {
                    KxStringBase = 1
                } String;
                #define KxStringExtra ((String)2)
            """.trimIndent())
            val src = files.joinToString("\n") { it.contents }

            src shouldContain "enum class String_"
            src shouldContain "fun KxStringExtra(): String_ = String_.fromValue(2L)"
            compileAndInvokeLong(
                files,
                """
                    package test
                    fun readMangledEnumMacro(): Long = KxStringExtra().value
                """.trimIndent(),
                "readMangledEnumMacro",
            ) shouldBe 2L
        }

        "a dollar enum name is boxed with its mangled Kotlin type" {
            val files = generateWithPipeline("""
                typedef enum : long {
                    KxDollarBase = 1
                } Kx${'$'}Dollar;
                #define KxDollarExtra ((Kx${'$'}Dollar)2)
            """.trimIndent())
            val src = files.joinToString("\n") { it.contents }

            src shouldContain "enum class Kx_Dollar"
            src shouldContain "KxDollarBase(1L), KxDollarExtra(2L);"
            src shouldContain "fun KxDollarExtra(): Kx_Dollar = Kx_Dollar.fromValue(2L)"
            compileAndInvokeLong(
                files,
                """
                    package test
                    fun readDollarEnumMacro(): Long = KxDollarExtra().value
                """.trimIndent(),
                "readDollarEnumMacro",
            ) shouldBe 2L
        }

        "a Unicode enum name is boxed with its mangled Kotlin type" {
            val files = generateWithPipeline("""
                typedef enum : long {
                    KxUnicodeBase = 3
                } KxÉtat;
                #define KxUnicodeExtra ((KxÉtat)4)
            """.trimIndent())
            val src = files.joinToString("\n") { it.contents }

            src shouldContain "enum class Kx_tat"
            src shouldContain "KxUnicodeBase(3L), KxUnicodeExtra(4L);"
            src shouldContain "fun KxUnicodeExtra(): Kx_tat = Kx_tat.fromValue(4L)"
            compileAndInvokeLong(
                files,
                """
                    package test
                    fun readUnicodeEnumMacro(): Long = KxUnicodeExtra().value
                """.trimIndent(),
                "readUnicodeEnumMacro",
            ) shouldBe 4L
        }
    }

    "manual enum identity resolution" - {
        "an exact skipped homonym is never redirected to a generated enum" {
            val generatedEnum = Declaration.enum_(
                Position.NO_POSITION,
                "KxSkippedHomonym",
                enumConstant("KxGeneratedBase", 1),
            )
            val skippedEnum = Declaration.enum_(
                Position.NO_POSITION,
                "KxSkippedHomonym",
                enumConstant("KxSkippedBase", 2),
            )
            markSkipped(skippedEnum)
            val macro = Declaration.constant(
                Position.NO_POSITION,
                "KxSkippedExactMacro",
                3L,
                Type.declared(skippedEnum),
            )

            val src = generateManual(generatedEnum, skippedEnum, macro)
                .joinToString("\n") { it.contents }

            src shouldNotContain "KxSkippedExactMacro(3L)"
            src shouldNotContain "KxSkippedHomonym.fromValue(3L)"
        }

        "a portable reparse homonym is unresolved when top-level candidates are ambiguous" {
            val firstEnum = Declaration.enum_(
                Position.NO_POSITION,
                "KxAmbiguousHomonym",
                enumConstant("KxAmbiguousFirst", 1),
            )
            val secondEnum = Declaration.enum_(
                Position.NO_POSITION,
                "KxAmbiguousHomonym",
                enumConstant("KxAmbiguousSecond", 2),
            )
            val reparsedEnum = Declaration.enum_(Position.NO_POSITION, "KxAmbiguousHomonym")
            val macro = Declaration.constant(
                Position.NO_POSITION,
                "KxAmbiguousMacro",
                3L,
                Type.declared(reparsedEnum),
            )

            val src = generateManual(firstEnum, secondEnum, macro)
                .joinToString("\n") { it.contents }

            src shouldNotContain "KxAmbiguousMacro(3L)"
            src shouldNotContain "KxAmbiguousHomonym.fromValue(3L)"
        }

        "a detached identity resolves the only generated homonym beside a skipped one" {
            val generatedEnum = Declaration.enum_(
                Position.NO_POSITION,
                "KxDetachedHomonym",
                enumConstant("KxDetachedGeneratedBase", 1),
            )
            val skippedEnum = Declaration.enum_(
                Position.NO_POSITION,
                "KxDetachedHomonym",
                enumConstant("KxDetachedSkippedBase", 2),
            )
            markSkipped(skippedEnum)
            val detachedEnum = Declaration.enum_(Position.NO_POSITION, "KxDetachedHomonym")
            val macro = Declaration.constant(
                Position.NO_POSITION,
                "KxDetachedMacro",
                3L,
                Type.declared(detachedEnum),
            )

            val src = generateManual(generatedEnum, skippedEnum, macro)
                .joinToString("\n") { it.contents }

            src shouldContain "KxDetachedMacro(3L)"
            src shouldContain
                "fun KxDetachedMacro(): KxDetachedHomonym = KxDetachedHomonym.fromValue(3L)"
        }

        "an exact generated homonym receives enrichment only on that identity" {
            val targetEnum = Declaration.enum_(
                Position.NO_POSITION,
                "KxExactHomonym",
                enumConstant("KxExactTargetBase", 1),
            )
            val otherEnum = Declaration.enum_(
                Position.NO_POSITION,
                "KxExactHomonym",
                enumConstant("KxExactOtherBase", 2),
            )
            val macro = Declaration.constant(
                Position.NO_POSITION,
                "KxExactMacro",
                3L,
                Type.declared(targetEnum),
            )

            val src = generateManual(targetEnum, otherEnum, macro)
                .joinToString("\n") { it.contents }

            Regex("KxExactMacro\\(3L\\)").findAll(src).count() shouldBe 1
            src shouldContain "fun KxExactMacro(): KxExactHomonym = KxExactHomonym.fromValue(3L)"
        }
    }

    "NS_OPTIONS generates @JvmInline value class" - {
        val src = generate("""
            typedef enum __attribute__((flag_enum)) : long {
                KxNone              = 0,
                KxCaseInsensitive   = 1,
                KxLiteral           = 2,
                KxBackwards         = 4
            } KxStringCompareOptions;
        """.trimIndent())

        "value class with rawValue is emitted" {
            src shouldContain "@JvmInline"
            src shouldContain "value class KxStringCompareOptions(val rawValue: Long)"
        }

        "constants are in companion object" {
            src shouldContain "companion object"
            src shouldContain "val KxNone = KxStringCompareOptions(0L)"
            src shouldContain "val KxCaseInsensitive = KxStringCompareOptions(1L)"
        }

        "bit-ops are emitted" {
            src shouldContain "operator fun plus(o: KxStringCompareOptions)"
            src shouldContain "operator fun contains(o: KxStringCompareOptions)"
        }

        "no typealias for options type" {
            src shouldNotContain "typealias KxStringCompareOptions"
        }
    }

    "packed pointer-only Objective-C records compile as opaque nominal addresses" {
        val files = generateAll("""
            typedef struct __attribute__((packed)) KxPackedPointerOnly {
                unsigned int tag;
                void *payload;
            } KxPackedPointerOnly;
            typedef const KxPackedPointerOnly *KxPackedPointerOnlyRef;
            @interface KxPackedPointerHost
            - (void)consume:(KxPackedPointerOnlyRef)value;
            @end
        """.trimIndent())

        compileOnly(
            files,
            """
                package test

                fun consumePackedPointer(
                    host: KxPackedPointerHost,
                    value: KxPackedPointerOnlyRef,
                ) = host.consume(value)
            """.trimIndent(),
        )
    }

    "filtered incomplete Objective-C pointer records compile as opaque nominal addresses" {
        val files = generateWithPipeline("""
            struct _KxZone;
            typedef struct _KxZone KxZone;
            struct _KxZone;
            typedef struct KxContext *KxContextRef;
            @interface KxFilteredOpaqueHost
            - (void)consumeZone:(KxZone *)zone context:(KxContextRef)context;
            @end
        """.trimIndent())

        compileOnly(
            files,
            """
                package test

                fun consumeFilteredOpaque(
                    host: KxFilteredOpaqueHost,
                    zone: KxZonePointer,
                    context: KxContextRef,
                ) = host.consumeZone_context(zone, context)
            """.trimIndent(),
        )
    }

    "repeated incomplete Objective-C record declarations emit one nominal pointer wrapper" {
        val first = Declaration.struct(Position.NO_POSITION, "KxRepeatedOpaque")
        val second = Declaration.struct(Position.NO_POSITION, "KxRepeatedOpaque")
        val third = Declaration.struct(Position.NO_POSITION, "KxRepeatedOpaque")
        val parameter = Declaration.parameter(
            Position.NO_POSITION,
            "value",
            Type.pointer(Type.declared(first)),
        )
        val method = Declaration.objcMethod(
            Position.NO_POSITION,
            "consume",
            "consume:",
            false,
            Type.void_(),
            "void",
            listOf(parameter),
            false,
        )
        val host = Declaration.objcClass(
            Position.NO_POSITION,
            "KxRepeatedOpaqueHost",
            null,
            emptyList(),
            listOf(method),
            emptyList(),
        )
        val files = generateManual(first, second, third, host)

        compileOnly(
            files,
            """
                package test

                fun consumeRepeatedOpaque(
                    host: KxRepeatedOpaqueHost,
                    value: KxRepeatedOpaquePointer,
                ) = host.consume(value)
            """.trimIndent(),
        )
    }

    "distinct anonymous Objective-C records keep separate pointer identities" {
        val files = generateAll("""
            typedef struct { int marker; } KxFirstAnonymous;
            typedef struct { double marker; } KxSecondAnonymous;
            @interface KxAnonymousRecordHost
            - (void)consumeFirst:(KxFirstAnonymous *)first second:(KxSecondAnonymous *)second;
            @end
        """.trimIndent())

        compileOnly(
            files,
            """
                package test

                fun consumeAnonymousRecords(
                    host: KxAnonymousRecordHost,
                    first: KxFirstAnonymousPointer,
                    second: KxSecondAnonymousPointer,
                ) = host.consumeFirst_second(first, second)
            """.trimIndent(),
        )
    }

    "single-segment Objective-C value records compile without constructor collisions" {
        val files = generateAll("""
            typedef struct KxSingleSegmentValue {
                void *payload;
            } KxSingleSegmentValue;
            @interface KxSingleSegmentValueHost
            - (KxSingleSegmentValue)roundTrip:(KxSingleSegmentValue)value;
            @end
        """.trimIndent())

        compileOnly(
            files,
            """
                package test

                import java.lang.foreign.MemorySegment
                import java.lang.foreign.SegmentAllocator

                fun allocateSingleSegmentValue(
                    allocator: SegmentAllocator,
                ): KxSingleSegmentValue =
                    KxSingleSegmentValue.allocate(allocator).apply {
                        payload = MemorySegment.NULL
                    }
            """.trimIndent(),
        )
    }

    "C by-value records retain their full API when Objective-C also uses a pointer" {
        val files = generateAll("""
            typedef struct KxMixedValue {
                unsigned int tag;
                void *payload;
            } KxMixedValue;
            KxMixedValue KxRoundTripMixedValue(KxMixedValue value);
            @interface KxMixedValueHost
            - (void)consume:(const KxMixedValue *)value;
            @end
        """.trimIndent())

        compileOnly(
            files,
            """
                package test

                import java.lang.foreign.MemorySegment
                import java.lang.foreign.SegmentAllocator

                fun roundTripMixedValue(
                    host: KxMixedValueHost,
                    allocator: SegmentAllocator,
                ): KxMixedValue {
                    val value = KxMixedValue(tag = 7, payload = MemorySegment.NULL)
                    val pointer = KxMixedValue.allocateArray(1L, allocator)
                    pointer.pointed().tag = value.tag
                    host.consume(pointer)
                    return KxRoundTripMixedValue(allocator, value)
                }
            """.trimIndent(),
        )
    }

    "legacy C record fields retain layouts for Objective-C pointer records" {
        val files = generateAll("""
            typedef struct KxNestedValue {
                long value;
            } KxNestedValue;
            typedef struct KxLegacyContainer {
                KxNestedValue nested;
            } KxLegacyContainer;
            @interface KxNestedValueHost
            - (void)consume:(const KxNestedValue *)value;
            @end
        """.trimIndent())

        compileOnly(
            files,
            """
                package test

                import java.lang.foreign.MemorySegment
                import java.lang.foreign.SegmentAllocator

                fun readNestedValue(
                    host: KxNestedValueHost,
                    allocator: SegmentAllocator,
                    pointer: KxNestedValuePointer,
                ): Long {
                    val container = KxLegacyContainer.allocate(allocator)
                    val nested: MemorySegment = KxLegacyContainer().nested(container)
                    host.consume(pointer)
                    return KxNestedValue(value = 11L).value + nested.byteSize()
                }
            """.trimIndent(),
        )
    }

    "late pointer promotion is independent of C declaration order" - {
        val prefix = """
            typedef struct KxLatePointer {
                long value;
            } KxLatePointer;
            typedef struct KxPromotedRoot {
                KxLatePointer *late;
            } KxPromotedRoot;
        """.trimIndent()
        val legacyContainer = """
            typedef struct KxEarlierLegacy {
                KxLatePointer late;
            } KxEarlierLegacy;
        """.trimIndent()
        val cValueUse =
            "KxPromotedRoot KxRoundTripPromotedRoot(KxPromotedRoot value);"
        val objcSurface = """
            @interface KxOrderHost
            - (void)consume:(const KxPromotedRoot *)value;
            @end
        """.trimIndent()
        val permutations = listOf(
            "legacy field before C value promotion" to
                listOf(prefix, legacyContainer, cValueUse, objcSurface).joinToString("\n"),
            "C value promotion before legacy field" to
                listOf(prefix, cValueUse, legacyContainer, objcSurface).joinToString("\n"),
        )

        for ((order, source) in permutations) {
            "$order compiles" {
                val files = generateAll(source)
                val src = files.joinToString("\n") { it.contents }
                src shouldContain "class KxLatePointer internal constructor"
                src shouldContain "val layout: GroupLayout"
                compileOnly(
                    files,
                    """
                        package test

                        import java.lang.foreign.SegmentAllocator

                        fun readLateValue(
                            allocator: SegmentAllocator,
                            value: KxLatePointer,
                        ): Long {
                            val container = KxEarlierLegacy.allocate(allocator)
                            return value.value + container.byteSize()
                        }
                    """.trimIndent(),
                )
            }
        }
    }

    "packed pointer-only classification is independent of C declaration order" - {
        val prefix = """
            typedef struct __attribute__((packed)) KxOrderPacked {
                unsigned int tag;
                void *payload;
            } KxOrderPacked;
            typedef struct KxOrderContainer KxOrderContainer;
            typedef struct KxOrderRoot {
                KxOrderContainer *container;
            } KxOrderRoot;
        """.trimIndent()
        val container = """
            struct KxOrderContainer {
                KxOrderPacked packed;
            };
        """.trimIndent()
        val cValueUse = "KxOrderRoot KxRoundTripOrderRoot(KxOrderRoot value);"
        val objcSurface = """
            @interface KxPackedOrderHost
            - (void)consumePacked:(const KxOrderPacked *)packed;
            - (void)consumeRoot:(const KxOrderRoot *)root;
            @end
        """.trimIndent()
        val permutations = listOf(
            "container before C value promotion" to
                listOf(prefix, container, cValueUse, objcSurface).joinToString("\n"),
            "C value promotion before container" to
                listOf(prefix, cValueUse, container, objcSurface).joinToString("\n"),
        )

        for ((order, source) in permutations) {
            "$order stays opaque and compiles" {
                val files = generateAll(source)
                val src = files.joinToString("\n") { it.contents }
                src shouldContain
                    "class KxOrderPackedPointer internal constructor(internal val segment: MemorySegment)"
                src shouldNotContain "class KxOrderPacked internal constructor"
                compileOnly(
                    files,
                    """
                        package test

                        fun consumePackedOrder(
                            host: KxPackedOrderHost,
                            packed: KxOrderPackedPointer,
                            root: KxOrderRootPointer,
                        ) {
                            host.consumePacked(packed)
                            host.consumeRoot(root)
                        }
                    """.trimIndent(),
                )
            }
        }
    }

    "distinct record tags compile through the nominal opaque pointer alias" {
        val files = generateAll("""
            typedef struct __KxOpaqueTag {
                unsigned int tag;
                void *payload;
            } KxOpaque;
            @interface KxOpaqueHost
            - (void)consume:(const KxOpaque *)value;
            @end
        """.trimIndent())

        compileOnly(
            files,
            """
                package test

                fun consumeOpaque(host: KxOpaqueHost, value: KxOpaquePointer) =
                    host.consume(value)
            """.trimIndent(),
        )
    }

    "Objective-C semantic wrappers compile and represent unknown values" - {
        val files = generateAll("""
            #define NS_ENUM(_type, _name) \
                enum _name : _type _name; enum _name : _type
            #define NS_OPTIONS(_type, _name) \
                enum __attribute__((flag_enum)) _name : _type _name; \
                enum __attribute__((flag_enum)) _name : _type

            typedef unsigned long NSUInteger;
            typedef struct KxPoint {
                double x;
                double y;
            } KxPoint;
            typedef struct KxRect {
                KxPoint origin;
                KxPoint size;
            } KxRect;
            typedef struct _KxRange {
                NSUInteger location;
                NSUInteger length;
            } KxRange;
            typedef KxRange *KxRangePointer;
            typedef NS_ENUM(long, KxOpenMode) { KxOpenModeKnown = 1 };
            typedef NS_OPTIONS(unsigned long, KxOpenFlags) { KxOpenFlagsKnown = 1 };
            typedef NS_ENUM(long, KxFieldMode) { KxFieldModeKnown = 2 };
            typedef union KxPayload {
                long integer;
                double decimal;
            } KxPayload;
            typedef struct KxSemanticRecord {
                KxFieldMode mode;
                KxRangePointer rangePointer;
                KxPayload payload;
                unsigned char bytes[8];
            } KxSemanticRecord;
            typedef struct KxPaddedRecord {
                unsigned char tag;
                NSUInteger payload;
                unsigned char tail;
            } KxPaddedRecord;
            typedef struct KxBitfieldRecord {
                unsigned int low : 3;
                unsigned int high : 5;
                NSUInteger payload;
                unsigned char tail;
            } KxBitfieldRecord;
            typedef struct KxTwoDoubles {
                double first;
                double second;
            } KxTwoDoubles;
            typedef struct KxFourDoubles {
                double first;
                double second;
                double third;
                double fourth;
            } KxFourDoubles;

            KxRange NSUnionRange(KxRange lhs, KxRange rhs);
            KxRange NSIntersectionRange(KxRange lhs, KxRange rhs);
            KxRange KxCurrentRange(void);

            @interface KxSemanticConsumer
            - (KxRect)transformRange:(KxRange)range point:(KxPoint)point pointer:(KxRangePointer)pointer;
            - (KxOpenMode)modeForFlags:(KxOpenFlags)flags;
            - (KxSemanticRecord)transformRecord:(KxSemanticRecord)record;
            - (KxPaddedRecord)transformPadded:(KxPaddedRecord)record;
            - (KxBitfieldRecord)transformBitfield:(KxBitfieldRecord)record;
            - (KxTwoDoubles)smallStructReturn;
            - (KxFourDoubles)largeStructReturn;
            @end
        """.trimIndent())

        "unknown enum raw values are constructible without throwing" {
            compileAndInvokeLong(
                files,
                """
                    package test
                    fun readUnknownMode(): Long = KxOpenMode(4_294_967_299L).rawValue
                """.trimIndent(),
                "readUnknownMode",
            ) shouldBe 4_294_967_299L
        }

        "asymmetric struct values survive named construction and typed access" {
            compileAndInvokeLong(
                files,
                """
                    package test

                    fun readAsymmetricRange(): Long {
                        val point = KxPoint(x = 13.0, y = -7.0)
                        val rect = KxRect(
                            origin = KxPoint(x = 17.0, y = -3.0),
                            size = KxPoint(x = 5.0, y = 11.0),
                        )
                        val range = KxRange(location = 7L, length = 13L)
                        val pointCode = (point.x * 100.0 - point.y).toLong()
                        val rectCode = (
                            rect.origin.x * 100.0 - rect.origin.y +
                                rect.size.x * 10.0 + rect.size.y
                            ).toLong()
                        return pointCode * 1_000_000L + rectCode * 1_000L +
                            range.location * 100L + range.length
                    }
                """.trimIndent(),
                "readAsymmetricRange",
            ) shouldBe 1_308_764_713L
        }

        "enum pointer union and array fields survive named construction and typed access" {
            compileAndInvokeLong(
                files,
                """
                    package test

                    import java.lang.foreign.Arena
                    import java.lang.foreign.ValueLayout

                    fun readCompositeRecord(): Long {
                        val arena = Arena.ofAuto()
                        val rangePointer = KxRange.allocateArray(1L, arena)
                        rangePointer.pointed().apply {
                            location = 7L
                            length = 13L
                        }
                        val payload = arena.allocate(KxPayload.layout)
                        payload.set(ValueLayout.JAVA_LONG, 0L, 101L)
                        val bytes = arena.allocate(8L)
                        bytes.set(ValueLayout.JAVA_BYTE, 0L, 3.toByte())
                        bytes.set(ValueLayout.JAVA_BYTE, 7L, 5.toByte())

                        val record = KxSemanticRecord(
                            mode = KxFieldMode(37L),
                            rangePointer = rangePointer,
                            payload = payload,
                            bytes = bytes,
                        )
                        return record.mode.rawValue * 1_000_000L +
                            record.rangePointer.pointed().location * 10_000L +
                            record.rangePointer.pointed().length * 100L +
                            record.payload.get(ValueLayout.JAVA_LONG, 0L) +
                            record.bytes.get(ValueLayout.JAVA_BYTE, 0L) * 10L +
                            record.bytes.get(ValueLayout.JAVA_BYTE, 7L)
                    }
                """.trimIndent(),
                "readCompositeRecord",
            ) shouldBe 37_071_436L
        }

        "a source shorter than an array field fails with the FFM bounds exception" {
            compileAndInvokeLong(
                files,
                """
                    package test

                    import java.lang.foreign.Arena

                    fun shortArraySourceIsRejected(): Long {
                        val arena = Arena.ofAuto()
                        val record = KxSemanticRecord.allocate(arena)
                        val sevenBytes = arena.allocate(7L)
                        return try {
                            record.bytes = sevenBytes
                            0L
                        } catch (_: IndexOutOfBoundsException) {
                            1L
                        }
                    }
                """.trimIndent(),
                "shortArraySourceIsRejected",
            ) shouldBe 1L
        }

        "an oversized union source copies only its prefix and preserves the adjacent array" {
            compileAndInvokeLong(
                files,
                """
                    package test

                    import java.lang.foreign.Arena
                    import java.lang.foreign.ValueLayout

                    fun oversizedUnionCopyIsBounded(): Long {
                        val arena = Arena.ofAuto()
                        val record = KxSemanticRecord.allocate(arena)
                        val arraySentinels = arena.allocate(8L)
                        arraySentinels.set(ValueLayout.JAVA_BYTE, 0L, 41.toByte())
                        arraySentinels.set(ValueLayout.JAVA_BYTE, 7L, 43.toByte())
                        record.bytes = arraySentinels

                        val oversizedPayload = arena.allocate(16L)
                        oversizedPayload.set(ValueLayout.JAVA_LONG, 0L, 101L)
                        oversizedPayload.set(ValueLayout.JAVA_BYTE, 8L, 91.toByte())
                        oversizedPayload.set(ValueLayout.JAVA_BYTE, 15L, 97.toByte())
                        record.payload = oversizedPayload

                        return record.payload.get(ValueLayout.JAVA_LONG, 0L) * 10_000L +
                            record.bytes.get(ValueLayout.JAVA_BYTE, 0L) * 100L +
                            record.bytes.get(ValueLayout.JAVA_BYTE, 7L)
                    }
                """.trimIndent(),
                "oversizedUnionCopyIsBounded",
            ) shouldBe 1_014_143L
        }

        "Clang offsets drive intermediate and trailing padding while bitfields stay opaque" {
            compileAndInvokeLong(
                files,
                """
                    package test

                    import java.lang.foreign.MemoryLayout.PathElement.groupElement

                    fun readPaddedLayouts(): Long {
                        val padded = KxPaddedRecord(tag = 3.toByte(), payload = 101L, tail = 5.toByte())
                        val paddedCode = KxPaddedRecord.byteSize * 1_000_000L +
                            KxPaddedRecord.layout.byteOffset(groupElement("payload")) * 10_000L +
                            KxPaddedRecord.layout.byteOffset(groupElement("tail")) * 100L +
                            padded.tag * 100L + padded.payload * 10L + padded.tail

                        val bitfield = KxBitfieldRecord(payload = 211L, tail = 7.toByte())
                        val bitfieldCode = KxBitfieldRecord.byteSize * 1_000_000L +
                            KxBitfieldRecord.layout.byteOffset(groupElement("payload")) * 10_000L +
                            KxBitfieldRecord.layout.byteOffset(groupElement("tail")) * 100L +
                            bitfield.payload * 10L + bitfield.tail
                        return paddedCode * 100_000_000L + bitfieldCode
                    }
                """.trimIndent(),
                "readPaddedLayouts",
            ) shouldBe 2_408_291_524_083_717L
        }

        "layout classifier selects regular and stret x86_64 struct returns" {
            compileAndInvokeLong(
                files,
                """
                    package test

                    import java.lang.foreign.MemoryLayout
                    import java.lang.foreign.ValueLayout

                    fun classifyStructReturns(): Long {
                        val smallX86 = objcStructReturnUsesStret(KxTwoDoubles.layout, "x86_64")
                        val largeX86 = objcStructReturnUsesStret(KxFourDoubles.layout, "x86_64")
                        val largeAmd64 = objcStructReturnUsesStret(KxFourDoubles.layout, "amd64")
                        val largeArm = objcStructReturnUsesStret(KxFourDoubles.layout, "aarch64")
                        val compactUnion = MemoryLayout.unionLayout(
                            ValueLayout.JAVA_BYTE,
                            ValueLayout.JAVA_LONG,
                        )
                        val unionX86 = objcStructReturnUsesStret(compactUnion, "x86_64")
                        return (if (smallX86) 1_000L else 0L) +
                            (if (largeX86) 100L else 0L) +
                            (if (largeAmd64) 10L else 0L) +
                            (if (largeArm) 1L else 0L) +
                            (if (unionX86) 10_000L else 0L)
                    }
                """.trimIndent(),
                "classifyStructReturns",
            ) shouldBe 110L
        }

        "typed C adapters compile for consumer calls while raw carriers remain callable" {
            compileOnly(
                files,
                """
                    package test

                    import java.lang.foreign.MemorySegment
                    import java.lang.foreign.SegmentAllocator

                    fun typedSharedCalls(allocator: SegmentAllocator, lhs: KxRange, rhs: KxRange): KxRange {
                        val union: KxRange = NSUnionRange(allocator, lhs, rhs)
                        val intersection: KxRange = NSIntersectionRange(allocator, lhs, rhs)
                        val current: KxRange = KxCurrentRangeTyped(allocator)
                        return KxRange(
                            location = union.location + current.location,
                            length = intersection.length,
                        )
                    }

                    fun rawSharedCall(
                        allocator: SegmentAllocator,
                        lhs: MemorySegment,
                        rhs: MemorySegment,
                    ): MemorySegment = NSUnionRange(allocator, lhs, rhs)
                """.trimIndent(),
            )
        }
    }

    "Apple Clang x86_64 uses regular msgSend for 16 bytes and stret for 32 bytes" {
        val assembly = compileX86ObjCAssembly("""
            typedef struct { double first; double second; } KxTwoDoubles;
            typedef struct { double first; double second; double third; double fourth; } KxFourDoubles;

            @interface KxReturnHost
            - (KxTwoDoubles)smallStructReturn;
            - (KxFourDoubles)largeStructReturn;
            @end

            KxTwoDoubles kxCallSmall(KxReturnHost *host) {
                return [host smallStructReturn];
            }

            KxFourDoubles kxCallLarge(KxReturnHost *host) {
                return [host largeStructReturn];
            }
        """.trimIndent())

        val smallBody = Regex("_kxCallSmall:[^\\n]*\\n([\\s\\S]*?)\\.cfi_endproc")
            .find(assembly)?.groupValues?.get(1) ?: error("missing kxCallSmall assembly:\n$assembly")
        val largeBody = Regex("_kxCallLarge:[^\\n]*\\n([\\s\\S]*?)\\.cfi_endproc")
            .find(assembly)?.groupValues?.get(1) ?: error("missing kxCallLarge assembly:\n$assembly")
        smallBody shouldContain "_objc_msgSend"
        smallBody shouldNotContain "_objc_msgSend_stret"
        largeBody shouldContain "_objc_msgSend_stret"
    }

    "FlagEnum attribute generates value class without a naming convention" - {
        val src = generate("""
            typedef enum __attribute__((flag_enum)) : long {
                KxEventNone  = 0,
                KxEventClick = 1,
                KxEventHover = 2
            } KxEventBits;
        """.trimIndent())

        "value class emitted for semantic options" {
            src shouldContain "@JvmInline"
            src shouldContain "value class KxEventBits(val rawValue: Long)"
        }
    }

    // ── NSString combined overloads (return + param) ──────────────────────────

    "NSString method with both NSString return and NSString param" - {
        // Regression: previously the AsString overload called the base method
        // with zero args, causing a Kotlin compile error.
        val src = generate("""
            ${kNSStringStub}
            @interface KxNSStringCombo
            - (NSString *)transform:(NSString *)input;
            @end
        """.trimIndent())

        "raw base method is emitted (MemorySegment params and return)" {
            src shouldContain "fun transform(input: MemorySegment): MemorySegment"
        }

        "AsString overload with raw MemorySegment param is emitted" {
            // Overload 1: raw param, String return
            src shouldContain "fun transformAsString(input: MemorySegment): String = ObjCRuntime.toJavaString(transform(input))"
        }

        "String param overload returning MemorySegment is emitted" {
            // Overload 2: String param, raw return
            src shouldContain "fun transform(input: String): MemorySegment = transform(ObjCRuntime.newNSString(Arena.global(), input))"
        }

        "combined overload with String param and String return is emitted" {
            // Overload 3: combined
            src shouldContain "fun transformAsString(input: String): String = ObjCRuntime.toJavaString(transform(ObjCRuntime.newNSString(Arena.global(), input)))"
        }
    }

    // ── Enum fromValue safe fallback ──────────────────────────────────────────

    "NS_ENUM fromValue handles unknown values gracefully" - {
        val src = generate("""
            typedef enum : long {
                KxStatusOk    = 0,
                KxStatusError = 1
            } KxStatus;
        """.trimIndent())

        "fromValue uses firstOrNull instead of first" {
            src shouldContain "firstOrNull"
            src shouldNotContain "entries.first {"
        }

        "fromValue emits descriptive error for unknown values" {
            src shouldContain "error("
            src shouldContain "Unknown KxStatus value"
        }
    }

    // ── ObjCRuntime toJavaString null safety ──────────────────────────────────

    "ObjCRuntime toJavaString is null-safe" - {
        val files = generateAll("""
            @interface KxDummy
            - (long)count;
            @end
        """.trimIndent())

        "toJavaString checks for NULL nsString" {
            val runtime = getRuntime(files)
            runtime shouldContain "if (nsString == MemorySegment.NULL) return"
        }

        "toJavaString checks for NULL utf8Ptr" {
            val runtime = getRuntime(files)
            runtime shouldContain "if (utf8Ptr == MemorySegment.NULL) return"
        }
    }
})
