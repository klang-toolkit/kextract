package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibilityTable
 * Inherits protocols: NSAccessibilityGroup
 */
interface NSAccessibilityTable : NSAccessibilityGroup {
    fun accessibilityLabel(): MemorySegment
    
    /** @return NSArray<id<NSAccessibilityRow>> * */
    fun accessibilityRows(): MemorySegment
    
    /** @return NSArray<id<NSAccessibilityRow>> * */
    // @optional
    fun accessibilitySelectedRows(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilitySelectedRows' not implemented")
    
    // @optional
    fun setAccessibilitySelectedRows(selectedRows: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'setAccessibilitySelectedRows:' not implemented")
    
    /** @return NSArray<id<NSAccessibilityRow>> * */
    // @optional
    fun accessibilityVisibleRows(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilityVisibleRows' not implemented")
    
    // @optional
    fun accessibilityColumns(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilityColumns' not implemented")
    
    // @optional
    fun accessibilityVisibleColumns(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilityVisibleColumns' not implemented")
    
    // @optional
    fun accessibilitySelectedColumns(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilitySelectedColumns' not implemented")
    
    // @optional
    fun accessibilityHeaderGroup(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilityHeaderGroup' not implemented")
    
    // @optional
    fun accessibilitySelectedCells(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilitySelectedCells' not implemented")
    
    // @optional
    fun accessibilityVisibleCells(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilityVisibleCells' not implemented")
    
    // @optional
    fun accessibilityRowHeaderUIElements(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilityRowHeaderUIElements' not implemented")
    
    // @optional
    fun accessibilityColumnHeaderUIElements(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilityColumnHeaderUIElements' not implemented")
    
}

