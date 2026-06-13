package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPressureConfiguration
 * Superclass: NSObject
 */
open class NSPressureConfiguration(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPressureConfiguration") }
        
    }
    
    open fun initWithPressureBehavior(pressureBehavior: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithPressureBehavior:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, pressureBehavior) as MemorySegment
    }
    
    open fun `set`(): Unit {
        val sel = ObjCRuntime.sel("set")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property pressureBehavior
    open fun pressureBehavior(): MemorySegment {
        val sel = ObjCRuntime.sel("pressureBehavior")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

