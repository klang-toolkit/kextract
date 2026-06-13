package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSProtocolChecker
 * Superclass: NSProxy
 */
open class NSProtocolChecker(override val ptr: MemorySegment) : NSProxy(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSProtocolChecker") }
        
    }
    
    // @property protocol
    open fun protocol(): MemorySegment {
        val sel = ObjCRuntime.sel("protocol")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property target
    open fun target(): MemorySegment {
        val sel = ObjCRuntime.sel("target")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSProtocolCheckerCreation on NSProtocolChecker ─────────────────────────────────────────

fun NSProtocolChecker.initWithTarget_protocol(anObject: MemorySegment, aProtocol: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithTarget:protocol:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, anObject, aProtocol) as MemorySegment
}

// Class method: +[NSProtocolChecker protocolCheckerWithTarget:protocol:]
fun NSProtocolChecker_protocolCheckerWithTarget_protocol(anObject: MemorySegment, aProtocol: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("protocolCheckerWithTarget:protocol:")
    val cls = ObjCRuntime.getClass("NSProtocolChecker")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, anObject, aProtocol) as MemorySegment
}

