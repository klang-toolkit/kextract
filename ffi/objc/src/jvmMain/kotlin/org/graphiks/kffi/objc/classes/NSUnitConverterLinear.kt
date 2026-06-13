package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitConverterLinear
 * Superclass: NSUnitConverter
 * Protocols: NSSecureCoding
 */
open class NSUnitConverterLinear(override val ptr: MemorySegment) : NSUnitConverter(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitConverterLinear") }
        
    }
    
    open fun initWithCoefficient(coefficient: Double): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoefficient:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coefficient) as MemorySegment
    }
    
    open fun initWithCoefficient_constant(coefficient: Double, constant: Double): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoefficient:constant:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coefficient, constant) as MemorySegment
    }
    
    // @property coefficient
    open fun coefficient(): Double {
        val sel = ObjCRuntime.sel("coefficient")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property constant
    open fun constant(): Double {
        val sel = ObjCRuntime.sel("constant")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _coefficient: Double
    // ivar: _constant: Double
}

