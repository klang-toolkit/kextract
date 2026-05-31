/**
 * Unit tests for the DPI types and their conversions.
 *
 * Verifies:
 * - Identical round-trip at scaleFactor = 1.0
 * - Conversion with scaleFactor = 2.0 produces the expected result
 * - Coverage of the Int, Float and Double types
 */
package org.graphiks.kadre.core

import kotlin.test.Test
import kotlin.test.assertEquals

class DpiTest {

    // -----------------------------------------------------------------------
    // LogicalSize → PhysicalSize
    // -----------------------------------------------------------------------

    @Test
    fun `LogicalSize Int toPhysical scaleFactor 1 doit être identique`() {
        val logique = LogicalSize(100, 200)
        val physique = logique.toPhysical(scaleFactor = 1.0)
        assertEquals(PhysicalSize(100.0, 200.0), physique)
    }

    @Test
    fun `LogicalSize Float toPhysical scaleFactor 1 doit être identique`() {
        val logique = LogicalSize(100f, 200f)
        val physique = logique.toPhysical(scaleFactor = 1.0)
        assertEquals(PhysicalSize(100.0, 200.0), physique)
    }

    @Test
    fun `LogicalSize Double toPhysical scaleFactor 1 doit être identique`() {
        val logique = LogicalSize(100.0, 200.0)
        val physique = logique.toPhysical(scaleFactor = 1.0)
        assertEquals(PhysicalSize(100.0, 200.0), physique)
    }

    @Test
    fun `LogicalSize Int toPhysical scaleFactor 2 doit doubler les dimensions`() {
        val logique = LogicalSize(100, 50)
        val physique = logique.toPhysical(scaleFactor = 2.0)
        assertEquals(PhysicalSize(200.0, 100.0), physique)
    }

    @Test
    fun `LogicalSize Float toPhysical scaleFactor 2 doit doubler les dimensions`() {
        val logique = LogicalSize(100f, 50f)
        val physique = logique.toPhysical(scaleFactor = 2.0)
        assertEquals(PhysicalSize(200.0, 100.0), physique)
    }

    @Test
    fun `LogicalSize Double toPhysical scaleFactor 2 doit doubler les dimensions`() {
        val logique = LogicalSize(100.0, 50.0)
        val physique = logique.toPhysical(scaleFactor = 2.0)
        assertEquals(PhysicalSize(200.0, 100.0), physique)
    }

    // -----------------------------------------------------------------------
    // PhysicalSize → LogicalSize
    // -----------------------------------------------------------------------

    @Test
    fun `PhysicalSize Int toLogical scaleFactor 1 doit être identique`() {
        val physique = PhysicalSize(100, 200)
        val logique = physique.toLogical(scaleFactor = 1.0)
        assertEquals(LogicalSize(100.0, 200.0), logique)
    }

    @Test
    fun `PhysicalSize Float toLogical scaleFactor 1 doit être identique`() {
        val physique = PhysicalSize(100f, 200f)
        val logique = physique.toLogical(scaleFactor = 1.0)
        assertEquals(LogicalSize(100.0, 200.0), logique)
    }

    @Test
    fun `PhysicalSize Double toLogical scaleFactor 1 doit être identique`() {
        val physique = PhysicalSize(100.0, 200.0)
        val logique = physique.toLogical(scaleFactor = 1.0)
        assertEquals(LogicalSize(100.0, 200.0), logique)
    }

    @Test
    fun `PhysicalSize Int toLogical scaleFactor 2 doit diviser les dimensions par deux`() {
        val physique = PhysicalSize(200, 100)
        val logique = physique.toLogical(scaleFactor = 2.0)
        assertEquals(LogicalSize(100.0, 50.0), logique)
    }

    @Test
    fun `PhysicalSize Float toLogical scaleFactor 2 doit diviser les dimensions par deux`() {
        val physique = PhysicalSize(200f, 100f)
        val logique = physique.toLogical(scaleFactor = 2.0)
        assertEquals(LogicalSize(100.0, 50.0), logique)
    }

    @Test
    fun `PhysicalSize Double toLogical scaleFactor 2 doit diviser les dimensions par deux`() {
        val physique = PhysicalSize(200.0, 100.0)
        val logique = physique.toLogical(scaleFactor = 2.0)
        assertEquals(LogicalSize(100.0, 50.0), logique)
    }

    // -----------------------------------------------------------------------
    // Round-trip LogicalSize ↔ PhysicalSize (scaleFactor = 1.0)
    // -----------------------------------------------------------------------

    @Test
    fun `LogicalSize round-trip via PhysicalSize scaleFactor 1 doit être identique`() {
        val original = LogicalSize(800.0, 600.0)
        val aller = original.toPhysical(scaleFactor = 1.0)
        val retour = aller.toLogical(scaleFactor = 1.0)
        assertEquals(original, retour)
    }

    @Test
    fun `LogicalSize round-trip via PhysicalSize scaleFactor 2 doit être identique`() {
        val original = LogicalSize(800.0, 600.0)
        val aller = original.toPhysical(scaleFactor = 2.0)
        val retour = aller.toLogical(scaleFactor = 2.0)
        assertEquals(original, retour)
    }

    // -----------------------------------------------------------------------
    // LogicalPosition → PhysicalPosition
    // -----------------------------------------------------------------------

    @Test
    fun `LogicalPosition Int toPhysical scaleFactor 1 doit être identique`() {
        val logique = LogicalPosition(10, 20)
        val physique = logique.toPhysical(scaleFactor = 1.0)
        assertEquals(PhysicalPosition(10.0, 20.0), physique)
    }

    @Test
    fun `LogicalPosition Float toPhysical scaleFactor 1 doit être identique`() {
        val logique = LogicalPosition(10f, 20f)
        val physique = logique.toPhysical(scaleFactor = 1.0)
        assertEquals(PhysicalPosition(10.0, 20.0), physique)
    }

    @Test
    fun `LogicalPosition Double toPhysical scaleFactor 1 doit être identique`() {
        val logique = LogicalPosition(10.0, 20.0)
        val physique = logique.toPhysical(scaleFactor = 1.0)
        assertEquals(PhysicalPosition(10.0, 20.0), physique)
    }

    @Test
    fun `LogicalPosition Int toPhysical scaleFactor 2 doit doubler les coordonnées`() {
        val logique = LogicalPosition(100, 50)
        val physique = logique.toPhysical(scaleFactor = 2.0)
        assertEquals(PhysicalPosition(200.0, 100.0), physique)
    }

    @Test
    fun `LogicalPosition Float toPhysical scaleFactor 2 doit doubler les coordonnées`() {
        val logique = LogicalPosition(100f, 50f)
        val physique = logique.toPhysical(scaleFactor = 2.0)
        assertEquals(PhysicalPosition(200.0, 100.0), physique)
    }

    @Test
    fun `LogicalPosition Double toPhysical scaleFactor 2 doit doubler les coordonnées`() {
        val logique = LogicalPosition(100.0, 50.0)
        val physique = logique.toPhysical(scaleFactor = 2.0)
        assertEquals(PhysicalPosition(200.0, 100.0), physique)
    }

    // -----------------------------------------------------------------------
    // PhysicalPosition → LogicalPosition
    // -----------------------------------------------------------------------

    @Test
    fun `PhysicalPosition Int toLogical scaleFactor 1 doit être identique`() {
        val physique = PhysicalPosition(10, 20)
        val logique = physique.toLogical(scaleFactor = 1.0)
        assertEquals(LogicalPosition(10.0, 20.0), logique)
    }

    @Test
    fun `PhysicalPosition Float toLogical scaleFactor 1 doit être identique`() {
        val physique = PhysicalPosition(10f, 20f)
        val logique = physique.toLogical(scaleFactor = 1.0)
        assertEquals(LogicalPosition(10.0, 20.0), logique)
    }

    @Test
    fun `PhysicalPosition Double toLogical scaleFactor 1 doit être identique`() {
        val physique = PhysicalPosition(10.0, 20.0)
        val logique = physique.toLogical(scaleFactor = 1.0)
        assertEquals(LogicalPosition(10.0, 20.0), logique)
    }

    @Test
    fun `PhysicalPosition Int toLogical scaleFactor 2 doit diviser les coordonnées par deux`() {
        val physique = PhysicalPosition(200, 100)
        val logique = physique.toLogical(scaleFactor = 2.0)
        assertEquals(LogicalPosition(100.0, 50.0), logique)
    }

    @Test
    fun `PhysicalPosition Float toLogical scaleFactor 2 doit diviser les coordonnées par deux`() {
        val physique = PhysicalPosition(200f, 100f)
        val logique = physique.toLogical(scaleFactor = 2.0)
        assertEquals(LogicalPosition(100.0, 50.0), logique)
    }

    @Test
    fun `PhysicalPosition Double toLogical scaleFactor 2 doit diviser les coordonnées par deux`() {
        val physique = PhysicalPosition(200.0, 100.0)
        val logique = physique.toLogical(scaleFactor = 2.0)
        assertEquals(LogicalPosition(100.0, 50.0), logique)
    }

    // -----------------------------------------------------------------------
    // Round-trip LogicalPosition ↔ PhysicalPosition (scaleFactor = 1.0)
    // -----------------------------------------------------------------------

    @Test
    fun `LogicalPosition round-trip via PhysicalPosition scaleFactor 1 doit être identique`() {
        val original = LogicalPosition(300.0, 400.0)
        val aller = original.toPhysical(scaleFactor = 1.0)
        val retour = aller.toLogical(scaleFactor = 1.0)
        assertEquals(original, retour)
    }

    @Test
    fun `LogicalPosition round-trip via PhysicalPosition scaleFactor 2 doit être identique`() {
        val original = LogicalPosition(300.0, 400.0)
        val aller = original.toPhysical(scaleFactor = 2.0)
        val retour = aller.toLogical(scaleFactor = 2.0)
        assertEquals(original, retour)
    }
}
