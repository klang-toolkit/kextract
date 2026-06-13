package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextRange
 * Superclass: NSObject
 */
open class NSTextRange(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextRange") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithLocation_endLocation(location: MemorySegment, endLocation: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithLocation:endLocation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, location, endLocation) as MemorySegment
    }
    
    open fun initWithLocation(location: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithLocation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, location) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun isEqualToTextRange(textRange: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isEqualToTextRange:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, textRange) as Boolean
    }
    
    open fun containsLocation(location: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("containsLocation:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, location) as Boolean
    }
    
    open fun containsRange(textRange: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("containsRange:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, textRange) as Boolean
    }
    
    open fun intersectsWithTextRange(textRange: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("intersectsWithTextRange:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, textRange) as Boolean
    }
    
    open fun textRangeByIntersectingWithTextRange(textRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("textRangeByIntersectingWithTextRange:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, textRange) as MemorySegment
    }
    
    open fun textRangeByFormingUnionWithTextRange(textRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("textRangeByFormingUnionWithTextRange:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, textRange) as MemorySegment
    }
    
    // @property empty
    open fun isEmpty(): Boolean {
        val sel = ObjCRuntime.sel("isEmpty")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property location
    /** @return id<NSTextLocation> */
    open fun location(): MemorySegment {
        val sel = ObjCRuntime.sel("location")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property endLocation
    /** @return id<NSTextLocation> */
    open fun endLocation(): MemorySegment {
        val sel = ObjCRuntime.sel("endLocation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

