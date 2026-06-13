package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUserUnixTask
 * Superclass: NSUserScriptTask
 */
open class NSUserUnixTask(override val ptr: MemorySegment) : NSUserScriptTask(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUserUnixTask") }
        
    }
    
    open fun executeWithArguments_completionHandler(arguments: MemorySegment, handler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("executeWithArguments:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, arguments, handler)
    }
    
    // @property standardInput
    open fun standardInput(): MemorySegment {
        val sel = ObjCRuntime.sel("standardInput")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setStandardInput(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setStandardInput:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property standardOutput
    open fun standardOutput(): MemorySegment {
        val sel = ObjCRuntime.sel("standardOutput")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setStandardOutput(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setStandardOutput:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property standardError
    open fun standardError(): MemorySegment {
        val sel = ObjCRuntime.sel("standardError")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setStandardError(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setStandardError:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

