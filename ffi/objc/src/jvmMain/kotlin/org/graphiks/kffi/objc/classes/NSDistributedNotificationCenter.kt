package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDistributedNotificationCenter
 * Superclass: NSNotificationCenter
 */
open class NSDistributedNotificationCenter(override val ptr: MemorySegment) : NSNotificationCenter(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDistributedNotificationCenter") }
        
        fun notificationCenterForType(notificationCenterType: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("notificationCenterForType:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, notificationCenterType) as MemorySegment
        }
        
        fun defaultCenter(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultCenter")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun addObserver_selector_name_object_suspensionBehavior(observer: MemorySegment, selector: MemorySegment, name: MemorySegment, `object`: MemorySegment, suspensionBehavior: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addObserver:selector:name:object:suspensionBehavior:")
        ObjCRuntime.msgSend(null, ptr, sel, observer, selector, name, `object`, suspensionBehavior)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun addObserver_selector_name_object_suspensionBehavior(observer: MemorySegment, selector: MemorySegment, name: MemorySegment, `object`: String, suspensionBehavior: MemorySegment): Unit = addObserver_selector_name_object_suspensionBehavior(observer, selector, name, ObjCRuntime.newNSString(Arena.global(), `object`), suspensionBehavior)
    
    open fun postNotificationName_object_userInfo_deliverImmediately(name: MemorySegment, `object`: MemorySegment, userInfo: MemorySegment, deliverImmediately: Boolean): Unit {
        val sel = ObjCRuntime.sel("postNotificationName:object:userInfo:deliverImmediately:")
        ObjCRuntime.msgSend(null, ptr, sel, name, `object`, userInfo, deliverImmediately)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun postNotificationName_object_userInfo_deliverImmediately(name: MemorySegment, `object`: String, userInfo: MemorySegment, deliverImmediately: Boolean): Unit = postNotificationName_object_userInfo_deliverImmediately(name, ObjCRuntime.newNSString(Arena.global(), `object`), userInfo, deliverImmediately)
    
    open fun postNotificationName_object_userInfo_options(name: MemorySegment, `object`: MemorySegment, userInfo: MemorySegment, options: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("postNotificationName:object:userInfo:options:")
        ObjCRuntime.msgSend(null, ptr, sel, name, `object`, userInfo, options)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun postNotificationName_object_userInfo_options(name: MemorySegment, `object`: String, userInfo: MemorySegment, options: MemorySegment): Unit = postNotificationName_object_userInfo_options(name, ObjCRuntime.newNSString(Arena.global(), `object`), userInfo, options)
    
    override fun addObserver_selector_name_object(observer: MemorySegment, aSelector: MemorySegment, aName: MemorySegment, anObject: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addObserver:selector:name:object:")
        ObjCRuntime.msgSend(null, ptr, sel, observer, aSelector, aName, anObject)
    }
    
    override fun postNotificationName_object(aName: MemorySegment, anObject: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("postNotificationName:object:")
        ObjCRuntime.msgSend(null, ptr, sel, aName, anObject)
    }
    
    override fun postNotificationName_object_userInfo(aName: MemorySegment, anObject: MemorySegment, aUserInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("postNotificationName:object:userInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, aName, anObject, aUserInfo)
    }
    
    override fun removeObserver_name_object(observer: MemorySegment, aName: MemorySegment, anObject: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeObserver:name:object:")
        ObjCRuntime.msgSend(null, ptr, sel, observer, aName, anObject)
    }
    
    // @property suspended
    open fun suspended(): Boolean {
        val sel = ObjCRuntime.sel("suspended")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSuspended(value: Boolean) {
        val sel = ObjCRuntime.sel("setSuspended:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

