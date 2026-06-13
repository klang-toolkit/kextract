package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDateComponentsFormatter
 * Superclass: NSFormatter
 */
open class NSDateComponentsFormatter(override val ptr: MemorySegment) : NSFormatter(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDateComponentsFormatter") }
        
        fun localizedStringFromDateComponents_unitsStyle(components: MemorySegment, unitsStyle: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("localizedStringFromDateComponents:unitsStyle:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, components, unitsStyle) as MemorySegment
        }
        
        /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
        fun localizedStringFromDateComponents_unitsStyleAsString(components: MemorySegment, unitsStyle: MemorySegment): String = ObjCRuntime.toJavaString(localizedStringFromDateComponents_unitsStyle(components, unitsStyle))
        
    }
    
    override fun stringForObjectValue(obj: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringForObjectValue:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, obj) as MemorySegment
    }
    
    open fun stringFromDateComponents(components: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringFromDateComponents:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, components) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringFromDateComponentsAsString(components: MemorySegment): String = ObjCRuntime.toJavaString(stringFromDateComponents(components))
    
    open fun stringFromDate_toDate(startDate: MemorySegment, endDate: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringFromDate:toDate:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, startDate, endDate) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringFromDate_toDateAsString(startDate: MemorySegment, endDate: MemorySegment): String = ObjCRuntime.toJavaString(stringFromDate_toDate(startDate, endDate))
    
    open fun stringFromTimeInterval(ti: Double): MemorySegment {
        val sel = ObjCRuntime.sel("stringFromTimeInterval:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ti) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringFromTimeIntervalAsString(ti: Double): String = ObjCRuntime.toJavaString(stringFromTimeInterval(ti))
    
    override fun getObjectValue_forString_errorDescription(obj: MemorySegment, string: MemorySegment, error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("getObjectValue:forString:errorDescription:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, obj, string, error) as Boolean
    }
    
    // @property unitsStyle
    open fun unitsStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("unitsStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setUnitsStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setUnitsStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
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
    
    // @property zeroFormattingBehavior
    open fun zeroFormattingBehavior(): MemorySegment {
        val sel = ObjCRuntime.sel("zeroFormattingBehavior")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setZeroFormattingBehavior(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setZeroFormattingBehavior:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property calendar
    open fun calendar(): MemorySegment {
        val sel = ObjCRuntime.sel("calendar")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCalendar(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCalendar:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property referenceDate
    open fun referenceDate(): MemorySegment {
        val sel = ObjCRuntime.sel("referenceDate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setReferenceDate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setReferenceDate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsFractionalUnits
    open fun allowsFractionalUnits(): Boolean {
        val sel = ObjCRuntime.sel("allowsFractionalUnits")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsFractionalUnits(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsFractionalUnits:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property maximumUnitCount
    open fun maximumUnitCount(): Long {
        val sel = ObjCRuntime.sel("maximumUnitCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setMaximumUnitCount(value: Long) {
        val sel = ObjCRuntime.sel("setMaximumUnitCount:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property collapsesLargestUnit
    open fun collapsesLargestUnit(): Boolean {
        val sel = ObjCRuntime.sel("collapsesLargestUnit")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCollapsesLargestUnit(value: Boolean) {
        val sel = ObjCRuntime.sel("setCollapsesLargestUnit:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property includesApproximationPhrase
    open fun includesApproximationPhrase(): Boolean {
        val sel = ObjCRuntime.sel("includesApproximationPhrase")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setIncludesApproximationPhrase(value: Boolean) {
        val sel = ObjCRuntime.sel("setIncludesApproximationPhrase:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property includesTimeRemainingPhrase
    open fun includesTimeRemainingPhrase(): Boolean {
        val sel = ObjCRuntime.sel("includesTimeRemainingPhrase")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setIncludesTimeRemainingPhrase(value: Boolean) {
        val sel = ObjCRuntime.sel("setIncludesTimeRemainingPhrase:")
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

