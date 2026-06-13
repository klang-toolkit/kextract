package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: CAOpenGLLayer
 * Superclass: CALayer
 */
open class CAOpenGLLayer(override val ptr: MemorySegment) : CALayer(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("CAOpenGLLayer") }
        
    }
    
    open fun canDrawInCGLContext_pixelFormat_forLayerTime_displayTime(ctx: MemorySegment, pf: MemorySegment, t: Double, ts: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("canDrawInCGLContext:pixelFormat:forLayerTime:displayTime:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, ctx, pf, t, ts) as Boolean
    }
    
    open fun drawInCGLContext_pixelFormat_forLayerTime_displayTime(ctx: MemorySegment, pf: MemorySegment, t: Double, ts: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawInCGLContext:pixelFormat:forLayerTime:displayTime:")
        ObjCRuntime.msgSend(null, ptr, sel, ctx, pf, t, ts)
    }
    
    open fun copyCGLPixelFormatForDisplayMask(mask: Int): MemorySegment {
        val sel = ObjCRuntime.sel("copyCGLPixelFormatForDisplayMask:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, mask) as MemorySegment
    }
    
    open fun releaseCGLPixelFormat(pf: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("releaseCGLPixelFormat:")
        ObjCRuntime.msgSend(null, ptr, sel, pf)
    }
    
    open fun copyCGLContextForPixelFormat(pf: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("copyCGLContextForPixelFormat:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, pf) as MemorySegment
    }
    
    open fun releaseCGLContext(ctx: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("releaseCGLContext:")
        ObjCRuntime.msgSend(null, ptr, sel, ctx)
    }
    
    // @property asynchronous
    open fun isAsynchronous(): Boolean {
        val sel = ObjCRuntime.sel("isAsynchronous")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAsynchronous(value: Boolean) {
        val sel = ObjCRuntime.sel("setAsynchronous:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property colorspace
    open fun colorspace(): MemorySegment {
        val sel = ObjCRuntime.sel("colorspace")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setColorspace(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setColorspace:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property wantsExtendedDynamicRangeContent
    override fun wantsExtendedDynamicRangeContent(): Boolean {
        val sel = ObjCRuntime.sel("wantsExtendedDynamicRangeContent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    override fun setWantsExtendedDynamicRangeContent(value: Boolean) {
        val sel = ObjCRuntime.sel("setWantsExtendedDynamicRangeContent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _glPriv: MemorySegment
}

