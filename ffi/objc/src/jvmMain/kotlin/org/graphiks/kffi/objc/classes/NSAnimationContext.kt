package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAnimationContext
 * Superclass: NSObject
 */
open class NSAnimationContext(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAnimationContext") }
        
        fun runAnimationGroup_completionHandler(changes: MemorySegment, completionHandler: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("runAnimationGroup:completionHandler:")
            ObjCRuntime.msgSend(null, _class, sel, changes, completionHandler)
        }
        
        fun runAnimationGroup(changes: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("runAnimationGroup:")
            ObjCRuntime.msgSend(null, _class, sel, changes)
        }
        
        fun beginGrouping(): Unit {
            val sel = ObjCRuntime.sel("beginGrouping")
            ObjCRuntime.msgSend(null, _class, sel)
        }
        
        fun endGrouping(): Unit {
            val sel = ObjCRuntime.sel("endGrouping")
            ObjCRuntime.msgSend(null, _class, sel)
        }
        
        fun currentContext(): MemorySegment {
            val sel = ObjCRuntime.sel("currentContext")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property currentContext
    open fun currentContext(): MemorySegment {
        val sel = ObjCRuntime.sel("currentContext")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property duration
    open fun duration(): Double {
        val sel = ObjCRuntime.sel("duration")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setDuration(value: Double) {
        val sel = ObjCRuntime.sel("setDuration:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property timingFunction
    open fun timingFunction(): MemorySegment {
        val sel = ObjCRuntime.sel("timingFunction")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTimingFunction(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTimingFunction:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property completionHandler
    open fun completionHandler(): MemorySegment {
        val sel = ObjCRuntime.sel("completionHandler")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCompletionHandler(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsImplicitAnimation
    open fun allowsImplicitAnimation(): Boolean {
        val sel = ObjCRuntime.sel("allowsImplicitAnimation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsImplicitAnimation(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsImplicitAnimation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

