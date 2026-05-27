package org.graphiks.kextract.clang

import org.graphiks.kextract.clang.libclang.*
import java.util.NoSuchElementException

enum class CallingConvention(val value: Int) {
    Default(CXCallingConv_Default()),
    C(CXCallingConv_C()),
    X86StdCall(CXCallingConv_X86StdCall()),
    X86FastCall(CXCallingConv_X86FastCall()),
    X86ThisCall(CXCallingConv_X86ThisCall()),
    X86Pascal(CXCallingConv_X86Pascal()),
    AAPCS(CXCallingConv_AAPCS()),
    AAPCS_VFP(CXCallingConv_AAPCS_VFP()),
    PnaclCall(CXCallingConv_X86RegCall()),
    IntelOclBicc(CXCallingConv_IntelOclBicc()),
    X86_64Win64(CXCallingConv_X86_64Win64()),
    X86_64SysV(CXCallingConv_X86_64SysV()),
    Invalid(CXCallingConv_Invalid()),
    Unexposed(CXCallingConv_Unexposed());

    companion object {
        private val lookup = entries.associateBy { it.value }

        @JvmStatic
        fun valueOf(value: Int): CallingConvention =
            lookup[value] ?: throw NoSuchElementException("No CallingConvention with value: $value")
    }
}
