/**
 * R3 — Cursor, theme and appearance types.
 *
 * Scope: pure Kotlin types, no native dependency.
 */
package org.graphiks.kadre.core

/**
 * Cursor shape to display over the window.
 *
 * The mapping to native cursors is backend-specific.
 * Backends that do not support a given shape fall back to [Default].
 */
enum class CursorIcon {
    /** Standard OS default pointer. */
    Default,

    /** Pointer / hand — typically used on links and buttons. */
    Pointer,

    /** Text I-beam cursor. */
    Text,

    /** Crosshair cursor — used for precise selection. */
    Crosshair,

    /** Four-directional move cursor. */
    Move,

    /** Resize north (up) cursor. */
    ResizeNorth,

    /** Resize south (down) cursor. */
    ResizeSouth,

    /** Resize east (right) cursor. */
    ResizeEast,

    /** Resize west (left) cursor. */
    ResizeWest,

    /** Resize north-east cursor. */
    ResizeNorthEast,

    /** Resize north-west cursor. */
    ResizeNorthWest,

    /** Resize south-east cursor. */
    ResizeSouthEast,

    /** Resize south-west cursor. */
    ResizeSouthWest,

    /** Not-allowed / forbidden cursor. */
    NotAllowed,

    /** Open hand / grab cursor. */
    Grab,

    /** Closed hand / grabbing cursor. */
    Grabbing,

    /** Wait / busy cursor. */
    Wait,

    /** Progress / background busy cursor. */
    Progress,

    /** East-west (horizontal) resize cursor (alias for ColResize). */
    EwResize,

    /** North-south (vertical) resize cursor (alias for RowResize). */
    NsResize,

    /** North-east / south-west resize cursor. */
    NeswResize,

    /** North-west / south-east resize cursor. */
    NwseResize,

    /** Column resize cursor. */
    ColResize,

    /** Row resize cursor. */
    RowResize,
}

/**
 * Pointer grab mode.
 *
 * - [None]     — cursor moves freely.
 * - [Confined] — cursor is constrained inside the window bounds.
 * - [Locked]   — cursor is hidden and positioned deltas are raw (FPS-style).
 *
 * Mobile and web backends document their limitations in their respective
 * implementations; this method never throws.
 */
enum class CursorGrabMode {
    /** Cursor moves freely (default). */
    None,

    /** Cursor is confined to the window boundaries. */
    Confined,

    /** Cursor position is locked; raw deltas are provided instead. */
    Locked,
}

/**
 * System UI theme preference.
 */
enum class Theme {
    /** Light mode. */
    Light,

    /** Dark mode. */
    Dark,
}

/**
 * Z-ordering level of the window relative to other windows.
 */
enum class WindowLevel {
    /** Window appears below normal windows. */
    AlwaysOnBottom,

    /** Normal Z-order (default). */
    Normal,

    /** Window appears above all other windows. */
    AlwaysOnTop,
}

/**
 * Window application icon (RGBA pixel data).
 *
 * @property rgba   Raw RGBA bytes (4 bytes per pixel, row-major, top-left origin).
 * @property width  Width in pixels.
 * @property height Height in pixels.
 */
data class Icon(
    val rgba: ByteArray,
    val width: Int,
    val height: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Icon) return false
        return width == other.width && height == other.height && rgba.contentEquals(other.rgba)
    }

    override fun hashCode(): Int {
        var result = rgba.contentHashCode()
        result = 31 * result + width
        result = 31 * result + height
        return result
    }
}
