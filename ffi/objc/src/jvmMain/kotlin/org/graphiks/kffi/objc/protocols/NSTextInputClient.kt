package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextInputClient
 */
interface NSTextInputClient {
    fun insertText_replacementRange(string: MemorySegment, replacementRange: MemorySegment): Unit
    
    fun doCommandBySelector(selector: MemorySegment): Unit
    
    fun setMarkedText_selectedRange_replacementRange(string: MemorySegment, selectedRange: MemorySegment, replacementRange: MemorySegment): Unit
    
    fun unmarkText(): Unit
    
    fun selectedRange(): MemorySegment
    
    fun markedRange(): MemorySegment
    
    fun hasMarkedText(): Boolean
    
    fun attributedSubstringForProposedRange_actualRange(range: MemorySegment, actualRange: MemorySegment): MemorySegment
    
    /** @return NSArray<NSAttributedStringKey> * */
    fun validAttributesForMarkedText(): MemorySegment
    
    fun firstRectForCharacterRange_actualRange(range: MemorySegment, actualRange: MemorySegment): MemorySegment
    
    fun characterIndexForPoint(point: MemorySegment): Long
    
    // @optional
    fun attributedString(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'attributedString' not implemented")
    
    // @optional
    fun fractionOfDistanceThroughGlyphForPoint(point: MemorySegment): Double =
        throw UnsupportedOperationException("Optional ObjC method 'fractionOfDistanceThroughGlyphForPoint:' not implemented")
    
    // @optional
    fun baselineDeltaForCharacterAtIndex(anIndex: Long): Double =
        throw UnsupportedOperationException("Optional ObjC method 'baselineDeltaForCharacterAtIndex:' not implemented")
    
    // @optional
    fun windowLevel(): Long =
        throw UnsupportedOperationException("Optional ObjC method 'windowLevel' not implemented")
    
    // @optional
    fun drawsVerticallyForCharacterAtIndex(charIndex: Long): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'drawsVerticallyForCharacterAtIndex:' not implemented")
    
    // @optional
    fun preferredTextAccessoryPlacement(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'preferredTextAccessoryPlacement' not implemented")
    
    // @optional
    fun insertAdaptiveImageGlyph_replacementRange(adaptiveImageGlyph: MemorySegment, replacementRange: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'insertAdaptiveImageGlyph:replacementRange:' not implemented")
    
    // @optional
    fun unionRectInVisibleSelectedRange(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'unionRectInVisibleSelectedRange' not implemented")
    
    // @optional
    fun documentVisibleRect(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'documentVisibleRect' not implemented")
    
    // @optional
    fun supportsAdaptiveImageGlyph(): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'supportsAdaptiveImageGlyph' not implemented")
    
}

