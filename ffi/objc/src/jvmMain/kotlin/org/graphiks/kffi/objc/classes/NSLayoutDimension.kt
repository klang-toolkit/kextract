package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSLayoutDimension
 * Superclass: NSLayoutAnchor
 */
open class NSLayoutDimension(override val ptr: MemorySegment) : NSLayoutAnchor(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSLayoutDimension") }
        
    }
    
    open fun constraintEqualToConstant(c: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintEqualToConstant:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, c) as MemorySegment
    }
    
    open fun constraintGreaterThanOrEqualToConstant(c: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintGreaterThanOrEqualToConstant:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, c) as MemorySegment
    }
    
    open fun constraintLessThanOrEqualToConstant(c: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintLessThanOrEqualToConstant:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, c) as MemorySegment
    }
    
    open fun constraintEqualToAnchor_multiplier(anchor: MemorySegment, m: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintEqualToAnchor:multiplier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, anchor, m) as MemorySegment
    }
    
    open fun constraintGreaterThanOrEqualToAnchor_multiplier(anchor: MemorySegment, m: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintGreaterThanOrEqualToAnchor:multiplier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, anchor, m) as MemorySegment
    }
    
    open fun constraintLessThanOrEqualToAnchor_multiplier(anchor: MemorySegment, m: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintLessThanOrEqualToAnchor:multiplier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, anchor, m) as MemorySegment
    }
    
    open fun constraintEqualToAnchor_multiplier_constant(anchor: MemorySegment, m: Double, c: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintEqualToAnchor:multiplier:constant:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, anchor, m, c) as MemorySegment
    }
    
    open fun constraintGreaterThanOrEqualToAnchor_multiplier_constant(anchor: MemorySegment, m: Double, c: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintGreaterThanOrEqualToAnchor:multiplier:constant:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, anchor, m, c) as MemorySegment
    }
    
    open fun constraintLessThanOrEqualToAnchor_multiplier_constant(anchor: MemorySegment, m: Double, c: Double): MemorySegment {
        val sel = ObjCRuntime.sel("constraintLessThanOrEqualToAnchor:multiplier:constant:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, anchor, m, c) as MemorySegment
    }
    
}

