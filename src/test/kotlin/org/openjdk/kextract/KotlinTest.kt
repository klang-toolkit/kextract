/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package org.openjdk.kextract

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Simple Kotlin test to validate Kotlin JVM plugin configuration.
 * This test verifies that Kotlin code can be compiled and executed
 * in the kextract project.
 */
class KotlinTest {

    @Test
    fun testKotlinWorks() {
        val message = "Kotlin is working in kextract!"
        assertEquals(message, message, "Kotlin test should pass")
    }

    @Test
    fun testStringManipulation() {
        val input = "Hello, Kotlin!"
        val expected = "HELLO, KOTLIN!"
        assertEquals(expected, input.uppercase(), "String uppercase should work")
    }

    @Test
    fun testCollectionOperations() {
        val numbers = listOf(1, 2, 3, 4, 5)
        val sum = numbers.sum()
        assertEquals(15, sum, "List sum should be 15")
    }
}
