package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMetadataQueryAttributeValueTuple
 * Superclass: NSObject
 */
open class NSMetadataQueryAttributeValueTuple(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMetadataQueryAttributeValueTuple") }
        
    }
    
    // @property attribute
    open fun attribute(): MemorySegment {
        val sel = ObjCRuntime.sel("attribute")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun attributeAsString(): String = ObjCRuntime.toJavaString(attribute())
    
    // @property value
    open fun value(): MemorySegment {
        val sel = ObjCRuntime.sel("value")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property count
    open fun count(): Long {
        val sel = ObjCRuntime.sel("count")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _attr: MemorySegment
    // ivar: _value: MemorySegment
    // ivar: _count: Long
    // ivar: _reserved: MemorySegment
}

