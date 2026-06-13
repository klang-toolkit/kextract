package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSRotationGestureRecognizer
 * Superclass: NSGestureRecognizer
 */
open class NSRotationGestureRecognizer(override val ptr: MemorySegment) : NSGestureRecognizer(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSRotationGestureRecognizer") }
        
    }
    
    // @property rotation
    open fun rotation(): Double {
        val sel = ObjCRuntime.sel("rotation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setRotation(value: Double) {
        val sel = ObjCRuntime.sel("setRotation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property rotationInDegrees
    open fun rotationInDegrees(): Double {
        val sel = ObjCRuntime.sel("rotationInDegrees")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setRotationInDegrees(value: Double) {
        val sel = ObjCRuntime.sel("setRotationInDegrees:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

