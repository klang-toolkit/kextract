package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSXPCCoder
 * Superclass: NSCoder
 */
open class NSXPCCoder(override val ptr: MemorySegment) : NSCoder(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSXPCCoder") }
        
    }
    
    open fun encodeXPCObject_forKey(xpcObject: MemorySegment, key: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("encodeXPCObject:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, xpcObject, key)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun encodeXPCObject_forKey(xpcObject: MemorySegment, key: String): Unit = encodeXPCObject_forKey(xpcObject, ObjCRuntime.newNSString(Arena.global(), key))
    
    open fun decodeXPCObjectOfType_forKey(type: MemorySegment, key: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("decodeXPCObjectOfType:forKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, type, key) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun decodeXPCObjectOfType_forKey(type: MemorySegment, key: String): MemorySegment = decodeXPCObjectOfType_forKey(type, ObjCRuntime.newNSString(Arena.global(), key))
    
    // @property userInfo
    /** @return id<NSObject> */
    open fun userInfo(): MemorySegment {
        val sel = ObjCRuntime.sel("userInfo")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setUserInfo(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setUserInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property connection
    open fun connection(): MemorySegment {
        val sel = ObjCRuntime.sel("connection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

