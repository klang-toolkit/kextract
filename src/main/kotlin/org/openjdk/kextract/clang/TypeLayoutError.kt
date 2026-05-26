/*
 *  Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
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
