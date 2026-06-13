package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextFieldDelegate
 * Inherits protocols: NSControlTextEditingDelegate
 */
interface NSTextFieldDelegate : NSControlTextEditingDelegate {
    // @optional
    fun textField_textView_candidatesForSelectedRange(textField: MemorySegment, textView: MemorySegment, selectedRange: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textField:textView:candidatesForSelectedRange:' not implemented")
    
    /** @return NSArray<NSTextCheckingResult *> * */
    // @optional
    fun textField_textView_candidates_forSelectedRange(textField: MemorySegment, textView: MemorySegment, candidates: MemorySegment, selectedRange: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textField:textView:candidates:forSelectedRange:' not implemented")
    
    // @optional
    fun textField_textView_shouldSelectCandidateAtIndex(textField: MemorySegment, textView: MemorySegment, index: Long): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'textField:textView:shouldSelectCandidateAtIndex:' not implemented")
    
}

