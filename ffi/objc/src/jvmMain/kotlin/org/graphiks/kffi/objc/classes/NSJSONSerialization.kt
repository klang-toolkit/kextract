package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSJSONSerialization
 * Superclass: NSObject
 */
open class NSJSONSerialization(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSJSONSerialization") }
        
        fun isValidJSONObject(obj: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("isValidJSONObject:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, obj) as Boolean
        }
        
        fun dataWithJSONObject_options_error(obj: MemorySegment, opt: MemorySegment, error: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("dataWithJSONObject:options:error:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, obj, opt, error) as MemorySegment
        }
        
        fun JSONObjectWithData_options_error(`data`: MemorySegment, opt: MemorySegment, error: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("JSONObjectWithData:options:error:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, `data`, opt, error) as MemorySegment
        }
        
        fun writeJSONObject_toStream_options_error(obj: MemorySegment, stream: MemorySegment, opt: MemorySegment, error: MemorySegment): Long {
            val sel = ObjCRuntime.sel("writeJSONObject:toStream:options:error:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, _class, sel, obj, stream, opt, error) as Long
        }
        
        fun JSONObjectWithStream_options_error(stream: MemorySegment, opt: MemorySegment, error: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("JSONObjectWithStream:options:error:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, stream, opt, error) as MemorySegment
        }
        
    }
    
}

