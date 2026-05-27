package org.graphiks.kextract.integration

import io.kotest.core.annotation.EnabledIf
import io.kotest.core.annotation.MacCondition
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.pipeline.NameMangler
import org.graphiks.kextract.kotlin.KotlinGenerator
import org.graphiks.kextract.kotlin.models.KotlinSourceFile
import org.graphiks.kextract.pipeline.KextractTool
import java.nio.file.Files

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
})
