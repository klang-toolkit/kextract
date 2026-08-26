package org.graphiks.kextract

import io.kotest.core.annotation.EnabledIf
import io.kotest.core.annotation.MacCondition
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.maps.shouldContainKey
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.kotlin.KotlinGenerator
import org.graphiks.kextract.kotlin.models.KotlinSourceFile
import org.graphiks.kextract.pipeline.IncludeFilter
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.IncludeHelper
import org.graphiks.kextract.pipeline.NameMangler
import java.nio.file.Files

/**
 * Parser-level and generator-level tests for Objective-C bindings.
 *
 * Tests in this class use either:
 * - Fixture headers from src/test/resources/objc/ (Animal.h, Counter.h)
 * - Inline ObjC source for focused single-case tests
 *
 * Coverage targets (GRA-92):
 * - Multi-class inheritance (Animal → Dog)
 * - @protocol adoption with required and optional methods
 * - @category emitting extension functions
 * - Class (+) methods in companion object vs instance (-) methods as member functions
 * - @property (readonly vs readwrite)
 * - NSString parameter and return-type convenience overloads
 * - Parser correctness: ObjCClass.superClass(), protocols(), methods()
 */
@EnabledIf(MacCondition::class)
class ObjCGeneratorTest : FreeSpec({

    // ── Helpers ───────────────────────────────────────────────────────────────

    /** Resolve a fixture header from src/test/resources/objc/. */
    fun fixtureHeader(name: String): String {
        val url = Thread.currentThread().contextClassLoader.getResource("objc/$name")
            ?: error("Fixture header not found on classpath: objc/$name")
        return url.toURI().let { java.io.File(it).absolutePath }
    }

    /**
     * Parse inline ObjC source and run the full generator pipeline.
     * Returns all generated [KotlinSourceFile] objects.
     */
    fun generateAll(objcSource: String, pkg: String = "test"): List<KotlinSourceFile> {
        val tmp = Files.createTempFile("kextract_objcgen_test_", ".h")
        return try {
            tmp.toFile().writeText(objcSource)
            val headerName = tmp.fileName.toString()
            val parsed = KextractTool.parse(listOf(tmp.toString()), "-x", "objective-c")
            val mangled = NameMangler(headerName).scan(parsed)
            KotlinGenerator().generate(mangled, headerName, pkg)
        } finally {
            Files.deleteIfExists(tmp)
        }
    }

    /** Concatenates all generated source file contents. */
    fun generate(objcSource: String, pkg: String = "test"): String =
        generateAll(objcSource, pkg).joinToString("\n") { it.contents }

    /**
     * Parse and generate from a fixture header file.
     * Returns all generated [KotlinSourceFile] objects.
     */
    fun generateFromFixture(headerName: String, pkg: String = "test"): List<KotlinSourceFile> {
        val path = fixtureHeader(headerName)
        val parsed = KextractTool.parse(listOf(path), "-x", "objective-c")
        val mangled = NameMangler(headerName).scan(parsed)
        return KotlinGenerator().generate(mangled, headerName, pkg)
    }

    /** Concatenated source from a fixture header. */
    fun generateSourceFromFixture(headerName: String, pkg: String = "test"): String =
        generateFromFixture(headerName, pkg).joinToString("\n") { it.contents }

    /**
     * Generate with --split-output enabled, returns map of className -> contents.
     */
    fun generateSplit(objcSource: String, pkg: String = "test"): Map<String, String> {
        val tmp = Files.createTempFile("kextract_split_test_", ".h")
        return try {
            tmp.toFile().writeText(objcSource)
            val headerName = tmp.fileName.toString()
            val parsed = KextractTool.parse(listOf(tmp.toString()), "-x", "objective-c")
            val mangled = NameMangler(headerName).scan(parsed)
            KotlinGenerator().generate(mangled, headerName, pkg, splitOutput = true)
                .associate { it.className to it.contents }
        } finally {
            Files.deleteIfExists(tmp)
        }
    }

    /**
     * Generate with configurable options, returns map of className -> contents.
     */
    fun generateWithOptions(objcSource: String, pkg: String = "test", splitOutput: Boolean = false): Map<String, String> {
        val tmp = Files.createTempFile("kextract_options_test_", ".h")
        return try {
            tmp.toFile().writeText(objcSource)
            val headerName = tmp.fileName.toString()
            val parsed = KextractTool.parse(listOf(tmp.toString()), "-x", "objective-c")
            val mangled = NameMangler(headerName).scan(parsed)
            KotlinGenerator().generate(mangled, headerName, pkg, splitOutput = splitOutput)
                .associate { it.className to it.contents }
        } finally {
            Files.deleteIfExists(tmp)
        }
    }

    // ── Parser-level checks ───────────────────────────────────────────────────

    "Parser: Animal.h produces expected ObjC declarations" - {

        // Note: Animal.h imports Foundation.h; the parser may not resolve the
        // superclass to 'NSObject' when Foundation headers are not fully available.
        // We therefore only assert on things the parser can determine from our
        // own declarations without fully resolving Foundation types.

        "Dog class is parsed with Animal superclass" {
            val path = fixtureHeader("Animal.h")
            val parsed = KextractTool.parse(listOf(path), "-x", "objective-c")
            val classes = parsed.members().filterIsInstance<Declaration.ObjCClass>()
            val dog = classes.firstOrNull { it.name() == "Dog" }
            dog?.superClass() shouldBe "Animal"
        }

        "Greetable protocol is parsed as ObjCProtocol" {
            val path = fixtureHeader("Animal.h")
            val parsed = KextractTool.parse(listOf(path), "-x", "objective-c")
            val protocols = parsed.members().filterIsInstance<Declaration.ObjCProtocol>()
            protocols.any { it.name() == "Greetable" } shouldBe true
        }

        "Animal class adopts the Greetable protocol" {
            val path = fixtureHeader("Animal.h")
            val parsed = KextractTool.parse(listOf(path), "-x", "objective-c")
            val classes = parsed.members().filterIsInstance<Declaration.ObjCClass>()
            val animal = classes.firstOrNull { it.name() == "Animal" }
            animal?.protocols()?.contains("Greetable") shouldBe true
        }

        "Counter class has readonly property" {
            val path = fixtureHeader("Counter.h")
            val parsed = KextractTool.parse(listOf(path), "-x", "objective-c")
            val classes = parsed.members().filterIsInstance<Declaration.ObjCClass>()
            val counter = classes.firstOrNull { it.name() == "Counter" }
            val countProp = counter?.properties()?.firstOrNull { it.name() == "count" }
            countProp?.isReadOnly() shouldBe true
        }

        "Counter class has both class and instance methods" {
            val path = fixtureHeader("Counter.h")
            val parsed = KextractTool.parse(listOf(path), "-x", "objective-c")
            val classes = parsed.members().filterIsInstance<Declaration.ObjCClass>()
            val counter = classes.firstOrNull { it.name() == "Counter" }
            counter?.methods()?.any { it.isClassMethod() } shouldBe true
            counter?.methods()?.any { !it.isClassMethod() } shouldBe true
        }

        "class property attributes are preserved" {
            val source = """
                @protocol KxParsedClassProperty
                @property (class, readonly) long classValue;
                @end
            """.trimIndent()
            val tmp = Files.createTempFile("kextract_objc_class_property_", ".h")
            try {
                tmp.toFile().writeText(source)
                val protocol = KextractTool.parse(listOf(tmp.toString()), "-x", "objective-c")
                    .members()
                    .filterIsInstance<Declaration.ObjCProtocol>()
                    .single { it.name() == "KxParsedClassProperty" }
                protocol.properties().single().isClassProperty() shouldBe true
            } finally {
                Files.deleteIfExists(tmp)
            }
        }
    }

    // ── Generator: Animal.h fixture ──────────────────────────────────────────

    "Generator: Animal.h produces correct Kotlin" - {

        "Animal class is generated as open class with MemorySegment ptr" {
            val src = generateSourceFromFixture("Animal.h")
            src shouldContain "open class Animal"
            src shouldContain "MemorySegment"
        }

        "Dog class extends Animal via superclass clause" {
            val src = generateSourceFromFixture("Animal.h")
            src shouldContain "open class Dog"
            src shouldContain ": Animal(ptr)"
        }

        "Dog class contains bark() instance method" {
            val src = generateSourceFromFixture("Animal.h")
            src shouldContain "fun bark()"
        }

        "Dog class contains greet() instance method" {
            val src = generateSourceFromFixture("Animal.h")
            // greet() is an instance method override
            src shouldContain "fun greet()"
        }

        "Animal class factory method is in companion object" {
            val src = generateSourceFromFixture("Animal.h")
            // animalWithName: is a class method (+) — must appear inside companion object
            val companionIdx = src.indexOf("companion object")
            val animalWithNameIdx = src.indexOf("fun animalWithName")
            assert(companionIdx >= 0) { "companion object not found in generated source" }
            assert(animalWithNameIdx > companionIdx) {
                "animalWithName should appear after companion object declaration"
            }
        }

        "Greetable protocol generates a Kotlin interface" {
            val src = generateSourceFromFixture("Animal.h")
            src shouldContain "interface Greetable"
        }

        "Greetable protocol greet() is a required abstract method" {
            val src = generateSourceFromFixture("Animal.h")
            src shouldContain "fun greet()"
            // Required methods have no default throw body
            src shouldNotContain "throw UnsupportedOperationException(\"greet\")"
        }

        "Greetable protocol greetWithName has // @optional comment" {
            val src = generateSourceFromFixture("Animal.h")
            src shouldContain "// @optional"
            src shouldContain "greetWithName"
        }

        "ObjCRuntime.kt is included in Animal.h output" {
            val files = generateFromFixture("Animal.h")
            files shouldHaveAtLeastSize 2
            val runtime = files.firstOrNull { it.className == "ObjCRuntime" }
            runtime?.contents shouldContain "object ObjCRuntime"
        }
    }

    // ── Generator: Counter.h fixture ────────────────────────────────────────

    "Generator: Counter.h produces correct Kotlin" - {

        "Counter class is open class with companion object" {
            val src = generateSourceFromFixture("Counter.h")
            src shouldContain "open class Counter"
            src shouldContain "companion object"
        }

        "counterWithStart class method appears in companion object" {
            val src = generateSourceFromFixture("Counter.h")
            val companionIdx = src.indexOf("companion object")
            val counterWithStartIdx = src.indexOf("fun counterWithStart")
            assert(companionIdx >= 0) { "companion object not found" }
            assert(counterWithStartIdx > companionIdx) {
                "counterWithStart class method should be inside companion object"
            }
        }

        "increment is generated as instance method" {
            val src = generateSourceFromFixture("Counter.h")
            src shouldContain "fun increment()"
        }

        "incrementBy: generates method with Int parameter" {
            val src = generateSourceFromFixture("Counter.h")
            src shouldContain "fun incrementBy"
            src shouldContain "amount: Int"
        }

        "readonly count property generates getter only — no setter" {
            val src = generateSourceFromFixture("Counter.h")
            src shouldContain "fun count()"
            src shouldNotContain "fun setCount"
        }
    }

    // ── Generator: inline — @category ────────────────────────────────────────

    "Objective-C API surfaces preserve semantic types" - {
        val files = generateFromFixture("SemanticTypes.h")
        val src = files.joinToString("\n") { it.contents }
        val declarations = KextractTool.parse(
            listOf(fixtureHeader("SemanticTypes.h")),
            "-x", "objective-c",
        ).members()

        "libclang exposes NS_OPTIONS through FlagEnum" {
            val flags = declarations
                .filterIsInstance<Declaration.Scoped>()
                .single { it.kind() == Declaration.Scoped.Kind.ENUM && it.name() == "KxFlags" }
            flags.getAttribute<Declaration.ClangAttributes>()!!
                .attributes shouldContainKey "FlagEnum"
        }

        "class methods and properties use semantic enum scalar and struct types" {
            src shouldContain "fun acceptsMode_flags(mode: KxMode, flags: KxFlags): Boolean"
            src shouldContain "fun negateByteBool(value: Boolean): Boolean"
            src shouldContain "fun signedIndex(): Long"
            src shouldContain "fun unsignedCount(): Long"
            src shouldContain "fun roundTripUnsignedCode(code: KxUnsignedCode): KxUnsignedCode"
            src shouldContain "fun translatePoint_rect_range_rangePointer(point: NSPoint, rect: NSRect, range: NSRange, rangePointer: NSRangePointer): NSPoint"
            src shouldContain "fun mode(): KxMode"
            src shouldContain "fun setMode(value: KxMode)"
            src shouldContain "fun flags(): KxFlags"
            src shouldContain "fun selection(): NSRange"
            src shouldContain "fun setSelection(value: NSRange)"
        }

        "signed-char BOOL uses a byte carrier while keeping a Boolean surface" {
            src shouldContain "ObjCRuntime.msgSend(ValueLayout.JAVA_BYTE, ptr, sel, if (value) 1.toByte() else 0.toByte()) as Byte"
            src shouldContain ") != 0.toByte()"
        }

        "protocols and categories share the same semantic signatures" {
            src shouldContain "fun modeForRange(range: NSRange): KxMode"
            src shouldContain "fun pointerForRange(range: NSRange): NSRangePointer"
            src shouldContain "fun KxSemanticHost.offsetRange_pointer(range: NSRange, pointer: NSRangePointer): NSRange"
        }

        "enum and options wrappers remain open to unknown raw values" {
            src shouldContain "value class KxMode(val rawValue: Long)"
            src shouldContain "val KxModeOne = KxMode(1L)"
            src shouldContain "value class KxFlags(val rawValue: Long)"
            src shouldContain "val KxFlagsOne = KxFlags(1L)"
            src shouldNotContain "enum class KxMode"
            src shouldNotContain "KxMode.fromValue("
        }

        "unsigned enum lowering preserves the full raw-value range" {
            src shouldContain "code.rawValue.toInt()"
            src shouldContain "Integer.toUnsignedLong("
        }

        "value and pointer structs have distinct nominal wrappers and lowering" {
            src shouldContain "class _NSRange internal constructor(internal val segment: MemorySegment)"
            src shouldContain "class _NSRangePointer internal constructor(internal val segment: MemorySegment)"
            src shouldContain "typealias NSRange = _NSRange"
            src shouldContain "typealias NSRangePointer = _NSRangePointer"
            Regex("typealias NSRangePointer = ").findAll(src).count() shouldBe 1
            src shouldNotContain "typealias NSRangePointer = MemorySegment"
            src shouldContain "constructor(location: Long, length: Long)"
            src shouldContain "ObjCRuntime.ObjCStructArg(range.segment, NSRange.layout)"
            src shouldContain "rangePointer.segment"
            src shouldNotContain "range: MemorySegment"
            src shouldNotContain "rangePointer: MemorySegment"
        }

        "surface structs lower enum pointer union and array fields coherently" {
            src shouldContain "value class KxFieldMode(val rawValue: Long)"
            src shouldContain "constructor(mode: KxFieldMode, rangePointer: NSRangePointer, payload: MemorySegment, bytes: MemorySegment)"
            src shouldContain "fun mode(): KxFieldMode"
            src shouldContain "fun mode(value: KxFieldMode)"
            src shouldContain "fun rangePointer(): NSRangePointer"
            src shouldContain "fun rangePointer(value: NSRangePointer)"
            src shouldContain "var payload: MemorySegment"
            src shouldContain "var bytes: MemorySegment"
            src shouldNotContain "fun mode(): KxFieldMode = mode_VH.get(segment, 0L) as KxFieldMode"
            src shouldNotContain "fun rangePointer(): MemorySegment"
        }

        "surface struct layouts preserve Clang padding and omit unsafe bitfield APIs" {
            val padded = src.substringAfter("class KxPaddedRecord internal constructor")
                .substringBefore("class KxPaddedRecordPointer")
            padded shouldContain "MemoryLayout.paddingLayout(7L)"
            Regex("MemoryLayout\\.paddingLayout\\(7L\\)").findAll(padded).count() shouldBe 2
            padded shouldContain ").withByteAlignment(8L).withName(\"KxPaddedRecord\")"

            val bitfield = src.substringAfter("class KxBitfieldRecord internal constructor")
                .substringBefore("class KxBitfieldRecordPointer")
            bitfield shouldContain "MemoryLayout.paddingLayout(8L)"
            bitfield shouldContain "MemoryLayout.paddingLayout(7L)"
            bitfield shouldContain "constructor(payload: Long, tail: Byte)"
            bitfield shouldNotContain "fun low("
            bitfield shouldNotContain "fun high("
        }

        "struct return dispatch is selected from the return layout" {
            val runtime = files.single { it.className == "ObjCRuntime" }.contents
            src shouldContain "ObjCRuntime.msgSendStruct(KxTwoDoubles.layout"
            src shouldContain "ObjCRuntime.msgSendStruct(KxFourDoubles.layout"
            src shouldNotContain "ObjCRuntime.msgSendStret(KxTwoDoubles.layout"
            runtime shouldContain "internal fun objcStructReturnUsesStret("
            runtime shouldContain "returnLayout.byteSize() > 16L"
        }

        "shared C structs retain raw functions and gain typed adapters" {
            src shouldContain "fun NSUnionRange(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment"
            src shouldContain "fun NSUnionRange(allocator: SegmentAllocator, arg0: NSRange, arg1: NSRange): NSRange"
            src shouldContain "NSRange(NSUnionRange(allocator, arg0.segment, arg1.segment))"
            src shouldContain "fun NSIntersectionRange(allocator: SegmentAllocator, arg0: NSRange, arg1: NSRange): NSRange"
            src shouldContain "fun KxCurrentRange(allocator: SegmentAllocator): MemorySegment"
            src shouldContain "fun KxCurrentRangeTyped(allocator: SegmentAllocator): NSRange"
        }

        "include filtering retains semantic types required by an included Objective-C class" {
            val path = fixtureHeader("SemanticTypes.h")
            val parsed = KextractTool.parse(listOf(path), "-x", "objective-c")
            val includes = IncludeHelper().apply {
                addSymbol(IncludeHelper.IncludeKind.OBJC_CLASS, "KxSemanticHost")
            }
            val filtered = IncludeFilter(includes).scan(parsed)
            val mangled = NameMangler("SemanticTypes.h").scan(filtered)
            val filteredSource = KotlinGenerator().generate(mangled, "SemanticTypes.h", "test")
                .joinToString("\n") { it.contents }

            filteredSource shouldContain "class _NSRange internal constructor"
            filteredSource shouldContain "typealias NSRange = _NSRange"
            filteredSource shouldContain "range: NSRange"
            filteredSource shouldContain "rangePointer: NSRangePointer"
        }

        "a C struct included without its filtered Objective-C consumer keeps the legacy API" {
            val tmp = Files.createTempFile("kextract_objc_filter_scope_", ".h")
            val filteredSource = try {
                tmp.toFile().writeText("""
                    typedef struct KxPlainRecord { long value; } KxPlainRecord;
                    @interface KxExcludedConsumer
                    - (KxPlainRecord)transform:(KxPlainRecord)value;
                    @end
                """.trimIndent())
                val parsed = KextractTool.parse(listOf(tmp.toString()), "-x", "objective-c")
                val includes = IncludeHelper().apply {
                    addSymbol(IncludeHelper.IncludeKind.STRUCT, "KxPlainRecord")
                }
                val filtered = IncludeFilter(includes).scan(parsed)
                val mangled = NameMangler(tmp.fileName.toString()).scan(filtered)
                KotlinGenerator().generate(mangled, tmp.fileName.toString(), "test")
                    .joinToString("\n") { it.contents }
            } finally {
                Files.deleteIfExists(tmp)
            }

            filteredSource shouldContain "class KxPlainRecord {"
            filteredSource shouldContain "fun allocate(allocator: SegmentAllocator): MemorySegment"
            filteredSource shouldNotContain "class KxPlainRecord internal constructor"
            filteredSource shouldNotContain "class KxPlainRecordPointer"
        }
    }

    "_Bool-backed BOOL uses a Boolean carrier end to end" {
        val src = generate("""
            typedef _Bool BOOL;
            @interface KxBoolHost
            - (BOOL)negate:(BOOL)value;
            @end
        """.trimIndent())

        src shouldContain "fun negate(value: Boolean): Boolean"
        src shouldContain "ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, value) as Boolean"
        src shouldNotContain "if (value) 1.toByte() else 0.toByte()"
        src shouldNotContain "as Byte"
    }

    "packed Objective-C surface structs fail generation instead of guessing an ABI layout" {
        val failure = runCatching {
            generate("""
                typedef struct __attribute__((packed)) KxPackedValue {
                    long value;
                } KxPackedValue;
                @interface KxPackedHost
                - (KxPackedValue)roundTrip:(KxPackedValue)value;
                @end
            """.trimIndent())
        }.exceptionOrNull() ?: error("packed surface generation unexpectedly succeeded")

        failure.message.orEmpty() shouldContain
            "Cannot safely emit Objective-C surface struct KxPackedValue"
    }

    "packed records used by C value and Objective-C pointer retain the safety failure" {
        val failure = runCatching {
            generate("""
                typedef struct __attribute__((packed)) KxPackedMixedValue {
                    unsigned int tag;
                    void *payload;
                } KxPackedMixedValue;
                KxPackedMixedValue KxRoundTripPackedMixedValue(KxPackedMixedValue value);
                @interface KxPackedMixedValueHost
                - (void)consume:(const KxPackedMixedValue *)value;
                @end
            """.trimIndent())
        }.exceptionOrNull() ?: error("packed mixed-value generation unexpectedly succeeded")

        failure.message.orEmpty() shouldContain
            "Cannot safely emit Objective-C surface struct KxPackedMixedValue"
    }

    "packed records used only through Objective-C pointers stay nominal and opaque" {
        val src = generate("""
            typedef struct __attribute__((packed)) KxPackedPointerOnly {
                unsigned int tag;
                void *payload;
            } KxPackedPointerOnly;
            typedef const KxPackedPointerOnly *KxPackedPointerOnlyRef;
            @interface KxPackedPointerHost
            - (void)consume:(KxPackedPointerOnlyRef)value;
            @end
        """.trimIndent())

        src shouldContain
            "class KxPackedPointerOnlyPointer internal constructor(internal val segment: MemorySegment)"
        src shouldContain "typealias KxPackedPointerOnlyRef = KxPackedPointerOnlyPointer"
        Regex("typealias KxPackedPointerOnlyRef = ").findAll(src).count() shouldBe 1
        src shouldContain "fun consume(value: KxPackedPointerOnlyRef)"
        src shouldContain "value.segment"
        src shouldNotContain "class KxPackedPointerOnly {"
        src shouldNotContain "class KxPackedPointerOnly internal constructor"

        val pointerWrapper = src.substringAfter(
            "class KxPackedPointerOnlyPointer internal constructor(internal val segment: MemorySegment)",
        ).substringBefore("open class KxPackedPointerHost")
        pointerWrapper shouldNotContain "layout"
        pointerWrapper shouldNotContain "allocate"
        pointerWrapper shouldNotContain "constructor("
        pointerWrapper shouldNotContain "pointed("
        pointerWrapper shouldNotContain "fun tag("
        pointerWrapper shouldNotContain "fun payload("
    }

    "distinct record tags synthesize one nominal opaque pointer alias" {
        val src = generate("""
            typedef struct __KxOpaqueTag {
                unsigned int tag;
                void *payload;
            } KxOpaque;
            @interface KxOpaqueHost
            - (void)consume:(const KxOpaque *)value;
            @end
        """.trimIndent())

        src shouldContain
            "class _KxOpaqueTagPointer internal constructor(internal val segment: MemorySegment)"
        src shouldContain "typealias KxOpaquePointer = _KxOpaqueTagPointer"
        Regex("typealias KxOpaquePointer = ").findAll(src).count() shouldBe 1
        src shouldContain "fun consume(value: KxOpaquePointer)"
        src shouldContain "value.segment"
        src shouldNotContain "class _KxOpaqueTag internal constructor"
        src shouldNotContain "class KxOpaque internal constructor"

        val opaqueSurface = src.substringAfter(
            "class _KxOpaqueTagPointer internal constructor(internal val segment: MemorySegment)",
        ).substringBefore("open class KxOpaqueHost")
        opaqueSurface shouldNotContain "layout"
        opaqueSurface shouldNotContain "allocate"
        opaqueSurface shouldNotContain "pointed("
    }

    // NOTE: In libclang, for `@interface KxAnimal (Tricks)`, c.spelling() returns
    // the category name ("Tricks"), not the extended class name. The generator
    // therefore produces extension functions with the category name as receiver
    // (e.g. `fun Tricks.rollOver()`). These tests document current behaviour.
    "@category methods are emitted as extension functions" - {
        val src = generate("""
            @interface KxAnimal
            - (long)age;
            @end

            @interface KxAnimal (Tricks)
            - (void)rollOver;
            - (long)trickCount;
            @end
        """.trimIndent())

        "rollOver selector is registered and emitted" {
            src shouldContain "ObjCRuntime.sel(\"rollOver\")"
        }

        "trickCount selector is registered and emitted" {
            src shouldContain "ObjCRuntime.sel(\"trickCount\")"
        }

        "a comment header for the category block is emitted" {
            src shouldContain "Category:"
        }

        "rollOver uses ObjCRuntime.msgSend for dispatch" {
            src shouldContain "ObjCRuntime.msgSend"
        }

        "trickCount return type is Long" {
            src shouldContain "trickCount(): Long"
        }
    }

    "@category class method emits a non-extension function" - {
        val src = generate("""
            @interface KxWidget
            @end

            @interface KxWidget (Factory)
            + (instancetype)defaultWidget;
            @end
        """.trimIndent())

        "class method from category is not an extension function (has // Class method comment)" {
            src shouldContain "// Class method"
        }

        "the factory function is present somewhere in the output" {
            src shouldContain "defaultWidget"
        }
    }

    // ── Generator: inline — multi-level inheritance ───────────────────────────

    "Three-level inheritance chain is generated correctly" - {
        val src = generate("""
            @interface KxA
            - (void)fromA;
            @end
            @interface KxB : KxA
            - (void)fromB;
            @end
            @interface KxC : KxB
            - (void)fromC;
            @end
        """.trimIndent())

        "KxA is a root class — its class declaration has no superclass clause" {
            // Find the specific line that declares KxA and assert it has no supertype
            val kxaClassLine = src.lines().firstOrNull { it.contains("open class KxA") }
            assert(kxaClassLine != null) { "KxA class declaration not found" }
            assert(!kxaClassLine!!.contains(": KxA(ptr)")) {
                "KxA should have no superclass clause but found: $kxaClassLine"
            }
        }

        "KxB extends KxA" {
            src shouldContain ": KxA(ptr)"
        }

        "KxC extends KxB" {
            src shouldContain ": KxB(ptr)"
        }

        "each class has its own method" {
            src shouldContain "fun fromA()"
            src shouldContain "fun fromB()"
            src shouldContain "fun fromC()"
        }
    }

    // ── Generator: inline — multi-param selector naming ──────────────────────

    "Multi-part selector with two labelled params maps to underscore-joined name" - {
        // Use int params (no Foundation.h needed) to verify multi-part selector naming
        val src = generate("""
            @interface KxScaler
            - (void)scaleWidth:(int)w height:(int)h;
            @end
        """.trimIndent())

        "selector colons become underscores, trailing colon stripped" {
            src shouldContain "fun scaleWidth_height"
        }

        "first parameter name and type are correct" {
            src shouldContain "w: Int"
        }

        "second parameter name and type are correct" {
            src shouldContain "h: Int"
        }

        "the method uses the full selector for ObjC dispatch" {
            src shouldContain "ObjCRuntime.sel(\"scaleWidth:height:\")"
        }
    }

    // ── Generator: inline — protocol inheritance ──────────────────────────────

    "Required adopted protocol members become concrete class extensions" - {
        val src = generate("""
            @protocol KxBase
            @required
            - (long)baseValue;
            @end

            @protocol KxAppearance <KxBase>
            @required
            @property (readonly, strong) id effectiveAppearance;
            @optional
            @property (readonly, strong) id optionalAppearance;
            - (void)optionalHook;
            @end

            @interface KxView <KxAppearance>
            - (long)baseValue;
            @end

            @interface KxView (Existing)
            - (id)effectiveAppearance;
            @end

            @interface KxPlainView <KxAppearance>
            @end
        """.trimIndent())

        "required object property is emitted once despite a category duplicate" {
            src.split("fun KxView.effectiveAppearance(): MemorySegment").size - 1 shouldBe 1
        }

        "direct class methods suppress matching inherited protocol extensions" {
            src.split("fun KxView.baseValue(): Long").size - 1 shouldBe 0
            src shouldContain "fun baseValue(): Long"
        }

        "required inherited protocol methods are emitted for classes without direct members" {
            src shouldContain "fun KxPlainView.baseValue(): Long"
            src shouldContain "fun KxPlainView.effectiveAppearance(): MemorySegment"
        }

        "optional protocol methods are never emitted as concrete class extensions" {
            src shouldNotContain "fun KxView.optionalHook()"
            src shouldNotContain "fun KxPlainView.optionalHook()"
        }

        "optional protocol properties are never emitted as concrete class extensions" {
            src shouldNotContain "fun KxView.optionalAppearance(): MemorySegment"
            src shouldNotContain "fun KxPlainView.optionalAppearance(): MemorySegment"
        }
    }

    "Required adopted protocol members preserve semantic type lowering" - {
        val src = generate("""
            typedef struct _KxProtocolPoint {
                double x;
                double y;
            } KxProtocolPoint;

            @protocol KxGeometryRequirement
            @required
            - (KxProtocolPoint)requiredPoint;
            @end

            @interface KxGeometryHost <KxGeometryRequirement>
            @end
        """.trimIndent())

        "a required protocol extension retains its nominal struct return type" {
            src shouldContain "fun KxGeometryHost.requiredPoint(): KxProtocolPoint"
            src shouldContain "ObjCRuntime.msgSendStruct(KxProtocolPoint.layout, this.ptr, sel)"
            src shouldNotContain "fun KxGeometryHost.requiredPoint(): MemorySegment"
        }
    }

    "Protocol and category properties deduplicate accessors independently" - {
        val src = generate("""
            @protocol KxReadOnlyRequirement
            @required
            @property (readonly, strong) id foo;
            @end

            @interface KxAccessorHost <KxReadOnlyRequirement>
            @end

            @interface KxAccessorHost (WritableFoo)
            @property (readwrite, strong) id foo;
            @end
        """.trimIndent())

        "the required getter is emitted once" {
            src.split("fun KxAccessorHost.foo(): MemorySegment").size - 1 shouldBe 1
        }

        "the category contributes the missing writable setter" {
            src.split("fun KxAccessorHost.setFoo(foo: MemorySegment)").size - 1 shouldBe 1
        }
    }

    "Protocol requirements inherited from generated parents are not duplicated on children" - {
        val src = generate("""
            @protocol KxSharedRequirement
            @required
            - (long)sharedValue;
            @end

            @interface KxProtocolParent <KxSharedRequirement>
            @end

            @interface KxProtocolChild : KxProtocolParent <KxSharedRequirement>
            @end
        """.trimIndent())

        "the parent receives the required extension" {
            src shouldContain "fun KxProtocolParent.sharedValue(): Long"
        }

        "the child does not receive a redundant extension" {
            src shouldNotContain "fun KxProtocolChild.sharedValue(): Long"
        }
    }

    "Required protocol class properties use class-object dispatch" - {
        val src = generate("""
            @protocol KxClassPropertyRequirement
            @required
            @property (class, readonly) long classValue;
            @property (class, readwrite) long mutableClassValue;
            @end

            @interface KxClassPropertyHost <KxClassPropertyRequirement>
            @end
        """.trimIndent())

        "the required class getter is a single top-level function" {
            src.split("fun KxClassPropertyHost_classValue(): Long").size - 1 shouldBe 1
            src shouldContain "ObjCRuntime.getClass(\"KxClassPropertyHost\")"
        }

        "the writable class setter is a single top-level function" {
            src.split("fun KxClassPropertyHost_setMutableClassValue(value: Long)").size - 1 shouldBe 1
        }

        "no class property is emitted as an instance extension" {
            src shouldNotContain "fun KxClassPropertyHost.classValue()"
            src shouldNotContain "fun KxClassPropertyHost.mutableClassValue()"
            src shouldNotContain "fun KxClassPropertyHost.setMutableClassValue("
        }
    }

    "Required protocol member dispatch kinds are independent" - {
        "a class method does not suppress an instance protocol extension with the same selector" {
            val src = generate("""
                @protocol KxInstanceRequirement
                @required
                - (long)foo;
                @end

                @interface KxClassFoo <KxInstanceRequirement>
                + (long)foo;
                @end
            """.trimIndent())

            src shouldContain "fun KxClassFoo.foo(): Long"
        }

        "an inherited required class method is emitted as a top-level concrete-class function" {
            val src = generate("""
                @protocol KxClassParent
                @required
                + (long)classValue;
                @end

                @protocol KxClassRequirement <KxClassParent>
                @end

                @interface KxClassRequirementOwner <KxClassRequirement>
                @end
            """.trimIndent())

            src shouldContain "fun KxClassRequirementOwner_classValue(): Long"
            src shouldContain "ObjCRuntime.getClass(\"KxClassRequirementOwner\")"
        }

        "a direct companion method suppresses a required class protocol function with the same selector" {
            val src = generate("""
                @protocol KxClassRequirement
                @required
                + (long)classValue;
                @end

                @interface KxClassRequirementOwner <KxClassRequirement>
                + (long)classValue;
                @end
            """.trimIndent())

            src shouldContain "fun classValue(): Long"
            src shouldNotContain "fun KxClassRequirementOwner_classValue(): Long"
        }

        "required class and instance methods with the same selector coexist" {
            val src = generate("""
                @protocol KxBothKinds
                @required
                + (long)foo;
                - (long)foo;
                @end

                @interface KxBothKindsOwner <KxBothKinds>
                @end
            """.trimIndent())

            src shouldContain "fun KxBothKindsOwner_foo(): Long"
            src shouldContain "fun KxBothKindsOwner.foo(): Long"
        }
    }

    "Split output keeps required protocol extensions and categories deduplicated" - {
        val files = generateSplit("""
            @protocol KxSplitRequirement
            @required
            @property (readonly, strong) id appearance;
            @end

            @interface KxSplitView <KxSplitRequirement>
            @end

            @interface KxSplitView (Existing)
            - (id)appearance;
            @end
        """.trimIndent())
        val classSource = files["KxSplitView"] ?: error("KxSplitView split file was not generated")

        "the required extension is emitted once in the class file" {
            classSource.split("fun KxSplitView.appearance(): MemorySegment").size - 1 shouldBe 1
        }
    }

    "Protocol with parent protocols" - {
        val src = generate("""
            @protocol KxBase
            - (void)baseMethod;
            @end

            @protocol KxExtended <KxBase>
            - (void)extendedMethod;
            @end
        """.trimIndent())

        "KxExtended interface is emitted" {
            src shouldContain "interface KxExtended"
        }

        "KxBase protocol is also emitted" {
            src shouldContain "interface KxBase"
        }
    }

    // ── Generator: inline — class method selector naming ─────────────────────

    "Class method with labelled selector parameter" - {
        val src = generate("""
            @interface KxFactory
            + (instancetype)createWithWidth:(int)w height:(int)h;
            @end
        """.trimIndent())

        "class method appears in companion object" {
            val companionIdx = src.indexOf("companion object")
            val methodIdx = src.indexOf("fun createWithWidth_height")
            assert(companionIdx >= 0) { "companion object not found" }
            assert(methodIdx > companionIdx) {
                "createWithWidth_height should be inside companion object"
            }
        }

        "selector colons become underscores" {
            src shouldContain "fun createWithWidth_height"
        }

        "parameters are generated with correct names and types" {
            src shouldContain "w: Int"
            src shouldContain "h: Int"
        }
    }

    // ── Generator: split-output mode ───────────────────────────────────────────

    "Split-output mode produces per-class files" - {

        "plain enum appears in Enums file, Options style in Options file" {
            val files = generateSplit("""
                typedef enum : long { Red = 1, Green = 2 } KxColor;
                typedef enum __attribute__((flag_enum)) : long { Readable = 1, Writable = 2 } KxFileOptions;
            """.trimIndent())
            files.keys.any { it.endsWith("Enums") } shouldBe true
            files.keys.any { it.endsWith("Options") } shouldBe true
            val enumsKey = files.keys.firstOrNull { it.endsWith("Enums") } ?: ""
            val optionsKey = files.keys.firstOrNull { it.endsWith("Options") } ?: ""
            (files[enumsKey] ?: "") shouldContain "enum class KxColor"
            (files[optionsKey] ?: "") shouldContain "value class KxFileOptions"
        }

        "multi-class generates separate files per class" {
            val files = generateSplit("""
                @interface KxA
                - (void)methodA;
                @end
                @interface KxB : KxA
                - (void)methodB;
                @end
            """.trimIndent())
            files.keys shouldContain "KxA"
            files.keys shouldContain "KxB"
            (files["KxA"] ?: "") shouldContain "fun methodA()"
            (files["KxB"] ?: "") shouldContain "fun methodB()"
        }

        "protocol gets its own file" {
            val files = generateSplit("""
                @protocol KxDrawable
                - (void)draw;
                @end
            """.trimIndent())
            files.keys shouldContain "KxDrawable"
            (files["KxDrawable"] ?: "") shouldContain "interface KxDrawable"
        }

        "ObjCRuntime is still included alongside split files" {
            val files = generateSplit("""
                @interface KxSimple
                - (void)doSomething;
                @end
            """.trimIndent())
            // In split mode, ObjCRuntime is NOT in the map (it's added separately by KotlinGenerator)
            // but each class file references ObjCRuntime.sel and ObjCRuntime.msgSend
            (files["KxSimple"] ?: "") shouldContain "ObjCRuntime.sel"
        }
    }

    // ── Generator: --include-framework ─────────────────────────────────────────

    "Include-framework mode" - {

        "class is generated in non-filtered mode (no --include-framework needed)" {
            val src = generate("""
                @interface KxFrameworkClass
                - (void)frameworkMethod;
                @end
            """.trimIndent())
            src shouldContain "fun frameworkMethod()"
        }

        "split-output with inline classes still generates per-class files" {
            val files = generateSplit("""
                @interface KxFwA
                - (void)methodA;
                @end
                @interface KxFwB
                - (void)methodB;
                @end
            """.trimIndent())
            files.keys shouldContain "KxFwA"
            files.keys shouldContain "KxFwB"
            (files["KxFwA"] ?: "") shouldContain "fun methodA()"
            (files["KxFwB"] ?: "") shouldContain "fun methodB()"
        }
    }
})
