package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitAcceleration
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitAcceleration(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitAcceleration") }
        
        fun metersPerSecondSquared(): MemorySegment {
            val sel = ObjCRuntime.sel("metersPerSecondSquared")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun gravity(): MemorySegment {
            val sel = ObjCRuntime.sel("gravity")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property metersPerSecondSquared
    open fun metersPerSecondSquared(): MemorySegment {
        val sel = ObjCRuntime.sel("metersPerSecondSquared")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property gravity
    open fun gravity(): MemorySegment {
        val sel = ObjCRuntime.sel("gravity")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

