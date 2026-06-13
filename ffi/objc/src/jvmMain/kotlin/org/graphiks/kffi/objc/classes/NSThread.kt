package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSThread
 * Superclass: NSObject
 */
open class NSThread(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSThread") }
        
        fun detachNewThreadWithBlock(block: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("detachNewThreadWithBlock:")
            ObjCRuntime.msgSend(null, _class, sel, block)
        }
        
        fun detachNewThreadSelector_toTarget_withObject(selector: MemorySegment, target: MemorySegment, argument: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("detachNewThreadSelector:toTarget:withObject:")
            ObjCRuntime.msgSend(null, _class, sel, selector, target, argument)
        }
        
        fun isMultiThreaded(): Boolean {
            val sel = ObjCRuntime.sel("isMultiThreaded")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun sleepUntilDate(date: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("sleepUntilDate:")
            ObjCRuntime.msgSend(null, _class, sel, date)
        }
        
        fun sleepForTimeInterval(ti: Double): Unit {
            val sel = ObjCRuntime.sel("sleepForTimeInterval:")
            ObjCRuntime.msgSend(null, _class, sel, ti)
        }
        
        fun exit(): Unit {
            val sel = ObjCRuntime.sel("exit")
            ObjCRuntime.msgSend(null, _class, sel)
        }
        
        fun threadPriority(): Double {
            val sel = ObjCRuntime.sel("threadPriority")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, _class, sel) as Double
        }
        
        fun setThreadPriority(p: Double): Boolean {
            val sel = ObjCRuntime.sel("setThreadPriority:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, p) as Boolean
        }
        
        fun currentThread(): MemorySegment {
            val sel = ObjCRuntime.sel("currentThread")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSArray<NSNumber *> * */
        fun callStackReturnAddresses(): MemorySegment {
            val sel = ObjCRuntime.sel("callStackReturnAddresses")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSArray<NSString *> * */
        fun callStackSymbols(): MemorySegment {
            val sel = ObjCRuntime.sel("callStackSymbols")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun isMainThread(): Boolean {
            val sel = ObjCRuntime.sel("isMainThread")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun mainThread(): MemorySegment {
            val sel = ObjCRuntime.sel("mainThread")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithTarget_selector_object(target: MemorySegment, selector: MemorySegment, argument: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTarget:selector:object:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, target, selector, argument) as MemorySegment
    }
    
    open fun initWithBlock(block: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithBlock:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, block) as MemorySegment
    }
    
    open fun cancel(): Unit {
        val sel = ObjCRuntime.sel("cancel")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun start(): Unit {
        val sel = ObjCRuntime.sel("start")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun main(): Unit {
        val sel = ObjCRuntime.sel("main")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property currentThread
    open fun currentThread(): MemorySegment {
        val sel = ObjCRuntime.sel("currentThread")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property threadDictionary
    open fun threadDictionary(): MemorySegment {
        val sel = ObjCRuntime.sel("threadDictionary")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
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
    
    // @property callStackReturnAddresses
    /** @return NSArray<NSNumber *> * */
    open fun callStackReturnAddresses(): MemorySegment {
        val sel = ObjCRuntime.sel("callStackReturnAddresses")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property callStackSymbols
    /** @return NSArray<NSString *> * */
    open fun callStackSymbols(): MemorySegment {
        val sel = ObjCRuntime.sel("callStackSymbols")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
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
    
    // @property stackSize
    open fun stackSize(): Long {
        val sel = ObjCRuntime.sel("stackSize")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setStackSize(value: Long) {
        val sel = ObjCRuntime.sel("setStackSize:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property isMainThread
    open fun isMainThread(): Boolean {
        val sel = ObjCRuntime.sel("isMainThread")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property mainThread
    open fun mainThread(): MemorySegment {
        val sel = ObjCRuntime.sel("mainThread")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
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
    
    // @property cancelled
    open fun isCancelled(): Boolean {
        val sel = ObjCRuntime.sel("isCancelled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _private: MemorySegment
    // ivar: _bytes: MemorySegment
}

