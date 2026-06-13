package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSBlockOperation
 * Superclass: NSOperation
 */
open class NSBlockOperation(override val ptr: MemorySegment) : NSOperation(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSBlockOperation") }
        
        fun blockOperationWithBlock(block: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("blockOperationWithBlock:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, block) as MemorySegment
        }
        
    }
    
    open fun addExecutionBlock(block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addExecutionBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, block)
    }
    
    // @property executionBlocks
    /** @return NSArray<void (^)(void)> * */
    open fun executionBlocks(): MemorySegment {
        val sel = ObjCRuntime.sel("executionBlocks")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

