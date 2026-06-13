package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSURLQueryItem
 * Superclass: NSObject
 * Protocols: NSSecureCoding, NSCopying
 */
open class NSURLQueryItem(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSURLQueryItem") }
        
        fun queryItemWithName_value(name: MemorySegment, value: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("queryItemWithName:value:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, value) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun queryItemWithName_value(name: String, value: String): MemorySegment = queryItemWithName_value(ObjCRuntime.newNSString(Arena.global(), name), ObjCRuntime.newNSString(Arena.global(), value))
        
    }
    
    open fun initWithName_value(name: MemorySegment, value: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithName:value:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, value) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithName_value(name: String, value: String): MemorySegment = initWithName_value(ObjCRuntime.newNSString(Arena.global(), name), ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property name
    open fun name(): MemorySegment {
        val sel = ObjCRuntime.sel("name")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun nameAsString(): String = ObjCRuntime.toJavaString(name())
    
    // @property value
    open fun value(): MemorySegment {
        val sel = ObjCRuntime.sel("value")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun valueAsString(): String = ObjCRuntime.toJavaString(value())
    
}

