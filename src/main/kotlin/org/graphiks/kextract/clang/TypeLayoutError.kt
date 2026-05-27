package org.graphiks.kextract.clang

import java.util.NoSuchElementException

class TypeLayoutError(value: Long, message: String) :
    IllegalStateException("${Kind.valueOf(value)}. $message") {

    val kind: Kind = Kind.valueOf(value)

    companion object {
        @JvmStatic
        fun isError(value: Long): Boolean = Kind.isError(value)
    }

    enum class Kind(val value: Long) {
        Invalid(-1),
        Incomplete(-2),
        Dependent(-3),
        NotConstantSize(-4),
        InvalidFieldName(-5);

        companion object {
            private val lookup = entries.associateBy { it.value }

            @JvmStatic
            fun valueOf(value: Long): Kind =
                lookup[value] ?: throw NoSuchElementException("TypeLayoutError = $value")

            @JvmStatic
            fun isError(value: Long): Boolean = value in lookup
        }
    }
}
