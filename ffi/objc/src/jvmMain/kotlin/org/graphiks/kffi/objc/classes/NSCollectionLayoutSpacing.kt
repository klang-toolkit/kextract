package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionLayoutSpacing
 * Superclass: NSObject
 * Protocols: NSCopying
 */
open class NSCollectionLayoutSpacing(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionLayoutSpacing") }
        
        fun flexibleSpacing(flexibleSpacing: Double): MemorySegment {
            val sel = ObjCRuntime.sel("flexibleSpacing:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, flexibleSpacing) as MemorySegment
        }
        
        fun fixedSpacing(fixedSpacing: Double): MemorySegment {
            val sel = ObjCRuntime.sel("fixedSpacing:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fixedSpacing) as MemorySegment
        }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property spacing
    open fun spacing(): Double {
        val sel = ObjCRuntime.sel("spacing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property isFlexibleSpacing
    open fun isFlexibleSpacing(): Boolean {
        val sel = ObjCRuntime.sel("isFlexibleSpacing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property isFixedSpacing
    open fun isFixedSpacing(): Boolean {
        val sel = ObjCRuntime.sel("isFixedSpacing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

