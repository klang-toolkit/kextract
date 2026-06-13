package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSNotificationQueue
 * Superclass: NSObject
 */
open class NSNotificationQueue(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSNotificationQueue") }
        
        fun defaultQueue(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultQueue")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithNotificationCenter(notificationCenter: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithNotificationCenter:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, notificationCenter) as MemorySegment
    }
    
    open fun enqueueNotification_postingStyle(notification: MemorySegment, postingStyle: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enqueueNotification:postingStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, notification, postingStyle)
    }
    
    open fun enqueueNotification_postingStyle_coalesceMask_forModes(notification: MemorySegment, postingStyle: MemorySegment, coalesceMask: MemorySegment, modes: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enqueueNotification:postingStyle:coalesceMask:forModes:")
        ObjCRuntime.msgSend(null, ptr, sel, notification, postingStyle, coalesceMask, modes)
    }
    
    open fun dequeueNotificationsMatching_coalesceMask(notification: MemorySegment, coalesceMask: Long): Unit {
        val sel = ObjCRuntime.sel("dequeueNotificationsMatching:coalesceMask:")
        ObjCRuntime.msgSend(null, ptr, sel, notification, coalesceMask)
    }
    
    // @property defaultQueue
    open fun defaultQueue(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultQueue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

