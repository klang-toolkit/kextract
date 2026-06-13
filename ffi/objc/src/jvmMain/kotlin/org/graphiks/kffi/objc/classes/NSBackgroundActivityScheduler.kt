package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSBackgroundActivityScheduler
 * Superclass: NSObject
 */
open class NSBackgroundActivityScheduler(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSBackgroundActivityScheduler") }
        
    }
    
    open fun initWithIdentifier(identifier: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, identifier) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithIdentifier(identifier: String): MemorySegment = initWithIdentifier(ObjCRuntime.newNSString(Arena.global(), identifier))
    
    open fun scheduleWithBlock(block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scheduleWithBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, block)
    }
    
    open fun invalidate(): Unit {
        val sel = ObjCRuntime.sel("invalidate")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property identifier
    open fun identifier(): MemorySegment {
        val sel = ObjCRuntime.sel("identifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun identifierAsString(): String = ObjCRuntime.toJavaString(identifier())
    
    // @property qualityOfService
    open fun qualityOfService(): MemorySegment {
        val sel = ObjCRuntime.sel("qualityOfService")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setQualityOfService(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setQualityOfService:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property repeats
    open fun repeats(): Boolean {
        val sel = ObjCRuntime.sel("repeats")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setRepeats(value: Boolean) {
        val sel = ObjCRuntime.sel("setRepeats:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property interval
    open fun interval(): Double {
        val sel = ObjCRuntime.sel("interval")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setInterval(value: Double) {
        val sel = ObjCRuntime.sel("setInterval:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tolerance
    open fun tolerance(): Double {
        val sel = ObjCRuntime.sel("tolerance")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setTolerance(value: Double) {
        val sel = ObjCRuntime.sel("setTolerance:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property shouldDefer
    open fun shouldDefer(): Boolean {
        val sel = ObjCRuntime.sel("shouldDefer")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

