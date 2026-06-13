package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSOpenGLContext
 * Superclass: NSObject
 * Protocols: NSLocking
 */
open class NSOpenGLContext(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSOpenGLContext") }
        
        fun clearCurrentContext(): Unit {
            val sel = ObjCRuntime.sel("clearCurrentContext")
            ObjCRuntime.msgSend(null, _class, sel)
        }
        
        fun currentContext(): MemorySegment {
            val sel = ObjCRuntime.sel("currentContext")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithFormat_shareContext(format: MemorySegment, share: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFormat:shareContext:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, format, share) as MemorySegment
    }
    
    open fun initWithCGLContextObj(context: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCGLContextObj:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, context) as MemorySegment
    }
    
    open fun setFullScreen(): Unit {
        val sel = ObjCRuntime.sel("setFullScreen")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun setOffScreen_width_height_rowbytes(baseaddr: MemorySegment, width: Int, height: Int, rowbytes: Int): Unit {
        val sel = ObjCRuntime.sel("setOffScreen:width:height:rowbytes:")
        ObjCRuntime.msgSend(null, ptr, sel, baseaddr, width, height, rowbytes)
    }
    
    open fun clearDrawable(): Unit {
        val sel = ObjCRuntime.sel("clearDrawable")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun update(): Unit {
        val sel = ObjCRuntime.sel("update")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun flushBuffer(): Unit {
        val sel = ObjCRuntime.sel("flushBuffer")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun makeCurrentContext(): Unit {
        val sel = ObjCRuntime.sel("makeCurrentContext")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun copyAttributesFromContext_withMask(context: MemorySegment, mask: Int): Unit {
        val sel = ObjCRuntime.sel("copyAttributesFromContext:withMask:")
        ObjCRuntime.msgSend(null, ptr, sel, context, mask)
    }
    
    open fun setValues_forParameter(vals: MemorySegment, param: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setValues:forParameter:")
        ObjCRuntime.msgSend(null, ptr, sel, vals, param)
    }
    
    open fun getValues_forParameter(vals: MemorySegment, param: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getValues:forParameter:")
        ObjCRuntime.msgSend(null, ptr, sel, vals, param)
    }
    
    open fun createTexture_fromView_internalFormat(target: Int, view: MemorySegment, format: Int): Unit {
        val sel = ObjCRuntime.sel("createTexture:fromView:internalFormat:")
        ObjCRuntime.msgSend(null, ptr, sel, target, view, format)
    }
    
    // @property pixelFormat
    open fun pixelFormat(): MemorySegment {
        val sel = ObjCRuntime.sel("pixelFormat")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
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
    
    // @property currentContext
    open fun currentContext(): MemorySegment {
        val sel = ObjCRuntime.sel("currentContext")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property currentVirtualScreen
    open fun currentVirtualScreen(): Int {
        val sel = ObjCRuntime.sel("currentVirtualScreen")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    open fun setCurrentVirtualScreen(value: Int) {
        val sel = ObjCRuntime.sel("setCurrentVirtualScreen:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property CGLContextObj
    open fun CGLContextObj(): MemorySegment {
        val sel = ObjCRuntime.sel("CGLContextObj")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSOpenGLPixelBuffer on NSOpenGLContext ─────────────────────────────────────────

fun NSOpenGLContext.setPixelBuffer_cubeMapFace_mipMapLevel_currentVirtualScreen(pixelBuffer: MemorySegment, face: Int, level: Int, screen: Int): Unit {
    val sel = ObjCRuntime.sel("setPixelBuffer:cubeMapFace:mipMapLevel:currentVirtualScreen:")
    ObjCRuntime.msgSend(null, this.ptr, sel, pixelBuffer, face, level, screen)
}

fun NSOpenGLContext.pixelBuffer(): MemorySegment {
    val sel = ObjCRuntime.sel("pixelBuffer")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSOpenGLContext.pixelBufferCubeMapFace(): Int {
    val sel = ObjCRuntime.sel("pixelBufferCubeMapFace")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, this.ptr, sel) as Int
}

fun NSOpenGLContext.pixelBufferMipMapLevel(): Int {
    val sel = ObjCRuntime.sel("pixelBufferMipMapLevel")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, this.ptr, sel) as Int
}

fun NSOpenGLContext.setTextureImageToPixelBuffer_colorBuffer(pixelBuffer: MemorySegment, source: Int): Unit {
    val sel = ObjCRuntime.sel("setTextureImageToPixelBuffer:colorBuffer:")
    ObjCRuntime.msgSend(null, this.ptr, sel, pixelBuffer, source)
}

