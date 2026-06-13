package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAutoreleasePool
 * Superclass: NSObject
 */
open class NSAutoreleasePool(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAutoreleasePool") }
        
        fun addObject(anObject: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("addObject:")
            ObjCRuntime.msgSend(null, _class, sel, anObject)
        }
        
    }
    
    open fun addObject(anObject: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addObject:")
        ObjCRuntime.msgSend(null, ptr, sel, anObject)
    }
    
    open fun drain(): Unit {
        val sel = ObjCRuntime.sel("drain")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _token: MemorySegment
    // ivar: _reserved3: MemorySegment
    // ivar: _reserved2: MemorySegment
    // ivar: _reserved: MemorySegment
}

