package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.kotlin.builders.KotlinObjCClassBuilder.Companion.kotlinName
import org.graphiks.kextract.kotlin.builders.KotlinObjCClassBuilder.Companion.returnLayout
import org.graphiks.kextract.kotlin.builders.KotlinObjCClassBuilder.Companion.returnTypeKotlin
import org.graphiks.kextract.kotlin.utils.TypeMapper

/**
 * Generates Kotlin extension functions for an Objective-C @category declaration.
 *
 * Each method in the category becomes an extension function on the extended class.
 * Properties become extension getter (and optional setter) functions.
 *
 * Example output for `@interface NSString (MyCategory)`:
 * ```kotlin
 * // Category: MyCategory on NSString
 * fun NSString.myMethod(arg: MemorySegment): MemorySegment {
 *     val sel = ObjCRuntime.sel("myMethod:")
 *     return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, arg) as MemorySegment
 * }
 * ```
 */
class KotlinObjCCategoryBuilder(
    private val builder: SourceBuilder,
    @Suppress("unused") private val toplevel: KotlinToplevelBuilder
) {

    fun visitCategory(decl: Declaration.ObjCCategory) {
        if (Skip.isPresent(decl)) return

        val extClass    = decl.extendedClass()
        val catName     = decl.categoryName()

        builder.appendLine("// ── Category: $catName on $extClass ─────────────────────────────────────────")
        builder.appendLine()

        for (method in decl.methods()) {
            emitMethod(extClass, method)
        }

        for (prop in decl.properties()) {
            emitProperty(extClass, prop)
        }
    }

    private fun emitMethod(extClass: String, method: Declaration.ObjCMethod) {
        val selector  = method.selector()
        val params    = method.parameters()
        val retKotlin = returnTypeKotlin(method.returnType())
        val retLayout = returnLayout(method.returnType())

        val paramList = params.mapIndexed { i, p ->
            val pName = p.name().ifEmpty { "arg$i" }
            val pType = TypeMapper.map(p.type())
            "$pName: $pType"
        }.joinToString(", ")

        val retDecl = if (retKotlin == "Unit") ": Unit" else ": $retKotlin"

        // Class methods on a category get a plain function (not extension) since we
        // don't have the class object. Use a companion-like top-level function instead.
        val receiver = if (method.isClassMethod()) {
            builder.appendLine("// Class method +[$extClass $selector]")
            null
        } else {
            extClass
        }

        if (receiver != null) {
            builder.appendLine("fun $receiver.${kotlinName(selector)}($paramList)$retDecl {")
        } else {
            builder.appendLine("fun ${extClass}_${kotlinName(selector)}($paramList)$retDecl {")
        }
        builder.indent()
        builder.appendLine("val sel = ObjCRuntime.sel(\"$selector\")")

        val argsList = params.mapIndexed { i, p -> p.name().ifEmpty { "arg$i" } }.joinToString(", ")
        val argsExpr = if (argsList.isEmpty()) "" else ", $argsList"

        val receiverExpr = if (method.isClassMethod()) "ObjCRuntime.getClass(\"$extClass\")" else "ptr"

        if (retKotlin == "Unit") {
            builder.appendLine("ObjCRuntime.msgSend(null, $receiverExpr, sel$argsExpr)")
        } else {
            builder.appendLine("return ObjCRuntime.msgSend($retLayout, $receiverExpr, sel$argsExpr) as $retKotlin")
        }
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitProperty(extClass: String, prop: Declaration.ObjCProperty) {
        val propName  = prop.name()
        val retKotlin = returnTypeKotlin(prop.type())
        val retLayout = returnLayout(prop.type())
        val getter    = prop.getterSelector()

        builder.appendLine("// @property $propName")
        builder.appendLine("fun $extClass.${kotlinName(getter)}(): $retKotlin {")
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
            val setter    = prop.setterSelector()
            val paramType = TypeMapper.map(prop.type())
            builder.appendLine("fun $extClass.${kotlinName(setter.removeSuffix(":"))}(value: $paramType) {")
            builder.indent()
            builder.appendLine("val sel = ObjCRuntime.sel(\"$setter\")")
            builder.appendLine("ObjCRuntime.msgSend(null, ptr, sel, value)")
            builder.unindent()
            builder.appendLine("}")
        }
        builder.appendLine()
    }
}
