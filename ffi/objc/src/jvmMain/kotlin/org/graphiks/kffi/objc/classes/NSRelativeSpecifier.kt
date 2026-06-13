package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSRelativeSpecifier
 * Superclass: NSScriptObjectSpecifier
 */
open class NSRelativeSpecifier(override val ptr: MemorySegment) : NSScriptObjectSpecifier(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSRelativeSpecifier") }
        
    }
    
    override fun initWithCoder(inCoder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, inCoder) as MemorySegment
    }
    
    open fun initWithContainerClassDescription_containerSpecifier_key_relativePosition_baseSpecifier(classDesc: MemorySegment, container: MemorySegment, property: MemorySegment, relPos: MemorySegment, baseSpecifier: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContainerClassDescription:containerSpecifier:key:relativePosition:baseSpecifier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, classDesc, container, property, relPos, baseSpecifier) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithContainerClassDescription_containerSpecifier_key_relativePosition_baseSpecifier(classDesc: MemorySegment, container: MemorySegment, property: String, relPos: MemorySegment, baseSpecifier: MemorySegment): MemorySegment = initWithContainerClassDescription_containerSpecifier_key_relativePosition_baseSpecifier(classDesc, container, ObjCRuntime.newNSString(Arena.global(), property), relPos, baseSpecifier)
    
    // @property relativePosition
    open fun relativePosition(): MemorySegment {
        val sel = ObjCRuntime.sel("relativePosition")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRelativePosition(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRelativePosition:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property baseSpecifier
    open fun baseSpecifier(): MemorySegment {
        val sel = ObjCRuntime.sel("baseSpecifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBaseSpecifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBaseSpecifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _relativePosition: MemorySegment
    // ivar: _baseSpecifier: MemorySegment
}

