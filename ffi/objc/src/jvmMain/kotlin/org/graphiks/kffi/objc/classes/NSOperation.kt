package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSOperation
 * Superclass: NSObject
 */
open class NSOperation(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSOperation") }
        
    }
    
    open fun start(): Unit {
        val sel = ObjCRuntime.sel("start")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun main(): Unit {
        val sel = ObjCRuntime.sel("main")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun cancel(): Unit {
        val sel = ObjCRuntime.sel("cancel")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun addDependency(op: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addDependency:")
        ObjCRuntime.msgSend(null, ptr, sel, op)
    }
    
    open fun removeDependency(op: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeDependency:")
        ObjCRuntime.msgSend(null, ptr, sel, op)
    }
    
    open fun waitUntilFinished(): Unit {
        val sel = ObjCRuntime.sel("waitUntilFinished")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property cancelled
    open fun isCancelled(): Boolean {
        val sel = ObjCRuntime.sel("isCancelled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property executing
    open fun isExecuting(): Boolean {
        val sel = ObjCRuntime.sel("isExecuting")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property finished
    open fun isFinished(): Boolean {
        val sel = ObjCRuntime.sel("isFinished")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property concurrent
    open fun isConcurrent(): Boolean {
        val sel = ObjCRuntime.sel("isConcurrent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property asynchronous
    open fun isAsynchronous(): Boolean {
        val sel = ObjCRuntime.sel("isAsynchronous")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property ready
    open fun isReady(): Boolean {
        val sel = ObjCRuntime.sel("isReady")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property dependencies
    /** @return NSArray<NSOperation *> * */
    open fun dependencies(): MemorySegment {
        val sel = ObjCRuntime.sel("dependencies")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property queuePriority
    open fun queuePriority(): MemorySegment {
        val sel = ObjCRuntime.sel("queuePriority")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setQueuePriority(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setQueuePriority:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property completionBlock
    open fun completionBlock(): MemorySegment {
        val sel = ObjCRuntime.sel("completionBlock")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCompletionBlock(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCompletionBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property threadPriority
    open fun threadPriority(): Double {
        val sel = ObjCRuntime.sel("threadPriority")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setThreadPriority(value: Double) {
        val sel = ObjCRuntime.sel("setThreadPriority:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property qualityOfService
    open fun qualityOfService(): MemorySegment {
        val sel = ObjCRuntime.sel("qualityOfService")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setQualityOfService(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setQualityOfService:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property name
    open fun name(): MemorySegment {
        val sel = ObjCRuntime.sel("name")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun nameAsString(): String = ObjCRuntime.toJavaString(name())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setName(value: String) = setName(ObjCRuntime.newNSString(Arena.global(), value))
    
}

