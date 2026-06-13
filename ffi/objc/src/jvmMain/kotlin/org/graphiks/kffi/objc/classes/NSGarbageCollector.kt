package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSGarbageCollector
 * Superclass: NSObject
 */
open class NSGarbageCollector(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSGarbageCollector") }
        
        fun defaultCollector(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultCollector")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun isCollecting(): Boolean {
        val sel = ObjCRuntime.sel("isCollecting")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun disable(): Unit {
        val sel = ObjCRuntime.sel("disable")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun enable(): Unit {
        val sel = ObjCRuntime.sel("enable")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun isEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun collectIfNeeded(): Unit {
        val sel = ObjCRuntime.sel("collectIfNeeded")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun collectExhaustively(): Unit {
        val sel = ObjCRuntime.sel("collectExhaustively")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun disableCollectorForPointer(ptr: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("disableCollectorForPointer:")
        ObjCRuntime.msgSend(null, ptr, sel, ptr)
    }
    
    open fun enableCollectorForPointer(ptr: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enableCollectorForPointer:")
        ObjCRuntime.msgSend(null, ptr, sel, ptr)
    }
    
    open fun zone(): MemorySegment {
        val sel = ObjCRuntime.sel("zone")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

