package org.graphiks.kextract.clang

import java.lang.foreign.MemorySegment
import org.graphiks.kextract.clang.libclang.*

open class EvalResult(private var ptr: MemorySegment) : AutoCloseable {

    enum class Kind { Integral, FloatingPoint, StrLiteral, Erroneous, Unknown }

    open fun getKind(): Kind = when (clang_EvalResult_getKind(ptr)) {
        1    -> Kind.Integral
        2    -> Kind.FloatingPoint
        3, 4, 5 -> Kind.StrLiteral
        else -> Kind.Unknown
    }

    fun getAsInt(): Long {
        check(getKind() == Kind.Integral) { "Unexpected kind: ${getKind()}" }
        return clang_EvalResult_getAsLongLong(ptr)
    }

    fun getAsFloat(): Double {
        check(getKind() == Kind.FloatingPoint) { "Unexpected kind: ${getKind()}" }
        return clang_EvalResult_getAsDouble(ptr)
    }

    fun getAsString(): String {
        check(getKind() == Kind.StrLiteral) { "Unexpected kind: ${getKind()}" }
        // Reinterpret with unbounded size so getString can scan for null terminator
        return clang_EvalResult_getAsStr(ptr).reinterpret(Long.MAX_VALUE).getString(0)
    }

    override fun close() {
        if (ptr != MemorySegment.NULL) {
            clang_EvalResult_dispose(ptr)
            ptr = MemorySegment.NULL
        }
    }

    companion object {
        @JvmField
        val erroneous: EvalResult = object : EvalResult(MemorySegment.NULL) {
            override fun getKind() = Kind.Erroneous
            override fun close() {}
        }
    }
}
