package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSByteCountFormatter
 * Superclass: NSFormatter
 */
open class NSByteCountFormatter(override val ptr: MemorySegment) : NSFormatter(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSByteCountFormatter") }
        
        fun stringFromByteCount_countStyle(byteCount: Long, countStyle: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("stringFromByteCount:countStyle:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, byteCount, countStyle) as MemorySegment
        }
        
        /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
        fun stringFromByteCount_countStyleAsString(byteCount: Long, countStyle: MemorySegment): String = ObjCRuntime.toJavaString(stringFromByteCount_countStyle(byteCount, countStyle))
        
        fun stringFromMeasurement_countStyle(measurement: MemorySegment, countStyle: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("stringFromMeasurement:countStyle:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, measurement, countStyle) as MemorySegment
        }
        
        /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
        fun stringFromMeasurement_countStyleAsString(measurement: MemorySegment, countStyle: MemorySegment): String = ObjCRuntime.toJavaString(stringFromMeasurement_countStyle(measurement, countStyle))
        
    }
    
    open fun stringFromByteCount(byteCount: Long): MemorySegment {
        val sel = ObjCRuntime.sel("stringFromByteCount:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, byteCount) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringFromByteCountAsString(byteCount: Long): String = ObjCRuntime.toJavaString(stringFromByteCount(byteCount))
    
    open fun stringFromMeasurement(measurement: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringFromMeasurement:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, measurement) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringFromMeasurementAsString(measurement: MemorySegment): String = ObjCRuntime.toJavaString(stringFromMeasurement(measurement))
    
    override fun stringForObjectValue(obj: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringForObjectValue:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, obj) as MemorySegment
    }
    
    // @property allowedUnits
    open fun allowedUnits(): MemorySegment {
        val sel = ObjCRuntime.sel("allowedUnits")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAllowedUnits(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAllowedUnits:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property countStyle
    open fun countStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("countStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCountStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCountStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsNonnumericFormatting
    open fun allowsNonnumericFormatting(): Boolean {
        val sel = ObjCRuntime.sel("allowsNonnumericFormatting")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsNonnumericFormatting(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsNonnumericFormatting:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property includesUnit
    open fun includesUnit(): Boolean {
        val sel = ObjCRuntime.sel("includesUnit")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setIncludesUnit(value: Boolean) {
        val sel = ObjCRuntime.sel("setIncludesUnit:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property includesCount
    open fun includesCount(): Boolean {
        val sel = ObjCRuntime.sel("includesCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setIncludesCount(value: Boolean) {
        val sel = ObjCRuntime.sel("setIncludesCount:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property includesActualByteCount
    open fun includesActualByteCount(): Boolean {
        val sel = ObjCRuntime.sel("includesActualByteCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setIncludesActualByteCount(value: Boolean) {
        val sel = ObjCRuntime.sel("setIncludesActualByteCount:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property adaptive
    open fun isAdaptive(): Boolean {
        val sel = ObjCRuntime.sel("isAdaptive")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAdaptive(value: Boolean) {
        val sel = ObjCRuntime.sel("setAdaptive:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property zeroPadsFractionDigits
    open fun zeroPadsFractionDigits(): Boolean {
        val sel = ObjCRuntime.sel("zeroPadsFractionDigits")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setZeroPadsFractionDigits(value: Boolean) {
        val sel = ObjCRuntime.sel("setZeroPadsFractionDigits:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property formattingContext
    open fun formattingContext(): MemorySegment {
        val sel = ObjCRuntime.sel("formattingContext")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFormattingContext(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFormattingContext:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

