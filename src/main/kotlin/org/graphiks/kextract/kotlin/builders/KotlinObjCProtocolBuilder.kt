package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.kotlin.builders.KotlinObjCClassBuilder.Companion.kotlinName

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
    @Suppress("unused") private val toplevel: KotlinToplevelBuilder,
    /** Set of class names that are generated in this run — used to filter out protocol
     *  parents that are actually classes (e.g. NSAccessibilityElement). */
    private val generatedClassNames: Set<String> = emptySet()
) {
    private val typeLowerer = ObjCTypeLowerer(toplevel)

    /** Set of Kotlin signatures already emitted for this protocol — deduplicates methods vs
     *  property accessors, and methods inherited from parent protocols. */
    private val emitted = mutableSetOf<String>()

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
        val superProtos = decl.protocols().filter { it !in generatedClassNames && it != "NSObject" && !it.endsWith("NSObjectProtocol") }
        val superExpr = if (superProtos.isEmpty()) "" else " : ${superProtos.joinToString(", ")}"
        builder.appendLine("interface $protoName$superExpr {")
        builder.indent()

        for (method in decl.methods()) {
            val sig = kotlinName(method.selector())
            if (sig in emitted) continue
            emitted.add(sig)
            emitMethod(method)
        }

        for (prop in decl.properties()) {
            val getterSig = kotlinName(prop.getterSelector())
            if (getterSig in emitted) continue
            emitted.add(getterSig)
            emitProperty(prop)
        }

        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitMethod(method: Declaration.ObjCMethod) {
        val selector = method.selector()
        val params   = method.parameters()
        val retKotlin = typeLowerer.lower(method.returnType()).kotlinType
        val retSpelling = method.returnTypeSpelling()

        val paramList = params.mapIndexed { i, p ->
            val pName = KotlinObjCClassBuilder.escapeIdentifier(p.name().ifEmpty { "arg$i" })
            val pType = typeLowerer.lower(p.type()).kotlinType
            "$pName: $pType"
        }.joinToString(", ")

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
        val retKotlin = typeLowerer.lower(prop.type()).kotlinType
        val getter    = prop.getterSelector()
        val propTypeSpelling = prop.typeSpelling()

        builder.appendLine("// @property $propName")
        // Emit a KDoc comment when the original ObjC property type carries generic information
        // (e.g. "NSArray<NSString *> *") that is erased to MemorySegment in the Kotlin binding.
        if (propTypeSpelling.contains('<')) {
            builder.appendLine("/** @return $propTypeSpelling */")
        }
        builder.appendLine("fun ${kotlinName(getter)}(): $retKotlin")

        if (!prop.isReadOnly()) {
            val setter    = prop.setterSelector()
            val paramType = typeLowerer.lower(prop.type()).kotlinType
            builder.appendLine("fun ${kotlinName(setter.removeSuffix(":"))}(value: $paramType)")
        }
        builder.appendLine()
    }
}
