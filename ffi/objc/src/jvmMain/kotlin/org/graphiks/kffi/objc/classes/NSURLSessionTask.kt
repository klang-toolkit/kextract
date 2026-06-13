package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSURLSessionTask
 * Superclass: NSObject
 * Protocols: NSCopying, NSProgressReporting
 */
open class NSURLSessionTask(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSURLSessionTask") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun cancel(): Unit {
        val sel = ObjCRuntime.sel("cancel")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun suspend(): Unit {
        val sel = ObjCRuntime.sel("suspend")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun resume(): Unit {
        val sel = ObjCRuntime.sel("resume")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property taskIdentifier
    open fun taskIdentifier(): Long {
        val sel = ObjCRuntime.sel("taskIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
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
    
    // @property response
    open fun response(): MemorySegment {
        val sel = ObjCRuntime.sel("response")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property delegate
    /** @return id<NSURLSessionTaskDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property progress
    open fun progress(): MemorySegment {
        val sel = ObjCRuntime.sel("progress")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property earliestBeginDate
    open fun earliestBeginDate(): MemorySegment {
        val sel = ObjCRuntime.sel("earliestBeginDate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setEarliestBeginDate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setEarliestBeginDate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property countOfBytesClientExpectsToSend
    open fun countOfBytesClientExpectsToSend(): Long {
        val sel = ObjCRuntime.sel("countOfBytesClientExpectsToSend")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setCountOfBytesClientExpectsToSend(value: Long) {
        val sel = ObjCRuntime.sel("setCountOfBytesClientExpectsToSend:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property countOfBytesClientExpectsToReceive
    open fun countOfBytesClientExpectsToReceive(): Long {
        val sel = ObjCRuntime.sel("countOfBytesClientExpectsToReceive")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setCountOfBytesClientExpectsToReceive(value: Long) {
        val sel = ObjCRuntime.sel("setCountOfBytesClientExpectsToReceive:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property countOfBytesSent
    open fun countOfBytesSent(): Long {
        val sel = ObjCRuntime.sel("countOfBytesSent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property countOfBytesReceived
    open fun countOfBytesReceived(): Long {
        val sel = ObjCRuntime.sel("countOfBytesReceived")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property countOfBytesExpectedToSend
    open fun countOfBytesExpectedToSend(): Long {
        val sel = ObjCRuntime.sel("countOfBytesExpectedToSend")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property countOfBytesExpectedToReceive
    open fun countOfBytesExpectedToReceive(): Long {
        val sel = ObjCRuntime.sel("countOfBytesExpectedToReceive")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property taskDescription
    open fun taskDescription(): MemorySegment {
        val sel = ObjCRuntime.sel("taskDescription")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTaskDescription(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTaskDescription:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun taskDescriptionAsString(): String = ObjCRuntime.toJavaString(taskDescription())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setTaskDescription(value: String) = setTaskDescription(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property state
    open fun state(): MemorySegment {
        val sel = ObjCRuntime.sel("state")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property error
    open fun error(): MemorySegment {
        val sel = ObjCRuntime.sel("error")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property priority
    open fun priority(): Float {
        val sel = ObjCRuntime.sel("priority")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    open fun setPriority(value: Float) {
        val sel = ObjCRuntime.sel("setPriority:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property prefersIncrementalDelivery
    open fun prefersIncrementalDelivery(): Boolean {
        val sel = ObjCRuntime.sel("prefersIncrementalDelivery")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPrefersIncrementalDelivery(value: Boolean) {
        val sel = ObjCRuntime.sel("setPrefersIncrementalDelivery:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

