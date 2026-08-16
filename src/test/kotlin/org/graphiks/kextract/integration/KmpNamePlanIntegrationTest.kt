package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import java.nio.file.Files

class KmpNamePlanIntegrationTest : FreeSpec({
    "anonymous record layout names are independent of the absolute header path" {
        val header =
            """
            typedef struct Outer {
                int tag;
                union {
                    struct {
                        int x;
                        short y;
                    } pair;
                    long long wide;
                } data;
                int tail;
            } Outer;
            """.trimIndent()
        val firstRoot = Files.createTempDirectory("kextract-anonymous-layout-first")
        val secondRoot = Files.createTempDirectory("kextract-anonymous-layout-second")

        try {
            val firstHeader = firstRoot.resolve("anonymous-layout.h")
            val secondHeader = secondRoot.resolve("anonymous-layout.h")
            val first = generateKmpSourcesFromHeaderPath(header, firstHeader)
            val second = generateKmpSourcesFromHeaderPath(header, secondHeader)
            val forbiddenPaths = listOf(firstRoot, secondRoot).flatMap { root ->
                val absolutePath = root.toAbsolutePath().toString()
                listOf(absolutePath, absolutePath.replace(Regex("[^a-zA-Z0-9_]"), "_"))
            }

            first shouldBe second
            listOf(first.common, first.jvm, first.native, first.android).forEach { source ->
                forbiddenPaths.forEach { path -> source shouldNotContain path }
            }
            // Memory-backed JVM structs bake Clang offsets into accessors; the
            // anonymous union sits at offset 8 and the whole record spans 24 bytes.
            first.jvm shouldContain "actual interface Outer {"
            first.jvm shouldContain "private val buffer: MemoryBuffer by lazy { MemoryBuffer(handle, 24uL) }"
            first.jvm shouldContain "get() = buffer.readPointer(8uL)"
            first.jvm shouldNotContain ".withName("
            first.jvm shouldNotContain "union (unnamed at"
            first.jvm shouldNotContain "struct (unnamed at"
            first.android shouldNotContain "unnamed_at"
        } finally {
            firstRoot.toFile().deleteRecursively()
            secondRoot.toFile().deleteRecursively()
        }
    }

    "KMP names are deterministic and avoid runtime and synthetic-member collisions" {
        val header =
            """
            typedef struct NativeAddress {
                int value;
            } NativeAddress;

            typedef struct CollisionRecord {
                int handler;
                int layout;
                int data;
                int Companion;
                NativeAddress address;
            } CollisionRecord;

            int data(int data);
            """.trimIndent()

        val first = generateKmpSources(header)
        val second = generateKmpSources(header)

        first shouldBe second
        first.common shouldContain "import org.graphiks.kffi.NativeAddress as KffiNativeAddress"
        first.common shouldContain "expect interface NativeAddress"
        first.common shouldContain "var handler_2: Int"
        first.common shouldContain "var layout: Int"
        first.common shouldContain "var data: Int"
        first.common shouldContain "var Companion_2: Int"
        first.common shouldContain "expect fun data(data: Int): Int"
        first.common shouldNotContain "var layout_2: Int"
        first.common shouldNotContain "var data_: Int"
        first.common shouldContain "val handler: KffiNativeAddress"
        first.jvm shouldContain "actual var handler_2: Int"
        first.jvm shouldContain "actual var layout: Int"
        first.jvm shouldContain "actual var data: Int"
        first.jvm shouldContain "actual var Companion_2: Int"
        first.jvm shouldContain "actual fun data(data: Int): Int"
        first.native shouldContain "actual var handler_2: Int"

        compileAndInvokeGeneratedKmpJvm(
            generated = first,
            probePackage = "sample.probe",
            probeSource =
                """
                package sample.probe

                import sample.bindings.CollisionRecord
                import sample.bindings.NativeAddress

                fun verifyNames(): Array<Class<*>> = arrayOf(
                    CollisionRecord::class.java,
                    NativeAddress::class.java,
                )
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "verifyNames",
        )
    }

    "ByValue is reserved for generated Native and Android wrapper classes" {
        val generated = generateKmpSources(
            """
            typedef struct ValueCollision {
                int ByValue;
            } ValueCollision;
            """.trimIndent(),
        )

        generated.common shouldContain "var ByValue_2: Int"
        generated.native shouldContain "actual var ByValue_2: Int"
        generated.native shouldContain "value class ByValue("
        generated.android shouldContain "actual var ByValue_2: Int"
        generated.android shouldContain "class ByValue("
    }

    "Android ByReference and ByValue wrappers avoid raw C field name collisions" {
        val generated = generateKmpSources(
            """
            typedef struct JnaHelperCollision {
                int ByReference;
                int ByValue;
            } JnaHelperCollision;
            """.trimIndent(),
        )

        generated.android shouldContain "actual var ByReference_2: Int"
        generated.android shouldContain "actual var ByValue_2: Int"
        generated.android shouldContain
            "class ByReference(val handle: NativeAddress = NativeAddress(0L)) : JnaHelperCollision {"
        generated.android shouldContain
            "class ByValue(val handle: NativeAddress = NativeAddress(0L)) : JnaHelperCollision {"
    }

    "KMP declarations and parameters are Kotlin-safe before emission" {
        val generated = generateKmpSources(
            """
            typedef struct class {
                int when;
                int when_;
            } class;

            typedef enum sealed {
                when = 1,
                when_ = 2
            } sealed;

            // Struct-by-value JVM downcalls still need the FFM layout companion
            // (M5.2 rewrites function emission); the keyword-safety contract is
            // exercised through a pointer parameter here.
            int fun(class* class, int when, int when_);
            int fun_(int value);
            """.trimIndent(),
        )

        compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.probe",
            probeSource =
                """
                package sample.probe

                import sample.bindings.class_

                fun verifyKeywordNames(): Class<*> = class_::class.java
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "verifyKeywordNames",
        )

        generated.common shouldContain "expect interface class_"
        generated.common shouldContain "var when_: Int"
        generated.common shouldContain "var when__2: Int"
        generated.common shouldContain "typealias sealed_"
        generated.common shouldContain "const val when_"
        generated.common shouldContain "const val when__2"
        generated.common shouldContain "expect fun fun_(class_: class_?, when_: Int, when__2: Int): Int"
        generated.common shouldContain "expect fun fun__2(value: Int): Int"
        generated.jvm shouldContain "actual fun fun_(class_: class_?, when_: Int, when__2: Int): Int"
        generated.native shouldContain "webgpu.native.`fun`("
        generated.native shouldContain "this.`when`"
        generated.android shouldContain "actual var when_: Int"
        generated.android shouldContain "actual var when__2: Int"
        generated.android shouldContain "get() = buffer.readInt(0uL)"
        generated.android shouldContain "set(value) { buffer.writeInt(value, 0uL) }"
        generated.android shouldContain "get() = buffer.readInt(4uL)"
    }

    "opaque handles use their planned public names in fields and functions" {
        val generated = generateKmpSources(
            """
            typedef struct classImpl *class;
            typedef struct Holder { class value; } Holder;
            void use(class value);
            """.trimIndent(),
        )

        generated.common shouldContain "expect value class class_(val handler: NativeAddress)"
        generated.common shouldContain "var value: class_?"
        generated.common shouldContain "expect fun use(value: class_?): Unit"
        generated.common shouldNotContain "var value: NativeAddress?"
        generated.common shouldNotContain "expect fun use(value: NativeAddress?"

        compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.probe",
            probeSource =
                """
                package sample.probe

                import sample.bindings.Holder
                import sample.bindings.class_

                fun verifyOpaqueHandleNames(): Array<Class<*>> = arrayOf(
                    Holder::class.java,
                    class_::class.java,
                )
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "verifyOpaqueHandleNames",
        )
    }

    "Native cinterop record classifiers keep escaped raw C names" {
        val generated = generateKmpSources(
            """
            typedef struct class { int when; } class;
            typedef class ClassAlias;
            typedef struct Holder { ClassAlias nested; } Holder;
            typedef void (*TakeClass)(class value);
            """.trimIndent(),
        )

        generated.common shouldContain "expect interface class_"
        generated.native shouldContain "sizeOf<webgpu.native.`class`>()"
        generated.native shouldContain "CValue<webgpu.native.`class`>"
        generated.native shouldContain "reinterpret<webgpu.native.`class`>()"
        generated.native shouldContain "val size_nested = sizeOf<webgpu.native.`class`>().toLong()"
        generated.native shouldContain
            "private val TakeClassTrampoline = staticCFunction<CValue<webgpu.native.`class`>, Unit>"
        generated.native shouldNotContain "webgpu.native.class_"
    }

    "Native inline record copies import cinterop get and set operators" {
        val generated = generateKmpSources(
            """
            typedef struct Nested { int value; } Nested;
            typedef struct Holder { Nested nested; } Holder;
            """.trimIndent(),
        )

        generated.native shouldContain "import kotlinx.cinterop.get\n"
        generated.native shouldContain "import kotlinx.cinterop.set\n"
        generated.native shouldContain "destBytes[i.toInt()] = srcBytes[i.toInt()]"
    }

    "Native inline record copies invoke aliased cinterop operators explicitly" {
        val generated = generateKmpSources(
            """
            typedef struct get { int value; } get;
            typedef struct set { int value; } set;
            typedef struct Nested { int value; } Nested;
            typedef struct Holder { Nested nested; } Holder;
            """.trimIndent(),
        )

        generated.native shouldContain "import kotlinx.cinterop.get as Kffiget"
        generated.native shouldContain "import kotlinx.cinterop.set as Kffiset"
        generated.native shouldContain
            "destBytes.Kffiset(i.toInt(), srcBytes.Kffiget(i.toInt()))"
        generated.native shouldNotContain "destBytes[i.toInt()] = srcBytes[i.toInt()]"
    }

    "runtime classifiers are aliased whenever a C classifier shadows them" {
        val runtimeSymbolClass = Class.forName("org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol")
        val preferredName = runtimeSymbolClass.getMethod("getPreferredName")
        val qualifiedName = runtimeSymbolClass.getMethod("getQualifiedName")
        val sourceSets = runtimeSymbolClass.getMethod("getSourceSets")
        val cases = runtimeSymbolClass.enumConstants.mapNotNull { symbol ->
            val preferred = preferredName.invoke(symbol) as String
            if (!preferred.matches(Regex("[A-Za-z_][A-Za-z0-9_]*"))) return@mapNotNull null
            val sourceSetNames = (sourceSets.invoke(symbol) as Set<*>)
                .mapTo(mutableSetOf()) { (it as Enum<*>).name }
            val sourceSet = listOf(SourceSet.JVM, SourceSet.NATIVE, SourceSet.COMMON, SourceSet.ANDROID)
                .firstOrNull { it.name in sourceSetNames }
                ?: return@mapNotNull null
            if (sourceSet == SourceSet.ANDROID && (symbol as Enum<*>).name in NO_LONGER_EMITTED_ANDROID_SYMBOLS) {
                return@mapNotNull null
            }
            RuntimeImportCase(
                qualifiedName = qualifiedName.invoke(symbol) as String,
                preferredName = preferred,
                sourceSet = sourceSet,
            )
        }
        val header =
            cases.joinToString("\n") { case ->
                "typedef struct ${case.preferredName} { int value; } ${case.preferredName};"
            } + "\ntypedef struct RuntimeAliasExercise { reinterpret nested; } RuntimeAliasExercise;" +
                "\ntypedef union RuntimeAndroidAliasExercise { int value; } RuntimeAndroidAliasExercise;"
        val generated = generateKmpSources(header)

        cases.forEach { case ->
            val source = case.sourceSet.source(generated)
            val alias = "Kffi${case.preferredName}"
            // The JVM imports FFM classifiers only when the emitted code uses them
            // (structs are memory-backed since M5.1); every import that IS emitted
            // must still be aliased away from the shadowing C classifier.
            if (case.qualifiedName in source) {
                source shouldContain "import ${case.qualifiedName} as $alias"
                source shouldNotContain "import ${case.qualifiedName}\n"
            }
        }
        generated.native shouldContain ".Kffireinterpret<"
        generated.native shouldContain ".Kffipointed"
        generated.native shouldContain ".Kffiptr"
        generated.native shouldContain ".KffiuseContents {"
        generated.android shouldContain "actual interface RuntimeAliasExercise"
        generated.android shouldContain "actual interface RuntimeAndroidAliasExercise"
        generated.android shouldContain
            "class ByReference(val handle: KffiNativeAddress = KffiNativeAddress(0L)) : RuntimeAliasExercise {"
        generated.android shouldContain
            "class ByValue(val handle: KffiNativeAddress = KffiNativeAddress(0L)) : RuntimeAndroidAliasExercise {"
        generated.android shouldContain "private val buffer: KffiMemoryBuffer by lazy { KffiMemoryBuffer(handle, 4uL) }"
        generated.android shouldContain "get() = reinterpret.ByValue(KffiNativeAddress(handle.rawValue + 0L))"
    }
})

private val NO_LONGER_EMITTED_ANDROID_SYMBOLS = setOf(
    "JNA_POINTER",
    "JNA_STRUCTURE",
    "JNA_UNION",
    "JNA_CALLBACK_REFERENCE",
    "JVM_FIELD",
)

private data class RuntimeImportCase(
    val qualifiedName: String,
    val preferredName: String,
    val sourceSet: SourceSet,
)

private enum class SourceSet {
    COMMON,
    JVM,
    NATIVE,
    ANDROID;

    fun source(generated: GeneratedKmpSources): String = when (this) {
        COMMON -> generated.common
        JVM -> generated.jvm
        NATIVE -> generated.native
        ANDROID -> generated.android
    }
}
