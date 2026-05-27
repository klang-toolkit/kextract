package org.graphiks.kextract

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
