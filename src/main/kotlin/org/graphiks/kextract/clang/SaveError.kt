package org.graphiks.kextract.clang

import org.graphiks.kextract.clang.libclang.*
import java.util.NoSuchElementException

enum class SaveError(val code: Int) {
    None(CXSaveError_None()),
    Unknown(CXSaveError_Unknown()),
    TranslationErrors(CXSaveError_TranslationErrors()),
    InvalidTU(CXSaveError_InvalidTU());

    companion object {
        private val lookup = entries.associateBy { it.code }

        @JvmStatic
        fun valueOf(code: Int): SaveError =
            lookup[code] ?: throw NoSuchElementException("No SaveError with code: $code")
    }
}
