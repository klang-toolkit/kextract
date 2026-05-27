package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.kotlin.utils.TypeMapper

/**
 * Generates a Kotlin class wrapper for an Objective-C @interface declaration.
 *
 * Example output for `@interface NSString : NSObject`:
 * ```kotlin
 * open class NSString(val ptr: MemorySegment) {
 *     companion object {
 *         private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSString") }
 *         fun stringWithUTF8String(cString: MemorySegment): MemorySegment { ... }
 *     }
 *     fun length(): Long { ... }
 * }
 * ```
 */
class KotlinObjCClassBuilder(
    private val builder: SourceBuilder,
    private val toplevel: KotlinToplevelBuilder
) {

    fun visitClass(decl: Declaration.ObjCClass) {
        if (Skip.isPresent(decl)) return

        val className = decl.name()
        val superClass = decl.superClass()

        // KDoc header
        builder.appendLine("/**")
        builder.appendLine(" * Kotlin/JVM wrapper for Objective-C class: $className")
        if (superClass != null) builder.appendLine(" * Superclass: $superClass")
        if (decl.protocols().isNotEmpty())
            builder.appendLine(" * Protocols: ${decl.protocols().joinToString()}")
        builder.appendLine(" */")

        // Class declaration (open so it can be subclassed to mirror ObjC inheritance)
        val superExpr = if (superClass != null) " : $superClass(ptr)" else ""
        builder.appendLine("open class $className(val ptr: MemorySegment)$superExpr {")
        builder.indent()

        // Companion object for class-level methods and the Class reference
        builder.appendLine("companion object {")
        builder.indent()
        builder.appendLine("private val _class: MemorySegment by lazy { ObjCRuntime.getClass(\"$className\") }")
        builder.appendLine()

        // Class methods (+)
        for (method in decl.methods().filter { it.isClassMethod() }) {
            emitMethod(method, receiver = "_class")
        }

        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()

        // Instance methods (-)
        for (method in decl.methods().filter { !it.isClassMethod() }) {
            emitMethod(method, receiver = "ptr")
        }

        // Properties
        for (prop in decl.properties()) {
            emitProperty(prop)
        }

        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    internal fun emitMethod(method: Declaration.ObjCMethod, receiver: String) {
        val selector = method.selector()
        val params = method.parameters()
        val retType = method.returnType()
        val retKotlin = returnTypeKotlin(retType)
        val retLayout = returnLayout(retType)

        val paramList = params.mapIndexed { i, p ->
            val pName = p.name().ifEmpty { "arg$i" }
            val pType = TypeMapper.map(p.type())
            "$pName: $pType"
        }.joinToString(", ")

        val retDecl = if (retKotlin == "Unit") ": Unit" else ": $retKotlin"

        builder.appendLine("fun ${kotlinName(selector)}($paramList)$retDecl {")
        builder.indent()
        builder.appendLine("val sel = ObjCRuntime.sel(\"$selector\")")

        val argsList = params.mapIndexed { i, p -> p.name().ifEmpty { "arg$i" } }.joinToString(", ")
        val argsExpr = if (argsList.isEmpty()) "" else ", $argsList"

        if (retKotlin == "Unit") {
            builder.appendLine("ObjCRuntime.msgSend(null, $receiver, sel$argsExpr)")
        } else {
            builder.appendLine("return ObjCRuntime.msgSend($retLayout, $receiver, sel$argsExpr) as $retKotlin")
        }
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitProperty(prop: Declaration.ObjCProperty) {
        val propName = prop.name()
        val retKotlin = returnTypeKotlin(prop.type())
        val retLayout = returnLayout(prop.type())
        val getter = prop.getterSelector()

        builder.appendLine("// @property $propName")
        builder.appendLine("fun ${kotlinName(getter)}(): $retKotlin {")
        builder.indent()
        builder.appendLine("val sel = ObjCRuntime.sel(\"$getter\")")
        if (retKotlin == "Unit") {
            builder.appendLine("ObjCRuntime.msgSend(null, ptr, sel)")
        } else {
            builder.appendLine("return ObjCRuntime.msgSend($retLayout, ptr, sel) as $retKotlin")
        }
        builder.unindent()
        builder.appendLine("}")

        if (!prop.isReadOnly()) {
            val setter = prop.setterSelector()
            val paramType = TypeMapper.map(prop.type())
            builder.appendLine("fun ${kotlinName(setter.removeSuffix(":"))}(value: $paramType) {")
            builder.indent()
            builder.appendLine("val sel = ObjCRuntime.sel(\"$setter\")")
            builder.appendLine("ObjCRuntime.msgSend(null, ptr, sel, value)")
            builder.unindent()
            builder.appendLine("}")
        }
        builder.appendLine()
    }

    companion object {
        /** Maps an ObjC return type to the Kotlin type name. */
        fun returnTypeKotlin(type: Type): String = when {
            type is Type.Primitive && type.kind() == Type.Primitive.Kind.Void -> "Unit"
            else -> TypeMapper.map(type)
        }

        /** Returns the Panama MemoryLayout expression for the return type (null for void). */
        fun returnLayout(type: Type): String = when {
            type is Type.Primitive && type.kind() == Type.Primitive.Kind.Void -> "null"
            type is Type.Primitive -> when (type.kind()) {
                Type.Primitive.Kind.Bool     -> "ValueLayout.JAVA_BOOLEAN"
                Type.Primitive.Kind.Char     -> "ValueLayout.JAVA_BYTE"
                Type.Primitive.Kind.Short    -> "ValueLayout.JAVA_SHORT"
                Type.Primitive.Kind.Int      -> "ValueLayout.JAVA_INT"
                Type.Primitive.Kind.Long,
                Type.Primitive.Kind.LongLong -> "ValueLayout.JAVA_LONG"
                Type.Primitive.Kind.Float    -> "ValueLayout.JAVA_FLOAT"
                Type.Primitive.Kind.Double   -> "ValueLayout.JAVA_DOUBLE"
                else                         -> "ValueLayout.ADDRESS"
            }
            else -> "ValueLayout.ADDRESS"  // pointers, ObjC objects, structs, etc.
        }

        /**
         * Converts an ObjC selector to a valid Kotlin function name.
         * "stringWithUTF8String:" → "stringWithUTF8String"
         * "setLength:" → "setLength"
         */
        fun kotlinName(selector: String): String =
            selector.replace(":", "_").trimEnd('_')
    }
}
