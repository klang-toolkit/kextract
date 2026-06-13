package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextInput
 */
interface NSTextInput {
    fun insertText(string: MemorySegment): Unit
    
    fun doCommandBySelector(selector: MemorySegment): Unit
    
    fun setMarkedText_selectedRange(string: MemorySegment, selRange: MemorySegment): Unit
    
    fun unmarkText(): Unit
    
    fun hasMarkedText(): Boolean
    
    fun conversationIdentifier(): Long
    
    fun attributedSubstringFromRange(range: MemorySegment): MemorySegment
    
    fun markedRange(): MemorySegment
    
    fun selectedRange(): MemorySegment
    
    fun firstRectForCharacterRange(range: MemorySegment): MemorySegment
    
    fun characterIndexForPoint(point: MemorySegment): Long
    
    fun validAttributesForMarkedText(): MemorySegment
    
}

