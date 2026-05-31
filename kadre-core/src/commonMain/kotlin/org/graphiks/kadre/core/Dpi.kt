/**
 * Types DPI pour la gestion des tailles et positions en coordonnées physiques et logiques.
 *
 * ## Coordonnées physiques vs logiques
 *
 * Les **coordonnées physiques** (Physical) représentent des pixels réels sur l'écran,
 * tels qu'ils sont adressés par le matériel. Elles dépendent de la résolution native
 * du moniteur et tiennent compte du facteur d'échelle (DPI scaling).
 *
 * Les **coordonnées logiques** (Logical) représentent des unités indépendantes de la
 * densité d'affichage (device-independent pixels). Elles correspondent à ce que le
 * développeur manipule conceptuellement, sans se soucier de la densité physique de
 * l'écran. Le système d'exploitation applique un facteur d'échelle (`scaleFactor`)
 * pour convertir entre les deux espaces.
 *
 * ### Exemple
 * Sur un écran Retina avec `scaleFactor = 2.0` :
 * - une fenêtre de taille logique 800 × 600 occupe physiquement 1600 × 1200 pixels.
 *
 * ## Conversions
 *
 * Utilisez les fonctions d'extension `toPhysical` et `toLogical` pour passer d'un
 * espace de coordonnées à l'autre :
 *
 * ```kotlin
 * val logique = LogicalSize(800, 600)
 * val physique = logique.toPhysical(scaleFactor = 2.0) // PhysicalSize(1600.0, 1200.0)
 * val retour  = physique.toLogical(scaleFactor = 2.0)  // LogicalSize(800.0, 600.0)
 * ```
 *
 * @since 0.1.0
 */
package org.graphiks.kadre.core

// ---------------------------------------------------------------------------
// Taille physique
// ---------------------------------------------------------------------------

/**
 * Taille exprimée en **pixels physiques** (pixels réels du matériel).
 *
 * @param T Type numérique des composantes (ex. [Int], [Float], [Double]).
 * @property width Largeur en pixels physiques.
 * @property height Hauteur en pixels physiques.
 */
data class PhysicalSize<T : Number>(val width: T, val height: T)

// ---------------------------------------------------------------------------
// Taille logique
// ---------------------------------------------------------------------------

/**
 * Taille exprimée en **unités logiques** (device-independent pixels).
 *
 * @param T Type numérique des composantes (ex. [Int], [Float], [Double]).
 * @property width Largeur en unités logiques.
 * @property height Hauteur en unités logiques.
 */
data class LogicalSize<T : Number>(val width: T, val height: T)

// ---------------------------------------------------------------------------
// Position physique
// ---------------------------------------------------------------------------

/**
 * Position exprimée en **pixels physiques** (pixels réels du matériel).
 *
 * @param T Type numérique des composantes (ex. [Int], [Float], [Double]).
 * @property x Coordonnée horizontale en pixels physiques.
 * @property y Coordonnée verticale en pixels physiques.
 */
data class PhysicalPosition<T : Number>(val x: T, val y: T)

// ---------------------------------------------------------------------------
// Position logique
// ---------------------------------------------------------------------------

/**
 * Position exprimée en **unités logiques** (device-independent pixels).
 *
 * @param T Type numérique des composantes (ex. [Int], [Float], [Double]).
 * @property x Coordonnée horizontale en unités logiques.
 * @property y Coordonnée verticale en unités logiques.
 */
data class LogicalPosition<T : Number>(val x: T, val y: T)

// ---------------------------------------------------------------------------
// Extensions de conversion — Taille
// ---------------------------------------------------------------------------

/**
 * Convertit cette taille logique en taille physique en appliquant le [scaleFactor].
 *
 * Formule : `physique = logique × scaleFactor`
 *
 * @param scaleFactor Facteur d'échelle DPI (ex. `2.0` pour un écran Retina).
 * @return [PhysicalSize] dont les composantes sont de type [Double].
 */
fun <T : Number> LogicalSize<T>.toPhysical(scaleFactor: Double): PhysicalSize<Double> =
    PhysicalSize(
        width = width.toDouble() * scaleFactor,
        height = height.toDouble() * scaleFactor
    )

/**
 * Convertit cette taille physique en taille logique en divisant par le [scaleFactor].
 *
 * Formule : `logique = physique ÷ scaleFactor`
 *
 * @param scaleFactor Facteur d'échelle DPI (ex. `2.0` pour un écran Retina).
 * @return [LogicalSize] dont les composantes sont de type [Double].
 */
fun <T : Number> PhysicalSize<T>.toLogical(scaleFactor: Double): LogicalSize<Double> =
    LogicalSize(
        width = width.toDouble() / scaleFactor,
        height = height.toDouble() / scaleFactor
    )

// ---------------------------------------------------------------------------
// Extensions de conversion — Position
// ---------------------------------------------------------------------------

/**
 * Convertit cette position logique en position physique en appliquant le [scaleFactor].
 *
 * Formule : `physique = logique × scaleFactor`
 *
 * @param scaleFactor Facteur d'échelle DPI (ex. `2.0` pour un écran Retina).
 * @return [PhysicalPosition] dont les composantes sont de type [Double].
 */
fun <T : Number> LogicalPosition<T>.toPhysical(scaleFactor: Double): PhysicalPosition<Double> =
    PhysicalPosition(
        x = x.toDouble() * scaleFactor,
        y = y.toDouble() * scaleFactor
    )

/**
 * Convertit cette position physique en position logique en divisant par le [scaleFactor].
 *
 * Formule : `logique = physique ÷ scaleFactor`
 *
 * @param scaleFactor Facteur d'échelle DPI (ex. `2.0` pour un écran Retina).
 * @return [LogicalPosition] dont les composantes sont de type [Double].
 */
fun <T : Number> PhysicalPosition<T>.toLogical(scaleFactor: Double): LogicalPosition<Double> =
    LogicalPosition(
        x = x.toDouble() / scaleFactor,
        y = y.toDouble() / scaleFactor
    )
