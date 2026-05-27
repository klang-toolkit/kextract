package org.graphiks.kextract.clang

import org.graphiks.kextract.clang.libclang.*
import java.util.NoSuchElementException

enum class ErrorCode(val code: Int) {
    Success(CXError_Success()),
    Failure(CXError_Failure()),
    Crashed(CXError_Crashed()),
    InvalidArguments(CXError_InvalidArguments()),
    ASTReadError(CXError_ASTReadError());

    companion object {
        private val lookup = entries.associateBy { it.code }

        @JvmStatic
        fun valueOf(code: Int): ErrorCode =
            lookup[code] ?: throw NoSuchElementException("No ErrorCode with code: $code")
    }
}
