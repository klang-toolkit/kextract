package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSClassDescription
 * Superclass: NSObject
 */
open class NSClassDescription(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSClassDescription") }
        
        fun registerClassDescription_forClass(description: MemorySegment, aClass: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("registerClassDescription:forClass:")
            ObjCRuntime.msgSend(null, _class, sel, description, aClass)
        }
        
        fun invalidateClassDescriptionCache(): Unit {
            val sel = ObjCRuntime.sel("invalidateClassDescriptionCache")
            ObjCRuntime.msgSend(null, _class, sel)
        }
        
        fun classDescriptionForClass(aClass: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("classDescriptionForClass:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, aClass) as MemorySegment
        }
        
    }
    
    open fun inverseForRelationshipKey(relationshipKey: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("inverseForRelationshipKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, relationshipKey) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun inverseForRelationshipKeyAsString(relationshipKey: MemorySegment): String = ObjCRuntime.toJavaString(inverseForRelationshipKey(relationshipKey))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun inverseForRelationshipKey(relationshipKey: String): MemorySegment = inverseForRelationshipKey(ObjCRuntime.newNSString(Arena.global(), relationshipKey))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun inverseForRelationshipKeyAsString(relationshipKey: String): String = ObjCRuntime.toJavaString(inverseForRelationshipKey(ObjCRuntime.newNSString(Arena.global(), relationshipKey)))
    
    // @property attributeKeys
    /** @return NSArray<NSString *> * */
    open fun attributeKeys(): MemorySegment {
        val sel = ObjCRuntime.sel("attributeKeys")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property toOneRelationshipKeys
    /** @return NSArray<NSString *> * */
    open fun toOneRelationshipKeys(): MemorySegment {
        val sel = ObjCRuntime.sel("toOneRelationshipKeys")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property toManyRelationshipKeys
    /** @return NSArray<NSString *> * */
    open fun toManyRelationshipKeys(): MemorySegment {
        val sel = ObjCRuntime.sel("toManyRelationshipKeys")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

