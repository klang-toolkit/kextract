package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPredicate
 * Superclass: NSObject
 * Protocols: NSSecureCoding, NSCopying
 */
open class NSPredicate(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPredicate") }
        
        fun predicateWithFormat_argumentArray(predicateFormat: MemorySegment, arguments: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("predicateWithFormat:argumentArray:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, predicateFormat, arguments) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun predicateWithFormat_argumentArray(predicateFormat: String, arguments: MemorySegment): MemorySegment = predicateWithFormat_argumentArray(ObjCRuntime.newNSString(Arena.global(), predicateFormat), arguments)
        
        fun predicateWithFormat(predicateFormat: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("predicateWithFormat:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, predicateFormat) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun predicateWithFormat(predicateFormat: String): MemorySegment = predicateWithFormat(ObjCRuntime.newNSString(Arena.global(), predicateFormat))
        
        fun predicateWithFormat_arguments(predicateFormat: MemorySegment, argList: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("predicateWithFormat:arguments:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, predicateFormat, argList) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun predicateWithFormat_arguments(predicateFormat: String, argList: MemorySegment): MemorySegment = predicateWithFormat_arguments(ObjCRuntime.newNSString(Arena.global(), predicateFormat), argList)
        
        fun predicateFromMetadataQueryString(queryString: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("predicateFromMetadataQueryString:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, queryString) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun predicateFromMetadataQueryString(queryString: String): MemorySegment = predicateFromMetadataQueryString(ObjCRuntime.newNSString(Arena.global(), queryString))
        
        fun predicateWithValue(value: Boolean): MemorySegment {
            val sel = ObjCRuntime.sel("predicateWithValue:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, value) as MemorySegment
        }
        
        fun predicateWithBlock(block: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("predicateWithBlock:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, block) as MemorySegment
        }
        
    }
    
    open fun predicateWithSubstitutionVariables(variables: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("predicateWithSubstitutionVariables:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, variables) as MemorySegment
    }
    
    open fun evaluateWithObject(`object`: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("evaluateWithObject:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, `object`) as Boolean
    }
    
    open fun evaluateWithObject_substitutionVariables(`object`: MemorySegment, bindings: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("evaluateWithObject:substitutionVariables:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, `object`, bindings) as Boolean
    }
    
    open fun allowEvaluation(): Unit {
        val sel = ObjCRuntime.sel("allowEvaluation")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property predicateFormat
    open fun predicateFormat(): MemorySegment {
        val sel = ObjCRuntime.sel("predicateFormat")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun predicateFormatAsString(): String = ObjCRuntime.toJavaString(predicateFormat())
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _predicateFlags: MemorySegment
    // ivar: reserved: Int
}

