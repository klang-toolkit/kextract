// src/main/kotlin/org/openjdk/kextract/kotlin/builders/KotlinHeaderBuilder.kt
package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.pipeline.LayoutUtils
import org.graphiks.kextract.kotlin.utils.TypeMapper

/**
 * Generates Kotlin code for functions, variables, and constants.
 */
class KotlinHeaderBuilder(private val builder: SourceBuilder, private val toplevel: KotlinToplevelBuilder) {

    fun visitFunction(decl: Declaration.Function) {
        val name = toplevel.javaName(decl.name())
        val returnType = TypeMapper.map(decl.type().returnType())
        val returnsStruct = isStructType(decl.type().returnType())
        val params = paramString(decl, returnsStruct)
        val allocatorPrefix = if (returnsStruct) "allocator, " else ""
        val paramNames = allocatorPrefix + decl.type().argumentTypes().indices.joinToString(", ") { "arg$it" }

        // KDoc
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : ${decl.name()} ${decl.type()}")
        builder.appendLine(" */")

        // Function descriptor, address and handle (toplevel properties)
        builder.appendLine("private val ${name}_DESC: FunctionDescriptor = ${functionDescriptorString(decl)}")
        val lookupExpr = if (toplevel.hasLookup) "LOOKUP" else "SymbolLookup.loaderLookup()"
        builder.appendLine(
            "private val ${name}_ADDR: MemorySegment = $lookupExpr.findOrThrow(\"${toplevel.lookupName(decl)}\")"
        )
        builder.appendLine("private val ${name}_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(${name}_ADDR, ${name}_DESC)")
        builder.appendLine()

        // Function
        val isVoid = returnType == "Unit"
        builder.appendLine("fun ${name}(${params}): ${returnType} {")
        builder.indent()
        builder.appendLine("try {")
        builder.indent()
        if (isVoid) {
            builder.appendLine("${name}_HANDLE.invokeExact(${paramNames})")
        } else {
            builder.appendLine("return ${name}_HANDLE.invokeExact(${paramNames}) as ${returnType}")
        }
        builder.unindent()
        builder.appendLine("} catch (ex: Error) {")
        builder.indent()
        builder.appendLine("throw ex")
        builder.unindent()
        builder.appendLine("} catch (ex: RuntimeException) {")
        builder.indent()
        builder.appendLine("throw ex")
        builder.unindent()
        builder.appendLine("} catch (ex: Throwable) {")
        builder.indent()
        builder.appendLine("throw AssertionError(\"should not reach here\", ex)")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun isStructType(type: Type): Boolean = when (type) {
        is Type.Declared -> {
            val tree = type.tree()
            tree is Declaration.Scoped &&
                (tree.kind() == Declaration.Scoped.Kind.STRUCT ||
                 tree.kind() == Declaration.Scoped.Kind.UNION)
        }
        is Type.Delegated -> when (type.kind()) {
            Type.Delegated.Kind.TYPEDEF -> isStructType(type.type())
            else -> false
        }
        else -> false
    }

    fun visitVariable(decl: Declaration.Variable) {
        val name = toplevel.javaName(decl.name())
        val type = TypeMapper.map(decl.type())

        // KDoc
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : ${decl.name()} ${decl.type()}")
        builder.appendLine(" */")

        // Variable Layout, Segment and Handle (toplevel properties)
        builder.appendLine("private val ${name}_LAYOUT: ValueLayout = ${layoutString(decl.type())}")
        val varLookupExpr = if (toplevel.hasLookup) "LOOKUP" else "SymbolLookup.loaderLookup()"
        builder.appendLine(
            "private val ${name}_SEGMENT: MemorySegment = $varLookupExpr.findOrThrow(\"${toplevel.lookupName(decl)}\")"
        )
        builder.appendLine("private val ${name}_VH: VarHandle = ${name}_LAYOUT.varHandle()")
        builder.appendLine()

        // Property (getter/setter)
        builder.appendLine("var ${name}: ${type}")
        builder.indent()
        builder.appendLine("get() = ${name}_VH.get(${name}_SEGMENT)")
        builder.appendLine("set(value) = ${name}_VH.set(${name}_SEGMENT, value)")
        builder.unindent()
        builder.appendLine()
    }

    fun visitConstant(decl: Declaration.Constant) {
        val name = toplevel.javaName(decl.name())
        val value = decl.value()

        // Skip constants whose values are not valid Kotlin literals
        // (e.g. unresolvable macros that produce values like "$DARWIN_EXTSN")
        val valueStr = value.toString()
        if (valueStr.contains('$') || valueStr.contains(' ') ||
            (!isValidKotlinLiteral(value) && valueStr.any { it.isLetter() && it != 'L' && it != 'f' && it != 'F' })) {
            // Emit as comment only
            builder.appendLine("// Skipped constant ${decl.name()}: value '$valueStr' is not a valid Kotlin literal")
            builder.appendLine()
            return
        }

        val type = TypeMapper.map(decl.type())

        // KDoc
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : #define ${decl.name()} ${value}")
        builder.appendLine(" */")

        // Constant
        builder.appendLine("fun ${name}(): ${type} = ${value}")
        builder.appendLine()
    }

    private fun isValidKotlinLiteral(value: Any): Boolean = when (value) {
        is Int, is Long, is Float, is Double -> true
        is String -> true
        else -> false
    }

    // --- Utilities (call Java LayoutUtils) ---
    fun layoutString(type: Type): String = LayoutUtils.layoutString(type)

    fun functionDescriptorString(decl: Declaration.Function): String =
        LayoutUtils.functionDescriptorString(decl.type())

    private fun paramString(decl: Declaration.Function, prependAllocator: Boolean = false): String {
        val args = decl.type().argumentTypes().mapIndexed { index, arg ->
            val type = TypeMapper.map(arg)
            "arg${index}: ${type}"
        }
        return if (prependAllocator) {
            listOf("allocator: SegmentAllocator") + args
        } else {
            args
        }.joinToString(", ")
    }
}
