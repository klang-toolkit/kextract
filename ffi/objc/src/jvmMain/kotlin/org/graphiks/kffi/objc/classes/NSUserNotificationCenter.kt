package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUserNotificationCenter
 * Superclass: NSObject
 */
open class NSUserNotificationCenter(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUserNotificationCenter") }
        
        fun defaultUserNotificationCenter(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultUserNotificationCenter")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun scheduleNotification(notification: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scheduleNotification:")
        ObjCRuntime.msgSend(null, ptr, sel, notification)
    }
    
    open fun removeScheduledNotification(notification: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeScheduledNotification:")
        ObjCRuntime.msgSend(null, ptr, sel, notification)
    }
    
    open fun deliverNotification(notification: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("deliverNotification:")
        ObjCRuntime.msgSend(null, ptr, sel, notification)
    }
    
    open fun removeDeliveredNotification(notification: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeDeliveredNotification:")
        ObjCRuntime.msgSend(null, ptr, sel, notification)
    }
    
    open fun removeAllDeliveredNotifications(): Unit {
        val sel = ObjCRuntime.sel("removeAllDeliveredNotifications")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property defaultUserNotificationCenter
    open fun defaultUserNotificationCenter(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultUserNotificationCenter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property delegate
    /** @return id<NSUserNotificationCenterDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property scheduledNotifications
    /** @return NSArray<NSUserNotification *> * */
    open fun scheduledNotifications(): MemorySegment {
        val sel = ObjCRuntime.sel("scheduledNotifications")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setScheduledNotifications(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setScheduledNotifications:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property deliveredNotifications
    /** @return NSArray<NSUserNotification *> * */
    open fun deliveredNotifications(): MemorySegment {
        val sel = ObjCRuntime.sel("deliveredNotifications")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _internal: MemorySegment
}

