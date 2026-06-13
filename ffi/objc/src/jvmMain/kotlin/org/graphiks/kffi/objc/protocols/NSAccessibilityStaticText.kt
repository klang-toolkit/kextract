package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibilityStaticText
 * Inherits protocols: NSAccessibilityElement
 */
interface NSAccessibilityStaticText {
    fun accessibilityValue(): MemorySegment
    
    // @optional
    fun accessibilityAttributedStringForRange(range: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilityAttributedStringForRange:' not implemented")
    
    // @optional
    fun accessibilityVisibleCharacterRange(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilityVisibleCharacterRange' not implemented")
    
}

