package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCloneCommand
 * Superclass: NSScriptCommand
 */
open class NSCloneCommand(override val ptr: MemorySegment) : NSScriptCommand(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCloneCommand") }
        
    }
    
    override fun setReceiversSpecifier(receiversRef: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setReceiversSpecifier:")
        ObjCRuntime.msgSend(null, ptr, sel, receiversRef)
    }
    
    // @property keySpecifier
    open fun keySpecifier(): MemorySegment {
        val sel = ObjCRuntime.sel("keySpecifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _keySpecifier: MemorySegment
}

