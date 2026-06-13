package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextLayoutManagerDelegate
 * Inherits protocols: NSObject
 */
interface NSTextLayoutManagerDelegate {
    // @optional
    fun textLayoutManager_textLayoutFragmentForLocation_inTextElement(textLayoutManager: MemorySegment, location: MemorySegment, textElement: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textLayoutManager:textLayoutFragmentForLocation:inTextElement:' not implemented")
    
    // @optional
    fun textLayoutManager_shouldBreakLineBeforeLocation_hyphenating(textLayoutManager: MemorySegment, location: MemorySegment, hyphenating: Boolean): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'textLayoutManager:shouldBreakLineBeforeLocation:hyphenating:' not implemented")
    
    /** @return NSDictionary<NSAttributedStringKey,id> * */
    // @optional
    fun textLayoutManager_renderingAttributesForLink_atLocation_defaultAttributes(textLayoutManager: MemorySegment, link: MemorySegment, location: MemorySegment, renderingAttributes: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textLayoutManager:renderingAttributesForLink:atLocation:defaultAttributes:' not implemented")
    
}

