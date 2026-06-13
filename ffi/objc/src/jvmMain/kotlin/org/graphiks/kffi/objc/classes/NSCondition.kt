package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCondition
 * Superclass: NSObject
 * Protocols: NSLocking
 */
open class NSCondition(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCondition") }
        
    }
    
    open fun waitObjC(): Unit {
        val sel = ObjCRuntime.sel("wait")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun waitUntilDate(limit: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("waitUntilDate:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, limit) as Boolean
    }
    
    open fun signal(): Unit {
        val sel = ObjCRuntime.sel("signal")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun broadcast(): Unit {
        val sel = ObjCRuntime.sel("broadcast")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property name
    open fun name(): MemorySegment {
        val sel = ObjCRuntime.sel("name")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun nameAsString(): String = ObjCRuntime.toJavaString(name())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setName(value: String) = setName(ObjCRuntime.newNSString(Arena.global(), value))
    
}

