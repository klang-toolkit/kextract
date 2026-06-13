package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDistantObjectRequest
 * Superclass: NSObject
 */
open class NSDistantObjectRequest(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDistantObjectRequest") }
        
    }
    
    open fun replyWithException(exception: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replyWithException:")
        ObjCRuntime.msgSend(null, ptr, sel, exception)
    }
    
    // @property invocation
    open fun invocation(): MemorySegment {
        val sel = ObjCRuntime.sel("invocation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property connection
    open fun connection(): MemorySegment {
        val sel = ObjCRuntime.sel("connection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property conversation
    open fun conversation(): MemorySegment {
        val sel = ObjCRuntime.sel("conversation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

