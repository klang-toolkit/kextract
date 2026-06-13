package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextContentStorageDelegate
 * Inherits protocols: NSTextContentManagerDelegate
 */
interface NSTextContentStorageDelegate : NSTextContentManagerDelegate {
    // @optional
    fun textContentStorage_textParagraphWithRange(textContentStorage: MemorySegment, range: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textContentStorage:textParagraphWithRange:' not implemented")
    
}

