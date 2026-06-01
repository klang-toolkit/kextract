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

// ── R5-CustomCursor ───────────────────────────────────────────────────────────

/**
 * RGBA pixel buffer for a custom cursor image.
 *
 * The buffer must contain exactly `width * height * 4` bytes, in row-major order,
 * starting from the top-left corner.
 *
 * @property rgba     Raw RGBA bytes (4 bytes per pixel).
 * @property width    Image width in pixels.
 * @property height   Image height in pixels.
 * @property hotspotX Horizontal hot-spot offset from the left edge (default 0).
 * @property hotspotY Vertical hot-spot offset from the top edge (default 0).
 */
data class CursorImage(
    val rgba: ByteArray,
    val width: Int,
    val height: Int,
    val hotspotX: Int = 0,
    val hotspotY: Int = 0,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CursorImage) return false
        return width == other.width && height == other.height &&
            hotspotX == other.hotspotX && hotspotY == other.hotspotY &&
            rgba.contentEquals(other.rgba)
    }

    override fun hashCode(): Int {
        var result = rgba.contentHashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + hotspotX
        result = 31 * result + hotspotY
        return result
    }
}

/**
 * Opaque handle to a platform-allocated custom cursor.
 *
 * Obtained via [ActiveEventLoop.createCustomCursor]; passed to [Window.setCustomCursor].
 * The `id` is an opaque platform-specific identifier (e.g. native pointer cast to Long).
 *
 * @property id Platform-specific cursor identifier.
 */
class CustomCursor internal constructor(internal val id: Long)

// ── R5-MiscWindow ─────────────────────────────────────────────────────────────

/**
 * Type of user-attention request on the taskbar or dock icon.
 *
 * Passed to [Window.requestUserAttention].
 */
enum class UserAttentionType {
    /**
     * Critical attention — the icon bounces continuously (macOS) or flashes rapidly (Win32).
     * Used for urgent alerts that require an immediate response.
     */
    Critical,

    /**
     * Informational attention — the icon bounces once (macOS) or flashes once (Win32).
     * Used for non-blocking notifications.
     */
    Informational,
}

/**
 * Direction of a programmatic window-resize drag.
 *
 * Passed to [Window.dragResizeWindow] to identify which window border
 * should be dragged.
 */
enum class ResizeDirection {
    /** Resize from the east (right) edge. */
    East,

    /** Resize from the north (top) edge. */
    North,

    /** Resize from the north-east corner. */
    NorthEast,

    /** Resize from the north-west corner. */
    NorthWest,

    /** Resize from the south (bottom) edge. */
    South,

    /** Resize from the south-east corner. */
    SouthEast,

    /** Resize from the south-west corner. */
    SouthWest,

    /** Resize from the west (left) edge. */
    West,
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
