package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPropertyListSerialization
 * Superclass: NSObject
 */
open class NSPropertyListSerialization(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPropertyListSerialization") }
        
        fun propertyList_isValidForFormat(plist: MemorySegment, format: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("propertyList:isValidForFormat:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, plist, format) as Boolean
        }
        
        fun dataWithPropertyList_format_options_error(plist: MemorySegment, format: MemorySegment, opt: Long, error: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("dataWithPropertyList:format:options:error:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, plist, format, opt, error) as MemorySegment
        }
        
        fun writePropertyList_toStream_format_options_error(plist: MemorySegment, stream: MemorySegment, format: MemorySegment, opt: Long, error: MemorySegment): Long {
            val sel = ObjCRuntime.sel("writePropertyList:toStream:format:options:error:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, _class, sel, plist, stream, format, opt, error) as Long
        }
        
        fun propertyListWithData_options_format_error(`data`: MemorySegment, opt: MemorySegment, format: MemorySegment, error: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("propertyListWithData:options:format:error:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, `data`, opt, format, error) as MemorySegment
        }
        
        fun propertyListWithStream_options_format_error(stream: MemorySegment, opt: MemorySegment, format: MemorySegment, error: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("propertyListWithStream:options:format:error:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, stream, opt, format, error) as MemorySegment
        }
        
        fun dataFromPropertyList_format_errorDescription(plist: MemorySegment, format: MemorySegment, errorString: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("dataFromPropertyList:format:errorDescription:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, plist, format, errorString) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun dataFromPropertyList_format_errorDescription(plist: MemorySegment, format: MemorySegment, errorString: String): MemorySegment = dataFromPropertyList_format_errorDescription(plist, format, ObjCRuntime.newNSString(Arena.global(), errorString))
        
        fun propertyListFromData_mutabilityOption_format_errorDescription(`data`: MemorySegment, opt: MemorySegment, format: MemorySegment, errorString: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("propertyListFromData:mutabilityOption:format:errorDescription:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, `data`, opt, format, errorString) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun propertyListFromData_mutabilityOption_format_errorDescription(`data`: MemorySegment, opt: MemorySegment, format: MemorySegment, errorString: String): MemorySegment = propertyListFromData_mutabilityOption_format_errorDescription(`data`, opt, format, ObjCRuntime.newNSString(Arena.global(), errorString))
        
    }
    
}

