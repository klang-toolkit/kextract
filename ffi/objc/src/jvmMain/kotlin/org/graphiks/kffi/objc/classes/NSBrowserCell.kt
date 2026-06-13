package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSBrowserCell
 * Superclass: NSCell
 */
open class NSBrowserCell(override val ptr: MemorySegment) : NSCell(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSBrowserCell") }
        
        fun branchImage(): MemorySegment {
            val sel = ObjCRuntime.sel("branchImage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun highlightedBranchImage(): MemorySegment {
            val sel = ObjCRuntime.sel("highlightedBranchImage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    override fun initTextCell(string: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initTextCell:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, string) as MemorySegment
    }
    
    override fun initImageCell(image: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initImageCell:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, image) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun highlightColorInView(controlView: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("highlightColorInView:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, controlView) as MemorySegment
    }
    
    open fun reset(): Unit {
        val sel = ObjCRuntime.sel("reset")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun `set`(): Unit {
        val sel = ObjCRuntime.sel("set")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property branchImage
    open fun branchImage(): MemorySegment {
        val sel = ObjCRuntime.sel("branchImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property highlightedBranchImage
    open fun highlightedBranchImage(): MemorySegment {
        val sel = ObjCRuntime.sel("highlightedBranchImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property leaf
    open fun isLeaf(): Boolean {
        val sel = ObjCRuntime.sel("isLeaf")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setLeaf(value: Boolean) {
        val sel = ObjCRuntime.sel("setLeaf:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property loaded
    open fun isLoaded(): Boolean {
        val sel = ObjCRuntime.sel("isLoaded")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setLoaded(value: Boolean) {
        val sel = ObjCRuntime.sel("setLoaded:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property image
    override fun image(): MemorySegment {
        val sel = ObjCRuntime.sel("image")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    override fun setImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property alternateImage
    open fun alternateImage(): MemorySegment {
        val sel = ObjCRuntime.sel("alternateImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAlternateImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAlternateImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

