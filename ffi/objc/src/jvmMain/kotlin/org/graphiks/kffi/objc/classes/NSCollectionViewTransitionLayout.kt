package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionViewTransitionLayout
 * Superclass: NSCollectionViewLayout
 */
open class NSCollectionViewTransitionLayout(override val ptr: MemorySegment) : NSCollectionViewLayout(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionViewTransitionLayout") }
        
    }
    
    open fun initWithCurrentLayout_nextLayout(currentLayout: MemorySegment, newLayout: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCurrentLayout:nextLayout:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, currentLayout, newLayout) as MemorySegment
    }
    
    open fun updateValue_forAnimatedKey(value: Double, key: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("updateValue:forAnimatedKey:")
        ObjCRuntime.msgSend(null, ptr, sel, value, key)
    }
    
    open fun valueForAnimatedKey(key: MemorySegment): Double {
        val sel = ObjCRuntime.sel("valueForAnimatedKey:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, key) as Double
    }
    
    // @property transitionProgress
    open fun transitionProgress(): Double {
        val sel = ObjCRuntime.sel("transitionProgress")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setTransitionProgress(value: Double) {
        val sel = ObjCRuntime.sel("setTransitionProgress:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property currentLayout
    open fun currentLayout(): MemorySegment {
        val sel = ObjCRuntime.sel("currentLayout")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property nextLayout
    open fun nextLayout(): MemorySegment {
        val sel = ObjCRuntime.sel("nextLayout")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

