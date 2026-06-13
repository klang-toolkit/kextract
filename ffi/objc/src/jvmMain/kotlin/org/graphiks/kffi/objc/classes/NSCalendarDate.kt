package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCalendarDate
 * Superclass: NSDate
 */
open class NSCalendarDate(override val ptr: MemorySegment) : NSDate(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCalendarDate") }
        
        fun calendarDate(): MemorySegment {
            val sel = ObjCRuntime.sel("calendarDate")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun dateWithString_calendarFormat_locale(description: MemorySegment, format: MemorySegment, locale: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("dateWithString:calendarFormat:locale:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, description, format, locale) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun dateWithString_calendarFormat_locale(description: String, format: String, locale: MemorySegment): MemorySegment = dateWithString_calendarFormat_locale(ObjCRuntime.newNSString(Arena.global(), description), ObjCRuntime.newNSString(Arena.global(), format), locale)
        
        fun dateWithString_calendarFormat(description: MemorySegment, format: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("dateWithString:calendarFormat:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, description, format) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun dateWithString_calendarFormat(description: String, format: String): MemorySegment = dateWithString_calendarFormat(ObjCRuntime.newNSString(Arena.global(), description), ObjCRuntime.newNSString(Arena.global(), format))
        
        fun dateWithYear_month_day_hour_minute_second_timeZone(year: Long, month: Long, day: Long, hour: Long, minute: Long, second: Long, aTimeZone: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("dateWithYear:month:day:hour:minute:second:timeZone:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, year, month, day, hour, minute, second, aTimeZone) as MemorySegment
        }
        
        fun distantFuture(): MemorySegment {
            val sel = ObjCRuntime.sel("distantFuture")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun distantPast(): MemorySegment {
            val sel = ObjCRuntime.sel("distantPast")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun dateByAddingYears_months_days_hours_minutes_seconds(year: Long, month: Long, day: Long, hour: Long, minute: Long, second: Long): MemorySegment {
        val sel = ObjCRuntime.sel("dateByAddingYears:months:days:hours:minutes:seconds:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, year, month, day, hour, minute, second) as MemorySegment
    }
    
    open fun dayOfCommonEra(): Long {
        val sel = ObjCRuntime.sel("dayOfCommonEra")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun dayOfMonth(): Long {
        val sel = ObjCRuntime.sel("dayOfMonth")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun dayOfWeek(): Long {
        val sel = ObjCRuntime.sel("dayOfWeek")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun dayOfYear(): Long {
        val sel = ObjCRuntime.sel("dayOfYear")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun hourOfDay(): Long {
        val sel = ObjCRuntime.sel("hourOfDay")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun minuteOfHour(): Long {
        val sel = ObjCRuntime.sel("minuteOfHour")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun monthOfYear(): Long {
        val sel = ObjCRuntime.sel("monthOfYear")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun secondOfMinute(): Long {
        val sel = ObjCRuntime.sel("secondOfMinute")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun yearOfCommonEra(): Long {
        val sel = ObjCRuntime.sel("yearOfCommonEra")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun calendarFormat(): MemorySegment {
        val sel = ObjCRuntime.sel("calendarFormat")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun calendarFormatAsString(): String = ObjCRuntime.toJavaString(calendarFormat())
    
    open fun descriptionWithCalendarFormat_locale(format: MemorySegment, locale: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("descriptionWithCalendarFormat:locale:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, format, locale) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun descriptionWithCalendarFormat_localeAsString(format: MemorySegment, locale: MemorySegment): String = ObjCRuntime.toJavaString(descriptionWithCalendarFormat_locale(format, locale))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun descriptionWithCalendarFormat_locale(format: String, locale: MemorySegment): MemorySegment = descriptionWithCalendarFormat_locale(ObjCRuntime.newNSString(Arena.global(), format), locale)
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun descriptionWithCalendarFormat_localeAsString(format: String, locale: MemorySegment): String = ObjCRuntime.toJavaString(descriptionWithCalendarFormat_locale(ObjCRuntime.newNSString(Arena.global(), format), locale))
    
    open fun descriptionWithCalendarFormat(format: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("descriptionWithCalendarFormat:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, format) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun descriptionWithCalendarFormatAsString(format: MemorySegment): String = ObjCRuntime.toJavaString(descriptionWithCalendarFormat(format))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun descriptionWithCalendarFormat(format: String): MemorySegment = descriptionWithCalendarFormat(ObjCRuntime.newNSString(Arena.global(), format))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun descriptionWithCalendarFormatAsString(format: String): String = ObjCRuntime.toJavaString(descriptionWithCalendarFormat(ObjCRuntime.newNSString(Arena.global(), format)))
    
    open fun descriptionWithLocale(locale: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("descriptionWithLocale:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, locale) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun descriptionWithLocaleAsString(locale: MemorySegment): String = ObjCRuntime.toJavaString(descriptionWithLocale(locale))
    
    open fun timeZone(): MemorySegment {
        val sel = ObjCRuntime.sel("timeZone")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithString_calendarFormat_locale(description: MemorySegment, format: MemorySegment, locale: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithString:calendarFormat:locale:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, description, format, locale) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithString_calendarFormat_locale(description: String, format: String, locale: MemorySegment): MemorySegment = initWithString_calendarFormat_locale(ObjCRuntime.newNSString(Arena.global(), description), ObjCRuntime.newNSString(Arena.global(), format), locale)
    
    open fun initWithString_calendarFormat(description: MemorySegment, format: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithString:calendarFormat:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, description, format) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithString_calendarFormat(description: String, format: String): MemorySegment = initWithString_calendarFormat(ObjCRuntime.newNSString(Arena.global(), description), ObjCRuntime.newNSString(Arena.global(), format))
    
    open fun initWithString(description: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithString:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, description) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithString(description: String): MemorySegment = initWithString(ObjCRuntime.newNSString(Arena.global(), description))
    
    open fun initWithYear_month_day_hour_minute_second_timeZone(year: Long, month: Long, day: Long, hour: Long, minute: Long, second: Long, aTimeZone: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithYear:month:day:hour:minute:second:timeZone:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, year, month, day, hour, minute, second, aTimeZone) as MemorySegment
    }
    
    open fun setCalendarFormat(format: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setCalendarFormat:")
        ObjCRuntime.msgSend(null, ptr, sel, format)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setCalendarFormat(format: String): Unit = setCalendarFormat(ObjCRuntime.newNSString(Arena.global(), format))
    
    open fun setTimeZone(aTimeZone: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setTimeZone:")
        ObjCRuntime.msgSend(null, ptr, sel, aTimeZone)
    }
    
    open fun years_months_days_hours_minutes_seconds_sinceDate(yp: MemorySegment, mop: MemorySegment, dp: MemorySegment, hp: MemorySegment, mip: MemorySegment, sp: MemorySegment, date: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("years:months:days:hours:minutes:seconds:sinceDate:")
        ObjCRuntime.msgSend(null, ptr, sel, yp, mop, dp, hp, mip, sp, date)
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: refCount: Long
    // ivar: _timeIntervalSinceReferenceDate: Double
    // ivar: _timeZone: MemorySegment
    // ivar: _formatString: MemorySegment
    // ivar: _reserved: MemorySegment
}

