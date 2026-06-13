package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSComboBoxCellDataSource
 * Inherits protocols: NSObject
 */
interface NSComboBoxCellDataSource {
    // @optional
    fun numberOfItemsInComboBoxCell(comboBoxCell: MemorySegment): Long =
        throw UnsupportedOperationException("Optional ObjC method 'numberOfItemsInComboBoxCell:' not implemented")
    
    // @optional
    fun comboBoxCell_objectValueForItemAtIndex(comboBoxCell: MemorySegment, index: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'comboBoxCell:objectValueForItemAtIndex:' not implemented")
    
    // @optional
    fun comboBoxCell_indexOfItemWithStringValue(comboBoxCell: MemorySegment, string: MemorySegment): Long =
        throw UnsupportedOperationException("Optional ObjC method 'comboBoxCell:indexOfItemWithStringValue:' not implemented")
    
    // @optional
    fun comboBoxCell_completedString(comboBoxCell: MemorySegment, uncompletedString: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'comboBoxCell:completedString:' not implemented")
    
}

