/*
 *  Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 *  This code is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License version 2 only, as
 *  published by the Free Software Foundation.  Oracle designates this
 *  particular file as subject to the "Classpath" exception as provided
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
package org.openjdk.kextract

import java.nio.file.Path

/**
 * Instances of this class model are used to model source code positions.
 */
interface Position {
    fun path(): Path?
    fun line(): Int
    fun col(): Int

    companion object {
        @JvmField
        val NO_POSITION: Position = object : Position {
            override fun path(): Path? = null
            override fun line(): Int = 0
            override fun col(): Int = 0
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                return other is Position &&
                    path() == other.path() &&
                    line() == other.line() &&
                    col() == other.col()
            }
            override fun hashCode(): Int = 0
            override fun toString(): String = "NO_POSITION"
        }
    }
}
