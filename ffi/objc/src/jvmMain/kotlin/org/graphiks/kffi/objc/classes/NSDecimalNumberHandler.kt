package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDecimalNumberHandler
 * Superclass: NSObject
 * Protocols: NSDecimalNumberBehaviors, NSCoding
 */
open class NSDecimalNumberHandler(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDecimalNumberHandler") }
        
        fun decimalNumberHandlerWithRoundingMode_scale_raiseOnExactness_raiseOnOverflow_raiseOnUnderflow_raiseOnDivideByZero(roundingMode: MemorySegment, scale: Short, exact: Boolean, overflow: Boolean, underflow: Boolean, divideByZero: Boolean): MemorySegment {
            val sel = ObjCRuntime.sel("decimalNumberHandlerWithRoundingMode:scale:raiseOnExactness:raiseOnOverflow:raiseOnUnderflow:raiseOnDivideByZero:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, roundingMode, scale, exact, overflow, underflow, divideByZero) as MemorySegment
        }
        
        fun defaultDecimalNumberHandler(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultDecimalNumberHandler")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithRoundingMode_scale_raiseOnExactness_raiseOnOverflow_raiseOnUnderflow_raiseOnDivideByZero(roundingMode: MemorySegment, scale: Short, exact: Boolean, overflow: Boolean, underflow: Boolean, divideByZero: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithRoundingMode:scale:raiseOnExactness:raiseOnOverflow:raiseOnUnderflow:raiseOnDivideByZero:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, roundingMode, scale, exact, overflow, underflow, divideByZero) as MemorySegment
    }
    
    // @property defaultDecimalNumberHandler
    open fun defaultDecimalNumberHandler(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultDecimalNumberHandler")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _scale: Int
    // ivar: _roundingMode: Int
    // ivar: _raiseOnExactness: Int
    // ivar: _raiseOnOverflow: Int
    // ivar: _raiseOnUnderflow: Int
    // ivar: _raiseOnDivideByZero: Int
    // ivar: _unused: Int
    // ivar: _reserved2: MemorySegment
    // ivar: _reserved: MemorySegment
}

