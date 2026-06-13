package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMassFormatter
 * Superclass: NSFormatter
 */
open class NSMassFormatter(override val ptr: MemorySegment) : NSFormatter(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMassFormatter") }
        
    }
    
    open fun stringFromValue_unit(value: Double, unit: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringFromValue:unit:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value, unit) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringFromValue_unitAsString(value: Double, unit: MemorySegment): String = ObjCRuntime.toJavaString(stringFromValue_unit(value, unit))
    
    open fun stringFromKilograms(numberInKilograms: Double): MemorySegment {
        val sel = ObjCRuntime.sel("stringFromKilograms:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, numberInKilograms) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringFromKilogramsAsString(numberInKilograms: Double): String = ObjCRuntime.toJavaString(stringFromKilograms(numberInKilograms))
    
    open fun unitStringFromValue_unit(value: Double, unit: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("unitStringFromValue:unit:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value, unit) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun unitStringFromValue_unitAsString(value: Double, unit: MemorySegment): String = ObjCRuntime.toJavaString(unitStringFromValue_unit(value, unit))
    
    open fun unitStringFromKilograms_usedUnit(numberInKilograms: Double, unitp: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("unitStringFromKilograms:usedUnit:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, numberInKilograms, unitp) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun unitStringFromKilograms_usedUnitAsString(numberInKilograms: Double, unitp: MemorySegment): String = ObjCRuntime.toJavaString(unitStringFromKilograms_usedUnit(numberInKilograms, unitp))
    
    override fun getObjectValue_forString_errorDescription(obj: MemorySegment, string: MemorySegment, error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("getObjectValue:forString:errorDescription:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, obj, string, error) as Boolean
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
    
    // @property unitStyle
    open fun unitStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("unitStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setUnitStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setUnitStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property forPersonMassUse
    open fun isForPersonMassUse(): Boolean {
        val sel = ObjCRuntime.sel("isForPersonMassUse")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setForPersonMassUse(value: Boolean) {
        val sel = ObjCRuntime.sel("setForPersonMassUse:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

