package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSecureUnarchiveFromDataTransformer
 * Superclass: NSValueTransformer
 */
open class NSSecureUnarchiveFromDataTransformer(override val ptr: MemorySegment) : NSValueTransformer(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSecureUnarchiveFromDataTransformer") }
        
        /** @return NSArray<Class> * */
        fun allowedTopLevelClasses(): MemorySegment {
            val sel = ObjCRuntime.sel("allowedTopLevelClasses")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property allowedTopLevelClasses
    /** @return NSArray<Class> * */
    open fun allowedTopLevelClasses(): MemorySegment {
        val sel = ObjCRuntime.sel("allowedTopLevelClasses")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

