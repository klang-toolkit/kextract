package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSURLConnection
 * Superclass: NSObject
 */
open class NSURLConnection(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSURLConnection") }
        
        fun connectionWithRequest_delegate(request: MemorySegment, delegate: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("connectionWithRequest:delegate:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, request, delegate) as MemorySegment
        }
        
        fun canHandleRequest(request: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("canHandleRequest:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, request) as Boolean
        }
        
    }
    
    open fun initWithRequest_delegate_startImmediately(request: MemorySegment, delegate: MemorySegment, startImmediately: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithRequest:delegate:startImmediately:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, request, delegate, startImmediately) as MemorySegment
    }
    
    open fun initWithRequest_delegate(request: MemorySegment, delegate: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithRequest:delegate:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, request, delegate) as MemorySegment
    }
    
    open fun start(): Unit {
        val sel = ObjCRuntime.sel("start")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun cancel(): Unit {
        val sel = ObjCRuntime.sel("cancel")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun scheduleInRunLoop_forMode(aRunLoop: MemorySegment, mode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scheduleInRunLoop:forMode:")
        ObjCRuntime.msgSend(null, ptr, sel, aRunLoop, mode)
    }
    
    open fun unscheduleFromRunLoop_forMode(aRunLoop: MemorySegment, mode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("unscheduleFromRunLoop:forMode:")
        ObjCRuntime.msgSend(null, ptr, sel, aRunLoop, mode)
    }
    
    open fun setDelegateQueue(queue: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setDelegateQueue:")
        ObjCRuntime.msgSend(null, ptr, sel, queue)
    }
    
    // @property originalRequest
    open fun originalRequest(): MemorySegment {
        val sel = ObjCRuntime.sel("originalRequest")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property currentRequest
    open fun currentRequest(): MemorySegment {
        val sel = ObjCRuntime.sel("currentRequest")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _internal: MemorySegment
}

// ── Category: NSURLConnectionSynchronousLoading on NSURLConnection ─────────────────────────────────────────

// Class method: +[NSURLConnection sendSynchronousRequest:returningResponse:error:]
fun NSURLConnection_sendSynchronousRequest_returningResponse_error(request: MemorySegment, response: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sendSynchronousRequest:returningResponse:error:")
    val cls = ObjCRuntime.getClass("NSURLConnection")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, request, response, error) as MemorySegment
}

// ── Category: NSURLConnectionQueuedLoading on NSURLConnection ─────────────────────────────────────────

// Class method: +[NSURLConnection sendAsynchronousRequest:queue:completionHandler:]
fun NSURLConnection_sendAsynchronousRequest_queue_completionHandler(request: MemorySegment, queue: MemorySegment, handler: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("sendAsynchronousRequest:queue:completionHandler:")
    val cls = ObjCRuntime.getClass("NSURLConnection")
    ObjCRuntime.msgSend(null, cls, sel, request, queue, handler)
}

