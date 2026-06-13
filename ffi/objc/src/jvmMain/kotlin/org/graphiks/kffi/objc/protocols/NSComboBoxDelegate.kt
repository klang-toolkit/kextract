package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSComboBoxDelegate
 * Inherits protocols: NSTextFieldDelegate
 */
interface NSComboBoxDelegate : NSTextFieldDelegate {
    // @optional
    fun comboBoxWillPopUp(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'comboBoxWillPopUp:' not implemented")
    
    // @optional
    fun comboBoxWillDismiss(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'comboBoxWillDismiss:' not implemented")
    
    // @optional
    fun comboBoxSelectionDidChange(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'comboBoxSelectionDidChange:' not implemented")
    
    // @optional
    fun comboBoxSelectionIsChanging(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'comboBoxSelectionIsChanging:' not implemented")
    
}

