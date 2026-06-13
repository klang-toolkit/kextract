package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMeasurement
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSMeasurement(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMeasurement") }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithDoubleValue_unit(doubleValue: Double, unit: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDoubleValue:unit:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, doubleValue, unit) as MemorySegment
    }
    
    open fun canBeConvertedToUnit(unit: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("canBeConvertedToUnit:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, unit) as Boolean
    }
    
    open fun measurementByConvertingToUnit(unit: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("measurementByConvertingToUnit:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, unit) as MemorySegment
    }
    
    /** @return NSMeasurement<UnitType> * */
    open fun measurementByAddingMeasurement(measurement: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("measurementByAddingMeasurement:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, measurement) as MemorySegment
    }
    
    /** @return NSMeasurement<UnitType> * */
    open fun measurementBySubtractingMeasurement(measurement: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("measurementBySubtractingMeasurement:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, measurement) as MemorySegment
    }
    
    // @property unit
    open fun unit(): MemorySegment {
        val sel = ObjCRuntime.sel("unit")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property doubleValue
    open fun doubleValue(): Double {
        val sel = ObjCRuntime.sel("doubleValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _unit: MemorySegment
    // ivar: _doubleValue: Double
}

