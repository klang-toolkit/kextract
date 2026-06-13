package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSRelativeDateTimeFormatter
 * Superclass: NSFormatter
 */
open class NSRelativeDateTimeFormatter(override val ptr: MemorySegment) : NSFormatter(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSRelativeDateTimeFormatter") }
        
    }
    
    open fun localizedStringFromDateComponents(dateComponents: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("localizedStringFromDateComponents:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, dateComponents) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun localizedStringFromDateComponentsAsString(dateComponents: MemorySegment): String = ObjCRuntime.toJavaString(localizedStringFromDateComponents(dateComponents))
    
    open fun localizedStringFromTimeInterval(timeInterval: Double): MemorySegment {
        val sel = ObjCRuntime.sel("localizedStringFromTimeInterval:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, timeInterval) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun localizedStringFromTimeIntervalAsString(timeInterval: Double): String = ObjCRuntime.toJavaString(localizedStringFromTimeInterval(timeInterval))
    
    open fun localizedStringForDate_relativeToDate(date: MemorySegment, referenceDate: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("localizedStringForDate:relativeToDate:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, date, referenceDate) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun localizedStringForDate_relativeToDateAsString(date: MemorySegment, referenceDate: MemorySegment): String = ObjCRuntime.toJavaString(localizedStringForDate_relativeToDate(date, referenceDate))
    
    override fun stringForObjectValue(obj: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringForObjectValue:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, obj) as MemorySegment
    }
    
    // @property dateTimeStyle
    open fun dateTimeStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("dateTimeStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDateTimeStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDateTimeStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
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
    
    // @property formattingContext
    open fun formattingContext(): MemorySegment {
        val sel = ObjCRuntime.sel("formattingContext")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFormattingContext(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFormattingContext:")
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
    
    // @property locale
    open fun locale(): MemorySegment {
        val sel = ObjCRuntime.sel("locale")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLocale(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLocale:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

