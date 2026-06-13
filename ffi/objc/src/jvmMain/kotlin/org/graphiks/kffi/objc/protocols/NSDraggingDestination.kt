package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSDraggingDestination
 * Inherits protocols: NSObject
 */
interface NSDraggingDestination {
    // @optional
    fun draggingEntered(sender: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'draggingEntered:' not implemented")
    
    // @optional
    fun draggingUpdated(sender: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'draggingUpdated:' not implemented")
    
    // @optional
    fun draggingExited(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'draggingExited:' not implemented")
    
    // @optional
    fun prepareForDragOperation(sender: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'prepareForDragOperation:' not implemented")
    
    // @optional
    fun performDragOperation(sender: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'performDragOperation:' not implemented")
    
    // @optional
    fun concludeDragOperation(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'concludeDragOperation:' not implemented")
    
    // @optional
    fun draggingEnded(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'draggingEnded:' not implemented")
    
    // @optional
    fun wantsPeriodicDraggingUpdates(): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'wantsPeriodicDraggingUpdates' not implemented")
    
    // @optional
    fun updateDraggingItemsForDrag(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'updateDraggingItemsForDrag:' not implemented")
    
}

