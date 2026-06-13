package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSURLAuthenticationChallenge
 * Superclass: NSObject
 * Protocols: NSSecureCoding
 */
open class NSURLAuthenticationChallenge(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSURLAuthenticationChallenge") }
        
    }
    
    open fun initWithProtectionSpace_proposedCredential_previousFailureCount_failureResponse_error_sender(space: MemorySegment, credential: MemorySegment, previousFailureCount: Long, response: MemorySegment, error: MemorySegment, sender: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithProtectionSpace:proposedCredential:previousFailureCount:failureResponse:error:sender:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, space, credential, previousFailureCount, response, error, sender) as MemorySegment
    }
    
    open fun initWithAuthenticationChallenge_sender(challenge: MemorySegment, sender: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithAuthenticationChallenge:sender:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, challenge, sender) as MemorySegment
    }
    
    // @property protectionSpace
    open fun protectionSpace(): MemorySegment {
        val sel = ObjCRuntime.sel("protectionSpace")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property proposedCredential
    open fun proposedCredential(): MemorySegment {
        val sel = ObjCRuntime.sel("proposedCredential")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property previousFailureCount
    open fun previousFailureCount(): Long {
        val sel = ObjCRuntime.sel("previousFailureCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property failureResponse
    open fun failureResponse(): MemorySegment {
        val sel = ObjCRuntime.sel("failureResponse")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property error
    open fun error(): MemorySegment {
        val sel = ObjCRuntime.sel("error")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property sender
    /** @return id<NSURLAuthenticationChallengeSender> */
    open fun sender(): MemorySegment {
        val sel = ObjCRuntime.sel("sender")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _internal: MemorySegment
}

