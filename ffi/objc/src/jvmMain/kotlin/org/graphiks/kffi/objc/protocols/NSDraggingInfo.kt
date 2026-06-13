package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSDraggingInfo
 * Inherits protocols: NSObject
 */
interface NSDraggingInfo {
    fun slideDraggedImageTo(screenPoint: MemorySegment): Unit
    
    /** @return NSArray<NSString *> * */
    fun namesOfPromisedFilesDroppedAtDestination(dropDestination: MemorySegment): MemorySegment
    
    fun enumerateDraggingItemsWithOptions_forView_classes_searchOptions_usingBlock(enumOpts: MemorySegment, view: MemorySegment, classArray: MemorySegment, searchOptions: MemorySegment, block: MemorySegment): Unit
    
    fun resetSpringLoading(): Unit
    
    fun draggingDestinationWindow(): MemorySegment
    
    fun draggingSourceOperationMask(): MemorySegment
    
    fun draggingLocation(): MemorySegment
    
    fun draggedImageLocation(): MemorySegment
    
    fun draggedImage(): MemorySegment
    
    fun draggingPasteboard(): MemorySegment
    
    fun draggingSource(): MemorySegment
    
    fun draggingSequenceNumber(): Long
    
    fun draggingFormation(): MemorySegment
    
    fun setDraggingFormation(draggingFormation: MemorySegment): Unit
    
    fun animatesToDestination(): Boolean
    
    fun setAnimatesToDestination(animatesToDestination: Boolean): Unit
    
    fun numberOfValidItemsForDrop(): Long
    
    fun setNumberOfValidItemsForDrop(numberOfValidItemsForDrop: Long): Unit
    
    fun springLoadingHighlight(): MemorySegment
    
}

