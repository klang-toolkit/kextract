package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionViewFlowLayout
 * Superclass: NSCollectionViewLayout
 */
open class NSCollectionViewFlowLayout(override val ptr: MemorySegment) : NSCollectionViewLayout(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionViewFlowLayout") }
        
    }
    
    open fun sectionAtIndexIsCollapsed(sectionIndex: Long): Boolean {
        val sel = ObjCRuntime.sel("sectionAtIndexIsCollapsed:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, sectionIndex) as Boolean
    }
    
    open fun collapseSectionAtIndex(sectionIndex: Long): Unit {
        val sel = ObjCRuntime.sel("collapseSectionAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, sectionIndex)
    }
    
    open fun expandSectionAtIndex(sectionIndex: Long): Unit {
        val sel = ObjCRuntime.sel("expandSectionAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, sectionIndex)
    }
    
    // @property minimumLineSpacing
    open fun minimumLineSpacing(): Double {
        val sel = ObjCRuntime.sel("minimumLineSpacing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMinimumLineSpacing(value: Double) {
        val sel = ObjCRuntime.sel("setMinimumLineSpacing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property minimumInteritemSpacing
    open fun minimumInteritemSpacing(): Double {
        val sel = ObjCRuntime.sel("minimumInteritemSpacing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMinimumInteritemSpacing(value: Double) {
        val sel = ObjCRuntime.sel("setMinimumInteritemSpacing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property itemSize
    open fun itemSize(): MemorySegment {
        val sel = ObjCRuntime.sel("itemSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setItemSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setItemSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property estimatedItemSize
    open fun estimatedItemSize(): MemorySegment {
        val sel = ObjCRuntime.sel("estimatedItemSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setEstimatedItemSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setEstimatedItemSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property scrollDirection
    open fun scrollDirection(): MemorySegment {
        val sel = ObjCRuntime.sel("scrollDirection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setScrollDirection(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setScrollDirection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property headerReferenceSize
    open fun headerReferenceSize(): MemorySegment {
        val sel = ObjCRuntime.sel("headerReferenceSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setHeaderReferenceSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHeaderReferenceSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property footerReferenceSize
    open fun footerReferenceSize(): MemorySegment {
        val sel = ObjCRuntime.sel("footerReferenceSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setFooterReferenceSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFooterReferenceSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property sectionInset
    open fun sectionInset(): MemorySegment {
        val sel = ObjCRuntime.sel("sectionInset")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets"), ptr, sel) as MemorySegment
    }
    open fun setSectionInset(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSectionInset:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets")))
    }
    
    // @property sectionHeadersPinToVisibleBounds
    open fun sectionHeadersPinToVisibleBounds(): Boolean {
        val sel = ObjCRuntime.sel("sectionHeadersPinToVisibleBounds")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSectionHeadersPinToVisibleBounds(value: Boolean) {
        val sel = ObjCRuntime.sel("setSectionHeadersPinToVisibleBounds:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property sectionFootersPinToVisibleBounds
    open fun sectionFootersPinToVisibleBounds(): Boolean {
        val sel = ObjCRuntime.sel("sectionFootersPinToVisibleBounds")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSectionFootersPinToVisibleBounds(value: Boolean) {
        val sel = ObjCRuntime.sel("setSectionFootersPinToVisibleBounds:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

