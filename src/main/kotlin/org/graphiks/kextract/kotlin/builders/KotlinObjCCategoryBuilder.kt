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
    private val toplevel: KotlinToplevelBuilder,
    /**
     * Mutable set of Kotlin method/property signatures already emitted by the extended
     * class's own interface AND by any other category on the same class.  Shared across
     * all [KotlinObjCCategoryBuilder] instances for the same extended class to avoid
     * "conflicting overloads".
     */
    private val existingSignatures: MutableSet<String> = mutableSetOf(),
    private val callableNames: KotlinCallableNameAllocator = KotlinCallableNameAllocator(),
    private val topLevelCallableNames: KotlinCallableNameAllocator = callableNames,
) {
    private val typeLowerer = ObjCTypeLowerer(toplevel)

    /** Replays this category's instance-extension surface for [receiver] without emitting code. */
    internal fun reserveInstanceCallables(
        decl: Declaration.ObjCCategory,
        receiver: String,
        signatures: MutableSet<String>,
        names: KotlinCallableNameAllocator,
    ) {
        if (Skip.isPresent(decl)) return
        for (method in decl.methods()) {
            if (!method.isClassMethod()) {
                allocateInstanceMethod(receiver, method, signatures, names)
            }
        }
        for (property in decl.properties()) {
            if (!property.isClassProperty()) {
                allocateInstanceProperty(receiver, property, signatures, names)
            }
        }
    }

    fun visitCategory(decl: Declaration.ObjCCategory) {
        if (Skip.isPresent(decl)) return

        val extClass = decl.extendedClass()
        val catName  = decl.categoryName()

        builder.appendLine("// ── Category: $catName on $extClass ─────────────────────────────────────────")
        builder.appendLine()

        val (classMethods, instanceMethods) = decl.methods().partition { it.isClassMethod() }

        for (method in instanceMethods) {
            val functionName = allocateInstanceMethod(
                extClass,
                method,
                existingSignatures,
                callableNames,
                allowHiddenInheritedExtension = true,
            ) ?: continue
            emitInstanceMethod(extClass, method, functionName)
        }

        for (method in classMethods) {
            val sig = toplevel.objcMemberSignatureKey(true, method.selector())
            if (sig in existingSignatures) continue
            existingSignatures.add(sig)
            val functionName = topLevelCallableNames.allocate(
                method.selector(),
                classFunctionName(extClass, method.selector()),
                method.parameters().map { typeLowerer.lower(it.type()).kotlinType },
            )
            emitClassMethod(extClass, method, functionName)
        }

        for (prop in decl.properties()) {
            val isClassProperty = prop.isClassProperty()
            if (!isClassProperty) {
                val propertyNames = allocateInstanceProperty(
                    extClass,
                    prop,
                    existingSignatures,
                    callableNames,
                    allowHiddenInheritedExtension = true,
                ) ?: continue
                emitProperty(extClass, prop, propertyNames.getter, propertyNames.setter)
                continue
            }
            val getterSig = toplevel.objcMemberSignatureKey(isClassProperty, prop.getterSelector())
            val setterSig = if (prop.isReadOnly()) null else toplevel.objcMemberSignatureKey(isClassProperty, prop.setterSelector())
            val emitGetter = getterSig !in existingSignatures
            val emitSetter = setterSig != null && setterSig !in existingSignatures
            if (!emitGetter && !emitSetter) continue
            if (emitGetter) existingSignatures.add(getterSig)
            if (emitSetter) existingSignatures.add(requireNotNull(setterSig))
            val getterName = if (emitGetter) {
                topLevelCallableNames.allocate(prop.getterSelector(), classFunctionName(extClass, prop.getterSelector()), emptyList())
            } else {
                null
            }
            val setterName = if (emitSetter) {
                topLevelCallableNames.allocate(
                    prop.setterSelector(),
                    classFunctionName(extClass, prop.setterSelector()),
                    listOf(typeLowerer.lower(prop.type()).kotlinType),
                )
            } else {
                null
            }
            emitClassProperty(extClass, prop, getterName, setterName)
        }
    }

    private fun allocateInstanceMethod(
        receiver: String,
        method: Declaration.ObjCMethod,
        signatures: MutableSet<String>,
        names: KotlinCallableNameAllocator,
        allowHiddenInheritedExtension: Boolean = false,
    ): String? {
        val signature = toplevel.objcMemberSignatureKey(false, method.selector())
        val parameterTypes = method.parameters().map { typeLowerer.lower(it.type()).kotlinType }
        val newSignature = signatures.add(signature)
        if (!newSignature &&
            !(allowHiddenInheritedExtension &&
                toplevel.claimHiddenInheritedInstanceExtension(
                    receiver,
                    method.selector(),
                    parameterTypes,
                ))
        ) return null
        return names.allocate(
            method.selector(),
            kotlinName(method.selector()),
            parameterTypes,
            receiver,
        )
    }

    private data class InstancePropertyNames(
        val getter: String?,
        val setter: String?,
    )

    private fun allocateInstanceProperty(
        receiver: String,
        property: Declaration.ObjCProperty,
        signatures: MutableSet<String>,
        names: KotlinCallableNameAllocator,
        allowHiddenInheritedExtension: Boolean = false,
    ): InstancePropertyNames? {
        val getter = property.getterSelector()
        val getterSignature = toplevel.objcMemberSignatureKey(false, getter)
        val setter = property.setterSelector()
        val setterSignature = if (property.isReadOnly()) null else toplevel.objcMemberSignatureKey(false, setter)
        val propertyType = typeLowerer.lower(property.type()).kotlinType
        val emitGetter = getterSignature !in signatures ||
            (allowHiddenInheritedExtension &&
                toplevel.claimHiddenInheritedInstanceExtension(receiver, getter, emptyList()))
        val emitSetter = setterSignature != null &&
            (setterSignature !in signatures ||
                (allowHiddenInheritedExtension &&
                    toplevel.claimHiddenInheritedInstanceExtension(
                        receiver,
                        setter,
                        listOf(propertyType),
                    )))
        if (!emitGetter && !emitSetter) return null

        if (emitGetter) signatures.add(getterSignature)
        if (emitSetter) signatures.add(requireNotNull(setterSignature))
        return InstancePropertyNames(
            getter = if (emitGetter) names.allocate(getter, kotlinName(getter), emptyList(), receiver) else null,
            setter = if (emitSetter) {
                names.allocate(setter, kotlinName(setter.removeSuffix(":")), listOf(propertyType), receiver)
            } else {
                null
            },
        )
    }

    /**
     * Emits an instance (-) method as an extension function on [extClass].
     * The ObjC message is sent to `ptr` (the wrapped MemorySegment).
     */
    private fun emitInstanceMethod(extClass: String, method: Declaration.ObjCMethod, functionName: String) {
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
        builder.appendLine("fun $extClass.$functionName($paramList)$retDecl {")
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
    private fun emitClassMethod(extClass: String, method: Declaration.ObjCMethod, functionName: String) {
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

        builder.appendLine("// Class method: +[$extClass $selector]")
        builder.appendLine("fun $functionName($paramList)$retDecl {")
        builder.indent()
        builder.appendLine("val sel = ObjCRuntime.sel(\"$selector\")")
        builder.appendLine("val cls = ObjCRuntime.getClass(\"$extClass\")")
        val invocation = returnLowering.invocation("cls", "sel", argsExpr)
        builder.appendLine(if (returnLowering.isVoid) invocation else "return $invocation")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitProperty(
        extClass: String,
        prop: Declaration.ObjCProperty,
        getterName: String?,
        setterName: String?,
    ) {
        val propName  = prop.name()
        val lowering = typeLowerer.lower(prop.type())
        val retKotlin = lowering.kotlinType
        val getter    = prop.getterSelector()
        val propTypeSpelling = prop.typeSpelling()

        if (getterName != null) {
            builder.appendLine("// @property $propName")
            // Emit a KDoc comment when the original ObjC property type carries generic information
            // (e.g. "NSArray<NSString *> *") that is erased to MemorySegment in the Kotlin binding.
            if (propTypeSpelling.contains('<')) {
                builder.appendLine("/** @return $propTypeSpelling */")
            }
            builder.appendLine("fun $extClass.$getterName(): $retKotlin {")
            builder.indent()
            builder.appendLine("val sel = ObjCRuntime.sel(\"$getter\")")
            val invocation = lowering.invocation("this.ptr", "sel", "")
            builder.appendLine(if (lowering.isVoid) invocation else "return $invocation")
            builder.unindent()
            builder.appendLine("}")
        }

        if (setterName != null) {
            val setter    = prop.setterSelector()
            val paramType = lowering.kotlinType
            val valueExpr = lowering.lowerArgument("value")
            builder.appendLine("fun $extClass.$setterName(value: $paramType) {")
            builder.indent()
            builder.appendLine("val sel = ObjCRuntime.sel(\"$setter\")")
            builder.appendLine("ObjCRuntime.msgSend(null, this.ptr, sel, $valueExpr)")
            builder.unindent()
            builder.appendLine("}")
        }
        builder.appendLine()
    }

    private fun emitClassProperty(
        extClass: String,
        prop: Declaration.ObjCProperty,
        getterName: String?,
        setterName: String?,
    ) {
        val lowering = typeLowerer.lower(prop.type())
        val returnKotlin = lowering.kotlinType
        val getter = prop.getterSelector()

        if (getterName != null) {
            builder.appendLine("// @property (class) ${prop.name()}")
            builder.appendLine("fun $getterName(): $returnKotlin {")
            builder.indent()
            builder.appendLine("val sel = ObjCRuntime.sel(\"$getter\")")
            builder.appendLine("val cls = ObjCRuntime.getClass(\"$extClass\")")
            val invocation = lowering.invocation("cls", "sel", "")
            builder.appendLine(if (lowering.isVoid) invocation else "return $invocation")
            builder.unindent()
            builder.appendLine("}")
        }

        if (setterName != null) {
            val setter = prop.setterSelector()
            val value = lowering.lowerArgument("value")
            builder.appendLine("fun $setterName(value: $returnKotlin) {")
            builder.indent()
            builder.appendLine("val sel = ObjCRuntime.sel(\"$setter\")")
            builder.appendLine("val cls = ObjCRuntime.getClass(\"$extClass\")")
            builder.appendLine("ObjCRuntime.msgSend(null, cls, sel, $value)")
            builder.unindent()
            builder.appendLine("}")
        }
        builder.appendLine()
    }

    private fun classFunctionName(extClass: String, selector: String): String =
        KotlinObjCClassBuilder.escapeIdentifier("${extClass}_${selector.replace(":", "_").trimEnd('_')}")
}
