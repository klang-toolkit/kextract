package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSOpenGLPixelBuffer
 * Superclass: NSObject
 */
open class NSOpenGLPixelBuffer(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSOpenGLPixelBuffer") }
        
    }
    
    open fun initWithTextureTarget_textureInternalFormat_textureMaxMipMapLevel_pixelsWide_pixelsHigh(target: Int, format: Int, maxLevel: Int, pixelsWide: Int, pixelsHigh: Int): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTextureTarget:textureInternalFormat:textureMaxMipMapLevel:pixelsWide:pixelsHigh:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, target, format, maxLevel, pixelsWide, pixelsHigh) as MemorySegment
    }
    
    open fun initWithCGLPBufferObj(pbuffer: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCGLPBufferObj:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, pbuffer) as MemorySegment
    }
    
    // @property CGLPBufferObj
    open fun CGLPBufferObj(): MemorySegment {
        val sel = ObjCRuntime.sel("CGLPBufferObj")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property pixelsWide
    open fun pixelsWide(): Int {
        val sel = ObjCRuntime.sel("pixelsWide")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property pixelsHigh
    open fun pixelsHigh(): Int {
        val sel = ObjCRuntime.sel("pixelsHigh")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property textureTarget
    open fun textureTarget(): Int {
        val sel = ObjCRuntime.sel("textureTarget")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property textureInternalFormat
    open fun textureInternalFormat(): Int {
        val sel = ObjCRuntime.sel("textureInternalFormat")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property textureMaxMipMapLevel
    open fun textureMaxMipMapLevel(): Int {
        val sel = ObjCRuntime.sel("textureMaxMipMapLevel")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
}

