// src/main/kotlin/org/openjdk/kextract/kotlin/utils/TypeMapper.kt
package org.openjdk.kextract.kotlin.utils

import org.openjdk.kextract.Type

/**
 * Maps C types to Kotlin types.
 * Handles primitives, pointers (nullable), structs, typedefs, and functions.
 */
object TypeMapper {
    /**
     * Maps a C type to its Kotlin equivalent.
     */
    fun map(type: Type): String = when {
        type is Type.Primitive -> mapPrimitive(type.kind())
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> {
            // All pointers map to non-nullable MemorySegment
            // Null pointers are represented as MemorySegment.NULL in Panama
            "MemorySegment"
        }

        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> {
            val name = type.name() ?: "Any"
            sanitizeName(name)
        }

        type is Type.Declared -> {
            val tree = type.tree()
            if (tree is org.openjdk.kextract.Declaration.Scoped &&
                (tree.kind() == org.openjdk.kextract.Declaration.Scoped.Kind.STRUCT ||
                 tree.kind() == org.openjdk.kextract.Declaration.Scoped.Kind.UNION)) {
                "MemorySegment"
            } else {
                "Int"   // enums are int-backed in C
            }
        }
        type is Type.Function -> mapFunctionType(type)
        type is Type.Array -> "MemorySegment"
        else -> "Any"
    }

    private fun mapPrimitive(kind: Type.Primitive.Kind): String = when (kind) {
        Type.Primitive.Kind.Bool -> "Boolean"
        Type.Primitive.Kind.Char -> "Byte"
        Type.Primitive.Kind.Short -> "Short"
        Type.Primitive.Kind.Int -> "Int"
        Type.Primitive.Kind.Long, Type.Primitive.Kind.LongLong -> "Long"
        Type.Primitive.Kind.Float -> "Float"
        Type.Primitive.Kind.Double -> "Double"
        Type.Primitive.Kind.Void -> "Unit"
        else -> "Any"
    }

    private fun mapFunctionType(type: Type.Function): String {
        // For function return types, we just map the return type itself
        // The function signature is handled separately in KotlinHeaderBuilder
        return map(type.returnType())
    }

    private fun sanitizeName(name: String): String =
        name.replace(Regex("[^a-zA-Z0-9_]"), "_")
            .replace(Regex("^\\d+"), "_")
}
