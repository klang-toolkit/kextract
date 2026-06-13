package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionLayoutBoundarySupplementaryItem
 * Superclass: NSCollectionLayoutSupplementaryItem
 * Protocols: NSCopying
 */
open class NSCollectionLayoutBoundarySupplementaryItem(override val ptr: MemorySegment) : NSCollectionLayoutSupplementaryItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionLayoutBoundarySupplementaryItem") }
        
        fun boundarySupplementaryItemWithLayoutSize_elementKind_alignment(layoutSize: MemorySegment, elementKind: MemorySegment, alignment: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("boundarySupplementaryItemWithLayoutSize:elementKind:alignment:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, layoutSize, elementKind, alignment) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun boundarySupplementaryItemWithLayoutSize_elementKind_alignment(layoutSize: MemorySegment, elementKind: String, alignment: MemorySegment): MemorySegment = boundarySupplementaryItemWithLayoutSize_elementKind_alignment(layoutSize, ObjCRuntime.newNSString(Arena.global(), elementKind), alignment)
        
        fun boundarySupplementaryItemWithLayoutSize_elementKind_alignment_absoluteOffset(layoutSize: MemorySegment, elementKind: MemorySegment, alignment: MemorySegment, absoluteOffset: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("boundarySupplementaryItemWithLayoutSize:elementKind:alignment:absoluteOffset:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, layoutSize, elementKind, alignment, ObjCRuntime.ObjCStructArg(absoluteOffset, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun boundarySupplementaryItemWithLayoutSize_elementKind_alignment_absoluteOffset(layoutSize: MemorySegment, elementKind: String, alignment: MemorySegment, absoluteOffset: MemorySegment): MemorySegment = boundarySupplementaryItemWithLayoutSize_elementKind_alignment_absoluteOffset(layoutSize, ObjCRuntime.newNSString(Arena.global(), elementKind), alignment, absoluteOffset)
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    override fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property extendsBoundary
    open fun extendsBoundary(): Boolean {
        val sel = ObjCRuntime.sel("extendsBoundary")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setExtendsBoundary(value: Boolean) {
        val sel = ObjCRuntime.sel("setExtendsBoundary:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property pinToVisibleBounds
    open fun pinToVisibleBounds(): Boolean {
        val sel = ObjCRuntime.sel("pinToVisibleBounds")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPinToVisibleBounds(value: Boolean) {
        val sel = ObjCRuntime.sel("setPinToVisibleBounds:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property alignment
    open fun alignment(): MemorySegment {
        val sel = ObjCRuntime.sel("alignment")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property offset
    open fun offset(): MemorySegment {
        val sel = ObjCRuntime.sel("offset")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel) as MemorySegment
    }
    
}

