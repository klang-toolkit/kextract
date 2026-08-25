// src/main/kotlin/org/openjdk/kextract/kotlin/utils/TypeMapper.kt
package org.graphiks.kextract.kotlin.utils

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type

/**
 * Maps C types to Kotlin types.
 * Handles primitives, pointers (nullable), structs, typedefs, and functions.
 */
object TypeMapper {
    internal data class NamedRecord(
        val publicName: String,
        val declaration: Declaration.Scoped,
    )

    /**
     * Maps a C type to its Kotlin equivalent.
     */
    fun map(type: Type, win32Abi: Boolean = false): String = when {
        type is Type.Primitive -> mapPrimitive(type.kind(), win32Abi)
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> {
            // All pointers map to non-nullable MemorySegment
            // Null pointers are represented as MemorySegment.NULL in Panama
            "MemorySegment"
        }

        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> {
            // Unwrap typedef: if inner type resolves (e.g. const_size_t → Long via UNSIGNED(Long)),
            // use the resolved type. Otherwise fall through to the typedef-name logic.
            val innerMapped = map(type.type(), win32Abi)
            if (innerMapped != "Any") return innerMapped
            val inner = type.type()
            if (inner is Type.Delegated && inner.kind() == Type.Delegated.Kind.POINTER) {
                "MemorySegment"
            } else {
                var name = type.name() ?: "Any"
                if (inner is Type.Declared) {
                    val tk = inner.tree().kind()
                    if (tk == Declaration.Scoped.Kind.STRUCT || tk == Declaration.Scoped.Kind.UNION) {
                        return "MemorySegment"
                    }
                }
                if (name == "Class") "MemorySegment" else sanitizeName(name)
            }
        }

        // Qualified types (CONST, UNSIGNED, SIGNED, VOLATILE, ATOMIC, COMPLEX) — unwrap to inner type
        type is Type.Delegated -> map(type.type(), win32Abi)

        type is Type.Declared -> {
            val tree = type.tree()
            when (tree.kind()) {
                Declaration.Scoped.Kind.STRUCT,
                Declaration.Scoped.Kind.UNION -> "MemorySegment"
                Declaration.Scoped.Kind.ENUM ->
                    if (tree.name().isNotEmpty()) tree.name() else "Long"
                else -> "Long"
            }
        }
        type is Type.Function -> mapFunctionType(type, win32Abi)
        type is Type.Array -> "MemorySegment"
        else -> "MemorySegment"
    }

    private fun mapPrimitive(kind: Type.Primitive.Kind, win32Abi: Boolean): String = when (kind) {
        Type.Primitive.Kind.Bool -> "Boolean"
        Type.Primitive.Kind.Char -> "Byte"
        Type.Primitive.Kind.Short -> "Short"
        Type.Primitive.Kind.Int -> "Int"
        Type.Primitive.Kind.Long -> if (win32Abi) "Int" else "Long"
        Type.Primitive.Kind.LongLong -> "Long"
        Type.Primitive.Kind.Float -> "Float"
        Type.Primitive.Kind.Double -> "Double"
        Type.Primitive.Kind.WChar -> if (win32Abi) "Char" else "MemorySegment"
        Type.Primitive.Kind.Void -> "Unit"
        else -> "MemorySegment"
    }

    private fun mapFunctionType(type: Type.Function, win32Abi: Boolean): String {
        // For function return types, we just map the return type itself
        // The function signature is handled separately in KotlinHeaderBuilder
        return map(type.returnType(), win32Abi)
    }

    private fun sanitizeName(name: String): String =
        name.replace(Regex("[^a-zA-Z0-9_]"), "_")
            .replace(Regex("^\\d+"), "_")

    /** Resolves a value record without crossing pointer indirections. */
    internal fun namedStruct(type: Type): NamedRecord? =
        namedRecord(type, Declaration.Scoped.Kind.STRUCT)

    /** Resolves the pointee record only when [type] is a pointer to a struct. */
    internal fun pointedStruct(type: Type): NamedRecord? =
        if (type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER) {
            namedStruct(type.type())
        } else {
            null
        }

    private fun namedRecord(
        type: Type,
        target: Declaration.Scoped.Kind,
        preferredName: String? = null,
    ): NamedRecord? = when {
        type is Type.Declared && type.tree().kind() == target -> {
            val name = preferredName ?: type.tree().name()
            name.takeIf(String::isNotEmpty)?.let { NamedRecord(it, type.tree()) }
        }
        type is Type.Delegated && type.kind() != Type.Delegated.Kind.POINTER -> {
            val name = if (type.kind() == Type.Delegated.Kind.TYPEDEF) {
                preferredName ?: type.name()
            } else {
                preferredName
            }
            namedRecord(type.type(), target, name)
        }
        else -> null
    }
}
