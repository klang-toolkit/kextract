package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.ClangEnumType
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.utils.TypeMapper

/**
 * ABI lowering for direct C function calls emitted by [KotlinHeaderBuilder].
 *
 * C enum functions retain their nominal Kotlin enum or value-class signature,
 * while FFM receives and returns the scalar carrier declared by Clang. This is
 * intentionally separate from [ObjCTypeLowerer]: direct C downcalls use their
 * own MethodHandle invocation and do not share Objective-C dispatch semantics.
 */
internal data class CFunctionTypeLowering(
    val kotlinType: String,
    private val fallbackCarrierValue: String = carrierDefault(kotlinType),
    private val unavailableBindingFallback: ((String) -> String)? = null,
    private val argumentLowering: (String) -> String = { it },
    private val returnReconstruction: (String) -> String = { "$it as $kotlinType" },
) {
    fun lowerArgument(name: String): String = argumentLowering(name)

    fun reconstruct(rawValue: String): String = returnReconstruction(rawValue)

    fun fallbackValue(bindingName: String): String =
        unavailableBindingFallback?.invoke(bindingName) ?: reconstruct(fallbackCarrierValue)

    fun missingBindingSetterAction(bindingName: String): String =
        unavailableBindingFallback?.invoke(bindingName) ?: "return"
}

/** Builds direct-C lowering descriptors without changing non-enum function types. */
internal class CFunctionTypeLowerer(private val toplevel: KotlinToplevelBuilder) {
    fun lower(type: Type): CFunctionTypeLowering {
        val enumDecl = KotlinEnumSupport.resolveEnum(type)
        if (enumDecl == null) {
            return CFunctionTypeLowering(toplevel.mapType(type))
        }

        val name = toplevel.javaName(enumDecl.name())
        val underlying = ClangEnumType.get(enumDecl) ?: Type.primitive(Type.Primitive.Kind.Int)
        val carrier = TypeMapper.map(underlying, toplevel.isWin32Mode)
        val rawProperty = if (KotlinEnumSupport.isOptionsStyle(enumDecl) || toplevel.isObjCSurfaceEnum(enumDecl)) {
            "rawValue"
        } else {
            "value"
        }
        val isOpenEnum = rawProperty == "rawValue"

        return CFunctionTypeLowering(
            kotlinType = name,
            fallbackCarrierValue = carrierDefault(carrier),
            unavailableBindingFallback = if (isOpenEnum) null else ::unavailableGlobalBinding,
            argumentLowering = { rawValueToCarrier("$it.$rawProperty", carrier) },
            returnReconstruction = { raw ->
                val value = carrierToLong("$raw as $carrier", carrier, isUnsigned(underlying))
                if (isOpenEnum) "$name($value)" else "$name.fromValue($value)"
            },
        )
    }

    private fun rawValueToCarrier(expression: String, carrier: String): String = when (carrier) {
        "Long" -> expression
        "Int" -> "$expression.toInt()"
        "Short" -> "$expression.toShort()"
        "Byte" -> "$expression.toByte()"
        else -> expression
    }

    private fun carrierToLong(expression: String, carrier: String, unsigned: Boolean): String = when {
        carrier == "Int" && unsigned -> "Integer.toUnsignedLong($expression)"
        carrier == "Short" && unsigned -> "java.lang.Short.toUnsignedInt($expression).toLong()"
        carrier == "Byte" && unsigned -> "java.lang.Byte.toUnsignedInt($expression).toLong()"
        carrier == "Long" -> expression
        carrier == "Int" || carrier == "Short" || carrier == "Byte" -> "($expression).toLong()"
        else -> expression
    }

    private fun isUnsigned(type: Type): Boolean = when {
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.UNSIGNED -> true
        type is Type.Delegated && type.kind() != Type.Delegated.Kind.POINTER -> isUnsigned(type.type())
        else -> false
    }
}

private fun unavailableGlobalBinding(name: String): String =
    "error(\"Unavailable global binding '$name': optional DLL or symbol is unavailable; make it available and call init() again\")"

private fun carrierDefault(carrier: String): String = when (carrier) {
    "Long" -> "0L"
    "Short" -> "0.toShort()"
    "Byte" -> "0.toByte()"
    "Float" -> "0f"
    "Double" -> "0.0"
    "Boolean" -> "false"
    "Char" -> "'\\u0000'"
    "MemorySegment" -> "MemorySegment.NULL"
    else -> "0"
}
