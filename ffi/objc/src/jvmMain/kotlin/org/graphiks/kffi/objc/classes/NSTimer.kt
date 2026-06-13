package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTimer
 * Superclass: NSObject
 */
open class NSTimer(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTimer") }
        
        fun timerWithTimeInterval_invocation_repeats(ti: Double, invocation: MemorySegment, yesOrNo: Boolean): MemorySegment {
            val sel = ObjCRuntime.sel("timerWithTimeInterval:invocation:repeats:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, ti, invocation, yesOrNo) as MemorySegment
        }
        
        fun scheduledTimerWithTimeInterval_invocation_repeats(ti: Double, invocation: MemorySegment, yesOrNo: Boolean): MemorySegment {
            val sel = ObjCRuntime.sel("scheduledTimerWithTimeInterval:invocation:repeats:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, ti, invocation, yesOrNo) as MemorySegment
        }
        
        fun timerWithTimeInterval_target_selector_userInfo_repeats(ti: Double, aTarget: MemorySegment, aSelector: MemorySegment, userInfo: MemorySegment, yesOrNo: Boolean): MemorySegment {
            val sel = ObjCRuntime.sel("timerWithTimeInterval:target:selector:userInfo:repeats:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, ti, aTarget, aSelector, userInfo, yesOrNo) as MemorySegment
        }
        
        fun scheduledTimerWithTimeInterval_target_selector_userInfo_repeats(ti: Double, aTarget: MemorySegment, aSelector: MemorySegment, userInfo: MemorySegment, yesOrNo: Boolean): MemorySegment {
            val sel = ObjCRuntime.sel("scheduledTimerWithTimeInterval:target:selector:userInfo:repeats:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, ti, aTarget, aSelector, userInfo, yesOrNo) as MemorySegment
        }
        
        fun timerWithTimeInterval_repeats_block(interval: Double, repeats: Boolean, block: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("timerWithTimeInterval:repeats:block:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, interval, repeats, block) as MemorySegment
        }
        
        fun scheduledTimerWithTimeInterval_repeats_block(interval: Double, repeats: Boolean, block: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("scheduledTimerWithTimeInterval:repeats:block:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, interval, repeats, block) as MemorySegment
        }
        
    }
    
    open fun initWithFireDate_interval_repeats_block(date: MemorySegment, interval: Double, repeats: Boolean, block: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFireDate:interval:repeats:block:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, date, interval, repeats, block) as MemorySegment
    }
    
    open fun initWithFireDate_interval_target_selector_userInfo_repeats(date: MemorySegment, ti: Double, t: MemorySegment, s: MemorySegment, ui: MemorySegment, rep: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFireDate:interval:target:selector:userInfo:repeats:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, date, ti, t, s, ui, rep) as MemorySegment
    }
    
    open fun fire(): Unit {
        val sel = ObjCRuntime.sel("fire")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun invalidate(): Unit {
        val sel = ObjCRuntime.sel("invalidate")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property fireDate
    open fun fireDate(): MemorySegment {
        val sel = ObjCRuntime.sel("fireDate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFireDate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFireDate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property timeInterval
    open fun timeInterval(): Double {
        val sel = ObjCRuntime.sel("timeInterval")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property tolerance
    open fun tolerance(): Double {
        val sel = ObjCRuntime.sel("tolerance")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setTolerance(value: Double) {
        val sel = ObjCRuntime.sel("setTolerance:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property valid
    open fun isValid(): Boolean {
        val sel = ObjCRuntime.sel("isValid")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property userInfo
    open fun userInfo(): MemorySegment {
        val sel = ObjCRuntime.sel("userInfo")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

