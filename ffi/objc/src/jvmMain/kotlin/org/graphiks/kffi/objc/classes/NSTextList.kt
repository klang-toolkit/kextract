package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextList
 * Superclass: NSObject
 * Protocols: NSSecureCoding, NSCopying
 */
open class NSTextList(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextList") }
        
        fun includesTextListMarkers(): Boolean {
            val sel = ObjCRuntime.sel("includesTextListMarkers")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
    }
    
    open fun initWithMarkerFormat_options_startingItemNumber(markerFormat: MemorySegment, options: MemorySegment, startingItemNumber: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithMarkerFormat:options:startingItemNumber:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, markerFormat, options, startingItemNumber) as MemorySegment
    }
    
    open fun initWithMarkerFormat_options(markerFormat: MemorySegment, options: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithMarkerFormat:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, markerFormat, options) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun markerForItemNumber(itemNumber: Long): MemorySegment {
        val sel = ObjCRuntime.sel("markerForItemNumber:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, itemNumber) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun markerForItemNumberAsString(itemNumber: Long): String = ObjCRuntime.toJavaString(markerForItemNumber(itemNumber))
    
    // @property markerFormat
    open fun markerFormat(): MemorySegment {
        val sel = ObjCRuntime.sel("markerFormat")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property listOptions
    open fun listOptions(): MemorySegment {
        val sel = ObjCRuntime.sel("listOptions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property startingItemNumber
    open fun startingItemNumber(): Long {
        val sel = ObjCRuntime.sel("startingItemNumber")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setStartingItemNumber(value: Long) {
        val sel = ObjCRuntime.sel("setStartingItemNumber:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property ordered
    open fun isOrdered(): Boolean {
        val sel = ObjCRuntime.sel("isOrdered")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property includesTextListMarkers
    open fun includesTextListMarkers(): Boolean {
        val sel = ObjCRuntime.sel("includesTextListMarkers")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

