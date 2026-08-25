package org.graphiks.kextract.integration

import io.kotest.core.annotation.EnabledIf
import io.kotest.core.annotation.MacCondition
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.kotlin.KotlinGenerator
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.NameMangler
import java.nio.file.Files

/**
 * Regression tests for issue #22 — generator emits ValueLayout.ADDRESS for all return types.
 *
 * Each test documents a specific failure mode from the report:
 *   Bug 1 — scalar typedef return types (BOOL, CGFloat, NSInteger) get ADDRESS instead of the
 *            correct primitive layout, causing ClassCastException at runtime.
 *   Bug 4 — @JvmInline value-class / NS_ENUM arguments crash layoutFor() in ObjCRuntime.
 */
@EnabledIf(MacCondition::class)
class Issue22ReproductionTest : FreeSpec({

    fun generate(objcSource: String, pkg: String = "test"): String {
        val tmp = Files.createTempFile("kextract_issue22_", ".h")
        return try {
            tmp.toFile().writeText(objcSource)
            val headerName = tmp.fileName.toString()
            val parsed = KextractTool.parse(listOf(tmp.toString()), "-x", "objective-c")
            val mangled = NameMangler(headerName).scan(parsed)
            KotlinGenerator().generate(mangled, headerName, pkg)
                .joinToString("\n") { it.contents }
        } finally {
            Files.deleteIfExists(tmp)
        }
    }

    // ── Bug 1: scalar typedef return types ────────────────────────────────────

    /**
     * CGFloat is typedef double.
     * The method returning CGFloat must use ValueLayout.JAVA_DOUBLE, not ADDRESS.
     * Using ADDRESS causes `NativeMemorySegmentImpl cannot be cast to Double` at runtime.
     */
    "Bug 1a — CGFloat return type uses JAVA_DOUBLE, not ADDRESS" {
        val src = generate("""
            typedef double CGFloat;

            @interface KxWindow
            - (CGFloat)backingScaleFactor;
            @end
        """.trimIndent())

        src shouldContain "ValueLayout.JAVA_DOUBLE"
        src shouldNotContain "ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as CGFloat"
    }

    /**
     * NSInteger is typedef long.
     * The method must use ValueLayout.JAVA_LONG, not ADDRESS.
     */
    "Bug 1b — NSInteger return type uses JAVA_LONG, not ADDRESS" {
        val src = generate("""
            typedef long NSInteger;

            @interface KxView
            - (NSInteger)tag;
            @end
        """.trimIndent())

        src shouldContain "ValueLayout.JAVA_LONG"
        src shouldNotContain "ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as NSInteger"
    }

    /**
     * BOOL is typedef signed char on Apple platforms.
     * The method must use ValueLayout.JAVA_BYTE, not ADDRESS.
     */
    "Bug 1c — BOOL return type uses JAVA_BYTE, not ADDRESS" {
        val src = generate("""
            typedef signed char BOOL;

            @interface KxView
            - (BOOL)isOpaque;
            @end
        """.trimIndent())

        src shouldContain "ValueLayout.JAVA_BYTE"
        src shouldNotContain "ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as BOOL"
    }

    /**
     * Plain int return type must not use ADDRESS (this was already working but guard against regression).
     */
    "Bug 1d — plain int return type uses JAVA_INT" {
        val src = generate("""
            @interface KxCounter
            - (int)count;
            @end
        """.trimIndent())

        src shouldContain "ValueLayout.JAVA_INT"
        src shouldNotContain "ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as Int"
    }

    /**
     * NSUInteger is typedef unsigned long.
     * The method must use ValueLayout.JAVA_LONG (unsigned long = long on 64-bit).
     */
    "Bug 1e — NSUInteger (typedef unsigned long) return type uses JAVA_LONG" {
        val src = generate("""
            typedef unsigned long NSUInteger;

            @interface KxArray
            - (NSUInteger)count;
            @end
        """.trimIndent())

        src shouldContain "ValueLayout.JAVA_LONG"
        src shouldNotContain "ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as NSUInteger"
    }

    // ── Bug 2: struct-by-value return type uses GroupLayout ──────────────────

    /**
     * A method returning a struct by value (e.g. `NSRect`) must be dispatched with
     * ObjCRuntime.msgSendStruct and a MemoryLayout.structLayout(…) expression, NOT
     * ValueLayout.ADDRESS. The runtime selects regular msgSend versus stret from the ABI.
     */
    "Bug 2a — struct-by-value return uses ABI-selected msgSendStruct with structLayout" {
        val src = generate("""
            typedef double CGFloat;
            struct KxPoint { CGFloat x; CGFloat y; };

            @interface KxShape
            - (struct KxPoint)origin;
            @end
        """.trimIndent())

        src shouldContain "msgSendStruct"
        src shouldContain "MemoryLayout.structLayout"
        src shouldNotContain "msgSend(ValueLayout.ADDRESS, ptr, sel)"
    }

    "Bug 2b — typedef'd struct return (NSRect-style) uses ABI-selected msgSendStruct" {
        val src = generate("""
            typedef double CGFloat;
            struct KxRect { CGFloat x; CGFloat y; CGFloat width; CGFloat height; };
            typedef struct KxRect KxRect;

            @interface KxWindow
            - (KxRect)frame;
            @end
        """.trimIndent())

        src shouldContain "msgSendStruct"
        src shouldContain "MemoryLayout.structLayout"
    }

    "Bug 2c — struct return layout contains correct field names and types" {
        val src = generate("""
            typedef double CGFloat;
            struct KxPoint { CGFloat x; CGFloat y; };

            @interface KxView
            - (struct KxPoint)center;
            @end
        """.trimIndent())

        src shouldContain "withName(\"x\")"
        src shouldContain "withName(\"y\")"
        src shouldContain "JAVA_DOUBLE"
    }

    // ── Bug 3: struct-by-value argument is wrapped with ObjCStructArg ────────

    /**
     * A method receiving a struct parameter by value must wrap the MemorySegment in
     * ObjCStructArg so ObjCRuntime.msgSend uses the correct GroupLayout. Without wrapping,
     * layoutFor() sees a MemorySegment and uses ValueLayout.ADDRESS, causing garbage geometry.
     */
    "Bug 3 — struct-by-value argument is wrapped in ObjCStructArg" {
        val src = generate("""
            typedef double CGFloat;
            struct KxRect { CGFloat x; CGFloat y; CGFloat w; CGFloat h; };

            @interface KxWindow
            - (void)setFrame:(struct KxRect)frame;
            @end
        """.trimIndent())

        src shouldContain "ObjCRuntime.ObjCStructArg"
        src shouldContain "MemoryLayout.structLayout"
    }

    // ── Bug 2 runtime: ObjCRuntime.kt contains ObjCStructArg and struct dispatch ──

    "Bug 2/3 runtime — ObjCRuntime.kt declares ObjCStructArg and ABI-selected struct dispatch" {
        val files = run {
            val tmp = java.nio.file.Files.createTempFile("kextract_rt_", ".h")
            try {
                tmp.toFile().writeText("@interface KxDummy\n- (void)noop;\n@end")
                val parsed = org.graphiks.kextract.pipeline.KextractTool.parse(
                    listOf(tmp.toString()), "-x", "objective-c")
                val mangled = org.graphiks.kextract.pipeline.NameMangler(tmp.fileName.toString()).scan(parsed)
                org.graphiks.kextract.kotlin.KotlinGenerator().generate(mangled, tmp.fileName.toString(), "test")
            } finally {
                java.nio.file.Files.deleteIfExists(tmp)
            }
        }
        val runtime = files.firstOrNull { it.className == "ObjCRuntime" }?.contents ?: ""

        runtime shouldContain "data class ObjCStructArg"
        runtime shouldContain "GroupLayout"
        runtime shouldContain "SegmentAllocator"
        runtime shouldContain "fun msgSendStruct"
        runtime shouldContain "fun objcStructReturnUsesStret"
        // Bug 5 — layout computed before unwrap
        runtime shouldContain "layouts before unwrap"
        // unwrap() is present
        runtime shouldContain "fun unwrap"
    }

    // ── Enum return types (Bug 1 extension) ───────────────────────────────────

    "Bug 1 extended — NS_ENUM return type reconstructs an open raw-value wrapper" {
        val src = generate("""
            typedef enum : long {
                KxStatusOk    = 0,
                KxStatusError = 1
            } KxStatus;

            @interface KxService
            - (KxStatus)getStatus;
            @end
        """.trimIndent())

        src shouldContain "return KxStatus(ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long)"
        src shouldNotContain "KxStatus.fromValue("
        src shouldNotContain "ObjCRuntime.msgSend(ValueLayout.ADDRESS"
    }

    "Bug 1 extended — NS_OPTIONS return type uses value class constructor" {
        val src = generate("""
            typedef enum : long {
                KxNone  = 0,
                KxRead  = 1,
                KxWrite = 2
            } KxPermissions;

            @interface KxFile
            - (KxPermissions)permissions;
            @end
        """.trimIndent())

        src shouldContain "KxPermissions("
        src shouldNotContain "ObjCRuntime.msgSend(ValueLayout.ADDRESS"
    }

    // ── Bug 4: @JvmInline value class in ObjCRuntime.layoutFor() ─────────────

    /**
     * When an NS_OPTIONS value class (e.g. KxWindowStyleMask) is passed as a vararg Any,
     * ObjCRuntime.layoutFor() must handle it — today it throws IllegalArgumentException.
     *
     * The fix is either to unbox at the call site (generate `value.rawValue`) or
     * to extend layoutFor() with reflection-based rawValue lookup.
     * This test documents the call-site unboxing approach: the generated method body
     * must pass `value.rawValue` (the underlying Long), not `value` itself.
     */
    "Bug 4 — NS_OPTIONS (value class) argument is unboxed at call site" {
        val src = generate("""
            typedef enum : long {
                KxWindowStyleMaskTitled    = 1,
                KxWindowStyleMaskClosable  = 2
            } KxWindowStyleMask;

            @interface KxWindow
            - (void)setStyleMask:(KxWindowStyleMask)mask;
            @end
        """.trimIndent())

        // The setter should pass mask.rawValue (Long) rather than mask (value class)
        // so that ObjCRuntime.layoutFor() sees a Long and doesn't crash.
        src shouldContain "mask.rawValue"
    }

    "Bug 4 — NS_ENUM raw-value wrapper argument is unboxed with .rawValue" {
        val src = generate("""
            typedef enum : long {
                KxAlignLeft   = 0,
                KxAlignCenter = 1,
                KxAlignRight  = 2
            } KxTextAlignment;

            @interface KxLabel
            - (void)setAlignment:(KxTextAlignment)alignment;
            @end
        """.trimIndent())

        src shouldContain "alignment.rawValue"
    }

    // ── Bonus: foundational typealiases survive --include-objc-class filtering ──

    /**
     * When IncludeFilter is active (simulated by an IncludeHelper with at least one entry),
     * typedefs backed by primitive types (BOOL, CGFloat, NSInteger) must NOT be Skip-marked.
     * Without this fix, the generated code references `CGFloat` etc. but has no typealias,
     * causing a Kotlin compile error in the downstream project.
     */
    "Bonus — primitive-backed typedefs survive include filtering" {
        // Build an IncludeHelper with only ObjC class filtering active
        val helper = org.graphiks.kextract.pipeline.IncludeHelper().also { h ->
            h.addSymbol(org.graphiks.kextract.pipeline.IncludeHelper.IncludeKind.OBJC_CLASS, "KxWidget")
        }
        val filter = org.graphiks.kextract.pipeline.IncludeFilter(helper)

        val tmp = java.nio.file.Files.createTempFile("kextract_bonus_", ".h")
        try {
            tmp.toFile().writeText("""
                typedef double CGFloat;
                typedef long NSInteger;
                typedef signed char BOOL;
                typedef struct { CGFloat x; CGFloat y; } KxPoint;

                @interface KxWidget
                - (CGFloat)scale;
                @end
            """.trimIndent())
            val parsed = org.graphiks.kextract.pipeline.KextractTool.parse(
                listOf(tmp.toString()), "-x", "objective-c")
            // Apply filter the same way KextractTool does
            filter.scan(parsed as org.graphiks.kextract.Declaration.Scoped)
            val mangled = org.graphiks.kextract.pipeline.NameMangler(tmp.fileName.toString()).scan(parsed)
            val src = org.graphiks.kextract.kotlin.KotlinGenerator()
                .generate(mangled, tmp.fileName.toString(), "test")
                .joinToString("\n") { it.contents }

            // Primitive typealiases must be present despite filtering
            src shouldContain "typealias CGFloat"
            src shouldContain "typealias NSInteger"
            src shouldContain "typealias BOOL"
        } finally {
            java.nio.file.Files.deleteIfExists(tmp)
        }
    }
})
