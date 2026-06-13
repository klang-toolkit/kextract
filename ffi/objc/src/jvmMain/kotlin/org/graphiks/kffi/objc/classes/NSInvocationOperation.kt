package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSInvocationOperation
 * Superclass: NSOperation
 */
open class NSInvocationOperation(override val ptr: MemorySegment) : NSOperation(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSInvocationOperation") }
        
    }
    
    open fun initWithTarget_selector_object(target: MemorySegment, sel: MemorySegment, arg: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTarget:selector:object:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, target, sel, arg) as MemorySegment
    }
    
    open fun initWithInvocation(inv: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithInvocation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, inv) as MemorySegment
    }
    
    // @property invocation
    open fun invocation(): MemorySegment {
        val sel = ObjCRuntime.sel("invocation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property result
    open fun result(): MemorySegment {
        val sel = ObjCRuntime.sel("result")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

