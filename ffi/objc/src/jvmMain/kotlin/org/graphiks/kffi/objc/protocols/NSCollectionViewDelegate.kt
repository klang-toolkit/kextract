package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSCollectionViewDelegate
 * Inherits protocols: NSObject
 */
interface NSCollectionViewDelegate {
    // @optional
    fun collectionView_canDragItemsAtIndexPaths_withEvent(collectionView: MemorySegment, indexPaths: MemorySegment, event: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:canDragItemsAtIndexPaths:withEvent:' not implemented")
    
    // @optional
    fun collectionView_canDragItemsAtIndexes_withEvent(collectionView: MemorySegment, indexes: MemorySegment, event: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:canDragItemsAtIndexes:withEvent:' not implemented")
    
    // @optional
    fun collectionView_writeItemsAtIndexPaths_toPasteboard(collectionView: MemorySegment, indexPaths: MemorySegment, pasteboard: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:writeItemsAtIndexPaths:toPasteboard:' not implemented")
    
    // @optional
    fun collectionView_writeItemsAtIndexes_toPasteboard(collectionView: MemorySegment, indexes: MemorySegment, pasteboard: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:writeItemsAtIndexes:toPasteboard:' not implemented")
    
    /** @return NSArray<NSString *> * */
    // @optional
    fun collectionView_namesOfPromisedFilesDroppedAtDestination_forDraggedItemsAtIndexPaths(collectionView: MemorySegment, dropURL: MemorySegment, indexPaths: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:namesOfPromisedFilesDroppedAtDestination:forDraggedItemsAtIndexPaths:' not implemented")
    
    /** @return NSArray<NSString *> * */
    // @optional
    fun collectionView_namesOfPromisedFilesDroppedAtDestination_forDraggedItemsAtIndexes(collectionView: MemorySegment, dropURL: MemorySegment, indexes: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:namesOfPromisedFilesDroppedAtDestination:forDraggedItemsAtIndexes:' not implemented")
    
    // @optional
    fun collectionView_draggingImageForItemsAtIndexPaths_withEvent_offset(collectionView: MemorySegment, indexPaths: MemorySegment, event: MemorySegment, dragImageOffset: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:draggingImageForItemsAtIndexPaths:withEvent:offset:' not implemented")
    
    // @optional
    fun collectionView_draggingImageForItemsAtIndexes_withEvent_offset(collectionView: MemorySegment, indexes: MemorySegment, event: MemorySegment, dragImageOffset: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:draggingImageForItemsAtIndexes:withEvent:offset:' not implemented")
    
    // @optional
    fun collectionView_validateDrop_proposedIndexPath_dropOperation(collectionView: MemorySegment, draggingInfo: MemorySegment, proposedDropIndexPath: MemorySegment, proposedDropOperation: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:validateDrop:proposedIndexPath:dropOperation:' not implemented")
    
    // @optional
    fun collectionView_validateDrop_proposedIndex_dropOperation(collectionView: MemorySegment, draggingInfo: MemorySegment, proposedDropIndex: MemorySegment, proposedDropOperation: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:validateDrop:proposedIndex:dropOperation:' not implemented")
    
    // @optional
    fun collectionView_acceptDrop_indexPath_dropOperation(collectionView: MemorySegment, draggingInfo: MemorySegment, indexPath: MemorySegment, dropOperation: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:acceptDrop:indexPath:dropOperation:' not implemented")
    
    // @optional
    fun collectionView_acceptDrop_index_dropOperation(collectionView: MemorySegment, draggingInfo: MemorySegment, index: Long, dropOperation: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:acceptDrop:index:dropOperation:' not implemented")
    
    /** @return id<NSPasteboardWriting> */
    // @optional
    fun collectionView_pasteboardWriterForItemAtIndexPath(collectionView: MemorySegment, indexPath: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:pasteboardWriterForItemAtIndexPath:' not implemented")
    
    /** @return id<NSPasteboardWriting> */
    // @optional
    fun collectionView_pasteboardWriterForItemAtIndex(collectionView: MemorySegment, index: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:pasteboardWriterForItemAtIndex:' not implemented")
    
    // @optional
    fun collectionView_draggingSession_willBeginAtPoint_forItemsAtIndexPaths(collectionView: MemorySegment, session: MemorySegment, screenPoint: MemorySegment, indexPaths: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:draggingSession:willBeginAtPoint:forItemsAtIndexPaths:' not implemented")
    
    // @optional
    fun collectionView_draggingSession_willBeginAtPoint_forItemsAtIndexes(collectionView: MemorySegment, session: MemorySegment, screenPoint: MemorySegment, indexes: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:draggingSession:willBeginAtPoint:forItemsAtIndexes:' not implemented")
    
    // @optional
    fun collectionView_draggingSession_endedAtPoint_dragOperation(collectionView: MemorySegment, session: MemorySegment, screenPoint: MemorySegment, operation: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:draggingSession:endedAtPoint:dragOperation:' not implemented")
    
    // @optional
    fun collectionView_updateDraggingItemsForDrag(collectionView: MemorySegment, draggingInfo: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:updateDraggingItemsForDrag:' not implemented")
    
    /** @return NSSet<NSIndexPath *> * */
    // @optional
    fun collectionView_shouldChangeItemsAtIndexPaths_toHighlightState(collectionView: MemorySegment, indexPaths: MemorySegment, highlightState: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:shouldChangeItemsAtIndexPaths:toHighlightState:' not implemented")
    
    // @optional
    fun collectionView_didChangeItemsAtIndexPaths_toHighlightState(collectionView: MemorySegment, indexPaths: MemorySegment, highlightState: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:didChangeItemsAtIndexPaths:toHighlightState:' not implemented")
    
    /** @return NSSet<NSIndexPath *> * */
    // @optional
    fun collectionView_shouldSelectItemsAtIndexPaths(collectionView: MemorySegment, indexPaths: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:shouldSelectItemsAtIndexPaths:' not implemented")
    
    /** @return NSSet<NSIndexPath *> * */
    // @optional
    fun collectionView_shouldDeselectItemsAtIndexPaths(collectionView: MemorySegment, indexPaths: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:shouldDeselectItemsAtIndexPaths:' not implemented")
    
    // @optional
    fun collectionView_didSelectItemsAtIndexPaths(collectionView: MemorySegment, indexPaths: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:didSelectItemsAtIndexPaths:' not implemented")
    
    // @optional
    fun collectionView_didDeselectItemsAtIndexPaths(collectionView: MemorySegment, indexPaths: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:didDeselectItemsAtIndexPaths:' not implemented")
    
    // @optional
    fun collectionView_willDisplayItem_forRepresentedObjectAtIndexPath(collectionView: MemorySegment, item: MemorySegment, indexPath: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:willDisplayItem:forRepresentedObjectAtIndexPath:' not implemented")
    
    // @optional
    fun collectionView_willDisplaySupplementaryView_forElementKind_atIndexPath(collectionView: MemorySegment, view: MemorySegment, elementKind: MemorySegment, indexPath: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:willDisplaySupplementaryView:forElementKind:atIndexPath:' not implemented")
    
    // @optional
    fun collectionView_didEndDisplayingItem_forRepresentedObjectAtIndexPath(collectionView: MemorySegment, item: MemorySegment, indexPath: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:didEndDisplayingItem:forRepresentedObjectAtIndexPath:' not implemented")
    
    // @optional
    fun collectionView_didEndDisplayingSupplementaryView_forElementOfKind_atIndexPath(collectionView: MemorySegment, view: MemorySegment, elementKind: MemorySegment, indexPath: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:didEndDisplayingSupplementaryView:forElementOfKind:atIndexPath:' not implemented")
    
    // @optional
    fun collectionView_transitionLayoutForOldLayout_newLayout(collectionView: MemorySegment, fromLayout: MemorySegment, toLayout: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:transitionLayoutForOldLayout:newLayout:' not implemented")
    
}

