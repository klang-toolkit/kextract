package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSOutlineViewDataSource
 * Inherits protocols: NSObject
 */
interface NSOutlineViewDataSource {
    // @optional
    fun outlineView_numberOfChildrenOfItem(outlineView: MemorySegment, item: MemorySegment): Long =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:numberOfChildrenOfItem:' not implemented")
    
    // @optional
    fun outlineView_child_ofItem(outlineView: MemorySegment, index: Long, item: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:child:ofItem:' not implemented")
    
    // @optional
    fun outlineView_isItemExpandable(outlineView: MemorySegment, item: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:isItemExpandable:' not implemented")
    
    // @optional
    fun outlineView_objectValueForTableColumn_byItem(outlineView: MemorySegment, tableColumn: MemorySegment, item: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:objectValueForTableColumn:byItem:' not implemented")
    
    // @optional
    fun outlineView_setObjectValue_forTableColumn_byItem(outlineView: MemorySegment, `object`: MemorySegment, tableColumn: MemorySegment, item: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:setObjectValue:forTableColumn:byItem:' not implemented")
    
    // @optional
    fun outlineView_itemForPersistentObject(outlineView: MemorySegment, `object`: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:itemForPersistentObject:' not implemented")
    
    // @optional
    fun outlineView_persistentObjectForItem(outlineView: MemorySegment, item: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:persistentObjectForItem:' not implemented")
    
    // @optional
    fun outlineView_sortDescriptorsDidChange(outlineView: MemorySegment, oldDescriptors: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:sortDescriptorsDidChange:' not implemented")
    
    /** @return id<NSPasteboardWriting> */
    // @optional
    fun outlineView_pasteboardWriterForItem(outlineView: MemorySegment, item: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:pasteboardWriterForItem:' not implemented")
    
    // @optional
    fun outlineView_draggingSession_willBeginAtPoint_forItems(outlineView: MemorySegment, session: MemorySegment, screenPoint: MemorySegment, draggedItems: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:draggingSession:willBeginAtPoint:forItems:' not implemented")
    
    // @optional
    fun outlineView_draggingSession_endedAtPoint_operation(outlineView: MemorySegment, session: MemorySegment, screenPoint: MemorySegment, operation: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:draggingSession:endedAtPoint:operation:' not implemented")
    
    // @optional
    fun outlineView_writeItems_toPasteboard(outlineView: MemorySegment, items: MemorySegment, pasteboard: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:writeItems:toPasteboard:' not implemented")
    
    // @optional
    fun outlineView_updateDraggingItemsForDrag(outlineView: MemorySegment, draggingInfo: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:updateDraggingItemsForDrag:' not implemented")
    
    // @optional
    fun outlineView_validateDrop_proposedItem_proposedChildIndex(outlineView: MemorySegment, info: MemorySegment, item: MemorySegment, index: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:validateDrop:proposedItem:proposedChildIndex:' not implemented")
    
    // @optional
    fun outlineView_acceptDrop_item_childIndex(outlineView: MemorySegment, info: MemorySegment, item: MemorySegment, index: Long): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:acceptDrop:item:childIndex:' not implemented")
    
    /** @return NSArray<NSString *> * */
    // @optional
    fun outlineView_namesOfPromisedFilesDroppedAtDestination_forDraggedItems(outlineView: MemorySegment, dropDestination: MemorySegment, items: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'outlineView:namesOfPromisedFilesDroppedAtDestination:forDraggedItems:' not implemented")
    
}

