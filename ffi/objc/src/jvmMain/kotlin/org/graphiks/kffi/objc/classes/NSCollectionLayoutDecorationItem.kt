package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionLayoutDecorationItem
 * Superclass: NSCollectionLayoutItem
 * Protocols: NSCopying
 */
open class NSCollectionLayoutDecorationItem(override val ptr: MemorySegment) : NSCollectionLayoutItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionLayoutDecorationItem") }
        
        fun backgroundDecorationItemWithElementKind(elementKind: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("backgroundDecorationItemWithElementKind:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, elementKind) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun backgroundDecorationItemWithElementKind(elementKind: String): MemorySegment = backgroundDecorationItemWithElementKind(ObjCRuntime.newNSString(Arena.global(), elementKind))
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    override fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property zIndex
    open fun zIndex(): Long {
        val sel = ObjCRuntime.sel("zIndex")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setZIndex(value: Long) {
        val sel = ObjCRuntime.sel("setZIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property elementKind
    open fun elementKind(): MemorySegment {
        val sel = ObjCRuntime.sel("elementKind")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun elementKindAsString(): String = ObjCRuntime.toJavaString(elementKind())
    
}

