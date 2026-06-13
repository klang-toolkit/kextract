package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSWhoseSpecifier
 * Superclass: NSScriptObjectSpecifier
 */
open class NSWhoseSpecifier(override val ptr: MemorySegment) : NSScriptObjectSpecifier(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSWhoseSpecifier") }
        
    }
    
    override fun initWithCoder(inCoder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, inCoder) as MemorySegment
    }
    
    open fun initWithContainerClassDescription_containerSpecifier_key_test(classDesc: MemorySegment, container: MemorySegment, property: MemorySegment, test: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContainerClassDescription:containerSpecifier:key:test:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, classDesc, container, property, test) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithContainerClassDescription_containerSpecifier_key_test(classDesc: MemorySegment, container: MemorySegment, property: String, test: MemorySegment): MemorySegment = initWithContainerClassDescription_containerSpecifier_key_test(classDesc, container, ObjCRuntime.newNSString(Arena.global(), property), test)
    
    // @property test
    open fun test(): MemorySegment {
        val sel = ObjCRuntime.sel("test")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTest(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTest:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property startSubelementIdentifier
    open fun startSubelementIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("startSubelementIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setStartSubelementIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setStartSubelementIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property startSubelementIndex
    open fun startSubelementIndex(): Long {
        val sel = ObjCRuntime.sel("startSubelementIndex")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setStartSubelementIndex(value: Long) {
        val sel = ObjCRuntime.sel("setStartSubelementIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property endSubelementIdentifier
    open fun endSubelementIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("endSubelementIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setEndSubelementIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setEndSubelementIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property endSubelementIndex
    open fun endSubelementIndex(): Long {
        val sel = ObjCRuntime.sel("endSubelementIndex")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setEndSubelementIndex(value: Long) {
        val sel = ObjCRuntime.sel("setEndSubelementIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _test: MemorySegment
    // ivar: _startSubelementIdentifier: MemorySegment
    // ivar: _startSubelementIndex: Long
    // ivar: _endSubelementIdentifier: MemorySegment
    // ivar: _endSubelementIndex: Long
}

