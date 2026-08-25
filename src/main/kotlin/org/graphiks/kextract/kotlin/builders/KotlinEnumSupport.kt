package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.getAttribute

internal object KotlinEnumSupport {
    fun resolveEnum(type: Type): Declaration.Scoped? = when {
        type is Type.Declared &&
            type.tree().kind() == Declaration.Scoped.Kind.ENUM &&
            isNamedClangEnum(type.tree().name()) -> type.tree()
        type is Type.Delegated && type.kind() != Type.Delegated.Kind.POINTER -> resolveEnum(type.type())
        else -> null
    }

    /** Clang's semantic marker for `NS_OPTIONS` / `__attribute__((flag_enum))`. */
    fun isOptionsStyle(decl: Declaration.Scoped): Boolean =
        decl.getAttribute<Declaration.ClangAttributes>()
            ?.attributes
            ?.keys
            ?.any { it == "FlagEnum" }
            ?: false

    /**
     * Clang preserves extension and Unicode identifiers (for example names containing `$`),
     * but synthesizes descriptions such as `enum (unnamed at ...)` for anonymous declarations.
     * Parentheses cannot occur in a C identifier, so rejecting only these pseudo-name markers
     * keeps real non-empty Clang names without imposing an ASCII-only identifier grammar.
     */
    private fun isNamedClangEnum(name: String): Boolean =
        name.isNotEmpty() &&
            !name.contains("(unnamed", ignoreCase = true) &&
            !name.contains("(anonymous", ignoreCase = true)
}
