package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSQuitCommand
 * Superclass: NSScriptCommand
 */
open class NSQuitCommand(override val ptr: MemorySegment) : NSScriptCommand(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSQuitCommand") }
        
    }
    
    // @property saveOptions
    open fun saveOptions(): MemorySegment {
        val sel = ObjCRuntime.sel("saveOptions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

