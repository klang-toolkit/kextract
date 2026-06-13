package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUserAppleScriptTask
 * Superclass: NSUserScriptTask
 */
open class NSUserAppleScriptTask(override val ptr: MemorySegment) : NSUserScriptTask(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUserAppleScriptTask") }
        
    }
    
    open fun executeWithAppleEvent_completionHandler(event: MemorySegment, handler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("executeWithAppleEvent:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, event, handler)
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _isParentDefaultTarget: Boolean
}

