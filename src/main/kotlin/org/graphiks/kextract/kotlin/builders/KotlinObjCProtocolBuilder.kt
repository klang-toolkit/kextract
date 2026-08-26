package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.builders.KotlinObjCClassBuilder.Companion.kotlinName

/**
 * Generates a Kotlin interface wrapper for an Objective-C @protocol declaration.
 *
 * @required methods → abstract fun in the interface.
 * @optional methods → fun with a default body that throws [UnsupportedOperationException].
 * Objective-C class methods and class properties are omitted: concrete wrapper top-level
 * functions provide their static dispatch API without making the interface uncompilable.
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
    @Suppress("unused") private val toplevel: KotlinToplevelBuilder,
    /** Set of class names that are generated in this run — used to filter out protocol
     *  parents that are actually classes (e.g. NSAccessibilityElement). */
    private val generatedClassNames: Set<String> = emptySet(),
    /** All non-skipped protocols discovered during the TOPLEVEL pre-scan. */
    private val protocolCatalogue: Map<String, Declaration.ObjCProtocol> = emptyMap(),
) {
    private val typeLowerer = ObjCTypeLowerer(toplevel)

    private data class InheritedCallable(
        val selector: String,
        val parameterTypes: List<String>,
        val returnType: String,
    )

    fun visitProtocol(decl: Declaration.ObjCProtocol) {
        if (Skip.isPresent(decl)) return

        val protoName = decl.name()

        // KDoc header
        builder.appendLine("/**")
        builder.appendLine(" * Kotlin/JVM interface for Objective-C protocol: $protoName")
        if (decl.protocols().isNotEmpty())
            builder.appendLine(" * Inherits protocols: ${decl.protocols().joinToString()}")
        builder.appendLine(" */")

        // Interface declaration with super-protocols as Kotlin supertypes.
        // Filter out:
        //   - NSObject (it is a class, not a protocol, in the generated Kotlin)
        //   - NSObjectProtocol (implicit for all ObjC objects)
        //   - Any name that is also a generated class (e.g. NSAccessibilityElement)
        val superProtos = kotlinSuperProtocols(decl)
        val superExpr = if (superProtos.isEmpty()) "" else " : ${superProtos.joinToString(", ")}"
        builder.appendLine("interface $protoName$superExpr {")
        builder.indent()
        // A builder instance emits several protocol interfaces in non-split output. Exact
        // selector deduplication belongs to one protocol interface, not the whole header:
        // two distinct protocols may each require the same selector.
        val emitted = mutableSetOf<String>()
        val callableNames = KotlinCallableNameAllocator()
        val inheritedCallables = reserveInheritedCallables(decl, callableNames)

        for (method in decl.methods()) {
            if (method.isClassMethod()) continue
            val sig = toplevel.objcMemberSignatureKey(false, method.selector())
            if (sig in emitted) continue
            emitted.add(sig)
            emitMethod(protoName, method, callableNames, inheritedCallables)
        }

        for (prop in decl.properties()) {
            if (prop.isClassProperty()) continue
            val getterSig = toplevel.objcMemberSignatureKey(false, prop.getterSelector())
            val setterSig = if (prop.isReadOnly()) null else
                toplevel.objcMemberSignatureKey(false, prop.setterSelector())
            val emitGetter = getterSig !in emitted
            val emitSetter = setterSig != null && setterSig !in emitted
            if (!emitGetter && !emitSetter) continue
            if (emitGetter) emitted.add(getterSig)
            if (emitSetter) emitted.add(requireNotNull(setterSig))
            emitProperty(protoName, prop, emitGetter, emitSetter, callableNames, inheritedCallables)
        }

        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    /**
     * Reserves callables inherited from parent protocols in the child's local interface scope.
     *
     * Protocol interfaces must remain isolated from unrelated protocols, but a child interface
     * has the Kotlin members of all of its protocol ancestors in scope. Replaying each ancestor
     * in parent-first declaration order gives a child-local allocator exactly the same names its
     * inherited members received, including collision suffixes, regardless of header ordering.
     */
    private fun reserveInheritedCallables(
        decl: Declaration.ObjCProtocol,
        callableNames: KotlinCallableNameAllocator,
    ): Set<InheritedCallable> {
        val visited = mutableSetOf<String>()
        val inheritedCallables = mutableSetOf<InheritedCallable>()

        fun reserveProtocol(protocolName: String) {
            if (!visited.add(protocolName)) return
            val protocol = protocolCatalogue[protocolName] ?: return
            if (Skip.isPresent(protocol)) return

            for (parentName in kotlinSuperProtocols(protocol)) reserveProtocol(parentName)

            val emitted = mutableSetOf<String>()
            for (method in protocol.methods()) {
                if (method.isClassMethod()) continue
                val signature = toplevel.objcMemberSignatureKey(false, method.selector())
                if (!emitted.add(signature)) continue
                val parameterTypes = method.parameters().map { lower(protocol.name(), method.selector(), it.type()).kotlinType }
                callableNames.allocate(
                    method.selector(),
                    kotlinName(method.selector()),
                    parameterTypes,
                )
                inheritedCallables.add(
                    InheritedCallable(
                        method.selector(),
                        parameterTypes,
                        lower(protocol.name(), method.selector(), method.returnType()).kotlinType,
                    ),
                )
            }

            for (property in protocol.properties()) {
                if (property.isClassProperty()) continue
                val getter = property.getterSelector()
                val getterSignature = toplevel.objcMemberSignatureKey(false, getter)
                if (emitted.add(getterSignature)) {
                    callableNames.allocate(getter, kotlinName(getter), emptyList())
                    inheritedCallables.add(
                        InheritedCallable(getter, emptyList(), lower(protocol.name(), getter, property.type()).kotlinType),
                    )
                }

                if (!property.isReadOnly()) {
                    val setter = property.setterSelector()
                    val setterSignature = toplevel.objcMemberSignatureKey(false, setter)
                    if (emitted.add(setterSignature)) {
                        callableNames.allocate(
                            setter,
                            kotlinName(setter.removeSuffix(":")),
                            listOf(lower(protocol.name(), setter, property.type()).kotlinType),
                        )
                        inheritedCallables.add(
                            InheritedCallable(
                                setter,
                                listOf(lower(protocol.name(), setter, property.type()).kotlinType),
                                "Unit",
                            ),
                        )
                    }
                }
            }
        }

        for (parentName in kotlinSuperProtocols(decl)) reserveProtocol(parentName)
        return inheritedCallables
    }

    /**
     * Parent protocols that actually become Kotlin supertypes for [decl]. The same filter must
     * govern both the emitted `: Parent` clause and inherited callable reservations: a filtered
     * Objective-C parent has no Kotlin member that a child declaration can override.
     */
    private fun kotlinSuperProtocols(decl: Declaration.ObjCProtocol): List<String> =
        decl.protocols().filter(::isKotlinSuperProtocol)

    private fun isKotlinSuperProtocol(protocolName: String): Boolean =
        protocolName !in generatedClassNames &&
            protocolName != "NSObject" &&
            !protocolName.endsWith("NSObjectProtocol")

    private fun emitMethod(
        protocolName: String,
        method: Declaration.ObjCMethod,
        callableNames: KotlinCallableNameAllocator,
        inheritedCallables: Set<InheritedCallable>,
    ) {
        val selector = method.selector()
        val params   = method.parameters()
        val retKotlin = lower(protocolName, selector, method.returnType()).kotlinType
        val retSpelling = method.returnTypeSpelling()

        val paramList = params.mapIndexed { i, p ->
            val pName = KotlinObjCClassBuilder.escapeIdentifier(p.name().ifEmpty { "arg$i" })
            val pType = lower(protocolName, selector, p.type()).kotlinType
            "$pName: $pType"
        }.joinToString(", ")
        val parameterTypes = params.map { lower(protocolName, selector, it.type()).kotlinType }
        val fnName = callableNames.allocate(
            selector,
            kotlinName(selector),
            parameterTypes,
        )
        val overrideModifier = if (
            InheritedCallable(selector, parameterTypes, retKotlin) in inheritedCallables
        ) {
            "override "
        } else {
            ""
        }

        // Always emit explicit return type, even for Unit: Kotlin infers Nothing from
        // "throw UnsupportedOperationException(...)" when the : Unit is omitted.
        val retDecl = ": $retKotlin"

        // Emit a KDoc comment when the original ObjC return type carries generic information
        // (e.g. "NSArray<NSString *> *") that is erased to MemorySegment in the Kotlin binding.
        if (retSpelling.contains('<')) {
            builder.appendLine("/** @return $retSpelling */")
        }
        if (method.isOptional()) {
            // Default implementation: throw UnsupportedOperationException
            builder.appendLine("// @optional")
            builder.appendLine("${overrideModifier}fun $fnName($paramList)$retDecl =")
            builder.indent()
            builder.appendLine("throw UnsupportedOperationException(\"Optional ObjC method '$selector' not implemented\")")
            builder.unindent()
        } else {
            // @required → abstract
            builder.appendLine("${overrideModifier}fun $fnName($paramList)$retDecl")
        }
        builder.appendLine()
    }

    private fun emitProperty(
        protocolName: String,
        prop: Declaration.ObjCProperty,
        emitGetter: Boolean,
        emitSetter: Boolean,
        callableNames: KotlinCallableNameAllocator,
        inheritedCallables: Set<InheritedCallable>,
    ) {
        val propName  = prop.name()
        val retKotlin = lower(protocolName, prop.getterSelector(), prop.type()).kotlinType
        val getter    = prop.getterSelector()
        val propTypeSpelling = prop.typeSpelling()

        builder.appendLine("// @property $propName")
        // Emit a KDoc comment when the original ObjC property type carries generic information
        // (e.g. "NSArray<NSString *> *") that is erased to MemorySegment in the Kotlin binding.
        if (propTypeSpelling.contains('<')) {
            builder.appendLine("/** @return $propTypeSpelling */")
        }
        // Standalone protocol interfaces model instance-property accessors as abstract Kotlin
        // overloads. Class-property accessors are exposed by concrete wrapper functions.
        if (emitGetter) {
            val getterName = callableNames.allocate(getter, kotlinName(getter), emptyList())
            val getterModifier = if (InheritedCallable(getter, emptyList(), retKotlin) in inheritedCallables) {
                "override "
            } else {
                ""
            }
            builder.appendLine("${getterModifier}fun $getterName(): $retKotlin")
        }

        if (emitSetter) {
            val setter    = prop.setterSelector()
            val paramType = lower(protocolName, setter, prop.type()).kotlinType
            val setterName = callableNames.allocate(
                setter,
                kotlinName(setter.removeSuffix(":")),
                listOf(paramType),
            )
            val setterModifier = if (InheritedCallable(setter, listOf(paramType), "Unit") in inheritedCallables) {
                "override "
            } else {
                ""
            }
            builder.appendLine("${setterModifier}fun $setterName(value: $paramType)")
        }
        builder.appendLine()
    }

    /** Adds source diagnostics to errors from the generic Objective-C type lowering. */
    private fun lower(protocolName: String, selector: String, type: Type): ObjCTypeLowering = try {
        typeLowerer.lower(type)
    } catch (cause: Exception) {
        throw IllegalStateException(
            "Unsupported Objective-C protocol member $protocolName selector '$selector'",
            cause,
        )
    }
}
