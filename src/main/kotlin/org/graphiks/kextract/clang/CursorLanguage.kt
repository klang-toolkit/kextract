package org.graphiks.kextract.clang

import org.graphiks.kextract.clang.libclang.*
import java.util.NoSuchElementException

enum class CursorLanguage(val code: Int, private val displayName: String) {
    Invalid(CXLanguage_Invalid(), "Invalid"),
    C(CXLanguage_C(), "C"),
    ObjC(CXLanguage_ObjC(), "Objective C"),
    CPlusPlus(CXLanguage_CPlusPlus(), "C++");

    override fun toString(): String = displayName

    companion object {
        private val lookup = entries.associateBy { it.code }

        @JvmStatic
        fun valueOf(code: Int): CursorLanguage =
            lookup[code] ?: throw NoSuchElementException("No CursorLanguage with code: $code")
    }
}
