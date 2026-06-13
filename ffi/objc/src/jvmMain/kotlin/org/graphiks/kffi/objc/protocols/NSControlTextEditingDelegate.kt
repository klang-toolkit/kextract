package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSControlTextEditingDelegate
 * Inherits protocols: NSObject
 */
interface NSControlTextEditingDelegate {
    // @optional
    fun controlTextDidBeginEditing(obj: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'controlTextDidBeginEditing:' not implemented")
    
    // @optional
    fun controlTextDidEndEditing(obj: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'controlTextDidEndEditing:' not implemented")
    
    // @optional
    fun controlTextDidChange(obj: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'controlTextDidChange:' not implemented")
    
    // @optional
    fun control_textShouldBeginEditing(control: MemorySegment, fieldEditor: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'control:textShouldBeginEditing:' not implemented")
    
    // @optional
    fun control_textShouldEndEditing(control: MemorySegment, fieldEditor: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'control:textShouldEndEditing:' not implemented")
    
    // @optional
    fun control_didFailToFormatString_errorDescription(control: MemorySegment, string: MemorySegment, error: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'control:didFailToFormatString:errorDescription:' not implemented")
    
    // @optional
    fun control_didFailToValidatePartialString_errorDescription(control: MemorySegment, string: MemorySegment, error: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'control:didFailToValidatePartialString:errorDescription:' not implemented")
    
    // @optional
    fun control_isValidObject(control: MemorySegment, obj: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'control:isValidObject:' not implemented")
    
    // @optional
    fun control_textView_doCommandBySelector(control: MemorySegment, textView: MemorySegment, commandSelector: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'control:textView:doCommandBySelector:' not implemented")
    
    /** @return NSArray<NSString *> * */
    // @optional
    fun control_textView_completions_forPartialWordRange_indexOfSelectedItem(control: MemorySegment, textView: MemorySegment, words: MemorySegment, charRange: MemorySegment, index: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'control:textView:completions:forPartialWordRange:indexOfSelectedItem:' not implemented")
    
}

