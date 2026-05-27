package org.graphiks.kextract

import java.nio.file.Path

/**
 * Represents a source-code position (file, line, column).
 * [NO_POSITION] is the sentinel for unknown / unavailable positions.
 */
data class Position(val path: Path?, val line: Int, val col: Int) {
    override fun toString(): String =
        if (path == null) "NO_POSITION" else "$path:$line:$col"

    companion object {
        val NO_POSITION = Position(null, 0, 0)
    }
}
