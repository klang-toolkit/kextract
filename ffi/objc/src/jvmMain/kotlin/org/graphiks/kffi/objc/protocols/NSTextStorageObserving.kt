package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextStorageObserving
 * Inherits protocols: NSObject
 */
interface NSTextStorageObserving {
    fun processEditingForTextStorage_edited_range_changeInLength_invalidatedRange(textStorage: MemorySegment, editMask: MemorySegment, newCharRange: MemorySegment, delta: Long, invalidatedCharRange: MemorySegment): Unit
    
    fun performEditingTransactionForTextStorage_usingBlock(textStorage: MemorySegment, transaction: MemorySegment): Unit
    
    fun textStorage(): MemorySegment
    
    fun setTextStorage(textStorage: MemorySegment): Unit
    
}

