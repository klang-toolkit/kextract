package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSStatusBar
 * Superclass: NSObject
 */
open class NSStatusBar(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSStatusBar") }
        
        fun systemStatusBar(): MemorySegment {
            val sel = ObjCRuntime.sel("systemStatusBar")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun statusItemWithLength(length: Double): MemorySegment {
        val sel = ObjCRuntime.sel("statusItemWithLength:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, length) as MemorySegment
    }
    
    open fun removeStatusItem(item: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeStatusItem:")
        ObjCRuntime.msgSend(null, ptr, sel, item)
    }
    
    // @property systemStatusBar
    open fun systemStatusBar(): MemorySegment {
        val sel = ObjCRuntime.sel("systemStatusBar")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property vertical
    open fun isVertical(): Boolean {
        val sel = ObjCRuntime.sel("isVertical")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property thickness
    open fun thickness(): Double {
        val sel = ObjCRuntime.sel("thickness")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
}

