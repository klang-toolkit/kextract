package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Type
import org.graphiks.kextract.TypeImpl
import org.graphiks.kextract.DeclarationImpl.ClangEnumType
import org.graphiks.kextract.DeclarationImpl.JavaName
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiIndex

/**
 * Utility class for generating layout strings for code generation.
 * Mirrors the Java LayoutUtils class; callers need not change.
 */
object LayoutUtils {

    fun layoutString(type: Type): String = fieldLayoutString(type, -1, -1, abiIndex = null)

    internal fun layoutString(type: Type, abiIndex: KotlinKmpAbiIndex): String =
        fieldLayoutString(type, -1, -1, abiIndex)

    fun layoutString(type: Type, byteAlignment: Long): String {
        return alignedLayoutString(type, byteAlignment, abiIndex = null)
    }

    internal fun layoutString(
        type: Type,
        byteAlignment: Long,
        abiIndex: KotlinKmpAbiIndex,
    ): String = alignedLayoutString(type, byteAlignment, abiIndex)

    private fun alignedLayoutString(
        type: Type,
        byteAlignment: Long,
        abiIndex: KotlinKmpAbiIndex?,
    ): String {
        require(byteAlignment > 0L && byteAlignment.countOneBits() == 1) {
            "Invalid byte alignment: $byteAlignment"
        }
        if (type is Type.Delegated && type.kind() != Type.Delegated.Kind.POINTER) {
            return alignedLayoutString(type.type(), byteAlignment, abiIndex)
        }
        val layout = if (type is Type.Array) {
            "MemoryLayout.sequenceLayout(${type.elementCount() ?: 0L}, " +
                "${alignedLayoutString(type.elementType(), byteAlignment, abiIndex)})"
        } else {
            fieldLayoutString(type, -1, -1, abiIndex)
        }
        return "$layout.withByteAlignment($byteAlignment)"
    }

    fun functionDescriptorString(functionType: Type.Function, variadicCount: Int = 0): String {
        return functionDescriptorString(functionType, variadicCount, abiIndex = null)
    }

    internal fun functionDescriptorString(
        functionType: Type.Function,
        abiIndex: KotlinKmpAbiIndex,
        variadicCount: Int = 0,
    ): String = functionDescriptorString(functionType, variadicCount, abiIndex)

    private fun functionDescriptorString(
        functionType: Type.Function,
        variadicCount: Int,
        abiIndex: KotlinKmpAbiIndex?,
    ): String {
        val type = functionType.methodType()
        val noArgs = type.parameterCount() == 0 && variadicCount == 0
        return buildString {
            if (type.returnType() != Void.TYPE) {
                append("FunctionDescriptor.of(")
                append(fieldLayoutString(functionType.returnType(), -1, -1, abiIndex))
                if (!noArgs) append(", ")
            } else {
                append("FunctionDescriptor.ofVoid(")
            }
            if (type.parameterCount() > 0) {
                append(
                    functionType.argumentTypes().joinToString(", ") {
                        fieldLayoutString(it, -1, -1, abiIndex)
                    },
                )
            }
            if (variadicCount > 0) {
                if (type.parameterCount() > 0) append(", ")
                append((0 until variadicCount).joinToString(", ") { "ValueLayout.ADDRESS" })
            }
            append(")")
        }
    }

    private fun fieldLayoutString(
        type: Type,
        typeAlign: Long,
        expectedAlign: Long,
        abiIndex: KotlinKmpAbiIndex?,
    ): String {
        if (type.isErroneous()) {
            if (type is org.graphiks.kextract.TypeImpl.ErronrousTypeImpl) {
                val name = type.erroneousName
                if (!name.contains("*")) {
                    val match = "\\b(WGPU[a-zA-Z0-9_]+)\\b".toRegex().find(name)
                    if (match != null) {
                        return "${match.value}.layout"
                    }
                }
            }
            return "ValueLayout.ADDRESS"
        }
        return when {
            type is Type.Primitive -> primitiveLayoutString(type, typeAlign, expectedAlign)
            type is Type.Declared && type.isEnum() -> {
                abiIndex?.enum(type.tree())?.jvmLayout ?: run {
                    val enumType = ClangEnumType.get(type.tree())
                    if (enumType != null) fieldLayoutString(enumType, typeAlign, expectedAlign, abiIndex)
                    else "ValueLayout.JAVA_INT"
                }
            }
            type is Type.Declared && type.isStructOrUnion() -> {
                val name = JavaName.getFullNameOrThrow(type.tree())
                "${name}.layout"
            }
            type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> "ValueLayout.ADDRESS"
            type is Type.Delegated -> fieldLayoutString(type.type(), typeAlign, expectedAlign, abiIndex)
            type is Type.Function -> "ValueLayout.ADDRESS"
            type is Type.Array -> "MemoryLayout.sequenceLayout(${type.elementCount() ?: 0L}, ${fieldLayoutString(type.elementType(), typeAlign, expectedAlign, abiIndex)})"
            else -> throw UnsupportedOperationException("Unexpected type: $type")
        }
    }

    private fun primitiveLayoutString(type: Type.Primitive, typeAlign: Long, expectedAlign: Long): String {
        val layoutPrefix = when (type.kind()) {
            Type.Primitive.Kind.Bool      -> "ValueLayout.JAVA_BOOLEAN"
            Type.Primitive.Kind.Char      -> "ValueLayout.JAVA_BYTE"
            Type.Primitive.Kind.Short     -> "ValueLayout.JAVA_SHORT"
            Type.Primitive.Kind.Int       -> "ValueLayout.JAVA_INT"
            Type.Primitive.Kind.Long      ->
                if (TypeImpl.IS_WINDOWS) "ValueLayout.JAVA_INT" else "ValueLayout.JAVA_LONG"
            Type.Primitive.Kind.LongLong  -> "ValueLayout.JAVA_LONG"
            Type.Primitive.Kind.Float     -> "ValueLayout.JAVA_FLOAT"
            Type.Primitive.Kind.Double    -> "ValueLayout.JAVA_DOUBLE"
            Type.Primitive.Kind.Char16    -> "ValueLayout.JAVA_CHAR"
            Type.Primitive.Kind.WChar     -> "ValueLayout.JAVA_CHAR"
            Type.Primitive.Kind.Int128    -> "ValueLayout.JAVA_LONG"
            Type.Primitive.Kind.Void      -> throw UnsupportedOperationException("Void type cannot be laid out")
            else -> throw UnsupportedOperationException("Unexpected primitive kind: ${type.kind()}")
        }
        return if (expectedAlign > 0) "$layoutPrefix.align($layoutPrefix, $expectedAlign)" else layoutPrefix
    }
}
