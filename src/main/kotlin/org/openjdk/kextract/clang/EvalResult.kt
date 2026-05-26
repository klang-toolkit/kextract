/*
 *  Copyright (c) 2020, 2024, Oracle and/or its affiliates. All rights reserved.
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 *  This code is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License version 2 only, as
 *  published by the Free Software Foundation.  Oracle designates this
 *  particular file is subject to the "Classpath" exception as provided
 *  by Oracle in the LICENSE file that accompanied this code.
 *
 *  This code is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 *  FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 *  version 2 for more details (a copy is included in the LICENSE file that
 *  accompanied this code).
 *
 *  You should have received a copy of the GNU General Public License version
 *  2 along with this work; if not, write to the Free Software Foundation,
 *  Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 *   Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 *  or visit www.oracle.com if you need additional information or have any
 *  questions.
 *
 */
package org.openjdk.kextract.clang

import java.lang.foreign.MemorySegment
import org.openjdk.kextract.clang.libclang.*

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
