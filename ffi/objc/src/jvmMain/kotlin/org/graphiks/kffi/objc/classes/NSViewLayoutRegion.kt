package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSViewLayoutRegion
 * Superclass: NSObject
 */
open class NSViewLayoutRegion(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSViewLayoutRegion") }
        
        fun safeAreaLayoutRegionWithCornerAdaptation(adaptivityAxis: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("safeAreaLayoutRegionWithCornerAdaptation:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, adaptivityAxis) as MemorySegment
        }
        
        fun marginsLayoutRegionWithCornerAdaptation(adaptivityAxis: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("marginsLayoutRegionWithCornerAdaptation:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, adaptivityAxis) as MemorySegment
        }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

