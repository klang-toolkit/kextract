package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDateComponents
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSDateComponents(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDateComponents") }
        
    }
    
    open fun week(): Long {
        val sel = ObjCRuntime.sel("week")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun setWeek(v: Long): Unit {
        val sel = ObjCRuntime.sel("setWeek:")
        ObjCRuntime.msgSend(null, ptr, sel, v)
    }
    
    open fun setValue_forComponent(value: Long, unit: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setValue:forComponent:")
        ObjCRuntime.msgSend(null, ptr, sel, value, unit)
    }
    
    open fun valueForComponent(unit: MemorySegment): Long {
        val sel = ObjCRuntime.sel("valueForComponent:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, unit) as Long
    }
    
    open fun isValidDateInCalendar(calendar: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isValidDateInCalendar:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, calendar) as Boolean
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
    
    // @property timeZone
    open fun timeZone(): MemorySegment {
        val sel = ObjCRuntime.sel("timeZone")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTimeZone(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTimeZone:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property era
    open fun era(): Long {
        val sel = ObjCRuntime.sel("era")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setEra(value: Long) {
        val sel = ObjCRuntime.sel("setEra:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property year
    open fun year(): Long {
        val sel = ObjCRuntime.sel("year")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setYear(value: Long) {
        val sel = ObjCRuntime.sel("setYear:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property month
    open fun month(): Long {
        val sel = ObjCRuntime.sel("month")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setMonth(value: Long) {
        val sel = ObjCRuntime.sel("setMonth:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property day
    open fun day(): Long {
        val sel = ObjCRuntime.sel("day")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setDay(value: Long) {
        val sel = ObjCRuntime.sel("setDay:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hour
    open fun hour(): Long {
        val sel = ObjCRuntime.sel("hour")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setHour(value: Long) {
        val sel = ObjCRuntime.sel("setHour:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property minute
    open fun minute(): Long {
        val sel = ObjCRuntime.sel("minute")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setMinute(value: Long) {
        val sel = ObjCRuntime.sel("setMinute:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property second
    open fun second(): Long {
        val sel = ObjCRuntime.sel("second")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setSecond(value: Long) {
        val sel = ObjCRuntime.sel("setSecond:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property nanosecond
    open fun nanosecond(): Long {
        val sel = ObjCRuntime.sel("nanosecond")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setNanosecond(value: Long) {
        val sel = ObjCRuntime.sel("setNanosecond:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property weekday
    open fun weekday(): Long {
        val sel = ObjCRuntime.sel("weekday")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setWeekday(value: Long) {
        val sel = ObjCRuntime.sel("setWeekday:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property weekdayOrdinal
    open fun weekdayOrdinal(): Long {
        val sel = ObjCRuntime.sel("weekdayOrdinal")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setWeekdayOrdinal(value: Long) {
        val sel = ObjCRuntime.sel("setWeekdayOrdinal:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property quarter
    open fun quarter(): Long {
        val sel = ObjCRuntime.sel("quarter")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setQuarter(value: Long) {
        val sel = ObjCRuntime.sel("setQuarter:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property weekOfMonth
    open fun weekOfMonth(): Long {
        val sel = ObjCRuntime.sel("weekOfMonth")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setWeekOfMonth(value: Long) {
        val sel = ObjCRuntime.sel("setWeekOfMonth:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property weekOfYear
    open fun weekOfYear(): Long {
        val sel = ObjCRuntime.sel("weekOfYear")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setWeekOfYear(value: Long) {
        val sel = ObjCRuntime.sel("setWeekOfYear:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property yearForWeekOfYear
    open fun yearForWeekOfYear(): Long {
        val sel = ObjCRuntime.sel("yearForWeekOfYear")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setYearForWeekOfYear(value: Long) {
        val sel = ObjCRuntime.sel("setYearForWeekOfYear:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property dayOfYear
    open fun dayOfYear(): Long {
        val sel = ObjCRuntime.sel("dayOfYear")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setDayOfYear(value: Long) {
        val sel = ObjCRuntime.sel("setDayOfYear:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property leapMonth
    open fun isLeapMonth(): Boolean {
        val sel = ObjCRuntime.sel("isLeapMonth")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setLeapMonth(value: Boolean) {
        val sel = ObjCRuntime.sel("setLeapMonth:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property repeatedDay
    open fun isRepeatedDay(): Boolean {
        val sel = ObjCRuntime.sel("isRepeatedDay")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setRepeatedDay(value: Boolean) {
        val sel = ObjCRuntime.sel("setRepeatedDay:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property date
    open fun date(): MemorySegment {
        val sel = ObjCRuntime.sel("date")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property validDate
    open fun isValidDate(): Boolean {
        val sel = ObjCRuntime.sel("isValidDate")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

