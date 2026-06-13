package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSOpenGLView
 * Superclass: NSView
 */
open class NSOpenGLView(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSOpenGLView") }
        
        fun defaultPixelFormat(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultPixelFormat")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithFrame_pixelFormat(frameRect: MemorySegment, format: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFrame:pixelFormat:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), format) as MemorySegment
    }
    
    open fun clearGLContext(): Unit {
        val sel = ObjCRuntime.sel("clearGLContext")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun update(): Unit {
        val sel = ObjCRuntime.sel("update")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun reshape(): Unit {
        val sel = ObjCRuntime.sel("reshape")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun prepareOpenGL(): Unit {
        val sel = ObjCRuntime.sel("prepareOpenGL")
        ObjCRuntime.msgSend(null, ptr, sel)
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
    
    // @property pixelFormat
    open fun pixelFormat(): MemorySegment {
        val sel = ObjCRuntime.sel("pixelFormat")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPixelFormat(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPixelFormat:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property wantsBestResolutionOpenGLSurface
    open fun wantsBestResolutionOpenGLSurface(): Boolean {
        val sel = ObjCRuntime.sel("wantsBestResolutionOpenGLSurface")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setWantsBestResolutionOpenGLSurface(value: Boolean) {
        val sel = ObjCRuntime.sel("setWantsBestResolutionOpenGLSurface:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property wantsExtendedDynamicRangeOpenGLSurface
    open fun wantsExtendedDynamicRangeOpenGLSurface(): Boolean {
        val sel = ObjCRuntime.sel("wantsExtendedDynamicRangeOpenGLSurface")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setWantsExtendedDynamicRangeOpenGLSurface(value: Boolean) {
        val sel = ObjCRuntime.sel("setWantsExtendedDynamicRangeOpenGLSurface:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

