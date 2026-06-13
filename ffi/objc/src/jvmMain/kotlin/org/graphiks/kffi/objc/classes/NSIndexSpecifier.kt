package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSIndexSpecifier
 * Superclass: NSScriptObjectSpecifier
 */
open class NSIndexSpecifier(override val ptr: MemorySegment) : NSScriptObjectSpecifier(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSIndexSpecifier") }
        
    }
    
    open fun initWithContainerClassDescription_containerSpecifier_key_index(classDesc: MemorySegment, container: MemorySegment, property: MemorySegment, index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContainerClassDescription:containerSpecifier:key:index:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, classDesc, container, property, index) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithContainerClassDescription_containerSpecifier_key_index(classDesc: MemorySegment, container: MemorySegment, property: String, index: Long): MemorySegment = initWithContainerClassDescription_containerSpecifier_key_index(classDesc, container, ObjCRuntime.newNSString(Arena.global(), property), index)
    
    // @property index
    open fun index(): Long {
        val sel = ObjCRuntime.sel("index")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setIndex(value: Long) {
        val sel = ObjCRuntime.sel("setIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _index: Long
}

