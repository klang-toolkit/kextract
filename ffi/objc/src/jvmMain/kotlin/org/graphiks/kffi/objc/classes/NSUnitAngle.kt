package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitAngle
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitAngle(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitAngle") }
        
        fun degrees(): MemorySegment {
            val sel = ObjCRuntime.sel("degrees")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun arcMinutes(): MemorySegment {
            val sel = ObjCRuntime.sel("arcMinutes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun arcSeconds(): MemorySegment {
            val sel = ObjCRuntime.sel("arcSeconds")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun radians(): MemorySegment {
            val sel = ObjCRuntime.sel("radians")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun gradians(): MemorySegment {
            val sel = ObjCRuntime.sel("gradians")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun revolutions(): MemorySegment {
            val sel = ObjCRuntime.sel("revolutions")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property degrees
    open fun degrees(): MemorySegment {
        val sel = ObjCRuntime.sel("degrees")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property arcMinutes
    open fun arcMinutes(): MemorySegment {
        val sel = ObjCRuntime.sel("arcMinutes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property arcSeconds
    open fun arcSeconds(): MemorySegment {
        val sel = ObjCRuntime.sel("arcSeconds")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property radians
    open fun radians(): MemorySegment {
        val sel = ObjCRuntime.sel("radians")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property gradians
    open fun gradians(): MemorySegment {
        val sel = ObjCRuntime.sel("gradians")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property revolutions
    open fun revolutions(): MemorySegment {
        val sel = ObjCRuntime.sel("revolutions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

