package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSOpenGLPixelFormat
 * Superclass: NSObject
 * Protocols: NSCoding
 */
open class NSOpenGLPixelFormat(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSOpenGLPixelFormat") }
        
    }
    
    open fun initWithCGLPixelFormatObj(format: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCGLPixelFormatObj:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, format) as MemorySegment
    }
    
    open fun initWithAttributes(attribs: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithAttributes:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, attribs) as MemorySegment
    }
    
    open fun initWithData(attribs: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithData:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, attribs) as MemorySegment
    }
    
    open fun attributes(): MemorySegment {
        val sel = ObjCRuntime.sel("attributes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun setAttributes(attribs: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setAttributes:")
        ObjCRuntime.msgSend(null, ptr, sel, attribs)
    }
    
    open fun getValues_forAttribute_forVirtualScreen(vals: MemorySegment, attrib: Int, screen: Int): Unit {
        val sel = ObjCRuntime.sel("getValues:forAttribute:forVirtualScreen:")
        ObjCRuntime.msgSend(null, ptr, sel, vals, attrib, screen)
    }
    
    // @property numberOfVirtualScreens
    open fun numberOfVirtualScreens(): Int {
        val sel = ObjCRuntime.sel("numberOfVirtualScreens")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property CGLPixelFormatObj
    open fun CGLPixelFormatObj(): MemorySegment {
        val sel = ObjCRuntime.sel("CGLPixelFormatObj")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

