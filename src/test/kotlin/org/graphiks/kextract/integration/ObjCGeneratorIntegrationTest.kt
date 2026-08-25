package org.graphiks.kextract.integration

import io.kotest.core.annotation.EnabledIf
import io.kotest.core.annotation.MacCondition
import io.kotest.core.spec.style.FreeSpec
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

    /** Runs every production filter and reads back all Kotlin files written by the tool. */
    fun generateWithPipeline(objcSource: String, pkg: String = "test"): List<KotlinSourceFile> {
        val workspace = Files.createTempDirectory("kextract_objc_pipeline_test_")
        val input = workspace.resolve("fixture.h")
        val output = workspace.resolve("output")
        return try {
            Files.writeString(input, objcSource)
            KextractTool(Logger.DEFAULT).runGeneration(
                listOf(input.toString()),
                Options(
                    clangArgs = listOf("-x", "objective-c"),
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

    fun compileAndInvokeLong(
        files: List<KotlinSourceFile>,
        probeSource: String,
        methodName: String,
    ): Long {
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
                    .invoke(null) as Long
            }
        } finally {
            workspace.toFile().deleteRecursively()
        }
    }

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
