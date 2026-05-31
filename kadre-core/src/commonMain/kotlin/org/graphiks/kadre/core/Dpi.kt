/**
 * DPI types for handling sizes and positions in physical and logical coordinates.
 *
 * ## Physical vs logical coordinates
 *
 * **Physical coordinates** (Physical) represent real pixels on the screen,
 * as addressed by the hardware. They depend on the monitor's native resolution
 * and account for the scale factor (DPI scaling).
 *
 * **Logical coordinates** (Logical) represent units independent of the display
 * density (device-independent pixels). They correspond to what the developer
 * manipulates conceptually, without worrying about the screen's physical
 * density. The operating system applies a scale factor (`scaleFactor`)
 * to convert between the two spaces.
 *
 * ### Example
 * On a Retina screen with `scaleFactor = 2.0`:
 * - a window of logical size 800 × 600 physically occupies 1600 × 1200 pixels.
 *
 * ## Conversions
 *
 * Use the `toPhysical` and `toLogical` extension functions to move from one
 * coordinate space to the other:
 *
 * ```kotlin
 * val logique = LogicalSize(800, 600)
 * val physique = logique.toPhysical(scaleFactor = 2.0) // PhysicalSize(1600.0, 1200.0)
 * val retour  = physique.toLogical(scaleFactor = 2.0)  // LogicalSize(800.0, 600.0)
 * ```
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.core

// ---------------------------------------------------------------------------
// Physical size
// ---------------------------------------------------------------------------

/**
 * Size expressed in **physical pixels** (real hardware pixels).
 *
 * @param T Numeric type of the components (e.g. [Int], [Float], [Double]).
 * @property width Width in physical pixels.
 * @property height Height in physical pixels.
 */
data class PhysicalSize<T : Number>(val width: T, val height: T)

// ---------------------------------------------------------------------------
// Logical size
// ---------------------------------------------------------------------------

/**
 * Size expressed in **logical units** (device-independent pixels).
 *
 * @param T Numeric type of the components (e.g. [Int], [Float], [Double]).
 * @property width Width in logical units.
 * @property height Height in logical units.
 */
data class LogicalSize<T : Number>(val width: T, val height: T)

// ---------------------------------------------------------------------------
// Physical position
// ---------------------------------------------------------------------------

/**
 * Position expressed in **physical pixels** (real hardware pixels).
 *
 * @param T Numeric type of the components (e.g. [Int], [Float], [Double]).
 * @property x Horizontal coordinate in physical pixels.
 * @property y Vertical coordinate in physical pixels.
 */
data class PhysicalPosition<T : Number>(val x: T, val y: T)

// ---------------------------------------------------------------------------
// Logical position
// ---------------------------------------------------------------------------

/**
 * Position expressed in **logical units** (device-independent pixels).
 *
 * @param T Numeric type of the components (e.g. [Int], [Float], [Double]).
 * @property x Horizontal coordinate in logical units.
 * @property y Vertical coordinate in logical units.
 */
data class LogicalPosition<T : Number>(val x: T, val y: T)

// ---------------------------------------------------------------------------
// Conversion extensions — Size
// ---------------------------------------------------------------------------

/**
 * Converts this logical size into a physical size by applying the [scaleFactor].
 *
 * Formula: `physical = logical × scaleFactor`
 *
 * @param scaleFactor DPI scale factor (e.g. `2.0` for a Retina screen).
 * @return [PhysicalSize] whose components are of type [Double].
 */
fun <T : Number> LogicalSize<T>.toPhysical(scaleFactor: Double): PhysicalSize<Double> =
    PhysicalSize(
        width = width.toDouble() * scaleFactor,
        height = height.toDouble() * scaleFactor
    )

/**
 * Converts this physical size into a logical size by dividing by the [scaleFactor].
 *
 * Formula: `logical = physical ÷ scaleFactor`
 *
 * @param scaleFactor DPI scale factor (e.g. `2.0` for a Retina screen).
 * @return [LogicalSize] whose components are of type [Double].
 */
fun <T : Number> PhysicalSize<T>.toLogical(scaleFactor: Double): LogicalSize<Double> =
    LogicalSize(
        width = width.toDouble() / scaleFactor,
        height = height.toDouble() / scaleFactor
    )

// ---------------------------------------------------------------------------
// Conversion extensions — Position
// ---------------------------------------------------------------------------

/**
 * Converts this logical position into a physical position by applying the [scaleFactor].
 *
 * Formula: `physical = logical × scaleFactor`
 *
 * @param scaleFactor DPI scale factor (e.g. `2.0` for a Retina screen).
 * @return [PhysicalPosition] whose components are of type [Double].
 */
fun <T : Number> LogicalPosition<T>.toPhysical(scaleFactor: Double): PhysicalPosition<Double> =
    PhysicalPosition(
        x = x.toDouble() * scaleFactor,
        y = y.toDouble() * scaleFactor
    )

/**
 * Converts this physical position into a logical position by dividing by the [scaleFactor].
 *
 * Formula: `logical = physical ÷ scaleFactor`
 *
 * @param scaleFactor DPI scale factor (e.g. `2.0` for a Retina screen).
 * @return [LogicalPosition] whose components are of type [Double].
 */
fun <T : Number> PhysicalPosition<T>.toLogical(scaleFactor: Double): LogicalPosition<Double> =
    LogicalPosition(
        x = x.toDouble() / scaleFactor,
        y = y.toDouble() / scaleFactor
    )
