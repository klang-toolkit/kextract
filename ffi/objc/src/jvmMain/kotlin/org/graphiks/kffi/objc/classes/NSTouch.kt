package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTouch
 * Superclass: NSObject
 * Protocols: NSCopying
 */
open class NSTouch(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTouch") }
        
    }
    
    // @property identity
    /** @return id<NSObject,NSCopying> */
    open fun identity(): MemorySegment {
        val sel = ObjCRuntime.sel("identity")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property phase
    open fun phase(): MemorySegment {
        val sel = ObjCRuntime.sel("phase")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property normalizedPosition
    open fun normalizedPosition(): MemorySegment {
        val sel = ObjCRuntime.sel("normalizedPosition")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel) as MemorySegment
    }
    
    // @property resting
    open fun isResting(): Boolean {
        val sel = ObjCRuntime.sel("isResting")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property device
    open fun device(): MemorySegment {
        val sel = ObjCRuntime.sel("device")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property deviceSize
    open fun deviceSize(): MemorySegment {
        val sel = ObjCRuntime.sel("deviceSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSTouchBar on NSTouch ─────────────────────────────────────────

fun NSTouch.locationInView(view: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("locationInView:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), this.ptr, sel, view) as MemorySegment
}

fun NSTouch.previousLocationInView(view: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("previousLocationInView:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), this.ptr, sel, view) as MemorySegment
}

fun NSTouch.type(): MemorySegment {
    val sel = ObjCRuntime.sel("type")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

