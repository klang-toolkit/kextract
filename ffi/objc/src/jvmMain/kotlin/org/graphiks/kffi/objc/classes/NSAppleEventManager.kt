package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAppleEventManager
 * Superclass: NSObject
 */
open class NSAppleEventManager(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAppleEventManager") }
        
        fun sharedAppleEventManager(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedAppleEventManager")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun setEventHandler_andSelector_forEventClass_andEventID(handler: MemorySegment, handleEventSelector: MemorySegment, eventClass: Int, eventID: Int): Unit {
        val sel = ObjCRuntime.sel("setEventHandler:andSelector:forEventClass:andEventID:")
        ObjCRuntime.msgSend(null, ptr, sel, handler, handleEventSelector, eventClass, eventID)
    }
    
    open fun removeEventHandlerForEventClass_andEventID(eventClass: Int, eventID: Int): Unit {
        val sel = ObjCRuntime.sel("removeEventHandlerForEventClass:andEventID:")
        ObjCRuntime.msgSend(null, ptr, sel, eventClass, eventID)
    }
    
    open fun dispatchRawAppleEvent_withRawReply_handlerRefCon(theAppleEvent: MemorySegment, theReply: MemorySegment, handlerRefCon: MemorySegment): Short {
        val sel = ObjCRuntime.sel("dispatchRawAppleEvent:withRawReply:handlerRefCon:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_SHORT, ptr, sel, theAppleEvent, theReply, handlerRefCon) as Short
    }
    
    open fun suspendCurrentAppleEvent(): MemorySegment {
        val sel = ObjCRuntime.sel("suspendCurrentAppleEvent")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun appleEventForSuspensionID(suspensionID: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("appleEventForSuspensionID:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, suspensionID) as MemorySegment
    }
    
    open fun replyAppleEventForSuspensionID(suspensionID: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("replyAppleEventForSuspensionID:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, suspensionID) as MemorySegment
    }
    
    open fun setCurrentAppleEventAndReplyEventWithSuspensionID(suspensionID: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setCurrentAppleEventAndReplyEventWithSuspensionID:")
        ObjCRuntime.msgSend(null, ptr, sel, suspensionID)
    }
    
    open fun resumeWithSuspensionID(suspensionID: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("resumeWithSuspensionID:")
        ObjCRuntime.msgSend(null, ptr, sel, suspensionID)
    }
    
    // @property currentAppleEvent
    open fun currentAppleEvent(): MemorySegment {
        val sel = ObjCRuntime.sel("currentAppleEvent")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property currentReplyAppleEvent
    open fun currentReplyAppleEvent(): MemorySegment {
        val sel = ObjCRuntime.sel("currentReplyAppleEvent")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _isPreparedForDispatch: Boolean
    // ivar: _padding: MemorySegment
}

