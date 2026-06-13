package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSDatePickerCellDelegate
 * Inherits protocols: NSObject
 */
interface NSDatePickerCellDelegate {
    // @optional
    fun datePickerCell_validateProposedDateValue_timeInterval(datePickerCell: MemorySegment, proposedDateValue: MemorySegment, proposedTimeInterval: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'datePickerCell:validateProposedDateValue:timeInterval:' not implemented")
    
}

