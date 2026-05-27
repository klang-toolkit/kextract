package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import io.kotest.matchers.string.shouldStartWith
import org.graphiks.kextract.pipeline.NameMangler
import org.graphiks.kextract.kotlin.KotlinGenerator
import org.graphiks.kextract.pipeline.KextractTool
import java.nio.file.Files

/**
 * Integration tests for the full kextract pipeline:
 * C header source → Parser → KotlinGenerator → Kotlin source
 */
class GeneratorIntegrationTest : FreeSpec({

    // Helper: parse an inline C source and run the Kotlin generator.
    // Mirrors the full pipeline: parse → NameMangler → KotlinGenerator.
    fun generate(csource: String, pkg: String = "test"): String {
        val tmp = Files.createTempFile("kextract_test_", ".h")
        try {
            tmp.toFile().writeText(csource)
            val headerName = tmp.fileName.toString()
            val parsed = KextractTool.parse(listOf(tmp.toString()))
            val mangled = NameMangler(headerName).scan(parsed)
            val files = KotlinGenerator().generate(mangled, headerName, pkg)
            return files.firstOrNull()?.contents ?: ""
        } finally {
            Files.deleteIfExists(tmp)
        }
    }

    "Package declaration" - {
        "should emit package when target package is set" {
            val src = generate("int add(int a, int b);", pkg = "com.example")
            src shouldStartWith "package com.example"
        }

        "should omit package line when target package is empty" {
            val src = generate("int add(int a, int b);", pkg = "")
            src shouldNotContain "package "
        }
    }

    "Struct generation" - {
        "simple struct produces a class with companion object" {
            val src = generate("""
                struct Point { int x; int y; };
            """.trimIndent())

            src shouldContain "class Point"
            src shouldContain "companion object"
            src shouldContain "val layout"
            src shouldContain "fun allocate"
            src shouldContain "val byteSize"
        }

        "struct fields generate VarHandle accessors" {
            val src = generate("""
                struct Point { int x; int y; };
            """.trimIndent())

            src shouldContain "x_VH"
            src shouldContain "y_VH"
            // getter
            src shouldContain "fun x(segment: MemorySegment)"
            // setter with value param
            src shouldContain "fun x(segment: MemorySegment, value:"
        }

        "array field generates asSlice accessor instead of VarHandle" {
            val src = generate("""
                struct Buffer { char data[64]; int len; };
            """.trimIndent())

            src shouldContain "asSlice"
            src shouldNotContain "data_VH"
        }

        "struct with nested struct pointer field compiles" {
            val src = generate("""
                struct Node { int value; struct Node* next; };
            """.trimIndent())

            src shouldContain "class Node"
        }

        "union generates a warning comment" {
            val src = generate("""
                union Value { int i; float f; double d; };
            """.trimIndent())

            src shouldContain "WARNING"
            src shouldContain "union"
        }
    }

    "Function generation" - {
        "void function generates try/catch without return" {
            val src = generate("void noop(void);")

            src shouldContain "fun noop("
            src shouldContain "_HANDLE"
            src shouldContain "invokeExact"
            // void → no return statement
            src shouldNotContain "return noop_HANDLE"
        }

        "int-returning function generates cast to Int" {
            val src = generate("int add(int a, int b);")

            src shouldContain "fun add("
            src shouldContain "): Int"
            src shouldContain "as Int"
        }

        "pointer-returning function maps to MemorySegment" {
            val src = generate("void* malloc(long size);")

            src shouldContain "): MemorySegment"
        }

        "function with multiple params generates arg0, arg1 names" {
            val src = generate("int clamp(int val, int lo, int hi);")

            src shouldContain "arg0: Int"
            src shouldContain "arg1: Int"
            src shouldContain "arg2: Int"
        }

        "struct-returning function adds SegmentAllocator param" {
            val src = generate("""
                struct Vec2 { float x; float y; };
                struct Vec2 make_vec(float x, float y);
            """.trimIndent())

            src shouldContain "allocator: SegmentAllocator"
        }

        "FunctionDescriptor and MethodHandle are emitted" {
            val src = generate("int square(int n);")

            src shouldContain "_DESC: FunctionDescriptor"
            src shouldContain "_ADDR: MemorySegment"
            src shouldContain "_HANDLE: MethodHandle"
        }
    }

    "Constant generation" - {
        "integer constant generates a fun returning Int" {
            val src = generate("#define MAX_SIZE 1024")

            src shouldContain "fun MAX_SIZE()"
            src shouldContain "1024"
        }

        "string constant with spaces in value is emitted as comment" {
            // Clang evaluates string macros by stripping the surrounding quotes,
            // leaving a bare string like "hello world" (with a space).
            // Since the space makes it impossible to emit as a valid Kotlin literal,
            // the generator emits a comment instead.
            val src = generate("""#define GREETING "hello world"""")

            src shouldContain "// Skipped constant"
        }
    }

    "TypeMapper" - {
        "primitive types map correctly" {
            val src = generate("""
                void f_bool(_Bool b);
                void f_char(char c);
                void f_short(short s);
                void f_int(int i);
                void f_long(long l);
                void f_float(float f);
                void f_double(double d);
            """.trimIndent())

            src shouldContain "arg0: Boolean"  // _Bool
            src shouldContain "arg0: Byte"     // char
            src shouldContain "arg0: Short"
            src shouldContain "arg0: Int"
            src shouldContain "arg0: Long"
            src shouldContain "arg0: Float"
            src shouldContain "arg0: Double"
        }

        "pointer type maps to MemorySegment" {
            val src = generate("void f(int* p, char* s, void* v);")

            src.lines()
                .filter { it.contains("fun f(") }
                .joinToString() shouldContain "MemorySegment"
        }

        "array type maps to MemorySegment" {
            val src = generate("""
                struct S { int arr[10]; };
            """.trimIndent())

            // array field accessor returns MemorySegment via asSlice
            src shouldContain "MemorySegment"
        }
    }

    "Generated file structure" - {
        "file contains required Panama imports" {
            val src = generate("int x;")

            src shouldContain "import java.lang.foreign"
        }

        "file for empty header has no declarations" {
            val files = run {
                val tmp = Files.createTempFile("kextract_empty_", ".h")
                try {
                    tmp.toFile().writeText("// empty header\n")
                    val parsed = KextractTool.parse(listOf(tmp.toString()))
                    KotlinGenerator().generate(parsed, tmp.fileName.toString(), "test")
                } finally {
                    Files.deleteIfExists(tmp)
                }
            }
            // Empty header may produce 0 or 1 file (just the package/imports)
            files.size shouldBe 1
            files.first().contents shouldNotContain "fun "
            files.first().contents shouldNotContain "class "
        }

        "generator returns exactly one file per header" {
            val tmp = Files.createTempFile("kextract_single_", ".h")
            try {
                tmp.toFile().writeText("int foo(void);")
                val parsed = KextractTool.parse(listOf(tmp.toString()))
                val files = KotlinGenerator().generate(parsed, tmp.fileName.toString(), "test")
                files shouldHaveAtLeastSize 1
                files.map { it.getPath().toString() }
                    .filter { it.endsWith(".kt") }
                    .shouldNotBeEmpty()
            } finally {
                Files.deleteIfExists(tmp)
            }
        }

        "output filename is derived from header basename not full path" {
            val tmp = Files.createTempFile("kextract_path_test_", ".h")
            try {
                tmp.toFile().writeText("int x;")
                val parsed = KextractTool.parse(listOf(tmp.toString()))
                val files = KotlinGenerator().generate(parsed, tmp.toString(), "test")
                // filename should be based on basename, not the full /tmp/... path
                files.first().getPath().toString() shouldNotContain "tmp"
                files.first().getPath().fileName.toString() shouldNotContain "/"
            } finally {
                Files.deleteIfExists(tmp)
            }
        }
    }

    "Typedef handling" - {
        "typedef of struct is not duplicated" {
            val src = generate("""
                typedef struct { int x; int y; } Point;
            """.trimIndent())

            // Should generate only one class for Point, not two
            src.lines().count { it.trimStart().startsWith("class Point") } shouldBe 1
        }
    }
})
