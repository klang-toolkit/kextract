package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSOrderedCollectionDifference
 * Superclass: NSObject
 * Protocols: NSFastEnumeration
 */
open class NSOrderedCollectionDifference(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSOrderedCollectionDifference") }
        
    }
    
    open fun initWithChanges(changes: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithChanges:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, changes) as MemorySegment
    }
    
    open fun initWithInsertIndexes_insertedObjects_removeIndexes_removedObjects_additionalChanges(inserts: MemorySegment, insertedObjects: MemorySegment, removes: MemorySegment, removedObjects: MemorySegment, changes: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithInsertIndexes:insertedObjects:removeIndexes:removedObjects:additionalChanges:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, inserts, insertedObjects, removes, removedObjects, changes) as MemorySegment
    }
    
    open fun initWithInsertIndexes_insertedObjects_removeIndexes_removedObjects(inserts: MemorySegment, insertedObjects: MemorySegment, removes: MemorySegment, removedObjects: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithInsertIndexes:insertedObjects:removeIndexes:removedObjects:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, inserts, insertedObjects, removes, removedObjects) as MemorySegment
    }
    
    /** @return NSOrderedCollectionDifference<id> * */
    open fun differenceByTransformingChangesWithBlock(block: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("differenceByTransformingChangesWithBlock:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, block) as MemorySegment
    }
    
    open fun inverseDifference(): MemorySegment {
        val sel = ObjCRuntime.sel("inverseDifference")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property insertions
    /** @return NSArray<NSOrderedCollectionChange<ObjectType> *> * */
    open fun insertions(): MemorySegment {
        val sel = ObjCRuntime.sel("insertions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property removals
    /** @return NSArray<NSOrderedCollectionChange<ObjectType> *> * */
    open fun removals(): MemorySegment {
        val sel = ObjCRuntime.sel("removals")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property hasChanges
    open fun hasChanges(): Boolean {
        val sel = ObjCRuntime.sel("hasChanges")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

