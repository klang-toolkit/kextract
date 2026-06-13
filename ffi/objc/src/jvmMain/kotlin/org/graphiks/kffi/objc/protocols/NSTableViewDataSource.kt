package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTableViewDataSource
 * Inherits protocols: NSObject
 */
interface NSTableViewDataSource {
    // @optional
    fun numberOfRowsInTableView(tableView: MemorySegment): Long =
        throw UnsupportedOperationException("Optional ObjC method 'numberOfRowsInTableView:' not implemented")
    
    // @optional
    fun tableView_objectValueForTableColumn_row(tableView: MemorySegment, tableColumn: MemorySegment, row: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tableView:objectValueForTableColumn:row:' not implemented")
    
    // @optional
    fun tableView_setObjectValue_forTableColumn_row(tableView: MemorySegment, `object`: MemorySegment, tableColumn: MemorySegment, row: Long): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'tableView:setObjectValue:forTableColumn:row:' not implemented")
    
    // @optional
    fun tableView_sortDescriptorsDidChange(tableView: MemorySegment, oldDescriptors: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'tableView:sortDescriptorsDidChange:' not implemented")
    
    /** @return id<NSPasteboardWriting> */
    // @optional
    fun tableView_pasteboardWriterForRow(tableView: MemorySegment, row: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tableView:pasteboardWriterForRow:' not implemented")
    
    // @optional
    fun tableView_draggingSession_willBeginAtPoint_forRowIndexes(tableView: MemorySegment, session: MemorySegment, screenPoint: MemorySegment, rowIndexes: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'tableView:draggingSession:willBeginAtPoint:forRowIndexes:' not implemented")
    
    // @optional
    fun tableView_draggingSession_endedAtPoint_operation(tableView: MemorySegment, session: MemorySegment, screenPoint: MemorySegment, operation: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'tableView:draggingSession:endedAtPoint:operation:' not implemented")
    
    // @optional
    fun tableView_updateDraggingItemsForDrag(tableView: MemorySegment, draggingInfo: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'tableView:updateDraggingItemsForDrag:' not implemented")
    
    // @optional
    fun tableView_writeRowsWithIndexes_toPasteboard(tableView: MemorySegment, rowIndexes: MemorySegment, pboard: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'tableView:writeRowsWithIndexes:toPasteboard:' not implemented")
    
    // @optional
    fun tableView_validateDrop_proposedRow_proposedDropOperation(tableView: MemorySegment, info: MemorySegment, row: Long, dropOperation: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tableView:validateDrop:proposedRow:proposedDropOperation:' not implemented")
    
    // @optional
    fun tableView_acceptDrop_row_dropOperation(tableView: MemorySegment, info: MemorySegment, row: Long, dropOperation: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'tableView:acceptDrop:row:dropOperation:' not implemented")
    
    /** @return NSArray<NSString *> * */
    // @optional
    fun tableView_namesOfPromisedFilesDroppedAtDestination_forDraggedRowsWithIndexes(tableView: MemorySegment, dropDestination: MemorySegment, indexSet: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'tableView:namesOfPromisedFilesDroppedAtDestination:forDraggedRowsWithIndexes:' not implemented")
    
}

