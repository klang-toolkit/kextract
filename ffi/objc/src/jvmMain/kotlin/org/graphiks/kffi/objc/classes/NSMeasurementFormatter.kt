package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMeasurementFormatter
 * Superclass: NSFormatter
 * Protocols: NSSecureCoding
 */
open class NSMeasurementFormatter(override val ptr: MemorySegment) : NSFormatter(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMeasurementFormatter") }
        
    }
    
    open fun stringFromMeasurement(measurement: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringFromMeasurement:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, measurement) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringFromMeasurementAsString(measurement: MemorySegment): String = ObjCRuntime.toJavaString(stringFromMeasurement(measurement))
    
    open fun stringFromUnit(unit: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringFromUnit:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, unit) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringFromUnitAsString(unit: MemorySegment): String = ObjCRuntime.toJavaString(stringFromUnit(unit))
    
    // @property unitOptions
    open fun unitOptions(): MemorySegment {
        val sel = ObjCRuntime.sel("unitOptions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setUnitOptions(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setUnitOptions:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property unitStyle
    open fun unitStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("unitStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setUnitStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setUnitStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property locale
    open fun locale(): MemorySegment {
        val sel = ObjCRuntime.sel("locale")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLocale(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLocale:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property numberFormatter
    open fun numberFormatter(): MemorySegment {
        val sel = ObjCRuntime.sel("numberFormatter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setNumberFormatter(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setNumberFormatter:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _formatter: MemorySegment
}

