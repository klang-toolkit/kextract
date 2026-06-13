package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionViewFlowLayoutInvalidationContext
 * Superclass: NSCollectionViewLayoutInvalidationContext
 */
open class NSCollectionViewFlowLayoutInvalidationContext(override val ptr: MemorySegment) : NSCollectionViewLayoutInvalidationContext(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionViewFlowLayoutInvalidationContext") }
        
    }
    
    // @property invalidateFlowLayoutDelegateMetrics
    open fun invalidateFlowLayoutDelegateMetrics(): Boolean {
        val sel = ObjCRuntime.sel("invalidateFlowLayoutDelegateMetrics")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setInvalidateFlowLayoutDelegateMetrics(value: Boolean) {
        val sel = ObjCRuntime.sel("setInvalidateFlowLayoutDelegateMetrics:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property invalidateFlowLayoutAttributes
    open fun invalidateFlowLayoutAttributes(): Boolean {
        val sel = ObjCRuntime.sel("invalidateFlowLayoutAttributes")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setInvalidateFlowLayoutAttributes(value: Boolean) {
        val sel = ObjCRuntime.sel("setInvalidateFlowLayoutAttributes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

