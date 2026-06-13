package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSValueTransformer
 * Superclass: NSObject
 */
open class NSValueTransformer(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSValueTransformer") }
        
        fun setValueTransformer_forName(transformer: MemorySegment, name: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("setValueTransformer:forName:")
            ObjCRuntime.msgSend(null, _class, sel, transformer, name)
        }
        
        fun valueTransformerForName(name: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("valueTransformerForName:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name) as MemorySegment
        }
        
        /** @return NSArray<NSValueTransformerName> * */
        fun valueTransformerNames(): MemorySegment {
            val sel = ObjCRuntime.sel("valueTransformerNames")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun transformedValueClass(): MemorySegment {
            val sel = ObjCRuntime.sel("transformedValueClass")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun allowsReverseTransformation(): Boolean {
            val sel = ObjCRuntime.sel("allowsReverseTransformation")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
    }
    
    open fun transformedValue(value: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("transformedValue:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun reverseTransformedValue(value: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("reverseTransformedValue:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
}

