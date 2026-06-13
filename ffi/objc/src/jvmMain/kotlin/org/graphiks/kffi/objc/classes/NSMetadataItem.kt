package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMetadataItem
 * Superclass: NSObject
 */
open class NSMetadataItem(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMetadataItem") }
        
    }
    
    open fun initWithURL(url: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url) as MemorySegment
    }
    
    open fun valueForAttribute(key: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("valueForAttribute:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, key) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun valueForAttribute(key: String): MemorySegment = valueForAttribute(ObjCRuntime.newNSString(Arena.global(), key))
    
    /** @return NSDictionary<NSString *,id> * */
    open fun valuesForAttributes(keys: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("valuesForAttributes:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, keys) as MemorySegment
    }
    
    // @property attributes
    /** @return NSArray<NSString *> * */
    open fun attributes(): MemorySegment {
        val sel = ObjCRuntime.sel("attributes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _item: MemorySegment
    // ivar: _reserved: MemorySegment
}

