package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDataDetector
 * Superclass: NSRegularExpression
 */
open class NSDataDetector(override val ptr: MemorySegment) : NSRegularExpression(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDataDetector") }
        
        fun dataDetectorWithTypes_error(checkingTypes: Long, error: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("dataDetectorWithTypes:error:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, checkingTypes, error) as MemorySegment
        }
        
    }
    
    open fun initWithTypes_error(checkingTypes: Long, error: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTypes:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, checkingTypes, error) as MemorySegment
    }
    
    // @property checkingTypes
    open fun checkingTypes(): Long {
        val sel = ObjCRuntime.sel("checkingTypes")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _types: Long
}

