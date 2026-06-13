package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDictionaryControllerKeyValuePair
 * Superclass: NSObject
 */
open class NSDictionaryControllerKeyValuePair(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDictionaryControllerKeyValuePair") }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property key
    open fun key(): MemorySegment {
        val sel = ObjCRuntime.sel("key")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setKey(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setKey:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun keyAsString(): String = ObjCRuntime.toJavaString(key())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setKey(value: String) = setKey(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property value
    open fun value(): MemorySegment {
        val sel = ObjCRuntime.sel("value")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setValue(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property localizedKey
    open fun localizedKey(): MemorySegment {
        val sel = ObjCRuntime.sel("localizedKey")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLocalizedKey(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLocalizedKey:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun localizedKeyAsString(): String = ObjCRuntime.toJavaString(localizedKey())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setLocalizedKey(value: String) = setLocalizedKey(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property explicitlyIncluded
    open fun isExplicitlyIncluded(): Boolean {
        val sel = ObjCRuntime.sel("isExplicitlyIncluded")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

