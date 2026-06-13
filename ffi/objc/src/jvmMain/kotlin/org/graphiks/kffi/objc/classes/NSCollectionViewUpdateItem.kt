package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionViewUpdateItem
 * Superclass: NSObject
 */
open class NSCollectionViewUpdateItem(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionViewUpdateItem") }
        
    }
    
    // @property indexPathBeforeUpdate
    open fun indexPathBeforeUpdate(): MemorySegment {
        val sel = ObjCRuntime.sel("indexPathBeforeUpdate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property indexPathAfterUpdate
    open fun indexPathAfterUpdate(): MemorySegment {
        val sel = ObjCRuntime.sel("indexPathAfterUpdate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property updateAction
    open fun updateAction(): MemorySegment {
        val sel = ObjCRuntime.sel("updateAction")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

