package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSGlyphStorage
 */
interface NSGlyphStorage {
    fun insertGlyphs_length_forStartingGlyphAtIndex_characterIndex(glyphs: MemorySegment, length: Long, glyphIndex: Long, charIndex: Long): Unit
    
    fun setIntAttribute_value_forGlyphAtIndex(attributeTag: Long, `val`: Long, glyphIndex: Long): Unit
    
    fun attributedString(): MemorySegment
    
    fun layoutOptions(): Long
    
}

