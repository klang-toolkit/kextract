package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCreateCommand
 * Superclass: NSScriptCommand
 */
open class NSCreateCommand(override val ptr: MemorySegment) : NSScriptCommand(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCreateCommand") }
        
    }
    
    // @property createClassDescription
    open fun createClassDescription(): MemorySegment {
        val sel = ObjCRuntime.sel("createClassDescription")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property resolvedKeyDictionary
    /** @return NSDictionary<NSString *,id> * */
    open fun resolvedKeyDictionary(): MemorySegment {
        val sel = ObjCRuntime.sel("resolvedKeyDictionary")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _moreVars2: MemorySegment
}

