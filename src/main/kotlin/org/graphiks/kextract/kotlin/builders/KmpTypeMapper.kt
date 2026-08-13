package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.KotlinKmpNamePlan
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.ARRAY_HOLDER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_STRING
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ADDRESS
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiIndex
import org.graphiks.kextract.pipeline.isEnum

internal class KmpTypeMapper(
    private val namePlan: KotlinKmpNamePlan,
    private val arraysAsHolders: Boolean = true,
    private val abiIndex: KotlinKmpAbiIndex? = null,
) {
    private val nativeAddress = namePlan.runtime(NATIVE_ADDRESS)
    private val cString = namePlan.runtime(C_STRING)
    private val arrayHolder = namePlan.runtime(ARRAY_HOLDER)

    fun mapType(type: Type): String = when {
        type is Type.Primitive -> mapPrimitive(type.kind())
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.UNSIGNED -> mapUnsigned(type.type())
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> mapPointer(type.type(), charNullable = false)
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> mapTypedef(type)
        type is Type.Declared -> declaredName(type)
        else -> nativeAddress
    }

    fun mapFunctionType(type: Type): String = when {
        type is Type.Primitive -> mapPrimitive(type.kind())
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.UNSIGNED -> mapType(type)
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> mapFunctionPointer(type.type())
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> mapFunctionTypedef(type)
        type is Type.Function -> "$nativeAddress?"
        type is Type.Declared -> declaredName(type)
        type is Type.Array && arraysAsHolders -> "$arrayHolder<${mapFunctionType(type.elementType()).removeSuffix("?")}>?"
        else -> nativeAddress
    }

    fun mapPrimitive(kind: Type.Primitive.Kind): String = when (kind) {
        Type.Primitive.Kind.Bool -> "Boolean"
        Type.Primitive.Kind.Char -> "Byte"
        Type.Primitive.Kind.Short -> "Short"
        Type.Primitive.Kind.Int -> "Int"
        Type.Primitive.Kind.Long, Type.Primitive.Kind.LongLong -> "Long"
        Type.Primitive.Kind.Float -> "Float"
        Type.Primitive.Kind.Double -> "Double"
        Type.Primitive.Kind.Void -> "Unit"
        else -> nativeAddress
    }

    fun callbackFunction(type: Type): Type.Function? = type.callbackFunctionOrNull()

    fun pointerDepth(type: Type): Int = when {
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER ->
            1 + pointerDepth(type.type())
        type is Type.Delegated -> pointerDepth(type.type())
        else -> 0
    }

    fun declaredRecord(type: Type): Declaration.Scoped? = when (type) {
        is Type.Declared -> type.tree().takeIf {
            it.kind() in setOf(Declaration.Scoped.Kind.STRUCT, Declaration.Scoped.Kind.UNION)
        }
        is Type.Delegated -> declaredRecord(type.type())
        else -> null
    }

    fun declaredUnion(type: Type): Declaration.Scoped? =
        declaredRecord(type)?.takeIf { it.kind() == Declaration.Scoped.Kind.UNION }

    fun canonicalKmpType(type: Type): String {
        val canonical = canonicalType(type)
        return when {
            canonical is Type.Primitive -> mapPrimitive(canonical.kind())
            isEnumType(canonical) -> abiIndex
                ?.enum(enumDeclaration(canonical))
                ?.kotlinType
                ?: "UInt"
            canonical is Type.Delegated && canonical.kind() == Type.Delegated.Kind.UNSIGNED -> mapUnsigned(canonical.type())
            else -> "Other"
        }
    }

    fun isEnumType(type: Type): Boolean = when {
        type.isEnum() -> true
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> isEnumType(type.type())
        else -> false
    }

    fun enumDeclaration(type: Type): Declaration.Scoped = when {
        type is Type.Declared && type.isEnum() -> type.tree()
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> enumDeclaration(type.type())
        else -> error("Expected enum type, found $type")
    }

    fun isOptionsEnumType(type: Type): Boolean =
        isEnumType(type) && isOptionsStyleName(namePlan.declaration(enumDeclaration(type)))

    fun isInlineStructOrUnion(type: Type): Boolean {
        val fieldType = mapType(type)
        return canonicalKmpType(type) == "Other" &&
            fieldType != nativeAddress &&
            fieldType != cString &&
            !fieldType.endsWith("?")
    }

    private fun mapUnsigned(inner: Type): String = if (inner is Type.Primitive) {
        when (inner.kind()) {
            Type.Primitive.Kind.Char -> "UByte"
            Type.Primitive.Kind.Short -> "UShort"
            Type.Primitive.Kind.Int -> "UInt"
            Type.Primitive.Kind.Long, Type.Primitive.Kind.LongLong -> "ULong"
            else -> "UInt"
        }
    } else {
        "UInt"
    }

    private fun mapPointer(pointee: Type, charNullable: Boolean): String = when {
        pointerDepth(pointee) > 0 -> "$nativeAddress?"
        pointee is Type.Primitive && pointee.kind() == Type.Primitive.Kind.Char -> if (charNullable) "$cString?" else cString
        declaredRecord(pointee) != null ->
            publicRecordClassifier(requireNotNull(declaredRecord(pointee)))?.let { "$it?" } ?: "$nativeAddress?"
        else -> "$nativeAddress?"
    }

    private fun mapTypedef(type: Type.Delegated): String {
        val inner = type.type()
        if (inner is Type.Delegated && inner.kind() == Type.Delegated.Kind.POINTER) {
            return declaredRecord(inner.type())
                ?.let(::publicRecordClassifier)
                ?.let { "$it?" }
                ?: "$nativeAddress?"
        }

        val innerMapped = mapType(inner)
        if (innerMapped != nativeAddress && innerMapped != "$nativeAddress?" && !innerMapped.contains("unnamed")) {
            return innerMapped
        }
        return nativeAddress
    }

    private fun mapFunctionPointer(pointee: Type): String = when {
        pointee is Type.Primitive && pointee.kind() == Type.Primitive.Kind.Char -> "$cString?"
        pointee is Type.Function -> "$nativeAddress?"
        pointee is Type.Delegated && pointee.kind() == Type.Delegated.Kind.TYPEDEF && pointee.type() is Type.Function -> "$nativeAddress?"
        else -> mapPointer(pointee, charNullable = true)
    }

    private fun mapFunctionTypedef(type: Type.Delegated): String {
        val inner = type.type()
        return when {
            callbackFunction(type) != null -> "$nativeAddress?"
            inner is Type.Function -> "$nativeAddress?"
            inner is Type.Delegated && inner.kind() == Type.Delegated.Kind.POINTER && inner.type() is Type.Function -> "$nativeAddress?"
            inner is Type.Delegated && inner.kind() == Type.Delegated.Kind.POINTER ->
                declaredRecord(inner.type())
                    ?.let(::publicRecordClassifier)
                    ?.let { "$it?" }
                    ?: "$nativeAddress?"
            else -> {
                val innerMapped = mapType(inner)
                if (innerMapped != nativeAddress && !innerMapped.contains("unnamed")) innerMapped else nativeAddress
            }
        }
    }

    private fun declaredName(type: Type.Declared): String {
        val name = type.tree().name()
        return if (name.isNotEmpty() && !name.contains("unnamed")) namePlan.declaration(type.tree()) else nativeAddress
    }

    private fun publicRecordClassifier(declaration: Declaration.Scoped): String? {
        val rawName = declaration.name()
        return if (rawName.isNotEmpty() && !rawName.contains("unnamed")) {
            namePlan.publicRecordClassifier(declaration)
        } else {
            null
        }
    }

    private fun Type.callbackFunctionOrNull(): Type.Function? = when {
        this is Type.Delegated && kind() == Type.Delegated.Kind.TYPEDEF -> type().callbackFunctionOrNull()
        this is Type.Delegated && kind() == Type.Delegated.Kind.POINTER -> type().callbackFunctionOrNull()
        this is Type.Function -> this
        else -> null
    }

    private fun canonicalType(type: Type): Type = when {
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> canonicalType(type.type())
        else -> type
    }

}

/**
 * Canonical NS_OPTIONS-style predicate shared by the common builder and the
 * per-target builders. EndsWith Options/Flags/Mask mirrors cinterop's heuristic;
 * the WGPUInstance* names are historical wgpu options enums that must be emitted
 * as [value class]es on every target (a common value class with a generic-enum
 * Android accessor would not compile).
 *
 * public (not internal): kextract tests reference kmain types without a friend-path.
 */
fun isOptionsStyleName(name: String): Boolean =
    name.endsWith("Options") ||
        name.endsWith("Flags") ||
        name.endsWith("Mask") ||
        name == "WGPUInstanceBackend" ||
        name == "WGPUInstanceFlag" ||
        name == "WGPUFlags"
