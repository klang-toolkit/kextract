package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.builders.KotlinObjCClassBuilder.Companion.kotlinName

/**
 * Emits concrete Kotlin extension functions for required instance members of protocols adopted
 * by an Objective-C class. The supplied signature set is shared with class and category output.
 */
class KotlinObjCProtocolRequirementBuilder(
    private val builder: SourceBuilder,
    @Suppress("unused") private val toplevel: KotlinToplevelBuilder,
    private val protocolCatalogue: Map<String, Declaration.ObjCProtocol>,
    private val existingSignatures: MutableSet<String>,
    private val callableNames: KotlinCallableNameAllocator,
    private val topLevelCallableNames: KotlinCallableNameAllocator = callableNames,
) {
    private val typeLowerer = ObjCTypeLowerer(toplevel)

    fun visitClassProtocols(classDecl: Declaration.ObjCClass) {
        if (Skip.isPresent(classDecl)) return

        val visited = mutableSetOf<String>()
        for (protocolName in classDecl.protocols()) {
            visitProtocol(classDecl.name(), protocolName, visited)
        }
    }

    /**
     * Replays the required instance-extension surface of [classDecl] without emitting source.
     * The caller supplies the receiver whose Kotlin lookup surface is being reserved.
     */
    internal fun reserveInstanceCallables(
        classDecl: Declaration.ObjCClass,
        receiver: String,
        signatures: MutableSet<String>,
        names: KotlinCallableNameAllocator,
    ) {
        if (Skip.isPresent(classDecl)) return

        val visited = mutableSetOf<String>()
        for (protocolName in classDecl.protocols()) {
            reserveProtocolInstanceCallables(protocolName, receiver, signatures, names, visited)
        }
    }

    private fun reserveProtocolInstanceCallables(
        protocolName: String,
        receiver: String,
        signatures: MutableSet<String>,
        names: KotlinCallableNameAllocator,
        visited: MutableSet<String>,
    ) {
        if (!visited.add(protocolName)) return
        val protocol = protocolCatalogue[protocolName] ?: return
        if (Skip.isPresent(protocol)) return

        for (parentProtocolName in protocol.protocols()) {
            reserveProtocolInstanceCallables(parentProtocolName, receiver, signatures, names, visited)
        }
        for (property in protocol.properties()) {
            if (!property.isOptional() && !property.isClassProperty()) {
                allocateInstanceProperty(protocol.name(), receiver, property, signatures, names)
            }
        }
        for (method in protocol.methods()) {
            if (!method.isOptional() && !method.isClassMethod()) {
                allocateInstanceMethod(protocol.name(), receiver, method, signatures, names)
            }
        }
    }

    private fun visitProtocol(className: String, protocolName: String, visited: MutableSet<String>) {
        if (!visited.add(protocolName)) return
        val protocol = protocolCatalogue[protocolName] ?: return
        if (Skip.isPresent(protocol)) return

        for (parentProtocolName in protocol.protocols()) {
            visitProtocol(className, parentProtocolName, visited)
        }

        for (property in protocol.properties()) {
            if (property.isOptional()) continue
            emitProperty(className, protocol.name(), property)
        }

        for (method in protocol.methods()) {
            if (method.isOptional()) continue
            emitMethod(className, protocol.name(), method)
        }
    }

    private fun emitMethod(className: String, protocolName: String, method: Declaration.ObjCMethod) {
        if (!method.isClassMethod()) {
            val functionName = allocateInstanceMethod(
                protocolName,
                className,
                method,
                existingSignatures,
                callableNames,
                allowHiddenInheritedExtension = true,
            ) ?: return
            emitInstanceMethod(className, protocolName, method, functionName)
            return
        }

        val signature = toplevel.objcMemberSignatureKey(method.isClassMethod(), method.selector())
        if (signature in existingSignatures) return

        existingSignatures.add(signature)
        val selector = method.selector()
        val parameterTypes = method.parameters().map { lower(protocolName, selector, it.type()).kotlinType }
        val functionName = topLevelCallableNames.allocate(
            selector,
            classFunctionName(className, selector),
            parameterTypes,
        )
        emitClassMethod(className, protocolName, method, functionName)
    }

    private fun allocateInstanceMethod(
        protocolName: String,
        receiver: String,
        method: Declaration.ObjCMethod,
        signatures: MutableSet<String>,
        names: KotlinCallableNameAllocator,
        allowHiddenInheritedExtension: Boolean = false,
    ): String? {
        val signature = toplevel.objcMemberSignatureKey(false, method.selector())
        val parameterTypes = method.parameters().map { lower(protocolName, method.selector(), it.type()).kotlinType }
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

    private fun emitInstanceMethod(
        className: String,
        protocolName: String,
        method: Declaration.ObjCMethod,
        functionName: String,
    ) {
        val selector = method.selector()
        val parameters = method.parameters()
        val returnLowering = lower(protocolName, selector, method.returnType())
        val returnKotlin = returnLowering.kotlinType
        val params = parameters.mapIndexed { index, parameter ->
            val name = KotlinObjCClassBuilder.escapeIdentifier(parameter.name().ifEmpty { "arg$index" })
            "$name: ${lower(protocolName, selector, parameter.type()).kotlinType}"
        }.joinToString(", ")
        val args = parameters.mapIndexed { index, parameter ->
            val name = KotlinObjCClassBuilder.escapeIdentifier(parameter.name().ifEmpty { "arg$index" })
            lower(protocolName, selector, parameter.type()).lowerArgument(name)
        }.joinToString(", ")
        val argsExpr = if (args.isEmpty()) "" else ", $args"
        val returnDeclaration = if (returnKotlin == "Unit") ": Unit" else ": $returnKotlin"

        builder.appendLine("/** Required by Objective-C protocol $protocolName. */")
        builder.appendLine("fun $className.$functionName($params)$returnDeclaration {")
        builder.indent()
        builder.appendLine("val sel = ObjCRuntime.sel(\"$selector\")")
        val invocation = returnLowering.invocation("this.ptr", "sel", argsExpr)
        builder.appendLine(if (returnLowering.isVoid) invocation else "return $invocation")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitClassMethod(
        className: String,
        protocolName: String,
        method: Declaration.ObjCMethod,
        functionName: String,
    ) {
        val selector = method.selector()
        val parameters = method.parameters()
        val returnLowering = lower(protocolName, selector, method.returnType())
        val returnKotlin = returnLowering.kotlinType
        val params = parameters.mapIndexed { index, parameter ->
            val name = KotlinObjCClassBuilder.escapeIdentifier(parameter.name().ifEmpty { "arg$index" })
            "$name: ${lower(protocolName, selector, parameter.type()).kotlinType}"
        }.joinToString(", ")
        val args = parameters.mapIndexed { index, parameter ->
            val name = KotlinObjCClassBuilder.escapeIdentifier(parameter.name().ifEmpty { "arg$index" })
            lower(protocolName, selector, parameter.type()).lowerArgument(name)
        }.joinToString(", ")
        val argsExpr = if (args.isEmpty()) "" else ", $args"
        val returnDeclaration = if (returnKotlin == "Unit") ": Unit" else ": $returnKotlin"
        builder.appendLine("/** Required by Objective-C protocol $protocolName. */")
        builder.appendLine("fun $functionName($params)$returnDeclaration {")
        builder.indent()
        builder.appendLine("val sel = ObjCRuntime.sel(\"$selector\")")
        builder.appendLine("val cls = ObjCRuntime.getClass(\"$className\")")
        val invocation = returnLowering.invocation("cls", "sel", argsExpr)
        builder.appendLine(if (returnLowering.isVoid) invocation else "return $invocation")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private data class InstancePropertyNames(
        val getter: String?,
        val setter: String?,
    )

    private fun allocateInstanceProperty(
        protocolName: String,
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
        val lowering = lower(protocolName, getter, property.type())
        val emitGetter = getterSignature !in signatures ||
            (allowHiddenInheritedExtension &&
                toplevel.claimHiddenInheritedInstanceExtension(receiver, getter, emptyList()))
        val emitSetter = setterSignature != null &&
            (setterSignature !in signatures ||
                (allowHiddenInheritedExtension &&
                    toplevel.claimHiddenInheritedInstanceExtension(
                        receiver,
                        setter,
                        listOf(lowering.kotlinType),
                    )))
        if (!emitGetter && !emitSetter) return null

        if (emitGetter) signatures.add(getterSignature)
        if (emitSetter) signatures.add(requireNotNull(setterSignature))

        return InstancePropertyNames(
            getter = if (emitGetter) names.allocate(getter, kotlinName(getter), emptyList(), receiver) else null,
            setter = if (emitSetter) {
                names.allocate(setter, kotlinName(setter), listOf(lowering.kotlinType), receiver)
            } else {
                null
            },
        )
    }

    private fun emitProperty(className: String, protocolName: String, property: Declaration.ObjCProperty) {
        val getter = property.getterSelector()
        val isClassProperty = property.isClassProperty()
        if (!isClassProperty) {
            val propertyNames = allocateInstanceProperty(
                protocolName,
                className,
                property,
                existingSignatures,
                callableNames,
                allowHiddenInheritedExtension = true,
            ) ?: return
            val lowering = lower(protocolName, getter, property.type())
            val returnKotlin = lowering.kotlinType

            propertyNames.getter?.let { getterName ->
                builder.appendLine("/**")
                builder.appendLine(" * Required by Objective-C protocol $protocolName.")
                if (property.isObjectiveCObjectReference()) {
                    builder.appendLine(" * This getter returns a borrowed (+0) Objective-C reference and does not transfer ownership.")
                }
                builder.appendLine(" */")
                builder.appendLine("fun $className.$getterName(): $returnKotlin {")
                builder.indent()
                builder.appendLine("val sel = ObjCRuntime.sel(\"$getter\")")
                val invocation = lowering.invocation("this.ptr", "sel", "")
                builder.appendLine(if (lowering.isVoid) invocation else "return $invocation")
                builder.unindent()
                builder.appendLine("}")
            }
            propertyNames.setter?.let { setterName ->
                val setter = property.setterSelector()
                val value = lowering.lowerArgument("value")
                builder.appendLine("fun $className.$setterName(value: $returnKotlin) {")
                builder.indent()
                builder.appendLine("val sel = ObjCRuntime.sel(\"$setter\")")
                builder.appendLine("ObjCRuntime.msgSend(null, this.ptr, sel, $value)")
                builder.unindent()
                builder.appendLine("}")
            }
            builder.appendLine()
            return
        }

        val getterSignature = toplevel.objcMemberSignatureKey(isClassProperty, getter)
        val setter = property.setterSelector()
        val setterSignature = if (property.isReadOnly()) null else toplevel.objcMemberSignatureKey(isClassProperty, setter)
        val emitGetter = getterSignature !in existingSignatures
        val emitSetter = setterSignature != null && setterSignature !in existingSignatures
        if (!emitGetter && !emitSetter) return

        if (emitGetter) existingSignatures.add(getterSignature)
        if (emitSetter) existingSignatures.add(requireNotNull(setterSignature))

        val getterName = if (emitGetter) {
            topLevelCallableNames.allocate(getter, classFunctionName(className, getter), emptyList())
        } else {
            null
        }
        val setterName = if (emitSetter) {
            topLevelCallableNames.allocate(
                setter,
                classFunctionName(className, setter),
                listOf(lower(protocolName, setter, property.type()).kotlinType),
            )
        } else {
            null
        }
        emitClassProperty(className, protocolName, property, getterName, setterName)
    }

    private fun emitClassProperty(
        className: String,
        protocolName: String,
        property: Declaration.ObjCProperty,
        getterName: String?,
        setterName: String?,
    ) {
        val lowering = lower(protocolName, property.getterSelector(), property.type())
        val returnKotlin = lowering.kotlinType
        val getter = property.getterSelector()

        if (getterName != null) {
            emitPropertyKDoc(protocolName, property)
            builder.appendLine("fun $getterName(): $returnKotlin {")
            builder.indent()
            builder.appendLine("val sel = ObjCRuntime.sel(\"$getter\")")
            builder.appendLine("val cls = ObjCRuntime.getClass(\"$className\")")
            val invocation = lowering.invocation("cls", "sel", "")
            builder.appendLine(if (lowering.isVoid) invocation else "return $invocation")
            builder.unindent()
            builder.appendLine("}")
        }

        if (setterName != null) {
            val setter = property.setterSelector()
            val value = lowering.lowerArgument("value")
            builder.appendLine("fun $setterName(value: $returnKotlin) {")
            builder.indent()
            builder.appendLine("val sel = ObjCRuntime.sel(\"$setter\")")
            builder.appendLine("val cls = ObjCRuntime.getClass(\"$className\")")
            builder.appendLine("ObjCRuntime.msgSend(null, cls, sel, $value)")
            builder.unindent()
            builder.appendLine("}")
        }
        builder.appendLine()
    }

    private fun emitPropertyKDoc(protocolName: String, property: Declaration.ObjCProperty) {
        builder.appendLine("/**")
        builder.appendLine(" * Required by Objective-C protocol $protocolName.")
        if (property.isObjectiveCObjectReference()) {
            builder.appendLine(" * This getter returns a borrowed (+0) Objective-C reference and does not transfer ownership.")
        }
        builder.appendLine(" */")
    }

    private fun classFunctionName(className: String, selector: String): String =
        KotlinObjCClassBuilder.escapeIdentifier("${className}_${selector.replace(":", "_").trimEnd('_')}")

    /** Adds the protocol/selector source context that generic type lowering lacks. */
    private fun lower(protocolName: String, selector: String, type: Type): ObjCTypeLowering = try {
        typeLowerer.lower(type)
    } catch (cause: Exception) {
        throw IllegalStateException(
            "Unsupported Objective-C protocol member $protocolName selector '$selector'",
            cause,
        )
    }

}
