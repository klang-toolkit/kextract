package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSOpenGLLayer
 * Superclass: CAOpenGLLayer
 */
open class NSOpenGLLayer(override val ptr: MemorySegment) : CAOpenGLLayer(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSOpenGLLayer") }
        
    }
    
    open fun openGLPixelFormatForDisplayMask(mask: Int): MemorySegment {
        val sel = ObjCRuntime.sel("openGLPixelFormatForDisplayMask:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, mask) as MemorySegment
    }
    
    open fun openGLContextForPixelFormat(pixelFormat: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("openGLContextForPixelFormat:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, pixelFormat) as MemorySegment
    }
    
    open fun canDrawInOpenGLContext_pixelFormat_forLayerTime_displayTime(context: MemorySegment, pixelFormat: MemorySegment, t: Double, ts: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("canDrawInOpenGLContext:pixelFormat:forLayerTime:displayTime:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, context, pixelFormat, t, ts) as Boolean
    }
    
    open fun drawInOpenGLContext_pixelFormat_forLayerTime_displayTime(context: MemorySegment, pixelFormat: MemorySegment, t: Double, ts: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawInOpenGLContext:pixelFormat:forLayerTime:displayTime:")
        ObjCRuntime.msgSend(null, ptr, sel, context, pixelFormat, t, ts)
    }
    
    // @property view
    open fun view(): MemorySegment {
        val sel = ObjCRuntime.sel("view")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property openGLPixelFormat
    open fun openGLPixelFormat(): MemorySegment {
        val sel = ObjCRuntime.sel("openGLPixelFormat")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setOpenGLPixelFormat(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setOpenGLPixelFormat:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property openGLContext
    open fun openGLContext(): MemorySegment {
        val sel = ObjCRuntime.sel("openGLContext")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setOpenGLContext(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setOpenGLContext:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

