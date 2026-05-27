package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.kotlin.builders.KotlinObjCClassBuilder.Companion.kotlinName
import org.graphiks.kextract.kotlin.builders.KotlinObjCClassBuilder.Companion.returnLayout
import org.graphiks.kextract.kotlin.builders.KotlinObjCClassBuilder.Companion.returnTypeKotlin
import org.graphiks.kextract.kotlin.utils.TypeMapper

/**
 * Generates a Kotlin interface wrapper for an Objective-C @protocol declaration.
 *
 * @required methods → abstract fun in the interface.
 * @optional methods → fun with a default body that throws [UnsupportedOperationException].
 *
 * Example output for `@protocol NSCopying`:
 * ```kotlin
 * interface NSCopying {
 *     fun copyWithZone(zone: MemorySegment): MemorySegment
 *
 *     // @optional
 *     fun description(): MemorySegment =
 *         throw UnsupportedOperationException("Optional ObjC method 'description' not implemented")
 * }
 * ```
 */
class KotlinObjCProtocolBuilder(
    private val builder: SourceBuilder,
    @Suppress("unused") private val toplevel: KotlinToplevelBuilder
) {

    fun visitProtocol(decl: Declaration.ObjCProtocol) {
        if (Skip.isPresent(decl)) return

        val protoName = decl.name()

        // KDoc header
        builder.appendLine("/**")
        builder.appendLine(" * Kotlin/JVM interface for Objective-C protocol: $protoName")
        if (decl.protocols().isNotEmpty())
            builder.appendLine(" * Inherits protocols: ${decl.protocols().joinToString()}")
        builder.appendLine(" */")

        // Interface declaration with super-protocols as Kotlin supertypes
        val superProtos = decl.protocols()
        val superExpr = if (superProtos.isEmpty()) "" else " : ${superProtos.joinToString(", ")}"
        builder.appendLine("interface $protoName$superExpr {")
        builder.indent()

        for (method in decl.methods()) {
            emitMethod(method)
        }

        for (prop in decl.properties()) {
            emitProperty(prop)
        }

        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitMethod(method: Declaration.ObjCMethod) {
        val selector = method.selector()
        val params   = method.parameters()
        val retKotlin = returnTypeKotlin(method.returnType())

        val paramList = params.mapIndexed { i, p ->
            val pName = p.name().ifEmpty { "arg$i" }
            val pType = TypeMapper.map(p.type())
            "$pName: $pType"
        }.joinToString(", ")

        val retDecl = if (retKotlin == "Unit") "" else ": $retKotlin"

        if (method.isOptional()) {
            // Default implementation: throw UnsupportedOperationException
            builder.appendLine("// @optional")
            builder.appendLine("fun ${kotlinName(selector)}($paramList)$retDecl =")
            builder.indent()
            builder.appendLine("throw UnsupportedOperationException(\"Optional ObjC method '$selector' not implemented\")")
            builder.unindent()
        } else {
            // @required → abstract
            builder.appendLine("fun ${kotlinName(selector)}($paramList)$retDecl")
        }
        builder.appendLine()
    }

    private fun emitProperty(prop: Declaration.ObjCProperty) {
        val propName  = prop.name()
        val retKotlin = returnTypeKotlin(prop.type())
        val getter    = prop.getterSelector()

        builder.appendLine("// @property $propName")
        builder.appendLine("fun ${kotlinName(getter)}(): $retKotlin")

        if (!prop.isReadOnly()) {
            val setter    = prop.setterSelector()
            val paramType = TypeMapper.map(prop.type())
            builder.appendLine("fun ${kotlinName(setter.removeSuffix(":"))}(value: $paramType)")
        }
        builder.appendLine()
    }
}
