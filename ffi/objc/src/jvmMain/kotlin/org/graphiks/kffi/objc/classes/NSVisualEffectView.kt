package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSVisualEffectView
 * Superclass: NSView
 */
open class NSVisualEffectView(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSVisualEffectView") }
        
    }
    
    override fun viewDidMoveToWindow(): Unit {
        val sel = ObjCRuntime.sel("viewDidMoveToWindow")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    override fun viewWillMoveToWindow(newWindow: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("viewWillMoveToWindow:")
        ObjCRuntime.msgSend(null, ptr, sel, newWindow)
    }
    
    // @property material
    open fun material(): MemorySegment {
        val sel = ObjCRuntime.sel("material")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMaterial(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMaterial:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property interiorBackgroundStyle
    open fun interiorBackgroundStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("interiorBackgroundStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property blendingMode
    open fun blendingMode(): MemorySegment {
        val sel = ObjCRuntime.sel("blendingMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBlendingMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBlendingMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property state
    open fun state(): MemorySegment {
        val sel = ObjCRuntime.sel("state")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setState(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setState:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property maskImage
    open fun maskImage(): MemorySegment {
        val sel = ObjCRuntime.sel("maskImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMaskImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMaskImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property emphasized
    open fun isEmphasized(): Boolean {
        val sel = ObjCRuntime.sel("isEmphasized")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEmphasized(value: Boolean) {
        val sel = ObjCRuntime.sel("setEmphasized:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

