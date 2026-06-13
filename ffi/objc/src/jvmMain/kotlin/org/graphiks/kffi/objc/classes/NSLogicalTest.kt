package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSLogicalTest
 * Superclass: NSScriptWhoseTest
 */
open class NSLogicalTest(override val ptr: MemorySegment) : NSScriptWhoseTest(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSLogicalTest") }
        
    }
    
    open fun initAndTestWithTests(subTests: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initAndTestWithTests:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, subTests) as MemorySegment
    }
    
    open fun initOrTestWithTests(subTests: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initOrTestWithTests:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, subTests) as MemorySegment
    }
    
    open fun initNotTestWithTest(subTest: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initNotTestWithTest:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, subTest) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _operator: Int
    // ivar: _subTests: MemorySegment
}

