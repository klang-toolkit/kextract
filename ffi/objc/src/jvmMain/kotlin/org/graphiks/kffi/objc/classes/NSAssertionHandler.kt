package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAssertionHandler
 * Superclass: NSObject
 */
open class NSAssertionHandler(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAssertionHandler") }
        
        fun currentHandler(): MemorySegment {
            val sel = ObjCRuntime.sel("currentHandler")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun handleFailureInMethod_object_file_lineNumber_description(selector: MemorySegment, `object`: MemorySegment, fileName: MemorySegment, line: Long, format: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("handleFailureInMethod:object:file:lineNumber:description:")
        ObjCRuntime.msgSend(null, ptr, sel, selector, `object`, fileName, line, format)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun handleFailureInMethod_object_file_lineNumber_description(selector: MemorySegment, `object`: MemorySegment, fileName: String, line: Long, format: String): Unit = handleFailureInMethod_object_file_lineNumber_description(selector, `object`, ObjCRuntime.newNSString(Arena.global(), fileName), line, ObjCRuntime.newNSString(Arena.global(), format))
    
    open fun handleFailureInFunction_file_lineNumber_description(functionName: MemorySegment, fileName: MemorySegment, line: Long, format: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("handleFailureInFunction:file:lineNumber:description:")
        ObjCRuntime.msgSend(null, ptr, sel, functionName, fileName, line, format)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun handleFailureInFunction_file_lineNumber_description(functionName: String, fileName: String, line: Long, format: String): Unit = handleFailureInFunction_file_lineNumber_description(ObjCRuntime.newNSString(Arena.global(), functionName), ObjCRuntime.newNSString(Arena.global(), fileName), line, ObjCRuntime.newNSString(Arena.global(), format))
    
    // @property currentHandler
    open fun currentHandler(): MemorySegment {
        val sel = ObjCRuntime.sel("currentHandler")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

