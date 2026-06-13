package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPort
 * Superclass: NSObject
 * Protocols: NSCopying, NSCoding
 */
open class NSPort(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPort") }
        
        fun port(): MemorySegment {
            val sel = ObjCRuntime.sel("port")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun invalidate(): Unit {
        val sel = ObjCRuntime.sel("invalidate")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun setDelegate(anObject: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, anObject)
    }
    
    /** @return id<NSPortDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun scheduleInRunLoop_forMode(runLoop: MemorySegment, mode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scheduleInRunLoop:forMode:")
        ObjCRuntime.msgSend(null, ptr, sel, runLoop, mode)
    }
    
    open fun removeFromRunLoop_forMode(runLoop: MemorySegment, mode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeFromRunLoop:forMode:")
        ObjCRuntime.msgSend(null, ptr, sel, runLoop, mode)
    }
    
    open fun sendBeforeDate_components_from_reserved(limitDate: MemorySegment, components: MemorySegment, receivePort: MemorySegment, headerSpaceReserved: Long): Boolean {
        val sel = ObjCRuntime.sel("sendBeforeDate:components:from:reserved:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, limitDate, components, receivePort, headerSpaceReserved) as Boolean
    }
    
    open fun sendBeforeDate_msgid_components_from_reserved(limitDate: MemorySegment, msgID: Long, components: MemorySegment, receivePort: MemorySegment, headerSpaceReserved: Long): Boolean {
        val sel = ObjCRuntime.sel("sendBeforeDate:msgid:components:from:reserved:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, limitDate, msgID, components, receivePort, headerSpaceReserved) as Boolean
    }
    
    open fun addConnection_toRunLoop_forMode(conn: MemorySegment, runLoop: MemorySegment, mode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addConnection:toRunLoop:forMode:")
        ObjCRuntime.msgSend(null, ptr, sel, conn, runLoop, mode)
    }
    
    open fun removeConnection_fromRunLoop_forMode(conn: MemorySegment, runLoop: MemorySegment, mode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeConnection:fromRunLoop:forMode:")
        ObjCRuntime.msgSend(null, ptr, sel, conn, runLoop, mode)
    }
    
    // @property valid
    open fun isValid(): Boolean {
        val sel = ObjCRuntime.sel("isValid")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property reservedSpaceLength
    open fun reservedSpaceLength(): Long {
        val sel = ObjCRuntime.sel("reservedSpaceLength")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
}

