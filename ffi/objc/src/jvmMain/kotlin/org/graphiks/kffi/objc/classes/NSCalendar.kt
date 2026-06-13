package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCalendar
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSCalendar(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCalendar") }
        
        fun calendarWithIdentifier(calendarIdentifierConstant: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("calendarWithIdentifier:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, calendarIdentifierConstant) as MemorySegment
        }
        
        fun currentCalendar(): MemorySegment {
            val sel = ObjCRuntime.sel("currentCalendar")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun autoupdatingCurrentCalendar(): MemorySegment {
            val sel = ObjCRuntime.sel("autoupdatingCurrentCalendar")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithCalendarIdentifier(ident: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCalendarIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ident) as MemorySegment
    }
    
    open fun minimumRangeOfUnit(unit: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("minimumRangeOfUnit:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, unit) as MemorySegment
    }
    
    open fun maximumRangeOfUnit(unit: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("maximumRangeOfUnit:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, unit) as MemorySegment
    }
    
    open fun rangeOfUnit_inUnit_forDate(smaller: MemorySegment, larger: MemorySegment, date: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("rangeOfUnit:inUnit:forDate:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, smaller, larger, date) as MemorySegment
    }
    
    open fun ordinalityOfUnit_inUnit_forDate(smaller: MemorySegment, larger: MemorySegment, date: MemorySegment): Long {
        val sel = ObjCRuntime.sel("ordinalityOfUnit:inUnit:forDate:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, smaller, larger, date) as Long
    }
    
    open fun rangeOfUnit_startDate_interval_forDate(unit: MemorySegment, datep: MemorySegment, tip: MemorySegment, date: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("rangeOfUnit:startDate:interval:forDate:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, unit, datep, tip, date) as Boolean
    }
    
    open fun dateFromComponents(comps: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dateFromComponents:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, comps) as MemorySegment
    }
    
    open fun components_fromDate(unitFlags: MemorySegment, date: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("components:fromDate:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, unitFlags, date) as MemorySegment
    }
    
    open fun dateByAddingComponents_toDate_options(comps: MemorySegment, date: MemorySegment, opts: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dateByAddingComponents:toDate:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, comps, date, opts) as MemorySegment
    }
    
    open fun components_fromDate_toDate_options(unitFlags: MemorySegment, startingDate: MemorySegment, resultDate: MemorySegment, opts: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("components:fromDate:toDate:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, unitFlags, startingDate, resultDate, opts) as MemorySegment
    }
    
    open fun getEra_year_month_day_fromDate(eraValuePointer: MemorySegment, yearValuePointer: MemorySegment, monthValuePointer: MemorySegment, dayValuePointer: MemorySegment, date: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getEra:year:month:day:fromDate:")
        ObjCRuntime.msgSend(null, ptr, sel, eraValuePointer, yearValuePointer, monthValuePointer, dayValuePointer, date)
    }
    
    open fun getEra_yearForWeekOfYear_weekOfYear_weekday_fromDate(eraValuePointer: MemorySegment, yearValuePointer: MemorySegment, weekValuePointer: MemorySegment, weekdayValuePointer: MemorySegment, date: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getEra:yearForWeekOfYear:weekOfYear:weekday:fromDate:")
        ObjCRuntime.msgSend(null, ptr, sel, eraValuePointer, yearValuePointer, weekValuePointer, weekdayValuePointer, date)
    }
    
    open fun getHour_minute_second_nanosecond_fromDate(hourValuePointer: MemorySegment, minuteValuePointer: MemorySegment, secondValuePointer: MemorySegment, nanosecondValuePointer: MemorySegment, date: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getHour:minute:second:nanosecond:fromDate:")
        ObjCRuntime.msgSend(null, ptr, sel, hourValuePointer, minuteValuePointer, secondValuePointer, nanosecondValuePointer, date)
    }
    
    open fun component_fromDate(unit: MemorySegment, date: MemorySegment): Long {
        val sel = ObjCRuntime.sel("component:fromDate:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, unit, date) as Long
    }
    
    open fun dateWithEra_year_month_day_hour_minute_second_nanosecond(eraValue: Long, yearValue: Long, monthValue: Long, dayValue: Long, hourValue: Long, minuteValue: Long, secondValue: Long, nanosecondValue: Long): MemorySegment {
        val sel = ObjCRuntime.sel("dateWithEra:year:month:day:hour:minute:second:nanosecond:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, eraValue, yearValue, monthValue, dayValue, hourValue, minuteValue, secondValue, nanosecondValue) as MemorySegment
    }
    
    open fun dateWithEra_yearForWeekOfYear_weekOfYear_weekday_hour_minute_second_nanosecond(eraValue: Long, yearValue: Long, weekValue: Long, weekdayValue: Long, hourValue: Long, minuteValue: Long, secondValue: Long, nanosecondValue: Long): MemorySegment {
        val sel = ObjCRuntime.sel("dateWithEra:yearForWeekOfYear:weekOfYear:weekday:hour:minute:second:nanosecond:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, eraValue, yearValue, weekValue, weekdayValue, hourValue, minuteValue, secondValue, nanosecondValue) as MemorySegment
    }
    
    open fun startOfDayForDate(date: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("startOfDayForDate:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, date) as MemorySegment
    }
    
    open fun componentsInTimeZone_fromDate(timezone: MemorySegment, date: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("componentsInTimeZone:fromDate:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, timezone, date) as MemorySegment
    }
    
    open fun compareDate_toDate_toUnitGranularity(date1: MemorySegment, date2: MemorySegment, unit: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("compareDate:toDate:toUnitGranularity:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, date1, date2, unit) as MemorySegment
    }
    
    open fun isDate_equalToDate_toUnitGranularity(date1: MemorySegment, date2: MemorySegment, unit: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isDate:equalToDate:toUnitGranularity:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, date1, date2, unit) as Boolean
    }
    
    open fun isDate_inSameDayAsDate(date1: MemorySegment, date2: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isDate:inSameDayAsDate:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, date1, date2) as Boolean
    }
    
    open fun isDateInToday(date: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isDateInToday:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, date) as Boolean
    }
    
    open fun isDateInYesterday(date: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isDateInYesterday:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, date) as Boolean
    }
    
    open fun isDateInTomorrow(date: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isDateInTomorrow:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, date) as Boolean
    }
    
    open fun isDateInWeekend(date: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isDateInWeekend:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, date) as Boolean
    }
    
    open fun rangeOfWeekendStartDate_interval_containingDate(datep: MemorySegment, tip: MemorySegment, date: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("rangeOfWeekendStartDate:interval:containingDate:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, datep, tip, date) as Boolean
    }
    
    open fun nextWeekendStartDate_interval_options_afterDate(datep: MemorySegment, tip: MemorySegment, options: MemorySegment, date: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("nextWeekendStartDate:interval:options:afterDate:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, datep, tip, options, date) as Boolean
    }
    
    open fun components_fromDateComponents_toDateComponents_options(unitFlags: MemorySegment, startingDateComp: MemorySegment, resultDateComp: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("components:fromDateComponents:toDateComponents:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, unitFlags, startingDateComp, resultDateComp, options) as MemorySegment
    }
    
    open fun dateByAddingUnit_value_toDate_options(unit: MemorySegment, value: Long, date: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dateByAddingUnit:value:toDate:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, unit, value, date, options) as MemorySegment
    }
    
    open fun enumerateDatesStartingAfterDate_matchingComponents_options_usingBlock(start: MemorySegment, comps: MemorySegment, opts: MemorySegment, block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateDatesStartingAfterDate:matchingComponents:options:usingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, start, comps, opts, block)
    }
    
    open fun nextDateAfterDate_matchingComponents_options(date: MemorySegment, comps: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("nextDateAfterDate:matchingComponents:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, date, comps, options) as MemorySegment
    }
    
    open fun nextDateAfterDate_matchingUnit_value_options(date: MemorySegment, unit: MemorySegment, value: Long, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("nextDateAfterDate:matchingUnit:value:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, date, unit, value, options) as MemorySegment
    }
    
    open fun nextDateAfterDate_matchingHour_minute_second_options(date: MemorySegment, hourValue: Long, minuteValue: Long, secondValue: Long, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("nextDateAfterDate:matchingHour:minute:second:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, date, hourValue, minuteValue, secondValue, options) as MemorySegment
    }
    
    open fun dateBySettingUnit_value_ofDate_options(unit: MemorySegment, v: Long, date: MemorySegment, opts: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dateBySettingUnit:value:ofDate:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, unit, v, date, opts) as MemorySegment
    }
    
    open fun dateBySettingHour_minute_second_ofDate_options(h: Long, m: Long, s: Long, date: MemorySegment, opts: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dateBySettingHour:minute:second:ofDate:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, h, m, s, date, opts) as MemorySegment
    }
    
    open fun date_matchesComponents(date: MemorySegment, components: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("date:matchesComponents:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, date, components) as Boolean
    }
    
    // @property currentCalendar
    open fun currentCalendar(): MemorySegment {
        val sel = ObjCRuntime.sel("currentCalendar")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property autoupdatingCurrentCalendar
    open fun autoupdatingCurrentCalendar(): MemorySegment {
        val sel = ObjCRuntime.sel("autoupdatingCurrentCalendar")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property calendarIdentifier
    open fun calendarIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("calendarIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
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
    
    // @property timeZone
    open fun timeZone(): MemorySegment {
        val sel = ObjCRuntime.sel("timeZone")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTimeZone(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTimeZone:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property firstWeekday
    open fun firstWeekday(): Long {
        val sel = ObjCRuntime.sel("firstWeekday")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setFirstWeekday(value: Long) {
        val sel = ObjCRuntime.sel("setFirstWeekday:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property minimumDaysInFirstWeek
    open fun minimumDaysInFirstWeek(): Long {
        val sel = ObjCRuntime.sel("minimumDaysInFirstWeek")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setMinimumDaysInFirstWeek(value: Long) {
        val sel = ObjCRuntime.sel("setMinimumDaysInFirstWeek:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property eraSymbols
    /** @return NSArray<NSString *> * */
    open fun eraSymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("eraSymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property longEraSymbols
    /** @return NSArray<NSString *> * */
    open fun longEraSymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("longEraSymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property monthSymbols
    /** @return NSArray<NSString *> * */
    open fun monthSymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("monthSymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property shortMonthSymbols
    /** @return NSArray<NSString *> * */
    open fun shortMonthSymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("shortMonthSymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property veryShortMonthSymbols
    /** @return NSArray<NSString *> * */
    open fun veryShortMonthSymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("veryShortMonthSymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property standaloneMonthSymbols
    /** @return NSArray<NSString *> * */
    open fun standaloneMonthSymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("standaloneMonthSymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property shortStandaloneMonthSymbols
    /** @return NSArray<NSString *> * */
    open fun shortStandaloneMonthSymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("shortStandaloneMonthSymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property veryShortStandaloneMonthSymbols
    /** @return NSArray<NSString *> * */
    open fun veryShortStandaloneMonthSymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("veryShortStandaloneMonthSymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property weekdaySymbols
    /** @return NSArray<NSString *> * */
    open fun weekdaySymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("weekdaySymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property shortWeekdaySymbols
    /** @return NSArray<NSString *> * */
    open fun shortWeekdaySymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("shortWeekdaySymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property veryShortWeekdaySymbols
    /** @return NSArray<NSString *> * */
    open fun veryShortWeekdaySymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("veryShortWeekdaySymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property standaloneWeekdaySymbols
    /** @return NSArray<NSString *> * */
    open fun standaloneWeekdaySymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("standaloneWeekdaySymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property shortStandaloneWeekdaySymbols
    /** @return NSArray<NSString *> * */
    open fun shortStandaloneWeekdaySymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("shortStandaloneWeekdaySymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property veryShortStandaloneWeekdaySymbols
    /** @return NSArray<NSString *> * */
    open fun veryShortStandaloneWeekdaySymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("veryShortStandaloneWeekdaySymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property quarterSymbols
    /** @return NSArray<NSString *> * */
    open fun quarterSymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("quarterSymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property shortQuarterSymbols
    /** @return NSArray<NSString *> * */
    open fun shortQuarterSymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("shortQuarterSymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property standaloneQuarterSymbols
    /** @return NSArray<NSString *> * */
    open fun standaloneQuarterSymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("standaloneQuarterSymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property shortStandaloneQuarterSymbols
    /** @return NSArray<NSString *> * */
    open fun shortStandaloneQuarterSymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("shortStandaloneQuarterSymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property AMSymbol
    open fun AMSymbol(): MemorySegment {
        val sel = ObjCRuntime.sel("AMSymbol")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun AMSymbolAsString(): String = ObjCRuntime.toJavaString(AMSymbol())
    
    // @property PMSymbol
    open fun PMSymbol(): MemorySegment {
        val sel = ObjCRuntime.sel("PMSymbol")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun PMSymbolAsString(): String = ObjCRuntime.toJavaString(PMSymbol())
    
}

