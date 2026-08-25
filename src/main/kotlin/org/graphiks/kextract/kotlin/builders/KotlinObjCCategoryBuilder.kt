package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.kotlin.builders.KotlinObjCClassBuilder.Companion.kotlinName

/**
 * Generates Kotlin extension functions for an Objective-C @category declaration.
 *
 * Instance methods (-) become extension functions on the extended class.
 * Class methods (+) become top-level functions named `<ClassName>_<methodName>` that
 * call `ObjCRuntime.getClass("ClassName")` directly — extension functions on the
 * companion object cannot access its private `_class` lazy property.
 * Properties become extension getter (and optional setter) functions.
 *
 * Example output for `@interface NSString (MyCategory)`:
 * ```kotlin
 * // Category: MyCategory on NSString
 * // Instance method: -[NSString myMethod:]
 * fun NSString.myMethod(arg: MemorySegment): MemorySegment {
 *     val sel = ObjCRuntime.sel("myMethod:")
 *     return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, arg) as MemorySegment
 * }
 * // Class method: +[NSString stringFromInt:]
 * fun NSString_stringFromInt(value: Int): MemorySegment {
 *     val sel = ObjCRuntime.sel("stringFromInt:")
 *     val cls = ObjCRuntime.getClass("NSString")
 *     return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
 * }
 * ```
 */
class KotlinObjCCategoryBuilder(
    private val builder: SourceBuilder,
    @Suppress("unused") private val toplevel: KotlinToplevelBuilder,
    /**
     * Mutable set of Kotlin method/property signatures already emitted by the extended
     * class's own interface AND by any other category on the same class.  Shared across
     * all [KotlinObjCCategoryBuilder] instances for the same extended class to avoid
     * "conflicting overloads".
     */
    private val existingSignatures: MutableSet<String> = mutableSetOf()
) {
    private val typeLowerer = ObjCTypeLowerer(toplevel)

    fun visitCategory(decl: Declaration.ObjCCategory) {
        if (Skip.isPresent(decl)) return

        val extClass = decl.extendedClass()
        val catName  = decl.categoryName()

        builder.appendLine("// ── Category: $catName on $extClass ─────────────────────────────────────────")
        builder.appendLine()

        val (classMethods, instanceMethods) = decl.methods().partition { it.isClassMethod() }

        for (method in instanceMethods) {
            val sig = kotlinName(method.selector())
            if (sig in existingSignatures) continue
            existingSignatures.add(sig)
            emitInstanceMethod(extClass, method)
        }

        for (method in classMethods) {
            emitClassMethod(extClass, method)
        }

        for (prop in decl.properties()) {
            val getterSig = kotlinName(prop.getterSelector())
            if (getterSig in existingSignatures) continue
            existingSignatures.add(getterSig)
            emitProperty(extClass, prop)
        }
    }

    /**
     * Emits an instance (-) method as an extension function on [extClass].
     * The ObjC message is sent to `ptr` (the wrapped MemorySegment).
     */
    private fun emitInstanceMethod(extClass: String, method: Declaration.ObjCMethod) {
        val selector  = method.selector()
        val params    = method.parameters()
        val returnLowering = typeLowerer.lower(method.returnType())
        val retKotlin = returnLowering.kotlinType
        val retSpelling = method.returnTypeSpelling()

        val paramList = params.mapIndexed { i, p ->
            val pName = KotlinObjCClassBuilder.escapeIdentifier(p.name().ifEmpty { "arg$i" })
            val pType = typeLowerer.lower(p.type()).kotlinType
            "$pName: $pType"
        }.joinToString(", ")

        val retDecl  = if (retKotlin == "Unit") ": Unit" else ": $retKotlin"

        val argsList = params.mapIndexed { i, p ->
            val name = KotlinObjCClassBuilder.escapeIdentifier(p.name().ifEmpty { "arg$i" })
            typeLowerer.lower(p.type()).lowerArgument(name)
        }.joinToString(", ")
        val argsExpr = if (argsList.isEmpty()) "" else ", $argsList"

        // Emit a KDoc comment when the original ObjC return type carries generic information
        // (e.g. "NSArray<NSString *> *") that is erased to MemorySegment in the Kotlin binding.
        if (retSpelling.contains('<')) {
            builder.appendLine("/** @return $retSpelling */")
        }
        builder.appendLine("fun $extClass.${kotlinName(selector)}($paramList)$retDecl {")
        builder.indent()
        builder.appendLine("val sel = ObjCRuntime.sel(\"$selector\")")
        val invocation = returnLowering.invocation("this.ptr", "sel", argsExpr)
        builder.appendLine(if (returnLowering.isVoid) invocation else "return $invocation")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    /**
     * Emits a class (+) method as a top-level function named `<extClass>_<methodName>`.
     *
     * Extension functions on a companion object cannot access its private `_class`
     * property, so we call `ObjCRuntime.getClass` directly inside the function body
     * instead of relying on companion state.
     */
    private fun emitClassMethod(extClass: String, method: Declaration.ObjCMethod) {
        val selector  = method.selector()
        val params    = method.parameters()
        val returnLowering = typeLowerer.lower(method.returnType())
        val retKotlin = returnLowering.kotlinType

        val paramList = params.mapIndexed { i, p ->
            val pName = KotlinObjCClassBuilder.escapeIdentifier(p.name().ifEmpty { "arg$i" })
            val pType = typeLowerer.lower(p.type()).kotlinType
            "$pName: $pType"
        }.joinToString(", ")

        val retDecl  = if (retKotlin == "Unit") ": Unit" else ": $retKotlin"
        val argsList = params.mapIndexed { i, p ->
            val name = KotlinObjCClassBuilder.escapeIdentifier(p.name().ifEmpty { "arg$i" })
            typeLowerer.lower(p.type()).lowerArgument(name)
        }.joinToString(", ")
        val argsExpr = if (argsList.isEmpty()) "" else ", $argsList"

        // Use the raw selector→name conversion (without backtick-escaping via kotlinName)
        // because the extClass_ prefix already prevents collision with Kotlin hard keywords.
        // kotlinName(selector) applied when selector IS a keyword returns `` `keyword` ``
        // and concatenating extClass_ + `keyword` produces extClass_`keyword` which is
        // syntactically invalid (two adjacent identifiers).
        val classFnName = "${extClass}_${selector.replace(":", "_").trimEnd('_')}"
        val escapedClassFnName = KotlinObjCClassBuilder.escapeIdentifier(classFnName)
        builder.appendLine("// Class method: +[$extClass $selector]")
        builder.appendLine("fun $escapedClassFnName($paramList)$retDecl {")
        builder.indent()
        builder.appendLine("val sel = ObjCRuntime.sel(\"$selector\")")
        builder.appendLine("val cls = ObjCRuntime.getClass(\"$extClass\")")
        val invocation = returnLowering.invocation("cls", "sel", argsExpr)
        builder.appendLine(if (returnLowering.isVoid) invocation else "return $invocation")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitProperty(extClass: String, prop: Declaration.ObjCProperty) {
        val propName  = prop.name()
        val lowering = typeLowerer.lower(prop.type())
        val retKotlin = lowering.kotlinType
        val getter    = prop.getterSelector()
        val propTypeSpelling = prop.typeSpelling()

        builder.appendLine("// @property $propName")
        // Emit a KDoc comment when the original ObjC property type carries generic information
        // (e.g. "NSArray<NSString *> *") that is erased to MemorySegment in the Kotlin binding.
        if (propTypeSpelling.contains('<')) {
            builder.appendLine("/** @return $propTypeSpelling */")
        }
        builder.appendLine("fun $extClass.${kotlinName(getter)}(): $retKotlin {")
        builder.indent()
        builder.appendLine("val sel = ObjCRuntime.sel(\"$getter\")")
        val invocation = lowering.invocation("this.ptr", "sel", "")
        builder.appendLine(if (lowering.isVoid) invocation else "return $invocation")
        builder.unindent()
        builder.appendLine("}")

        if (!prop.isReadOnly()) {
            val setter    = prop.setterSelector()
            val paramType = lowering.kotlinType
            val valueExpr = lowering.lowerArgument("value")
            builder.appendLine("fun $extClass.${kotlinName(setter.removeSuffix(":"))}(value: $paramType) {")
            builder.indent()
            builder.appendLine("val sel = ObjCRuntime.sel(\"$setter\")")
            builder.appendLine("ObjCRuntime.msgSend(null, this.ptr, sel, $valueExpr)")
            builder.unindent()
            builder.appendLine("}")
        }
        builder.appendLine()
    }
}
