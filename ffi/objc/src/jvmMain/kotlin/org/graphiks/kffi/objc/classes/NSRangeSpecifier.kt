package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSRangeSpecifier
 * Superclass: NSScriptObjectSpecifier
 */
open class NSRangeSpecifier(override val ptr: MemorySegment) : NSScriptObjectSpecifier(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSRangeSpecifier") }
        
    }
    
    override fun initWithCoder(inCoder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, inCoder) as MemorySegment
    }
    
    open fun initWithContainerClassDescription_containerSpecifier_key_startSpecifier_endSpecifier(classDesc: MemorySegment, container: MemorySegment, property: MemorySegment, startSpec: MemorySegment, endSpec: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContainerClassDescription:containerSpecifier:key:startSpecifier:endSpecifier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, classDesc, container, property, startSpec, endSpec) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithContainerClassDescription_containerSpecifier_key_startSpecifier_endSpecifier(classDesc: MemorySegment, container: MemorySegment, property: String, startSpec: MemorySegment, endSpec: MemorySegment): MemorySegment = initWithContainerClassDescription_containerSpecifier_key_startSpecifier_endSpecifier(classDesc, container, ObjCRuntime.newNSString(Arena.global(), property), startSpec, endSpec)
    
    // @property startSpecifier
    open fun startSpecifier(): MemorySegment {
        val sel = ObjCRuntime.sel("startSpecifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setStartSpecifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setStartSpecifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property endSpecifier
    open fun endSpecifier(): MemorySegment {
        val sel = ObjCRuntime.sel("endSpecifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setEndSpecifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setEndSpecifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _startSpec: MemorySegment
    // ivar: _endSpec: MemorySegment
}

