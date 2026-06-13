package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSLayoutYAxisAnchor
 * Superclass: NSLayoutAnchor
 */
open class NSLayoutYAxisAnchor(override val ptr: MemorySegment) : NSLayoutAnchor(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSLayoutYAxisAnchor") }
        
    }
    
    open fun anchorWithOffsetToAnchor(otherAnchor: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("anchorWithOffsetToAnchor:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, otherAnchor) as MemorySegment
    }
    
    open fun constraintEqualToSystemSpacingBelowAnchor_multiplier(anchor: MemorySegment, multiplier: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintEqualToSystemSpacingBelowAnchor:multiplier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, anchor, multiplier) as MemorySegment
    }
    
    open fun constraintGreaterThanOrEqualToSystemSpacingBelowAnchor_multiplier(anchor: MemorySegment, multiplier: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintGreaterThanOrEqualToSystemSpacingBelowAnchor:multiplier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, anchor, multiplier) as MemorySegment
    }
    
    open fun constraintLessThanOrEqualToSystemSpacingBelowAnchor_multiplier(anchor: MemorySegment, multiplier: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintLessThanOrEqualToSystemSpacingBelowAnchor:multiplier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, anchor, multiplier) as MemorySegment
    }
    
}

