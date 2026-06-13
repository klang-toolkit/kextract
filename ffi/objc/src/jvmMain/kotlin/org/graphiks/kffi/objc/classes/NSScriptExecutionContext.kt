package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSScriptExecutionContext
 * Superclass: NSObject
 */
open class NSScriptExecutionContext(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSScriptExecutionContext") }
        
        fun sharedScriptExecutionContext(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedScriptExecutionContext")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property topLevelObject
    open fun topLevelObject(): MemorySegment {
        val sel = ObjCRuntime.sel("topLevelObject")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTopLevelObject(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTopLevelObject:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property objectBeingTested
    open fun objectBeingTested(): MemorySegment {
        val sel = ObjCRuntime.sel("objectBeingTested")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setObjectBeingTested(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setObjectBeingTested:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property rangeContainerObject
    open fun rangeContainerObject(): MemorySegment {
        val sel = ObjCRuntime.sel("rangeContainerObject")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRangeContainerObject(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRangeContainerObject:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _topLevelObject: MemorySegment
    // ivar: _objectBeingTested: MemorySegment
    // ivar: _rangeContainerObject: MemorySegment
    // ivar: _moreVars: MemorySegment
}

