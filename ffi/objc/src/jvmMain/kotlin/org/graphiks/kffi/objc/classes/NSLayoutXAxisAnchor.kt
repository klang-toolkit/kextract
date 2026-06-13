package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSLayoutXAxisAnchor
 * Superclass: NSLayoutAnchor
 */
open class NSLayoutXAxisAnchor(override val ptr: MemorySegment) : NSLayoutAnchor(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSLayoutXAxisAnchor") }
        
    }
    
    open fun anchorWithOffsetToAnchor(otherAnchor: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("anchorWithOffsetToAnchor:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, otherAnchor) as MemorySegment
    }
    
    open fun constraintEqualToSystemSpacingAfterAnchor_multiplier(anchor: MemorySegment, multiplier: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintEqualToSystemSpacingAfterAnchor:multiplier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, anchor, multiplier) as MemorySegment
    }
    
    open fun constraintGreaterThanOrEqualToSystemSpacingAfterAnchor_multiplier(anchor: MemorySegment, multiplier: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintGreaterThanOrEqualToSystemSpacingAfterAnchor:multiplier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, anchor, multiplier) as MemorySegment
    }
    
    open fun constraintLessThanOrEqualToSystemSpacingAfterAnchor_multiplier(anchor: MemorySegment, multiplier: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintLessThanOrEqualToSystemSpacingAfterAnchor:multiplier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, anchor, multiplier) as MemorySegment
    }
    
}

