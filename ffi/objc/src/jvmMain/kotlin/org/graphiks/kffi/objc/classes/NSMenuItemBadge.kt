package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMenuItemBadge
 * Superclass: NSObject
 * Protocols: NSCopying
 */
open class NSMenuItemBadge(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMenuItemBadge") }
        
        fun updatesWithCount(itemCount: Long): MemorySegment {
            val sel = ObjCRuntime.sel("updatesWithCount:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, itemCount) as MemorySegment
        }
        
        fun newItemsWithCount(itemCount: Long): MemorySegment {
            val sel = ObjCRuntime.sel("newItemsWithCount:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, itemCount) as MemorySegment
        }
        
        fun alertsWithCount(itemCount: Long): MemorySegment {
            val sel = ObjCRuntime.sel("alertsWithCount:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, itemCount) as MemorySegment
        }
        
    }
    
    open fun initWithCount_type(itemCount: Long, type: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCount:type:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, itemCount, type) as MemorySegment
    }
    
    open fun initWithCount(itemCount: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCount:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, itemCount) as MemorySegment
    }
    
    open fun initWithString(string: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithString:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, string) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithString(string: String): MemorySegment = initWithString(ObjCRuntime.newNSString(Arena.global(), string))
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property itemCount
    open fun itemCount(): Long {
        val sel = ObjCRuntime.sel("itemCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property type
    open fun type(): MemorySegment {
        val sel = ObjCRuntime.sel("type")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property stringValue
    open fun stringValue(): MemorySegment {
        val sel = ObjCRuntime.sel("stringValue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun stringValueAsString(): String = ObjCRuntime.toJavaString(stringValue())
    
}

