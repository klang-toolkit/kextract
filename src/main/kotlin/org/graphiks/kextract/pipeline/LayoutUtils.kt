package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Type
import org.graphiks.kextract.DeclarationImpl.ClangEnumType
import org.graphiks.kextract.DeclarationImpl.JavaName

/**
 * Utility class for generating layout strings for code generation.
 * Mirrors the Java LayoutUtils class; callers need not change.
 */
object LayoutUtils {

    fun layoutString(type: Type): String = fieldLayoutString(type, -1, -1)

    fun functionDescriptorString(functionType: Type.Function): String {
        val type = functionType.methodType()
        val noArgs = type.parameterCount() == 0
        return buildString {
            if (type.returnType() != Void.TYPE) {
                append("FunctionDescriptor.of(")
                append(layoutString(functionType.returnType()))
                if (!noArgs) append(", ")
            } else {
                append("FunctionDescriptor.ofVoid(")
            }
            if (!noArgs) {
                append(functionType.argumentTypes().joinToString(", ") { layoutString(it) })
            }
            append(")")
        }
    }

    private fun fieldLayoutString(type: Type, typeAlign: Long, expectedAlign: Long): String {
        if (type.isErroneous()) return "ValueLayout.ADDRESS"
        return when {
            type is Type.Primitive -> primitiveLayoutString(type, typeAlign, expectedAlign)
            type is Type.Declared && type.isEnum() -> {
                val enumType = ClangEnumType.get(type.tree())
                if (enumType != null) fieldLayoutString(enumType, typeAlign, expectedAlign)
                else "ValueLayout.JAVA_INT"
            }
            type is Type.Declared && type.isStructOrUnion() -> {
                val name = JavaName.getFullNameOrThrow(type.tree())
                "${name}.layout"
            }
            type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> "ValueLayout.ADDRESS"
            type is Type.Delegated -> fieldLayoutString(type.type(), typeAlign, expectedAlign)
            type is Type.Function -> "ValueLayout.ADDRESS"
            type is Type.Array -> "MemoryLayout.sequenceLayout(${type.elementCount() ?: 0L}, ${fieldLayoutString(type.elementType(), typeAlign, expectedAlign)})"
            else -> throw UnsupportedOperationException("Unexpected type: $type")
        }
    }

    private fun primitiveLayoutString(type: Type.Primitive, typeAlign: Long, expectedAlign: Long): String {
        val layoutPrefix = when (type.kind()) {
            Type.Primitive.Kind.Bool      -> "ValueLayout.JAVA_BOOLEAN"
            Type.Primitive.Kind.Char      -> "ValueLayout.JAVA_BYTE"
            Type.Primitive.Kind.Short     -> "ValueLayout.JAVA_SHORT"
            Type.Primitive.Kind.Int       -> "ValueLayout.JAVA_INT"
            Type.Primitive.Kind.Long,
            Type.Primitive.Kind.LongLong  -> "ValueLayout.JAVA_LONG"
            Type.Primitive.Kind.Float     -> "ValueLayout.JAVA_FLOAT"
            Type.Primitive.Kind.Double    -> "ValueLayout.JAVA_DOUBLE"
            Type.Primitive.Kind.Char16    -> "ValueLayout.JAVA_CHAR"
            Type.Primitive.Kind.Int128    -> "ValueLayout.JAVA_LONG"
            Type.Primitive.Kind.Void      -> throw UnsupportedOperationException("Void type cannot be laid out")
            else -> throw UnsupportedOperationException("Unexpected primitive kind: ${type.kind()}")
        }
        return if (expectedAlign > 0) "$layoutPrefix.align($layoutPrefix, $expectedAlign)" else layoutPrefix
    }
}
