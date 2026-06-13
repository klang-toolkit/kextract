package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSpecifierTest
 * Superclass: NSScriptWhoseTest
 */
open class NSSpecifierTest(override val ptr: MemorySegment) : NSScriptWhoseTest(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSpecifierTest") }
        
    }
    
    override fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    override fun initWithCoder(inCoder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, inCoder) as MemorySegment
    }
    
    open fun initWithObjectSpecifier_comparisonOperator_testObject(obj1: MemorySegment, compOp: MemorySegment, obj2: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithObjectSpecifier:comparisonOperator:testObject:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, obj1, compOp, obj2) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _comparisonOperator: MemorySegment
    // ivar: _object1: MemorySegment
    // ivar: _object2: MemorySegment
}

