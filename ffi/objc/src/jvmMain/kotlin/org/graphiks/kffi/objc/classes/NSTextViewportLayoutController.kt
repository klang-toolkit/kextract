package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextViewportLayoutController
 * Superclass: NSObject
 */
open class NSTextViewportLayoutController(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextViewportLayoutController") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithTextLayoutManager(textLayoutManager: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTextLayoutManager:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, textLayoutManager) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun layoutViewport(): Unit {
        val sel = ObjCRuntime.sel("layoutViewport")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun relocateViewportToTextLocation(textLocation: MemorySegment): Double {
        val sel = ObjCRuntime.sel("relocateViewportToTextLocation:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, textLocation) as Double
    }
    
    open fun adjustViewportByVerticalOffset(verticalOffset: Double): Unit {
        val sel = ObjCRuntime.sel("adjustViewportByVerticalOffset:")
        ObjCRuntime.msgSend(null, ptr, sel, verticalOffset)
    }
    
    // @property delegate
    /** @return id<NSTextViewportLayoutControllerDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property textLayoutManager
    open fun textLayoutManager(): MemorySegment {
        val sel = ObjCRuntime.sel("textLayoutManager")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property viewportBounds
    open fun viewportBounds(): MemorySegment {
        val sel = ObjCRuntime.sel("viewportBounds")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property viewportRange
    open fun viewportRange(): MemorySegment {
        val sel = ObjCRuntime.sel("viewportRange")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

