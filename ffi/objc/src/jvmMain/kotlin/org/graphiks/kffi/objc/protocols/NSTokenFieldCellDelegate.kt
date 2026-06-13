package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTokenFieldCellDelegate
 * Inherits protocols: NSObject
 */
interface NSTokenFieldCellDelegate {
    // @optional
    fun tokenFieldCell_completionsForSubstring_indexOfToken_indexOfSelectedItem(tokenFieldCell: MemorySegment, substring: MemorySegment, tokenIndex: Long, selectedIndex: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenFieldCell:completionsForSubstring:indexOfToken:indexOfSelectedItem:' not implemented")
    
    // @optional
    fun tokenFieldCell_shouldAddObjects_atIndex(tokenFieldCell: MemorySegment, tokens: MemorySegment, index: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenFieldCell:shouldAddObjects:atIndex:' not implemented")
    
    // @optional
    fun tokenFieldCell_displayStringForRepresentedObject(tokenFieldCell: MemorySegment, representedObject: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenFieldCell:displayStringForRepresentedObject:' not implemented")
    
    // @optional
    fun tokenFieldCell_editingStringForRepresentedObject(tokenFieldCell: MemorySegment, representedObject: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenFieldCell:editingStringForRepresentedObject:' not implemented")
    
    // @optional
    fun tokenFieldCell_representedObjectForEditingString(tokenFieldCell: MemorySegment, editingString: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenFieldCell:representedObjectForEditingString:' not implemented")
    
    // @optional
    fun tokenFieldCell_writeRepresentedObjects_toPasteboard(tokenFieldCell: MemorySegment, objects: MemorySegment, pboard: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'tokenFieldCell:writeRepresentedObjects:toPasteboard:' not implemented")
    
    // @optional
    fun tokenFieldCell_readFromPasteboard(tokenFieldCell: MemorySegment, pboard: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenFieldCell:readFromPasteboard:' not implemented")
    
    // @optional
    fun tokenFieldCell_menuForRepresentedObject(tokenFieldCell: MemorySegment, representedObject: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenFieldCell:menuForRepresentedObject:' not implemented")
    
    // @optional
    fun tokenFieldCell_hasMenuForRepresentedObject(tokenFieldCell: MemorySegment, representedObject: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'tokenFieldCell:hasMenuForRepresentedObject:' not implemented")
    
    // @optional
    fun tokenFieldCell_styleForRepresentedObject(tokenFieldCell: MemorySegment, representedObject: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tokenFieldCell:styleForRepresentedObject:' not implemented")
    
}

