package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAccessibilityCustomRotorSearchParameters
 * Superclass: NSObject
 */
open class NSAccessibilityCustomRotorSearchParameters(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAccessibilityCustomRotorSearchParameters") }
        
    }
    
    // @property currentItem
    open fun currentItem(): MemorySegment {
        val sel = ObjCRuntime.sel("currentItem")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCurrentItem(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCurrentItem:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property searchDirection
    open fun searchDirection(): MemorySegment {
        val sel = ObjCRuntime.sel("searchDirection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSearchDirection(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSearchDirection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property filterString
    open fun filterString(): MemorySegment {
        val sel = ObjCRuntime.sel("filterString")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFilterString(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFilterString:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun filterStringAsString(): String = ObjCRuntime.toJavaString(filterString())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setFilterString(value: String) = setFilterString(ObjCRuntime.newNSString(Arena.global(), value))
    
}

