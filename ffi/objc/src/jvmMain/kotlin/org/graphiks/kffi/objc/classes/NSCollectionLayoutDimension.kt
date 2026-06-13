package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionLayoutDimension
 * Superclass: NSObject
 * Protocols: NSCopying
 */
open class NSCollectionLayoutDimension(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionLayoutDimension") }
        
        fun fractionalWidthDimension(fractionalWidth: Double): MemorySegment {
            val sel = ObjCRuntime.sel("fractionalWidthDimension:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fractionalWidth) as MemorySegment
        }
        
        fun fractionalHeightDimension(fractionalHeight: Double): MemorySegment {
            val sel = ObjCRuntime.sel("fractionalHeightDimension:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fractionalHeight) as MemorySegment
        }
        
        fun absoluteDimension(absoluteDimension: Double): MemorySegment {
            val sel = ObjCRuntime.sel("absoluteDimension:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, absoluteDimension) as MemorySegment
        }
        
        fun estimatedDimension(estimatedDimension: Double): MemorySegment {
            val sel = ObjCRuntime.sel("estimatedDimension:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, estimatedDimension) as MemorySegment
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
    
    // @property isFractionalWidth
    open fun isFractionalWidth(): Boolean {
        val sel = ObjCRuntime.sel("isFractionalWidth")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property isFractionalHeight
    open fun isFractionalHeight(): Boolean {
        val sel = ObjCRuntime.sel("isFractionalHeight")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property isAbsolute
    open fun isAbsolute(): Boolean {
        val sel = ObjCRuntime.sel("isAbsolute")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property isEstimated
    open fun isEstimated(): Boolean {
        val sel = ObjCRuntime.sel("isEstimated")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property dimension
    open fun dimension(): Double {
        val sel = ObjCRuntime.sel("dimension")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
}

