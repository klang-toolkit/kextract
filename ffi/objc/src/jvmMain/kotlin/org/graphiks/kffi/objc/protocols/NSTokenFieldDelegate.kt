package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTokenFieldDelegate
 * Inherits protocols: NSTextFieldDelegate
 */
interface NSTokenFieldDelegate : NSTextFieldDelegate {
    // @optional
    fun tokenField_completionsForSubstring_indexOfToken_indexOfSelectedItem(tokenField: MemorySegment, substring: MemorySegment, tokenIndex: Long, selectedIndex: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenField:completionsForSubstring:indexOfToken:indexOfSelectedItem:' not implemented")
    
    // @optional
    fun tokenField_shouldAddObjects_atIndex(tokenField: MemorySegment, tokens: MemorySegment, index: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenField:shouldAddObjects:atIndex:' not implemented")
    
    // @optional
    fun tokenField_displayStringForRepresentedObject(tokenField: MemorySegment, representedObject: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenField:displayStringForRepresentedObject:' not implemented")
    
    // @optional
    fun tokenField_editingStringForRepresentedObject(tokenField: MemorySegment, representedObject: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenField:editingStringForRepresentedObject:' not implemented")
    
    // @optional
    fun tokenField_representedObjectForEditingString(tokenField: MemorySegment, editingString: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenField:representedObjectForEditingString:' not implemented")
    
    // @optional
    fun tokenField_writeRepresentedObjects_toPasteboard(tokenField: MemorySegment, objects: MemorySegment, pboard: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'tokenField:writeRepresentedObjects:toPasteboard:' not implemented")
    
    // @optional
    fun tokenField_readFromPasteboard(tokenField: MemorySegment, pboard: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenField:readFromPasteboard:' not implemented")
    
    // @optional
    fun tokenField_menuForRepresentedObject(tokenField: MemorySegment, representedObject: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenField:menuForRepresentedObject:' not implemented")
    
    // @optional
    fun tokenField_hasMenuForRepresentedObject(tokenField: MemorySegment, representedObject: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'tokenField:hasMenuForRepresentedObject:' not implemented")
    
    // @optional
    fun tokenField_styleForRepresentedObject(tokenField: MemorySegment, representedObject: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenField:styleForRepresentedObject:' not implemented")
    
}

