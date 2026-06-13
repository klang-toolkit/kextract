package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextContentManagerDelegate
 * Inherits protocols: NSObject
 */
interface NSTextContentManagerDelegate {
    // @optional
    fun textContentManager_textElementAtLocation(textContentManager: MemorySegment, location: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textContentManager:textElementAtLocation:' not implemented")
    
    // @optional
    fun textContentManager_shouldEnumerateTextElement_options(textContentManager: MemorySegment, textElement: MemorySegment, options: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'textContentManager:shouldEnumerateTextElement:options:' not implemented")
    
}

