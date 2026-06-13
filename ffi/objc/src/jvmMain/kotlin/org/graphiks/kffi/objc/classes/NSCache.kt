package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCache
 * Superclass: NSObject
 */
open class NSCache(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCache") }
        
    }
    
    open fun objectForKey(key: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("objectForKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, key) as MemorySegment
    }
    
    open fun setObject_forKey(obj: MemorySegment, key: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setObject:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, obj, key)
    }
    
    open fun setObject_forKey_cost(obj: MemorySegment, key: MemorySegment, g: Long): Unit {
        val sel = ObjCRuntime.sel("setObject:forKey:cost:")
        ObjCRuntime.msgSend(null, ptr, sel, obj, key, g)
    }
    
    open fun removeObjectForKey(key: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeObjectForKey:")
        ObjCRuntime.msgSend(null, ptr, sel, key)
    }
    
    open fun removeAllObjects(): Unit {
        val sel = ObjCRuntime.sel("removeAllObjects")
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
    
    // @property delegate
    /** @return id<NSCacheDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property totalCostLimit
    open fun totalCostLimit(): Long {
        val sel = ObjCRuntime.sel("totalCostLimit")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setTotalCostLimit(value: Long) {
        val sel = ObjCRuntime.sel("setTotalCostLimit:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property countLimit
    open fun countLimit(): Long {
        val sel = ObjCRuntime.sel("countLimit")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setCountLimit(value: Long) {
        val sel = ObjCRuntime.sel("setCountLimit:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property evictsObjectsWithDiscardedContent
    open fun evictsObjectsWithDiscardedContent(): Boolean {
        val sel = ObjCRuntime.sel("evictsObjectsWithDiscardedContent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEvictsObjectsWithDiscardedContent(value: Boolean) {
        val sel = ObjCRuntime.sel("setEvictsObjectsWithDiscardedContent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

