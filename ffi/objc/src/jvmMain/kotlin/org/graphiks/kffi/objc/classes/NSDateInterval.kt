package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDateInterval
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSDateInterval(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDateInterval") }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun initWithStartDate_duration(startDate: MemorySegment, duration: Double): MemorySegment {
        val sel = ObjCRuntime.sel("initWithStartDate:duration:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, startDate, duration) as MemorySegment
    }
    
    open fun initWithStartDate_endDate(startDate: MemorySegment, endDate: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithStartDate:endDate:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, startDate, endDate) as MemorySegment
    }
    
    open fun compare(dateInterval: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("compare:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, dateInterval) as MemorySegment
    }
    
    open fun isEqualToDateInterval(dateInterval: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isEqualToDateInterval:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, dateInterval) as Boolean
    }
    
    open fun intersectsDateInterval(dateInterval: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("intersectsDateInterval:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, dateInterval) as Boolean
    }
    
    open fun intersectionWithDateInterval(dateInterval: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("intersectionWithDateInterval:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, dateInterval) as MemorySegment
    }
    
    open fun containsDate(date: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("containsDate:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, date) as Boolean
    }
    
    // @property startDate
    open fun startDate(): MemorySegment {
        val sel = ObjCRuntime.sel("startDate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property endDate
    open fun endDate(): MemorySegment {
        val sel = ObjCRuntime.sel("endDate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property duration
    open fun duration(): Double {
        val sel = ObjCRuntime.sel("duration")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
}

