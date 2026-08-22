package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Type
import org.graphiks.kextract.DeclarationImpl.ClangEnumType
import org.graphiks.kextract.DeclarationImpl.JavaName
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiIndex

/**
 * Utility class for generating layout strings for code generation.
 * Mirrors the Java LayoutUtils class; callers need not change.
 */
object LayoutUtils {

    fun layoutString(type: Type, win32Abi: Boolean = false): String =
        fieldLayoutString(type, -1, -1, abiIndex = null, win32Abi = win32Abi)

    internal fun layoutString(type: Type, abiIndex: KotlinKmpAbiIndex): String =
        fieldLayoutString(type, -1, -1, abiIndex, win32Abi = false)

    fun layoutString(type: Type, byteAlignment: Long, win32Abi: Boolean = false): String {
        return alignedLayoutString(type, byteAlignment, abiIndex = null, win32Abi = win32Abi)
    }

    internal fun layoutString(
        type: Type,
        byteAlignment: Long,
        abiIndex: KotlinKmpAbiIndex,
    ): String = alignedLayoutString(type, byteAlignment, abiIndex, win32Abi = false)

    private fun alignedLayoutString(
        type: Type,
        byteAlignment: Long,
        abiIndex: KotlinKmpAbiIndex?,
        win32Abi: Boolean,
    ): String {
        require(byteAlignment > 0L && byteAlignment.countOneBits() == 1) {
            "Invalid byte alignment: $byteAlignment"
        }
        if (type is Type.Delegated && type.kind() != Type.Delegated.Kind.POINTER) {
            return alignedLayoutString(type.type(), byteAlignment, abiIndex, win32Abi)
        }
        val layout = if (type is Type.Array) {
            "MemoryLayout.sequenceLayout(${type.elementCount() ?: 0L}, " +
                "${alignedLayoutString(type.elementType(), byteAlignment, abiIndex, win32Abi)})"
        } else {
            fieldLayoutString(type, -1, -1, abiIndex, win32Abi)
        }
        return "$layout.withByteAlignment($byteAlignment)"
    }

    fun functionDescriptorString(
        functionType: Type.Function,
        variadicCount: Int = 0,
        win32Abi: Boolean = false,
    ): String {
        return functionDescriptorString(functionType, variadicCount, abiIndex = null, win32Abi)
    }

    internal fun functionDescriptorString(
        functionType: Type.Function,
        abiIndex: KotlinKmpAbiIndex,
        variadicCount: Int = 0,
    ): String = functionDescriptorString(functionType, variadicCount, abiIndex, win32Abi = false)

    private fun functionDescriptorString(
        functionType: Type.Function,
        variadicCount: Int,
        abiIndex: KotlinKmpAbiIndex?,
        win32Abi: Boolean,
    ): String {
        val type = functionType.methodType(win32Abi)
        val noArgs = type.parameterCount() == 0 && variadicCount == 0
        return buildString {
            if (type.returnType() != Void.TYPE) {
                append("FunctionDescriptor.of(")
                append(fieldLayoutString(functionType.returnType(), -1, -1, abiIndex, win32Abi))
                if (!noArgs) append(", ")
            } else {
                append("FunctionDescriptor.ofVoid(")
            }
            if (type.parameterCount() > 0) {
                append(
                    functionType.argumentTypes().joinToString(", ") {
                        fieldLayoutString(it, -1, -1, abiIndex, win32Abi)
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
        win32Abi: Boolean,
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
            type is Type.Primitive -> primitiveLayoutString(type, typeAlign, expectedAlign, win32Abi)
            type is Type.Declared && type.isEnum() -> {
                abiIndex?.enum(type.tree())?.jvmLayout ?: run {
                    val enumType = ClangEnumType.get(type.tree())
                    if (enumType != null) fieldLayoutString(enumType, typeAlign, expectedAlign, abiIndex, win32Abi)
                    else "ValueLayout.JAVA_INT"
                }
            }
            type is Type.Declared && type.isStructOrUnion() -> {
                val name = JavaName.getFullNameOrThrow(type.tree())
                "${name}.layout"
            }
            type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> "ValueLayout.ADDRESS"
            type is Type.Delegated -> fieldLayoutString(type.type(), typeAlign, expectedAlign, abiIndex, win32Abi)
            type is Type.Function -> "ValueLayout.ADDRESS"
            type is Type.Array -> "MemoryLayout.sequenceLayout(${type.elementCount() ?: 0L}, ${fieldLayoutString(type.elementType(), typeAlign, expectedAlign, abiIndex, win32Abi)})"
            else -> throw UnsupportedOperationException("Unexpected type: $type")
        }
    }

    private fun primitiveLayoutString(
        type: Type.Primitive,
        typeAlign: Long,
        expectedAlign: Long,
        win32Abi: Boolean,
    ): String {
        val layoutPrefix = when (type.kind()) {
            Type.Primitive.Kind.Bool      -> "ValueLayout.JAVA_BOOLEAN"
            Type.Primitive.Kind.Char      -> "ValueLayout.JAVA_BYTE"
            Type.Primitive.Kind.Short     -> "ValueLayout.JAVA_SHORT"
            Type.Primitive.Kind.Int       -> "ValueLayout.JAVA_INT"
            Type.Primitive.Kind.Long      -> if (win32Abi) "ValueLayout.JAVA_INT" else "ValueLayout.JAVA_LONG"
            Type.Primitive.Kind.LongLong  -> "ValueLayout.JAVA_LONG"
            Type.Primitive.Kind.Float     -> "ValueLayout.JAVA_FLOAT"
            Type.Primitive.Kind.Double    -> "ValueLayout.JAVA_DOUBLE"
            Type.Primitive.Kind.Char16    -> "ValueLayout.JAVA_CHAR"
            Type.Primitive.Kind.WChar     -> if (win32Abi) "ValueLayout.JAVA_CHAR" else
                throw UnsupportedOperationException("wchar_t requires the Win32 ABI")
            Type.Primitive.Kind.Int128    -> "ValueLayout.JAVA_LONG"
            Type.Primitive.Kind.Void      -> throw UnsupportedOperationException("Void type cannot be laid out")
            else -> throw UnsupportedOperationException("Unexpected primitive kind: ${type.kind()}")
        }
        return if (expectedAlign > 0) "$layoutPrefix.align($layoutPrefix, $expectedAlign)" else layoutPrefix
    }
}
