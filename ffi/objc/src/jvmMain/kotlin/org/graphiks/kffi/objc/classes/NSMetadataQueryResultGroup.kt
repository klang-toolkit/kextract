package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMetadataQueryResultGroup
 * Superclass: NSObject
 */
open class NSMetadataQueryResultGroup(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMetadataQueryResultGroup") }
        
    }
    
    open fun resultAtIndex(idx: Long): MemorySegment {
        val sel = ObjCRuntime.sel("resultAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, idx) as MemorySegment
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
    
    // @property subgroups
    /** @return NSArray<NSMetadataQueryResultGroup *> * */
    open fun subgroups(): MemorySegment {
        val sel = ObjCRuntime.sel("subgroups")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property resultCount
    open fun resultCount(): Long {
        val sel = ObjCRuntime.sel("resultCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property results
    open fun results(): MemorySegment {
        val sel = ObjCRuntime.sel("results")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _private: MemorySegment
    // ivar: _private2: MemorySegment
    // ivar: _reserved: MemorySegment
}

