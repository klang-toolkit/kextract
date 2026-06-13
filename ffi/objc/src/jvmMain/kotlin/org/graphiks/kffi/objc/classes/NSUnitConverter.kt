package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitConverter
 * Superclass: NSObject
 */
open class NSUnitConverter(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitConverter") }
        
    }
    
    open fun baseUnitValueFromValue(value: Double): Double {
        val sel = ObjCRuntime.sel("baseUnitValueFromValue:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, value) as Double
    }
    
    open fun valueFromBaseUnitValue(baseUnitValue: Double): Double {
        val sel = ObjCRuntime.sel("valueFromBaseUnitValue:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, baseUnitValue) as Double
    }
    
}

