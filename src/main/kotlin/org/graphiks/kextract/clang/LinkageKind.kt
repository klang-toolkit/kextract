package org.graphiks.kextract.clang

import org.graphiks.kextract.clang.libclang.*
import java.util.NoSuchElementException

enum class LinkageKind(val value: Int) {
    Invalid(CXLinkage_Invalid()),
    NoLinkage(CXLinkage_NoLinkage()),
    Internal(CXLinkage_Internal()),
    UniqueExternal(CXLinkage_UniqueExternal()),
    External(CXLinkage_External());

    companion object {
        private val lookup = entries.associateBy { it.value }

        @JvmStatic
        fun valueOf(value: Int): LinkageKind =
            lookup[value] ?: throw NoSuchElementException("Invalid LinkageKind value: $value")
    }
}
